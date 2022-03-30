package app.recipe.restapi.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import app.recipe.restapi.utils.MessageUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The Class CreateIngredientDTO.
 */
@ApiModel(value = "Ingredient", description = "Ingredient information")
public class CreateIngredientDTO {
    
    /** The name. */
    @NotNull
    @NotEmpty
    @Size(max = 50, min = 3, message = MessageUtils.ALLOWED_CHARS_3_50)
	@ApiModelProperty(value = MessageUtils.TEXT_INGREDIENT_NAME)
    private final String name;

    /**
     * Instantiates a new creates the ingredient DTO.
     *
     * @param name the name
     */
    @JsonCreator
    public CreateIngredientDTO(final String name) {
        this.name = name;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

	@Override
	public String toString() {
		return "CreateIngredientDTO [" + (name != null ? "name=" + name : "") + "]";
	}
    
    
}