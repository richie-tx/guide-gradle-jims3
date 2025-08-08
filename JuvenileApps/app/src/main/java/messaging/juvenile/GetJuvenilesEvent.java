package messaging.juvenile;

import mojo.km.messaging.RequestEvent;


public class GetJuvenilesEvent extends RequestEvent 
{

	private String juvenileId;
	private String statusId;
   
    /**
    * @roseuid 42B196830128
    */
    public GetJuvenilesEvent() 
    {
    }
    
    /**
	 * @return Returns the juvenileId.
	 */
	public String getJuvenileId() {
		return juvenileId;
	}

	/**
	 * @param juvenileId
	 *            The juvenileId to set.
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}

	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId
	 *            The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
}
