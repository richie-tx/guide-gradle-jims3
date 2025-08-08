package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileDSPlacementListEvent extends RequestEvent	 {

	public GetJuvenileDSPlacementListEvent(){
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
