package messaging.supervisionstaff;

import mojo.km.messaging.RequestEvent;

public class GetLightParentOrganizationEvent extends RequestEvent{

    private String programUnitId ; 
	
	/**
	 * @return Returns the organizationId.
	 */
	public String getProgramUnitId() {
		return programUnitId;
	}
	
	
	/**
	 * @param organizationId The organizationId to set.
	 */
	public void setProgramUnitId( String organizationId ) {
		this.programUnitId = organizationId;
	}
	
}
