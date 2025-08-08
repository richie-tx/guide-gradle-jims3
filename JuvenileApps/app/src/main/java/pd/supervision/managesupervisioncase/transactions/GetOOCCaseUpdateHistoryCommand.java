//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\managesupervisioncase\\transactions\\GetOutOfCountyCaseCommand.java

package pd.supervision.managesupervisioncase.transactions;

import java.util.Collection;

import messaging.managesupervisioncase.GetOOCCaseUpdateHistoryEvent;
import messaging.managesupervisioncase.reply.OOCCaseUpdateHistoryEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import pd.supervision.Factory.OutOfCountyCaseFactory;
import pd.supervision.managesupervisioncase.OutOfCountyProbationCase;
;

public class GetOOCCaseUpdateHistoryCommand implements ICommand
{

	/**
	 * 
	 */
	public GetOOCCaseUpdateHistoryCommand()
	{

	}

	/**
	 * @param event
	 */
	public void execute(IEvent event)
	{
		GetOOCCaseUpdateHistoryEvent getHistoryEvent = (GetOOCCaseUpdateHistoryEvent)event;
		// lookup the OOC case.  
		OutOfCountyProbationCase oocCase = (OutOfCountyProbationCase)OutOfCountyCaseFactory.find(getHistoryEvent.getCaseNum(), getHistoryEvent.getCourtDivisionId());
		
		OOCCaseUpdateHistoryEvent historyEvent = new OOCCaseUpdateHistoryEvent();
		
		if (oocCase != null)
		{
			// get the update history
			Collection updateHistory = oocCase.getUpdateHistory(true);
			// add the data to the return event
			historyEvent.setUpdateHistory(updateHistory);
		}
			
		// post the response
		EventManager.getSharedInstance(EventManager.REPLY).postEvent(historyEvent);
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
