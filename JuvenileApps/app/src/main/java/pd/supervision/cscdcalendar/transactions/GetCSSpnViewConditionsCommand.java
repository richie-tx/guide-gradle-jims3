/*
 * Created on Apr 7, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.cscdcalendar.transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.cscdcalendar.GetCSSpnViewConditionsEvent;
import messaging.cscdcalendar.reply.CSSpnViewConditionsResponseEvent;
import messaging.supervisionorder.GetOrderConditionsBySPNEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderCondition;
import pd.supervision.supervisionorder.SupervisionOrderConditionView;
import pd.supervision.supervisionorder.SupervisionOrderHelper;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetCSSpnViewConditionsCommand implements ICommand {

	public GetCSSpnViewConditionsCommand() {

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetCSSpnViewConditionsEvent spnEvent = (GetCSSpnViewConditionsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetOrderConditionsBySPNEvent spnOrderEvent = new GetOrderConditionsBySPNEvent();
		spnOrderEvent.setSpnId(spnEvent.getSpnId());
		Iterator iterator = SupervisionOrderConditionView.findAll(spnOrderEvent);
		Hashtable caseConditions = new Hashtable();
		
		while (iterator.hasNext()) {
			SupervisionOrderConditionView view = (SupervisionOrderConditionView) iterator.next();
			SupervisionOrder order = SupervisionOrder.find(new Integer(view.getSprOrderId()).toString());			
			String orderVersion = " " + SupervisionOrderHelper.getOrderVersionLiteral(order);
			if (caseConditions.containsKey(view.getCaseNumber() + orderVersion)) {
				ArrayList conditionList = (ArrayList) caseConditions.get(view.getCaseNumber() + orderVersion);
				conditionList.add("" + view.getOID());
			} else {
				ArrayList newSprOrdrCondId = new ArrayList();
				newSprOrdrCondId.add("" + view.getOID());
				caseConditions.put(view.getCaseNumber() + orderVersion, newSprOrdrCondId);
			}
		}

		Iterator it = caseConditions.entrySet().iterator();
		CSSpnViewConditionsResponseEvent conditionsRespEvent = new CSSpnViewConditionsResponseEvent();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			Collections.sort((List)pairs.getValue());
			List descrList = new ArrayList();
			for(int i=0;i< ((List)pairs.getValue()).size();i++){
			List temp = new ArrayList();
			temp.add(((List)pairs.getValue()).get(i));
			Iterator descrIter = SupervisionOrderCondition.findAllByIds(temp);
			descrList.add(((SupervisionOrderCondition) descrIter.next()).getResolvedDescription());
			}
			pairs.setValue(descrList);
		}			
		conditionsRespEvent.setCaseConditions(caseConditions);
		dispatch.postEvent(conditionsRespEvent);

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}

}
