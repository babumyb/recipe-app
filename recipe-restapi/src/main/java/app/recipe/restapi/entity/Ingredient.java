package app.recipe.restapi.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// TODO: Auto-generated Javadoc
/**
 * The Class Ingredient.
 */
@Entity
public class Ingredient {
    
    /** The ingredient id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ingredientId;

    /** The ingredient name. */
    @Column(unique = true)
    private String ingredientName;

    /**
     * Instantiates a new ingredient.
     *
     * @param ingredientId the ingredient id
     * @param ingredientName the ingredient name
     */
    public Ingredient(final int ingredientId, final String ingredientName) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
    }

    /**
     * Instantiates a new ingredient.
     *
     * @param ingredientName the ingredient name
     */
    public Ingredient(final String ingredientName) {
        this(0, ingredientName);
    }

    /**
     * Instantiates a new ingredient.
     */
    public Ingredient() { }

    /**
     * Gets the ingredient id.
     *
     * @return the ingredient id
     */
    public int getIngredientId() {
        return ingredientId;
    }

    /**
     * Gets the ingredient name.
     *
     * @return the ingredient name
     */
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     * Sets the ingredient name.
     *
     * @param name the new ingredient name
     */
    public void setIngredientName(final String name) {
        this.ingredientName = name;
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
        Ingredient that = (Ingredient) o;
        return ingredientId == that.ingredientId && Objects.equals(ingredientName, that.ingredientName);
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, ingredientName);
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + ingredientId +
                ", name='" + ingredientName + '\'' +
                '}';
    }
}
