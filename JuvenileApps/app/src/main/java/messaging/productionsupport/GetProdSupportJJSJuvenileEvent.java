package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

/**
 * This is a bean.
 *
 */
public class GetProdSupportJJSJuvenileEvent extends RequestEvent
{
	private String juvenileId;
	
	public GetProdSupportJJSJuvenileEvent() {
		
	}

	public String getJuvenileId() {
		return juvenileId;
	}

	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
	
	
	
}
