/*
 * Created on Jul 14, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * 
 * 
 * 
 * 
 * Perpose of this event is to search a NOT_ACTIIVE or ACTIVE warrant 
 */
public class SearchJuvenileWarrantActiveOrPendingEvent extends RequestEvent
{
	private String daLogNumber;
	private String juvenileFirstName;
	private String juvenileLastName;
	private String dateOfBirth;
	private Integer referralNumber;
	private String petitionNumber;
	private String warrantActivationStatus; 
	private String warrantStatusId;
	private Integer juvenileNum;
	

	/**
	 * @return
	 */
	public String getDaLogNumber()
	{
		return daLogNumber;
	}

	/**
	 * @return
	 */
	public String getDateOfBirth()
	{
		return dateOfBirth;
	}

	/**
	 * @return
	 */
	public String getJuvenileFirstName()
	{
		return juvenileFirstName;
	}

	/**
	 * @return
	 */
	public String getJuvenileLastName()
	{
		return juvenileLastName;
	}


	/**
	 * @return
	 */
	public String getPetitionNumber()
	{
		return petitionNumber;
	}

	/**
	 * @return
	 */
	public Integer getReferralNumber()
	{
		return referralNumber;
	}

	/**
	 * @param string
	 */
	public void setDaLogNumber(String string)
	{
		daLogNumber = string;
	}

	/**
	 * @param string
	 */
	public void setDateOfBirth(String string)
	{
		dateOfBirth = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileFirstName(String string)
	{
		juvenileFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileLastName(String string)
	{
		juvenileLastName = string;
	}

	/**
	 * @param string
	 */
	public void setPetitionNumber(String string)
	{
		petitionNumber = string;
	}

	/**
	 * @param string
	 */
	public void setReferralNumber(Integer referralNumber)
	{
		this.referralNumber = referralNumber;
	}

	/**
	 * @return
	 */
	public String getWarrantActivationStatus()
	{
		return warrantActivationStatus;
	}

	/**
	 * @param string
	 */
	public void setWarrantActivationStatus(String string)
	{
		warrantActivationStatus = string;
	}

	/**
	 * @return
	 */
	public Integer getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param integer
	 */
	public void setJuvenileNum(Integer integer)
	{
		juvenileNum = integer;
	}

	/**
	 * @return Returns the warrantStatusId.
	 */
	public String getWarrantStatusId() {
		return warrantStatusId;
	}
	/**
	 * @param warrantStatusId The warrantStatusId to set.
	 */
	public void setWarrantStatusId(String warrantStatusId) {
		this.warrantStatusId = warrantStatusId;
	}
}
