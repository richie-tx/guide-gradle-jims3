package ui.contact.user.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import messaging.domintf.contact.user.IUserProfileUpdate;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;
import ui.common.CodeHelper;
import org.apache.struts.action.ActionForm;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SocialSecurity;
import java.util.Date;
import org.apache.commons.lang.time.FastDateFormat;
/**
 * @author dwilliamson
 *
 * Form used for viewing and maintaining User Profiles.
 */
public class UserProfileForm extends ActionForm
{
// Fields	
/* 05/08/2007 per ER 39600 -- remove badge and other Id number */	
	private static FastDateFormat FULL_DATE_FAST_FORMAT = FastDateFormat.getInstance("MM/dd/yyyy");
	private String activationDate="";
	private String activationTime="";
	private Name activatedBy = new Name();
	private String agencyId = "";
	private String agencyName = "";
//	private String badgeNum = "";
	private String comments = "";
	private String createDate = "";
	private Name creatorName = new Name();
	private String createTime = "";
	private String createdBy = "";
	private String dateOfBirth ="";
	private String departmentId = "";
	private String departmentName = "";
	private String newDepartmentId = "";
	private String newDepartmentName = "";
	private String email = "";
	private String genericUserType = "";
	private String genericUserTypeId = "";
	private String inactivationDate ="";
	private Name inactivatedBy = new Name();
	private String inactivationTime = "";
	private String inactivationTimeId="";
	private String OPID = "";
	private String orgCode = "";
//	private String otherIdNum = "";
	private PhoneNumber phoneNum = new PhoneNumber("");
//	private String publicInd = "";
//	private String publicIndString="";
	private Name requestorName = new Name();
	private String searchDepartmentId = "";
	private String searchDepartmentName = "";
	private SocialSecurity ssn = new SocialSecurity("");
	private String trainingInd = "";
	private String transferDate = "";
	private String transferTime = "";
	private Name userName = new Name();	
	private String userStatus = "";
	private String userStatusId = "";
	private String userType = "";
	private String userTypeId = "";
	private String loggedInUserType = "";
	private String uvCodeGeneration = "";
	private String uvCodeGenerationStr="";
	private String customCodeGeneration = "";
	private String selectedValue="";
	private String action ="";
	private int userProfilesSize = 0;
	private String numUsers = "";
	private String logonId = "";
	private int departmentsSize=0;
	private String jims2Ind="";
	private String jims2LogonId="";
	private String inactivationReqDate = "";
	private String inactivationReqTime = "";
	private String deptTransferReqDate = "";
	private String deptTransferReqTime = "";
// user profile display page	
	private String userIsLA = "";
	private String allowUserUpdate = "";
// jims2 display data
	private Name jims2UserName = new Name();
	private String jims2UserId = "";
	private String jims2DepartmentName = "";
	private String jims2Password = "";
	private String forgottenPasswdPhrase = "";
	private String answer = "";

// Collections
	private Collection userProfiles = new ArrayList(); 
	private Collection userProfileDetails = new ArrayList();
   	private Collection departments = new ArrayList();
	private Collection genericUserTypes = new ArrayList();  	
	private Collection userTypes = new ArrayList();  	
	private Collection userStatuses = new ArrayList();  	
	private Collection workDays = new ArrayList();  	
	private Collection userProfileHistory = new ArrayList(); 
	private Collection inactivateTimes = new ArrayList();
	private Collection matchingProfiles = new ArrayList(); 
	
// Methods
   public void clear()
   {
   	action = "";
	activationDate = "";
	activationTime ="";
	activatedBy = null;
	agencyId = "";
	agencyName = "";
//	badgeNum = "";
	comments = "";
	createDate = "";
	creatorName.clear();
	createTime = "";
	createdBy = "";
	dateOfBirth = "";
	departmentId = "";
	departmentsSize=0;
	departmentName = "";
	email = "";
	genericUserType = "";
	genericUserTypeId = "";
	inactivationDate = "";
	inactivatedBy = null;
	inactivationTime = "";
	inactivationTimeId="";	
	OPID = "";
	orgCode = "";
//	otherIdNum = "";
	phoneNum.clear();
	phoneNum.setExt("");
//	publicInd = "";
//	publicIndString="";
	requestorName.clear();
	ssn.clear();
	trainingInd = "";
	transferDate = "";
	transferTime = "";
	userName.clear();
	userProfilesSize = 0;
	userStatus = "";
	userStatusId="";
	userType = "";
	userTypeId="";
	loggedInUserType = "";
	uvCodeGeneration = "";
	uvCodeGenerationStr="";
	customCodeGeneration = "";
	inactivateTimes=null;
	numUsers="zero";
//	setPublicInd("N");
	inactivationReqDate = "";
	inactivationReqTime = "";
	deptTransferReqDate = "";	
	deptTransferReqTime = "";		
//	 jims2 display data
	jims2UserName.clear();
	jims2UserId = "";
	jims2DepartmentName = "";
	jims2Password = "";
	forgottenPasswdPhrase = "";
	answer = "";
   }
   
   public void clearUserProfileDetail()
	{
		activationDate="";
		activationTime="";
		activatedBy = new Name();
		agencyId = "";
		agencyName = "";
		comments = "";
		createDate = "";
		createTime = "";
		createdBy = "";
		departmentName = "";		
		email = "";
		genericUserType = "";
		inactivationDate ="";
		inactivationReqDate = "";
		inactivatedBy = new Name();
		inactivationTime = "";
		inactivationReqTime = "";
		OPID = "";
		orgCode = "";
//		otherIdNum = "";
//		private String publicIndString="";
		requestorName = new Name();
		trainingInd = "";
		transferDate = "";
		deptTransferReqDate = "";
		 transferTime = "";
		deptTransferReqTime = "";
		userType = "";
		jims2UserId = "";
		jims2Password= "";
		jims2DepartmentName="";
		jims2UserName=new Name();
		forgottenPasswdPhrase = "";
		answer = "";
	}	
	
   public void clearDepartmentDetails()
	{
		this.newDepartmentId="";
		this.newDepartmentName="";
		this.transferDate="";
		this.transferTime="";
	}
	
	public void clearUserProfiles()
	{
		userProfiles=new ArrayList();
	}
	
	public void clearUserProfileDetails()
	{
		userProfileDetails=new ArrayList();
	}
	
	public void clearMatchingUserProfiles()
	{
		matchingProfiles=new ArrayList();
	}
	public void clearDepartments()
	{
		departments=new ArrayList();
	}
	/**
	 * @return
	 */
	public Name getActivatedBy()
	{
		return activatedBy;
	}

    /**
     * @return
     */
    public String getActivationDate()
    {
	    return activationDate;
    }
    
	public String getActivationDateFormatted()
		{
		if(this.activationDate != null)
		{
			String formattedDate = FULL_DATE_FAST_FORMAT.format(this.activationDate);
			return formattedDate;
//			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//			return formatter.format(this.activationDate);
		}
		return "";
	}

	/**
	 * @return
	 */
	public String getActivationTime()
	{
		return activationTime;
	}

	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * @return
	 */
	public String getAgencyName()
	{
		return agencyName;
	}

	/**
	 * @return
	 */
//	public String getBadgeNum()
//	{
//		return badgeNum;
//	}

	/**
	 * @return
	 */
	public String getComments()
	{
		return comments;
	}

	/**
	 * @return
	 */
	public String getCreatedBy()
	{
		return createdBy;
	}
	/**
	 * @return
	 */
	public String getCreateDate()
	{
		return createDate;
	}
	
 
	public String getCreateDateFormatted()
		{
		if(this.createDate != null)
		{
			String formattedDate = FULL_DATE_FAST_FORMAT.format(this.createDate);
        
			return formattedDate;

//			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//			return formatter.format(this.createDate);
		}
		return "";
	}

	/**
	 * @return
	 */
	public Name getCreatorName()
	{
		return creatorName;
	}

	/**
	 * @return
	 */
	public String getCreateTime()
	{
		return createTime;
	}
	
	/**
	 * @return
	 */
	public String getCustomCodeGeneration()
	{
		return customCodeGeneration;
	}


	/**
	 * @return
	 */
	public String getDateOfBirth()
	{
		return dateOfBirth;
	}

	public String getDateOfBirthFormatted()
		{
		if(this.dateOfBirth != null)
		{
			String formattedDate = FULL_DATE_FAST_FORMAT.format(this.dateOfBirth);
			return formattedDate;
			//SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			//return formatter.format(this.dateOfBirth);
		}
		return "";
	}
	/**
	 * @return
	 */
	public int getDepartmentsSize()
	{
		return departmentsSize;
	}
	
	/**
	 * @return
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	/**
	 * @return
	 */
	public String getDepartmentName()
	{
		return departmentName;
	}

    /**
     * @return
     */
    public Collection getDepartments()
    {
	    return departments;
    }

	/**
	 * @return
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @return
	 */
	public String getGenericUserType()
	{
		return genericUserType;
	}

	/**
	 * @return
	 */
	public String getGenericUserTypeId()
	{
		return genericUserTypeId;
	}
	
    /**
     * @return
     */
    public Collection getGenericUserTypes()
    {
	    return genericUserTypes;
    }
	/**
	 * @return
	 */
	public String getJims2Ind()
	{
		return jims2Ind;
	}
	/**
	 * Returns the logonId.
	 * @return String
	 */
	public String getLogonId()
	{
		return logonId;
	}	
	
	/**
	 * Returns the loggedInUserType.
	 * @return String
	 */
	public String getLoggedInUserType()
	{
		return loggedInUserType;
	}	


	/**
	 * @return
	 */
	public Collection getUserTypes()
	{
		return userTypes;
	}

	/**
	 * @return
	 */
	public Collection getUserStatuses()
	{
		return userStatuses;
	}

	/**
	 * @return
	 */
	public Collection getWorkDays()
	{
		return workDays;
	}

	/**
	 * @return
	 */
	public Name getInactivatedBy()
	{
		return inactivatedBy;
	}
	
	/**
	 * @return
	 */
	public Collection getInactivateTimes()
	{
		return inactivateTimes;
	}

	/**
	 * @return
	 */
	public String getInactivationDate()
	{
		return inactivationDate;
	}

	public String getInactivationDateFormatted()
		{
		if(this.inactivationDate!= null)
		{
			String formattedDate = FULL_DATE_FAST_FORMAT.format(this.inactivationDate);
			return formattedDate;
			//SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			//return formatter.format(this.inactivationDate);
		}
		return "";
	}
	/**
	 * @return
	 */
	public String getInactivationTime()
	{
		return inactivationTime;
	}
	/**
	 * @return
	 */
	public String getInactivationTimeId()
	{
		return inactivationTimeId;
	}
	
		
	/**
	 * @return
	 */
	public String getNewDepartmentId()
	{
		return newDepartmentId;
	}
	/**
	 * @return
	 */
	public String getNewDepartmentName()
	{
		return newDepartmentName;
	}

	/**
	 * @return
	 */
	public String getOPID()
	{
		return OPID;
	}

	/**
	 * @return
	 */
	public String getOrgCode()
	{
		return orgCode;
	}

	/**
	 * @return
	 */
//	public String getOtherIdNum()
//	{
//		return otherIdNum;
//	}

	/**
	 * @return
	 */
	public PhoneNumber getPhoneNum()
	{
		return phoneNum;
	}

	/**
	 * @return
	 */
//	public String getPublicInd()
//	{
//		return publicInd;
//	}
	
	/**
	 * @return
	 */
//	public String getPublicIndString()
//	{
//		return publicIndString;
//	}

	/**
	 * @return
	 */
	public Name getRequestorName()
	{
		return requestorName;
	}
	/**
	 * @return Returns the searchDepartmentId.
	 */
	public String getSearchDepartmentId() {
		return searchDepartmentId;
	}
	/**
	 * @param searchDepartmentId The searchDepartmentId to set.
	 */
	public void setSearchDepartmentId(String searchDepartmentId) {
		this.searchDepartmentId = searchDepartmentId;
	}
	/**
	 * @return Returns the searchDepartmentName.
	 */
	public String getSearchDepartmentName() {
		return searchDepartmentName;
	}
	/**
	 * @param searchDepartmentName The searchDepartmentName to set.
	 */
	public void setSearchDepartmentName(String searchDepartmentName) {
		this.searchDepartmentName = searchDepartmentName;
	}
	/**
	 * @return
	 */
	public String getSelectedValue()
	{
		return selectedValue;
	}

	/**
	 * @return
	 */
	public SocialSecurity getSsn()
	{
		return ssn;
	}

	/**
	 * @return
	 */
	public String getTrainingInd()
	{
		return trainingInd;
	}

	/**
	 * @return
	 */
	public String getTransferDate()
	{
		return transferDate;
	}
	
	

	public String getTransferDateFormatted()
		{
		if(this.transferDate!= null)
		{
			
			String formattedDate = FULL_DATE_FAST_FORMAT.format(this.transferDate);
            return formattedDate;

//			
//			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//			return formatter.format(this.transferDate);
		}
		return "";
	}
	
	/**
	 * @return
	 */
	public String getTransferTime()
	{
		return transferTime;
	}
	/**
	 * @return
	 */
	public String getTransferRequestTime()
	{
		return transferTime;
	}


	/**
	 * @return
	 */
	public Name getUserName()
	{
		return userName;
	}

	/**
	 * @return
	 */
	public String getUserStatus()
	{
		return userStatus;
	}
	
	/**
	 * @return
	 */
	public String getUserStatusId()
	{
		return userStatusId;
	}

	/**
	 * @return
	 */
	public String getUserType()
	{
		return userType;
	}
	
	/**
	 * @return
	 */
	public String getUserTypeId()
	{
		return userTypeId;
	}
	
	/**
	 * @return
	 */
	public String getUvCodeGeneration()
	{
		return uvCodeGeneration;
	}
	
	/**
	 * @return
	 */
	public String getUvCodeGenerationStr()
	{
		return uvCodeGenerationStr;
	}


	/**
	 * @param string
	 */
	public void setActivatedBy(Name string)
	{
		activatedBy = string;
	}

  
	/**
	 * @param string
	 */
	public void setActivationDate(String string)
	{
		activationDate =string;
	}
	
	/**
	 * @param string
	 */
	public void setActivationTime(String string)
	{
		activationTime =string;
	}
	
	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}

	/**
	 * @param string
	 */
	public void setAgencyName(String string)
	{
		agencyName = string;
	}

	/**
	 * @param string
	 */
//	public void setBadgeNum(String string)
//	{
//		badgeNum = string;
//	}

	/**
	 * @param string
	 */
	public void setComments(String string)
	{
		comments = string;
	}
	
	/**
	 * @param created by
	 */
	public void setCreatedBy(String create)
	{
		createdBy = create;
	}

	/**
	 * @param create date
	 */
	public void setCreateDate(String create)
	{
		createDate = create;
	}

	/**
	 * @param string
	 */
	public void setCreatorName(Name string)
	{
		creatorName = string;
	}

	/**
	 * @param string
	 */
	public void setCreateTime(String create)
	{
		createTime = create;
	}
	
	/**
	 * @param string
	 */
	public void setCustomCodeGeneration(String string)
	{
		customCodeGeneration = string;
	}


	/**
	 * @param string
	 */
	public void setDateOfBirth(String dob)
	{
		dateOfBirth =dob;
	}

	/**
	 * @param string
	 */
	public void setDepartmentId(String string)
	{
		departmentId = string;
	}

	/**
	 * @param string
	 */
	public void setDepartmentName(String string)
	{
		departmentName = string;
	}

    /**
    * @param collection
     */
    public void setDepartments(Collection collection)
    {
	    departments = collection;
    }
	/**
	 * @param string
	 */
	public void setDepartmentsSize(int size)
	{
		departmentsSize = size;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string)
	{
		email = string;
	}

	/**
	 * @param string
	 */
	public void setGenericUserType(String string)
	{
		genericUserType = string;
	}
	
	/**
	 * @param string
	 */
	public void setGenericUserTypeId(String string)
	{
		if(string.equals(""))
			genericUserTypeId="N";
		else
			genericUserTypeId = string;
		
		if(genericUserTypeId.equals("N"))
			setGenericUserType("Non-Generic");
		if(genericUserTypeId.equals("L"))
					setGenericUserType("Law Enforcement Officer");
		if(genericUserTypeId.equals("S"))
					setGenericUserType("Service Provider");
	}

	/**
	 * @param collection
	 */
	public void setGenericUserTypes(Collection collection)
	{
		genericUserTypes = collection;
	}

	/**
	 * @param string
	 */
	public void setJims2Ind(String string)
	{
		jims2Ind = string;
	}
	/**
	 * @param collection
	 */
	public void setUserTypes(Collection collection)
	{
		userTypes = collection;
	}

	/**
	 * @param collection
	 */
	public void setUserStatuses(Collection collection)
	{
		userStatuses = collection;
	}

	/**
	 * @param collection
	 */
	public void setWorkDays(Collection collection)
	{
		workDays = collection;
	}

	/**
	 * @return
	 */
	public void setInactivatedBy(Name name)
	{
		inactivatedBy = name;
	}

	/**
	 * @param coll
	 */
	public void setInactivateTimes(Collection coll)
	{
		inactivateTimes = coll;
	}

	/**
	 * @param string
	 */
	public void setInactivationDate(String inactivate)
	{
		inactivationDate =inactivate;
	}
	
	/**
	 * @param string
	 */
	public void setInactivationDateAsDate(String inactivate)
	{
		inactivationDate =inactivate;
	}

	/**
	 * @param string
	 */
	public void setInactivationTime(String inactiveTime)
	{
		inactivationTime = inactiveTime;
	}
	/**
	 * @param string
	 */
	public void setInactivationTimeId(String inactiveTimeId)
	{
		inactivationTimeId = inactiveTimeId;
	}
	/**
	 * @param string
	 */
	public void setNewDepartmentId(String string)
	{
		newDepartmentId=string;
	}
	/**
	 * @param string
	 */
	public void setNewDepartmentName(String string)
	{
		newDepartmentName=string;
	}
	/**
	 * @return
	 */
	public void setNumUsers(String string)
	{
		numUsers = string;
	}
	/**
	 * @param string
	 */
	public void setOPID(String string)
	{
		OPID = string;
	}

	/**
	 * @param string
	 */
	public void setOrgCode(String string)
	{
		orgCode = string;
	}

	/**
	 * @param string
	 */
//	public void setOtherIdNum(String string)
//	{
//		otherIdNum = string;
//	}

	/**
	 * @param number
	 */
	public void setPhoneNum(PhoneNumber number)
	{
		phoneNum = number;
	}

	/**
	 * @param String
	 */
//	public void setPublicInd(String ind)
//	{
//		if(ind.equalsIgnoreCase("Y")|| ind.equalsIgnoreCase("Yes"))
//		{
//			this.setPublicIndString("Yes");
//			publicInd="Y";
//			
//		}
//		else
//		{
//			this.setPublicIndString("No");
//			publicInd="N";		
//		}
//		
//	}
	/**
	 * @param string
	 */
//	public void setPublicIndString(String string)
//	{
//		publicIndString = string;
//	}


	/**
	 * @param name
	 */
	public void setRequestorName(Name name)
	{
		requestorName = name;
	}
	
	/**
	 * @param string
	 */
	public void setSelectedValue(String string)
	{
		selectedValue = string;
	}


	/**
	 * @param string
	 */
	public void setTrainingInd(String string)
	{
		trainingInd = string;
	}

	/**
	 * @param transfer
	 */
	public void setTransferDate(String transfer)
	{
		transferDate = transfer;
	}

	/**
	 * @param transfer
	 */
	public void setTransferTime(String transfer)
	{
		transferTime = transfer;
	}

	/**
	 * @param name
	 */
	public void setUserName(Name name)
	{
		userName = name;
	}

	/**
	 * @param string
	 */
	public void setUserStatus(String string)
	{
		userStatus = string;
	}
	
	/**
	 * @param string
	 */
	public void setUserStatusId(String string)
	{
		userStatusId = string;
		if(userStatusId.equals("I"))
			this.setUserStatus("INACTIVE");
		else if(userStatusId.equals("A"))
			this.setUserStatus("ACTIVE");
	}

	/**
	 * @param string
	 */
	public void setUserType(String string)
	{
		userType = string;
		
	}
	
	/**
	 * @param string
	 */
	public void setUserTypeId(String string)
	{
		userTypeId = string;
		userType=CodeHelper.getCodeDescriptionByCode(userStatuses,userTypeId);
	}
	
	/**
	 * @param string
	 */
	public void setLoggedInUserType(String string)
	{
		loggedInUserType = string;
	
	}
	
	/**
	 * @param string
	 */
	public void setUvCodeGeneration (String string)
	{
		uvCodeGeneration = string;
		if(uvCodeGeneration.equalsIgnoreCase("Y"))
			uvCodeGenerationStr="Yes";
		else
			uvCodeGenerationStr="No";
	}


	/**
	 * @param security
	 */
	public void setSsn(SocialSecurity security)
	{
		ssn = security;
	}

	/**
	 * Sets the logonId.
	 * @param logonId
	 */
	public void setLogonId(String aLogonId)
	{
		this.logonId = aLogonId;
	}

	/**
	 * @return
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @return
	 */
	public int getUserProfilesSize()
	{
		return userProfilesSize;
	}
	
	/**
	 * @return
	 */
	public String getNumUsers()
	{
		return numUsers;
	}

	/**
	 * @param string
	 */
	public void setAction(String string)
	{
		action = string;
	}

	/**
	 * @param string
	 */
	public void setUserProfilesSize(int size)
	{
		userProfilesSize = size;
	}

	/**
	 * @return Collection
	 */
	public Collection getUserProfiles()
	{
		return userProfiles;
	}

	/**
	 * @return Collection
	 */
	public Collection getUserProfileDetails()
	{
		return userProfileDetails;
	}
	/**
	 * @param coll
	 */
	public void setUserProfiles(Collection coll)
	{
		userProfiles = coll;
	}
	
	/**
	 * @param coll
	 */
	public void setUserProfileDetails(Collection coll)
	{
		userProfileDetails = coll;
	}

	/**
	 * @return Collection
	 */
	public Collection getUserProfileHistory()
	{
		return userProfileHistory;
	}

	/**
	 * @param coll
	 */
	public void setUserProfileHistory(Collection coll)
	{
		userProfileHistory = coll;
	}

	/**
	 * @return Collection
	 */
	public Collection getMatchingProfiles()
	{
		return matchingProfiles;
	}

	/**
	 * @param coll
	 */
	public void setMatchingProfiles(Collection coll)
	{
		matchingProfiles = coll;
	}

	public void fillUserProfileData(IUserProfileUpdate userProfile)
	{
		userProfile.setEmail(getEmail());
		userProfile.setFirstName(getUserName().getFirstName());
		userProfile.setLastName(getUserName().getLastName());
		userProfile.setMiddleName(getUserName().getMiddleName());
		userProfile.setSsn(getSsn().getSSN());
		userProfile.setLogonId(getLogonId());
		userProfile.setDepartmentId(getDepartmentId());
		userProfile.setDateOfBirth(DateUtil.stringToDate(getDateOfBirth(),"MM/dd/yyyy"));
		userProfile.setInactivationDate(DateUtil.stringToDate(getInactivationDate(),"MM/dd/yyyy"));
		userProfile.setInactivationTime(getInactivationTime());
		userProfile.setUserStatus(getUserStatusId());
//		userProfile.setPublicInd(getPublicInd());		
		userProfile.setComments(getComments());
		userProfile.setCustomCodeGeneration(getCustomCodeGeneration());
		userProfile.setGenericUserType(getGenericUserType());
		userProfile.setGenericUserTypeId(getGenericUserTypeId());
		userProfile.setOrgCode(getOrgCode());
		userProfile.setPhoneNum(getPhoneNum().getPhoneNumber());
		userProfile.setPhoneExt(getPhoneNum().getExt());
		userProfile.setRequestorFirstName(getRequestorName().getFirstName());
		userProfile.setRequestorLastName(getRequestorName().getLastName());
		if(getUvCodeGeneration().equalsIgnoreCase(UIConstants.YES))
			userProfile.setUvCodeGeneration(true);
		else
			userProfile.setUvCodeGeneration(false);
		userProfile.setBadgeNum("");
		userProfile.setOtherIdNum("");
		if(getAction().equalsIgnoreCase("updateSummary"))
		{
			userProfile.setInactivationDate(DateUtil.stringToDate(getInactivationDate(),"MM/dd/yyyy"));
		}
	}
	
	public static class UserProfile{
		private String logonId="";
		private String departmentId="";
		private Date dateOfBirth =null;
		private SocialSecurity ssn = new SocialSecurity("");
		private PhoneNumber phoneNum=new PhoneNumber("");
		private String userStatus="";
		private Name userName=new Name();
		private String trainingInd="";
		private String genericUserType="";
		/**
		 * Returns the logonId.
		 * @return String
		 */
		public String getLogonId()
		{
			return logonId;
		}	
		
		/**
		 * Sets the logonId.
		 * @param logonId
		 */
		public void setLogonId(String aLogonId)
		{
			this.logonId = aLogonId;
		}
		
		/**
		 * @return
		 */
		public String getDepartmentId()
		{
			return departmentId;
		}
		
		/**
		 * @param string
		 */
		public void setDepartmentId(String string)
		{
			departmentId = string;
		}
		
		/**
		 * @return
		 */
		public SocialSecurity getSsn()
		{
			return ssn;
		}
		

		/**
		 * @param security
		 */
		public void setSsn(SocialSecurity security)
		{
			ssn = security;
		}

		/**
		 * @return
		 */
		public PhoneNumber getPhoneNum()
		{
			return phoneNum;
		}
		/**
		 * @param number
		 */
		public void setPhoneNum(PhoneNumber number)
		{
			phoneNum = number;
		}
		
		/**
		 * @return
		 */
		public String getUserStatus()
		{
			return userStatus;
		}
		
	
		/**
		 * @param string
		 */
		public void setUserStatus(String string)
		{
			userStatus = string;
		}

		/**
		 * @return
		 */
		public Date getDateOfBirth()
		{
			return dateOfBirth;
		}
		/**
		 * @param string
		 */
		public void setDateOfBirth(Date dob)
		{
			dateOfBirth = dob;
		}
		/**
		 * @return
		 */
		public Name getUserName()
		{
			return userName;
		}
		
		/**
		 * @return
		 */
		public String getUserNameString()
		{
			return userName.toString();
		}
		/**
		 * @param name
		 */
		public void setUserName(Name name)
		{
			userName = name;
		}
		
		/**
		 * Returns the trainingInd.
		 * @return String
		 */
		public String getTrainingInd()
		{
			return trainingInd;
		}	
		/**
		 * @param string
		 */
		public void setTrainingInd(String string)
		{
			trainingInd = string;
		}
		

		/**
		 * @return Returns the genericUserType.
		 */
		public String getGenericUserType() {
			return genericUserType;
		}
		/**
		 * @param genericUserType The genericUserType to set.
		 */
		public void setGenericUserType(String genericUserType) {
			this.genericUserType = genericUserType;
		}
	}
	
	public static class UserProfileDetail extends UserProfile
	{
		private String activationDate="";
		private String activationTime="";
		private Name activatedBy = new Name();
		private String agencyId = "";
		private String agencyName = "";
		private String badgeNum = "";
		private String comments = "";
		private String createDate = "";
		private String createTime = "";
		private String createdBy = "";
		private String departmentName = "";		
		private String email = "";
		private String genericUserType = "";
		private String inactivationDate ="";
		private String inactivationReqDate = "";
		private Name inactivatedBy = new Name();
		private String inactivationTime = "";
		private String inactivationReqTime = "";
		private String OPID = "";
		private String orgCode = "";
		private String otherIdNum = "";
//		private String publicIndString="";
		private Name requestorName = new Name();
		private String trainingInd = "";
		private String transferDate = "";
		private String deptTransferReqDate = "";
		private String transferTime = "";
		private String deptTransferReqTime = "";
		private String userType = "";
		private String jims2UserId = "";
		private String jims2Password= "";
		private String jims2DepartmentName="";
		private Name jims2UserName=new Name();
		private String forgottenPasswdPhrase = "";
		private String forgottenPasswdPhraseId = "";
		private String answer = ""; 
		
		/**
			 * @return
			 */
			public Name getActivatedBy()
			{
				return activatedBy;
			}

			/**
			 * @return
			 */
			public String getActivationDate()
			{
				return activationDate;
			}    
		
			/**
			 * @return
			 */
			public String getActivationTime()
			{
				return activationTime;
			}

			/**
			 * @return
			 */
			public String getAgencyId()
			{
				return agencyId;
			}

			/**
			 * @return
			 */
			public String getAgencyName()
			{
				return agencyName;
			}
			
			/**
			*  
			* @return the answer
			*/
		   public String getAnswer()
		   {
			   return answer;
		   } //end of ui.contact.user.form.UserProfileForm.getAnswer


			/**
			 * @return
			 */
			public String getBadgeNum()
			{
				return badgeNum;
			}

			/**
			 * @return
			 */
			public String getComments()
			{
				return comments;
			}

			/**
			 * @return
			 */
			public String getCreatedBy()
			{
				return createdBy;
			}
			/**
			 * @return
			 */
			public String getCreateDate()
			{
				return createDate;
			}
			/**
			 * @return
			 */
			public String getCreateTime()
			{
				return createTime;
			}

 
		
			/**
			 * @return
			 */
			public String getDepartmentName()
			{
				return departmentName;
			}
			
			/**
			 * @return
			 */
			public String getDeptTransferReqDate()
			{
				return deptTransferReqDate;
			}
			
			/**
			 * @return
			 */
			public String getDeptTransferReqTime()
			{
				return deptTransferReqTime;
			}
			

		
			/**
			 * @return
			 */
			public String getEmail()
			{
				return email;
			}
			
			/**
			 *  
			 * @return forgottenPasswdPhrase
			 */
			public String getForgottenPasswdPhrase()
			{
				return forgottenPasswdPhrase;
			} //end of ui.contact.user.form.UserProfileForm.getForgottenPasswdPhrase

			/**
			 *  
			 * @return forgottenPasswdPhraseId
			 */
			public String getForgottenPasswdPhraseId()
			{
				return forgottenPasswdPhraseId;
			} //end of ui.contact.user.form.UserProfileForm.getForgottenPasswdPhraseId

			/**
			 * @return
			 */
			public String getGenericUserType()
			{
				return genericUserType;
			}
			
		
			/**
			 * @return
			 */
			public Name getInactivatedBy()
			{
				return inactivatedBy;
			}

			/**
			 * @return
			 */
			public String getInactivationDate()
			{
				return inactivationDate;
			}
			
	
			/**
			 * @return
			 */
			public String getInactivationReqDate()
			{
				return inactivationReqDate;
			}

			
			/**
			 * @return
			 */
			public String getInactivationTime()
			{
				return inactivationTime;
			}
			
			/**
			 * @return
			 */
			public String getInactivationReqTime()
			{
				return inactivationReqTime;
			}
			
			/**
			 *  
			 * @return departmentName
			 */
			public String getJims2DepartmentName()
			{
				return jims2DepartmentName;
			} //end of ui.contact.user.form.UserProfileForm.getJims2DepartmentName
			
			/**
			 *  
			 * @return jims2UserId 
			 */
			public String getJims2UserId()
			{
				return jims2UserId;
			} //end of ui.contact.user.form.UserProfileForm.getJims2UserId

			/**
			 *  
			 * @return jims2UserName 
			 */
			public Name getJims2UserName()
			{
				return jims2UserName;
			} //end of ui.contact.user.form.UserProfileForm.getJims2UserName
			/**
			 *  
			 * @return jims2Password 
			 */
			public String getJims2Password()
			{
				return jims2Password;
			} //end of ui.contact.user.form.UserProfileForm.getJims2Password
	

			/**
			 * @return
			 */
			public String getOPID()
			{
				return OPID;
			}

			/**
			 * @return
			 */
			public String getOrgCode()
			{
				return orgCode;
			}

			/**
			 * @return
			 */
			public String getOtherIdNum()
			{
				return otherIdNum;
			}


			/**
			 * @return
			 */
//			public String getPublicIndString()
//			{
//				return publicIndString;
//			}

			/**
			 * @return
			 */
			public Name getRequestorName()
			{
				return requestorName;
			}


			/**
			 * @return
			 */
			public String getTrainingInd()
			{
				return trainingInd;
			}

			/**
			 * @return
			 */
			public String getTransferDate()
			{
				return transferDate;
			}

		
			/**
			 * @return
			 */
			public String getTransferTime()
			{
				return transferTime;
			}


			/**
			 * @return
			 */
			public String getUserType()
			{
				return userType;
			}
	

			/**
			 * @param string
			 */
			public void setActivatedBy(Name string)
			{
				activatedBy = string;
			}

  
			/**
			 * @param string
			 */
			public void setActivationDate(String string)
			{
				activationDate =string;
			}
	
			/**
			 * @param string
			 */
			public void setActivationTime(String string)
			{
				activationTime =string;
			}
	
			/**
			 * @param string
			 */
			public void setAgencyId(String string)
			{
				agencyId = string;
			}

			/**
			 * @param string
			 */
			public void setAgencyName(String string)
			{
				agencyName = string;
			}
			
			/**
			*  
			* @param answer
			*/
		   public void setAnswer(String answer)
		   {
			   this.answer = answer;
		   } //end of ui.contact.user.form.UserProfileForm.setAnswer


			/**
			 * @param string
			 */
			public void setBadgeNum(String string)
			{
				badgeNum = string;
			}

			/**
			 * @param string
			 */
			public void setComments(String string)
			{
				comments = string;
			}
	
			/**
			 * @param created by
			 */
			public void setCreatedBy(String create)
			{
				createdBy = create;
			}

			/**
			 * @param create date
			 */
			public void setCreateDate(String create)
			{
				createDate = create;
			}



			/**
			 * @param string
			 */
			public void setCreateTime(String create)
			{
				createTime = create;
			}
	
	
			/**
			 * @param string
			 */
			public void setDepartmentName(String string)
			{
				departmentName = string;
			}
				/**
			 * @param string
			 */
			public void setDeptTransferReqDate(String string)
			{
				deptTransferReqDate = string;
			}

			/**
			 * @param string
			 */
			public void setDeptTransferReqTime(String string)
			{
				deptTransferReqTime = string;
			}
	
			/**
			 * @param string
			 */
			public void setEmail(String string)
			{
				email = string;
			}
			
			/**
			 *  
			 * @param string
			 */
			public void setForgottenPasswdPhrase(String string)
			{
				forgottenPasswdPhrase = string;
			} //end of ui.contact.user.form.UserProfileForm.setForgottenPasswdPhrase
	
			/**
			 *  
			 * @param forgottenPasswdPhraseId
			 */
			public void setForgottenPasswdPhraseId(String forgottenPasswdPhraseId)
			{
				this.forgottenPasswdPhraseId = forgottenPasswdPhraseId;
			} //end of ui.contact.user.form.UserProfileForm.setForgottenPasswdPhraseId


			/**
			 * @param string
			 */
			public void setGenericUserType(String string)
			{
				genericUserType = string;
			}
	
	
			/**
			 * @return
			 */
			public void setInactivatedBy(Name name)
			{
				inactivatedBy = name;
			}

			/**
			 * @param string
			 */
			public void setInactivationDate(String inactivate)
			{
				inactivationDate =inactivate;
			}
			
			/**
			 * @param string
			 */
			public void setInactivationReqDate(String inactivate)
			{
				inactivationReqDate =inactivate;
			}
	
			/**
			 * @param string
			 */
			public void setInactivationDateAsDate(String inactivate)
			{
				inactivationDate =inactivate;
			}


			/**
			 * @param string
			 */
			public void setInactivationTime(String inactiveTime)
			{
				inactivationTime = inactiveTime;
			}
			
			/**
			 * @param string
			 */
			public void setInactivationReqTime(String inactiveTime)
			{
				inactivationReqTime = inactiveTime;
			}
			/**
			 *  
			 * @return name
			 */
			public void setJims2DepartmentName(String name)
			{
				jims2DepartmentName = name;
			} //end of ui.contact.user.form.UserProfileForm.setJims2DepartmentName
	
		
			/**	
			 *  
			 * @param jims2UserId
			 */
			public void setJims2UserId(String JIMS2UserId)
			{
				jims2UserId = JIMS2UserId;
			} //end of ui.contact.user.form.UserProfileForm.setJims2UserId
			
			/**	
			 *  
			 * @param name
			 */
			public void setJims2UserName(Name name)
			{
				jims2UserName = name;
			} //end of ui.contact.user.form.UserProfileForm.setJims2UserName
	
			/**	
			 *  
			 * @param jims2Password
			 */
			public void setJims2Password(String JIMS2Password)
			{
				jims2Password = JIMS2Password;
			} //end of ui.contact.user.form.UserProfileForm.setJims2Password
			/**
			 * @param string
			 */
			public void setOPID(String string)
			{
				OPID = string;
			}

			/**
			 * @param string
			 */
			public void setOrgCode(String string)
			{
				orgCode = string;
			}

			/**
			 * @param string
			 */
			public void setOtherIdNum(String string)
			{
				otherIdNum = string;
			}

		
			/**
			 * @param string
			 */
//			public void setPublicIndString(String string)
//			{
//				publicIndString = string;
//			}


			/**
			 * @param name
			 */
			public void setRequestorName(Name name)
			{
				requestorName = name;
			}
	

			/**
			 * @param string
			 */
			public void setTrainingInd(String string)
			{
				trainingInd = string;
			}

			/**
			 * @param transfer
			 */
			public void setTransferDate(String transfer)
			{
				transferDate = transfer;
			}

			/**
			 * @param transfer
			 */
			public void setTransferTime(String transfer)
			{
				transferTime = transfer;
			}

	
			/**
			 * @param string
			 */
			public void setUserType(String string)
			{
				userType = string;
		
			}
	
	
	
	}
	public void getGenericUserTypeCodeDescription()
	{
			
		Iterator iter = genericUserTypes.iterator();		
		while(iter.hasNext())
		{
			CodeResponseEvent codeResp = (CodeResponseEvent) iter.next();
			if(codeResp.getCode().equals(this.genericUserTypeId))
			{
				genericUserType=codeResp.getDescription();
				return;
			}
	
		}
	}
	public void getAgencyStatusCodeDescription()
		{
			
			Iterator iter = userStatuses.iterator();		
			while(iter.hasNext())
			{
				CodeResponseEvent codeResp = (CodeResponseEvent) iter.next();
				if(codeResp.getCode().equals(this.userStatusId))
				{
					userStatus=codeResp.getDescription();
					return;
				}
	
			}
		}
	public void getUserTypeCodeDescription()
	{
	
		Iterator iter = userTypes.iterator();		
		while(iter.hasNext())
		{
			CodeResponseEvent codeResp = (CodeResponseEvent) iter.next();
			if(codeResp.getCode().equals(this.userTypeId))
			{
				userType=codeResp.getDescription();
				return;
			}

		}
	}
	
	public void getWorkDaysCodeDescription()
	{
	
		Iterator iter = workDays.iterator();		
		while(iter.hasNext())
		{
			CodeResponseEvent codeResp = (CodeResponseEvent) iter.next();
			if(codeResp.getCode().equals(this.inactivationTimeId))
			{
				inactivationTime=codeResp.getDescription();
				return;
			}

		}
	}

	/**
	 * @return Returns the jims2LogonId.
	 */
	public String getJims2LogonId() {
		return jims2LogonId;
	}
	/**
	 * @param jims2LogonId The jims2LogonId to set.
	 */
	public void setJims2LogonId(String jims2LogonId) {
		this.jims2LogonId = jims2LogonId;
	}
	/**
	 * @return Returns the answer.
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer The answer to set.
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * @return Returns the deptTransferReqDate.
	 */
	public String getDeptTransferReqDate() {
		return deptTransferReqDate;
	}
	/**
	 * @param deptTransferReqDate The deptTransferReqDate to set.
	 */
	public void setDeptTransferReqDate(String deptTransferReqDate) {
		this.deptTransferReqDate = deptTransferReqDate;
	}
	/**
	 * @return Returns the deptTransferReqTime.
	 */
	public String getDeptTransferReqTime() {
		return deptTransferReqTime;
	}
	/**
	 * @param deptTransferReqTime The deptTransferReqTime to set.
	 */
	public void setDeptTransferReqTime(String deptTransferReqTime) {
		this.deptTransferReqTime = deptTransferReqTime;
	}
	/**
	 * @return Returns the forgottenPasswdPhrase.
	 */
	public String getForgottenPasswdPhrase() {
		return forgottenPasswdPhrase;
	}
	/**
	 * @param forgottenPasswdPhrase The forgottenPasswdPhrase to set.
	 */
	public void setForgottenPasswdPhrase(String forgottenPasswdPhrase) {
		this.forgottenPasswdPhrase = forgottenPasswdPhrase;
	}
	/**
	 * @return Returns the inactivationReqDate.
	 */
	public String getInactivationReqDate() {
		return inactivationReqDate;
	}
	/**
	 * @param inactivationReqDate The inactivationReqDate to set.
	 */
	public void setInactivationReqDate(String inactivationReqDate) {
		this.inactivationReqDate = inactivationReqDate;
	}
	/**
	 * @return Returns the inactivationReqTime.
	 */
	public String getInactivationReqTime() {
		return inactivationReqTime;
	}
	/**
	 * @param inactivationReqTime The inactivationReqTime to set.
	 */
	public void setInactivationReqTime(String inactivationReqTime) {
		this.inactivationReqTime = inactivationReqTime;
	}
	/**
	 * @return Returns the jims2DepartmentName.
	 */
	public String getJims2DepartmentName() {
		return jims2DepartmentName;
	}
	/**
	 * @param jims2DepartmentName The jims2DepartmentName to set.
	 */
	public void setJims2DepartmentName(String jims2DepartmentName) {
		this.jims2DepartmentName = jims2DepartmentName;
	}
	/**
	 * @return Returns the jims2Password.
	 */
	public String getJims2Password() {
		return jims2Password;
	}
	/**
	 * @param jims2Password The jims2Password to set.
	 */
	public void setJims2Password(String jims2Password) {
		this.jims2Password = jims2Password;
	}
	/**
	 * @return Returns the jims2UserId.
	 */
	public String getJims2UserId() {
		return jims2UserId;
	}
	/**
	 * @param jims2UserId The jims2UserId to set.
	 */
	public void setJims2UserId(String jims2UserId) {
		this.jims2UserId = jims2UserId;
	}
/**
 * @return Returns the jims2UserName.
 */
public Name getJims2UserName() {
	return jims2UserName;
}
/**
 * @param jims2UserName The jims2UserName to set.
 */
public void setJims2UserName(Name jims2UserName) {
	this.jims2UserName = jims2UserName;
}
/**
 * @return Returns the userIsLA.
 */
public String getUserIsLA() {
	return userIsLA;
}
/**
 * @param userIsLA The userIsLA to set.
 */
public void setUserIsLA(String userIsLA) {
	this.userIsLA = userIsLA;
}
	/**
	 * @return Returns the allowUserUpdate.
	 */
	public String getAllowUserUpdate() {
		return allowUserUpdate;
	}
	/**
	 * @param allowUserUpdate The allowUserUpdate to set.
	 */
	public void setAllowUserUpdate(String allowUserUpdate) {
		this.allowUserUpdate = allowUserUpdate;
	}
}
