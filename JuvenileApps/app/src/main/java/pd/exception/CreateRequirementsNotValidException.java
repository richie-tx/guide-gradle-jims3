package pd.exception;

/**
 * @author DNikolis
 * This class is used for Delete Exceptions.  
 * 
 * 
 */
public class CreateRequirementsNotValidException extends Exception {

	/**
	 * Constructor for M204Exception.
	 */
	public CreateRequirementsNotValidException() {
		super();
	}

	/**
	 * Constructor for M204Exception.
	 * @param message
	 */
	public CreateRequirementsNotValidException(String message) {
		super(message);
	}
}
