/*
 * Created on Oct 31, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.Date;
import java.util.List;

import mojo.km.messaging.ResponseEvent;

/**
 * @author kmurthy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CourtReferralAssessmentEvent extends ResponseEvent
{

	private Date enteredDate = null;
	private String custodyStatus = "";
	private String assessmentType = "";
	private List recommendations;
	private boolean recommendationOverridden;
	private String overRiddenReasonCd;
	private String overRiddenReasonOther;
	private String modReason;
	
	private String casefileId;
	
	private boolean completed;
	
	private String collateralVisits; 
    private String face2FaceVisits;
    private String courtDispositionTJPC;
    private String jjsCourtDecision;
    private String jjsCourtDisposition;

	public CourtReferralAssessmentEvent()
	{
	}
	
	/**
	 * @return
	 */
	public String getAssessmentType()
	{
		return assessmentType;
	}

	/**
	 * @return
	 */
	public String getCustodyStatus()
	{
		return custodyStatus;
	}

	/**
	 * @return
	 */
	public Date getEnteredDate()
	{
		return enteredDate;
	}

	/**
	 * @param string
	 */
	public void setAssessmentType(final String string)
	{
		assessmentType = string;
	}

	/**
	 * @param string
	 */
	public void setCustodyStatus(final String string)
	{
		custodyStatus = string;
	}

	/**
	 * @param date
	 */
	public void setEnteredDate(final Date date)
	{
		enteredDate = date;
	}
	
	/**
	 * @return recommendationOverridden
	 */
	public boolean isRecommendationOverridden() {
		return recommendationOverridden;
	}

	/**
	 * @param recommendationOverridden
	 */
	public void setRecommendationOverridden(boolean recommendationOverridden) {
		this.recommendationOverridden = recommendationOverridden;
	}

	/**
	 * @return overRiddenReasonCd
	 */
	public String getOverRiddenReasonCd() {
		return overRiddenReasonCd;
	}

	/**
	 * @param overRiddenReasonCd
	 */
	public void setOverRiddenReasonCd(String overRiddenReasonCd) {
		this.overRiddenReasonCd = overRiddenReasonCd;
	}

	/**
	 * @return overRiddenReasonOther
	 */
	public String getOverRiddenReasonOther() {
		return overRiddenReasonOther;
	}

	/**
	 * @param overRiddenReasonOther
	 */
	public void setOverRiddenReasonOther(String overRiddenReasonOther) {
		this.overRiddenReasonOther = overRiddenReasonOther;
	}

	public void setModReason(String modReason) {
		this.modReason = modReason;
	}

	public String getModReason() {
		return modReason;
	}
	
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}

	public String getCasefileId() {
		return casefileId;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setRecommendations(List recommendations) {
		this.recommendations = recommendations;
	}

	public List getRecommendations() {
		return recommendations;
	}

	public void setCollateralVisits(String collateralVisits) {
		this.collateralVisits = collateralVisits;
	}

	public String getCollateralVisits() {
		return collateralVisits;
	}

	public void setFace2FaceVisits(String face2FaceVisits) {
		this.face2FaceVisits = face2FaceVisits;
	}

	public String getFace2FaceVisits() {
		return face2FaceVisits;
	}

	public void setCourtDispositionTJPC(String courtDispositionTJPC) {
		this.courtDispositionTJPC = courtDispositionTJPC;
	}

	public String getCourtDispositionTJPC() {
		return courtDispositionTJPC;
	}

	public void setJjsCourtDecision(String jjsCourtDecision) {
		this.jjsCourtDecision = jjsCourtDecision;
	}

	public String getJjsCourtDecision() {
		return jjsCourtDecision;
	}

	public void setJjsCourtDisposition(String jjsCourtDisposition) {
		this.jjsCourtDisposition = jjsCourtDisposition;
	}

	public String getJjsCourtDisposition() {
		return jjsCourtDisposition;
	}

}
