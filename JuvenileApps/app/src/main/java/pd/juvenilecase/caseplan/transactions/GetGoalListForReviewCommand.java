//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\caseplan\\transactions\\GetCaseplanDetailsCommand.java

package pd.juvenilecase.caseplan.transactions;

import java.util.Iterator;

import pd.codetable.Code;
import pd.juvenilecase.caseplan.CasePlan;
import pd.juvenilecase.caseplan.CaseplanHelper;
import pd.juvenilecase.caseplan.Goal;
import pd.juvenilecase.caseplan.Placement;
import messaging.caseplan.GetCaseplanDetailsEvent;
import messaging.caseplan.GetGoalListForReviewEvent;
import messaging.caseplan.reply.CaseplanDetailsResponseEvent;
import messaging.caseplan.reply.GoalDetailsResponseEvent;
import messaging.caseplan.reply.GoalListResponseEvent;
import messaging.caseplan.reply.PlacementInfoResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetGoalListForReviewCommand implements ICommand 
{
   
   /**
    * @roseuid 4533B81001A8
    */
   public GetGoalListForReviewCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45119A64015D
    */
   public void execute(IEvent event) 
   {
   		GetGoalListForReviewEvent request = (GetGoalListForReviewEvent)event;
   		String caseplanID = request.getCaseplanID();
   		CasePlan cp = CasePlan.find(caseplanID);
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		// get goals 
   		Iterator ite = cp.getTheGoal().iterator();
   		while(ite.hasNext()) {
   			Goal goal = (Goal)ite.next();
   			GoalDetailsResponseEvent goalResponse = CaseplanHelper.getGoalDetailsResponseEventFromGoal(goal);
   			dispatch.postEvent(goalResponse);
   		}
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
