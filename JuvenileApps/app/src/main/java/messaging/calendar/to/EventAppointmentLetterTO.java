package messaging.calendar.to;

import java.util.List;

import messaging.contact.to.Address;

public class EventAppointmentLetterTO 
{
	private String Name;
	
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

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getLocationUnitName() {
		return locationUnitName;
	}

	public void setLocationUnitName(String locationUnitName) {
		this.locationUnitName = locationUnitName;
	}

	public Address getLocationUnitAddress() {
		return locationUnitAddress;
	}

	public void setLocationUnitAddress(Address locationUnitAddress) {
		this.locationUnitAddress = locationUnitAddress;
	}

	public String getMemberRelationship() {
		return memberRelationship;
	}

	public void setMemberRelationship(String memberRelationship) {
		this.memberRelationship = memberRelationship;
	}

	public String getGuardianFirstName() {
		return guardianFirstName;
	}

	public void setGuardianFirstName(String guardianFirstName) {
		this.guardianFirstName = guardianFirstName;
	}

	public String getGuardianLastName() {
		return guardianLastName;
	}

	public void setGuardianLastName(String guardianLastName) {
		this.guardianLastName = guardianLastName;
	}

	public String getGuardianMiddleName() {
		return guardianMiddleName;
	}

	public void setGuardianMiddleName(String guardianMiddleName) {
		this.guardianMiddleName = guardianMiddleName;
	}

	public Address getGuardianAddress() {
		return guardianAddress;
	}

	public void setGuardianAddress(Address guardianAddress) {
		this.guardianAddress = guardianAddress;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getJuvenileLastName() {
		return juvenileLastName;
	}

	public void setJuvenileLastName(String juvenileLastName) {
		this.juvenileLastName = juvenileLastName;
	}

	public String getJuvenileMiddleName() {
		return juvenileMiddleName;
	}

	public void setJuvenileMiddleName(String juvenileMiddleName) {
		this.juvenileMiddleName = juvenileMiddleName;
	}

	public String getJuvenileFirstName() {
		return juvenileFirstName;
	}

	public void setJuvenileFirstName(String juvenileFirstName) {
		this.juvenileFirstName = juvenileFirstName;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventComments() {
		return eventComments;
	}

	public void setEventComments(String eventComments) {
		this.eventComments = eventComments;
	}

	public List getInventoryDocuments() {
		return inventoryDocuments;
	}

	public void setInventoryDocuments(List inventoryDocuments) {
		this.inventoryDocuments = inventoryDocuments;
	}

	public String getOfficerLastName() {
		return officerLastName;
	}

	public void setOfficerLastName(String officerLastName) {
		this.officerLastName = officerLastName;
	}

	public String getOfficerMiddleName() {
		return officerMiddleName;
	}

	public void setOfficerMiddleName(String officerMiddleName) {
		this.officerMiddleName = officerMiddleName;
	}

	public String getOfficerFirstName() {
		return officerFirstName;
	}

	public void setOfficerFirstName(String officerFirstName) {
		this.officerFirstName = officerFirstName;
	}

	public String getOfficerPhone() {
		return officerPhone;
	}

	public void setOfficerPhone(String officerPhone) {
		this.officerPhone = officerPhone;
	}

	public String getManagerLastName() {
		return managerLastName;
	}

	public void setManagerLastName(String managerLastName) {
		this.managerLastName = managerLastName;
	}

	public String getManagerMiddleName() {
		return managerMiddleName;
	}

	public void setManagerMiddleName(String managerMiddleName) {
		this.managerMiddleName = managerMiddleName;
	}

	public String getManagerFirstName() {
		return managerFirstName;
	}

	public void setManagerFirstName(String managerFirstName) {
		this.managerFirstName = managerFirstName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getServiceLocationName() {
		return serviceLocationName;
	}

	public void setServiceLocationName(String serviceLocationName) {
		this.serviceLocationName = serviceLocationName;
	}

	public String getInterviewLocationPhone() {
		return interviewLocationPhone;
	}

	public void setInterviewLocationPhone(String interviewLocationPhone) {
		this.interviewLocationPhone = interviewLocationPhone;
	}
	
	
}

