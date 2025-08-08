//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GetAssociatedConditionsForDepartmentPolicyCommand.java

package pd.supervision.supervisionoptions.transactions;

import messaging.supervisionoptions.GetAssociatedConditionsForDepartmentPolicyEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.supervisionoptions.CommonSupervisionHelper;

public class GetAssociatedConditionsForDepartmentPolicyCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C43C0000
    */
   public GetAssociatedConditionsForDepartmentPolicyCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A7402F1
    */
   public void execute(IEvent event) 
   {
		GetAssociatedConditionsForDepartmentPolicyEvent reqEvent = (GetAssociatedConditionsForDepartmentPolicyEvent)event;
		// the entire logic is in helper to be used from other places
		CommonSupervisionHelper.postAssociatedConditionsForDepartmentPolicy(reqEvent.getPolicyId());
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A7402FD
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A7402FF
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A740301
    */
   public void update(Object anObject) 
   {
    
   }
   
}
