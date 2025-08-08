/**
 * 
 */
package messaging.csserviceprovider;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_cwalters
 *
 */
public class GetSPLocationEvent extends RequestEvent 
{
	private String locationId;

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
		
}
