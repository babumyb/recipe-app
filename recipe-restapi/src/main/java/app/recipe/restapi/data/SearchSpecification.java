package app.recipe.restapi.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import app.recipe.restapi.dto.SearchDto;
import app.recipe.restapi.entity.Ingredient;
import app.recipe.restapi.entity.Recipe;
import app.recipe.restapi.entity.RecipeIngredient;
import app.recipe.restapi.utils.DateTimeUtil;

/**
 * The Class SearchSpecification.
 */
public final class SearchSpecification {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchSpecification.class);
	
	/**
	 * 
	 */
	private SearchSpecification() { }
	
	
	/**
	 * Search predicate.
	 *
	 * @param searchDto the search dto
	 * @return the specification
	 */
	@SuppressWarnings("serial")
	public static Specification<Recipe> searchPredicate(final SearchDto searchDto){
		return new Specification<Recipe>() {
			@Override
			public Predicate toPredicate(final Root<Recipe> root, final CriteriaQuery<?> query, final CriteriaBuilder criteriaBuilder) {
				
				 List<Predicate> predicates = new ArrayList<>();
				 Join<Recipe, RecipeIngredient> recipeIng = root.join("ingredients", JoinType.INNER);
				 Join<RecipeIngredient, Ingredient> ing = recipeIng.join("ingredient", JoinType.INNER);
 			     if (searchDto.getIngredient() != null) {
			          predicates.add(criteriaBuilder.and(criteriaBuilder.equal(criteriaBuilder.lower(ing.get("ingredientName")), searchDto.getIngredient().toLowerCase())));
			          logSearchParam("ingredientName", "equalsIgnoreCase", searchDto.getIngredient().toLowerCase());
			      }
 			     if (searchDto.getServes() != null) {
 			    	 predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("serves"), searchDto.getServes())));
 			    	 logSearchParam("serves", "greaterThanOrEqualTo", searchDto.getServes().toString());
 			     }
 			     if (searchDto.getFromDate() != null) {
 			    	predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("creationDateTime"), searchDto.getFromDate())));
			          logSearchParam("creationDateTime", "greaterThanOrEqualTo", DateTimeUtil.parseLocalDateTime(searchDto.getFromDate()));

 			     }
 			     if (searchDto.getToDate() != null) {
 			    	predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("creationDateTime"), searchDto.getToDate())));
 			    	 logSearchParam("creationDateTime", "lessThanOrEqualTo", DateTimeUtil.parseLocalDateTime(searchDto.getToDate()));
 			     }
 			     if (searchDto.getName() != null) {
 			    	predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("recipeName")), "%" + searchDto.getName().toLowerCase() + "%")));
 			    	logSearchParam("recipeName", "likeAndEqualsIgnoreCase", searchDto.getName().toLowerCase());
 			     }  
 			     
 			     query.distinct(true);

			     return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		
	}
	
	/**
	 * Log search param.
	 *
	 * @param paramName the param name
	 * @param paramValue the param value
	 * @param criteria the criteria
	 */
	private static void logSearchParam(final String paramName, final String paramValue, final String criteria) {
		LOGGER.debug("Criteria [" + paramName + " " + criteria + " " + paramValue + "] added");
	}
}

