package messaging.riskanalysis.reply;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import ui.juvenilecase.casefile.form.JournalForm.CaseReviewRiskAnalysisBean;

import mojo.km.messaging.ResponseEvent;

public class RiskAssessmentResponseEvent extends ResponseEvent {
	private Date enteredDate = null;
	private String custodyStatus = "";
	private String assessmentType = "";
	private String assessmentTypeDesc = "";
	private List recommendations;
	private boolean recommendationOverridden;
	private String overRiddenReasonCd;
	private String overRiddenReasonOther;
	private String modReason;
	private String casefileId;
	private int riskFormulaId;
	private String riskAnalysisId = "";
	
	public Date getEnteredDate() {
		return enteredDate;
	}
	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}
	public String getCustodyStatus() {
		return custodyStatus;
	}
	public void setCustodyStatus(String custodyStatus) {
		this.custodyStatus = custodyStatus;
	}
	public String getAssessmentType() {
		return assessmentType;
	}
	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}
	public List getRecommendations() {
		return recommendations;
	}
	public void setRecommendations(List recommendations) {
		this.recommendations = recommendations;
	}
	public boolean isRecommendationOverridden() {
		return recommendationOverridden;
	}
	public void setRecommendationOverridden(boolean recommendationOverridden) {
		this.recommendationOverridden = recommendationOverridden;
	}
	public String getOverRiddenReasonCd() {
		return overRiddenReasonCd;
	}
	public void setOverRiddenReasonCd(String overRiddenReasonCd) {
		this.overRiddenReasonCd = overRiddenReasonCd;
	}
	public String getOverRiddenReasonOther() {
		return overRiddenReasonOther;
	}
	public void setOverRiddenReasonOther(String overRiddenReasonOther) {
		this.overRiddenReasonOther = overRiddenReasonOther;
	}
	public String getModReason() {
		return modReason;
	}
	public void setModReason(String modReason) {
		this.modReason = modReason;
	}
	public String getCasefileId() {
		return casefileId;
	}
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	public void setRiskFormulaId(int riskFormulaId) {
		this.riskFormulaId = riskFormulaId;
	}
	public int getRiskFormulaId() {
		return riskFormulaId;
	}
	public void setAssessmentTypeDesc(String assessmentTypeDesc) {
		this.assessmentTypeDesc = assessmentTypeDesc;
	}
	public String getAssessmentTypeDesc() {
		return assessmentTypeDesc;
	}
	/**
	 * @return the riskAnalysisId
	 */
	public String getRiskAnalysisId() {
		return riskAnalysisId;
	}
	/**
	 * @param riskAnalysisId the riskAnalysisId to set
	 */
	public void setRiskAnalysisId(String riskAnalysisId) {
		this.riskAnalysisId = riskAnalysisId;
	}
	
	/**
	 * comparator to order by latest date first
	 */
	public static Comparator CaseReviewJournalLatestRiskAssessmentComparator = new Comparator() {
		public int compare(Object riskAssessment, Object otherRiskAssessment) {
			
		  int result = 0;
		  Date riskAnalysisDate = ((RiskAssessmentResponseEvent)riskAssessment).getEnteredDate();
		  Date otherRiskAnalysisDate = ((RiskAssessmentResponseEvent)otherRiskAssessment).getEnteredDate();
		  
		  if(riskAnalysisDate == null || riskAnalysisDate.equals(""))
		  {
			  result = 1;
		  }else if(otherRiskAnalysisDate == null || otherRiskAnalysisDate.equals(""))
		  {
			  result = -1;
		  }
		  else 
		  {
			  result = otherRiskAnalysisDate.compareTo(riskAnalysisDate);
		  }
		  
		  
		  return result;
		}	
	};
}
