package pd.contact.user.transactions;// no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.

/*//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\user\\transactions\\GetUserProfileHistoryCommand.java

package pd.contact.user.transactions;

import java.util.Iterator;

import messaging.contact.user.reply.UserHistoryResponseEvent;
import messaging.user.GetUserProfileHistoryEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import pd.contact.PDContactHelper;
import pd.contact.user.UserHistory;
import pd.contact.user.UserProfile;

public class GetUserProfileHistoryCommand implements ICommand
{

	*//**
	 * 
	 *//*
	public GetUserProfileHistoryCommand()
	{

	}

	*//**
	 * @param event
	 *//*
	public void execute(IEvent event)
	{
		GetUserProfileHistoryEvent getHistoryEvent = (GetUserProfileHistoryEvent) event;
		UserProfile userProfile = UserProfile.find(getHistoryEvent.getLogonId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		//Create user reply
		if (userProfile != null)
		{
			Iterator userHistories = UserHistory.findAll("logonId",getHistoryEvent.getLogonId());
			while (userHistories.hasNext())
			{
				UserHistory userHistory = (UserHistory)userHistories.next();
				UserHistoryResponseEvent responseEvent = PDContactHelper.getUserHistoryResponseEvent(userHistory);
				dispatch.postEvent(responseEvent);
			}
		}
		//throw new ReturnException("User Profile not found for User with ID = '" + getHistoryEvent.getLogonId() + "'.");
	}

	*//**
	 * @param event
	 * @roseuid 42E65EA601BE
	 *//*
	public void onRegister(IEvent event)
	{

	}

	*//**
	 * @param event
	 * @roseuid 42E65EA601C0
	 *//*
	public void onUnregister(IEvent event)
	{

	}

	*//**
	 * @param anObject
	 * @roseuid 42E65EA601C2
	 *//*
	public void update(Object anObject)
	{

	}
}*/