/*
 * Created on Mar 13, 2006
 *
 */
package pd.supervision.supervisionorder.transactions;

import java.util.Iterator;
import java.util.List;

import naming.PDCodeTableConstants;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionorder.OrderVersionSequence;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderCondition;
import pd.supervision.supervisionorder.SupervisionOrderConditionRel;
import messaging.supervisionorder.DeleteSupervisionOrderEvent;
import messaging.supervisionorder.reply.OrderDeleteErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;

/**
 * @author dgibler
 *
 */
public class DeleteSupervisionOrderCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	 
	 public DeleteSupervisionOrderCommand()
	 {
	 }
	 
	public void execute(IEvent event) 
	{
		DeleteSupervisionOrderEvent deleteSOE = (DeleteSupervisionOrderEvent) event;
		
		SupervisionOrder so	= SupervisionOrder.find(deleteSOE.getSupervisionOrderId());
		if (so != null)
		{
			if (PDCodeTableConstants.STATUS_INCOMPLETE_ID.equals(so.getOrderStatusId())){
				OrderVersionSequence.prev(so.getCriminalCaseId(), so.getVersionTypeId(),so.getOrderChainNum());
				so.delete();
				this.deleteSpecialConditions(deleteSOE.getSupervisionOrderId());
			} else {
				MessageUtil.postReply(new OrderDeleteErrorResponseEvent());
			}
		}
	}

	private void deleteSpecialConditions(String supervisionOrderId) {
		
		//Delete from CSCONDITION if special condition not attached to a previous order version.
		
		IHome home = new Home();

		Iterator iter = home.findAll("supervisionOrderId", supervisionOrderId, SupervisionOrderConditionRel.class);
		
		if (iter != null && iter.hasNext()){
			SupervisionOrderConditionRel soCondRel = null;
			List aList = CollectionUtil.iteratorToList(iter);
			List aList2 = null;
			SupervisionOrderCondition soCondition = null;
			Condition specialCondition = null;
			Iterator iter2 = null;
			for (int i = 0; i < aList.size(); i++) {
				soCondRel = (SupervisionOrderConditionRel) aList.get(i);
				soCondition = soCondRel.getSupervisionOrderCondition();
				if (soCondition != null && soCondition.getIsSpecialCondition()){
					iter2 = SupervisionOrderCondition.findAll("conditionId", soCondition.getConditionId());
					aList2 = CollectionUtil.iteratorToList(iter2);
					if (aList2.size() == 1){
						specialCondition = soCondition.getCondition();
						specialCondition.delete();
					}

				}
			}
		}

		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
		

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
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
