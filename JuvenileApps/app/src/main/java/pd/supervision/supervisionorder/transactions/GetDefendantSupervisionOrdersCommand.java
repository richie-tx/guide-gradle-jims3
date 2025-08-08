package pd.supervision.supervisionorder.transactions;

import java.util.ArrayList;
import java.util.List;

import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderHelper;
import messaging.supervisionorder.GetDefendantSupervisionOrdersEvent;
import messaging.supervisionorder.GetSupervisionOrderDetailsEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;

public class GetDefendantSupervisionOrdersCommand implements ICommand {

	public void execute(IEvent event) throws Exception 
	{
		GetDefendantSupervisionOrdersEvent 
			get_defendant_orders_event = (GetDefendantSupervisionOrdersEvent)event;

    		//retrieve orders for defendant SPN
        List<SupervisionOrder> defendant_orders = 
        	CollectionUtil.iteratorToList(
        			SupervisionOrder.findAll("defendantId", 
        					get_defendant_orders_event.getDefendantId()));
        
        	//convert orders to response objects
        GetSupervisionOrderDetailsEvent get_details = new GetSupervisionOrderDetailsEvent();
        get_details.setDeleteAction(false);
        
        int num_orders = defendant_orders.size();
        List<SupervisionOrderDetailResponseEvent> 
        	defendant_orders_responses = 
        		new ArrayList<SupervisionOrderDetailResponseEvent>(num_orders);
        for (int i=0; i<num_orders;i++)
        {
        	defendant_orders_responses.add(SupervisionOrderHelper.
        		getSupervisionOrderDetailResponseEvent(
        				defendant_orders.get(i), get_details));
        }
		
        	//return response objects
        MessageUtil.postReplies(defendant_orders_responses);
	}

	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}

	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub

	}

	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}

}
