//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\suggestedorder\\transactions\\DeleteSuggestedOrderCommand.java

package pd.supervision.suggestedorder.transactions;

import pd.supervision.suggestedorder.SuggestedOrder;
import messaging.suggestedorder.DeleteSuggestedOrderEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *
 */
public class DeleteSuggestedOrderCommand implements ICommand
{

	/**
	 * @roseuid 433AF34C02B7
	 */
	public DeleteSuggestedOrderCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 433AF05200C5
	 */
	public void execute(IEvent event)
	{
		DeleteSuggestedOrderEvent deleteSuggestedOrderEvent = (DeleteSuggestedOrderEvent) event;

		SuggestedOrder so = SuggestedOrder.find(deleteSuggestedOrderEvent.getSuggestedOrderId());
		if (so != null)
		{
			so.delete();
		}

	}
	/**
	 * @param event
	 * @roseuid 433AF05200C7
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 433AF05200D3
	 */
	public void onUnregister(IEvent event)
	{

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
	}
}
