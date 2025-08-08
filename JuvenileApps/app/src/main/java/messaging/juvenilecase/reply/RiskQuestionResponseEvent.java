/*
 * Created on Oct 4, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author kmurthy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RiskQuestionResponseEvent extends ResponseEvent implements Comparable
{
	private String assessmentType = "";
	private int questionID;
	private String questionText = "";
	private String questionName="";
	private String uiControlType = "";
	private String controlCode = "";
	private int questionNbr;
	private String helpFileId;
	
	private Collection answers;
	private String selectedAnswerID;
	private String selectedAnswerWeight;
	private String[] selectedAnswerIDs;
	private String[] selectedChronicIDs;
	private String selectedChronicID;
	
	//Used within JSP to tell logic tags
	//whether to use AnswerText or SelectedAnswerId
	//for display purposes
	private boolean useAnswerText;
	private boolean hideOnDisplay;
	private String initialAction;
	private boolean required;
	private boolean numeric;
	private boolean allowsFutureDates;
	private boolean defaultToSystemDate;
	private String riskFormulaId;
	private String riskCategoryId;
	private String isAllowPrint;
	
	//Used to determine whether a prefilled question should be refreshed.
	private boolean refreshable;
	
	
	public RiskQuestionResponseEvent()
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
	public int getQuestionID()
	{
		return questionID;
	}

	/**
	 * @return
	 */
	public String getQuestionText()
	{
		return questionText;
	}

	/**
	 * @return
	 */
	public String getUiControlType()
	{
		return uiControlType;
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
	public void setQuestionID(final int quesId)
	{
		questionID = quesId;
	}

	/**
	 * @param string
	 */
	public void setQuestionText(final String string)
	{
		questionText = string;
	}

	/**
	 * @param string
	 */
	public void setUiControlType(final String string)
	{
		uiControlType = string;
	}

	/**
	 * @return
	 */
	public void addAnswer(final RiskWeightedResponseEvent evt)
	{
		if(answers == null) 
		{
			answers = new ArrayList();	
		}
		answers.add(evt);
	}
	public void setAnswers(Collection coll){
		if (coll != null){
			answers = coll;
		} else {
			answers = new ArrayList();
		}
	}
	/**
	 * @param void
	 */
	public Collection getAnswers()
	{
		return answers;
	}

	/**
	 * @return
	 */
	public String getSelectedAnswerID()
	{
		return selectedAnswerID;
	}

	/**
	 * @param i
	 */
	public void setSelectedAnswerID(final String i)
	{
		selectedAnswerID = i;
	}

	/**
	 * @return
	 */
	public int getQuestionNbr()
	{
		return questionNbr;
	}

	/**
	 * @param i
	 */
	public void setQuestionNbr(final int i)
	{
		questionNbr = i;
	}
	
	/** Sort in order of ascending questionNbr
	 * @see Comparable#compareTo(Object)
	 */
	public int compareTo(Object questionEvent)
	{
		final int otherQNum = ((RiskQuestionResponseEvent) questionEvent).getQuestionNbr() ;
		final int thisQNum = this.questionNbr ;
		int result = 0 ;
		
		if( thisQNum < otherQNum )
		{
			result = (-1) ;
		}
		else if( thisQNum > otherQNum )
		{
			result = 1 ;
		}

		return result;
	}

	/**
	 * @return
	 */
	public String[] getSelectedAnswerIDs()
	{
		return selectedAnswerIDs;
	}

	/**
	 * @param strings
	 */
	public void setSelectedAnswerIDs(final String[] strings)
	{
		selectedAnswerIDs = strings;
	}

	/**
	 * @return
	 */
	public String getSelectedChronicID()
	{
		return selectedChronicID;
	}

	/**
	 * @return
	 */
	public String[] getSelectedChronicIDs()
	{
		return selectedChronicIDs;
	}

	/**
	 * @param string
	 */
	public void setSelectedChronicID(String string)
	{
		selectedChronicID = string;
	}

	/**
	 * @param strings
	 */
	public void setSelectedChronicIDs(String[] strings)
	{
		selectedChronicIDs = strings;
	}


	/**
	 * @param selectedAnswerWeight
	 */
	public void setSelectedAnswerWeight(String selectedAnswerWeight) {
		this.selectedAnswerWeight = selectedAnswerWeight;
	}


	/**
	 * @return
	 */
	public String getSelectedAnswerWeight() {
		return selectedAnswerWeight;
	}

	/**
	 * @param controlCode
	 */
	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}

	/**
	 * @return
	 */
	public String getControlCode() {
		return controlCode;
	}

	/**
	 * @param useAnswerText
	 */
	public void setUseAnswerText(boolean useAnswerText) {
		this.useAnswerText = useAnswerText;
	}

	/**
	 * @return
	 */
	public boolean isUseAnswerText() {
		return useAnswerText;
	}
	
	/**
	 * @param useAnswerText
	 */
	public void setHideOnDisplay(boolean hideOnDisplay) {
		this.hideOnDisplay = hideOnDisplay;
	}

	/**
	 * @return
	 */
	public boolean isHideOnDisplay() {
		return hideOnDisplay;
	}
	
	/**
	 * @param initialAction
	 */
	public void setInitialAction(String initialAction) {
		this.initialAction = initialAction;
	}

	/**
	 * @return
	 */
	public String getInitialAction() {
		return initialAction;
	}

	/**
	 * @param helpFileId
	 */
	public void setHelpFileId(String helpFileId) {
		this.helpFileId = helpFileId;
	}

	/**
	 * @return
	 */
	public String getHelpFileId() {
		return helpFileId;
	}
	
	/**
	 * @param useAnswerText
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * @return
	 */
	public boolean isRequired() {
		return required;
	}
	/**
	 * @param useAnswerText
	 */
	public void setNumeric(boolean numeric) {
		this.numeric = numeric;
	}

	/**
	 * @return
	 */
	public boolean isNumeric() {
		return numeric;
	}

	public void setAllowsFutureDates(boolean allowsFutureDates) {
		this.allowsFutureDates = allowsFutureDates;
	}

	public boolean isAllowsFutureDates() {
		return allowsFutureDates;
	}
	
	public void setDefaultToSystemDate(boolean defaultToSystemDate) {
		this.defaultToSystemDate = defaultToSystemDate;
	}

	public boolean isDefaultToSystemDate() {
		return defaultToSystemDate;
	}

	public void setRefreshable(boolean refreshable) {
		this.refreshable = refreshable;
	}

	public boolean isRefreshable() {
		return refreshable;
	}

	public void setRiskFormulaId(String formulaId) {
		this.riskFormulaId = formulaId;
	}

	public String getRiskFormulaId() {
		return riskFormulaId;
	}

	public void setRiskCategoryId(String riskCategoryId) {
		this.riskCategoryId = riskCategoryId;
	}

	public String getRiskCategoryId() {
		return riskCategoryId;
	}

	public String getQuestionName()
	{
	    return questionName;
	}

	public void setQuestionName(String questionName)
	{
	    this.questionName = questionName;
	}

	public String getIsAllowPrint()
	{
	    return isAllowPrint;
	}

	public void setIsAllowPrint(String isAllowPrint)
	{
	    this.isAllowPrint = isAllowPrint;
	}

	
}
