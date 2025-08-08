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
public class GetDivisionEvent extends RequestEvent 
{

	String divisionId ; 
	
	
	
	/**
	 * @return Returns the divisionId.
	 */
	public String getDivisionId() {
		return divisionId;
	}
	/**
	 * @param divisionId The divisionId to set.
	 */
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
}
