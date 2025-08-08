package messaging.riskanalysis;

import java.util.Date;

import mojo.km.messaging.Composite.CompositeRequest;

public class SaveRiskAssessmentEvent extends CompositeRequest {
	
	private String assessmentID;
	private boolean isUpdate;
	private String modReason;
	private String filteredModReason;
	private String casefileID = "";
	private String juvenileNum = "";
	private Date assessmentDate = null;
	private String riskAnalysisId;
	private String assessmentType;
	private String riskFormulaId;
	private boolean updateOverRiddenStatus = false;
	private boolean recommendationOveridden;
	private String overiddenReasonCd;
	private String overiddenReasonOther;
	//private boolean isNewReferral;
	private boolean isCompletionUpdate;
   /**
    * @roseuid 433D83FA03C0
    */
   
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
	
	public String getAssessmentID() {
		return assessmentID;
	}
	public void setAssessmentID(String assessmentID) {
		this.assessmentID = assessmentID;
	}
	public boolean isUpdate() {
		return isUpdate;
	}
	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	public String getModReason() {
		return modReason;
	}
	public void setModReason(String modReason) {
		this.modReason = modReason;
	}
	public String getCasefileID() {
		return casefileID;
	}
	public void setCasefileID(String casefileID) {
		this.casefileID = casefileID;
	}
	public String getJuvenileNum() {
		return juvenileNum;
	}
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	public Date getAssessmentDate() {
		return assessmentDate;
	}
	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}
	public String getRiskAnalysisId() {
		return riskAnalysisId;
	}
	public void setRiskAnalysisId(String riskAnalysisId) {
		this.riskAnalysisId = riskAnalysisId;
	}
	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}
	public String getAssessmentType() {
		return assessmentType;
	}
	public void setRiskFormulaId(String formulaId) {
		this.riskFormulaId = formulaId;
	}
	public String getRiskFormulaId() {
		return riskFormulaId;
	}

/*	public void setNewReferral(boolean isNewReferral) {
		this.isNewReferral = isNewReferral;
	}

	public boolean isNewReferral() {
		return isNewReferral;
	}
*/
	public void setCompletionUpdate(boolean isCompletionUpdate) {
		this.isCompletionUpdate = isCompletionUpdate;
	}

	public boolean isCompletionUpdate() {
		return isCompletionUpdate;
	}

	public String getFilteredModReason()
	{
	    return filteredModReason;
	}

	public void setFilteredModReason(String filteredModReason)
	{
	    this.filteredModReason = filteredModReason;
	}

}
