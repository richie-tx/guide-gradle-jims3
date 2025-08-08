//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerconditioncompliance\\transactions\\GetNonCompliantEventsCommand.java

package pd.supervision.administercompliance.transactions;

import java.util.HashMap;
import java.util.Iterator;

import messaging.administercompliance.reply.NonComplianceEventResponseEvent;
import messaging.administercompliance.GetNonCompliantEventsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.SupervisionOrderConditionConstants;
import pd.codetable.supervision.SupervisionCode;
import pd.supervision.administercompliance.NonComplianceEvent;
import pd.supervision.supervisionoptions.Condition;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetNonCompliantEventsCommand implements ICommand 
{
   
   /**
    * @roseuid 473B887E0371
    */
   public GetNonCompliantEventsCommand() 
   {

   }
   
   /**
    * @param event
    * @roseuid 473B75560233
    */
   public void execute(IEvent event) 
   {
   	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   GetNonCompliantEventsEvent sEvent = (GetNonCompliantEventsEvent) event;
   	   
	   Condition condition = Condition.find(sEvent.getConditionId());
	   Iterator iterSupervisionTypes = condition.getSupervisionEventTypes().iterator();
	   HashMap map = new HashMap();
	   while(iterSupervisionTypes.hasNext()){
	       SupervisionCode code = (SupervisionCode) iterSupervisionTypes.next();
	       map.put(code.getCode(), code.getDescription());
	   }

	   Iterator iterator = NonComplianceEvent.findAllByNumericParam(SupervisionOrderConditionConstants.SUPERVISION_ORDER_CONDITION_ID, sEvent.getSprOrderConditionId());
   	   while(iterator.hasNext()){
   	       NonComplianceEvent nce = (NonComplianceEvent) iterator.next();
   	       if(nce != null){
   	       	   NonComplianceEventResponseEvent resp = nce.getResponseEvent(map);
   	       	   dispatch.postEvent(resp);
   	       }
   	   }
   }
   
   /**
    * @param event
    * @roseuid 473B75560240
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 473B75560242
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 473B75560244
    */
   public void update(Object anObject) 
   {
    
   }
}
