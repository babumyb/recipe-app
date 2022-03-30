package app.recipe.restapi.data;

import org.springframework.data.repository.CrudRepository;

import app.recipe.restapi.entity.RecipeIngredient;

/**
 * The Interface RecipeIngredientRepository.
 */
public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, String> {
}
