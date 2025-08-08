
package ui.juvenilecase.populationReport;

import java.util.Date;
import java.util.List;

public class FacilityCurrentPopulationReportBean {
	
	private String juvenileNum;

	private String referralNum;
	private String firstName;
	private String lastName;
	private String middleName;
	private String raceId;
	private String sexId;
	private int ageInYears;
	private Date admitDate;
	private String reasonCode;
	private String secureStatus;
	private String offenseCodeId;    
    private String reportName = "";
    private String facilityName;
    
    private List currentPopulations;
    
	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return the referralNum
	 */
	public String getReferralNum() {
		return referralNum;
	}

	/**
	 * @param referralNum the referralNum to set
	 */
	public void setReferralNum(String referralNum) {
		this.referralNum = referralNum;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the raceId
	 */
	public String getRaceId() {
		return raceId;
	}

	/**
	 * @param raceId the raceId to set
	 */
	public void setRaceId(String raceId) {
		this.raceId = raceId;
	}

	/**
	 * @return the sexId
	 */
	public String getSexId() {
		return sexId;
	}

	/**
	 * @param sexId the sexId to set
	 */
	public void setSexId(String sexId) {
		this.sexId = sexId;
	}

	/**
	 * @return the ageInYears
	 */
	public int getAgeInYears() {
		return ageInYears;
	}

	/**
	 * @param ageInYears the ageInYears to set
	 */
	public void setAgeInYears(int ageInYears) {
		this.ageInYears = ageInYears;
	}

	/**
	 * @return the admitDate
	 */
	public Date getAdmitDate() {
		return admitDate;
	}

	/**
	 * @param admitDate the admitDate to set
	 */
	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}

	/**
	 * @return the reasonCode
	 */
	public String getReasonCode() {
		return reasonCode;
	}

	/**
	 * @param reasonCode the reasonCode to set
	 */
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	/**
	 * @return the secureStatus
	 */
	public String getSecureStatus() {
		return secureStatus;
	}

	/**
	 * @param secureStatus the secureStatus to set
	 */
	public void setSecureStatus(String secureStatus) {
		this.secureStatus = secureStatus;
	}

	/**
	 * @return the offenseCodeId
	 */
	public String getOffenseCodeId() {
		return offenseCodeId;
	}

	/**
	 * @param offenseCodeId the offenseCodeId to set
	 */
	public void setOffenseCodeId(String offenseCodeId) {
		this.offenseCodeId = offenseCodeId;
	}

	/**
	 * @return the reportName
	 */
	public String getReportName() {
		return reportName;
	}

	/**
	 * @param reportName the reportName to set
	 */
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	/**
	 * @return the facilityName
	 */
	public String getFacilityName() {
		return facilityName;
	}

	/**
	 * @param facilityName the facilityName to set
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	/**
	 * @return the currentPopulations
	 */
	public List getCurrentPopulations() {
		return currentPopulations;
	}

	/**
	 * @param currentPopulations the currentPopulations to set
	 */
	public void setCurrentPopulations(List currentPopulations) {
		this.currentPopulations = currentPopulations;
	}

    
   

}
