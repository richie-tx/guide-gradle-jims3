/*
 * Created on Feb 2, 2006
 *
  */
package ui.supervision.supervisionorder.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;

/**
 * @author dgibler
 *
  */
public class RemoveConditionsFromListAction 
{

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void removeConditions(SupervisionOrderForm sof)
	{
		Collection selectedConditions = sof.getConditionSelectedList();
		Map conditionsSelectedMap = this.buildConditionDetailMap(selectedConditions);
		Collection conditionResultList = sof.getConditionResultList();
		Map conditionsResultsMap = this.buildConditionMap(conditionResultList);
		if (selectedConditions == null){
			selectedConditions = new ArrayList();
		}
		if (conditionResultList == null){
			conditionResultList = new ArrayList();
		}
		String idToBeRemoved = null;
		for (int x=0; x < sof.getSelectedConditionIds().length; x++){
			idToBeRemoved = sof.getSelectedConditionIds()[x];
			ConditionDetailResponseEvent objectToBeRemoved =
				(ConditionDetailResponseEvent) conditionsSelectedMap.get(idToBeRemoved);

			if (conditionsResultsMap.get(idToBeRemoved) == null){
				ConditionResponseEvent cre = this.getConditionResponseEvent(objectToBeRemoved);
				if (cre != null){
					conditionResultList.add(cre);	
				}
			}
		
			conditionsSelectedMap.remove(idToBeRemoved);
		}
		
		Collection coll1 = conditionsSelectedMap.values();
		ArrayList coll2 = new ArrayList();
		if (coll1 != null && coll1.size() > 0){
			coll2.addAll(coll1);
			Collections.sort((List)coll2, ConditionDetailResponseEvent.SeqNumComparator);
		}
		
		this.setSequenceNumbers(coll2);
		sof.setConditionSelectedList(coll2);
		
		if (conditionResultList != null && conditionResultList.size() > 0){
			Collections.sort((List) conditionResultList);
			sof.setConditionResultList(conditionResultList);
		}
	}
	
	private void setSequenceNumbers(ArrayList condList){
		
		ConditionDetailResponseEvent cdre = null;
		int seqNum = 1;

		for (int i = 0; i < condList.size(); i++) {
			cdre = (ConditionDetailResponseEvent) condList.get(i);
			cdre.setSequenceNum(seqNum);
			seqNum++;
		}
	}
	
	/**
	 * @param aConditionDetailResponseEvent
	 */
	private ConditionResponseEvent getConditionResponseEvent(ConditionDetailResponseEvent aConditionDetailResponseEvent)
	{
		ConditionResponseEvent cre = null;
		
		if (aConditionDetailResponseEvent != null){
			cre = new ConditionResponseEvent();
			cre.setAgencyId(aConditionDetailResponseEvent.getAgencyId());
			cre.setConditionId(aConditionDetailResponseEvent.getConditionId());
			cre.setName(aConditionDetailResponseEvent.getName());
			cre.setGroup1Id(aConditionDetailResponseEvent.getGroup1Id());
			cre.setGroup1Name(aConditionDetailResponseEvent.getGroup1Name());
			cre.setGroup2Id(aConditionDetailResponseEvent.getGroup2Id());
			cre.setGroup2Name(aConditionDetailResponseEvent.getGroup2Name());
			cre.setGroup3Id(aConditionDetailResponseEvent.getGroup3Id());
			cre.setGroup3Name(aConditionDetailResponseEvent.getGroup3Name());
			if(aConditionDetailResponseEvent.getResolvedDescription()==null || aConditionDetailResponseEvent.getResolvedDescription().trim().equals("")){
				cre.setDescription(aConditionDetailResponseEvent.getDescription());
			} else {
				cre.setResolvedDescription(aConditionDetailResponseEvent.getResolvedDescription());
			}
			cre.setSpecialCondition(aConditionDetailResponseEvent.isSpecialCondition());
			cre.setTopic(aConditionDetailResponseEvent.getConditionId());
			cre.setConditionLiteralPreview(aConditionDetailResponseEvent.getConditionLiteralPreview());
		}
		return cre;

	}
	/**
	 * @param collection
	 * @return
	 */
	private Map buildConditionDetailMap(Collection collection)
	{
		HashMap map = new HashMap();
		if (collection != null && collection.size() > 0){
			Iterator iter = collection.iterator();
			ConditionDetailResponseEvent condition = null;
			while (iter.hasNext())
			{
				condition = (ConditionDetailResponseEvent) iter.next();
				map.put(condition.getConditionId(), condition);
			}
		}
		return map;
	}
	/**
	 * @param collection
	 * @return
	 */
	private Map buildConditionMap(Collection collection)
	{
		HashMap map = new HashMap();
		if (collection != null && collection.size() > 0){
			Iterator iter = collection.iterator();
			ConditionResponseEvent condition = null;
			while (iter.hasNext())
			{
				condition = (ConditionResponseEvent) iter.next();
				map.put(condition.getConditionId(), condition);
			}
		}
		return map;
	}

}
