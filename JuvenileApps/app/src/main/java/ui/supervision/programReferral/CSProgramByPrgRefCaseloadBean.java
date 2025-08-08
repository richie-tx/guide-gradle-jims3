package ui.supervision.programReferral;

import java.util.List;
import java.util.Set;

public class CSProgramByPrgRefCaseloadBean 
{
	private String programId;
	private String programIdentifier;
	private String programName;
	private String cstsCode;
	private String referralTypeCd;
	private String referralTypeDesc;
	
	private String serviceProviderId;
	private String serviceProviderName;
	
	private Set locationIdsSet;
	private List locationList; //CSLocByPrgRefCaseloadBean
	
	private List programPrgReferralList; //CSProgRefCaseloadBean
	
	private List openProgReferralList; //CSProgRefCaseloadBean
	private List allProgReferralList; //CSProgRefCaseloadBean
	
	
	
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
	 * @return the cstsCode
	 */
	public String getCstsCode() {
		return cstsCode;
	}

	/**
	 * @param cstsCode the cstsCode to set
	 */
	public void setCstsCode(String cstsCode) {
		this.cstsCode = cstsCode;
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

	/**
	 * @return the locationList
	 */
	public List getLocationList() {
		return locationList;
	}

	/**
	 * @param locationList the locationList to set
	 */
	public void setLocationList(List locationList) {
		this.locationList = locationList;
	}

	/**
	 * @return the programPrgReferralList
	 */
	public List getProgramPrgReferralList() {
		return programPrgReferralList;
	}

	/**
	 * @param programPrgReferralList the programPrgReferralList to set
	 */
	public void setProgramPrgReferralList(List programPrgReferralList) {
		this.programPrgReferralList = programPrgReferralList;
	}

	/**
	 * @return the locationIdsSet
	 */
	public Set getLocationIdsSet() {
		return locationIdsSet;
	}

	/**
	 * @param locationIdsSet the locationIdsSet to set
	 */
	public void setLocationIdsSet(Set locationIdsSet) {
		this.locationIdsSet = locationIdsSet;
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
	 * @return the openProgReferralList
	 */
	public List getOpenProgReferralList() {
		return openProgReferralList;
	}

	/**
	 * @param openProgReferralList the openProgReferralList to set
	 */
	public void setOpenProgReferralList(List openProgReferralList) {
		this.openProgReferralList = openProgReferralList;
	}

	/**
	 * @return the allProgReferralList
	 */
	public List getAllProgReferralList() {
		return allProgReferralList;
	}

	/**
	 * @param allProgReferralList the allProgReferralList to set
	 */
	public void setAllProgReferralList(List allProgReferralList) {
		this.allProgReferralList = allProgReferralList;
	}
}
