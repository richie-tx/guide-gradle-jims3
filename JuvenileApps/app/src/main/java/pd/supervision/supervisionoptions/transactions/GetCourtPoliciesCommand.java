//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GetCourtPoliciesCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;

import messaging.supervisionoptions.GetCourtPoliciesEvent;
import messaging.supervisionoptions.reply.CourtPolicyResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.supervisionoptions.CommonSupervisionHelper;
import pd.supervision.supervisionoptions.CourtPolicy;

public class GetCourtPoliciesCommand implements ICommand
{
	/**
	 * @roseuid 42F7C43F031C
	 */
	public GetCourtPoliciesCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42F7997E001F
	 */
	public void execute(IEvent event)
	{
		GetCourtPoliciesEvent evt = (GetCourtPoliciesEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		

		Iterator policyIter = CourtPolicy.findAll(evt);
		while (policyIter.hasNext())
		{
			CourtPolicy policy = (CourtPolicy) policyIter.next();
			CourtPolicyResponseEvent reply = CommonSupervisionHelper.getCourtPolicyResponseEvent(policy);
			dispatch.postEvent(reply);
		}
	}

	/**
	 * @param event
	 * @roseuid 42F7997E0021
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42F7997E0023
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42F7997E0025
	 */
	public void update(Object anObject)
	{

	}

}
