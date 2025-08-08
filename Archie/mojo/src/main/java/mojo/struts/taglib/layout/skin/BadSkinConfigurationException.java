package mojo.struts.taglib.layout.skin;

/**
 * @author eamundson
 *
 */
public class BadSkinConfigurationException extends RuntimeException {
	private Exception exception;
	public BadSkinConfigurationException(String in_string) {
		super(in_string);	
	}
	public BadSkinConfigurationException(Exception in_exception) {
		exception = in_exception;
	}
	public Exception getException() {
		return exception;	
	}
	public String toString() {
		if (exception==null) {
			return super.toString();	
		} else {
			return exception.toString();
		}
	}
}
