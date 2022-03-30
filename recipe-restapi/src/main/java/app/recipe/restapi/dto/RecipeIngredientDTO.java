package app.recipe.restapi.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;

import app.recipe.restapi.entity.Enums.Measurement;
import app.recipe.restapi.utils.MessageUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The Class RecipeIngredientDTO.
 */
@ApiModel(value = "RecipeIngredient", description = "Recipe ingredient")
public class RecipeIngredientDTO {
	
	/** The id. */
	@ApiModelProperty(value = MessageUtils.TEXT_INGREDIENT_ID)
    private final Integer id; // ID of Ingredient, not RecipeIngredient
	
	/** The name. */
	@NotNull
    @NotEmpty
    @Size(max = 50, min = 3, message = MessageUtils.ALLOWED_CHARS_3_50)
	@ApiModelProperty(value = MessageUtils.TEXT_INGREDIENT_NAME)
    private final String name;
	
	/** The measurement. */
	@NotNull
	@ApiModelProperty(value = MessageUtils.TEXT_INGREDIENT_MEASUREMENT)
    private final Measurement measurement;
	
	/** The amount. */
	@NotNull
    @NotEmpty
	@ApiModelProperty(value = MessageUtils.TEXT_INGREDIENT_AMOUNT)
    private final Double amount;

    /**
     * Instantiates a new recipe ingredient DTO.
     *
     * @param id the id
     * @param name the name
     * @param measurement the measurement
     * @param amount the amount
     */
    @JsonCreator
    public RecipeIngredientDTO(final Integer id, final String name, final Measurement measurement, final Double amount) {
        this.id = id;
        this.name = name;
        this.measurement = measurement;
        this.amount = amount;
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
     * Gets the measurement.
     *
     * @return the measurement
     */
    public Measurement getMeasurement() {
        return measurement;
    }

    /**
     * Gets the amount.
     *
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }
}
