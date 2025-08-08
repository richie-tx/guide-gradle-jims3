/*
 * Created on Apr 11, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.manageassociate.reply;

import messaging.address.reply.AddressResponseEvent;

/**
 * @author cc_rsojitrawala
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AssociateAddressResponseEvent extends AddressResponseEvent {
	
	private String complexName;
	private boolean isPrimaryResidenceAddress;
	/**
	 * @return Returns the complexName.
	 */
	public String getComplexName() {
		return complexName;
	}
	/**
	 * @param complexName The complexName to set.
	 */
	public void setComplexName(String complexName) {
		this.complexName = complexName;
	}
	/**
	 * @return Returns the isPrimaryResidenceAddress.
	 */
	public boolean getIsPrimaryResidenceAddress() {
		return isPrimaryResidenceAddress;
	}
	/**
	 * @param isPrimaryResidenceAddress The isPrimaryResidenceAddress to set.
	 */
	public void setPrimaryResidenceAddress(boolean isPrimaryResidenceAddress) {
		this.isPrimaryResidenceAddress = isPrimaryResidenceAddress;
	}
}
