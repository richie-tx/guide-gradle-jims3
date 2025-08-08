//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\caseplan\\transactions\\GetGoalDetailsCommand.java

package pd.juvenilecase.caseplan.transactions;

import java.util.ArrayList;
import java.util.Iterator;

import pd.juvenilecase.caseplan.CaseplanHelper;
import pd.juvenilecase.caseplan.Goal;
import pd.juvenilecase.caseplan.GoalRulesJuvenileCaseSupervisionRule;
import pd.juvenilecase.caseplan.PersonResponsible;
import pd.juvenilecase.factory.IJuvenileCaseworkResponseFactory;
import pd.juvenilecase.factory.JuvenileCaseworkResponseFactory;
import pd.juvenilecase.rules.JuvenileCaseSupervisionRule;
import pd.juvenilecase.rules.RuleGroupConditionView;
import messaging.caseplan.GetGoalDetailsEvent;
import messaging.caseplan.reply.GoalDetailsResponseEvent;
import messaging.juvenilecase.reply.RuleResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetGoalDetailsCommand implements ICommand 
{
   
   /**
    * @roseuid 4533B81502D2
    */
   public GetGoalDetailsCommand() 
   {
   		
    
   }
   
   /**
    * @param event
    * @roseuid 45119A6401F3
    */
   public void execute(IEvent event) 
   {
   		GetGoalDetailsEvent request = (GetGoalDetailsEvent)event;
   		String goalID = request.getGoalID();
   		Goal goal = Goal.find(goalID);
   		GoalDetailsResponseEvent gEvent = CaseplanHelper.getGoalDetailsResponseEventFromGoal(goal);
   		
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		dispatch.postEvent(gEvent);
   		
   		// get the rules
   		Iterator ruleIte = goal.getRules().iterator();
   		IJuvenileCaseworkResponseFactory responseFactory =  new JuvenileCaseworkResponseFactory();
   		while(ruleIte.hasNext()) {
   			JuvenileCaseSupervisionRule supRule = (JuvenileCaseSupervisionRule) ruleIte.next(); 
			RuleGroupConditionView ruleView = RuleGroupConditionView.find(supRule.getOID().toString());
			if(ruleView != null) {
				RuleResponseEvent response = responseFactory.getRuleResponseEvent(ruleView);
				dispatch.postEvent(response);
			}
   		}
   		
   }
   
   /**
    * @param event
    * @roseuid 45119A6401FB
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45119A6401FD
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45119A640205
    */
   public void update(Object anObject) 
   {
    
   }

}
