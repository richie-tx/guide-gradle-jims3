//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GetCourtPoliciesInUseCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import messaging.supervisionoptions.GetCourtPoliciesInUseEvent;
import messaging.supervisionoptions.reply.CourtPolicyInUseResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.supervisionoptions.CourtPolicySOCondition;

public class GetCourtPoliciesInUseCommand implements ICommand 
{
	private static String COURTPOLICY_ID = "courtPolicyId";
   /**
    * @roseuid 42F7C44002AF
    */
   public GetCourtPoliciesInUseCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F7997D0280
    */
   public void execute(IEvent event) 
   {
		GetCourtPoliciesInUseEvent requestEvent = (GetCourtPoliciesInUseEvent) event;
			String courtPolicyId = requestEvent.getPolicyId();
			if (courtPolicyId == null || courtPolicyId.equals(""))
			{
				return;
			}
			checkForCourtPolicyInUse(courtPolicyId);

   }
   
   private void checkForCourtPolicyInUse(String courtPolicyId)
	   {
		   Iterator iter = null;
		   //Check to see if court policy is used in a supervision order
		   iter = CourtPolicySOCondition.findAll(COURTPOLICY_ID, courtPolicyId);
		   
		   if (iter != null && iter.hasNext())
		   {
		   		HashMap myCourtsMap=new HashMap();
		   		while(iter.hasNext()){
		   			CourtPolicySOCondition crtCond=(CourtPolicySOCondition)iter.next();
		   			if(crtCond.getCourtId()==null || crtCond.getCourtId().equals("")){
		   				// do nothing
		   			}
		   			else{
		   				myCourtsMap.put(crtCond.getCourtId(), crtCond.getCourtId());
		   			}
		   		}
			   this.postCourtPolicyInUseEvent(myCourtsMap.values());
		   }
	   }
   
   
	/**
		 * 
		 */
		private void postCourtPolicyInUseEvent(Collection courtsColl)
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			CourtPolicyInUseResponseEvent inUseEvent = new CourtPolicyInUseResponseEvent();
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
