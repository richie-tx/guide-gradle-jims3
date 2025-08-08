package messaging.administerprogramreferrals.reply;

import java.util.Date;
import java.util.List;

import mojo.km.messaging.ResponseEvent;

public class AppointmentDetailsResponseEvent extends ResponseEvent
{
	private String serviceProviderName;
	private String serviceProviderPhone;
	private String serviceProviderURL;
	
	private Date scheduleDateTime;
	
	private String streetNumber;
	private String streetName;
	private String streetType;
	private String aptNum;
	private String city;
	private String state;
	private String zipCode;
	private String locationPhone;
	private String locationFax;
	
	private String programName;
	private List contactsList; //only admin contacts
	private String officeHours;
	
	
	
	/**
	 * @return the serviceProviderName
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	/**
	 * @param serviceProviderName the serviceProviderName to set
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
	/**
	 * @return the serviceProviderPhone
	 */
	public String getServiceProviderPhone() {
		return serviceProviderPhone;
	}
	/**
	 * @param serviceProviderPhone the serviceProviderPhone to set
	 */
	public void setServiceProviderPhone(String serviceProviderPhone) {
		this.serviceProviderPhone = serviceProviderPhone;
	}
	/**
	 * @return the serviceProviderURL
	 */
	public String getServiceProviderURL() {
		return serviceProviderURL;
	}
	/**
	 * @param serviceProviderURL the serviceProviderURL to set
	 */
	public void setServiceProviderURL(String serviceProviderURL) {
		this.serviceProviderURL = serviceProviderURL;
	}
	/**
	 * @return the scheduleDateTime
	 */
	public Date getScheduleDateTime() {
		return scheduleDateTime;
	}
	/**
	 * @param scheduleDateTime the scheduleDateTime to set
	 */
	public void setScheduleDateTime(Date scheduleDateTime) {
		this.scheduleDateTime = scheduleDateTime;
	}
	/**
	 * @return the streetNumber
	 */
	public String getStreetNumber() {
		return streetNumber;
	}
	/**
	 * @param streetNumber the streetNumber to set
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}
	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	/**
	 * @return the streetType
	 */
	public String getStreetType() {
		return streetType;
	}
	/**
	 * @param streetType the streetType to set
	 */
	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}
	/**
	 * @return the aptNum
	 */
	public String getAptNum() {
		return aptNum;
	}
	/**
	 * @param aptNum the aptNum to set
	 */
	public void setAptNum(String aptNum) {
		this.aptNum = aptNum;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the locationPhone
	 */
	public String getLocationPhone() {
		return locationPhone;
	}
	/**
	 * @param locationPhone the locationPhone to set
	 */
	public void setLocationPhone(String locationPhone) {
		this.locationPhone = locationPhone;
	}
	/**
	 * @return the locationFax
	 */
	public String getLocationFax() {
		return locationFax;
	}
	/**
	 * @param locationFax the locationFax to set
	 */
	public void setLocationFax(String locationFax) {
		this.locationFax = locationFax;
	}
	/**
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
	}
	/**
	 * @param programName the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	/**
	 * @return the contactsList
	 */
	public List getContactsList() {
		return contactsList;
	}
	/**
	 * @param contactsList the contactsList to set
	 */
	public void setContactsList(List contactsList) {
		this.contactsList = contactsList;
	}
	/**
	 * @return the officeHours
	 */
	public String getOfficeHours() {
		return officeHours;
	}
	/**
	 * @param officeHours the officeHours to set
	 */
	public void setOfficeHours(String officeHours) {
		this.officeHours = officeHours;
	}
}
