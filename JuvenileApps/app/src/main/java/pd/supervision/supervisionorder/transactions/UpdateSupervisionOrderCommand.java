//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionorder\\transactions\\UpdateSupervisionOrderCommand.java

package pd.supervision.supervisionorder.transactions;

import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderHelper;
import messaging.supervisionorder.UpdateSupervisionOrderEvent;
import messaging.supervisionorder.reply.OrderCreateErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UpdateSupervisionOrderCommand implements ICommand 
{
   
   /**
    * @roseuid 43B2E787006D
    */
   public UpdateSupervisionOrderCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 438F22CB022B
    */
   public void execute(IEvent event) throws CloneNotSupportedException 
   {
		UpdateSupervisionOrderEvent reqEvent = (UpdateSupervisionOrderEvent)event;
		
		SupervisionOrder supervisionOrder = null;
		// performance changes
		if(reqEvent.isAddRemoveCondition()){
			supervisionOrder = SupervisionOrder.addRemoveConditions(reqEvent);
		}else{
			supervisionOrder = SupervisionOrder.update(reqEvent);
			if(supervisionOrder == null){
			    // error situation for the case when there is already a valid order version existing
			    OrderCreateErrorResponseEvent orderCreateErrorResponseEvent = new OrderCreateErrorResponseEvent();
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
				dispatch.postEvent(orderCreateErrorResponseEvent);
			}
		}

		if(supervisionOrder != null){
			// send CaseOrderResponseEvent 
			SupervisionOrderHelper.postOrderRespEvent(supervisionOrder);
		}    
   }
   
   /**
    * @param event
    * @roseuid 438F22CB022D
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 438F22CB022F
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param updateObject
    * @roseuid 43B2E787007D
    */
   public void update(Object updateObject) 
   {
    
   }
}
