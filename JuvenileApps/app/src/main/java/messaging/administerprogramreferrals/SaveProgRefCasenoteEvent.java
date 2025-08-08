package messaging.administerprogramreferrals;

import mojo.km.messaging.RequestEvent;

public class SaveProgRefCasenoteEvent extends RequestEvent
{
	private String programReferralId;
	
	private String casenoteSubjectCd;
	private String casenoteTypeId;
	private String contactMethodId;
	private String howGeneratedId;
	
	private String casenoteContext;
	
	private String casenoteComments;
	
	
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
	 * @return the casenoteComments
	 */
	public String getCasenoteComments() {
		return casenoteComments;
	}
	/**
	 * @param casenoteComments the casenoteComments to set
	 */
	public void setCasenoteComments(String casenoteComments) {
		this.casenoteComments = casenoteComments;
	}
	/**
	 * @return the casenoteSubjectCd
	 */
	public String getCasenoteSubjectCd() {
		return casenoteSubjectCd;
	}
	/**
	 * @param casenoteSubjectCd the casenoteSubjectCd to set
	 */
	public void setCasenoteSubjectCd(String casenoteSubjectCd) {
		this.casenoteSubjectCd = casenoteSubjectCd;
	}
	/**
	 * @return the casenoteTypeId
	 */
	public String getCasenoteTypeId() {
		return casenoteTypeId;
	}
	/**
	 * @param casenoteTypeId the casenoteTypeId to set
	 */
	public void setCasenoteTypeId(String casenoteTypeId) {
		this.casenoteTypeId = casenoteTypeId;
	}
	/**
	 * @return the contactMethodId
	 */
	public String getContactMethodId() {
		return contactMethodId;
	}
	/**
	 * @param contactMethodId the contactMethodId to set
	 */
	public void setContactMethodId(String contactMethodId) {
		this.contactMethodId = contactMethodId;
	}
	/**
	 * @return the howGeneratedId
	 */
	public String getHowGeneratedId() {
		return howGeneratedId;
	}
	/**
	 * @param howGeneratedId the howGeneratedId to set
	 */
	public void setHowGeneratedId(String howGeneratedId) {
		this.howGeneratedId = howGeneratedId;
	}
	/**
	 * @return the casenoteContext
	 */
	public String getCasenoteContext() {
		return casenoteContext;
	}
	/**
	 * @param casenoteContext the casenoteContext to set
	 */
	public void setCasenoteContext(String casenoteContext) {
		this.casenoteContext = casenoteContext;
	}
	
}
