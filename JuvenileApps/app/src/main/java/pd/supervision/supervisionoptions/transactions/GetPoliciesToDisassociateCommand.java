//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GetPoliciesToDisassociateCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import messaging.supervisionoptions.GetPoliciesToDisassociateEvent;
import messaging.supervisionoptions.reply.CourtPolicyResponseEvent;
import messaging.supervisionoptions.reply.DepartmentPolicyResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.supervisionoptions.AgencyPolicy;
import pd.supervision.supervisionoptions.CommonSupervisionHelper;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionoptions.CourtPolicy;

public class GetPoliciesToDisassociateCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C446004E
    */
   public GetPoliciesToDisassociateCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A3B036F
    */
   public void execute(IEvent event) 
   {
		GetPoliciesToDisassociateEvent reqEvent = (GetPoliciesToDisassociateEvent)event;

		// get Condition
		// existing condition
		Condition cond = Condition.find( reqEvent.getConditionId() );
		// check for errors
		if(cond == null){
			return;
		}
		
		Set courtPolicies = new HashSet();
		Set deptPolicies = new HashSet();
		
		courtPolicies.addAll( cond.disassociatedCourtPoliciesForCourts(reqEvent.getCourtIds()) );
		courtPolicies.addAll( cond.disassociatedCourtPoliciesForGroups(reqEvent.getGroupId()) );
		
		deptPolicies.addAll( cond.disassociatedDepartmentPoliciesForCourts(reqEvent.getCourtIds()) );
		deptPolicies.addAll( cond.disassociatedDepartmentPoliciesForGroups(reqEvent.getGroupId()) );
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
		// post resp events back
		// court policy
		Iterator crtPolIter = courtPolicies.iterator();
		while(crtPolIter.hasNext()){
			CourtPolicy crtPol = (CourtPolicy)crtPolIter.next();
			CourtPolicyResponseEvent reply = CommonSupervisionHelper.getCourtPolicyResponseEvent(crtPol);
			dispatch.postEvent(reply);
		}
		
		// dept policy
		Iterator deptPolIter = deptPolicies.iterator();
		while(deptPolIter.hasNext()){
			AgencyPolicy deptPol = (AgencyPolicy)deptPolIter.next();
			DepartmentPolicyResponseEvent reply = CommonSupervisionHelper.getDepartmentPolicyResponseEvent(deptPol);
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
