//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionorder\\transactions\\GetSupervisionOrdersCommand.java

package pd.supervision.supervisionorder.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import naming.PDCodeTableConstants;
import naming.PDCommonSupervisionConstants;
import naming.PDConstants;
import pd.codetable.supervision.SupervisionCode;
import pd.criminalcase.CriminalCase;
import pd.supervision.supervisionorder.ImpactedOrderHelper;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderConditionValue;
import pd.supervision.supervisionorder.SupervisionOrderHelper;
import messaging.codetable.GetSupervisionCodesEvent;
import messaging.criminalcase.GetCaseEvent;
import messaging.criminalcase.GetSpnEvent;
import messaging.supervisionorder.GetMostRecentInactiveOrderForSpnEvent;
import messaging.supervisionorder.GetSupervisionOrderConditionValuesEvent;
import messaging.supervisionorder.GetSupervisionOrdersEvent;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;

/**
 * @author dgibler Retrieves supervision orders and criminal cases from the VSAM
 *         case master file that do not have supervision orders.
 */
public class GetSupervisionOrdersCommand implements ICommand {
	private static String BLANK = "";

	//private static String ORDER_IN_PROGRESS = "orderInProgress";

	private static String ZERO = "0";

	/**
	 * @roseuid 43B2E782006D
	 */
	public GetSupervisionOrdersCommand() {

	}

	/**
	 * @param event
	 * @roseuid 438F22CC0066
	 */
	public void execute(IEvent event) 
	{
		//long timeStart = System.currentTimeMillis();

		GetSupervisionOrdersEvent getSupervisionOrdersEvent = (GetSupervisionOrdersEvent) event;

		String spn = getSupervisionOrdersEvent.getSpn();
		
		// spn or cdi/case is mandatory for this command
		if ((spn == null || spn.equals(""))
			&& ((getSupervisionOrdersEvent.getCourtDivision() == null || getSupervisionOrdersEvent.getCourtDivision().equals(""))
			&& getSupervisionOrdersEvent.getCaseNum() == null || getSupervisionOrdersEvent.getCaseNum().equals(""))) 
		{
			ReturnException re = new ReturnException();
			IDispatch replyDispatch = EventManager.getSharedInstance(EventManager.REPLY);
			replyDispatch.postEvent(re);
			return;
		}
		if (spn != null && !spn.equals(BLANK) && spn.length() < 8) 
		{
			StringBuffer sb = new StringBuffer(spn);
			for (int i = 0; i < 8 - spn.length(); i++) 
			{
				sb.insert(0, ZERO);
			}
			spn = sb.toString();
		}
		getSupervisionOrdersEvent.setSpn(spn);

//		HashMap criminalCaseMap = this.retrieveCriminalCaseData(getSupervisionOrdersEvent);
		
		String agencyId = getSupervisionOrdersEvent.getAgencyId();
		// retrieve orders 
		// orderMap -> key: criminalCaseId, value: SupervisionOrder
		
		
		Iterator caseIter = retrieveCriminalCaseData(spn, getSupervisionOrdersEvent.getCourtDivision(), getSupervisionOrdersEvent.getCaseNum(), agencyId);
		
		if (caseIter != null) 
		{
			Map orderMap = null; 
			List orderCondValues = new ArrayList();
			List newCases = new ArrayList();
			Map courtDivMap = getAllCourtDivisions(agencyId);  
			while (caseIter.hasNext()) 
			{
				CriminalCase criminalCase = (CriminalCase) caseIter.next();
				
				if (criminalCase.getDefendantId() == null 
						|| criminalCase.getDefendantId().trim().equals(PDConstants.BLANK)){
					//CM-jj info was not found, i.e. cm-jj info is out-of-sync with pm-jz info for spn
					continue;
				}
				//JIMS200054393: don't display Out of County cases of type HCT(HARRIS COUNTY TREASURER OFFICE FUND 1000)
				if (!criminalCase.getCourtId().contains("HCT"))
				{
					spn=criminalCase.getDefendantId();
					// get all the OrderConditionValues for the current spn to set likeConditionInd
					if(orderCondValues.size() == 0)
					{
						GetSupervisionOrderConditionValuesEvent orderCondValueEvent = new GetSupervisionOrderConditionValuesEvent();
						orderCondValueEvent.setAgencyId(agencyId);
						orderCondValueEvent.setDefendantId(criminalCase.getDefendantId());
						
						// View retrieves conditions for orders that have a status other than W (withdrawn) and I (inactive).
						// fetch records from view
						Iterator iterator = SupervisionOrderConditionValue.findAll(orderCondValueEvent);
						
						// add this iterator to collection to be able to iterate it multiple times
						while(iterator.hasNext())
						{
							orderCondValues.add(iterator.next()); 
						}
					}
					
					String courtDiv = criminalCase.getCourtDivisionId();
					
					//Determine if valid court division for agency of requestor
					if(courtDivMap.containsKey(courtDiv))
					{ 
					    // valid case for this agency
						StringBuffer caseNum = new StringBuffer(courtDiv);
						caseNum.append(criminalCase.getCaseNum());
						if(orderMap==null){
							orderMap=retrieveSupervisionOrderData(spn,agencyId);
						}
						List orders = (List) orderMap.get(caseNum.toString());
						if(orders != null)
						{ 
						    // orders found
							postOrderResponseEvents(orders, criminalCase, orderCondValues.iterator());
						}
						else
						{
						    // no Order for criminalcase
							//ER JIMS200038691 Defect JIMS200040633 - return all cases regardless of case status
						    //if ((!criminalCase.getCaseStatusId().equals("C")) && (!criminalCase.getCaseStatusId().equals("D"))){
						    newCases.add(criminalCase);
						    //}
						}
					}				
				}
			}
			this.postCaseResponseEvents(newCases);
			//long timeEnd = System.currentTimeMillis();
			//System.out.println("Total PD Time(milli seconds) to search SupervisionOrders : " + (timeEnd - timeStart));
		}
	}

	/**
	 * @param status
	 * @param orderMap
	 * @return
	 */
	/* private boolean orderHasStatus(String status, HashMap orderMap) {
		boolean orderExistsForStatus = false;
		Object obj = orderMap.get(status);
		if (obj != null) {
			orderExistsForStatus = true;
		}

		return orderExistsForStatus;
	}*/

	/**
	 * @param criminalCaseMap
	 */
	private void postOrderResponseEvents(List orders, CriminalCase criminalCase, Iterator orderCondValues) {
		Map orderToCondMap = new HashMap(); // maps order to its conditions
		Map condToOrderMap = new HashMap(); // maps condition to all the Orders
		Map orderToCondValueMap = new HashMap(); // maps order to its condition and values		
		
		for(Iterator iter = orders.iterator(); iter.hasNext(); )
		{
			boolean hasMulti = false;
			if (orders.size() > 1) {
				hasMulti = true;
			}
			SupervisionOrder order = (SupervisionOrder)iter.next();
			CaseOrderResponseEvent responseEvent = SupervisionOrderHelper.getLightCaseOrderResponseEvent(order, criminalCase);
			if (responseEvent == null) 
			{
				continue;
			}

			ImpactedOrderHelper.createOrderConditionMaps(orderCondValues, orderToCondMap, condToOrderMap, orderToCondValueMap);

			// check to see if it is an impacted order. Only Active
			// orders can be impacted.
			if (PDCodeTableConstants.STATUS_ACTIVE_ID.equals(order.getOrderStatusId()) && 
					ImpactedOrderHelper.hasImpactedOrders(orderToCondMap, condToOrderMap, orderToCondValueMap, order)) 
			{
				responseEvent.setLikeConditionInd(true);
			}
			SupervisionOrderHelper.setCaseActivityRuleStatus(order, responseEvent, hasMulti);
			EventManager.getSharedInstance(EventManager.REPLY).postEvent(responseEvent);
		}
	}

	/**
	 * @param criminalCaseMap
	 */
	private void postCaseResponseEvents(Collection criminalCases) {
		for(Iterator iter = criminalCases.iterator(); iter.hasNext(); ) {
			CriminalCase crimCase = (CriminalCase)iter.next();
			
			if (crimCase == null || crimCase.getDefendantId().trim().equals(PDConstants.BLANK)){
				//CM-jj info was not found, i.e. cm-jj info is out-of-sync with pm-jz info for spn
				continue;
			}
			CaseOrderResponseEvent responseEvent = SupervisionOrderHelper.getCaseResponseEvent(crimCase);
			responseEvent.setCaseActivityRuleStatus(PDCommonSupervisionConstants.BTN_GRP_NAME_PASO_CASEFILE
							+ PDCommonSupervisionConstants.BTN_GRP_NAME_SEPARATOR
							+ PDCommonSupervisionConstants.BTN_GRP_STATUS_PASO_CASEFILE_NO_ORDERS);
			EventManager.getSharedInstance(EventManager.REPLY).postEvent(responseEvent);
		}
	}

	/**
	 * @param getSupervisionOrdersEvent
	 * @return
	 */
	private Iterator retrieveCriminalCaseData(String spn, String courtDivision, String caseNum, String agencyId) {
		Iterator caseIter = null;
		if ( (spn != null && !spn.equals(BLANK))
				&& (courtDivision != null && !courtDivision.equals(BLANK))
				&& (caseNum != null && !caseNum.equals(BLANK))) {
			Iterator cicsNewOrderIter = null;
			List<CriminalCase> validCases = new ArrayList<CriminalCase>();
			GetCaseEvent caseEvent = new GetCaseEvent();
			caseEvent.setCaseNum(caseNum);
			caseEvent.setCourtDivisionId(courtDivision);
			caseEvent.setAgencyId(agencyId);
			cicsNewOrderIter = CriminalCase.findAll(caseEvent);
			for(int i = 0; cicsNewOrderIter.hasNext(); i++){
				CriminalCase aCase = (CriminalCase) cicsNewOrderIter.next();
				if(aCase.getDefendantId().trim().equalsIgnoreCase(spn)){
					validCases.add(aCase);
				}
			}
			caseIter = validCases.iterator(); 
			
		} else if (spn != null && !spn.equals(BLANK)) {
			GetSpnEvent spnEvent = new GetSpnEvent();
			spnEvent.setSpn(spn);
			spnEvent.setAgencyId(agencyId);
			caseIter = CriminalCase.findAll(spnEvent);
		} 
		return caseIter;
	}

	/**
	 * @param agencyId
	 * @param courtDiv
	 * @return
	 */
	private Map getAllCourtDivisions(String agencyId) {
		Map map = new HashMap();
		GetSupervisionCodesEvent getCodesEvent = new GetSupervisionCodesEvent();
		getCodesEvent.setCodeTableName("AGENCY_COURT_DIVISION");
		getCodesEvent.setAgencyId(agencyId);
		Iterator iter = SupervisionCode.findAll(getCodesEvent);
		while(iter.hasNext()){
			SupervisionCode code = (SupervisionCode)iter.next();
			map.put(code.getCode(), code);
		}
		return map;
	}

	private HashMap retrieveSupervisionOrderData(String aSpn, String aAgency) {
		
		HashMap orderMap = new HashMap();
		//Retrieve order in progress
		GetSupervisionOrdersEvent getOrdersEvent=new GetSupervisionOrdersEvent();
		getOrdersEvent.setSpn(aSpn);
		getOrdersEvent.setAgencyId(aAgency);
		Iterator supOrders = SupervisionOrder.findAll(getOrdersEvent);
		boolean addToCaseOrders=true;
		String spn=getOrdersEvent.getSpn();
		while(supOrders.hasNext()){
			SupervisionOrder supOrder = (SupervisionOrder)supOrders.next();
			String caseNum = supOrder.getCriminalCaseId();
			addToCaseOrders=true;
			Collection caseOrders = (Collection)orderMap.get(caseNum);
			if(caseOrders == null){ // new caseNum 
				caseOrders = new ArrayList();
				orderMap.put(caseNum, caseOrders);
			}
			else{   // this means we already have a case num and order in there and we need to check to see if they are the same order chain num
				int currentOrderChainNum=supOrder.getOrderChainNum();
				int maximumOrderChainNum=0;

				for(int loopX=0; loopX<caseOrders.size(); loopX++){
					SupervisionOrder storedSupOrder = (SupervisionOrder)((List)caseOrders).get(loopX);
					
					int tempOrderChainNum=storedSupOrder.getOrderChainNum();
					if(tempOrderChainNum>=maximumOrderChainNum){
						maximumOrderChainNum=tempOrderChainNum;
					}
				}
				if(maximumOrderChainNum==currentOrderChainNum){
					// do nothing can add with no problems since order chain is the same
				}
				else if(maximumOrderChainNum<currentOrderChainNum){
					// delete all elements from the current caseOrders
					caseOrders=new ArrayList();
				}
				else if(maximumOrderChainNum>currentOrderChainNum){
					//don't add current supOrder
					addToCaseOrders=false;
				}
				if (!addToCaseOrders && !supOrder.getOrderStatusId().equals(PDCodeTableConstants.STATUS_ACTIVE)){
					//Add order in process to order list. Order chain will be set to 1 until order 
					//is activated.
					addToCaseOrders = true;
				}
			}
			if(addToCaseOrders)
				caseOrders.add(supOrder);
		}
		// get most recent inactive orders 
		GetMostRecentInactiveOrderForSpnEvent theEvent = new GetMostRecentInactiveOrderForSpnEvent();
		theEvent.setAgencyId(getOrdersEvent.getAgencyId());
		theEvent.setSpn(spn);
		theEvent.setOrderStatusId(PDCodeTableConstants.STATUS_INACTIVE_ID);
		Iterator inactiveOrders = SupervisionOrder.findAll(theEvent);
		List<SupervisionOrder> allInactiveCaseOrders = new ArrayList<SupervisionOrder>();
		while(inactiveOrders.hasNext()){
		SupervisionOrder inactiveOrder = (SupervisionOrder)inactiveOrders.next();
			allInactiveCaseOrders.add(inactiveOrder);
		}
		Collections.sort(allInactiveCaseOrders,SupervisionOrder.GetMostRecentInactiveOrdersForSpnByVersionTypeVersionNumberComparator);
		for(SupervisionOrder inactiveOrder: allInactiveCaseOrders){
			String caseNum = inactiveOrder.getCriminalCaseId();
			Collection caseOrders = (Collection)orderMap.get(caseNum);
			if(caseOrders == null){ // new caseNum 
				caseOrders = new ArrayList();
				orderMap.put(caseNum, caseOrders);
				SupervisionOrder inOrder = SupervisionOrder.find(inactiveOrder.getOID().toString());
				caseOrders.add(inOrder);
			} 
		}
		
		return orderMap;
	}

	/* private boolean hasActiveOrder(Collection supOrders){
		boolean result = false;
		for(Iterator iter = supOrders.iterator(); iter.hasNext(); ){
			SupervisionOrder order = (SupervisionOrder)iter.next();
			if(PDCodeTableConstants.STATUS_ACTIVE_ID.equals(order.getOrderStatusId())){
				result = true;
				break;
			}
		}
		return result;
	} */
	
	/**
	 * @param getOrdersEvent
	 * @return
	 */
	/* private SupervisionOrder getSupervisionOrder(IEvent getOrdersEvent) {
		Iterator iter = SupervisionOrder.findAll(getOrdersEvent);
		SupervisionOrder supervisionOrder = null;
		if (iter != null && iter.hasNext()) {
			supervisionOrder = (SupervisionOrder) iter.next();
		}
		return supervisionOrder;
	}*/

	/**
	 * @param event
	 * @roseuid 438F22CC0068
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 438F22CC006A
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param updateObject
	 * @roseuid 43B2E782007D
	 */
	public void update(Object updateObject) {

	}
}
