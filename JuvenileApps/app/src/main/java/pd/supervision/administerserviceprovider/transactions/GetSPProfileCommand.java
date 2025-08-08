//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\GetContactsCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.Iterator;

import messaging.administerserviceprovider.GetSPProfileEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.user.GetUserProfilesEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.contact.agency.Department;
import pd.contact.user.UserProfile;
import pd.supervision.administerserviceprovider.SP_Profile;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class GetSPProfileCommand implements ICommand 
{
   
   /**
    * @roseuid 450ACD9202BF
    */
   public GetSPProfileCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 450AA1670311
    */
   public void execute(IEvent event) 
   {
	   GetSPProfileEvent reqEvent = (GetSPProfileEvent)event;
	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
	   Iterator<SP_Profile> iter = SP_Profile.findAll(reqEvent);
	
	   while(iter.hasNext()){
	       SP_Profile spProfile = (SP_Profile) iter.next();		
		   ServiceProviderContactResponseEvent respEvent = new ServiceProviderContactResponseEvent();  
	       respEvent.setServiceProviderId(spProfile.getServiceProviderId());
	       respEvent.setAdminContact(spProfile.getIsAdminContact());
		   respEvent.setJuvServProviderProfileId(spProfile.getOID().toString());
		   respEvent.setFirstName(spProfile.getFirstName());
		   respEvent.setMiddleName(spProfile.getMiddleName());
		   respEvent.setLastName(spProfile.getLastName());
		   respEvent.setEmployeeId(spProfile.getEmployeeId());
		   respEvent.setLogonId(spProfile.getJIMSLogonId());
		   respEvent.setProviderName(spProfile.getServiceProviderName());
		   respEvent.setJuvServProviderProfileId(spProfile.getOID().toString());
		   if(reqEvent.isDepartmentInfoRequired()){
		   	   GetUserProfilesEvent uEvent = new GetUserProfilesEvent();
		   	   String logonId = (spProfile.getInHouseLogonId() == null || spProfile.getInHouseLogonId().equals(""))?reqEvent.getJimsLogonId():spProfile.getInHouseLogonId();
		   	   uEvent.setLogonId(logonId);
		   	   
		   	   UserProfile user = UserProfile.find(logonId);
			  /* UserProfile user = null;
			   while(users.hasNext()){
		   	   	   user = (UserProfile) users.next();
		   	   	   // not expecting more than one
		   	   	   break;
		   	   }*/
		   	   if(user != null){
		   	   	   Department dept = Department.find(user.getDepartmentId());
		   	   	   if(dept != null){
			   	   	   respEvent.setDepartmentName(dept.getDepartmentName());
			   	   }
		   	   }
		   }
		   dispatch.postEvent(respEvent);	 
		}    
   }
   
   /**
    * @param event
    * @roseuid 450AA1670313
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 450AA167031E
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 450AA1670320
    */
   public void update(Object anObject) 
   {
    
   }
   
  
}
