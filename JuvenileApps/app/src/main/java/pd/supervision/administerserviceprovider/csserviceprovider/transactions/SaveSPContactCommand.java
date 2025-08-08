//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\csserviceprovider\\transactions\\SaveSPContactCommand.java

package pd.supervision.administerserviceprovider.csserviceprovider.transactions;

import naming.CSAdministerServiceProviderConstants;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProvider;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProviderContact;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProviderContactHelper;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProviderHelper;
import messaging.csserviceprovider.SaveSPContactEvent;
import messaging.csserviceprovider.reply.CSServiceProviderResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.km.context.ICommand;

public class SaveSPContactCommand implements ICommand 
{
   
   /**
    * @roseuid 478679450198
    */
   public SaveSPContactCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 478543A30339
    */
   public void execute(IEvent event) 
   {
  		// save service provider contact using the specified event info
       SaveSPContactEvent save_contact_event = (SaveSPContactEvent)event;
       CSServiceProviderContact sp_contact = 
           	CSServiceProviderContactHelper.saveServiceProviderContact(save_contact_event);

       if (sp_contact != null)
       {           
           CSServiceProvider cs_service_provider = 
               					CSServiceProvider.find(sp_contact.getServiceProviderId());

      		//activate service provider if prerequisites met
           if (CSServiceProviderHelper.satisfyActivateCondition(
                   							cs_service_provider))
           {
               CSServiceProviderHelper.activateServiceProvider(cs_service_provider);                   
           }
           
      			//create a service provider response event for the given service provider
           CSServiceProviderResponseEvent sp_response_event =
               CSServiceProviderHelper.getServiceProviderResponseEvent(cs_service_provider);
           MessageUtil.postReply(sp_response_event);            
           
       }
   }
   
   /**
    * @param event
    * @roseuid 478543A3033B
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 478543A30348
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 478543A3034A
    */
   public void update(Object anObject) 
   {
    
   }
   
}
