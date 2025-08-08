/*
 * Created on Jun 28, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.mentalhealth.reply;

import java.util.Date;

import messaging.contact.domintf.IName;
import mojo.km.messaging.ResponseEvent;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MAYSISubAssessResponseEvent extends ResponseEvent {
	private String subAssessId;
	private String reviewComments;
	private Date assessmentReviewdate;
	private String assessmentReviewtime;
	private IName subAssessOfficerName;
	private String subAssessOfficerId; 
	private String providerType;
	private String providerTypeId;
	private boolean subReferral;
	private boolean assessComplete;
	private String assessmentOption;
	/**
	 * @return Returns the assessComplete.
	 */
	public boolean isAssessComplete() {
		return assessComplete;
	}
	/**
	 * @param assessComplete The assessComplete to set.
	 */
	public void setAssessComplete(boolean assessComplete) {
		this.assessComplete = assessComplete;
	}
	/**
	 * @return Returns the assessmentReviewdate.
	 */
	public Date getAssessmentReviewdate() {
		return assessmentReviewdate;
	}
	/**
	 * @param assessmentReviewdate The assessmentReviewdate to set.
	 */
	public void setAssessmentReviewdate(Date assessmentReviewdate) {
		this.assessmentReviewdate = assessmentReviewdate;
	}
	/**
	 * @return Returns the assessmentReviewtime.
	 */
	public String getAssessmentReviewtime() {
		return assessmentReviewtime;
	}
	/**
	 * @param assessmentReviewtime The assessmentReviewtime to set.
	 */
	public void setAssessmentReviewtime(String assessmentReviewtime) {
		this.assessmentReviewtime = assessmentReviewtime;
	}
	/**
	 * @return Returns the providerType.
	 */
	public String getProviderType() {
		return providerType;
	}
	/**
	 * @param providerType The providerType to set.
	 */
	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}
	/**
	 * @return Returns the providerTypeId.
	 */
	public String getProviderTypeId() {
		return providerTypeId;
	}
	/**
	 * @param providerTypeId The providerTypeId to set.
	 */
	public void setProviderTypeId(String providerTypeId) {
		this.providerTypeId = providerTypeId;
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
	 * @return Returns the subAssessId.
	 */
	public String getSubAssessId() {
		return subAssessId;
	}
	/**
	 * @param subAssessId The subAssessId to set.
	 */
	public void setSubAssessId(String subAssessId) {
		this.subAssessId = subAssessId;
	}
	/**
	 * @return Returns the subAssessOfficerId.
	 */
	public String getSubAssessOfficerId() {
		return subAssessOfficerId;
	}
	/**
	 * @param subAssessOfficerId The subAssessOfficerId to set.
	 */
	public void setSubAssessOfficerId(String subAssessOfficerId) {
		this.subAssessOfficerId = subAssessOfficerId;
	}
	/**
	 * @return Returns the subAssessOfficerName.
	 */
	public IName getSubAssessOfficerName() {
		return subAssessOfficerName;
	}
	/**
	 * @param subAssessOfficerName The subAssessOfficerName to set.
	 */
	public void setSubAssessOfficerName(IName subAssessOfficerName) {
		this.subAssessOfficerName = subAssessOfficerName;
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
	/**
	 * @return Returns the assessmentOption.
	 */
	public String getAssessmentOption() {
		return assessmentOption;
	}
	/**
	 * @param assessmentOption The assessmentOption to set.
	 */
	public void setAssessmentOption(String assessmentOption) {
		this.assessmentOption = assessmentOption;
	}
}
