/*
 * Created on Feb 11, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.common;



/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSCQuestionResponse
{
	private String responseId="";
	private String responseDisplayText="";
	private String responseValue="";
	private boolean isDefault=false;
	
	
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
	 * @return Returns the responseDisplayText.
	 */
	public String getResponseDisplayText() {
		return responseDisplayText;
	}
	/**
	 * @param responseDisplayText The responseDisplayText to set.
	 */
	public void setResponseDisplayText(String responseDisplayText) {
		this.responseDisplayText = responseDisplayText;
	}
	/**
	 * @return Returns the responseId.
	 */
	public String getResponseId() {
		return responseId;
	}
	/**
	 * @param responseId The responseId to set.
	 */
	public void setResponseId(String responseId) {
		this.responseId = responseId;
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
