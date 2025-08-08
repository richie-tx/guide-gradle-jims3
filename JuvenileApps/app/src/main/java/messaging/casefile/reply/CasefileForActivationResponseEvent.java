package messaging.casefile.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * 
 */
public class CasefileForActivationResponseEvent extends ResponseEvent
{
	private String supervisionNum;
	private String juvenileMasterStatusId;
	private boolean isMAYSINeeded;
	private boolean isTitle4eNeeded;
	private Date dateOfBirth;
	private boolean riskAnalysis;
	private String supervisionStatusId;
	
	private String supervisionTypeId;
	private Date supervisionEndDate;

	private Date activationDate;
	private String caseStatusId;
	
	private Date courtOrderedProbationStartDate;
	
	/**
	 * @return Returns the activationDate.
	 */
	public Date getActivationDate() {
		return activationDate;
	}
	/**
	 * @param activationDate The activationDate to set.
	 */
	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}
	/**
	 * @return Returns the caseStatusId.
	 */
	public String getCaseStatusId() {
		return caseStatusId;
	}
	/**
	 * @param caseStatusId The caseStatusId to set.
	 */
	public void setCaseStatusId(String caseStatusId) {
		this.caseStatusId = caseStatusId;
	}


	/**
	 * @return
	 */
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	/**
	 * @return
	 */
	public boolean isMAYSINeeded()
	{
		return isMAYSINeeded;
	}

	/**
	 * @return
	 */
	public boolean isTitle4eNeeded()
	{
		return isTitle4eNeeded;
	}

	/**
	 * @return
	 */
	public String getJuvenileMasterStatusId()
	{
		return juvenileMasterStatusId;
	}

	/**
	 * @return
	 */
	public boolean isRiskAnalysis()
	{
		return riskAnalysis;
	}

	/**
	 * @return
	 */
	public String getSupervisionNum()
	{
		return supervisionNum;
	}

	/**
	 * @param date
	 */
	public void setDateOfBirth(Date date)
	{
		dateOfBirth = date;
	}

	/**
	 * @param b
	 */
	public void setMAYSINeeded(boolean b)
	{
		isMAYSINeeded = b;
	}

	/**
	 * @param b
	 */
	public void setTitle4eNeeded(boolean b)
	{
		isTitle4eNeeded = b;
	}

	/**
	 * @param string
	 */
	public void setJuvenileMasterStatusId(String string)
	{
		juvenileMasterStatusId = string;
	}

	/**
	 * @param b
	 */
	public void setRiskAnalysis(boolean b)
	{
		riskAnalysis = b;
	}

	/**
	 * @param string
	 */
	public void setSupervisionNum(String string)
	{
		supervisionNum = string;
	}

	/**
	 * @return
	 */
	public String getSupervisionStatusId()
	{
		return supervisionStatusId;
	}

	/**
	 * @param string
	 */
	public void setSupervisionStatusId(String string)
	{
		supervisionStatusId = string;
	}

	/**
	 * @return
	 */
	public Date getSupervisionEndDate()
	{
		return supervisionEndDate;
	}

	/**
	 * @return
	 */
	public String getSupervisionTypeId()
	{
		return supervisionTypeId;
	}

	/**
	 * @param date
	 */
	public void setSupervisionEndDate(Date date)
	{
		supervisionEndDate = date;
	}

	/**
	 * @param string
	 */
	public void setSupervisionTypeId(String string)
	{
		supervisionTypeId = string;
	}
	/**
	 * @return the courtOrderedProbationStartDate
	 */
	public Date getCourtOrderedProbationStartDate() {
		return courtOrderedProbationStartDate;
	}
	
	/**
	 * @param string
	 */
	public void setCourtOrderedProbationStartDate(Date date) {
		courtOrderedProbationStartDate = date;
	}

}