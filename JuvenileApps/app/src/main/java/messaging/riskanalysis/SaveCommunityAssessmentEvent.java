//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\SaveResidentialAssessmentEvent.java

package messaging.riskanalysis;

import java.util.Date;

import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeRequest;

public class SaveCommunityAssessmentEvent extends CompositeRequest 
{
   
	private String assessmentID;
	private boolean updateOverRiddenStatus = false;
	private boolean recommendationOveridden;
	private String overiddenReasonCd;
	private String overiddenReasonOther;
	private boolean isUpdate;
	private String modReason;
	
	private String casefileID = "";
	private String juvenileNum = "";
	private Date assessmentDate;
	
	private String riskAnalysisId;
	
   /**
    * @roseuid 4357DCEA00E6
    */
   public SaveCommunityAssessmentEvent() 
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

}
