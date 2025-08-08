//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionorder\\transactions\\GetLightSupervisionOrdersCommand.java

package pd.supervision.supervisionorder.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import messaging.supervisionorder.GetLightSupervisionOrdersEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderHelper;

/**
 * @author rcapestani Retrieves supervision orders without conditions.
 *         
 */
public class GetLightSupervisionOrdersCommand implements ICommand {
	

	/**
	 * @roseuid 43B2E782006D
	 */
	public GetLightSupervisionOrdersCommand() {

	}

	/**
	 * @param event
	 * @roseuid 438F22CC0066
	 */
	public void execute(IEvent event) 
	{

		GetLightSupervisionOrdersEvent getOrdersEvent = (GetLightSupervisionOrdersEvent) event;
		SupervisionOrder order = null;
		List ordersList=new ArrayList();
		Iterator supOrders = SupervisionOrder.findAll( getOrdersEvent );
		
		while( supOrders.hasNext() ){
			
			order = (SupervisionOrder) supOrders.next();
			
			SupervisionOrderDetailResponseEvent resp = SupervisionOrderHelper.getLightSupervisionOrderResponseEvent( order );
			ordersList.add(resp);
			
		}
		MessageUtil.postReplies( ordersList );
		
	}
	
	/**
	 * @param event
	 * @roseuid 438F22CC006A
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param updateObject
	 * @roseuid 43B2E782007D
	 */
	public void update(Object updateObject) {

	}
}
