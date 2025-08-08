//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\UpdateNCResponseEvent.java

package messaging.administercompliance;

import java.sql.Timestamp;

import mojo.km.messaging.RequestEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateNCReportingEvent extends RequestEvent 
{
	/**
	 * @return Returns the details.
	 */
	public String getDetails() {
		return details;
	}
	/**
	 * @param details The details to set.
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	/**
	 * @return Returns the eventTypes.
	 */
	public String getEventTypes() {
		return eventTypes;
	}
	/**
	 * @param eventTypes The eventTypes to set.
	 */
	public void setEventTypes(String eventTypes) {
		this.eventTypes = eventTypes;
	}

	/**
	 * @return Returns the occurencedate.
	 */
	public Timestamp getOccurencedate() {
		return occurencedate;
	}
	/**
	 * @param occurencedate The occurencedate to set.
	 */
	public void setOccurencedate(Timestamp occurencedate) {
		this.occurencedate = occurencedate;
	}
	/**
	 * @return Returns the reportingid.
	 */
	public String getReportingid() {
		return reportingid;
	}
	/**
	 * @param reportingid The reportingid to set.
	 */
	public void setReportingid(String reportingid) {
		this.reportingid = reportingid;
	}
	
    private boolean manualAdded;
    private Timestamp occurencedate; 
    private String eventTypes;
    private String details;
    private String reportingid;
	/**
	 * @return the manualAdded
	 */
	public boolean isManualAdded() {
		return manualAdded;
	}
	/**
	 * @param manualAdded the manualAdded to set
	 */
	public void setManualAdded(boolean manualAdded) {
		this.manualAdded = manualAdded;
	}
}
