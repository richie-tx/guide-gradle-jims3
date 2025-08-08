//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\pd\\security\\transactions\\GetRoleUsersCommand.java

package pd.security.transactions;

import java.util.Collection;
import java.util.Iterator;
import pd.security.SecurityCommandsHelper;
import messaging.security.GetRoleUsersAndUserGroupsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.security.Role;
import mojo.km.security.User;
import mojo.km.security.UserGroup;

/**
 * @author mchowdhury
 * This command returns user and UserGroups associated with the Role.
 */

public class GetRoleUsersAndUserGroupsCommand implements ICommand 
{
   
   /**
    * @roseuid 425AB15803D8
    */
   public GetRoleUsersAndUserGroupsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 425551F802B1
    */
   public void execute(IEvent event) 
   {
	/*  GetRoleUsersAndUserGroupsEvent userEvent = (GetRoleUsersAndUserGroupsEvent) event;
	  Role role = Role.find(userEvent.getRoleId());
	  	
	  Collection userGroups = role.getUserGroups();
	  Iterator iter = userGroups.iterator();
	  while (iter.hasNext()){
		 UserGroup userGroup = (UserGroup) iter.next();
		 if(userGroup != null){
		 	SecurityCommandsHelper.sendUserGroupsResponseEvent(userGroup);
		 }
	  }
	  
	  Collection users = role.getUsers();
	  Iterator iterator = users.iterator();
	  while (iterator.hasNext()){
	    User user = (User) iterator.next();
	    if(user != null){
	    	SecurityCommandsHelper.sendUsersResponseEvent(user);
	    }
	  }*/ //87191
   }

/**
    * @param event
    * @roseuid 425551F802B3
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 425551F802B5
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 425551F802B7
    */
   public void update(Object anObject) 
   {
    
   }
}
