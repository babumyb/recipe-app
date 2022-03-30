package app.recipe.restapi.exception;

/**
 * The Class NotFoundException.
 */
public class NotFoundException extends IllegalArgumentException {
    
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new not found exception.
	 *
	 * @param message the message
	 */
	public NotFoundException(final String message) {
        super(message);
    }
}
