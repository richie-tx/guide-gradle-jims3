//Source file: C:\\views\\MSA\\app\\src\\messaging\\user\\UpdateUserProfileEvent.java

package messaging.user;

import java.util.Date;

import messaging.domintf.contact.user.IUserProfileUpdate;
import mojo.km.messaging.RequestEvent;

public class UpdateUserProfileEvent extends RequestEvent implements IUserProfileUpdate
{
	private String logonId;
	private String firstName;
	private String lastName;
	private String middleName;
	private Date dateOfBirth;
	private String departmentId;
	private String ssn;
	private String comments;
	private String customCodeGeneration;
	private String email;
	private String genericUserType;
	private String genericUserTypeId;
	private String phoneExt;
	private String phoneNum;
	private String publicInd;
	private String requestorFirstName;
	private String requestorLastName;
	private String badgeNum;
	private String otherIdNum;
	private String orgCode;
	private boolean uvCodeGeneration;
	private String userStatus;
	private Date inactivationDate;
	private String inactivationTime;
	private Date deptTransferRequestDate;
	private String deptTransferRequestTime;

	/**
	 * Indicates whether the request is to create a new user rather than update an 
	 * existing user.
	 */
	private boolean createInd;

	/**
	 * default constructor
	 * @roseuid 43F4CEB8026B
	 */
	public UpdateUserProfileEvent()
	{

	}

	/**
	 * @param logonId
	 */
	public void setLogonId(String logonId)
	{
		this.logonId = logonId;
	}

	/**
	 * @return String
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * @param firstName
	 * @roseuid 43EA4DD80127
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return String
	 * @roseuid 43EA4DD80129
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param lastName
	 * @roseuid 43EA4DD8013B
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return String
	 * @roseuid 43EA4DD8013D
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param middleName
	 * @roseuid 43EA4DD8014F
	 */
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	/**
	 * @return String
	 * @roseuid 43EA4DD80151
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @param dateOfBirth
	 * @roseuid 43EA4DD8016D
	 */
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return java.util.Date
	 * @roseuid 43EA4DD8016F
	 */
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	/**
	 * @param departmentId
	 * @roseuid 43EA4DD80181
	 */
	public void setDepartmentId(String departmentId)
	{
		this.departmentId = departmentId;
	}

	/**
	 * @return String
	 * @roseuid 43EA4DD80183
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}
	
	public void setDeptTransferRequestDate(Date deptTransferReqDate)
	{
		this.deptTransferRequestDate = deptTransferReqDate;
	}


	public Date getDeptTransferRequestDate()
	{
		return deptTransferRequestDate;
	}
	
	public void setDeptTransferRequestTime(String deptTransferReqTime)
	{
		this.deptTransferRequestTime = deptTransferReqTime;
	}

	/**
	 * @return java.util.Date
	 * @roseuid 43EA4DD8016F
	 */
	public String getDeptTransferRequestTime()
	{
		return deptTransferRequestTime;
	}


	/**
	 * @param ssn
	 * @roseuid 43EA4DD80195
	 */
	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}

	/**
	 * @return String
	 * @roseuid 43EA4DD801A9
	 */
	public String getSsn()
	{
		return ssn;
	}

	/**
	 * @return 
	 */
	public String getUserStatus() {
		return userStatus;
	}
	
	/**
	 * @param requestorFirstName
	 * @roseuid 43F4CEBD0330
	 */
	public void setRequestorFirstName(String requestorFirstName)
	{
		this.requestorFirstName = requestorFirstName;
	}

	/**
	 * @return String
	 * @roseuid 43F4CEBE0328
	 */
	public String getRequestorFirstName()
	{
		return requestorFirstName;
	}

	/**
	 * @param requestorLastName
	 * @roseuid 43F4CEBF017A
	 */
	public void setRequestorLastName(String requestorLastName)
	{
		this.requestorLastName = requestorLastName;
	}

	/**
	 * @return String
	 * @roseuid 43F4CEC0030C
	 */
	public String getRequestorLastName()
	{
		return requestorLastName;
	}

	/**
	 * @param genericUserType
	 * @roseuid 43F4CEC2034B
	 */
	public void setGenericUserType(String genericUserType)
	{
		this.genericUserType = genericUserType;
	}

	
	/**
	 * @return String
	 * @roseuid 43F4CEC303B1
	 */
	public String getGenericUserType()
	{
		return genericUserType;
	}
	
	/**
	 * @param genericUserType
	 * @roseuid 43F4CEC2034B
	 */
	public void setGenericUserTypeId(String genericUserTypeId)
	{
		this.genericUserTypeId = genericUserTypeId;
	}

	/**
	 * @return String
	 * @roseuid 43F4CEC303B1
	 */
	public String getGenericUserTypeId()
	{
		return genericUserTypeId;
	}
	/**
	 * @param badgeNum
	 * @roseuid 43F4CEC4022C
	 */
	public void setBadgeNum(String badgeNum)
	{
		this.badgeNum = badgeNum;
	}

	/**
	 * @return String
	 * @roseuid 43F4CEC50205
	 */
	public String getBadgeNum()
	{
		return badgeNum;
	}

	/**
	 * @param otherIdNum
	 * @roseuid 43F4CEC5020F
	 */
	public void setOtherIdNum(String otherIdNum)
	{
		this.otherIdNum = otherIdNum;
	}

	/**
	 * @return String
	 * @roseuid 43F4CEC60275
	 */
	public String getOtherIdNum()
	{
		return otherIdNum;
	}

	/**
	 * @param phoneNum
	 * @roseuid 43F4CEC700F0
	 */
	public void setPhoneNum(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}

	/**
	 * @return String
	 * @roseuid 43F4CEC800B5
	 */
	public String getPhoneNum()
	{
		return phoneNum;
	}

	/**
	 * @param phoneExt
	 * @roseuid 43F4CEC802FA
	 */
	public void setPhoneExt(String phoneExt)
	{
		this.phoneExt = phoneExt;
	}

	/**
	 * @return String
	 * @roseuid 43F4CEC9035F
	 */
	public String getPhoneExt()
	{
		return phoneExt;
	}

	/**
	 * @param customCodeGeneration
	 * @roseuid 43F4CECA01C6
	 */
	public void setCustomCodeGeneration(String customCodeGeneration)
	{
		this.customCodeGeneration = customCodeGeneration;
	}

	/**
	 * @return String
	 * @roseuid 43F4CECB01A0
	 */
	public String getCustomCodeGeneration()
	{
		return customCodeGeneration;
	}

	/**
	 * @param uvCodeGeneration
	 * @roseuid 43F4CECB03A8
	 */
	public void setUvCodeGeneration(boolean uvCodeGeneration)
	{
		this.uvCodeGeneration = uvCodeGeneration;
	}

	/**
	 * @return boolean
	 * @roseuid 43F4CECC03BE
	 */
	public boolean getUvCodeGeneration()
	{
		return uvCodeGeneration;
	}

	/**
	 * @param comments
	 * @roseuid 43F4CECD0207
	 */
	public void setComments(String comments)
	{
		this.comments = comments;
	}

	/**
	 * @return String
	 * @roseuid 43F4CECE01FE
	 */
	public String getComments()
	{
		return comments;
	}

	/**
	 * @param publicInd
	 * @roseuid 43F4CECE03CB
	 */
	public void setPublicInd(String publicInd)
	{
		this.publicInd = publicInd;
	}

	/**
	 * @return String
	 * @roseuid 43F4CED000E9
	 */
	public String getPublicInd()
	{
		return publicInd;
	}

	/**
	 * @param orgCode
	 * @roseuid 43F4CED002E7
	 */
	public void setOrgCode(String orgCode)
	{
		this.orgCode = orgCode;
	}

	/**
	 * @return String
	 * @roseuid 43F4CED1027B
	 */
	public String getOrgCode()
	{
		return orgCode;
	}

	/**
	 * @param createInd
	 * @roseuid 43F4D072039D
	 */
	public void setCreateInd(boolean createInd)
	{
		this.createInd = createInd;
	}

	/**
	 * @return boolean
	 * @roseuid 43F4D073038A
	 */
	public boolean getCreateInd()
	{
		return createInd;
	}

	/**
	 * @return boolean
	 */
	public boolean isCreate()
	{
		return createInd;
	}
	/**
	 * @return String
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @param userStatus 
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	/**
	 * @return Date
	 */
	public Date getInactivationDate()
	{
		return inactivationDate;
	}

	/**
	 * @return String
	 */
	public String getInactivationTime()
	{
		return inactivationTime;
	}

	/**
	 * @param aDate
	 */
	public void setInactivationDate(Date aDate)
	{
		inactivationDate = aDate;
	}

	/**
	 * @param inactivationTime
	 */
	public void setInactivationTime(String inactivationTime)
	{
		this.inactivationTime = inactivationTime;
	}

}
