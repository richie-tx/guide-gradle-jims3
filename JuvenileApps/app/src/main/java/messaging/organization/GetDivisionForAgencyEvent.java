/*
 * Created on Aug 31, 2007
 *
 */
package messaging.organization;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_mdsouza
 *
 */
public class GetDivisionForAgencyEvent extends RequestEvent {

	
	private String agencyCode ; 
	private String divisionId;
	
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}	
	
	/**
	 * @return Returns the agencyCode.
	 */
	public String getAgencyCode() {
		return agencyCode;
	}
	/**
	 * @param agencyCode The agencyCode to set.
	 */
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
}
