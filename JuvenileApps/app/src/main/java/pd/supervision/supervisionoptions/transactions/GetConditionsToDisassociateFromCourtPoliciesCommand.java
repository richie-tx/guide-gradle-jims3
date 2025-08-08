//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GetPoliciesToDisassociateCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.HashSet;
import java.util.Iterator;

import messaging.supervisionoptions.GetConditionsToDisassociateFromCourtPoliciesEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.supervisionoptions.CommonSupervisionHelper;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionoptions.CourtPolicy;

public class GetConditionsToDisassociateFromCourtPoliciesCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C446004E
    */
   public GetConditionsToDisassociateFromCourtPoliciesCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A3B036F
    */
   public void execute(IEvent event) 
   {
		GetConditionsToDisassociateFromCourtPoliciesEvent reqEvent = (GetConditionsToDisassociateFromCourtPoliciesEvent)event;

		CourtPolicy policy = CourtPolicy.find( reqEvent.getPolicyId() );
		if(policy == null){
			return;
		}
		
		HashSet disassociatedConditions = new HashSet();
		disassociatedConditions.addAll( policy.disassociationsForCourts( reqEvent.getCourtIds() ) );
		disassociatedConditions.addAll( policy.disassociationsForGroups( reqEvent.getGroupId() ) );
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
		// post resp events back
		// court policy
		Iterator condIter = disassociatedConditions.iterator();
		while( condIter.hasNext() )
		{
			Condition cond = (Condition)condIter.next();
			ConditionResponseEvent reply = CommonSupervisionHelper.getConditionResponseEvent(cond);
			dispatch.postEvent(reply);
		}
   }
   
   /**
    * @param event
    * @roseuid 42F79A3B037B
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A3B037D
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A3B037F
    */
   public void update(Object anObject) 
   {
    
   }

}
