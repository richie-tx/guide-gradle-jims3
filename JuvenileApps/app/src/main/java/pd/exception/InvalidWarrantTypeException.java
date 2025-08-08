/*
 * Created on Nov 1, 2004
 */
package pd.exception;

/**
 * @author dnikolis
 */
public class InvalidWarrantTypeException extends Exception
{
	/**
	 * Constructor for InvalidWarrantTypeException.
	 */
	public InvalidWarrantTypeException() {
		super();
	}

	/**
	 * Constructor for InvalidWarrantTypeException.
	 * @param message
	 */
	public InvalidWarrantTypeException(String message) {
		super(message);
	}
}
