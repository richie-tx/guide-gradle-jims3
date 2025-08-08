//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\SaveSubsequentMAYSIDataEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class SaveSubsequentMAYSIDataEvent extends RequestEvent
{
	public String reviewUserId;
	public Date reviewDate;
	public String reviewComments;
	public String juvenileMAYSIAssessId;
	private String providerTypeReferredId;
	private boolean subReferral;
	private boolean assessmentComplete;
	
	

	/**
	 * @roseuid 42790E0A0119
	 */
	public SaveSubsequentMAYSIDataEvent()
	{

	}

	
	/**
	 * @return Returns the assessmentComplete.
	 */
	public boolean isAssessmentComplete() {
		return assessmentComplete;
	}
	/**
	 * @param assessmentComplete The assessmentComplete to set.
	 */
	public void setAssessmentComplete(boolean assessmentComplete) {
		this.assessmentComplete = assessmentComplete;
	}
	/**
	 * @return Returns the juvenileMAYSIAssessId.
	 */
	public String getJuvenileMAYSIAssessId() {
		return juvenileMAYSIAssessId;
	}
	/**
	 * @param juvenileMAYSIAssessId The juvenileMAYSIAssessId to set.
	 */
	public void setJuvenileMAYSIAssessId(String juvenileMAYSIAssessId) {
		this.juvenileMAYSIAssessId = juvenileMAYSIAssessId;
	}
	/**
	 * @return Returns the providerTypeReferredId.
	 */
	public String getProviderTypeReferredId() {
		return providerTypeReferredId;
	}
	/**
	 * @param providerTypeReferredId The providerTypeReferredId to set.
	 */
	public void setProviderTypeReferredId(String providerTypeReferredId) {
		this.providerTypeReferredId = providerTypeReferredId;
	}
	/**
	 * @return Returns the reviewComments.
	 */
	public String getReviewComments() {
		return reviewComments;
	}
	/**
	 * @param reviewComments The reviewComments to set.
	 */
	public void setReviewComments(String reviewComments) {
		this.reviewComments = reviewComments;
	}
	/**
	 * @return Returns the reviewDate.
	 */
	public Date getReviewDate() {
		return reviewDate;
	}
	/**
	 * @param reviewDate The reviewDate to set.
	 */
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	/**
	 * @return Returns the reviewUserId.
	 */
	public String getReviewUserId() {
		return reviewUserId;
	}
	/**
	 * @param reviewUserId The reviewUserId to set.
	 */
	public void setReviewUserId(String reviewUserId) {
		this.reviewUserId = reviewUserId;
	}
	/**
	 * @return Returns the subReferral.
	 */
	public boolean isSubReferral() {
		return subReferral;
	}
	/**
	 * @param subReferral The subReferral to set.
	 */
	public void setSubReferral(boolean subReferral) {
		this.subReferral = subReferral;
	}
}
