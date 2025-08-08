/*
 * Created on Dec 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.codetable;

import java.util.List;

import mojo.km.messaging.RequestEvent;

/**
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateCodetableRegistrationAttributesEvent extends RequestEvent {
	
	private List updateAttributes;
	private List removeAttributes;
	private String codeRegId;
	
	
	
	/**
	 * @return Returns the codeRegId.
	 */
	public String getCodeRegId() {
		return codeRegId;
	}
	/**
	 * @param codeRegId The codeRegId to set.
	 */
	public void setCodeRegId(String codeRegId) {
		this.codeRegId = codeRegId;
	}
	/**
	 * @return Returns the removeAttributes.
	 */
	public List getRemoveAttributes() {
		return removeAttributes;
	}
	/**
	 * @param removeAttributes The removeAttributes to set.
	 */
	public void setRemoveAttributes(List removeAttributes) {
		this.removeAttributes = removeAttributes;
	}
	/**
	 * @return Returns the updateAttributes.
	 */
	public List getUpdateAttributes() {
		return updateAttributes;
	}
	/**
	 * @param updateAttributes The updateAttributes to set.
	 */
	public void setUpdateAttributes(List updateAttributes) {
		this.updateAttributes = updateAttributes;
	}
}
