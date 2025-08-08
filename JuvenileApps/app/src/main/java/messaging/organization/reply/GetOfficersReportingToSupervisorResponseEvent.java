/*
 * Created on Aug 31, 2007
 *
 */
package messaging.organization.reply;

import java.io.Serializable;
import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_mdsouza
 *
 */
public class GetOfficersReportingToSupervisorResponseEvent extends ResponseEvent implements Serializable {

	Collection OfficersReportingToSupervisor ; 
	
	
	
	/**
	 * @return Returns the officersReportingToSupervisor.
	 */
	public Collection getOfficersReportingToSupervisor() {
		return OfficersReportingToSupervisor;
	}
	/**
	 * @param officersReportingToSupervisor The officersReportingToSupervisor to set.
	 */
	public void setOfficersReportingToSupervisor(Collection officersReportingToSupervisor) {
		OfficersReportingToSupervisor = officersReportingToSupervisor;
	}
}
