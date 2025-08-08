package messaging.administerprogramreferrals;

import java.util.Collection;

import mojo.km.messaging.RequestEvent;

public class SaveReferralFormEvent extends RequestEvent
{
	private String programReferralId;
	private String referralFormId;
	private Collection fieldDataList; // ReferralFormFieldValue
	
	
	/**
	 * @return the referralFormId
	 */
	public String getReferralFormId() {
		return referralFormId;
	}
	/**
	 * @param referralFormId the referralFormId to set
	 */
	public void setReferralFormId(String referralFormId) {
		this.referralFormId = referralFormId;
	}
	/**
	 * @return the programReferralId
	 */
	public String getProgramReferralId() {
		return programReferralId;
	}
	/**
	 * @param programReferralId the programReferralId to set
	 */
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}
	/**
	 * @return the fieldDataList
	 */
	public Collection getFieldDataList() {
		return fieldDataList;
	}
	/**
	 * @param fieldDataList the fieldDataList to set
	 */
	public void setFieldDataList(Collection fieldDataList) {
		this.fieldDataList = fieldDataList;
	}

}
