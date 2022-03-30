package app.recipe.restapi.dto;

import java.time.LocalDateTime;

/**
 * The Class SearchDto.
 */
public class SearchDto {

	/** The from date. */
	private LocalDateTime fromDate;
    
    /** The to date. */
    private LocalDateTime toDate;
    
    /** The ingredient. */
    private String ingredient;
    
    /** The serves. */
    private Integer serves;
    
    /** The name. */
    private String name;
	
	/**
	 * Instantiates a new search dto.
	 *
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @param ingredient the ingredient
	 * @param serves the serves
	 * @param name the name
	 */
	public SearchDto(final LocalDateTime fromDate, final LocalDateTime toDate, final String ingredient, final Integer serves, final String name) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.ingredient = ingredient;
		this.serves = serves;
		this.name = name;
	}
	
	/**
	 * Gets the from date.
	 *
	 * @return the from date
	 */
	public LocalDateTime getFromDate() {
		return fromDate;
	}
	
	/**
	 * Sets the from date.
	 *
	 * @param fromDate the new from date
	 */
	public void setFromDate(final LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}
	
	/**
	 * Gets the to date.
	 *
	 * @return the to date
	 */
	public LocalDateTime getToDate() {
		return toDate;
	}
	
	/**
	 * Sets the to date.
	 *
	 * @param toDate the new to date
	 */
	public void setToDate(final LocalDateTime toDate) {
		this.toDate = toDate;
	}
	
	/**
	 * Gets the ingredient.
	 *
	 * @return the ingredient
	 */
	public String getIngredient() {
		return ingredient;
	}
	
	/**
	 * Sets the ingredient.
	 *
	 * @param ingredient the new ingredient
	 */
	public void setIngredient(final String ingredient) {
		this.ingredient = ingredient;
	}
	
	/**
	 * Gets the serves.
	 *
	 * @return the serves
	 */
	public Integer getServes() {
		return serves;
	}
	
	/**
	 * Sets the serves.
	 *
	 * @param serves the new serves
	 */
	public void setServes(final Integer serves) {
		this.serves = serves;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(final String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "SearchDto [fromDate=" + fromDate + ", toDate=" + toDate + ", ingredient=" + ingredient + ", serves="
				+ serves + ", name=" + name + "]";
	}
	
    
}
