package app.recipe.restapi.exception;

/**
 * The Class ValidationException.
 */
public class ValidationException extends IllegalArgumentException {
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new validation exception.
	 *
	 * @param message the message
	 */
	public ValidationException(final String message) {
        super(message);
    }
}
