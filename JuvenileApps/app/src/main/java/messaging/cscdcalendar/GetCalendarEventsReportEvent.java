package messaging.cscdcalendar;

import java.util.Date;

import mojo.km.messaging.RequestEvent;


/**
 * 
 * @author cc_bjangay
 *
 */
public class GetCalendarEventsReportEvent  extends RequestEvent
{
	private String supervisorPositionId;
	
	private String csCalendarCategory;
	
	private Date startDate;
	
	private Date endDate;

	/**
	 * @return the csCalendarCategory
	 */
	public String getCsCalendarCategory() {
		return csCalendarCategory;
	}

	/**
	 * @param csCalendarCategory the csCalendarCategory to set
	 */
	public void setCsCalendarCategory(String csCalendarCategory) {
		this.csCalendarCategory = csCalendarCategory;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the supervisorPositionId
	 */
	public String getSupervisorPositionId() {
		return supervisorPositionId;
	}

	/**
	 * @param supervisorPositionId the supervisorPositionId to set
	 */
	public void setSupervisorPositionId(String supervisorPositionId) {
		this.supervisorPositionId = supervisorPositionId;
	}
}
