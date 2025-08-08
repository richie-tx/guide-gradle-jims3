//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\managesupervisioncase\\transactions\\ValidateOutOfCountyCaseCommand.java

package pd.supervision.managesupervisioncase.transactions;

import messaging.managesupervisioncase.ValidateOutOfCountyCaseEvent;
import messaging.managesupervisioncase.reply.ValidateResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import pd.supervision.managesupervisioncase.OutOfCountyProbationCase;

public class ValidateOutOfCountyCaseCommand implements ICommand
{

	/**
	 * @roseuid 444525F0010F
	 */
	public ValidateOutOfCountyCaseCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 443D14B103D5
	 */
	public void execute(IEvent event)
	{
		ValidateOutOfCountyCaseEvent input = (ValidateOutOfCountyCaseEvent) event;
		String cJISNum = input.getCJISNum();

		ValidateResponseEvent response = new ValidateResponseEvent();

		if (cJISNum != null && !cJISNum.equals(""))
		{
			String message = OutOfCountyProbationCase.validateCJISNumber(cJISNum, input.getCaseNum());
			response.setValid(message == null);
			response.setMessage(message);
		}

		// return the results of the validation
		EventManager.getSharedInstance(EventManager.REPLY).postEvent(response);
	}

	/**
	 * @param event
	 * @roseuid 443D14B103D7
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 443D14B103DF
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 444525F00123
	 */
	public void update(Object updateObject)
	{

	}

}
