//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\ValidateSupervisionConditionCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;

import pd.supervision.supervisionoptions.Condition;

import messaging.supervisionoptions.ValidateSupervisionConditionEvent;
import messaging.supervisionoptions.reply.DuplicationNameErrorEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class ValidateSupervisionConditionCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C4520196
    */
   public ValidateSupervisionConditionCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A3B0040
    */
   public void execute(IEvent event) 
   {
		ValidateSupervisionConditionEvent reqEvent = (ValidateSupervisionConditionEvent)event;
		// OID is of type numeric, hence set some numeric value if it is null
		if(reqEvent.getConditionId() == null || reqEvent.getConditionId().equals("")){
			reqEvent.setConditionId("0");
		}
		// get Condition by name and agencyId		
		Iterator policyIter = Condition.findAll( reqEvent );
		// post condition response event back if found
		if(policyIter.hasNext())
		{
			Condition condition = (Condition)policyIter.next();
			// post duplication error event
			DuplicationNameErrorEvent errorEvent = new DuplicationNameErrorEvent();
			errorEvent.setName(condition.getName());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(errorEvent);
				    	
		}
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A3B0042
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A3B005E
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A3B0060
    */
   public void update(Object anObject) 
   {
    
   }
   
}
