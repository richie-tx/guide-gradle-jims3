/*
 * Created on Feb 8, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerassessments.reply;

import java.util.Collection;

/**
 * @author cc_bjangay
 *
  */
public class CSCQuestionResponseEvent
{
	private String id;
	private String text;
	private String displayTextAlign;
	private boolean isDisplayTextDetailHeader = false;
	private String displayText = "";
	
    private String questionAlignment = "left";   
	private String columnWidth;
	private String summaryRespColumnWidth;
	
	private int rowSequence;
	private int columnSequence=0;
	
	private int summaryRowSeq;
	private int summaryColSeq=0;
	
	private boolean isRequired = false;
	private boolean isRequiredImageShown=false;
	
	
	private Collection possibleResponseEvents;
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
	
	private String selectedResponseId;
	private String selectedResponseText;
	
//	used in case of checkbox, to store multiple responses
//	private Collection selectedResponses=null;
	
	private String formatKey="";

	
	/**
	 * @return Returns the selectedResponseText.
	 */
	public String getSelectedResponseText() {
		return selectedResponseText;
	}
	/**
	 * @param selectedResponseText The selectedResponseText to set.
	 */
	public void setSelectedResponseText(String selectedResponseText) {
		this.selectedResponseText = selectedResponseText;
	}
	/**
	 * @return Returns the columnSequence.
	 */
	public int getColumnSequence() {
		return columnSequence;
	}
	/**
	 * @param columnSequence The columnSequence to set.
	 */
	public void setColumnSequence(int columnSequence) {
		this.columnSequence = columnSequence;
	}	
	/**
	 * @return Returns the possibleResponseEvents.
	 */
	public Collection getPossibleResponseEvents() {
		return possibleResponseEvents;
	}
	/**
	 * @param possibleResponseEvents The possibleResponseEvents to set.
	 */
	public void setPossibleResponseEvents(Collection possibleResponseEvents) {
		this.possibleResponseEvents = possibleResponseEvents;
	}
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Returns the isRequired.
	 */
	public boolean isRequired() {
		return isRequired;
	}
	/**
	 * @param isRequired The isRequired to set.
	 */
	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}
	/**
	 * @return Returns the isResponseNewLine.
	 */
	public boolean isResponseNewLine() {
		return isResponseNewLine;
	}
	/**
	 * @param isResponseNewLine The isResponseNewLine to set.
	 */
	public void setResponseNewLine(boolean isResponseNewLine) {
		this.isResponseNewLine = isResponseNewLine;
	}
	/**
	 * @return Returns the responseDataType.
	 */
	public String getResponseDataType() {
		return responseDataType;
	}
	/**
	 * @param responseDataType The responseDataType to set.
	 */
	public void setResponseDataType(String responseDataType) {
		this.responseDataType = responseDataType;
	}
	/**
	 * @return Returns the responseUIControlType.
	 */
	public String getResponseUIControlType() {
		return responseUIControlType;
	}
	/**
	 * @param responseUIControlType The responseUIControlType to set.
	 */
	public void setResponseUIControlType(String responseUIControlType) {
		this.responseUIControlType = responseUIControlType;
	}
	/**
	 * @return Returns the selectedResponseId.
	 */
	public String getSelectedResponseId() {
		return selectedResponseId;
	}
	/**
	 * @param selectedResponseId The selectedResponseId to set.
	 */
	public void setSelectedResponseId(String selectedResponseId) {
		this.selectedResponseId = selectedResponseId;
	}
	/**
	 * @return Returns the text.
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text The text to set.
	 */
	public void setText(String text) {
		this.text = text;
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
/**
 * @return Returns the uiControlSize.
 */
public String getUiControlSize() {
	return uiControlSize;
}
/**
 * @param uiControlSize The uiControlSize to set.
 */
public void setUiControlSize(String uiControlSize) {
	this.uiControlSize = uiControlSize;
}
/**
 * @return Returns the formatKey.
 */
public String getFormatKey() {
	return formatKey;
}
/**
 * @param formatKey The formatKey to set.
 */
public void setFormatKey(String formatKey) {
	this.formatKey = formatKey;
}

	/**
	 * @return Returns the questionAlignment.
	 */
	public String getQuestionAlignment() {
		return questionAlignment;
	}
	/**
	 * @param questionAlignment The questionAlignment to set.
	 */
	public void setQuestionAlignment(String questionAlignment) {
		this.questionAlignment = questionAlignment;
	}	

    /**
     * @return Returns the displayTextAlign.
     */
    public String getDisplayTextAlign() {
        return displayTextAlign;
    }
    /**
     * @param displayTextAlign The displayTextAlign to set.
     */
    public void setDisplayTextAlign(String displayTextAlign) {
        this.displayTextAlign = displayTextAlign;
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
	 * @return Returns the rowSequence.
	 */
	public int getRowSequence() {
		return rowSequence;
	}
	/**
	 * @param rowSequence The rowSequence to set.
	 */
	public void setRowSequence(int rowSequence) {
		this.rowSequence = rowSequence;
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

///**
// * @return Returns the selectedResponses.
// */
//public Collection getSelectedResponses() {
//	return selectedResponses;
//}
///**
// * @param selectedResponses The selectedResponses to set.
// */
//public void setSelectedResponses(Collection selectedResponses) {
//	this.selectedResponses = selectedResponses;
//}
    /**
     * @return Returns the displayText.
     */
    public String getDisplayText() {
        return displayText;
    }
    /**
     * @param displayText The displayText to set.
     */
    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }
    /**
     * @return Returns the isDisplayTextDetailHeader.
     */
    public boolean isDisplayTextDetailHeader() {
        return isDisplayTextDetailHeader;
    }
    /**
     * @param isDisplayTextDetailHeader The isDisplayTextDetailHeader to set.
     */
    public void setDisplayTextDetailHeader(boolean isDisplayTextDetailHeader) {
        this.isDisplayTextDetailHeader = isDisplayTextDetailHeader;
    }
    /**
     * @return Returns the columnWidth.
     */
    public String getColumnWidth() {
        return columnWidth;
    }
    /**
     * @param columnWidth The columnWidth to set.
     */
    public void setColumnWidth(String columnWidth) {
        this.columnWidth = columnWidth;
    }
	/**
	 * @return Returns the maxValue.
	 */
	public String getMaxValue() {
		return maxValue;
	}
	/**
	 * @param maxValue The maxValue to set.
	 */
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
/**
 * @return Returns the minValue.
 */
public String getMinValue() {
	return minValue;
}
/**
 * @param minValue The minValue to set.
 */
public void setMinValue(String minValue) {
	this.minValue = minValue;
}
}
