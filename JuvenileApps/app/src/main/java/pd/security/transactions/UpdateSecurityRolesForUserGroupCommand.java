//Source file: C:\\views\\archproduction\\app\\src\\pd\\security\\transactions\\UpdateSecurityRolesForUserGroupCommand.java

package pd.security.transactions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import messaging.security.UpdateSecurityRolesForUserGroupEvent;
import messaging.security.reply.RoleResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.security.Role;
import mojo.km.security.UserGroup;

/*
 * @author mchowdhury
 * @description Assign roles to a usergroup
 */
public class UpdateSecurityRolesForUserGroupCommand implements ICommand
{
	/**
	 * @roseuid 429721580360
	 */
	public UpdateSecurityRolesForUserGroupCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 429486240301
	 */
	public void execute(IEvent event)
	{
	    //done on sec 87191
		/*UpdateSecurityRolesForUserGroupEvent aEvent = (UpdateSecurityRolesForUserGroupEvent) event; 
		UserGroup userGroup = UserGroup.find(aEvent.getUserGroupId()); 
	
		if(userGroup != null){
			this.updateRoleAssociation(userGroup,aEvent.getRoles());
		}*/
	}

	/**
	 * @param user UserGroup
	 * @param wantedRoles Collection
	 */
	private void updateRoleAssociation(UserGroup userGroup, Collection wantedRoles)
	{
		/*HashMap map = this.getExistingRoleFeatureMap(userGroup);

		Iterator iter =  wantedRoles.iterator();
		while(iter.hasNext()){
		   RoleResponseEvent wantedRoleResponse = (RoleResponseEvent) iter.next();
  		   Role wantedRole = Role.find(wantedRoleResponse.getRoleId());
		   
		   if(map != null && map.containsKey(wantedRole.getRoleId())){
			   // this Role is wanted, Since this is already in database, so do nothing and remove from the map
			   map.remove(wantedRole.getRoleId());
		   }else{
			   // these are new Roles user want
			   if(wantedRole != null){
			     //  userGroup.insertRoles(wantedRole);87191
			   }
		   }
		}   
		   
		// at this point whatever is left in the existingRoleMap is undesirable   
		if(map != null && map.size() > 0){
			Iterator unWantedRolesIterator = map.values().iterator();
			while(unWantedRolesIterator.hasNext()){
				Role unWantedRole = (Role) unWantedRolesIterator.next();
				//userGroup.removeRoles(unWantedRole);87191
			}
		}*/ //87191
	}

	/**
	 * @param userGroup
	 * @return existingRoleFeaturesMap
	 */
	private HashMap getExistingRoleFeatureMap(UserGroup userGroup){
		HashMap existingRoleMap = new HashMap();
		/*Iterator existingIter = userGroup.getRoles().iterator(); 87191
		while(existingIter.hasNext()){
			Role existingRole = (Role) existingIter.next();
			if(!existingRoleMap.containsKey(existingRole.getRoleId())){
				existingRoleMap.put(existingRole.getRoleId(),existingRole);
			}
		}*/
		return existingRoleMap;
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
