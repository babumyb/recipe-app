package app.recipe.restapi.dto;

import app.recipe.restapi.utils.MessageUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The Class RecipeCategoryDTO.
 */
@ApiModel(value = "RecipeCategory", description = "Recipe category")
public class RecipeCategoryDTO {
	
	/** The id. */
	@ApiModelProperty(value = MessageUtils.TEXT_RECIPE_CATEGORY_ID)
    private final Integer id;
	
	/** The name. */
	@ApiModelProperty(value = MessageUtils.TEXT_RECIPE_CATEGORY_NAME)
    private final String name;

    /**
     * Instantiates a new recipe category DTO.
     *
     * @param id the id
     * @param name the name
     */
    public RecipeCategoryDTO(final Integer id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
