//Source file: C:\\views\\archproduction\\app\\src\\pd\\security\\transactions\\ValidateUserGroupDetailsCommand.java

package pd.security.transactions;

import java.util.Iterator;

import naming.PDConstants;
import messaging.security.ValidateUserGroupDetailsEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.UserGroup;

public class ValidateUserGroupDetailsCommand implements ICommand
{

	/**
	 * @roseuid 4297213601E9
	 */
	public ValidateUserGroupDetailsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 428B82BC02FF
	 */
	public void execute(IEvent event)
	{
	//87191
	/*	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
		ValidateUserGroupDetailsEvent validateEvent = (ValidateUserGroupDetailsEvent) event;
		
		Iterator userGroups = UserGroup.findAll(validateEvent);
		
		while (userGroups.hasNext())
		{
			DuplicateRecordErrorResponseEvent error = new DuplicateRecordErrorResponseEvent();
			error.setMessage("A user group already exists for this name and description.");
			error.setTopic(PDConstants.ERROR_EVENT_TOPIC);
			dispatch.postEvent(error);
			return;
		}*/ //87191
	}

	/**
	 * @param event
	 * @roseuid 428B82BC0301
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 428B82BC0303
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 428B82BC0305
	 */
	public void update(Object anObject)
	{

	}
}