//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\GetContactsCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.Iterator;

import naming.PDAdministerServiceProviderConstants;

import pd.supervision.administerserviceprovider.SP_Profile;
import messaging.administerserviceprovider.GetContactsEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetContactsCommand implements ICommand 
{
   
   /**
    * @roseuid 450ACD9202BF
    */
   public GetContactsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 450AA1670311
    */
   public void execute(IEvent event) 
   {
   	GetContactsEvent reqEvent = (GetContactsEvent)event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	
	Iterator iter = SP_Profile.findAll(PDAdministerServiceProviderConstants.SERVICE_PROVIDER_ID, reqEvent.getServiceProviderId());

	while(iter.hasNext()){
		SP_Profile spProfile = (SP_Profile) iter.next();		
		ServiceProviderContactResponseEvent respEvent = new ServiceProviderContactResponseEvent();  

		respEvent.setJuvServProviderProfileId(spProfile.getOID().toString());
		respEvent.setFirstName(spProfile.getFirstName());
		respEvent.setMiddleName(spProfile.getMiddleName());
		respEvent.setLastName(spProfile.getLastName());
		respEvent.setEmployeeId(spProfile.getEmployeeId()); //86318
		respEvent.setAdminContact(spProfile.getIsAdminContact());
		respEvent.setEmail(spProfile.getEmail());
		respEvent.setWorkPhone(spProfile.getPhoneNum());		
	//	respEvent.setExtnNum(spProfile.getExtnNum()); //88615
	//	respEvent.setPrefix(spProfile.getPrefix());
	//	respEvent.setSuffix(spProfile.getSuffix());
	//	respEvent.setCellPhone(spProfile.getCellPhone());
	//	respEvent.setFaxNum(spProfile.getFaxNum());
	//	respEvent.setNotes(spProfile.getNotes());
	//	respEvent.setPager(spProfile.getPager());
		respEvent.setLogonId(spProfile.getInHouseLogonId());
	//	respEvent.setJobTitle(spProfile.getJobTitle());
		respEvent.setStatusId(spProfile.getStatusId());
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
