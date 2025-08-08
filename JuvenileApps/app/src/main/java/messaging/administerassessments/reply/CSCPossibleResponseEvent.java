/*
 * Created on Feb 8, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerassessments.reply;

/**
 * @author cc_bjangay
 *
 */
public class CSCPossibleResponseEvent
{
	private String id;
	private String displayText;	
	private String responseValue;
	private boolean isDefault = false;
	
	
	/**
	 * @return Returns the displayText.
	 */
	public String getDisplayText() {
		return displayText;
	}
	/**
	 * @param displayText The displayText to set.
	 */
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Returns the isDefault.
	 */
	public boolean isDefault() {
		return isDefault;
	}
	/**
	 * @param isDefault The isDefault to set.
	 */
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	/**
	 * @return Returns the responseValue.
	 */
	public String getResponseValue() {
		return responseValue;
	}
	/**
	 * @param responseValue The responseValue to set.
	 */
	public void setResponseValue(String responseValue) {
		this.responseValue = responseValue;
	}
}
