package pd.codetable.transactions;

import messaging.codetable.DeleteCodeEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import pd.codetable.Code;
import pd.codetable.CodeTable;

/**
 * @author dgibler
 *
 * Deletes a Code
 */
public class DeleteCodeCommand implements ICommand
{

	/**
	@roseuid 4117989B03B4
	* Constructor
	 */
	public DeleteCodeCommand()
	{
		super();
	}

	/**
	@param event
	@roseuid 4117983903D2
	 */
	public void execute(IEvent event)
	{

		DeleteCodeEvent deleteEvent = (DeleteCodeEvent) event;

		String codeId = deleteEvent.getCodeId();

		if (codeId == null)
		{
			ReturnException returnEvent = new ReturnException();					
			EventManager.getSharedInstance(EventManager.REPLY).postEvent(returnEvent);
		}

		CodeTable codeTable = CodeTable.find(deleteEvent.getCodeTableName());

		Code code = Code.find(codeId);
		
		codeTable.removeCode(code);
	}

	/**
	@param event
	@roseuid 4117983903D4
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	@param event
	@roseuid 4117983903D6
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	@param anObject
	@roseuid 4117983903D8
	 */
	public void update(Object anObject)
	{

	}

}
