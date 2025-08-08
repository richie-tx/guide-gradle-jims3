//Source file: C:\\views\\archproduction\\app\\src\\pd\\security\\transactions\\InactivateUserGroupCommand.java

package pd.security.transactions;

import pd.security.PDSecurityHelper;
import naming.PDSecurityConstants;
import messaging.security.InactivateUserGroupEvent;
import messaging.security.reply.UserGroupResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.UserGroup;

public class InactivateUserGroupCommand implements ICommand
{

	/**
	 * @roseuid 42972136010E
	 */
	public InactivateUserGroupCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 428B82BE0234
	 */
	public void execute(IEvent event)
	{
	    // done sec 87191
	/*	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		InactivateUserGroupEvent inactivateEvent = (InactivateUserGroupEvent) event;
		UserGroup userGroup = (UserGroup) UserGroup.find(inactivateEvent.getUserGroupId());

		if (userGroup != null)
		{
			userGroup.setStatusId(PDSecurityConstants.USER_GROUP_STATUS_INACTIVE);
			//create response event
			UserGroupResponseEvent ugrEvent = PDSecurityHelper.getUserGroupResponseEvent(userGroup);
			dispatch.postEvent(ugrEvent);
		}*/
	}

	/**
	 * @param event
	 * @roseuid 428B82BE0236
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 428B82BE0238
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 428B82BE0242
	 */
	public void update(Object anObject)
	{

	}
}
