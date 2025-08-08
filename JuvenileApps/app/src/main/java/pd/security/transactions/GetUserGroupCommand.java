//Source file: C:\\views\\archproduction\\app\\src\\pd\\security\\transactions\\GetUserGroupCommand.java

package pd.security.transactions;

import pd.security.PDSecurityHelper;
import messaging.security.GetUserGroupEvent;
import messaging.security.reply.UserGroupResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.UserGroup;

public class GetUserGroupCommand implements ICommand
{

	/**
	 * @roseuid 429721360043
	 */
	public GetUserGroupCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 428B82BC0188
	 */
	public void execute(IEvent event)
	{
	    //87192 Not in use. Moved to SM
		/*GetUserGroupEvent ugEvent = (GetUserGroupEvent) event;
		UserGroup userGroup = UserGroup.find(ugEvent.getUserGroupId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		if (userGroup != null)
		{
			UserGroupResponseEvent ugrEvent = PDSecurityHelper.getUserGroupResponseEvent(userGroup);
			dispatch.postEvent(ugrEvent);
		}*/
	}

	/**
	 * @param event
	 * @roseuid 428B82BC018A
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 428B82BC018C
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 428B82BC0196
	 */
	public void update(Object anObject)
	{

	}
}
