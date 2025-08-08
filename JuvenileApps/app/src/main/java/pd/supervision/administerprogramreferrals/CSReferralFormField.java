/*
 * Created on Mar 25, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerprogramreferrals;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSReferralFormField extends PersistentObject 
{
	private String referralFormId;
	private String fieldLabel;
	
	private String responseDataType;
	private String responseUIControlType;
	
	private int rowSequence;
	private int colSequence;
	
	private String colWidth;
	private String summaryColWidth;
	
	private String uiControlSize;
	private String textLength;
	private String minValue;
	private String maxValue;
	
	private boolean isRequired;
	
	private boolean isResponseNewLine;
	private boolean isEachResponseNewLine;
	
	private String format;
	private String noOfRespInRow;
	
	private String childFieldId;
	
	public static CSReferralFormField find(String oid)
	{
		IHome home = new Home();

		CSReferralFormField csreferralFormField = (CSReferralFormField) home.find(oid, CSReferralFormField.class);
		return csreferralFormField;
	}//end of find()
    
   
	public static Iterator findAll()
	{
	    IHome home = new Home();
	    
		Iterator iter = home.findAll(CSReferralFormField.class);
		return iter;
	}//end of findAll()
	
    
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, CSReferralFormField.class);
	}//end of findAll()

    
	static public Iterator findAll(String attrName, String attrValue)
	{
	    IHome home = new Home();
		return home.findAll(attrName, attrValue, CSReferralFormField.class);
	}


	
	/**
	 * @return the referralFormId
	 */
	public String getReferralFormId() {
		fetch();
		return referralFormId;
	}
	/**
	 * @param referralFormId the referralFormId to set
	 */
	public void setReferralFormId(String referralFormId) {
		if (this.referralFormId == null || !this.referralFormId.equals(referralFormId))
		{
			markModified();
		}
		this.referralFormId = referralFormId;
	}
	/**
	 * @return the fieldLabel
	 */
	public String getFieldLabel() {
		fetch();
		return fieldLabel;
	}
	/**
	 * @param fieldLabel the fieldLabel to set
	 */
	public void setFieldLabel(String fieldLabel) {
		if (this.fieldLabel == null || !this.fieldLabel.equals(fieldLabel))
		{
			markModified();
		}
		this.fieldLabel = fieldLabel;
	}
	/**
	 * @return the responseDataType
	 */
	public String getResponseDataType() {
		fetch();
		return responseDataType;
	}
	/**
	 * @param responseDataType the responseDataType to set
	 */
	public void setResponseDataType(String responseDataType) {
		if (this.responseDataType == null || !this.responseDataType.equals(responseDataType))
		{
			markModified();
		}
		this.responseDataType = responseDataType;
	}
	/**
	 * @return the responseUIControlType
	 */
	public String getResponseUIControlType() {
		fetch();
		return responseUIControlType;
	}
	/**
	 * @param responseUIControlType the responseUIControlType to set
	 */
	public void setResponseUIControlType(String responseUIControlType) {
		if (this.responseUIControlType == null || !this.responseUIControlType.equals(responseUIControlType))
		{
			markModified();
		}
		this.responseUIControlType = responseUIControlType;
	}
	/**
	 * @return the rowSequence
	 */
	public int getRowSequence() {
		fetch();
		return rowSequence;
	}
	/**
	 * @param rowSequence the rowSequence to set
	 */
	public void setRowSequence(int rowSequence) {
		if (this.rowSequence != rowSequence)
		{
			markModified();
		}
		this.rowSequence = rowSequence;
	}
	/**
	 * @return the colSequence
	 */
	public int getColSequence() {
		fetch();
		return colSequence;
	}
	/**
	 * @param colSequence the colSequence to set
	 */
	public void setColSequence(int colSequence) {
		if (this.colSequence != colSequence)
		{
			markModified();
		}
		this.colSequence = colSequence;
	}
	/**
	 * @return the isRequired
	 */
	public boolean getIsRequired() {
		fetch();
		return isRequired;
	}
	/**
	 * @param isRequired the isRequired to set
	 */
	public void setIsRequired(boolean isRequired) {
		if (this.isRequired != isRequired)
		{
			markModified();
		}
		this.isRequired = isRequired;
	}
	/**
	 * @return the isResponseNewLine
	 */
	public boolean getIsResponseNewLine() {
		fetch();
		return isResponseNewLine;
	}
	/**
	 * @param isResponseNewLine the isResponseNewLine to set
	 */
	public void setIsResponseNewLine(boolean isResponseNewLine) {
		if (this.isResponseNewLine != isResponseNewLine)
		{
			markModified();
		}
		this.isResponseNewLine = isResponseNewLine;
	}
	/**
	 * @return the isEachResponseNewLine
	 */
	public boolean getIsEachResponseNewLine() {
		fetch();
		return isEachResponseNewLine;
	}
	/**
	 * @param isEachResponseNewLine the isEachResponseNewLine to set
	 */
	public void setIsEachResponseNewLine(boolean isEachResponseNewLine) {
		if (this.isEachResponseNewLine != isEachResponseNewLine)
		{
			markModified();
		}
		this.isEachResponseNewLine = isEachResponseNewLine;
	}


	/**
	 * @return the uiControlSize
	 */
	public String getUiControlSize() {
		fetch();
		return uiControlSize;
	}


	/**
	 * @param uiControlSize the uiControlSize to set
	 */
	public void setUiControlSize(String uiControlSize) {
		if (this.uiControlSize == null || !this.uiControlSize.equals(uiControlSize))
		{
			markModified();
		}
		this.uiControlSize = uiControlSize;
	}


	/**
	 * @return the textLength
	 */
	public String getTextLength() {
		fetch();
		return textLength;
	}


	/**
	 * @param textLength the textLength to set
	 */
	public void setTextLength(String textLength) {
		if (this.textLength == null || !this.textLength.equals(textLength))
		{
			markModified();
		}
		this.textLength = textLength;
	}


	/**
	 * @return the minValue
	 */
	public String getMinValue() {
		fetch();
		return minValue;
	}


	/**
	 * @param minValue the minValue to set
	 */
	public void setMinValue(String minValue) {
		if (this.minValue == null || !this.minValue.equals(minValue))
		{
			markModified();
		}
		this.minValue = minValue;
	}


	/**
	 * @return the maxValue
	 */
	public String getMaxValue() {
		fetch();
		return maxValue;
	}


	/**
	 * @param maxValue the maxValue to set
	 */
	public void setMaxValue(String maxValue) {
		if (this.maxValue == null || !this.maxValue.equals(maxValue))
		{
			markModified();
		}
		this.maxValue = maxValue;
	}


	/**
	 * @return the colWidth
	 */
	public String getColWidth() {
		fetch();
		return colWidth;
	}


	/**
	 * @param colWidth the colWidth to set
	 */
	public void setColWidth(String colWidth) {
		if (this.colWidth == null || !this.colWidth.equals(colWidth))
		{
			markModified();
		}
		this.colWidth = colWidth;
	}


	/**
	 * @return the summaryColWidth
	 */
	public String getSummaryColWidth() {
		fetch();
		return summaryColWidth;
	}


	/**
	 * @param summaryColWidth the summaryColWidth to set
	 */
	public void setSummaryColWidth(String summaryColWidth) {
		if (this.summaryColWidth == null || !this.summaryColWidth.equals(summaryColWidth))
		{
			markModified();
		}
		this.summaryColWidth = summaryColWidth;
	}


	/**
	 * @return the format
	 */
	public String getFormat() {
		fetch();
		return format;
	}


	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		if (this.format == null || !this.format.equals(format))
		{
			markModified();
		}
		this.format = format;
	}
	/**
	 * @return the noOfRespInRow
	 */
	public String getNoOfRespInRow() {
		fetch();
		return noOfRespInRow;
	}


	/**
	 * @param noOfRespInRow the noOfRespInRow to set
	 */
	public void setNoOfRespInRow(String noOfRespInRow) {
		if (this.noOfRespInRow == null || !this.noOfRespInRow.equals(noOfRespInRow))
		{
			markModified();
		}
		this.noOfRespInRow = noOfRespInRow;
	}


	/**
	 * @return the childFieldId
	 */
	public String getChildFieldId() {
		fetch();
		return childFieldId;
	}


	/**
	 * @param childFieldId the childFieldId to set
	 */
	public void setChildFieldId(String childFieldId) {
		if (this.childFieldId == null || !this.childFieldId.equals(childFieldId))
		{
			markModified();
		}
		this.childFieldId = childFieldId;
	}
	
	
	
}//end of CSReferralFormField class
