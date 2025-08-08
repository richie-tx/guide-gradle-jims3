/*
 * Created on May 03, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.security.reply;

import mojo.km.messaging.ResponseEvent;


/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DuplicateRecordErrorResponseEvent extends ResponseEvent
{
	private String message;
	private String itemName;
    private String itemVal;	
    private String errorKey;
	/**
	 * @return
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * @param string
	 */
	public void setMessage(String string)
	{
		message = string;
	}

	/**
	 * @return Returns the itemName.
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName The itemName to set.
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @return Returns the itemVal.
	 */
	public String getItemVal() {
		return itemVal;
	}
	/**
	 * @param itemVal The itemVal to set.
	 */
	public void setItemVal(String itemVal) {
		this.itemVal = itemVal;
	}
	/**
	 * @return Returns the errorKey.
	 */
	public String getErrorKey() {
		return errorKey;
	}
	/**
	 * @param errorKey The errorKey to set.
	 */
	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}
}
