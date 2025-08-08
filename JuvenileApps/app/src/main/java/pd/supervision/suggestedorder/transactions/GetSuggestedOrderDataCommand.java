//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\suggestedorder\\transactions\\GetSuggestedOrderDataCommand.java

package pd.supervision.suggestedorder.transactions;

import pd.supervision.suggestedorder.SuggestedOrder;
import pd.supervision.suggestedorder.SuggestedOrderHelper;
import messaging.suggestedorder.GetSuggestedOrderDataEvent;
import messaging.suggestedorder.reply.SuggestedOrderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *
 * Retrieves a SuggestedOrder and it's child objects SuggestedOrderCondition, SuggestedOrderOffense, SuggestedOrderCourt.
 */
public class GetSuggestedOrderDataCommand implements ICommand
{

	/**
	 * @roseuid 433AF3970075
	 */
	public GetSuggestedOrderDataCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 433AF0520018
	 */
	public void execute(IEvent event)
	{
		GetSuggestedOrderDataEvent getSuggestedOrderEvent = (GetSuggestedOrderDataEvent) event;
		if(getSuggestedOrderEvent.getSuggestedOrderId()!=null && !getSuggestedOrderEvent.getSuggestedOrderId().equals("")){
			SuggestedOrder suggestedOrder = SuggestedOrder.find(getSuggestedOrderEvent.getSuggestedOrderId());
			if (suggestedOrder != null)
			{
				SuggestedOrderResponseEvent suggestedOrderResponse = suggestedOrder.getResponseEvent();
	
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
				dispatch.postEvent(suggestedOrderResponse);
	
				SuggestedOrderHelper.postChildResponseEvents(dispatch, suggestedOrder);
			}
		}
	}

	/**
	 * @param event
	 * @roseuid 433AF052001A
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 433AF052001C
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 433AF3970085
	 */
	public void update(Object updateObject)
	{

	}
}
