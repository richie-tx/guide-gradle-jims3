//Source file: C:\\views\\Security\\app\\src\\pd\\contact\\agency\\transactions\\GetAgencySecurityInfoCommand.java

package pd.security.inquiries.transactions;

import naming.ResponseLocatorConstants;
import messaging.inquiries.GetAgencySecurityInfoEvent;
import messaging.security.inquiries.reply.AgencySecurityInfoResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.contact.agency.Agency;
import pd.exception.ReflectionException;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetAgencySecurityInfoCommand implements ICommand 
{
   
   /**
    * @roseuid 44E9D33E0366
    */
   public GetAgencySecurityInfoCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 44E5FE1E01E4
    */
   public void execute(IEvent event) 
   {
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		GetAgencySecurityInfoEvent getAgencySecurityInfoEvent = (GetAgencySecurityInfoEvent) event;
		Agency agency = Agency.find(getAgencySecurityInfoEvent.getAgencyId());
		
		if(agency != null){
			ResponseContextFactory respFac = new ResponseContextFactory();
			ResponseCreator aCreator =  null;
	   		try {
				aCreator = (ResponseCreator) respFac.lookup(ResponseLocatorConstants.AGENCY_SECURITY_RESPONSE_LOCATOR);
			} catch (ReflectionException e) {
				e.printStackTrace();
			}
			AgencySecurityInfoResponseEvent resp = (AgencySecurityInfoResponseEvent) aCreator.create(agency);
			dispatch.postEvent(resp);
		}
   }
   
   /**
    * @param event
    * @roseuid 44E5FE1E01E6
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 44E5FE1E01E8
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 44E5FE1E01F3
    */
   public void update(Object anObject) 
   {
    
   }
}


