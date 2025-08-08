package messaging.administercaseload;

import mojo.km.messaging.RequestEvent;

public class GetActiveCasesByCaseEvent extends RequestEvent
{
	private String criminalCaseId;
    
	
	
	
	/**
     * @roseuid 46435F2003D4
     */
    public GetActiveCasesByCaseEvent()
    {

    }
    
    
	public String getCriminalCaseId() {
		return criminalCaseId;
	}
	
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
    
    
}
