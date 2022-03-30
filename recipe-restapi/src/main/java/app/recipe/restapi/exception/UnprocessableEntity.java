package app.recipe.restapi.exception;

/**
 * The Class UnprocessableEntity.
 */
public class UnprocessableEntity extends IllegalArgumentException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new unprocessable entity.
	 *
	 * @param message the message
	 */
	public UnprocessableEntity(final String message) {
        super(message);
    }
}
