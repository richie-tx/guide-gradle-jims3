package messaging.administerprogramreferrals.reply;

import java.util.Collection;
import java.util.List;

import mojo.km.messaging.ResponseEvent;

public class ReferralFormFieldResponseEvent extends ResponseEvent
{
	private String fieldId;
	private String fieldLabel;

	private String columnWidth;
	private String summaryColumnWidth;
	
	private int rowSequence;
	private int columnSequence=0;
	
	private int summaryRowSeq;
	private int summaryColSeq=0;		
	
	private boolean isRequired = false;
	
	private String responseUIControlType;
	private String responseDataType="ALPHA_NUMERIC";	
	
//	used for Text Field
	private String uiControlSize;	
//	used for TextBox
	private String textLength;	
//	to specify the range in case of numeric Textfield
	private String minValue;
	private String maxValue;
	
	private boolean isResponseNewLine = false;
	private boolean isEachResponseNewLine = false;
	private String noOfRespInARow;
	private String formatKey="";
	
	private String childFieldId;
	
	private Collection possibleResponseEvents;
	
	private String selectedResponseId;
	private String selectedResponseText;
	
//	used in case of checkbox, to store multiple responses
	private List selectedResponseIdsList;
	

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
	 * @return the columnWidth
	 */
	public String getColumnWidth() {
		return columnWidth;
	}

	/**
	 * @param columnWidth the columnWidth to set
	 */
	public void setColumnWidth(String columnWidth) {
		this.columnWidth = columnWidth;
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
	 * @return the responseUIControlType
	 */
	public String getResponseUIControlType() {
		return responseUIControlType;
	}

	/**
	 * @param responseUIControlType the responseUIControlType to set
	 */
	public void setResponseUIControlType(String responseUIControlType) {
		this.responseUIControlType = responseUIControlType;
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
	 * @return the uiControlSize
	 */
	public String getUiControlSize() {
		return uiControlSize;
	}

	/**
	 * @param uiControlSize the uiControlSize to set
	 */
	public void setUiControlSize(String uiControlSize) {
		this.uiControlSize = uiControlSize;
	}

	/**
	 * @return the textLength
	 */
	public String getTextLength() {
		return textLength;
	}

	/**
	 * @param textLength the textLength to set
	 */
	public void setTextLength(String textLength) {
		this.textLength = textLength;
	}

	/**
	 * @return the minValue
	 */
	public String getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue the minValue to set
	 */
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	/**
	 * @return the maxValue
	 */
	public String getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue the maxValue to set
	 */
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return the isResponseNewLine
	 */
	public boolean isResponseNewLine() {
		return isResponseNewLine;
	}

	/**
	 * @param isResponseNewLine the isResponseNewLine to set
	 */
	public void setResponseNewLine(boolean isResponseNewLine) {
		this.isResponseNewLine = isResponseNewLine;
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
	 * @return the selectedResponseId
	 */
	public String getSelectedResponseId() {
		return selectedResponseId;
	}

	/**
	 * @param selectedResponseId the selectedResponseId to set
	 */
	public void setSelectedResponseId(String selectedResponseId) {
		this.selectedResponseId = selectedResponseId;
	}

	/**
	 * @return the selectedResponseText
	 */
	public String getSelectedResponseText() {
		return selectedResponseText;
	}

	/**
	 * @param selectedResponseText the selectedResponseText to set
	 */
	public void setSelectedResponseText(String selectedResponseText) {
		this.selectedResponseText = selectedResponseText;
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
	 * @return the possibleResponseEvents
	 */
	public Collection getPossibleResponseEvents() {
		return possibleResponseEvents;
	}

	/**
	 * @param possibleResponseEvents the possibleResponseEvents to set
	 */
	public void setPossibleResponseEvents(Collection possibleResponseEvents) {
		this.possibleResponseEvents = possibleResponseEvents;
	}
	/**
	 * @return the noOfRespInARow
	 */
	public String getNoOfRespInARow() {
		return noOfRespInARow;
	}

	/**
	 * @param noOfRespInARow the noOfRespInARow to set
	 */
	public void setNoOfRespInARow(String noOfRespInARow) {
		this.noOfRespInARow = noOfRespInARow;
	}

	/**
	 * @return the selectedResponseIdsList
	 */
	public List getSelectedResponseIdsList() {
		return selectedResponseIdsList;
	}

	/**
	 * @param selectedResponseIdsList the selectedResponseIdsList to set
	 */
	public void setSelectedResponseIdsList(List selectedResponseIdsList) {
		this.selectedResponseIdsList = selectedResponseIdsList;
	}
}
