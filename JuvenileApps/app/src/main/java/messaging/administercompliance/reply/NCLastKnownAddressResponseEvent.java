/*
 * Created on April 11, 2008
 */
package messaging.administercompliance.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class NCLastKnownAddressResponseEvent extends ResponseEvent 
{
    private Date lastContactDate;
    private String addressType;
    private String addressTypeId;
    private String streetNumber;
    private String streetName;
    private String city;
    private String state;
    private String stateId;
    private String zip;
    private String aptNumber;
    

    public String getAddressTypeId() {
		return addressTypeId;
	}
	public void setAddressTypeId(String addressTypeId) {
		this.addressTypeId = addressTypeId;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	
	/**
	 * @return Returns the aptNumber.
	 */
	public String getAptNumber() {
		return aptNumber;
	}
	/**
	 * @param aptNumber The aptNumber to set.
	 */
	public void setAptNumber(String aptNumber) {
		this.aptNumber = aptNumber;
	}
    /**
	 * @return Returns the addressType.
	 */
	public String getAddressType() {
		return addressType;
	}
	/**
	 * @param addressType The addressType to set.
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return Returns the lastContactDate.
	 */
	public Date getLastContactDate() {
		return lastContactDate;
	}
	/**
	 * @param lastContactDate The lastContactDate to set.
	 */
	public void setLastContactDate(Date lastContactDate) {
		this.lastContactDate = lastContactDate;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return Returns the streetName.
	 */
	public String getStreetName() {
		return streetName;
	}
	/**
	 * @param streetName The streetName to set.
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	/**
	 * @return Returns the streetNumber.
	 */
	public String getStreetNumber() {
		return streetNumber;
	}
	/**
	 * @param streetNumber The streetNumber to set.
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	/**
	 * @return Returns the zip.
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * @param zip The zip to set.
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
 }
