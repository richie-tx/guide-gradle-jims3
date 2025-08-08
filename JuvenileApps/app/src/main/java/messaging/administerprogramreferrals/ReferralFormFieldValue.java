package messaging.administerprogramreferrals;

import java.util.List;

public class ReferralFormFieldValue 
{
	private String fieldId;

    private String responseId;
    private String responseText;
    
//    used in case of checkbox
    private boolean isMultiCheckBoxField;
    private List responseIdsList;
    
    private String userEnteredOptionName;
    
	/**
	 * @return the fieldId
	 */
	public String getFieldId() {
		return fieldId;
	}
	/**
	 * @param fieldId the fieldId to set
	 */
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	/**
	 * @return the responseId
	 */
	public String getResponseId() {
		return responseId;
	}
	/**
	 * @param responseId the responseId to set
	 */
	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}
	/**
	 * @return the responseText
	 */
	public String getResponseText() {
		return responseText;
	}
	/**
	 * @param responseText the responseText to set
	 */
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
	/**
	 * @return the responseIdsList
	 */
	public List getResponseIdsList() {
		return responseIdsList;
	}
	/**
	 * @param responseIdsList the responseIdsList to set
	 */
	public void setResponseIdsList(List responseIdsList) {
		this.responseIdsList = responseIdsList;
	}
	/**
	 * @return the isMultiCheckBoxField
	 */
	public boolean isMultiCheckBoxField() {
		return isMultiCheckBoxField;
	}
	/**
	 * @param isMultiCheckBoxField the isMultiCheckBoxField to set
	 */
	public void setMultiCheckBoxField(boolean isMultiCheckBoxField) {
		this.isMultiCheckBoxField = isMultiCheckBoxField;
	}
	/**
	 * @return the userEnteredOptionName
	 */
	public String getUserEnteredOptionName() {
		return userEnteredOptionName;
	}
	/**
	 * @param userEnteredOptionName the userEnteredOptionName to set
	 */
	public void setUserEnteredOptionName(String userEnteredOptionName) {
		this.userEnteredOptionName = userEnteredOptionName;
	}
}
