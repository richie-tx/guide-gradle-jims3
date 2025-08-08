/*
 * Created on Apr 8, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerprogramreferrals.reply;

import java.util.List;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceProviderReftypeResponseEvent extends ResponseEvent 
{
	private String serviceProviderId;
	private String serviceProviderName;
	private List programReferralTypes;
	private List programLocationRegions;
	private String phoneNumber;
	private String faxNumber;
	private boolean isInHouse;
	private String emailAddress;
	
	
	/**
	 * @return Returns the faxNumber.
	 */
	public String getFaxNumber() {
		return faxNumber;
	}
	/**
	 * @param faxNumber The faxNumber to set.
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	/**
	 * @return Returns the phoneNumber.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return Returns the programLocationRegions.
	 */
	public List getProgramLocationRegions() {
		return programLocationRegions;
	}
	/**
	 * @param programLocationRegions The programLocationRegions to set.
	 */
	public void setProgramLocationRegions(List programLocationRegions) {
		this.programLocationRegions = programLocationRegions;
	}
	/**
	 * @return Returns the programReferralTypes.
	 */
	public List getProgramReferralTypes() {
		return programReferralTypes;
	}
	/**
	 * @param programReferralTypes The programReferralTypes to set.
	 */
	public void setProgramReferralTypes(List programReferralTypes) {
		this.programReferralTypes = programReferralTypes;
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
	public String getServiceProviderId() {
		return serviceProviderId;
	}
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the isInHouse
	 */
	public boolean isInHouse() {
		return isInHouse;
	}
	/**
	 * @param isInHouse the isInHouse to set
	 */
	public void setInHouse(boolean isInHouse) {
		this.isInHouse = isInHouse;
	}
}
