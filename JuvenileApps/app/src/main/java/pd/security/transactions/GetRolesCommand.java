//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\pd\\security\\transactions\\GetRolesCommand.java

package pd.security.transactions;

import java.util.Iterator;
import messaging.security.GetRolesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.security.Role;
import pd.security.SecurityCommandsHelper;

public class GetRolesCommand implements ICommand
{

	/**
	 * @roseuid 42569402000F
	 */
	public GetRolesCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 425551F800CC
	 */
	public void execute(IEvent event)
	{
		/*GetRolesEvent getRolesEvent = (GetRolesEvent) event;
		Iterator roleIterator = Role.findAll(getRolesEvent);
		while (roleIterator.hasNext())
		{
			Role role = (Role) roleIterator.next();
			SecurityCommandsHelper.sendRoleResponseEvent(role);
		}
*/ //87191
	}

	/**
	 * @param event
	 * @roseuid 425551F800CE
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 425551F800D0
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 425551F800D2
	 */
	public void update(Object anObject)
	{

	}

}
