package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileDSservicesListEvent extends RequestEvent	 {

	public GetJuvenileDSservicesListEvent(){
	}
	private String dualID;
	
	public String getDualID()
	{
	    return dualID;
	}

	public void setDualID(String dualID)
	{
	    this.dualID = dualID;
	}

	
}
