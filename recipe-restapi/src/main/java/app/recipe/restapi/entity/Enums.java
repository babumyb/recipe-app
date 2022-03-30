package app.recipe.restapi.entity;

/**
 * The Class Enums.
 */
public class Enums {
	
	/**
	 * The Enum Measurement.
	 */
	public enum Measurement {
	    MILLILITER,
	    DECILITER,
	    LITER,
	    MILLIGRAM,
	    GRAM,
	    KILOGRAM,
	    PIECES
	}
	
	/**
	 * The Enum Type.
	 */
	public enum Type {
		VEG, NONVEG
	}
	
	/**
	 * The Enum Status.
	 */
	public enum Status {
		CREATED, UPDATED, DELETED
	}
}