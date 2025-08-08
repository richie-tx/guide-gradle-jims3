//Source file: C:\\views\\archproduction\\app\\src\\pd\\security\\transactions\\GetRolesForUserGroupCommand.java

package pd.security.transactions;

import java.util.Collection;
import java.util.Iterator;

import pd.security.PDSecurityHelper;

import messaging.security.GetRolesForUserGroupEvent;
import messaging.security.reply.RoleResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.Role;
import mojo.km.security.UserGroup;

/*
 * 
 * 
 * @author mchowdhury
 * @description Get Roles for UserGroup 
 */
 
public class GetRolesForUserGroupCommand implements ICommand
{

	/**
	 * @roseuid 4297215802D4
	 */
	public GetRolesForUserGroupCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 42948624016B
	 */
	public void execute(IEvent event)
	{
	    	//87191
		/*GetRolesForUserGroupEvent aEvent = (GetRolesForUserGroupEvent) event;
		UserGroup userGroup = UserGroup.find(aEvent.getUserGroupId());
		Collection roles = userGroup.getRoles();
		this.sendRoleResponseEvent(roles);*/
	}

	/**
	 * @param roles
	 */
	private void sendRoleResponseEvent(Collection roles)
	{
		/*IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        Iterator rolesIterator = roles.iterator();
		Role role = null;
		while (rolesIterator.hasNext()) {
		   role = (Role) rolesIterator.next();
		   RoleResponseEvent responseEvent = PDSecurityHelper.getRoleResponseEvent(role);
		   dispatch.postEvent(responseEvent);
		}*/ //87191
	}
	
	/**
	 * @param event
	 * @roseuid 42948624016D
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42948624016F
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42948624017A
	 */
	public void update(Object anObject)
	{

	}
}
