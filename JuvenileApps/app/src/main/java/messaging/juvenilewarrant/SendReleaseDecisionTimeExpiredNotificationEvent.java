//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilewarrant\\CheckTransferFromReleaseDecisionTimeEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.PersistentEvent;

public class SendReleaseDecisionTimeExpiredNotificationEvent extends PersistentEvent
{
	private String warrantNum;
	private String emailTo;
	private String emailFrom;
	private java.sql.Timestamp releaseDecisionDate;
	private String releaseDecision;
	private String juvenileFirstName;
	private String juvenileLastName;
	private String officerFirstName;
	private String officerLastName;
	private String managerFirstName;
	private String managerLastName;
	private String contactFirstName;
	private String contactLastName;
	private String contactPhone;

	/**
	 * @roseuid 4345531902EE
	 */
	public SendReleaseDecisionTimeExpiredNotificationEvent()
	{

	}

	/**
	 * @param warrantNum
	 * @roseuid 434551EA011C
	 */
	public void setWarrantNum(String warrantNum)
	{
		this.warrantNum = warrantNum;
	}

	/**
	 * @return String
	 * @roseuid 434551EA0128
	 */
	public String getWarrantNum()
	{
		return this.warrantNum;
	}
	/**
	 * @return
	 */
	public String getEmailTo()
	{
		return emailTo;
	}

	/**
	 * @param string
	 */
	public void setEmailTo(String string)
	{
		emailTo = string;
	}

	/**
	 * @return
	 */
	public String getEmailFrom()
	{
		return emailFrom;
	}

	/**
	 * @param string
	 */
	public void setEmailFrom(String string)
	{
		emailFrom = string;
	}

	/**
	 * @return
	 */
	public String getContactPhone()
	{
		return contactPhone;
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
	public String getManagerFirstName()
	{
		return managerFirstName;
	}

	/**
	 * @return
	 */
	public String getManagerLastName()
	{
		return managerLastName;
	}

	/**
	 * @return
	 */
	public String getOfficerFirstName()
	{
		return officerFirstName;
	}

	/**
	 * @return
	 */
	public String getOfficerLastName()
	{
		return officerLastName;
	}

	/**
	 * @return
	 */
	public java.sql.Timestamp getReleaseDecisionDate()
	{
		return releaseDecisionDate;
	}

	/**
	 * @param string
	 */
	public void setContactPhone(String string)
	{
		contactPhone = string;
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
	public void setManagerFirstName(String string)
	{
		managerFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setManagerLastName(String string)
	{
		managerLastName = string;
	}

	/**
	 * @param string
	 */
	public void setOfficerFirstName(String string)
	{
		officerFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setOfficerLastName(String string)
	{
		officerLastName = string;
	}

	/**
	 * @param date
	 */
	public void setReleaseDecisionDate(java.sql.Timestamp date)
	{
		releaseDecisionDate = date;
	}

	/**
	 * @return
	 */
	public String getReleaseDecision()
	{
		return releaseDecision;
	}

	/**
	 * @param string
	 */
	public void setReleaseDecision(String string)
	{
		releaseDecision = string;
	}

	/**
	 * @return
	 */
	public String getContactFirstName()
	{
		return contactFirstName;
	}

	/**
	 * @return
	 */
	public String getContactLastName()
	{
		return contactLastName;
	}

	/**
	 * @param string
	 */
	public void setContactFirstName(String string)
	{
		contactFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setContactLastName(String string)
	{
		contactLastName = string;
	}

}
