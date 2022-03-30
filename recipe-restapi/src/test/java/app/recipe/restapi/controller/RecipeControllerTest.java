package app.recipe.restapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.beans.PropertyEditor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import app.recipe.restapi.TestDataGenerator;
import app.recipe.restapi.data.IngredientRepository;
import app.recipe.restapi.dto.ActionStatus;
import app.recipe.restapi.dto.RecipeDTO;
import app.recipe.restapi.dto.RecipeResponse;
import app.recipe.restapi.dto.UpdateRecipeDTO;
import app.recipe.restapi.entity.Enums.Status;
import app.recipe.restapi.exception.InvalidParameterCombinationException;
import app.recipe.restapi.exception.NotFoundException;
import app.recipe.restapi.service.IngredientConverter;
import app.recipe.restapi.service.RecipeConverter;

/**
 * The Class RecipeControllerTest.
 */
@SpringBootTest
class RecipeControllerTest {
	
	 @TestConfiguration
	    static class TestConfig {
	        @Bean
	        TestDataGenerator testDataGen(long seed, IngredientRepository ingredientRepository) {
	            return new TestDataGenerator(seed, ingredientRepository);
	        }

	        @Bean
	        long seed() {
	            return -698526451L;
	        }
	    }
	
	@Autowired
	TestDataGenerator testGenerator;
	
	@Autowired 
	RecipeConverter recipeConverter;
	
	@Autowired
	IngredientConverter ingredientConverter;
	
	private static Pageable pageable = Pageable.ofSize(100);
	@Autowired
	RecipeController recipeController;
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetRecipesSize() {
		assertEquals(recipeController.getRecipes(null, null, null, null, null, pageable).getBody().getRecipes().size() , 2);
	}

	@Test
	public void testGetRecipeWithDateParameter() {
		ResponseEntity<RecipeResponse> response = recipeController.getRecipes(LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(1), null, null, null, pageable);
		assertEquals(response.getBody().getRecipes().size(), 2);
	}
	
	@Test
	public void testGetRecipeWithNameParameter() {
		ResponseEntity<RecipeResponse> response = recipeController.getRecipes(null, null, null, null, "fiffikaka", pageable);
		assertEquals(response.getBody().getRecipes().iterator().next().getName(), "Fiffikaka");
	}
	
	@Test
	public void testGetRecipeWithServesParameter() {
		ResponseEntity<RecipeResponse> response = recipeController.getRecipes(null, null, null, 1, null , pageable);
		assertEquals(response.getBody().getRecipes().size(), 2);
	}
	
	@Test
	public void testGetRecipeWithIngredientParameter() {
		ResponseEntity<RecipeResponse> response = recipeController.getRecipes(null, null, "butter", null, null , pageable);
		assertEquals(response.getBody().getRecipes().size(), 1);
	}
	
	@Test
	public void testGetRecipeWithAllParameters() {
		ResponseEntity<RecipeResponse> response = recipeController.getRecipes(LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(1), null, 1, "Fiffikaka" , pageable);
		assertEquals(response.getBody().getRecipes().iterator().next().getName(), "Fiffikaka");
		assertEquals(response.getBody().getRecipes().iterator().next().getServes(), 2);
	}
	
	@Test
	public void testSaveRecipe() {
		ResponseEntity<ActionStatus> recipe = recipeController.createRecipe(testGenerator.recipeDtoWithName("falafel"), dummyBindingResult());
		assertEquals(recipe.getBody().getStatus(), Status.CREATED);
		assertNotEquals(recipe.getBody().getId(), 0);
		recipeController.deleteRecipe(recipe.getBody().getId());
	}
	
	@Test
	public void testUpdateRecipe() {
		ResponseEntity<ActionStatus> recipe = recipeController.updateRecipe(1, new UpdateRecipeDTO("Falafel", null, null, null, null, null) ,dummyBindingResult());
		assertEquals(recipe.getBody().getStatus(), Status.UPDATED);
		RecipeDTO updatedRecipe = recipeController.getRecipeById(recipe.getBody().getId()).getBody();
		assertEquals(updatedRecipe.getName(), "Falafel");
		
		//reset db
		recipeController.updateRecipe(updatedRecipe.getId(), new UpdateRecipeDTO("Fiffikaka", null, null, null, null, null) ,dummyBindingResult());
		
	}
	
	@Test
	public void testNoDataFoundException () {
		Assertions.assertThrows(NotFoundException.class,() -> 
		{	
			recipeController.getRecipeById(5); 
			}
		);
	}
	
	
	
	@Test
	public void testInvalidParameterException () {
		Assertions.assertThrows(InvalidParameterCombinationException.class,() -> 
		{	
			recipeController.getRecipes(LocalDateTime.now().plusHours(1), LocalDateTime.now().minusHours(1), null, null, null, pageable);
			}
		);
	}
	
	
	
	private BindingResult dummyBindingResult() {
		return new BindingResult() {
			
			@Override
			public void setNestedPath(String nestedPath) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void rejectValue(String field, String errorCode, String defaultMessage) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void rejectValue(String field, String errorCode) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reject(String errorCode, Object[] errorArgs, String defaultMessage) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reject(String errorCode, String defaultMessage) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reject(String errorCode) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void pushNestedPath(String subPath) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void popNestedPath() throws IllegalStateException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean hasGlobalErrors() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean hasFieldErrors(String field) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean hasFieldErrors() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean hasErrors() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public String getObjectName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getNestedPath() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<ObjectError> getGlobalErrors() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getGlobalErrorCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public ObjectError getGlobalError() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getFieldValue(String field) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Class<?> getFieldType(String field) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<FieldError> getFieldErrors(String field) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<FieldError> getFieldErrors() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getFieldErrorCount(String field) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int getFieldErrorCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public FieldError getFieldError(String field) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public FieldError getFieldError() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getErrorCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public List<ObjectError> getAllErrors() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void addAllErrors(Errors errors) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String[] resolveMessageCodes(String errorCode, String field) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String[] resolveMessageCodes(String errorCode) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getTarget() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getRawFieldValue(String field) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public PropertyEditorRegistry getPropertyEditorRegistry() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Map<String, Object> getModel() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public PropertyEditor findEditor(String field, Class<?> valueType) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void addError(ObjectError error) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	
	
	
}