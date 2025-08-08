package messaging.administerprogramreferrals;

import java.util.List;

import mojo.km.messaging.RequestEvent;

/**
 * 
 * @author cc_bjangay
 *
 */
public class GetProgRefByCaseloadEvent extends RequestEvent
{
	private String searchBy;
	
	private List defendantIdsList;
	
	private String serviceProviderName;
	private String isInHouse;
	private String regionCd;
	
	private String programName;
	private String cstsCode;
	private String programGroupId;
	private String programTypeId;
	private String programSubTypeId;
	
	private String programId;
	private String locationId;
	
	/**
	 * @return the searchBy
	 */
	public String getSearchBy() {
		return searchBy;
	}
	/**
	 * @param searchBy the searchBy to set
	 */
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	/**
	 * @return the defendantIdsList
	 */
	public List getDefendantIdsList() {
		return defendantIdsList;
	}
	/**
	 * @param defendantIdsList the defendantIdsList to set
	 */
	public void setDefendantIdsList(List defendantIdsList) {
		this.defendantIdsList = defendantIdsList;
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
	 * @return the isInHouse
	 */
	public String getIsInHouse() {
		return isInHouse;
	}
	/**
	 * @param isInHouse the isInHouse to set
	 */
	public void setIsInHouse(String isInHouse) {
		this.isInHouse = isInHouse;
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
	 * @return the programGroupId
	 */
	public String getProgramGroupId() {
		return programGroupId;
	}
	/**
	 * @param programGroupId the programGroupId to set
	 */
	public void setProgramGroupId(String programGroupId) {
		this.programGroupId = programGroupId;
	}
	/**
	 * @return the programTypeId
	 */
	public String getProgramTypeId() {
		return programTypeId;
	}
	/**
	 * @param programTypeId the programTypeId to set
	 */
	public void setProgramTypeId(String programTypeId) {
		this.programTypeId = programTypeId;
	}
	/**
	 * @return the programSubTypeId
	 */
	public String getProgramSubTypeId() {
		return programSubTypeId;
	}
	/**
	 * @param programSubTypeId the programSubTypeId to set
	 */
	public void setProgramSubTypeId(String programSubTypeId) {
		this.programSubTypeId = programSubTypeId;
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
	
}
