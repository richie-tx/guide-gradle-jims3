//Source file: C:\\views\\CommonSupervision\\app\\src\\action\\DisplaySuggestedOrderSelectConditionAction.java
package ui.supervision.suggestedorder.action;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.suggestedorder.GetConditionsForSuggestedOrderEvent;
import messaging.suggestedorder.reply.SuggestedOrderConditionResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
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
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.suggestedorder.form.SuggestedOrderForm;
import ui.supervision.suggestedorder.helper.UISuggestedOrderHelper;
import ui.supervision.supervisionorder.UISupervisionOrderHelper;

/**
 * @author dgibler
 *
 */
public class DisplaySuggestedOrderSelectConditionAction extends LookupDispatchAction
{

	/**
	 * @roseuid 433AF4B0020B
	 */
	public DisplaySuggestedOrderSelectConditionAction()
	{

	}

	/**
		* @see LookupDispatchAction#getKeyMethodMap()
		* @return Map
		*/
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
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

		String standardId = sugOrderForm.getStandardId();
		Collection conditions = null;
		if(sugOrderForm.getAction().equals(UIConstants.CREATE)){
			sugOrderForm.clearConditionSelectedList();
		}
		// set default search condition field
		sugOrderForm.clearConditionResultList();
		sugOrderForm.clearConditionSearchElements();
		sugOrderForm.setJurisdictionId("HC");

		if (sugOrderForm.getGroups() == null || sugOrderForm.getGroups().size() == 0)
		{
			Collection groups = UISupervisionOptionHelper.fetchGroups(UIUtil.getCurrentUserAgencyID());
			sugOrderForm.setGroups(groups);
		}
		
		Collection selCourts = new ArrayList();
		
		if (!sugOrderForm.getAllCourtsSelected())
		{
			selCourts.addAll(UISuggestedOrderHelper.retrieveSelectedCourtsFromRequest(sugOrderForm, aRequest));
		}
		else
		{
			selCourts.addAll(sugOrderForm.getCourts());
		}
		
		if(!selCourts.isEmpty())
		{
			sugOrderForm.setSelectedCourts(selCourts);
		}

		// if standard condition option is selected, forward diretly to the condition list page and display
		// all standard conditions
		if(standardId.equals(SupervisionConstants.STANDARD_ONLY_CONDITION) ||
			standardId.equals(SupervisionConstants.STANDARD_AND_NON_STANDARD_CONDITION))
		{
			GetConditionsForSuggestedOrderEvent requestEvent =
				(GetConditionsForSuggestedOrderEvent) EventFactory.getInstance(
					SuggestedOrderControllerServiceNames.GETCONDITIONSFORSUGGESTEDORDER);

			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

			Collection selectedCourts = MessageUtil.processEmptyCollection(sugOrderForm.getSelectedCourts());
			requestEvent.setAgencyId(UIUtil.getCurrentUserAgencyID());
			requestEvent.setCourts(UISuggestedOrderHelper.buildCourtList(selectedCourts));
			requestEvent.setStandardInd(sugOrderForm.getStandardId());
			dispatch.postEvent(requestEvent);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
			conditions = MessageUtil.compositeToCollection(compositeResponse, SuggestedOrderConditionResponseEvent.class);
			conditions = MessageUtil.processEmptyCollection(conditions);
			addConditions(sugOrderForm, conditions);
		}

		// Condition search page is skipped if standard only option is selected
		if(standardId.equals(SupervisionConstants.STANDARD_ONLY_CONDITION)){
			// create sequence for the conditions
			if(sugOrderForm.getConditionSelectedList()==null || sugOrderForm.getConditionSelectedList().size()<1){
				sendToErrorPage(aRequest,"error.no.standard.conditions.exist");
				return aMapping.findForward(UIConstants.FAILURE);          
			}
			UISuggestedOrderHelper.createConditionSequence(conditions);
			sugOrderForm.setConditionSelectedList(conditions);
			sugOrderForm.setPreviousAction(standardId);
			return aMapping.findForward(UIConstants.SUCCESS);                
		}

		if (action.equals(UIConstants.CREATE) || action.equals(UIConstants.UPDATE) || action.equals(UIConstants.COPY))
		{
			sugOrderForm.setPreviousAction(standardId);
			forward = aMapping.findForward(UIConstants.SELECT_SUCCESS);
		}
		else
		{
			forward = aMapping.findForward(UIConstants.FAILURE);
		}
		return forward;
	}

	private void addConditions(SuggestedOrderForm sugOrderForm, Collection conditions){
		// get the existing collection from the form and remove standard conditions
		Collection curConditions = sugOrderForm.getConditionSelectedList();
		// sort them by sequence number
		Collections.sort((List)curConditions);
		Map map = new HashMap();
		for(Iterator iter = curConditions.iterator(); iter.hasNext(); ){
			SuggestedOrderConditionResponseEvent socre = (SuggestedOrderConditionResponseEvent)iter.next();
			map.put(socre.getConditionId(), socre);
		}
		
		// traverse through fetched conditions
		Map newCondMap = new HashMap();
		for(Iterator iter = conditions.iterator(); iter.hasNext(); ){
			SuggestedOrderConditionResponseEvent socre = (SuggestedOrderConditionResponseEvent)iter.next();
			newCondMap.put(socre.getConditionId(), socre);
			if(!map.containsKey(socre.getConditionId())){
				curConditions.add(socre);
			}
		}
		
		// conditions to be removed
		Collection removedConditions = new ArrayList();
		for(Iterator iter = curConditions.iterator(); iter.hasNext(); ){
			SuggestedOrderConditionResponseEvent socre = (SuggestedOrderConditionResponseEvent)iter.next();
			if(SupervisionConstants.STANDARD_ONLY_CONDITION.equals(socre.getStandardId()) && !newCondMap.containsKey(socre.getConditionId())){
				removedConditions.add(socre);
			}
		}
		//remove conditions
		for(Iterator iter = removedConditions.iterator(); iter.hasNext(); ){
			curConditions.remove(iter.next());
		}
		UISupervisionOrderHelper.setPreviewSample(sugOrderForm, curConditions);
	}
	
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;
		sugOrderForm.setSelectedCourts(sugOrderForm.getCopyOfSelectedCourts());
		ActionForward forward = new ActionForward();		
		if (sugOrderForm.isHasOffenses()){
			forward = aMapping.findForward("backToSelectOffense");			
		}else {
			forward = aMapping.findForward("backToCreateUpdate"); 			
		}			
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
}
