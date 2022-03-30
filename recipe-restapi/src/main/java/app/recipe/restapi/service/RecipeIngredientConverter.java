package app.recipe.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.recipe.restapi.data.IngredientRepository;
import app.recipe.restapi.dto.RecipeIngredientDTO;
import app.recipe.restapi.entity.Ingredient;
import app.recipe.restapi.entity.Recipe;
import app.recipe.restapi.entity.RecipeIngredient;

// TODO: Auto-generated Javadoc
/**
 * The Class RecipeIngredientConverter.
 */
@Service
public class RecipeIngredientConverter {
    
    /** The repository. */
    private final IngredientRepository repository;

    /**
     * Instantiates a new recipe ingredient converter.
     *
     * @param repository the repository
     */
    @Autowired
    public RecipeIngredientConverter(final IngredientRepository repository) {
        this.repository = repository;
    }

    /**
     * Dto to recipe ingredient.
     *
     * @param dto the dto
     * @param recipe the recipe
     * @return the recipe ingredient
     */
    public RecipeIngredient dtoToRecipeIngredient(final RecipeIngredientDTO dto, final Recipe recipe) {
        return new RecipeIngredient(dto.getAmount(), dto.getMeasurement(),
                repository
                        .findByIngredientNameEqualsIgnoreCase(dto.getName())
                        .orElse(new Ingredient(dto.getName())),
                recipe);
    }

    /**
     * Recipe ingredient to DTO.
     *
     * @param recipeIngredient the recipe ingredient
     * @return the recipe ingredient DTO
     */
    public RecipeIngredientDTO recipeIngredientToDTO(final RecipeIngredient recipeIngredient) {
        return new RecipeIngredientDTO(recipeIngredient.getIngredient().getIngredientId(),
                recipeIngredient.getIngredient().getIngredientName(),
                recipeIngredient.getMeasurement(), recipeIngredient.getAmount());
    }
}
