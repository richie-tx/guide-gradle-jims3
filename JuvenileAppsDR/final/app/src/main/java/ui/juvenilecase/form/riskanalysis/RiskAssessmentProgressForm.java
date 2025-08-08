/*
 * Created on Sep 29, 2005
 *
 */
package ui.juvenilecase.form.riskanalysis;

import java.util.Date;

import org.apache.struts.action.ActionForm;

/**
 * @author dwilliamson
 * @deprecated 
 */
public class RiskAssessmentProgressForm extends ActionForm
{
	private String action;
	
	//private Date progressAssessmentDate;

//    private String assessmentType = RiskAnalysisConstants.RISK_TYPE_PROGRESS;
//
//    private String juvenileNum;
//
//    private String casefileID;
//
//    //private String recommendation;
//    private List recommendations;

//    private String recommendationScore;
//    
//    private String riskAnalysisId;

    private String totalSupervisionRules;

    private int supervisionMonth;

    private String supervisionLevel;

    //private String supervisionLevelPoints;

//    private List questionAnswers = new ArrayList();
//
//    private List processedQuestionAnswers;
//    
//    private List processedViewQuestionAnswers;

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
    public Date getProgressAssessmentDate()
    {
        return new Date();
    }

    /**
     * @return
     */
/*    public String getAssessmentType()
    {
        return assessmentType;
    }
*/
    /**
     * @return
     */
/*    public String getCasefileID()
    {
        return casefileID;
    }
*/
    /**
     * @return
     */
/*    public String getJuvenileNum()
    {
        return juvenileNum;
    }
*/
    /**
     * @return
     */
/*    public Collection getProcessedQuestionAnswers()
    {
        return processedQuestionAnswers;
    }
*/
    /**
     * @return
     */
/*    public List getQuestionAnswers()
    {
        return questionAnswers;
    }
*/
    /**
     * @return
     */
/*    public String getRecommendation()
    {
        return recommendation;
    }
*/
    /**
     * @return
     */
/*    public String getRecommendationScore()
    {
        return recommendationScore;
    }
    
    public void setRiskAnalysisId(String riskAnalysisId) {
		this.riskAnalysisId = riskAnalysisId;
	}

	public String getRiskAnalysisId() {
		return riskAnalysisId;
	}
*/    

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
/*    public void setAssessmentType(String string)
    {
        assessmentType = string;
    }

*/    /**
     * @param string
     */
/*    public void setCasefileID(String string)
    {
        casefileID = string;
    }
*/
    /**
     * @param string
     */
/*    public void setJuvenileNum(String string)
    {
        juvenileNum = string;
    }
*/
    /**
     * @param collection
     */
/*    public void setProcessedQuestionAnswers(List collection)
    {
        processedQuestionAnswers = collection;
    }
 */   

    /**
     * @param collection
     */
/*    public void setQuestionAnswers(List collection)
    {
        questionAnswers = collection;
    }

*/    /**
     * @param string
     */
/*    public void setRecommendation(String string)
    {
        recommendation = string;
    }
*/
    /**
     * @param date
     */
/*    public void setProgressAssessmentDate(Date date)
    {
        progressAssessmentDate = date;
    }
*/
    /**
     * @param string
     */
/*    public void setRecommendationScore(String string)
    {
        recommendationScore = string;
    }
*/
    /**
     * @return
     */
    public int getSupervisionMonth()
    {
        return supervisionMonth;
    }

    /**
     * @return
     */
    public String getTotalSupervisionRules()
    {
        return totalSupervisionRules;
    }

    /**
     * @param i
     */
    public void setSupervisionMonth(int i)
    {
        supervisionMonth = i;
    }

    /**
     * @param i
     */
    public void setTotalSupervisionRules(String i)
    {
        totalSupervisionRules = i;
    }

    /**
     * @return
     */
    public String getSupervisionLevel()
    {
        return supervisionLevel;
    }

    /**
     * @param string
     */
    public void setSupervisionLevel(String string)
    {
        supervisionLevel = string;
    }

    /**
     * @return
     */
/*    public String getSupervisionLevelPoints()
    {
        return supervisionLevelPoints;
    }
*/
    /**
     * @param string
     */
 /*   public void setSupervisionLevelPoints(String string)
    {
        supervisionLevelPoints = string;
    }
 */   
    /**
     * @return
     */
/*    public List getProcessedViewQuestionAnswers() {
		return processedViewQuestionAnswers;
	}
*/
	/**
	 * @param viewOnlyRequestEvents
	 */
/*	public void setProcessedViewQuestionAnswers(List processedViewQuestionAnswers) {
		this.processedViewQuestionAnswers = processedViewQuestionAnswers;
	}

	public void setRecommendations(List recommendations) {
		this.recommendations = recommendations;
	}

	public List getRecommendations() {
		return recommendations;
	}
*/
}
