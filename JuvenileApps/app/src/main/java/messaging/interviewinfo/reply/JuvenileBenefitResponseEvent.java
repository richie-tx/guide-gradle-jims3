/*
 * Created on Oct 13, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.interviewinfo.reply;

import java.util.Date;

import ui.common.Name;

import mojo.km.messaging.ResponseEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileBenefitResponseEvent extends ResponseEvent
{
	private String 	benefitId;
	private boolean	eligibleForBenefits;
	private boolean	receivingBenefits;
	private String 	eligibilityTypeId;
	private Date 	entryDate;
	
	//added for US 27022
	private String idNumber;
	private int receivedAmt;
	private Name receivedBy;
	private String benefitStatus;
	
	/**
	 * @return
	 */
	public String getBenefitId()
	{
		return benefitId;
	}

	/**
	 * @return
	 */
	public Date getEntryDate()
	{
		return entryDate;
	}

	/**
	 * @return
	 */
	public boolean isEligibleForBenefits()
	{
		return eligibleForBenefits;
	}

	/**
	 * @return
	 */
	public String getEligibilityTypeId()
	{
		return eligibilityTypeId;
	}

	/**
	 * @return
	 */
	public boolean isReceivingBenefits()
	{
		return receivingBenefits;
	}

	/**
	 * @param string
	 */
	public void setBenefitId(String string)
	{
		benefitId = string;
	}

	/**
	 * @param date
	 */
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}

	/**
	 * @param b
	 */
	public void setEligibleForBenefits(boolean b)
	{
		eligibleForBenefits = b;
	}

	/**
	 * @param string
	 */
	public void setEligibilityTypeId(String string)
	{
		eligibilityTypeId = string;
	}

	/**
	 * @param b
	 */
	public void setReceivingBenefits(boolean b)
	{
		receivingBenefits = b;
	}

	public Name getReceivedBy() {
		return receivedBy;
	}

	public void setReceivedBy(Name receivedBy) {
		this.receivedBy = receivedBy;
	}

	public int getReceivedAmt() {
		return receivedAmt;
	}

	public void setReceivedAmt(int receivedAmt) {
		this.receivedAmt = receivedAmt;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getBenefitStatus() {
		return benefitStatus;
	}

	public void setBenefitStatus(String benefitStatus) {
		this.benefitStatus = benefitStatus;
	}

}
