//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\suggestedorder\\transactions\\GetSuggestedOrdersCommand.java

package pd.supervision.suggestedorder.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import naming.SupervisionConstants;

import pd.codetable.Code;
import pd.codetable.criminal.OffenseCode;
import pd.security.PDSecurityHelper;
import pd.supervision.suggestedorder.SuggestedOrder;
import pd.supervision.suggestedorder.SuggestedOrderCondition;
import pd.supervision.suggestedorder.SuggestedOrderCourt;
import pd.supervision.suggestedorder.SuggestedOrderHelper;
import pd.supervision.suggestedorder.SuggestedOrderOffense;
import pd.supervision.supervisionoptions.CommonSupervisionHelper;
import pd.supervision.supervisionoptions.Condition;

import messaging.codetable.GetOffenseCodesEvent;
import messaging.suggestedorder.GetSuggestedOrdersByNameDescEvent;
import messaging.suggestedorder.GetSuggestedOrdersEvent;
import messaging.suggestedorder.GetSuggestedOrdersForCourtCategoryEvent;
import messaging.suggestedorder.reply.SuggestedOrderConditionListResponseEvent;
import messaging.suggestedorder.reply.SuggestedOrderResponseEvent;
import messaging.supervisionoptions.GetSupervisionConditionsEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;

import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *  
 */
public class GetSuggestedOrdersCommand implements ICommand {

	private static String FELONY_COURT_DIVISION = "3";

	/**
	 * @roseuid 433AF3B6023A
	 */
	public GetSuggestedOrdersCommand() {

	}

	/**
	 * @param suggestedOrders
	 * @return
	 */
	private Map buildSuggestedOrderMap(Collection suggestedOrders) {
		HashMap map = new HashMap();
		SuggestedOrder suggestedOrder = null;
		Iterator iter = suggestedOrders.iterator();

		while (iter.hasNext()) {
			suggestedOrder = (SuggestedOrder) iter.next();
			map.put(suggestedOrder.getSuggestedOrderId(), suggestedOrder);
		}
		return map;
	}

	/**
	 * @param courtDiv
	 * @return
	 */
	private boolean courtDivisionHandlesFelonyCases(String courtDiv) {
		boolean handlesFelonyCases = false;
		if (courtDiv != null && courtDiv.equals(FELONY_COURT_DIVISION)) {
			handlesFelonyCases = true;
		}
		return handlesFelonyCases;
	}

	/**
	 * @param courtDiv
	 * @return
	 */
	private boolean courtDivisionIsOutOfCounty(String courtDiv) {
		boolean isOutOfCounty = false;
		if (courtDiv != null && (courtDiv.equals("10") || courtDiv.equals("20"))) {
			isOutOfCounty = true;
		}
		return isOutOfCounty;
	}

	/**
	 * @param event
	 * @roseuid 433AF05102F7
	 */
	public void execute(IEvent event) {
		GetSuggestedOrdersEvent getSuggestedOrdersEvent = (GetSuggestedOrdersEvent) event;

		if (getSuggestedOrdersEvent.getAgencyId() == null || getSuggestedOrdersEvent.getAgencyId().equals("")) {
			getSuggestedOrdersEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
		}

		Collection suggestedOrders = this.retrieveSuggestedOrders(getSuggestedOrdersEvent);

		Map offenseCodesMap = this.retrieveOffenses(getSuggestedOrdersEvent);
		Map courtConditionsMap = this.retrieveConditions(getSuggestedOrdersEvent);
		Map courtsMap = this.retrieveCourts(getSuggestedOrdersEvent);

		Collection suggestedOrderResults = this.filterSuggestedOrders(getSuggestedOrdersEvent, suggestedOrders,
				offenseCodesMap, courtConditionsMap, courtsMap);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		SuggestedOrderConditionListResponseEvent responseEvent = null;
		Map map = null;
		SuggestedOrder suggestedOrder = null;
		SuggestedOrderResponseEvent sore = null;

		if (suggestedOrderResults != null) {
			Iterator iter = suggestedOrderResults.iterator();
			while (iter.hasNext()) {
				responseEvent = (SuggestedOrderConditionListResponseEvent) iter.next();

				if (suggestedOrderResults.size() == 1) {
					map = this.buildSuggestedOrderMap(suggestedOrders);
					suggestedOrder = (SuggestedOrder) map.get(responseEvent.getSuggestedOrderId());
					sore = suggestedOrder.getResponseEvent();
					dispatch.postEvent(sore);
					SuggestedOrderHelper.postChildResponseEvents(dispatch, suggestedOrder);
				} else {
					responseEvent.setTopic(SupervisionConstants.SUGGESTED_ORDER_CONDITION_EVENT_TOPIC);
					dispatch.postEvent(responseEvent);
				}
			}
		}
	}

	/**
	 * @param suggestedOrders
	 * @param offenseCodesMap
	 * @param courtConditionsMap
	 * @return collection of SuggestedOrderConditionListResponseEvents
	 */
	private Collection filterSuggestedOrders(GetSuggestedOrdersEvent getSugOrdersEvent, Collection suggestedOrders,
			Map offenseCodesMap, Map courtConditionsMap, Map courtsMap) {

		Collection filteredSuggestedOrderConditionResponseEvents = new ArrayList();
		Collection filteredSuggestedOrders = new ArrayList();

		if (isSearchByCourtDivision(getSugOrdersEvent)) {
			filteredSuggestedOrders = this.filterSuggestedOrdersByCourtDivision(suggestedOrders, getSugOrdersEvent);
		} else {
			filteredSuggestedOrders = suggestedOrders;
		}

		if (isSearchByCourts(getSugOrdersEvent)) {
			filteredSuggestedOrders = this.filterSuggestedOrdersByCourt(filteredSuggestedOrders, courtsMap);
		}

		boolean searchByOffense = isSearchByOffense(getSugOrdersEvent);
		boolean searchConditions = isSearchByConditions(getSugOrdersEvent);

		if (!searchConditions && !searchByOffense) {
			filteredSuggestedOrderConditionResponseEvents = this.filterSuggestedOrdersByConditions(
					filteredSuggestedOrders, null, false);
		} else {
			if (searchByOffense) {
				filteredSuggestedOrders = this.filterSuggestedOrdersByOffense(filteredSuggestedOrders, offenseCodesMap);
			}

			filteredSuggestedOrderConditionResponseEvents = this.filterSuggestedOrdersByConditions(
					filteredSuggestedOrders, courtConditionsMap, searchConditions);
		}

		filteredSuggestedOrderConditionResponseEvents = this
				.omitDuplicates(filteredSuggestedOrderConditionResponseEvents);
		return filteredSuggestedOrderConditionResponseEvents;
	}

	/**
	 * @param suggestedOrders
	 * @param courtConditionsMap
	 * @param filter
	 * @return Collection of SuggestedOrderConditionListResponseEvents
	 */
	private Collection filterSuggestedOrdersByConditions(Collection suggestedOrders, Map courtConditionsMap,
			boolean filter) {
		Collection suggestedOrderConditionResponseEvents = new ArrayList();
		Iterator iter = suggestedOrders.iterator();
		SuggestedOrder suggestedOrder = null;
		Collection suggestedOrderConditions = null;
		SuggestedOrderCondition suggestedOrderCondition = null;
		ConditionResponseEvent courtCondition = null;
		Condition condition = null;
		SuggestedOrderConditionListResponseEvent re = null;
		Iterator condIter = null;

		while (iter.hasNext()) {
			suggestedOrder = (SuggestedOrder) iter.next();
			suggestedOrderConditions = suggestedOrder.getConditions();
			condIter = suggestedOrderConditions.iterator();
			while (condIter.hasNext()) {

				suggestedOrderCondition = (SuggestedOrderCondition) condIter.next();
				courtCondition = null;

				if (filter) {
					courtCondition = (ConditionResponseEvent) courtConditionsMap.get(suggestedOrderCondition
							.getConditionId());
				} else {
					condition = suggestedOrderCondition.getCondition();
					courtCondition = new ConditionResponseEvent();
					courtCondition.setConditionId(condition.getOID().toString());
					courtCondition.setName(condition.getName());
				}
				if (courtCondition != null) {
					re = new SuggestedOrderConditionListResponseEvent();
					re.setSuggestedOrderId(suggestedOrderCondition.getSuggestedOrderId());
					re.setConditionId(suggestedOrderCondition.getConditionId());
					re.setSuggestedOrderConditionId(suggestedOrderCondition.getSuggestedOrderConditionId());
					re.setSuggestedOrderConditionName(courtCondition.getName());
					re.setSuggestedOrderName(suggestedOrder.getOrderName());
					re.setSuggestedOrderDescription(suggestedOrder.getOrderDescription());
					re.setTopic(SupervisionConstants.SUGGESTED_ORDER_CONDITION_EVENT_TOPIC);
					suggestedOrderConditionResponseEvents.add(re);
					if (!filter) {
						break;
					}
				}
			}
		}

		return suggestedOrderConditionResponseEvents;
	}

	/**
	 * @param suggestedOrders
	 * @param courtsMap
	 * @return
	 */
	private Collection filterSuggestedOrdersByCourt(Collection suggestedOrders, Map courtsMap) {
		Collection filteredSuggestedOrders = new ArrayList();
		Iterator iter = suggestedOrders.iterator();
		SuggestedOrder suggestedOrder = null;
		Collection courts = null;
		Iterator courtsIter = null;
		SuggestedOrderCourt soCourt = null;

		Object obj = null;

		while (iter.hasNext()) {
			suggestedOrder = (SuggestedOrder) iter.next();
			courts = suggestedOrder.getCourts();

			if (courts != null) {
				courtsIter = courts.iterator();
				while (courtsIter.hasNext()) {
					soCourt = (SuggestedOrderCourt) courtsIter.next();
					obj = courtsMap.get(soCourt.getCourtId());
					if (obj != null) {
						filteredSuggestedOrders.add(suggestedOrder);
						break;
					}
				}
			}
		}
		return filteredSuggestedOrders;
	}

	/**
	 * @param previouslyFilteredSos
	 *            filtered by agency & name
	 * @param theMainEvent
	 * @return
	 */
	private Collection filterSuggestedOrdersByCourtDivision(Collection previouslyFilteredSos,
			GetSuggestedOrdersEvent theMainEvent) {
		//Retrieve valid court categories for court division
		String courtDivision = theMainEvent.getCourtDivision();
		String ZERO = "0";
		if (courtDivision.length() < 3) {
			StringBuffer sb = new StringBuffer(courtDivision);
			while (sb.length() < 3) {
				sb.insert(0, ZERO);
			}
			courtDivision = sb.toString();
		}
		Code code = Code.find("CDI_COURTCTG", courtDivision);
		String courtCategory = "";
		Collection sosFilteredByCourtCtg = new ArrayList();
		if (code != null) {
			courtCategory = code.getDescription();
			GetSuggestedOrdersForCourtCategoryEvent getEvent = new GetSuggestedOrdersForCourtCategoryEvent();
			getEvent.setAgencyId(theMainEvent.getAgencyId());
			getEvent.setCourtCategory(courtCategory);
			Iterator iter = SuggestedOrder.findAll(getEvent);
			SuggestedOrder suggestedOrder = null;
			if (iter != null && iter.hasNext()) {
				while (iter.hasNext()) {
					suggestedOrder = (SuggestedOrder) iter.next();
					sosFilteredByCourtCtg.add(suggestedOrder);
				}
			}
		}
		Collection filteredSuggestedOrders = new ArrayList();
		if (previouslyFilteredSos != null && previouslyFilteredSos.size() > 0) {
			Map prevSosMap = this.buildSuggestedOrderMap(previouslyFilteredSos);
			Map ctgSosMap = this.buildSuggestedOrderMap(sosFilteredByCourtCtg);
			filteredSuggestedOrders = this.compareSuggestedOrderMaps(prevSosMap, ctgSosMap);
		} else {
			filteredSuggestedOrders = sosFilteredByCourtCtg;
		}
		return filteredSuggestedOrders;
	}

	/**
	 * @param map1
	 * @param map2
	 * @return
	 */
	private Collection compareSuggestedOrderMaps(Map map1, Map map2) {
		Collection filteredSuggestedOrders = new ArrayList();
		Set map1KeySet = map1.keySet();
		Iterator iter = map1KeySet.iterator();
		if (iter != null && iter.hasNext()){
			String map1Id = null;
			SuggestedOrder suggestedOrder = null;
			while (iter.hasNext()){
				map1Id = (String) iter.next();
				suggestedOrder = (SuggestedOrder) map2.get(map1Id);
				if (suggestedOrder != null){
					filteredSuggestedOrders.add(suggestedOrder);
				}
			}
		}
		
		return filteredSuggestedOrders;
	}

	/**
	 * @param suggestedOrders
	 * @param offenseCodesMap
	 * @return collection of SuggestedOrders
	 */
	private Collection filterSuggestedOrdersByOffense(Collection suggestedOrders, Map offenseCodesMap) {
		Collection filteredSuggestedOrders = new ArrayList();
		SuggestedOrder suggestedOrder = null;
		Collection suggestedOrderOffenses = null;
		Iterator iter = suggestedOrders.iterator();
		Iterator offenseIter = null;
		SuggestedOrderOffense suggestedOrderOffense = null;

		while (iter.hasNext()) {
			suggestedOrder = (SuggestedOrder) iter.next();
			suggestedOrderOffenses = suggestedOrder.getOffenses();
			offenseIter = suggestedOrderOffenses.iterator();
			while (offenseIter.hasNext()) {
				suggestedOrderOffense = (SuggestedOrderOffense) offenseIter.next();
				if (offenseCodesMap.get(suggestedOrderOffense.getOffenseId()) != null) {
					filteredSuggestedOrders.add(suggestedOrder);
				}
			}

		}
		return filteredSuggestedOrders;
	}

	private boolean isSearchByConditions(GetSuggestedOrdersEvent getSuggestedOrdersEvent) {
		boolean searchByConditions = false;

		if (getSuggestedOrdersEvent.getConditionName() != null
				&& !getSuggestedOrdersEvent.getConditionName().equals("")) {
			searchByConditions = true;
		}
		return searchByConditions;
	}

	/**
	 * @param getSugOrderEvent
	 * @return
	 */
	private boolean isSearchByCourtDivision(GetSuggestedOrdersEvent getSugOrdersEvent) {
		boolean courtDivSearch = false;
		if (getSugOrdersEvent.getCourtDivision() != null && !getSugOrdersEvent.getCourtDivision().equals("")) {
			courtDivSearch = true;
		}
		return courtDivSearch;
	}

	private boolean isSearchByCourts(GetSuggestedOrdersEvent getSuggestedOrdersEvent) {
		boolean searchByCourts = false;
		if (getSuggestedOrdersEvent.getCourts() != null && getSuggestedOrdersEvent.getCourts().size() > 0) {
			searchByCourts = true;
		}
		return searchByCourts;
	}

	private boolean isSearchByOffense(GetSuggestedOrdersEvent getSuggestedOrdersEvent) {
		boolean searchByOffense = false;
		if ((getSuggestedOrdersEvent.getOffenseCode() != null && !getSuggestedOrdersEvent.getOffenseCode().equals(""))
				|| (getSuggestedOrdersEvent.getOffenseLiteral() != null && !getSuggestedOrdersEvent.getOffenseLiteral()
						.equals(""))
				|| (getSuggestedOrdersEvent.getPenalCode() != null && !getSuggestedOrdersEvent.getPenalCode()
						.equals(""))) {
			searchByOffense = true;
		}
		return searchByOffense;
	}

	/**
	 * @param soclres
	 * @return
	 */
	private Collection omitDuplicates(Collection soclres) {
		Map aMap = new HashMap();
		SuggestedOrderConditionListResponseEvent soclre = null;
		if (soclres != null && soclres.size() > 0) {
			Iterator iter = soclres.iterator();
			if (iter != null && iter.hasNext()) {
				while (iter.hasNext()) {
					soclre = (SuggestedOrderConditionListResponseEvent) iter.next();
					if (aMap.get(soclre.getSuggestedOrderId()) == null) {
						aMap.put(soclre.getSuggestedOrderId(), soclre);
					}
				}
			}
		}

		return aMap.values();
	}

	/**
	 * @param event
	 * @roseuid 433AF05102F9
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 433AF05102FB
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param getSuggestedOrdersEvent
	 * @return
	 */
	private Map retrieveConditions(GetSuggestedOrdersEvent getSuggestedOrdersEvent) {
		GetSupervisionConditionsEvent getSupervisionConditionsEvent = new GetSupervisionConditionsEvent();
		
		String conditionName = getSuggestedOrdersEvent.getConditionName();
		Collection courts = getSuggestedOrdersEvent.getCourts();

		if (courts != null && courts.size() > 0) {
			getSupervisionConditionsEvent.setCourts(courts);
		}
		if (conditionName != null && !conditionName.equals("")) {
			getSupervisionConditionsEvent.setName(conditionName);
		}

		HashMap courtConditionsMap = new HashMap();

		if (isSearchByConditions(getSuggestedOrdersEvent)) {
			if (getSuggestedOrdersEvent.getConditionName() != null
					&& !getSuggestedOrdersEvent.getConditionName().equals("")) {
				getSupervisionConditionsEvent.setName(getSuggestedOrdersEvent.getConditionName());
			} else {
				getSupervisionConditionsEvent.setName("");
			}

			List courtConditions = CommonSupervisionHelper.getSupervisionConditions(getSupervisionConditionsEvent);
			ConditionResponseEvent cre = null;

			for (int i = 0; i < courtConditions.size(); i++) {
				cre = (ConditionResponseEvent) courtConditions.get(i);
				courtConditionsMap.put(cre.getConditionId(), cre);
			}
		}

		return courtConditionsMap;
	}

	/**
	 * @param getSuggestedOrdersEvent
	 * @return
	 */
	private Map retrieveCourts(GetSuggestedOrdersEvent getSuggestedOrdersEvent) {
		Collection courts = getSuggestedOrdersEvent.getCourts();

		HashMap hm = new HashMap();

		if (courts != null && courts.size() > 0) {
			Iterator iter = courts.iterator();
			String court = null;

			while (iter.hasNext()) {
				court = (String) iter.next();
				hm.put(court, court);
			}
		}
		return hm;
	}

	/**
	 * @param getSuggestedOrdersEvent
	 * @return
	 */
	private Map retrieveOffenses(GetSuggestedOrdersEvent getSuggestedOrdersEvent) {
		String offenseCodeId = getSuggestedOrdersEvent.getOffenseCode();
		String offenseLiteral = getSuggestedOrdersEvent.getOffenseLiteral();
		String penalCode = getSuggestedOrdersEvent.getPenalCode();
		String courtDivision = getSuggestedOrdersEvent.getCourtDivision();
		String offenseLevel = null;
		if (courtDivision != null && !courtDivision.equals("")) {
			if (courtDivisionIsOutOfCounty(courtDivision)) {
				offenseLevel = "";
			} else if (courtDivisionHandlesFelonyCases(courtDivision)) {
				offenseLevel = SupervisionConstants.FELONY;
			} else if (!courtDivisionHandlesFelonyCases(courtDivision)) {
				offenseLevel = SupervisionConstants.MISDEMEANOR;
			}
		}

		HashMap offenseCodesMap = new HashMap();

		if (isSearchByOffense(getSuggestedOrdersEvent)) {
			GetOffenseCodesEvent getOffensesEvent = new GetOffenseCodesEvent();
			getOffensesEvent.setOffenseCode(offenseCodeId);
			getOffensesEvent.setOffenseLiteral(offenseLiteral);
			getOffensesEvent.setPenalCode(penalCode);
			getOffensesEvent.setOffenseLevel(offenseLevel);

			Iterator iter = OffenseCode.findAll(getOffensesEvent);
			OffenseCode offenseCode = null;

			if (iter != null) {
				while (iter.hasNext()) {
					offenseCode = (OffenseCode) iter.next();
					offenseCodesMap.put(offenseCode.getOffenseCodeId(), offenseCode);
				}
			}
		}

		return offenseCodesMap;
	}

	/**
	 * @param getSuggestedOrdersEvent
	 * @return collection of SuggestedOrders
	 */
	private Collection retrieveSuggestedOrders(GetSuggestedOrdersEvent getSuggestedOrdersEvent) {
		GetSuggestedOrdersByNameDescEvent getByNameDesc = new GetSuggestedOrdersByNameDescEvent();
		Collection suggestedOrders = new ArrayList();
		String agencyId = getSuggestedOrdersEvent.getAgencyId();
		boolean limitSearch = false;

		if (getSuggestedOrdersEvent.getSuggestedOrderName() != null
				&& !getSuggestedOrdersEvent.getSuggestedOrderName().equals("")) {
			getByNameDesc.setSuggestedOrderName("*" + getSuggestedOrdersEvent.getSuggestedOrderName() + "*");
			limitSearch = true;
		}
		if (getSuggestedOrdersEvent.getSuggestedOrderDescription() != null
				&& !getSuggestedOrdersEvent.getSuggestedOrderDescription().equals("")) {
			getByNameDesc.setSuggestedOrderDescription("*" + getSuggestedOrdersEvent.getSuggestedOrderDescription()+ "*");
			limitSearch = true;
		}

		Iterator iter = null;
		if (!limitSearch) {
			iter = SuggestedOrder.findByAgency(agencyId);
		} else {
			getByNameDesc.setAgencyId(agencyId);
			iter = SuggestedOrder.findAll(getByNameDesc);
		}

		Object obj = null;

		if (iter != null) {
			while (iter.hasNext()) {
				obj = iter.next();
				suggestedOrders.add(obj);
			}
		}
		return suggestedOrders;
	}

	/**
	 * @param updateObject
	 * @roseuid 433AF3B60259
	 */
	public void update(Object updateObject) {

	}
}
