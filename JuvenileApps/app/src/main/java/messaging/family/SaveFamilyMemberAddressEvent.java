/*
 * Created on Oct 3, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import mojo.km.messaging.RequestEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveFamilyMemberAddressEvent extends RequestEvent
{

	private String 	addressId;
	private String 	streetNumber;
	private String 	streetName;
	private String 	streetNumSuffixId;
	private String 	streetTypeId;
	private String 	aptNum;
	private String 	city;
	private String 	stateId;
	private String 	zip;
	private String 	additionalZip; 
	private String 	addressTypeId;
	private String 	countyId;	
	private String  validated;
	
	/**
	 * @return
	 */
	public String getAdditionalZip()
	{
		return additionalZip;
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
	public String getAddressTypeId()
	{
		return addressTypeId;
	}

	/**
	 * @return
	 */
	public String getAptNum()
	{
		return aptNum;
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
	public String getCountyId()
	{
		return countyId;
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
	public String getStreetNumber()
	{
		return streetNumber;
	}

	/**
	 * @return
	 */
	public String getStreetTypeId()
	{
		return streetTypeId;
	}

	/**
	 * @return
	 */
	public String getZip()
	{
		return zip;
	}

	/**
	 * @param string
	 */
	public void setAdditionalZip(String string)
	{
		additionalZip = string;
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
	public void setAddressTypeId(String string)
	{
		addressTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setAptNum(String string)
	{
		aptNum = string;
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
	public void setCountyId(String string)
	{
		countyId = string;
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
	public void setStreetNumber(String string)
	{
		streetNumber = string;
	}

	/**
	 * @param string
	 */
	public void setStreetTypeId(String string)
	{
		streetTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setZip(String string)
	{
		zip = string;
	}

	public String getValidated() {
		return validated;
	}

	public void setValidated(String validated) {
		this.validated = validated;
	}

	public String getStreetNumSuffixId() {
		return streetNumSuffixId;
	}

	public void setStreetNumSuffixId(String streetNumSuffixId) {
		this.streetNumSuffixId = streetNumSuffixId;
	}


}
