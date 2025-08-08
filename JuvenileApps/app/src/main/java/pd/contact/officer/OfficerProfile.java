package pd.contact.officer;

import java.util.Collection;
import java.util.Iterator;

import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.PDOfficerProfileConstants;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.to.PhoneNumberBean;
import messaging.identityaddress.domintf.IAddressable;
import messaging.officer.UpdateOfficerProfileEvent;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.security.IUserInfo;
import pd.codetable.Code;
import pd.contact.IContact;
import pd.contact.agency.Department;
import pd.contact.user.UserProfile;
import pd.security.PDSecurityHelper;
import pd.supervision.administerserviceprovider.administerlocation.Location;
import pd.transferobjects.helper.DepartmentHelper;
import pd.transferobjects.helper.UserProfileHelper;

/**
 * @roseuid 42E67D38037D
 */
public class OfficerProfile extends PersistentObject implements IContact, IUserInfo, IAddressable
{
    private String JIMSLogonId;

    private String JIMS2LogonId;

    private String userTypeId;

    private String JIMS2Password;

    private String password;

    private String agencyId;

    /**
     * Properties for addresses
     * 
     * @referencedType pd.contact.officer.OfficerProfileAddress
     * @detailerDoNotGenerate false
     */
    private Collection addresses;

    private String assignedArea;

    private String badgeNum;

    private String cellPhone;

    private String contactId;

    /**
     * @return Returns the managerId.
     */
    public String getManagerId()
    {
	fetch();
	return this.managerId;
    }

    /**
     * Properties for department
     * 
     * @referencedType pd.contact.agency.Department
     * @detailerDoNotGenerate true
     */
    private Department department = null;

    private String departmentId;

    private String division;

    private String email;

    private String faxLocation;

    private String faxNum;

    private String firstName;

    private String homePhoneNum;

    private Location juvLocation = null;

    private Code officerSubType = null;

    private String juvLocationId;

    /**
     * Properties for juvUnit
     * 
     * @referencedType pd.codetable.Code
     * @contextKey JUV_UNIT
     * @detailerDoNotGenerate true
     */
    private Code juvUnit = null;

    private String juvUnitId;

    private String lastName;

    private String logonId;

    /**
     * Properties for manager
     * 
     * @referencedType pd.contact.user.UserProfile
     * @detailerDoNotGenerate true
     */
    private UserProfile manager = null;

    private String managerId;

    private String managerFirstName;

    private String managerLastName;

    private String managerMiddleName;

    private String middleName;

    private String officerProfileId;

    /**
     * Properties for rank
     * 
     * @referencedType pd.codetable.Code
     * @contextKey OFFICER_TYPE
     * @detailerDoNotGenerate true
     */
    private Code officerType = null;

    private String officerTypeId;

    private String officerSubTypeId;

    private String pager;

    private String otherIdNum;

    private String phoneExt;

    private String phoneNum;

    private String radioNum;

    /**
     * Properties for rank
     * 
     * @referencedType pd.codetable.Code
     * @contextKey RANK
     * @detailerDoNotGenerate true
     */
    private Code rank = null;

    private String rankId;

    private String ssn;
    
    private String survey;
    private String supervisor;


    /**
     * Properties for rank
     * 
     * @referencedType pd.codetable.Code
     * @contextKey AGENCY_STATUS
     * @detailerDoNotGenerate true
     */
    private Code status = null;

    private String statusId;

    private String title;

    /**
     * Properties for user
     * 
     * @referencedType pd.contact.user.UserProfile
     * @detailerDoNotGenerate true
     */
    private UserProfile user = null;

    /**
     * Properties for workDays
     * 
     * @referencedType pd.contact.officer.WorkDay
     * @detailerDoNotGenerate true
     */
    private Collection workDays = null;

    private String workPhoneExtn;

    private String workPhoneNum;

    private String workShift;
    
    private String accountType;
    
    
   

    /**
     * @roseuid 42E67D38037D
     */
    public OfficerProfile()
    {
    }

    /**
     * Clears all pd.contact.officer.OfficerProfileAddress from class
     * relationship collection.
     */
    public void clearAddresses()
    {
	initAddresses();
	addresses.clear();
    }

    /**
     * Clears all pd.contact.officer.WorkDay from class relationship collection.
     */
    public void clearWorkDays()
    {
	initWorkDays();
	workDays.clear();
    }

    /**
     * returns a collection of pd.contact.officer.OfficerProfileAddress
     */
    public Collection getAddresses()
    {
	initAddresses();
	return addresses;
    }

    /**
     * Access method for the assignedArea property.
     * 
     * @return the current value of the assignedArea property
     */
    public String getAssignedArea()
    {
	fetch();
	return assignedArea;
    }

    /**
     * Access method for the badgeNum property.
     * 
     * @return the current value of the badgeNum property
     */
    public String getBadgeNum()
    {
	fetch();
	return badgeNum;
    }

    /**
     * Access method for the cellPhone property.
     * 
     * @return the current value of the cellPhone property
     */
    public String getCellPhone()
    {
	fetch();
	return cellPhone;
    }

    /**
     * String to format cellPhone;
     * 
     * @return
     */
    public String getCellPhoneNumberString()
    {
	StringBuffer buffer = new StringBuffer(25);

	if (getCellPhone() != null && !getCellPhone().equals(""))
	{
	    PhoneNumberBean cellPhoneNumberFormatter;
	    String officerCellPhoneNumber = getCellPhone();
	    cellPhoneNumberFormatter = new PhoneNumberBean(officerCellPhoneNumber);
	    buffer.append("cell phone: ");
	    buffer.append(cellPhoneNumberFormatter.getFormattedPhoneNumber());
	}
	String cellPhoneNumberString = buffer.toString();
	return cellPhoneNumberString;
    }

    /**
     * Access method for the contactId property.
     * 
     * @return the current value of the contactId property
     */
    public String getContactId()
    {
	fetch();
	return contactId;
    }

    /**
     * Gets referenced type pd.contact.agency.Department
     */
    public Department getDepartment()
    {
	fetch();
	initDepartment();
	return department;
    }

    /**
     * Get the reference value to class :: pd.contact.agency.Department
     */
    public String getDepartmentId()
    {
	fetch();
	return departmentId;
    }

    /**
     * Access method for the division property.
     * 
     * @return the current value of the division property
     */
    public String getDivision()
    {
	fetch();
	return division;
    }

    /**
     * Access method for the email property.
     * 
     * @return the current value of the email property
     */
    public String getEmail()
    {
	fetch();
	return email;
    }

    /**
     * Access method for the faxLocation property.
     * 
     * @return the current value of the faxLocation property
     */
    public String getFaxLocation()
    {
	fetch();
	return faxLocation;
    }

    /**
     * Access method for the faxNum property.
     * 
     * @return the current value of the faxNum property
     */
    public String getFaxNum()
    {
	fetch();
	return faxNum;
    }

    /**
     * Access method for the homePhoneNum property.
     * 
     * @return the current value of the homePhoneNum property
     */
    public String getHomePhoneNum()
    {
	fetch();
	return homePhoneNum;
    }

    /**
     * Gets referenced type Location
     */
    public Location getJuvLocation()
    {
	fetch();
	initJuvLocation();
	return juvLocation;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getJuvLocationId()
    {
	fetch();
	return juvLocationId;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getJuvUnit()
    {
	fetch();
	initJuvUnit();
	return juvUnit;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getOfficerSubType()
    {
	fetch();
	initOfficerSubType();
	return officerSubType;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getJuvUnitId()
    {
	fetch();
	return juvUnitId;
    }

    /**
     * Get the reference value to class :: pd.contact.user.UserProfile
     */
    public String getLogonId()
    {
	fetch();
	return logonId;
    }

    /**
     * Gets referenced type pd.contact.user.UserProfile
     */
    public UserProfile getManager()
    {
	fetch();
	initManager();
	return manager;
    }

    /**
     * @return See notes on setManagerId(String).
     */
    public String getManagerFirstName()
    {
	/*
	UserProfile manager = getManager();
	if (manager != null)
	{
	    return manager.getFirstName();
	}
	else
	{
	    fetch();
	    return managerFirstName;
	}*/
	fetch();
	return managerFirstName;
    }

    /**
     * Get the reference value to class :: pd.contact.user.UserProfile
     */
    public String getManagerLogonId()
    {
	fetch();
	return managerId;
    }

    /**
     * @return See notes on setManagerId(String).
     */
    public String getManagerLastName()
    {
	/*
	UserProfile manager = getManager();
	if (manager != null)
	{
	    return manager.getLastName();
	}
	else
	{
	    fetch();
	    return managerLastName;
	}
	*/
	fetch();
	return managerLastName;
    }

    /**
     * @return See notes on setManagerId(String).
     */
    public String getManagerMiddleName()
    {
	/*
	UserProfile manager = getManager();
	if (manager != null)
	{
	    return manager.getMiddleName();
	}
	else
	{
	    fetch();
	    return managerMiddleName;
	}
	*/
	 fetch();
	 return managerMiddleName;
	
    }
    
    public String getSurvey()
    {
	fetch();
	return survey;
    }
    
    public void setSurvey(String string)
    {
	if (this.survey == null || !this.survey.equals(string))
	{
	    markModified();
	}
	
	this.survey = string;
    }
    
    public String getSupervisor()
    {
	fetch();
	return supervisor;
    }
    
    public void setSupervisor(String string)
    {
	if (this.supervisor == null || !this.supervisor.equals(string))
	{
	    markModified();
	}
	
	this.supervisor = string;
    }
    

    /**
     * @return Returns the officerPhoneNumbers.
     */
    public String getOfficerPhoneNumbers()
    {
	StringBuffer buffer = new StringBuffer(100);
	boolean space = false;

	if (getWorkPhoneNumAndExtnString() != null && !getWorkPhoneNumAndExtnString().equals(""))
	{
	    buffer.append(getWorkPhoneNumAndExtnString());
	    space = true;
	}
	if (getCellPhoneNumberString() != null && !getCellPhoneNumberString().equals(""))
	{
	    if (space)
	    {
		buffer.append(", ");
	    }
	    buffer.append(getCellPhoneNumberString());
	    space = true;
	}
	if (getPager() != null && !getPager().equals(""))
	{
	    if (space)
	    {
		buffer.append(", ");
	    }
	    PhoneNumberBean pagerFormatter;
	    String officerPager = getPager();
	    pagerFormatter = new PhoneNumberBean(officerPager);
	    buffer.append("pager: ");
	    buffer.append(pagerFormatter.getFormattedPhoneNumber());
	    space = true;
	}
	if (getFaxNum() != null && !getFaxNum().equals(""))
	{
	    if (space)
	    {
		buffer.append(", ");
	    }
	    PhoneNumberBean faxNumFormatter;
	    String officerFaxNum = getFaxNum();
	    faxNumFormatter = new PhoneNumberBean(officerFaxNum);
	    buffer.append("fax: ");
	    buffer.append(faxNumFormatter.getFormattedPhoneNumber());
	    space = true;
	}
	String officerPhoneNumbers = buffer.toString();
	return officerPhoneNumbers;
    }

    /**
     * @return
     */
    public String getOfficerProfileId()
    {
	return "" + getOID();
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getOfficerType()
    {
	fetch();
	initOfficerType();
	return officerType;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getOfficerTypeId()
    {
	fetch();
	return officerTypeId;
    }

    /**
     * Access method for the pager property.
     * 
     * @return the current value of the pager property
     */
    public String getPager()
    {
	fetch();
	return pager;
    }

    /**
     * Access method for the otherIdNum property.
     * 
     * @return the current value of the otherIdNum property
     */
    public String getOtherIdNum()
    {
	fetch();
	return otherIdNum;
    }

    /**
     * Access method for the phoneExt property.
     * 
     * @return the current value of the phoneExt property
     */
    public String getPhoneExt()
    {
	fetch();
	return phoneExt;
    }

    /**
     * Access method for the phoneNum property.
     * 
     * @return the current value of the phoneNum property
     */
    public String getPhoneNum()
    {
	fetch();
	return phoneNum;
    }

    /**
     * Access method for the radioNum property.
     * 
     * @return the current value of the radioNum property
     */
    public String getRadioNum()
    {
	fetch();
	return radioNum;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getRank()
    {
	fetch();
	initRank();
	return rank;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getRankId()
    {
	fetch();
	return rankId;
    }

    /**
     * Access method for the ssn property.
     * 
     * @return the current value of the ssn property
     */
    public String getSsn()
    {
	fetch();
	return ssn;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getStatus()
    {
	fetch();
	initStatus();
	return status;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getStatusId()
    {
	fetch();
	return statusId;
    }

    /**
     * Access method for the title property.
     * 
     * @return the current value of the title property
     */
    public String getTitle()
    {
	fetch();
	return title;
    }

    /**
     * Gets referenced type pd.contact.user.UserProfile
     */
    public UserProfile getUser()
    {
	fetch();
	initUser();
	return user;
    }

    /**
     * returns a collection of pd.contact.officer.WorkDay
     */
    public Collection getWorkDays()
    {
	fetch();
	initWorkDays();
	return workDays;
    }

    /**
     * @return
     */
    public String getWorkPhoneExtn()
    {
	fetch();
	return workPhoneExtn;
    }

    /**
     * Access method for the workPhoneNum property.
     * 
     * @return the current value of the workPhoneNum property
     */
    public String getWorkPhoneNum()
    {
	fetch();
	return workPhoneNum;
    }

    /**
     * String to format workPhoneNumber and workPhoneExtn;
     * 
     * @return
     */
    public String getWorkPhoneNumAndExtnString()
    {
	StringBuffer buffer = new StringBuffer(25);

	if (getWorkPhoneNum() != null && !getWorkPhoneNum().equals(""))
	{
	    PhoneNumberBean workPhoneNumFormatter;
	    String officerWorkPhoneNum = getWorkPhoneNum();
	    workPhoneNumFormatter = new PhoneNumberBean(officerWorkPhoneNum);
	    buffer.append("work phone: ");
	    buffer.append(workPhoneNumFormatter.getFormattedPhoneNumber());
	}
	String workPhoneNumAndExtnString = buffer.toString();
	return workPhoneNumAndExtnString;
    }

    /**
     * Access method for the workShift property.
     * 
     * @return the current value of the workShift property
     */
    public String getWorkShift()
    {
	fetch();
	return workShift;
    }

    /**
     * Initialize class relationship implementation for
     * pd.contact.officer.OfficerProfileAddress
     */
    private void initAddresses()
    {
	if (addresses == null)
	{
	    if (this.getOID() == null)
	    {
		new mojo.km.persistence.Home().bind(this);
	    }
	    addresses = new mojo.km.persistence.ArrayList(OfficerProfileAddress.class, "officerProfileId", "" + getOID());
	}
    }

    /**
     * Initialize class relationship to class pd.contact.agency.Department
     */
    private void initDepartment()
    {
	if (department == null)
	{
	    department = Department.find(departmentId);//(pd.contact.agency.Department) new mojo.km.persistence.Reference(departmentId,
	    //pd.contact.agency.Department.class).getObject();
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initJuvLocation()
    {
	if (juvLocation == null)
	{

	    juvLocation = (Location) new mojo.km.persistence.Reference(juvLocationId, Location.class).getObject();
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initJuvUnit()
    {
	if (juvUnit == null)
	{
	    juvUnit = (Code) new mojo.km.persistence.Reference(juvUnitId, Code.class, "JUV_UNIT").getObject();
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initOfficerSubType()
    {
	if (officerSubType == null)
	{
	    officerSubType = (Code) new mojo.km.persistence.Reference(officerSubTypeId, Code.class, "OFFICER_SUBTYPE").getObject();
	}
    }

    /**
     * Initialize class relationship to class pd.contact.user.UserProfile
     */
    private void initManager()
    {
	if (manager == null)
	{
	    manager = UserProfile.find(managerId);/*(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(managerId,
									    pd.contact.user.UserProfile.class).getObject();*/
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initOfficerType()
    {
	if (officerType == null)
	{
	    officerType = (Code) new mojo.km.persistence.Reference(officerTypeId, Code.class, "OFFICER_TYPE").getObject();
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initRank()
    {
	if (rank == null)
	{
	    rank = (Code) new mojo.km.persistence.Reference(rankId, Code.class, "RANK").getObject();
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initStatus()
    {
	if (status == null)
	{
	    status = (Code) new mojo.km.persistence.Reference(statusId, Code.class, PDCodeTableConstants.AGENCY_STATUS).getObject();
	}
    }

    /**
     * Initialize class relationship to class pd.contact.user.UserProfile
     */
    private void initUser()
    {
	if (this.user == null)
	{
	    this.user = UserProfile.find(logonId);//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(logonId,
	    //pd.contact.user.UserProfile.class).getObject();
	}
    }

    /**
     * Initialize class relationship implementation for
     * pd.contact.officer.WorkDay
     */
    private void initWorkDays()
    {
	if (workDays == null)
	{
	    if (this.getOID() == null)
	    {
		new mojo.km.persistence.Home().bind(this);
	    }
	    workDays = new mojo.km.persistence.ArrayList(WorkDay.class, "officerProfileId", "" + getOID());
	}
    }

    /**
     * insert a pd.contact.officer.OfficerProfileAddress into class relationship
     * collection.
     */
    public void insertAddresses(OfficerProfileAddress anObject)
    {
	initAddresses();
	addresses.add(anObject);
    }

    /**
     * insert a pd.contact.officer.WorkDay into class relationship collection.
     */
    public void insertWorkDays(WorkDay anObject)
    {
	initWorkDays();
	workDays.add(anObject);
    }

    /**
     * Removes a pd.contact.officer.OfficerProfileAddress from class
     * relationship collection.
     */
    public void removeAddresses(OfficerProfileAddress anObject)
    {
	initAddresses();
	addresses.remove(anObject);
    }

    /**
     * Removes a pd.contact.officer.WorkDay from class relationship collection.
     */
    public void removeWorkDays(WorkDay anObject)
    {
	initWorkDays();
	workDays.remove(anObject);
    }

    /**
     * Sets the value of the assignedArea property.
     * 
     * @param aAssignedArea
     *            the new value of the assignedArea property
     */
    public void setAssignedArea(String aAssignedArea)
    {
	if (this.assignedArea == null || !this.assignedArea.equals(aAssignedArea))
	{
	    markModified();
	}
	this.assignedArea = aAssignedArea;
    }

    /**
     * Sets the value of the badgeNum property.
     * 
     * @param aBadgeNum
     *            the new value of the badgeNum property
     */
    public void setBadgeNum(String aBadgeNum)
    {
	if (this.badgeNum == null || !this.badgeNum.equals(aBadgeNum))
	{
	    markModified();
	}
	this.badgeNum = aBadgeNum;
    }

    /**
     * Sets the value of the cellPhone property.
     * 
     * @param aCellPhone
     *            the new value of the cellPhone property
     */
    public void setCellPhone(String aCellPhone)
    {
	if (this.cellPhone == null || !this.cellPhone.equals(aCellPhone))
	{
	    markModified();
	}
	this.cellPhone = aCellPhone;
    }

    /**
     * Sets the value of the contactId property.
     * 
     * @param aContactId
     *            the new value of the contactId property
     */
    public void setContactId(String aContactId)
    {
	if (this.contactId == null || !this.contactId.equals(aContactId))
	{
	    markModified();
	}
	this.contactId = aContactId;
    }

    /**
     * 87191 set the type reference for class member department
     */
      public void setDepartment(Department department)
      {
        /*  if (this.department == null || !this.department.equals(department))
          {
              markModified();
          }
          if (department.getOID() == null)
          {
              new mojo.km.persistence.Home().bind(department);
          }*/
          setDepartmentId("" + department.getDepartmentId());
          this.department = department ;//(pd.contact.agency.Department) new mojo.km.persistence.Reference(department).getObject();
      }

    /**
     * Set the reference value to class :: pd.contact.agency.Department
     */
    public void setDepartmentId(String departmentId)
    {
	if (this.departmentId == null || !this.departmentId.equals(departmentId))
	{
	    markModified();
	}
	department = null;
	this.departmentId = departmentId;
    }

    /**
     * Sets the value of the division property.
     * 
     * @param aDivision
     *            the new value of the division property
     */
    public void setDivision(String aDivision)
    {
	if (this.division == null || !this.division.equals(aDivision))
	{
	    markModified();
	}
	this.division = aDivision;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param aEmail
     *            the new value of the email property
     */
    public void setEmail(String aEmail)
    {
	if (this.email == null || !this.email.equals(aEmail))
	{
	    markModified();
	}
	this.email = aEmail;
    }

    /**
     * Sets the value of the faxLocation property.
     * 
     * @param aFaxLocation
     *            the new value of the faxLocation property
     */
    public void setFaxLocation(String aFaxLocation)
    {
	if (this.faxLocation == null || !this.faxLocation.equals(aFaxLocation))
	{
	    markModified();
	}
	this.faxLocation = aFaxLocation;
    }

    /**
     * Sets the value of the faxNum property.
     * 
     * @param aFaxNum
     *            the new value of the faxNum property
     */
    public void setFaxNum(String aFaxNum)
    {
	if (this.faxNum == null || !this.faxNum.equals(aFaxNum))
	{
	    markModified();
	}
	this.faxNum = aFaxNum;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param aFirstName
     *            the new value of the firstName property
     */
    public void setFirstName(String aFirstName)
    {
	if (this.firstName == null || !this.firstName.equals(aFirstName))
	{
	    markModified();
	}
	this.firstName = aFirstName;
    }

    /**
     * Sets the value of the homePhoneNum property.
     * 
     * @param aHomePhoneNum
     *            the new value of the homePhoneNum property
     */
    public void setHomePhoneNum(String aHomePhoneNum)
    {
	if (this.homePhoneNum == null || !this.homePhoneNum.equals(aHomePhoneNum))
	{
	    markModified();
	}
	this.homePhoneNum = aHomePhoneNum;
    }

    /**
     * set the type reference for class member juvLocation
     */
    public void setJuvLocation(Location juvLocation)
    {
	if (this.juvLocation == null || !this.juvLocation.equals(juvLocation))
	{
	    markModified();
	}
	if (juvLocation.getOID() == null)
	{

	    new mojo.km.persistence.Home().bind(juvLocation);
	}
	setJuvLocationId("" + juvLocation.getOID());
	this.juvLocation = (Location) new mojo.km.persistence.Reference(juvLocation).getObject();

    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setJuvLocationId(String juvLocationId)
    {
	if (this.juvLocationId == null || !this.juvLocationId.equals(juvLocationId))
	{
	    markModified();
	}
	juvLocation = null;
	this.juvLocationId = juvLocationId;
    }

    /**
     * set the type reference for class member juvUnit
     */
    public void setJuvUnit(Code juvUnit)
    {
	if (this.juvUnit == null || !this.juvUnit.equals(juvUnit))
	{
	    markModified();
	}
	if (juvUnit.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(juvUnit);
	}
	setJuvUnitId("" + juvUnit.getOID());
	juvUnit.setContext("JUV_UNIT");
	this.juvUnit = (Code) new mojo.km.persistence.Reference(juvUnit).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setJuvUnitId(String juvUnitId)
    {
	if (this.juvUnitId == null || !this.juvUnitId.equals(juvUnitId))
	{
	    markModified();
	}
	juvUnit = null;
	this.juvUnitId = juvUnitId;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param aLastName
     *            the new value of the lastName property
     */
    public void setLastName(String aLastName)
    {
	if (this.lastName == null || !this.lastName.equals(aLastName))
	{
	    markModified();
	}
	this.lastName = aLastName;
    }

    /**
     * Set the reference value to class :: pd.contact.user.UserProfile
     */
    public void setLogonId(String logonId)
    {
	if (this.logonId == null || !this.logonId.equals(logonId))
	{
	    markModified();
	}
	user = null;
	this.logonId = logonId;
    }

    /**
     * set the type reference for class member manager See notes on
     * setManagerId(String).
     */
        public void setManager(UserProfile manager)
        {
          /*  if (this.manager == null || !this.manager.equals(manager))
            {
                markModified();
            }
            if (manager.getOID() == null)
            {
                new mojo.km.persistence.Home().bind(manager);
            }*/
            setManagerId("" + manager.getLogonId());
            this.manager = manager;//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(manager).getObject();
        }

    /**
     * @param string
     *            See notes on setManagerId(String).
     */
    public void setManagerFirstName(String string)
    {
	if (this.managerFirstName == null || !this.managerFirstName.equals(string))
	{
	    markModified();
	}
	this.managerFirstName = string;
    }

    /**
     * Set the reference value to class :: pd.contact.user.UserProfile If a
     * manager is associated to the officer then the managers name will be
     * retrieved from the managers UserProfile and not from the name on this
     * instance. If a manager is not associated the the local name will be used.
     * This is to allow systems that do not maintain the manager in a
     * UserProfile to still identify the manager by name.
     */
    public void setManagerId(String managerId)
    {
	if (this.managerId == null || !this.managerId.equals(managerId))
	{
	    markModified();
	}
	this.managerId = managerId;
    }

    /**
     * @param string
     *            See notes on setManagerId(String).
     */
    public void setManagerLastName(String string)
    {
	if (this.managerLastName == null || !this.managerLastName.equals(string))
	{
	    markModified();
	}
	this.managerLastName = string;
    }

    /**
     * @param string
     *            See notes on setManagerId(String).
     */
    public void setManagerMiddleName(String string)
    {
	if (this.managerMiddleName == null || !this.managerMiddleName.equals(string))
	{
	    markModified();
	}
	this.managerMiddleName = string;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param aMiddleName
     *            the new value of the middleName property
     */
    public void setMiddleName(String aMiddleName)
    {
	if (this.middleName == null || !this.middleName.equals(aMiddleName))
	{
	    markModified();
	}
	this.middleName = aMiddleName;
    }

    /**
     * @param string
     */
    public void setOfficerProfileId(String string)
    {
	if (this.officerProfileId == null || !this.officerProfileId.equals(string))
	{
	    markModified();
	}
	this.officerProfileId = string;
    }

    /**
     * set the type reference for class member officerType
     */
    public void setOfficerType(Code officerType)
    {
	if (this.officerType == null || !this.officerType.equals(officerType))
	{
	    markModified();
	}
	if (officerType.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(officerType);
	}
	setOfficerTypeId("" + officerType.getOID());
	officerType.setContext("OFFICER_TYPE");
	this.officerType = (Code) new mojo.km.persistence.Reference(officerType).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setOfficerTypeId(String officerTypeId)
    {
	if (this.officerTypeId == null || !this.officerTypeId.equals(officerTypeId))
	{
	    markModified();
	}
	officerType = null;
	this.officerTypeId = officerTypeId;
    }

    /**
     * Sets the value of the pager property.
     * 
     * @param aPager
     *            the new value of the pager property
     */
    public void setPager(String aPager)
    {
	if (this.pager == null || !this.pager.equals(aPager))
	{
	    markModified();
	}
	this.pager = aPager;
    }

    /**
     * Sets the value of the otherIdNum property.
     * 
     * @param aOtherIdNum
     *            the new value of the otherIdNum property
     */
    public void setOtherIdNum(String aOtherIdNum)
    {
	if (this.otherIdNum == null || !this.otherIdNum.equals(aOtherIdNum))
	{
	    markModified();
	}
	this.otherIdNum = aOtherIdNum;
    }

    /**
     * Sets the value of the phoneExt property.
     * 
     * @param aPhoneExt
     *            the new value of the phoneExt property
     */
    public void setPhoneExt(String aPhoneExt)
    {
	if (this.phoneExt == null || !this.phoneExt.equals(aPhoneExt))
	{
	    markModified();
	}
	this.phoneExt = aPhoneExt;
    }

    /**
     * Sets the value of the phoneNum property.
     * 
     * @param aPhoneNum
     *            the new value of the phoneNum property
     */
    public void setPhoneNum(String aPhoneNum)
    {
	if (this.phoneNum == null || !this.phoneNum.equals(aPhoneNum))
	{
	    markModified();
	}
	this.phoneNum = aPhoneNum;
    }

    /**
     * Sets the value of the radioNum property.
     * 
     * @param aRadioNum
     *            the new value of the radioNum property
     */
    public void setRadioNum(String aRadioNum)
    {
	if (this.radioNum == null || !this.radioNum.equals(aRadioNum))
	{
	    markModified();
	}
	this.radioNum = aRadioNum;
    }

    /**
     * set the type reference for class member rank
     */
    public void setRank(Code rank)
    {
	if (this.rank == null || !this.rank.equals(rank))
	{
	    markModified();
	}
	if (rank.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(rank);
	}
	setRankId("" + rank.getOID());
	rank.setContext("RANK");
	this.rank = (Code) new mojo.km.persistence.Reference(rank).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setRankId(String rankId)
    {
	if (this.rankId == null || !this.rankId.equals(rankId))
	{
	    markModified();
	}
	rank = null;
	this.rankId = rankId;
    }

    /**
     * Sets the value of the ssn property.
     * 
     * @param aSsn
     *            the new value of the ssn property
     */
    public void setSsn(String aSsn)
    {
	if (this.ssn == null || !this.ssn.equals(aSsn))
	{
	    markModified();
	}
	this.ssn = aSsn;
    }

    /**
     * set the type reference for class member status
     */
    public void setStatus(Code status)
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
	status.setContext(PDCodeTableConstants.AGENCY_STATUS);
	this.status = (Code) new mojo.km.persistence.Reference(status).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
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
     * Sets the value of the title property.
     * 
     * @param aTitle
     *            the new value of the title property
     */
    public void setTitle(String aTitle)
    {
	if (this.title == null || !this.title.equals(aTitle))
	{
	    markModified();
	}
	this.title = aTitle;
    }

    /**
     * set the type reference for class member user
     */
        public void setUser(UserProfile user)
        {
            
            /* * if (this.user == null || !this.user.equals(user)) { markModified(); }
             
            if (user.getOID() == null)
            {
                new mojo.km.persistence.Home().bind(user);
            }*/

            setLogonId("" + user.getUserID());
            this.user =user; //(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(user).getObject();
        }

    /**
     * @param string
     */
    public void setWorkPhoneExtn(String string)
    {
	if (this.workPhoneExtn == null || !this.workPhoneExtn.equals(string))
	{
	    markModified();
	}
	this.workPhoneExtn = string;
    }

    /**
     * Sets the value of the workPhoneNum property.
     * 
     * @param aWorkPhoneNum
     *            the new value of the workPhoneNum property
     */
    public void setWorkPhoneNum(String aWorkPhoneNum)
    {
	if (this.workPhoneNum == null || !this.workPhoneNum.equals(aWorkPhoneNum))
	{
	    markModified();
	}
	this.workPhoneNum = aWorkPhoneNum;
    }

    /**
     * Sets the value of the workShift property.
     * 
     * @param aWorkShift
     *            the new value of the workShift property
     */
    public void setWorkShift(String aWorkShift)
    {
	if (this.workShift == null || !this.workShift.equals(aWorkShift))
	{
	    markModified();
	}
	this.workShift = aWorkShift;
    }

    /**
     * @return the firstName.
     */
    public String getFirstName()
    {
	fetch();
	//if (getUser() != null)
	//return getUser().getFirstName();
	//else
	return this.firstName;
    }

    /**
     * @return the middleName.
     */
    public String getMiddleName()
    {
	fetch();
	//if (getUser() != null)
	//return getUser().getMiddleName();
	//else
	return this.middleName;
    }

    /**
     * @return the lastName.
     */
    public String getLastName()
    {
	fetch();
	//if (getUser() != null)
	//return getUser().getLastName();
	//else
	return this.lastName;
    }

    /**
     * @return pd.contact.OfficerProfile
     * @param officerProfileId
     * @roseuid 42E65EA60111
     */
    static public OfficerProfile find(String officerProfileId)
    {
	OfficerProfile officerProfile = null;
	IHome home = new Home();
	officerProfile = (OfficerProfile) home.find(officerProfileId, OfficerProfile.class);
	return officerProfile;
    }
    
    static public OfficerProfile findByLogonId(String logonId)
    {
	OfficerProfile officerProfile = null;
	IHome home = new Home();
	officerProfile = (OfficerProfile) home.find("logonId", logonId, OfficerProfile.class);
	return officerProfile; 
    }

    /**, 
     * @roseuid 42E65EA6010F
     */
    static public Iterator findAll()
    {
	IHome home = new Home();
	Iterator iter = home.findAll(OfficerProfile.class);
	return iter;
    }

    /**
     * @return java.util.Iterator
     * @param event
     * @roseuid 4107B06D01BB
     */
    static public Iterator findAll(IEvent event)
    {
	IHome home = new Home();
	Iterator iter = home.findAll(event, OfficerProfile.class);
	return iter;
    }

    /**
     * @return java.util.Iterator
     * @param attrName
     * @param attrValue
     * @roseuid 42E65EA6010F
     */
    static public Iterator findAll(String attrName, String attrValue)
    {
	IHome home = new Home();
	return (Iterator) home.findAll(attrName, attrValue, OfficerProfile.class);
    }
    
   

    /**
     * @description this method takes care of both update and create and convert
     *              a request event into ofiicer profile entity
     * @param requestEvent
     *            UpdateOfficerProfileEvent
     */
    public void setOfficerProfile(UpdateOfficerProfileEvent requestEvent)
    {
	this.setLastName(requestEvent.getLastName());
	this.setFirstName(requestEvent.getFirstName());
	this.setDepartmentId(requestEvent.getDepartmentId());
	this.setOtherIdNum(requestEvent.getOtherIdNum());
	this.setBadgeNum(requestEvent.getBadgeNum());
	String logonId = requestEvent.getLogonId();
	if (logonId != null && logonId.equals(""))
	{
	    logonId = null;
	}
	this.setLogonId(logonId);
	this.setMiddleName(requestEvent.getMiddleName());
	this.setOfficerTypeId(requestEvent.getOfficerTypeId());
	this.setSsn(requestEvent.getSsn());
	this.setJuvLocationId(requestEvent.getJuvLocationId());
	this.setJuvUnitId(requestEvent.getJuvUnitId());
	this.setWorkPhoneNum(requestEvent.getWorkPhone());
	this.setWorkPhoneExtn(requestEvent.getExtn());
	this.setHomePhoneNum(requestEvent.getHomePhone());
	this.setCellPhone(requestEvent.getCellPhone());
	this.setPager(requestEvent.getPager());
	this.setFaxNum(requestEvent.getFax());
	this.setFaxLocation(requestEvent.getFaxLocation());
	this.setEmail(requestEvent.getEmail());
	this.setRankId(requestEvent.getRankId());
	this.setDivision(requestEvent.getDivisionName());
	this.setAssignedArea(requestEvent.getAssignedArea());
	this.setRadioNum(requestEvent.getRadioNum());
	this.setWorkShift(requestEvent.getWorkShift());
	this.setManagerId(requestEvent.getManagerId());
	this.setManagerFirstName(requestEvent.getManagerFirstName());
	this.setManagerLastName(requestEvent.getManagerLastName());
	this.setManagerMiddleName(requestEvent.getManagerMiddleName());
	this.setStatusId(requestEvent.getStatusId());
	this.setOfficerSubTypeId(requestEvent.getOfficerSubTypeId());
	this.setSupervisor(requestEvent.getSupervisor());
	this.setSurvey(requestEvent.getSurvey());
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUser#getUserLoginName()
     */
    public String getUserLoginName()
    {
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUser#setUserLoginName(java.lang.String)
     */
    public void setUserLoginName(String userLoginName)
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUser#getPassword()
     */
    public String getPassword()
    {
	return password;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUser#setPassword(java.lang.String)
     */
    public void setPassword(String password)
    {
	this.password = password;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUser#getUserTypeId()
     */
    public String getUserTypeId()
    {
	return userTypeId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUser#setUserTypeId(java.lang.String)
     */
    public void setUserTypeId(String userTypeId)
    {
	this.userTypeId = userTypeId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUser#getAgencyId()
     */
    public String getAgencyId()
    {
	return agencyId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUser#setAgencyId(java.lang.String)
     */
    public void setAgencyId(String agencyId)
    {
	this.agencyId = agencyId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUser#getUserOID()
     */
    public String getUserOID()
    {
	return JIMS2LogonId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUserInfo#getJIMS2LogonId()
     */
    public String getJIMS2LogonId()
    {
	return JIMS2LogonId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUserInfo#setJIMS2LogonId(java.lang.String)
     */
    public void setJIMS2LogonId(String JIMS2LogonId)
    {
	this.JIMS2LogonId = JIMS2LogonId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUserInfo#getJIMSLogonId()
     */
    public String getJIMSLogonId()
    {
	return JIMSLogonId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUserInfo#setJIMSLogonId(java.lang.String)
     */
    public void setJIMSLogonId(String JIMSLogonId)
    {
	this.JIMSLogonId = JIMSLogonId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUserInfo#getJIMS2Password()
     */
    public String getJIMS2Password()
    {
	return JIMS2Password;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUserInfo#setJIMS2Password(java.lang.String)
     */
    public void setJIMS2Password(String jims2Password)
    {
	this.JIMS2Password = jims2Password;

    }

    /**
     * @return
     */
    public String getOfficerSubTypeId()
    {
	fetch();
	return officerSubTypeId;
    }

    /**
     * @param string
     */
    public void setOfficerSubTypeId(String string)
    {
	if (this.officerSubTypeId == null || !this.officerSubTypeId.equals(string))
	{
	    markModified();
	}
	this.officerSubTypeId = string;
    }

    public OfficerProfileResponseEvent valueObject()
    {
	return this.valueObject(true);
    }
    
    
    public String getAccountType()
    {
	fetch();
        return accountType;
    }

    public void setAccountType(String accountType)
    {
	if (this.accountType == null || !this.accountType.equals(accountType))
	{
	    markModified();
	}	
        this.accountType = accountType;
    }

    public OfficerProfileResponseEvent valueObject(boolean withSecurity)
    {
	OfficerProfileResponseEvent event = new OfficerProfileResponseEvent();
	event.setTopic(PDOfficerProfileConstants.OFFICER_PROFILE_EVENT_TOPIC);

	event.setOfficerId(this.getOID().toString());
	event.setBadgeNum(this.getBadgeNum());
	if (this.getDepartmentId() != null && this.getDepartmentId().equals("") == false)
	{
	    event.setDepartmentId(this.getDepartmentId());
	    Department department = this.getDepartment();
	    event.setDepartmentName(department.getDepartmentName());
	    event.setAgencyId(department.getAgencyId());
	    event.setAgencyName(department.getAgencyName());
	}
	event.setCellPhone(this.getCellPhone());
	event.setEmail(this.getEmail());
	event.setFirstName(this.getFirstName());
	event.setLastName(this.getLastName());
	event.setMiddleName(this.getMiddleName());
	event.setOfficerProfileId(this.getOfficerProfileId());
	event.setPager(this.getPager());
	event.setOtherIdNum(this.getOtherIdNum());
	event.setManagerId(this.getManagerId());
	event.setUserId(this.getLogonId());
	event.setWorkPhone(this.getWorkPhoneNum());

	if (withSecurity == true)
	{
	    if (PDSecurityHelper.isUserMA())
	    {
		event.setUpdatableStatus(PDConstants.YES);
		event.setDeletableStatus(PDConstants.YES);
	    }
	    else
		if (PDSecurityHelper.isUserSA())
		{
		    if (event.getAgencyId().equalsIgnoreCase(PDSecurityHelper.getUserAgencyId()))
		    {
			
			event.setUpdatableStatus(PDConstants.YES);
			event.setDeletableStatus(PDConstants.YES);
		    }
		    else
		    {
			event.setUpdatableStatus(PDConstants.NO);
			event.setDeletableStatus(PDConstants.NO);
		    }

		}

		else
		    if (PDSecurityHelper.isUserASA() || PDSecurityHelper.isUserLA())
		    {

			/*if ((PDSecurityHelper.validateAdminDept(event.getDepartmentId())))
			{
			    event.setUpdatableStatus(PDConstants.YES);
			    event.setDeletableStatus(PDConstants.YES);
			}
			else
			{
			    event.setDeletableStatus(PDConstants.NO);
			    event.setUpdatableStatus(PDConstants.NO);

			}*/ //87191

		    }
		    else
		    {
			String userId = this.getLogonId();
			String logonId = PDSecurityHelper.getLogonId();
			if (userId != null && logonId != null && userId.equalsIgnoreCase(logonId))
			{
			    event.setLimitedUpdatableStatus(PDConstants.YES);
			    event.setUpdatableStatus(PDConstants.NO);
			}
			else
			{
			    event.setLimitedUpdatableStatus(PDConstants.NO);
			    event.setUpdatableStatus(PDConstants.NO);
			}
			event.setDeletableStatus(PDConstants.NO);
		    }
	}
	return event;
    }

    /**
     * @param officerEvent
     * @param class1
     * @return
     */
    public static MetaDataResponseEvent findMeta(IEvent officerEvent)
    {
	IHome home = new Home();
	MetaDataResponseEvent iter = home.findMeta(officerEvent, OfficerProfile.class);
	return iter;
    }

    @Override
    public void setUserOID(String smUserId)
    {
	// TODO Auto-generated method stub

    }

    @Override
    public String getDepartmentName()
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setDepartmentName(String departmentName)
    {
	// TODO Auto-generated method stub

    }

    @Override
    public String getOrgCode()
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setOrgCode(String orgCode)
    {
	// TODO Auto-generated method stub

    }

    @Override
    public String getAgencyName()
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setAgencyName(String agencyName)
    {
	// TODO Auto-generated method stub

    }
}