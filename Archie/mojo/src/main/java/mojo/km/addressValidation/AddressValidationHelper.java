/*
 * Created on Aug 16, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.addressValidation;

import messaging.addressValidation.reply.ValidateAddressResponseEvent;

/**
 * @author Rcooper
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class AddressValidationHelper
{
	public static ValidateAddressResponseEvent getValidateAddressResponseEvent(Address address)
		{
			ValidateAddressResponseEvent vare = new ValidateAddressResponseEvent();
			// DPA: Setting the ids
			vare.setAddressId(address.getOID().toString());
			vare.setKeymap(address.getKeyMap());
			vare.setStreetName(address.getStreetName());
			vare.setStreetNum(new Integer(address.getStreetNum()).toString());
			vare.setZipCode(new Integer(address.getZipCode()).toString());
           
            vare.setValidAddressInd("Y");
			
			return vare;
		}
}
