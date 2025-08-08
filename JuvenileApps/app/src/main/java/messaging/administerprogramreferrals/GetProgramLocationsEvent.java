/*
 * Created on Apr 9, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerprogramreferrals;

import java.util.List;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetProgramLocationsEvent extends RequestEvent 
{
	private List serviceProviderIds;
	private List referralTypesCodeList;
	private boolean isOrderByLocation;
	private String manualServiceProviderName;
	private String manualServiceProviderPhone;
	private String manualServiceProviderFax;
	
	
	/**
	 * @return Returns the manualServiceProviderFax.
	 */
	public String getManualServiceProviderFax() {
		return manualServiceProviderFax;
	}
	/**
	 * @param manualServiceProviderFax The manualServiceProviderFax to set.
	 */
	public void setManualServiceProviderFax(String manualServiceProviderFax) {
		this.manualServiceProviderFax = manualServiceProviderFax;
	}
	/**
	 * @return Returns the manualServiceProviderName.
	 */
	public String getManualServiceProviderName() {
		return manualServiceProviderName;
	}
	/**
	 * @param manualServiceProviderName The manualServiceProviderName to set.
	 */
	public void setManualServiceProviderName(String manualServiceProviderName) {
		this.manualServiceProviderName = manualServiceProviderName;
	}
	/**
	 * @return Returns the manualServiceProviderPhone.
	 */
	public String getManualServiceProviderPhone() {
		return manualServiceProviderPhone;
	}
	/**
	 * @param manualServiceProviderPhone The manualServiceProviderPhone to set.
	 */
	public void setManualServiceProviderPhone(String manualServiceProviderPhone) {
		this.manualServiceProviderPhone = manualServiceProviderPhone;
	}
	/**
	 * @return Returns the serviceProviderIds.
	 */
	public List getServiceProviderIds() {
		return serviceProviderIds;
	}
	/**
	 * @param serviceProviderIds The serviceProviderIds to set.
	 */
	public void setServiceProviderIds(List serviceProviderIds) {
		this.serviceProviderIds = serviceProviderIds;
	}
	/**
	 * @return the referralTypesCodeList
	 */
	public List getReferralTypesCodeList() {
		return referralTypesCodeList;
	}
	/**
	 * @param referralTypesCodeList the referralTypesCodeList to set
	 */
	public void setReferralTypesCodeList(List referralTypesCodeList) {
		this.referralTypesCodeList = referralTypesCodeList;
	}
	/**
	 * @return the isOrderByLocation
	 */
	public boolean isOrderByLocation() {
		return isOrderByLocation;
	}
	/**
	 * @param isOrderByLocation the isOrderByLocation to set
	 */
	public void setOrderByLocation(boolean isOrderByLocation) {
		this.isOrderByLocation = isOrderByLocation;
	}
	
	
}//end of GetProgramLocationsEvent
