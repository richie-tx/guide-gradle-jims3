/*
 * Created on Oct 6, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author kmurthy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RiskResponseEvent extends ResponseEvent
{
	private String questionText;
	private String dataEntryValue = null;
	//private boolean	isWeighted = false;
	private int responseID;
	private int weight;

	//added for Production Support
	private String responseText;
	private String riskAnalysisID;
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;

	/**
	 * @return
	 */
	public String getDataEntryValue()
	{
		return dataEntryValue;
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
	public int getResponseID()
	{
		return responseID;
	}

	/**
	 * @return
	 */
	public int getWeight()
	{
		return weight;
	}

	/**
	 * @param string
	 */
	public void setDataEntryValue(final String string)
	{
		dataEntryValue = string;
	}

	/**
	 * @param i
	 */
	public void setQuestionText(final String questionText)
	{
		this.questionText = questionText;
	}

	/**
	 * @param i
	 */
	public void setResponseID(final int i)
	{
		responseID = i;
	}

	/**
	 * @param i
	 */
	public void setWeight(final int i)
	{
		weight = i;
	}


	/**
	 * @return the createUserID
	 */
	public String getCreateUserID() {
		return createUserID;
	}


	/**
	 * @param createUserID the createUserID to set
	 */
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}


	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}


	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}


	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}


	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}


	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	/**
	 * @return the createJIMS2UserID
	 */
	public String getCreateJIMS2UserID() {
		return createJIMS2UserID;
	}


	/**
	 * @param createJIMS2UserID the createJIMS2UserID to set
	 */
	public void setCreateJIMS2UserID(String createJIMS2UserID) {
		this.createJIMS2UserID = createJIMS2UserID;
	}


	/**
	 * @return the updateJIMS2UserID
	 */
	public String getUpdateJIMS2UserID() {
		return updateJIMS2UserID;
	}


	/**
	 * @param updateJIMS2UserID the updateJIMS2UserID to set
	 */
	public void setUpdateJIMS2UserID(String updateJIMS2UserID) {
		this.updateJIMS2UserID = updateJIMS2UserID;
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
	 * @return the riskAnalysisID
	 */
	public String getRiskAnalysisID() {
		return riskAnalysisID;
	}


	/**
	 * @param riskAnalysisID the riskAnalysisID to set
	 */
	public void setRiskAnalysisID(String riskAnalysisID) {
		this.riskAnalysisID = riskAnalysisID;
	}

}
