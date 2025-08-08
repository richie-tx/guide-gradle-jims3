/*
 * Created on Oct 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

/**
 * @author kmurthy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RiskQuestionAnswerEvent extends RequestEvent implements Comparable
{
	//First are the only two that are stored
	private String text = null;
	private int weightedResponseID;
	//The rest are for use in the commands
	private String questionText;
	private String questionName;
	private String answerText;
	private String filteredAnswerText;
	private int questionNumber;
	private int weight;
	private String uiControlType;
	private String controlCode;
	private String questionId;
	private boolean numeric;//Used in conjunction with controlCode to indicate when a prefilled value should be used in score calculation.
	private String isAllowPrint;

	/**
	 * @return
	 */
	public String getText()
	{
		return text;
	}	
	/**
	 * @param string
	 */
	public void setText(final String string)
	{
		text = string;
	}
	/**
	 * @return
	 */
	public int getWeightedResponseID()
	{
		return weightedResponseID;
	}		
	/**
	 * @param i
	 */
	public void setWeightedResponseID(final int i)
	{
		weightedResponseID = i;
	}
	
	/**
	 * @return
	 */
	public String getQuestionText() {
		return questionText;
	}
	/**
	 * @param questionText
	 */
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	/**
	 * @return
	 */
	public String getAnswerText() {
		return answerText;
	}
	/**
	 * @param answerText
	 */
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	/**
	 * @return
	 */
	public int getQuestionNumber() {
		return questionNumber;
	}
	/**
	 * @param questionNumber
	 */
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	/**
	 * @return
	 */
	public int getWeight() {
		return weight;
	}
	/**
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
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
	public void setUiControlType(final String string)
	{
		uiControlType = string;
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
	
	/** Sort in order of ascending question number.
	 * @see Comparable#compareTo(Object)
	 */
	public int compareTo(Object questionEvent)
	{
		final Integer qNum= new Integer(((RiskQuestionAnswerEvent) questionEvent).getQuestionNumber());
		final Integer thisQNum = new Integer(questionNumber);
		final int result = thisQNum.compareTo(qNum);
		return result;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public void setNumeric(boolean numeric) {
		this.numeric = numeric;
	}
	public boolean isNumeric() {
		return numeric;
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
	public String getFilteredAnswerText()
	{
	    return filteredAnswerText;
	}
	public void setFilteredAnswerText(String filteredAnswerText)
	{
	    this.filteredAnswerText = filteredAnswerText;
	}
	
	
	
}
