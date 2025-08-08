//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\csserviceprovider\\transactions\\SaveProgramCommand.java

package pd.supervision.administerserviceprovider.csserviceprovider.transactions;

import naming.CSAdministerServiceProviderConstants;
import pd.supervision.administerserviceprovider.csserviceprovider.CSProgramHelper;
import pd.supervision.administerserviceprovider.csserviceprovider.CSProgram;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProvider;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProviderHelper;
import messaging.csserviceprovider.SaveProgramEvent;
import messaging.csserviceprovider.reply.CSServiceProviderResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.km.context.ICommand;

public class SaveProgramCommand implements ICommand 
{
   
   /**
    * @roseuid 4786792E0225
    */
   public SaveProgramCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47856C100326
    */
   public void execute(IEvent event) 
   {
   		// save service provider program using the specified event info
       SaveProgramEvent save_prog_event = (SaveProgramEvent)event;
       CSProgram sp_program = 
           	CSProgramHelper.saveServiceProviderProgram(save_prog_event);

       if (sp_program != null)
       {
           CSServiceProvider cs_service_provider = 
               					CSServiceProvider.find(sp_program.getServiceProviderId());
           
      		//activate service provider if prerequisites met and not presently active 
           if (!cs_service_provider.getServiceProviderStatus().
           			equals(CSAdministerServiceProviderConstants.ACTIVE_SP_STATUS)
						&& CSServiceProviderHelper.satisfyActivateCondition(
                   							cs_service_provider))
           {
               CSServiceProviderHelper.activateServiceProvider(cs_service_provider);                   
           }
            //reset service provider to pending status if prerequisites met
           if (save_prog_event.getProgramStatus().
           		equals(CSAdministerServiceProviderConstants.INACTIVE_PROG_STATUS)
           		&&
				(CSServiceProviderHelper.satisfyResetPendingCondition(
                   							cs_service_provider)))
           {
               CSServiceProviderHelper.resetPendingServiceProvider(cs_service_provider);                   
           }
           
           	//create a service provider response event for the given service provider
           CSServiceProviderResponseEvent sp_response_event =
               CSServiceProviderHelper.getServiceProviderResponseEvent(cs_service_provider);
           MessageUtil.postReply(sp_response_event);                       
       }
       
   }//end of execute()
   
   /**
    * @param event
    * @roseuid 47856C100333
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47856C100335
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 47856C100337
    */
   public void update(Object anObject) 
   {
    
   }
   
}
