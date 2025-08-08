/*
 * Created on Sep 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.codetable.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetAgenciesEvent;
import messaging.codetable.SearchCodetableRecordsEvent;
import messaging.codetable.reply.CodetableRecordResponseEvent;
import messaging.info.reply.CountInfoMessage;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.CodeTableControllerServiceNames;
import naming.PDContactConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.codetable.form.CodetableRegistrationForm;
import ui.common.IShoppingCart;
import ui.common.ShoppingCartImpl;
import ui.common.UIUtil;
import ui.contact.UIContactHelper;

/**
 * @author cc_nnagaraju
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */

public class DisplayCodetableRegistrationSearchResultsAction extends JIMSBaseAction
{

	public DisplayCodetableRegistrationSearchResultsAction()
	{

	}

	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.submit", "find");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.find", "findAgencies");
		keyMap.put("button.clear", "clearAgencies");

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward find(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		SearchCodetableRecordsEvent request = (SearchCodetableRecordsEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.SEARCHCODETABLERECORDS);

		String promptCodetableRegisterName = cForm.getPromptCodetableRegisterName();
		String codetableDescription = cForm.getCodetableDescription();
		String codetableType = cForm.getCodetableType();
		String agencyId = cForm.getAgencyId();
		if (promptCodetableRegisterName != null)
		{
			promptCodetableRegisterName = promptCodetableRegisterName.trim().toUpperCase();
		}
		request.setCodeTableName(promptCodetableRegisterName);
		if (codetableDescription != null)
		{
			codetableDescription = codetableDescription.trim().toUpperCase();
		}
		request.setCodeTableDescription(codetableDescription);
		if (codetableType != null)
		{
			codetableType = codetableType.trim().toUpperCase();
		}
		request.setCodeTableType(codetableType);
		if (agencyId != null)
		{
			agencyId = agencyId.trim().toUpperCase();
		}
		request.setCodeTableAgencyId(agencyId);
		dispatch.postEvent(request);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		ActionForward forward;
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection codetables = MessageUtil
				.compositeToCollection(compositeResponse, CodetableRecordResponseEvent.class);
		if (codetables == null || codetables.isEmpty())
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.search.results.found"));
			saveErrors(aRequest, errors);
			cForm.setCodetables(new ArrayList());
			return aMapping.findForward(UIConstants.SEARCH_FAILURE);
		}

		if (codetables.size() == 1)
		{
			CodetableRecordResponseEvent result = (CodetableRecordResponseEvent) MessageUtil.filterComposite(
					compositeResponse, CodetableRecordResponseEvent.class);
			ActionForward oldForward = aMapping.findForward("search");
			forward = new ActionForward(oldForward.getPath() + "?codetableRegId=" + result.getCodetableRegId()
					+ "&submitAction=Link");
			// forward = new
			// ActionForward("handleCodetableRegistration.do?submitAction=Link&codetableRegId="+
			// result.getCodetableRegId()+"&action=default");
			Collection col = new ArrayList();
			col.add(result);
			cForm.setCodetables(col);
			forward.setRedirect(true);
			return forward;
		}

		Collections.sort((List) codetables);
		cForm.setCodetables(codetables);
		cForm.setSearchResultsCount("" + codetables.size());
		return aMapping.findForward(UIConstants.FIND_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;
		cForm.setPromptCodetableRegisterName("");
		cForm.setCodetableDescription("");
		cForm.setCodetableType("");
		cForm.setAgencyName("");
		cForm.setAgencyCode("");
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}

	public ActionForward findAgencies(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;
		String forward = UIConstants.FAILURE;

		/**
		 * use agnecy name, agency type, and/or jmc representative values to find agency information
		 * from form Collection roleNames for display
		 */
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetAgenciesEvent event = (GetAgenciesEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETAGENCIES);

		event.setAgencyName(cForm.getAgencyName());
		event.setAgencyId(cForm.getAgencyCode());
		dispatch.postEvent(event);
		cForm.setAgencyCode("");

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection agencies = null;
		if (dataMap.containsKey(PDContactConstants.AGENCY_EVENT_TOPIC))
		{
			agencies = (Collection) dataMap.get(PDContactConstants.AGENCY_EVENT_TOPIC);
		}
		int size = 0;
		if (agencies != null)
		{
			size = agencies.size();
			cForm.setAgencySearchResultSize(String.valueOf(size));
		}
		if (size == 0 || agencies == null)
		{
			cForm.setAvailableAgencies(new ArrayList());
			CountInfoMessage infoEvent = new CountInfoMessage();
			CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,
					CountInfoMessage.class);
			if (iMessage != null)
			{
				ActionErrors errors = new ActionErrors();
				ActionMessage error = new ActionMessage("error.max.limit.exceeded");
				errors.add(ActionErrors.GLOBAL_MESSAGE, error);
				saveErrors(aRequest, errors);
			}
			else
			{
				this.sendErrorMessage(aRequest, "error.no.agency.found");
			}
			return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
		}
		IShoppingCart sCart = new ShoppingCartImpl();
		String agencyEventName = UIConstants.AGENCY_RESPONSE_EVENT;
		String agencyEventIdName = UIConstants.AGENCY_RESPONSE_EVENT_ID;
		Collection availableAgencyList;
		try
		{
			availableAgencyList = sCart.removeFromAvailableShoppingItemList(agencyEventName, agencyEventIdName, cForm
					.getCurrentAgencies(), agencies);

			availableAgencyList = UIContactHelper.sortAgencyList(availableAgencyList);
			cForm.setAvailableAgencies(availableAgencyList);
			cForm.setAgencies(availableAgencyList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ReturnException returnException = new ReturnException(e.getMessage());
			throw returnException;
		}
		forward = UIConstants.REFRESH_SUCCESS;
		return aMapping.findForward(forward);
	}

	public ActionForward clearAgencies(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;
		// empty all collection so that the display page does not display it
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		cForm.setAvailableAgencies(emptyColl);
		cForm.setAgencyName("");
		cForm.setAgencyCode("");
		cForm.setAgencyId("");
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}

	private void sendErrorMessage(HttpServletRequest aRequest, String errorKey)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey));
		saveErrors(aRequest, errors);
	}

}