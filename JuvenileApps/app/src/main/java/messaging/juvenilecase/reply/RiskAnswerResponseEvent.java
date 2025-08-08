/*
 * Created on Oct 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import mojo.km.messaging.ResponseEvent;


/**
 * @author kmurthy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RiskAnswerResponseEvent extends ResponseEvent implements Comparable
{
	private String weightID;
	private String questionID;
	private String questionNumber;
	private String answerText = "";
	private String riskQuestionText = "";
	private String uiControlType;
	private String uiControlTypeDesc;
	private int weight;
	private String riskAnalysisId = "";
	private Date entryDate;
	private String text = ""; //For Dates, Text and UICONSTANTS.ISCHRONIC (Freeform)
	
	private List weightIDs = new ArrayList() ;
	private List chronicIDs = new ArrayList() ;
	private String chronicID;
	private String createUserID;
	
	private String assessmentType;
	
	private String filteredAnswerText;
	
	public RiskAnswerResponseEvent()
	{
	}
	

	/**
	 * @return
	 */
	public String getAnswerText()
	{
		return answerText;
	}

	/**
	 * @return
	 */
	public String getWeightID()
	{
		return weightID;
	}

	/**
	 * @param string
	 */
	public void setAnswerText(final String string)
	{
		answerText = string;
	}

	/**
	 * @param i
	 */
	public void setWeightID(final String i)
	{
		weightID = i;
	}

	/**
	 * @return
	 */
	public int getWeight()
	{
		return weight;
	}

	/**
	 * @param i
	 */
	public void setWeight(final int i)
	{
		weight = i;
	}

	/**
	 * @return
	 */
	public String getRiskQuestionText()
	{
		return riskQuestionText;
	}

	/**
	 * @param string
	 */
	public void setRiskQuestionText(final String string)
	{
		riskQuestionText = string;
	}

	/**
	 * @return
	 */
	public String getRiskAnalysisId()
	{
		return riskAnalysisId;
	}

	/**
	 * @param string
	 */
	public void setRiskAnalysisId(final String string)
	{
		riskAnalysisId = string;
	}
	
	/**
	 * @return entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}


	/**
	 * @param createDate
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	/**
	 * @return
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
     * @return
     */
    public String getQuestionID() {
		return questionID;
	}
	/**
	 * @param questionID
	 */
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}
	/**
	 * @return
	 */
	public List getWeightIDs() {
		return weightIDs;
	}
	/**
	 * @param responseIDs
	 */
	public void setWeightIDs(List weightIDs) {
		this.weightIDs = weightIDs;
	}
	/**
	 * @return
	 */
	public List getChronicIDs() {
		return chronicIDs;
	}
	/**
	 * @param chronicIDs
	 */
	public void setChronicIDs(List chronicIDs) {
		this.chronicIDs = chronicIDs;
	}
	/**
	 * @return
	 */
	public String getChronicID() {
		return chronicID;
	}
	/**
	 * @param chronicID
	 */
	public void setChronicID(String chronicID) {
		this.chronicID = chronicID;
	}
	
	/**
	 * @return
	 */
	public String getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * @param questionNumber
	 */
	public void setQuestionNumber(String questionNumber) {
		this.questionNumber = questionNumber;
	}
	
	/**
	 * @param uiControlType
	 */
	public void setUiControlType(String uiControlType) {
		this.uiControlType = uiControlType;
	}


	/**
	 * @return uiControlType
	 */
	public String getUiControlType() {
		return uiControlType;
	}

	/* 
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object obj)
    {
        if (obj == null)
            return -1;
        RiskAnswerResponseEvent evt = (RiskAnswerResponseEvent) obj;
        int result = 0;

        try
        {
            if (this.getEntryDate()!= null || evt.getEntryDate()!= null)
            {
                if (this.getEntryDate() == null)
                    return -1; // this makes any null objects go to the bottom change this to 1 if
                               // you want the top of the list
                if (evt.getEntryDate() == null)
                    return 1; // this makes any null objects go to the bottom change this to -1 if
                              // you want the top of the list
//              result = this.getEntryDate().compareTo(evt.getEntryDate()); 
                result = evt.getEntryDate().compareTo(this.getEntryDate()); // backwards in order to
                															// get list to show up
                															// most recent first
            }

        }
        catch (NumberFormatException e)
        {
            result = 0;
        }

        return result;
    }


	public String getCreateUserID() {
		return createUserID;
	}


	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}


	public void setUiControlTypeDesc(String uiControlTypeDesc) {
		this.uiControlTypeDesc = uiControlTypeDesc;
	}


	public String getUiControlTypeDesc() {
		return uiControlTypeDesc;
	}


	public String getAssessmentType() {
		return assessmentType;
	}


	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}


	public String getFilteredAnswerText()
	{
	    if(this.answerText!=null)	    
		return this.answerText.toString().replaceAll("\\s*\\[.*?\\]\\s*", " ");
	    else 
		return "";
	}



}
