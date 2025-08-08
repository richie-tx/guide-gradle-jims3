/*
 * Created on Aug 20, 2007
 *
 */
package messaging.transferobjects;

import java.util.ArrayList;


/**
 * @author cc_mdsouza
 *
 */
public class CSCDStaffPositionTO 
extends PersistentObjectTO
{

	private java.util.Collection childStaffPositions = null;
	private java.util.Collection courts = null;
	private java.util.Collection histories = null;

	private SupervisionCodeTO cstsOfficerType = null;
	private SupervisionCodeTO jobTitle = null;
	private LocationTO location = null;
	private OrganizationTO organization = null;
	private CSCDStaffPositionTO parentPosition = null;
	private SupervisionCodeTO positionType = null;
	private CSCDStaffProfileTO staffProfile = null;
	private SupervisionCodeTO status = null;
	private UserProfileTO userProfile = null;

	private boolean hasCaseload;

	private String userProfileId;
	private String cstsOfficerTypeId;
	private String jobTitleId;
	private String locationDetails;
	private String locationId;
	private String organizationId;
	private String organizationName;
	private String parentPositionId;
	private String phoneNum;
	private String positionName;
	private String positionTypeId;
	private String probationOfficerInd;
	private String staffProfileId;
	private String statusId;
	
//	private CSCDStaffPosition staffPositionPDObject  ; 
	private String staffPositionId;

	public CSCDStaffPositionTO() 
	{
		this.childStaffPositions = new ArrayList() ; 
		this.courts = new ArrayList() ; 
		this.histories = new ArrayList() ; 
	}
	
	
	
	/**
	 * @return Returns the childStaffPositions.
	 
	public java.util.Collection getChildStaffPositions() {
		return childStaffPositions;
	}
	/**
	 * @param childStaffPositions The childStaffPositions to set.
	 */
	public void setChildStaffPositions(java.util.Collection childStaffPositions) {
		this.childStaffPositions = childStaffPositions;
	}
	/**
	 * @return Returns the courts.
	 */
	public java.util.Collection getCourts() {
		return courts;
	}
	/**
	 * @param courts The courts to set.
	 */
	public void setCourts(java.util.Collection courts) {
		this.courts = courts;
	}
	/**
	 * @return Returns the cstsOfficerType.
	 */
	public SupervisionCodeTO getCstsOfficerType() {
		return cstsOfficerType;
	}
	/**
	 * @param cstsOfficerType The cstsOfficerType to set.
	 */
	public void setCstsOfficerType(SupervisionCodeTO cstsOfficerType) {
		this.cstsOfficerType = cstsOfficerType;
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
	/**
	 * @return Returns the hasCaseload.
	 */
	public boolean isHasCaseload() {
		return hasCaseload;
	}
	/**
	 * @param hasCaseload The hasCaseload to set.
	 */
	public void setHasCaseload(boolean hasCaseload) {
		this.hasCaseload = hasCaseload;
	}
	/**
	 * @return Returns the histories.
	 */
	public java.util.Collection getHistories() {
		return histories;
	}
	/**
	 * @param histories The histories to set.
	 */
	public void setHistories(java.util.Collection histories) {
		this.histories = histories;
	}
	/**
	 * @return Returns the jobTitle.
	 */
	public SupervisionCodeTO getJobTitle() {
		return jobTitle;
	}
	/**
	 * @param jobTitle The jobTitle to set.
	 */
	public void setJobTitle(SupervisionCodeTO jobTitle) {
		this.jobTitle = jobTitle;
	}
	/**
	 * @return Returns the jobTitleId.
	 */
	public String getJobTitleId() {
		return jobTitleId;
	}
	/**
	 * @param jobTitleId The jobTitleId to set.
	 */
	public void setJobTitleId(String jobTitleId) {
		this.jobTitleId = jobTitleId;
	}
	/**
	 * @return Returns the location.
	 */
	public LocationTO getLocation() {
		return location;
	}
	/**
	 * @param location The location to set.
	 */
	public void setLocation(LocationTO location) {
		this.location = location;
	}
	/**
	 * @return Returns the locationDetails.
	 */
	public String getLocationDetails() {
		return locationDetails;
	}
	/**
	 * @param locationDetails The locationDetails to set.
	 */
	public void setLocationDetails(String locationDetails) {
		this.locationDetails = locationDetails;
	}
	/**
	 * @return Returns the locationId.
	 */
	public String getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId The locationId to set.
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	/**
	 * @return Returns the organization.
	 */
	public OrganizationTO getOrganization() {
		return organization;
	}
	/**
	 * @param organization The organization to set.
	 */
	public void setOrganization(OrganizationTO organization) {
		this.organization = organization;
	}
	/**
	 * @return Returns the organizationId.
	 */
	public String getOrganizationId() {
		return organizationId;
	}
	/**
	 * @param organizationId The organizationId to set.
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	/**
	 * @return Returns the parentPosition.
	 */
	public CSCDStaffPositionTO getParentPosition() {
		return parentPosition;
	}
	/**
	 * @param parentPosition The parentPosition to set.
	 */
	public void setParentPosition(CSCDStaffPositionTO parentPosition) {
		this.parentPosition = parentPosition;
	}
	/**
	 * @return Returns the parentPositionId.
	 */
	public String getParentPositionId() {
		return parentPositionId;
	}
	/**
	 * @param parentPositionId The parentPositionId to set.
	 */
	public void setParentPositionId(String parentPositionId) {
		this.parentPositionId = parentPositionId;
	}
	/**
	 * @return Returns the phoneNum.
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	/**
	 * @param phoneNum The phoneNum to set.
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	/**
	 * @return Returns the positionName.
	 */
	public String getPositionName() {
		return positionName;
	}
	/**
	 * @param positionName The positionName to set.
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	/**
	 * @return Returns the positionType.
	 */
	public SupervisionCodeTO getPositionType() {
		return positionType;
	}
	/**
	 * @param positionType The positionType to set.
	 */
	public void setPositionType(SupervisionCodeTO positionType) {
		this.positionType = positionType;
	}
	/**
	 * @return Returns the positionTypeId.
	 */
	public String getPositionTypeId() {
		return positionTypeId;
	}
	/**
	 * @param positionTypeId The positionTypeId to set.
	 */
	public void setPositionTypeId(String positionTypeId) {
		this.positionTypeId = positionTypeId;
	}
	/**
	 * @return Returns the probationOfficerInd.
	 */
	public String getProbationOfficerInd() {
		return probationOfficerInd;
	}
	/**
	 * @param probationOfficerInd The probationOfficerInd to set.
	 */
	public void setProbationOfficerInd(String probationOfficerInd) {
		this.probationOfficerInd = probationOfficerInd;
	}
	/**
	 * @return Returns the staffProfile.
	 */
	public CSCDStaffProfileTO getStaffProfile() {
		return staffProfile;
	}
	/**
	 * @param staffProfile The staffProfile to set.
	 */
	public void setStaffProfile(CSCDStaffProfileTO staffProfile) {
		this.staffProfile = staffProfile;
	}
	/**
	 * @return Returns the staffProfileId.
	 */
	public String getStaffProfileId() {
		return staffProfileId;
	}
	/**
	 * @param staffProfileId The staffProfileId to set.
	 */
	public void setStaffProfileId(String staffProfileId) {
		this.staffProfileId = staffProfileId;
	}
	/**
	 * @return Returns the status.
	 */
	public SupervisionCodeTO getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(SupervisionCodeTO status) {
		this.status = status;
	}
	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	/**
	 * @return Returns the userProfile.
	 */
	public UserProfileTO getUserProfile() {
		return userProfile;
	}
	/**
	 * @param userProfile The userProfile to set.
	 */
	public void setUserProfile(UserProfileTO userProfile) {
		this.userProfile = userProfile;
	}
	/**
	 * @return Returns the userProfileId.
	 */
	public String getUserProfileId() {
		return userProfileId;
	}
	/**
	 * @param userProfileId The userProfileId to set.
	 */
	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}
	
	
//	/**
//	 * @return Returns the staffPosition.
//	 */
//	public CSCDStaffPosition getStaffPositionPDObject() {
//		return staffPositionPDObject ;
//	}
//	/**
//	 * @param staffPosition The staffPosition to set.
//	 */
//	public void setStaffPositionPDObject (CSCDStaffPosition staffPosition) {
//		this.staffPositionPDObject  = staffPosition;
//	}
    /**
     * @return Returns the childStaffPositions.
     */
    public java.util.Collection getChildStaffPositions()
    {
        return childStaffPositions;
    }
	/**
	 * @return Returns the staffPositionId.
	 */
	public String getStaffPositionId() {
		return staffPositionId;
	}
	/**
	 * @param staffPositionId The staffPositionId to set.
	 */
	public void setStaffPositionId(String staffPositionId) {
		this.staffPositionId = staffPositionId;
	}



	public String getOrganizationName() {
		return organizationName;
	}



	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
}
