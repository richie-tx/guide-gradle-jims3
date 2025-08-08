//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\supervisionorder\\action\\DisplaySupervisionOrderAddRemoveConditionsAction.java

package ui.supervision.supervisionorder.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.info.reply.CountInfoMessage;
import messaging.supervisionoptions.GetSupervisionConditionsEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import messaging.supervisionorder.GetConditionDetailsForSupervisionOrderEvent;
import messaging.supervisionorder.UpdateSupervisionOrderEvent;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.ResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.SupervisionConstants;
import naming.SupervisionOptionsControllerServiceNames;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.UISupervisionOrderHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;

/**
 * @author dgibler
 *
 */
public class DisplaySupervisionOrderAddRemoveConditionsAction extends LookupDispatchAction
{
	private final static String COLLECTION_NAME = "conditionResultListIndex";
	private final static String KEY_FIELD_NAME = "conditionId";
	private final static String PERIOD = ".";
	/**
	 * @roseuid 438F23B6012F
	 */
	public DisplaySupervisionOrderAddRemoveConditionsAction()
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
		keyMap.put("button.saveCreateSpecialCondition", "createSpecialCondition");
		keyMap.put("button.viewSample", "viewSample");
		keyMap.put("button.hideSample", "hideSample");
		keyMap.put("button.saveContinue", "saveContinue");
		/**keyMap.put("button.previewOrder", "previewOrder"); */
		keyMap.put("button.resequenceConditions","resequenceConditions");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.removeSelected", "removeSelectedConditions");
		return keyMap;
	}


	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward addSelected(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		sof.clearValueMap();
		Collection selectedList = sof.getConditionSelectedList();
		Map selectedListMap = this.buildResponseEventMap(selectedList);

		Collection resultList = sof.getConditionResultList();
		Map resultListMap = this.buildResponseEventMap(resultList);

		ConditionResponseEvent cre = null;

		int counter = 0;
		String check = null;
		Iterator iter = resultList.iterator();
		String parameterName = null;
		Collection conditionsToBeDetailed = new ArrayList();
		Collection specialConds = new ArrayList();
		while (iter.hasNext())
		{
			cre = (ConditionResponseEvent) iter.next();
			parameterName = this.getParameterName(counter);

			check = aRequest.getParameter(parameterName);
			//Create collection of selected conditions
			//Remove selected condition from result list
			if (check != null)
			{
				//Determine if condition already exists in selected list
				if (selectedListMap.get(cre.getConditionId()) == null)
				{
					// dont add special condition
					if(cre.isSpecialCondition()){
						ConditionDetailResponseEvent spCdre = convertSpecialCondition(cre);
						specialConds.add(spCdre);
					}else{
						conditionsToBeDetailed.add(cre.getConditionId());
					}
				}
				resultListMap.remove(cre.getTopic());
			}
			counter++;
		}
		Collection detailedConditions =
			this.getDetailedConditions(sof.getOrderId(), sof.getCourtId(), conditionsToBeDetailed, sof.getReferenceVariableMap());

//		Collection detailedConditions =
//			this.getDetailedConditions(sof.getOrderId(), sof.getCurrentCourtId(), conditionsToBeDetailed, sof.getReferenceVariableMap());

		detailedConditions = MessageUtil.processEmptyCollection(detailedConditions);

		// add special conditions
		detailedConditions.addAll(specialConds);

		Collection coll1 = resultListMap.values();
		Collection coll2 = new ArrayList();
		coll2.addAll(coll1);
		sof.setConditionResultList(coll2);

		Collections.sort((List) selectedList, ConditionDetailResponseEvent.SeqNumComparator);
		sof.setConditionSelectedList(selectedList);

		// set sequence numbers
		setSequenceNumbers(selectedList, detailedConditions);
		UISupervisionOrderHelper.setPreviewSample(sof,detailedConditions,true);
		selectedList.addAll(detailedConditions);
		
		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.ADD_TO_LIST_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward removeSelectedConditions(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		RemoveConditionsFromListAction removeConditionAction = new RemoveConditionsFromListAction();
		
		if (sof.getSelectedConditionIds() != null && sof.getSelectedConditionIds().length > 0){
			removeConditionAction.removeConditions(sof);
		}
		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.REMOVE_FROM_LIST_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	private ConditionDetailResponseEvent convertSpecialCondition(ConditionResponseEvent cre){
		ConditionDetailResponseEvent cdre = new ConditionDetailResponseEvent();
		cdre.setSpecialCondition(cre.isSpecialCondition());
		cdre.setResolvedDescription(cre.getResolvedDescription());
		cdre.setConditionId(cre.getConditionId());
		cdre.setName(cre.getName());
		cdre.setGroup1Id(cre.getGroup1Id());
		cdre.setGroup1Name(cre.getGroup1Name());
		cdre.setAgencyId(cre.getAgencyId());
		cdre.setStatus(cre.getStatus());
		return cdre; 
	}
	
	private void setSequenceNumbers(Collection selectedList, Collection detailedConditions)
	{
		// get next available sequence number
		int size = selectedList.size();
		int lastIndex = 0;
		if(size > 0){
			// get the last object from the sorted list
			ConditionDetailResponseEvent lastObject = (ConditionDetailResponseEvent)((List)selectedList).get(size-1);
			lastIndex = lastObject.getSequenceNum();
		}
		for (Iterator condIter = detailedConditions.iterator(); condIter.hasNext();)
		{
			ConditionDetailResponseEvent conditionDetailResponseEvent = (ConditionDetailResponseEvent) condIter.next();
			conditionDetailResponseEvent.setSequenceNum(++lastIndex);
		}
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
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		sof.setStandardSearchCriteria("");  //false=Non-Standard  true=Standard ""=All
		sof.setGroup1Id(null);
		sof.setGroup2Id(null);
		sof.setGroup3Id(null);
		sof.setConditionName(null);
		sof.setConditionLiteral(null);
		sof.setSearchSuperCondPerformed(false);
		sof.setConditionResultList(null);
		
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("refreshSuccess", UIUtil.getCurrentUserAgencyID()));
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		sof.clearValueMap();
		
		String action = sof.getAction();
		ActionForward forward = new ActionForward();
		if (action.equals(UIConstants.CREATE))
		{
			forward=aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("backToOrderCreateSuccess", UIUtil.getCurrentUserAgencyID()));
		}else if (sof.getWorkflowFrom() != null){
			if (sof.getWorkflowFrom().equals(SupervisionConstants.PASO_WORKFLOWFROM)){				
				forward=aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("backToOrderPresentation", UIUtil.getCurrentUserAgencyID()));
			}
		}else if(action.equals(UIConstants.UPDATE))
		{
			forward=aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("backToOrderUpdateSuccess", UIUtil.getCurrentUserAgencyID()));
		}
		else{
			forward=aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK, UIUtil.getCurrentUserAgencyID()));
		}
		return forward;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward createSpecialCondition(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		processUpdateEvent(sof);
		sof.clearValueMap();
		// clear out group1Id
		sof.setGroup1Id(null);
		sof.setConditionId("");
		List tempList = new ArrayList();
		List tempList2 = new ArrayList();
		sof.setInputConditionList(tempList);
		UISupervisionOrderHelper.add10LightConditions(tempList2);
		sof.setDisplayConditionList(tempList2);
		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SPECIAL_CONDITION_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward hideSample(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;

		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.HIDE_SAMPLE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward resequenceConditions(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		sof.clearValueMap();
		processUpdateEvent(sof);
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.RESEQUENCE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward saveContinue(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		//long timeStart = System.currentTimeMillis();
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		processUpdateEvent(sof);
		//get conditions
		sof.setSecondaryAction(""); // used to control back button on preceding pages
		sof.clearValueMap();

		//long timeEnd = System.currentTimeMillis();
		//System.out.println("Total Time(milli seconds) for Supervision Order update from add/remove page: " + (timeEnd - timeStart));
		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SELECT_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	private void processUpdateEvent(SupervisionOrderForm sof){
		//UpdateSupervisionOrderEvent updateEvent = updateSupervisionOrder(sof);
		UpdateSupervisionOrderEvent updateEvent = UISupervisionOrderHelper.updateSupervisionOrder(sof);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		updateEvent.setAddRemoveCondition(true);
		dispatch.postEvent(updateEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(response);

		Collection searchResults =
			MessageUtil.compositeToCollection(response, CaseOrderResponseEvent.class);
		
		MessageUtil.processEmptyCollection(searchResults);

		Iterator iter = searchResults.iterator(); // only ome record
		CaseOrderResponseEvent orderRespEvent = null;
		if (iter.hasNext())
		{
			orderRespEvent = (CaseOrderResponseEvent) iter.next();
		}
		//set orderId in the form as we might have created a new version as a result of save/Continue
		sof.setOrderId(orderRespEvent.getOrderId());
		sof.setOrderStatusId(orderRespEvent.getOrderStatusId());
		sof.setStatusChangeDate(orderRespEvent.getStatusChangeDate()); 
		updateLikeConditions(sof, orderRespEvent);
	}
	
	private void updateLikeConditions(SupervisionOrderForm sof, CaseOrderResponseEvent orderRespEvent)
	{
		Collection likeCondIds = orderRespEvent.getLikeConditionIds();
		for (Iterator iter = sof.getConditionSelectedList().iterator(); iter.hasNext();)
		{
			ConditionDetailResponseEvent cdre = (ConditionDetailResponseEvent) iter.next();
			// only like condition ids are returned from pd
			if (likeCondIds.contains(cdre.getConditionId()))
			{
				cdre.setLikeConditionInd(true);
				// set indicator in the form to display "process Impacted Orders" button.
				sof.setLikeConditionInd(true);
			}
			else
			{
				cdre.setLikeConditionInd(false);
			}
		}
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward submit(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		sof.setConditionResultList(new ArrayList());

		GetSupervisionConditionsEvent getConditionsEvent =
			(GetSupervisionConditionsEvent) EventFactory.getInstance(
				SupervisionOptionsControllerServiceNames.GETSUPERVISIONCONDITIONS);

		Collection courts = new ArrayList();
		//courts.add(sof.getCourtId());
		courts.add(sof.getCurrentCourtId());
		// need only active conditions
		getConditionsEvent.setStatus(PDCodeTableConstants.STATUS_ACTIVE);
		getConditionsEvent.setCourts(courts);
		if(sof.getConditionLiteral()!=null && !sof.getConditionLiteral().equals("")){
			getConditionsEvent.setUnformattedDesc(sof.getConditionLiteral());
		}
		getConditionsEvent.setAgencyId(sof.getAgencyId());
		getConditionsEvent.setName(sof.getConditionName());
		getConditionsEvent.setGroup1(sof.getGroup1Id());
		// 06-30-09  group2Id and group3Id values are not updated to correct values in jsp due to drop down being disabled,
		// using group2 and group3 form values which contain correct group Id values set via js in jsp
		getConditionsEvent.setGroup2(sof.getGroup2());
		getConditionsEvent.setGroup3(sof.getGroup3());
		sof.setGroup2("");
		sof.setGroup3("");
		
		getConditionsEvent.setStandardSelected(true);
//		getConditionsEvent.setStandard(sof.isStandard());
		String standardSearchCriteria = sof.getStandardSearchCriteria();
		if (standardSearchCriteria != null && standardSearchCriteria.length() > 0)
		{
			getConditionsEvent.setStandardSelected(true);
			sof.setStandard(Boolean.valueOf(standardSearchCriteria).booleanValue());
			getConditionsEvent.setStandard(Boolean.valueOf(standardSearchCriteria).booleanValue());
		}
		else
		{
			getConditionsEvent.setStandardSelected(false);
		}

		getConditionsEvent.setLimitSearchResults(true);
		
		CompositeResponse compositeResponse = MessageUtil.postRequest(getConditionsEvent);


		CountInfoMessage infomsg = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,
				CountInfoMessage.class);

		if (infomsg != null)
		{
			this.sendToErrorPage(aRequest, "error.max.limit.exceeded");
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SEARCH_FAILURE, UIUtil.getCurrentUserAgencyID()));

		} else {

			Map responseMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(responseMap);

			Collection cres = MessageUtil.compositeToCollection(compositeResponse, ConditionResponseEvent.class);

			cres = MessageUtil.processEmptyCollection(cres);

			cres = this.setTopicOnConditionResponseEvents(cres);

			if (cres.size() == 0)
			{
				this.sendToErrorPage(aRequest, "error.noRecords");
				forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SEARCH_FAILURE, UIUtil.getCurrentUserAgencyID()));
			}
			else{
				sof.setConditionResultList(cres);
				UISupervisionOrderHelper.setPreviewSample(sof,cres,false);
			}
		
			sof.setSearchSuperCondPerformed(true);	
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SEARCH_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		}
		return forward;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward viewSample(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;

		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.VIEW_SAMPLE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}
	/**
	 * @param collection
	 * @return
	 */
	private Map buildResponseEventMap(Collection collection)
	{
		HashMap map = new HashMap();
		Iterator iter = collection.iterator();
		while (iter.hasNext())
		{
			ResponseEvent re = (ResponseEvent) iter.next();
			map.put(re.getTopic(), re);
		}
		return map;
	}

	/**
	 * @param courtId
	 * @param conditionsToBeConverted
	 * @return
	 */
	private Collection getDetailedConditions(String orderId, String courtId, Collection conditionList, Map referenceVariableMap)
	{
		Collection cdres = new ArrayList();
		if (courtId != null && !courtId.equals("") && conditionList != null && conditionList.size() > 0)
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			GetConditionDetailsForSupervisionOrderEvent requestEvent =
				(GetConditionDetailsForSupervisionOrderEvent) EventFactory.getInstance(
					SupervisionOrderControllerServiceNames.GETCONDITIONDETAILSFORSUPERVISIONORDER);

			requestEvent.setCourtId(courtId);
			requestEvent.setConditions(conditionList);
			requestEvent.setReferenceVariableMap(referenceVariableMap);
			requestEvent.setOrderId(orderId);

			dispatch.postEvent(requestEvent);
			CompositeResponse response = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(response);

			cdres = MessageUtil.compositeToCollection(response, ConditionDetailResponseEvent.class);
		}

		return cdres;
	}

	/**
	 * @param anInteger
	 * @return
	 */
	private String getParameterName(int counter)
	{
		StringBuffer sb = new StringBuffer(COLLECTION_NAME);
		sb.append(UIConstants.BRACKET_LEFT);
		Integer anInteger = new Integer(counter);
		sb.append(anInteger.toString());
		sb.append(UIConstants.BRACKET_RIGHT);
		sb.append(PERIOD);
		sb.append(KEY_FIELD_NAME);
		return sb.toString();
	}

	/**
	 * @param aRequest
	 * @param msg
	 */
	private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}

	/**
	 * @param cres
	 * @return
	 */
	private Collection setTopicOnConditionResponseEvents(Collection cres)
	{
		Iterator iter = cres.iterator();
		while (iter.hasNext())
		{
			ConditionResponseEvent cre = (ConditionResponseEvent) iter.next();
			cre.setTopic(cre.getConditionId());
		}
		return cres;
	}

}
