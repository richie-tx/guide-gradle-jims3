//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionorder\\transactions\\GetImpactedOrdersCommand.java

package pd.supervision.supervisionorder.transactions;

import java.util.Iterator;

import messaging.supervisionorder.GetCurrentSupervisionOrderStartEndDatesEvent;
import messaging.supervisionorder.GetMostCurrentSupervisionOrderForCaseEvent;
import messaging.supervisionorder.reply.SupervisionOrderStartEndDatesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;
import pd.supervision.supervisionorder.SupervisionOrder;

/**
 * 
 *
 */
public class GetCurrentSupervisionOrderStartEndDatesCommand implements ICommand 
{
   
   /**
    * @roseuid 43B2E77C03C8
    */
   public GetCurrentSupervisionOrderStartEndDatesCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 43B2B6ED0244
    */
   public void execute(IEvent event) 
   {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetCurrentSupervisionOrderStartEndDatesEvent reqEvent = (GetCurrentSupervisionOrderStartEndDatesEvent)event;
		
		SupervisionOrder activeOrder=null;		
		
		GetMostCurrentSupervisionOrderForCaseEvent theEvent = new GetMostCurrentSupervisionOrderForCaseEvent();
		theEvent.setAgencyId(reqEvent.getAgencyId());
		theEvent.setCaseId(reqEvent.getCaseId());
		theEvent.setOrderStatusId(PDCodeTableConstants.STATUS_ACTIVE_ID);
		activeOrder = this.getSupervisionOrder(theEvent);
		
		if (activeOrder != null) {
			SupervisionOrderStartEndDatesResponseEvent respEvent = new SupervisionOrderStartEndDatesResponseEvent();
			respEvent.setCaseSupervisionBeginDate(activeOrder.getCaseSupervisionBeginDate());
			respEvent.setCaseSupervisionEndDate(activeOrder.getCaseSupervisionEndDate());
			dispatch.postEvent(respEvent);	
		
		}
		
		
   }
   
   
   private SupervisionOrder getSupervisionOrder(IEvent getOrdersEvent) {
		Iterator iter = SupervisionOrder.findAll(getOrdersEvent);
		SupervisionOrder supervisionOrder = null;
		if (iter != null && iter.hasNext()) {
			supervisionOrder = (SupervisionOrder) iter.next();
		}
		return supervisionOrder;
	}

   /**
    * @param event
    * @roseuid 43B2B6ED0246
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 43B2B6ED0252
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param updateObject
    * @roseuid 43B2E77C03D8
    */
   public void update(Object updateObject) 
   {
    
   }
}
