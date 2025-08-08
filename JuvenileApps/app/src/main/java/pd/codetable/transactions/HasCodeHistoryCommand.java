package pd.codetable.transactions;

import naming.PDCodeTableConstants;
import pd.codetable.Code;
import messaging.codetable.HasCodeHistoryEvent;
import messaging.codetable.reply.CodeHistoryResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *
 */
public class HasCodeHistoryCommand implements ICommand {

	/**
	@roseuid 40CF4CB100B7
	 */
	public HasCodeHistoryCommand() {}

	/**
	 * @param event
	 * @precondition ((HasCodeHistoryEvent)event).getCodeId() != null)
	 * @postcondition (eventHistoryReply.getCode() != null)
	 */
	public void execute(IEvent event) {
		HasCodeHistoryEvent getEvent = (HasCodeHistoryEvent) event;

		Code code = Code.find(getEvent.getCode());
		if (code != null) {
			sendCodeHistory(!code.getCodeHistories().isEmpty());
		}
		sendCodeHistory(false);
	}

	/**
	 * Posts CodeHistory response events.
	 * @param hasHistory
	 */
	private void sendCodeHistory(boolean hasHistory) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		if (hasHistory) {
			CodeHistoryResponseEvent historyResponse = new CodeHistoryResponseEvent();
			historyResponse.setTopic(PDCodeTableConstants.CODE_HISTORY);

			dispatch.postEvent(historyResponse);
		}
	}

	/**
	@param event
	@roseuid 40CF4AF600D9
	 */
	public void onRegister(IEvent event) {}

	/**
	@param event
	@roseuid 40CF4AF600DB
	 */
	public void onUnregister(IEvent event) {}

	/**
	@param updateObject
	@roseuid 40CF4CB100CB
	 */
	public void update(Object updateObject) {}
}
