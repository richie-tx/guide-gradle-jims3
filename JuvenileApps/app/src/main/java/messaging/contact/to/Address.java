package messaging.contact.to;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import messaging.contact.domintf.IAddress;

/**
 *
 */
public class Address implements IAddress {
	private String addressId;
	private String address2;
	private String additionalZipCode;
	private String addressStatus;
	private String addressTypeCode;
	private Date createDate;
	private String aptNum;
	private String city;
	private String country;
	private String countryCode;
	private String countyCode;
	private String currentAddressInd;
	private String keymap;
	private String latitude = "";
	private String longitude = "";
	private String state;
	private String stateCode;
	private String streetName;
	private String streetNum;
	private String streetNumSuffix;
	private String streetNumSuffixCode;
	private String streetType;
	private String streetTypeCode;
	private String validated;
	private String zipCode;

	/**
	 * @return
	 */
	public String getAdditionalZipCode() {

		if (additionalZipCode == null) {
			if (zipCode != null && zipCode.length() > 5) {
				additionalZipCode = zipCode.substring(5);
			}
		}
		return additionalZipCode;
	}

	/**
	 * @return
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @return
	 */
	public String getAddressId() {
		return addressId;
	}

	/**
	 * @return
	 */
	public String getAddressStatus() {
		return addressStatus;
	}

	/**
	 * @return
	 */
	public String getAddressTypeCode() {
		return addressTypeCode;
	}
	public String getStreetNumSuffixCode() {
		return streetNumSuffixCode;
	}

	/**
	 * @return
	 */
	public String getAptNum() {
		return aptNum;
	}

	/**
	 * @return
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @return
	 */
	public String getCountyCode() {
		return countyCode;
	}

	/**
	 * @return
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @return
	 */
	public String getCurrentAddressInd() {
		return currentAddressInd;
	}

	/**
	 * @return
	 */
	public String getKeymap() {
		return keymap;
	}

	/**
	 * @return
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @return
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @return
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * @return
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @return
	 */
	public String getStreetNum() {
		return streetNum;
	}

	/**
	 * @return
	 */
	public String getStreetTypeCode() {
		return streetTypeCode;
	}

	/**
	 * @return
	 */
	public String getZipCode() {
		String s = "";
		if (zipCode != null && zipCode.length() >= 5) {
			s = zipCode.substring(0, 5);
		}
		return s;
	}

	/**
	 * @return String
	 */
	public String getCompleteZipCode() {
		StringBuffer zip = new StringBuffer();
		zip.append(getZipCode());
		if ( StringUtils.isNotEmpty(getAdditionalZipCode()) ) {
			zip.append("-");
			zip.append(getAdditionalZipCode());
		}
		return zip.toString();		
	}
	
	/**
	 * @return
	 */
	public String getFullZipCode() {
		return zipCode;
	}

	/**
	 * @param string
	 */
	public void setAdditionalZipCode(String string) {
		additionalZipCode = string;
	}

	/**
	 * @param string
	 */
	public void setAddress2(String string) {
		address2 = string;
	}

	/**
	 * @param string
	 */
	public void setAddressId(String string) {
		addressId = string;
	}

	/**
	 * @param string
	 */
	public void setAddressStatus(String string) {
		addressStatus = string;
	}

	/**
	 * @param string
	 */
	public void setAddressTypeCode(String string) {
		addressTypeCode = string;
	}

	/**
	 * @param string
	 */
	public void setAptNum(String string) {
		aptNum = string;
	}

	/**
	 * @param string
	 */
	public void setCity(String string) {
		city = string;
	}

	/**
	 * @param string
	 */
	public void setCountry(String string) {
		country = string;
	}

	/**
	 * @param string
	 */
	public void setCountryCode(String string) {
		countryCode = string;
	}

	/**
	 * @param string
	 */
	public void setCountyCode(String string) {
		countyCode = string;
	}

	/**
	 * @param string
	 */
	public void setCreateDate(Date aCreateDate) {
		createDate = aCreateDate;
	}

	/**
	 * @param string
	 */
	public void setCurrentAddressInd(String string) {
		currentAddressInd = string;
	}

	/**
	 * @param string
	 */
	public void setKeymap(String string) {
		keymap = string;
	}

	/**
	 * Precondition: Attempting to set latitude with an invalid value will
	 * result in it being set to '0' without an exception thrown.
	 * 
	 * @param string
	 */
	public void setLatitude(String string) {
		try {
			Double.parseDouble(string);
			latitude = string;
		} catch (Exception ex) {
			// Not a number
			latitude = "0";
		}
	}

	/**
	 * Precondition: Attempting to set longitude with an invalid value will
	 * result in it being set to '0' without an exception thrown.
	 * 
	 * @param string
	 */
	public void setLongitude(String string) {
		try {
			Double.parseDouble(string);
			longitude = string;
		} catch (Exception ex) {
			// Not a number
			longitude = "0";
		}
	}

	/**
	 * @param string
	 */
	public void setStateCode(String string) {
		stateCode = string;
	}

	/**
	 * @param string
	 */
	public void setStreetName(String string) {
		streetName = string;
	}

	/**
	 * @param string
	 */
	public void setStreetNum(String string) {
		streetNum = string;
	}

	/**
	 * @param string
	 */
	public void setStreetTypeCode(String string) {
		streetTypeCode = string;
	}

	public void setStreetNumSuffixCode(String string) {
		streetNumSuffixCode = string;
	}

	/**
	 * @param string
	 */
	public void setZipCode(String string) {
		zipCode = string;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreetType() {
		return streetType;
	}

	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}
	public void setStreetNumSuffix(String streetSuffix) {
		this.streetNumSuffix = streetSuffix;
	}

	public String getFormattedAddress() {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotEmpty(getStreetAddress())) {
			sb.append(getStreetAddress());
		} else if (StringUtils.isNotEmpty(getNewStreetAddress())) {
			sb.append(getNewStreetAddress());
		}
		if (StringUtils.isNotEmpty(getCityStateZip())) {
			sb.append(", ");
			sb.append(getCityStateZip());
			
		}
		return  sb.toString();

	}

	public String getStreetAddress() {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotEmpty(streetNum)) {
			sb.append(streetNum);
			sb.append(" ");
		}
		if (StringUtils.isNotEmpty(streetNumSuffix)) {
			sb.append(streetNumSuffix);
			sb.append(" ");
		}
		if (StringUtils.isNotEmpty(streetName)) {
			sb.append(streetName);
			sb.append(" ");
		}
		if (StringUtils.isNotEmpty(streetType)) {
			sb.append(streetType);
		}

		if (StringUtils.isNotEmpty(aptNum)) {
			sb.append(" Apt/Suite ");
			sb.append(aptNum);
		}
		return sb.toString().toUpperCase();

	}

	public String getNewStreetAddress() {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotEmpty(streetNum)) {
			sb.append(streetNum);
			sb.append(" ");
		}
		if (StringUtils.isNotEmpty(streetNumSuffix)) {
			sb.append(streetNumSuffix);
			sb.append(" ");
		}
		if (StringUtils.isNotEmpty(streetName)) {
			sb.append(streetName);
			sb.append(" ");
		}
		if (StringUtils.isNotEmpty(streetType)) {
			sb.append(streetType);
		}
		if (StringUtils.isNotEmpty(aptNum)) {
			sb.append(" Apt/Suite ");
			sb.append(aptNum);
		}
		return sb.toString().toUpperCase();

	}

	public String getCityStateZip() {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotEmpty(city)) {
			sb.append(city);
		}

		if (StringUtils.isNotEmpty(state)) {
			if (StringUtils.isNotEmpty(city)) {
				sb.append(", ");
			}
			sb.append(state);
		} else if(StringUtils.isNotEmpty(stateCode)) {
			if (StringUtils.isNotEmpty(city)) {
				sb.append(", ");
			}
			sb.append(stateCode);
		}

		if (StringUtils.isNotEmpty(zipCode)) {
			sb.append(" ");
			sb.append(zipCode);
		}

		if (StringUtils.isNotEmpty(additionalZipCode)) {
			sb.append("-");
			sb.append(additionalZipCode);
		}
		return sb.toString().toUpperCase();
	}

	// These are just methods of different names but get/set the same variables
	public String getAptNumber() {
		return this.aptNum;
	}

	public void setAptNumber(String aptNumber) {
		this.aptNum = aptNumber;
	}

	public String getStreetNumber() {
		return this.streetNum;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNum = streetNumber;
	}

	public String getStreetTypeId() {
		return this.getStreetTypeCode();
	}

	public void setStreetTypeId(String streetTypeId) {
		this.streetTypeCode = streetTypeId;
	}

	public String getStateId() {
		return this.getStateCode();
	}

	public void setStateId(String stateId) {
		this.stateCode = stateId;
	}

	public String getCountyId() {
		return this.getCountyCode();
	}

	public void setCountyId(String countyId) {
		this.countyCode = countyId;
	}

	public String getValidated() {
		
		return validated;
	}

	public void setValidated(String aValidated) {
		
		this.validated = aValidated;
	}

	public String getStreetNumSuffix() {
		return this.streetNumSuffix;
	}
}
