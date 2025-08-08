package messaging.administerprogramreferrals.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class ProgrRefByCaseloadResponseEvent extends ResponseEvent
{
	private String programReferralId;
	private String criminalCaseId;
	private String defendantId;
	private String superviseeName;
	private String referralStatusCd;
	private String referralTypeCd;
	private String referralTypeDesc;
	private String programGroupCd;
	private String programTypeCd;
	private String programHierarchyCd;
	private Date referralDate;
	private String progLocationId;
	private Date referralBeginDate;
	private Date referralEndDate;
	private String serviceProviderId;
	private String serviceProviderName;
	private String programId;
	private String programIdentifier;
	private String programName;
	private boolean isInHouse;
	private String ctsCode;
	private String locationId;
	private String regionCd;
	private String locationName;
	private String locationPhone;
	private String streetNum;
	private String streetName;
	private String streetTypeCd;
	private String aptNum;
	private String city;
	private String state;
	private String zipCode;
	
	
	/**
	 * @return the programReferralId
	 */
	public String getProgramReferralId() {
		return programReferralId;
	}
	/**
	 * @param programReferralId the programReferralId to set
	 */
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}
	/**
	 * @return the criminalCaseId
	 */
	public String getCriminalCaseId() {
		return criminalCaseId;
	}
	/**
	 * @param criminalCaseId the criminalCaseId to set
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
	/**
	 * @return the defendantId
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId the defendantId to set
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	/**
	 * @return the superviseeName
	 */
	public String getSuperviseeName() {
		return superviseeName;
	}
	/**
	 * @param superviseeName the superviseeName to set
	 */
	public void setSuperviseeName(String superviseeName) {
		this.superviseeName = superviseeName;
	}
	/**
	 * @return the referralStatusCd
	 */
	public String getReferralStatusCd() {
		return referralStatusCd;
	}
	/**
	 * @param referralStatusCd the referralStatusCd to set
	 */
	public void setReferralStatusCd(String referralStatusCd) {
		this.referralStatusCd = referralStatusCd;
	}
	/**
	 * @return the referralTypeCd
	 */
	public String getReferralTypeCd() {
		return referralTypeCd;
	}
	/**
	 * @param referralTypeCd the referralTypeCd to set
	 */
	public void setReferralTypeCd(String referralTypeCd) {
		this.referralTypeCd = referralTypeCd;
	}
	/**
	 * @return the programGroupCd
	 */
	public String getProgramGroupCd() {
		return programGroupCd;
	}
	/**
	 * @param programGroupCd the programGroupCd to set
	 */
	public void setProgramGroupCd(String programGroupCd) {
		this.programGroupCd = programGroupCd;
	}
	/**
	 * @return the programTypeCd
	 */
	public String getProgramTypeCd() {
		return programTypeCd;
	}
	/**
	 * @param programTypeCd the programTypeCd to set
	 */
	public void setProgramTypeCd(String programTypeCd) {
		this.programTypeCd = programTypeCd;
	}
	/**
	 * @return the programHierarchyCd
	 */
	public String getProgramHierarchyCd() {
		return programHierarchyCd;
	}
	/**
	 * @param programHierarchyCd the programHierarchyCd to set
	 */
	public void setProgramHierarchyCd(String programHierarchyCd) {
		this.programHierarchyCd = programHierarchyCd;
	}
	/**
	 * @return the progLocationId
	 */
	public String getProgLocationId() {
		return progLocationId;
	}
	/**
	 * @param progLocationId the progLocationId to set
	 */
	public void setProgLocationId(String progLocationId) {
		this.progLocationId = progLocationId;
	}
	/**
	 * @return the referralDate
	 */
	public Date getReferralDate() {
		return referralDate;
	}
	/**
	 * @param referralDate the referralDate to set
	 */
	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}
	/**
	 * @return the referralBeginDate
	 */
	public Date getReferralBeginDate() {
		return referralBeginDate;
	}
	/**
	 * @param referralBeginDate the referralBeginDate to set
	 */
	public void setReferralBeginDate(Date referralBeginDate) {
		this.referralBeginDate = referralBeginDate;
	}
	/**
	 * @return the referralEndDate
	 */
	public Date getReferralEndDate() {
		return referralEndDate;
	}
	/**
	 * @param referralEndDate the referralEndDate to set
	 */
	public void setReferralEndDate(Date referralEndDate) {
		this.referralEndDate = referralEndDate;
	}
	/**
	 * @return the serviceProviderId
	 */
	public String getServiceProviderId() {
		return serviceProviderId;
	}
	/**
	 * @param serviceProviderId the serviceProviderId to set
	 */
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
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
	 * @return the programId
	 */
	public String getProgramId() {
		return programId;
	}
	/**
	 * @param programId the programId to set
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	/**
	 * @return the programIdentifier
	 */
	public String getProgramIdentifier() {
		return programIdentifier;
	}
	/**
	 * @param programIdentifier the programIdentifier to set
	 */
	public void setProgramIdentifier(String programIdentifier) {
		this.programIdentifier = programIdentifier;
	}
	/**
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
	}
	/**
	 * @param programName the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
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
	/**
	 * @return the ctsCode
	 */
	public String getCtsCode() {
		return ctsCode;
	}
	/**
	 * @param ctsCode the ctsCode to set
	 */
	public void setCtsCode(String ctsCode) {
		this.ctsCode = ctsCode;
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
	 * @return the regionCd
	 */
	public String getRegionCd() {
		return regionCd;
	}
	/**
	 * @param regionCd the regionCd to set
	 */
	public void setRegionCd(String regionCd) {
		this.regionCd = regionCd;
	}
	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}
	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	/**
	 * @return the locationPhone
	 */
	public String getLocationPhone() {
		return locationPhone;
	}
	/**
	 * @param locationPhone the locationPhone to set
	 */
	public void setLocationPhone(String locationPhone) {
		this.locationPhone = locationPhone;
	}
	/**
	 * @return the streetNum
	 */
	public String getStreetNum() {
		return streetNum;
	}
	/**
	 * @param streetNum the streetNum to set
	 */
	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
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
	 * @return the referralTypeDesc
	 */
	public String getReferralTypeDesc() {
		return referralTypeDesc;
	}
	/**
	 * @param referralTypeDesc the referralTypeDesc to set
	 */
	public void setReferralTypeDesc(String referralTypeDesc) {
		this.referralTypeDesc = referralTypeDesc;
	}
}
