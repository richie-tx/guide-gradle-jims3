//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefileSupervisionRulesCommand.java

package pd.juvenilecase.rules.transactions;

import java.util.Iterator;

import messaging.juvenilecase.reply.RuleResponseEvent;
import messaging.rules.GetJuvenileCasefileSupervisionRulesEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.factory.IJuvenileCaseworkResponseFactory;
import pd.juvenilecase.factory.JuvenileCaseworkResponseFactory;
import pd.juvenilecase.rules.RuleGroupConditionView;

public class GetJuvenileCasefileSupervisionRulesCommand implements ICommand
{

	/**
	 * @roseuid 43821BA301C5
	 */
	public GetJuvenileCasefileSupervisionRulesCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4381F46B005F
	 */
	public void execute(IEvent event)
	{
		GetJuvenileCasefileSupervisionRulesEvent requestEvent = (GetJuvenileCasefileSupervisionRulesEvent) event;
		String supervisionNum = requestEvent.getSupervisionNumber();
		if (supervisionNum != null && supervisionNum.length() > 0)
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			IJuvenileCaseworkResponseFactory responseFactory =  new JuvenileCaseworkResponseFactory();
			Iterator rulesIterator = RuleGroupConditionView.findAll("casefileId", supervisionNum);
			while(rulesIterator.hasNext())
			{
				RuleGroupConditionView ruleView = (RuleGroupConditionView) rulesIterator.next();
				RuleResponseEvent response = responseFactory.getRuleResponseEvent(ruleView);
				dispatch.postEvent(response);
				
			}
			
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
