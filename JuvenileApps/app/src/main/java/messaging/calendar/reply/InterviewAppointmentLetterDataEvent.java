/*
 * Created on Mar 29, 2007
 *
 *
 */
package messaging.calendar.reply;

import java.util.List;

import messaging.contact.to.Address;



/**
 * @author C_NRaveendran
 *
 */
public class InterviewAppointmentLetterDataEvent {
	private String locationUnitName;
	private Address locationUnitAddress;
	private String memberRelationship;
	private String guardianFirstName;
	private String guardianLastName;
	private String guardianMiddleName;
	
	private Address guardianAddress;
		
	private String currentDate;
	
	private String juvenileLastName;
	private String juvenileMiddleName;
	private String juvenileFirstName;
	
	private String eventDate;
	private String eventTime;
	private String eventComments;
	
	private List inventoryDocuments;
	
	private String officerLastName;
	private String officerMiddleName;
	private String officerFirstName;
	private String officerPhone;

	private String managerLastName;
	private String managerMiddleName;
	private String managerFirstName;
	private String managerPhone;
		
	private String serviceLocationName;
	private String interviewLocationPhone;
	
	
	/**
	 * @return Returns the currentDate.
	 */
	public String getCurrentDate() {
		return currentDate;
	}
	/**
	 * @param currentDate The currentDate to set.
	 */
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	/**
	 * @return Returns the eventComments.
	 */
	public String getEventComments() {
		return eventComments;
	}
	/**
	 * @param eventComments The eventComments to set.
	 */
	public void setEventComments(String eventComments) {
		this.eventComments = eventComments;
	}
	/**
	 * @return Returns the eventDate.
	 */
	public String getEventDate() {
		return eventDate;
	}
	/**
	 * @param eventDate The eventDate to set.
	 */
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	
	/**
	 * @return Returns the guardianFirstName.
	 */
	public String getGuardianFirstName() {
		return guardianFirstName;
	}
	/**
	 * @param guardianFirstName The guardianFirstName to set.
	 */
	public void setGuardianFirstName(String guardianFirstName) {
		this.guardianFirstName = guardianFirstName;
	}
	/**
	 * @return Returns the guardianLastName.
	 */
	public String getGuardianLastName() {
		return guardianLastName;
	}
	/**
	 * @param guardianLastName The guardianLastName to set.
	 */
	public void setGuardianLastName(String guardianLastName) {
		this.guardianLastName = guardianLastName;
	}
	/**
	 * @return Returns the guardianMiddleName.
	 */
	public String getGuardianMiddleName() {
		return guardianMiddleName;
	}
	/**
	 * @param guardianMiddleName The guardianMiddleName to set.
	 */
	public void setGuardianMiddleName(String guardianMiddleName) {
		this.guardianMiddleName = guardianMiddleName;
	}

	/**
	 * @return Returns the juvenileFirstName.
	 */
	public String getJuvenileFirstName() {
		return juvenileFirstName;
	}
	/**
	 * @param juvenileFirstName The juvenileFirstName to set.
	 */
	public void setJuvenileFirstName(String juvenileFirstName) {
		this.juvenileFirstName = juvenileFirstName;
	}
	/**
	 * @return Returns the juvenileLastName.
	 */
	public String getJuvenileLastName() {
		return juvenileLastName;
	}
	/**
	 * @param juvenileLastName The juvenileLastName to set.
	 */
	public void setJuvenileLastName(String juvenileLastName) {
		this.juvenileLastName = juvenileLastName;
	}
	/**
	 * @return Returns the juvenileMiddleName.
	 */
	public String getJuvenileMiddleName() {
		return juvenileMiddleName;
	}
	/**
	 * @param juvenileMiddleName The juvenileMiddleName to set.
	 */
	public void setJuvenileMiddleName(String juvenileMiddleName) {
		this.juvenileMiddleName = juvenileMiddleName;
	}
	
	public Address getLocationUnitAddress() {
		return locationUnitAddress;
	}
	public void setLocationUnitAddress(Address locationUnitAddress) {
		this.locationUnitAddress = locationUnitAddress;
	}
	/**
	 * @return Returns the locationUnitName.
	 */
	public String getLocationUnitName() {
		return locationUnitName;
	}
	/**
	 * @param locationUnitName The locationUnitName to set.
	 */
	public void setLocationUnitName(String locationUnitName) {
		this.locationUnitName = locationUnitName;
	}
	/**
	 * @return Returns the managerFirstName.
	 */
	public String getManagerFirstName() {
		return managerFirstName;
	}
	/**
	 * @param managerFirstName The managerFirstName to set.
	 */
	public void setManagerFirstName(String managerFirstName) {
		this.managerFirstName = managerFirstName;
	}
	/**
	 * @return Returns the managerLastName.
	 */
	public String getManagerLastName() {
		return managerLastName;
	}
	/**
	 * @param managerLastName The managerLastName to set.
	 */
	public void setManagerLastName(String managerLastName) {
		this.managerLastName = managerLastName;
	}
	/**
	 * @return Returns the managerMiddleName.
	 */
	public String getManagerMiddleName() {
		return managerMiddleName;
	}
	/**
	 * @param managerMiddleName The managerMiddleName to set.
	 */
	public void setManagerMiddleName(String managerMiddleName) {
		this.managerMiddleName = managerMiddleName;
	}
	/**
	 * @return Returns the managerPhone.
	 */
	public String getManagerPhone() {
		return managerPhone;
	}
	/**
	 * @param managerPhone The managerPhone to set.
	 */
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	/**
	 * @return Returns the memberRelationship.
	 */
	public String getMemberRelationship() {
		return memberRelationship;
	}
	/**
	 * @param memberRelationship The memberRelationship to set.
	 */
	public void setMemberRelationship(String memberRelationship) {
		this.memberRelationship = memberRelationship;
	}
	/**
	 * @return Returns the officerFirstName.
	 */
	public String getOfficerFirstName() {
		return officerFirstName;
	}
	/**
	 * @param officerFirstName The officerFirstName to set.
	 */
	public void setOfficerFirstName(String officerFirstName) {
		this.officerFirstName = officerFirstName;
	}
	/**
	 * @return Returns the officerLastName.
	 */
	public String getOfficerLastName() {
		return officerLastName;
	}
	/**
	 * @param officerLastName The officerLastName to set.
	 */
	public void setOfficerLastName(String officerLastName) {
		this.officerLastName = officerLastName;
	}
	/**
	 * @return Returns the officerMiddleName.
	 */
	public String getOfficerMiddleName() {
		return officerMiddleName;
	}
	/**
	 * @param officerMiddleName The officerMiddleName to set.
	 */
	public void setOfficerMiddleName(String officerMiddleName) {
		this.officerMiddleName = officerMiddleName;
	}
	/**
	 * @return Returns the officerPhone.
	 */
	public String getOfficerPhone() {
		return officerPhone;
	}
	/**
	 * @param officerPhone The officerPhone to set.
	 */
	public void setOfficerPhone(String officerPhone) {
		this.officerPhone = officerPhone;
	}
	
	/**
	 * @return Returns the inventoryDocuments.
	 */
	public List getInventoryDocuments() {
		return inventoryDocuments;
	}
	/**
	 * @param inventoryDocuments The inventoryDocuments to set.
	 */
	public void setInventoryDocuments(List inventoryDocuments) {
		this.inventoryDocuments = inventoryDocuments;
	}
	
	public String getInterviewLocationPhone() {
		return interviewLocationPhone;
	}
	public void setInterviewLocationPhone(String interviewLocationPhone) {
		this.interviewLocationPhone = interviewLocationPhone;
	}
	/**
	 * @return Returns the serviceLocationName.
	 */
	public String getServiceLocationName() {
		return serviceLocationName;
	}
	/**
	 * @param serviceLocationName The serviceLocationName to set.
	 */
	public void setServiceLocationName(String serviceLocationName) {
		this.serviceLocationName = serviceLocationName;
	}
	/**
	 * @return Returns the eventTime.
	 */
	public String getEventTime() {
		return eventTime;
	}
	/**
	 * @param eventTime The eventTime to set.
	 */
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	
	
	public Address getGuardianAddress() {
		return guardianAddress;
	}
	public void setGuardianAddress(Address guardianAddress) {
		this.guardianAddress = guardianAddress;
	}
}
