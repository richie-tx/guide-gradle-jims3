/*
 * Created on Apr 22, 2004
 */
package messaging.contact.user.reply;

import java.util.Date;

import messaging.domintf.contact.user.IUserProfile;
import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 */
public class UserResponseEvent extends ResponseEvent implements IUserProfile, Comparable
{
	private String lastName;
	private String firstName;
	private String middleName;
	private String activatedByFirstName;
	private String activatedByLastName;
	private Date activationDate;
	private String activationTime;
	private String agencyId;
	private String agencyName;
	private Date transferDate;
	private String transferTime;
	private Date deptTransferRequestDate;
	private String deptTransferRequestTime;
	private String comments;
	private Date createDate;
	private String createTime;
	private String createdByFirstName;
	private String createdByLastName;
	private String createdByName;
	private Date dateOfBirth;
	private String inactivatedByFirstName;
	private String inactivatedByLastName;
	private String departmentId;
	private String departmentName;
	private String email;
	private Date inactivationDate;
	private String inactivationTimeId;
	private Date inactivationRequestDate;
	private String inactivationRequestTimeId;	
	private String genericUserType;
	private Date lastLoginDate;
	private String phoneNum;
	private String phoneExt;
	private String requestorFirstName;
	private String requestorLastName;
	private String requestorName;
	private String ssn;
	private String trainingInd;
	private String logonId;
	private String operatorId;
	private String orgCode;
	private String userStatus;
	private String userType;
	private String userTypeId;
	private String badgeNum;
	private String otherIdNum;
	private String title;
	private String publicInd;
	private String createOfficerProfileInd;
	private String formattedName;
	
	/**
	 * @return java.util.Date
	 */
	public Date getActivationDate()
	{
		return activationDate;
	}

	public String getActivationTime()
	{
		return activationTime;
	}

	/**
	 * @return String
	 */
	public String getActivatedByLastName()
	{
		return activatedByLastName;
	}

	/**
	 * @return String
	 */
	public String getActivatedByFirstName()
	{
		return activatedByFirstName;
	}

	/**
	 * @return String
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * @return String
	 */
	public String getAgencyName()
	{
		return agencyName;
	}

	/**
	 * @return java.util.Date
	 */
	public Date getTransferDate()
	{
		return transferDate;
	}
	
	/**
	 * @return java.util.Date
	 */
	public Date getDeptTransferRequestDate()
	{
		return deptTransferRequestDate;
	}

	/**
	 * @return String
	 */
	public String getDeptTransferRequestTime()
	{
		return deptTransferRequestTime;
	}


	/**
	 * @return
	 */
	public String getComments()
	{
		return comments;
	}

	/**
	 * @return java.util.Date
	 */
	public Date getCreateDate()
	{
		return createDate;
	}

	public String getCreateTime()
	{
		return createTime;
	}

	/**
	 * @return String
	 */
	public String getCreatedByFirstName()
	{
		return createdByFirstName;
	}

	/**
	 * @return String
	 */
	public String getCreatedByLastName()
	{
		return createdByLastName;
	}

	/**
	 * @return String
	 */
	public String getCreatedByName()
	{
		return createdByName;
	}
	
	/**
	 * @return java.util.Date
	 */
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	/**
	 * @return String
	 */
	public String getInactivatedByLastName()
	{
		return inactivatedByLastName;
	}

	/**
	 * @return String
	 */
	public String getInactivatedByFirstName()
	{
		return inactivatedByFirstName;
	}

	/**
	 * @return String
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @return java.util.Date
	 */
	public Date getInactivationDate()
	{
		return inactivationDate;
	}

	/**
	 * @return java.util.Date
	 */
	public Date getInactivationRequestDate()
	{
		return inactivationRequestDate;
	}
	/**
	 * @return String
	 */
	public String getInactivationTimeId()
	{
		return inactivationTimeId;
	}
	
	/**
	 * @return String
	 */
	public String getInactivationRequestTimeId()
	{
		return inactivationRequestTimeId;
	}

	/**
	 * @return String
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return String
	 */
	public String getGenericUserType()
	{
		return genericUserType;
	}

	/**
	 * @return java.util.Date
	 */
	public Date getLastLoginDate()
	{
		return lastLoginDate;
	}

	/**
	 * @return String
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return String
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @return String
	 */
	public String getPhoneNum()
	{
		return phoneNum;
	}
	
	/**
	 * @return String
	 */
	public String getPublicInd()
	{
		return publicInd;
	}

	/**
	 * @return String
	 */
	public String getRequestorFirstName()
	{
		return requestorFirstName;
	}

	/**
	 * @return String
	 */
	public String getRequestorLastName()
	{
		return requestorLastName;
	}

	/**
	 * @return String
	 */
	public String getRequestorName()
	{
		return requestorName;
	}

	/**
	 * @return String
	 */
	public String getSsn()
	{
		return ssn;
	}

	/**
	 * @return String
	 */
	public String getTrainingInd()
	{
		return trainingInd;
	}

	/**
	 * @return String
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * @return String
	 */
	public String getUserStatus()
	{
		return userStatus;
	}

	/**
	 * @return String
	 */
	public String getPhoneExt()
	{
		return phoneExt;
	}

	/**
	 * @return String
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	/**
	 * @return String
	 */
	public String getDepartmentName()
	{
		return departmentName;
	}

	/**
	 * @return String
	 */
	public String getBadgeNum()
	{
		return badgeNum;
	}

	/**
	 * @return String
	 */
	public String getOtherIdNum()
	{
		return otherIdNum;
	}

	/**
	 * @return String
	 */
	public String getOperatorId()
	{
		return operatorId;
	}

	/**
	 * @return String
	 */
	public String getOrgCode()
	{
		return orgCode;
	}

	/**
	 * @return String
	 */
	public String getUserType()
	{
		return userType;
	}

	/**
	 * @return String
	 */
	public String getUserTypeId()
	{
		return userTypeId;
	}
	/**
	 * @return String
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @return String
	 */
	public String getTransferTime()
	{
		return transferTime;
	}

	/**
	 * @param activationDate
	 */
	public void setActivationDate(Date activationDate)
	{
		this.activationDate = activationDate;
	}

	/**
	 * @param activationTime
	 */
	public void setActivationTime(String activationTime)
	{
		this.activationTime = activationTime;
	}

	/**
	 * @param activatedByLastName
	 */
	public void setActivatedByLastName(String activatedByLastName)
	{
		this.activatedByLastName = activatedByLastName;
	}

	/**
	 * @param activatedByFirstName
	 */
	public void setActivatedByFirstName(String activatedByFirstName)
	{
		this.activatedByFirstName = activatedByFirstName;
	}

	/**
	 * @param agencyId
	 */
	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
	}

	/**
	 * @param agencyName
	 */
	public void setAgencyName(String agencyName)
	{
		this.agencyName = agencyName;
	}

	/**
	 * @param transferDate
	 */
	public void setTransferDate(Date transferDate)
	{
		this.transferDate = transferDate;
	}
	
	/**
	 * @param transferRequestDate
	 */
	public void setDeptTransferRequestDate(Date transferRequestDate)
	{
		this.deptTransferRequestDate = transferRequestDate;
	}

	/**
	 * @param transferRequestTime
	 */
	public void setDeptTransferRequestTime(String transferReqTime)
	{
		this.deptTransferRequestTime = transferReqTime;
	}
	/**
	 * @param transferTime
	 */
	public void setTransferTime(String transferTime)
	{
		this.transferTime = transferTime;
	}

	/**
	 * @param comments
	 */
	public void setComments(String comments)
	{
		this.comments = comments;
	}

	/**
	 * @param createDate
	 */
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	/**
	 * @param createTime
	 */
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	/**
	 * @param createdBy
	 */
	public void setCreatedByFirstName(String createdByFirstName)
	{
		this.createdByFirstName = createdByFirstName;
	}

	/**
	 * @param createdByName
	 */
	public void setCreatedByName(String createdByName)
	{
		this.createdByName = createdByName;
	}

	/**
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @param inactivatedByLastName
	 */
	public void setInactivatedByLastName(String inactivatedByLastName)
	{
		this.inactivatedByLastName = inactivatedByLastName;
	}

	/**
	 * @param inactivatedByFirstName
	 */
	public void setInactivatedByFirstName(String inactivatedByFirstName)
	{
		this.inactivatedByFirstName = inactivatedByFirstName;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @param inactivationDate
	 */
	public void setInactivationDate(Date inactivationDate)
	{
		this.inactivationDate = inactivationDate;
	}
	/**
	 * @param inactivationReqDate
	 */
	public void setInactivationRequestDate(Date inactivationReqDate)
	{
		this.inactivationRequestDate = inactivationReqDate;
	}

	/**
	 * @param inactivationTimeId
	 */
	public void setInactivationTimeId(String inactivationTimeId)
	{
		this.inactivationTimeId = inactivationTimeId;
	}
	/**
	 * @param inactivationReqTimeId
	 */
	public void setInactivationRequestTimeId(String inactivationReqTimeId)
	{
		this.inactivationRequestTimeId = inactivationReqTimeId;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @param badgeNum
	 */
	public void setBadgeNum(String badgeNum)
	{
		this.badgeNum = badgeNum;
	}

	/**
	 * @param otherIdNum
	 */
	public void setOtherIdNum(String otherIdNum)
	{
		this.otherIdNum = otherIdNum;
	}

	/**
	 * @param lastLoginDate
	 */
	public void setLastLoginDate(Date lastLoginDate)
	{
		this.lastLoginDate = lastLoginDate;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @param middleName
	 */
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	/**
	 * @param phoneNum
	 */
	public void setPhoneNum(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}
	
	/**
	 * @param publicInd
	 */
	public void setPublicInd(String ind)
	{
		publicInd=ind;
	}

	/**
	 * @param requestorFirstName
	 */
	public void setRequestorFirstName(String requestorFirstName)
	{
		this.requestorFirstName = requestorFirstName;
	}

	/**
	 * @param requestorLastName
	 */
	public void setRequestorLastName(String requestorLastName)
	{
		this.requestorLastName = requestorLastName;
	}

	/**
	 * @param requestorName
	 */
	public void setRequestorName(String requestorName)
	{
		this.requestorName = requestorName;
	}

	/**
	 * @param ssn
	 */
	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}

	/**
	 * @param trainingInd
	 */
	public void setTrainingInd(String trainingInd)
	{
		this.trainingInd = trainingInd;
	}

	/**
	 * @param logonId
	 */
	public void setLogonId(String logonId)
	{
		this.logonId = logonId;
	}

	/**
	 * @param userStatus
	 */
	public void setUserStatus(String userStatus)
	{
		this.userStatus = userStatus;
	}

	/**
	 * @param userType
	 */
	public void setUserType(String userType)
	{
		this.userType = userType;
	}
	/**
	 * @param userTypeId
	 */
	public void setUserTypeId(String userTypeId)
	{
		this.userTypeId = userTypeId;
	}
	/**
	 * @param genericUserType
	 */
	public void setGenericUserType(String genericUserType)
	{
		this.genericUserType = genericUserType;
	}

	/**
	 * @param createdByLastName
	 */
	public void setCreatedByLastName(String createdByLastName)
	{
		this.createdByLastName = createdByLastName;
	}

	/**
	 * @param phoneExt
	 */
	public void setPhoneExt(String phoneExt)
	{
		this.phoneExt = phoneExt;
	}

	/**
	 * @param departmentId
	 */
	public void setDepartmentId(String departmentId)
	{
		this.departmentId = departmentId;
	}

	/**
	 * @param departmentName
	 */
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}

	/**
	 * @param operatorId
	 */
	public void setOperatorId(String operatorId)
	{
		this.operatorId = operatorId;
	}

	/**
	 * @param orgCode
	 */
	public void setOrgCode(String orgCode)
	{
		this.orgCode = orgCode;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/* (non-Javadoc)
	 * @see messaging.domintf.contact.user.IUserProfile#getCreateOfficerProfileInd()
	 */
	public String getCreateOfficerProfileInd()
	{
		return createOfficerProfileInd;
	}

	/* (non-Javadoc)
	 * @see messaging.domintf.contact.user.IUserProfile#setCreateOfficerProfileInd(java.lang.String)
	 */
	public void setCreateOfficerProfileInd(String createOfficerProfileInd)
	{
		this.createOfficerProfileInd = createOfficerProfileInd;
	}
	public int compareTo(Object obj) throws ClassCastException
	{
		UserResponseEvent evt = (UserResponseEvent) obj;
		return logonId.compareTo(evt.getLogonId());
	}
	/**
	 * @return Returns the formattedName.
	 */
	public String getFormattedName() {
		return formattedName;
	}
	/**
	 * @param formattedName The formattedName to set.
	 */
	public void setFormattedName(String formattedName) {
		this.formattedName = formattedName;
	}
}