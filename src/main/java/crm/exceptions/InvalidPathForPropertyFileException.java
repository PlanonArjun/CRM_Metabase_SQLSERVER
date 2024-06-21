package crm.exceptions;

/**
 * Runtime Exception occurs when the path given for excel sheet is incorrect.
 * 
 * @author Ansuman
 */
@SuppressWarnings("serial")
public class InvalidPathForPropertyFileException extends InvalidPathForFilesException {

	/**
	 * Pass the message that needs to be appended to the stacktrace
	 * 
	 * @param message Details about the exception or custom message
	 */
	public InvalidPathForPropertyFileException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param message Details about the exception or custom message
	 * @param cause   Pass the enriched stacktrace or customised stacktrace
	 */
	public InvalidPathForPropertyFileException(String message, Throwable cause) {
		super(message, cause);
	}

}
