//Source file: C:\\views\\archproduction\\app\\src\\pd\\security\\transactions\\DeleteUserGroupCommand.java

package pd.security.transactions;

import java.util.Iterator;
import pd.contact.agency.Agency;
import messaging.security.DeleteUserGroupEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.security.Constraint;
import mojo.km.security.Role;
import mojo.km.security.User;
import mojo.km.security.UserGroup;

public class DeleteUserGroupCommand implements ICommand
{

	/**
	 * @roseuid 42972135039E
	 */
	public DeleteUserGroupCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 428B82BE01F4
	 */
	public void execute(IEvent event)
	{
		DeleteUserGroupEvent ugEvent = (DeleteUserGroupEvent) event;
		//87191
		/*UserGroup userGroup = UserGroup.find(ugEvent.getUserGroupId());
		
		if (userGroup != null)
		{
			Iterator roles = userGroup.getRoles().iterator();
			while (roles.hasNext())
			{
				Role role = (Role) roles.next();
				userGroup.removeRoles(role);
			}
			Iterator users = userGroup.getUsers().iterator();
			while (users.hasNext())
			{
				User user = (User) users.next();
				userGroup.removeUsers(user);
			}
			Iterator constraints = userGroup.getConstraints().iterator();
			while (constraints.hasNext())
			{
				Constraint constraint = (Constraint) constraints.next();
				userGroup.removeConstraint(constraint.getConstrainerId(),Agency.class);
			}
			userGroup.delete();
		}*/ //87191 taken Care on the SM side.
	}

	/**
	 * @param event
	 * @roseuid 428B82BE01F6
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 428B82BE01F8
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 428B82BE01FA
	 */
	public void update(Object anObject)
	{

	}
}