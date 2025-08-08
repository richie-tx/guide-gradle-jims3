/*
 * Created on Aug 15, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.addressValidation.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author Rcooper
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ValidateAddressResponseEvent extends ResponseEvent
{	//private String additionalZipCode;
	//private String address2;
	private String addressId;
	//private String addressType;
	//private String addressTypeId;
	//private String aptNum;
	private String city;
	//private String country;
	//private String countryId;
	//private String county;
	//private String countyId;
	//private String currentAddressInd;
	private String keymap;
	//private String latitude;
	//private String longitude;
	private String state;
	//private String stateId;
	private String streetName;
	private String streetNum;
	private String streetType;
	//private String streetTypeId;
	private String zipCode;
	private String returnMessage;
	private String validAddressInd; 

/**
 * @return
 */
public String getAddressId()
{
	return addressId;
}

	/**
	 * @return
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * @return
	 */
	public String getKeymap()
	{
		return keymap;
	}

	/**
	 * @return
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * @return
	 */
	public String getStreetName()
	{
		return streetName;
	}

	/**
	 * @return
	 */
	public String getStreetNum()
	{
		return streetNum;
	}

	/**
	 * @return
	 */
	public String getStreetType()
	{
		return streetType;
	}

	/**
	 * @return
	 */
	public String getZipCode()
	{
		return zipCode;
	}

/**
 * @param string
 */
public void setAddressId(String string)
{
	addressId = string;
}

	/**
	 * @param string
	 */
	public void setCity(String string)
	{
		city = string;
	}

	/**
	 * @param string
	 */
	public void setKeymap(String string)
	{
		keymap = string;
	}

	/**
	 * @param string
	 */
	public void setState(String string)
	{
		state = string;
	}

	/**
	 * @param string
	 */
	public void setStreetName(String string)
	{
		streetName = string;
	}

	/**
	 * @param string
	 */
	public void setStreetNum(String string)
	{
		streetNum = string;
	}

	/**
	 * @param string
	 */
	public void setStreetType(String string)
	{
		streetType = string;
	}

	/**
	 * @param string
	 */
	public void setZipCode(String string)
	{
		zipCode = string;
	}

	/**
	 * @return
	 */
	public String getReturnMessage()
	{
		return returnMessage;
	}

	/**
	 * @return
	 */
	public String getValidAddressInd()
	{
		return validAddressInd;
	}

	/**
	 * @param string
	 */
	public void setReturnMessage(String string)
	{
		returnMessage = string;
	}

	/**
	 * @param string
	 */
	public void setValidAddressInd(String string)
	{
		validAddressInd = string;
	}

}
