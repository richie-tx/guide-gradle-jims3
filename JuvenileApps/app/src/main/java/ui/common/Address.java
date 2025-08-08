/*
 * Created on Oct 13, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common;

import java.util.Date;

import messaging.contact.domintf.IAddress;

/**
 * @author dapte
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Address implements IAddress
{
	private String addressId;
	private String addressStatus;
	private String country;
	private String countryId;
	private String currentAddressInd;
	private String latitude;
	private String longitude;
	private String address2;
	private String addressType;
	private String addressTypeId;
	private String city;
	private String state;
	private String stateId;
	private String streetName;
	private String streetNumber;
	private String zipCode;		
	private String streetTypeId;
	private String streetType;
	private String streetNumSuffixId;
	private String streetNumSuffix;
	private String additionalZipCode;
	private String aptNumber;
	private String county;
	private String countyId;
	private boolean update=false;
	private boolean delete=false;
	private boolean dirty=false;
	private Date createDate;
	private String validated;
	
	public Address() {
		addressId = "";
		addressStatus = "";
		country = "";
		countryId = "";
		currentAddressInd = "";
		latitude = "";
		longitude = "";
		address2 = "";
		addressType = "";
		addressTypeId = "";
		city = "";
		state = "";
		stateId = "";
		streetName = "";
		streetNumber = "";
		streetNumSuffix = "";
		streetNumSuffixId = "";
		zipCode = "";
		streetTypeId = "";
		streetType = "";
		additionalZipCode = "";
		aptNumber = "";
		county = "";
		countyId = "";
		
	}
	
	/**
	 * 
	 */
	public void clear() {
		streetNumber = "";
		streetNumSuffix = "";
		streetNumSuffixId = "";
		streetName = "";
		streetType = "";
		aptNumber = "";
		city = "";
		state = "";
		zipCode = "";
		additionalZipCode = "";
		addressType = "";
		county="";
		countyId="";
		addressTypeId="";
		streetTypeId="";
		stateId="";
		address2="";
	}
	
	/**
	 * 
	 */
	public void reset() {
		clear();
	}

	/**
	 * @return String
	 */
	public String getAdditionalZipCode()
	{
		return additionalZipCode;
	}

	/**
	 * @return String
	 */
	public String getAddressType()
	{
		return addressType;
	}

	/**
	 * @return String
	 */
	public String getAptNumber()
	{
		return aptNumber;
	}

	/**
	 * @return String
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * @return String
	 */
	public String getCompleteZipCode() {
		//StringBuffer zip = new StringBuffer(zipCode).append(additionalZipCode);
		String zip = null; 
		
		if(this.zipCode != null && this.additionalZipCode != null && !this.additionalZipCode.equals("")){
		    zip = this.zipCode + "-" + this.additionalZipCode;
		} else if(this.zipCode != null && this.additionalZipCode == null){
		    zip = this.zipCode;
		} else {
		    zip = this.zipCode;
		}
		
		return zip;
		
	}
	/**
	 * @return String
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * @return String
	 */
	public String getStreetName()
	{
		return streetName;
	}

	/** 
	 * @return String
	 */
	public String getStreetNumber()
	{
		return streetNumber;
	}
	/** 
	 * @return String
	 */
	public String getStreetNumSuffix()
	{
		return streetNumSuffix;
	}

	/**
	 * @return String
	 */
	public String getStreetType()
	{
		return streetType;
	}

	/**
	 * @return String
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
	 * @param string
	 */
	public void setAddressType(String string)
	{
		addressType = string;
	}

	/**
	 * @param string
	 */
	public void setAptNumber(String string)
	{
		aptNumber = string;
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
	public void setStreetName(String string)
	{
		streetName = string;
	}

	/**
	 * @param string
	 */
	public void setStreetNumber(String string)
	{
		streetNumber = string;
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
	public void setStreetNumSuffix(String string)
	{
		streetNumSuffix = string;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#setStreetNumSuffixCode(java.lang.String)
	 */
	public void setStreetNumSuffixCode(String string) {
		setStreetNumSuffixId(string);
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
	public String getAddress2()
	{
		return address2;
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
	public void setAddress2(String string)
	{
		address2 = string;
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
	 * @return
	 */
	public boolean isDelete()
	{
		return delete;
	}

	/**
	 * @return
	 */
	public boolean isDirty()
	{
		return dirty;
	}

	/**
	 * @return
	 */
	public boolean isUpdate()
	{
		return update;
	}

	/**
	 * @param b
	 */
	public void setDelete(boolean b)
	{
		delete = b;
	}

	/**
	 * @param b
	 */
	public void setDirty(boolean b)
	{
		dirty = b;
	}

	/**
	 * @param b
	 */
	public void setUpdate(boolean b)
	{
		update = b;
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
	 * @see messaging.contact.domintf.IAddress#getAddressId()
	 */
	public String getAddressId() {
		return addressId;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#getAddressStatus()
	 */
	public String getAddressStatus() {
		return addressStatus;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#getAddressTypeCode()
	 */
	public String getAddressTypeCode() {
		return addressTypeId;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#getAptNum()
	 */
	public String getAptNum() {
		return aptNumber;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#getCountry()
	 */
	public String getCountry() {
		return country;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#getCountryCode()
	 */
	public String getCountryCode() {
		return countryId;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#getCountyCode()
	 */
	public String getCountyCode() {
		return countyId;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#getCurrentAddressInd()
	 */
	public String getCurrentAddressInd() {
		return currentAddressInd;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#getKeymap()
	 */
	public String getKeymap() {
		return null;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#getLatitude()
	 */
	public String getLatitude() {
		return latitude;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#getLongitude()
	 */
	public String getLongitude() {
		return longitude;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#getStateCode()
	 */
	public String getStateCode() {
		return stateId;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#getStreetNum()
	 */
	public String getStreetNum() {
		return streetNumber;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#getStreetTypeCode()
	 */
	public String getStreetTypeCode() {
		return streetTypeId;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#setAddressId(java.lang.String)
	 */
	public void setAddressId(String string) {
		addressId=string;

	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#setAddressStatus(java.lang.String)
	 */
	public void setAddressStatus(String string) {
		addressStatus=string;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#setAddressTypeCode(java.lang.String)
	 */
	public void setAddressTypeCode(String string) {
		setAddressTypeId(string);
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#setAptNum(java.lang.String)
	 */
	public void setAptNum(String string) {
		setAptNumber(string);
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#setCountryCode(java.lang.String)
	 */
	public void setCountryCode(String string) {
		countryId=string;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#setCountyCode(java.lang.String)
	 */
	public void setCountyCode(String string) {
		setCountyId(string);
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#setCurrentAddressInd(java.lang.String)
	 */
	public void setCurrentAddressInd(String string) {
		currentAddressInd=string;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#setKeymap(java.lang.String)
	 */
	public void setKeymap(String string) {
		
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#setLatitude(java.lang.String)
	 */
	public void setLatitude(String string) {
		latitude=string;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#setLongitude(java.lang.String)
	 */
	public void setLongitude(String string) {
		longitude=string;
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#setStateCode(java.lang.String)
	 */
	public void setStateCode(String string) {
		setStateId(string);
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#setStreetNum(java.lang.String)
	 */
	public void setStreetNum(String string) {
		setStreetNumber(string);
	}
	/* (non-Javadoc)
	 * @see messaging.contact.domintf.IAddress#setStreetTypeCode(java.lang.String)
	 */
	public void setStreetTypeCode(String string) {
		setStreetTypeId(string);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getStreetNumSuffixId() {
		return streetNumSuffixId;
	}

	/**
	 * 
	 * @param streetNumSuffix
	 */
	public void setStreetNumSuffixId(String streetNumSuffix) {
		this.streetNumSuffixId = streetNumSuffix;
	}

	public String getFormattedAddress()
	{
		return getStreetAddress() + ", " + getCityStateZip();
		
	}
	
	public String getStreetAddress()
	{
		StringBuffer sb = new StringBuffer();
		if(streetNumber != null && streetNumber.length() > 0)
		{	
			sb.append(streetNumber);
			sb.append(" ");
			if (streetNumSuffix != null && streetNumSuffix.length() > 0){
				sb.append(streetNumSuffix);
				sb.append(" ");
			}
		}
		if(streetName != null && streetName.length() > 0)
		{	
			sb.append(streetName);
		}		
		//JIMS200056934 : MJCW - Family Member Address Street Type is missing
		if(streetType != null && streetType.length() > 0)
		{	
			sb.append(" ");
			sb.append(streetType);
		}		
		if(aptNumber != null && aptNumber.length() > 0)
		{
			sb.append(" Apt/Suite ");
			sb.append(aptNumber);
		}
		return sb.toString();
		
	}
	
	public String getStreetAddressForReport()
	{
		StringBuffer sb = new StringBuffer();
		if(streetNumber != null && streetNumber.length() > 0)
		{	
			sb.append(streetNumber);
			sb.append(" ");
			if (streetNumSuffix != null && streetNumSuffix.length() > 0){
				sb.append(streetNumSuffix);
				sb.append(" ");
			}
		}
		if(streetName != null && streetName.length() > 0)
		{	
			sb.append(streetName);
		}	
		
		sb.append("<br/>");
		
		//JIMS200056934 : MJCW - Family Member Address Street Type is missing
		if(streetType != null && streetType.length() > 0)
		{	
			sb.append(" ");
			sb.append(streetType);
		}		
		if(aptNumber != null && aptNumber.length() > 0)
		{
			sb.append(" Apt/Suite ");
			sb.append(aptNumber);
		}
		
		sb.append("<br/>");
		
		if(city!=null && city.length()>0) {		    
		    sb.append(city);
		}
		
		if(state!=null && state.length()>0) {
			sb.append(", ");
			sb.append(state);	
		}
		
		if(zipCode!=null && zipCode.length()>0) {
			sb.append(" ");
			sb.append(zipCode);
		}
		
		if(additionalZipCode != null && additionalZipCode.length() > 0) {
			sb.append("-");
			sb.append(additionalZipCode);
		}
		
		return sb.toString();
		
	}
	
	
	public String getCityStateZip()
	{
		StringBuffer sb = new StringBuffer();
		if(city!=null && city.length()>0) {
			sb.append(city);
		}
		
		if(state!=null && state.length()>0) {
			sb.append(", ");
			sb.append(state);	
		}
		
		if(zipCode!=null && zipCode.length()>0) {
			sb.append(" ");
			sb.append(zipCode);
		}
		
		if(additionalZipCode != null && additionalZipCode.length() > 0) {
			sb.append("-");
			sb.append(additionalZipCode);
		}
		return sb.toString();
	}
	public String getValidated() {
		return validated;
	}
	public void setValidated(String aValidated) {
				
		this.validated = aValidated;
	}

	public String getStreetNumSuffixCode() {
		return this.streetNumSuffixId;
	}
}
