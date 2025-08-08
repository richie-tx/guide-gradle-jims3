/*
 * Created on Jan 27, 2005
 */
package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

/**
 * @author dnikolis
 */
public class GetJuvenileWarrantsForActivateEvent extends RequestEvent
{
	public String warrantAcknowledgeStatus;
	public String warrantActivationStatus;
	public String warrantNum;
	public String warrantSignedStatus;
	public String warrantStatus;
	public String warrantTypeId;
	/**
	 * @return
	 */
	public String getWarrantAcknowledgeStatus()
	{
		return warrantAcknowledgeStatus;
	}

	/**
	 * @return
	 */
	public String getWarrantActivationStatus()
	{
		return warrantActivationStatus;
	}

	/**
	 * @return
	 */
	public String getWarrantNum()
	{
		return warrantNum;
	}

	/**
	 * @return
	 */
	public String getWarrantSignedStatus()
	{
		return warrantSignedStatus;
	}

	/**
	 * @return
	 */
	public String getWarrantStatus()
	{
		return warrantStatus;
	}

	/**
	 * @return
	 */
	public String getWarrantTypeId()
	{
		return warrantTypeId;
	}

	/**
	 * @param warrantAcknowledgeStatus
	 */
	public void setWarrantAcknowledgeStatus(String warrantAcknowledgeStatus)
	{
		this.warrantAcknowledgeStatus = warrantAcknowledgeStatus;
	}

	/**
	 * @param warrantActivationStatus
	 */
	public void setWarrantActivationStatus(String warrantActivationStatus)
	{
		this.warrantActivationStatus = warrantActivationStatus;
	}

	/**
	 * @param warrantNum
	 */
	public void setWarrantNum(String warrantNum)
	{
		this.warrantNum = warrantNum;
	}

	/**
	 * @param warrantSignedStatus
	 */
	public void setWarrantSignedStatus(String warrantSignedStatus)
	{
		this.warrantSignedStatus = warrantSignedStatus;
	}

	/**
	 * @param warrantStatus
	 */
	public void setWarrantStatus(String warrantStatus)
	{
		this.warrantStatus = warrantStatus;
	}

	/**
	 * @param warrantTypeId
	 */
	public void setWarrantTypeId(String warrantTypeId)
	{
		this.warrantTypeId = warrantTypeId;
	}

}
