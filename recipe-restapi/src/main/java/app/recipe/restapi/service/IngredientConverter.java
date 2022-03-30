package app.recipe.restapi.service;

import org.springframework.stereotype.Service;

import app.recipe.restapi.dto.IngredientDTO;
import app.recipe.restapi.entity.Ingredient;

/**
 * The Class IngredientConverter.
 */
@Service
public class IngredientConverter {
    
    /**
     * Ingredient to DTO.
     *
     * @param ingredient the ingredient
     * @return the ingredient DTO
     */
    public IngredientDTO ingredientToDTO(final Ingredient ingredient) {
        return new IngredientDTO(ingredient.getIngredientId(), ingredient.getIngredientName());
    }
}
