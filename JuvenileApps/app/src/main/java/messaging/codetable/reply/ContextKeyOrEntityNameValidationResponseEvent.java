/*
 * Created on Sep 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.codetable.reply;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import mojo.km.messaging.ResponseEvent;

/**
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContextKeyOrEntityNameValidationResponseEvent  extends ResponseEvent{

	private List contextKeysOrEntityNames;	
	private String errorMessage;
	private String nameError;
	
	/**
	 * @return Returns the errorMessage.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage The errorMessage to set.
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}	
	/**
	 * @return Returns the contextKeysOrEntityNames.
	 */
	public List getContextKeysOrEntityNames() {
		return contextKeysOrEntityNames;
	}
	/**
	 * @param contextKeysOrEntityNames The contextKeysOrEntityNames to set.
	 */
	public void setContextKeysOrEntityNames(List contextKeysOrEntityNames) {
		this.contextKeysOrEntityNames = contextKeysOrEntityNames;
	}
	/**
	 * @return Returns the nameError.
	 */
	public String getNameError() {
		return nameError;
	}
	/**
	 * @param nameError The nameError to set.
	 */
	public void setNameError(String nameError) {
		this.nameError = nameError;
	}
}

