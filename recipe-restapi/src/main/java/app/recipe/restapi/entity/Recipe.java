package app.recipe.restapi.entity;


import app.recipe.restapi.entity.Enums.Type;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

// TODO: Auto-generated Javadoc
/**
 * The Class Recipe.
 */
@Entity
public class Recipe {
    
    /** The recipe id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;

    /** The recipe name. */
    private String recipeName;

    /** The instructions. */
    private String instructions;
    
    /** The type. */
    private Type type;
    
    /** The serves. */
    private int serves;
    
    /** The creation date time. */
    private LocalDateTime creationDateTime;

    /** The ingredients. */
    @OneToMany(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER,
            mappedBy = "recipe"
    )
    private Collection<RecipeIngredient> ingredients;

    /** The category. */
    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER
    )
    private RecipeCategory category;

    /**
     * Instantiates a new recipe.
     *
     * @param recipeId the recipe id
     * @param recipeName the recipe name
     * @param instructions the instructions
     * @param ingredients the ingredients
     * @param category the category
     * @param creationDate the creation date
     * @param type the type
     * @param serves the serves
     */
    public Recipe(final int recipeId, final String recipeName, final String instructions, final Collection<RecipeIngredient> ingredients,
                  final RecipeCategory category, final LocalDateTime creationDate, final Type type, final int serves) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.category = category;
        this.creationDateTime = creationDate;
        this.type = type;
        this.serves = serves;
    }

    /**
     * Instantiates a new recipe.
     *
     * @param recipeName the recipe name
     * @param instructions the instructions
     * @param ingredients the ingredients
     * @param category the category
     * @param creationDate the creation date
     * @param type the type
     * @param serves the serves
     */
    public Recipe(final String recipeName, final String instructions, final Collection<RecipeIngredient> ingredients,
                  final RecipeCategory category, final LocalDateTime creationDate, final Type type, final int serves) {
        this(0, recipeName, instructions, ingredients, category, creationDate, type, serves);
    }

    /**
     * Instantiates a new recipe.
     *
     * @param recipeName the recipe name
     * @param instructions the instructions
     * @param type the type
     * @param serves the serves
     */
    public Recipe(final String recipeName, final String instructions, final Type type, final int serves) {
        this(0, recipeName, instructions, new HashSet<>(), null, LocalDateTime.now(), type, serves);
    }

    /**
     * Instantiates a new recipe.
     */
    public Recipe() { }

    /**
     * Pre remove.
     */
    @PreRemove
    public void preRemove() {
        clearIngredients();
        clearCategories();
    }

    /**
     * Clear ingredients.
     */
    public void clearIngredients() {
        ingredients.forEach(RecipeIngredient::detachRecipe);
        ingredients.clear();
    }

    /**
     * Clear categories.
     */
    public void clearCategories() {
        category.removeRecipe(this);
        category = null;
    }

    /**
     * Gets the recipe id.
     *
     * @return the recipe id
     */
    public int getRecipeId() {
        return recipeId;
    }

    /**
     * Gets the recipe name.
     *
     * @return the recipe name
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * Sets the recipe name.
     *
     * @param name the new recipe name
     */
    public void setRecipeName(final String name) {
        this.recipeName = name;
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
     * Sets the instructions.
     *
     * @param instructions the new instructions
     */
    public void setInstructions(final String instructions) {
        this.instructions = instructions;
    }

    /**
     * Gets the ingredients.
     *
     * @return the ingredients
     */
    public Collection<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    /**
     * Sets the ingredients.
     *
     * @param ingredients the new ingredients
     */
    public void setIngredients(final Collection<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Gets the category.
     *
     * @return the category
     */
    public RecipeCategory getCategory() {
        return category;
    }

    /**
     * Sets the category.
     *
     * @param category the new category
     */
    public void setCategory(final RecipeCategory category) {
        this.category = category;
    }

    /**
     * Gets the creation date time.
     *
     * @return the creation date time
     */
    public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	/**
	 * Sets the creation date time.
	 *
	 * @param creationDateTime the new creation date time
	 */
	public void setCreationDateTime(final LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
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
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(final Type type) {
		this.type = type;
	}

	/**
	 * Gets the serves.
	 *
	 * @return the serves
	 */
	public int getServes() {
		return serves;
	}

	/**
	 * Sets the serves.
	 *
	 * @param serves the new serves
	 */
	public void setServes(final int serves) {
		this.serves = serves;
	}


	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(category, creationDateTime, ingredients, instructions, recipeId, recipeName, serves, type);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Recipe other = (Recipe) obj;
		return Objects.equals(category, other.category) && Objects.equals(creationDateTime, other.creationDateTime)
				&& Objects.equals(ingredients, other.ingredients) && Objects.equals(instructions, other.instructions)
				&& recipeId == other.recipeId && Objects.equals(recipeName, other.recipeName) && serves == other.serves
				&& Objects.equals(type, other.type);
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
    public String toString() {
        return "Recipe{" 
        		+ "id=" + recipeId 
        		+ ", name='" + recipeName + '\'' 
                + '}';
    }
}
