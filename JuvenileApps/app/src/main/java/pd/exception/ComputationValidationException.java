package pd.exception;

/**
 * @author palcocer
 */
public class ComputationValidationException extends Exception
{
	/**
	 * Constructor for ActiveWarrantException.
	 */
	public ComputationValidationException() {
		super();
	}

	/**
	 * Constructor for ActiveWarrantException.
	 * @param message
	 */
	public ComputationValidationException(String message) {
		super(message);
	}
}
