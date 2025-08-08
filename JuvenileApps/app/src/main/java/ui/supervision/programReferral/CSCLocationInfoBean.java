/*
 * Created on Apr 7, 2008
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
public class CSCLocationInfoBean
{
	private String locationId;
	private String streetNumber;
	private String streetName;
	private String streetTypeCd;
	private String aptNum;
	private String city;
	private String state;
	private String zipCode;
	private IPhoneNumber locationPhone;
	private IPhoneNumber locationFax;
	private String locationRegionDesc;
	private boolean selected;
	
//	stores the names of the programs of a particular SP which offer pgms at this location (print packet)
	private List programBeanList; // CSCProgramInfoBean
	
	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}
	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
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
	public IPhoneNumber getLocationPhone() {
		return locationPhone;
	}
	/**
	 * @param locationPhone the locationPhone to set
	 */
	public void setLocationPhone(IPhoneNumber locationPhone) {
		this.locationPhone = locationPhone;
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
	 * @return the streetTypeCd
	 */
	public String getStreetTypeCd() {
		return streetTypeCd;
	}
	/**
	 * @param streetTypeCd the streetTypeCd to set
	 */
	public void setStreetTypeCd(String streetTypeCd) {
		this.streetTypeCd = streetTypeCd;
	}
	/**
	 * @return the programBeanList
	 */
	public List getProgramBeanList() {
		return programBeanList;
	}
	/**
	 * @param programBeanList the programBeanList to set
	 */
	public void setProgramBeanList(List programBeanList) {
		this.programBeanList = programBeanList;
	}
	/**
	 * @return the locationFax
	 */
	public IPhoneNumber getLocationFax() {
		return locationFax;
	}
	/**
	 * @param locationFax the locationFax to set
	 */
	public void setLocationFax(IPhoneNumber locationFax) {
		this.locationFax = locationFax;
	}
	/**
	 * @return the locationRegionDesc
	 */
	public String getLocationRegionDesc() {
		return locationRegionDesc;
	}
	/**
	 * @param locationRegionDesc the locationRegionDesc to set
	 */
	public void setLocationRegionDesc(String locationRegionDesc) {
		this.locationRegionDesc = locationRegionDesc;
	}
	
}// END CLASS
