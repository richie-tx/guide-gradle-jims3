package pd.supervision.supervisionorder.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pd.criminalcase.CriminalCase;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderHelper;
import messaging.supervisionorder.GetUnfinishedOrdersEvent;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;

public class GetUnfinishedOrdersCommand implements ICommand {

	public void execute(IEvent event) {
		//long timeStart = System.currentTimeMillis();
		GetUnfinishedOrdersEvent reqEvent = (GetUnfinishedOrdersEvent) event;
		
		CriminalCase criminalCase = null;
		SupervisionOrder order = null;
		CaseOrderResponseEvent responseEvent = null;
		
		List responseList = new ArrayList();
		if (reqEvent.getCourtId() != null){
			StringBuffer court = new StringBuffer(reqEvent.getCourtId().toUpperCase());
			court.insert(0, "%");
			reqEvent.setCourtId(court.toString());
		}
		
		Iterator iter = SupervisionOrder.findAll(reqEvent);
		List orderList = CollectionUtil.iteratorToList(iter);
		
		for (int i = 0; i < orderList.size(); i++) {
			order = (SupervisionOrder) orderList.get(i);
			criminalCase = order.getCriminalCase();
			if ( criminalCase != null ){
               responseEvent = SupervisionOrderHelper.getLightCaseOrderResponseEvent(order, criminalCase);
			}
//			responseEvent = SupervisionOrderHelper.getLightCaseOrderResponseEvent(order, criminalCase);
			if (responseEvent == null) 
			{
				continue;
			}
			SupervisionOrderHelper.setCaseActivityRuleStatus(order, responseEvent, false);
			responseList.add(responseEvent);
		}

		MessageUtil.postReplies(responseList);
		
		//long timeEnd = System.currentTimeMillis();
		//System.out.println("Total PD Time(milli seconds) to search for unfinished SupervisionOrders : " + (timeEnd - timeStart));

	}

}
