//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\ValidateCourtPolicyCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;

import pd.supervision.supervisionoptions.CourtPolicy;

import messaging.supervisionoptions.ValidateCourtPolicyEvent;
import messaging.supervisionoptions.reply.DuplicationNameErrorEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class ValidateCourtPolicyCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C4500271
    */
   public ValidateCourtPolicyCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F7997C038C
    */
   public void execute(IEvent event) 
   {
		ValidateCourtPolicyEvent reqEvent = (ValidateCourtPolicyEvent)event;
		// OID is of type numeric, hence set some numeric value if it is null
		if(reqEvent.getPolicyId() == null || reqEvent.getPolicyId().equals("")){
			reqEvent.setPolicyId("0");
		}
		// get Condition by name and agencyId		
		Iterator policyIter = CourtPolicy.findAll( reqEvent );
		// post condition response event back if found
		if(policyIter.hasNext())
		{
			CourtPolicy policy = (CourtPolicy)policyIter.next();

			// post duplication error event
			DuplicationNameErrorEvent errorEvent = new DuplicationNameErrorEvent();
			errorEvent.setName(policy.getName());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(errorEvent);
		}
	    
   }
   
   /**
    * @param event
    * @roseuid 42F7997C038E
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F7997C039A
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F7997C03AA
    */
   public void update(Object anObject) 
   {
    
   }
   
}
