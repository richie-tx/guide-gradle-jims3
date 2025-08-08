//Source file: C:\\views\\dev\\app\\src\\pd\\codetable\\transactions\\GetCodeCommand.java

package pd.codetable.transactions;

import java.util.Iterator;

import naming.PDCodeTableConstants;
import pd.codetable.Code;
import messaging.codetable.GetCodeEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.ReadOnlyTransactional;

/**
 * @author dgibler
 *
 * Retrieves Code object
 */
public class GetCodeCommand implements ICommand, ReadOnlyTransactional
{

	/**
	@roseuid 411295740136
	 */
	public GetCodeCommand()
	{
	}

	/**
	@param event
	@roseuid 4112952C02DA
	 */
	public void execute(IEvent event)
	{
		GetCodeEvent codeEvent = (GetCodeEvent) event;

		Code code = Code.find(codeEvent.getCodeTableName(), codeEvent.getCode());

		if (code != null)
		{
			this.sendCode(code);
		}
	}

	private void sendCode(Code code)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		CodeResponseEvent codeResponse = new CodeResponseEvent();

		codeResponse.setTopic(PDCodeTableConstants.getCodeTableTopic(code.getCodeTableName()));
		codeResponse.setCodeId(code.getCodeId());
		codeResponse.setActiveDate(code.getActiveDate());
		codeResponse.setCode(code.getCode());
		codeResponse.setCodeTableName(code.getCodeTableName());
		codeResponse.setDescription(code.getDescription());
		codeResponse.setInactiveDate(code.getInactiveDate());
		codeResponse.setInactiveEffectiveDate(code.getInactiveEffectiveDate());
		codeResponse.setStatus(code.getStatus());

		dispatch.postEvent(codeResponse);
	}

	/**
	@param event
	@roseuid 4112952C02E3
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	@param event
	@roseuid 4112952C02E5
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	@param updateObject
	@roseuid 4112952C02ED
	 */
	public void update(Object updateObject)
	{

	}
}
