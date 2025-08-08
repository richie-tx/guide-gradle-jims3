/*
 * Created on Oct 13, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.Date;

import pd.km.util.Name;


import mojo.km.messaging.ResponseEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FamilyMemberBenefitResponseEvent extends ResponseEvent
{
	private String 	benefitId;
	private boolean	elgibleForBenefits;
	private boolean	receivingBenefits;
	private String 	eligibilityTypeId;
	private Date 	entryDate;
	
	//added for US 27022
	private String idNumber;
	private int receivedAmt;
	private Name receivedBy;
	
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
	public boolean isElgibleForBenefits()
	{
		return elgibleForBenefits;
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
	public void setElgibleForBenefits(boolean b)
	{
		elgibleForBenefits = b;
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

	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * @return the receivedAmt
	 */
	public int getReceivedAmt() {
		return receivedAmt;
	}

	/**
	 * @param receivedAmt the receivedAmt to set
	 */
	public void setReceivedAmt(int receivedAmt) {
		this.receivedAmt = receivedAmt;
	}

	/**
	 * @return the receivedBy
	 */
	public Name getReceivedBy() {
		return receivedBy;
	}

	/**
	 * @param receivedBy the receivedBy to set
	 */
	public void setReceivedBy(Name receivedBy) {
		this.receivedBy = receivedBy;
	}

}
