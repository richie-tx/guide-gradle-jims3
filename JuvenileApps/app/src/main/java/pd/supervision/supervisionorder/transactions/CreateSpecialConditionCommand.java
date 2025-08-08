package pd.supervision.supervisionorder.transactions;


import pd.supervision.Group;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderCondition;
import pd.supervision.supervisionorder.SupervisionOrderConditionRel;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionorder.CreateSpecialConditionEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;


/**
 * @author asrvastava
 *
 *	This command creates SpecialCondition for an Order.
 */
public class CreateSpecialConditionCommand implements ICommand 
{
	/**
	 * @param event
	 * @roseuid 43BECD3500D5
	 */
	public void execute(IEvent event) 
	{
		//This command has been replaced with a command that creates 
		//multiple special conditions. (CreateSpecialConditionsCommand).
		// ---------------- create Condition ---------------------------
		/* CreateSpecialConditionEvent reqEvent = (CreateSpecialConditionEvent)event;
		
		// ---------------- create Order components ---------------------------
		// get Order
		SupervisionOrder supervisionOrder = SupervisionOrder.find(reqEvent.getOrderId());


		//--------- SupervisionOrderCondiitonRel-----------------//
		SupervisionOrderConditionRel supervisionOrderConditionRel = new SupervisionOrderConditionRel();
		supervisionOrderConditionRel.setSupervisionOrderId(supervisionOrder.getOID().toString());
		supervisionOrderConditionRel.setSequenceNum(reqEvent.getSequenceNum());

		//--------- SupervisionOrderCondiiton-----------------//
		//  create special condition
		Condition condition = Condition.createSpecialCondition(reqEvent);
		SupervisionOrderCondition supOrderCondition = SupervisionOrderCondition.create(condition);
		supOrderCondition.setResolvedDescription(reqEvent.getDescription());
		supervisionOrderConditionRel.setSupervisionOrderCondition(supOrderCondition);

		// insert supervisionorderRel into SupervisionOrder
		supervisionOrder.insertSupervisionOrderConditionRels(supervisionOrderConditionRel);
		
		// POST RESP EVENT BACK		
		ConditionDetailResponseEvent reply = new ConditionDetailResponseEvent();
		reply.setConditionId(condition.getOID().toString());
		reply.setName(condition.getName());
		Group group = condition.getGroup();
		if (group != null)
		{
			reply.setGroup1Name(group.getGroupName());
		}
		reply.setName(condition.getName());
		reply.setNotes(condition.getNotes());
		reply.setResolvedDescription(supOrderCondition.getResolvedDescription());
		reply.setSpecialCondition(true);
		reply.setSequenceNum(reqEvent.getSequenceNum());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(reply);*/
		
		 	 	    
	}
   
	/**
	 * @param event
	 * @roseuid 43BECD3500D7
	 */
	public void onRegister(IEvent event) 
	{
    
	}
   
	/**
	 * @param event
	 * @roseuid 43BECD3500D9
	 */
	public void onUnregister(IEvent event) 
	{
    
	}
   
	/**
	 * @param updateObject
	 * @roseuid 43BECFD80150
	 */
	public void update(Object updateObject) 
	{
    
	}
	
}