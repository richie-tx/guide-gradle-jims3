//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\suggestedorder\\transactions\\GetSuggestedOrderConditionsCommand.java

package pd.supervision.suggestedorder.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import pd.supervision.supervisionoptions.CommonSupervisionHelper;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionoptions.StringSet;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.SupervisionConstants;
import messaging.info.reply.CountInfoMessage;
import messaging.suggestedorder.GetConditionsForSuggestedOrderEvent;
import messaging.suggestedorder.reply.SuggestedOrderConditionResponseEvent;
import messaging.supervisionoptions.GetSupervisionConditionsEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;

/**
 * @author dgibler
 *  
 */
public class GetConditionsForSuggestedOrderCommand implements ICommand {

	/**
	 * @roseuid 433AF373021B
	 */
	public GetConditionsForSuggestedOrderCommand() {

	}

	/**
	 * @param event
	 * @roseuid 433AF05003C1
	 */
	public void execute(IEvent event) {
		GetConditionsForSuggestedOrderEvent getConditionsForSuggestedOrderEvent = (GetConditionsForSuggestedOrderEvent) event;

		GetSupervisionConditionsEvent getConditionsEvent = new GetSupervisionConditionsEvent();

		getConditionsEvent.setAgencyId(getConditionsForSuggestedOrderEvent
				.getAgencyId());
		getConditionsEvent.setName(getConditionsForSuggestedOrderEvent
				.getConditionName());
		getConditionsEvent.setGroup1(getConditionsForSuggestedOrderEvent
				.getConditionType());
		getConditionsEvent.setGroup2(getConditionsForSuggestedOrderEvent
				.getConditionSubType());
		getConditionsEvent.setGroup3(getConditionsForSuggestedOrderEvent
				.getConditionSubTypeDetail());
		getConditionsEvent.setCourts(getConditionsForSuggestedOrderEvent
				.getCourts());
		getConditionsEvent.setJurisdiction(getConditionsForSuggestedOrderEvent
				.getJurisdiction());
		getConditionsEvent.setStatus(PDCodeTableConstants.STATUS_ACTIVE);
		getConditionsEvent.setUnformattedDesc(getConditionsForSuggestedOrderEvent.getConditionLiteral());
		String standardIndId = getConditionsForSuggestedOrderEvent
				.getStandardInd();
		getConditionsEvent.setStandardSelected(false);
		getConditionsEvent.setLimitSearchResults(getConditionsForSuggestedOrderEvent.isLimitSearchResults());

		if (standardIndId != null) {
			if (standardIndId
					.equals(SupervisionConstants.STANDARD_ONLY_CONDITION)
					|| standardIndId
							.equals(SupervisionConstants.STANDARD_AND_NON_STANDARD_CONDITION)) {
				getConditionsEvent.setStandard(true);
				getConditionsEvent.setStandardSelected(true);
			} else if (standardIndId
					.equals(SupervisionConstants.NON_STANDARD_ONLY_CONDITION)) {
				getConditionsEvent.setStandard(false);
				getConditionsEvent.setStandardSelected(true);
			}
		}
		// find conditions
		Collection conditions = new ArrayList();
		Iterator condIter = Condition.findAll( getConditionsEvent );
		List condList = CollectionUtil.iteratorToList(condIter);
		
		if (getConditionsEvent.isLimitSearchResults() && condList.size() > PDConstants.SEARCH_LIMIT){
			CountInfoMessage infoEvent = new CountInfoMessage();
			infoEvent.setMessage("error.max.limit.exceeded");
			MessageUtil.postReply(infoEvent);
		} else {
			for (int i = 0; i < condList.size(); i++) {
				Condition cond = (Condition) condList.get(i);
				ConditionResponseEvent condRespEvent = CommonSupervisionHelper
					.getConditionResponseEvent(cond);
				conditions.add(condRespEvent);
			}

			if (conditions != null && conditions.size() > 0) {
				Collection filteredConditions = null;
				filteredConditions = conditions;
				filteredConditions = this.filterConditionsByCourts(
					filteredConditions, getConditionsForSuggestedOrderEvent
							.getCourts());
				this.postResponseEvents(filteredConditions);
			}
		}
	}

	/**
	 * @param filteredConditions
	 * @param courts
	 * @return
	 */
	private Collection filterConditionsByCourts(Collection conditions,
			Collection selectedCourts) {
		Collection filteredConditions = new ArrayList();

		ConditionResponseEvent conditionResponseEvent = null;
		Condition condition = null;
		boolean validConditionForCourt = true;
		String selectedCourt = null;
		Iterator iter = null;
		Iterator selectedCourtsIter = null;
		HashMap validCourtsForConditionMap = null;
		StringSet stringSet = null;
		String validCourtForCondition = null;

		if (selectedCourts != null) {
			Iterator condIter = conditions.iterator();
			while (condIter.hasNext()) {
				conditionResponseEvent = (ConditionResponseEvent) condIter
						.next();
				condition = Condition.find(conditionResponseEvent
						.getConditionId());
				validCourtsForConditionMap = new HashMap();
				stringSet = condition.getAllCourtIds();

				if (stringSet != null) {
					iter = stringSet.iterator();
					while (iter.hasNext()) {
						validCourtForCondition = (String) iter.next();
						validCourtsForConditionMap.put(validCourtForCondition,
								validCourtForCondition);
					}
				}
				selectedCourtsIter = selectedCourts.iterator();
				validConditionForCourt = true;

				while (selectedCourtsIter.hasNext()) {
					selectedCourt = (String) selectedCourtsIter.next();
					if (validCourtsForConditionMap.get(selectedCourt) == null) {
						validConditionForCourt = false;
						break;
					}
				}
				if (validConditionForCourt) {
					filteredConditions.add(conditionResponseEvent);
				}

			}
		}
		return filteredConditions;
	}

	/**
	 * @param conditions
	 */
	private void postResponseEvents(Collection conditions) {
		ConditionResponseEvent cre = null;
		SuggestedOrderConditionResponseEvent socre = null;
		Condition condition = null;
		List condList = CollectionUtil.iteratorToList(conditions.iterator());
		List respList = new ArrayList();
		for (int i = 0; i < condList.size(); i++) {
			cre = (ConditionResponseEvent) condList.get(i);
			socre = new SuggestedOrderConditionResponseEvent();
			socre.setConditionName(cre.getName());
			socre.setConditionType(cre.getGroup1Name());
			socre.setConditionSubType(cre.getGroup2Name());
			socre.setConditionSubTypeDetail(cre.getGroup3Name());
			condition = Condition.find(cre.getConditionId());
			socre.setConditionLiteral(condition.getDescription());

			if (cre.isStandard()) {
				socre.setStandardId(SupervisionConstants.STANDARD_ONLY_CONDITION);
			} else {
				socre.setStandardId(SupervisionConstants.NON_STANDARD_ONLY_CONDITION);
			}
			socre.setConditionId(cre.getConditionId());
			respList.add(socre);
		}
		MessageUtil.postReplies(respList);
	}

	

	/**
	 * @param event
	 * @roseuid 433AF05003C3
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 433AF05003C5
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param updateObject
	 * @roseuid 433AF373023A
	 */
	public void update(Object updateObject) {

	}
}
