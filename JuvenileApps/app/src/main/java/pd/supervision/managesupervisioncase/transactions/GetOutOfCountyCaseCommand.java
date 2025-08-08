//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\managesupervisioncase\\transactions\\GetOutOfCountyCaseCommand.java

package pd.supervision.managesupervisioncase.transactions;

import messaging.managesupervisioncase.GetOutOfCountyCaseEvent;
import messaging.managesupervisioncase.reply.OutOfCountyCaseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import pd.supervision.Factory.OutOfCountyCaseFactory;
import pd.supervision.managesupervisioncase.OutOfCountyCase;

public class GetOutOfCountyCaseCommand implements ICommand
{

	/**
	 * @roseuid 4447C72F02A4
	 */
	public GetOutOfCountyCaseCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4447C36603E3
	 */
	public void execute(IEvent event)
	{
		GetOutOfCountyCaseEvent getCaseEvent = (GetOutOfCountyCaseEvent)event;
		// lookup the OOC case.  
		OutOfCountyCase oocCase = (OutOfCountyCase)OutOfCountyCaseFactory.find(getCaseEvent.getCaseNum(), getCaseEvent.getCourtDivisionId());
		
		OutOfCountyCaseEvent caseEvent = new OutOfCountyCaseEvent();
		
		if (oocCase != null)
		{
			// add the data to the return event
			oocCase.fillOutOfCountyCase(caseEvent, getCaseEvent.isReactivate());
			// set the Party data
			oocCase.getPartyInfo(caseEvent);
		}
		// post the response
		EventManager.getSharedInstance(EventManager.REPLY).postEvent(caseEvent);
	}

	/**
	 * @param event
	 * @roseuid 4447C3670004
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4447C3670006
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 4447C3670008
	 */
	public void update(Object updateObject)
	{

	}

}
