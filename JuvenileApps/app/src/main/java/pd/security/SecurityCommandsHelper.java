/*
 * Created on Apr 21, 2005
 *
 */
package pd.security;

import messaging.security.reply.RoleUsersResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.security.ISecurityManager;
import mojo.km.security.Role;
import mojo.km.security.User;
import mojo.km.security.UserGroup;

/**
 * @author athorat
 */
public final class SecurityCommandsHelper
{
    /**
     * Constructor
     */
    private SecurityCommandsHelper()
    {
	super();
    }

    public static void sendRoleResponseEvent(Role role)
    {
	/*IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	RoleResponseEvent responseEvent = new RoleResponseEvent();
	//responseEvent.setTopic(SecurityConstants.ROLE_EVENT_TOPIC);
	responseEvent.setRoleId(role.getOID().toString());
	responseEvent.setRoleName(role.getName());
	responseEvent.setRoleDescription(role.getDescription());
	responseEvent.setRoleType(role.getRoleType());
	dispatch.postEvent(responseEvent);*/ //87191
    }

    /*
     * @params user User
     */
    public static void sendUsersResponseEvent(User user)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	RoleUsersResponseEvent responseEvent = new RoleUsersResponseEvent();
	/* 87191
	responseEvent.setTopic(SecurityConstants.USER_EVENT_TOPIC);
	//responseEvent.setUserId(user.getUserID());
	 responseEvent.setUserId(user.getJIMSLogonId());
	   responseEvent.setUserLoginName(user.getJIMSLogonId());
	   responseEvent.setFirstName(user.getFirstName());
	   responseEvent.setMiddleName(user.getMiddleName());
	   responseEvent.setLastName(user.getLastName());
	//responseEvent.setName(user.getJIMSLogonId());
	responseEvent.setUserTypeId(user.getUserTypeId());*/
	dispatch.postEvent(responseEvent);
    }

    /*
     * @params userGroups UserGroup
     */
    public static void sendUserGroupsResponseEvent(UserGroup userGroup)
    {
	//87191
	/*IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	RoleUserGroupsResponseEvent responseEvent = new RoleUserGroupsResponseEvent();
	responseEvent.setTopic(SecurityConstants.USERGROUP_EVENT_TOPIC);
	responseEvent.setDescription(userGroup.getDescription());
	responseEvent.setName(userGroup.getName());
	responseEvent.setUsers(userGroup.getUsers());
	responseEvent.setUserGroupId(userGroup.getOID().toString());
	dispatch.postEvent(responseEvent);*/
    }

    public static boolean isUserMA()
    {
	ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	return manager.isUserOfType("MA");
    }

    public static boolean isUserSA()
    {
	ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	return manager.isUserOfType("MA");
    }

}
