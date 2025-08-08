/*
 * Created on May 20, 2005
 *
 */
package pd.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import messaging.agency.GetDepartmentsForASAEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import messaging.security.reply.UserGroupResponseEvent;
import messaging.security.reply.UserResponseforUserAdministrationEvent;
import mojo.km.caching.generic.CacheManager;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.persistence.Reference;
import mojo.km.security.AgencyEntityBean;
import mojo.km.security.DepartmentEntityBean;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.security.RolesEntityBean;
import mojo.km.security.SecurityManagerBaseResponse;
import mojo.km.security.Token;
import mojo.km.security.UserEntityBean;
import mojo.km.security.UserGroupEntityBean;
import mojo.km.security.helper.SecurityManagerWebServiceHelper;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDContactConstants;
import naming.PDSecurityConstants;
import pd.codetable.Code;
import pd.contact.agency.Agency;
import pd.contact.user.UserProfile;

/**
 * @author dnikolis To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PDSecurityHelper
{

    public static UserResponseforUserAdministrationEvent getUserResponseEvent(UserProfile user)
    {
	UserResponseforUserAdministrationEvent event = new UserResponseforUserAdministrationEvent();

	event.setLogonId(user.getLogonId());
	event.setUserFirstName(user.getFirstName());
	event.setUserLastName(user.getLastName());
	event.setUserMiddleName(user.getMiddleName());
	boolean isSARole = false;
	if ((user.getUserTypeId() != null) && (!(user.getUserTypeId().equals(""))))
	{
	    Code userCode = user.getUserType();
	    if (userCode != null)
	    {
		String userType = userCode.getCode();
		event.setUserTypeId(userType);
		event.setUserType(userCode.getDescription());
		if ((userType.equalsIgnoreCase(PDSecurityConstants.USER_TYPE_SA) || userType.equalsIgnoreCase(PDSecurityConstants.USER_TYPE_ASA)))
		{
		    isSARole = true;
		}
	    }
	}
	event.setDepartmentId(user.getDepartmentId());
	event.setDepartmentName(user.getDepartmentName());
	event.setAgencyId(user.getAgencyId());
	event.setAgencyName(user.getAgencyName());
	//87191
	/*	if (isSARole == false)
		{
		    // if user type is not SA or ASA check if SA role is defined for this agency
		    String agencyId = user.getAgencyId();
		    if (agencyId != null && agencyId.length() > 0)
		    {
			isSARole = doesAgencyHaveSARole(agencyId);
		    }
		    else
		    {
			isSARole = false;
		    }
		}*/
	event.setAgencyHasSA(isSARole);

	event.setTopic(PDContactConstants.USER_EVENT_TOPIC);
	return event;
    }

    /*
     *@param boolean
     *87191
     */
    /*    private static boolean doesAgencyHaveSARole(String agencyId)
        {
    	// get SA roles  
    	//IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

    	GetRolesByConstraintsEvent aEvent = (GetRolesByConstraintsEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.GETROLESBYCONSTRAINTS);

    	aEvent.setAgencyId(agencyId);
    	aEvent.setRoleType(PDSecurityConstants.ROLE_TYPE_SA);

    	//		dispatch.postEvent(aEvent);
    	//		CompositeResponse response = (CompositeResponse) dispatch.getReply();

    	Iterator roles = Role.findAll(aEvent);
    	if (roles.hasNext())
    	{
    	    return true;
    	}

    	//		Collection roles = MessageUtil.compositeToCollection(response, RoleResponseEvent.class);
    	//		if (roles != null && roles.size() > 0)
    	//		{
    	//			// if SA role found
    	//			return true;
    	//		}

    	return false;
        }*/

    public static UserGroupResponseEvent getUserGroupResponseEvent(UserEntityBean user)
    {
	UserGroupResponseEvent ugEvent = new UserGroupResponseEvent();
	UserGroupEntityBean userGroup = null;
	if (user != null && user.getUseraccesses() != null && user.getUseraccesses().getUsergroups() != null)
	{
	    Collection<RolesEntityBean> roles = user.getUseraccesses().getRoles();
	    if (roles != null && !roles.isEmpty())
	    {
		ugEvent.setRoleExist(true);
	    }
	    List<UserGroupEntityBean> userGroups = user.getUseraccesses().getUsergroups();
	    if (userGroups.iterator().hasNext())
	    {
		userGroup = userGroups.iterator().next();
	    }
	    ugEvent.setDescription(userGroup.getGroupdescription());
	    ugEvent.setName(userGroup.getGroupname());
	    if (user.getDept() != null && user.getDept().getAgency() != null)
	    {
		AgencyEntityBean agency = user.getDept().getAgency();
		ugEvent.setAgencyId(agency.getAgencyid());
		ugEvent.setAgencyName(agency.getAgencyname());
	    }
	    ugEvent.setStatusId(userGroup.isIsActive() ? "A" : "I");
	    if ((ugEvent.getStatusId() != null) && (!(ugEvent.getStatusId().equals(""))))
	    {
		String statusId = ugEvent.getStatusId();
		Code statusCode = (Code) new Reference(statusId, Code.class, PDCodeTableConstants.USER_GROUP_STATUS).getObject();
		if (statusCode != null)
		{
		    ugEvent.setStatus(statusCode.getDescription());
		}
	    }
	    ugEvent.setUserGroupId(userGroup.getGroupname()); // group Id after security manager Migration is the group name.
	    ugEvent.setUserGroupType(userGroup.getGrouptype());
	}
	/*	Iterator constraints = userGroup.getConstraints().iterator();
		while (constraints.hasNext())
		{
		    Constraint constraint = (Constraint) constraints.next();
		    if (constraint != null)
		    {
			Agency agency = (Agency) constraint.getConstrainerObject();
			if (agency != null)
			{*/

	/*}
	}
	}*/
	/*	ugEvent.setCreatorId(userGroup.getCreateUserID());
		if ((userGroup.getCreateUserID() != null) && (!(userGroup.getCreateUserID().equals(""))))
		{
		    UserProfile user = UserProfile.find(userGroup.getCreateUserID());
		    if (user != null)
		    {
			ugEvent.setCreatorLastName(user.getLastName());
			ugEvent.setCreatorFirstName(user.getFirstName());
			ugEvent.setCreatorMiddleName(user.getMiddleName());
		    }
		}*/
	/*ugEvent.setCategory(userGroup.getCategory());*/

	ugEvent.setTopic(PDSecurityConstants.USER_GROUP_EVENT_TOPIC);
	return ugEvent;
    }

    /**
     * @param features
     */
    /* public static void sendFeaturesResponseEvent(Iterator features)
     {
    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
    HashMap map = new HashMap();
    while (features.hasNext())
    {
        Feature feature = (Feature) features.next();
        if (feature != null)
        {
    	FeaturesResponseEvent featureResponseEvent = getFeaturesResponseEvent(feature);

    	if (featureResponseEvent.getParentId() != null)
    	{
    	    Feature pFeature = Feature.find(feature.getParentId());
    	    // send the parent feature if it has not been sent before
    	    if (!map.containsKey(pFeature.getOID().toString()))
    	    {
    		FeaturesResponseEvent parentResponseEvent = getFeaturesResponseEvent(pFeature);
    		dispatch.postEvent(parentResponseEvent);
    		map.put(pFeature.getOID().toString(), parentResponseEvent);
    	    }

    	    // send the child feature if it has not been sent before
    	    if (!map.containsKey(featureResponseEvent.getFeatureId()))
    	    {
    		dispatch.postEvent(featureResponseEvent);
    		map.put(featureResponseEvent.getFeatureId(), featureResponseEvent);
    	    }
    	}
    	else
    	{
    	    Collection childFeatureCollection = feature.getChildFeatures();
    	    if (childFeatureCollection != null)
    	    {
    		SortedMap childList = new TreeMap();
    		Iterator childFeatureIt = childFeatureCollection.iterator();
    		while (childFeatureIt.hasNext())
    		{
    		    Feature childFeature = (Feature) childFeatureIt.next();
    		    FeaturesResponseEvent childResponseEvent = getFeaturesResponseEvent(childFeature);
    		    // send the child feature if it has not been sent before
    		    if (!map.containsKey(featureResponseEvent.getFeatureId()))
    		    {
    			map.put(childResponseEvent.getFeatureId(), childResponseEvent);
    			dispatch.postEvent(childResponseEvent);
    		    }
    		}
    		// send the parent feature if it has not been sent before
    		if (!map.containsKey(featureResponseEvent.getFeatureId()))
    		{
    		    dispatch.postEvent(featureResponseEvent);
    		    map.put(featureResponseEvent.getFeatureId(), featureResponseEvent);
    		}
    	    }
    	}
        }
    }
     }*///87191

    /**
     * @param feature
     *            Feature
     * @returns FeaturesResponseEvent.
     */
    /* public static FeaturesResponseEvent getFeaturesResponseEvent(Feature feature)
     {
    FeaturesResponseEvent responseEvent = new FeaturesResponseEvent();
    responseEvent.setTopic(SecurityConstants.ROLE_FEATURES_EVENT_TOPIC);
    responseEvent.setDescription(feature.getDescription());
    responseEvent.setFeatureName(feature.getName());
    responseEvent.setFeatureType(feature.getFeatureType());
    responseEvent.setParentId(feature.getParentId());
    responseEvent.setFeatureCategory(feature.getFeatureCategory());
    responseEvent.setFeatureId(feature.getOID().toString());
    return responseEvent;
     }*///87191

    /**
     * @param role
     *            Role
     * @returns RoleResponseEvent.
     */
    /*  public static RoleResponseEvent getRoleResponseEvent(Role role)
      {
    RoleResponseEvent responseEvent = new RoleResponseEvent();
    responseEvent.setTopic(PDSecurityConstants.ROLE_EVENT_TOPIC);
    responseEvent.setRoleId(role.getOID().toString());
    responseEvent.setRoleName(role.getName());
    responseEvent.setRoleDescription(role.getDescription());
    responseEvent.setRoleType(role.getRoleType());
    responseEvent.setCreatorId((role.getCreatorUserId() == null) ? "" : role.getCreatorUserId());
    return responseEvent;
      }*///87191

    /**
     * @param agency
     *            Agency
     * @returns AgencyResponseEvent.
     */
    public static AgencyResponseEvent getAgencyResponseEvent(Agency agency)
    {
	AgencyResponseEvent agencyResponseEvent = new AgencyResponseEvent();
	if (agency != null)
	{
	    agencyResponseEvent.setAgencyId(agency.getAgencyId());
	    agencyResponseEvent.setAgencyName(agency.getAgencyName());
	    agencyResponseEvent.setAgencyType(agency.getAgencyTypeId());
	}
	return agencyResponseEvent;
    }

    /**
     * @return boolean isUserASA
     */
    public static boolean isUserMA()
    {
	ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	if (manager != null)
	{
	    IUserInfo user = manager.getIUserInfo();
	    return user.getUserTypeId().equalsIgnoreCase("MA");
	}
	else
	{
	    return false;
	}
    }

    /**
     * @return boolean isUserASA
     */
    public static boolean isUserLA()
    {
	ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	if (manager != null)
	{
	    IUserInfo user = manager.getIUserInfo();
	    return user.getUserTypeId().equalsIgnoreCase("LA");
	}
	else
	{
	    return false;
	}
    }

    /**
     * @return boolean isUserASA
     */
    public static boolean isUserASA()
    {
	ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	if (manager != null)
	{
	    IUserInfo user = manager.getIUserInfo();
	    return user.getUserTypeId().equalsIgnoreCase("ASA");
	}
	else
	{
	    return false;
	}
    }

    /**
     * @return boolean isUserSA
     */
    public static boolean isUserSA()
    {
	ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	if (manager != null)
	{
	    IUserInfo user = manager.getIUserInfo();
	    return user.getUserTypeId().equalsIgnoreCase("SA");
	}
	else
	{
	    return false;
	}
    }

    /**
     * @return String userId
     */
    public static String getUserId()
    {
	ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	if (manager != null)
	{
	    IUserInfo user = manager.getIUserInfo();
	    return user.getUserOID().toString();
	}
	else
	{
	    return null;
	}
    }

    /**
     * @return User user
     */
    public static IUserInfo getUser()
    {
	ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	if (manager != null)
	{
	    return manager.getIUserInfo();
	}
	else
	{
	    return null;
	}
    }

    /**
     * @return String logonId
     */
    public static String getLogonId()
    {
	ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	String logonId = null;
	if (manager != null)
	{
	    IUserInfo user = manager.getIUserInfo();
	    logonId = user.getJIMSLogonId();
	}
	return logonId;
    }

    /**
     * @param agencyId
     * @return collectoin departments
     * @roseuid 428B82BD0158
     */
    public static Collection getASADepartments(String agencyId)
    {
	GetDepartmentsForASAEvent deptRequestEvent = (GetDepartmentsForASAEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENTSFORASA);
	deptRequestEvent.setAgencyId(agencyId);
	deptRequestEvent.setLogonId(PDSecurityHelper.getLogonId());
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(deptRequestEvent);

	CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
	ReturnException returnException = (ReturnException) MessageUtil.filterComposite(replyEvent, ReturnException.class);
	if (returnException != null)
	{
	    return null;
	}
	Collection departments = MessageUtil.compositeToCollection(replyEvent, DepartmentResponseEvent.class);
	departments = MessageUtil.processEmptyCollection(departments);
	return departments;
    }

    /**
     * @return String userAgencyId
     */
    public static String getUserAgencyId()
    {
	ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	String retVal = null;
	if (manager != null)
	{
	    IUserInfo user = manager.getIUserInfo();
	    retVal = user.getAgencyId();
	}
	return retVal;
    }

    /**
     * getUserAgencyName
     * 
     * @return
     */
    public static String getUserAgencyName()
    {
	ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	String agencyName = null;
	if (manager != null)
	{
	    IUserInfo user = manager.getIUserInfo();
	    agencyName = user.getAgencyName();
	}
	return agencyName;
    }

    /**
     * getUserDepartmentName
     * 
     * @return
     */
    public static String getUserDepartmentName()
    {
	ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	String departmentName = null;
	if (manager != null)
	{
	    IUserInfo user = manager.getIUserInfo();
	    departmentName = user.getDepartmentName();
	}
	return departmentName;
    }

    /**
     * getUserDepartmentId
     * 
     * @return
     */
    public static String getUserDepartmentId()
    {
	ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	String departmentId = null;
	if (manager != null)
	{
	    IUserInfo user = manager.getIUserInfo();
	    departmentId = user.getDepartmentId();
	}
	return departmentId;
    }

    /**
     * getUserOrgName
     * 
     * @return
     */
    public static String getUserOrgCode()
    {
	ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	String orgCode = null;
	if (manager != null)
	{
	    IUserInfo user = manager.getIUserInfo();
	    orgCode = user.getOrgCode();
	}
	return orgCode;
    }

    /**
     * @params roleName
     */
    public static void sendErrorEvent(String roleName)
    {

	DuplicateRecordErrorResponseEvent errorEvent = new DuplicateRecordErrorResponseEvent();
	StringBuffer buff = new StringBuffer("The role ");
	buff.append(roleName);
	buff.append(" already exists in the datastore. Please choose a different name");
	errorEvent.setMessage(buff.toString());
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	dispatch.postEvent(errorEvent);
    }

    /* public static boolean validateAdminDept(String department)
     {
    String logon = null;
    ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
    if (manager != null)
    {
        IUserInfo user = manager.getIUserInfo();
        logon = user.getJIMSLogonId();
    }
    else
    {
        return false;
    }
    Constraint constraints = Constraint.findByConstrainsIdAndConstrainerId(logon, "USER", department, "DEPARTMENT");

    if (constraints != null)
    {
        return true;
    }
    else
    {

        return false;
    }
     }*/

    /**
     * 87191
     * 
     * @return TOKEN
     */
    public static Token getToken()
    {
	//Token token = (Token) CacheManager.get(Token.class, "Bearer");
	
	//if (token == null)
	//{
	  Token  token = SecurityManagerWebServiceHelper.getAuthTokenURLConnection(); // token authentication
	  if(token!=null){ //added null check
	    System.out.println("======Token from service:PDSecurityHelper======="+token.getAccess_token());
	  }
	 //   CacheManager.add(token, "Bearer");
	//}else{
	//    System.out.println("=======Token from cache:PDSecurityHelper========="+token.getAccess_token());
//	} //Commented as it may be a problem in PROD as there are more than one server handled in the PROD
	return token;
    }

    /**
     * Web-Service call to get department by id //87191
     * 
     * @param departmentId
     */
    public static DepartmentEntityBean getDepartmentById(String departmentId)
    {
	DepartmentEntityBean departmentEBean = null;
	Token token = getToken();
	if (token != null)
	{
	    departmentEBean = (DepartmentEntityBean) CacheManager.get(DepartmentEntityBean.class, departmentId);
	    if (departmentEBean == null)
	    {
		SecurityManagerBaseResponse<List<DepartmentEntityBean>> departmentResponse = SecurityManagerWebServiceHelper.getDepartmentById(token, departmentId);
		if (departmentResponse != null)
		{
		    if (departmentResponse.isIsSuccess() && departmentResponse.isRecFound())
		    {
			List<DepartmentEntityBean> departments = departmentResponse.getData();
			if (departments != null)
			{
			    Iterator<DepartmentEntityBean> deptItr = departments.iterator();
			    if (deptItr.hasNext())
			    {
				departmentEBean = deptItr.next();
				if (departmentEBean != null)
				{
				    CacheManager.add(departmentEBean, departmentId);
				    return departmentEBean;
				}
			    }
			}
		    }
		}
	    }
	}
	return departmentEBean;
    }

    /**
     * getDepartmentByCodeOrDesc
     * @param departmentId
     * @param departmentName
     * @return List<DepartmentEntityBean>
     */
    public static <T> Object getDepartmentByCodeOrDesc(String departmentId,String departmentName)
    {
	DepartmentEntityBean departmentEBean = null;
	Token token = getToken();
	if (token != null)
	{
	    if(departmentId!=null && !departmentId.isEmpty()){
	      departmentEBean = (DepartmentEntityBean) CacheManager.get(DepartmentEntityBean.class, departmentId);
	      if(departmentEBean!=null)
		  return departmentEBean; // bug 88256
	    }
	    if (departmentEBean == null)
	    {
		SecurityManagerBaseResponse<List<DepartmentEntityBean>> departmentResponse = SecurityManagerWebServiceHelper.getDepartmentsByCodeOrDescription(token, departmentId, departmentName);
		if (departmentResponse != null)
		{
		    if (departmentResponse.isIsSuccess() && departmentResponse.isRecFound())
		    {
			List<DepartmentEntityBean> departments = departmentResponse.getData();
			if (departments != null)
			{
			    Iterator<DepartmentEntityBean> deptItr = departments.iterator();
			    if (deptItr.hasNext())
			    {
				if(departmentId!=null && departments.size()==1){
				    departmentEBean = deptItr.next();
				    CacheManager.add(departmentEBean,departmentId);
				    return departmentEBean;
				}else{
				    return departments;
				}
			    }
			}
		    }
		}
	    }
	}
	return null;
    }

    
    
    
    /**
     * //87191 getSecurityUserProfile
     * 
     * @param <T>
     * @param uvCode
     * @return SecurityUserProfile
     */
    public static UserEntityBean getSecurityUserProfileByJUCode(String uvCode)
    {
	UserEntityBean userEBean = null;
	Token token = getToken();
	if (token != null)
	{
	    userEBean = (UserEntityBean) CacheManager.get(UserEntityBean.class, uvCode);
	    if (userEBean == null)
	    {
		SecurityManagerBaseResponse<UserEntityBean> userInfo = SecurityManagerWebServiceHelper.getSecurityUserByJUCode(token, uvCode);
		if (userInfo != null)
		{
		    if (userInfo.isIsSuccess() && userInfo.isRecFound())
		    {
			if (userInfo.isMaxRecCountExceeded())
			{
			    UserEntityBean secProfile = new UserEntityBean();
			    secProfile.setMaxRecCount(secProfile.getMaxRecCount());
			    return secProfile;
			}

			UserEntityBean secProfile = userInfo.getData();

			if (secProfile != null)
			{
			    CacheManager.add(secProfile, uvCode);
			    return secProfile;
			}
		    }
		}
	    }
	}
	return userEBean;
    }
    
    /**
     * getUsers
     * @param jucode
     * @param fName
     * @param lName
     * @param deptName
     * @param departmentId
     * @return getUsers
     */
    public static <T> Object getUsers(String jucode, String fName, String lName, String deptName, String departmentId)
    {
	UserEntityBean userEBean = null;
	Token token = getToken();
	if (token != null)
	{
	    if (userEBean == null)
	    {
		SecurityManagerBaseResponse<List<UserEntityBean>> userInfo = SecurityManagerWebServiceHelper.getUsers(token, jucode, fName, lName, deptName, departmentId);
		if (userInfo != null)
		{
		    if (userInfo.isIsSuccess() && userInfo.isRecFound())
		    {
			if (userInfo.isMaxRecCountExceeded())
			{
			    UserEntityBean secProfile = new UserEntityBean();
			    secProfile.setMaxRecCount(secProfile.getMaxRecCount());
			    return secProfile;
			}
			else
			{
			    Iterator<UserEntityBean> userItr = userInfo.getData().iterator();
			    if (userItr.hasNext())
			    {
				List<UserEntityBean> userInfoList = userInfo.getData();
				if (userInfoList != null)
				{
				    return userInfoList;
				}
			    }
			}
		    }
		}
	    }
	}
	return null;
    }

    /**
     * //87191 getSecurityUserProfile
     * 
     * @param <T>
     * @param uvCode
     * @return SecurityUserProfile
     */
    public static List<UserEntityBean> getUserGroupByIdOrName(String groupId, String groupName)
    {
	UserEntityBean userEBean = null;
	List<UserEntityBean> userInfoList = null;
	Token token = getToken();
	if (token != null)
	{
	    userEBean =  (UserEntityBean) CacheManager.get(UserEntityBean.class, groupId);
	    if (userEBean == null)
	    {
		SecurityManagerBaseResponse<List<UserEntityBean>> userInfo = SecurityManagerWebServiceHelper.getUserGroupByIdOrName(token, groupId, groupName);
		if (userInfo != null)
		{
		    if (userInfo.isIsSuccess() && userInfo.isRecFound())
		    {
			Iterator<UserEntityBean> userItr = userInfo.getData().iterator();
			if (userItr.hasNext())
			{
			    if (userInfo.getData() != null)
			    {
				userInfoList = userInfo.getData();
				if (groupId != null)
				{
				    if (userInfoList.size()==1)
				    {
					userEBean = userInfoList.get(0);
					CacheManager.add(userEBean, groupId);
				    }
				}
				return userInfoList;
			    }
			}
		    }
		}
	    }
	    //if the usergroup has only one user
	    if(userEBean!=null){
		userInfoList = new ArrayList<UserEntityBean>();
		userInfoList.add(userEBean);
		return userInfoList;
	    }
	}
	return null;
    }

    /**
     * getAgencyByAgencyId
     * 
     * @param agencyId
     * @return AgencyEntityBean
     */
    public static AgencyEntityBean getAgencyByAgencyId(String agencyId)
    {
	AgencyEntityBean agencyEBean = null;
	Token token = getToken();
	if (token != null)
	{
	    agencyEBean = (AgencyEntityBean) CacheManager.get(AgencyEntityBean.class, agencyId);
	    if (agencyEBean == null)
	    {
		SecurityManagerBaseResponse<AgencyEntityBean> agencyBaseResponse = SecurityManagerWebServiceHelper.getAgencyByAgencyId(token, agencyId);
		if (agencyBaseResponse != null)
		{
		    if (agencyBaseResponse.isIsSuccess() && agencyBaseResponse.isRecFound())
		    {
			if (agencyBaseResponse.getData() != null)
			{
			    agencyEBean = agencyBaseResponse.getData();

			    CacheManager.add(agencyEBean, agencyId);
			    return agencyEBean;
			}
		    }
		}
	    }
	}
	return agencyEBean;
    }
    
    /**
     * getAgencies
     * @param agencyId
     * @param agencyName
     * @return
     */
    public static <T> Object getAgencies(String agencyId, String agencyName)
    {
	AgencyEntityBean agencyEBean = null;
	List<AgencyEntityBean> agencieEBeans = null;
	Token token = getToken();
	if (token != null)
	{
	    agencyEBean = (AgencyEntityBean) CacheManager.get(AgencyEntityBean.class, agencyId);
	    if (agencyEBean == null)
	    {
		SecurityManagerBaseResponse<List<AgencyEntityBean>> agencyBaseResponse = SecurityManagerWebServiceHelper.getAgencyByCodeOrDescription(token, agencyId, agencyName);
		if (agencyBaseResponse != null)
		{
		    if (agencyBaseResponse.isIsSuccess() && agencyBaseResponse.isRecFound())
		    {
			if (agencyBaseResponse.isMaxRecCountExceeded())
			{
			    agencyEBean = new AgencyEntityBean();
			    agencyEBean.setMaxRecCount(agencyBaseResponse.getRecCount());
			    return agencyEBean;
			}
			else
			{
			    agencieEBeans = agencyBaseResponse.getData();
			    if (agencieEBeans != null)
			    {
				Iterator<AgencyEntityBean> agencyEBeanItr = agencieEBeans.iterator();
				if (agencyEBeanItr.hasNext())
				{
				    if (agencieEBeans.size()==1)
				    {
					agencyEBean = agencieEBeans.get(0);
					CacheManager.add(agencyEBean, agencyId);
				    }
				    return agencieEBeans;
				}
			    }
			}
		    }
		}
	    }
	    if(agencyEBean!=null){
		agencieEBeans = new ArrayList<AgencyEntityBean>();
		agencieEBeans.add(agencyEBean);
		return agencieEBeans;
	    }
	}
	return null;
    }
}
