/*
 * Created on Mar 31, 2006
 *
 */
package pd.supervision.supervisionorder;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import naming.PDCodeTableConstants;

import messaging.supervisionorder.GetSupervisionOrderConditionValuesEvent;
import messaging.supervisionorder.reply.SupOrderConditionRelValueResponseEvent;
import messaging.supervisionorder.reply.SupOrderConditionResponseEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;

/**
 * @author asrvastava
 *
 */
public final class ImpactedOrderHelper
{
	/**
	 * 
	 */
	private ImpactedOrderHelper()
	{
		super();
	}

	/**
	 * @param orderId
	 * @param impactedOrderMap
	 * @param likeCondMap
	 */
	public static void getImpactedOrders(String orderId, Map impactedOrderMap, Map likeCondMap)
	{
		//find the impacting order
		SupervisionOrder order = SupervisionOrder.find(orderId);
		Map orderValueMap = createOrderValueMap(order);

		// find all the like conditions
		GetSupervisionOrderConditionValuesEvent orderCondValueEvent = populateConditionValueEvent(order);
		// only active orders can be impacted
		orderCondValueEvent.setOrderStatus(PDCodeTableConstants.STATUS_ACTIVE_ID);
		Iterator orderCondValues = SupervisionOrderConditionValue.findAll(orderCondValueEvent);
		while (orderCondValues.hasNext())
		{
			SupervisionOrderConditionValue orderCondValue = (SupervisionOrderConditionValue) orderCondValues.next();
			// select it only when
			// 1. same variable element type
			// 2. different value 
			// 3. different criminalcase
			Object valObj =
				orderValueMap.get(orderCondValue.getConditionId() + "^" + orderCondValue.getVariableElementTypeId());
			if (valObj != null
				&& orderCondValue.getValue() != null
				&& !orderCondValue.getValue().equals(valObj)
				&& !orderCondValue.getCriminalCaseId().equals(order.getCriminalCaseId()))
			{
				//--------------- fill impactedOrderMap
				Set orderConds = (Set) impactedOrderMap.get(orderCondValue.getOrderId());
				if (orderConds == null)
				{
					orderConds = new HashSet();
					impactedOrderMap.put(orderCondValue.getOrderId(), orderConds);
				}
				// add conditionId in the collection
				orderConds.add(orderCondValue.getConditionId());

				//--------------- fill likeConditionMap
				Set condVals = (Set) likeCondMap.get(orderCondValue.getConditionId());
				if (condVals == null)
				{
					condVals = new HashSet();
					likeCondMap.put(orderCondValue.getConditionId(), condVals);
				}
				// add conditionId in the collection
				condVals.add(orderCondValue.getVariableElementTypeId());
			}
		}
	}

	/**
	 * @param order
	 * @param likeConditions
	 * @param likeCondValueMap
	 * @return
	 */
	public static SupervisionOrderDetailResponseEvent getImpactedOrderResponseEvent(
		SupervisionOrder order,
		Collection likeConditions,
		Map likeCondValueMap)
	{
		SupervisionOrderDetailResponseEvent sodre = new SupervisionOrderDetailResponseEvent();
		sodre.setOrderId(order.getOID().toString());
		sodre.setCriminalCaseid(order.getCriminalCaseId());
		sodre.setOrderStatusId(order.getOrderStatusId());
		sodre.setOrderTitleId(order.getOrderTitleId());
		sodre.setActivationDate(order.getActivationDate());
		sodre.setOrderVersion(SupervisionOrderHelper.getOrderVersionLiteral(order));
		sodre.setVersionNum(order.getVersionNum());
		sodre.setOrderChainNum(order.getOrderChainNum());
		sodre.setVersionTypeId(order.getVersionTypeId());

		// conditions		
		Collection condRels = order.getSupervisionOrderConditionRels();
		if (condRels != null)
		{
			Iterator iter = condRels.iterator();
			SupervisionOrderConditionRel condRel = null;
			while (iter.hasNext())
			{
				condRel = (SupervisionOrderConditionRel) iter.next();
				SupOrderConditionResponseEvent socre = createLikeConditionResponseEvent(condRel, likeCondValueMap);
				if (likeConditions.contains(socre.getConditionId()))
				{
					socre.setLikeConditionInd(true);
					sodre.insertCondition(socre);
				}
				else
				{
					continue;
				}
			}
		}
		return sodre;
	}

	/**
	 * @param condRel
	 * @param likeCondValueMap
	 * @return
	 */
	private static SupOrderConditionResponseEvent createLikeConditionResponseEvent(
		SupervisionOrderConditionRel condRel,
		Map likeCondValueMap)
	{
		SupervisionOrderCondition soCondition = condRel.getSupervisionOrderCondition();

		SupOrderConditionResponseEvent reply = new SupOrderConditionResponseEvent();
		reply.setConditionId(soCondition.getConditionId());
		reply.setResolvedDescription(soCondition.getResolvedDescription());
		reply.setName(soCondition.getName());
		reply.setSequenceNum(condRel.getSequenceNum());

		// get a Set of all different values for this condition that are contributing to a impacted Order
		Set condvalues = (Set) likeCondValueMap.get(soCondition.getConditionId());
		Collection orderCondRelValues = condRel.getOrderConditionRelValues();
		if (orderCondRelValues != null)
		{
			for (Iterator i = orderCondRelValues.iterator(); i.hasNext();)
			{
				SupervisionOrderConditionRelValue orderCondRelValue = (SupervisionOrderConditionRelValue) i.next();
				// add values for this condition that are contributing to a impacted Order
				SupOrderConditionRelValueResponseEvent orderCondRelValEvent =
					new SupOrderConditionRelValueResponseEvent();
				SupervisionOrderHelper.populateSupOrderConditionRelValueResponse(
					orderCondRelValue,
					orderCondRelValEvent);
				if (condvalues != null && condvalues.contains(orderCondRelValue.getVariableElementTypeId()))
				{
					orderCondRelValEvent.setLikeConditionInd(true);
				}
				reply.addSupOrderConditionRelValue(orderCondRelValEvent);
			}
		}
		return reply;
	}

	/**
	 * @param orderToCondMap
	 * @param condToOrderValMap
	 * @param order
	 * @return
	 */
	public static boolean hasImpactedOrders(Map orderToCondMap, Map condToOrderValMap, Map orderToCondValueMap, SupervisionOrder order)
	{
//		Map orderValueMap = createOrderValueMap(order);
		// get a map of Condition's variable element and value to its value for the passed order
		Map orderValueMap = (Map)orderToCondValueMap.get(order.getOID().toString());
		
		boolean result = false;
		// get conditions for the current Order
		Collection conditions = (Collection) orderToCondMap.get(order.getOID().toString());
		// traverse through the conditions to see if values are different
		if (conditions != null)
		{
			for (Iterator condIter = conditions.iterator(); condIter.hasNext();)
			{
				String conditionId = (String) condIter.next();
				// get all the OrderConditionValues for this condition
				Collection orderConditionValues = (Collection) condToOrderValMap.get(conditionId);
				for (Iterator orderIter = orderConditionValues.iterator(); orderIter.hasNext();)
				{
					SupervisionOrderConditionValue orderCondValue = (SupervisionOrderConditionValue) orderIter.next();
					// select it only when:
					// 1. doesn't belong to the same case
					// 2. same court
					// 3. same VariableElementType
					// 4. different value 
					Object valObj =
						orderValueMap.get(
							orderCondValue.getConditionId() + "^" + orderCondValue.getVariableElementTypeId());
					if (!orderCondValue.getCriminalCaseId().equals(order.getCriminalCaseId())
						&& orderCondValue.getCourtId().equals(order.getOrderCourtId())
						&& (valObj != null && orderCondValue.getValue() != null)
						&& !orderCondValue.getValue().equals(valObj))
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	
	/**
	 * @param order
	 * @return
	 */
	public static Set getLikeConditions(SupervisionOrder order)
	{
		Map orderValueMap = createOrderValueMap(order);
		Set conditions = new HashSet();
		GetSupervisionOrderConditionValuesEvent orderCondValueEvent = populateConditionValueEvent(order);
		// only active orders can be impacted
		orderCondValueEvent.setOrderStatus(PDCodeTableConstants.STATUS_ACTIVE_ID);
		Iterator orderCondValues = SupervisionOrderConditionValue.findAll(orderCondValueEvent);
		while (orderCondValues.hasNext())
		{
			SupervisionOrderConditionValue orderCondValue = (SupervisionOrderConditionValue) orderCondValues.next();
			// select it only when
			// 1. same variable element type
			// 2. different value 
			// 3. different criminalcase
			Object valObj =
				orderValueMap.get(orderCondValue.getConditionId() + "^" + orderCondValue.getVariableElementTypeId());
			if (valObj != null
					&& PDCodeTableConstants.STATUS_ACTIVE_ID.equals(orderCondValue.getOrderStatus())
					&& (orderCondValue.getValue() != null && !orderCondValue.getValue().equals(valObj))
					&& !orderCondValue.getCriminalCaseId().equals(order.getCriminalCaseId()))
				{
					// add conditionId in the Set if its a like condition
					conditions.add(orderCondValue.getConditionId());
				}
		}
//		Map orderValueMap = null;
//		Set conditions = new HashSet();
//		GetSupervisionOrderConditionValuesEvent orderCondValueEvent = populateConditionValueEvent(order);
//		// only active orders can be impacted
//		//orderCondValueEvent.setOrderStatus(PDCodeTableConstants.STATUS_ACTIVE_ID);
//		Iterator orderCondValues = SupervisionOrderConditionValue.findAll(orderCondValueEvent);
//		// convert Iterator into collection to traverse it multiple times
//		Collection orderCondValuesColl = new ArrayList();
//		while(orderCondValues.hasNext()){
//			orderCondValuesColl.add(orderCondValues.next());
//		}
//		if(orderCondValuesColl.size() > 0){
//			orderValueMap = createOrderValueMap(order, orderCondValuesColl.iterator());
//		}
//		for(Iterator iter = orderCondValuesColl.iterator(); iter.hasNext(); ){
//			SupervisionOrderConditionValue orderCondValue = (SupervisionOrderConditionValue) iter.next();
//			// select it only when
//			// 1. same variable element type
//			// 2. different value 
//			// 3. different criminalcase
//			// 4. status active
//			Object valObj =
//				orderValueMap.get(orderCondValue.getConditionId() + "^" + orderCondValue.getVariableElementTypeId());
//			if (valObj != null
//				&& PDCodeTableConstants.STATUS_ACTIVE_ID.equals(orderCondValue.getOrderStatus())
//				&& (orderCondValue.getValue() != null && !orderCondValue.getValue().equals(valObj))
//				&& !orderCondValue.getCriminalCaseId().equals(order.getCriminalCaseId()))
//			{
//				// add conditionId in the Set if its a like condition
//				conditions.add(orderCondValue.getConditionId());
//			}
//		}
		return conditions;
	}

	/**
	 * @param order
	 * @return
	 */
	private static Map createOrderValueMap(SupervisionOrder order, Iterator orderCondValues)
	{
		Map orderValueMap = new HashMap();
		while (orderCondValues.hasNext())
		{
			SupervisionOrderConditionValue orderCondValue = (SupervisionOrderConditionValue) orderCondValues.next();
			if(orderCondValue.getOrderId().equals(order.getOID().toString())){
				orderValueMap.put(orderCondValue.getConditionId() + "^" + orderCondValue.getVariableElementTypeId(), orderCondValue.getValue());
			}
		}		
		return orderValueMap;
	}

	/**
	 * @param order
	 * @return
	 */
	private static Map createOrderValueMap(SupervisionOrder order)
	{
		Map orderValueMap = new HashMap();
		// get order values for the current Order
		Iterator conditionRels = order.getSupervisionOrderConditionRels().iterator();
		while (conditionRels.hasNext())
		{
			SupervisionOrderConditionRel condRel = (SupervisionOrderConditionRel) conditionRels.next();
			// get condition Id
			SupervisionOrderCondition orderCond = condRel.getSupervisionOrderCondition();
			if (orderCond != null){
				String conditionId = orderCond.getConditionId();
				Collection conditionRelVals = condRel.getOrderConditionRelValues();
				for (Iterator iter = conditionRelVals.iterator(); iter.hasNext();)
				{
					SupervisionOrderConditionRelValue conditionRelVal = (SupervisionOrderConditionRelValue) iter.next();
					orderValueMap.put(
							conditionId + "^" + conditionRelVal.getVariableElementTypeId(),
							conditionRelVal.getValue());
				}
			}
		}
		return orderValueMap;
	}

	
	/**
	 * @param order
	 * @return
	 */
	//	public static boolean hasImpactedOrder(String orderId){
	//		boolean result = false;
	//		// find all records from db view
	//		Iterator curOrderCondValues = SupervisionOrderConditionValue.findAll("orderId", orderId);
	//		while(curOrderCondValues.hasNext()){
	//			SupervisionOrderConditionValue orderCondValue = (SupervisionOrderConditionValue)curOrderCondValues.next();
	//			GetSupervisionOrderConditionValuesEvent orderCondValueEvent = populateConditionValueEvent(orderCondValue);
	//			//find all the rows with different values
	//			Iterator otherOrderCondValues = SupervisionOrderConditionValue.findAll(orderCondValueEvent);
	//			if(otherOrderCondValues.hasNext()){ // result is true if atleast one found
	//				result = true;
	//				break;
	//			}
	//		}
	//		return result;
	//	}

	//	public static boolean hasImpactedOrder(SupervisionOrderConditionValue orderCondValue){
	//		boolean result = false;
	//		GetSupervisionOrderConditionValuesEvent orderCondValueEvent = populateConditionValueEvent(orderCondValue);
	//		//find all the rows with different values
	//		Iterator otherOrderCondValues = SupervisionOrderConditionValue.findAll(orderCondValueEvent);
	//		if(otherOrderCondValues.hasNext()){ // result is true if atleast one found
	//			result = true;
	//		}
	//		return result;
	//	}

	private static GetSupervisionOrderConditionValuesEvent populateConditionValueEvent(SupervisionOrder order)
	{
		GetSupervisionOrderConditionValuesEvent orderCondValueEvent = new GetSupervisionOrderConditionValuesEvent();
		orderCondValueEvent.setAgencyId(order.getAgencyId());
//		orderCondValueEvent.setCriminalCaseId(order.getCriminalCaseId());
		orderCondValueEvent.setDefendantId(order.getDefendantId());
		orderCondValueEvent.setCourtId(order.getOrderCourtId());
		return orderCondValueEvent;
	}

	/**
	 * creates two maps for the view OrderConditionValues. First view maps order to a collection of Conditions 
	 * and the ssecond maps Condition to a collection of Orders.
	 * 
	 * @param orderCondValues
	 * @param orderToConditionMap
	 * @param conditionToOrderMap
	 */
	public static void createOrderConditionMaps(
		Iterator orderCondValues,
		Map orderToConditionMap,
		Map conditionToOrderValMap,
		Map orderCondValueMap)
	{
		while (orderCondValues.hasNext())
		{
			SupervisionOrderConditionValue orderCondValue = (SupervisionOrderConditionValue) orderCondValues.next();
			// order to conditions map
			Set conditions = (Set) orderToConditionMap.get(orderCondValue.getOrderId());
			if (conditions == null)
			{ // new order for map
				conditions = new HashSet();
				orderToConditionMap.put(orderCondValue.getOrderId(), conditions);
			}
			conditions.add(orderCondValue.getConditionId());

			// condition to orders map
			Set orderVals = (Set) conditionToOrderValMap.get(orderCondValue.getConditionId());
			if (orderVals == null)
			{ // new Condition for map
				orderVals = new HashSet();
				conditionToOrderValMap.put(orderCondValue.getConditionId(), orderVals);
			}
			orderVals.add(orderCondValue);

			// order mapped to another map that is for condition^VarElementType to its value 
			Map condValueMap = (Map)orderCondValueMap.get(orderCondValue.getOrderId());
			if (condValueMap == null)
			{ // new order for map
				//orderCondValue.getConditionId() + "^" + orderCondValue.getVariableElementTypeId()
				condValueMap = new HashMap();
				orderCondValueMap.put(orderCondValue.getOrderId(), condValueMap);
			}
			condValueMap.put(orderCondValue.getConditionId() + "^" + orderCondValue.getVariableElementTypeId(), orderCondValue.getValue());
		}
	}
}
