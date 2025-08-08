package ui.supervision.programReferral;

/**
 * 
 * @author cc_bjangay
 *
 */
public class RefFormFieldOption implements Comparable
{
	private String optionId="";
	private String optionDisplayText="";
	private String optionValue="";
	private String responseId="";
	private int displaySequence;
	private boolean isDefault;
	private boolean isOptionEdit;
	private String userEnteredOptionName;
	
	public String getResponseId() {
		return responseId;
	}
	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}
	/**
	 * @return the optionId
	 */
	public String getOptionId() {
		return optionId;
	}
	/**
	 * @param optionId the optionId to set
	 */
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	/**
	 * @return the optionDisplayText
	 */
	public String getOptionDisplayText() {
		return optionDisplayText;
	}
	/**
	 * @param optionDisplayText the optionDisplayText to set
	 */
	public void setOptionDisplayText(String optionDisplayText) {
		this.optionDisplayText = optionDisplayText;
	}
	/**
	 * @return the optionValue
	 */
	public String getOptionValue() {
		return optionValue;
	}
	/**
	 * @param optionValue the optionValue to set
	 */
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	/**
	 * @return the isDefault
	 */
	public boolean isDefault() {
		return isDefault;
	}
	/**
	 * @param isDefault the isDefault to set
	 */
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	/**
	 * @return the displaySequence
	 */
	public int getDisplaySequence() {
		return displaySequence;
	}
	/**
	 * @param displaySequence the displaySequence to set
	 */
	public void setDisplaySequence(int displaySequence) {
		this.displaySequence = displaySequence;
	}
	/**
	 * @return the isOptionEdit
	 */
	public boolean isOptionEdit() {
		return isOptionEdit;
	}
	/**
	 * @param isOptionEdit the isOptionEdit to set
	 */
	public void setOptionEdit(boolean isOptionEdit) {
		this.isOptionEdit = isOptionEdit;
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
	/**
	 * 
	 */
	public int compareTo(Object obj) 
	{
		if(obj==null || !(obj instanceof RefFormFieldOption))
			return 1;
		
		RefFormFieldOption incomingObj = (RefFormFieldOption)obj;
		if(this.displaySequence == incomingObj.displaySequence)
		{
			return 0;
		}
		else if(this.displaySequence > incomingObj.displaySequence)
		{
			return 1;
		}
		else
			return -1;
	}
}
