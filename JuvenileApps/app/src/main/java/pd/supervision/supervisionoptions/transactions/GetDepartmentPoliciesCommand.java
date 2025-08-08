//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GetDepartmentPoliciesCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;

import messaging.supervisionoptions.GetDepartmentPoliciesEvent;
import messaging.supervisionoptions.reply.DepartmentPolicyResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.supervisionoptions.AgencyPolicy;
import pd.supervision.supervisionoptions.CommonSupervisionHelper;

public class GetDepartmentPoliciesCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C4420213
    */
   public GetDepartmentPoliciesCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A0900FC
    */
   public void execute(IEvent event) 
   {
		GetDepartmentPoliciesEvent evt = (GetDepartmentPoliciesEvent)event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			
		Iterator policyIter = AgencyPolicy.findAll( evt );
		while ( policyIter.hasNext() )
		{
			AgencyPolicy policy = (AgencyPolicy)policyIter.next();
			if (evt.getStatus() != null) {
				if ((evt.getStatus().equals(policy.getStatus()))
						|| evt.getStatus().equals("")) {
					DepartmentPolicyResponseEvent reply = CommonSupervisionHelper
							.getDepartmentPolicyResponseEvent(policy);
					dispatch.postEvent(reply);
				}
			} else {
				DepartmentPolicyResponseEvent reply = CommonSupervisionHelper
						.getDepartmentPolicyResponseEvent(policy);
				dispatch.postEvent(reply);
			}
		}
   }
   
   /**
    * @param event
    * @roseuid 42F79A0900FE
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A090109
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A09010B
    */
   public void update(Object anObject) 
   {
    
   }
   
}
