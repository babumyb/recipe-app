package app.recipe.restapi.data;

import org.springframework.data.jpa.repository.JpaRepository;
import app.recipe.restapi.entity.Ingredient;

import java.util.Optional;
import java.util.Set;

/**
 * The Interface IngredientRepository.
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {


    /**
     * Find an ingredient by exact name match, case insensitive.
     * @param name exact name of ingredient to find.
     * @return An optional containing the ingredient if an ingredient exists with 'name'.
     */
    Optional<Ingredient> findByIngredientNameEqualsIgnoreCase(String name);

    /**
     * Find all ingredients which name contains the specified query, case insensitive.
     * @param query search query for ingredient name.
     * @return A set containing all ingredients which name contains 'query'.
     */
    Set<Ingredient> findByIngredientNameContainingIgnoreCase(String query);
}
