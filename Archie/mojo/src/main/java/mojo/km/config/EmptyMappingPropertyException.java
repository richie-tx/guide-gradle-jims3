package mojo.km.config;

/**
 * @author mchowdhury
 * This class is used for empty property in xml file Exceptions.  
 * 
 * 
 */
public class EmptyMappingPropertyException extends Exception {

	/**
	 * Constructor.
	 */
	public EmptyMappingPropertyException() {
		super();
	}

	/**
	 * Constructor.
	 * @param message
	 */
	public EmptyMappingPropertyException(String message) {
		super(message);
	}
}
