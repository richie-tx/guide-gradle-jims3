//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\suggestedorder\\action\\DisplayConditionsSequenceListAction.java

package ui.supervision.suggestedorder.action;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.info.reply.CountInfoMessage;
import messaging.suggestedorder.GetConditionsForSuggestedOrderEvent;
import messaging.suggestedorder.reply.SuggestedOrderConditionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.SuggestedOrderControllerServiceNames;
import naming.SupervisionConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.supervision.suggestedorder.helper.UISuggestedOrderHelper;
import ui.supervision.suggestedorder.form.SuggestedOrderForm;

/**
 * @author dgibler
 *
 */
public class DisplayConditionsSequenceListAction extends LookupDispatchAction
{
	private final static String COLLECTION_NAME = "conditionResultListIndex";
	private final static String PERIOD = ".";
	private final static String KEY_FIELD_NAME = "conditionId";

	/**
	 * @roseuid 433AF43C02C7
	 */
	public DisplayConditionsSequenceListAction()
	{

	}

	/**
		* @see LookupDispatchAction#getKeyMethodMap()
		* @return Map
		*/
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.submit", "submit");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.addSelected", "addSelected");
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;	
		sugOrderForm.clearConditionSearchElements();
		sugOrderForm.setConditionResultList(null);
		sugOrderForm.setJurisdictionId("HC");		
		
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}

	public ActionForward submit(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;

		GetConditionsForSuggestedOrderEvent requestEvent =
			(GetConditionsForSuggestedOrderEvent) EventFactory.getInstance(
				SuggestedOrderControllerServiceNames.GETCONDITIONSFORSUGGESTEDORDER);

		Collection selectedCourts = MessageUtil.processEmptyCollection(sugOrderForm.getSelectedCourts());

		requestEvent.setAgencyId(UIUtil.getCurrentUserAgencyID());
		requestEvent.setCourts(UISuggestedOrderHelper.buildCourtList(selectedCourts));
		requestEvent.setConditionName(sugOrderForm.getConditionName());
		requestEvent.setConditionLiteral(sugOrderForm.getConditionLiteral());
		requestEvent.setConditionType(sugOrderForm.getGroup1Id());
		requestEvent.setConditionSubType(sugOrderForm.getGroup2Id());
		requestEvent.setConditionSubTypeDetail(sugOrderForm.getGroup3Id());
		requestEvent.setJurisdiction(sugOrderForm.getJurisdictionId());
		requestEvent.setStandardInd(SupervisionConstants.NON_STANDARD_ONLY_CONDITION);
		requestEvent.setLimitSearchResults(true);

		CompositeResponse compositeResponse = MessageUtil.postRequest(requestEvent);
		CountInfoMessage infomsg = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,
				CountInfoMessage.class);

		if (infomsg != null)
		{
			this.sendToErrorPage(aRequest, "error.max.limit.exceeded");
		} else {
			Collection conditions =
				MessageUtil.compositeToCollection(compositeResponse, SuggestedOrderConditionResponseEvent.class);
			conditions = MessageUtil.processEmptyCollection(conditions);
			conditions = UISuggestedOrderHelper.addTopicsToConditions(conditions);

			Map selectedListMap = UISuggestedOrderHelper.buildResponseEventMap(sugOrderForm.getConditionSelectedList());
			Iterator iter = conditions.iterator();
			SuggestedOrderConditionResponseEvent condition = null;

			ArrayList filteredConditions = new ArrayList();
			while (iter.hasNext())
			{
				condition = (SuggestedOrderConditionResponseEvent) iter.next();
				if (selectedListMap.get(condition.getConditionId()) == null)
				{
					filteredConditions.add(condition);
				}
			}

			if (filteredConditions.size() == 0)
			{
				this.sendToErrorPage(aRequest, "error.noRecords");
			}
			sugOrderForm.setConditionResultList(filteredConditions);
		}
		forward = aMapping.findForward(UIConstants.SEARCH_SUCCESS);
		return forward;
	}

	public ActionForward addSelected(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;
		Collection resultList = MessageUtil.processEmptyCollection(sugOrderForm.getConditionResultList());
		Map resultListMap = UISuggestedOrderHelper.buildResponseEventMap(resultList);
		Collection selectedList = new ArrayList();
		SuggestedOrderConditionResponseEvent socre = null;
		StringBuffer sb = null;
		String check = null;
		int counter = 0;
		Iterator iter = resultList.iterator();

		while (iter.hasNext())
		{
			socre = (SuggestedOrderConditionResponseEvent) iter.next();
			sb = new StringBuffer(COLLECTION_NAME);
			sb.append(UIConstants.BRACKET_LEFT);
			sb.append(new Integer(counter).toString());
			sb.append(UIConstants.BRACKET_RIGHT);
			sb.append(PERIOD);
			sb.append(KEY_FIELD_NAME);

			check = aRequest.getParameter(sb.toString());
			if (check != null)
			{
				selectedList.add(socre);
				resultListMap.remove(socre.getTopic());
			}
			counter++;
		}
		sugOrderForm.setConditionResultList(UISuggestedOrderHelper.buildNewCollection(resultListMap.values()));

		Collection conditionsSelected = sugOrderForm.getConditionSelectedList();

		if (conditionsSelected != null)
		{
			filterOutDuplicates(conditionsSelected, selectedList);
			sugOrderForm.setConditionSelectedList(conditionsSelected);
		}
		else
		{
			sugOrderForm.setConditionSelectedList(selectedList);
		}
		forward = aMapping.findForward(UIConstants.ADD_TO_LIST_SUCCESS);
		return forward;
	}

	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;
		String action = sugOrderForm.getAction();
		
		if(action.equals(UIConstants.CREATE)){
			// sort this collection based on its type (standard/Non standard)
			Collections.sort((java.util.List)sugOrderForm.getConditionSelectedList(), SuggestedOrderConditionResponseEvent.StandardComparator);
			UISuggestedOrderHelper.createConditionSequence(sugOrderForm.getConditionSelectedList());
		}else{
			// sort this collection based on its seq num
			Collections.sort((java.util.List)sugOrderForm.getConditionSelectedList());
			UISuggestedOrderHelper.createConditionSequence(sugOrderForm.getConditionSelectedList());
			// sort this collection again
			Collections.sort((java.util.List)sugOrderForm.getConditionSelectedList());
		}


		if (action.equals(UIConstants.CREATE) || action.equals(UIConstants.UPDATE) || action.equals(UIConstants.COPY))
		{
			forward = aMapping.findForward(UIConstants.SELECT_SUCCESS);
		}
		else
		{
			forward = aMapping.findForward(UIConstants.FAILURE);
		}
		return forward;
	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;

		// get conditions again if "back" button is pressed
		CompositeResponse compositeResponse = UISuggestedOrderHelper.fetchSuggestedOrder(sugOrderForm.getOrderId());
		Collection soConditions = MessageUtil.compositeToCollection(compositeResponse, SuggestedOrderConditionResponseEvent.class); 
		soConditions = MessageUtil.processEmptyCollection(soConditions);
		sugOrderForm.setConditionSelectedList(soConditions);

		return forward;
	}

	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;
		String action = sugOrderForm.getAction();

		if (action.equals(UIConstants.CREATE))
		{
			forward = aMapping.findForward(UIConstants.CANCEL_CREATE);
		}
		else
		{
			forward = aMapping.findForward(UIConstants.CANCEL);
		}
		return forward;
	}

	private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}

	/**
	 * @param map
	 * @param responseEvents
	 * @return
	 */
	private void filterOutDuplicates(Collection currSelected, Collection responseEvents)
	{
		Map map = createConditionMap(currSelected);
		Iterator iter = responseEvents.iterator();
		while (iter.hasNext())
		{
			SuggestedOrderConditionResponseEvent newSOCRE = (SuggestedOrderConditionResponseEvent)iter.next();
			if(!map.containsKey(newSOCRE.getConditionId())){
				currSelected.add(newSOCRE);			
			}
		}
	}

	private Map createConditionMap(Collection conditions){
		Map map = new HashMap();
		for(Iterator iter = conditions.iterator(); iter.hasNext(); ){
			SuggestedOrderConditionResponseEvent suggestedOrderConditionResponseEvent = (SuggestedOrderConditionResponseEvent)iter.next();
			map.put(suggestedOrderConditionResponseEvent.getConditionId(), suggestedOrderConditionResponseEvent);
		}
		
		return map;
	}
}
