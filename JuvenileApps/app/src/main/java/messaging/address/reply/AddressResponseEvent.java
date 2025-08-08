/*
 * Created on Oct 19, 2004
 */
package messaging.address.reply;

import java.util.Date;

import messaging.contact.domintf.IAddress;
import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 */
public class AddressResponseEvent extends ResponseEvent implements IAddress, Comparable
{
	private String address2;
	private String addressMessage;
	private String addressStatus;
	private String addressType;
	private String addressTypeId;
	private String city;
	private String state;
	private String stateId;
	private String streetName;
	private String streetNum;
	private String streetNumSuffixId;
	private String streetNumSuffix;
	private String zipCode;
	private String streetTypeId;
	private String streetType;
	private String additionalZipCode;
	private String aptNum;
	private String addressId;
	private String country;
	private String countryId;
	private String county;
	private String countyId;
	private String currentAddressInd;
	private String keymap;
	private String latitude;
	private String longitude;
	private Date createDate;
	private String validated;

	public AddressResponseEvent() {
		address2 = "";
		addressMessage = "";
		addressStatus = "";
		addressType = "";
		addressTypeId = "";
		city = "";
		state = "";
		stateId = "";
		streetName = "";
		streetNum = "";
		zipCode = "";
		streetTypeId = "";
		streetType = "";
		streetNumSuffixId = "";
		streetNumSuffix = "";
		additionalZipCode = "";
		aptNum = "";
		addressId = "";
		country = "";
		countryId = "";
		county = "";
		countyId = "";
		currentAddressInd = "";
		keymap = "";
		latitude = "";
		longitude = "";
		createDate = new Date();
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
	public String getAddressType()
	{
		return addressType;
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
	public String getCity()
	{
		return city;
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
	public String getStateId()
	{
		return stateId;
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
	public String getStreetNumSuffix()
	{
		return streetNumSuffix;
	}
	/**
	 * @return
	 */
	public String getStreetNumSuffixId()
	{
		return streetNumSuffixId;
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
	public void setAddress2(String string)
	{
		address2 = string;
	}

	/**
	 * @param string
	 */
	public void setAddressType(String string)
	{
		addressType = string;
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
	public void setCity(String string)
	{
		city = string;
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
	public void setStateId(String string)
	{
		stateId = string;
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
	public void setStreetNumSuffix(String string)
	{
		streetNumSuffix = string;
	}
	/*
	 * @param string
	 */
	public void setStreetNumSuffixId(String string)
	{
		streetNumSuffixId = string;
	}

	/**
	 * @param string
	 */
	public void setZipCode(String string)
	{
		zipCode = string;
	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		if (streetNum != null && !streetNum.equals(""))
		{
			sb.append(streetNum);
			if (streetNumSuffix != null && !streetNumSuffix.equals("")){
				sb.append(" ");
				sb.append(streetNumSuffix);
			}
		}

		if (streetName != null && !streetName.equals(""))
		{
		    sb.append(" ");
			sb.append(streetName);
		}
		if (aptNum != null && !aptNum.equals(""))
		{
		    sb.append(" ");
			sb.append(aptNum);
		}
		if (city != null && !city.equals(""))
		{
		    sb.append(", ");
			sb.append(city);
		}
		if (state != null && !state.equals(""))
		{
		    sb.append(" ");
			sb.append(state);
		}
		if (zipCode != null && !zipCode.equals(""))
		{
		    sb.append(" ");
			sb.append(zipCode);
		}
		return sb.toString();
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
	public void setStreetTypeId(String string)
	{
		streetTypeId = string;
	}

	/**
	 * @return
	 */
	public String getAdditionalZipCode()
	{
		return additionalZipCode;
	}

	/**
	 * @param string
	 */
	public void setAdditionalZipCode(String string)
	{
		additionalZipCode = string;
	}

	/**
	 * @return
	 */
	public String getStreetType()
	{
		return streetType;
	}

	/**
	 * @param string
	 */
	public void setStreetType(String string)
	{
		streetType = string;
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
	public void setAptNum(String string)
	{
		aptNum = string;
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
	public String getCountry()
	{
		return country;
	}

	/**
	 * @return
	 */
	public String getCountryId()
	{
		return countryId;
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
	 * @param string
	 */
	public void setAddressId(String string)
	{
		addressId = string;
	}

	/**
	 * @param string
	 */
	public void setCountry(String string)
	{
		country = string;
	}

	/**
	 * @param string
	 */
	public void setCountryId(String string)
	{
		countryId = string;
	}

	/**
	 * @param string
	 */
	public void setCounty(String string)
	{
		county = string;
	}

	/**
	 * @param string
	 */
	public void setCountyId(String string)
	{
		countyId = string;
	}

	/**
	 * @param string
	 */
	public void setCurrentAddressInd(String string)
	{
		currentAddressInd = string;
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
	public void setLatitude(String string)
	{
		latitude = string;
	}

	/**
	 * @param string
	 */
	public void setLongitude(String string)
	{
		longitude = string;
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

	public String getAddressTypeCode()
	{
		return this.addressTypeId;
	}

	public String getCountryCode()
	{
		return this.countryId;
	}

	public String getCountyCode()
	{
		return this.countyId;
	}

	public String getStateCode()
	{
		return this.stateId;
	}

	public String getStreetTypeCode()
	{
		return this.streetTypeId;
	}

	public void setAddressTypeCode(String string)
	{
		this.addressTypeId = string;
	}

	public void setCountryCode(String string)
	{
		this.countryId = string;
	}

	public void setCountyCode(String string)
	{
		this.countyId = string;
	}

	public void setStateCode(String string)
	{
		this.stateId = string;
	}

	public void setStreetTypeCode(String string)
	{
		this.streetTypeId = string;
	}

	public String getStreetSuffixCode() {
		
		return this.streetNumSuffixId;
	}

	public void setStreetSuffixCode(String string) {
		
		this.streetNumSuffixId = string;
	}

	/**
	 * @return
	 */
	public Date getCreateDate()
	{
		return createDate;
	}

	/**
	 * @param date
	 */
	public void setCreateDate(Date date)
	{
		createDate = date;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		if(obj==null)
			return -1;
		AddressResponseEvent evt = (AddressResponseEvent) obj;
		int result = 0;
		if(getCreateDate() != null && evt.getCreateDate() != null )
		{
			try{
				if(this.getCreateDate()!=null || evt.getCreateDate()!=null){
					if(evt.getCreateDate()==null)
						return 1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
					if(this.getCreateDate()==null)
						return -1; // this makes any null objects go to the bottom change this to 1 if you want the top of the list
					
					if(this.getCreateDate()==null)
						return -1;
					if(evt.getCreateDate()==null)
						return 1;
					result= evt.getCreateDate().compareTo(this.getCreateDate()); // backwards in order to get list to show up most recent first
				}
			}
			catch(NumberFormatException e){
				result = 0;
			}
		}
		return result;
	}
	public String getValidated() {
		return validated;
	}

	public void setValidated(String string) {
		this.validated = string;
	}

	public String getStreetNumSuffixCode() {
		return this.streetNumSuffixId;
	}

	public void setStreetNumSuffixCode(String string) {
		this.streetNumSuffixId = string;
		
	}


}