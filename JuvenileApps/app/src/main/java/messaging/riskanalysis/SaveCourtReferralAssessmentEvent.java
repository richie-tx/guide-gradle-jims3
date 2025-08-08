//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\SaveResidentialAssessmentEvent.java

package messaging.riskanalysis;

import java.util.Date;

import mojo.km.messaging.Composite.CompositeRequest;

public class SaveCourtReferralAssessmentEvent extends CompositeRequest 
{
   
	private String riskAnalysisId;
	private String assessmentID;
	private String assessmentType;
	private String jpoUserID = "";
	private boolean updateOverRiddenStatus = false;
	private boolean recommendationOveridden;
	private String overiddenReasonCd;
	private String overiddenReasonOther;
	private boolean isUpdate;
	private boolean isCompletionUpdate;
	private String modReason;
	private String casefileID = "";
	private String juvenileNum = "";
	private Date assessmentDate;
	
	private String collateralVisits; 
    private String courtDispositionTJPC;
    private String face2FaceVisits;
    private String jjsCourtDecision;
    private String jjsCourtDisposition;
	
   /**
    * @roseuid 4357DCEA00E6
    */
   public SaveCourtReferralAssessmentEvent() 
   {
    
   }
	/**
	 * @return
	 */
	public String getCasefileID()
	{
		return casefileID;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param string
	 */
	public void setCasefileID(final String string)
	{
		casefileID = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(final String string)
	{
		juvenileNum = string;
	}


	/**
	 * @return
	 */
	public Date getAssessmentDate()
	{
		return assessmentDate;
	}

	/**
	 * @param date
	 */
	public void setAssessmentDate(final Date date)
	{
		assessmentDate = date;
	}
	/**
	 * @return riskAnalysisId
	 */
	public String getRiskAnalysisId() {
		return riskAnalysisId;
	}
	/**
	 * @param riskAnalysisId
	 */
	public void setRiskAnalysisId(String riskAnalysisId) {
		this.riskAnalysisId = riskAnalysisId;
	}
	
	public void setAssessmentID(String assessmentID) {
		this.assessmentID = assessmentID;
	}

	public String getAssessmentID() {
		return assessmentID;
	}

	public void setUpdateOverRiddenStatus(boolean updateOverRiddenStatus) {
		this.updateOverRiddenStatus = updateOverRiddenStatus;
	}

	public boolean isUpdateOverRiddenStatus() {
		return updateOverRiddenStatus;
	}

	public void setRecommendationOveridden(boolean recommendationOveridden) {
		this.recommendationOveridden = recommendationOveridden;
	}

	public boolean isRecommendationOveridden() {
		return recommendationOveridden;
	}

	public void setOveriddenReasonCd(String overiddenReasonCd) {
		this.overiddenReasonCd = overiddenReasonCd;
	}

	public String getOveriddenReasonCd() {
		return overiddenReasonCd;
	}

	public void setOveriddenReasonOther(String overiddenReasonOther) {
		this.overiddenReasonOther = overiddenReasonOther;
	}

	public String getOveriddenReasonOther() {
		return overiddenReasonOther;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setModReason(String modReason) {
		this.modReason = modReason;
	}

	public String getModReason() {
		return modReason;
	}
	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}
	public String getAssessmentType() {
		return assessmentType;
	}
	public void setJpoUserID(String jpoUserID) {
		this.jpoUserID = jpoUserID;
	}
	public String getJpoUserID() {
		return jpoUserID;
	}
	public void setCompletionUpdate(boolean isCompletionUpdate) {
		this.isCompletionUpdate = isCompletionUpdate;
	}
	public boolean isCompletionUpdate() {
		return isCompletionUpdate;
	}
	
	public void setCollateralVisits(String collateralVisits) 
	{
		this.collateralVisits = collateralVisits;
	}

	public String getCollateralVisits() 
	{
		return collateralVisits;
	}

	public void setCourtDispositionTJPC(String courtDispositionTJPC) 
	{
		this.courtDispositionTJPC = courtDispositionTJPC;
	}

	public String getCourtDispositionTJPC() 
	{
		return courtDispositionTJPC;
	}

	public void setFace2FaceVisits(String face2FaceVisits) 
	{
		this.face2FaceVisits = face2FaceVisits;
	}

	public String getFace2FaceVisits() 
	{
		return face2FaceVisits;
	}

	public void setJjsCourtDecision(String jjsCourtDecision) {
		this.jjsCourtDecision = jjsCourtDecision;
	}

	public String getJjsCourtDecision() 
	{
		return jjsCourtDecision;
	}

	public void setJjsCourtDisposition(String jjsCourtDisposition) 
	{
		this.jjsCourtDisposition = jjsCourtDisposition;
	}

	public String getJjsCourtDisposition() 
	{
		return jjsCourtDisposition;
	}

}
