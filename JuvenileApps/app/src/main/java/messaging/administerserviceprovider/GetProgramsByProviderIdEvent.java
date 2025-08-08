/*
 * Created on Oct 10, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetProgramsByProviderIdEvent extends RequestEvent {
	   
	private String juvServiceProviderId;
	private String statusId;
	
	public GetProgramsByProviderIdEvent(){

	}

	public String getJuvServiceProviderId()
	{
	    return juvServiceProviderId;
	}

	public void setJuvServiceProviderId(String juvServiceProviderId)
	{
	    this.juvServiceProviderId = juvServiceProviderId;
	}

	public String getStatusId()
	{
	    return statusId;
	}

	public void setStatusId(String statusId)
	{
	    this.statusId = statusId;
	}

}
