//Source file: C:\\views\\archproduction\\app\\src\\pd\\security\\transactions\\UpdateSecurityRolesForUserGroupCommand.java

package pd.security.transactions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import pd.security.PDSecurityHelper;

import messaging.security.UpdateSecurityRolesForUserEvent;
import messaging.security.UpdateSecurityRolesForUserGroupEvent;
import messaging.security.reply.RoleResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.security.Role;
import mojo.km.security.User;
import mojo.km.security.UserGroup;

/*
 * @author mchowdhury
 * @description Assign roles to a user
 */
public class UpdateSecurityRolesForUserCommand implements ICommand
{
	/**
	 * @roseuid 429721580360
	 */
	public UpdateSecurityRolesForUserCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 429486240301
	 */
	public void execute(IEvent event)
	{/*
		UpdateSecurityRolesForUserEvent aEvent = (UpdateSecurityRolesForUserEvent) event; 
		User user = User.find(aEvent.getLogonId()); 

		if(user != null){
			// update the role association
			this.updateRoleAssociation(user, aEvent.getRoles());
		}*/ //87191
	}

	/**
	 * @param user
	 * @return existingRoleFeaturesMap
	 */
	private HashMap getExistingRoleFeatureMap(User user){
		HashMap existingRoleMap = new HashMap();
		/*Iterator existingIter = user.getRoles().iterator();
		while(existingIter.hasNext()){
			Role existingRole = (Role) existingIter.next();
			if(!existingRoleMap.containsKey(existingRole.getRoleId())){
				existingRoleMap.put(existingRole.getRoleId(),existingRole);
			}
		}*/ //87191
		return existingRoleMap;		
	}

	/**
	 * @param user User
	 * @param wantedRoles Collection
	 * @param existingRoleMap
	 */
	private void updateRoleAssociation(User user, Collection wantedRoles)
	{
		/*HashMap map = this.getExistingRoleFeatureMap(user);

		Iterator iter =  wantedRoles.iterator();
		while(iter.hasNext()){
		   RoleResponseEvent wantedRoleResponse = (RoleResponseEvent) iter.next();
		   Role wantedRole = Role.find(wantedRoleResponse.getRoleId());
		   if(map != null && map.containsKey(wantedRole.getRoleId())){
			   // user want this Role, Since this is already in database, so do nothing and remove from the map
			   map.remove(wantedRole.getRoleId());
		   }else{
			   // these are new Roles user want
			   if(wantedRole != null){
			   	 // user.insertRoles(wantedRole); 87191
			   }
		   }
		}   
		   
		// at this point whatever is left in the existingRoleMap is undesirable   
		if(map != null && map.size() > 0){
			Iterator unWantedRolesIterator = map.values().iterator();
		    while(unWantedRolesIterator.hasNext()){
			    Role unWantedRole = (Role) unWantedRolesIterator.next();
			  //  user.removeRoles(unWantedRole); 87191
		    }
		}*/// 87191
	}


	/**
	 * @param event
	 * @roseuid 429486240303
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 429486240305
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42948624030F
	 */
	public void update(Object anObject)
	{

	}
}
