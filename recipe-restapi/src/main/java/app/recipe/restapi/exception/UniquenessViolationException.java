package app.recipe.restapi.exception;

/**
 * The Class UniquenessViolationException.
 */
public class UniquenessViolationException extends RuntimeException {
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new uniqueness violation exception.
	 *
	 * @param message the message
	 */
	public UniquenessViolationException(final String message) {
        super(message);
    }
}
