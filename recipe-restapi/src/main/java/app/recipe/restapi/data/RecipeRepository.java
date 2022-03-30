package app.recipe.restapi.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.recipe.restapi.entity.Recipe;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * The Interface RecipeRepository.
 */
public interface RecipeRepository extends JpaRepository<Recipe, Integer>,JpaSpecificationExecutor<Recipe> {
    /**
     * Find all recipes which name contains the specified query, case insensitive.
     * @param query search query for recipe name.
     * @return A set containing all recipes which name contains 'query'.
     */
    Set<Recipe> findByRecipeNameContainingIgnoreCase(String query);

    /**
     * Find all recipes that contains ingredients with the specified name. The match is exact and case insensitive.
     * @param ingredientName name of the ingredient to search recipes by.
     * @return A set containing all recipes that contain the specified ingredient.
     */
    @Query("SELECT r FROM Recipe r JOIN FETCH r.ingredients AS ri WHERE UPPER(ri.ingredient.ingredientName) = UPPER(:name)")
    Set<Recipe> findByIngredientName(@Param("name") String ingredientName);
    
    /**
     * Find all by creation date time between.
     *
     * @param creationDateTimeStart the creation date time start
     * @param creationDateTimeEnd the creation date time end
     * @return the sets the
     */
    Set<Recipe> findAllByCreationDateTimeBetween(LocalDateTime creationDateTimeStart, LocalDateTime creationDateTimeEnd);
    
    	
    }

