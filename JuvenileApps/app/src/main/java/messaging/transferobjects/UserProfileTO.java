/*
 * Created on Aug 20, 2007
 *
 */
package messaging.transferobjects;

import java.util.Date;

import mojo.km.utilities.Name;

/**
 * @author cc_mdsouza
 *
 */
public class UserProfileTO
extends PersistentObjectTO 
{

	public String logonId;
	public String departmentId;
	public Date deptTransferDate;
	public Date deptTransferRequestDate;
	public String deptTransferTime;
	public String deptTransferRequestTime;
	public String trainingInd;
	public Date creationDate;
	public String creationTime;
	public String requestorFirstName;
	public String requestorLastName;
	public String inactivatedById;
	public String activatorId;
	public String departmentName;
	public String comments;
	public String userStatus;
	public String ssn;
	public String agencyId;
	public String serverRegion;
	public Date activationDate;
	public String activationTime;
	public Date lastLoginDate;
	public String agencyName;
	public String firstName;
	public String lastName;
	public String middleName;
	private String formattedName;

	public Date dateOfBirth;
	public String userTypeId;
	public String genericUserTypeId;
	public String inactivationTimeId;
	public String inactivationRequestTimeId;
	public Date inactivationDate;
	public Date inactivationRequestDate;
	public String operatorId;
	public String orgCode;
	public String publicInd;
	public String customLogonCode;

	public String JIMSLogonId;
	public String JIMS2LogonId;
	public String JIMS2Password;
	public String password;

	
	public java.util.Collection userHistories = null;
	public java.util.Collection trainingClassSchedules = null;
	
	public CodeTO userType = null;
	//public DepartmentTO department = null;
	public CodeTO inactivationTime = null;
	public CodeTO genericUserType = null;
	
	public UserProfileTO()
	{
	}
	
	
	
	
	/**
	 * @return Returns the activationDate.
	 */
	public Date getActivationDate() {
		return activationDate;
	}
	/**
	 * @param activationDate The activationDate to set.
	 */
	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}
	/**
	 * @return Returns the activationTime.
	 */
	public String getActivationTime() {
		return activationTime;
	}
	/**
	 * @param activationTime The activationTime to set.
	 */
	public void setActivationTime(String activationTime) {
		this.activationTime = activationTime;
	}
	/**
	 * @return Returns the activatorId.
	 */
	public String getActivatorId() {
		return activatorId;
	}
	/**
	 * @param activatorId The activatorId to set.
	 */
	public void setActivatorId(String activatorId) {
		this.activatorId = activatorId;
	}
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	/**
	 * @return Returns the agencyName.
	 */
	public String getAgencyName() {
		return agencyName;
	}
	/**
	 * @param agencyName The agencyName to set.
	 */
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the creationDate.
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate The creationDate to set.
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return Returns the creationTime.
	 */
	public String getCreationTime() {
		return creationTime;
	}
	/**
	 * @param creationTime The creationTime to set.
	 */
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * @return Returns the customLogonCode.
	 */
	public String getCustomLogonCode() {
		return customLogonCode;
	}
	/**
	 * @param customLogonCode The customLogonCode to set.
	 */
	public void setCustomLogonCode(String customLogonCode) {
		this.customLogonCode = customLogonCode;
	}
	/**
	 * @return Returns the dateOfBirth.
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth The dateOfBirth to set.
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return Returns the department.
	 */
/*	public DepartmentTO getDepartment() {
		return department;
	}
	*//**
	 * @param department The department to set.
	 *//*
	public void setDepartment(DepartmentTO department) {
		this.department = department;
	}*/
	/**
	 * @return Returns the departmentId.
	 */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId The departmentId to set.
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * @return Returns the departmentName.
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * @param departmentName The departmentName to set.
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * @return Returns the deptTransferDate.
	 */
	public Date getDeptTransferDate() {
		return deptTransferDate;
	}
	/**
	 * @param deptTransferDate The deptTransferDate to set.
	 */
	public void setDeptTransferDate(Date deptTransferDate) {
		this.deptTransferDate = deptTransferDate;
	}
	/**
	 * @return Returns the deptTransferRequestDate.
	 */
	public Date getDeptTransferRequestDate() {
		return deptTransferRequestDate;
	}
	/**
	 * @param deptTransferRequestDate The deptTransferRequestDate to set.
	 */
	public void setDeptTransferRequestDate(Date deptTransferRequestDate) {
		this.deptTransferRequestDate = deptTransferRequestDate;
	}
	/**
	 * @return Returns the deptTransferRequestTime.
	 */
	public String getDeptTransferRequestTime() {
		return deptTransferRequestTime;
	}
	/**
	 * @param deptTransferRequestTime The deptTransferRequestTime to set.
	 */
	public void setDeptTransferRequestTime(String deptTransferRequestTime) {
		this.deptTransferRequestTime = deptTransferRequestTime;
	}
	/**
	 * @return Returns the deptTransferTime.
	 */
	public String getDeptTransferTime() {
		return deptTransferTime;
	}
	/**
	 * @param deptTransferTime The deptTransferTime to set.
	 */
	public void setDeptTransferTime(String deptTransferTime) {
		this.deptTransferTime = deptTransferTime;
	}
	/**
	 * @return Returns the genericUserType.
	 */
	public CodeTO getGenericUserType() {
		return genericUserType;
	}
	/**
	 * @param genericUserType The genericUserType to set.
	 */
	public void setGenericUserType(CodeTO genericUserType) {
		this.genericUserType = genericUserType;
	}
	/**
	 * @return Returns the genericUserTypeId.
	 */
	public String getGenericUserTypeId() {
		return genericUserTypeId;
	}
	/**
	 * @param genericUserTypeId The genericUserTypeId to set.
	 */
	public void setGenericUserTypeId(String genericUserTypeId) {
		this.genericUserTypeId = genericUserTypeId;
	}
	/**
	 * @return Returns the inactivatedById.
	 */
	public String getInactivatedById() {
		return inactivatedById;
	}
	/**
	 * @param inactivatedById The inactivatedById to set.
	 */
	public void setInactivatedById(String inactivatedById) {
		this.inactivatedById = inactivatedById;
	}
	/**
	 * @return Returns the inactivationDate.
	 */
	public Date getInactivationDate() {
		return inactivationDate;
	}
	/**
	 * @param inactivationDate The inactivationDate to set.
	 */
	public void setInactivationDate(Date inactivationDate) {
		this.inactivationDate = inactivationDate;
	}
	/**
	 * @return Returns the inactivationRequestDate.
	 */
	public Date getInactivationRequestDate() {
		return inactivationRequestDate;
	}
	/**
	 * @param inactivationRequestDate The inactivationRequestDate to set.
	 */
	public void setInactivationRequestDate(Date inactivationRequestDate) {
		this.inactivationRequestDate = inactivationRequestDate;
	}
	/**
	 * @return Returns the inactivationRequestTimeId.
	 */
	public String getInactivationRequestTimeId() {
		return inactivationRequestTimeId;
	}
	/**
	 * @param inactivationRequestTimeId The inactivationRequestTimeId to set.
	 */
	public void setInactivationRequestTimeId(String inactivationRequestTimeId) {
		this.inactivationRequestTimeId = inactivationRequestTimeId;
	}
	/**
	 * @return Returns the inactivationTime.
	 */
	public CodeTO getInactivationTime() {
		return inactivationTime;
	}
	/**
	 * @param inactivationTime The inactivationTime to set.
	 */
	public void setInactivationTime(CodeTO inactivationTime) {
		this.inactivationTime = inactivationTime;
	}
	/**
	 * @return Returns the inactivationTimeId.
	 */
	public String getInactivationTimeId() {
		return inactivationTimeId;
	}
	/**
	 * @param inactivationTimeId The inactivationTimeId to set.
	 */
	public void setInactivationTimeId(String inactivationTimeId) {
		this.inactivationTimeId = inactivationTimeId;
	}
	/**
	 * @return Returns the jIMS2LogonId.
	 */
	public String getJIMS2LogonId() {
		return JIMS2LogonId;
	}
	/**
	 * @param logonId The jIMS2LogonId to set.
	 */
	public void setJIMS2LogonId(String logonId) {
		JIMS2LogonId = logonId;
	}
	/**
	 * @return Returns the jIMS2Password.
	 */
	public String getJIMS2Password() {
		return JIMS2Password;
	}
	/**
	 * @param password The jIMS2Password to set.
	 */
	public void setJIMS2Password(String password) {
		JIMS2Password = password;
	}
	/**
	 * @return Returns the jIMSLogonId.
	 */
	public String getJIMSLogonId() {
		return JIMSLogonId;
	}
	/**
	 * @param logonId The jIMSLogonId to set.
	 */
	public void setJIMSLogonId(String logonId) {
		JIMSLogonId = logonId;
	}
	/**
	 * @return Returns the lastLoginDate.
	 */
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	/**
	 * @param lastLoginDate The lastLoginDate to set.
	 */
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	/**
	 * @return Returns the logonId.
	 */
	public String getLogonId() {
		return logonId;
	}
	/**
	 * @param logonId The logonId to set.
	 */
	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}
	/**
	 * @return Returns the operatorId.
	 */
	public String getOperatorId() {
		return operatorId;
	}
	/**
	 * @param operatorId The operatorId to set.
	 */
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	/**
	 * @return Returns the orgCode.
	 */
	public String getOrgCode() {
		return orgCode;
	}
	/**
	 * @param orgCode The orgCode to set.
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return Returns the publicInd.
	 */
	public String getPublicInd() {
		return publicInd;
	}
	/**
	 * @param publicInd The publicInd to set.
	 */
	public void setPublicInd(String publicInd) {
		this.publicInd = publicInd;
	}
	/**
	 * @return Returns the requestorFirstName.
	 */
	public String getRequestorFirstName() {
		return requestorFirstName;
	}
	/**
	 * @param requestorFirstName The requestorFirstName to set.
	 */
	public void setRequestorFirstName(String requestorFirstName) {
		this.requestorFirstName = requestorFirstName;
	}
	/**
	 * @return Returns the requestorLastName.
	 */
	public String getRequestorLastName() {
		return requestorLastName;
	}
	/**
	 * @param requestorLastName The requestorLastName to set.
	 */
	public void setRequestorLastName(String requestorLastName) {
		this.requestorLastName = requestorLastName;
	}
	/**
	 * @return Returns the serverRegion.
	 */
	public String getServerRegion() {
		return serverRegion;
	}
	/**
	 * @param serverRegion The serverRegion to set.
	 */
	public void setServerRegion(String serverRegion) {
		this.serverRegion = serverRegion;
	}
	/**
	 * @return Returns the ssn.
	 */
	public String getSsn() {
		return ssn;
	}
	/**
	 * @param ssn The ssn to set.
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	/**
	 * @return Returns the trainingClassSchedules.
	 */
	public java.util.Collection getTrainingClassSchedules() {
		return trainingClassSchedules;
	}
	/**
	 * @param trainingClassSchedules The trainingClassSchedules to set.
	 */
	public void setTrainingClassSchedules(java.util.Collection trainingClassSchedules) {
		this.trainingClassSchedules = trainingClassSchedules;
	}
	/**
	 * @return Returns the trainingInd.
	 */
	public String getTrainingInd() {
		return trainingInd;
	}
	/**
	 * @param trainingInd The trainingInd to set.
	 */
	public void setTrainingInd(String trainingInd) {
		this.trainingInd = trainingInd;
	}
	/**
	 * @return Returns the userHistories.
	 */
	public java.util.Collection getUserHistories() {
		return userHistories;
	}
	/**
	 * @param userHistories The userHistories to set.
	 */
	public void setUserHistories(java.util.Collection userHistories) {
		this.userHistories = userHistories;
	}
	/**
	 * @return Returns the userStatus.
	 */
	public String getUserStatus() {
		return userStatus;
	}
	/**
	 * @param userStatus The userStatus to set.
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	/**
	 * @return Returns the userType.
	 */
	public CodeTO getUserType() {
		return userType;
	}
	/**
	 * @param userType The userType to set.
	 */
	public void setUserType(CodeTO userType) {
		this.userType = userType;
	}
	/**
	 * @return Returns the userTypeId.
	 */
	public String getUserTypeId() {
		return userTypeId;
	}
	/**
	 * @param userTypeId The userTypeId to set.
	 */
	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}
    /**
     * @return Returns the firstName.
     */
    public String getFirstName()
    {
        return firstName;
    }
    /**
     * @param firstName The firstName to set.
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    /**
     * @return Returns the lastName.
     */
    public String getLastName()
    {
        return lastName;
    }
    /**
     * @param lastName The lastName to set.
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    /**
     * @return Returns the middleName.
     */
    public String getMiddleName()
    {
        return middleName;
    }
    /**
     * @param middleName The middleName to set.
     */
    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }
	/**
	 * @return Returns the formattedName.
	 */
	public String getFormattedName() {
		if (formattedName == null) {
			Name name = new Name(this.firstName, this.middleName, this.lastName);
			formattedName = name.getFormattedName();
		}
		return formattedName;
	}
	/**
	 * @param formattedName The formattedName to set.
	 */
	public void setFormattedName(String formattedName) {
		this.formattedName = formattedName;
	}
}
