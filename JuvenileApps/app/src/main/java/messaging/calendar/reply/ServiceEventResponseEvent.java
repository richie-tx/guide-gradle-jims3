/*
 * Created on Oct 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.calendar.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceEventResponseEvent extends ResponseEvent {

	private Date startDateTime;
	private Date programName;
	private Date serviceName;
	private Date serviceProviderName;
	
	/**
	 * @return Returns the startDateTime.
	 */
	public Date getStartDateTime() {
		return startDateTime;
	}
	/**
	 * @param startDateTime The startDateTime to set.
	 */
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	/**
	 * @return Returns the programName.
	 */
	public Date getProgramName() {
		return programName;
	}
	/**
	 * @param programName The programName to set.
	 */
	public void setProgramName(Date programName) {
		this.programName = programName;
	}
	/**
	 * @return Returns the serviceName.
	 */
	public Date getServiceName() {
		return serviceName;
	}
	/**
	 * @param serviceName The serviceName to set.
	 */
	public void setServiceName(Date serviceName) {
		this.serviceName = serviceName;
	}
	/**
	 * @return Returns the serviceProviderName.
	 */
	public Date getServiceProviderName() {
		return serviceProviderName;
	}
	/**
	 * @param serviceProviderName The serviceProviderName to set.
	 */
	public void setServiceProviderName(Date serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
}
