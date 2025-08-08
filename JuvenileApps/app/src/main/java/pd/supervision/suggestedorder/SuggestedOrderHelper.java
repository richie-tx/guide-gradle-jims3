/*
 * Created on Sep 29, 2005
 *
 */
package pd.supervision.suggestedorder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pd.codetable.criminal.OffenseCode;
import pd.supervision.Group;
import pd.supervision.supervisionoptions.CommonSupervisionHelper;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionoptions.ConditionCourtVariableElement;
import pd.supervision.supervisionoptions.ConditionSupervisionOption;
import pd.supervision.supervisionoptions.VariableElement;
import pd.supervision.supervisionoptions.VariableElementHelper;
import pd.supervision.supervisionorder.SupervisionOrder;
import naming.SupervisionConstants;
import naming.SupervisionOptionsConstants;
import messaging.codetable.GetOffenseCodeEvent;
import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import messaging.suggestedorder.reply.SuggestedOrderConditionResponseEvent;
import messaging.suggestedorder.reply.SuggestedOrderCourtResponseEvent;
import messaging.supervisionoptions.GetSupervisionConditionsEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.utilities.CollectionUtil;

/**
 * @author dgibler
 *
 */
public final class SuggestedOrderHelper
{

	/**
	 * 
	 */
	private SuggestedOrderHelper()
	{
		super();
	}

	/**
	 * Builds a map of ConditionResponseEvents for a collection of courts setting conditionId as the topic on each response.
	 * @param suggestedOrder
	 * @param soCourts
	 * @return
	 */
	private static Map buildCourtConditionsMap(SuggestedOrder suggestedOrder, Collection soCourts)
	{
		
		GetSupervisionConditionsEvent requestEvent = new GetSupervisionConditionsEvent();

		Collection courts = new ArrayList();

		SuggestedOrderCourt soCourt = null;
		Iterator iter = soCourts.iterator();
		while (iter.hasNext())
		{
			soCourt = (SuggestedOrderCourt) iter.next();
			courts.add(soCourt.getCourtId());
		}

		requestEvent.setCourts(courts);
		if (suggestedOrder.getIncludedConditionsId() != null
			&& suggestedOrder.getIncludedConditionsId().equals(SupervisionConstants.STANDARD_ONLY_CONDITION))
		{
			requestEvent.setStandardSelected(true);
			requestEvent.setStandard(true);
		}
		requestEvent.setName("");

		List responses = CommonSupervisionHelper.getSupervisionConditions(requestEvent);
		HashMap courtConditionsMap = new HashMap();
		ConditionResponseEvent cre = null;

		for (int i = 0; i < responses.size(); i++) {
			cre = (ConditionResponseEvent) responses.get(i);
			courtConditionsMap.put(cre.getConditionId(), cre);
		}
		
		return courtConditionsMap;
	}

	/**
	 * Creates and posts child object response events for a SuggestedOrder.
	 * @param suggestedOrder
	 */
	public static void postChildResponseEvents(IDispatch dispatch, SuggestedOrder suggestedOrder)
	{
		Collection soOffenses = suggestedOrder.getOffenses();
		postOffenseResponseEvents(dispatch, soOffenses);

		Collection soCourts = suggestedOrder.getCourts();
		postCourtResponseEvents(dispatch, soCourts);

		Collection soConditions = suggestedOrder.getConditions();

		Map courtConditionsMap = buildCourtConditionsMap(suggestedOrder, soCourts);
		postConditionResponseEvents(dispatch, soConditions, courtConditionsMap, suggestedOrder);

		soOffenses = null;
		soCourts = null;
		soConditions = null;
	}

	/**
	 * Creates and posts response events for conditions attached to a SuggestedOrder.
	 * @param dispatch
	 * @param soConditions
	 */
	public static void postConditionResponseEvents(IDispatch dispatch, Collection soConditions, Map courtConditionsMap, SuggestedOrder suggestedOrder)
	{
		if (soConditions != null)
		{
			SuggestedOrderCondition soCondition = null;
			SuggestedOrderConditionResponseEvent socre = null;
			Iterator iter = soConditions.iterator();
			ConditionResponseEvent aCondition = null;
			
				//allocate list for maintaining courts of suggested order
			List this_so_courts = new ArrayList();
			
			Collection<SuggestedOrderCourt> soCourts = 
				(Collection<SuggestedOrderCourt>) suggestedOrder.getCourts();
			if (soCourts != null)
			{
				List<SuggestedOrderCourt>  soCourtList = CollectionUtil.iteratorToList(soCourts.iterator());
				for (int i=0;i<soCourtList.size();i++)
				{
						//add suggested order court IDs to list
					SuggestedOrderCourt this_so_court = soCourtList.get(i);
					this_so_courts.add(this_so_court.getCourtId());
				}
			}
			
			while (iter.hasNext())
			{
				soCondition = (SuggestedOrderCondition) iter.next();
				socre = soCondition.getResponseEvent();

				if (socre != null)
				{
					aCondition = (ConditionResponseEvent) courtConditionsMap.get(soCondition.getConditionId());
					if (aCondition == null)
					{
						socre.setStatusId(SupervisionOptionsConstants.INVALID_COURT);
					}
					else
					{
							//allocate list to maintain courts of current condition
						List this_condition_courts = new ArrayList();
						
						//check courts of condition
						Condition 
							this_condition = 
								Condition.find(soCondition.getConditionId());
						if (this_condition != null)
						{
							Collection<ConditionSupervisionOption>
								cond_sup_options =
									this_condition.getConditionSupervisionOptions();
							if (cond_sup_options != null)
							{
								List<ConditionSupervisionOption> cond_sup_options_list =  
									CollectionUtil.iteratorToList(cond_sup_options.iterator());
								for (int i=0;i<cond_sup_options_list.size();i++)
								{
										//retrieve condition option and add court ID to list
									ConditionSupervisionOption 
										this_condition_option = cond_sup_options_list.get(i); 									
									this_condition_courts.add(this_condition_option.getCourtId());

								}								
							}
						}
							
							//verify whether courts of this condition contain all courts of suggested order
						if (!this_condition_courts.containsAll(this_so_courts))
						{
							socre.setStatusId(SupervisionOptionsConstants.INVALID_COURT);
						}
						else
						{
							socre.setStatusId(aCondition.getStatus());
						}

					}
					dispatch.postEvent(socre);
					socre = null;
				}
			}
		}
	}

	/**
	 * Creates and posts response events for courts attached to a SuggestedOrder.
	 * @param dispatch
	 * @param soCourts
	 */
	public static void postCourtResponseEvents(IDispatch dispatch, Collection soCourts)
	{
		if (soCourts != null)
		{
			SuggestedOrderCourt soCourt = null;
			SuggestedOrderCourtResponseEvent socre = null;
			Iterator iter = soCourts.iterator();
			while (iter.hasNext())
			{
				soCourt = (SuggestedOrderCourt) iter.next();
				socre = new SuggestedOrderCourtResponseEvent();
				socre.setCourtId(soCourt.getCourtId());
				socre.setTopic(SupervisionConstants.SUGGESTED_ORDER_COURT_EVENT_TOPIC);
				if (socre != null)
				{
					dispatch.postEvent(socre);
					socre = null;
				}
			}
		}

	}

	/**
	 * Creates and posts response events for offenses attached to a SuggestedOrder.
	 * @param dispatch
	 * @param soOffenses
	 */
	public static void postOffenseResponseEvents(IDispatch dispatch, Collection soOffenses)
	{
		if (soOffenses != null)
		{
			SuggestedOrderOffense soOffense = null;
			OffenseCode offenseCode = null;
			OffenseCodeResponseEvent ocre = null;
			Iterator iter = soOffenses.iterator();
			List aList = CollectionUtil.iteratorToList(iter);
			
			for (int i = 0; i < aList.size(); i++) {
				soOffense =  (SuggestedOrderOffense) aList.get(i);
				offenseCode = OffenseCode.find(soOffense.getOffenseId());
				
				if (offenseCode != null){
					//M204 program does LIKE on CODE and may not return the correct code.
					//For example when a query is done on code 99999, M204 R program returns 
					//data for 999999.  
					if (!offenseCode.getOffenseCodeId().equals(soOffense.getOffenseId())){
						//R2 Program returns exact match, but the codes aren't cached.
						offenseCode = retrieveOffenseViaEventQuery(soOffense.getOffenseId());
						if (offenseCode == null){
							continue;
						}
					}
					ocre = offenseCode.getResponseEvent();
					if (ocre != null)
					{
						ocre.setTopic(SupervisionConstants.SUGGESTED_ORDER_OFFENSE_EVENT_TOPIC);
						dispatch.postEvent(ocre);
						ocre = null;
					}
				} 
			}
		}

	}

	private static OffenseCode retrieveOffenseViaEventQuery(String offenseId) {
		GetOffenseCodeEvent anEvent = new GetOffenseCodeEvent();
		anEvent.setOffenseCode(offenseId);
		Iterator iter = OffenseCode.findAll(anEvent);
		List aList = CollectionUtil.iteratorToList(iter);
		OffenseCode aCode = null;
		
		for (int i = 0; i < aList.size(); i++) {
			aCode = (OffenseCode) aList.get(i);
			if (aCode.equals(offenseId)){
				break;
			}
		}
		return aCode;
	}

	/**
	 * @param suggOrderId
	 * @return
	 */

	public static void getSuggestedOrderConditions(String suggOrderId, Map conditionMap, Collection sugOrderConds)
	{
		Set conditionIds = new HashSet();
		Map sugOrderCondMap = new HashMap();
		Iterator conditionIter = SuggestedOrderCondition.findAll("suggestedOrderId", suggOrderId);
		while (conditionIter.hasNext())
		{
			SuggestedOrderCondition suggOrderCondition = (SuggestedOrderCondition) conditionIter.next();
			conditionIds.add(suggOrderCondition.getConditionId());
			sugOrderCondMap.put(suggOrderCondition.getConditionId(), suggOrderCondition);
//			sugOrderConds.add(suggOrderCondition);
		}
		// get Conditions
		Iterator supConditions = null;
		if(conditionIds.size() > 0){
			supConditions = Condition.findAllByIds(conditionIds, false);
			while(supConditions.hasNext()){
				Condition condition = (Condition)supConditions.next();
				// add it in the conditionMap
				conditionMap.put(condition.getOID().toString(), condition);
				// add it in the sugg order collection
				sugOrderConds.add(sugOrderCondMap.get(condition.getOID().toString()));
			}
		}
		
	}

	/**
	 * @param condition
	 * @param courtId
	 * @return
	 */
	public static ConditionDetailResponseEvent getConditionDetailResponseEvent(
		Condition condition,
		String courtId,
		Map variableElementRefererenceMap)
	{
		ConditionDetailResponseEvent reply = new ConditionDetailResponseEvent();
		reply.setConditionId(condition.getOID().toString());
		reply.setDescription(condition.getDescription());
		reply.setName(condition.getName());
		reply.setStandard(condition.getIsStandard());
		reply.setTopic(condition.getOID().toString());
		reply.setStatus(condition.getStatus());

		Group group = condition.getGroup();
		if(group != null){			
			Group[] groups = group.getGroupList();
			if (groups[0] != null)
			{
				reply.setGroup1Id(groups[0].getOID().toString());
				reply.setGroup1Name(groups[0].getGroupName());
			}
			if (groups[1] != null)
			{
				reply.setGroup2Id(groups[1].getOID().toString());
				reply.setGroup2Name(groups[1].getGroupName());
			}
			if (groups[2] != null)
			{
				reply.setGroup3Id(groups[2].getOID().toString());
				reply.setGroup3Name(groups[2].getGroupName());
			}
		}

		VariableElement varElem = null;
		VariableElementResponseEvent vere = null;
		Iterator variableElements =
			ConditionSupervisionOption.findVariableElements(condition.getOID().toString(), courtId);

		while (variableElements.hasNext())
		{
			varElem = (VariableElement) variableElements.next();
			// create VariableElementResponseEvent
			vere = new VariableElementResponseEvent();
			VariableElementHelper.populateVariableElemResponseEvent(varElem, vere, variableElementRefererenceMap);
			reply.addVariableElement(vere);
		}

		return reply;
	}
	/**
	 * @param condition
	 * @param courtId
	 * @return
	 */
	public static ConditionDetailResponseEvent getConditionDetailResponseEvent(
		SupervisionOrder order, 
		Condition condition,
		String courtId,
		Map variableElementRefererenceMap)
	{
		ConditionDetailResponseEvent reply = new ConditionDetailResponseEvent();
		reply.setConditionId(condition.getOID().toString());
		reply.setDescription(condition.getDescription());
		reply.setName(condition.getName());
		reply.setStandard(condition.getIsStandard());
		reply.setTopic(condition.getOID().toString());
		reply.setStatus(condition.getStatus());

		Group group = condition.getGroup();
		if(group != null){			
			Group[] groups = group.getGroupList();
			if (groups[0] != null)
			{
				reply.setGroup1Id(groups[0].getOID().toString());
				reply.setGroup1Name(groups[0].getGroupName());
			}
			if (groups[1] != null)
			{
				reply.setGroup2Id(groups[1].getOID().toString());
				reply.setGroup2Name(groups[1].getGroupName());
			}
			if (groups[2] != null)
			{
				reply.setGroup3Id(groups[2].getOID().toString());
				reply.setGroup3Name(groups[2].getGroupName());
			}
		}

		VariableElement varElem = null;
		VariableElementResponseEvent vere = null;
		Iterator variableElements =
			ConditionSupervisionOption.findVariableElements(condition.getOID().toString(), courtId);

		while (variableElements.hasNext())
		{
			varElem = (VariableElement) variableElements.next();
			// create VariableElementResponseEvent
			vere = new VariableElementResponseEvent();
			VariableElementHelper.populateVariableElemResponseEvent(order, varElem, vere, variableElementRefererenceMap);
			reply.addVariableElement(vere);
		}

		return reply;
	}	
	/**
	 * @param condition
	 * @param courtId
	 * @return
	 */
	public static ConditionDetailResponseEvent getConditionDetailResponseEvent(
		SupervisionOrder order, 
		Condition condition,
		String courtId,
		Map variableElementRefererenceMap,
		Map conditionVarElementMap)
	{
		ConditionDetailResponseEvent reply = new ConditionDetailResponseEvent();
		reply.setConditionId(condition.getOID().toString());
		reply.setDescription(condition.getDescription());
		reply.setName(condition.getName());
		reply.setStandard(condition.getIsStandard());
		reply.setTopic(condition.getOID().toString());

		Group group = condition.getGroup();
		if(group != null){			
			Group[] groups = group.getGroupList();
			if (groups[0] != null)
			{
				reply.setGroup1Id(groups[0].getOID().toString());
				reply.setGroup1Name(groups[0].getGroupName());
			}
			if (groups[1] != null)
			{
				reply.setGroup2Id(groups[1].getOID().toString());
				reply.setGroup2Name(groups[1].getGroupName());
			}
			if (groups[2] != null)
			{
				reply.setGroup3Id(groups[2].getOID().toString());
				reply.setGroup3Name(groups[2].getGroupName());
			}
		}

		Collection conditionCourtVariableElements = (Collection)conditionVarElementMap.get(reply.getConditionId());
		if(conditionCourtVariableElements != null){
			for (Iterator iter = conditionCourtVariableElements.iterator(); iter.hasNext(); ){
				ConditionCourtVariableElement condCrtVarElement = (ConditionCourtVariableElement)iter.next();
				//dont need to create a response event if variable element id is null
			    if(condCrtVarElement.getVariableElementTypeId() == null){
			        continue;
			    }
				// create VariableElementResponseEvent
				VariableElementResponseEvent vere = new VariableElementResponseEvent();
				VariableElementHelper.populateVariableElemResponseEvent(order, vere, condCrtVarElement, variableElementRefererenceMap);
				reply.addVariableElement(vere);
			}
		}
		
		return reply;
	}
	public static ConditionDetailResponseEvent getConditionDetailResponseEvent(
			Condition condition,
			String courtId,
			Map variableElementRefererenceMap,
			Map conditionVarElementMap)
		{
			ConditionDetailResponseEvent reply = new ConditionDetailResponseEvent();
			reply.setConditionId(condition.getOID().toString());
			reply.setDescription(condition.getDescription());
			reply.setName(condition.getName());
			reply.setStandard(condition.getIsStandard());
			reply.setTopic(condition.getOID().toString());

			Group group = condition.getGroup();
			if(group != null){			
				Group[] groups = group.getGroupList();
				if (groups[0] != null)
				{
					reply.setGroup1Id(groups[0].getOID().toString());
					reply.setGroup1Name(groups[0].getGroupName());
				}
				if (groups[1] != null)
				{
					reply.setGroup2Id(groups[1].getOID().toString());
					reply.setGroup2Name(groups[1].getGroupName());
				}
				if (groups[2] != null)
				{
					reply.setGroup3Id(groups[2].getOID().toString());
					reply.setGroup3Name(groups[2].getGroupName());
				}
			}

			Collection conditionCourtVariableElements = (Collection)conditionVarElementMap.get(reply.getConditionId());
			if(conditionCourtVariableElements != null){
				for (Iterator iter = conditionCourtVariableElements.iterator(); iter.hasNext(); ){
					ConditionCourtVariableElement condCrtVarElement = (ConditionCourtVariableElement)iter.next();
					//dont need to create a response event if variable element id is null
				    if(condCrtVarElement.getVariableElementTypeId() == null){
				        continue;
				    }
					// create VariableElementResponseEvent
					VariableElementResponseEvent vere = new VariableElementResponseEvent();
					VariableElementHelper.populateVariableElemResponseEvent(null, vere, condCrtVarElement, variableElementRefererenceMap);
					reply.addVariableElement(vere);
				}
			}
			
			return reply;
		}

	/**
	 * @param condition
	 * @param sequenceNum
	 * @param courtId
	 * @param variableElementReferenceMap
	 */
	public static void postConditionDetailRespEvent( 
		Condition condition,
		int sequenceNum,
		String courtId,
		Map variableElementReferenceMap,
		Map conditionVarElementMap)
	{
		ConditionDetailResponseEvent reply =
			SuggestedOrderHelper.getConditionDetailResponseEvent(condition, courtId, variableElementReferenceMap, conditionVarElementMap);
		reply.setSequenceNum(sequenceNum);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(reply);
	}

}
