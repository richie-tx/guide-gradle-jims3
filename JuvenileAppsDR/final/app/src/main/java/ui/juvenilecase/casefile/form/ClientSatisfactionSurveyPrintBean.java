/*
 * Created on Aug 29, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.casefile.form;

import java.util.Collection;

import ui.common.Name;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ClientSatisfactionSurveyPrintBean {

	private Name probationOfficer;
	private String currentDate;
	private String casefileId;
	private String fullStreetName;
	private String cityStateZip;
	// For Vendor Satisfaction Survey
	private Collection serviceProviderName;
	
	public void clearAll() {
		probationOfficer = new Name();
		currentDate = ""; 
		casefileId = "";
		serviceProviderName = null;
		fullStreetName = "";
		cityStateZip = "";
	}
	
	/**
	 * @return Returns the casefileId.
	 */
	public String getCasefileId() {
		return casefileId;
	}
	/**
	 * @param casefileId The casefileId to set.
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
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
	 * @return Returns the serviceProviderName.
	 */
	public Collection getServiceProviderName() {
		return serviceProviderName;
	}
	/**
	 * @param serviceProviderName The serviceProviderName to set.
	 */
	public void setServiceProviderName(Collection serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}

	public String getFullStreetName() {
		return fullStreetName;
	}

	public void setFullStreetName(String fullStreetName) {
		this.fullStreetName = fullStreetName;
	}

	public String getCityStateZip() {
		return cityStateZip;
	}

	public void setCityStateZip(String cityStateZip) {
		this.cityStateZip = cityStateZip;
	}
}
