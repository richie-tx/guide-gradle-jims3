//Source file: C:\\views\\archproduction\\app\\src\\pd\\security\\transactions\\GetRolesForUserCommand.java

package pd.security.transactions;

import java.util.Collection;
import java.util.Iterator;

import pd.security.PDSecurityHelper;

import messaging.security.GetRolesForUserEvent;
import messaging.security.reply.RoleResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.Role;
import mojo.km.security.User;

/*
 * @author mchowdhury
 * @description Get Roles for User
 */
 
public class GetRolesForUserCommand implements ICommand
{

	/**
	 * @roseuid 429721580285
	 */
	public GetRolesForUserCommand()
	{
		
	}

	/**
	 * @param event
	 * @roseuid 429486240090
	 */
	public void execute(IEvent event)
	{
		GetRolesForUserEvent aEvent = (GetRolesForUserEvent) event;
		/*User user = User.find(aEvent.getLogonId());
		Collection roles = user.getRoles();
		this.sendRoleResponseEvent(roles);*/ //87191
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
	 * @roseuid 429486240092
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 429486240094
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42948624009E
	 */
	public void update(Object anObject)
	{

	}
}
