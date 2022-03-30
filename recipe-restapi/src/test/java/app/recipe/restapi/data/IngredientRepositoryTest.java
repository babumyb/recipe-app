package app.recipe.restapi.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import app.recipe.restapi.TestDataGenerator;
import app.recipe.restapi.entity.Ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@DataJpaTest
public class IngredientRepositoryTest {
	
	
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
    private IngredientRepository ingredientRepository;

    @Autowired
    private TestEntityManager em;

    @Autowired
    private TestDataGenerator testDataGen;

    @Test
    void findByIngredientNameEquals_findsExisting() {
        String name = testDataGen.ingredientName();
        Ingredient expected = new Ingredient(name);
        em.persistAndFlush(expected);

        Optional<Ingredient> actual = ingredientRepository.findByIngredientNameEqualsIgnoreCase(name);

        assertTrue(actual.isPresent());
        assertEquals(expected.getIngredientName(), actual.get().getIngredientName());
    }

    @Test
    void findByIngredientNameEquals_caseSensitive() {
        String name = "TestIngredient";
        Ingredient ingredient = new Ingredient(name);
        em.persistAndFlush(ingredient);

        Optional<Ingredient> actual = ingredientRepository.findByIngredientNameEqualsIgnoreCase("testingredient");
        assertTrue(actual.isPresent());
    }

    @Test
    void findByIngredientNameContainingIgnoreCase_findMatching() {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("apple"),
                new Ingredient("pineapple"),
                new Ingredient("carrot")
        );
        ingredients.forEach(em::persist);
        em.flush();

        Collection<Ingredient> actual = ingredientRepository.findByIngredientNameContainingIgnoreCase("apple");

        assertTrue(actual.contains(ingredients.get(0)));
        assertTrue(actual.contains(ingredients.get(1)));
        assertFalse(actual.contains(ingredients.get(2)));
    }
}
