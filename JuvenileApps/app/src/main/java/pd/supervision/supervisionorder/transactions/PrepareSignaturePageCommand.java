package pd.supervision.supervisionorder.transactions;

import naming.PDCodeTableConstants;
import naming.PDConstants;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderHelper;
import messaging.supervisionorder.CreateSupervisionOrderEvent;
import messaging.supervisionorder.PrepareSignaturePageEvent;
import messaging.supervisionorder.reply.OrderCreateErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class PrepareSignaturePageCommand implements ICommand {

	public void execute(IEvent event) {
		//long timeStart = System.currentTimeMillis();
		
		PrepareSignaturePageEvent reqEvent = (PrepareSignaturePageEvent) event;
		CreateSupervisionOrderEvent createEvent = (CreateSupervisionOrderEvent) reqEvent;

		if (reqEvent.getSupervisionOrderId() != null 
				&& !reqEvent.getSupervisionOrderId().equals(PDConstants.BLANK)
				&& !reqEvent.getStatus().equals(PDCodeTableConstants.STATUS_ACTIVE_ID)){
			//Status will be active when creating a new order version on an active order.
			//Do not want to update active order, need to create new order version instead.
			createEvent.setStatus(reqEvent.getStatus());
			this.updateSupervisionOrder(createEvent);
		} else { //Either creating new order version or new order on case without previous orders.
			createEvent.setStatus(PDCodeTableConstants.STATUS_INCOMPLETE_ID);
			SupervisionOrderHelper.createSupervisionOrder(createEvent);
		}
		//long timeEnd = System.currentTimeMillis();
		//System.out.println("***********PD Time(milli seconds) to create order on print signature : " + (timeEnd - timeStart));
	}

	private void updateSupervisionOrder(CreateSupervisionOrderEvent reqEvent) {
		SupervisionOrder supervisionOrder = SupervisionOrder.find(reqEvent.getSupervisionOrderId());
		if (supervisionOrder != null){
			supervisionOrder.setOrderSignedByDefendant(reqEvent.isDefendantSignatureInd());
			supervisionOrder.setOrderSignedByDefendantDate(reqEvent.getDefendantSignatureDate());
			supervisionOrder.setOrderSignedByJudgeDate(reqEvent.getJudgeSignatureDate());
			supervisionOrder.setOrderPresentorFirstName(reqEvent.getPresentorFirstName());
			supervisionOrder.setOrderPresentorLastName(reqEvent.getPresentorLastName());
			supervisionOrder.setSignedByFirstName(reqEvent.getSignorFirstName());
			supervisionOrder.setSignedByLastName(reqEvent.getSignorLastName());
			supervisionOrder.setOrderSignedDate(reqEvent.getOrderSignatureDate());
			supervisionOrder.setLimitedSupervisionPeriod(reqEvent.getLimitedSupervisionPeriod());
			supervisionOrder.setOrderStatusId(reqEvent.getStatus());
			supervisionOrder.setOrderTitleId(reqEvent.getOrderTitle());
			supervisionOrder.setFineAmount(reqEvent.getFineAmount());
			supervisionOrder.setPlea(reqEvent.getPlea());
			supervisionOrder.setPrintedName(reqEvent.getPrintedName());
			supervisionOrder.setPrintedOffenseDesc(reqEvent.getPrintedOffenseDesc());
			supervisionOrder.setLimitedSupervisionBeginDate(reqEvent.getLimitedSupervisionBeginDate());
			supervisionOrder.setLimitedSupervisionEndDate(reqEvent.getLimitedSupervisionEndDate());
			//supervisionOrder.setOffenseId(reqEvent.getOffenseId());
			supervisionOrder.setConfinementLengthMonths(reqEvent.getConfinementLengthMonths());
			supervisionOrder.setConfinementLengthDays(reqEvent.getConfinementLengthDays());
			supervisionOrder.setConfinementLengthYears(reqEvent.getConfinementLengthYears());
			supervisionOrder.setSupervisionLengthMonths(reqEvent.getSupervisionLengthMonths());
			supervisionOrder.setSupervisionLengthDays(reqEvent.getSupervisionLengthDays());
			supervisionOrder.setSupervisionLengthYears(reqEvent.getSupervisionLengthYears());
			supervisionOrder.setDispositionTypeId(reqEvent.getDispositionTypeId());
			supervisionOrder.setCaseSupervisionBeginDate(reqEvent.getCaseSupervisionBeginDate());
			supervisionOrder.setCaseSupervisionEndDate(reqEvent.getCaseSupervisionEndDate());	
			supervisionOrder.setVersionNum(reqEvent.getVersionNum());
			supervisionOrder.setJuvenileCourtId(reqEvent.getJuvCourtId());
			supervisionOrder.setJuvSupervisionBeginDate(reqEvent.getJuvSupervisionBeginDate());
			supervisionOrder.setJuvSupervisionLenDays(reqEvent.getJuvSupervisionLengthDays());
			supervisionOrder.setJuvSupervisionLenMonths(reqEvent.getJuvSupervisionLengthMonths());
			supervisionOrder.setJuvSupervisionLenYears(reqEvent.getJuvSupervisionLengthYears());
			supervisionOrder.setSpecialCourtCd(reqEvent.getSpecialCourtCd());
			SupervisionOrderHelper.postOrderRespEvent(supervisionOrder);
		} else {
		    OrderCreateErrorResponseEvent orderCreateErrorResponseEvent = new OrderCreateErrorResponseEvent();
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(orderCreateErrorResponseEvent);
		}
	}
}
