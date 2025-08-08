//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefileSupervisionRulesCommand.java

package pd.juvenilecase.rules.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.caseplan.reply.GoalListResponseEvent;
import messaging.juvenilecase.reply.RuleDetailResponseEvent;
import messaging.rules.GetJuvenileCasefileSupervisionRuleDetailsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.caseplan.CaseplanHelper;
import pd.juvenilecase.caseplan.Goal;
import pd.juvenilecase.factory.IJuvenileCaseworkResponseFactory;
import pd.juvenilecase.factory.JuvenileCaseworkResponseFactory;
import pd.juvenilecase.rules.JuvenileCaseSupervisionRule;
import pd.juvenilecase.rules.RuleGroupConditionView;

public class GetJuvenileCasefileSupervisionRuleDetailsCommand implements ICommand
{

	/**
	 * @roseuid 43821BA301C5
	 */
	public GetJuvenileCasefileSupervisionRuleDetailsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4381F46B005F
	 */
	public void execute(IEvent event)
	{
		IJuvenileCaseworkResponseFactory responseFactory =  new JuvenileCaseworkResponseFactory();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		RuleDetailResponseEvent response = new RuleDetailResponseEvent();
		
		GetJuvenileCasefileSupervisionRuleDetailsEvent requestEvent = (GetJuvenileCasefileSupervisionRuleDetailsEvent) event;

		String ruleId = requestEvent.getRuleId();
		RuleGroupConditionView ruleView = RuleGroupConditionView.find( ruleId );

		if(ruleView == null)
		{
			dispatch.postEvent(response);
			return;
		}
		
		response = responseFactory.getRuleDetailResponseEvent(ruleView);
		dispatch.postEvent(response);
		
		// send the goals
		JuvenileCaseSupervisionRule rule = JuvenileCaseSupervisionRule.find(ruleId);
		Collection goals = rule.getGoals();
		Iterator ite = goals.iterator();
		while(ite.hasNext()) {
			Goal goal = (Goal) ite.next();
			GoalListResponseEvent gEvt = CaseplanHelper.getGoalListResponseEventFromGoal(goal);
			dispatch.postEvent(gEvt);
		}
		
		
	}

	/**
	 * @param event
	 * @roseuid 4381F46B006E
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4381F46B0070
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 4381F46B0072
	 */
	public void update(Object anObject)
	{

	}

}
