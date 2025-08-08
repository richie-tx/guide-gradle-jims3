/**
 * 
 */
package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_vnarsingoju
 *
 */
public class GetSPNamesEvent extends RequestEvent {

	public boolean allServiceProviders;

	public boolean isAllServiceProviders() {
		return allServiceProviders;
	}

	public void setAllServiceProviders(boolean allServiceProviders) {
		this.allServiceProviders = allServiceProviders;
	}
	   
	
	
}
