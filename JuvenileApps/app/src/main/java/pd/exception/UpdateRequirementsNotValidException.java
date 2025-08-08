package pd.exception;

/**
 * @author dnikolis
 *
 * This class is used for Update Exceptions.  
 */
public class UpdateRequirementsNotValidException extends Exception {
	/**
	 * Constructor for UpdateRequirementsNotValidException.
	 */
	public UpdateRequirementsNotValidException() {
		super();
	}

	/**
	 * Constructor for UpdateRequirementsNotValidException.
	 * @param message
	 */
	public UpdateRequirementsNotValidException(String message) {
		super(message);
	}
}
