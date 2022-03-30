package app.recipe.restapi.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

// TODO: Auto-generated Javadoc
/**
 * The Class ExceptionResponse.
 */
public class ExceptionResponse {
    
    /** The timestamp. */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;
    
    /** The status. */
    private final Integer status;
    
    /** The error. */
    private final String error;
    
    /** The message. */
    private final String message;
    
    /** The path. */
    private final String path;

    /**
     * Instantiates a new exception response.
     *
     * @param timestamp the timestamp
     * @param status the status
     * @param error the error
     * @param message the message
     * @param path the path
     */
    public ExceptionResponse(final LocalDateTime timestamp, final Integer status, final String error, final String message, final String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    /**
     * Gets the timestamp.
     *
     * @return the timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Gets the error.
     *
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ExceptionResponse [timestamp=" + timestamp + ", status=" + status + ", error=" + error + ", message="
				+ message + ", path=" + path + "]";
	}
    
}
