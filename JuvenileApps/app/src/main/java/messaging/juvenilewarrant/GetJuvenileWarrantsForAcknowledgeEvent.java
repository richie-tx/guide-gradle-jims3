/*
 * Created on Jan 27, 2005
 */
package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

/**
 * @author dnikolis

 */
public class GetJuvenileWarrantsForAcknowledgeEvent extends RequestEvent
{

	public String firstName;
	public String lastName;
	public String warrantActivationStatus;
	public String warrantNum;
	public String warrantStatus;
	public String warrantTypeId;
	public String warrantAcknowledgeStatus;
	
	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
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
	 * @param firstName
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
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
	/**
	 * @return
	 */
	public String getWarrantAcknowledgeStatus()
	{
		return warrantAcknowledgeStatus;
	}

	/**
	 * @param string
	 */
	public void setWarrantAcknowledgeStatus(String string)
	{
		warrantAcknowledgeStatus = string;
	}

}