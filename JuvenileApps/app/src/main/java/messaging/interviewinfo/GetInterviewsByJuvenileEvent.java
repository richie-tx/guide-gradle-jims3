package messaging.interviewinfo;

import mojo.km.messaging.RequestEvent;


public class GetInterviewsByJuvenileEvent extends RequestEvent 
{
	private String juvenileId;
	
	/**
	* 
	*/
	public GetInterviewsByJuvenileEvent() 
	{
    
	}
	
	/**
	 * @return Returns the juvenileId.
	 */
	public String getJuvenileId() {
		return juvenileId;
	}
	/**
	 * @param juvenileId The juvenileId to set.
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
}
