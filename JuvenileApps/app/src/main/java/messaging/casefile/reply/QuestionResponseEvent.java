/*
 * Created on July 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.casefile.reply; 

import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 * Class holding data for each individual jsp.
 */
 
public class QuestionResponseEvent extends ResponseEvent{
	private String id;
	private String text;
	private String rowSequence;
	private String responseUIControlType;
	private String uiControlSize;
	private boolean isRequired = false;
	private boolean isRenderQuesNum = false;
	private boolean isResponseNewLine = false;
	private String selectedResponseId;
	private String dependentQuestionId;
	private String realAnswerText;
	private String realAnswerId;
	private String responseId;
	private String textLength;
	
	private int columnSequence=0;
	private String validationDesc="";
	private String responseDataType="ALPHA_NUMERIC";
	private String formatKey="";
	
	private Collection possibleResponseEvents;

	
	/**
	 * Consturctor
	 */
	public QuestionResponseEvent(){
	}
	
	/**
	 * @return
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @return
	 */
	public boolean isRequired()
	{
		return isRequired;
	}

	/**
	 * @return
	 */
	public boolean isResponseNewLine()
	{
		return isResponseNewLine;
	}

	/**
	 * @return
	 */
	public String getResponseUIControlType()
	{
		return responseUIControlType;
	}

	/**
	 * @return
	 */
	public String getRowSequence()
	{
		return rowSequence;
	}

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
	public void setId(String string)
	{
		id = string;
	}

	/**
	 * @param b
	 */
	public void setRequired(boolean b)
	{
		isRequired = b;
	}

	/**
	 * @param b
	 */
	public void setResponseNewLine(boolean b)
	{
		isResponseNewLine = b;
	}

	/**
	 * @param string
	 */
	public void setResponseUIControlType(String string)
	{
		responseUIControlType = string;
	}

	/**
	 * @param string
	 */
	public void setRowSequence(String string)
	{
		rowSequence = string;
	}

	/**
	 * @param string
	 */
	public void setText(String string)
	{
		text = string;
	}

	/**
	 * @return
	 */
	public Collection getPossibleResponseEvents()
	{
		return possibleResponseEvents;
	}

	/**
	 * @param collection
	 */
	public void setPossibleResponseEvents(Collection collection)
	{
		possibleResponseEvents = collection;
	}

	/**
	 * @return
	 */
	public String getUiControlSize()
	{
		return uiControlSize;
	}

	/**
	 * @param string
	 */
	public void setUiControlSize(String string)
	{
		uiControlSize = string;
	}

	/**
	 * @return
	 */
	public String getDependentQuestionId()
	{
		return dependentQuestionId;
	}

	/**
	 * @return
	 */
	public String getSelectedResponseId()
	{
		return selectedResponseId;
	}

	/**
	 * @param string
	 */
	public void setDependentQuestionId(String string)
	{
		dependentQuestionId = string;
	}

	/**
	 * @param string
	 */
	public void setSelectedResponseId(String string)
	{
		selectedResponseId = string;
	}

	/**
	 * @return
	 */
	public String getRealAnswerId()
	{
		return realAnswerId;
	}

	/**
	 * @return
	 */
	public String getRealAnswerText()
	{
		return realAnswerText;
	}

	/**
	 * @param string
	 */
	public void setRealAnswerId(String string)
	{
		realAnswerId = string;
	}

	/**
	 * @param string
	 */
	public void setRealAnswerText(String string)
	{
		realAnswerText = string;
	}

	/**
	 * @return
	 */
	public int getColumnSequence()
	{
		return columnSequence;
	}

	/**
	 * @return
	 */
	public String getFormatKey()
	{
		return formatKey;
	}

	/**
	 * @return
	 */
	public String getResponseDataType()
	{
		return responseDataType;
	}

	/**
	 * @return
	 */
	public String getValidationDesc()
	{
		return validationDesc;
	}

	/**
	 * @param i
	 */
	public void setColumnSequence(int i)
	{
		columnSequence = i;
	}

	/**
	 * @param string
	 */
	public void setFormatKey(String string)
	{
		formatKey = string;
	}

	/**
	 * @param string
	 */
	public void setResponseDataType(String string)
	{
		responseDataType = string;
	}

	/**
	 * @param string
	 */
	public void setValidationDesc(String string)
	{
		validationDesc = string;
	}

	/**
	 * @return
	 */
	public String getResponseId()
	{
		return responseId;
	}

	/**
	 * @param string
	 */
	public void setResponseId(String string)
	{
		responseId = string;
	}

	/**
	 * @return Returns the isRenderQuesNum.
	 */
	public boolean isRenderQuesNum() {
		return isRenderQuesNum;
	}
	/**
	 * @param isRenderQuesNum The isRenderQuesNum to set.
	 */
	public void setRenderQuesNum(boolean isRenderQuesNum) {
		this.isRenderQuesNum = isRenderQuesNum;
	}
	/**
	 * @return Returns the textLength.
	 */
	public String getTextLength() {
		return textLength;
	}
	/**
	 * @param textLength The textLength to set.
	 */
	public void setTextLength(String textLength) {
		this.textLength = textLength;
	}
}
