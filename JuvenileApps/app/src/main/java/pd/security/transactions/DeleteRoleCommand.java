//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\pd\\security\\transactions\\DeleteRoleCommand.java

package pd.security.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.security.DeleteRoleEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.security.Constraint;
import mojo.km.security.Feature;
import mojo.km.security.Role;
import mojo.km.security.User;
import mojo.km.security.UserGroup;
import pd.contact.agency.Agency;

/**
 * 
 * 
 * @author mchowdhury
 * @description delete a role  
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DeleteRoleCommand implements ICommand 
{
   
   /**
    * public constructor
    * @roseuid 4256F0A70222
    */
   public DeleteRoleCommand() 
   {
	   
   }
   
   /**
    * @param event
    * @roseuid 425551F802F3
    */
   public void execute(IEvent event) 
   {
	  /*DeleteRoleEvent roleEvent = (DeleteRoleEvent) event;
	  Role role = Role.find(roleEvent.getRoleId());
	  	  
	  //remove the feature association
	  this.removeFeatureAssociation(role);
	  
	  // remove the constraint association
	  this.removeConstraintAssociation(role);
	  
	  // delete user association
	  this.deleteUserAssociation(role);
	  
	  // delete usergroup association
	  this.deleteUserGroupAssociation(role);
	  
	  // delete the role
	  if (role != null)
	  {
	     role.delete();
	  }*/ //87191
   }
   
	/**
	 * @param role
	 */
	/*private void deleteUserGroupAssociation(Role role)
	{
		Collection usersGroups = role.getUserGroups();
	    if(usersGroups != null && !(usersGroups.isEmpty())){
	       Iterator rGIt = usersGroups.iterator();
		   UserGroup userGroup = null;
		   while (rGIt.hasNext()) {
		      userGroup = (UserGroup) rGIt.next();
			  role.removeUserGroups(userGroup);
		   }
		}
	}*/

   /**
	 * @param role
	 */
	/*private void deleteUserAssociation(Role role)
	{
		Collection users = role.getUsers();
		if(users != null && !(users.isEmpty())){
		   Iterator rIt = users.iterator();
		   User user = null;
		   while (rIt.hasNext()) {
			  user = (User) rIt.next();
			  role.removeUsers(user);
		   }
		}
	}
*/
   /**
	 * @param role
	 */
	/*private void removeFeatureAssociation(Role role)
	{
	   Collection features = role.getFeatures();
       if(features != null && !(features.isEmpty())){
	      Iterator fIt = features.iterator();
		  Feature feature = null;
		  while (fIt.hasNext()) {
		     feature = (Feature) fIt.next();
			 role.removeFeatures(feature);			 
		  }
	   }
    }*/

   /**
	 * @param role
	 */
	/*private void removeConstraintAssociation(Role role)
	{
		Collection constraints = role.getConstraintsByConstrainerType(Agency.class);
		if(constraints != null && !(constraints.isEmpty())){
		   Iterator cIt = constraints.iterator();
		   Constraint constraint = null;
		   while (cIt.hasNext()) {
			 constraint = (Constraint) cIt.next();
			 constraint.delete();
	       }
		}
	}*/

  /**
    * @param event
    * @roseuid 425551F802F5
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 425551F802F7
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 425551F802F9
    */
   public void update(Object anObject) 
   {
    
   }
}