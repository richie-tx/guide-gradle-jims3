package pd.supervision.supervisionstaff.cscdstaffposition;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import messaging.cscdstaffposition.UpdateStaffPositionEvent;
import messaging.supervisionoptions.CourtRequestEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.CollectionUtil;
import naming.PDCodeTableConstants;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.contact.user.UserProfile;
import pd.security.PDSecurityHelper;
import pd.supervision.Court;
import pd.supervision.administercaseload.CaseAssignmentOrder;
import pd.supervision.administerserviceprovider.administerlocation.Location;
import pd.supervision.supervisionoptions.CommonSupervisionHelper;
import pd.supervision.supervisionstaff.Organization;

/**
 * 
 * @roseuid 460BDC51028B
 */
public class CSCDStaffPosition extends PersistentObject
{
	final static private String BLANK = "";
	/**
	 * Create new Staff Position
	 * @param updateEvent
	 */
	static public CSCDStaffPosition create(UpdateStaffPositionEvent updateEvent)
	{
		CSCDStaffPosition staffPosition = new CSCDStaffPosition();
		staffPosition.setCstsOfficerTypeId(updateEvent.getCstsOfficerTypeId());
		staffPosition.setHasCaseload(updateEvent.getHasCaseload());
		staffPosition.setEffectiveDate(updateEvent.getEffectiveDate());			
		
		SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(updateEvent.getAgencyId(), PDCodeTableConstants.STAFF_JOB_TITLE, updateEvent.getJobTitleId());
		if (aCode != null){
		    staffPosition.setJobTitleId(aCode.getOID());
		}
		
		staffPosition.setLocationDetails(updateEvent.getLocationDetails());
		if (updateEvent.getLocationId() != null && !updateEvent.getLocationId().equals(""))
		{
			staffPosition.setLocationId(updateEvent.getLocationId());
		}
		else
		{
			staffPosition.setLocationId(null);
		}
		staffPosition.setOrganizationId(updateEvent.getOrganizationId());
		if (updateEvent.getParentPositionId() != null && !updateEvent.getParentPositionId().equals(""))
		{
			staffPosition.setParentPositionId(updateEvent.getParentPositionId());
		}
		else
		{
			staffPosition.setParentPositionId(null);
		}
		staffPosition.setPhoneNum(updateEvent.getPhoneNum());
		staffPosition.setPositionName(updateEvent.getPositionName());
		
		aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(updateEvent.getAgencyId(), PDCodeTableConstants.STAFF_POSITION_TYPE, updateEvent.getPositionTypeId());
		if (aCode != null){
		    staffPosition.setPositionTypeId(aCode.getOID());
		}
		if (updateEvent.getProbationOfficerInd() != null && !updateEvent.getProbationOfficerInd().equals("")){
		    staffPosition.setProbationOfficerInd(updateEvent.getProbationOfficerInd().toUpperCase());
		} else {
		    staffPosition.setProbationOfficerInd(null);
		}
		
		aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(updateEvent.getAgencyId(), PDCodeTableConstants.STAFF_STATUS, PDCodeTableConstants.STAFF_STATUS_ACTIVE);
		if (aCode != null){
		    staffPosition.setStatusId(aCode.getOID());
		}
		
		boolean staffOidGenerated = false;
		if (updateEvent.getCjadNum() != null && !updateEvent.getCjadNum().equals(BLANK))
		{
			CSCDStaffProfile staffProfile = new CSCDStaffProfile();
			staffProfile.setCjadNum(updateEvent.getCjadNum());
			staffPosition.setStaffProfile(staffProfile);
			staffOidGenerated = true;
		}
		else
		{
			staffPosition.setStaffProfileId(null);
		}
		Enumeration childEnum = updateEvent.getRequests();
		if (childEnum != null && childEnum.hasMoreElements())
		{
			staffOidGenerated = true;
			Map courtsMap = null;
			while (childEnum.hasMoreElements())
			{
				Object obj = childEnum.nextElement();
				if (courtsMap == null)
				{
					//Make 1 call to M204 to retrieve all courts
					courtsMap = CommonSupervisionHelper.buildCourtsMap();
				}
				if (obj instanceof CourtRequestEvent)
				{
					CourtRequestEvent cre = (CourtRequestEvent) obj;
					Court court = (Court) courtsMap.get(cre.getCourtId());
					staffPosition.insertCourts(court);
				}
			}
		}
		if (!staffOidGenerated)
		{
			IHome home = new Home();
			home.bind(staffPosition);
		}
		//create history
		CSCDStaffPosition.createHistory(staffPosition);
		return staffPosition;
	}

	/**
	 * @param staffPosition
	 * @param programUnitId
	 */
	static public void createHistory(CSCDStaffPosition staffPosition)
	{
		StaffPositionOrganizationHistory history = new StaffPositionOrganizationHistory();
		if (staffPosition.getStaffProfileId() != null && !staffPosition.getStaffProfileId().equals(BLANK))
		{
			CSCDStaffProfile staffProfile = staffPosition.getStaffProfile();
			if (staffProfile != null){
			    history.setCjadNum(staffProfile.getCjadNum());
			}
		}
		history.setCstsOfficerTypeId(staffPosition.getCstsOfficerTypeId());
		history.setHasCaseload(staffPosition.getHasCaseload());
		history.setJobTitleId(staffPosition.getJobTitleId());
		history.setLocationDetails(staffPosition.getLocationDetails());
		history.setLocationId(staffPosition.getLocationId());
		history.setOrganizationId(staffPosition.getOrganizationId());
		history.setParentPositionId(staffPosition.getParentPositionId());
		history.setPhoneNum(staffPosition.getPhoneNum());
		history.setPositionChangeDate(new Date());
		history.setPositionChangeUserId(PDSecurityHelper.getLogonId());
		history.setPositionId(staffPosition.getOID());
		history.setPositionName(staffPosition.getPositionName());
		history.setPositionTypeId(staffPosition.getPositionTypeId());
		history.setProbationOfficerInd(staffPosition.getProbationOfficerInd());
		history.setStatusId(staffPosition.getStatusId());
		history.setUserProfileId(staffPosition.getUserProfileId());
		history.setEffectiveDate(staffPosition.getEffectiveDate());
		history.setStaffProfileId(staffPosition.getStaffProfileId());
		staffPosition.insertHistories(history);
	}

	/**
	 * 
	 * @roseuid 460BD54700DA
	 */
	static public CSCDStaffPosition find(String staffPositionId)
	{
		IHome home = new Home();
		return (CSCDStaffPosition) home.find(staffPositionId, CSCDStaffPosition.class);
	}

	/**
	 * @param event
	 * @return 
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, CSCDStaffPosition.class);
	}

	/**
	 * @param attrName
	 * @param attrValue
	 * @return 
	 */
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		return home.findAll(attrName, attrValue, CSCDStaffPosition.class);
	}

	/**
	 * @param updateEvent
	 * @return 
	 */
	static public CSCDStaffPosition update(UpdateStaffPositionEvent updateEvent)
	{
		CSCDStaffPosition staffPosition = CSCDStaffPosition.find(updateEvent.getPositionId());

		staffPosition.setParentPositionId(updateEvent.getParentPositionId());   
		
		SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(updateEvent.getAgencyId(), PDCodeTableConstants.STAFF_JOB_TITLE, updateEvent.getJobTitleId());
		if (aCode != null){
		    staffPosition.setJobTitleId(aCode.getOID());
		}
		staffPosition.setLocationDetails(updateEvent.getLocationDetails());
		if (updateEvent.getLocationId() != null && !updateEvent.getLocationId().equals(""))
		{
			staffPosition.setLocationId(updateEvent.getLocationId());
		}
		else
		{
			staffPosition.setLocationId(null);
		}
		staffPosition.setOrganizationId(updateEvent.getOrganizationId());
		staffPosition.setPhoneNum(updateEvent.getPhoneNum());
		staffPosition.setPositionName(updateEvent.getPositionName());
		staffPosition.setEffectiveDate(updateEvent.getEffectiveDate());			
		staffPosition.setCstsOfficerTypeId(updateEvent.getCstsOfficerTypeId());
		aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(updateEvent.getAgencyId(), PDCodeTableConstants.STAFF_POSITION_TYPE, updateEvent.getPositionTypeId());
		if (aCode != null){
		    staffPosition.setPositionTypeId(aCode.getOID());
		}
		if (updateEvent.getProbationOfficerInd() != null && !updateEvent.getProbationOfficerInd().equals("")){
		    staffPosition.setProbationOfficerInd(updateEvent.getProbationOfficerInd().toUpperCase());
		} else {
		    staffPosition.setProbationOfficerInd(null);
		}
		CSCDStaffProfile staffProfile = null;
		if (updateEvent.getCjadNum() != null && !updateEvent.getCjadNum().trim().equals(BLANK))
		{
			if (staffPosition.getStaffProfileId() == null || staffPosition.getStaffProfileId().equals(BLANK))
			{
				staffProfile = new CSCDStaffProfile();
			}
			else
			{
				staffProfile = staffPosition.getStaffProfile();
				if (staffProfile == null){
				    staffProfile = new CSCDStaffProfile();
				}
			}
			staffProfile.setCjadNum(updateEvent.getCjadNum());
			staffPosition.setStaffProfile(staffProfile);
		}
		else if (staffPosition.getStaffProfileId() != null && !staffPosition.getStaffProfileId().equals(BLANK))
		{
			staffProfile = staffPosition.getStaffProfile();
			if (staffProfile != null){
			    staffProfile.delete();
			    staffPosition.setStaffProfileId(null);
			}
		}
		Enumeration anEnum = updateEvent.getRequests();
		Map newCourtsMap = new HashMap();
		if (anEnum != null && anEnum.hasMoreElements())
		{
			Object obj = null;
			while (anEnum.hasMoreElements())
			{
				obj = anEnum.nextElement();
				if (obj instanceof CourtRequestEvent)
				{
					CourtRequestEvent cre = (CourtRequestEvent) obj;
					newCourtsMap.put(cre.getCourtId(), cre.getCourtId());
				}
			}
		}
		if (newCourtsMap.size() == 0)
		{
		    //staffPosition.clearCourts();
		    List courtList = CollectionUtil.iteratorToList(staffPosition.getCourts().iterator());
		    Court aCourt = null;
		    for (int i = 0; i < courtList.size(); i++) {
                aCourt = (Court) courtList.get(i);
                staffPosition.removeCourts(aCourt);
            }
		}
		else
		{
			staffPosition.updateCourts(staffPosition, newCourtsMap);
		}
		CSCDStaffPosition.createHistory(staffPosition);
		return staffPosition;
	}
	/**
	 * Properties for childStaffPositions
	 * @detailerDoNotGenerate true
	 * @referencedType pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 * @associationType simple
	 */
	private java.util.Collection childStaffPositions = null;
	/**
	 * Properties for courts
	 * @detailerDoNotGenerate true
	 * @referencedType pd.supervision.Court
	 * @associationType simple
	 */
	private java.util.Collection courts = null;
	/**
	 * Properties for cstsOfficerType
	 */
	private SupervisionCode cstsOfficerType = null;
	private String cstsOfficerTypeId;
	private boolean hasCaseload;
	/**
	 * Properties for histories
	 * @referencedType pd.supervision.supervisionstaff.cscdstaffposition.StaffPositionOrganizationHistory
	 */
	private java.util.Collection histories = null;
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
	private String positionName;
	/**
	 * Properties for positionType
	 */
	private SupervisionCode positionType = null;
	private String positionTypeId;
	private String probationOfficerInd;
	/**
	 * Properties for staffProfile
	 */
	private CSCDStaffProfile staffProfile = null;
	private String staffProfileId;
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
	private Date retirementDate;

    private String jobTitleCode;
    private String jobTitleDesc;
    private String parentPositionDesc;
    private String positionTypeDesc;
    private String positionTypeCode;
    private String positionStatusDesc;
    private String positionStatusCode;
    private String officerTypeDesc;
	
	/**
	 * 
	 * @roseuid 460BDC51028B
	 */
	public CSCDStaffPosition()
	{
		this.childStaffPositions = new ArrayList() ; 
	}

	/**
	 * 
	 * @roseuid 460BD54700DC
	 */
	public void assignStaffPosition()
	{
		markModified();
	}

	/**
	 * 
	 * @roseuid 460BD54700D9
	 */
	public void bind()
	{
		IHome home = new Home();
		home.bind(this);
	}

	/**
	 * Clears all
	 * pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition from
	 * class relationship collection.
	 */
	public void clearChildStaffPositions()
	{
		initChildStaffPositions();
		childStaffPositions.clear();
	}

	/**
	 * Clears all pd.supervision.Court from class relationship collection.
	 */
	public void clearCourts()
	{
		initCourts();
		courts.clear();
	}

	/**
	 * Clears all
	 * pd.supervision.supervisionstaff.cscdstaffposition.StaffPositionOrganizationHistory
	 * from class relationship collection.
	 */
	public void clearHistories()
	{
		initHistories();
		histories.clear();
	}

	/**
	 * returns a collection of
	 * pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	public java.util.Collection getChildStaffPositions()
	{
		fetch();
		initChildStaffPositions();
		ArrayList retVal = new ArrayList();
		Iterator i = childStaffPositions.iterator();
		Object obj =  null;
		while (i.hasNext())
		{
			obj = i.next();
			    CSCDStaffPosition actual = (CSCDStaffPosition) obj;
			    retVal.add(actual);
		}
		return retVal;
	}

	/**
	 * returns a collection of pd.supervision.Court
	 */
	public java.util.Collection getCourts()
	{
		fetch();
		initCourts();
		ArrayList retVal = new ArrayList();
		Iterator i = courts.iterator();
		while (i.hasNext())
		{
			CSCDStaffPositionCourt actual = (CSCDStaffPositionCourt) i
					.next();
			retVal.add(actual.getCourt());
		}
		return retVal;
	}

	/**
	 * Gets referenced type pd.codetable.supervision.SupervisionCode
	 */
	public SupervisionCode getCstsOfficerType()
	{
		fetch();
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

	/**
	 * 
	 * @return Returns the hasCaseload.
	 */
	public boolean getHasCaseload()
	{
		fetch();
		return hasCaseload;
	}

	/**
	 * returns a collection of
	 * pd.supervision.supervisionstaff.cscdstaffposition.StaffPositionOrganizationHistory
	 */
	public java.util.Collection getHistories()
	{
		fetch();
		initHistories();
		return histories;
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
	 * Get the reference value to class ::
	 * pd.codetable.supervision.SupervisionCode
	 */
	public String getJobTitleId()
	{
		fetch();
		return jobTitleId;
	}

	/**
	 * Gets referenced type
	 * pd.supervision.administerserviceprovider.administerlocation.Location
	 */
	public Location getLocation()
	{
		fetch();
		initLocation();
		return location;
	}

	/**
	 * 
	 * @return Returns the locationDetails.
	 */
	public String getLocationDetails()
	{
		fetch();
		return locationDetails;
	}

	/**
	 * Get the reference value to class ::
	 * pd.supervision.administerserviceprovider.administerlocation.Location
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
	 * Get the reference value to class ::
	 * pd.supervision.supervisionstaff.Organization
	 */
	public String getOrganizationId()
	{
		fetch();
		return organizationId;
	}

	/**
	 * Gets referenced type
	 * pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	public CSCDStaffPosition getParentPosition()
	{
		fetch();
		initParentPosition();
		return parentPosition;
	}

	/**
	 * Get the reference value to class ::
	 * pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	public String getParentPositionId()
	{
		fetch();
		return parentPositionId;
	}

	/**
	 * 
	 * @return Returns the phoneNum.
	 */
	public String getPhoneNum()
	{
		fetch();
		return phoneNum;
	}

	/**
	 * 
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
	 * Get the reference value to class ::
	 * pd.codetable.supervision.SupervisionCode
	 */
	public String getPositionTypeId()
	{
		fetch();
		return positionTypeId;
	}

	/**
	 * 
	 * @return Returns the probationOfficerInd.
	 */
	public String getProbationOfficerInd()
	{
		fetch();
		return probationOfficerInd;
	}

	/**
	 * Gets referenced type
	 * pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffProfile
	 */
	public CSCDStaffProfile getStaffProfile()
	{
		fetch();
		initStaffProfile();
		return staffProfile;
	}

	/**
	 * Get the reference value to class ::
	 * pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffProfile
	 */
	public String getStaffProfileId()
	{
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
	 * Get the reference value to class ::
	 * pd.codetable.supervision.SupervisionCode
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
		fetch();
		initUserProfile();
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
	 * @return 
	 */
	public boolean hasActiveCaseload()
	{
        List caseList = CaseAssignmentOrder.findAllByOfficer(this.getOID());
        boolean activeCases = false;	
        if (caseList != null && caseList.size() > 0){
            activeCases = true;
        }
		return activeCases;
	}

	/**
	 * @return 
	 */
	public boolean hasActiveSubordinates()
	{
		boolean activeSubordinates = false;
		if (this.getChildStaffPositions() != null)
		{
	        SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDSecurityHelper.getUserAgencyId(), PDCodeTableConstants.STAFF_STATUS, PDCodeTableConstants.STAFF_STATUS_ACTIVE);
			Iterator iter = this.getChildStaffPositions().iterator();
			CSCDStaffPosition childStaffPos = null;
			while (iter.hasNext())
			{
				childStaffPos = (CSCDStaffPosition) iter.next();
				if (aCode.getOID().equals(childStaffPos.getStatusId()))
				{
					activeSubordinates = true;
					break;
				}
				else if (childStaffPos.hasActiveSubordinates())
				{
					activeSubordinates = true;
					break;
				}
			}
		}
		return activeSubordinates;
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	private void initChildStaffPositions()
	{
		if (childStaffPositions == null || childStaffPositions.size() == 0)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			childStaffPositions = new mojo.km.persistence.ArrayList(
					CSCDStaffPosition.class,
					"parentPositionId", "" + getOID());
		}
	}

	/**
	 * Initialize class relationship implementation for pd.supervision.Court
	 */
	private void initCourts()
	{
		if (courts == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			courts = new mojo.km.persistence.ArrayList(
					CSCDStaffPositionCourt.class, "staffPositionId",
					"" + getOID());
		}
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
	 * Initialize class relationship implementation for
	 * pd.supervision.supervisionstaff.cscdstaffposition.StaffPositionOrganizationHistory
	 */
	private void initHistories()
	{
		if (histories == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			histories = new mojo.km.persistence.ArrayList(
					StaffPositionOrganizationHistory.class,
					"positionId", "" + getOID());
		}
	}

	/**
	 * Initialize class relationship to class
	 * pd.codetable.supervision.SupervisionCode
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
	 * Initialize class relationship to class
	 * pd.supervision.administerserviceprovider.administerlocation.Location
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
	 * Initialize class relationship to class
	 * pd.supervision.supervisionstaff.Organization
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
	 * Initialize class relationship to class
	 * pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
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
	 * Initialize class relationship to class
	 * pd.codetable.supervision.SupervisionCode
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
	 * Initialize class relationship to class
	 * pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffProfile
	 */
	private void initStaffProfile()
	{
		if (staffProfile == null)
		{
			staffProfile = (CSCDStaffProfile) new mojo.km.persistence.Reference(
					staffProfileId, CSCDStaffProfile.class)
					.getObject();
		}
	}

	/**
	 * Initialize class relationship to class
	 * pd.codetable.supervision.SupervisionCode
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
	 * insert a pd.supervision.Court into class relationship collection.
	 */
	public void insertCourts(Court anObject)
	{
		initCourts();
		CSCDStaffPositionCourt actual = new CSCDStaffPositionCourt();
		if (this.getOID() == null)
		{
			new Home().bind(this);
		}
		if (anObject.getOID() == null)
		{
			new Home().bind(anObject);
		}
		actual.setCourt(anObject);
		actual.setStaffPosition(this);
		courts.add(actual);
	}

	/**
	 * insert a
	 * pd.supervision.supervisionstaff.cscdstaffposition.StaffPositionOrganizationHistory
	 * into class relationship collection.
	 */
	public void insertHistories(
			StaffPositionOrganizationHistory anObject)
	{
		initHistories();
		histories.add(anObject);
	}

	/**
	 * Removes a pd.supervision.Court from class relationship collection.
	 */
	public void removeCourts(Court anObject)
	{
	    initCourts();
		mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
		assocEvent.setChildId((String) anObject.getOID());
		assocEvent.setParentId((String) this.getOID());
		CSCDStaffPositionCourt actual = (CSCDStaffPositionCourt) new mojo.km.persistence.Reference(
				assocEvent, CSCDStaffPositionCourt.class).getObject();
		courts.remove(actual);
	}

	/**
	 * Removes a
	 * pd.supervision.supervisionstaff.cscdstaffposition.StaffPositionOrganizationHistory
	 * from class relationship collection.
	 */
	public void removeHistories(
			StaffPositionOrganizationHistory anObject)
	{
		initHistories();
		histories.remove(anObject);
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

	/**
	 * 
	 * @param hasCaseload
	 The hasCaseload to set.
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
	 * Set the reference value to class ::
	 * pd.codetable.supervision.SupervisionCode
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
	 * 
	 * @param locationDetails
	 The locationDetails to set.
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
	 * Set the reference value to class ::
	 * pd.supervision.administerserviceprovider.administerlocation.Location
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
	 * Set the reference value to class ::
	 * pd.supervision.supervisionstaff.Organization
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
	 * Set the reference value to class ::
	 * pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
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
	 * 
	 * @param phoneNum
	 The phoneNum to set.
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
	 * 
	 * @param positionName
	 The positionName to set.
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
	 * Set the reference value to class ::
	 * pd.codetable.supervision.SupervisionCode
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
	 * 
	 * @param probationOfficerInd
	 The probationOfficerInd to set.
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
	 * set the type reference for class member staffProfile
	 */
	public void setStaffProfile(CSCDStaffProfile staffProfile)
	{
		if (this.staffProfile == null || !this.staffProfile.equals(staffProfile))
		{
			markModified();
		}
		if (this.getOID() == null)
		{
			new Home().bind(this);
		}
		staffProfile.setStaffPositionId(this.getOID());
		if (staffProfile.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(staffProfile);
		}
		setStaffProfileId("" + staffProfile.getOID());
		this.staffProfile = (CSCDStaffProfile) new mojo.km.persistence.Reference(
				staffProfile).getObject();
		
			//persist staff position changes
		new Home().bind(this);
	}

	/**
	 * Set the reference value to class ::
	 * pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffProfile
	 */
	public void setStaffProfileId(String staffProfileId)
	{
		if (this.staffProfileId == null || !this.staffProfileId.equals(staffProfileId))
		{
			markModified();
		}
		staffProfile = null;
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
	 * Set the reference value to class ::
	 * pd.codetable.supervision.SupervisionCode
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

	/**
	 * @param staffPosition
	 * @param courtsToBeAdded
	 */
	private void updateCourts(CSCDStaffPosition staffPosition, Map courtsToBeAdded)
	{
		//markModified();
		Map existingCourtsMap = CSCDStaffPositionHelper.buildCourtsMap(staffPosition.getCourts());
		Set set = existingCourtsMap.keySet();
		Iterator iter = set.iterator();
		String aKey = null;
		Map courtsToBeRemoved = new HashMap();
		while (iter.hasNext())
		{
			aKey = (String) iter.next();
			if (courtsToBeAdded.containsKey(aKey))
			{
				//Remove existing courts from new courts.
				courtsToBeAdded.remove(aKey);
			}
			else
			{
				//Court association needs to be removed from staff.
				courtsToBeRemoved.put(aKey, aKey);
			}
		}
		Map allCourtsMap = CommonSupervisionHelper.buildCourtsMap();
		set = courtsToBeAdded.keySet();
		iter = set.iterator();
		Court court = null;
		if (iter != null && iter.hasNext())
		{
			while (iter.hasNext())
			{
				aKey = (String) iter.next();
				court = (Court) allCourtsMap.get(aKey);
				staffPosition.insertCourts(court);
			}
		}
		set = courtsToBeRemoved.keySet();
		iter = set.iterator();
		if (iter != null && iter.hasNext())
		{
			while (iter.hasNext())
			{
				aKey = (String) iter.next();
				court = (Court) allCourtsMap.get(aKey);
				staffPosition.removeCourts(court);
			}
		}
	}
	
	public void addChildStaffPositionFromService( CSCDStaffPosition staffPosition)
	{
	    this.childStaffPositions.add(staffPosition) ; 	

	}
	
	public void removeChildStaffPositionFromService( CSCDStaffPosition staffPosition)
	{
	  	this.childStaffPositions.remove(staffPosition) ; 
	}

	public String getJobTitleCode() {
		fetch();
		return jobTitleCode;
	}

	public void setJobTitleCode(String jobTitleCode) {
		this.jobTitleCode = jobTitleCode;
	}

	public String getJobTitleDesc() {
		fetch();
		return jobTitleDesc;
	}

	public void setJobTitleDesc(String jobTitleDesc) {
		this.jobTitleDesc = jobTitleDesc;
	}

	public String getOfficerTypeDesc() {
		fetch();
		return officerTypeDesc;
	}

	public void setOfficerTypeDesc(String officerTypeDesc) {
		this.officerTypeDesc = officerTypeDesc;
	}

	public String getParentPositionDesc() {
		fetch();
		return parentPositionDesc;
	}

	public void setParentPositionDesc(String parentPositionDesc) {
		fetch();
		this.parentPositionDesc = parentPositionDesc;
	}

	public String getPositionStatusDesc() {
		fetch();
		return positionStatusDesc;
	}

	public void setPositionStatusDesc(String positionStatusDesc) {
		this.positionStatusDesc = positionStatusDesc;
	}

	public String getPositionTypeDesc() {
		fetch();
		return positionTypeDesc;
	}

	public void setPositionTypeDesc(String positionTypeDesc) {
		this.positionTypeDesc = positionTypeDesc;
	}

	public Date getEffectiveDate() {
		fetch();
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		
    	
		if (this.effectiveDate == null || !this.effectiveDate.equals(effectiveDate))
		{		//indicate that change needs to be persisted to database if modified
			markModified();
		}
        
		this.effectiveDate = effectiveDate;
	}

	public String getPositionStatusCode()
	{
		fetch();
		return positionStatusCode;
	}

	public void setPositionStatusCode(String positionStatusCode)
	{
		this.positionStatusCode = positionStatusCode;
	}

	public String getPositionTypeCode()
	{
		fetch();
		return positionTypeCode;
	}

	public void setPositionTypeCode(String positionTypeCode)
	{
		this.positionTypeCode = positionTypeCode;
	}

	public Date getRetirementDate() {
		return retirementDate;
	}

	public void setRetirementDate(Date retirementDate) {
		this.retirementDate = retirementDate;
	}
	
}
