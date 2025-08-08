package ui.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.agency.GetDepartmentsForASAEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.common.reply.CharacterResponseEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.security.GetSARoleByAgencyIdEvent;
import messaging.security.GetUserGroupUsersEvent;
import messaging.security.GetUserGroupsEvent;
import messaging.security.reply.FeaturesResponseEvent;
import messaging.security.reply.RoleResponseEvent;
import messaging.security.reply.UserGroupResponseEvent;
import messaging.security.reply.UserResponseforUserAdministrationEvent;
import messaging.user.GetUserEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.security.helper.SecurityUtil;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.SecurityAdminControllerServiceNames;
import naming.UIConstants;
import naming.UserControllerServiceNames;

import org.apache.commons.collections.FastArrayList;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.contact.officer.form.OfficerForm;

/**
 * @author mchowdhury
 * 
 * description This class returns the helper methods for security.
 */

public class SecurityUIHelper
{
    public final static String OFFICER_FORM = "officerForm";

    /**
     * @return boolean isUserSA
     */
    public static boolean isUserMA()
    {
        ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        if (manager != null)
        {
            return manager.isUserOfType("MA");
        } else
        {
            return false;
        }

    }

    /**
     * @return boolean isUserSA
     */
    public static boolean isUserASA()
    {
        ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        if (manager != null)
        {
            return manager.isUserOfType("ASA");
        } else
        {
            return false;
        }
    }

    /**
     * @return String userAgencyId
     */
    public static String getUserAgencyId()
    {
        ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        if (manager != null)
        {
            IUserInfo user = manager.getIUserInfo();
            return user.getAgencyId();
        } else
        {
            return null;
        }
    }

    /**
     * @return String userDepartmentId
     */
    public static String getUserDepartmentId()
    {
        ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        if (manager != null)
        {
            IUserInfo user = manager.getIUserInfo();
            return user.getDepartmentId();
        } else
        {
            return null;
        }
    }

    public static String getServiceProviderId()
    {
    	String providerId = null;
    	IUserInfo userInfo = SecurityUtil.getCurrentUser();
		if(userInfo instanceof ServiceProviderContactResponseEvent)
		{
			ServiceProviderContactResponseEvent resp = (ServiceProviderContactResponseEvent) userInfo;
			providerId = resp.getJuvServProviderProfileId();
		}		
		
		return providerId;
    }

    //<KISHORE>JIMS200060329 : Email method from SP to CLM (UI) - KK
    public static String getServiceProviderName()
    {
    	String providerName = null;
    	IUserInfo userInfo = SecurityUtil.getCurrentUser();
		if(userInfo instanceof ServiceProviderContactResponseEvent)
		{
			ServiceProviderContactResponseEvent resp = (ServiceProviderContactResponseEvent) userInfo;
			providerName = resp.getContactName() == null?"":resp.getContactName().getFormattedName();
		}		
		
		return providerName;
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
        } else
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
        } else
        {
            return null;
        }
    }

    public static String getJIMSLogonId()
    {
        ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        if (manager != null)
        {
            IUserInfo user = manager.getIUserInfo();
            return user.getJIMSLogonId().toString();
            
        } else
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
        } else
        {
            return null;
        }
    }

    /**
     * @return String logonId
     * 79250 changes.
     */
    public static String getJIMS2LogonId()
    {
        ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        if (manager != null)
        {
            IUserInfo user = manager.getIUserInfo();
            return user.getJIMS2LogonId();
        } else
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
        if (manager != null)
        {
            IUserInfo user = manager.getIUserInfo();
            return user.getJIMSLogonId();
        } else
        {
            return null;
        }
    }

    /**
     * @return String logonId
     */
    public static boolean isLoggedIn()
    {
        ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        if (manager != null)
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * @param agencies
     * @return Collection
     */
    public static Collection getAgenciesWhichDoesNotHaveSARole(Collection agencies)
    {
        Collection agencyList = new ArrayList();
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        GetSARoleByAgencyIdEvent aEvent = (GetSARoleByAgencyIdEvent) EventFactory
                .getInstance(SecurityAdminControllerServiceNames.GETSAROLEBYAGENCYID);
        Iterator iter = agencies.iterator();
        while (iter.hasNext())
        {
            AgencyResponseEvent event = (AgencyResponseEvent) iter.next();
            aEvent.setAgencyId(event.getAgencyId());
            dispatch.postEvent(aEvent);

            CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
            Map dataMap = MessageUtil.groupByTopic(compositeResponse);
            MessageUtil.processReturnException(dataMap);

            Collection roles = MessageUtil.compositeToCollection(compositeResponse, RoleResponseEvent.class);
            if (roles == null || roles.isEmpty() || roles.size() == 0)
            {
                agencyList.add(event);
            }
        }
        return agencyList;
    }

    /**
     * @return Collection of JMCREPCode
     */
    public static List setJMCREPDropDownCodes()
    {
        List codes = new FastArrayList();
        CodeResponseEvent event = new CodeResponseEvent();
        event.setCode(UIConstants.JMCREP_CODE);
        event.setDescription(UIConstants.JMCREP);
        codes.add(event);
        event = new CodeResponseEvent();
        event.setCode(UIConstants.NON_JMCREP_CODE);
        event.setDescription(UIConstants.NON_JMCREP);
        codes.add(event);
        return codes;
    }

    /*
     * @return Collection dropDownList for Character sorting
     */
    public static List dropDownListForSortingByCharacter()
    {
        List characterList = new FastArrayList();
        CharacterResponseEvent event = new CharacterResponseEvent();
        event.setCharacter("0");
        event.setCharacterValue("-");
        characterList.add(event);

        String[] characterArray = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
                "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
        for (int i = 0; i < characterArray.length; i++)
        {
            event = new CharacterResponseEvent();
            event.setCharacter(characterArray[i]);
            event.setCharacterValue("" + i + 65);
            characterList.add(event);
        }
        return characterList;
    }

    /**
     * @param features
     *            Collection
     * @return Collection of featureTree in tree view
     */
    public static Collection createFeatureTreeData(Collection features)
    {
        SortedMap map = new TreeMap();
        Iterator iter = features.iterator();
        while (iter.hasNext())
        {
            FeaturesResponseEvent currentEvent = (FeaturesResponseEvent) iter.next();
            map.put(currentEvent.getFeatureName() + currentEvent.getFeatureId(), currentEvent);
        }

        HashMap featureTree = new HashMap();
        iter = features.iterator();
        while (iter.hasNext())
        {
            FeaturesResponseEvent event = (FeaturesResponseEvent) iter.next();
            if ((event.getParentId() == null || event.getParentId().equals(""))
                    && !(featureTree.containsKey(event.getFeatureId())))
            {
                featureTree.put(event.getFeatureId(), event);
                map.remove(event.getFeatureName() + event.getFeatureId());
            }
        }

        iter = map.values().iterator();
        while (iter.hasNext())
        {
            FeaturesResponseEvent event = (FeaturesResponseEvent) iter.next();
            if (featureTree.containsKey(event.getParentId()))
            {
                FeaturesResponseEvent parentEvent = (FeaturesResponseEvent) featureTree.get(event.getParentId());
                featureTree.remove(parentEvent.getFeatureId());
                Collection childFeatures = parentEvent.getChildFeatures();

                if (childFeatures == null)
                {
                    childFeatures = new ArrayList();
                }
                if (childFeatures != null && !childFeatures.contains(event))
                {
                    childFeatures.add(event);
                    parentEvent.setChildFeatures(childFeatures);
                    featureTree.put(parentEvent.getFeatureId(), parentEvent);
                }
            } else
            {
                event.setChildFeatures(null);
                featureTree.put(event.getFeatureId(), event);
            }
        }
        // sort the parent features
        map = new TreeMap();
        Iterator iterator = featureTree.values().iterator();
        while (iterator.hasNext())
        {
            FeaturesResponseEvent finalFeature = (FeaturesResponseEvent) iterator.next();
            if (finalFeature.getChildFeatures() == null || finalFeature.getChildFeatures().size() < 1)
            {
                SortedMap childFeatureMap = finalFeature.getChildFeatureMap();
                if (childFeatureMap != null && childFeatureMap.size() > 0)
                {
                    finalFeature.setChildFeatures(childFeatureMap.values());
                }
            }
            map.put(finalFeature.getFeatureName() + finalFeature.getFeatureId(), finalFeature);
        }
        return map.values();
    }

    /**
     * @return String userTypeId
     */
    public static String getUserTypeId()
    {
        ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        if (manager != null)
        {
            IUserInfo user = manager.getIUserInfo();
            return user.getUserTypeId();
        } else
        {
            return null;
        }
    }

    public static UserResponseEvent getUser(String userId)
    {
        UserResponseEvent userResponse = null;
        if (userId != null)
        {
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

            GetUserEvent requestEvent = (GetUserEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSER);
            requestEvent.setLogonId(userId);
            dispatch.postEvent(requestEvent);

            IEvent replyEvent = dispatch.getReply();
	    CompositeResponse compositeResponse = (CompositeResponse) replyEvent;
	    MessageUtil.processReturnException(compositeResponse);

            // should only be 1 user because search based on unique logonId
           // userResponse = (UserResponseEvent) MessageUtil.filterComposite(compositeResponse, UserResponseEvent.class);
	    Collection userResponses = MessageUtil.compositeToCollection(compositeResponse, UserResponseEvent.class);
	    Iterator iter = userResponses.iterator();
	    while (iter.hasNext())
	    {
		userResponse = (UserResponseEvent) iter.next();
	    }
        }

        return userResponse;
    }

    /**
     * @param agencyId
     * @return collectoin departments
     * @roseuid 428B82BD0158
     */
    public static Collection getASADepartments(String agencyId)
    {
        GetDepartmentsForASAEvent deptRequestEvent = (GetDepartmentsForASAEvent) EventFactory
                .getInstance(AgencyControllerServiceNames.GETDEPARTMENTSFORASA);
        deptRequestEvent.setAgencyId(agencyId);
        ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        deptRequestEvent.setLogonId(manager.getIUserInfo().getJIMSLogonId());
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(deptRequestEvent);

        CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
        Map dataMap = MessageUtil.groupByTopic(compositeResponse);
        MessageUtil.processReturnException(dataMap);

        Collection departments = MessageUtil.compositeToCollection(compositeResponse, DepartmentResponseEvent.class);
        departments = MessageUtil.processEmptyCollection(departments);
        return departments;
    }

    /**
     * @param users
     * @return
     */
    public static Collection sortAssignRolesUsers(Collection users)
    {
        if (users != null)
        {
            SortedMap map = new TreeMap();
            Iterator iter = users.iterator();
            while (iter.hasNext())
            {
                SecurityUserResponseEvent user = (SecurityUserResponseEvent) iter.next();
                map.put(user.getLastName() + user.getFirstName() + user.getLogonId(), user);
            }
            return map.values();
        } else
        {
            return null;
        }
    }

    /**
     * @param departments
     * @return
     */
    public static Collection sortDepartments(Collection departments)
    {
        SortedMap map = new TreeMap();
        Iterator iter = departments.iterator();
        while (iter.hasNext())
        {
            DepartmentResponseEvent department = (DepartmentResponseEvent) iter.next();
            map.put(department.getDepartmentName() + department.getDepartmentId(), department);
        }
        return map.values();
    }

    /**
     * @param userGroups
     * @return
     */
    public static Collection sortAssignRolesUserGroups(Collection userGroups)
    {
        if (userGroups != null)
        {
            SortedMap map = new TreeMap();
            Iterator iter = userGroups.iterator();
            while (iter.hasNext())
            {
                UserGroupResponseEvent userGroup = (UserGroupResponseEvent) iter.next();
                map.put(userGroup.getName() + userGroup.getUserGroupId(), userGroup);
            }
            return map.values();
        } else
        {
            return null;
        }
    }

    /**
     * @param features
     *            Collection
     * @return Collection of featureTree in tree view
     */
    public static Collection sortFeatures(Collection features)
    {
        SortedMap map = new TreeMap();
        Iterator iter = features.iterator();
        while (iter.hasNext())
        {
            FeaturesResponseEvent currentEvent = (FeaturesResponseEvent) iter.next();
            map.put(currentEvent.getFeatureName() + currentEvent.getFeatureId(), currentEvent);
        }
        return map.values();
    }

    /**
     * @param roles
     *            Collection
     * @return Collection of roleNames in tree view
     */
    public static Collection sortRoleNames(Collection roleNames)
    {
        SortedMap map = new TreeMap();
        Iterator iter = roleNames.iterator();
        while (iter.hasNext())
        {
            RoleResponseEvent roleEvent = (RoleResponseEvent) iter.next();
            map.put(roleEvent.getRoleName() + roleEvent.getRoleId(), roleEvent);
        }
        return map.values();
    }

    /**
     * @param features
     *            Collection
     * @return Collection of featureTree in tree view
     */
    public static Collection sortUserAdministrationNames(Collection userNames)
    {
        SortedMap map = new TreeMap();
        Iterator iter = userNames.iterator();
        while (iter.hasNext())
        {
            UserResponseforUserAdministrationEvent userEvent = (UserResponseforUserAdministrationEvent) iter.next();
            map.put(userEvent.getUserLastName() + userEvent.getUserFirstName() + userEvent.getLogonId(), userEvent);
        }
        return map.values();
    }

    /**
     * @param features
     *            Collection
     * @return Collection of userProfileTree in tree view
     */
    public static Collection sortUserProfileNames(Collection userNames)
    {
        SortedMap map = new TreeMap();
        Iterator iter = userNames.iterator();
        while (iter.hasNext())
        {
            UserResponseEvent userEvent = (UserResponseEvent) iter.next();
            String firstName = userEvent.getFirstName();
            String lastName = userEvent.getLastName();
            String middleName = userEvent.getMiddleName();
            if (firstName == null)
            {
                firstName = "";
            }
            if (lastName == null)
            {
                lastName = "";
            }
            if (middleName == null)
            {
                middleName = "";
            }
            map.put(lastName + firstName + middleName + userEvent.getLogonId(), userEvent);
        }
        return map.values();
    }

    /**
     * @param features
     *            Collection
     * @return Collection of userGroupTree in tree view
     */
    public static Collection sortUserGroups(Collection userGroups)
    {
        SortedMap map = new TreeMap();
        Iterator iter = userGroups.iterator();
        while (iter.hasNext())
        {
            UserGroupResponseEvent currentEvent = (UserGroupResponseEvent) iter.next();
            if (currentEvent.getUsers() != null)
            {
                Collections.sort((List) currentEvent.getUsers());
            }
            map.put(currentEvent.getName(), currentEvent);
        }
        return map.values();
    }

    /**
     * @param features
     *            Collection
     * @return Collection of userProfileTree in tree view
     */
    public static Collection sortUserProfiles(Collection userProfiles)
    {
        SortedMap map = new TreeMap();
        Iterator iter = userProfiles.iterator();
        while (iter.hasNext())
        {
            UserResponseforUserAdministrationEvent userEvent = (UserResponseforUserAdministrationEvent) iter.next();
            String firstName = userEvent.getUserFirstName();
            String lastName = userEvent.getUserLastName();
            String middleName = userEvent.getUserMiddleName();
            if (firstName == null)
            {
                firstName = "";
            }
            if (lastName == null)
            {
                lastName = "";
            }
            if (middleName == null)
            {
                middleName = "";
            }
            map.put(lastName + firstName + middleName + userEvent.getLogonId(), userEvent);

        }
        return map.values();
    }

    /**
     * @param features
     *            Collection
     * @return Collection of userProfileTree in tree view
     */
    public static Collection sortOfficerProfileNames(Collection officerProfiles)
    {
        SortedMap map = new TreeMap();
        Iterator iter = officerProfiles.iterator();
        while (iter.hasNext())
        {
            OfficerProfileResponseEvent officerEvent = (OfficerProfileResponseEvent) iter.next();
            String firstName = officerEvent.getFirstName();
            String lastName = officerEvent.getLastName();
            String middleName = officerEvent.getMiddleName();
            if (firstName == null)
            {
                firstName = "";
            }
            if (lastName == null)
            {
                lastName = "";
            }
            if (middleName == null)
            {
                middleName = "";
            }
            map.put(lastName + firstName + middleName + officerEvent.getOfficerId(), officerEvent);
        }
        return map.values();
    }

    public static Collection getPasswordQues()
    {
        Collection codes = CodeHelper.getCodes(PDCodeTableConstants.PASSWORD_QUESTION);
        Iterator i = codes.iterator();
        Collection passwordQuestions = new ArrayList();
        while (i.hasNext())
        {
            CodeResponseEvent r = (CodeResponseEvent) i.next();
            passwordQuestions.add(r);
        }

        return passwordQuestions;
    }

    /**
     * @param userId
     *            unique Id of the user for which to get the name
     * @return Name representing the user
     */
    public static IName getUserName(String userId)
    {
        UserResponseEvent user = getUser(userId);
        if(user!=null){
	        String firstName = user.getFirstName();
	        String lastName = user.getLastName();
	        String middleName = user.getMiddleName();
	        if (firstName == null)
	        {
	            firstName = "";
	        }
	        if (lastName == null)
	        {
	            lastName = "";
	        }
	        if (middleName == null)
	        {
	            middleName = "";
	        }
	        	return new Name(firstName, middleName, lastName);
	    }
        return new Name("", "", "");
    }

    public static OfficerForm getOfficerForm(HttpServletRequest aRequest)
    {
        HttpSession session = aRequest.getSession();
        OfficerForm officerForm = (OfficerForm) session.getAttribute(OFFICER_FORM);
        return officerForm;
    }

    public static OfficerForm getOfficerForm(HttpServletRequest aRequest, boolean isCreate)
    {

        OfficerForm myForm = getOfficerForm(aRequest);
        if (myForm == null)
        {
            HttpSession session = aRequest.getSession();
            myForm = new OfficerForm();
            session.setAttribute(OFFICER_FORM, myForm);
        }
        return myForm;
    }

    /**
     * GetUserGroupbyName
     * @param userGrpName
     * @return userGroups
     */
	public static Collection<UserGroupResponseEvent> getUserGroupByName(
			String userGrpName) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetUserGroupsEvent ugEvent = (GetUserGroupsEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.GETUSERGROUPS);
		ugEvent.setUserGroupName(userGrpName);
		dispatch.postEvent(ugEvent);
		
		CompositeResponse replies = (CompositeResponse) dispatch.getReply();
		Map map = MessageUtil.groupByTopic(replies);
		MessageUtil.processReturnException(map);
		Collection userGroups = MessageUtil.compositeToCollection(replies,UserGroupResponseEvent.class);
		return userGroups;
	}
    /**
     * GetUsersByGroupId
     * @param groupId
     * @return userGroupUsers
     */
	public static Collection<SecurityUserResponseEvent> getUsersByGroupId(
			String groupId) {
		GetUserGroupUsersEvent event = (GetUserGroupUsersEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.GETUSERGROUPUSERS);
		event.setUserGroupId(groupId);
		List userGroupUsers = MessageUtil.postRequestListFilter(event,SecurityUserResponseEvent.class);
		return userGroupUsers;
	}
}