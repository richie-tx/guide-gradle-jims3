//Source file: C:\\views\\archproduction\\app\\src\\pd\\security\\transactions\\UpdateUserGroupUsersCommand.java

package pd.security.transactions;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import messaging.security.UpdateUserGroupUsersEvent;
import messaging.security.UserGroupUserRequestEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.security.User;
import mojo.km.security.UserGroup;

public class UpdateUserGroupUsersCommand implements ICommand
{

	/**
	 * @roseuid 42972136019B
	 */
	public UpdateUserGroupUsersCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 428B82BD0311
	 */
	public void execute(IEvent event) // 87191
	{/*
		UpdateUserGroupUsersEvent updateEvent = (UpdateUserGroupUsersEvent) event;
		UserGroup userGroup = UserGroup.find(updateEvent.getUserGroupId());

		//Retrieve the users currently associated to user group			
		Iterator currentUsers = userGroup.getUsers().iterator();
		//Create a set of users currently associated to user group			
		Set currUsersSet = new HashSet();
		User user = null;
		while (currentUsers.hasNext())
		{
			user = ((User) currentUsers.next());
			if(user!=null)
				currUsersSet.add(user.getJIMSLogonId());
		}
		
		//selected users from ui
		Set selectedUsersSet = new HashSet();
		Enumeration requests = updateEvent.getRequests();
		while (requests.hasMoreElements())
		{
			UserGroupUserRequestEvent userEvent = (UserGroupUserRequestEvent) requests.nextElement();
			//Create HashMap of users selected for the user group
			selectedUsersSet.add(userEvent.getLogonId());
			//If new user, insert on the user group
			if (!(currUsersSet.contains(userEvent.getLogonId())))
			{
				User newUser = User.find(userEvent.getLogonId());
				if (newUser != null)
				{
					userGroup.insertUsers(newUser);
				}
			}
		}

		//Check for users to be removed from user group
		for (Iterator iter = currUsersSet.iterator(); iter.hasNext();)
		{
			String userId = (String) iter.next();
			if (!(selectedUsersSet.contains(userId)))
			{
				User removeUser = User.find(userId);
				if (removeUser != null)
				{
					userGroup.removeUsers(removeUser);
				}
			}
		}*///87191
	}

	/**
	 * @param event
	 * @roseuid 428B82BD0313
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 428B82BD031D
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 428B82BD031F
	 */
	public void update(Object anObject)
	{

	}
}