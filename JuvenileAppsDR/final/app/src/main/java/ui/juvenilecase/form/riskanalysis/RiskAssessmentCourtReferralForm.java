package ui.juvenilecase.form.riskanalysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import ui.common.CodeHelper;

public class RiskAssessmentCourtReferralForm extends ActionForm
{
	private String action;
	//private Date courtReferralAssessmentDate;    
	//= RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE;
	//= RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE;
    private String assessmentType; 
    private String juvenileNum;
    private String casefileID;
    private String recommendation;
    private List recommendations;
    private String recommendationScore;
    private String riskAnalysisId;
    private List questionAnswers = new ArrayList();
    private List processedQuestionAnswers;
    private List processedViewQuestionAnswers;
    
    private boolean completed;
    
    private String collateralVisits; 
    private String courtDispositionTJPC;
    private String courtDispositionTJPCDesc;
    private String face2FaceVisits;
    private String jjsCourtDecision;
    private String jjsCourtDisposition;
    
    private List suggestedCasePlanDomains;
    
    private int suggestedCasePlanDomainsSizeMinusOne;
    
    public void clearCompletionFields()
    {
    	this.collateralVisits = null;
    	this.courtDispositionTJPC = null;
    	this.courtDispositionTJPCDesc = null;
    	this.face2FaceVisits = null;
    	this.jjsCourtDecision = null;
    	this.jjsCourtDisposition = null;
    }

	public String getAction()
	{
		return action;
	}
	
    public String getAssessmentType()
    {
        return assessmentType;
    }

    public Date getCourtReferralAssessmentDate() {
		return new Date();
	}
    
/*    public void setCourtReferralAssessmentDate(Date courtReferralAssessmentDate) {
		this.courtReferralAssessmentDate = courtReferralAssessmentDate;
	}
*/
    public String getCasefileID()
    {
        return casefileID;
    }

    public Date getInterviewAssessmentDate()
    {
        return new Date();
    }

    public String getJuvenileNum()
    {
        return juvenileNum;
    }

    public Collection getProcessedQuestionAnswers()
    {
        return processedQuestionAnswers;
    }

    public List getQuestionAnswers()
    {
        return questionAnswers;
    }

    public String getRecommendation()
    {
        return recommendation;
    }

    public String getRecommendationScore()
    {
        return recommendationScore;
    }
    
    public void setRiskAnalysisId(String riskAnalysisId) {
		this.riskAnalysisId = riskAnalysisId;
	}

	public String getRiskAnalysisId() {
		return riskAnalysisId;
	}
    
	public void setAction(String action)
	{
		this.action = action;
	}

    public void setAssessmentType(String string)
    {
        assessmentType = string;
    }

    public void setCasefileID(String string)
    {
        casefileID = string;
    }

    public void setJuvenileNum(String string)
    {
        juvenileNum = string;
    }

    public void setProcessedQuestionAnswers(List collection)
    {
        processedQuestionAnswers = collection;
    }

    public void setQuestionAnswers(List collection)
    {
        questionAnswers = collection;
    }

    public void setRecommendation(String string)
    {
        recommendation = string;
    }

    public void setRecommendationScore(String string)
    {
        recommendationScore = string;
    }
	
    public List getProcessedViewQuestionAnswers() 
    {
		return processedViewQuestionAnswers;
	}

    public void setProcessedViewQuestionAnswers(List processedViewQuestionAnswers) 
    {
		this.processedViewQuestionAnswers = processedViewQuestionAnswers;
	}

	public void setRecommendations(List recommendations) 
	{
		this.recommendations = recommendations;
	}

	public List getRecommendations() 
	{
		return recommendations;
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

	public String getCourtDispositionTJPCDesc() 
	{
		if (courtDispositionTJPC != null )
		{
			courtDispositionTJPCDesc 
				= CodeHelper.getCodeDescription("TJPC_COURT_DISPOSITIONS", courtDispositionTJPC);
		} 
		return courtDispositionTJPCDesc;
	}

	public void setSuggestedCasePlanDomains(List suggestedCasePlanDomains) {
		this.suggestedCasePlanDomains = suggestedCasePlanDomains;
	}

	public List getSuggestedCasePlanDomains() {
		return suggestedCasePlanDomains;
	}
	
	public int getSuggestedCasePlanDomainsSizeMinusOne() {
		
		if (suggestedCasePlanDomains != null) {
			return suggestedCasePlanDomains.size() - 1;
		} else {
			return 0;
		}
		
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isCompleted() {
		return completed;
	}

}
