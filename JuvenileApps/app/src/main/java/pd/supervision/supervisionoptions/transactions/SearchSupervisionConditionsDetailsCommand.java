//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionoptions\\transactions\\SearchSupervisionConditionsDetailsCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.supervisionoptions.SearchSupervisionConditionsDetailsEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.Factory.ISupervisionOptionsFactory;
import pd.supervision.Factory.SupervisionOptionsFactory;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionoptions.ConditionSupervisionOption;
import pd.supervision.supervisionoptions.ConditionView;

public class SearchSupervisionConditionsDetailsCommand implements ICommand
{

	/**
	 * @roseuid 43821E2C0186
	 */
	public SearchSupervisionConditionsDetailsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4381FA2D00BD
	 */
	public void execute(IEvent event)
	{
		SearchSupervisionConditionsDetailsEvent searchEvent = (SearchSupervisionConditionsDetailsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		// find conditions
		Iterator condIter = ConditionView.findAll(searchEvent);

		// get factory instance
		ISupervisionOptionsFactory supervisionOptionsFactory = new SupervisionOptionsFactory();
		while (condIter.hasNext())
		{
			ConditionView condView = (ConditionView) condIter.next();
			Condition cond = Condition.find(condView.getConditionOID());
			// get condition reply
			ConditionDetailResponseEvent reply = supervisionOptionsFactory.getConditionResponseEvent(cond);
			ConditionSupervisionOption option = ConditionSupervisionOption.find(condView.getSupervisionOptionOID());
			if (option != null)
			{
				// get collection of variable elements reply
				Collection variableElementResponses = supervisionOptionsFactory.getVariableElementResponses(option);
				// add variable element reply ro condition reply
				for (Iterator iter = variableElementResponses.iterator(); iter.hasNext();)
					reply.addVariableElement((VariableElementResponseEvent) iter.next());
				dispatch.postEvent(reply);
			}

		}

	}

	/**
	 * @param event
	 * @roseuid 4381FA2D00BF
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4381FA2D00CB
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 4381FA2D00CD
	 */
	public void update(Object anObject)
	{

	}

}
