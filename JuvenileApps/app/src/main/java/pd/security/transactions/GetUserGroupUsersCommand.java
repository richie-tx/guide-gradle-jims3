//Source file: C:\\views\\archproduction\\app\\src\\pd\\security\\transactions\\GetUserGroupUsersCommand.java

package pd.security.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.security.GetUserGroupUsersEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.User;
import mojo.km.security.UserEntityBean;
import pd.contact.PDContactHelper;
import pd.contact.user.UserProfile;
import pd.security.PDSecurityHelper;
import pd.transferobjects.helper.UserProfileHelper;

public class GetUserGroupUsersCommand implements ICommand
{

	/**
	 * @roseuid 4297213600CF
	 */
	public GetUserGroupUsersCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 428B82BC0224
	 */
	public void execute(IEvent event)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetUserGroupUsersEvent ugEvent = (GetUserGroupUsersEvent) event;
		List<UserEntityBean> users = PDSecurityHelper.getUserGroupByIdOrName(ugEvent.getUserGroupId(), ""); //UserGroup.find(ugEvent.getUserGroupId());
		if (users != null)
		{
		    Iterator<UserEntityBean> usersItr = users.iterator();
		    while (usersItr.hasNext())
		    {
			UserEntityBean user = usersItr.next();
			if (user != null && user.getUseraccesses() != null)
			{
			    UserProfile userProfile =UserProfileHelper.populateUserProfileFromUserEntityBean(user);
			    SecurityUserResponseEvent suResponseEvent =	PDContactHelper.getSecurityUserResponseEvent(userProfile);
			    dispatch.postEvent(suResponseEvent); //87191
			}
		    }
		}
		/*IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		if (userGroup != null)
		{
			Iterator users = userGroup.getUsers().iterator();
			users = PDContactHelper.sortedByUserName(users);
			while (users.hasNext())
			{
				User user = (User) users.next();
				if (user != null)
				{
					UserProfile userProfile = UserProfile.find(user.getJIMSLogonId());
					SecurityUserResponseEvent suResponseEvent =	PDContactHelper.getSecurityUserResponseEvent(userProfile);
					dispatch.postEvent(suResponseEvent); //87191
				}
			}
		}*/
	}

	/**
	 * @param event
	 * @roseuid 428B82BC0226
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 428B82BC0232
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 428B82BC0234
	 */
	public void update(Object anObject)
	{

	}
}
