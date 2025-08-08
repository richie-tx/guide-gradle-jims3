//Source file: C:\\views\\dev\\app\\src\\pd\\codetable\\transactions\\GetCodeHistoryCommand.java

package pd.codetable.transactions;

import java.util.Collection;
import java.util.Iterator;
import naming.PDCodeTableConstants;
import pd.codetable.Code;
import pd.codetable.CodeHistory;
import messaging.codetable.GetCodeHistoryEvent;
import messaging.codetable.reply.CodeHistoryResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *
 * Retrieves CodeHistory object
 */
public class GetCodeHistoryCommand implements ICommand
{

	/**
	@roseuid 40CF4CB100B7
	 */
	public GetCodeHistoryCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 40CF4AF600D0
	 * @precondition ((GetCodeHistoryEvent)event).getCodeId() != null)
	 * @postcondition (eventHistoryReply.getCode() != null)
	 */
	public void execute(IEvent event)
	{
		GetCodeHistoryEvent getEvent = (GetCodeHistoryEvent) event;

		Code code = Code.find(getEvent.getCodeId());
		if (code != null)
		{
			Collection histories = code.getCodeHistories();
			Iterator codeHistories = histories.iterator();
			// This line causes an exception

			this.sendCodeHistory(codeHistories);
		}

	}

	private void sendCodeHistory(Iterator codeHistories)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		while (codeHistories.hasNext())
		{
			CodeHistory codeHistory = (CodeHistory) codeHistories.next();

			CodeHistoryResponseEvent historyResponse =
				new CodeHistoryResponseEvent();
			historyResponse.setTopic(PDCodeTableConstants.CODE_HISTORY);

			historyResponse.setActiveDate(codeHistory.getActiveDate());
			historyResponse.setAuditId(codeHistory.getAuditId());
			historyResponse.setCode(codeHistory.getCode());
			historyResponse.setDescription(codeHistory.getDescription());
			historyResponse.setInactiveDate(codeHistory.getInactiveDate());
			historyResponse.setInactiveEffectiveDate(
				codeHistory.getInactiveEffectiveDate());
			historyResponse.setStatus(codeHistory.getStatus());
			historyResponse.setTransaction(codeHistory.getTransaction());
			historyResponse.setTransactionDate(
				codeHistory.getTransactionDate());
			historyResponse.setTransactionLogonId(
				codeHistory.getTransactionLogonId());
			historyResponse.setLogonId(codeHistory.getTransactionLogonId());

			dispatch.postEvent(historyResponse);
		}
	}

	/**
	@param event
	@roseuid 40CF4AF600D9
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	@param event
	@roseuid 40CF4AF600DB
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	@param updateObject
	@roseuid 40CF4CB100CB
	 */
	public void update(Object updateObject)
	{

	}
}
