package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class RetrieveJuvPgmRefsByProvPgmIdEvent extends RequestEvent {
	public String provProgramId;

	public String getProvProgramId()
	{
	    return provProgramId;
	}

	public void setProvProgramId(String provProgramId)
	{
	    this.provProgramId = provProgramId;
	}

	/**
	 * @roseuid 463BA4D003A2
	 */
	public RetrieveJuvPgmRefsByProvPgmIdEvent() {

	}

	
	


}
