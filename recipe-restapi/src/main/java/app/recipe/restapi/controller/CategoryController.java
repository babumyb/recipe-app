package app.recipe.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.recipe.restapi.data.RecipeCategoryRepository;
import app.recipe.restapi.dto.RecipeCategoryDTO;
import app.recipe.restapi.entity.RecipeCategory;
import app.recipe.restapi.exception.ExceptionResponse;
import app.recipe.restapi.exception.NotFoundException;
import app.recipe.restapi.service.RecipeCategoryConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * The Class CategoryController.
 */
@RestController
@CrossOrigin
@Api(tags = {"Categories"}, description = "Categories endpoint")
@RequestMapping(value = "/api/categories", produces = "application/json")
public class CategoryController {


	/** The repository. */
	private final RecipeCategoryRepository repository;
	
	/** The converter. */
	private final RecipeCategoryConverter converter;

	/**
	 * Instantiates a new category controller.
	 *
	 * @param repository the repository
	 * @param converter the converter
	 */
	@Autowired
	public CategoryController(final RecipeCategoryRepository repository, final RecipeCategoryConverter converter) {
		this.repository = repository;
		this.converter = converter;
	}

	/**
	 * Gets the categories.
	 *
	 * @return the categories
	 */
	@GetMapping("")
	@ApiOperation(value = "Gets the recipe categories",
	response = Collection.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = "Bad Request|NOT_FOUND", response = ExceptionResponse.class)})
	public ResponseEntity<Collection<RecipeCategoryDTO>> getCategories() {

		Iterable<RecipeCategory> categories = repository.findAll();

		if (!categories.iterator().hasNext()) {
			throw new NotFoundException("No categories found ");
		}

		return ResponseEntity.ok(StreamSupport
				.stream(categories.spliterator(), false)
				.map(converter::recipeCategoryToDTO)
				.collect(Collectors.toList())
				);
	}
}