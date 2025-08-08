package pd.contact.user;

import java.util.Date;

import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.domintf.contact.user.IUserProfile;
import messaging.domintf.contact.user.IUserProfileUpdate;
import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.security.AllUserAccessInfoBean;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import naming.PDConstants;
import naming.PDContactConstants;
import naming.PDSecurityConstants;
import pd.codetable.Code;
import pd.codetable.PDCodeTableHelper;
import pd.contact.Contact;
import pd.contact.IContact;
import pd.contact.agency.Department;
import pd.transferobjects.helper.UserProfileHelper;

/**
 * @author dgibler
 */
public class UserProfile extends Contact implements IContact, IUserInfo, IAddressable
{
    // serves as the unique identifier for UserProfile
    // serves as the unique identifier for UserProfile
    private String logonId;
    private String departmentId;
    private Date deptTransferDate;
    private Date deptTransferRequestDate;
    private String deptTransferTime;
    private String deptTransferRequestTime;
    private String trainingInd;
    private Date creationDate;
    private String creationTime;
    private String requestorFirstName;
    private String requestorLastName;
    private String inactivatedById;
    private String activatorId;
    private String departmentName;
    private String comments;
    private String userStatus;
    private String ssn;
    private String agencyId;
    private String serverRegion;
    /**
     * Properties for userHistories
     * 
     * @referencedType pd.contact.user.UserHistory
     * @detailerDoNotGenerate true
     */
    private java.util.Collection userHistories = null;
    private Date activationDate;
    private String activationTime;
    private Date lastLoginDate;
    private String agencyName;
    private String accountType;

    

    /**
     * Properties for userType
     * 
     * @referencedType pd.codetable.Code
     * @contextKey USER_TYPE
     * @detailerDoNotGenerate true
     */
    private Code userType = null;

    private Date dateOfBirth;
    /**
     * Properties for department
     * 
     * @referencedType pd.contact.agency.Department
     * @detailerDoNotGenerate true
     */
    private Department department = null;
    private String userTypeId;
    private String genericUserTypeId;
    private String inactivationTimeId;
    private String inactivationRequestTimeId;
    private Date inactivationDate;
    private Date inactivationRequestDate;
    /**
     * Properties for inactivationTime
     * 
     * @referencedType pd.codetable.Code
     * @contextKey WORK_DAY
     * @detailerDoNotGenerate true
     */
    private Code inactivationTime = null;

    /**
     * Properties for genericUserType
     * 
     * @referencedType pd.codetable.Code
     * @contextKey JIMS2_ACCOUNT_TYPE
     * @detailerDoNotGenerate true
     */
    private Code genericUserType = null;
    private String operatorId;
    private String orgCode;
    private String publicInd;
    private String customLogonCode;

    private String JIMSLogonId;
    private String JIMS2LogonId;
    private String JIMS2Password;
    private String password;
    private boolean maxRecordExceeded; //87191
    private AllUserAccessInfoBean userAccess;
    private MetaDataResponseEvent metaDataRespEvent;

    /**
     * @return the userHistories
     */
    public java.util.Collection getUserHistories()
    {
	return userHistories;
    }

    /**
     * @param userHistories
     *            the userHistories to set
     */
    /*public void setUserHistories(java.util.Collection userHistories)
    {
        this.userHistories = userHistories;
    }*/

    /**
     * @return the maxRecordExceeded
     */
    public boolean isMaxRecordExceeded()
    {
	return maxRecordExceeded;
    }

    /**
     * @param maxRecordExceeded
     *            the maxRecordExceeded to set
     */
    public void setMaxRecordExceeded(boolean maxRecordExceeded)
    {
	this.maxRecordExceeded = maxRecordExceeded;
    }

    /**
     * @roseuid 4107BF850379
     */
    public UserProfile()
    {
    }

    /**
     * Clears all pd.contact.UserHistory from class relationship collection.
     * 
     * @roseuid 4107DFB90052
     */
    // no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
    /*public void clearUserHistories()
    {
    	initUserHistories();
    	userHistories.clear();
    }*/
    /**
     * @return
     */
    public Date getActivationDate()
    {
	//	fetch();
	return activationDate;
    }

    public String getActivationTime()
    {
	//	fetch();
	return activationTime;
    }

    /**
     * Access method for the agencyId property.
     * 
     * @return java.lang.String
     * @roseuid 4107DFB80354
     */
    public String getAgencyId()
    {
	//fetch();
	return agencyId;
    }

    /**
     * Access method for the agencyName property.
     * 
     * @return java.lang.String
     * @roseuid 4107DFB80354
     */
    public String getAgencyName()
    {
	//fetch();
	return agencyName;
    }

    /**
     * Access method for the deptTransferDate property.
     * 
     * @return the current value of the deptTransferDate property
     */
    public Date getDeptTransferDate()
    {
	//fetch();
	return deptTransferDate;
    }

    /**
     * Access method for the deptTransferRequestDate property.
     * 
     * @return the current value of the deptTransferRequestDate property
     */
    public Date getDeptTransferRequestDate()
    {
	//fetch();
	return deptTransferRequestDate;
    }

    /**
     * Access method for the deptTransferTime property.
     * 
     * @return the current value of the deptTransferTime property
     */
    public String getDeptTransferTime()
    {
	//fetch();
	return deptTransferTime;
    }

    /**
     * Access method for the deptTransferRequestTime property.
     * 
     * @return the current value of the deptTransferRequestTime property
     */
    public String getDeptTransferRequestTime()
    {
	//	fetch();
	return deptTransferRequestTime;
    }

    /**
     * Access method for the comments property.
     * 
     * @return the current value of the comments property
     */
    public String getComments()
    {
	//fetch();
	return comments;
    }

    /**
     * Access method for the creationDate property.
     * 
     * @return
     */
    public Date getCreationDate()
    {
	//fetch();
	return creationDate;
    }

    /**
     * Access method for the creationTime property.
     * 
     * @return
     */
    public String getCreationTime()
    {
	//fetch();
	return creationTime;
    }

    /**
     * Access method for the dateOfBirth property.
     * 
     * @return the current value of the dateOfBirth property
     */
    public Date getDateOfBirth()
    {
	//fetch();
	return dateOfBirth;
    }

    /**
     * @return
     */
    public String getInactivatedById()
    {
	//fetch();
	return inactivatedById;
    }

    /**
     * @return
     */
    public String getActivatorId()
    {
	//fetch();
	return activatorId;
    }

    /**
     * Gets referenced type pd.contact.agency.Department
     * 
     * @return pd.contact.agency.Department
     * @roseuid 4107DFB900AC
     */
    public Department getDepartment()
    {
	//fetch();
	initDepartment();
	return department;
    }

    /**
     * Get the reference value to class :: pd.contact.agency.Department
     * 
     * @return java.lang.String
     * @roseuid 4107DFB80354
     */
    public String getDepartmentId()
    {
	//fetch();
	return departmentId;
    }

    /**
     * @return
     */
    public String getDepartmentName()
    {
	//	fetch();
	return departmentName;
    }

    /**
     * @return
     */
    public Date getInactivationDate()
    {
	//fetch();
	return inactivationDate;
    }

    /**
     * @return
     */
    public Date getInactivationRequestDate()
    {
	//fetch();
	return inactivationRequestDate;
    }

    /**
     * Gets referenced type pd.codetable.Code
     * 
     * @return Code inactivationTime
     */
    public Code getInactivationTime()
    {
	//fetch();
	initInactivationTime();
	return inactivationTime;
    }

    public String getInactivationTimeId()
    {
	//fetch();
	//initInactivationTime();
	return inactivationTimeId;
    }

    public String getInactivationRequestTimeId()
    {
	//fetch();
	//initInactivationTime();
	return inactivationRequestTimeId;
    }

    /**
     * Gets referenced type pd.codetable.Code
     * 
     * @return Code genericUserType
     */
    public Code getGenericUserType()
    {
	//fetch();
	initGenericUserType();
	return genericUserType;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String userTypeId
     */
    public String getGenericUserTypeId()
    {
	//fetch();
	return genericUserTypeId;
    }

    /**
     * @return
     */
    public Date getLastLoginDate()
    {
	//fetch();
	return lastLoginDate;
    }

    /**
     * Access method for the creatorId property. This is not a persistent
     * property. It is only used when creating a new user to specify the logon
     * ID rather than having one generated.
     * 
     * @return
     */
    public String getCustomLogonCode()
    {
	//fetch();
	return customLogonCode;
    }

    /**
     * Access method for the logonId property.
     * 
     * @return the current value of the logonId property
     */
    public String getLogonId()
    {
	//fetch();
	return logonId;
    }

    /**
     * @return
     */
    public String getRequestorFirstName()
    {
	//fetch();
	return requestorFirstName;
    }

    /**
     * @return
     */
    public String getRequestorLastName()
    {
	//fetch();
	return requestorLastName;
    }

    /**
     * Access method for the ssn property.
     * 
     * @return the current value of the ssn property
     */
    public String getSsn()
    {
	//fetch();
	return ssn;
    }

    /**
     * Access method for the trainingInd property.
     * 
     * @return the current value of the trainingInd property
     */
    public String getTrainingInd()
    {
	//fetch();
	return trainingInd;
    }

    /**
     * returns a collection of pd.contact.UserHistory
     * 
     * @return java.util.Collection
     * @roseuid 4107DFB90020
     */
    // no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
    /*public java.util.Collection getUserHistories()
    {
    	fetch();
    	initUserHistories();
    	return userHistories;
    }*/
    /**
     * @return
     */
    public String getUserStatus()
    {
	//fetch();
	return userStatus;
    }

    /**
     * Gets referenced type pd.codetable.Code
     * 
     * @return Code userType
     */
    public Code getUserType()
    {
	//fetch();
	initUserType();
	return userType;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String userTypeId
     */
    public String getUserTypeId()
    {
	//fetch();
	return userTypeId;
    }

    /**
     * @return String
     */
    public String getOperatorId()
    {
	//fetch();
	return operatorId;
    }

    /**
     * @return String
     */
    public String getOrgCode()
    {
	//fetch();
	return orgCode;
    }

    /**
     * @return boolean
     */
    public String getPublicInd()
    {
	//fetch();
	return publicInd;
    }

    /**
     * @return boolean
     */
    //	public boolean isPublic()
    //	{
    //		if (getPublicInd().equalsIgnoreCase("y"))
    //			return true;
    //		else
    //			return false;
    //	}

    /**
     * userType = null; Initialize class relationship to class
     * pd.contact.agency.Department
     * 
     * @roseuid 4107DFB900A2
     */
    private void initDepartment()
    {
	if (department == null)
	{
	    try
	    {
		department = Department.find(departmentId);
		//87191
		/*(pd.contact.agency.Department) new mojo
			.km
			.persistence
			.Reference(departmentId, pd.contact.agency.Department.class)
			.getObject();*/
	    }
	    catch (Throwable t)
	    {
		department = null;
	    }
	}
    }

    /**
     * Initialize class relationship implementation for pd.contact.UserHistory
     * 
     * @roseuid 4107DFB90016
     */
    // no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
    /*private void initUserHistories()
    {
    	if (userHistories == null)
    	{
    		try
    		{
    			userHistories =
    				new mojo.km.persistence.ArrayList(pd.contact.user.UserHistory.class, "logonId", "" + getOID());
    		}
    		catch (Throwable t)
    		{
    			userHistories = new java.util.ArrayList();
    		}
    	}
    }*/
    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initUserType()
    {
	if (userType == null)
	{
	    try
	    {
		userType = (Code) new mojo.km.persistence.Reference(userTypeId, Code.class, "USER_TYPE").getObject();
	    }
	    catch (Throwable t)
	    {
	    }
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initGenericUserType()
    {
	if (genericUserType == null)
	{
	    try
	    {
		genericUserType = (Code) new mojo.km.persistence.Reference(genericUserTypeId, Code.class, "JIMS2_ACCOUNT_TYPE").getObject();
	    }
	    catch (Throwable t)
	    {
	    }
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initInactivationTime()
    {
	if (inactivationTime == null)
	{
	    try
	    {
		inactivationTime = (Code) new mojo.km.persistence.Reference(inactivationTimeId, Code.class, "WORK_DAY").getObject();
	    }
	    catch (Throwable t)
	    {
	    }
	}
    }

    /**
     * insert a pd.contact.UserHistory into class relationship collection.
     * 
     * @param anObject
     * @roseuid 4107DFB9002A
     */
    // no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
    /*public void insertUserHistories(pd.contact.user.UserHistory anObject)
    {
    	initUserHistories();
    	userHistories.add(anObject);
    }*/

    /**
     * Removes a pd.contact.UserHistory from class relationship collection.
     * 
     * @param anObject
     * @roseuid 4107DFB9003E
     */
    // no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
    /*public void removeUserHistories(pd.contact.user.UserHistory anObject)
    {
    	initUserHistories();
    	userHistories.remove(anObject);
    }*/
    /**
     * @param activationDate
     */
    public void setActivationDate(Date activationDate)
    {
	if (this.activationDate == null || !this.activationDate.equals(activationDate))
	{
	    //markModified();
	}
	this.activationDate = activationDate;
    }

    /**
     * @param anActivationTime
     */
    public void setActivationTime(String anActivationTime)
    {
	if (this.activationTime == null || !this.activationTime.equals(anActivationTime))
	{
	    //markModified();
	}
	this.activationTime = anActivationTime;
    }

    /**
     * Sets the value of the agencyId property.
     * 
     * @param agencyId
     * @roseuid 4107DFB8034A
     */
    public void setAgencyId(String aagencyId)
    {
	//		if (this.agencyId == null || !this.agencyId.equals(aagencyId)) {
	//			markModified();
	//		}
	this.agencyId = aagencyId;
    }

    /**
     * Sets the value of the agencyName property.
     * 
     * @param agencyName
     * @roseuid 4107DFB8034A
     */
    public void setAgencyName(String aAgencyName)
    {
	//		if (this.agencyName == null || !this.agencyName.equals(aAgencyName)) {
	//			markModified();
	//		}
	this.agencyName = aAgencyName;
    }

    /**
     * Sets the value of the deptTransferDate property.
     * 
     * @param aDeptTransferDate
     *            the new value of the deptTransferDate property
     */
    public void setDeptTransferDate(Date aDeptTransferDate)
    {
	if (this.deptTransferDate == null || !this.deptTransferDate.equals(aDeptTransferDate))
	{
	    //markModified();
	}
	deptTransferDate = aDeptTransferDate;
    }

    /**
     * Sets the value of the deptTransferRequestDate property.
     * 
     * @param aDeptTransferDate
     *            the new value of the deptTransferRequestDate property
     */
    public void setDeptTransferRequestDate(Date aDeptTransferReqDate)
    {
	if (this.deptTransferRequestDate == null || !this.deptTransferRequestDate.equals(aDeptTransferReqDate))
	{
	    //markModified();
	}
	deptTransferRequestDate = aDeptTransferReqDate;
    }

    /**
     * Sets the value of the deptTransferTime property.
     * 
     * @param aDeptTransferTime
     *            the new value of the deptTransferTime property
     */
    public void setDeptTransferTime(String aDeptTransferTime)
    {
	if (this.deptTransferTime == null || !this.deptTransferTime.equals(aDeptTransferTime))
	{
	    //markModified();
	}
	deptTransferTime = aDeptTransferTime;
    }

    /**
     * Sets the value of the deptTransferRequestTime property.
     * 
     * @param aDeptTransferTime
     *            the new value of the deptTransferRequestTime property
     */
    public void setDeptTransferRequestTime(String aDeptTransferReqTime)
    {
	if (this.deptTransferRequestTime == null || !this.deptTransferRequestTime.equals(aDeptTransferReqTime))
	{
	    //markModified();
	}
	deptTransferRequestTime = aDeptTransferReqTime;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param aComments
     *            the new value of the comments property
     */
    public void setComments(String aComments)
    {
	if (this.comments == null || !this.comments.equals(aComments))
	{

	    //markModified();
	}
	comments = aComments;
    }

    /**
     * @param creationDate
     */
    public void setCreationDate(Date creationDate)
    {
	if (this.creationDate == null || !this.creationDate.equals(creationDate))
	{

	    //markModified();
	}
	this.creationDate = creationDate;
    }

    /**
     * @param aCreationTime
     */
    public void setCreationTime(String aCreationTime)
    {
	if (this.creationTime == null || !this.creationTime.equals(aCreationTime))
	{
	    //markModified();
	}
	this.creationTime = aCreationTime;
    }

    /**
     * Sets the value of the dateOfBirth property.
     * 
     * @param aDateOfBirth
     *            the new value of the dateOfBirth property
     */
    public void setDateOfBirth(Date aDateOfBirth)
    {
	if (this.dateOfBirth == null || !this.dateOfBirth.equals(aDateOfBirth))
	{
	    //markModified();
	}
	this.dateOfBirth = aDateOfBirth;
    }

    /**
     * @param deactivatorId
     */
    public void setInactivatedById(String inactivatedById)
    {
	if (this.inactivatedById == null || !this.inactivatedById.equals(inactivatedById))
	{
	    //markModified();
	}
	this.inactivatedById = inactivatedById;
    }

    /**
     * @param deactivatorId
     */
    public void setActivatorId(String activatorId)
    {
	if (this.activatorId == null || !this.activatorId.equals(activatorId))
	{
	    //markModified();
	}
	this.activatorId = activatorId;
    }

    /**
     * set the type reference for class member department
     * 
     * @param department
     * @roseuid 4107DFB900B6
     */
    public void setDepartment(Department aDepartment)
    {
	if(aDepartment!=null){
	    setDepartmentId("" + aDepartment.getDepartmentId());
	}
	this.department = aDepartment;

    }

    /**
     * Set the reference value to class :: pd.contact.agency.Department
     * 
     * @param departmentId
     * @roseuid 4107DFB8034A
     */
    public void setDepartmentId(String aDepartmentId)
    {
	this.departmentId = aDepartmentId;
    }

    /**
     * @param departmentName
     */
    public void setDepartmentName(String aDepartmentName)
    {
	//		if (this.departmentName == null || !this.departmentName.equals(aDepartmentName)) {
	//			markModified();
	//		}
	this.departmentName = aDepartmentName;
    }

    /**
     * @param inactivationDate
     */
    public void setInactivationDate(Date inactivationDate)
    {
	if (this.inactivationDate == null || !this.inactivationDate.equals(inactivationDate))
	{
	    //markModified();
	}
	this.inactivationDate = inactivationDate;
    }

    /**
     * @param inactivationRequestDate
     */
    public void setInactivationRequestDate(Date inactivationReqDate)
    {
	if (this.inactivationRequestDate == null || !this.inactivationRequestDate.equals(inactivationReqDate))
	{
	    //markModified();
	}
	this.inactivationRequestDate = inactivationReqDate;
    }

    /**
     * @param lastLoginDate
     */
    public void setLastLoginDate(Date lastLoginDate)
    {
	if (this.lastLoginDate == null || !this.lastLoginDate.equals(lastLoginDate))
	{
	    //markModified();
	}
	this.lastLoginDate = lastLoginDate;
    }

    /**
     * Sets the value of the logonId property.
     * 
     * @param aLogonId
     *            the new value of the logonId property
     */
    public void setLogonId(String aLogonId)
    {
	//		if (this.logonId == null || !this.logonId.equals(aLogonId)) {
	//			markModified();
	//		}
	logonId = aLogonId;
    }

    /**
     * @param requestorFirstName
     */
    public void setRequestorFirstName(String requestorFirstName)
    {
	if (this.requestorFirstName == null || !this.requestorFirstName.equals(requestorFirstName))
	{
	    //markModified();
	}
	this.requestorFirstName = requestorFirstName;
    }

    /**
     * @param requestorLastName
     */
    public void setRequestorLastName(String requestorLastName)
    {
	/*	if (this.requestorLastName == null || !this.requestorLastName.equals(requestorLastName))
		{
			markModified();
		}
	*/this.requestorLastName = requestorLastName;
    }

    /**
     * Sets the value of the ssn property.
     * 
     * @param aSsn
     *            the new value of the ssn property
     */
    public void setSsn(String aSsn)
    {
	/*	if (this.ssn == null || !this.ssn.equals(aSsn))
		{
			markModified();
		}
	*/ssn = aSsn;
    }

    /**
     * Sets the value of the trainingInd property.
     * 
     * @param aTrainingInd
     *            the new value of the trainingInd property
     */
    public void setTrainingInd(String aTrainingInd)
    {
	//		if (this.trainingInd == null || !this.trainingInd.equals(aTrainingInd))
	//		{
	//			markModified();
	//		}
	trainingInd = aTrainingInd;
    }

    /**
     * @param userStatus
     */
    public void setUserStatus(String userStatus)
    {
	/*	if (this.userStatus == null || !this.userStatus.equals(userStatus))
		{
			markModified();
		}
	*/this.userStatus = userStatus;
    }

    /**
     * Sets the value of the customLogonCode property. This property is not
     * persisted.
     * 
     * @param aCustomLogonCode
     *            the value to be used as the logonId
     */
    public void setCustomLogonCode(String aCustomLogonCode)
    {
	customLogonCode = aCustomLogonCode;
    }

    /**
     * @param publicInd
     */
    public void setPublicInd(String ind)
    {
	/*	if (this.publicInd == null || !this.publicInd.equalsIgnoreCase(ind))
		{
			markModified();
		}
	*/this.publicInd = ind;
    }

    /**
     * @param operatorId
     */
    public void setOperatorId(String operatorId)
    {
	//		if (this.operatorId == null || !this.operatorId.equals(operatorId)) {
	//			markModified();
	//		}
	this.operatorId = operatorId;
    }

    /**
     * @param orgCode
     */
    public void setOrgCode(String orgCode)
    {
	//		if (this.orgCode == null || !this.orgCode.equals(orgCode)) {
	//			markModified();
	//		}
	this.orgCode = orgCode;
    }

    /**
     * set the type reference for class member userType
     * 
     * @param userType
     */
    public void setUserType(Code aUserType)
    {
	/*	if (this.userType == null || !this.userType.equals(aUserType))
		{
			markModified();
		}
	*/setUserTypeId("" + aUserType.getOID());
	this.userType = (Code) new mojo.km.persistence.Reference(aUserType).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     * 
     * @param userTypeId
     */
    public void setUserTypeId(String aUserTypeId)
    {
	/*	if (this.userTypeId == null || !this.userTypeId.equals(aUserTypeId))
		{
			markModified();
		}
	*/userType = null;
	this.userTypeId = aUserTypeId;
    }

    /**
     * set the type reference for class member userType
     * 
     * @param userType
     */
    public void setGenericUserType(Code aGenericUserType)
    {
	/*	if (this.genericUserType == null || !this.genericUserType.equals(aGenericUserType))
		{
			markModified();
		}
	*/setGenericUserTypeId("" + aGenericUserType.getOID());
	this.genericUserType = (Code) new mojo.km.persistence.Reference(aGenericUserType).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     * 
     * @param genericUserTypeId
     */
    public void setGenericUserTypeId(String aGenericUserTypeId)
    {
	/*	if (this.genericUserTypeId == null || !this.genericUserTypeId.equals(aGenericUserTypeId))
		{
			markModified();
		}
	*/genericUserType = null;
	this.genericUserTypeId = aGenericUserTypeId;
    }

    /**
     * set the type reference for class member inactivationTime
     * 
     * @param anInactivationTime
     */
    public void setInactivationTime(Code anInactivationTime)
    {
	/*	if (this.inactivationTime == null || !this.inactivationTime.equals(anInactivationTime))
		{
			markModified();
		}
	*/setInactivationTimeId("" + anInactivationTime.getOID());
	this.inactivationTime = (Code) new mojo.km.persistence.Reference(anInactivationTime).getObject();
    }

    /**
     * @param inactivationRequestTimeId
     */
    public void setInactivationRequestTimeId(String anInactivationReqTimeId)
    {
	/*	if (this.inactivationRequestTimeId == null || !this.inactivationRequestTimeId.equals(anInactivationReqTimeId))
		{
			markModified();
		}
	*/
	this.inactivationRequestTimeId = anInactivationReqTimeId;
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     * 
     * @param inactivationTimeId
     */
    public void setInactivationTimeId(String anInactivationTimeId)
    {
	/*		if (this.inactivationTimeId == null || !this.inactivationTimeId.equals(anInactivationTimeId))
			{
				markModified();
			}
	*/inactivationTime = null;
	this.inactivationTimeId = anInactivationTimeId;
    }

    /* (non-Javadoc)
     * @see mojo.km.security.IUser#getPassword()
     */
    public String getPassword()
    {
	return password;
    }

    /* (non-Javadoc)
     * @see mojo.km.security.IUser#setPassword(java.lang.String)
     */
    public void setPassword(String password)
    {
	this.password = password;
    }

    /* (non-Javadoc)
     * @see mojo.km.security.IUser#getUserOID()
     */
    public String getUserOID()
    {
	return JIMS2LogonId;
    }

    /* (non-Javadoc)
     * @see mojo.km.security.IUserInfo#getJIMS2LogonId()
     */
    public String getJIMS2LogonId()
    {
	return JIMS2LogonId;
    }

    /* (non-Javadoc)
     * @see mojo.km.security.IUserInfo#setJIMS2LogonId(java.lang.String)
     */
    public void setJIMS2LogonId(String JIMS2LogonId)
    {
	this.JIMS2LogonId = JIMS2LogonId;
    }

    /* (non-Javadoc)
     * @see mojo.km.security.IUserInfo#getJIMSLogonId()
     */
    public String getJIMSLogonId()
    {
	return JIMSLogonId;
    }

    /* (non-Javadoc)
     * @see mojo.km.security.IUserInfo#setJIMSLogonId(java.lang.String)
     */
    public void setJIMSLogonId(String JIMSLogonId)
    {
	this.JIMSLogonId = JIMSLogonId;
    }

    /* (non-Javadoc)
     * @see mojo.km.security.IUserInfo#getJIMS2Password()
     */
    public String getJIMS2Password()
    {
	return JIMS2Password;
    }

    /* (non-Javadoc)
     * @see mojo.km.security.IUserInfo#setJIMS2Password(java.lang.String)
     */
    public void setJIMS2Password(String jims2Password)
    {
	this.JIMS2Password = jims2Password;

    }

    /**
     * @return userProfile
     * @param logonId
     * @roseuid 4107B06C01EC
     */
    static public UserProfile find(String logonId)
    {
	UserProfile userProfile = null;
	//IHome home = new Home();
	if(logonId!=null && !logonId.isEmpty()){
	    userProfile = UserProfileHelper.getUserProfileFromJUCode(logonId);//87191(UserProfile) home.find(logonId, UserProfile.class);
	}
	return userProfile;
    }

    /**
     * @return Iterator UserProfile
     * @roseuid 4107B06C01EB
     */
    /*  static public Iterator findAll(IEvent event)
      {
       IHome home = new Home();
       return home.findAll(event, UserProfile.class);
    
      }*/

    public String setDefaultCreateValues(String customCode)
    {
	if (customCode != null && !customCode.equals(""))
	{

	    // use custom code for logon id
	    setCustomLogonCode(customCode);
	}
	this.userTypeId = PDSecurityConstants.USER_TYPE_BASIC;
	this.userType = PDCodeTableHelper.getCode(PDSecurityConstants.USER_TYPE_BASIC);
	Date currentDate = DateUtil.getCurrentDate();
	String currentTime = DateUtil.getHHMMSSWithColonFromDate(currentDate);
	//this.activationDate = currentDate;
	//this.activationTime = currentTime;
	this.creationDate = currentDate;
	this.creationTime = currentTime;
	this.trainingInd = PDConstants.NO;

	return null;
    }

    /**
     * If the parent agency of the department associated to the created user
     * profile is JIMS, the Juvenile Probation Department, or an agency that has
     * a JMC Rep, the user profile should be activated upon creation.
     */
    private boolean shouldBeActivated()
    {
	Department dept = getDepartment();
	if (dept != null)
	{
	    if ((dept.getAgencyId().equals(PDContactConstants.JIMS)) || (dept.getAgencyId().equals(PDContactConstants.JUVENILE)))
	    {
		return true;
	    }

	    // Get this from Agency
	    /*if (dept.getAgency().hasJMCBoardRep())
	    {
	    	return true;
	    }*///87191
	}
	return false;
    }

    public IName getName()
    {
	IName nameBean = new NameBean();
	nameBean.setFirstName(this.getFirstName());
	nameBean.setMiddleName(this.getMiddleName());
	nameBean.setLastName(this.getLastName());
	return nameBean;
    }

    public void fillUserProfile(IUserProfile userProfile, boolean thinResponseRequested)
    {
	// get the basic UserProfile attributes
	userProfile.setLogonId(getLogonId());
	userProfile.setAgencyId(getAgencyId());
	userProfile.setEmail(getEmail());
	userProfile.setFirstName(getFirstName());
	//userProfile.setGenericUserType(getGenericUserType().getCode());

	// REMOVE THIS CODE WHEN THE DATA IS FIXED		
	Code genericUserType = getGenericUserType();
	if (genericUserType != null && !genericUserType.equals(""))
	{
	    userProfile.setGenericUserType(genericUserType.getCode());
	}
	else
	{
	    userProfile.setGenericUserType(PDSecurityConstants.NON_GENERIC_USER);
	}
	userProfile.setTitle(getTitle());
	userProfile.setLastName(getLastName());
	userProfile.setMiddleName(getMiddleName());
	userProfile.setPhoneNum(getPhoneNum());
	userProfile.setPhoneExt(getPhoneExt());
	userProfile.setSsn(getSsn());
	userProfile.setTrainingInd(getTrainingInd());
	userProfile.setUserStatus(getUserStatus());
	userProfile.setUserTypeId(getUserTypeId());
	//Set the Agency for the User
	if ((getDepartmentId() != null) && (!(getDepartmentId().equals(""))))
	{
	    Department dept = getDepartment();
	    if (dept != null)
	    {
		userProfile.setDepartmentId(dept.getDepartmentId());
		userProfile.setDepartmentName(dept.getDepartmentName());
		userProfile.setAgencyName(dept.getAgencyName());
		userProfile.setCreateOfficerProfileInd(dept.getCreateOfficerProfileInd());
	    }
	}
	userProfile.setDateOfBirth(getDateOfBirth());

	if (thinResponseRequested)
	{
	    return;
	}
	// get the rest of the UserProfile attributes
	userProfile.setTransferDate(getDeptTransferDate());
	userProfile.setTransferTime(getDeptTransferTime());
	userProfile.setDeptTransferRequestDate(getDeptTransferRequestDate());
	userProfile.setDeptTransferRequestTime(getDeptTransferRequestTime());
	userProfile.setComments(getComments());
	userProfile.setDepartmentId(getDepartmentId());
	userProfile.setActivationDate(getActivationDate());
	userProfile.setActivationTime(getActivationTime());
	/*if ((getCreateUserID() != null) && (!(getCreateUserID().equals(""))))
	{
		String creatorId = getCreateUserID();
		UserProfile user = UserProfile.find(creatorId);
		if (user != null)
		{
			userProfile.setCreatedByFirstName(user.getFirstName());
			userProfile.setCreatedByLastName(user.getLastName());
		}
	}*/

	userProfile.setCreateDate(getCreationDate());
	userProfile.setCreateTime(getCreationTime());
	if ((getInactivatedById() != null) && (!(getInactivatedById().equals(""))))
	{
	    String deactivatorId = getInactivatedById();
	    UserProfile user = UserProfile.find(deactivatorId);
	    if (user != null)
	    {
		userProfile.setInactivatedByFirstName(user.getFirstName());
		userProfile.setInactivatedByLastName(user.getLastName());
	    }
	}
	if ((getActivatorId() != null) && (!(getActivatorId().equals(""))))
	{
	    String activatorId = getActivatorId();
	    UserProfile user = UserProfile.find(activatorId);
	    if (user != null)
	    {
		userProfile.setActivatedByFirstName(user.getFirstName());
		userProfile.setActivatedByLastName(user.getLastName());
	    }
	}
	userProfile.setInactivationDate(getInactivationDate());
	/*Code inactivationTime = getInactivationTime();
	if (inactivationTime != null)
	{
		userProfile.setInactivationTime(inactivationTime.getCode());
	}*/
	userProfile.setInactivationTimeId(getInactivationTimeId());
	userProfile.setInactivationRequestDate(getInactivationRequestDate());
	userProfile.setInactivationRequestTimeId(getInactivationRequestTimeId());
	userProfile.setLastLoginDate(getLastLoginDate());
	userProfile.setRequestorFirstName(getRequestorFirstName());
	userProfile.setRequestorLastName(getRequestorLastName());
	//	userProfile.setPublicInd(getPublicInd());
	userProfile.setOrgCode(getOrgCode());
	userProfile.setOperatorId(getOperatorId());
	//userProfile.setTopic(PDContactConstants.USER_EVENT_TOPIC);
    }

    public void updateUserProfile(IUserProfileUpdate userProfileUpdate)
    {
	//Set the UserProfile attributes
	this.setLastName(userProfileUpdate.getLastName());
	this.setFirstName(userProfileUpdate.getFirstName());
	this.setMiddleName(userProfileUpdate.getMiddleName());
	this.setDateOfBirth(userProfileUpdate.getDateOfBirth());
	this.setSsn(userProfileUpdate.getSsn());
	this.setGenericUserTypeId(userProfileUpdate.getGenericUserTypeId());
	//		this.setPublicInd(userProfileUpdate.getPublicInd());
	this.setDepartmentId(userProfileUpdate.getDepartmentId());
	this.setOrgCode(userProfileUpdate.getOrgCode());
	this.setPhoneNum(userProfileUpdate.getPhoneNum());
	this.setPhoneExt(userProfileUpdate.getPhoneExt());
	this.setEmail(userProfileUpdate.getEmail());
	this.setInactivationDate(userProfileUpdate.getInactivationDate());
	this.setInactivationTimeId(userProfileUpdate.getInactivationTime());
	this.setDeptTransferRequestDate(userProfileUpdate.getDeptTransferRequestDate());
	this.setDeptTransferRequestTime(userProfileUpdate.getDeptTransferRequestTime());
	this.setRequestorFirstName(userProfileUpdate.getRequestorFirstName());
	this.setRequestorLastName(userProfileUpdate.getRequestorLastName());
	this.setComments(userProfileUpdate.getComments());
	this.setUserStatus(userProfileUpdate.getUserStatus());
	if (userProfileUpdate.isCreate())
	{
	    if (shouldBeActivated())
	    {
		this.userStatus = PDSecurityConstants.ACTIVE;
	    }
	    else
	    {
		this.userStatus = PDSecurityConstants.PENDING_ADD;
	    }
	}
    }

    /**
     * @return
     */
    public UserResponseEvent getValueObject()
    {
	UserResponseEvent event = new UserResponseEvent();
	event.setLogonId(this.getLogonId());
	return event;
    }

    /**
     * @return Returns the serverRegion.
     */
    public String getServerRegion()
    {
	return serverRegion;
    }

    /**
     * @param serverRegion
     *            The serverRegion to set.
     */
    public void setServerRegion(String serverRegion)
    {
	this.serverRegion = serverRegion;
    }

    /**
     * @param deptEvent
     * @param class1
     * @return
     */
    /*public static MetaDataResponseEvent findMeta(IEvent userEvent ) {
    	IHome home = new Home();
    	MetaDataResponseEvent iter = home.findMeta(userEvent, UserProfile.class);
    	return iter;
    }*/

    public void setUserOID(String smUserId)
    {
	smUserId = getUserOID();

    }

    //87191
    /**
     * @return the userAccess
     */
    public AllUserAccessInfoBean getUserAccess()
    {
	return userAccess;
    }

    /**
     * @param userAccess
     *            the userAccess to set
     */
    public void setUserAccess(AllUserAccessInfoBean userAccess)
    {
	this.userAccess = userAccess;
    }

    /**
     * @return the metaDataRespEvent
     */
    public MetaDataResponseEvent getMetaDataRespEvent()
    {
	return metaDataRespEvent;
    }

    /**
     * @param metaDataRespEvent
     *            the metaDataRespEvent to set
     */
    public void setMetaDataRespEvent(MetaDataResponseEvent metaDataRespEvent)
    {
	this.metaDataRespEvent = metaDataRespEvent;
    }
    public String getAccountType()
    {
        return accountType;
    }

    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }
}