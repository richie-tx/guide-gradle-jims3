/*
 * Created on Dec 20, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.casefile;

import ui.common.Name;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ClosingPacketReportPrintBean
{

	private Name probationOfficer;

	private String locationUnitName;
	private String locationUnitAddress;
	
	private String officerPhone;
	private String courtNum;
	
	//changed format 36225
	private String probationOfficerName; 
	
	
	public ClosingPacketReportPrintBean() {
		probationOfficer = new Name();
	}
	/**
	 * @return Returns the locationUnitAddress.
	 */
	public String getLocationUnitAddress() {
		return locationUnitAddress;
	}
	/**
	 * @param locationUnitAddress The locationUnitAddress to set.
	 */
	public void setLocationUnitAddress(String locationUnitAddress) {
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
	 * @return Returns the probationOfficer.
	 */
	public Name getProbationOfficer() {
		return probationOfficer;
	}
	/**
	 * @param probationOfficer The probationOfficer to set.
	 */
	public void setProbationOfficer(Name probationOfficer) {
		this.probationOfficer = probationOfficer;
	}
	/**
	 * @return the courtNum
	 */
	public String getCourtNum() {
		return courtNum;
	}
	/**
	 * @param courtNum the courtNum to set
	 */
	public void setCourtNum(String courtNum) {
		this.courtNum = courtNum;
	}
	public String getProbationOfficerName() {
		return probationOfficerName;
	}
	public void setProbationOfficerName(String probationOfficerName) {
		this.probationOfficerName = probationOfficerName;
	}
		
}
