/*
 * Created on Oct 13, 2004
 */
package messaging.address;

import mojo.km.messaging.RequestEvent;

/**
 * @author dnikolis
 */
public class AddressRequestEvent extends RequestEvent
{
	private String additionalZipCode;
	private String address2;
	private String addressId;
	private String addressMessage;
	private String addressStatus;
	private String addressType;
	private String addressTypeId;
	private String aptNum;
	private String city;
	private String country;
	private String county;
	private String countyId;
	private String currentAddressInd;
	private String keymap;
	private String latitude;
	private String longitude;
	private String state;
	private String stateId;
	private String streetName;
	private String streetNum;
	private String streetType;
	private String streetTypeId;
	private String zipCode;
	private boolean deletable;
	private String validated;
	

	/**
	 * @return
	 */
	public String getAdditionalZipCode()
	{
		return additionalZipCode;
	}

	/**
	 * @return
	 */
	public String getAddress2()
	{
		return address2;
	}
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
	public String getAddressType()
	{
		return addressType;
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
	public String getCountry()
	{
		return country;
	}

	/**
	 * @return
	 */
	public String getCurrentAddressInd()
	{
		return currentAddressInd;
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
	public String getLatitude()
	{
		return latitude;
	}

	/**
	 * @return
	 */
	public String getLongitude()
	{
		return longitude;
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
	public void setAdditionalZipCode(String string)
	{
		additionalZipCode = string;
	}

	/**
	 * @param address2
	 */
	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}

	/**
	 * @param addressId
	 */
	public void setAddressId(String addressId)
	{
		this.addressId = addressId;
	}

	/**
	 * @param addressType
	 */
	public void setAddressType(String addressType)
	{
		this.addressType = addressType;
	}

	/**
	 * @param city
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * @param country
	 */
	public void setCountry(String country)
	{
		this.country = country;
	}

	/**
	 * @param currentAddressInd
	 */
	public void setCurrentAddressInd(String currentAddressInd)
	{
		this.currentAddressInd = currentAddressInd;
	}

	/**
	 * @param keymap
	 */
	public void setKeymap(String keymap)
	{
		this.keymap = keymap;
	}

	/**
	 * @param latitude
	 */
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}

	/**
	 * @param longitude
	 */
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	/**
	 * @param state
	 */
	public void setState(String state)
	{
		this.state = state;
	}

	/**
	 * @param streetName
	 */
	public void setStreetName(String streetName)
	{
		this.streetName = streetName;
	}

	/**
	 * @param streetNum
	 */
	public void setStreetNum(String streetNum)
	{
		this.streetNum = streetNum;
	}

	/**
	 * @param streetType
	 */
	public void setStreetType(String streetType)
	{
		this.streetType = streetType;
	}

	/**
	 * @param zipCode
	 */
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	/**
	 * @return
	 */
	public String getAptNum()
	{
		return aptNum;
	}

	/**
	 * @param string
	 */
	public void setAptNum(String aptNum)
	{
		this.aptNum = aptNum;
	}

	/**
	 * @return
	 */
	public String getCounty()
	{
		return county;
	}

	/**
	 * @return
	 */
	public String getCountyId()
	{
		return countyId;
	}

	/**
	 * @param string
	 */
	public void setCounty(String county)
	{
		this.county = county;
	}

	/**
	 * @param string
	 */
	public void setCountyId(String countyId)
	{
		this.countyId = countyId;
	}

	/**
	 * @return
	 */
	public String getAddressTypeId()
	{
		return addressTypeId;
	}

	/**
	 * @return
	 */
	public String getStateId()
	{
		return stateId;
	}

	/**
	 * @return
	 */
	public String getStreetTypeId()
	{
		return streetTypeId;
	}

	/**
	 * @param string
	 */
	public void setAddressTypeId(String string)
	{
		addressTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setStateId(String string)
	{
		stateId = string;
	}

	/**
	 * @param string
	 */
	public void setStreetTypeId(String string)
	{
		streetTypeId = string;
	}

	/**
	 * @return
	 */
	public boolean isDeletable()
	{
		return deletable;
	}

	/**
	 * @param b
	 */
	public void setDeletable(boolean b)
	{
		deletable = b;
	}

	/**
	 * @return
	 */
	public String getAddressStatus()
	{
		return addressStatus;
	}

	/**
	 * @param string
	 */
	public void setAddressStatus(String string)
	{
		addressStatus = string;
	}

	/**
	 * @return
	 */
	public String getAddressMessage()
	{
		return addressMessage;
	}

	/**
	 * @param string
	 */
	public void setAddressMessage(String string)
	{
		addressMessage = string;
	}

	public String getValidated() {
		return validated;
	}

	public void setValidated(String aValidated) {
		this.validated = aValidated;
	}
	

}