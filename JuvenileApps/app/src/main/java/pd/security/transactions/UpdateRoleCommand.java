//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\pd\\security\\transactions\\UpdateRoleCommand.java

package pd.security.transactions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import pd.contact.agency.Agency;
import messaging.security.UpdateRoleEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.security.Constraint;
import mojo.km.security.Feature;
import mojo.km.security.Role;

/*
 * 
 * 
 * @author mchowdhury
 * @description update a role  
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
 
public class UpdateRoleCommand implements ICommand 
{
   
   /**
	* @roseuid 4256F0A8003E
	*/
   public UpdateRoleCommand() 
   {
    
   }
   
   /**
	* @param event
	* @roseuid 425551F803CA
	*/
   public void execute(IEvent event) 
   {
	/*  UpdateRoleEvent roleEvent = (UpdateRoleEvent) event;
	  Role role = Role.find(roleEvent.getRoleId());
 	  
	  if(role != null){
		  role.setName(roleEvent.getRoleName());
		  role.setDescription(roleEvent.getRoleDescription());
	  
		  // update the feature association
		  this.updateFeatureAssociation(role,roleEvent.getFeaturesList());
	  	   
		  // update the feature association of agency
		  this.updateConstraintAssociation(role,roleEvent.getAgenciesList());
	  }*/ //87191
   }

	/**
	 * @param role
	 * @return existingRoleFeaturesMap
	 */
/*	private HashMap getExistingRoleFeatureMap(Role role){
		HashMap map = new HashMap();
		Iterator existingIter = role.getFeatures().iterator();
		while(existingIter.hasNext()){
		   Feature existingFeature = (Feature) existingIter.next();
		   if(!map.containsKey(existingFeature.getOID().toString())){
			   map.put(existingFeature.getOID().toString(),existingFeature);
		   }
		}
		return map;
	}*/
	
	/**
	 * @param role
	 * @return existingRoleConstraintsMap
	 */
/*	private HashMap getExistingRoleConstraintsMap(Role role){
		HashMap map = new HashMap();
		Collection constrainsts = role.getConstraintsByConstrainerType(Agency.class);
		Iterator existingIter = constrainsts.iterator();
		while(existingIter.hasNext()){
		   Constraint existingConstraint = (Constraint) existingIter.next();
		   if(!map.containsKey(existingConstraint.getConstrainerId())){
			   map.put(existingConstraint.getConstrainerId(),existingConstraint);
		   }
		}
		return map;
	}*/	
	
   /**
	 * @param role
	 */
/*	private void updateConstraintAssociation(Role role,Collection wantedAgencyIds)
	{
		HashMap map = this.getExistingRoleConstraintsMap(role);
		Iterator iter =  wantedAgencyIds.iterator();
		while(iter.hasNext()){
		   String wantedAgencyId = (String) iter.next();
		   if(map != null && map.containsKey(wantedAgencyId)){
			   // user want this Constraint, Since this is already in database, so do nothing and remove from the map
			   map.remove(wantedAgencyId);
		   }else{
			   // these are new Constraints user want
			   role.addConstraint(wantedAgencyId, Agency.class); 
		   }
		}   
		   
		// at this point whatever is left in the map is undesirable  
		if(map != null && map.size() > 0){ 
			Iterator unWantedRoleConstraintsIterator = map.values().iterator();
			while(unWantedRoleConstraintsIterator.hasNext()){
				Constraint unWantedConstraint = (Constraint) unWantedRoleConstraintsIterator.next();
				unWantedConstraint.delete();
			}
		}
	}
	
	*//**
	 * @param role
	 * @param featureIds
	 *//*
	private void updateFeatureAssociation(Role role, Collection wantedFeatureIds)
	{
		HashMap map = this.getExistingRoleFeatureMap(role);
		  
		Iterator iter =  wantedFeatureIds.iterator();
		while(iter.hasNext()){
		   String wantedFeatureId = (String) iter.next();
		   if(map != null && map.containsKey(wantedFeatureId)){
			   // user want this Feature, Since this is already in database, so do nothing and remove from the map
			   map.remove(wantedFeatureId);
		   }else{
			   // these are new Features user want
			   Feature wantedFeature = Feature.find(wantedFeatureId);
			   if(wantedFeature != null){
				   role.insertFeatures(wantedFeature);
			   }
		   }
		}   
		   
		// at this point whatever is left in the map is undesirable   
		if(map != null && map.size() > 0){ 
			Iterator unWantedRoeFeaturesIterator = map.values().iterator();
			while(unWantedRoeFeaturesIterator.hasNext()){
				Feature unWantedFeature = (Feature) unWantedRoeFeaturesIterator.next();
				role.removeFeatures(unWantedFeature);
			}
		}
	}*/


/**
	* @param event
	* @roseuid 425551F803CC
	*/
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
	* @param event
	* @roseuid 425551F803CE
	*/
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
	* @param anObject
	* @roseuid 425551F803D0
	*/
   public void update(Object anObject) 
   {
    
   }
}
