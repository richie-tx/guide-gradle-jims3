package pd.exception;

/**
 * @author DNikolis
 * 
 * This class is used for Delete Exceptions.  
 * 
 */
public class DeleteRequirementsNotValidException extends Exception {

	/**
	 * Constructor for DeleteRequirementsNotValidException.
	 */
	public DeleteRequirementsNotValidException() {
		super();
	}

	/**
	 * Constructor for DeleteRequirementsNotValidException.
	 * @param message
	 */
	public DeleteRequirementsNotValidException(String message) {
		super(message);
	}
}
