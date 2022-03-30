package app.recipe.restapi.service;

import org.springframework.stereotype.Service;

import app.recipe.restapi.dto.RecipeCategoryDTO;
import app.recipe.restapi.entity.RecipeCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class RecipeCategoryConverter.
 */
@Service
public class RecipeCategoryConverter {
    
    /**
     * Recipe category to DTO.
     *
     * @param category the category
     * @return the recipe category DTO
     */
    public RecipeCategoryDTO recipeCategoryToDTO(final RecipeCategory category) {
        return new RecipeCategoryDTO(category.getRecipeCategoryId(), category.getCategory());
    }
}
