/*
 * Created on Oct 19, 2004
 */
package messaging.administerlocation.reply;

import java.util.ArrayList;

import messaging.identityaddress.domintf.IAddressable;

/**
 * @author dgibler
 */
public class LocationNotificationResponseEvent implements IAddressable 
{
	String serviceProviderId;
	String serviceProviderName;
	String serviceName;
	String serviceType;
	String sessionDate;
	String sessionTime;
	String locationAddress;
	String subject;
	String identity;
	String serviceProviderFax;
	String userName;
	String adminUserProfileId;
	String juvenileName;
	String juvenileNum;
	ArrayList services;
	
	String notificationMessage;
	String guardianEmail;
	String probationOfficerName;
	

	/**
	 * @return Returns the notificationMessage.
	 */
	public String getNotificationMessage() {
		return notificationMessage;
	}
	/**
	 * @param notificationMessage The notificationMessage to set.
	 */
	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}
	/**
	 * @return Returns the serviceProviderId.
	 */
	public String getServiceProviderId() {
		return serviceProviderId;
	}
	/**
	 * @param serviceProviderId The serviceProviderId to set.
	 */
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
	/**
	 * @return Returns the serviceProviderName.
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	/**
	 * @param serviceProviderName The serviceProviderName to set.
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
	/**
	 * @return Returns the services.
	 */
	public ArrayList getServices() {
		return services;
	}
	/**
	 * @param services The services to set.
	 */
	public void setServices(ArrayList services) {
		this.services = services;
	}
	/**
	 * @return Returns the locationAddress.
	 */
	public String getLocationAddress() {
		return locationAddress;
	}
	/**
	 * @param locationAddress The locationAddress to set.
	 */
	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}
	/**
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return Returns the identity.
	 */
	public String getIdentity() {
		return identity;
	}
	/**
	 * @param identity The identity to set.
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	/**
	 * @return Returns the serviceProviderFax.
	 */
	public String getServiceProviderFax() {
		return serviceProviderFax;
	}
	/**
	 * @param serviceProviderFax The serviceProviderFax to set.
	 */
	public void setServiceProviderFax(String serviceProviderFax) {
		this.serviceProviderFax = serviceProviderFax;
	}
	/**
	 * @return Returns the serviceName.
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * @param serviceName The serviceName to set.
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	/**
	 * @return Returns the serviceType.
	 */
	public String getServiceType() {
		return serviceType;
	}
	/**
	 * @param serviceType The serviceType to set.
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	/**
	 * @return Returns the sessionDate.
	 */
	public String getSessionDate() {
		return sessionDate;
	}
	/**
	 * @param sessionDate The sessionDate to set.
	 */
	public void setSessionDate(String sessionDate) {
		this.sessionDate = sessionDate;
	}
	/**
	 * @return Returns the sessionTime.
	 */
	public String getSessionTime() {
		return sessionTime;
	}
	/**
	 * @param sessionTime The sessionTime to set.
	 */
	public void setSessionTime(String sessionTime) {
		this.sessionTime = sessionTime;
	}
	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return Returns the adminUserProfileId.
	 */
	public String getAdminUserProfileId() {
		return adminUserProfileId;
	}
	/**
	 * @param adminUserProfileId The adminUserProfileId to set.
	 */
	public void setAdminUserProfileId(String adminUserProfileId) {
		this.adminUserProfileId = adminUserProfileId;
	}
	/**
	 * @return Returns the juvenileName.
	 */
	public String getJuvenileName() {
		return juvenileName;
	}
	/**
	 * @param juvenileName The juvenileName to set.
	 */
	public void setJuvenileName(String juvenileName) {
		this.juvenileName = juvenileName;
	}
	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	/**
	 * @return Returns the guardianEmail.
	 */
	public String getGuardianEmail() {
		return guardianEmail;
	}
	/**
	 * @param guardianEmail The guardianEmail to set.
	 */
	public void setGuardianEmail(String guardianEmail) {
		this.guardianEmail = guardianEmail;
	}
	/**
	 * @return Returns the probationOfficerName.
	 */
	public String getProbationOfficerName() {
		return probationOfficerName;
	}
	/**
	 * @param probationOfficerName The probationOfficerName to set.
	 */
	public void setProbationOfficerName(String probationOfficerName) {
		this.probationOfficerName = probationOfficerName;
	}
}
