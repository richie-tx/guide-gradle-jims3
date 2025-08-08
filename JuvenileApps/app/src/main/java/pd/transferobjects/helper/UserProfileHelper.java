/*
 * Created on Aug 29, 2007
 *
 */
package pd.transferobjects.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.security.AgencyEntityBean;
import mojo.km.security.UserEntityBean;
import pd.contact.user.UserProfile;
import pd.security.PDSecurityHelper;

/**
 * @author cc_mdsouza
 */
public class UserProfileHelper
{
    //87191
    /*    public static UserProfileTO initializeTransferObjectFromPersistentObject(PersistentObject persistentObject) throws Exception
        {

    	UserProfileTO userProfileTO = new UserProfileTO();

    	try
    	{
    	    UserProfile userProfile = (UserProfile) persistentObject;
    	    userProfileTO.setLogonId(userProfile.getLogonId());
    	    userProfileTO.setDepartmentId(userProfile.getDepartmentId());
    	    userProfileTO.setDeptTransferDate(userProfile.getDeptTransferDate());
    	    userProfileTO.setDeptTransferRequestDate(userProfile.getDeptTransferRequestDate());
    	    userProfileTO.setDeptTransferTime(userProfile.getDeptTransferTime());
    	    userProfileTO.setDeptTransferRequestTime(userProfile.getDeptTransferRequestTime());
    	    userProfileTO.setTrainingInd(userProfile.getTrainingInd());
    	    userProfileTO.setCreationDate(userProfile.getCreationDate());
    	    userProfileTO.setCreationTime(userProfile.getCreationTime());
    	    userProfileTO.setRequestorFirstName(userProfile.getRequestorFirstName());
    	    userProfileTO.setRequestorLastName(userProfile.getRequestorLastName());
    	    userProfileTO.setInactivatedById(userProfile.getInactivatedById());
    	    userProfileTO.setActivatorId(userProfile.getActivatorId());
    	    userProfileTO.setDepartmentName(userProfile.getDepartmentName());
    	    userProfileTO.setComments(userProfile.getComments());
    	    userProfileTO.setUserStatus(userProfile.getUserStatus());
    	    userProfileTO.ssn = userProfile.getSsn();
    	    userProfileTO.agencyId = userProfile.getAgencyId();
    	    userProfileTO.serverRegion = userProfile.getServerRegion();
    	    userProfileTO.activationDate = userProfile.getActivationDate();
    	    userProfileTO.activationTime = userProfile.getActivationTime();
    	    userProfileTO.lastLoginDate = userProfile.getLastLoginDate();
    	    userProfileTO.agencyName = userProfile.getAgencyName();
    	    userProfileTO.dateOfBirth = userProfile.getDateOfBirth();
    	    userProfileTO.userTypeId = userProfile.getUserTypeId();
    	    userProfileTO.genericUserTypeId = userProfile.getGenericUserTypeId();
    	    userProfileTO.inactivationTimeId = userProfile.getInactivationTimeId();
    	    userProfileTO.inactivationRequestTimeId = userProfile.getInactivationRequestTimeId();
    	    userProfileTO.activationDate = userProfile.getActivationDate();
    	    userProfileTO.inactivationDate = userProfile.getInactivationDate();
    	    userProfileTO.operatorId = userProfile.getOperatorId();
    	    userProfileTO.orgCode = userProfile.getOrgCode();
    	    userProfileTO.publicInd = userProfile.getPublicInd();
    	    userProfileTO.customLogonCode = userProfile.getCustomLogonCode();
    	    userProfileTO.JIMSLogonId = userProfile.getJIMS2LogonId();
    	    userProfileTO.JIMS2LogonId = userProfile.getJIMS2LogonId();
    	    userProfileTO.JIMS2Password = userProfile.getJIMS2Password();
    	    userProfileTO.password = userProfile.getPassword();
    	    userProfileTO.firstName = userProfile.getFirstName();
    	    userProfileTO.lastName = userProfile.getLastName();
    	    userProfileTO.middleName = userProfile.getMiddleName();

    	    //	  	if (userProfile.getUserType() != null  && this.userType == null )
    	    //	  	{
    	    //	  		this.userType = new CodeTO() ; 
    	    //	  		this.userType.initializeTransferObjectFromPersistentObject( userProfile.getUserType() ) ; 
    	    //	  	}
    	    //	  	if (userProfile.getDepartment() != null  && this.department== null )
    	    //	  	{
    	    //	  		this.department = new DepartmentTO() ; 
    	    //	  		this.department.initializeTransferObjectFromPersistentObject( userProfile.getDepartment() ) ; 
    	    //	  	}
    	    //	  	if (userProfile.getInactivationTime() != null  && this.inactivationTime== null )
    	    //	  	{
    	    //	  		this.inactivationTime = new CodeTO() ; 
    	    //	  		this.inactivationTime.initializeTransferObjectFromPersistentObject( userProfile.getInactivationTime() ) ; 
    	    //	  	}
    	    //	  	if (userProfile.getGenericUserType() != null  && this.genericUserType == null )
    	    //	  	{
    	    //	  		this.genericUserType = new CodeTO() ; 
    	    //	  		this.genericUserType.initializeTransferObjectFromPersistentObject( userProfile.getGenericUserType() ) ; 
    	    //	  	}
    	    //
    	    //	  	if (userProfile.getUserHistories().size() > 0 && this.userHistories.size() != userProfile.getUserHistories().size())
    	    //	  	{
    	    //	  		// TODO : initialize user histories 
    	    //	  		this.userHistories = new ArrayList() ; 
    	    //	  	}
    	    //	  	
    	    //	  	if (userProfile.getTrainingClassSchedules().size() > 0 && this.trainingClassSchedules.size() != userProfile.getTrainingClassSchedules().size())
    	    //	  	{
    	    //	  		// TODO : initialize user histories 
    	    //	  		this.trainingClassSchedules = new ArrayList() ; 
    	    //	  	}

    	}
    	catch (Exception e)
    	{
    	    e.printStackTrace();
    	    throw new Exception(e);
    	}
    	return userProfileTO;
        }

        public void putTransferObjectToPersistentObject(TransferObjectInterface transferObjectInterface) throws Exception
        {
    	try
    	{
    	    throw new UnsupportedOperationException();
    	}
    	catch (Exception e)
    	{
    	    e.printStackTrace();
    	    throw new Exception(e);
    	}

        }*/
    //87191 commented above 

    /**
     * getUserProfileFromJUCode
     * 
     * @param uvCode
     * @return UserProfile
     */
    public static UserProfile getUserProfileFromJUCode(String uvCode)
    {
	UserProfile userProfile = null;
	if (uvCode != null && !uvCode.isEmpty())
	{
	    UserEntityBean userEBean = PDSecurityHelper.getSecurityUserProfileByJUCode(uvCode);
	    if (userEBean != null)
	    {
		userProfile = populateUserProfileFromUserEntityBean(userEBean);
	    }
	}
	return userProfile;
    }

    /**
     * getUserProfiles
     * 
     * @param jucode
     * @param fName
     * @param lName
     * @param deptName
     * @param departmentId
     * @return <T> Object
     */
    public static <T> Object getUserProfiles(String jucode, String fName, String lName, String deptName, String departmentId)
    {
	List<UserProfile> userProfiles = null;
	Object obj = PDSecurityHelper.getUsers(jucode, fName, lName, deptName, departmentId);
	if (obj != null)
	{
	    if (obj instanceof UserEntityBean)
	    {
		UserEntityBean userEBean = (UserEntityBean) obj;
		UserProfile profile = new UserProfile();
		MetaDataResponseEvent metaDataRespEvent = new MetaDataResponseEvent();
		metaDataRespEvent.setCount(userEBean.getMaxRecCount());
		profile.setMetaDataRespEvent(metaDataRespEvent);
		return profile;
	    }
	    if (obj instanceof List<?>)
	    {
		List<UserEntityBean> userEBeans = (List<UserEntityBean>) obj;
		userProfiles = populateUserProfileFromUserEntityBeans(userEBeans);
		return userProfiles;
	    }
	}

	return userProfiles;
    }

    /**
     * populateUserProfileFromUserEntityBeans
     * 
     * @param userEBeans
     * @return List<UserProfile>
     */
    public static List<UserProfile> populateUserProfileFromUserEntityBeans(List<UserEntityBean> userEBeans)
    {
	List<UserProfile> userProfiles = new ArrayList<UserProfile>(); //bug fix:88342
	if (userEBeans != null)
	{
	    Iterator<UserEntityBean> userEBeanItr = userEBeans.iterator();
	    while (userEBeanItr.hasNext())
	    {
		UserEntityBean userEBean = userEBeanItr.next();
		if (userEBean != null)
		{
		    UserProfile userProfile = populateUserProfileFromUserEntityBean(userEBean);
		    userProfiles.add(userProfile);
		}
	    }
	}

	return userProfiles;
    }

    /**
     * populateUserProfileFromSecurityUserProfile
     * 
     * @param userEBean
     * @return UserProfile
     */
    public static UserProfile populateUserProfileFromUserEntityBean(UserEntityBean userEBean)
    {
	UserProfile userProfile = new UserProfile();
	MetaDataResponseEvent respEvent  = new MetaDataResponseEvent();
	respEvent.setCount(userEBean.getMaxRecCount());
	userProfile.setMetaDataRespEvent(respEvent);
	userProfile.setFirstName(userEBean.getFirstname());
	userProfile.setLastName(userEBean.getLastname());
	userProfile.setDateOfBirth(userEBean.getDateofbirth());
	userProfile.setWorkPhoneNum(userEBean.getPhone());
	userProfile.setLogonId(userEBean.getUsername());
	userProfile.setUserOID(String.valueOf(userEBean.getUserid()));
	userProfile.setUserStatus(userEBean.isDisabled() ? "I" : "A");
	userProfile.setJIMSLogonId(userEBean.getUsername());
	userProfile.setEmail(userEBean.getEmail());
	userProfile.setUserAccess(userEBean.getUseraccesses());

	if (userEBean.getDept() != null)
	{
	    AgencyEntityBean agencyEBean = userEBean.getDept().getAgency();
	    if (agencyEBean != null)
	    {
		userProfile.setAgencyId(agencyEBean.getAgencyid());
		userProfile.setAgencyName(agencyEBean.getAgencyname());
		userProfile.setDepartment(DepartmentHelper.populateDepartmentFromDeptEntityBean(userEBean.getDept()));
		userProfile.setDepartmentId(userEBean.getDept().getDepartmentid());
		userProfile.setDepartmentName(userEBean.getDept().getDepartmentdescription());
		userProfile.setOrgCode(userEBean.getDept().getOrgcode());
	    }

	}
	return userProfile;
    }
}
