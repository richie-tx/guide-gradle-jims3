//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\ValidateDepartentPolicyCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;

import pd.supervision.supervisionoptions.AgencyPolicy;
import messaging.supervisionoptions.ValidateDepartmentPolicyEvent;
import messaging.supervisionoptions.reply.DuplicationNameErrorEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class ValidateDepartmentPolicyCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C4510203
    */
   public ValidateDepartmentPolicyCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A080292
    */
   public void execute(IEvent event) 
   {
		ValidateDepartmentPolicyEvent reqEvent = (ValidateDepartmentPolicyEvent)event;
		// OID is of type numeric, hence set some numeric value if it is null
		if(reqEvent.getPolicyId() == null || reqEvent.getPolicyId().equals("")){
			reqEvent.setPolicyId("0");
		}
		// get Condition by name and agencyId		
		Iterator policyIter = AgencyPolicy.findAll( reqEvent );
		// post condition response event back if found
		if(policyIter.hasNext())
		{
			AgencyPolicy policy = (AgencyPolicy)policyIter.next();
			// post duplication error event
			DuplicationNameErrorEvent errorEvent = new DuplicationNameErrorEvent();
			errorEvent.setName(policy.getName());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(errorEvent);
		}
		    
   }
   
   /**
    * @param event
    * @roseuid 42F79A080294
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A0802A0
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A0802A2
    */
   public void update(Object anObject) 
   {
    
   }
   
}
