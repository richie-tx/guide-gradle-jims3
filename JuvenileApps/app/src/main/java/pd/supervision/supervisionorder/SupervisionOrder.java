package pd.supervision.supervisionorder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import messaging.party.GetTasksByDefendantIdsEvent;
import messaging.supervisionorder.CreateSupervisionOrderEvent;
import messaging.supervisionorder.GetMostRecentInactiveOrderForSpnEvent;
import messaging.supervisionorder.GetOrdersForSupervisionPeriodIdsEvent;
import messaging.supervisionorder.GetOriginalSupervisionOrderEvent;
import messaging.supervisionorder.GetSupervisionOrdersFromConditionEvent;
import messaging.supervisionorder.SupervisionOrderConditionEvent;
import messaging.supervisionorder.SupervisionOrderConditionRelValueEvent;
import messaging.supervisionorder.UpdateSupervisionOrderEvent;
import messaging.supervisionorder.ValidateNewOrderVersionEvent;
import messaging.supervisionorder.reply.SuperviseeCaseOrderResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.Name;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.SupervisionConstants;
import naming.UIConstants;
import pd.codetable.Code;
import pd.codetable.supervision.Magistrate;
import pd.codetable.supervision.SupervisionCode;
import pd.common.util.PDUtil;
import pd.contact.agency.Agency;
import pd.contact.party.Party;
import pd.criminalcase.CriminalCase;
import pd.criminalcase.Supervision;
import pd.supervision.suggestedorder.SuggestedOrderCondition;
import pd.supervision.suggestedorder.SuggestedOrderHelper;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionoptions.ConditionCourtVariableElement;
import pd.supervision.supervisionoptions.ConditionSupervisionOption;
import pd.supervision.supervisionoptions.StringSet;
import pd.supervision.supervisionstaff.pretrialstaff.SupervisionStaff;

/**
 * @author dgibler
 */
public class SupervisionOrder extends PersistentObject implements Comparable
{

	/**
	 * @return 
	 * @param reqEvent

	 */
	private static final String MIGRATED = "MIG";
	/**
	 * faster method that dshould be called from condition add/remove page. the
	 * only thing it allows is Condition add/remove.
	 * @return 
	 * @param reqEvent
	 *
	 */
	private static final String UPDATE_ERROR = "Supervision order not found.  Update not completed.";
	static public SupervisionOrder addRemoveConditions(UpdateSupervisionOrderEvent reqEvent)
			throws CloneNotSupportedException
	{
//		String orderId = reqEvent.getOrderId();
		//---------Supervision Order------------------------//
		SupervisionOrder supervisionOrder = find(reqEvent.getOrderId());
		if (supervisionOrder == null){
			ReturnException re = new ReturnException();
			re.setErrorReason(UPDATE_ERROR);
			IDispatch replyDispatch = EventManager.getSharedInstance(EventManager.REPLY);
			replyDispatch.postEvent(re);
			return null;
		}
		supervisionOrder.setOrderStatusId(reqEvent.getOrderStatusId());
		//Can change info on order presentation page if add/remove conditions button is pressed there.
		//supervisionOrder.setOffenseId(reqEvent.getOffenseId());
		supervisionOrder.setPrintedName(reqEvent.getPrintedName());
		supervisionOrder.setPrintedOffenseDesc(reqEvent.getPrintedOffenseDesc());
		if (supervisionOrder.getIsHistoricalOrder()){
			supervisionOrder.setDispositionTypeId(PDCodeTableConstants.PRETRIAL_INTERVENTION);
		} else {
			supervisionOrder.setDispositionTypeId(reqEvent.getDispositionTypeId());
		}
		supervisionOrder.setFineAmount(reqEvent.getFineAmount());
		supervisionOrder.setSupervisionLengthDays(reqEvent.getSupervisionLengthDays());
		supervisionOrder.setSupervisionLengthMonths(reqEvent.getSupervisionLengthMonths());
		supervisionOrder.setSupervisionLengthYears(reqEvent.getSupervisionLengthYears());
		supervisionOrder.setCaseSupervisionBeginDate(reqEvent.getCaseSupervisionBeginDate());
		supervisionOrder.setCaseSupervisionEndDate(reqEvent.getCaseSupervisionEndDate());
		supervisionOrder.setPlea(reqEvent.getPlea());
		supervisionOrder.setComments(reqEvent.getComments());
		supervisionOrder.setJuvenileCourtId(reqEvent.getJuvCourtId());
		supervisionOrder.setJuvSupervisionBeginDate(reqEvent.getJuvSupervisionBeginDate());
		supervisionOrder.setJuvSupervisionLenDays(reqEvent.getJuvSupervisionLengthDays());
		supervisionOrder.setJuvSupervisionLenMonths(reqEvent.getJuvSupervisionLengthMonths());
		supervisionOrder.setJuvSupervisionLenYears(reqEvent.getJuvSupervisionLengthYears());
		supervisionOrder.setSpecialCourtCd(reqEvent.getSpecialCourtCd());
		supervisionOrder.setSummaryChanges(reqEvent.getSummaryChanges());
		supervisionOrder.setOrderTitleId(reqEvent.getOrderTitle());
		
		//-------------------------conditions------------------------//
		Collection supervisionOrderConditionRels = supervisionOrder.getSupervisionOrderConditionRels();
		Map existingCondMap = new HashMap();
		Map existingCondToRelMap = new HashMap();
		Map relToValuesMap = new HashMap(); //added 09/14/09
//		createExistingConditionMaps(supervisionOrderConditionRels, existingCondMap, existingCondToRelMap, null);
		createExistingConditionMaps(supervisionOrderConditionRels, existingCondMap, existingCondToRelMap, relToValuesMap);

		StringSet exConditions = existCondSet(existingCondMap.keySet());
		StringSet passedConditions = newCondSet(reqEvent.getConditions());
		Set newConditions = passedConditions.complement(exConditions);
		Set removedConditions = exConditions.complement(passedConditions);
		// get Condition Objects from new condition ids
		Map conditionMap = new HashMap();
		if (newConditions.size() > 0)
		{
			Iterator condObjects = Condition.findAllByIds(newConditions, true);
			while (condObjects.hasNext())
			{
				Condition condition = (Condition) condObjects.next();
				conditionMap.put(condition.getOID(), condition);
			}
		}
		// retrieve variable elements for the given court
		List conditionIds = new ArrayList();
		Iterator conditionIter = passedConditions.iterator();
		while (conditionIter.hasNext())
		{
			conditionIds.add(conditionIter.next());
		}
		Map conditionVarElementMap = getVariableElements(supervisionOrder.getOrderCourtId(), supervisionOrder
				.getAgencyId(), conditionIds);
//		Map conditionVarElementMap = getVariableElements(supervisionOrder.getCurrentCourtId(), supervisionOrder
//				.getAgencyId(), conditionIds);

		// iterate conditions
		Collection conditionRelEvents = reqEvent.getConditions();
		for (Iterator i = conditionRelEvents.iterator(); i.hasNext();)
		{
			SupervisionOrderConditionEvent orderCondEvent = (SupervisionOrderConditionEvent) i.next();
			SupervisionOrderConditionRel supervisionOrderConditionRel = null;
			String conditionId = orderCondEvent.getConditionId();
			if (newConditions.contains(conditionId)) // new conditions just been
			// added
			{
				//				Condition condition = Condition.find(conditionId);
				//----------SupervisionOrderCondition------------------//
				Condition condition = (Condition) conditionMap.get(conditionId);
				SupervisionOrderCondition supOrderCondition = SupervisionOrderCondition.create(condition);
				//------------SupervisionOrderConditionRel---------------//
				supervisionOrderConditionRel = new SupervisionOrderConditionRel();
				//                supervisionOrderConditionRel.setSupervisionOrderId(supervisionOrder.getOID());
				supervisionOrderConditionRel.setSupervisionOrderCondition(supOrderCondition);
				supervisionOrderConditionRel.setSequenceNum(orderCondEvent.getSequenceNum());
				//new Home().bind(supervisionOrderConditionRel);
				supervisionOrder.insertSupervisionOrderConditionRels(supervisionOrderConditionRel);
				//				------------SupervisionOrderConditionRelValue---------------//
				//				Iterator variableElements =
				// ConditionSupervisionOption.findVariableElements(condition.getOID().toString(),
				// supervisionOrder.getOrderCourtId());
				Collection conditionCourtVariableElements = (Collection) conditionVarElementMap.get(condition.getOID()
						.toString());
				if (conditionCourtVariableElements != null)
				{
					for (Iterator iter = conditionCourtVariableElements.iterator(); iter.hasNext();)
					{
						ConditionCourtVariableElement condCrtVarElement = (ConditionCourtVariableElement) iter.next();
						if (condCrtVarElement.getVariableElementTypeId() != null)
						{
							SupervisionOrderConditionRelValue supervisionOrderConditionRelValue = SupervisionOrderConditionRelValue
							.create(supervisionOrder, condCrtVarElement, null);
							supervisionOrderConditionRel
									.insertOrderConditionRelValues(supervisionOrderConditionRelValue);
							//------------SupervisionOrderRelValueHistory---------------//
							if (condCrtVarElement.getIsReference())
							{
								SupervisionOrderRelValueHistory relValHistory = SupervisionOrderRelValueHistory
										.create(PDCodeTableConstants.TRANS_TYPE_ADD, "");
								supervisionOrderConditionRelValue.insertSupervisionOrderRelValHistories(relValHistory);
							}
						}
					}
				}
			}
			else
			{ // modified
				supervisionOrderConditionRel = (SupervisionOrderConditionRel) existingCondToRelMap.get(conditionId);
				//supervisionOrderConditionRel.setSequenceNum(orderCondEvent.getSequenceNum());
				//09/14/09 - added update of variable element values.  is needed since
				//we can add/remove conditions in many places now.
				if (supervisionOrderConditionRel != null){
					supervisionOrderConditionRel.setSequenceNum(orderCondEvent.getSequenceNum());
					SupervisionOrderCondition supOrderCondition = (SupervisionOrderCondition) existingCondMap
						.get(conditionId);
					supOrderCondition.setResolvedDescription(orderCondEvent.getResolvedDescription());
					Collection newCondRelVals = orderCondEvent.getOrderConditionRelValues();
					Collection relValues = (Collection) relToValuesMap
						.get(supervisionOrderConditionRel.getOID());
					if (relValues != null)
					{
						Map existingRelVals = createExistingRelValueMap(relValues);
						if (newCondRelVals != null)
						{
							for (Iterator valIndex = newCondRelVals.iterator(); valIndex.hasNext();)
							{
								SupervisionOrderConditionRelValueEvent condRelValEvent = (SupervisionOrderConditionRelValueEvent) valIndex
									.next();
								SupervisionOrderConditionRelValue condRelVal = (SupervisionOrderConditionRelValue) existingRelVals
									.get(condRelValEvent.getVariableElementTypeId());
								if (condRelVal != null)
								{
									String priorValue = condRelVal.getValue();
									condRelVal.populate(condRelValEvent);
									new Home().bind(condRelVal);
									//create relValHistory for reference field only
									if (condRelValEvent.isReference()
										&& (priorValue == null || !priorValue.equals(condRelVal.getValue())))
									{
										SupervisionOrderRelValueHistory relValHistory = SupervisionOrderRelValueHistory
											.create(PDCodeTableConstants.TRANS_TYPE_CHANGE, condRelVal.getValue());
										relValHistory
											.setSupervisionOrderConditionRelValueId(condRelVal.getOID());
										condRelVal.insertSupervisionOrderRelValHistories(relValHistory);
									}
								}
							}
						}
					}
				}
			}
		}
		// removed conditions
		Iterator remCondIter = removedConditions.iterator();
		SupervisionOrderCondition soCondition = null;
		List aList = null;
		Iterator i = null;
		Condition specialCondition = null;
		while (remCondIter.hasNext())
		{
			SupervisionOrderConditionRel supervisionOrderConditionRel = (SupervisionOrderConditionRel) existingCondToRelMap
					.get(remCondIter.next());
			//Delete from CSCONDITION if special condition not attached to a previous order version.
			soCondition = supervisionOrderConditionRel.getSupervisionOrderCondition();
			if (soCondition != null && soCondition.getIsSpecialCondition()){
				i = SupervisionOrderCondition.findAll("conditionId", soCondition.getConditionId());
				aList = CollectionUtil.iteratorToList(i);
				if (aList.size() == 1){
					specialCondition = soCondition.getCondition();
					specialCondition.delete();
				}
			}
			supervisionOrder.removeSupervisionOrderConditionRels(supervisionOrderConditionRel);
		}
		return supervisionOrder;
	}
	public static String calculateDate(SupervisionOrder supervisionOrder, ConditionCourtVariableElement condCrtVarElement) {
		String dateString = null;
		if (supervisionOrder.getCaseSupervisionBeginDate() != null){

			Code aCode = Code.find(condCrtVarElement.getElementCodeTableId(), condCrtVarElement.getValue());
			if (aCode != null){
				dateString = SupervisionOrderHelper.calculateDate(supervisionOrder.getCaseSupervisionBeginDate(), aCode.getCode());
			} else {
				dateString = DateUtil.dateToString(supervisionOrder.getCaseSupervisionBeginDate(), DateUtil.DATE_FMT_1);
			}
		}
		return dateString;
	}
	static public SupervisionOrder cloneOrder(SupervisionOrder origOrder)
	{
		SupervisionOrder supervisionOrder = new SupervisionOrder();
		supervisionOrder.setAgencyId(origOrder.getAgencyId());
		supervisionOrder.setCriminalCaseId(origOrder.getCriminalCaseId());
		CriminalCase theCase = CriminalCase.find(origOrder.getCriminalCaseId());
		if (theCase != null){
			supervisionOrder.setCurrentCourtId(theCase.getCourtId());
			supervisionOrder.setOrderCourtId(theCase.getCourtId());
		} else {
			supervisionOrder.setCurrentCourtId(origOrder.getCurrentCourtId());
			supervisionOrder.setOrderCourtId(origOrder.getCurrentCourtId());
		}

		supervisionOrder.setDefendantId(origOrder.getDefendantId());
		supervisionOrder.setJuvenileCourtId(origOrder.getJuvenileCourtId());
		supervisionOrder.setJuvSupervisionBeginDate(origOrder.getJuvSupervisionBeginDate());
		supervisionOrder.setJuvSupervisionLenDays(origOrder.getJuvSupervisionLenDays());
		supervisionOrder.setJuvSupervisionLenMonths(origOrder.getJuvSupervisionLenMonths());
		supervisionOrder.setJuvSupervisionLenYears(origOrder.getJuvSupervisionLenYears());
		supervisionOrder.setLimitedSupervisionPeriod(origOrder.isLimitedSupervisionPeriod());
		supervisionOrder.setParentSupervisionOrderId(origOrder.getParentSupervisionOrderId());
		supervisionOrder.setPlea(origOrder.getPlea());
		supervisionOrder.setPrintedName(origOrder.getPrintedName());
		supervisionOrder.setPrintedOffenseDesc(origOrder.getPrintedOffenseDesc());

		supervisionOrder.setLimitedSupervisionBeginDate(origOrder.getLimitedSupervisionBeginDate());
		supervisionOrder.setLimitedSupervisionEndDate(origOrder.getLimitedSupervisionEndDate());
		supervisionOrder.setSuggestedOrderId(origOrder.getSuggestedOrderId());
		supervisionOrder.setOrderInProgress(origOrder.isOrderInProgress());
		supervisionOrder.setOrderChainNum(origOrder.getOrderChainNum());
		supervisionOrder.setJuvenileCourtId(origOrder.getJuvenileCourtId());
		supervisionOrder.setSpecialCourtCd(origOrder.getSpecialCourtCd());
		supervisionOrder.setIsHistoricalOrder(origOrder.getIsHistoricalOrder());
		return supervisionOrder;
	}
	/**
	 * @return 
	 * @param reqEvent
	 */
	static public SupervisionOrder create(CreateSupervisionOrderEvent reqEvent)
	{
		//long timeStart = System.currentTimeMillis();
		SupervisionOrder supervisionOrder = null;
		if (!reqEvent.getVersionType().equals(SupervisionConstants.ORIGINAL)){
			supervisionOrder = SupervisionOrder.getOriginalSupervisionOrder(reqEvent.getAgencyId(), reqEvent.getCriminalCaseId());
		}
			if (!reqEvent.getVersionType().equals(SupervisionConstants.ORIGINAL)
				&& supervisionOrder != null 
				&& supervisionOrder.getOrigJudgeLastName() != null 
				&& !PDConstants.BLANK.equals(supervisionOrder.getOrigJudgeLastName())){
			reqEvent.setOrigJudgeFirstName(supervisionOrder.getOrigJudgeFirstName());
			reqEvent.setOrigJudgeLastName(supervisionOrder.getOrigJudgeLastName());
		}
		// check to see if there is already an original SupervisionOrder for this case
//		if (SupervisionOrder.getOriginalSupervisionOrder(reqEvent.getAgencyId(), reqEvent.getCriminalCaseId()) == null)
		// new originals can be obtained if the original supervision order is in Active status
		reqEvent.setOrderChainNum(1); // Default is 1
		if (supervisionOrder == null || supervisionOrder.getOrderStatusId().equals(PDCodeTableConstants.STATUS_INACTIVE_ID) || supervisionOrder.getOrderStatusId().equals(PDCodeTableConstants.STATUS_ACTIVE_ID))
		{
			// create supervision order
			if(supervisionOrder!=null){
				reqEvent.setOrderChainNum(supervisionOrder.getOrderChainNum());
				
			}
			supervisionOrder = createOrder(reqEvent);
			//long timeEnd = System.currentTimeMillis();
			//long diff = timeEnd - timeStart;
			//System.out.println("********  time in createOrder(): " + diff);
			// create other associated objects
			if (reqEvent.getSuggestedOrderId() != null)
			{
				Map variableElementReferenceMap = reqEvent.getVariableElementReferenceMap();
				Map updatedRefVarsMap = SupervisionOrderReferenceVariableHelper.getNewValuesForVolatileRefVars(reqEvent.getAgencyId(), supervisionOrder);
				variableElementReferenceMap = SupervisionOrderReferenceVariableHelper.updateRefVars(variableElementReferenceMap, updatedRefVarsMap);;
				Map conditionMap = new HashMap();
				Collection sugOrderConditions = new ArrayList();
				//timeStart = System.currentTimeMillis();
				SuggestedOrderHelper.getSuggestedOrderConditions(reqEvent.getSuggestedOrderId(), conditionMap,
						sugOrderConditions);
				//timeEnd = System.currentTimeMillis();
				//diff = timeEnd - timeStart;
				//System.out.println("********  time in getSuggestedOrderConditions(): " + diff);
				// retrieve variable elements for the given court
				//timeStart = System.currentTimeMillis();
				List conditionIds = new ArrayList();
				for (Iterator iter = conditionMap.keySet().iterator(); iter.hasNext();)
				{
					conditionIds.add(iter.next());
				}
				Map conditionVarElementMap = getVariableElements(reqEvent.getOrderCourtId(), reqEvent.getAgencyId(),
						conditionIds);
				//timeEnd = System.currentTimeMillis();
				//diff = timeEnd - timeStart;
				//System.out.println("********  time in getVariableElements(): " + diff);
				if (sugOrderConditions.size() > 0)
				{ // if suggested order has atleast one condition
					//timeStart = System.currentTimeMillis();
					// sort it by sequence num
					Collections.sort((List) sugOrderConditions, SuggestedOrderCondition.SeqNumComparator);
					int curSeqNum = 1;
					
					for (Iterator conditionIter = sugOrderConditions.iterator(); conditionIter.hasNext();)
					{
						List condRelVals = new ArrayList();
						SuggestedOrderCondition suggOrderCondition = (SuggestedOrderCondition) conditionIter.next();
						Condition condition = (Condition) conditionMap.get(suggOrderCondition.getConditionId());
						// create SupervisionOrderCondition
						SupervisionOrderCondition supOrderCondition = SupervisionOrderCondition.create(condition);
						supOrderCondition.setResolvedDescription(condition.getDescription());
						// create SupervisionOrderConditionRel
						SupervisionOrderConditionRel supervisionOrderConditionRel = new SupervisionOrderConditionRel();
						//                    supervisionOrderConditionRel.setSupervisionOrderId(supervisionOrder.getOID().toString());
						//timeStart = System.currentTimeMillis();
						supervisionOrderConditionRel.setSupervisionOrderCondition(supOrderCondition);
						//timeEnd = System.currentTimeMillis();
						//diff = timeEnd - timeStart;
						//System.out.println("********  time in SupervisionOrderCond commit: " + diff
						//		+ "for conditionId: " + condition.getOID());
						supervisionOrderConditionRel.setSequenceNum(curSeqNum++);
						//timeStart = System.currentTimeMillis();
						supervisionOrder.insertSupervisionOrderConditionRels(supervisionOrderConditionRel);
						//timeEnd = System.currentTimeMillis();
						//diff = timeEnd - timeStart;
//						System.out.println("********  time in SupervisionOrder commit: " + diff + "for conditionId: "
//								+ condition.getOID());
						Collection conditionCourtVariableElements = (Collection) conditionVarElementMap.get(condition
								.getOID());
						if (conditionCourtVariableElements != null)
						{
							//timeStart = System.currentTimeMillis();
							for (Iterator iter = conditionCourtVariableElements.iterator(); iter.hasNext();)
							{
								ConditionCourtVariableElement condCrtVarElement = (ConditionCourtVariableElement) iter
										.next();
								if (condCrtVarElement.getVariableElementId() != null)
								{
									SupervisionOrderConditionRelValue supervisionOrderConditionRelValue = SupervisionOrderConditionRelValue
											.create(condCrtVarElement, variableElementReferenceMap);
									if (condCrtVarElement.getIsCalculated()){
										//String aStringDate = calculateDate(supervisionOrder, supervisionOrderConditionRelValue, condCrtVarElement);
										String aStringDate = calculateDate(supervisionOrder, condCrtVarElement);

										supervisionOrderConditionRelValue.setValue(aStringDate);
										condCrtVarElement.setValue(aStringDate);
									}
									supervisionOrderConditionRel
											.insertOrderConditionRelValues(supervisionOrderConditionRelValue);
									//                                	new Home().bind(supervisionOrderConditionRelValue);
									if (condCrtVarElement.getIsReference())
									{
										SupervisionOrderRelValueHistory relValHistory = SupervisionOrderRelValueHistory
												.create(PDCodeTableConstants.TRANS_TYPE_ADD, "");
										//                                relValHistory.setSupervisionOrderConditionRelValueId(supervisionOrderConditionRelValue.getOID().toString());
										supervisionOrderConditionRelValue.insertSupervisionOrderRelValHistories(relValHistory);
									}
									condRelVals.add(condCrtVarElement);
								}
							}
							String resolvedDesc = SupervisionOrderHelper.createResolvedDescription(condition.getDescription(), condRelVals);
							supOrderCondition.setResolvedDescription(resolvedDesc);
							//timeEnd = System.currentTimeMillis();
							//diff = timeEnd - timeStart;
							//System.out.println("********  time in varElement loop: " + diff);
						}
						// post response event back to ui to display conditions
						SuggestedOrderHelper.postConditionDetailRespEvent(condition, suggOrderCondition.getSeqNum(),
								reqEvent.getOrderCourtId(), variableElementReferenceMap, conditionVarElementMap);
					}
					//timeEnd = System.currentTimeMillis();
					//diff = timeEnd - timeStart;
					//System.out.println("********  associated objects loop: " + diff);
				}
				else
				{ // if Suggested Order is blank
					// we need to bind this SupervisionOrder when there is no condition to insert (suggested order is blank)
					// as OrderId needs to be passed in the response event
					new Home().bind(supervisionOrder);
				} 
			} else if (supervisionOrder.getIsHistoricalOrder()){
			    //An historical order should always have conditions
			    supervisionOrder.setOrderStatusId(reqEvent.getHistoricalOrderStatus());
			    if (reqEvent.getConditions() != null && reqEvent.getConditions().size() > 0){
			        Iterator iter = reqEvent.getConditions().iterator();
			        SupervisionOrderConditionEvent orderCondEvent = null;
			        SupervisionOrderConditionRel supervisionOrderConditionRel = null;
			        Condition condition = null;
			        while (iter.hasNext()){
			            orderCondEvent = (SupervisionOrderConditionEvent) iter.next();
			            condition = supervisionOrder.createSpecialConditionForHistoricalOrder(orderCondEvent);
//		        		conditionMap.put((String) condition.getOID(), condition);
			            supervisionOrderConditionRel = new SupervisionOrderConditionRel();
			            supervisionOrderConditionRel.setSupervisionOrderId((String) supervisionOrder.getOID());
			            supervisionOrderConditionRel.setSequenceNum(orderCondEvent.getSequenceNum());
		        
			        	SupervisionOrderCondition supOrderCondition = SupervisionOrderCondition.create(condition);
			        	supOrderCondition.setResolvedDescription(orderCondEvent.getResolvedDescription());
			        	supervisionOrderConditionRel.setSupervisionOrderCondition(supOrderCondition);
			        	// insert supervisionorderRel into SupervisionOrder
			        	supervisionOrder.insertSupervisionOrderConditionRels(supervisionOrderConditionRel);
			        }
			    } else {
			        new Home().bind(supervisionOrder);
			    }
			} else {
		        new Home().bind(supervisionOrder);  //There may be no suggested order if printing signature page.
		    }
		}  else {
		    supervisionOrder = null;
		}
		return supervisionOrder;
	}
	static private void createExistingConditionMaps(Collection conditionRelEvents, Map existingCondMap,
			Map existingCondToRelMap, Map relToValuesMap)
	{
		List conditionRelIds = new ArrayList();
		List orderConditionIds = new ArrayList();
		Map conditionRelIdMap = new HashMap();
		// performance fix. add orderConditionIds into a collection and execute
		// one query insted of executing multiple queries
		if (conditionRelEvents != null)
		{
			for (Iterator i = conditionRelEvents.iterator(); i.hasNext();)
			{
				SupervisionOrderConditionRel orderCondRel = (SupervisionOrderConditionRel) i.next();
				orderConditionIds.add(orderCondRel.getSupervisionOrderConditionId());
				conditionRelIdMap.put(orderCondRel.getSupervisionOrderConditionId(), orderCondRel);
			}
		}
		// get orderConditions
		if (orderConditionIds.size() > 0)
		{
			Iterator orderConditions = SupervisionOrderCondition.findAllByIds(orderConditionIds);
			while (orderConditions.hasNext())
			{
				SupervisionOrderCondition orderCondition = (SupervisionOrderCondition) orderConditions.next();
				SupervisionOrderConditionRel orderCondRel = (SupervisionOrderConditionRel) conditionRelIdMap
						.get(orderCondition.getOID());
				if (existingCondToRelMap != null)
				{
					existingCondToRelMap.put(orderCondition.getConditionId(), orderCondRel);
				}
				existingCondMap.put(orderCondition.getConditionId(), orderCondition);
				conditionRelIds.add(orderCondRel.getOID());
			}
		}
		if (relToValuesMap != null && conditionRelIds.size() > 0)
		{
			Iterator orderValues = SupervisionOrderConditionRelValue.findAllByRelIds(conditionRelIds);
			while (orderValues.hasNext())
			{
				SupervisionOrderConditionRelValue orderRelValue = (SupervisionOrderConditionRelValue) orderValues
						.next();
				List orderRelValues = (List) relToValuesMap.get(orderRelValue.getSupervisionOrderConditionRelId());
				if (orderRelValues == null)
				{
					orderRelValues = new ArrayList();
					relToValuesMap.put(orderRelValue.getSupervisionOrderConditionRelId(), orderRelValues);
				}
				orderRelValues.add(orderRelValue);
			}
		}
	}
	static private Map createExistingRelValueMap(Collection conditionRelVals)
	{
		Map existingRelVals = new HashMap();
		if (conditionRelVals != null)
		{
			for (Iterator i = conditionRelVals.iterator(); i.hasNext();)
			{
				SupervisionOrderConditionRelValue relVal = (SupervisionOrderConditionRelValue) i.next();
				existingRelVals.put(relVal.getVariableElementTypeId(), relVal);
			}
		}
		return existingRelVals;
	}

	static private SupervisionOrder createOrder(CreateSupervisionOrderEvent reqEvent)
	{
		SupervisionOrder supervisionOrder = null;
		supervisionOrder = new SupervisionOrder();
		//the following was added for the "Print Signature Page" being at beginning of workflow.
		supervisionOrder.setOrderSignedByDefendant(reqEvent.isDefendantSignatureInd());
		supervisionOrder.setOrderSignedByDefendantDate(reqEvent.getDefendantSignatureDate());
		supervisionOrder.setOrderSignedByJudgeDate(reqEvent.getJudgeSignatureDate());
		supervisionOrder.setOrderPresentorFirstName(reqEvent.getPresentorFirstName());
		supervisionOrder.setOrderPresentorLastName(reqEvent.getPresentorLastName());
		supervisionOrder.setSignedByFirstName(reqEvent.getSignorFirstName());
		supervisionOrder.setSignedByLastName(reqEvent.getSignorLastName());
		supervisionOrder.setOrderSignedDate(reqEvent.getOrderSignatureDate());
		supervisionOrder.setAgencyId(reqEvent.getAgencyId());
		supervisionOrder.setCriminalCaseId(reqEvent.getCriminalCaseId());
		supervisionOrder.setDefendantId(reqEvent.getDefendantId());
		supervisionOrder.setLimitedSupervisionPeriod(reqEvent.getLimitedSupervisionPeriod());
		supervisionOrder.setOrderStatusId(reqEvent.getStatus());
		supervisionOrder.setOrderTitleId(reqEvent.getOrderTitle());
		supervisionOrder.setOrigJudgeFirstName(reqEvent.getOrigJudgeFirstName());
		supervisionOrder.setOrigJudgeLastName(reqEvent.getOrigJudgeLastName());
		supervisionOrder.setParentSupervisionOrderId(reqEvent.getParentSupervisionOrderId());
		supervisionOrder.setVersionTypeId(reqEvent.getVersionType());
		supervisionOrder.setOrderCourtId(reqEvent.getOrderCourtId());
		if (reqEvent.getCurrentCourtId() != null){
			supervisionOrder.setCurrentCourtId(reqEvent.getCurrentCourtId());
		} else {
			supervisionOrder.setCurrentCourtId(reqEvent.getOrderCourtId());
		}
		supervisionOrder.setFineAmount(reqEvent.getFineAmount());
		supervisionOrder.setPlea(reqEvent.getPlea());
		supervisionOrder.setPrintedName(reqEvent.getPrintedName());
		supervisionOrder.setJuvenileCourtId(reqEvent.getJuvCourtId());
		supervisionOrder.setJuvSupervisionBeginDate(reqEvent.getJuvSupervisionBeginDate());
		supervisionOrder.setJuvSupervisionLenDays(reqEvent.getJuvSupervisionLengthDays());
		supervisionOrder.setJuvSupervisionLenMonths(reqEvent.getJuvSupervisionLengthMonths());
		supervisionOrder.setJuvSupervisionLenYears(reqEvent.getJuvSupervisionLengthYears());
		supervisionOrder.setSpecialCourtCd(reqEvent.getSpecialCourtCd());
		supervisionOrder.setPrintedOffenseDesc(reqEvent.getPrintedOffenseDesc());
		supervisionOrder.setLimitedSupervisionBeginDate(reqEvent.getLimitedSupervisionBeginDate());
		supervisionOrder.setLimitedSupervisionEndDate(reqEvent.getLimitedSupervisionEndDate());
		supervisionOrder.setSuggestedOrderId(reqEvent.getSuggestedOrderId());
		supervisionOrder.setOrderInProgress(true);
		supervisionOrder.setIsHistoricalOrder(reqEvent.getIsHistoricalOrder());
		supervisionOrder.setOrderChainNum(reqEvent.getOrderChainNum());

		if (reqEvent.getIsHistoricalOrder() || reqEvent.isMigratedOrder()) {	
		    supervisionOrder.setVersionNum(OrderVersionSequence.next(reqEvent.getCriminalCaseId(), reqEvent
		            .getVersionType(), reqEvent.getVersionNum(),reqEvent.getOrderChainNum()));
		} else {
		    supervisionOrder.setVersionNum(OrderVersionSequence.next(reqEvent.getCriminalCaseId(), reqEvent
		            .getVersionType(),reqEvent.getOrderChainNum()));
		}
		//supervisionOrder.setOffenseId(reqEvent.getOffenseId());
		supervisionOrder.setComments(reqEvent.getComments());
		supervisionOrder.setConfinementLengthMonths(reqEvent.getConfinementLengthMonths());
		supervisionOrder.setConfinementLengthDays(reqEvent.getConfinementLengthDays());
		supervisionOrder.setConfinementLengthYears(reqEvent.getConfinementLengthYears());
		supervisionOrder.setSupervisionLengthMonths(reqEvent.getSupervisionLengthMonths());
		supervisionOrder.setSupervisionLengthDays(reqEvent.getSupervisionLengthDays());
		supervisionOrder.setSupervisionLengthYears(reqEvent.getSupervisionLengthYears());
		//supervisionOrder.setDispositionTypeId(reqEvent.getDispositionTypeId());
		if (supervisionOrder.getIsHistoricalOrder()){
			supervisionOrder.setDispositionTypeId(PDCodeTableConstants.PRETRIAL_INTERVENTION);
		} else {
			supervisionOrder.setDispositionTypeId(reqEvent.getDispositionTypeId());
		}
		supervisionOrder.setCaseSupervisionBeginDate(reqEvent.getCaseSupervisionBeginDate());
		supervisionOrder.setCaseSupervisionEndDate(reqEvent.getCaseSupervisionEndDate());
//		if (PDCodeTableConstants.VERSION_TYPE_ID_AMMENDED.equals(supervisionOrder.getVersionTypeId())
//				|| PDCodeTableConstants.VERSION_TYPE_ID_MODIFIED.equals(supervisionOrder.getVersionTypeId())){
		if (reqEvent.getSummaryOfChanges() != null
				&& !reqEvent.getSummaryOfChanges().equals(PDConstants.BLANK)){	
			supervisionOrder.setSummaryChanges(reqEvent.getSummaryOfChanges());
		}
		return supervisionOrder;
	}

	/**
     * @param historicalOrder
     */
    public static void createOriginalFromHistoricalOrder(SupervisionOrder historicalOrder) {
        SupervisionOrder originalOrder = new SupervisionOrder();
        originalOrder.setOrderChainNum(1);
        originalOrder.setActivationDate(historicalOrder.getActivationDate());
        originalOrder.setAgencyId(historicalOrder.getAgencyId());
        originalOrder.setCaseSupervisionBeginDate(historicalOrder.getCaseSupervisionBeginDate());
        originalOrder.setCaseSupervisionEndDate(historicalOrder.getCaseSupervisionEndDate());
        originalOrder.setConfinementLengthMonths(historicalOrder.getConfinementLengthMonths());
        originalOrder.setConfinementLengthDays(historicalOrder.getConfinementLengthDays());
        originalOrder.setConfinementLengthYears(historicalOrder.getConfinementLengthYears());
        originalOrder.setCriminalCaseId(historicalOrder.getCriminalCaseId());
        originalOrder.setCurrentCourtId(historicalOrder.getCurrentCourtId());
        originalOrder.setDefendantId(historicalOrder.getDefendantId());
        originalOrder.setDispositionTypeId(historicalOrder.getDispositionTypeId());
        originalOrder.setFineAmount(historicalOrder.getFineAmount());
        originalOrder.setInactivationDate(historicalOrder.getActivationDate());
        originalOrder.setIsHistoricalOrder(historicalOrder.getIsHistoricalOrder());
        originalOrder.setLimitedSupervisionBeginDate(historicalOrder.getLimitedSupervisionBeginDate());
        originalOrder.setLimitedSupervisionEndDate(historicalOrder.getLimitedSupervisionEndDate());
        originalOrder.setLimitedSupervisionPeriod(historicalOrder.isLimitedSupervisionPeriod());
        //originalOrder.setOffenseId(historicalOrder.getOffenseId());
        originalOrder.setOrderCourtId(historicalOrder.getOrderCourtId());
        originalOrder.setOrderFiledDate(historicalOrder.getOrderFiledDate());
        originalOrder.setOrderInProgress(false);
        originalOrder.setOrderPresentorFirstName(historicalOrder.getOrderPresentorFirstName());
        originalOrder.setOrderPresentorId(historicalOrder.getOrderPresentorId());
        originalOrder.setOrderPresentorLastName(historicalOrder.getOrderPresentorLastName());
        originalOrder.setOrderSignedByDefendant(historicalOrder.isOrderSignedByDefendant());
        originalOrder.setOrderSignedByDefendantDate(historicalOrder.getOrderSignedByDefendantDate());
        originalOrder.setOrderSignedByJudgeDate(historicalOrder.getOrderSignedByJudgeDate());
        originalOrder.setOrderSignedDate(historicalOrder.getOrderSignedDate());
        originalOrder.setOrderStatusId(PDCodeTableConstants.STATUS_INACTIVE);
        originalOrder.setOrderTitleId(historicalOrder.getOrderTitleId());
        originalOrder.setPlea(historicalOrder.getPlea());
        originalOrder.setPrintSpanish(historicalOrder.isPrintSpanish());
        originalOrder.setPrintTemplateId(historicalOrder.getPrintTemplateId());
        originalOrder.setRevisionDate(historicalOrder.getRevisionDate());
        originalOrder.setSignedByFirstName(historicalOrder.getSignedByFirstName());
        originalOrder.setSignedByLastName(historicalOrder.getSignedByLastName());
        originalOrder.setSignedByTypeId(historicalOrder.getSignedByTypeId());
        originalOrder.setSummaryChanges(historicalOrder.getSummaryChanges());
        originalOrder.setSupervisionLengthMonths(historicalOrder.getSupervisionLengthMonths());
        originalOrder.setSupervisionLengthDays(historicalOrder.getSupervisionLengthDays());
        originalOrder.setSupervisionLengthYears(historicalOrder.getSupervisionLengthYears());
        originalOrder.setSupervisionPeriodId(historicalOrder.getSupervisionPeriodId());
        //originalOrder.setUpdateDate(historicalOrder.getUpdateDate()); 
        originalOrder.setStatusChangeDate(historicalOrder.getStatusChangeDate()); 
        originalOrder.setVersionNum(OrderVersionSequence.next(historicalOrder.getCriminalCaseId(), PDCodeTableConstants.VERSION_TYPE_ID_ORIGINAL,1));
        
        originalOrder.setVersionTypeId(PDCodeTableConstants.VERSION_TYPE_ID_ORIGINAL);
        
        Collection histCondRels = historicalOrder.getSupervisionOrderConditionRels();
        Iterator iter = histCondRels.iterator();
        if (iter != null && iter.hasNext()){
            SupervisionOrderConditionRel origCondRel = null;
            SupervisionOrderConditionRelValue origCondRelVal = null;
            SupervisionOrderConditionRel histCondRel = null;
            SupervisionOrderConditionRelValue histCondRelVal = null;
            //Collection histCondRelVals = null;
            Iterator iter2 = null;

            while (iter.hasNext()){
                histCondRel = (SupervisionOrderConditionRel) iter.next();
                origCondRel = new SupervisionOrderConditionRel();
                origCondRel.setSequenceNum(histCondRel.getSequenceNum());
                origCondRel.setSupervisionOrderConditionId(histCondRel.getSupervisionOrderConditionId());
                origCondRel.setWasDeleted(histCondRel.getWasDeleted());
                originalOrder.insertSupervisionOrderConditionRels(origCondRel);
                //At the time this was written historical orders did not have variable element values. Instead they 
                //were always special conditions.  I'm including this code just in case requirements change.

                iter2 = histCondRel.getOrderConditionRelValues().iterator();
                if (iter2 != null && iter.hasNext()){
                    while (iter2.hasNext()){
                        histCondRelVal = (SupervisionOrderConditionRelValue) iter2.next();
                        origCondRelVal = new SupervisionOrderConditionRelValue();
                        origCondRelVal.setIsFixed(histCondRelVal.getIsFixed());
                        origCondRelVal.setIsRefreshable(histCondRelVal.getIsRefreshable());
                        origCondRel.insertOrderConditionRelValues(origCondRelVal);
                    }
                } 
            }
        } 
    }
	static private StringSet existCondSet(Collection conditionIds)
	{
		StringSet set = new StringSet(conditionIds, new StringSet.Stringizer() {
			public String toString(Object obj)
			{
				return obj.toString();
			}
		});
		return set;
	}
	/**
	 * @return 
	 * @param oid
	 */
	static public SupervisionOrder find(String oid)
	{
		IHome home = new Home();
		SupervisionOrder order = (SupervisionOrder) home.find(oid, SupervisionOrder.class);
		return order;
	}

	/**
	 * @return 
	 * @param event
	 * @roseuid 438F22CA0277
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, SupervisionOrder.class);
	}

	/**
	 * @return 
	 * @param attributeName
	 * @param attributeValue
	 */
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		return home.findAll(attributeName, attributeValue, SupervisionOrder.class);
	}

	/**
	 * @return 
	 * @param event
	 * @roseuid 438F22CA0277
	 * @methodInvocation setConditionId
	 * @methodInvocation findAll
	 * @methodInvocation setConditionId
	 * @methodInvocation findAll
	 */
	static public Iterator findAllByCondition(String conditionId)
	{
		GetSupervisionOrdersFromConditionEvent reqEvent = new GetSupervisionOrdersFromConditionEvent();
		reqEvent.setConditionId(conditionId);
		Iterator orders = findAll(reqEvent);
		return orders;
	}

	/**
	 * @return 
	 * @param event
	 * @roseuid 438F22CA0277
	 * @methodInvocation setPeriodIds
	 * @methodInvocation findAll
	 * @methodInvocation setPeriodIds
	 * @methodInvocation findAll
	 */
	static public Iterator findAllByPeriodIds(List periodIds)
	{
		GetOrdersForSupervisionPeriodIdsEvent getSupervisionPeriodEvent = new GetOrdersForSupervisionPeriodIdsEvent();
		getSupervisionPeriodEvent.setPeriodIds(periodIds);
		Iterator iter = findAll(getSupervisionPeriodEvent);
		return iter;
	}

	/**
	 * @return 
	 * @param event
	 * @roseuid 438F22CA0277
	 this will get all the supervision order for the given list of case numbers
	 and list of agencies.
	 create a map of agency to supervision orders.
	 */
	static public Iterator GetOrdersForCaseIds(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, SupervisionOrder.class);
	}

	static public Iterator GetOrdersForSpnSupPeriodIdsQuery(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, SupervisionOrder.class);
	}
	
	/**
	 * @param agencyId
	 * @param criminalCaseId
	 * @return 
	 * @methodInvocation setAgencyId
	 * @methodInvocation setCriminalCaseId
	 * @methodInvocation findAll
	 * @methodInvocation setAgencyId
	 * @methodInvocation setCriminalCaseId
	 * @methodInvocation findAll
	 */
	static public SupervisionOrder getOriginalSupervisionOrder(String agencyId, String criminalCaseId)
	{
		GetOriginalSupervisionOrderEvent anEvent = new GetOriginalSupervisionOrderEvent();
		anEvent.setAgencyId(agencyId);
		anEvent.setCriminalCaseId(criminalCaseId);
		Iterator iter = SupervisionOrder.findAll(anEvent);
		SupervisionOrder latestOriginalOrder = null;
		SupervisionOrder thisOrder = null;
		
		// THIS METHOD ASSUMES there is only one original order and must be modified without breaking anything to handle
		// more than one original order in any state, incomplete, draft, active, inactive.
		// USE state chart
		//Scen 1:  1 Original in Incomplete, Draft, or Active Chain 1
//		  Scen 2:  1 Original in Active, 1 Modified or Amended  Incomplet, Draft, or Pending
		//Scen 3:  1 Active Modified or Amended Order Chain 1, 1 Inactive Original Chain 1
		//Scen 4:  1 Active Original Order Chain 1, 1 Original Incomplete, Draft, Pending Chain 1
		//Scen 5:  1 Active Original Order Chain 2, 1 Inactive Order Chain 1
		//Scen 6:  1 Active Modified Amended Order Chain 2, 1 Inactive Orginal ORder Chain 2, 1 Inactive Order Chain 1 
		
		if (iter != null && iter.hasNext())
		{
			while (iter.hasNext()){
			    thisOrder = (SupervisionOrder) iter.next();
			    if (latestOriginalOrder == null){
			        latestOriginalOrder = thisOrder;
			    }
			    else{
			    	if(isDraftIncompletePending(latestOriginalOrder.getOrderStatusId())){
			    		// latest is a draft/pending/incomplete
			    		// this means the only valid values for thisOrder are Active, Incomplete, and Restated
			    		// all of which indicate that this order may be the true original order
			    		if(thisOrder.getOrderChainNum()>latestOriginalOrder.getOrderChainNum()){
				    		// This is suppossedly not possible since a the latest order has the highest order chain value
				    	}
				    	else if(thisOrder.getOrderChainNum()==latestOriginalOrder.getOrderChainNum()){
				    		// This means that there is an order of the same chain meaning this is the original order of this chain
				    		// and it must be the active order
				    		if(thisOrder.getActivationDate() != null && thisOrder.getActivationDate().after(latestOriginalOrder.getActivationDate())){
				    			latestOriginalOrder=thisOrder;
				    		}
				    	}
				    	else if(thisOrder.getOrderChainNum()<latestOriginalOrder.getOrderChainNum()){
				    		// we can skip this because if the order chain num of the latest is greate then the latest original hasn't been found
				    	}
			    	}
			    	else{
			    		//latest is either active, inactive, or withdraw
			    		// thisOrder can be anythign active, inactive, or restated, draft, pending, incomplete
			    		if(thisOrder.getOrderChainNum()>latestOriginalOrder.getOrderChainNum()){
				    		latestOriginalOrder=thisOrder;
				    	}
				    	else if(thisOrder.getOrderChainNum()==latestOriginalOrder.getOrderChainNum()){
				    		// This means that there is an order of the same chain meaning we need to check which order is the latest order
				    		// and it must be the active order
				    		if(isDraftIncompletePending(thisOrder.getOrderStatusId())){
				    			// currentOrder is pending, draft, or incomplete 
				    			// this means that the thisOrder is not the latest original
				    		}
				    		else{
				    			// thisOrder is active, inactive, or withdraw
				    			if(thisOrder.getActivationDate() != null && thisOrder.getActivationDate().after(latestOriginalOrder.getActivationDate())){
					    			latestOriginalOrder=thisOrder;
					    		}
				    		}
				    	}
				    	else if(thisOrder.getOrderChainNum()<latestOriginalOrder.getOrderChainNum()){
				    		// we can skip this because if the order chain num of the latest is greate then the latest original hasn't been found
				    	}
			    	}// END ELSE
			    } // END ELSE
			} // END WHILE
		}// END IF
		return latestOriginalOrder;
	}

	public static Map getPartiesbyIds(String defendantIds){
        GetTasksByDefendantIdsEvent tEvent = new GetTasksByDefendantIdsEvent();
		tEvent.setDefendantIds(defendantIds);
		Iterator partyIterator = Party.findAll(tEvent);
		Map partyMap = new HashMap();
		while(partyIterator.hasNext()){
			Party party = (Party) partyIterator.next();
			if(!partyMap.containsKey(party.getSpn())){
				partyMap.put(party.getSpn(), party);
			}
		}
		return partyMap;
	}

	static public Map getVariableElements(String courtId, String agencyId, Collection conditionIds)
	{
		Map conditionVarElementMap = new HashMap();
		Iterator conditionCourtVariableElements = ConditionCourtVariableElement
				.findAll(courtId, agencyId, conditionIds);
		while (conditionCourtVariableElements.hasNext())
		{
			ConditionCourtVariableElement conditionCourtVariableElement = (ConditionCourtVariableElement) conditionCourtVariableElements
					.next();
			ArrayList varElements = (ArrayList) conditionVarElementMap.get(conditionCourtVariableElement.getObjectId());
			if (varElements == null)
			{
				varElements = new ArrayList();
				conditionVarElementMap.put(conditionCourtVariableElement.getObjectId(), varElements);
			}
			varElements.add(conditionCourtVariableElement);
		}
		return conditionVarElementMap;
	}

	private static boolean isDraftIncompletePending(String orderStatusId){
		boolean retVal=false;
		if(orderStatusId!=null){
			if(orderStatusId.equals(PDCodeTableConstants.STATUS_INCOMPLETE_ID)){
				retVal=true;
			}
			else if(orderStatusId.equals(PDCodeTableConstants.STATUS_PENDING_ID)){
				retVal=true;
			}
			else if(orderStatusId.equals(PDCodeTableConstants.STATUS_DRAFT_ID)){
				retVal=true;
			}
		}
		return retVal;
	}

	static private StringSet newCondSet(Collection conditionRels)
	{
		StringSet set = new StringSet(conditionRels, new StringSet.Stringizer() {
			public String toString(Object obj)
			{
				SupervisionOrderConditionEvent orderCondEvent = (SupervisionOrderConditionEvent) obj;
				return orderCondEvent.getConditionId();
			}
		});
		return set;
	}

	static public SupervisionOrder update(UpdateSupervisionOrderEvent reqEvent) throws CloneNotSupportedException
	{
		SupervisionOrder supervisionOrder = null;
		//String orderId = reqEvent.getOrderId();
		supervisionOrder = find(reqEvent.getOrderId());
		// check for duplicate order version create
		if (reqEvent.isNewVersion() 
				&& SupervisionOrder.validateNewOrderVersion(supervisionOrder.getAgencyId(), supervisionOrder
						.getCriminalCaseId(),supervisionOrder.getOrderChainNum()))
		{
			// there is already a version existing for this case+agency in Incomplete/draft/pending state
			return null;
		}
		//-------------------------conditions------------------------//
		Collection supervisionOrderConditionRels = supervisionOrder.getSupervisionOrderConditionRels();
		// performance fix. create various maps before hand to avoid multiple db
		// queries
		Map existingCondMap = new HashMap();
		Map existingCondToRelMap = new HashMap();
		Map relToValuesMap = new HashMap();
		createExistingConditionMaps(supervisionOrderConditionRels, existingCondMap, existingCondToRelMap,
				relToValuesMap);
		StringSet exConditions = existCondSet(existingCondMap.keySet());
		StringSet passedConditions = newCondSet(reqEvent.getConditions());
		//Set newConditions = passedConditions.complement(exConditions);
		Set removedConditions = exConditions.complement(passedConditions);
		// get Condition Objects from new condition ids
		Map conditionMap = new HashMap();
		if (passedConditions.size() > 0)
		{
			Iterator condObjects = Condition.findAllByIds(passedConditions, true);
			while (condObjects.hasNext())
			{
				Condition condition = (Condition) condObjects.next();
				conditionMap.put(condition.getOID(), condition);
			}
		}
//DAG - Don't understand why this was here.
//		String amendedOrderTitle = null;
//		if (SupervisionConstants.AMMENDED.equals(supervisionOrder.getVersionTypeId())){
//			amendedOrderTitle = supervisionOrder.getOrderTitleId();
//		}
		//---------Supervision Order------------------------//
		// if new version of the same criminalcase
		if (reqEvent.isNewVersion())
		{
			supervisionOrder = cloneOrder(supervisionOrder);
			supervisionOrder.setOrderStatusId(PDCodeTableConstants.STATUS_INCOMPLETE_ID);
			supervisionOrder.setVersionTypeId(reqEvent.getVersionType());
			supervisionOrder.setOrderInProgress(true);
			supervisionOrder.setParentSupervisionOrderId(reqEvent.getOrderId());
			supervisionOrder.setRevisionDate(new Date());
			supervisionOrder.setVersionNum(OrderVersionSequence.next(supervisionOrder.getCriminalCaseId(), reqEvent
					.getVersionType(),supervisionOrder.getOrderChainNum()));
//			//is this going to cause a performance problem?
//			supervisionOrder.bind();
		}
		if (reqEvent.isMigratedOrder()){
			supervisionOrder.setVersionNum(reqEvent.getVersionNum());
		}
		if (reqEvent.getOrderTitle() != null
				&& !reqEvent.getOrderTitle().equals(PDConstants.BLANK)){
			supervisionOrder.setOrderTitleId(reqEvent.getOrderTitle());
		}
		supervisionOrder.setIsHistoricalOrder(reqEvent.getIsHistoricalOrder());
		supervisionOrder.setLimitedSupervisionBeginDate(reqEvent.getLimitedSupervisionBeginDate());
		supervisionOrder.setLimitedSupervisionEndDate(reqEvent.getLimitedSupervisionEndDate());
		supervisionOrder.setFineAmount(reqEvent.getFineAmount());
		supervisionOrder.setModificationReason(reqEvent.getModificationReason());
		supervisionOrder.setOrigJudgeFirstName(reqEvent.getOrigJudgeFirstName());
		supervisionOrder.setOrigJudgeLastName(reqEvent.getOrigJudgeLastName());
		supervisionOrder.setPlea(reqEvent.getPlea());
		supervisionOrder.setPrintedName(reqEvent.getPrintedName());
		supervisionOrder.setPrintedOffenseDesc(reqEvent.getPrintedOffenseDesc());
		supervisionOrder.setSummaryChanges(reqEvent.getSummaryChanges());
		supervisionOrder.setLimitedSupervisionPeriod(reqEvent.isLimitedSupervisionPeriod());
		
		SupervisionOrder originalOrder = null;
		if (!SupervisionConstants.ORIGINAL.equals(reqEvent.getVersionType())){
			originalOrder = SupervisionOrder.getOriginalSupervisionOrder(supervisionOrder
					.getAgencyId(), supervisionOrder.getCriminalCaseId());
			if ((reqEvent.getOrigJudgeLastName() == null
					|| reqEvent.getOrigJudgeLastName().equals(PDConstants.BLANK))
				&& (originalOrder != null
						&& originalOrder.getOrigJudgeLastName() != null
						&& !originalOrder.getOrigJudgeLastName().equals(PDConstants.BLANK))){
				supervisionOrder.setOrigJudgeFirstName(originalOrder.getOrigJudgeFirstName());
				supervisionOrder.setOrigJudgeLastName(originalOrder.getOrigJudgeLastName());
			}
		}
		
		//Set order title of amended orders to title of original order
		if (SupervisionConstants.AMMENDED.equals(reqEvent.getVersionType()))
		{
//			SupervisionOrder originalOrder = SupervisionOrder.getOriginalSupervisionOrder(supervisionOrder
//					.getAgencyId(), supervisionOrder.getCriminalCaseId());
			if (originalOrder != null 
					&& ((originalOrder.getCreateJIMS2UserID() != null 
							&& !originalOrder.getCreateJIMS2UserID().startsWith(MIGRATED))
							|| originalOrder.getCreateJIMS2UserID() == null)
					&& supervisionOrder.getOrderTitleId() == null)//not set to something else.
			{
				supervisionOrder.setOrderTitleId(originalOrder.getOrderTitleId());
			} //Don't understand why the following logic was here:
			//else if (originalOrder != null 
//					&& (originalOrder.getCreateJIMS2UserID() != null 
//							&& originalOrder.getCreateJIMS2UserID().startsWith(MIGRATED))
//							&& supervisionOrder.getOrderTitleId() == null){
//					supervisionOrder.setOrderTitleId(amendedOrderTitle);
//			}
		} else if (SupervisionConstants.MODIFIED.equals(reqEvent.getVersionType())){
					//supervisionOrder.setOrderTitleId(SupervisionConstants.ORDER_TITLE_MODIFIED);
		    		supervisionOrder.setOrderTitleId(SupervisionOrderHelper.getModifiedOrderTitleId());
		} else {
			supervisionOrder.setOrderTitleId(reqEvent.getOrderTitle());
		}
		supervisionOrder.setJuvenileCourtId(reqEvent.getJuvCourtId());
		supervisionOrder.setJuvSupervisionBeginDate(reqEvent.getJuvSupervisionBeginDate());
		supervisionOrder.setJuvSupervisionLenDays(reqEvent.getJuvSupervisionLengthDays());
		supervisionOrder.setJuvSupervisionLenMonths(reqEvent.getJuvSupervisionLengthMonths());
		supervisionOrder.setJuvSupervisionLenYears(reqEvent.getJuvSupervisionLengthYears());
		supervisionOrder.setSpecialCourtCd(reqEvent.getSpecialCourtCd());
		supervisionOrder.setComments(reqEvent.getComments());
		supervisionOrder.setConfinementLengthMonths(reqEvent.getConfinementLengthMonths());
		supervisionOrder.setConfinementLengthDays(reqEvent.getConfinementLengthDays());
		supervisionOrder.setConfinementLengthYears(reqEvent.getConfinementLengthYears());
		//supervisionOrder.setOffenseId(reqEvent.getOffenseId());
		supervisionOrder.setSupervisionLengthMonths(reqEvent.getSupervisionLengthMonths());
		supervisionOrder.setSupervisionLengthDays(reqEvent.getSupervisionLengthDays());
		supervisionOrder.setSupervisionLengthYears(reqEvent.getSupervisionLengthYears());
		if (supervisionOrder.getIsHistoricalOrder()){
			supervisionOrder.setDispositionTypeId(PDCodeTableConstants.PRETRIAL_INTERVENTION);
		} else {
			supervisionOrder.setDispositionTypeId(reqEvent.getDispositionTypeId());
		}
		supervisionOrder.setCaseSupervisionBeginDate(reqEvent.getCaseSupervisionBeginDate());
		supervisionOrder.setCaseSupervisionEndDate(reqEvent.getCaseSupervisionEndDate());
		// iterate conditions
		Collection conditionRelEvents = reqEvent.getConditions();
		for (Iterator i = conditionRelEvents.iterator(); i.hasNext();)
		{
			SupervisionOrderConditionEvent orderCondEvent = (SupervisionOrderConditionEvent) i.next();
			SupervisionOrderConditionRel supervisionOrderConditionRel = null;
			String conditionId = orderCondEvent.getConditionId();
			Condition condition = null;
			if (supervisionOrder.getIsHistoricalOrder()){
			    if (conditionId == null){ //condition has not been created in MSO
			        condition = supervisionOrder.createSpecialConditionForHistoricalOrder(orderCondEvent);
			        conditionMap.put((String) condition.getOID(), condition);
					supervisionOrderConditionRel = new SupervisionOrderConditionRel();
					supervisionOrderConditionRel.setSupervisionOrderId((String) supervisionOrder.getOID());
					supervisionOrderConditionRel.setSequenceNum(orderCondEvent.getSequenceNum());
			        
					SupervisionOrderCondition supOrderCondition = SupervisionOrderCondition.create(condition);
					supOrderCondition.setResolvedDescription(orderCondEvent.getResolvedDescription());
					supervisionOrderConditionRel.setSupervisionOrderCondition(supOrderCondition);
					// insert supervisionorderRel into SupervisionOrder
					supervisionOrder.insertSupervisionOrderConditionRels(supervisionOrderConditionRel);
			    } else { //condition was created before choosing add/remove conditions.
			        condition = (Condition) conditionMap.get(conditionId);
			    }
			}
			if (reqEvent.isNewVersion())
			{ // new version
				//----------SupervisionOrderCondition------------------//
				condition = (Condition) conditionMap.get(conditionId);
				SupervisionOrderCondition supOrderCondition = SupervisionOrderCondition.create(condition);
				supOrderCondition.setResolvedDescription(orderCondEvent.getResolvedDescription());
				//------------SupervisionOrderConditionRel---------------//
				SupervisionOrderConditionRel prevSupervisionOrderConditionRel = (SupervisionOrderConditionRel) existingCondToRelMap
						.get(conditionId);
				supervisionOrderConditionRel = new SupervisionOrderConditionRel();
				//			    supervisionOrderConditionRel.setSupervisionOrderId(supervisionOrder.getOID());
				supervisionOrderConditionRel.setSequenceNum(orderCondEvent.getSequenceNum());
				supervisionOrderConditionRel.setSupervisionOrderCondition(supOrderCondition);
				supervisionOrder.insertSupervisionOrderConditionRels(supervisionOrderConditionRel);
				//------------SupervisionOrderConditionRelValue---------------//
				Collection newCondRelVals = orderCondEvent.getOrderConditionRelValues();
				Collection relValues = (Collection) relToValuesMap.get(prevSupervisionOrderConditionRel.getOID());
				if (relValues != null)
				{
					Map existingRelVals = createExistingRelValueMap(relValues);
					if (newCondRelVals != null)
					{
						for (Iterator valIndex = newCondRelVals.iterator(); valIndex.hasNext();)
						{
							SupervisionOrderConditionRelValueEvent condRelValEvent = (SupervisionOrderConditionRelValueEvent) valIndex.next();
							// create new OrderConditionValue
							SupervisionOrderConditionRelValue newOrderCondValue = new SupervisionOrderConditionRelValue();
							newOrderCondValue.populate(condRelValEvent);
							supervisionOrderConditionRel.insertOrderConditionRelValues(newOrderCondValue);
							//------------SupervisionOrderRelValueHistory---------------//
							SupervisionOrderConditionRelValue condRelVal = (SupervisionOrderConditionRelValue) existingRelVals.get(condRelValEvent.getVariableElementTypeId());
							if (condRelVal != null)
							{
								String priorValue = condRelVal.getValue();
								//create relValHistory for reference field only
								if (condRelValEvent.isReference()
										&& (priorValue == null || !priorValue.equals(newOrderCondValue.getValue())))
								{
									SupervisionOrderRelValueHistory relValHistory = SupervisionOrderRelValueHistory
											.create(PDCodeTableConstants.TRANS_TYPE_CHANGE, condRelVal.getValue());
									//                                    relValHistory.setSupervisionOrderConditionRelValueId(condRelVal.getOID().toString());
									condRelVal.insertSupervisionOrderRelValHistories(relValHistory);
								}
							}
						}
					}
				}
			}
			//else
			else if (!(supervisionOrder.getIsHistoricalOrder() && conditionId == null))
			// existing conditions
			{
				supervisionOrderConditionRel = (SupervisionOrderConditionRel) existingCondToRelMap.get(conditionId);
				if (supervisionOrderConditionRel != null){
					supervisionOrderConditionRel.setSequenceNum(orderCondEvent.getSequenceNum());
					SupervisionOrderCondition supOrderCondition = (SupervisionOrderCondition) existingCondMap
						.get(conditionId);
					supOrderCondition.setResolvedDescription(orderCondEvent.getResolvedDescription());
					Collection newCondRelVals = orderCondEvent.getOrderConditionRelValues();
					//				Map existingRelVals =
					// createExistingRelValueMap(supervisionOrderConditionRel.getOrderConditionRelValues());
					Collection relValues = (Collection) relToValuesMap
						.get(supervisionOrderConditionRel.getOID());
					if (relValues != null)
					{
						Map existingRelVals = createExistingRelValueMap(relValues);
						if (newCondRelVals != null)
						{
							for (Iterator valIndex = newCondRelVals.iterator(); valIndex.hasNext();)
							{
								SupervisionOrderConditionRelValueEvent condRelValEvent = (SupervisionOrderConditionRelValueEvent) valIndex
									.next();
								SupervisionOrderConditionRelValue condRelVal = (SupervisionOrderConditionRelValue) existingRelVals
									.get(condRelValEvent.getVariableElementTypeId());
								if (condRelVal != null)
								{
									String priorValue = condRelVal.getValue();
									condRelVal.populate(condRelValEvent);
									new Home().bind(condRelVal);
								//create relValHistory for reference field only
									if (condRelValEvent.isReference()
										&& (priorValue == null || !priorValue.equals(condRelVal.getValue())))
									{
										SupervisionOrderRelValueHistory relValHistory = SupervisionOrderRelValueHistory
											.create(PDCodeTableConstants.TRANS_TYPE_CHANGE, condRelVal.getValue());
										relValHistory
											.setSupervisionOrderConditionRelValueId(condRelVal.getOID());
										condRelVal.insertSupervisionOrderRelValHistories(relValHistory);
									}
								} 
							}
						}
					}
				}
			}
		}
		Iterator remCondIter = removedConditions.iterator();
		SupervisionOrderConditionRel supervisionOrderConditionRel = null;
		SupervisionOrderCondition supervisionOrderCondition = null;
		SupervisionOrderCondition soCondition = null;
		List aList = null;
		Iterator i = null;
		Condition specialCondition = null;

		while (remCondIter.hasNext())
		{
			supervisionOrderCondition = (SupervisionOrderCondition) existingCondMap
				.get(remCondIter.next());
			supervisionOrderConditionRel = (SupervisionOrderConditionRel)existingCondToRelMap.get(supervisionOrderCondition.getConditionId());
			//Delete from CSCONDITION if special condition not attached to a previous order version.
			soCondition = supervisionOrderConditionRel.getSupervisionOrderCondition();
			if (soCondition != null && soCondition.getIsSpecialCondition()){
				i = SupervisionOrderCondition.findAll("conditionId", soCondition.getConditionId());
				aList = CollectionUtil.iteratorToList(i);
				if (aList.size() == 1){
					specialCondition = soCondition.getCondition();
					specialCondition.delete();
				}
			}
			supervisionOrder.removeSupervisionOrderConditionRels(supervisionOrderConditionRel);
		
		}
		return supervisionOrder;
	}

	/**
	 * @param agencyId
	 * @param criminalCaseId
	 * @return 
	 * @methodInvocation setAgencyId
	 * @methodInvocation setCriminalCaseId
	 * @methodInvocation findAll
	 * @methodInvocation setAgencyId
	 * @methodInvocation setCriminalCaseId
	 * @methodInvocation findAll
	 */
	static public boolean validateNewOrderVersion(String agencyId, String criminalCaseId, int aOrderChainNum)
	{
		ValidateNewOrderVersionEvent anEvent = new ValidateNewOrderVersionEvent();
		anEvent.setAgencyId(agencyId);
		anEvent.setCriminalCaseId(criminalCaseId);
		if(aOrderChainNum==0)
			aOrderChainNum=1; // default is 1
		anEvent.setOrderChainNum(aOrderChainNum);
		Iterator iter = SupervisionOrder.findAll(anEvent);
		boolean result = false;
		if (iter != null && iter.hasNext())
		{
			result = true;
		}
		return result;
	}

	private Date activationDate;

	/**
	 * Properties for agency
	 * @referencedType pd.contact.agency.Agency
	 */
	private Agency agency = null;

	private String agencyId;

	private Date caseSupervisionBeginDate;
	
	private Date caseSupervisionEndDate;

	private String comments;

	private int confinementLengthDays;
	private int confinementLengthMonths;

	private int confinementLengthYears;
	/**
	 * Properties for criminalCase
	 * @referencedType pd.criminalcase.CriminalCase
	 */
	private CriminalCase criminalCase = null;
	private String criminalCaseId;
	/**
	 * Properties for currentCourt
	 * @referencedType pd.supervision.Court
	 */
	private pd.supervision.Court currentCourt = null;
	private String currentCourtId;
	/**
	 * Properties for defendant
	 * @referencedType pd.contact.party.Party
	 */
	private Party defendant = null;
	private String defendantId;
	/**
	 * Properties for dispositionType
	 */
	private Code dispositionType = null;
	private String dispositionTypeId;
	private double fineAmount;
	private Date inactivationDate;
	private boolean isHistoricalOrder;
	private pd.supervision.Court juvenileCourt = null;
	private String juvenileCourtId;
	private Date juvSupervisionBeginDate;
	private int juvSupervisionLenDays;
	private int juvSupervisionLenMonths;
	private int juvSupervisionLenYears;
	/**
	 * Also known as activationDate
	 */
	private Date limitedSupervisionBeginDate;
	/**
	 * Also known as inactivate date
	 */
	private Date limitedSupervisionEndDate;
	private boolean limitedSupervisionPeriod;
	/**
	 * Properties for magistrate
	 * @referencedType pd.codetable.supervision.Magistrate
	 */
	private Magistrate magistrate = null;
	private String magistrateId;
	private String modificationReason;
	/**
	 * Properties for offense
	 * @referencedType pd.codetable.criminal.OffenseCode
	 */
	//private OffenseCode offense = null;
	//private String offenseId;  
	private int orderChainNum;
	/**
	 * Properties for orderCourt
	 * @referencedType pd.supervision.Court
	 */
	private pd.supervision.Court orderCourt = null;
	
	private String orderCourtId;
	private Date orderFiledDate;
	private boolean orderInProgress;
	/**
	 * Properties for orderPresentor
	 * @referencedType pd.supervisionstaff.pretrialstaff.SupervisionStaff
	 */
	private SupervisionStaff orderPresentor = null;
	private String orderPresentorFirstName;
	private String orderPresentorId;
	private String orderPresentorLastName;
	private boolean orderSignedByDefendant;
	private Date orderSignedByDefendantDate;
	private Date orderSignedByJudgeDate;
	private Date orderSignedDate;
	/**
	 * Properties for orderStatus
	 * @referencedType pd.codetable.Code
	 * @detailerDoNotGenerate true
	 * @contextKey ORDER_STATUS
	 */
	private Code orderStatus = null;
	private String orderStatusId;
	/**
	 * Properties for orderTitle
	 * @referencedType pd.codetable.supervision.SupervisionCode
	 * @detailerDoNotGenerate true
	 * @contextKey ORDER_TITLE
	 */
	private SupervisionCode orderTitle = null;
	private String orderTitleId;
	private String origJudgeFirstName;
	private String origJudgeLastName;
	/**
	 * Properties for parentSupervisionOrder
	 * @referencedType pd.supervision.supervisionorder.SupervisionOrder
	 */
	private SupervisionOrder parentSupervisionOrder;
	private String parentSupervisionOrderId;
	private String plea;
	private String printedName;
	private String printedOffenseDesc;
	private boolean printSpanish;

	/**
	 * Properties for printTemplate
	 * @referencedType pd.supervision.supervisionorder.SupervisionOrderPrintTemplate
	 */
	private SupervisionOrderPrintTemplate printTemplate = null;
	private String printTemplateId;
	/**
	 * Properties for reinstatements
	 * @referencedType pd.supervision.supervisionorder.SupervisionOrderReinstatement
	 */
	private Collection reinstatements = null;
	private Date revisionDate;
	private String signedByFirstName;
	private String signedByLastName;
	/**
	 * Properties for signedByType
	 */
	private Code signedByType = null;
	private String signedByTypeId;
	/**
	 * Properties for specialCourt
	 * @referencedType pd.codetable.Code
	 * @detailerDoNotGenerate true
	 * @contextKey SPECIAL_COURT
	 */
	private Code specialCourt = null;
	private String specialCourtCd;
	private String suggestedOrderId;
	private String summaryChanges;
	/**
	 * Properties for supervision
	 * @referencedType pd.supervision.supervisionorder.Supervision
	 */
	private Supervision supervision = null;
	private String supervisionId;
	private int supervisionLengthDays;
	private int supervisionLengthMonths;
	private int supervisionLengthYears;
	/**
	 * Properties for supervisionOrderConditionRels
	 * @referencedType pd.supervision.supervisionorder.SupervisionOrderConditionRel
	 */
	private Collection supervisionOrderConditionRels = null;
	/**
	 * Properties for supervisionPeriod
	 * @referencedType pd.supervision.supervisionorder.SupervisionPeriod
	 */
	private SupervisionPeriod supervisionPeriod = null;
	private String supervisionPeriodId;
	private Date transferInDate;
	private Date updateDate;
	private Date statusChangeDate;
	private int versionNum;
	/**
	 * Properties for versionType
	 * @referencedType pd.codetable.Code
	 * @detailerDoNotGenerate true
	 * @contextKey VERSION_TYPE
	 */
	private Code versionType = null;
	private String versionTypeId;
	/**
	 * Properties for withdrawReason
	 * @referencedType pd.codetable.Code
	 * @detailerDoNotGenerate true
	 * @contextKey WITHDRAW_REASON
	 */
	private Code withdrawReason = null;
	private String withdrawReasonId;
	/**
	 * @roseuid 43B2E6DC02EE
	 */
	public SupervisionOrder()
	{
	}
	/**
	 * Bind entity to database thus creating an OID
	 *
	 */
    public void bind()
    {
        IHome home = new Home();
        home.bind(this);
    }//end of bind()
	/**
	 * Clears all pd.supervision.supervisionorder.SupervisionOrderReinstatement
	 * from class relationship collection.
	 * @methodInvocation initReinstatements
	 * @methodInvocation clear
	 * @methodInvocation initReinstatements
	 * @methodInvocation clear
	 */
	public void clearReinstatements()
	{
		initReinstatements();
		reinstatements.clear();
	}
	/**
	 * Clears all pd.supervision.supervisionorder.SupervisionOrderConditionRel
	 * from class relationship collection.
	 * @methodInvocation initSupervisionOrderConditionRels
	 * @methodInvocation clear
	 * @methodInvocation initSupervisionOrderConditionRels
	 * @methodInvocation clear
	 */
	public void clearSupervisionOrderConditionRels()
	{
		initSupervisionOrderConditionRels();
		supervisionOrderConditionRels.clear();
	}
	public int compareTo(Object obj) throws ClassCastException
	{
		SupervisionOrder order = (SupervisionOrder) obj;
		Date curDate = new Date();
		if (activationDate == null && order.getActivationDate() == null)
		{
			return 0;
		}
		else if (activationDate == null)
		{
			return order.getActivationDate().compareTo(curDate);
		}
		else if (order.getActivationDate() == null)
		{
			return curDate.compareTo(activationDate);
		}
		else
		{
			return order.getActivationDate().compareTo(activationDate);
		}
	}
	/**
	 * @param soCondEvent
	 */
	private Condition createSpecialConditionForHistoricalOrder(SupervisionOrderConditionEvent soCondEvent){
		// ---------------- create Order components ---------------------------
		//--------- SupervisionOrderConditionRel-----------------//
//		SupervisionOrderConditionRel supervisionOrderConditionRel = new SupervisionOrderConditionRel();
//		supervisionOrderConditionRel.setSupervisionOrderId((String) this.getOID());
//		supervisionOrderConditionRel.setSequenceNum(soCondEvent.getSequenceNum());

		//--------- SupervisionOrderCondition-----------------//
		//  create special condition
		Condition condition = new Condition();
		condition.setName(soCondEvent.getConditionName());
		condition.setAgencyId(this.getAgencyId());
		condition.setIsStandard(false);
		condition.setDescription(soCondEvent.getResolvedDescription());
		condition.setEffectiveDate(new Date());
		condition.setIsSpecialCondition(true);
		
		// create ConditionSupervisionOption
		ConditionSupervisionOption condSupOption = new ConditionSupervisionOption();
		condSupOption.setCourtId(this.getCurrentCourtId());
		condition.insertConditionSupervisionOptions(condSupOption);

		soCondEvent.setConditionId((String) condition.getOID());
		
		return condition;
	}
	private String formatCaseFilingDate(String filingDate){
		StringBuffer formattedFilingDate = new StringBuffer();
		if(filingDate != null && !filingDate.equals("")){
			String year = filingDate.substring(4,6);
			String month = filingDate.substring(0,2);
			String day = filingDate.substring(2,4);
			
			if(filingDate.length() == 6){        				
				if(60 < Integer.parseInt(year)){  // ask Mary if you need to know about the '60'
					year = "19" + year;
				}else{
					year = "20" + year;
				}
			}	
			
			formattedFilingDate.append(month);
			formattedFilingDate.append("/");	        				
			formattedFilingDate.append(day);
			formattedFilingDate.append("/");
			formattedFilingDate.append(year);			
		}
		return formattedFilingDate.toString();
	}
	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public Date getActivationDate()
	{
		fetch();
		return activationDate;
	}
	/**
	 * Gets referenced type pd.contact.agency.Agency
	 * @methodInvocation fetch
	 * @methodInvocation initAgency
	 * @methodInvocation fetch
	 * @methodInvocation initAgency
	 */
	public Agency getAgency()
	{
		fetch();
		initAgency();
		return agency;
	}

	/**
	 * Get the reference value to class :: pd.contact.agency.Agency
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getAgencyId()
	{
		fetch();
		return agencyId;
	}

	/**
     * @return Returns the caseSupervisionBeginDate.
     */
    public Date getCaseSupervisionBeginDate() {
        fetch();
        return caseSupervisionBeginDate;
    }
	
	/**
     * @return Returns the caseSupervisionEndDate.
     */
    public Date getCaseSupervisionEndDate() {
        fetch();
        return caseSupervisionEndDate;
    }

	public String getComments() {
		fetch();
		return comments;
	}

	/**
	 * @return Returns the confinementLengthDays.
	 * @methodInvocation fetch
	 */
	public int getConfinementLengthDays()
	{
		fetch();
		return confinementLengthDays;
	}

	/**
	 * @return Returns the confinementLengthMonths.
	 * @methodInvocation fetch
	 */
	public int getConfinementLengthMonths()
	{
		fetch();
		return confinementLengthMonths;
	}

	/**
	 * @return Returns the confinementLengthYears.
	 * @methodInvocation fetch
	 */
	public int getConfinementLengthYears()
	{
		fetch();
		return confinementLengthYears;
	}

	/**
	 * Gets referenced type pd.criminalcase.CriminalCase
	 * @methodInvocation fetch
	 * @methodInvocation initCriminalCase
	 * @methodInvocation fetch
	 * @methodInvocation initCriminalCase
	 */
	public CriminalCase getCriminalCase()
	{
		fetch();
		initCriminalCase();
		return criminalCase;
	}
	/**
	 * Get the reference value to class :: pd.criminalcase.CriminalCase
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getCriminalCaseId()
	{
		fetch();
		return criminalCaseId;
	}
	/**
	 * Gets referenced type pd.supervision.Court
	 */
	public pd.supervision.Court getCurrentCourt()
	{
		fetch();
		initCurrentCourt();
		return currentCourt;
	}

	/**
	 * Get the reference value to class :: pd.supervision.Court
	 */
	public String getCurrentCourtId()
	{
		fetch();
		return currentCourtId;
	}
	/**
	 * Gets referenced type pd.contact.party.Party
	 * @methodInvocation initDefendant
	 */
	public Party getDefendant()
	{
		fetch();
		initDefendant();
		return defendant;
	}
	/**
	 * Get the reference value to class :: pd.contact.party.Party
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getDefendantId()
	{
		fetch();
		return defendantId;
	}
	/**
	 * @return Returns the dispositionType.
	 */
	public Code getDispositionType()
	{
		initDispositionType();
		return dispositionType;
	}

    /**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getDispositionTypeId()
	{
		fetch();
		return dispositionTypeId;
	}
    /**
     * @return Returns the fineAmount.
     */
    public double getFineAmount() {
        fetch();
        return fineAmount;
    }
	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation initCriminalCase
	 * @methodInvocation initCriminalCase
	 */
	public String getFormattedCaseNum()
	{
		fetch();
		initCriminalCase();
		String caseNum = PDConstants.BLANK;
		if (criminalCase != null)
		{
			caseNum = criminalCase.getCaseNum();
		}
		return caseNum;
	}
	/**
	 * "Dummy" method needed in order to have multiple reference variables mapped to a single attribute.
	 * @return
	 */
	public String getFormattedCaseSupervisionBeginDate1(){
	    String stringDate = DateUtil.dateToString(this.getCaseSupervisionBeginDate(), DateUtil.DATE_FMT_1);
	    return stringDate;
	}
	/**
	 * "Dummy" method needed in order to have multiple reference variables mapped to a single attribute.
	 * @return
	 */
	public String getFormattedCaseSupervisionBeginDate2(){
	    return this.getFormattedCaseSupervisionBeginDate1();
	}
	/**
	 * "Dummy" method needed in order to have multiple reference variables mapped to a single attribute.
	 * @return
	 */
	public String getFormattedCaseSupervisionBeginDate3(){
	    return this.getFormattedCaseSupervisionBeginDate1();
	}
	/**
	 * "Dummy" method needed in order to have multiple reference variables mapped to a single attribute.
	 * @return
	 */
	public String getFormattedCaseSupervisionBeginDate4(){
	    return this.getFormattedCaseSupervisionBeginDate1();
	}

	/**
	 * "Dummy" method needed in order to have multiple reference variables mapped to a single attribute.
	 * @return
	 */
	public String getFormattedCaseSupervisionBeginDate5(){
	    return this.getFormattedCaseSupervisionBeginDate1();
	}

	/**
	 * "Dummy" method needed in order to have multiple reference variables mapped to a single attribute.
	 * @return
	 */
    public String getFormattedCaseSupervisionEndDate1(){
	    String stringDate = DateUtil.dateToString(this.getCaseSupervisionEndDate(), DateUtil.DATE_FMT_1);
	    return stringDate;
    }

	/**
	 * "Dummy" method needed in order to have multiple reference variables mapped to a single attribute.
	 * @return
	 */
    public String getFormattedCaseSupervisionEndDate2(){
        return this.getFormattedCaseSupervisionEndDate1();
    }

	/**
	 * "Dummy" method needed in order to have multiple reference variables mapped to a single attribute.
	 * @return
	 */
    public String getFormattedCaseSupervisionEndDate3(){
        return this.getFormattedCaseSupervisionEndDate1();
    }
	/**
	 * "Dummy" method needed in order to have multiple reference variables mapped to a single attribute.
	 * @return
	 */
    public String getFormattedCaseSupervisionEndDate4(){
        return this.getFormattedCaseSupervisionEndDate1();
    }

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation getCriminalCase
	 * @methodInvocation getCriminalCase
	 */
	public String getFormattedCDI()
	{
		fetch();
		getCriminalCase();
		String courtDivision = PDConstants.BLANK;
		if (criminalCase != null)
		{
			courtDivision = criminalCase.getCourtDivisionId();
		}
		return courtDivision;
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation getOrderCourt
	 * @methodInvocation getOrderCourt
	 */
	public String getFormattedCourt()
	{
		fetch();
		getOrderCourt();
		String courtNum = PDConstants.BLANK;
		if (orderCourt != null)
		{
			courtNum = orderCourt.getCourtNumber();
		}
		return courtNum;
	}

	/**
	 * "Dummy" method needed in convert double to formatted.
	 * @return
	 */
	public String getFormattedFineAmountTotal(){
		String aString = PDUtil.formatCurrency(new Double(this.fineAmount), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT);
	    return aString;
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 */
	public String getFormattedPresidingJudgeName()
	{
		fetch();
		String fullName = PDConstants.BLANK;
		Name name = new Name(signedByFirstName, PDConstants.BLANK, signedByLastName);
		if (name != null)
		{
			fullName = name.getFullNameFirst();
		}
		return fullName;
	}

	/**
	 * @return Returns the inactivationDate.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public Date getInactivationDate()
	{
		fetch();
		return inactivationDate;
	}

	/**
     * @return Returns the isHistoricalOrder.
     */
    public boolean getIsHistoricalOrder() {
        fetch();
        return isHistoricalOrder;
    }

	/**
	 * Gets referenced type pd.supervision.Court
	 */
	public pd.supervision.Court getJuvenileCourt()
	{
		fetch();
		initJuvenileCourt();
		return juvenileCourt;
	}
    /**
	 * Get the reference value to class :: pd.supervision.Court
	 */
	public String getJuvenileCourtId()
	{
		fetch();
		return juvenileCourtId;
	}

	public Date getJuvSupervisionBeginDate() {
		fetch();
		return juvSupervisionBeginDate;
	}

	public int getJuvSupervisionLenDays() {
		fetch();
		return juvSupervisionLenDays;
	}

	public int getJuvSupervisionLenMonths() {
		fetch();
		return juvSupervisionLenMonths;
	}

	public int getJuvSupervisionLenYears() {
		fetch();
		return juvSupervisionLenYears;
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public Date getLimitedSupervisionBeginDate()
	{
		fetch();
		return limitedSupervisionBeginDate;
	}
    /**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public Date getLimitedSupervisionEndDate()
	{
		fetch();
		return limitedSupervisionEndDate;
	}

	/**
	 * Gets referenced type pd.codetable.supervision.Magistrate
	 * @methodInvocation fetch
	 * @methodInvocation initMagistrate
	 * @methodInvocation fetch
	 * @methodInvocation initMagistrate
	 */
	public Magistrate getMagistrate()
	{
		fetch();
		initMagistrate();
		return magistrate;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getMagistrateId()
	{
		fetch();
		return magistrateId;
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getModificationReason()
	{
		fetch();
		return modificationReason;
	}

	/**
	 * Get the reference value to class :: pd.codetable.criminal.OffenseCode
	 * @methodInvocation fetch
	 */
	public String getOffenseId()
	{
//		fetch();
		return "";
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public int getOrderChainNum()
	{
		fetch();
		return orderChainNum;
	}

	/**
	 * Gets referenced type pd.supervision.Court
	 * @methodInvocation fetch
	 * @methodInvocation initOrderCourt
	 * @methodInvocation fetch
	 * @methodInvocation initOrderCourt
	 */
	public pd.supervision.Court getOrderCourt()
	{
		fetch();
		initOrderCourt();
		return orderCourt;
	}

	/**
	 * Get the reference value to class :: pd.supervision.Court
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getOrderCourtId()
	{
		fetch();
		return orderCourtId;
	}

	/**
	 * @return Returns the offense.
	 * @methodInvocation initOffense
	 */
//	public OffenseCode getOffense()
//	{
//		fetch();
//		initOffense();
//		return offense;
//	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public Date getOrderFiledDate()
	{
		fetch();
		return orderFiledDate;
	}

	/**
	 * Gets referenced type
	 * pd.supervision.supervisionstaff.pretrialstaff.SupervisionStaff
	 * @methodInvocation fetch
	 * @methodInvocation initOrderPresentor
	 * @methodInvocation fetch
	 * @methodInvocation initOrderPresentor
	 */
	public SupervisionStaff getOrderPresentor()
	{
		fetch();
		initOrderPresentor();
		return orderPresentor;
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getOrderPresentorFirstName()
	{
		fetch();
		return orderPresentorFirstName;
	}

	/**
	 * Get the reference value to class :: pd.contact.user.UserProfile
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getOrderPresentorId()
	{
		fetch();
		return orderPresentorId;
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getOrderPresentorLastName()
	{
		fetch();
		return orderPresentorLastName;
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public Date getOrderSignedByDefendantDate()
	{
		fetch();
		return orderSignedByDefendantDate;
	}
	
	public Date getOrderSignedByJudgeDate() 
	{
		fetch();
		return orderSignedByJudgeDate;
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public Date getOrderSignedDate()
	{
		fetch();
		return orderSignedDate;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getOrderStatus()
	{
		fetch();
		initOrderStatus();
		return orderStatus;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getOrderStatusId()
	{
		fetch();
		return orderStatusId;
	}

	/**
	 * Gets referenced type pd.codetable.supervision.SupervisionCode
	 * @methodInvocation fetch
	 * @methodInvocation initOrderTitle
	 * @methodInvocation fetch
	 * @methodInvocation initOrderTitle
	 */
	public SupervisionCode getOrderTitle()
	{
		fetch();
		initOrderTitle();
		return orderTitle;
	}

	/**
	 * Get the reference value to class ::
	 * pd.codetable.supervision.SupervisionCode
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getOrderTitleId()
	{
		fetch();
		return orderTitleId;
	}

	public String getOrigJudgeFirstName() {
		fetch();
		return origJudgeFirstName;
	}
	public String getOrigJudgeLastName() {
		fetch();
		return origJudgeLastName;
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation initParentSupervisionOrder
	 * @methodInvocation fetch
	 * @methodInvocation initParentSupervisionOrder
	 */
	public SupervisionOrder getParentSupervisionOrder()
	{
		fetch();
		initParentSupervisionOrder();
		return parentSupervisionOrder;
	}
	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getParentSupervisionOrderId()
	{
		fetch();
		return parentSupervisionOrderId;
	}

	/**
	 * @return Returns the plea.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getPlea()
	{
		fetch();
		return plea;
	}

	public String getPrintedName() {
		fetch();
		return printedName;
	}
	public String getPrintedOffenseDesc() {
		fetch();
		return printedOffenseDesc;
	}

	/**
	 * Gets referenced type
	 * pd.supervision.supervisionorder.SupervisionOrderPrintTemplate
	 * @methodInvocation fetch
	 * @methodInvocation initPrintTemplate
	 * @methodInvocation fetch
	 * @methodInvocation initPrintTemplate
	 */
	public SupervisionOrderPrintTemplate getPrintTemplate()
	{
		fetch();
		initPrintTemplate();
		return printTemplate;
	}

	/**
	 * Get the reference value to class ::
	 * pd.supervision.supervisionorder.SupervisionOrderPrintTemplate
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getPrintTemplateId()
	{
		fetch();
		return printTemplateId;
	}

	/**
	 * returns a collection of
	 * pd.supervision.supervision.supervisionorder.SupervisionOrderReinstatement
	 * @methodInvocation fetch
	 * @methodInvocation initReinstatements
	 * @methodInvocation fetch
	 * @methodInvocation initReinstatements
	 */
	public Collection getReinstatements()
	{
		fetch();
		initReinstatements();
		return reinstatements;
	}

	/**
	 * @return Returns the revisionDate.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public Date getRevisionDate()
	{
		fetch();
		return revisionDate;
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getSignedByFirstName()
	{
		fetch();
		return signedByFirstName;
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getSignedByLastName()
	{
		fetch();
		return signedByLastName;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation initSignedByType
	 * @methodInvocation fetch
	 * @methodInvocation initSignedByType
	 */
	public Code getSignedByType()
	{
		fetch();
		initSignedByType();
		return signedByType;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getSignedByTypeId()
	{
		fetch();
		return signedByTypeId;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getSpecialCourt()
	{
		fetch();
		initSpecialCourt();
		return specialCourt;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getSpecialCourtCd()
	{
		fetch();
		return specialCourtCd;
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getSuggestedOrderId()
	{
		fetch();
		return suggestedOrderId;
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getSummaryChanges()
	{
		fetch();
		return summaryChanges;
	}

	/**
	 * @return SuperviseeCaseOrderResponseEvent
	 */
	public SuperviseeCaseOrderResponseEvent getSuperviseeCaseOrder(CriminalCase cCase) {
		SuperviseeCaseOrderResponseEvent resp = new SuperviseeCaseOrderResponseEvent();
		resp.setCourtId(this.getOrderCourtId());
		resp.setCaseNumber(this.getCriminalCaseId());        				
		resp.setOrderStatus((this.getOrderStatusId() == null || this.getOrderStatusId().equals(""))?"":this.getOrderStatus().getDescription());
		resp.setVersion((this.getVersionTypeId() == null || this.getVersionTypeId().equals(""))?"":this.getVersionType().getDescription());
		resp.setOrderFiledDate(this.getOrderFiledDate());
		resp.setDefendantId(this.getDefendantId());
		resp.setSupervisionOrderId(this.getOID());
		
		if(cCase != null){
    		resp.setCdi(cCase.getCourtDivisionId());
    		resp.setOffenseCodeId(cCase.getOffenseCodeId());
    		if(cCase.getOffenseCodeId() != null && !"".equals(cCase.getOffenseCodeId().trim())){
    			resp.setOffenseCodeDesc((cCase.getOffenseCode() == null)?"":cCase.getOffenseCode().getDescription());
     		}
    		resp.setCaseFileDate(DateUtil.stringToDate(formatCaseFilingDate(cCase.getFilingDate()),DateUtil.DATE_FMT_1));
		}
		return resp;
	}

	/**
	 * Gets referenced type pd.criminalcase.Supervision
	 * @methodInvocation fetch
	 * @methodInvocation initSupervision
	 * @methodInvocation fetch
	 * @methodInvocation initSupervision
	 */
	public Supervision getSupervision()
	{
		fetch();
		initSupervision();
		return supervision;
	}

	/**
	 * Get the reference value to class :: pd.criminalcase.Supervision
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getSupervisionId()
	{
		fetch();
		return supervisionId;
	}

	/**
	 * @return Returns the supervisionLengthDays.
	 * @methodInvocation fetch
	 */
	public int getSupervisionLengthDays()
	{
		fetch();
		return supervisionLengthDays;
	}

	/**
	 * @return Returns the supervisionLengthMonths.
	 * @methodInvocation fetch
	 */
	public int getSupervisionLengthMonths()
	{
		fetch();
		return supervisionLengthMonths;
	}

	/**
	 * @return Returns the supervisionLengthYears.
	 * @methodInvocation fetch
	 */
	public int getSupervisionLengthYears()
	{
		fetch();
		return supervisionLengthYears;
	}

	/**
	 * returns a collection of
	 * pd.supervision.supervisionorder.SupervisionOrderConditionRel
	 * @methodInvocation fetch
	 * @methodInvocation initSupervisionOrderConditionRels
	 * @methodInvocation fetch
	 * @methodInvocation initSupervisionOrderConditionRels
	 */
	public Collection getSupervisionOrderConditionRels()
	{
		fetch();
		initSupervisionOrderConditionRels();
		return supervisionOrderConditionRels;
	}

	/**
	 * Gets referenced type pd.supervision.supervisionorder.SupervisionPeriod
	 * @methodInvocation initSupervisionPeriod
	 */
	public SupervisionPeriod getSupervisionPeriod()
	{
		fetch();
		initSupervisionPeriod();
		// this SupervisionPeriod needs to point to the modified
		// SupervisionPeriod (target) in SupervisionPeriodRedirect.
		// Whenever a reinstatement occurs and SupervisionPeriod is not current,
		// target in SupervisionPeriodRdirect is updated.
		supervisionPeriod = SupervisionPeriod.findPeriodForSupervisionOrder("" + this.getOID());
		return supervisionPeriod;
	}

	/**
	 * Get the reference value to class ::
	 * pd.supervision.supervisionorder.SupervisionPeriod
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getSupervisionPeriodId()
	{
		fetch();
		return supervisionPeriodId;
	}
	
	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public Date getUpdateDate()
	{
		fetch();
		return updateDate;
	}
	

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public Date getStatusChangeDate()
	{
		fetch();
		return statusChangeDate;
	}


	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public int getVersionNum()
	{
		fetch();
		return versionNum;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation initVersionType
	 * @methodInvocation fetch
	 * @methodInvocation initVersionType
	 */
	public Code getVersionType()
	{
		fetch();
		initVersionType();
		return versionType;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getVersionTypeId()
	{
		fetch();
		return versionTypeId;
	}
	/**
	 * Gets referenced type pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation initWithdrawReason
	 * @methodInvocation fetch
	 * @methodInvocation initWithdrawReason
	 */
	public Code getWithdrawReason()
	{
		fetch();
		initWithdrawReason();
		return withdrawReason;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getWithdrawReasonId()
	{
		fetch();
		return withdrawReasonId;
	}

	public boolean hasSupervisionInfo(){
		boolean hasSupervisionInfo = false;
		if (this.getCaseSupervisionBeginDate() != null ||
			this.getCaseSupervisionEndDate() != null || 
			this.getConfinementLengthDays() > 0 ||
			this.getConfinementLengthMonths() > 0 ||
			this.getConfinementLengthYears() > 0 ||
			this.getSupervisionLengthDays() > 0 ||
			this.getSupervisionLengthMonths() > 0 ||
			this.getSupervisionLengthYears() > 0 ||
			this.getJuvSupervisionBeginDate() != null ||
			this.getJuvSupervisionLenDays() > 0 ||
			this.getJuvSupervisionLenMonths() > 0 ||
			this.getJuvSupervisionLenYears() > 0){
			hasSupervisionInfo = true;
			}
		return hasSupervisionInfo;
	}

	/**
	 * Initialize class relationship to class pd.contact.agency.Agency
	 */
	private void initAgency()
	{
		if (agency == null)
		{
			agency = (Agency) new mojo.km.persistence.Reference(agencyId,
					Agency.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.criminalcase.CriminalCase
	 */
	private void initCriminalCase()
	{
		if (criminalCase == null)
		{
			criminalCase = (CriminalCase) new mojo.km.persistence.Reference(criminalCaseId,
					CriminalCase.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.Court
	 */
	private void initCurrentCourt()
	{
		if (currentCourt == null)
		{
			currentCourt = (pd.supervision.Court) new mojo.km.persistence.Reference(currentCourtId,
					pd.supervision.Court.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.contact.party.Party
	 */
	private void initDefendant()
	{
		if (defendant == null)
		{
			defendant = (Party) new mojo.km.persistence.Reference(defendantId,
					Party.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initDispositionType()
	{
		if (dispositionType == null)
		{
			dispositionType = (Code) new mojo.km.persistence.Reference(dispositionTypeId,
					Code.class, "SUPERVISION_DISPOSITION").getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.Court
	 */
	private void initJuvenileCourt()
	{
		if (juvenileCourt == null)
		{
			juvenileCourt = (pd.supervision.Court) new mojo.km.persistence.Reference(juvenileCourtId,
					pd.supervision.Court.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class
	 * pd.codetable.supervision.Magistrate
	 */
	private void initMagistrate()
	{
		if (magistrate == null)
		{
			magistrate = (Magistrate) new mojo.km.persistence.Reference(magistrateId,
					Magistrate.class).getObject();
		}
	}
	/**
	 * Initialize class relationship to class pd.codetable.criminal.OffenseCode
	 */
//	private void initOffense()
//	{
//		if (offense == null)
//		{
//			offense = (pd.codetable.criminal.OffenseCode) new mojo.km.persistence.Reference(offenseId,
//					pd.codetable.criminal.OffenseCode.class).getObject();
//		}
//	}
//
	/**
	 * Initialize class relationship to class pd.supervision.Court
	 */
	private void initOrderCourt()
	{
		if (orderCourt == null)
		{
			orderCourt = (pd.supervision.Court) new mojo.km.persistence.Reference(orderCourtId,
					pd.supervision.Court.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class
	 * pd.supervision.supervisionstaff.pretrialstaff.SupervisionStaff
	 */
	private void initOrderPresentor()
	{
		if (orderPresentor == null)
		{
			orderPresentor = (SupervisionStaff) new mojo.km.persistence.Reference(
					orderPresentorId, SupervisionStaff.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initOrderStatus()
	{
		if (orderStatus == null)
		{
			orderStatus = (Code) new mojo.km.persistence.Reference(orderStatusId, Code.class,
					"ORDER_STATUS").getObject();
		}
	}

	/**
	 * Initialize class relationship to class
	 * pd.codetable.supervision.SupervisionCode
	 */
	private void initOrderTitle()
	{
		if (orderTitle == null)
		{
			orderTitle = (SupervisionCode) new mojo.km.persistence.Reference(orderTitleId,
					SupervisionCode.class, "ORDER_TITLE").getObject();
		}
	}

	/**
	 * Initialize class relationship to class
	 * pd.supervision.supervisionorder.SupervisionOrder
	 */
	private void initParentSupervisionOrder()
	{
		if (parentSupervisionOrder == null)
		{
			parentSupervisionOrder = (SupervisionOrder) new mojo.km.persistence.Reference(
					parentSupervisionOrderId, SupervisionOrder.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class
	 * pd.supervision.supervisionorder.SupervisionOrderPrintTemplate
	 */
	private void initPrintTemplate()
	{
		if (printTemplate == null)
		{
			printTemplate = (SupervisionOrderPrintTemplate) new mojo.km.persistence.Reference(
					printTemplateId, SupervisionOrderPrintTemplate.class).getObject();
		}
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.supervision.supervision.supervisionorder.SupervisionOrderReinstatement
	 * @methodInvocation bind
	 * @methodInvocation bind
	 */
	private void initReinstatements()
	{
		if (reinstatements == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			reinstatements = new mojo.km.persistence.ArrayList(
					SupervisionOrderReinstatement.class, "supervisionOrderId", ""
							+ getOID());
		}
	}
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initSignedByType()
	{
		if (signedByType == null)
		{
			signedByType = (Code) new mojo.km.persistence.Reference(signedByTypeId,
					Code.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initSpecialCourt()
	{
		if (specialCourt == null)
		{
			specialCourt = (Code) new mojo.km.persistence.Reference(specialCourtCd, Code.class,
					"SPECIAL_COURT").getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.criminalcase.Supervision
	 */
	private void initSupervision()
	{
		if (supervision == null)
		{
			supervision = (Supervision) new mojo.km.persistence.Reference(supervisionId,
					Supervision.class).getObject();
		}
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.supervision.supervisionorder.SupervisionOrderConditionRel
	 * @methodInvocation bind
	 * @methodInvocation bind
	 */
	private void initSupervisionOrderConditionRels()
	{
		if (supervisionOrderConditionRels == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			supervisionOrderConditionRels = new mojo.km.persistence.ArrayList(
					SupervisionOrderConditionRel.class, "supervisionOrderId", ""
							+ getOID());
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.supervisionorder.SupervisionPeriod
	 */
	private void initSupervisionPeriod()
	{
		if (supervisionPeriod == null)
		{
			supervisionPeriod = (SupervisionPeriod) new mojo.km.persistence.Reference(
					supervisionPeriodId, SupervisionPeriod.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initVersionType()
	{
		if (versionType == null)
		{
			versionType = (Code) new mojo.km.persistence.Reference(versionTypeId, Code.class,
					"VERSION_TYPE").getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initWithdrawReason()
	{
		if (withdrawReason == null)
		{
			withdrawReason = (Code) new mojo.km.persistence.Reference(versionTypeId,
					Code.class, "WITHDRAW_REASON").getObject();
		}
	}

	/**
	 * insert a pd.supervision.supervisionorder.SupervisionOrderReinstatement
	 * into class relationship collection.
	 * @methodInvocation initReinstatements
	 * @methodInvocation add
	 * @methodInvocation initReinstatements
	 * @methodInvocation add
	 */
	public void insertReinstatements(SupervisionOrderReinstatement anObject)
	{
		initReinstatements();
		reinstatements.add(anObject);
	}

	/**
	 * insert a pd.supervision.supervisionorder.SupervisionOrderConditionRel
	 * into class relationship collection.
	 * @methodInvocation initSupervisionOrderConditionRels
	 * @methodInvocation add
	 * @methodInvocation initSupervisionOrderConditionRels
	 * @methodInvocation add
	 */
	public void insertSupervisionOrderConditionRels(
			SupervisionOrderConditionRel anObject)
	{
		initSupervisionOrderConditionRels();
		supervisionOrderConditionRels.add(anObject);
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public boolean isLimitedSupervisionPeriod()
	{
		fetch();
		return limitedSupervisionPeriod;
	}

	/**
	 * Determines whether order data can be refreshed.
	 * @return 
	 * @methodInvocation fetch
	 */
	public boolean isOrderDataRefreshable()
	{
		fetch();
		boolean isRefreshable = false;
		if (this.orderStatusId.equals(PDCodeTableConstants.STATUS_DRAFT_ID)
				|| this.orderStatusId.equals(PDCodeTableConstants.STATUS_INCOMPLETE_ID)
				|| this.orderStatusId.equals(PDCodeTableConstants.STATUS_PENDING_ID))
		{
			isRefreshable = true;
		}
		return isRefreshable;
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public boolean isOrderInProgress()
	{
		fetch();
		return orderInProgress;
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public boolean isOrderSignedByDefendant()
	{
		fetch();
		return orderSignedByDefendant;
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public boolean isPrintSpanish()
	{
		fetch();
		return printSpanish;
	}

	/**
	 * Removes a pd.supervision.supervisionorder.SupervisionOrderReinstatement
	 * from class relationship collection.
	 * @methodInvocation initReinstatements
	 * @methodInvocation remove
	 * @methodInvocation initReinstatements
	 * @methodInvocation remove
	 */
	public void removeReinstatements(SupervisionOrderReinstatement anObject)
	{
		initReinstatements();
		reinstatements.remove(anObject);
	}

	/**
	 * Removes a pd.supervision.supervisionorder.SupervisionOrderConditionRel
	 * from class relationship collection.
	 * @methodInvocation initSupervisionOrderConditionRels
	 * @methodInvocation remove
	 * @methodInvocation initSupervisionOrderConditionRels
	 * @methodInvocation remove
	 */
	public void removeSupervisionOrderConditionRels(
			SupervisionOrderConditionRel anObject)
	{
		initSupervisionOrderConditionRels();
		supervisionOrderConditionRels.remove(anObject);
	}

	/**
	 * @param aDate
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setActivationDate(Date aDate)
	{
		if (this.activationDate == null || !this.activationDate.equals(aDate))
		{
			markModified();
		}
		activationDate = aDate;
	}

	/**
	 * set the type reference for class member agency
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setAgencyId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setAgencyId
	 */
	public void setAgency(Agency anAgency)
	{
		if (this.agency == null || !this.agency.equals(anAgency))
		{
			markModified();
		}
		if (anAgency.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(anAgency);
		}
		setAgencyId("" + anAgency.getOID());
		this.agency = (Agency) new mojo.km.persistence.Reference(anAgency).getObject();
	}

	/**
	 * Set the reference value to class :: pd.contact.agency.Agency
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setAgencyId(String anAgencyId)
	{
		if (this.agencyId == null || !this.agencyId.equals(anAgencyId))
		{
			markModified();
		}
		agency = null;
		this.agencyId = anAgencyId;
	}

	/**
     * @param aCaseSupervisionBeginDate The caseSupervisionBeginDate to set.
     */
    public void setCaseSupervisionBeginDate(Date aCaseSupervisionBeginDate) {
        
		if (this.caseSupervisionBeginDate == null || !this.caseSupervisionBeginDate.equals(aCaseSupervisionBeginDate))
		{
			markModified();
		}
		this.caseSupervisionBeginDate = aCaseSupervisionBeginDate;

    }

	/**
     * @param aCaseSupervisionEndDate The caseSupervisionEndDate to set.
     */
    public void setCaseSupervisionEndDate(Date aCaseSupervisionEndDate) {
		if (this.caseSupervisionEndDate == null || !this.caseSupervisionEndDate.equals(aCaseSupervisionEndDate))
		{
			markModified();
		}
        this.caseSupervisionEndDate = aCaseSupervisionEndDate;
    }

	public void setComments(String comments) {
		if (this.comments == null || !this.comments.equals(comments))
		{
			markModified();
		}
		this.comments = comments;
	}
    /**
	 * @param confinementLengthDays The confinementLengthDays to set.
	 * @methodInvocation markModified
	 */
	public void setConfinementLengthDays(int confinementLengthDays)
	{
		if (this.confinementLengthDays != confinementLengthDays)
		{
			markModified();
		}
		this.confinementLengthDays = confinementLengthDays;
	}

    /**
	 * @param confinementLength The confinementLength to set.
	 * @methodInvocation markModified
	 */
	public void setConfinementLengthMonths(int confinementLengthMonths)
	{
		if (this.confinementLengthMonths != confinementLengthMonths)
		{
			markModified();
		}
		this.confinementLengthMonths = confinementLengthMonths;
	}

	/**
	 * @param confinementLengthYears The confinementLengthYears to set.
	 * @methodInvocation markModified
	 */
	public void setConfinementLengthYears(int confinementLengthYears)
	{
		if (this.confinementLengthYears != confinementLengthYears)
		{
			markModified();
		}
		this.confinementLengthYears = confinementLengthYears;
	}

	/**
	 * set the type reference for class member criminalCase
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setCriminalCaseId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setCriminalCaseId
	 */
	public void setCriminalCase(CriminalCase aCase)
	{
		if (this.criminalCase == null || !this.criminalCase.equals(aCase))
		{
			markModified();
		}
		if (aCase.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aCase);
		}
		setCriminalCaseId("" + aCase.getOID());
		this.criminalCase = (CriminalCase) new mojo.km.persistence.Reference(aCase).getObject();
	}

	/**
	 * Set the reference value to class :: pd.criminalcase.CriminalCase
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setCriminalCaseId(String aCaseId)
	{
		if (this.criminalCaseId == null || !this.criminalCaseId.equals(aCaseId))
		{
			markModified();
		}
		criminalCase = null;
		this.criminalCaseId = aCaseId;
	}

	/**
	 * set the type reference for class member orderCourt
	 */
	public void setCurrentCourt(pd.supervision.Court aCourt)
	{
		if (this.currentCourt == null || !this.currentCourt.equals(aCourt))
		{
			markModified();
		}
		if (aCourt.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aCourt);
		}
		setCurrentCourtId("" + aCourt.getOID());
		this.currentCourt = (pd.supervision.Court) new mojo.km.persistence.Reference(aCourt).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.Court
	 */
	public void setCurrentCourtId(String aCourtId)
	{
		if (this.currentCourtId == null || !this.currentCourtId.equals(aCourtId))
		{
			markModified();
		}
		currentCourt = null;
		this.currentCourtId = aCourtId;
	}

	/**
	 * set the type reference for class member defendant
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setDefendantId
	 */
	public void setDefendant(Party defendant)
	{
		if (this.defendant == null || !this.defendant.equals(defendant))
		{
			markModified();
		}
		if (defendant.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(defendant);
		}
		setDefendantId("" + defendant.getOID());
		this.defendant = (Party) new mojo.km.persistence.Reference(defendant).getObject();
	}

	/**
	 * @param aDefendant
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setDefendantId(String aDefendantId)
	{
		if (this.defendantId == null || !this.defendantId.equals(aDefendantId))
		{
			markModified();
		}
		this.defendantId = aDefendantId;
	}

	/**
	 * set the type reference for class member dispositionType
	 */
	public void setDispositionType(Code dispositionType)
	{
		if (this.dispositionType == null || !this.dispositionType.equals(dispositionType))
		{
			markModified();
		}
		if (dispositionType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(dispositionType);
		}
		setDispositionTypeId("" + dispositionType.getOID());
		this.dispositionType = (Code) new mojo.km.persistence.Reference(dispositionType).getObject();
	}
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setDispositionTypeId(String dispositionTypeId)
	{
		if (this.dispositionTypeId == null || !this.dispositionTypeId.equals(dispositionTypeId))
		{
			markModified();
		}
		dispositionType = null;
		this.dispositionTypeId = dispositionTypeId;
	}

	/**
     * @param fineAmount The fineAmount to set.
     */
    public void setFineAmount(double fineAmount) {
		if (!(this.fineAmount == fineAmount))
		{
			markModified();
		}
        this.fineAmount = fineAmount;
    }
	/**
	 * @param inactivationDate
	 The inactivationDate to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setInactivationDate(Date aDate)
	{
		if (this.inactivationDate == null || !this.inactivationDate.equals(aDate))
		{
			markModified();
		}
		inactivationDate = aDate;
	}

	/**
     * @param isHistoricalOrder The isHistoricalOrder to set.
     */
    public void setIsHistoricalOrder(boolean isHistoricalOrder) {
		if (this.isHistoricalOrder != isHistoricalOrder)
		{
			markModified();
		}
       
        this.isHistoricalOrder = isHistoricalOrder;
    }
    /**
	 * set the type reference for class member orderCourt
	 */
	public void setJuvenileCourt(pd.supervision.Court aCourt)
	{
		if (this.juvenileCourt == null || !this.juvenileCourt.equals(aCourt))
		{
			markModified();
		}
		if (aCourt.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aCourt);
		}
		setJuvenileCourtId("" + aCourt.getOID());
		this.juvenileCourt = (pd.supervision.Court) new mojo.km.persistence.Reference(aCourt).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.Court
	 */
	public void setJuvenileCourtId(String aCourtId)
	{
		if (this.juvenileCourtId == null || !this.juvenileCourtId.equals(aCourtId))
		{
			markModified();
		}
		juvenileCourt = null;
		this.juvenileCourtId = aCourtId;
	}
    public void setJuvSupervisionBeginDate(Date juvSupervisionBeginDate) {
		if (this.juvSupervisionBeginDate == null || !this.juvSupervisionBeginDate.equals(juvSupervisionBeginDate))
		{
			markModified();
		}
		this.juvSupervisionBeginDate = juvSupervisionBeginDate;
	}

	public void setJuvSupervisionLenDays(int juvSupervisionLenDays) {
		if (this.juvSupervisionLenDays != juvSupervisionLenDays)
		{
			markModified();
		}
		this.juvSupervisionLenDays = juvSupervisionLenDays;
	}

	public void setJuvSupervisionLenMonths(int juvSupervisionLenMonths) {
		if (this.juvSupervisionLenMonths != juvSupervisionLenMonths)
		{
			markModified();
		}
		this.juvSupervisionLenMonths = juvSupervisionLenMonths;
	}

	public void setJuvSupervisionLenYears(int juvSupervisionLenYears) {
		if (this.juvSupervisionLenYears != juvSupervisionLenYears)
		{
			markModified();
		}
		this.juvSupervisionLenYears = juvSupervisionLenYears;
	}

	/**
	 * @param aDate
	 * @methodInvocation markModified
	 */
	public void setLimitedSupervisionBeginDate(Date aDate)
	{
		if (this.limitedSupervisionBeginDate == null || !this.limitedSupervisionBeginDate.equals(aDate))
		{
			markModified();
		}
		limitedSupervisionBeginDate = aDate;
	}

	/**
	 * @param aDate
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setLimitedSupervisionEndDate(Date aDate)
	{
		if (this.limitedSupervisionEndDate == null || !this.limitedSupervisionEndDate.equals(aDate))
		{
			markModified();
		}
		limitedSupervisionEndDate = aDate;
	}

	/**
	 * @param b
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setLimitedSupervisionPeriod(boolean b)
	{
		if (this.limitedSupervisionPeriod != b)
		{
			markModified();
		}
		limitedSupervisionPeriod = b;
	}

	/**
	 * set the type reference for class member magistrate
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setMagistrateId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setMagistrateId
	 */
	public void setMagistrate(Magistrate aMagistrate)
	{
		if (this.magistrate == null || !this.magistrate.equals(aMagistrate))
		{
			markModified();
		}
		if (aMagistrate.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aMagistrate);
		}
		setMagistrateId("" + aMagistrate.getOID());
		this.magistrate = (Magistrate) new mojo.km.persistence.Reference(aMagistrate)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setMagistrateId(String aMagistrateId)
	{
		if (this.magistrateId == null || !this.magistrateId.equals(aMagistrateId))
		{
			markModified();
		}
		magistrate = null;
		this.magistrateId = aMagistrateId;
	}

	/**
	 * @param string
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setModificationReason(String aString)
	{
		if (this.modificationReason == null || !this.modificationReason.equals(aString))
		{
			markModified();
		}
		modificationReason = aString;
	}

	/**
	 * set the type reference for class member offense
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setOffenseId
	 */
//	public void setOffense(pd.codetable.criminal.OffenseCode offense)
//	{
//		if (this.offense == null || !this.offense.equals(offense))
//		{
//			markModified();
//		}
//		if (offense.getOID() == null)
//		{
//			new mojo.km.persistence.Home().bind(offense);
//		}
//		setOffenseId("" + offense.getOID());
//		this.offense = (pd.codetable.criminal.OffenseCode) new mojo.km.persistence.Reference(offense).getObject();
//	}

	/**
	 * Set the reference value to class :: pd.codetable.criminal.OffenseCode
	 * @methodInvocation markModified
	 */
	public void setOffenseId(String offenseId)
	{
//		if (this.offenseId == null || !this.offenseId.equals(offenseId))
//		{
//			markModified();
//		}
		//offense = null;
//		this.offenseId = offenseId;
	}

	/**
	 * @param aOrderChainNum
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setOrderChainNum(int aOrderChainNum)
	{
		if(aOrderChainNum==0)  // default value for orderr chain num is 1
			aOrderChainNum=1;
		if (this.orderChainNum != aOrderChainNum)
		{
			markModified();
		}
		orderChainNum = aOrderChainNum;
	}

	/**
	 * set the type reference for class member orderCourt
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setOrderCourtId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setOrderCourtId
	 */
	public void setOrderCourt(pd.supervision.Court aCourt)
	{
		if (this.orderCourt == null || !this.orderCourt.equals(aCourt))
		{
			markModified();
		}
		if (aCourt.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aCourt);
		}
		setOrderCourtId("" + aCourt.getOID());
		this.orderCourt = (pd.supervision.Court) new mojo.km.persistence.Reference(aCourt).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.Court
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setOrderCourtId(String aCourtId)
	{
		if (this.orderCourtId == null || !this.orderCourtId.equals(aCourtId))
		{
			markModified();
		}
		orderCourt = null;
		this.orderCourtId = aCourtId;
	}

	/**
	 * @param date
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setOrderFiledDate(Date aDate)
	{
		if (this.orderFiledDate == null || !this.orderFiledDate.equals(aDate))
		{
			markModified();
		}
		orderFiledDate = aDate;
	}

	/**
	 * @param b
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setOrderInProgress(boolean b)
	{
		if (this.orderInProgress != b)
		{
			markModified();
		}
		orderInProgress = b;
	}

	/**
	 * set the type reference for class member orderPresentor
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setOrderPresentorId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setOrderPresentorId
	 */
	public void setOrderPresentor(SupervisionStaff anOrderPresentor)
	{
		if (this.orderPresentor == null || !this.orderPresentor.equals(anOrderPresentor))
		{
			markModified();
		}
		if (anOrderPresentor.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(anOrderPresentor);
		}
		setOrderPresentorId("" + anOrderPresentor.getOID());
		this.orderPresentor = (SupervisionStaff) new mojo.km.persistence.Reference(
				anOrderPresentor).getObject();
	}

	/**
	 * @param aString
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setOrderPresentorFirstName(String aString)
	{
		if (this.orderPresentorFirstName == null || !this.orderPresentorFirstName.equals(aString))
		{
			markModified();
		}
		orderPresentorFirstName = aString;
	}

	/**
	 * Set the reference value to class :: pd.contact.user.UserProfile
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setOrderPresentorId(String anOrderPresentorId)
	{
		if (this.orderPresentorId == null || !this.orderPresentorId.equals(anOrderPresentorId))
		{
			markModified();
		}
		orderPresentor = null;
		this.orderPresentorId = anOrderPresentorId;
	}

	/**
	 * @param aString
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setOrderPresentorLastName(String aString)
	{
		if (this.orderPresentorLastName == null || !this.orderPresentorLastName.equals(aString))
		{
			markModified();
		}
		orderPresentorLastName = aString;
	}

	/**
	 * @param b
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setOrderSignedByDefendant(boolean b)
	{
		if (this.orderSignedByDefendant != b)
		{
			markModified();
		}
		orderSignedByDefendant = b;
	}

	/**
	 * @param aDate
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setOrderSignedByDefendantDate(Date aDate)
	{
		if (this.orderSignedByDefendantDate == null || !this.orderSignedByDefendantDate.equals(aDate))
		{
			markModified();
		}
		orderSignedByDefendantDate = aDate;
	}

	public void setOrderSignedByJudgeDate(Date orderSignedByJudgeDate) {
		
		if (this.orderSignedByJudgeDate == null || !this.orderSignedByJudgeDate.equals(orderSignedByJudgeDate))
		{
			markModified();
		}

		this.orderSignedByJudgeDate = orderSignedByJudgeDate;
	}
	
	/**
	 * @param aDate
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setOrderSignedDate(Date aDate)
	{
		if (this.orderSignedDate == null || !this.orderSignedDate.equals(aDate))
		{
			markModified();
		}
		orderSignedDate = aDate;
	}

	/**
	 * set the type reference for class member orderStatus
	 */
	public void setOrderStatus(Code anOrderStatus)
	{
		if (this.orderStatus == null || !this.orderStatus.equals(anOrderStatus))
		{
			markModified();
		}
		if (anOrderStatus.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(anOrderStatus);
		}
		setOrderStatusId("" + anOrderStatus.getOID());
		anOrderStatus.setContext("ORDER_STATUS");
		this.orderStatus = (Code) new mojo.km.persistence.Reference(anOrderStatus).getObject();
	}
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setOrderStatusId(String anOrderStatusId)
	{
		if (this.orderStatusId == null || !this.orderStatusId.equals(anOrderStatusId))
		{
			markModified();
		}
		orderStatus = null;
		this.orderStatusId = anOrderStatusId;
	}

	/**
	 * set the type reference for class member orderTitle
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setOrderTitleId
	 * @methodInvocation setContext
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setOrderTitleId
	 * @methodInvocation setContext
	 */
	public void setOrderTitle(SupervisionCode anOrderTitle)
	{
		if (this.orderTitle == null || !this.orderTitle.equals(anOrderTitle))
		{
			markModified();
		}
		if (anOrderTitle.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(anOrderTitle);
		}
		setOrderTitleId("" + anOrderTitle.getOID());
		anOrderTitle.setContext("ORDER_TITLE");
		this.orderTitle = (SupervisionCode) new mojo.km.persistence.Reference(anOrderTitle)
				.getObject();
	}
	/**
	 * Set the reference value to class ::
	 * pd.codetable.supervision.SupervisionCode
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setOrderTitleId(String anOrderTitleId)
	{
		if (this.orderTitleId == null || !this.orderTitleId.equals(anOrderTitleId))
		{
			markModified();
		}
		orderTitle = null;
		this.orderTitleId = anOrderTitleId;
	}

	public void setOrigJudgeFirstName(String origJudgeFirstName) {
		if (this.origJudgeFirstName == null || !this.origJudgeFirstName.equals(origJudgeFirstName))
		{
			markModified();
		}
		this.origJudgeFirstName = origJudgeFirstName;
	}
	public void setOrigJudgeLastName(String origJudgeLastName) {
		if (this.origJudgeLastName == null || !this.origJudgeLastName.equals(origJudgeLastName))
		{
			markModified();
		}

		this.origJudgeLastName = origJudgeLastName;
	}

	/**
	 * @param anOrder
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setParentSupervisionOrder(SupervisionOrder anOrder)
	{
		if (this.parentSupervisionOrder == null || !this.parentSupervisionOrder.equals(anOrder))
		{
			markModified();
		}
		parentSupervisionOrder = anOrder;
	}

	/**
	 * @param aParentSupervisionOrderId
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setParentSupervisionOrderId(String aParentSupervisionOrderId)
	{
		if (this.parentSupervisionOrderId == null || !this.parentSupervisionOrderId.equals(aParentSupervisionOrderId))
		{
			markModified();
		}
		parentSupervisionOrderId = aParentSupervisionOrderId;
	}

	/**
	 * @param plea
	 The plea to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setPlea(String aPlea)
	{
		if (this.plea == null || !this.plea.equals(aPlea))
		{
			markModified();
		}
		plea = aPlea;
	}

	public void setPrintedName(String printedName) {
		if (this.printedName == null || !this.printedName.equals(printedName))
		{
			markModified();
		}

		this.printedName = printedName;
	}

	public void setPrintedOffenseDesc(String printedOffenseDesc) {
		if (this.printedOffenseDesc == null || !this.printedOffenseDesc.equals(printedOffenseDesc))
		{
			markModified();
		}
		this.printedOffenseDesc = printedOffenseDesc;
	}

	/**
	 * @param b
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setPrintSpanish(boolean b)
	{
		if (this.printSpanish != b)
		{
			markModified();
		}
		printSpanish = b;
	}

	/**
	 * set the type reference for class member printTemplate
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setPrintTemplateId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setPrintTemplateId
	 */
	public void setPrintTemplate(SupervisionOrderPrintTemplate aPrintTemplate)
	{
		if (this.printTemplate == null || !this.printTemplate.equals(aPrintTemplate))
		{
			markModified();
		}
		if (aPrintTemplate.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aPrintTemplate);
		}
		setPrintTemplateId("" + aPrintTemplate.getOID());
		this.printTemplate = (SupervisionOrderPrintTemplate) new mojo.km.persistence.Reference(
				aPrintTemplate).getObject();
	}

	/**
	 * Set the reference value to class ::
	 * pd.supervision.supervisionorder.SupervisionOrderPrintTemplate
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setPrintTemplateId(String aPrintTemplateId)
	{
		if (this.printTemplateId == null || !this.printTemplateId.equals(aPrintTemplateId))
		{
			markModified();
		}
		printTemplate = null;
		this.printTemplateId = aPrintTemplateId;
	}

	/**
	 * @param collection
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setReinstatements(Collection aCollection)
	{
		if (this.reinstatements == null || !this.reinstatements.equals(aCollection))
		{
			markModified();
		}
		reinstatements = aCollection;
	}

	/**
	 * @param aRevisionDate
	 The revisionDate to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setRevisionDate(Date aRevisionDate)
	{
		if (this.revisionDate == null || !this.revisionDate.equals(aRevisionDate))
		{
			markModified();
		}
		this.revisionDate = aRevisionDate;
	}

	/**
	 * @param aSignedByFirstName
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setSignedByFirstName(String aSignedByFirstName)
	{
		if (this.signedByFirstName == null || !this.signedByFirstName.equals(aSignedByFirstName))
		{
			markModified();
		}
		signedByFirstName = aSignedByFirstName;
	}

	/**
	 * @param aSignedByLastName
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setSignedByLastName(String aSignedByLastName)
	{
		if (this.signedByLastName == null || !this.signedByLastName.equals(aSignedByLastName))
		{
			markModified();
		}
		signedByLastName = aSignedByLastName;
	}

	/**
	 * set the type reference for class member signedByType
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setSignedByTypeId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setSignedByTypeId
	 */
	public void setSignedByType(Code aSignedByType)
	{
		if (this.signedByType == null || !this.signedByType.equals(aSignedByType))
		{
			markModified();
		}
		if (aSignedByType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aSignedByType);
		}
		setSignedByTypeId("" + aSignedByType.getOID());
		this.signedByType = (Code) new mojo.km.persistence.Reference(aSignedByType).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setSignedByTypeId(String aSignedByTypeId)
	{
		if (this.signedByTypeId == null || !this.signedByTypeId.equals(aSignedByTypeId))
		{
			markModified();
		}
		signedByType = null;
		this.signedByTypeId = aSignedByTypeId;
	}

	/**
	 * set the type reference for class member orderStatus
	 */
	public void setSpecialCourt(Code specialCourt)
	{
		if (this.specialCourt == null || !this.specialCourt.equals(specialCourt))
		{
			markModified();
		}
		if (specialCourt.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(specialCourt);
		}
		setSpecialCourtCd("" + specialCourt.getOID());
		specialCourt.setContext("SPECIAL_COURT");
		this.specialCourt = (Code) new mojo.km.persistence.Reference(specialCourt).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setSpecialCourtCd(String specialCourtCd)
	{
		if (this.specialCourtCd == null || !this.orderStatusId.equals(specialCourtCd))
		{
			markModified();
		}
		specialCourt = null;
		this.specialCourtCd = specialCourtCd;
	}

	/**
	 * @param string
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setSuggestedOrderId(String aString)
	{
		if (this.suggestedOrderId == null || !this.suggestedOrderId.equals(aString))
		{
			markModified();
		}
		suggestedOrderId = aString;
	}

	/**
	 * @param string
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setSummaryChanges(String aString)
	{
		if (this.summaryChanges == null || !this.summaryChanges.equals(aString))
		{
			markModified();
		}
		summaryChanges = aString;
	}

	/**
	 * set the type reference for class member supervision
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setSupervisionId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setSupervisionId
	 */
	public void setSupervision(Supervision aSupervision)
	{
		if (this.supervision == null || !this.supervision.equals(aSupervision))
		{
			markModified();
		}
		if (aSupervision.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aSupervision);
		}
		setSupervisionId("" + aSupervision.getOID());
		this.supervision = (Supervision) new mojo.km.persistence.Reference(aSupervision).getObject();
	}

	/**
	 * Set the reference value to class :: pd.criminalcase.Supervision
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setSupervisionId(String aSupervisionId)
	{
		if (this.supervisionId == null || !this.supervisionId.equals(aSupervisionId))
		{
			markModified();
		}
		this.supervisionId = aSupervisionId;
	}

	/**
	 * @param supervisionLengthDays The supervisionLengthDays to set.
	 * @methodInvocation markModified
	 */
	public void setSupervisionLengthDays(int supervisionLengthDays)
	{
		if (this.supervisionLengthDays != supervisionLengthDays)
		{
			markModified();
		}
		this.supervisionLengthDays = supervisionLengthDays;
	}
	
	/**
	 * @param supervisionLength The supervisionLength to set.
	 * @methodInvocation markModified
	 */
	public void setSupervisionLengthMonths(int supervisionLengthMonths)
	{
		if (this.supervisionLengthMonths != supervisionLengthMonths)
		{
			markModified();
		}
		this.supervisionLengthMonths = supervisionLengthMonths;
	}

	/**
	 * @param supervisionLengthYears The supervisionLengthYears to set.
	 * @methodInvocation markModified
	 */
	public void setSupervisionLengthYears(int supervisionLengthYears)
	{
		if (this.supervisionLengthYears != supervisionLengthYears)
		{
			markModified();
		}
		this.supervisionLengthYears = supervisionLengthYears;
	}

	/**
	 * @param collection
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setSupervisionOrderConditionRels(Collection aCollection)
	{
		if (this.supervisionOrderConditionRels == null || !this.supervisionOrderConditionRels.equals(aCollection))
		{
			markModified();
		}
		supervisionOrderConditionRels = aCollection;
	}

	/**
	 * set the type reference for class member supervisionPeriod
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setSupervisionPeriodId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setSupervisionPeriodId
	 */
	public void setSupervisionPeriod(SupervisionPeriod aSupervisionPeriod)
	{
		if (this.supervisionPeriod == null || !this.supervisionPeriod.equals(aSupervisionPeriod))
		{
			markModified();
		}
		if (aSupervisionPeriod.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aSupervisionPeriod);
		}
		setSupervisionPeriodId("" + aSupervisionPeriod.getOID());
		this.supervisionPeriod = (SupervisionPeriod) new mojo.km.persistence.Reference(
				aSupervisionPeriod).getObject();
	}

	/**
	 * Set the reference value to class ::
	 * pd.supervision.supervisionorder.SupervisionPeriod
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setSupervisionPeriodId(String aSupervisionPeriodId)
	{
		if (this.supervisionPeriodId == null || !this.supervisionPeriodId.equals(aSupervisionPeriodId))
		{
			markModified();
		}
		//		supervisionPeriod = null;
		this.supervisionPeriodId = aSupervisionPeriodId;
	}
	public Date getTransferInDate() {
		fetch();
		return transferInDate;
	}
	public void setTransferInDate(Date transferInDate) {
		if (this.transferInDate == null || !this.transferInDate.equals(transferInDate))
		{
			markModified();
		}

		this.transferInDate = transferInDate;
	}


	/**
	 * @param date
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setUpdateDate(Date aDate)
	{
		if (this.updateDate == null || !this.updateDate.equals(aDate))
		{
			markModified();
		}
		updateDate = aDate;
	}  
	

	/**
	 * @param date
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setStatusChangeDate(Date aDate)
	{
		if (this.statusChangeDate == null || !this.statusChangeDate.equals(aDate))
		{
			markModified();
		}
		statusChangeDate = aDate;
	} 
	
	/**
	 * @param aVersionNum
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setVersionNum(int aVersionNum)
	{
		if (this.versionNum != aVersionNum)
		{
			markModified();
		}
		versionNum = aVersionNum;
	}

	/**
	 * set the type reference for class member versionType
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setVersionTypeId
	 * @methodInvocation setContext
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setVersionTypeId
	 * @methodInvocation setContext
	 */
	public void setVersionType(Code aVersionType)
	{
		if (this.versionType == null || !this.versionType.equals(aVersionType))
		{
			markModified();
		}
		if (aVersionType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aVersionType);
		}
		setVersionTypeId("" + aVersionType.getOID());
		aVersionType.setContext("VERSION_TYPE");
		this.versionType = (Code) new mojo.km.persistence.Reference(aVersionType).getObject();
	}
	
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setVersionTypeId(String aVersionTypeId)
	{
		if (this.versionTypeId == null || !this.versionTypeId.equals(aVersionTypeId))
		{
			markModified();
		}
		versionType = null;
		this.versionTypeId = aVersionTypeId;
	}
	
	
	/**
	 * set the type reference for class member withdrawReason
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setWithdrawReasonId
	 * @methodInvocation setContext
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setWithdrawReasonId
	 * @methodInvocation setContext
	 */
	public void setWithdrawReason(Code aWithdrawReason)
	{
		if (this.withdrawReason == null || !this.versionType.equals(aWithdrawReason))
		{
			markModified();
		}
		if (aWithdrawReason.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aWithdrawReason);
		}
		setWithdrawReasonId("" + aWithdrawReason.getOID());
		aWithdrawReason.setContext("WITHDRAW_REASON");
		this.withdrawReason = (Code) new mojo.km.persistence.Reference(aWithdrawReason).getObject();
	}
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setWithdrawReasonId(String aWithdrawReasonId)
	{
		if (this.withdrawReasonId == null || !this.withdrawReasonId.equals(aWithdrawReasonId))
		{
			markModified();
		}
		withdrawReason = null;
		this.withdrawReasonId = aWithdrawReasonId;
	}

	/**
	 * returns a collection of
	 * pd.supervision.supervisionorder.SupervisionOrderConditionRel
	 * @methodInvocation markModified
	 * @methodInvocation fetch
	 * @methodInvocation initSupervisionOrderConditionRels
	 * @methodInvocation markModified
	 * @methodInvocation fetch
	 * @methodInvocation initSupervisionOrderConditionRels
	 */
	public Collection supervisionConditionRels()
	{
		fetch();
		initSupervisionOrderConditionRels();
		return supervisionOrderConditionRels;
	}

	/**
	 * Comparator used to sort a collection of GetMostRecentInactiveOrderForSpnEvent events
	 * based on the versionType (A, then O, then M), and then on the versionNum (most recent/largest)
	 */
	public static Comparator GetMostRecentInactiveOrdersForSpnByVersionTypeVersionNumberComparator = new Comparator() 
	{
		public int compare(Object caseOrder, Object otherCaseOrder) {
			int compareResult = 0;
			int versionTypeRank1 = 0;
			int versionTypeRank2 = 0;
			
			SupervisionOrder caseOrder1 = ((SupervisionOrder)caseOrder);
			SupervisionOrder caseOrder2 = ((SupervisionOrder)otherCaseOrder);
			if(caseOrder1 == null || caseOrder2 == null){
				return compareResult;
			}
			String versionType1 = caseOrder1.getVersionTypeId();
			String versionType2 = caseOrder2.getVersionTypeId();		
			Integer versionNum1 = caseOrder1.getVersionNum();
			Integer versionNum2 = caseOrder2.getVersionNum();
			// check for one of versionType values being null to avoid null pointer
			if( versionType1 == null && versionType2 != null){
				return -1;
			}else if(versionType1 != null && versionType2 == null){
				return 1;
			}
			// assign value to possible values for ranking
			if(versionType1.equalsIgnoreCase("A")){
				versionTypeRank1 = 2;
			}else if(versionType1.equalsIgnoreCase("O")){
				versionTypeRank1 = 1;
			}
			if(versionType2.equalsIgnoreCase("A")){
				versionTypeRank2 = 2;
			}else if(versionType2.equalsIgnoreCase("O")){
				versionTypeRank2 = 1;
			}
			

			// rank based on versionTypeId
			if(versionTypeRank1 > versionTypeRank2){
				compareResult = -1;
			}else if(versionTypeRank1 < versionTypeRank2){
				compareResult = 1;
			}
			// rank based on versionNum if versionType equal
			if(compareResult == 0 && (versionNum1 != null && versionNum2 != null) ){
				return versionNum2.compareTo(versionNum1);
			}
				
			return compareResult;
		}		
	};

}

