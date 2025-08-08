//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\csserviceprovider\\transactions\\GetServiceProviderCommand.java

package pd.supervision.administerserviceprovider.csserviceprovider.transactions;

import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProvider;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProviderHelper;
import messaging.csserviceprovider.GetCSServiceProviderEvent;
import messaging.csserviceprovider.reply.CSServiceProviderResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.km.context.ICommand;

public class GetCSServiceProviderCommand implements ICommand 
{
   
   /**
    * @roseuid 4786878F026B
    */
   public GetCSServiceProviderCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4786842E00A3
    */
   public void execute(IEvent event) 
   {
       		//retrieve the specified service provider
       GetCSServiceProviderEvent get_sp_event = (GetCSServiceProviderEvent)event;
       CSServiceProvider service_provider = 
           CSServiceProviderHelper.getServiceProvider(get_sp_event.getServiceProviderId());
       
       		//post response with service provider details
       CSServiceProviderResponseEvent sp_response =
           CSServiceProviderHelper.getServiceProviderResponseEvent(service_provider);
       MessageUtil.postReply(sp_response);
       
   }//end of execute()
   
   /**
    * @param event
    * @roseuid 4786842E00D0
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4786842E00D2
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 4786842E00E0
    */
   public void update(Object anObject) 
   {
    
   }
   
}
