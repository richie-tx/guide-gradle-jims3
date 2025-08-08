//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GetAssociatedDepartmentPoliciesForConditionCommand.java

package pd.supervision.supervisionoptions.transactions;

import messaging.supervisionoptions.GetAssociatedDepartmentPoliciesForConditionEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.supervisionoptions.CommonSupervisionHelper;

public class GetAssociatedDepartmentPoliciesForConditionCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C43D038A
    */
   public GetAssociatedDepartmentPoliciesForConditionCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A750198
    */
   public void execute(IEvent event) 
   {
		GetAssociatedDepartmentPoliciesForConditionEvent reqEvent = (GetAssociatedDepartmentPoliciesForConditionEvent)event;
		
		// the entire logic is in helper to be used from other places
		CommonSupervisionHelper.postAssociatedAgencyPolicies(reqEvent.getConditionId());
   }
   
   /**
    * @param event
    * @roseuid 42F79A75019A
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A7501A6
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A7501A8
    */
   public void update(Object anObject) 
   {
    
   }
   
}
