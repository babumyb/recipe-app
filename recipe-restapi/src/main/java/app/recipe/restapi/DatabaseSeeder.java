package app.recipe.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import app.recipe.restapi.data.*;
import app.recipe.restapi.entity.*;
import app.recipe.restapi.entity.Enums.Measurement;
import app.recipe.restapi.entity.Enums.Type;

import java.util.*;

@Component
public class DatabaseSeeder implements ApplicationRunner {
	private final RecipeRepository recipeRepository;

	@Autowired
	public DatabaseSeeder(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	public void run(ApplicationArguments args) {
		Ingredient egg = new Ingredient("egg");
		Ingredient milk = new Ingredient("milk");
		Ingredient flour = new Ingredient("wheat flour");
		Ingredient baking_powder = new Ingredient("baking powder");
		Ingredient butter = new  Ingredient("butter");
		Ingredient sugar = new  Ingredient("sugar");


		RecipeCategory cookies = new RecipeCategory("Cookies");
		RecipeCategory fried = new RecipeCategory("Fried Food");

		Collection<Recipe> recipesToAdd = new ArrayList<>();
		recipesToAdd.add(createRecipe(
				"Fiffikaka",
				"blend all and heat it up",
				cookies,
				Arrays.asList(
						new RecipeIngredient(6, Measurement.DECILITER, milk),
						new RecipeIngredient(3, Measurement.PIECES, egg),
						new RecipeIngredient(2.5, Measurement.DECILITER, flour),
						new RecipeIngredient(15, Measurement.MILLILITER, baking_powder),
						new RecipeIngredient(5,Measurement.MILLILITER, new Ingredient("salt")),
						new RecipeIngredient(4.5, Measurement.DECILITER,sugar),
						new RecipeIngredient(225, Measurement.GRAM, butter),
						new RecipeIngredient(22, Measurement.MILLILITER, new Ingredient("vanilla powder")),
						new RecipeIngredient(1.5,Measurement.DECILITER, new Ingredient("cocoa"))
						)
				, Type.NONVEG, 2));
		recipesToAdd.add(createRecipe(
				"Pancakes",
				"Fry it",
				fried,
				Arrays.asList(
						new RecipeIngredient(1, Measurement.LITER, milk),
						new RecipeIngredient(4, Measurement.PIECES, egg),
						new RecipeIngredient(4, Measurement.DECILITER, flour)
						)
				,Type.NONVEG,2));

		recipeRepository.saveAll(recipesToAdd);
	}

	private Recipe createRecipe(String name, String instructions, RecipeCategory category, Collection<RecipeIngredient> ingredients, Type type, int serves) {
		Recipe recipe = new Recipe(name, instructions, type, serves);
		category.getRecipes().add(recipe);

		for (RecipeIngredient ingredient : ingredients) {
			ingredient.setRecipe(recipe);
		}

		recipe.setCategory(category);
		recipe.setIngredients(ingredients);

		return recipe;
	}
}
