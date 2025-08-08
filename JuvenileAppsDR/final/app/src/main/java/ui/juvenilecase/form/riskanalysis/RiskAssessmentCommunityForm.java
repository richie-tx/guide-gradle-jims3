package ui.juvenilecase.form.riskanalysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import naming.RiskAnalysisConstants;

import org.apache.struts.action.ActionForm;

/**
 * @author dgibler
 *	@deprecated
 */
public class RiskAssessmentCommunityForm extends ActionForm
{
	private String action;
	//private Date communityAssessmentDate;
	
	private String assessmentType = RiskAnalysisConstants.RISK_TYPE_COMMUNITY;
	private String juvenileNum;
	private String casefileID;
	//private String recommendation;
	private List recommendations;
	private String recommendationScore;
	private String riskAnalysisId;

	private List questionAnswers = new ArrayList();
	private List processedQuestionAnswers;
	
	private List processedViewQuestionAnswers;

	/**
	 * @return the action
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @return
	 */
	public Date getCommunityAssessmentDate()
	{
		return new Date();
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
	 * @return
	 */
	public Collection getProcessedQuestionAnswers()
	{
		return processedQuestionAnswers;
	}

	/**
	 * @return
	 */
	public List getQuestionAnswers()
	{
		return questionAnswers;
	}

	/**
	 * @return
	 */
//	public String getRecommendation()
//	{
//		return recommendation;
//	}

	/**
	 * @return
	 */
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
	
	/**
	 * @param action the action to set
	 */
	public void setAction(String action)
	{
		this.action = action;
	}

	/**
	 * @param string
	 */
	public void setAssessmentType(String string)
	{
		assessmentType = string;
	}

	/**
	 * @param string
	 */
	public void setCasefileID(String string)
	{
		casefileID = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @param collection
	 */
	public void setProcessedQuestionAnswers(List collection)
	{
		processedQuestionAnswers = collection;
	}

	/**
	 * @param collection
	 */
	public void setQuestionAnswers(List collection)
	{
		questionAnswers = collection;
	}

	/**
	 * @param string
	 */
//	public void setRecommendation(String string)
//	{
//		recommendation = string;
//	}

/*	*//**
	 * @param date
	 *//*
	public void setCommunityAssessmentDate(Date date)
	{
		communityAssessmentDate = date;
	}
*/
	/**
	 * @param string
	 */
	public void setRecommendationScore(String string)
	{
		recommendationScore = string;
	}
	
	/**
     * @return
     */
    public List getProcessedViewQuestionAnswers() {
		return processedViewQuestionAnswers;
	}

	/**
	 * @param viewOnlyRequestEvents
	 */
	public void setProcessedViewQuestionAnswers(List processedViewQuestionAnswers) {
		this.processedViewQuestionAnswers = processedViewQuestionAnswers;
	}

	public void setRecommendations(List recommendations) {
		this.recommendations = recommendations;
	}

	public List getRecommendations() {
		return recommendations;
	}

}
