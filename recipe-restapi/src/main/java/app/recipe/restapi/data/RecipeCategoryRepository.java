package app.recipe.restapi.data;

import org.springframework.data.repository.CrudRepository;

import app.recipe.restapi.entity.RecipeCategory;

import java.util.Optional;

/**
 * The Interface RecipeCategoryRepository.
 */
public interface RecipeCategoryRepository extends CrudRepository<RecipeCategory, Integer> {
    
    /**
     * Find by category equals ignore case.
     *
     * @param name the name
     * @return the optional
     */
    Optional<RecipeCategory> findByCategoryEqualsIgnoreCase(String name);
}
