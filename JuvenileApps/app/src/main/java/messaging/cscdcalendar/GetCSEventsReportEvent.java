package messaging.cscdcalendar;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class GetCSEventsReportEvent extends RequestEvent
{
	private String positionId;
	private String csEventCategory;
	private Date startDate;
	private Date endDate;
	
	/**
	 * @return the csEventCategory
	 */
	public String getCsEventCategory() {
		return csEventCategory;
	}
	/**
	 * @param csEventCategory the csEventCategory to set
	 */
	public void setCsEventCategory(String csEventCategory) {
		this.csEventCategory = csEventCategory;
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
	 * @return the positionId
	 */
	public String getPositionId() {
		return positionId;
	}
	/**
	 * @param positionId the positionId to set
	 */
	public void setPositionId(String positionId) {
		this.positionId = positionId;
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
}
