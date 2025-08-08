package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

/**
 * This is a bean.
 *
 */
public class GetProductionSupportJuvenileEvent extends RequestEvent
{
	private String juvenileId;
	
	public GetProductionSupportJuvenileEvent() {
		
	}

	public String getJuvenileId() {
		return juvenileId;
	}

	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
	
	
	
}
