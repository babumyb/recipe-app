package app.recipe.restapi.dto;

import java.util.Collection;

import org.springframework.data.domain.Pageable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The Class RecipeResponse.
 */
@ApiModel(value = "RecipeResponse", description = "Collection of recipes with page information")
public class RecipeResponse {
	
	/** The recipes. */
	@ApiModelProperty(value = "List of recipes", name = "recipes", notes = "Collection of recipes")
	private Collection<RecipeDTO> recipes;
	
	/** The pageable. */
	@ApiModelProperty(value = "Page Information", name = "pageInfo", notes = "Current page information")
	private Pageable pageable;
	
	/** The total pages. */
	@ApiModelProperty(value = "Total number of pages", name = "totalPages", notes = "Number of pages in search")
	private int totalPages;
	

	/**
	 * Instantiates a new recipe response.
	 *
	 * @param recipes the recipes
	 * @param pageable the pageable
	 * @param totalPages the total pages
	 */
	public RecipeResponse(final Collection<RecipeDTO> recipes, final Pageable pageable, final int totalPages) {
		super();
		this.recipes = recipes;
		this.pageable = pageable;
		this.totalPages = totalPages;
	}

	/**
	 * Gets the recipes.
	 *
	 * @return the recipes
	 */
	public Collection<RecipeDTO> getRecipes() {
		return recipes;
	}

	/**
	 * Sets the recipes.
	 *
	 * @param recipes the new recipes
	 */
	public void setRecipes(final Collection<RecipeDTO> recipes) {
		this.recipes = recipes;
	}

	/**
	 * Gets the pageable.
	 *
	 * @return the pageable
	 */
	public Pageable getPageable() {
		return pageable;
	}

	/**
	 * Sets the pageable.
	 *
	 * @param pageable the new pageable
	 */
	public void setPageable(final Pageable pageable) {
		this.pageable = pageable;
	}

	/**
	 * Gets the total pages.
	 *
	 * @return the total pages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * Sets the total pages.
	 *
	 * @param totalPages the new total pages
	 */
	public void setTotalPages(final int totalPages) {
		this.totalPages = totalPages;
	}
	

}
