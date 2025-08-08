//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\csserviceprovider\\transactions\\GetSPContactCommand.java

package pd.supervision.administerserviceprovider.csserviceprovider.transactions;

import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProviderContact;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProviderContactHelper;
import messaging.csserviceprovider.GetSPContactEvent;
import messaging.csserviceprovider.reply.CSServiceProviderContactResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.km.context.ICommand;

public class GetSPContactCommand implements ICommand 
{
   
   /**
    * @roseuid 4786798A0080
    */
   public GetSPContactCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47856C10020C
    */
   public void execute(IEvent event) 
   {
			//retrieve the specified service provider contact
       GetSPContactEvent get_contact_event = (GetSPContactEvent)event;
       CSServiceProviderContact contact = 
           CSServiceProviderContactHelper.getContact(get_contact_event.getContactId());
       
       		//post response with contact details
       CSServiceProviderContactResponseEvent contact_response =
           CSServiceProviderContactHelper.getContactResponseEvent(contact);
       MessageUtil.postReply(contact_response);    
    
   }
   
   /**
    * @param event
    * @roseuid 47856C10021A
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47856C10021C
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 47856C10022B
    */
   public void update(Object anObject) 
   {
    
   }
   
}
