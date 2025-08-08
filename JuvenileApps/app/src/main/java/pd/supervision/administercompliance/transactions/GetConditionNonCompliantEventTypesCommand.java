//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerconditioncompliance\\transactions\\GetNonCompliantEventsCommand.java

package pd.supervision.administercompliance.transactions;

import java.util.Iterator;

import messaging.administercompliance.reply.NonComplianceEventTypeCodeResponseEvent;
import messaging.administercompliance.GetConditionNonCompliantEventTypesEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.SupervisionOrderConditionConstants;
import pd.codetable.supervision.SupervisionCode;
import pd.supervision.supervisionoptions.Condition;


/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetConditionNonCompliantEventTypesCommand implements ICommand 
{
   
   /**
    * @roseuid 473B887E0371
    */
   public GetConditionNonCompliantEventTypesCommand() 
   {

   }
   
   /**
    * @param event
    * @roseuid 473B75560233
    */
   public void execute(IEvent event) 
   { 
   	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   	    GetConditionNonCompliantEventTypesEvent gEvent = (GetConditionNonCompliantEventTypesEvent) event;
	   
	    //Condition condition = Condition.find(gEvent.getConditionId());
	    Condition condition = (Condition) Condition.find(gEvent.getConditionId());
	    if(condition != null){
	    	Iterator iterator = condition.getSupervisionEventTypes().iterator();
		    while(iterator.hasNext()){
		    	SupervisionCode code = (SupervisionCode) iterator.next();
		    	NonComplianceEventTypeCodeResponseEvent resp = new NonComplianceEventTypeCodeResponseEvent();
		    	resp.setConditionId(gEvent.getConditionId());
		    	resp.setNonComplianceEventTypeCodeId(code.getCode());
		    	resp.setNonComplianceEventTypeCodeDesc(code.getDescription());
		    	dispatch.postEvent(resp);
			}
	    }	    
	    
    	NonComplianceEventTypeCodeResponseEvent resp = new NonComplianceEventTypeCodeResponseEvent();
    	resp.setConditionId(gEvent.getConditionId());
    	resp.setNonComplianceEventTypeCodeId(SupervisionOrderConditionConstants.NEWEVENTTYPECODE);
    	resp.setNonComplianceEventTypeCodeDesc(SupervisionOrderConditionConstants.NEWEVENTTYPECODEDESC);
    	dispatch.postEvent(resp);	    	
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
