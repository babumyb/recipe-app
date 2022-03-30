package app.recipe.restapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.recipe.restapi.data.RecipeCategoryRepository;
import app.recipe.restapi.data.RecipeIngredientRepository;
import app.recipe.restapi.data.RecipeRepository;
import app.recipe.restapi.data.SearchSpecification;
import app.recipe.restapi.dto.CreateRecipeDTO;
import app.recipe.restapi.dto.RecipeDTO;
import app.recipe.restapi.dto.RecipeResponse;
import app.recipe.restapi.dto.ActionStatus;
import app.recipe.restapi.dto.SearchDto;
import app.recipe.restapi.dto.UpdateRecipeDTO;
import app.recipe.restapi.entity.Enums.Status;
import app.recipe.restapi.entity.Recipe;
import app.recipe.restapi.entity.RecipeCategory;
import app.recipe.restapi.exception.ExceptionResponse;
import app.recipe.restapi.exception.InvalidParameterCombinationException;
import app.recipe.restapi.exception.NotFoundException;
import app.recipe.restapi.exception.ValidationException;
import app.recipe.restapi.service.RecipeConverter;
import app.recipe.restapi.service.RecipeIngredientConverter;
import app.recipe.restapi.utils.DateTimeUtil;
import app.recipe.restapi.utils.MessageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * The Class RecipeController.
 */
@Validated
@RestController()
@CrossOrigin(origins = "*")
@Api(tags = {"Recipe"}, description = "Recipe endpoint")
@RequestMapping(value = "/api/recipes", produces = "application/json")
public class RecipeController {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);
	
	/** The recipe repository. */
	private final RecipeRepository recipeRepository;
	
	/** The category repository. */
	private final RecipeCategoryRepository categoryRepository;

	/** The recipe ingredient converter. */
	private final RecipeIngredientConverter recipeIngredientConverter;
	
	/** The recipe converter. */
	private final RecipeConverter recipeConverter;

	/**
	 * Instantiates a new recipe controller.
	 *
	 * @param recipeRepository the recipe repository
	 * @param recipeIngredientRepository the recipe ingredient repository
	 * @param categoryRepository the category repository
	 * @param recipeIngredientConverter the recipe ingredient converter
	 * @param recipeConverter the recipe converter
	 */
	@Autowired
	public RecipeController(final RecipeRepository recipeRepository, final RecipeIngredientRepository recipeIngredientRepository,
			final RecipeCategoryRepository categoryRepository,
			final RecipeIngredientConverter recipeIngredientConverter, final RecipeConverter recipeConverter) {
		this.recipeRepository = recipeRepository;
		this.categoryRepository = categoryRepository;
		this.recipeIngredientConverter = recipeIngredientConverter;
		this.recipeConverter = recipeConverter;
	}

	/**
	 * Gets the recipes.
	 *
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @param ingredient the ingredient
	 * @param serves the serves
	 * @param name the name
	 * @param pageable the pageable
	 * @return the recipes
	 */
	@GetMapping("")
	@ApiOperation(value = "Gets the recipes based on search parameters",
	notes = "One or more search parameters can be provided",
	response = RecipeResponse.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = "Bad Request|NOT_FOUND", response = ExceptionResponse.class)})
	public ResponseEntity<RecipeResponse> getRecipes(
			@ApiParam(value = "Search the recipes created from date", required = false, example = "02.03.2019 02:03") 
			@RequestParam (name = "createdFrom", required = false)  @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") final  
			LocalDateTime fromDate,
			@ApiParam(value = "Search the recipes created upto date", required = false, example = "02.03.2019 02:03")
			@RequestParam (name = "createdTo", required = false)  @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") final 
			LocalDateTime toDate,
			@ApiParam(value = MessageUtils.TEXT_INGREDIENT_NAME, required = false, example = "Milk")
			@RequestParam (name = "ingredient", required = false) @Size(max = 50, min = 3, message = MessageUtils.ALLOWED_CHARS_3_50) final 
			String ingredient,
			@ApiParam(value = MessageUtils.TEXT_SERVES, required = false, example = "2")
			@RequestParam (name = "serves", required = false)  @Max(value = 10, message = MessageUtils.ALLOWED_MAX_NUMBER_10) @Min(value = 1, message = MessageUtils.ALLOWED_MIN_NUMBER_1) final 
			Integer serves,
			@ApiParam(value = MessageUtils.TEXT_RECIPE_NAME, required = false, example = "Spaghetti")
			@RequestParam (name = "name", required = false)  @Size(max = 50, min = 3, message = MessageUtils.ALLOWED_CHARS_3_50) final 
			String name, 
			@PageableDefault(size = 40, sort = "recipeId") final Pageable pageable) {

		if (invalidParamCombination(fromDate, toDate)) { 
			throw new InvalidParameterCombinationException("createdFrom date " + DateTimeUtil.parseLocalDateTime(fromDate) + " is after createdTo date " + DateTimeUtil.parseLocalDateTime(toDate)); 
		}

		SearchDto searchDto = new SearchDto(fromDate, toDate, ingredient, serves, name);
		LOGGER.debug("Search parameters from request " + searchDto.toString());
		
		Page<Recipe> results = recipeRepository.findAll(SearchSpecification.searchPredicate(searchDto), pageable);

		if (results.getContent().isEmpty()) {
			throw new NotFoundException("No recipe found for search parameters " + searchDto.toString());
		}

		RecipeResponse response = new RecipeResponse(StreamSupport
				.stream(results.spliterator(), false)
				.map(recipeConverter::recipeToDTO)
				.collect(Collectors.toList()), results.getPageable(), results.getTotalPages());

		return ResponseEntity.ok(response);
	}

	/**
	 * Gets the recipe by id.
	 *
	 * @param id the id
	 * @return the recipe by id
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "Gets the recipes by the recipe id",
	notes = "Provide the id of the recipe",
	response = RecipeDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = MessageUtils.ERROR_BADREQUEST_NOTFOUND, response = ExceptionResponse.class)})
	public ResponseEntity<RecipeDTO> getRecipeById(@ApiParam(value = MessageUtils.TEXT_RECIPE_ID, required = true, example = "1") 
	@PathVariable final int id) {
		LOGGER.debug("Recipe id is " + id);
		return recipeRepository
				.findById(id)
				.map(recipeConverter::recipeToDTO)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new NotFoundException("No recipe found"));
	}

	/**
	 * Creates the recipe.
	 *
	 * @param dto the dto
	 * @param bind the bind
	 * @return the response entity
	 */
	@PostMapping("")
	@ApiOperation(value = "Create the receipe",
	notes = "Recipe request to store",
	response = ActionStatus.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = MessageUtils.ERROR_BADREQUEST_NOTFOUND, response = ExceptionResponse.class),
			@ApiResponse(code = 422, message = MessageUtils.ERROR_UNPROCESSABLE_ENTITY, response = ExceptionResponse.class)})
	public ResponseEntity<ActionStatus> createRecipe(
			@ApiParam(value = "Recipe request", required = true, name = "Recipe")
			@Valid @RequestBody final CreateRecipeDTO dto, 
			final BindingResult bind) {
		
		LOGGER.debug("Request is " + dto.toString());
		
		validateRequest(bind);

		Recipe recipe = new Recipe(dto.getName(), dto.getInstructions(), dto.getType(), dto.getServes());

		recipe.setIngredients(dto
				.getIngredients()
				.stream()
				.map(ingredientDTO -> recipeIngredientConverter.dtoToRecipeIngredient(ingredientDTO, recipe))
				.collect(Collectors.toList()));

		recipe.setCategory(categoryRepository
				.findByCategoryEqualsIgnoreCase(dto.getCategory())
				.orElse(new RecipeCategory(dto.getCategory())));

		Recipe recipeResponse =  recipeRepository.save(recipe);


		return ResponseEntity.ok(new ActionStatus(recipeResponse.getRecipeId(), Status.CREATED));
	}

	/**
	 * Update recipe.
	 *
	 * @param id the id
	 * @param dto the dto
	 * @param bind the bind
	 * @return the response entity
	 */
	@PatchMapping("/{id}")
	@ApiOperation(value = "Updates the receipe",
	notes = "Recipe request to update",
	response = ActionStatus.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = MessageUtils.ERROR_BADREQUEST_NOTFOUND, response = ExceptionResponse.class),
			@ApiResponse(code = 422, message = MessageUtils.ERROR_UNPROCESSABLE_ENTITY, response = ExceptionResponse.class)})
	public ResponseEntity<ActionStatus> updateRecipe(@ApiParam(value = MessageUtils.TEXT_RECIPE_ID, required = true, example = "1") 
	@PathVariable final Integer id, 
	@ApiParam(value = "Recipe id", required = true, name = "1")
	@Valid @RequestBody final UpdateRecipeDTO dto,
	final BindingResult bind) {
		
		LOGGER.debug("Id is " + id + ". Update reuest is " + dto.toString());
		
		validateRequest(bind);
		
		if (dto.getServes() != null && (dto.getServes() < 1 || dto.getServes() > 10)) {
			throw new ValidationException("serves should be between 1 and 10");
		}

		Optional<Recipe> recipe = recipeRepository.findById(id);

		if (!recipe.isPresent()) {
			throw new NotFoundException("No recipe found for update with id " + recipe.toString());
		}

		if (dto.getName() != null) {
			recipe.get().setRecipeName(dto.getName());
		}

		if (dto.getInstructions() != null) {
			recipe.get().setInstructions(dto.getInstructions());
		}
		
		if (dto.getServes() != null && dto.getServes() > 0) {
			recipe.get().setServes(dto.getServes());
		}
		
		if (dto.getInstructions() != null) {
			recipe.get().setInstructions(dto.getInstructions());
		}

		if (dto.getIngredients() != null) {
			recipe.get().clearIngredients();
			recipe.get().getIngredients().addAll(dto.getIngredients().stream()
					.map(ingredientDTO -> recipeIngredientConverter.dtoToRecipeIngredient(ingredientDTO, recipe.get()))
					.collect(Collectors.toList()));
		}

		if (dto.getCategory() != null) {
			recipe.get().setCategory(categoryRepository
					.findByCategoryEqualsIgnoreCase(dto.getCategory())
					.orElse(new RecipeCategory(dto.getCategory())));
		}
		
		Recipe recipeResponse =  recipeRepository.save(recipe.get());

		return ResponseEntity.ok(new ActionStatus(recipeResponse.getRecipeId(), Status.UPDATED));
	}

	/**
	 * Delete recipe.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletes the receipe",
	notes = "Recipe to delete",
	response = ActionStatus.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = MessageUtils.ERROR_BADREQUEST_NOTFOUND, response = ExceptionResponse.class),
			@ApiResponse(code = 422, message = MessageUtils.ERROR_UNPROCESSABLE_ENTITY, response = ExceptionResponse.class)})
	public ResponseEntity<ActionStatus> deleteRecipe(@ApiParam(value = MessageUtils.TEXT_RECIPE_ID, required = true, example = "1")
	@PathVariable final Integer id) {
		LOGGER.debug("Id is " + id);
		Optional<Recipe> recipe = recipeRepository.findById(id);

		if (!recipe.isPresent()) {
			throw new NotFoundException("No recipe found to delete " + recipe.toString());
		}

		recipeRepository.delete(recipe.get());

		return ResponseEntity.ok(new ActionStatus(id, Status.DELETED));
	}

	/**
	 * Invalid param combination.
	 *
	 * @param fromDate the from date
	 * @param toDateTime the to date time
	 * @return true, if successful
	 */
	private boolean invalidParamCombination(final LocalDateTime fromDate, final LocalDateTime toDateTime) {
		return fromDate != null && toDateTime != null && fromDate.isAfter(toDateTime);
	}

	/**
	 * Validate request.
	 *
	 * @param bind the bind
	 */
	private void validateRequest(final BindingResult bind) {
		if (bind.hasErrors()) {
			String message = bind
					.getFieldErrors()
					.stream()
					.map(fieldError -> String.format("%s - %s", fieldError.getField(), fieldError.getDefaultMessage()))
					.collect(Collectors.joining(", "));
			throw new ValidationException("Create recipe request has validation errors " + message);
		}
	}

}
