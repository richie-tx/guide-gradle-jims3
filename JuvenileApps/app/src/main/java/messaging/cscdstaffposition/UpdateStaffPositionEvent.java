//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\cscdstaffposition\\UpdateStaffPositionEvent.java

package messaging.cscdstaffposition;

import java.util.Date;

import mojo.km.messaging.Composite.CompositeRequest;

public class UpdateStaffPositionEvent extends CompositeRequest 
{
	private String agencyId;
    private String cjadNum;
	private	String divisionId;
	private	boolean hasCaseload;
	private	String jobTitleId;
	private	String locationDetails;
	private	String locationId;
	private String organizationId;
	private String parentPositionId;
	private	String phoneNum;
	private String positionId;
	private	String positionName;
	private	String positionTypeId;
	private	String probationOfficerInd;
	private String programSectionId;
	private	String programUnitId;
	private String cstsOfficerTypeId;
	private Date effectiveDate;
  
   /**
    * @roseuid 460BDCE6021E
    */
   public UpdateStaffPositionEvent() 
   {
    
   }
    /**
     * @return Returns the agencyId.
     */
    public String getAgencyId() {
        return agencyId;
    }
    /**
     * @return Returns the cjadNum.
     */
    public String getCjadNum() {
        return cjadNum;
    }
	/**
	 * @return Returns the divisionId.
	 */
	public String getDivisionId() {
		return divisionId;
	}
	/**
	 * @return Returns the hasCaseload.
	 */
	public boolean getHasCaseload() {
		return hasCaseload;
	}
	/**
	 * @return Returns the jobTitleId.
	 */
	public String getJobTitleId() {
		return jobTitleId;
	}
	/**
	 * @return Returns the locationDetails.
	 */
	public String getLocationDetails() {
		return locationDetails;
	}
	/**
	 * @return Returns the locationId.
	 */
	public String getLocationId() {
		return locationId;
	}
	/**
	 * @return Returns the organizationId.
	 */
	public String getOrganizationId() {
		return organizationId;
	}
	/**
	 * @return Returns the parentPositionId.
	 */
	public String getParentPositionId() {
		return parentPositionId;
	}
	/**
	 * @return Returns the phoneNum.
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	/**
	 * @return Returns the positionId.
	 */
	public String getPositionId() {
		return positionId;
	}
	/**
	 * @return Returns the positionName.
	 */
	public String getPositionName() {
		return positionName;
	}
	/**
	 * @return Returns the positionTypeId.
	 */
	public String getPositionTypeId() {
		return positionTypeId;
	}
	/**
	 * @return Returns the probationOfficerInd.
	 */
	public String getProbationOfficerInd() {
		return probationOfficerInd;
	}
    /**
     * @return Returns the programSectionId.
     */
    public String getProgramSectionId() {
        return programSectionId;
    }
	/**
	 * @return Returns the programUnitId.
	 */
	public String getProgramUnitId() {
		return programUnitId;
	}
    /**
     * @param agencyId The agencyId to set.
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
    /**
     * @param cjadNum The cjadNum to set.
     */
    public void setCjadNum(String cjadNum) {
        this.cjadNum = cjadNum;
    }
	/**
	 * @param divisionId The divisionId to set.
	 */
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	/**
	 * @param hasCaseload The hasCaseload to set.
	 */
	public void setHasCaseload(boolean hasCaseload) {
		this.hasCaseload = hasCaseload;
	}
	/**
	 * @param jobTitleId The jobTitleId to set.
	 */
	public void setJobTitleId(String jobTitleId) {
		this.jobTitleId = jobTitleId;
	}
	/**
	 * @param locationDetails The locationDetails to set.
	 */
	public void setLocationDetails(String locationDetails) {
		this.locationDetails = locationDetails;
	}
	/**
	 * @param locationId The locationId to set.
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	/**
	 * @param organizationId The organizationId to set.
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	/**
	 * @param parentPositionId The parentPositionId to set.
	 */
	public void setParentPositionId(String parentPositionId) {
		this.parentPositionId = parentPositionId;
	}
	/**
	 * @param phoneNum The phoneNum to set.
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	/**
	 * @param positionId The positionId to set.
	 */
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	/**
	 * @param positionName The positionName to set.
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	/**
	 * @param positionTypeId The positionTypeId to set.
	 */
	public void setPositionTypeId(String positionTypeId) {
		this.positionTypeId = positionTypeId;
	}
	/**
	 * @param probationOfficerInd The probationOfficerInd to set.
	 */
	public void setProbationOfficerInd(String probationOfficerInd) {
		this.probationOfficerInd = probationOfficerInd;
	}
    /**
     * @param programSectionId The programSectionId to set.
     */
    public void setProgramSectionId(String programSectionId) {
        this.programSectionId = programSectionId;
    }
	/**
	 * @param programUnitId The programUnitId to set.
	 */
	public void setProgramUnitId(String programUnitId) {
		this.programUnitId = programUnitId;
	}
	/**
	 * @return Returns the cstsOfficerTypeId.
	 */
	public String getCstsOfficerTypeId() {
		return cstsOfficerTypeId;
	}
	/**
	 * @param cstsOfficerTypeId The cstsOfficerTypeId to set.
	 */
	public void setCstsOfficerTypeId(String cstsOfficerTypeId) {
		this.cstsOfficerTypeId = cstsOfficerTypeId;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
}
