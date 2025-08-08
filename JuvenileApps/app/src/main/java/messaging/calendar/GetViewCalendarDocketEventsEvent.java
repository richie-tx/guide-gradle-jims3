/*
 * Created on Apr 20, 2007
 *
 */
package messaging.calendar;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author C_NRaveendran
 */
public class GetViewCalendarDocketEventsEvent extends RequestEvent
{

	private String juvenileFirstName;

	private String juvenileMiddleName;

	private String juvenileLastName;

	private String juvenileNum;

	private String jpoUserId;

	private String eventTypeId;

	private Date startDate;

	private Date endDate;

	public boolean hasJuvenileName()
	{
		return !((this.juvenileFirstName == null || this.juvenileFirstName.equals("") == true)
				|| (this.juvenileMiddleName == null || this.juvenileMiddleName.equals("") == true)
				|| (this.juvenileLastName == null || this.juvenileLastName.equals("") == true));
	}

	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * @param endDate
	 *            The endDate to set.
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * @return Returns the eventTypeId.
	 */
	public String getEventTypeId()
	{
		return eventTypeId;
	}

	/**
	 * @param eventTypeId
	 *            The eventTypeId to set.
	 */
	public void setEventTypeId(String eventTypeId)
	{
		this.eventTypeId = eventTypeId;
	}

	/**
	 * @return Returns the juvenileFirstName.
	 */
	public String getJuvenileFirstName()
	{
		return juvenileFirstName;
	}

	/**
	 * @param juvenileFirstName
	 *            The juvenileFirstName to set.
	 */
	public void setJuvenileFirstName(String juvenileFirstName)
	{
		this.juvenileFirstName = juvenileFirstName;
	}

	/**
	 * @return Returns the juvenileLastName.
	 */
	public String getJuvenileLastName()
	{
		return juvenileLastName;
	}

	/**
	 * @param juvenileLastName
	 *            The juvenileLastName to set.
	 */
	public void setJuvenileLastName(String juvenileLastName)
	{
		this.juvenileLastName = juvenileLastName;
	}

	/**
	 * @return Returns the juvenileMiddleName.
	 */
	public String getJuvenileMiddleName()
	{
		return juvenileMiddleName;
	}

	/**
	 * @param juvenileMiddleName
	 *            The juvenileMiddleName to set.
	 */
	public void setJuvenileMiddleName(String juvenileMiddleName)
	{
		this.juvenileMiddleName = juvenileMiddleName;
	}

	/**
	 * @return Returns the startDate.
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * @param startDate
	 *            The startDate to set.
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * @return Returns the jpoUserId.
	 */
	public String getJpoUserId()
	{
		return jpoUserId;
	}

	/**
	 * @param jpoUserId
	 *            The jpoUserId to set.
	 */
	public void setJpoUserId(String jpoUserId)
	{
		this.jpoUserId = jpoUserId;
	}

	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param juvenileNum
	 *            The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		this.juvenileNum = juvenileNum;
	}
	
}
