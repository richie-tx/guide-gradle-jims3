//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GetAssociatedConditionsForCourtPolicyCommand.java

package pd.supervision.supervisionoptions.transactions;

import messaging.supervisionoptions.GetAssociatedConditionsForCourtPolicyEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.supervisionoptions.CommonSupervisionHelper;

public class GetAssociatedConditionsForCourtPolicyCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C43A038A
    */
   public GetAssociatedConditionsForCourtPolicyCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A7402B1
    */
   public void execute(IEvent event) 
   {
		GetAssociatedConditionsForCourtPolicyEvent reqEvent = (GetAssociatedConditionsForCourtPolicyEvent)event;
		// the entire logic is in helper to be used from other places
		CommonSupervisionHelper.postAssociatedConditionsForCourtPolicy(reqEvent.getPolicyId());

   }
   
   /**
    * @param event
    * @roseuid 42F79A7402B3
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A7402B5
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A7402C0
    */
   public void update(Object anObject) 
   {
    
   }
   
}
