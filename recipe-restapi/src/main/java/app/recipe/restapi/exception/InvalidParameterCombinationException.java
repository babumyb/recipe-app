package app.recipe.restapi.exception;

/**
 * The Class InvalidParameterCombinationException.
 */
public class InvalidParameterCombinationException extends IllegalArgumentException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new invalid parameter combination exception.
	 *
	 * @param message the message
	 */
	public InvalidParameterCombinationException(final String message) {
        super(message);
    }
}
