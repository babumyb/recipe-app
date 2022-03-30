package app.recipe.restapi.entity;

import org.hibernate.annotations.GenericGenerator;

import app.recipe.restapi.entity.Enums.Measurement;

import javax.persistence.*;
import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class RecipeIngredient.
 */
@Entity
public class RecipeIngredient {
    
    /** The recipe ingredient id. */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String recipeIngredientId;

    /** The amount. */
    private double amount;
    
    /** The measurement. */
    private Measurement measurement;

    /** The ingredient. */
    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "ingredient_id", table = "recipe_ingredient")
    private Ingredient ingredient;

    /** The recipe. */
    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "recipe_id", table = "recipe_ingredient")
    private Recipe recipe;

    /**
     * Instantiates a new recipe ingredient.
     *
     * @param recipeIngredientId the recipe ingredient id
     * @param amount the amount
     * @param measurement the measurement
     * @param ingredient the ingredient
     * @param recipe the recipe
     */
    public RecipeIngredient(final String recipeIngredientId, final double amount, final Measurement measurement, final Ingredient ingredient,
                            final Recipe recipe) {
        this.recipeIngredientId = recipeIngredientId;
        this.amount = amount;
        this.measurement = measurement;
        this.ingredient = ingredient;
        this.recipe = recipe;
    }

    /**
     * Instantiates a new recipe ingredient.
     *
     * @param amount the amount
     * @param measurement the measurement
     * @param ingredient the ingredient
     * @param recipe the recipe
     */
    public RecipeIngredient(final double amount, final Measurement measurement, final Ingredient ingredient, final Recipe recipe) {
        this(null, amount, measurement, ingredient, recipe);
    }

    /**
     * Instantiates a new recipe ingredient.
     *
     * @param amount the amount
     * @param measurement the measurement
     * @param ingredient the ingredient
     */
    public RecipeIngredient(final double amount, final Measurement measurement, final Ingredient ingredient) {
        this(null, amount, measurement, ingredient, null);
    }

    /**
     * Instantiates a new recipe ingredient.
     */
    public RecipeIngredient() { }

    /**
     * Detach recipe.
     */
    public void detachRecipe() {
        recipe = null;
    }

    /**
     * Gets the recipe ingredient id.
     *
     * @return the recipe ingredient id
     */
    public String getRecipeIngredientId() {
        return recipeIngredientId;
    }

    /**
     * Gets the amount.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount.
     *
     * @param amount the new amount
     */
    public void setAmount(final double amount) {
        this.amount = amount;
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
     * Sets the measurement.
     *
     * @param measurement the new measurement
     */
    public void setMeasurement(final Measurement measurement) {
        this.measurement = measurement;
    }

    /**
     * Gets the ingredient.
     *
     * @return the ingredient
     */
    public Ingredient getIngredient() {
        return ingredient;
    }

    /**
     * Sets the ingredient.
     *
     * @param ingredient the new ingredient
     */
    public void setIngredient(final Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    /**
     * Gets the recipe.
     *
     * @return the recipe
     */
    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * Sets the recipe.
     *
     * @param recipe the new recipe
     */
    public void setRecipe(final Recipe recipe) {
        this.recipe = recipe;
    }

    /**
     * Equals.
     *
     * @param o the o
     * @return true, if successful
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
			return true;
		}
        if (o == null || getClass() != o.getClass()) {
			return false;
		}
        RecipeIngredient that = (RecipeIngredient) o;
        return amount == that.amount && Objects.equals(recipeIngredientId, that.recipeIngredientId) && measurement == that.measurement;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(recipeIngredientId, amount, measurement);
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "uuid='" + recipeIngredientId + '\'' +
                ", amount=" + amount +
                ", measurement=" + measurement +
                '}';
    }
}
