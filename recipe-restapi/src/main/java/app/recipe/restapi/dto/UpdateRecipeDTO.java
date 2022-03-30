package app.recipe.restapi.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import app.recipe.restapi.entity.Enums.Type;
import app.recipe.restapi.utils.MessageUtils;
import app.recipe.restapi.validation.ElementsNotNull;
import app.recipe.restapi.validation.NotEmptyUnlessNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * The Class UpdateRecipeDTO.
 */
@ApiModel(value = "Recipe", description = "Recipe information")
public class UpdateRecipeDTO {
    
    /** The name. */
    @NotEmptyUnlessNull
    @Size(max = 50, min = 3, message = MessageUtils.ALLOWED_CHARS_3_50)
    @ApiModelProperty(value = MessageUtils.TEXT_RECIPE_NAME)
    private final String name;

    /** The instructions. */
    @NotEmptyUnlessNull
    @Size(max = 1000, min = 10, message = MessageUtils.ALLOWED_CHARS_10_1000)
    @ApiModelProperty(value = MessageUtils.TEXT_INSTRUCTIONS)
    private final String instructions;
    
    /** The type. */
    @ApiModelProperty(value = MessageUtils.TEXT_TYPE, required = false)
    private final Type type;
    
    /** The serves. */
    @ApiModelProperty(value = MessageUtils.TEXT_SERVES, required = false)
    private final Integer serves;

    /** The ingredients. */
    @NotEmptyUnlessNull
    @ElementsNotNull
    @ApiModelProperty(value = MessageUtils.TEXT_COLLECTION_INGREDIENTS)
    private final List<RecipeIngredientDTO> ingredients;

    /** The category. */
    @NotEmptyUnlessNull
    @Size(max = 50, min = 3, message = MessageUtils.ALLOWED_CHARS_3_50)
    @ApiModelProperty(value = MessageUtils.TEXT_RECIPE_CATEGORY_NAME)
    private final String category;

    /**
     * Instantiates a new update recipe DTO.
     *
     * @param name the name
     * @param instructions the instructions
     * @param ingredients the ingredients
     * @param category the category
     * @param type the type
     * @param serves the serves
     */
    @JsonCreator
    public UpdateRecipeDTO(final String name, final String instructions, final List<RecipeIngredientDTO> ingredients, final String category,
    		final Type type, final Integer serves) {
        this.name = name;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.category = category;
        this.type = type;
        this.serves = serves;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the instructions.
     *
     * @return the instructions
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * Gets the ingredients.
     *
     * @return the ingredients
     */
    public List<RecipeIngredientDTO> getIngredients() {
        return ingredients;
    }

    /**
     * Gets the category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Gets the serves.
	 *
	 * @return the serves
	 */
	public Integer getServes() {
		return serves;
	}

	@Override
	public String toString() {
		return "UpdateRecipeDTO [" + (name != null ? "name=" + name + ", " : "")
				+ (instructions != null ? "instructions=" + instructions + ", " : "")
				+ (type != null ? "type=" + type + ", " : "") + "serves=" + serves + ", "
				+ (ingredients != null ? "ingredients=" + ingredients + ", " : "")
				+ (category != null ? "category=" + category : "") + "]";
	}
    
	
}
