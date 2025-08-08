package pd.supervision.supervisionorder.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import naming.PDCodeTableConstants;
import pd.supervision.suggestedorder.SuggestedOrderCondition;
import pd.supervision.suggestedorder.SuggestedOrderHelper;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionoptions.ConditionCourtVariableElement;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderCondition;
import pd.supervision.supervisionorder.SupervisionOrderConditionRel;
import pd.supervision.supervisionorder.SupervisionOrderConditionRelValue;
import pd.supervision.supervisionorder.SupervisionOrderHelper;
import pd.supervision.supervisionorder.SupervisionOrderReferenceVariableHelper;
import pd.supervision.supervisionorder.SupervisionOrderRelValueHistory;
import messaging.supervisionorder.UpdateSupervisionOrderWithSuggestedOrderConditionsEvent;
import messaging.supervisionorder.reply.OrderCreateErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;

public class UpdateSupervisionOrderWithSuggestedOrderConditionsCommand
		implements ICommand {

	public void execute(IEvent event)  {

		UpdateSupervisionOrderWithSuggestedOrderConditionsEvent reqEvent = (UpdateSupervisionOrderWithSuggestedOrderConditionsEvent) event;
		
		SupervisionOrder supervisionOrder = SupervisionOrder.find(reqEvent.getOrderId());
		if(supervisionOrder == null){
		    // error situation for the case when there is already a valid order version existing
		    OrderCreateErrorResponseEvent orderCreateErrorResponseEvent = new OrderCreateErrorResponseEvent();
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(orderCreateErrorResponseEvent);
		} else {
			this.updateSupervisionOrder(supervisionOrder, reqEvent);
			// send CaseOrderResponseEvent 
			SupervisionOrderHelper.postOrderRespEvent(supervisionOrder);
		}
	}

	private void updateSupervisionOrder(SupervisionOrder supervisionOrder,
			UpdateSupervisionOrderWithSuggestedOrderConditionsEvent reqEvent) {
		supervisionOrder.setRevisionDate(new Date());
		supervisionOrder.setLimitedSupervisionBeginDate(reqEvent.getLimitedSupervisionBeginDate());
		supervisionOrder.setLimitedSupervisionEndDate(reqEvent.getLimitedSupervisionEndDate());
		supervisionOrder.setFineAmount(reqEvent.getFineAmount());
		supervisionOrder.setModificationReason(reqEvent.getModificationReason());
		supervisionOrder.setComments(reqEvent.getComments());
		supervisionOrder.setJuvenileCourtId(reqEvent.getJuvCourtId());
		supervisionOrder.setSpecialCourtCd(reqEvent.getSpecialCourtCd());
		supervisionOrder.setPlea(reqEvent.getPlea());
		supervisionOrder.setPrintedOffenseDesc(reqEvent.getPrintedOffenseDesc());
		supervisionOrder.setSummaryChanges(reqEvent.getSummaryChanges());
		supervisionOrder.setLimitedSupervisionPeriod(reqEvent.isLimitedSupervisionPeriod());
		supervisionOrder.setConfinementLengthMonths(reqEvent.getConfinementLengthMonths());
		supervisionOrder.setConfinementLengthDays(reqEvent.getConfinementLengthDays());
		supervisionOrder.setConfinementLengthYears(reqEvent.getConfinementLengthYears());
		//supervisionOrder.setOffenseId(reqEvent.getOffenseId());
		supervisionOrder.setSuggestedOrderId(reqEvent.getSuggestedOrderId());
		supervisionOrder.setSupervisionLengthMonths(reqEvent.getSupervisionLengthMonths());
		supervisionOrder.setSupervisionLengthDays(reqEvent.getSupervisionLengthDays());
		supervisionOrder.setSupervisionLengthYears(reqEvent.getSupervisionLengthYears());
		supervisionOrder.setDispositionTypeId(reqEvent.getDispositionTypeId());
		supervisionOrder.setCaseSupervisionBeginDate(reqEvent.getCaseSupervisionBeginDate());
		supervisionOrder.setCaseSupervisionEndDate(reqEvent.getCaseSupervisionEndDate());
		Collection exConds = supervisionOrder.getSupervisionOrderConditionRels();
		
		List exCondList = CollectionUtil.iteratorToList(exConds.iterator());
		//Remove conditions already attached to order to avoid duplication of conditions.
		for (int i = 0; i < exCondList.size(); i++) {
			SupervisionOrderConditionRel cond = (SupervisionOrderConditionRel) exCondList.get(i);
			supervisionOrder.removeSupervisionOrderConditionRels(cond);
		}
		if (reqEvent.getSuggestedOrderId() != null)
		{
			Map variableElementReferenceMap = reqEvent.getVariableElementReferenceMap();
			Map updatedRefVarsMap = SupervisionOrderReferenceVariableHelper.getNewValuesForVolatileRefVars(reqEvent.getAgencyId(), supervisionOrder);
			variableElementReferenceMap = SupervisionOrderReferenceVariableHelper.updateRefVars(variableElementReferenceMap, updatedRefVarsMap);;
			Map conditionMap = new HashMap();
			Collection sugOrderConditions = new ArrayList();
			
			SuggestedOrderHelper.getSuggestedOrderConditions(reqEvent.getSuggestedOrderId(), conditionMap,
					sugOrderConditions);
			// retrieve variable elements for the given court
			List conditionIds = new ArrayList();
			for (Iterator iter = conditionMap.keySet().iterator(); iter.hasNext();)
			{
				conditionIds.add(iter.next());
			}
			Map conditionVarElementMap = SupervisionOrder.getVariableElements(reqEvent.getOrderCourtId(), reqEvent.getAgencyId(),
					conditionIds);

			if (sugOrderConditions.size() > 0)
			{ // if suggested order has atleast one condition
				// sort it by sequence num
				Collections.sort((List) sugOrderConditions, SuggestedOrderCondition.SeqNumComparator);
				int curSeqNum = 1;
				for (Iterator conditionIter = sugOrderConditions.iterator(); conditionIter.hasNext();)
				{
					SuggestedOrderCondition suggOrderCondition = (SuggestedOrderCondition) conditionIter.next();
					Condition condition = (Condition) conditionMap.get(suggOrderCondition.getConditionId());
					// create SupervisionOrderCondition
					SupervisionOrderCondition supOrderCondition = SupervisionOrderCondition.create(condition);
					// create SupervisionOrderConditionRel
					SupervisionOrderConditionRel supervisionOrderConditionRel = new SupervisionOrderConditionRel();
					//                    supervisionOrderConditionRel.setSupervisionOrderId(supervisionOrder.getOID().toString());
					supervisionOrderConditionRel.setSupervisionOrderCondition(supOrderCondition);
					supervisionOrderConditionRel.setSequenceNum(curSeqNum++);
					supervisionOrder.insertSupervisionOrderConditionRels(supervisionOrderConditionRel);
					
					Collection conditionCourtVariableElements = (Collection) conditionVarElementMap.get(condition
							.getOID());
					if (conditionCourtVariableElements != null)
					{
						for (Iterator iter = conditionCourtVariableElements.iterator(); iter.hasNext();)
						{
							ConditionCourtVariableElement condCrtVarElement = (ConditionCourtVariableElement) iter
									.next();
							if (condCrtVarElement.getVariableElementId() != null)
							{
								SupervisionOrderConditionRelValue supervisionOrderConditionRelValue = SupervisionOrderConditionRelValue
										.create(condCrtVarElement, variableElementReferenceMap);
								if (condCrtVarElement.getIsCalculated()){
									//String aStringDate = SupervisionOrder.calculateDate(supervisionOrder, supervisionOrderConditionRelValue, condCrtVarElement);
									String aStringDate = SupervisionOrder.calculateDate(supervisionOrder, condCrtVarElement);
									
									supervisionOrderConditionRelValue.setValue(aStringDate);
									condCrtVarElement.setValue(aStringDate);
								}
								supervisionOrderConditionRel
										.insertOrderConditionRelValues(supervisionOrderConditionRelValue);
								if (condCrtVarElement.getIsReference())
								{
									SupervisionOrderRelValueHistory relValHistory = SupervisionOrderRelValueHistory
											.create(PDCodeTableConstants.TRANS_TYPE_ADD, "");
									supervisionOrderConditionRelValue.insertSupervisionOrderRelValHistories(relValHistory);
								}
							}
						}
					}
					// post response event back to ui to display conditions
					SuggestedOrderHelper.postConditionDetailRespEvent(condition, suggOrderCondition.getSeqNum(),
							reqEvent.getOrderCourtId(), variableElementReferenceMap, conditionVarElementMap);
				}
			}
		
		}
	}
}
