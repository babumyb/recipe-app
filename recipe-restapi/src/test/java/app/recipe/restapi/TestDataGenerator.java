package app.recipe.restapi;

import com.github.javafaker.Faker;

import app.recipe.restapi.data.IngredientRepository;
import app.recipe.restapi.dto.CreateRecipeDTO;
import app.recipe.restapi.dto.RecipeIngredientDTO;
import app.recipe.restapi.entity.*;
import app.recipe.restapi.entity.Enums.Measurement;
import app.recipe.restapi.entity.Enums.Type;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestDataGenerator {
    private final Faker faker;
    IngredientRepository ingredientRepository;

    public TestDataGenerator(long seed, IngredientRepository ingredients) {
        this.faker = new Faker(new Random(seed));
        this.ingredientRepository = ingredients;
    }

    /* Special handling for ingredients since they must have unique names. If a generated ingredient name already
       exists in the database, fetch it instead of trying to insert it, since inserting causes an exception. */
    public Ingredient ingredient() {
        String ingredientName = ingredientName();
        Ingredient ingredient;

        try {
        	ingredient = ingredientRepository.findByIngredientNameEqualsIgnoreCase(ingredientName).get();
        } catch (NoSuchElementException ex) {
            ingredient = new Ingredient(ingredientName);
            ingredient = ingredientRepository.save(ingredient);
        }

        return ingredient;
    }
    
    

    public String ingredientName() {
        return faker.food().ingredient();
    }

    public String recipeName() {
        return faker.food().ingredient() + " and " + faker.food().ingredient();
    }

    public Recipe recipeWithName(String name) {
        Recipe recipe = new Recipe(name, recipeInstruction(), recipeType(), faker.number().numberBetween(1, 10));

        recipe.setIngredients(recipeIngredients(recipe));
        recipe.setCategory(recipeCategory(recipe));

        return recipe;
    }
    
    

    public Recipe recipeWithIngredients(Ingredient ...ingredients) {
        Recipe recipe = new Recipe(recipeName(), recipeInstruction(), recipeType(), faker.number().numberBetween(1, 10));

        recipe.setIngredients(recipeIngredients(recipe, Arrays.asList(ingredients)));
        recipe.setCategory(recipeCategory(recipe));

        return recipe;
    }
    
    public CreateRecipeDTO recipeDtoWithName(String name) {
    	CreateRecipeDTO recipeDto = new CreateRecipeDTO(name, recipeInstruction(), createRecipeIngredientDto() , recipeCategoryName(), recipeType(), faker.random().nextInt(3, 10));

        return recipeDto;
    }
    
    public List<RecipeIngredientDTO> createRecipeIngredientDto () {
    	return IntStream.range(1, faker.random().nextInt(3, 10))
                .mapToObj(i -> new RecipeIngredientDTO(null, ingredientName(), measurement(), faker.random().nextDouble()))
                .collect(Collectors.toList());
    }

    public Recipe recipeWithCategories(RecipeCategory category) {
        Recipe recipe = new Recipe(recipeName(), recipeInstruction(), recipeType(), faker.number().numberBetween(1, 10));

        recipe.setIngredients(recipeIngredients(recipe));
        recipe.setCategory(category);

        return recipe;
    }

    public String recipeInstruction() {
        return faker.lorem().paragraph();
    }
    
    public Type recipeType() {
    	return Type.values()[faker.random().nextInt(Type.values().length)];
    }

    public Measurement measurement() {
        return Measurement.values()[faker.random().nextInt(Measurement.values().length)];
    }

    public List<RecipeIngredient> recipeIngredients(Recipe recipe) {
        return IntStream.range(1, faker.random().nextInt(3, 10))
                .mapToObj(i -> new RecipeIngredient(faker.random().nextDouble(), measurement(), ingredient(), recipe))
                .collect(Collectors.toList());
    }

    public List<RecipeIngredient> recipeIngredients(Recipe recipe, List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(ing -> new RecipeIngredient(faker.random().nextDouble(), measurement(), ing, recipe))
                .collect(Collectors.toList());
    }

    public String recipeCategoryName() {
        return faker.lorem().word();
    }

    public RecipeCategory recipeCategory(Recipe recipe) {
        return new RecipeCategory(recipeCategoryName(), Collections.singletonList(recipe));
    }
}
