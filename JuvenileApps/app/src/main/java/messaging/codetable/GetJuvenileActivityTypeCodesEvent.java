//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\codetable\\GetJuvenileActivityTypeCodesEvent.java

package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileActivityTypeCodesEvent extends RequestEvent {

	String action="";
	String permissionType;

	/**
	 * @roseuid 45771D04030F
	 */
	public GetJuvenileActivityTypeCodesEvent() {

	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPermissionType()
	{
	    return permissionType;
	}

	public void setPermissionType(String permissionType)
	{
	    this.permissionType = permissionType;
	}
	
	

	
}
