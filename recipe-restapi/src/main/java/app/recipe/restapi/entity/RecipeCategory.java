package app.recipe.restapi.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

/**
 * The Class RecipeCategory.
 */
@Entity
public class RecipeCategory {
    
    /** The recipe category id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeCategoryId;

    /** The category. */
    @Column(unique = true)
    private String category;

    /** The recipes. */
    @ManyToMany(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "recipe_recipe_category",
            joinColumns = @JoinColumn(name = "recipe_category_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private Collection<Recipe> recipes;

    /**
     * Instantiates a new recipe category.
     *
     * @param recipeCategoryId the recipe category id
     * @param category the category
     * @param recipes the recipes
     */
    public RecipeCategory(final int recipeCategoryId, final String category, final Collection<Recipe> recipes) {
        this.recipeCategoryId = recipeCategoryId;
        this.category = category;
        this.recipes = recipes;
    }

    /**
     * Instantiates a new recipe category.
     *
     * @param category the category
     * @param recipes the recipes
     */
    public RecipeCategory(final String category, final Collection<Recipe> recipes) {
        this(0, category, recipes);
    }

    /**
     * Instantiates a new recipe category.
     *
     * @param category the category
     */
    public RecipeCategory(final String category) {
        this(0, category, new HashSet<>());
    }

    /**
     * Removes the recipe.
     *
     * @param recipe the recipe
     */
    public void removeRecipe(final Recipe recipe) {
        recipes.remove(recipe);
    }

    /**
     * Instantiates a new recipe category.
     */
    public RecipeCategory() { }

    /**
     * Gets the recipe category id.
     *
     * @return the recipe category id
     */
    public int getRecipeCategoryId() {
        return recipeCategoryId;
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
     * Sets the category.
     *
     * @param category the new category
     */
    public void setCategory(final String category) {
        this.category = category;
    }

    /**
     * Gets the recipes.
     *
     * @return the recipes
     */
    public Collection<Recipe> getRecipes() {
        return recipes;
    }

    /**
     * Sets the recipes.
     *
     * @param recipes the new recipes
     */
    public void setRecipes(final Collection<Recipe> recipes) {
        this.recipes = recipes;
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
        RecipeCategory that = (RecipeCategory) o;
        return recipeCategoryId == that.recipeCategoryId && Objects.equals(category, that.category);
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(recipeCategoryId, category);
    }

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "RecipeCategory [recipeCategoryId=" + recipeCategoryId + ", "
				+ (category != null ? "category=" + category + ", " : "")
				+ (recipes != null ? "recipes=" + recipes : "") + "]";
	}

    
}
