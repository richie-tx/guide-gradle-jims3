package pd.supervision.supervisionstaff.cscdstaffposition;

import java.util.Date;
import java.util.Iterator;

import pd.supervision.supervisionstaff.Organization;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.contact.agency.Agency;

/**
 * 
 * @author dgibler
 */
public class CSCDOrganizationStaffPosition extends CSCDStaffPosition
{
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getProgramUnitName() {
		return programUnitName;
	}
	public void setProgramUnitName(String programUnitName) {
		this.programUnitName = programUnitName;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	/**
	 * @param event
	 * @return
	 */
	public static Iterator findAll(IEvent event){
	    IHome home = new Home();
	    return home.findAll(event, CSCDOrganizationStaffPosition.class);
	}
	/**
	 * @param attrName
	 * @param attrValue
	 * @return
	 */
	public static Iterator findAll(String attrName, String attrValue){
	    IHome home = new Home();
	    return home.findAll(attrName, attrValue, CSCDOrganizationStaffPosition.class);
	}
	/**
	 * Properties for agency
	 */
	private Agency agency = null;
	private String agencyId;
	private String cjadNum;
	private Date createDate;
	private String createUser;
	/**
	 * Properties for cstsOfficerType
	 */
//	private SupervisionCode cstsOfficerType = null;
//	private String cstsOfficerTypeId;
	/**
	 * Properties for division
	 */
	private Organization division = null;
	private String divisionId;
	private String divisionName;
	//private String parentPositionId;
	private String programUnitName;
	private String sectionName;
//	private boolean hasCaseload;
	/**
	 * Properties for jobTitle
	 */
//	private SupervisionCode jobTitle = null;
//	private String jobTitleId;
	/**
	 * Properties for location
	 */
//	private Location location = null;
//	private String locationDetails;
//	private String locationId;
//	private String phoneNum;
//	private String positionName;
	/**
	 * Properties for positionType
	 */
//	private SupervisionCode positionType = null;
//	private String positionTypeId;
//	private String probationOfficerInd;
	/**
	 * Properties for programSection
	 */
	private Organization programSection = null;
	private String programSectionId;
	/**
	 * Properties for programUnit
	 */
	private Organization programUnit = null;
	private String programUnitId;
	/**
	 * Properties for staffPosition
	 */
	private CSCDStaffPosition staffPosition = null;
	private String staffPositionId;
	/**
	 * Properties for staffProfile
	 */
//	private CSCDStaffProfile staffProfile = null;
//	private String staffProfileId;
	/**
	 * Properties for status
	 */
//	private SupervisionCode status = null;
//	private String statusId;
	private Date updateDate;
	private String updateUser;
	/**
	 * Properties for userProfile
	 */
//	private UserProfile userProfile = null;
//	private String userProfileId; this attribute is defined in parent object.
//	private String legacyProgramUnit;
	/**
	 * 
	 * @return Returns the agency.
	 */
	public Agency getAgency()
	{
		initAgency();
		return agency;
	}

	/**
	 * Get the reference value to class :: pd.contact.agency.Agency
	 */
	public String getAgencyId()
	{
		fetch();
		return agencyId;
	}
    /**
     * @return Returns the cjadNum.
     */
    public String getCjadNum() {
        fetch();
        return cjadNum;
    }
    /**
     * @return Returns the updateDate.
     */
    public Date getCreateDate() {
        fetch();
        return createDate;
    }
    /**
     * @return Returns the updateUser.
     */
    public String getCreateUser() {
        fetch();
        return createUser;
    }

	/**
	 * Gets referenced type pd.codetable.supervision.SupervisionCode
	 */
//	public pd.codetable.supervision.SupervisionCode getCstsOfficerType()
//	{
//		fetch();
//		initCstsOfficerType();
//		return cstsOfficerType;
//	}

	/**
	 * Get the reference value to class :: pd.codetable.supervision.SupervisionCode
	 */
//	public String getCstsOfficerTypeId()
//	{
//		fetch();
//		return cstsOfficerTypeId;
//	}

	/**
	 * 
	 * @return Returns the division.
	 */
	public Organization getDivision()
	{
		initDivision();
		return division;
	}

	/**
	 * Get the reference value to class :: pd.supervision.supervisionstaff.Organization
	 */
	public String getDivisionId()
	{
		fetch();
		return divisionId;
	}
    /**
     * @return Returns the hasCaseload.
     */
//    public boolean getHasCaseload() {
//        fetch();
//        return hasCaseload;
//    }

	/**
	 * 
	 * @return Returns the jobTitle.
	 */
//	public SupervisionCode getJobTitle()
//	{
//		initJobTitle();
//		return jobTitle;
//	}

	/**
	 * Get the reference value to class :: pd.codetable.supervision.SupervisionCode
	 */
//	public String getJobTitleId()
//	{
//		fetch();
//		return jobTitleId;
//	}

	/**
	 * 
	 * @return Returns the location.
	 */
//	public Location getLocation()
//	{
//		initLocation();
//		return location;
//	}

	/**
	 * 
	 * @return Returns the locationDetails.
	 */
//	public String getLocationDetails()
//	{
//		fetch();
//		return locationDetails;
//	}

	/**
	 * Get the reference value to class :: pd.supervision.administerserviceprovider.administerlocation.Location
	 */
//	public String getLocationId()
//	{
//		fetch();
//		return locationId;
//	}

	/**
	 * 
	 * @return Returns the phoneNum.
	 */
//	public String getPhoneNum()
//	{
//		fetch();
//		return phoneNum;
//	}

	/**
	 * 
	 * @return Returns the positionName.
	 */
//	public String getPositionName()
//	{
//		fetch();
//		return positionName;
//	}

	/**
	 * 
	 * @return Returns the positionType.
	 */
//	public SupervisionCode getPositionType()
//	{
//		initPositionType();
//		return positionType;
//	}

	/**
	 * Get the reference value to class :: pd.codetable.supervision.SupervisionCode
	 */
//	public String getPositionTypeId()
//	{
//		fetch();
//		return positionTypeId;
//	}

	/**
	 * 
	 * @return Returns the probationOfficerInd.
	 */
//	public String getProbationOfficerInd()
//	{
//		fetch();
//		return probationOfficerInd;
//	}

	/**
	 * 
	 * @return Returns the programSection.
	 */
	public Organization getProgramSection()
	{
		initProgramSection();
		return programSection;
	}

	/**
	 * Get the reference value to class :: pd.supervision.supervisionstaff.Organization
	 */
	public String getProgramSectionId()
	{
		fetch();
		return programSectionId;
	}

	/**
	 * 
	 * @return Returns the programUnit.
	 */
	public Organization getProgramUnit()
	{
		initProgramUnit();
		return programUnit;
	}

	/**
	 * Get the reference value to class :: pd.supervision.supervisionstaff.Organization
	 */
	public String getProgramUnitId()
	{
		fetch();
		return programUnitId;
	}

	/**
	 * 
	 * @return Returns the staffPosition.
	 */
	public CSCDStaffPosition getStaffPosition()
	{
		initStaffPosition();
		return staffPosition;
	}

	/**
	 * Get the reference value to class :: pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	public String getStaffPositionId()
	{
		fetch();
		return staffPositionId;
	}

	/**
	 * 
	 * @return Returns the staffProfile.
	 */
//	public CSCDStaffProfile getStaffProfile()
//	{
//		initStaffProfile();
//		return staffProfile;
//	}

	/**
	 * Get the reference value to class :: pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffProfile
	 */
//	public String getStaffProfileId()
//	{
//		fetch();
//		return staffProfileId;
//	}

	/**
	 * 
	 * @return Returns the status.
	 */
//	public SupervisionCode getStatus()
//	{
//		initStatus();
//		return status;
//	}

	/**
	 * Get the reference value to class :: pd.codetable.supervision.SupervisionCode
	 */
//	public String getStatusId()
//	{
//		fetch();
//		return statusId;
//	}
    /**
     * @return Returns the updateDate.
     */
    public Date getUpdateDate() {
        fetch();
        return updateDate;
    }
    /**
     * @return Returns the updateUser.
     */
    public String getUpdateUser() {
        fetch();
        return updateUser;
    }

	/**
	 * 
	 * @return Returns the userProfile.
	 */
//	public UserProfile getUserProfile()
//	{
//		initUserProfile();
//		return userProfile;
//	}

	/**
	 * Initialize class relationship to class pd.contact.agency.Agency
	 */
	private void initAgency()
	{
		if (agency == null)
		{
			agency = (Agency) new mojo.km.persistence.Reference(agencyId,
					Agency.class).getObject();
		}
	}
	/**
	 * Initialize class relationship to class pd.codetable.supervision.SupervisionCode
	 */
//	private void initCstsOfficerType()
//	{
//		if (cstsOfficerType == null)
//		{
//			cstsOfficerType = (pd.codetable.supervision.SupervisionCode) new mojo.km.persistence.Reference(
//					cstsOfficerTypeId, pd.codetable.supervision.SupervisionCode.class).getObject();
//		}
//	}

	/**
	 * Initialize class relationship to class pd.supervision.supervisionstaff.Organization
	 */
	private void initDivision()
	{
		if (division == null)
		{
			division = (Organization) new mojo.km.persistence.Reference(divisionId,
					Organization.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.supervision.SupervisionCode
	 */
//	private void initJobTitle()
//	{
//		if (jobTitle == null)
//		{
//			jobTitle = (pd.codetable.supervision.SupervisionCode) new mojo.km.persistence.Reference(jobTitleId,
//					pd.codetable.supervision.SupervisionCode.class).getObject();
//		}
//	}

	/**
	 * Initialize class relationship to class pd.supervision.administerserviceprovider.administerlocation.Location
	 */
//	private void initLocation()
//	{
//		if (location == null)
//		{
//			location = (pd.supervision.administerserviceprovider.administerlocation.Location) new mojo.km.persistence.Reference(
//					locationId, pd.supervision.administerserviceprovider.administerlocation.Location.class).getObject();
//		}
//	}

	/**
	 * Initialize class relationship to class pd.codetable.supervision.SupervisionCode
	 */
//	private void initPositionType()
//	{
//		if (positionType == null)
//		{
//			positionType = (pd.codetable.supervision.SupervisionCode) new mojo.km.persistence.Reference(positionTypeId,
//					pd.codetable.supervision.SupervisionCode.class).getObject();
//		}
//	}

	/**
	 * Initialize class relationship to class pd.supervision.supervisionstaff.Organization
	 */
	private void initProgramSection()
	{
		if (programSection == null)
		{
			programSection = (Organization) new mojo.km.persistence.Reference(
					programSectionId, Organization.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.supervisionstaff.Organization
	 */
	private void initProgramUnit()
	{
		if (programUnit == null)
		{
			programUnit = (Organization) new mojo.km.persistence.Reference(
					programUnitId, Organization.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	private void initStaffPosition()
	{
		if (staffPosition == null)
		{
			staffPosition = (CSCDStaffPosition) new mojo.km.persistence.Reference(
					staffPositionId, CSCDStaffPosition.class)
					.getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffProfile
	 */
//	private void initStaffProfile()
//	{
//		if (staffProfile == null)
//		{
//			staffProfile = (pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffProfile) new mojo.km.persistence.Reference(
//					staffProfileId, pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffProfile.class)
//					.getObject();
//		}
//	}

	/**
	 * Initialize class relationship to class pd.codetable.supervision.SupervisionCode
	 */
//	private void initStatus()
//	{
//		if (status == null)
//		{
//			status = (pd.codetable.supervision.SupervisionCode) new mojo.km.persistence.Reference(statusId,
//					pd.codetable.supervision.SupervisionCode.class).getObject();
//		}
//	}

	/**
	 * Initialize class relationship to class pd.contact.user.UserProfile
	 */
//	private void initUserProfile()
//	{
//		if (userProfile == null)
//		{
//			userProfile = (pd.contact.user.UserProfile) new mojo.km.persistence.Reference(userProfileId,
//					pd.contact.user.UserProfile.class).getObject();
//		}
//	}

	/**
	 * set the type reference for class member agency
	 */
	public void setAgency(Agency agency)
	{
		if (this.agency == null || !this.agency.equals(agency))
		{
			markModified();
		}
		if (agency.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(agency);
		}
		setAgencyId("" + agency.getOID());
		this.agency = (Agency) new mojo.km.persistence.Reference(agency).getObject();
	}

	/**
	 * Set the reference value to class :: pd.contact.agency.Agency
	 */
	public void setAgencyId(String agencyId)
	{
		if (this.agencyId == null || !this.agencyId.equals(agencyId))
		{
			markModified();
		}
		agency = null;
		this.agencyId = agencyId;
	}
    /**
     * @param cjadNum The cjadNum to set.
     */
    public void setCjadNum(String cjadNum) {
		if (this.cjadNum == null || !this.cjadNum.equals(cjadNum))
		{
			markModified();
		}
        this.cjadNum = cjadNum;
    }
    /**
     * @param updateDate The updateDate to set.
     */
//    public void setCreateDate(Date createDate) {
//		if (this.createDate == null || !this.createDate.equals(createDate))
//		{
//			markModified();
//		}
//        this.createDate = createDate;
//    }
    /**
     * @param createUser The createUser to set.
     */
//    public void setCreateUser(String createUser) {
//		if (this.createUser == null || !this.createUser.equals(createUser))
//		{
//			markModified();
//		}
//
//        this.createUser = createUser;
//    }
	
    /**
     * @param cstsOfficerTypeId The cstsOfficerTypeId to set.
     */
//    public void setCstsOfficerTypeId(String cstsOfficerTypeId) {
//		if (this.cstsOfficerTypeId == null || !this.cstsOfficerTypeId.equals(cstsOfficerTypeId))
//		{
//			markModified();
//		}
//		cstsOfficerType = null;
//        this.cstsOfficerTypeId = cstsOfficerTypeId;
//    }

	/**
	 * set the type reference for class member division
	 */
	public void setDivision(Organization division)
	{
		if (this.division == null || !this.division.equals(division))
		{
			markModified();
		}
		if (division.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(division);
		}
		setDivisionId("" + division.getOID());
		this.division = (Organization) new mojo.km.persistence.Reference(division)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.supervisionstaff.Organization
	 */
	public void setDivisionId(String divisionId)
	{
		if (this.divisionId == null || !this.divisionId.equals(divisionId))
		{
			markModified();
		}
		division = null;
		this.divisionId = divisionId;
	}
    /**
     * @param hasCaseload The hasCaseload to set.
     */
//    public void setHasCaseload(boolean hasCaseload) {
//        this.hasCaseload = hasCaseload;
//    }

	/**
	 * set the type reference for class member jobTitle
	 */
//	public void setJobTitle(pd.codetable.supervision.SupervisionCode jobTitle)
//	{
//		if (this.jobTitle == null || !this.jobTitle.equals(jobTitle))
//		{
//			markModified();
//		}
//		if (jobTitle.getOID() == null)
//		{
//			new mojo.km.persistence.Home().bind(jobTitle);
//		}
//		setJobTitleId("" + jobTitle.getOID());
//		this.jobTitle = (pd.codetable.supervision.SupervisionCode) new mojo.km.persistence.Reference(jobTitle)
//				.getObject();
//	}
	/**
	 * set the type reference for class member cstsOfficerType
	 */
//	public void cstsOfficerType(pd.codetable.supervision.SupervisionCode cstsOfficerType)
//	{
//		if (this.cstsOfficerType == null || !this.cstsOfficerType.equals(cstsOfficerType))
//		{
//			markModified();
//		}
//		if (cstsOfficerType.getOID() == null)
//		{
//			new mojo.km.persistence.Home().bind(cstsOfficerType);
//		}
//		setCstsOfficerTypeId("" + cstsOfficerType.getOID());
//		this.cstsOfficerType = (pd.codetable.supervision.SupervisionCode) new mojo.km.persistence.Reference(cstsOfficerType)
//				.getObject();
//	}

	/**
	 * Set the reference value to class :: pd.codetable.supervision.SupervisionCode
	 */
//	public void setJobTitleId(String jobTitleId)
//	{
//		if (this.jobTitleId == null || !this.jobTitleId.equals(jobTitleId))
//		{
//			markModified();
//		}
//		jobTitle = null;
//		this.jobTitleId = jobTitleId;
//	}

	/**
	 * set the type reference for class member location
	 */
//	public void setLocation(pd.supervision.administerserviceprovider.administerlocation.Location location)
//	{
//		if (this.location == null || !this.location.equals(location))
//		{
//			markModified();
//		}
//		if (location.getOID() == null)
//		{
//			new mojo.km.persistence.Home().bind(location);
//		}
//		setLocationId("" + location.getOID());
//		this.location = (pd.supervision.administerserviceprovider.administerlocation.Location) new mojo.km.persistence.Reference(
//				location).getObject();
//	}

	/**
	 * 
	 * @param locationDetails The locationDetails to set.
	 */
//	public void setLocationDetails(String locationDetails)
//	{
//		if (this.locationDetails == null || !this.locationDetails.equals(locationDetails))
//		{
//			markModified();
//		}
//		this.locationDetails = locationDetails;
//	}

	/**
	 * Set the reference value to class :: pd.supervision.administerserviceprovider.administerlocation.Location
	 */
//	public void setLocationId(String locationId)
//	{
//		if (this.locationId == null || !this.locationId.equals(locationId))
//		{
//			markModified();
//		}
//		location = null;
//		this.locationId = locationId;
//	}

	/**
	 * 
	 * @param phoneNum The phoneNum to set.
	 */
//	public void setPhoneNum(String phoneNum)
//	{
//		if (this.phoneNum == null || !this.phoneNum.equals(phoneNum))
//		{
//			markModified();
//		}
//		this.phoneNum = phoneNum;
//	}

	/**
	 * 
	 * @param positionName The positionName to set.
	 */
//	public void setPositionName(String positionName)
//	{
//		if (this.positionName == null || !this.positionName.equals(positionName))
//		{
//			markModified();
//		}
//		this.positionName = positionName;
//	}

	/**
	 * set the type reference for class member positionType
	 */
//	public void setPositionType(pd.codetable.supervision.SupervisionCode positionType)
//	{
//		if (this.positionType == null || !this.positionType.equals(positionType))
//		{
//			markModified();
//		}
//		if (positionType.getOID() == null)
//		{
//			new mojo.km.persistence.Home().bind(positionType);
//		}
//		setPositionTypeId("" + positionType.getOID());
//		this.positionType = (pd.codetable.supervision.SupervisionCode) new mojo.km.persistence.Reference(positionType)
//				.getObject();
//	}
	/**
	 * Set the reference value to class :: pd.codetable.supervision.SupervisionCode
	 */
//	public void setPositionTypeId(String positionTypeId)
//	{
//		if (this.positionTypeId == null || !this.positionTypeId.equals(positionTypeId))
//		{
//			markModified();
//		}
//		positionType = null;
//		this.positionTypeId = positionTypeId;
//	}

	/**
	 * 
	 * @param probationOfficerInd The probationOfficerInd to set.
	 */
//	public void setProbationOfficerInd(String probationOfficerInd)
//	{
//		if (this.probationOfficerInd == null || !this.probationOfficerInd.equals(probationOfficerInd))
//		{
//			markModified();
//		}
//		this.probationOfficerInd = probationOfficerInd;
//	}

	/**
	 * set the type reference for class member programSection
	 */
	public void setProgramSection(Organization programSection)
	{
		if (this.programSection == null || !this.programSection.equals(programSection))
		{
			markModified();
		}
		if (programSection.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(programSection);
		}
		setProgramSectionId("" + programSection.getOID());
		this.programSection = (Organization) new mojo.km.persistence.Reference(
				programSection).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.supervisionstaff.Organization
	 */
	public void setProgramSectionId(String programSectionId)
	{
		if (this.programSectionId == null || !this.programSectionId.equals(programSectionId))
		{
			markModified();
		}
		programSection = null;
		this.programSectionId = programSectionId;
	}

	/**
	 * set the type reference for class member programUnit
	 */
	public void setProgramUnit(Organization programUnit)
	{
		if (this.programUnit == null || !this.programUnit.equals(programUnit))
		{
			markModified();
		}
		if (programUnit.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(programUnit);
		}
		setProgramUnitId("" + programUnit.getOID());
		this.programUnit = (Organization) new mojo.km.persistence.Reference(programUnit)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.supervisionstaff.Organization
	 */
	public void setProgramUnitId(String programUnitId)
	{
		if (this.programUnitId == null || !this.programUnitId.equals(programUnitId))
		{
			markModified();
		}
		programUnit = null;
		this.programUnitId = programUnitId;
	}

	/**
	 * set the type reference for class member staffPosition
	 */
//	public void setStaffPosition(pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition staffPosition)
//	{
//		if (this.staffPosition == null || !this.staffPosition.equals(staffPosition))
//		{
//			markModified();
//		}
//		if (staffPosition.getOID() == null)
//		{
//			new mojo.km.persistence.Home().bind(staffPosition);
//		}
//		setStaffPositionId("" + staffPosition.getOID());
//		this.staffPosition = (pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition) new mojo.km.persistence.Reference(
//				staffPosition).getObject();
//	}

	/**
	 * Set the reference value to class :: pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	public void setStaffPositionId(String staffPositionId)
	{
		if (this.staffPositionId == null || !this.staffPositionId.equals(staffPositionId))
		{
			markModified();
		}
		staffPosition = null;
		this.staffPositionId = staffPositionId;
	}

	/**
	 * set the type reference for class member staffProfile
	 */
//	public void setStaffProfile(pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffProfile staffProfile)
//	{
//		if (this.staffProfile == null || !this.staffProfile.equals(staffProfile))
//		{
//			markModified();
//		}
//		if (staffProfile.getOID() == null)
//		{
//			new mojo.km.persistence.Home().bind(staffProfile);
//		}
//		setStaffProfileId("" + staffProfile.getOID());
//		this.staffProfile = (pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffProfile) new mojo.km.persistence.Reference(
//				staffProfile).getObject();
//	}

	/**
	 * Set the reference value to class :: pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffProfile
	 */
//	public void setStaffProfileId(String staffProfileId)
//	{
//		if (this.staffProfileId == null || !this.staffProfileId.equals(staffProfileId))
//		{
//			markModified();
//		}
//		staffProfile = null;
//		this.staffProfileId = staffProfileId;
//	}

	/**
	 * set the type reference for class member status
	 */
//	public void setStatus(pd.codetable.supervision.SupervisionCode status)
//	{
//		if (this.status == null || !this.status.equals(status))
//		{
//			markModified();
//		}
//		if (status.getOID() == null)
//		{
//			new mojo.km.persistence.Home().bind(status);
//		}
//		setStatusId("" + status.getOID());
//		this.status = (pd.codetable.supervision.SupervisionCode) new mojo.km.persistence.Reference(status).getObject();
//	}

	/**
	 * Set the reference value to class :: pd.codetable.supervision.SupervisionCode
	 */
//	public void setStatusId(String statusId)
//	{
//		if (this.statusId == null || !this.statusId.equals(statusId))
//		{
//			markModified();
//		}
//		status = null;
//		this.statusId = statusId;
//	}
    /**
     * @param updateDate The updateDate to set.
     */
//    public void setUpdateDate(Date updateDate) {
//		if (this.updateDate == null || !this.updateDate.equals(updateDate))
//		{
//			markModified();
//		}
//
//        this.updateDate = updateDate;
//    }
//    /**
//     * @param updateUser The updateUser to set.
//     */
//    public void setUpdateUser(String updateUser) {
//		if (this.updateUser == null || !this.updateUser.equals(updateUser))
//		{
//			markModified();
//		}
//
//       this.updateUser = updateUser;
//   }

	/**
	 * set the type reference for class member userProfile
	 */
//	public void setUserProfile(pd.contact.user.UserProfile userProfile)
//	{
//		if (this.userProfile == null || !this.userProfile.equals(userProfile))
//		{
//			markModified();
//		}
//		if (userProfile.getOID() == null)
//		{
//			new mojo.km.persistence.Home().bind(userProfile);
//		}
//		setUserProfileId("" + userProfile.getOID());
//		this.userProfile = (pd.contact.user.UserProfile) new mojo.km.persistence.Reference(userProfile).getObject();
//	}
	
	public String getParentPositionId() {
		return super.getParentPositionId();
	}
	public void setParentPositionId(String parentPositionId) {
		super.setParentPositionId(parentPositionId);
	}

//	public String getLegacyProgramUnit()
//	{
//		return legacyProgramUnit;
//	}
//	public void setLegacyProgramUnit(String legacyProgramUnit)
//	{
//		this.legacyProgramUnit = legacyProgramUnit;
//	}
}
