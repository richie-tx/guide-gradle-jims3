//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionorder\\transactions\\GetImpactedOrdersCommand.java

package pd.supervision.supervisionorder.transactions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import pd.supervision.supervisionorder.ImpactedOrderHelper;
import pd.supervision.supervisionorder.SupervisionOrder;
import messaging.supervisionorder.GetImpactedOrdersEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *
 */
public class GetImpactedOrdersCommand implements ICommand 
{
   
   /**
    * @roseuid 43B2E77C03C8
    */
   public GetImpactedOrdersCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 43B2B6ED0244
    */
   public void execute(IEvent event) 
   {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetImpactedOrdersEvent reqEvent = (GetImpactedOrdersEvent)event;
		Map impactedOrderMap = new HashMap(); 
		Map likeCondMap = new HashMap();
		ImpactedOrderHelper.getImpactedOrders(reqEvent.getOrderId(), impactedOrderMap, likeCondMap);
		
		Set impOrderSet = impactedOrderMap.keySet();
		for(Iterator iter = impOrderSet.iterator(); iter.hasNext(); ){
			String orderId = (String)iter.next();   	 
			SupervisionOrder order = SupervisionOrder.find(orderId);
			SupervisionOrderDetailResponseEvent respEvent = ImpactedOrderHelper.getImpactedOrderResponseEvent(order, (Collection)impactedOrderMap.get(orderId), 
																												likeCondMap);
			dispatch.postEvent(respEvent);
		}
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
