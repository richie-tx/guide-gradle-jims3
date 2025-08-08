/*
 * Created on Nov 1, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.supervisionoptions.transactions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import messaging.supervisionoptions.GetDeptPoliciesInUseEvent;
import messaging.supervisionoptions.reply.DepartmentPolicyInUseResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.supervisionoptions.DeptPolicySOCondition;
/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetDeptPoliciesInUseCommand implements ICommand 
{
	private static String DEPTPOLICY_ID = "deptPolicyId";
   /**
    * @roseuid 42F7C44002AF
    */
   public GetDeptPoliciesInUseCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F7997D0280
    */
   public void execute(IEvent event) 
   {
		GetDeptPoliciesInUseEvent requestEvent = (GetDeptPoliciesInUseEvent) event;
			String deptPolicyId = requestEvent.getPolicyId();
			if (deptPolicyId == null || deptPolicyId.equals(""))
			{
				return;
			}
			checkForDeptPolicyInUse(deptPolicyId);

   }
   
   private void checkForDeptPolicyInUse(String deptPolicyId)
	   {
		   Iterator iter = null;
		   //Check to see if court policy is used in a supervision order
		   iter = DeptPolicySOCondition.findAll(DEPTPOLICY_ID, deptPolicyId);
		   
		   if (iter != null && iter.hasNext())
		   {
		   		HashMap myCourtsMap=new HashMap();
		   		while(iter.hasNext()){
		   			DeptPolicySOCondition crtCond=(DeptPolicySOCondition)iter.next();
		   			if(crtCond.getCourtId()==null || crtCond.getCourtId().equals("")){
		   				// do nothing
		   			}
		   			else{
		   				myCourtsMap.put(crtCond.getCourtId(), crtCond.getCourtId());
		   			}
		   		}
			   this.postDepartmentPolicyInUseEvent(myCourtsMap.values());
		   }
	   }
   
   
	/**
		 * 
		 */
		private void postDepartmentPolicyInUseEvent(Collection courtsColl)
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			DepartmentPolicyInUseResponseEvent inUseEvent = new DepartmentPolicyInUseResponseEvent();
			if(courtsColl==null || courtsColl.size()<=0)
				inUseEvent.setCourtIds(null);
			else
				inUseEvent.setCourtIds(courtsColl);
			dispatch.postEvent(inUseEvent);
		}
   
   /**
    * @param event
    * @roseuid 42F7997D0282
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F7997D0284
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F7997D0286
    */
   public void update(Object anObject) 
   {
    
   }
   
}
