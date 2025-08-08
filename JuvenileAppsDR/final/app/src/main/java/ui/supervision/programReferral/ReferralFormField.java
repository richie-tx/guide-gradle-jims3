package ui.supervision.programReferral;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import messaging.contact.domintf.IPhoneNumber;

import ui.common.CSCQuestion;
import ui.common.PhoneNumber;

/**
 * 
 * @author cc_bjangay
 *
 */
public class ReferralFormField
{
	public static String RESPONSE_VAR_TYPE_INTEGER="INTEGER";
	public static String RESPONSE_VAR_TYPE_NUMERIC="NUMERIC";
	public static String RESPONSE_VAR_TYPE_ALPHA="ALPHA";
	public static String RESPONSE_VAR_TYPE_ALPHA_NUMERIC="ALPHANUMERIC";
	public static String RESPONSE_VAR_TYPE_ALPHA_NUMERIC_WTH_SYMBOLS="ALPHANUMERIC_WITH_SYMBOLS";
	public static String RESPONSE_VAR_TYPE_DATE="DATE";
	public static String RESPONSE_VAR_TYPE_TIME="TIME";
	public static String RESPONSE_VAR_TYPE_CURRENCY="CURRENCY";
	public static String RESPONSE_VAR_TYPE_PHONE="PHONE";
	public static String RESPONSE_VAR_TYPE_MONETARY="MONETARY";
	
	public static String UI_CNTRL_TYPE_MCE_EDIT_TEXTBOX="MCETEXTBOX";
	public static String UI_CNTRL_TYPE_TEXTBOX="TEXTBOX";
	public static String UI_CNTRL_TYPE_TEXT="TEXT";
	public static String UI_CNTRL_TYPE_RADIO="RADIO";
	public static String UI_CNTRL_TYPE_SELECT="SELECT";
	public static String UI_CNTRL_TYPE_HIDDEN="HIDDEN";
	public static String UI_CNTRL_TYPE_SINGLE_CHECKBOX="SINGLECHECKBOX";
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
	
	private String fieldId="";
	private String fieldLabel="";
	
	private boolean isRequired=false;
	private boolean isRequiredImageShown=false;
	
	private String uiControlType="";
	private String responseDataType="";	
	
	private String validationElementType = "";
	private String validationElementDetailType = "";
	
	private boolean responseNextLine=false;
	private boolean isEachResponseNewLine=false;
	private int noOfResponsesInARow=1;
	
	private int uiControlSize=0;
	private int textLength=0;
	private boolean isRangeValidationExists=false;
	private int minValue=0;
	private int maxValue=0;
	
	private String questionAlign="";
	private String questionColumnWidth="";
	private String summaryColumnWidth="";
	
	
	private List responseChoices=null; //RefFormFieldOption

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

	private String childFieldId="";
	
//	used to store the phone Number if the RESPONSETYPE="PHONE"
	private IPhoneNumber responsePhoneNum = new PhoneNumber("");
	
	
	/**
	 * @return the rowSequence
	 */
	public int getRowSequence() {
		return rowSequence;
	}

	/**
	 * @param rowSequence the rowSequence to set
	 */
	public void setRowSequence(int rowSequence) {
		this.rowSequence = rowSequence;
	}

	/**
	 * @return the columnSequence
	 */
	public int getColumnSequence() {
		return columnSequence;
	}

	/**
	 * @param columnSequence the columnSequence to set
	 */
	public void setColumnSequence(int columnSequence) {
		this.columnSequence = columnSequence;
	}

	/**
	 * @return the summaryRowSeq
	 */
	public int getSummaryRowSeq() {
		return summaryRowSeq;
	}

	/**
	 * @param summaryRowSeq the summaryRowSeq to set
	 */
	public void setSummaryRowSeq(int summaryRowSeq) {
		this.summaryRowSeq = summaryRowSeq;
	}

	/**
	 * @return the summaryColSeq
	 */
	public int getSummaryColSeq() {
		return summaryColSeq;
	}

	/**
	 * @param summaryColSeq the summaryColSeq to set
	 */
	public void setSummaryColSeq(int summaryColSeq) {
		this.summaryColSeq = summaryColSeq;
	}

	/**
	 * @return the fieldId
	 */
	public String getFieldId() {
		return fieldId;
	}

	/**
	 * @param fieldId the fieldId to set
	 */
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	/**
	 * @return the fieldLabel
	 */
	public String getFieldLabel() {
		return fieldLabel;
	}

	/**
	 * @param fieldLabel the fieldLabel to set
	 */
	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	/**
	 * @return the isRequired
	 */
	public boolean isRequired() {
		return isRequired;
	}

	/**
	 * @param isRequired the isRequired to set
	 */
	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	/**
	 * @return the isRequiredImageShown
	 */
	public boolean isRequiredImageShown() {
		return isRequiredImageShown;
	}

	/**
	 * @param isRequiredImageShown the isRequiredImageShown to set
	 */
	public void setRequiredImageShown(boolean isRequiredImageShown) {
		this.isRequiredImageShown = isRequiredImageShown;
	}

	/**
	 * @return the uiControlType
	 */
	public String getUiControlType() {
		return uiControlType;
	}

	/**
	 * @param uiControlType the uiControlType to set
	 */
	public void setUiControlType(String uiControlType) {
		this.uiControlType = uiControlType;
	}

	/**
	 * @return the responseDataType
	 */
	public String getResponseDataType() {
		return responseDataType;
	}

	/**
	 * @param responseDataType the responseDataType to set
	 */
	public void setResponseDataType(String responseDataType) {
		this.responseDataType = responseDataType;
	}

	/**
	 * @return the validationElementType
	 */
	public String getValidationElementType() {
		return validationElementType;
	}

	/**
	 * @param validationElementType the validationElementType to set
	 */
	public void setValidationElementType(String validationElementType) {
		this.validationElementType = validationElementType;
	}

	/**
	 * @return the validationElementDetailType
	 */
	public String getValidationElementDetailType() {
		return validationElementDetailType;
	}

	/**
	 * @param validationElementDetailType the validationElementDetailType to set
	 */
	public void setValidationElementDetailType(String validationElementDetailType) {
		this.validationElementDetailType = validationElementDetailType;
	}

	/**
	 * @return the responseNextLine
	 */
	public boolean isResponseNextLine() {
		return responseNextLine;
	}

	/**
	 * @param responseNextLine the responseNextLine to set
	 */
	public void setResponseNextLine(boolean responseNextLine) {
		this.responseNextLine = responseNextLine;
	}

	/**
	 * @return the isEachResponseNewLine
	 */
	public boolean isEachResponseNewLine() {
		return isEachResponseNewLine;
	}

	/**
	 * @param isEachResponseNewLine the isEachResponseNewLine to set
	 */
	public void setEachResponseNewLine(boolean isEachResponseNewLine) {
		this.isEachResponseNewLine = isEachResponseNewLine;
	}

	/**
	 * @return the uiControlSize
	 */
	public int getUiControlSize() {
		return uiControlSize;
	}

	/**
	 * @param uiControlSize the uiControlSize to set
	 */
	public void setUiControlSize(int uiControlSize) {
		this.uiControlSize = uiControlSize;
	}

	/**
	 * @return the textLength
	 */
	public int getTextLength() {
		return textLength;
	}

	/**
	 * @param textLength the textLength to set
	 */
	public void setTextLength(int textLength) {
		this.textLength = textLength;
	}

	/**
	 * @return the isRangeValidationExists
	 */
	public boolean isRangeValidationExists() {
		return isRangeValidationExists;
	}

	/**
	 * @param isRangeValidationExists the isRangeValidationExists to set
	 */
	public void setRangeValidationExists(boolean isRangeValidationExists) {
		this.isRangeValidationExists = isRangeValidationExists;
	}

	/**
	 * @return the minValue
	 */
	public int getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue the minValue to set
	 */
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	/**
	 * @return the maxValue
	 */
	public int getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue the maxValue to set
	 */
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return the questionAlign
	 */
	public String getQuestionAlign() {
		return questionAlign;
	}

	/**
	 * @param questionAlign the questionAlign to set
	 */
	public void setQuestionAlign(String questionAlign) {
		this.questionAlign = questionAlign;
	}

	/**
	 * @return the questionColumnWidth
	 */
	public String getQuestionColumnWidth() {
		return questionColumnWidth;
	}

	/**
	 * @param questionColumnWidth the questionColumnWidth to set
	 */
	public void setQuestionColumnWidth(String questionColumnWidth) {
		this.questionColumnWidth = questionColumnWidth;
	}

	/**
	 * @return the summaryColumnWidth
	 */
	public String getSummaryColumnWidth() {
		return summaryColumnWidth;
	}

	/**
	 * @param summaryColumnWidth the summaryColumnWidth to set
	 */
	public void setSummaryColumnWidth(String summaryColumnWidth) {
		this.summaryColumnWidth = summaryColumnWidth;
	}
	/**
	 * @return the responseChoices
	 */
	public List getResponseChoices() {
		return responseChoices;
	}

	/**
	 * @param responseChoices the responseChoices to set
	 */
	public void setResponseChoices(List responseChoices) {
		this.responseChoices = responseChoices;
	}

	/**
	 * @return the tempRows
	 */
	public static HashMap getTempRows() {
		return tempRows;
	}

	/**
	 * @param tempRows the tempRows to set
	 */
	public static void setTempRows(HashMap tempRows) {
		ReferralFormField.tempRows = tempRows;
	}

	/**
	 * @return the responseText
	 */
	public String getResponseText() {
		return responseText;
	}

	/**
	 * @param responseText the responseText to set
	 */
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	/**
	 * @return the responseId
	 */
	public String getResponseId() {
		return responseId;
	}

	/**
	 * @param responseId the responseId to set
	 */
	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	/**
	 * @return the selectedResponseIdsArr
	 */
	public String[] getSelectedResponseIdsArr() {
		return selectedResponseIdsArr;
	}

	/**
	 * @param selectedResponseIdsArr the selectedResponseIdsArr to set
	 */
	public void setSelectedResponseIdsArr(String[] selectedResponseIdsArr) {
		this.selectedResponseIdsArr = selectedResponseIdsArr;
	}

	/**
	 * @return the formatKey
	 */
	public String getFormatKey() {
		return formatKey;
	}

	/**
	 * @param formatKey the formatKey to set
	 */
	public void setFormatKey(String formatKey) {
		this.formatKey = formatKey;
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
				RefFormFieldOption myAnswer=(RefFormFieldOption)i.next();
				if(myAnswer.getOptionId().equalsIgnoreCase(responseId))
					return true;
			}		
		}
		return false;
	}
	/**
	 * @return the childFieldId
	 */
	public String getChildFieldId() {
		return childFieldId;
	}

	/**
	 * @param childFieldId the childFieldId to set
	 */
	public void setChildFieldId(String childFieldId) {
		this.childFieldId = childFieldId;
	}

	/**
	 * @return the noOfResponsesInARow
	 */
	public int getNoOfResponsesInARow() {
		return noOfResponsesInARow;
	}

	/**
	 * @param noOfResponsesInARow the noOfResponsesInARow to set
	 */
	public void setNoOfResponsesInARow(int noOfResponsesInARow) {
		this.noOfResponsesInARow = noOfResponsesInARow;
	}

	/**
	 * @return the responsePhoneNum
	 */
	public IPhoneNumber getResponsePhoneNum() {
		return responsePhoneNum;
	}

	/**
	 * @param responsePhoneNum the responsePhoneNum to set
	 */
	public void setResponsePhoneNum(IPhoneNumber responsePhoneNum) {
		this.responsePhoneNum = responsePhoneNum;
	}
	
	public String getResponseCurrency() {
		String fldVal = this.responseText;
		if (fldVal != null && !"".equals(fldVal))
		{
			String dollars  = "";
			String cents = "";
			if (fldVal.indexOf(".") > 0) {
				dollars  = fldVal.substring(0, fldVal.indexOf("."));
				cents = fldVal.substring(fldVal.indexOf(".") );
				dollars = dollars.replace(",", "");
				fldVal = dollars;
			}
			if (fldVal.indexOf(",") > 0) {
				fldVal = fldVal.replace(",", "");
			}
			try {
				Integer x = Integer.valueOf(fldVal);
				NumberFormat fmt = NumberFormat.getCurrencyInstance();
				fldVal = fmt.format(x);
				if (!"".equals(cents) ) {
					dollars  = fldVal.substring(0, fldVal.indexOf(".") );
					fldVal = dollars + cents;
				}
				fldVal = fldVal.replace("$","");  // remove leading dollar sign
			} catch (Exception e){
				// do nothing
			}
		}
		return fldVal;
	}
}