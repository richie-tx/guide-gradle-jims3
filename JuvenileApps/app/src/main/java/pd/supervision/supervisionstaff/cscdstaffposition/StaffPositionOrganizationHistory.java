package pd.supervision.supervisionstaff.cscdstaffposition;

import mojo.km.persistence.Home;
import pd.supervision.administerserviceprovider.administerlocation.Location;
import mojo.km.persistence.IHome;
import pd.supervision.supervisionstaff.Organization;
import mojo.km.persistence.PersistentObject;
import pd.contact.user.UserProfile;
import pd.codetable.supervision.SupervisionCode;
import java.util.Date;
import java.util.Iterator;
import mojo.km.messaging.IEvent;

/**
 * Properties for jobTitle
 */
public class StaffPositionOrganizationHistory extends PersistentObject
{

	/**
	 * 
	 * @return 
	 * @param event
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, StaffPositionOrganizationHistory.class);
	}
	private String cjadNum;
	/**
	 * Properties for cstsOfficerType
	 */
	private SupervisionCode cstsOfficerType = null;
	private String cstsOfficerTypeId;
	private boolean hasCaseload;
	/**
	 * Properties for jobTitle
	 */
	private SupervisionCode jobTitle = null;
	private String jobTitleId;
	/**
	 * Properties for location
	 */
	private Location location = null;
	private String locationDetails;
	private String locationId;
	/**
	 * Properties for organization
	 */
	private Organization organization = null;
	private String organizationId;
	/**
	 * Properties for parentPosition
	 */
	private CSCDStaffPosition parentPosition = null;
	private String parentPositionId;
	private String phoneNum;
	/**
	 * Properties for position
	 */
	private CSCDStaffPosition position = null;
	private Date positionChangeDate;
	/**
	 * Properties for positionChangeUser
	 */
	private UserProfile positionChangeUser = null;
	private String positionChangeUserId;
	private String positionId;
	private String positionName;
	/**
	 * Properties for positionType
	 */
	private SupervisionCode positionType = null;
	private String positionTypeId;
	private String probationOfficerInd;
	/**
	 * Properties for status
	 */
	private SupervisionCode status = null;
	private String statusId;
	/**
	 * Properties for staff
	 */
	private UserProfile userProfile = null;
	private String userProfileId;
	private Date effectiveDate;
	/* Storing staffProfileId for research purposes only.  There can be no association to staffprofile
	 * because staffProfile records can be deleted.
	 */
	private String staffProfileId;

	/**
	 * 
	 * @roseuid 460BDC81025C
	 */
	public StaffPositionOrganizationHistory()
	{
	}

	/**
	 * @roseuid 460BD54700DA
	 */
	public void find()
	{
		fetch();
	}

	/**
	 * @roseuid 460BD54700CA
	 */
	public void findAll()
	{
		fetch();
	}

	/**
	 * 
	 * @return Returns the cjadNum.
	 */
	public String getCjadNum()
	{
		fetch();
		return cjadNum;
	}

	/**
	 * Gets referenced type pd.codetable.supervision.SupervisionCode
	 */
	public SupervisionCode getCstsOfficerType()
	{
		initCstsOfficerType();
		return cstsOfficerType;
	}

	/**
	 * Get the reference value to class :: pd.codetable.supervision.SupervisionCode
	 */
	public String getCstsOfficerTypeId()
	{
		fetch();
		return cstsOfficerTypeId;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @return Returns the getCaseload.
	 */
	public boolean getHasCaseload()
	{
		fetch();
		return hasCaseload;
	}

	/**
	 * Gets referenced type pd.codetable.supervision.SupervisionCode
	 */
	public SupervisionCode getJobTitle()
	{
		fetch();
		initJobTitle();
		return jobTitle;
	}

	/**
	 * Get the reference value to class :: pd.codetable.supervision.SupervisionCode
	 */
	public String getJobTitleId()
	{
		fetch();
		return jobTitleId;
	}

	/**
	 * Gets referenced type pd.supervision.administerserviceprovider.administerlocation.Location
	 */
	public Location getLocation()
	{
		fetch();
		initLocation();
		return location;
	}

	/**
	 * @return Returns the locationDetails.
	 */
	public String getLocationDetails()
	{
		fetch();
		return locationDetails;
	}

	/**
	 * Get the reference value to class :: pd.supervision.administerserviceprovider.administerlocation.Location
	 */
	public String getLocationId()
	{
		fetch();
		return locationId;
	}

	/**
	 * Gets referenced type pd.supervision.supervisionstaff.Organization
	 */
	public Organization getOrganization()
	{
		fetch();
		initOrganization();
		return organization;
	}

	/**
	 * Get the reference value to class :: pd.supervision.supervisionstaff.Organization
	 */
	public String getOrganizationId()
	{
		fetch();
		return organizationId;
	}

	/**
	 * Gets referenced type pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	public CSCDStaffPosition getParentPosition()
	{
		fetch();
		initParentPosition();
		return parentPosition;
	}

	/**
	 * Get the reference value to class :: pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	public String getParentPositionId()
	{
		fetch();
		return parentPositionId;
	}

	/**
	 * @return Returns the phoneNum.
	 */
	public String getPhoneNum()
	{
		fetch();
		return phoneNum;
	}

	/**
	 * Gets referenced type pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	public CSCDStaffPosition getPosition()
	{
		fetch();
		initPosition();
		return position;
	}

	/**
	 * @return Returns the positionChangeDate.
	 */
	public Date getPositionChangeDate()
	{
		fetch();
		return positionChangeDate;
	}

	/**
	 * Gets referenced type pd.contact.user.UserProfile
	 */
	public UserProfile getPositionChangeUser()
	{
		fetch();
		initPositionChangeUser();
		return positionChangeUser;
	}

	/**
	 * Get the reference value to class :: pd.contact.user.UserProfile
	 */
	public String getPositionChangeUserId()
	{
		fetch();
		return positionChangeUserId;
	}

	/**
	 * Get the reference value to class :: pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	public String getPositionId()
	{
		fetch();
		return positionId;
	}

	/**
	 * @return Returns the positionName.
	 */
	public String getPositionName()
	{
		fetch();
		return positionName;
	}

	/**
	 * Gets referenced type pd.codetable.supervision.SupervisionCode
	 */
	public SupervisionCode getPositionType()
	{
		fetch();
		initPositionType();
		return positionType;
	}

	/**
	 * Get the reference value to class :: pd.codetable.supervision.SupervisionCode
	 */
	public String getPositionTypeId()
	{
		fetch();
		return positionTypeId;
	}

	/**
	 * @return Returns the probationOfficerInd.
	 */
	public String getProbationOfficerInd()
	{
		fetch();
		return probationOfficerInd;
	}

	/**
	 * @return
	 */
	public String getStaffProfileId() {
		fetch();
		return staffProfileId;
	}

	/**
	 * Gets referenced type pd.codetable.supervision.SupervisionCode
	 */
	public SupervisionCode getStatus()
	{
		fetch();
		initStatus();
		return status;
	}

	/**
	 * Get the reference value to class :: pd.codetable.supervision.SupervisionCode
	 */
	public String getStatusId()
	{
		fetch();
		return statusId;
	}

	/**
	 * Gets referenced type pd.contact.user.UserProfile
	 */
	public UserProfile getUserProfile()
	{
		initUserProfile();
		fetch();
		initStaff();
		return userProfile;
	}

	/**
	 * Get the reference value to class :: pd.contact.user.UserProfile
	 */
	public String getUserProfileId()
	{
		fetch();
		return userProfileId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.supervision.SupervisionCode
	 */
	private void initCstsOfficerType()
	{
		if (cstsOfficerType == null)
		{
			cstsOfficerType = (SupervisionCode) new mojo.km.persistence.Reference(
					cstsOfficerTypeId, SupervisionCode.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.supervision.SupervisionCode
	 */
	private void initJobTitle()
	{
		if (jobTitle == null)
		{
			jobTitle = (SupervisionCode) new mojo.km.persistence.Reference(jobTitleId,
					SupervisionCode.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.administerserviceprovider.administerlocation.Location
	 */
	private void initLocation()
	{
		if (location == null)
		{
			location = (Location) new mojo.km.persistence.Reference(
					locationId, Location.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.supervisionstaff.Organization
	 */
	private void initOrganization()
	{
		if (organization == null)
		{
			organization = (Organization) new mojo.km.persistence.Reference(
					organizationId, Organization.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	private void initParentPosition()
	{
		if (parentPosition == null)
		{
			parentPosition = (CSCDStaffPosition) new mojo.km.persistence.Reference(
					parentPositionId, CSCDStaffPosition.class)
					.getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	private void initPosition()
	{
		if (position == null)
		{
			position = (CSCDStaffPosition) new mojo.km.persistence.Reference(
					positionId, CSCDStaffPosition.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.contact.user.UserProfile
	 */
	private void initPositionChangeUser()
	{
		if (positionChangeUser == null)
		{
			positionChangeUser = (UserProfile) new mojo.km.persistence.Reference(positionChangeUserId,
					UserProfile.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.supervision.SupervisionCode
	 */
	private void initPositionType()
	{
		if (positionType == null)
		{
			positionType = (SupervisionCode) new mojo.km.persistence.Reference(positionTypeId,
					SupervisionCode.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.contact.user.UserProfile
	 */
	private void initStaff()
	{
		if (userProfile == null)
		{
			userProfile = (UserProfile) new mojo.km.persistence.Reference(userProfileId,
					UserProfile.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.supervision.SupervisionCode
	 */
	private void initStatus()
	{
		if (status == null)
		{
			status = (SupervisionCode) new mojo.km.persistence.Reference(statusId,
					SupervisionCode.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.contact.user.UserProfile
	 */
	private void initUserProfile()
	{
		if (userProfile == null)
		{
			userProfile = (UserProfile) new mojo.km.persistence.Reference(userProfileId,
					UserProfile.class).getObject();
		}
	}

	/**
	 * 
	 * @param cjadNum The cjadNum to set.
	 */
	public void setCjadNum(String cjadNum)
	{
		if (this.cjadNum == null || !this.cjadNum.equals(cjadNum))
		{
			markModified();
		}
		this.cjadNum = cjadNum;
	}

	/**
	 * set the type reference for class member cstsOfficerType
	 */
	public void setCstsOfficerType(SupervisionCode cstsOfficerType)
	{
		if (this.cstsOfficerType == null || !this.cstsOfficerType.equals(cstsOfficerType))
		{
			markModified();
		}
		if (cstsOfficerType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(cstsOfficerType);
		}
		setCstsOfficerTypeId("" + cstsOfficerType.getOID());
		this.cstsOfficerType = (SupervisionCode) new mojo.km.persistence.Reference(
				cstsOfficerType).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.supervision.SupervisionCode
	 */
	public void setCstsOfficerTypeId(String cstsOfficerTypeId)
	{
		if (this.cstsOfficerTypeId == null || !this.cstsOfficerTypeId.equals(cstsOfficerTypeId))
		{
			markModified();
		}
		cstsOfficerType = null;
		this.cstsOfficerTypeId = cstsOfficerTypeId;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @param hasCaseload The hasCaseload to set.
	 */
	public void setHasCaseload(boolean hasCaseload)
	{
		if (this.hasCaseload != hasCaseload)
		{
			markModified();
		}
		this.hasCaseload = hasCaseload;
	}

	/**
	 * set the type reference for class member jobTitle
	 */
	public void setJobTitle(SupervisionCode jobTitle)
	{
		if (this.jobTitle == null || !this.jobTitle.equals(jobTitle))
		{
			markModified();
		}
		if (jobTitle.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(jobTitle);
		}
		setJobTitleId("" + jobTitle.getOID());
		this.jobTitle = (SupervisionCode) new mojo.km.persistence.Reference(jobTitle)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.supervision.SupervisionCode
	 */
	public void setJobTitleId(String jobTitleId)
	{
		if (this.jobTitleId == null || !this.jobTitleId.equals(jobTitleId))
		{
			markModified();
		}
		jobTitle = null;
		this.jobTitleId = jobTitleId;
	}

	/**
	 * set the type reference for class member location
	 */
	public void setLocation(Location location)
	{
		if (this.location == null || !this.location.equals(location))
		{
			markModified();
		}
		if (location.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(location);
		}
		setLocationId("" + location.getOID());
		this.location = (Location) new mojo.km.persistence.Reference(
				location).getObject();
	}

	/**
	 * @param locationDetails The locationDetails to set.
	 */
	public void setLocationDetails(String locationDetails)
	{
		if (this.locationDetails == null || !this.locationDetails.equals(locationDetails))
		{
			markModified();
		}
		this.locationDetails = locationDetails;
	}

	/**
	 * Set the reference value to class :: pd.supervision.administerserviceprovider.administerlocation.Location
	 */
	public void setLocationId(String locationId)
	{
		if (this.locationId == null || !this.locationId.equals(locationId))
		{
			markModified();
		}
		location = null;
		this.locationId = locationId;
	}

	/**
	 * set the type reference for class member organization
	 */
	public void setOrganization(Organization organization)
	{
		if (this.organization == null || !this.organization.equals(organization))
		{
			markModified();
		}
		if (organization.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(organization);
		}
		setOrganizationId("" + organization.getOID());
		this.organization = (Organization) new mojo.km.persistence.Reference(
				organization).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.supervisionstaff.Organization
	 */
	public void setOrganizationId(String organizationId)
	{
		if (this.organizationId == null || !this.organizationId.equals(organizationId))
		{
			markModified();
		}
		organization = null;
		this.organizationId = organizationId;
	}

	/**
	 * set the type reference for class member parentPosition
	 */
	public void setParentPosition(CSCDStaffPosition parentPosition)
	{
		if (this.parentPosition == null || !this.parentPosition.equals(parentPosition))
		{
			markModified();
		}
		if (parentPosition.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(parentPosition);
		}
		setParentPositionId("" + parentPosition.getOID());
		this.parentPosition = (CSCDStaffPosition) new mojo.km.persistence.Reference(
				parentPosition).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	public void setParentPositionId(String parentPositionId)
	{
		if (this.parentPositionId == null || !this.parentPositionId.equals(parentPositionId))
		{
			markModified();
		}
		parentPosition = null;
		this.parentPositionId = parentPositionId;
	}

	/**
	 * @param phoneNum The phoneNum to set.
	 */
	public void setPhoneNum(String phoneNum)
	{
		if (this.phoneNum == null || !this.phoneNum.equals(phoneNum))
		{
			markModified();
		}
		this.phoneNum = phoneNum;
	}

	/**
	 * set the type reference for class member position
	 */
	public void setPosition(CSCDStaffPosition position)
	{
		if (this.position == null || !this.position.equals(position))
		{
			markModified();
		}
		if (position.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(position);
		}
		setPositionId("" + position.getOID());
		this.position = (CSCDStaffPosition) new mojo.km.persistence.Reference(
				position).getObject();
	}

	/**
	 * @param positionChangeDate The positionChangeDate to set.
	 */
	public void setPositionChangeDate(Date positionChangeDate)
	{
		if (this.positionChangeDate == null || !this.positionChangeDate.equals(positionChangeDate))
		{
			markModified();
		}
		this.positionChangeDate = positionChangeDate;
	}

	/**
	 * set the type reference for class member positionChangeUser
	 */
	public void setPositionChangeUser(UserProfile positionChangeUser)
	{
		if (this.positionChangeUser == null || !this.positionChangeUser.equals(positionChangeUser))
		{
			markModified();
		}
		if (positionChangeUser.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(positionChangeUser);
		}
		setPositionChangeUserId("" + positionChangeUser.getOID());
		this.positionChangeUser = (UserProfile) new mojo.km.persistence.Reference(positionChangeUser)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.contact.user.UserProfile
	 */
	public void setPositionChangeUserId(String positionChangeUserId)
	{
		if (this.positionChangeUserId == null || !this.positionChangeUserId.equals(positionChangeUserId))
		{
			markModified();
		}
		positionChangeUser = null;
		this.positionChangeUserId = positionChangeUserId;
	}

	/**
	 * Set the reference value to class :: pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	public void setPositionId(String positionId)
	{
		if (this.positionId == null || !this.positionId.equals(positionId))
		{
			markModified();
		}
		position = null;
		this.positionId = positionId;
	}

	/**
	 * @param positionName The positionName to set.
	 */
	public void setPositionName(String positionName)
	{
		if (this.positionName == null || !this.positionName.equals(positionName))
		{
			markModified();
		}
		this.positionName = positionName;
	}

	/**
	 * set the type reference for class member positionType
	 */
	public void setPositionType(SupervisionCode positionType)
	{
		if (this.positionType == null || !this.positionType.equals(positionType))
		{
			markModified();
		}
		if (positionType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(positionType);
		}
		setPositionTypeId("" + positionType.getOID());
		this.positionType = (SupervisionCode) new mojo.km.persistence.Reference(positionType)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.supervision.SupervisionCode
	 */
	public void setPositionTypeId(String positionTypeId)
	{
		if (this.positionTypeId == null || !this.positionTypeId.equals(positionTypeId))
		{
			markModified();
		}
		positionType = null;
		this.positionTypeId = positionTypeId;
	}

	/**
	 * @param probationOfficerInd The probationOfficerInd to set.
	 */
	public void setProbationOfficerInd(String probationOfficerInd)
	{
		if (this.probationOfficerInd == null || !this.probationOfficerInd.equals(probationOfficerInd))
		{
			markModified();
		}
		this.probationOfficerInd = probationOfficerInd;
	}

	/**
	 * @param staffProfileId
	 */
	public void setStaffProfileId(String staffProfileId) {
		if (this.staffProfileId == null || !this.staffProfileId.equals(staffProfileId))
		{
			markModified();
		}

		this.staffProfileId = staffProfileId;
	}

	/**
	 * set the type reference for class member status
	 */
	public void setStatus(SupervisionCode status)
	{
		if (this.status == null || !this.status.equals(status))
		{
			markModified();
		}
		if (status.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(status);
		}
		setStatusId("" + status.getOID());
		this.status = (SupervisionCode) new mojo.km.persistence.Reference(status).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.supervision.SupervisionCode
	 */
	public void setStatusId(String statusId)
	{
		if (this.statusId == null || !this.statusId.equals(statusId))
		{
			markModified();
		}
		status = null;
		this.statusId = statusId;
	}

	/**
	 * set the type reference for class member staff
	 */
	public void setUserProfile(UserProfile staff)
	{
		if (this.userProfile == null || !this.userProfile.equals(staff))
		{
			markModified();
		}
		if (staff.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(staff);
		}
		setUserProfileId("" + staff.getOID());
		this.userProfile = (UserProfile) new mojo.km.persistence.Reference(staff).getObject();
	}

	/**
	 * Set the reference value to class :: pd.contact.user.UserProfile
	 */
	public void setUserProfileId(String staffId)
	{
		if (this.userProfileId == null || !this.userProfileId.equals(staffId))
		{
			markModified();
		}
		userProfile = null;
		this.userProfileId = staffId;
	}
}
