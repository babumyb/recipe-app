package app.recipe.restapi.dto;

import java.util.Collection;

import app.recipe.restapi.entity.Enums.Type;
import app.recipe.restapi.utils.MessageUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The Class RecipeDTO.
 */
@ApiModel(value = "Recipe", description = "Recipe information")
public class RecipeDTO {
	
	/** The id. */
	@ApiModelProperty(value = MessageUtils.TEXT_RECIPE_ID)
    private final Integer id;
	
	/** The creation date. */
	@ApiModelProperty(value = MessageUtils.TEXT_CREATION_DATETIME)
    private final String creationDate;
	
	/** The name. */
	@ApiModelProperty(value = MessageUtils.TEXT_RECIPE_NAME)
    private final String name;
	
	/** The instructions. */
	@ApiModelProperty(value = MessageUtils.TEXT_INSTRUCTIONS)
    private final String instructions;
	
	/** The type. */
	@ApiModelProperty(value = MessageUtils.TEXT_TYPE)
    private final Type type;
	
	/** The serves. */
	@ApiModelProperty(value = MessageUtils.TEXT_SERVES)
    private final Integer serves;
	
	/** The ingredients. */
	@ApiModelProperty(value = MessageUtils.TEXT_COLLECTION_INGREDIENTS)
    private final Collection<RecipeIngredientDTO> ingredients;
	
	/** The category. */
	@ApiModelProperty(value = MessageUtils.TEXT_CATEGORY)
    private final RecipeCategoryDTO category;

    /**
     * Instantiates a new recipe DTO.
     *
     * @param id the id
     * @param name the name
     * @param instructions the instructions
     * @param ingredients the ingredients
     * @param category the category
     * @param creationDate the creation date
     * @param type the type
     * @param serves the serves
     */
    public RecipeDTO(final Integer id, final String name, final String instructions,
                     final Collection<RecipeIngredientDTO> ingredients,
                     final RecipeCategoryDTO category, final String creationDate, final Type type, final Integer serves) {
        this.id = id;
        this.name = name;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.category = category;
        this.creationDate = creationDate;
        this.type = type;
        this.serves = serves;
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
    public Collection<RecipeIngredientDTO> getIngredients() {
        return ingredients;
    }

    /**
     * Gets the category.
     *
     * @return the category
     */
    public RecipeCategoryDTO getCategory() {
        return category;
    }

	/**
	 * Gets the creation date.
	 *
	 * @return the creation date
	 */
	public String getCreationDate() {
		return creationDate;
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
    
    	
}
