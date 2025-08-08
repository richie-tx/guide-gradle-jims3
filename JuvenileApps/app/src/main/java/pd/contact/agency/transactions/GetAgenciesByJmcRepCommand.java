//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\pd\\security\\transactions\\GetAgenciesCommand.java

package pd.contact.agency.transactions;

import java.util.Iterator;
import naming.PDContactConstants;
import naming.ResponseLocatorConstants;
import pd.common.ResponseCommonUtil;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.contact.PDContactHelper;
import pd.contact.agency.Agency;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.agency.GetAgenciesByJmcRepEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *
 */
public class GetAgenciesByJmcRepCommand extends ResponseCommonUtil implements ICommand 
{
   
   /**
    * @roseuid 4256F0A7029F
    */
   public GetAgenciesByJmcRepCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 425551F80098
    */
   public void execute(IEvent event) 
   {
   		ResponseContextFactory respFac = new ResponseContextFactory();
   		ResponseCreator aCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.AGENCY_RESPONSE_LOCATOR,respFac);
		GetAgenciesByJmcRepEvent agyEvent = (GetAgenciesByJmcRepEvent) event;
		agyEvent.setJmcRepId("Y");
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		/*Iterator agencies = Agency.findAll(agyEvent); //87191
		while (agencies.hasNext())
		{
			Agency agency = (Agency) agencies.next();
			if(agency != null){
				AgencyResponseEvent agencyResponse = (AgencyResponseEvent) aCreator.create(agency);
				agencyResponse.setTopic(PDContactConstants.AGENCY_LISTITEM_EVENT_TOPIC);
				dispatch.postEvent(agencyResponse);
			}
		}*/
   }
   
   /**
    * @param event
    * @roseuid 425551F8009A
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 425551F8009C
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 425551F8009E
    */
   public void update(Object anObject) 
   {
    
   }
}
