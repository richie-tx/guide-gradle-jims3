package messaging.administerprogramreferrals.reply;

import mojo.km.messaging.ResponseEvent;

public class ReferralFormOptionResponseEvent extends ResponseEvent
{
	private String optionId; //OID
	private String optionLabel;
	private String optionValue;
	private int displaySequence;
	private boolean isDefault;
	private boolean isOptionEdit;
	
//	used only in case of editable checkbox
	private String userEnteredOptionName;
	
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
	 * @return the optionLabel
	 */
	public String getOptionLabel() {
		return optionLabel;
	}
	/**
	 * @param optionLabel the optionLabel to set
	 */
	public void setOptionLabel(String optionLabel) {
		this.optionLabel = optionLabel;
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
}
