package ui.common;

import ui.juvenilecase.form.JuvenileMemberForm.MemberAddress;
import messaging.addressValidation.AddressValidationEvent;
import messaging.addressValidation.reply.ValidateAddressResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;

/**
 * @author dgibler
 */
public final class AddressHelper {
	/**
		 *
		 */
	private AddressHelper() {
	}

	public static messaging.contact.to.Address validateAddress(
			messaging.contact.to.Address myAddress) {
		String streetNum = myAddress.getStreetNum();
		streetNum = AddressHelper.cleanStreetNum(streetNum);
		String streetName = myAddress.getStreetName();
		String zip = myAddress.getZipCode();

		AddressValidationEvent requestEvent = new AddressValidationEvent();
		if ( streetNum.length() > 0 ) {
		    requestEvent.setStreetNum(AddressHelper.convertStreetNum(streetNum));
		}
		requestEvent.setStreetName(streetName);
		if (zip != null && zip.equals("") == false) {
			requestEvent.setZipCode(new Integer(zip).intValue());
		}

		CompositeResponse response = MessageUtil.postRequest(requestEvent);
		ValidateAddressResponseEvent data = (ValidateAddressResponseEvent) MessageUtil
				.filterComposite(response, ValidateAddressResponseEvent.class);

		myAddress.setValidated(data.getValidAddressInd());
		return myAddress;
	}
	
	public static Address validateAddress(Address myAddress) {
		String streetNum = myAddress.getStreetNum();
		streetNum = AddressHelper.cleanStreetNum(streetNum);
		String streetName = myAddress.getStreetName();
		String zip = myAddress.getZipCode();

		AddressValidationEvent requestEvent = new AddressValidationEvent();
		if ( streetNum.length() > 0 ) {
		    requestEvent.setStreetNum(AddressHelper.convertStreetNum(streetNum));
		}
		requestEvent.setStreetName(streetName);
		if (zip != null && zip.equals("") == false) {
			requestEvent.setZipCode(new Integer(zip).intValue());
		}

		CompositeResponse response = MessageUtil.postRequest(requestEvent);
		ValidateAddressResponseEvent data = (ValidateAddressResponseEvent) MessageUtil
				.filterComposite(response, ValidateAddressResponseEvent.class);

		myAddress.setValidated(data.getValidAddressInd());
		return myAddress;
	}

	public static MemberAddress validateAddress(MemberAddress myAddress) {
		String streetNum = myAddress.getStreetNum();
		streetNum = AddressHelper.cleanStreetNum(streetNum);
		String streetName = myAddress.getStreetName();
		String zip = myAddress.getZipCode();

		AddressValidationEvent requestEvent = new AddressValidationEvent();
		if ( streetNum.length() > 0 ) {
		    requestEvent.setStreetNum(AddressHelper.convertStreetNum(streetNum));
		}
		requestEvent.setStreetName(streetName);
		if (zip != null && zip.equals("") == false) {
			requestEvent.setZipCode(new Integer(zip).intValue());
		}

		CompositeResponse response = MessageUtil.postRequest(requestEvent);
		ValidateAddressResponseEvent data = (ValidateAddressResponseEvent) MessageUtil
				.filterComposite(response, ValidateAddressResponseEvent.class);

		myAddress.setValidated(data.getValidAddressInd());
		return myAddress;
	}

	public static String validateAddress(String streetNumber,
			String streetName, String zip) {
		String streetNum = AddressHelper.cleanStreetNum(streetNumber);
		AddressValidationEvent requestEvent = new AddressValidationEvent();
		if ( streetNum.length() > 0 ) {
		    requestEvent.setStreetNum(AddressHelper.convertStreetNum(streetNum));
		}
		requestEvent.setStreetName(streetName);
		if (zip != null && zip.equals("") == false) {
			requestEvent.setZipCode(new Integer(zip).intValue());
		}

		CompositeResponse response = MessageUtil.postRequest(requestEvent);
		ValidateAddressResponseEvent data = (ValidateAddressResponseEvent) MessageUtil
				.filterComposite(response, ValidateAddressResponseEvent.class);

		return data.getValidAddressInd();
	}

	/**
	 * @param streetNum
	 * @return
	 */
	private static String cleanStreetNum(String streetNum) {
	    if (streetNum != null
		    && streetNum.length() > 0) {
		StringBuffer buffer = new StringBuffer();
		boolean done = false;
		for (int i = 0; i < streetNum.length() && done == false; i++) {
			char ch = streetNum.charAt(i);
			if (Character.isDigit(ch) == false) {
				done = true;
			} else {
				buffer.append(ch);
			}
		}
		Integer streetNumInt = new Integer(buffer.toString());
		return streetNumInt.toString();
	    } else {
		return "";
	    }
	}

	/**
	 * Converts a street number String into an int. The algorithm truncates off
	 * any non-numeric digits.
	 * 
	 * @param streetNum
	 * @return
	 */
	private static int convertStreetNum(String streetNum) {
		int streetNumInt;
		try {
			streetNumInt = Integer.parseInt(streetNum);
		} catch (NumberFormatException e) {
			StringBuffer buffer = new StringBuffer();
			boolean done = false;
			for (int i = 0; i < streetNum.length() && done == false; i++) {
				char ch = streetNum.charAt(i);
				if (ch >= '0' && ch <= '9') {
					buffer.append(ch);
				} else {
					done = true;
				}
			}
			streetNumInt = Integer.parseInt(buffer.toString());
		}
		return streetNumInt;
	}
}
