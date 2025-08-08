//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionorder\\transactions\\WithdrawSupervisionOrderCommand.java

package pd.supervision.supervisionorder.transactions;

import messaging.supervisionorder.WithdrawSupervisionOrderEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;
import pd.supervision.supervisionorder.OrderVersionSequence;
import pd.supervision.supervisionorder.SupervisionOrder;

/**
 * @author dgibler
 *
 */
public class WithdrawSupervisionOrderCommand implements ICommand
{

	/**
	 * Withdraw Supervision Order
	 * @roseuid 442169A5006A
	 */
	public WithdrawSupervisionOrderCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 442071B60334
	 */
	public void execute(IEvent event)
	{
		WithdrawSupervisionOrderEvent requestEvent = (WithdrawSupervisionOrderEvent) event;
		SupervisionOrder order = SupervisionOrder.find(requestEvent.getSupervisionOrderId());
		
		if (order != null)
		{
			order.setOrderStatusId(PDCodeTableConstants.STATUS_WITHDRAWN_ID);
			// Create casenote
			// JM - Replaced casenote with setSummaryChanges. ER is being fasttracked for this.
			//Casenote casenote = Casenote.createOrderCasenote(requestEvent.getSupervisionOrderId(), order.getDefendantId(), order.getSupervisionPeriodId(), requestEvent.getNotes());
			//order.insertCasenote(casenote);
			order.setSummaryChanges(requestEvent.getNotes());
			
			//Decrease version number by one for case/versionType.
			OrderVersionSequence.prev(order.getCriminalCaseId(), order.getVersionTypeId(),order.getOrderChainNum());
			//Order versionNum does not apply to withdrawn orders.
			order.setVersionNum(0);
			order.setWithdrawReasonId(requestEvent.getWithdrawReasonId());
			order.setOrderInProgress(false);
		}

	}

	/**
	 * @param event
	 * @roseuid 442071B60341
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 442071B60343
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 442169A5007A
	 */
	public void update(Object updateObject)
	{

	}
}
