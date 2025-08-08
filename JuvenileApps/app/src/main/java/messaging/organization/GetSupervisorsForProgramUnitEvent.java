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
public class GetSupervisorsForProgramUnitEvent extends RequestEvent  {

	String programUnitId ; 
	
	
	
	/**
	 * @return Returns the programUnitId.
	 */
	public String getProgramUnitId() {
		return programUnitId;
	}
	/**
	 * @param programUnitId The programUnitId to set.
	 */
	public void setProgramUnitId(String programUnitId) {
		this.programUnitId = programUnitId;
	}
}
