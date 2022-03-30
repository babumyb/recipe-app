package app.recipe.restapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import javax.validation.ConstraintViolationException;

// TODO: Auto-generated Javadoc
/**
 * The Class RestExceptionHandler.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	/**
	 * Handle invalid parameter combination.
	 *
	 * @param ex the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler(InvalidParameterCombinationException.class)
	public ResponseEntity<ExceptionResponse> handleInvalidParameterCombination(final InvalidParameterCombinationException ex,
			final WebRequest request) {
		
		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.name(), ex.getMessage(), request.getDescription(false));
		LOGGER.error(response.toString());
		return ResponseEntity.badRequest()
				.body(response);
	}

	/**
	 * Handle validation exception. Thrown when model validation occurs
	 *
	 * @param ex the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ExceptionResponse> handleValidationException(final ValidationException ex, final WebRequest request) {
		ExceptionResponse response =  new ExceptionResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.name(), ex.getMessage(), request.getDescription(false));
		LOGGER.error(response.toString());
		return ResponseEntity.badRequest()
				.body(response);
	}

	/**
	 * Handle type mismatch. Thrown when path params validation occurs.
	 *
	 * @param ex the ex
	 * @param headers the headers
	 * @param status the status
	 * @param request the request
	 * @return the response entity
	 */
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.name(), ex.getMessage(), request.getDescription(false)) ;
		LOGGER.error(response.toString());
		return ResponseEntity.badRequest()
				.body(response);
	}

	/**
	 * Handle uniqueness violation.
	 *
	 * @param ex the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler(UniquenessViolationException.class)
	public ResponseEntity<ExceptionResponse> handleUniquenessViolation(final UniquenessViolationException ex,
			final WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
				HttpStatus.UNPROCESSABLE_ENTITY.name(), ex.getMessage(), request.getDescription(false)) ;
		LOGGER.error(response.toString());
		return ResponseEntity.unprocessableEntity()
				.body(response);
	}
	
	 /**
 	 * Handle constraint violation.
 	 *
 	 * @param ex the ex
 	 * @param request the request
 	 * @return the response entity
 	 */
 	@ExceptionHandler(ConstraintViolationException.class)
	  public final ResponseEntity<ExceptionResponse> handleConstraintViolation(
	                      final ConstraintViolationException ex,
	                      final WebRequest request){
 		ExceptionResponse response =  new ExceptionResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.name(), ex.getMessage(), request.getDescription(false));
		LOGGER.error(response.toString());
	 
	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	  }

	/**
	 * Handle http message not readable.
	 *
	 * @param ex the ex
	 * @param headers the headers
	 * @param status the status
	 * @param request the request
	 * @return the response entity
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), ex.getMessage(), request.getDescription(false)) ;
		LOGGER.error(response.toString());
		return ResponseEntity.badRequest().body(response);
	}
	
	/**
	 * Handle not found exception.
	 *
	 * @param ex the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleNotFoundException(final NotFoundException ex,
			final WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.name(), ex.getMessage(), request.getDescription(false)) ;
		LOGGER.error(response.toString());
		return ResponseEntity.badRequest()
				.body(response);
	}
	
	/**
	 * Handle unprocessable entity exception.
	 *
	 * @param ex the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler(UnprocessableEntity.class)
	public ResponseEntity<ExceptionResponse> handleUnprocessableEntityException(final UnprocessableEntity ex,
			final WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
				HttpStatus.UNPROCESSABLE_ENTITY.name(), ex.getMessage(), request.getDescription(false)) ;
		LOGGER.error(response.toString());
		return ResponseEntity.unprocessableEntity()
				.body(response);
	}
}
