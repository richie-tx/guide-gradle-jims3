package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;


/**
 * @author rcarter
 */

public class GetProductionSupportFacilityHeaderEvent extends RequestEvent 
{	
	private String juvenileId;

	/**
	 * @return the juvenileId
	 */
	public String getJuvenileId() {
		return juvenileId;
	}

	/**
	 * @param juvenileId the juvenileId to set
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}

	
	
}
