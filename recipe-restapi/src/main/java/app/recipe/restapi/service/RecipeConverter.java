package app.recipe.restapi.service;

import org.springframework.stereotype.Service;

import app.recipe.restapi.dto.RecipeDTO;
import app.recipe.restapi.entity.Recipe;
import app.recipe.restapi.utils.DateTimeUtil;

import java.util.stream.Collectors;

// TODO: Auto-generated Javadoc
/**
 * The Class RecipeConverter.
 */
@Service
public class RecipeConverter {
    
    /** The ingredient converter. */
    private final RecipeIngredientConverter ingredientConverter;
    
    /** The category converter. */
    private final RecipeCategoryConverter categoryConverter;

    /**
     * Instantiates a new recipe converter.
     *
     * @param ingredientConverter the ingredient converter
     * @param categoryConverter the category converter
     */
    public RecipeConverter(final RecipeIngredientConverter ingredientConverter, final RecipeCategoryConverter categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
    }

    /**
     * Recipe to DTO.
     *
     * @param recipe the recipe
     * @return the recipe DTO
     */
    public RecipeDTO recipeToDTO(final Recipe recipe) {
        return new RecipeDTO(
                recipe.getRecipeId(),
                recipe.getRecipeName(),
                recipe.getInstructions(),
                recipe.getIngredients()
                        .stream()
                        .map(ingredientConverter::recipeIngredientToDTO)
                        .collect(Collectors.toList()),
                categoryConverter.recipeCategoryToDTO(recipe.getCategory()), DateTimeUtil.parseLocalDateTime(recipe.getCreationDateTime()), recipe.getType(), recipe.getServes());
    }
}
