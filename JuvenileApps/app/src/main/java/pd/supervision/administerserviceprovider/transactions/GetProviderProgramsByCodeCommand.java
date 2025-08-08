//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\GetProviderProgramsCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.Iterator;

import messaging.administerserviceprovider.GetProviderProgramsByCodeEvent;
import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.PDConstants;
import pd.supervision.administerserviceprovider.SearchServiceProvider;

public class GetProviderProgramsByCodeCommand implements ICommand 
{
   
   /**
    * @roseuid 450ACDA502DE
    */
   public GetProviderProgramsByCodeCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 450AA17901AA
    */
   public void execute(IEvent event) 
   {
   	GetProviderProgramsByCodeEvent reqEvent = 
		  (GetProviderProgramsByCodeEvent)event;

   	IHome home = new Home();
	MetaDataResponseEvent metaData = (MetaDataResponseEvent) home.findMeta(reqEvent, SearchServiceProvider.class);
	 
	int totalRecords = metaData.getCount();
	 
	if (totalRecords > PDConstants.SEARCH_LIMIT){
		ErrorResponseEvent errorResp = new ErrorResponseEvent();
		errorResp.setMessage("error.max.limit.exceeded");			
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(errorResp);
	}
	else
	{
		Iterator iter = SearchServiceProvider.findAll(reqEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while(iter.hasNext()){
			SearchServiceProvider serviceProvider = (SearchServiceProvider) iter.next();		
		    ProviderProgramResponseEvent providerProgramResponseEvent = new ProviderProgramResponseEvent();
		    
		    providerProgramResponseEvent.setServiceProviderId(serviceProvider.getJuvenileServiceProviderId());
		    providerProgramResponseEvent.setServiceProviderName(serviceProvider.getServiceProviderName());
		    providerProgramResponseEvent.setInHouse(serviceProvider.getInHouse());
		    providerProgramResponseEvent.setServiceProviderStatusId(serviceProvider.getServiceProviderStatusId());
		    providerProgramResponseEvent.setProgramName(serviceProvider.getProgramName());
		    providerProgramResponseEvent.setProgramCodeId(serviceProvider.getProgramCodeId());
		    providerProgramResponseEvent.setStateProgramCodeId(serviceProvider.getStateProgramCodeId());
		    providerProgramResponseEvent.setTargetInterventionId(serviceProvider.getTargetInterventionId());
		    providerProgramResponseEvent.setProgramStatusId(serviceProvider.getProgramStatusId());
		    providerProgramResponseEvent.setStartDate(serviceProvider.getStartDate());
		    providerProgramResponseEvent.setEndDate(serviceProvider.getEndDate());
		    providerProgramResponseEvent.setProgramDescription(serviceProvider.getProgramDescription());
		    providerProgramResponseEvent.setProviderProgramId(serviceProvider.getProgramId());
		    dispatch.postEvent(providerProgramResponseEvent);	 
		}    
	}
   	
   }
   
   /**
    * @param event
    * @roseuid 450AA17901AC
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 450AA17901AE
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 450AA17901B8
    */
   public void update(Object anObject) 
   {
    
   }
   
  
}
