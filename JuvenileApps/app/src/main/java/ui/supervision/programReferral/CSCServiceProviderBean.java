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
public class CSCServiceProviderBean 
{
	 private String serviceProviderId;
	 
	 private String serviceProviderName;
	 
	 private String serviceProviderRefTypes;
	 private List serviceProviderRefTypeCdList;
	 
	 private String serviceProviderRegions;
	 
	 private IPhoneNumber serviceProviderPhoneNumber;
	 private IPhoneNumber serviceProviderFaxNumber;
	 private boolean serviceProviderInHouse;
	 private String serviceProviderInHouseAsStr;
	 
//	 private boolean actualSP;
//	 private boolean selected;
//	 private String selectedSPId;
	 
	 private String serviceProviderURL; //(print packet)
	 private List pgmLocationList; //CSCLocationInfoBean (print packet)
	
	

	/**
	 * @return Returns the serviceProviderFaxNumber.
	 */
	public IPhoneNumber getServiceProviderFaxNumber() {
		return serviceProviderFaxNumber;
	}
	/**
	 * @param serviceProviderFaxNumber The serviceProviderFaxNumber to set.
	 */
	public void setServiceProviderFaxNumber(IPhoneNumber serviceProviderFaxNumber) {
		this.serviceProviderFaxNumber = serviceProviderFaxNumber;
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
	 * @return Returns the serviceProviderInHouse.
	 */
	public boolean isServiceProviderInHouse() {
		return serviceProviderInHouse;
	}
	/**
	 * @param serviceProviderInHouse The serviceProviderInHouse to set.
	 */
	public void setServiceProviderInHouse(boolean serviceProviderInHouse) {
		this.serviceProviderInHouse = serviceProviderInHouse;
	}
	/**
	 * @return Returns the serviceProviderInHouseAsStr.
	 */
	public String getServiceProviderInHouseAsStr() {
		return serviceProviderInHouseAsStr;
	}
	/**
	 * @param serviceProviderInHouseAsStr The serviceProviderInHouseAsStr to set.
	 */
	public void setServiceProviderInHouseAsStr(String serviceProviderInHouseAsStr) {
		this.serviceProviderInHouseAsStr = serviceProviderInHouseAsStr;
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
	 * @return Returns the serviceProviderPhoneNumber.
	 */
	public IPhoneNumber getServiceProviderPhoneNumber() {
		return serviceProviderPhoneNumber;
	}
	/**
	 * @param serviceProviderPhoneNumber The serviceProviderPhoneNumber to set.
	 */
	public void setServiceProviderPhoneNumber(IPhoneNumber serviceProviderPhoneNumber) {
		this.serviceProviderPhoneNumber = serviceProviderPhoneNumber;
	}
	/**
	 * @return Returns the serviceProviderRefTypes.
	 */
	public String getServiceProviderRefTypes() {
		return serviceProviderRefTypes;
	}
	/**
	 * @param serviceProviderRefTypes The serviceProviderRefTypes to set.
	 */
	public void setServiceProviderRefTypes(String serviceProviderRefTypes) {
		this.serviceProviderRefTypes = serviceProviderRefTypes;
	}
	/**
	 * @return Returns the serviceProviderRegions.
	 */
	public String getServiceProviderRegions() {
		return serviceProviderRegions;
	}
	/**
	 * @param serviceProviderRegions The serviceProviderRegions to set.
	 */
	public void setServiceProviderRegions(String serviceProviderRegions) {
		this.serviceProviderRegions = serviceProviderRegions;
	}
	/**
	 * @return the serviceProviderRefTypeCdList
	 */
	public List getServiceProviderRefTypeCdList() {
		return serviceProviderRefTypeCdList;
	}
	/**
	 * @param serviceProviderRefTypeCdList the serviceProviderRefTypeCdList to set
	 */
	public void setServiceProviderRefTypeCdList(List serviceProviderRefTypeCdList) {
		this.serviceProviderRefTypeCdList = serviceProviderRefTypeCdList;
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
	 * @return the pgmLocationList
	 */
	public List getPgmLocationList() {
		return pgmLocationList;
	}
	/**
	 * @param pgmLocationList the pgmLocationList to set
	 */
	public void setPgmLocationList(List pgmLocationList) {
		this.pgmLocationList = pgmLocationList;
	}
	
	
}// END Class
