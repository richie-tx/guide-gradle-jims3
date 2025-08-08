//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\caseplan\\transactions\\GetGoalDetailsCommand.java

package pd.juvenilecase.caseplan.transactions;

import java.util.Iterator;

import messaging.caseplan.GetGoalRuleDetailsByCaseplanIdEvent;
import messaging.caseplan.reply.GoalRuleDetailsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.caseplan.GoalRuleDetails;

public class GetGoalRuleDetailsByCaseplanIdCommand implements ICommand 
{
   
   /**
    * @roseuid 4533B81502D2
    */
   public GetGoalRuleDetailsByCaseplanIdCommand() 
   {
   		
    
   }
   
   /**
    * @param event
    * @roseuid 45119A6401F3
    */
   public void execute(IEvent event) 
   {
   		GetGoalRuleDetailsByCaseplanIdEvent request = (GetGoalRuleDetailsByCaseplanIdEvent)event;
   		//String caseplanId = request.getCaseplanId();
   		Iterator iter = GoalRuleDetails.findAll(request);
   		
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		
   		while(iter.hasNext()) {
   			GoalRuleDetails goalRuleDetailsEntity = (GoalRuleDetails)iter.next();
   			
   			GoalRuleDetailsResponseEvent respEvt = 
   				GoalRuleDetails.getRespEvt(goalRuleDetailsEntity);
   			dispatch.postEvent(respEvt);
   			
   		}
   		
   		
   		
		
   		/*
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
			RuleResponseEvent response = responseFactory.getRuleResponseEvent(ruleView);
			dispatch.postEvent(response);
   		}*/
   		
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
