/*
 * Created on Apr 22, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.programReferral;

import java.util.List;

import messaging.contact.domintf.IPhoneNumber;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSCServiceProviderPacketBean
{
	 private List referralTypesList; 
	
	 private String serviceProviderName;
	 private IPhoneNumber serviceProviderPhone;
	 private String serviceProviderEmail;
	 private boolean spFaithBased;
	 
	 private List programLocList; // CSCLocationInfoBean

	 
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
	public IPhoneNumber getServiceProviderPhone() {
		return serviceProviderPhone;
	}

	/**
	 * @param serviceProviderPhone the serviceProviderPhone to set
	 */
	public void setServiceProviderPhone(IPhoneNumber serviceProviderPhone) {
		this.serviceProviderPhone = serviceProviderPhone;
	}

	/**
	 * @return the serviceProviderEmail
	 */
	public String getServiceProviderEmail() {
		return serviceProviderEmail;
	}

	/**
	 * @param serviceProviderEmail the serviceProviderEmail to set
	 */
	public void setServiceProviderEmail(String serviceProviderEmail) {
		this.serviceProviderEmail = serviceProviderEmail;
	}

	/**
	 * @return the programLocList
	 */
	public List getProgramLocList() {
		return programLocList;
	}

	/**
	 * @param programLocList the programLocList to set
	 */
	public void setProgramLocList(List programLocList) {
		this.programLocList = programLocList;
	}

	/**
	 * @return the referralTypesList
	 */
	public List getReferralTypesList() {
		return referralTypesList;
	}

	/**
	 * @param referralTypesList the referralTypesList to set
	 */
	public void setReferralTypesList(List referralTypesList) {
		this.referralTypesList = referralTypesList;
	}

	/**
	 * @return the spFaithBased
	 */
	public boolean isSpFaithBased() {
		return spFaithBased;
	}

	/**
	 * @param spFaithBased the spFaithBased to set
	 */
	public void setSpFaithBased(boolean spFaithBased) {
		this.spFaithBased = spFaithBased;
	}
	 
	
}// END Class
