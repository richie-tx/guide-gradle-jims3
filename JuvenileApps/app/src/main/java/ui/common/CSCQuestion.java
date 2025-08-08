/*
 * Created on Feb 11, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSCQuestion 
{
	public static String RESPONSE_VAR_TYPE_NUMERIC="NUMERIC";
	public static String RESPONSE_VAR_TYPE_ALPHA_NUMERIC="ALPHA_NUMERIC";
	public static String RESPONSE_VAR_TYPE_ALPHA_NUMERIC_WTH_SYMBOLS="ALPHA_NUMERIC_WTH_SYMBOLS";
	public static String RESPONSE_VAR_TYPE_ALPHA="ALPHA";
	public static String RESPONSE_VAR_TYPE_DATE="DATE";
	public static String RESPONSE_VAR_TYPE_INTEGER="INTEGER";
	public static String RESPONSE_VAR_TYPE_MONETARY="MONETARY";
	
	public static String UI_CNTRL_TYPE_MCE_TEXTBOX="MCETEXTBOX";
	public static String UI_CNTRL_TYPE_SPELL_CHECK="SPELLCHECK";
	public static String UI_CNTRL_TYPE_TEXTBOX="TEXTBOX";
	public static String UI_CNTRL_TYPE_TEXT="TEXT";
	public static String UI_CNTRL_TYPE_RADIO="RADIO";
	public static String UI_CNTRL_TYPE_SELECT="SELECT";
	public static String UI_CNTRL_TYPE_HIDDEN="HIDDEN";
	public static String UI_CNTRL_TYPE_CHECKBOX="CHECKBOX";
	
	
	public static String QUESTION_ALIGN_LEFT="left";
	public static String QUESTION_ALIGN_CENTER="center";
	public static String QUESTION_ALIGN_RIGHT="right";
	
//	rowSeq and colSeq of each Question on input page
	private int rowSequence=0;
	private int columnSequence=0;
	
//	rowSeq and colSeq of each Question on summary page
	private int summaryRowSeq=0;
	private int summaryColSeq=0;
	
	private String questionId="";
	private String questionText="";
	
	private boolean isRequired=false;
	private boolean isRequiredImageShown=false;
	
	private String uiControlType="";
	private String responseDataType="";	
	private String validationElementType = "";
	private String validationElementDetailType = "";
	
	private boolean responseNextLine=false;
	private boolean isEachResponseNewLine=false;
	
	private int uiControlSize=0;
	private int textLength=0;
	private boolean isRangeValidationExists=false;
	private int minValue=0;
	private int maxValue=0;
	
	private String questionAlign="";
	private String questionColumnWidth="";
	private String summaryRespColumnWidth="";
	
	
	private Collection responseChoices=null;

	private static HashMap tempRows=new HashMap();

//	variables used to hold the selected answer/answers
	
//  holds the response text (for TEXT and TEXTBOX)
	private String responseText="";
//	holds the selected response Id (for RADIO, SELECT)
	private String responseId="";
	
//	MultiCheck
//	holds the selected responseIds for UI_RESPONSE_TYPE CHECKBOX
	private String[] selectedResponseIdsArr=null;
	


	private String formatKey="";
	private String[] formattedQText;
	
	
	
	public String[] getFormattedQText() {
		return formattedQText;
	}
	public void setFormattedQText(String[] formattedQText) {
		this.formattedQText = formattedQText;
	}
	
	/**
	 * @return Returns the isRangeValidationExists.
	 */
	public boolean isRangeValidationExists() {
		return isRangeValidationExists;
	}
	/**
	 * @param isRangeValidationExists The isRangeValidationExists to set.
	 */
	public void setRangeValidationExists(boolean isRangeValidationExists) {
		this.isRangeValidationExists = isRangeValidationExists;
	}
	/**
	 * @return Returns the maxValue.
	 */
	public int getMaxValue() {
		return maxValue;
	}
	/**
	 * @param maxValue The maxValue to set.
	 */
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	/**
	 * @return Returns the minValue.
	 */
	public int getMinValue() {
		return minValue;
	}
	/**
	 * @param minValue The minValue to set.
	 */
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}
	/**
	 * @return Returns the validationElementDetailType.
	 */
	public String getValidationElementDetailType() {
		return validationElementDetailType;
	}
	/**
	 * @param validationElementDetailType The validationElementDetailType to set.
	 */
	public void setValidationElementDetailType(String validationElementDetailType) {
		this.validationElementDetailType = validationElementDetailType;
	}
	/**
	 * @return Returns the validationElementType.
	 */
	public String getValidationElementType() {
		return validationElementType;
	}
	/**
	 * @param validationElementType The validationElementType to set.
	 */
	public void setValidationElementType(String validationElementType) {
		this.validationElementType = validationElementType;
	}
	/**
	 * @return
	 */
	public String getResponseText()
	{
		if(responseText==null)
			return "";
		return responseText;
	}

	/**
	 * @return
	 */
	public Collection getResponseChoices()
	{
		return responseChoices;
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
	public String getQuestionId()
	{
		return questionId;
	}

	/**
	 * @return
	 */
	public int getRowSequence()
	{
		return rowSequence;
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
	public String getResponseDataType()
	{
		return responseDataType;
	}

	/**
	 * @return
	 */
	public int getUiControlSize()
	{
		return uiControlSize;
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
	public void setResponseText(String string)
	{
		responseText = string;
	}

	/**
	 * @param collection
	 */
	public void setResponseChoices(Collection collection)
	{
		responseChoices = collection;
	}
	/**
	 * @param b
	 */
	public void setRequired(boolean b)
	{
		isRequired = b;
	}

	/**
	 * @param string
	 */
	public void setQuestionId(String string)
	{
		questionId = string;
	}

	/**
	 * @param i
	 */
	public void setRowSequence(int i)
	{
		rowSequence = i;
	}

	/**
	 * @param string
	 */
	public void setQuestionText(String string)
	{
		questionText = string;
	}

	/**
	 * @param string
	 */
	public void setResponseDataType(String string)
	{
		responseDataType = string;
	}

	/**
	 * @param i
	 */
	public void setUiControlSize(int i)
	{
		uiControlSize = i;
	}

	/**
	 * @param string
	 */
	public void setUiControlType(String string)
	{
		uiControlType = string;
	}

	
	public boolean answerMatchesChoice()
	{
//		MultiCheck
		/** */
		if(uiControlType.equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_CHECKBOX))
		{
			if(selectedResponseIdsArr==null || responseChoices==null || responseChoices.size()<=0)
			{
				return false;
			}
			return true;
		}
		else
		
		{
			if(responseId==null || responseId.trim().equalsIgnoreCase("")|| responseChoices==null || responseChoices.size()<1)
				return false;
			Iterator i=responseChoices.iterator();			
			while(i.hasNext())
			{
				CSCQuestionResponse myAnswer=(CSCQuestionResponse)i.next();
				if(myAnswer.getResponseId().equalsIgnoreCase(responseId))
					return true;
			}		
		}
		return false;
	}

	/**
	 * @return
	 */
	public String getFormatKey()
	{
		return formatKey;
	}

	/**
	 * @param string
	 */
	public void setFormatKey(String string)
	{
		formatKey = string;
	}

	/**
	 * @return
	 */
	public int getColumnSequence()
	{
		return columnSequence;
	}

	/**
	 * @param i
	 */
	public void setColumnSequence(int i)
	{
		columnSequence = i;
	}

	/**
	 * @return
	 */
	public boolean isResponseNextLine()
	{
		return responseNextLine;
	}

	/**
	 * @param b
	 */
	public void setResponseNextLine(boolean b)
	{
		responseNextLine = b;
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
	 * @return Returns the textLength.
	 */
	public int getTextLength() {
		return textLength;
	}
	/**
	 * @param textLength The textLength to set.
	 */
	public void setTextLength(int textLength) {
		this.textLength = textLength;
	}
	/**
	 * @return Returns the questionAlign.
	 */
	public String getQuestionAlign() {
		return questionAlign;
	}
	/**
	 * @param questionAlign The questionAlign to set.
	 */
	public void setQuestionAlign(String questionAlign) {
		this.questionAlign = questionAlign;
	}
	/**
	 * @return Returns the questionColumnWidth.
	 */
	public String getQuestionColumnWidth() {
		return questionColumnWidth;
	}
	/**
	 * @param questionColumnWidth The questionColumnWidth to set.
	 */
	public void setQuestionColumnWidth(String questionColumnWidth) {
		this.questionColumnWidth = questionColumnWidth;
	}	
	/**
	 * @return Returns the tempRows.
	 */
	public static HashMap getTempRows() {
		return tempRows;
	}
	/**
	 * @param tempRows The tempRows to set.
	 */
	public static void setTempRows(HashMap tempRows) {
		CSCQuestion.tempRows = tempRows;
	}
/**
 * @return Returns the selectedResponseIdsArr.
 */
public String[] getSelectedResponseIdsArr() {
	return selectedResponseIdsArr;
}
/**
 * @param selectedResponseIdsArr The selectedResponseIdsArr to set.
 */
public void setSelectedResponseIdsArr(String[] selectedResponseIdsArr) {
	this.selectedResponseIdsArr = selectedResponseIdsArr;
}
/**
 * @return Returns the isEachResponseNewLine.
 */
public boolean isEachResponseNewLine() {
	return isEachResponseNewLine;
}
/**
 * @param isEachResponseNewLine The isEachResponseNewLine to set.
 */
public void setEachResponseNewLine(boolean isEachResponseNewLine) {
	this.isEachResponseNewLine = isEachResponseNewLine;
}
/**
 * @return Returns the isRequiredImageShown.
 */
public boolean isRequiredImageShown() {
	return isRequiredImageShown;
}
/**
 * @param isRequiredImageShown The isRequiredImageShown to set.
 */
public void setRequiredImageShown(boolean isRequiredImageShown) {
	this.isRequiredImageShown = isRequiredImageShown;
}
/**
 * @return Returns the summaryColSeq.
 */
public int getSummaryColSeq() {
	return summaryColSeq;
}
/**
 * @param summaryColSeq The summaryColSeq to set.
 */
public void setSummaryColSeq(int summaryColSeq) {
	this.summaryColSeq = summaryColSeq;
}
/**
 * @return Returns the summaryRowSeq.
 */
public int getSummaryRowSeq() {
	return summaryRowSeq;
}
/**
 * @param summaryRowSeq The summaryRowSeq to set.
 */
public void setSummaryRowSeq(int summaryRowSeq) {
	this.summaryRowSeq = summaryRowSeq;
}
/**
 * @return Returns the summaryRespColumnWidth.
 */
public String getSummaryRespColumnWidth() {
	return summaryRespColumnWidth;
}
/**
 * @param summaryRespColumnWidth The summaryRespColumnWidth to set.
 */
public void setSummaryRespColumnWidth(String summaryRespColumnWidth) {
	this.summaryRespColumnWidth = summaryRespColumnWidth;
}
}
