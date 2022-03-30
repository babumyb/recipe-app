package app.recipe.restapi.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// TODO: Auto-generated Javadoc
/**
 * The Class DateTimeUtil.
 */
public final class DateTimeUtil {
	
	/**
	 * Instantiates a new date time util.
	 */
	private DateTimeUtil() { }
	
	/** The formatter. */
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
	 
	/**
	 * Parses the timestamp.
	 *
	 * @param timestamp the timestamp
	 * @return the local date time
	 */
	public static LocalDateTime parseTimestamp(final String timestamp) {
		return LocalDateTime.parse(timestamp, formatter);
	}

	/**
	 * Parses the local date time.
	 *
	 * @param dateTime the date time
	 * @return the string
	 */
	public static String parseLocalDateTime(final LocalDateTime dateTime) {
		return dateTime.format(formatter);
	}
}
