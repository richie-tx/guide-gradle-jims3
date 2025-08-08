//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administercontract\\transactions\\GetContractServicesCommand.java

package pd.supervision.administerserviceprovider.administercontract.transactions;

import java.util.Iterator;

import naming.PDSecurityConstants;
import naming.ResponseLocatorConstants;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.exception.ReflectionException;
import pd.supervision.administerserviceprovider.administercontract.ContractServiceContractValue;
import pd.supervision.administerserviceprovider.administercontract.ServiceServiceContractValueView;
import messaging.administercontract.GetContractServicesEvent;
import messaging.administerserviceprovider.administercontract.reply.ContractServiceResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class GetContractServicesCommand implements ICommand 
{
   
   /**
    * @roseuid 451C4F7301EF
    */
   public GetContractServicesCommand() 
   {

   }
   
   /**
    * @param event
    * @roseuid 451C453B03DE
    */
   public void execute(IEvent event) 
   {
       IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   GetContractServicesEvent contractServicesEvent = (GetContractServicesEvent) event;
		
	   ResponseContextFactory respFac = new ResponseContextFactory();
	   ResponseCreator aCreator =  null;
	   try {
		   aCreator = (ResponseCreator) respFac.lookup(ResponseLocatorConstants.SERVICE_SERVICE_CONTRACT_VALUE_RESPONSE_LOCATOR);
	   } catch (ReflectionException e) {
		   e.printStackTrace();
	   }
		
	   Iterator iter = ServiceServiceContractValueView.findAll(contractServicesEvent);
	   while(iter.hasNext()){
	   	    ServiceServiceContractValueView value = (ServiceServiceContractValueView) iter.next();
			if(value != null){
				ServiceResponseEvent resp = (ServiceResponseEvent) aCreator.create(value);
				dispatch.postEvent(resp);
			}
		}   
   }
   
   /**
    * @param event
    * @roseuid 451C453B03E0
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 451C453B03E2
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 451C453C0006
    */
   public void update(Object anObject) 
   {
    
   }
}
