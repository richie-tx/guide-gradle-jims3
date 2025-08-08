package pd.supervision.supervisionorder.transactions;

import java.util.List;

import pd.supervision.Group;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionorder.CourtSequenceGenerator;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderCondition;
import pd.supervision.supervisionorder.SupervisionOrderConditionRel;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionorder.CreateSpecialConditionEvent;
import messaging.supervisionorder.CreateSpecialConditionsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;

public class CreateSpecialConditionsCommand implements ICommand {

	public void execute(IEvent event) {
		// ---------------- create Condition ---------------------------
		CreateSpecialConditionsEvent mainEvent = (CreateSpecialConditionsEvent)event;
		
		// ---------------- create Order components ---------------------------
		// get Order
		SupervisionOrder supervisionOrder = SupervisionOrder.find(mainEvent.getOrderId());

		List reqList = CollectionUtil.enumerationToList(mainEvent.getRequests());
		CreateSpecialConditionEvent reqEvent = null;
		CourtSequenceGenerator crtSeqGenerator = null;
		
		if (reqList.size() > 0){
			crtSeqGenerator = Condition.getCourtSequenceGenerator(mainEvent);
		}
		
		for (int i = 0; i < reqList.size(); i++) {
			reqEvent = (CreateSpecialConditionEvent) reqList.get(i);

			//--------- SupervisionOrderConditionRel-----------------//
			SupervisionOrderConditionRel supervisionOrderConditionRel = new SupervisionOrderConditionRel();
			supervisionOrderConditionRel.setSupervisionOrderId(supervisionOrder.getOID());
			supervisionOrderConditionRel.setSequenceNum(reqEvent.getSequenceNum());

			//--------- SupervisionOrderCondiiton-----------------//
			//  create special condition
			
			Condition condition = Condition.createSpecialCondition(mainEvent, reqEvent, crtSeqGenerator);
			
			SupervisionOrderCondition supOrderCondition = SupervisionOrderCondition.create(condition);
			supOrderCondition.setResolvedDescription(reqEvent.getDescription());
			supOrderCondition.setDescription(reqEvent.getDescription());
			supervisionOrderConditionRel.setSupervisionOrderCondition(supOrderCondition);

			// insert supervisionorderRel into SupervisionOrder
			supervisionOrder.insertSupervisionOrderConditionRels(supervisionOrderConditionRel);
		
			// POST RESP EVENT BACK		
			ConditionDetailResponseEvent reply = new ConditionDetailResponseEvent();
			reply.setConditionId(condition.getOID());
			reply.setName(condition.getName());
			Group group = condition.getGroup();
			if (group != null)
			{
				reply.setGroup1Name(group.getGroupName());
			}
			reply.setName(condition.getName());
			reply.setNotes(condition.getNotes());
			reply.setResolvedDescription(supOrderCondition.getResolvedDescription());
			// Added for the ConditionBean RRY
			reply.setDescription(supOrderCondition.getDescription());
			reply.setSpecialCondition(true);
			reply.setSequenceNum(reqEvent.getSequenceNum());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(reply);
		}
	}

}
