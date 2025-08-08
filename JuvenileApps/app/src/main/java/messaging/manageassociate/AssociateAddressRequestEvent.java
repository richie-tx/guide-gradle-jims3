/*
 * Created on Mar 19, 2007
 *
 */
package messaging.manageassociate;

import messaging.address.AddressRequestEvent;

/**
 * @author cc_rsojitrawala
 *
 */
public class AssociateAddressRequestEvent extends AddressRequestEvent {
	
	private String complexName;
	private boolean isPrimaryResidenceAddress;
	
	public AssociateAddressRequestEvent(){
		
	}
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
	public void setIsPrimaryResidenceAddress(boolean isPrimaryResidenceAddress) {
		this.isPrimaryResidenceAddress = isPrimaryResidenceAddress;
	}
}
