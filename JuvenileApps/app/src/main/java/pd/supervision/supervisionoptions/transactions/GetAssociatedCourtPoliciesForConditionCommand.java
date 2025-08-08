//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GetAssociatedPoliciesForConditionCommand.java

package pd.supervision.supervisionoptions.transactions;

import messaging.supervisionoptions.GetAssociatedCourtPoliciesForConditionEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.supervisionoptions.CommonSupervisionHelper;

public class GetAssociatedCourtPoliciesForConditionCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C43E034B
    */
   public GetAssociatedCourtPoliciesForConditionCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A75034B
    */
   public void execute(IEvent event) 
   {
		GetAssociatedCourtPoliciesForConditionEvent reqEvent = (GetAssociatedCourtPoliciesForConditionEvent)event;
		// the entire logic is in helper to be used from other places
		CommonSupervisionHelper.postAssociatedCourtPolicies(reqEvent.getConditionId());
   }
   
   /**
    * @param event
    * @roseuid 42F79A75034D
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A75034F
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A750351
    */
   public void update(Object anObject) 
   {
    
   }
   
}
