/*
 * Created on Oct 5, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.address;

import mojo.km.messaging.RequestEvent;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetAddressByIdEvent extends RequestEvent {
	
	private String addressId;

	/**
	 * @return Returns the addressId.
	 */
	public String getAddressId() {
		return addressId;
	}
	/**
	 * @param addressId The addressId to set.
	 */
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
}
