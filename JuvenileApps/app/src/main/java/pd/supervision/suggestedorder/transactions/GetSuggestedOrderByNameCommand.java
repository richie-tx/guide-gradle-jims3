//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\suggestedorder\\transactions\\GetSuggestedOrderCommand.java

package pd.supervision.suggestedorder.transactions;

import java.util.Iterator;

import pd.security.PDSecurityHelper;
import pd.supervision.suggestedorder.SuggestedOrder;
import messaging.suggestedorder.GetSuggestedOrderByNameEvent;
import messaging.suggestedorder.reply.SuggestedOrderDuplicateNameErrorEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *
 */
public class GetSuggestedOrderByNameCommand implements ICommand
{

	/**
	 * @roseuid 433AF3640140
	 */
	public GetSuggestedOrderByNameCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 433AF05001A4
	 */
	public void execute(IEvent event)
	{
		GetSuggestedOrderByNameEvent getSuggestedOrderByNameEvent = (GetSuggestedOrderByNameEvent) event;
		String agencyId = PDSecurityHelper.getUserAgencyId();
		getSuggestedOrderByNameEvent.setAgencyId(agencyId);
		Iterator iter = SuggestedOrder.findAll(getSuggestedOrderByNameEvent);
		if (iter != null && iter.hasNext())
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			SuggestedOrderDuplicateNameErrorEvent errorEvent = new SuggestedOrderDuplicateNameErrorEvent();
			dispatch.postEvent(errorEvent);
		}

	}

	/**
	 * @param event
	 * @roseuid 433AF05001AF
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 433AF05001B1
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 433AF364015F
	 */
	public void update(Object updateObject)
	{

	}
}
