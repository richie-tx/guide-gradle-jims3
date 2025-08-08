//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\pd\\security\\transactions\\CreateRoleCommand.java

package pd.security.transactions;

import java.util.Collection;
import java.util.Iterator;
import pd.contact.agency.Agency;
import pd.security.PDSecurityHelper;
import messaging.security.CreateRoleEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.security.Feature;
import mojo.km.security.Role;

/**
 * 
 * 
 * @author mchowdhury
 * @description Create a role given the role name, description, agencies and features 
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CreateRoleCommand implements ICommand 
{
   
   /**
	* public constructor
	* @roseuid 4256946101D4
	*/
   public CreateRoleCommand() 
   {
    
   }
   
   /**
	* @param event IEvent
	* @roseuid 425551F80208
	*/
   public void execute(IEvent event) 
   {/*
	   CreateRoleEvent roleEvent = (CreateRoleEvent) event;
	    
	   // check for whether the same role exist for the agency
	   boolean doesRoleExist = false;
	   Collection agenciesId = roleEvent.getAgenciesList();
	   Iterator iterAgencies = agenciesId.iterator();
	   while(iterAgencies.hasNext()){
		  String agencyId = (String) iterAgencies.next();
		  doesRoleExist = Role.isRoleExistsWithinAgency("name",roleEvent.getRoleName().toUpperCase(),agencyId);
		  if(doesRoleExist) 
			 break;  
	   }
	   // if duplicate record exists, send the error event
	   if(doesRoleExist){
		  PDSecurityHelper.sendErrorEvent(roleEvent.getRoleName());
		  roleEvent = null;
		  return; 
	   }else{
		  // persist the role
		  Role role = this.saveRole(roleEvent);
		  // make asssociation to features
		  this.addFeatureToRole(role, roleEvent);
		  // make asssociation to agencies
		  this.addAgenciesToRole(role, roleEvent);
	   }*/ //87191
   }

   /**
	* @param roleEvent CreateRoleEvent
	* @return Role
	*/
	/*private Role saveRole(CreateRoleEvent roleEvent){
	   Role role = new Role();
	   role.setName(roleEvent.getRoleName());
	   role.setDescription(roleEvent.getRoleDescription());
	   role.setRoleType(roleEvent.getRoleType());
	   role.setCreatorUserId(roleEvent.getRoleCreatorId());
	   new Home().bind(role);
	   return role;
	}*/ //87191

   /**
	* @param role
	* @param roleEvent
	*/
	/*private void addAgenciesToRole(Role role, CreateRoleEvent roleEvent)
	{
	   Collection agenciesId = roleEvent.getAgenciesList();
	   if(agenciesId != null && agenciesId.size() > 0){  
		  Iterator itAgencies = agenciesId.iterator();
		  while(itAgencies.hasNext()){
			 String agencyId = (String) itAgencies.next();
			 role.addConstraint(agencyId, Agency.class); 
		  }
	   }
	}*/ //87191

   /**
	* @param role Role
	* @param roleEvent CreateRoleEvent
	*/
	/*private void addFeatureToRole(Role role, CreateRoleEvent roleEvent)
	{
		Collection featuresId = roleEvent.getFeaturesList();
		if(featuresId != null && featuresId.size() > 0){  
		   Iterator itFeatures = featuresId.iterator();
		   while(itFeatures.hasNext()){
			  String featureId = (String) itFeatures.next();
			  Feature feature = Feature.find(featureId);
			  if((feature.getParentId() != null ) && (!feature.getParentId().equals(""))){
				  role.insertFeatures(feature);
			  }
			  }
		}  
	}*/ //87191

/**
	* @param event
	* @roseuid 425551F8020A
	*/
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
	* @param event
	* @roseuid 425551F80214
	*/
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
	* @param anObject
	* @roseuid 425551F80216
	*/
   public void update(Object anObject) 
   {
    
   }
}
