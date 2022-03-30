package app.recipe.restapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import app.recipe.restapi.data.IngredientRepository;
import app.recipe.restapi.data.RecipeRepository;
import app.recipe.restapi.dto.CreateIngredientDTO;
import app.recipe.restapi.dto.IngredientDTO;
import app.recipe.restapi.dto.ActionStatus;
import app.recipe.restapi.entity.Enums.Status;
import app.recipe.restapi.entity.Ingredient;
import app.recipe.restapi.entity.Recipe;
import app.recipe.restapi.exception.ExceptionResponse;
import app.recipe.restapi.exception.NotFoundException;
import app.recipe.restapi.exception.UniquenessViolationException;
import app.recipe.restapi.exception.UnprocessableEntity;
import app.recipe.restapi.exception.ValidationException;
import app.recipe.restapi.service.IngredientConverter;
import app.recipe.restapi.utils.MessageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * The Class IngredientController.
 */
@RestController
@CrossOrigin(origins = "*")
@Api(tags = {"Ingredients"}, description = "Ingredients endpoint")
@RequestMapping(value = "/api/ingredients", produces = "application/json")
public class IngredientController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(IngredientController.class);

	/** The ingredient repository. */
	private final IngredientRepository ingredientRepository;
	
	/** The recipe repository. */
	private final RecipeRepository recipeRepository;
	
	/** The converter. */
	private final IngredientConverter converter;

	/**
	 * Instantiates a new ingredient controller.
	 *
	 * @param ingredientRepository the ingredient repository
	 * @param recipeRepository the recipe repository
	 * @param converter the converter
	 */
	@Autowired
	public IngredientController(final IngredientRepository ingredientRepository, final RecipeRepository recipeRepository,
			final IngredientConverter converter) {
		this.ingredientRepository = ingredientRepository;
		this.recipeRepository = recipeRepository;
		this.converter = converter;
	}

	/**
	 * Gets the ingredients.
	 *
	 * @param name the name
	 * @param pageable the pageable
	 * @return the ingredients
	 */
	@GetMapping("/")
	@ApiOperation(value = "Gets the ingredients based on search parameter",
	response = IngredientDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = "Bad Request|NOT_FOUND", response = ExceptionResponse.class)})
	public ResponseEntity<Collection<IngredientDTO>> getIngredients(
			@ApiParam(value = MessageUtils.TEXT_INGREDIENT_NAME, required = false, example = "Milk")
			@Size(max = 50, min = 3, message = MessageUtils.ALLOWED_CHARS_3_50)
			@RequestParam(name = "name", required = false) final 
			String name,
			@PageableDefault(size = 40, sort = "ingredientId") final Pageable pageable
			) {
		LOGGER.debug("Name of ingredient is " + name);
		Iterable<Ingredient> ingredients = name != null 
				? ingredientRepository.findByIngredientNameContainingIgnoreCase(name) 
						: ingredientRepository.findAll(pageable);

		if (!ingredients.iterator().hasNext()) {
			throw new NotFoundException("No ingredients found " + name);
		}

		return ResponseEntity.ok(StreamSupport.stream(ingredients.spliterator(), false)
				.map(converter::ingredientToDTO)
				.collect(Collectors.toList()));
	}

	/**
	 * Gets the ingredients.
	 *
	 * @param id the id
	 * @return the ingredients
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "Gets the ingredients by the ingredient id",
	notes = "Provide the id of the ingredient",
	response = IngredientDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = MessageUtils.ERROR_BADREQUEST_NOTFOUND, response = ExceptionResponse.class)})
	public ResponseEntity<IngredientDTO> getIngredients(@ApiParam(value = MessageUtils.TEXT_INGREDIENT_ID, required = true, example = "1")
	@PathVariable("id") final Integer id) {
		LOGGER.debug("Id of ingredient is " + id);

		return ingredientRepository
				.findById(id)
				.map(converter::ingredientToDTO)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new NotFoundException("No ingredient found"));
	}

	/**
	 * Creates the ingredient.
	 *
	 * @param dto the dto
	 * @param bind the bind
	 * @return the response entity
	 */
	@PostMapping("/")
	@ApiOperation(value = "Create the ingredient",
	notes = "Ingrdient request to store",
	response = ActionStatus.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = MessageUtils.ERROR_BADREQUEST_NOTFOUND, response = ExceptionResponse.class),
			@ApiResponse(code = 422, message = MessageUtils.UNIQUE_VIOLATION_EXCEPTION, response = ExceptionResponse.class)})
	public ResponseEntity<ActionStatus> createIngredient(@Valid @RequestBody 
			@ApiParam(value = "Ingredient request", required = true, name = "Ingredient") final CreateIngredientDTO dto, 
			final BindingResult bind) {
		LOGGER.debug(" Create request is " + dto.toString());

		if (bind.hasErrors()) {
			throw new ValidationException("Value for 'name' must not be empty.");
		}

		if (ingredientRepository.findByIngredientNameEqualsIgnoreCase(dto.getName()).isPresent()) {
			throw new UniquenessViolationException(String.format("An ingredient with name '%s' already exists.",
					dto.getName()));
		}

		Ingredient ingredient = ingredientRepository.save(new Ingredient(dto.getName()));

		return ResponseEntity.ok(new ActionStatus(ingredient.getIngredientId(), Status.CREATED));
	}

	/**
	 * Delete ingredient.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletes the ingredient",
	notes = "Ingredient to delete",
	response = ActionStatus.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = MessageUtils.ERROR_BADREQUEST_NOTFOUND, response = ExceptionResponse.class),
			@ApiResponse(code = 422, message = MessageUtils.ERROR_UNPROCESSABLE_ENTITY, response = ExceptionResponse.class)})
	public ResponseEntity<ActionStatus> deleteIngredient(@ApiParam(value = MessageUtils.TEXT_INGREDIENT_ID, required = true, example = "1")
	@PathVariable final Integer id) {
		LOGGER.debug("Id of ingredient is " + id);

		Optional<Ingredient> toDelete = ingredientRepository.findById(id);

		if (!toDelete.isPresent()) {
			throw new NotFoundException("No ingredient found to delete " + toDelete.toString());
		}

		Collection<Recipe> recipesContainingIngredient =
				recipeRepository.findByIngredientName(toDelete.get().getIngredientName());

		if (recipesContainingIngredient.size() > 0) {

			throw new UnprocessableEntity(String.format(
					"Cannot delete ingredient since it is in use by the following recipes: %s. Delete them and then " +
							"try again.",
							recipesContainingIngredient.stream().map(Recipe::getRecipeName).collect(Collectors.toList())));
		}

		ingredientRepository.delete(toDelete.get());

		return ResponseEntity.ok(new ActionStatus(id, Status.DELETED));
	}

	/**
	 * Update ingredient.
	 *
	 * @param id the id
	 * @param dto the dto
	 * @param bind the bind
	 * @return the response entity
	 */
	@PatchMapping("/{id}")
	@ApiOperation(value = "Updates the ingredient",
	notes = "Ingredient request to update",
	response = ActionStatus.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = MessageUtils.ERROR_BADREQUEST_NOTFOUND, response = ExceptionResponse.class),
			@ApiResponse(code = 422, message = MessageUtils.ERROR_UNPROCESSABLE_ENTITY, response = ExceptionResponse.class)})
	public ResponseEntity<ActionStatus> updateIngredient(@PathVariable 
			@ApiParam(value = "Ingredient id request", required = true, name = "2") final Integer id,
			@Valid @RequestBody 
			@ApiParam(value = "Ingredient request", required = true, name = "Ingredient") final CreateIngredientDTO dto,
			final BindingResult bind) {

		LOGGER.debug("Id is " + id + " Request is " + dto.toString());

		if (bind.hasErrors()) {
			throw new ValidationException("Value for 'name' must not be empty.");
		}

		Optional<Ingredient> toUpdate = ingredientRepository.findById(id);

		if (!toUpdate.isPresent()) {
			throw new NotFoundException("Not found");
		}

		toUpdate.get().setIngredientName(dto.getName());
		ingredientRepository.save(toUpdate.get());

		return ResponseEntity.ok(new ActionStatus(id, Status.UPDATED));
	}
}
