/*
 * Created on Sep 27, 2005
 *
 */
package ui.juvenilecase.form.riskanalysis;

import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * @author dwilliamson
 */
public class RiskAssessmentResidentialForm extends ActionForm
{
	
	private String action;
	
    //private Date residentialAssessmentDate;

    // generic risk assessment attributes
/*    private String assessmentType = RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL;

    private String juvenileNum;

    private String casefileID;

    //private String recommendation;
    private List recommendations;

    private String recommendationScore;
    
    private String riskAnalysisId;

    private List questionAnswers = new ArrayList();

    private List processedQuestionAnswers;
    
    private List processedViewQuestionAnswers;

*/    private String traitTypeId;

    //list of all the Juv Traits details from JVCTRAITS table
    private List juvTraitsDetails;

    //list of all the root Traits for display in the drop down
    private List rootTraitList;

    //list of all the Child traits for the above root traits
    private List childTraitList;

    //list of the juv traits details to be displayed after the user chooses
    // from the Root trait dropdown
    private List displayJuvTraitsDetails;

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
 /*   public String getJuvenileNum()
    {
        return juvenileNum;
    }
*/
    /**
     * @return
     */
 /*   public Collection getProcessedQuestionAnswers()
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
     * @return
     */
    public Date getResidentialAssessmentDate()
    {
        return new Date();
    }

    /**
     * @param string
     */
 /*   public void setAssessmentType(String string)
    {
        assessmentType = string;
    }
*/
    /**
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
 /*   public void setJuvenileNum(String string)
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
*/
    /**
     * @param string
     */
/*    public void setRecommendation(String string)
    {
        recommendation = string;
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
     * @param date
     */
/*    public void setResidentialAssessmentDate(Date date)
    {
        residentialAssessmentDate = date;
    }
*/
    /**
     * @return
     */
    public List getJuvTraitsDetails()
    {
        return juvTraitsDetails;
    }

    /**
     * @param iterator
     */
    public void setJuvTraitsDetails(List iterator)
    {
        juvTraitsDetails = iterator;
    }

    /**
     * @return
     */
    public String getTraitTypeId()
    {
        return traitTypeId;
    }

    /**
     * @param string
     */
    public void setTraitTypeId(String string)
    {
        traitTypeId = string;
    }

    /**
     * @return
     */
    public List getChildTraitList()
    {
        return childTraitList;
    }

    /**
     * @return
     */
    public List getRootTraitList()
    {
        return rootTraitList;
    }

    /**
     * @param list
     */
    public void setChildTraitList(List list)
    {
        childTraitList = list;
    }

    /**
     * @param list
     */
    public void setRootTraitList(List list)
    {
        rootTraitList = list;
    }

    /**
     * @return
     */
    public List getDisplayJuvTraitsDetails()
    {
        return displayJuvTraitsDetails;
    }

    /**
     * @param list
     */
    public void setDisplayJuvTraitsDetails(List list)
    {
        displayJuvTraitsDetails = list;
    }
    
    /**
     * @return
     */
//    public List getProcessedViewQuestionAnswers() {
//		return processedViewQuestionAnswers;
//	}

	/**
	 * @param viewOnlyRequestEvents
	 */
/*    public void setProcessedViewQuestionAnswers(List processedViewQuestionAnswers) {
		this.processedViewQuestionAnswers = processedViewQuestionAnswers;
	}
*/    
    /** 
	 * @return the action
	 */
	public String getAction()
	{
		return action;
	}
	   
    /**
	 * @param action the action to set
	 */
	public void setAction(String action)
	{
		this.action = action;
	}

/*	public void setRecommendations(List recommendations) {
		this.recommendations = recommendations;
	}
*/
/*	public List getRecommendations() {
		return recommendations;
	}
*/

}
