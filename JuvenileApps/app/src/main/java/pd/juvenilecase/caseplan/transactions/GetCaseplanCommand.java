//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\caseplan\\transactions\\GetCaseplanCommand.java

package pd.juvenilecase.caseplan.transactions;

import java.util.Iterator;

import pd.juvenilecase.caseplan.CasePlan;
import pd.juvenilecase.caseplan.CaseplanHelper;
import pd.juvenilecase.caseplan.Goal;
import pd.juvenilecase.caseplan.Placement;
import messaging.caseplan.GetCaseplanEvent;
import messaging.caseplan.reply.CaseplanDetailsResponseEvent;
import messaging.caseplan.reply.GoalListResponseEvent;
import messaging.caseplan.reply.PlacementInfoResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetCaseplanCommand implements ICommand 
{
   
   /**
    * @roseuid 4533B81001A8
    */
   public GetCaseplanCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45119A64015D
    */
   public void execute(IEvent event) 
   {
   		GetCaseplanEvent request = (GetCaseplanEvent)event;   		
   		String caseplanId = request.getCaseplanId();
   		CaseplanDetailsResponseEvent cpResponse = new CaseplanDetailsResponseEvent();
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);   		
   		CasePlan cp=CasePlan.find(caseplanId);   		
   		if(cp == null) {
   			cpResponse.setCaseplanID(null);
   			dispatch.postEvent(cpResponse);
   			return;
   		}
   		cpResponse.setCaseplanID(cp.getOID().toString());
   		cpResponse.setReviewDate(cp.getReviewDate());
   		cpResponse.setCreateDate(cp.getCreateTimestamp());
   		cpResponse.setStatusId(cp.getStatusId());   		
   		dispatch.postEvent(cpResponse);
   		
   		// get goals and create goallistresponsevent
   		Iterator ite = cp.getTheGoal().iterator();
   		while(ite.hasNext()) {
   			Goal goal = (Goal)ite.next();
   			GoalListResponseEvent goalResponse = CaseplanHelper.getGoalListResponseEventFromGoal(goal);
   			dispatch.postEvent(goalResponse);
   		}
   		
   		// send back the placement information if it is for normal details
   	//	if(!request.isForClmReview()) {
	   		// get the placement info
	   		Placement placement = cp.getThePlacement();
	   		if(placement != null) {
	   			PlacementInfoResponseEvent pResponse = CaseplanHelper.getPlacementInfoResponseFromPlacement(placement);
	   			dispatch.postEvent(pResponse);
	   		}
   	//	} 
   }
   
   /**
    * @param event
    * @roseuid 45119A64015F
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45119A640166
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45119A64016F
    */
   public void update(Object anObject) 
   {
    
   }
   
}
