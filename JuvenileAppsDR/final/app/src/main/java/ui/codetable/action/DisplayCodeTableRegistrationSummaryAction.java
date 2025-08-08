/*
 * Created on Nov 15, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.codetable.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetAgenciesEvent;
import messaging.codetable.ValidateContextKeyOrEntityNameEvent;
import messaging.codetable.reply.ContextKeyOrEntityNameValidationResponseEvent;
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
import ui.contact.UIContactHelper;

/**
 * @author Nagalla
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class DisplayCodeTableRegistrationSummaryAction extends JIMSBaseAction
{

	/**
	 * @roseuid
	 */
	public DisplayCodeTableRegistrationSummaryAction()
	{

	}

	/**
	 * @return Map
	 * @roseuid
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.submit", "success");
		keyMap.put("button.reset", "reset");
		keyMap.put("button.find", "findAgencies");
		keyMap.put("button.clear", "clearAgencies");
		keyMap.put("button.addSelected", "addSelected");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid
	 */
	public ActionForward success(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		ValidateContextKeyOrEntityNameEvent request = (ValidateContextKeyOrEntityNameEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.VALIDATECONTEXTKEYORENTITYNAME);

		String codetableType = cForm.getCodetableType();
		if (codetableType.equalsIgnoreCase("SL"))
		{
			request.setContextkey(cForm.getCodetableContextKey());
		}
		else
			request.setEntityName(cForm.getCodetableEntityName()); 
		if (cForm.getOpType().equalsIgnoreCase("create")) {
			request.setName(cForm.getCodetableName());			
		}
		dispatch.postEvent(request);

		// Getting PD Response Event
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(response);

		Map dataMap = MessageUtil.groupByTopic(response);

		ContextKeyOrEntityNameValidationResponseEvent resp = null;
		if (dataMap != null && response != null)
		{
			List refList = MessageUtil.compositeToList(response, ContextKeyOrEntityNameValidationResponseEvent.class);
			if (refList != null && !refList.isEmpty())
			{
				Iterator resIte = refList.iterator();
				resp = (ContextKeyOrEntityNameValidationResponseEvent) resIte.next();
				if (resp != null)
				{
					if (resp.getErrorMessage() != null)
					{
						ActionErrors errors = new ActionErrors();
						String errorAdd = null;
						if (resp.getErrorMessage().equalsIgnoreCase("Context_Key_Exists"))
						{
							errorAdd = "error.codetable.contextkey.exists";
							if (cForm.getOpType().equalsIgnoreCase("update")
									&& (cForm.getCodetableContextKey().equalsIgnoreCase(cForm
											.getCodetableContextKeyOrig())))
							{
								errorAdd = null;
							}
						}
						else if (resp.getErrorMessage().equalsIgnoreCase("Entity_Name_Exists"))
						{
							errorAdd = "error.codetable.entityname.exists";
							
							if (cForm.getOpType().equalsIgnoreCase("update")
									&& (cForm.getCodetableEntityName().equalsIgnoreCase(cForm
											.getCodetableEntityNameOrig())))
							{
								errorAdd = null;
							}
						}
						else if (resp.getErrorMessage().equalsIgnoreCase("No_Context_Key"))
							errorAdd = "error.codetable.contextkey.none";
						else if (resp.getErrorMessage().equalsIgnoreCase("No_Entity_Name"))
							errorAdd = "error.codetable.entityname.none";
						if (errorAdd != null)
						{
							errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorAdd));
						}
						if (resp.getNameError() != null && resp.getNameError().equalsIgnoreCase("Name_Exists"))
						{
							errorAdd = "error.name.exist";
							errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorAdd));
						}
						if (errorAdd != null)
						{
							saveErrors(aRequest, errors);
							return aMapping.findForward(UIConstants.FAILURE);
						}
					}
					else
					{
						List multiEntityOrContextList = resp.getContextKeysOrEntityNames();
						if (multiEntityOrContextList.size() > 1)
						{
							if (codetableType.equalsIgnoreCase("SL"))
							{
								cForm.setSearchType("context");
							}
							else
							{
								cForm.setSearchType("entity");
							}
							cForm.setSearchResultsCount(multiEntityOrContextList.size() + "");
							cForm.setCodetableContextOrEntityList(multiEntityOrContextList);
							return aMapping.findForward(UIConstants.UPDATE_CONITNUE);
						}
						else if (multiEntityOrContextList.size() == 1)
						{
							if (codetableType.equalsIgnoreCase("SL"))
							{
								cForm.setCodetableContextKey((String) multiEntityOrContextList.iterator().next());
								cForm.setCodetableEntityName("pd.codetable.Code");
							}
							else
							{
								cForm.setCodetableEntityName((String) multiEntityOrContextList.iterator().next());
							}
							cForm.setOpStatus("summary");
							cForm.setShowAttributes(false);
							cForm.setShowResequenceButton(false);
							return aMapping.findForward(UIConstants.SUCCESS);
						}
					}
				}
			}
		}
		cForm.setOpStatus("summary");
		cForm.setShowAttributes(false);
		cForm.setShowResequenceButton(false);
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid
	 */
	public ActionForward reset(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;
		cForm.resetForUpdate();
		return aMapping.findForward(UIConstants.RESET_SUCCESS);
	}

	public ActionForward findAgencies(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;
		String forward = UIConstants.RESET_SUCCESS;

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
			return aMapping.findForward(UIConstants.RESET_SUCCESS);
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
		forward = UIConstants.RESET_SUCCESS;
		return aMapping.findForward(forward);
	}

	public ActionForward addSelected(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;
		Collection availableAgencyList = cForm.getAvailableAgencies();
		Collection currentList = cForm.getCurrentAgencies();
		String[] selectedAgenciesId = cForm.getSelectedAgencies();
		IShoppingCart sCart = new ShoppingCartImpl();
		String agencyEventName = UIConstants.AGENCY_RESPONSE_EVENT;
		String agencyEventIdName = UIConstants.AGENCY_RESPONSE_EVENT_ID;
		try
		{
			currentList = sCart.addToShoppingCart(agencyEventName, agencyEventIdName, selectedAgenciesId, currentList,
					availableAgencyList);
			cForm.setCurrentAgencies(currentList);

			availableAgencyList = sCart.removeFromAvailableShoppingItemList(agencyEventName, agencyEventIdName,
					currentList, availableAgencyList);
			cForm.setAvailableAgencies(availableAgencyList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ReturnException returnException = new ReturnException(e.getMessage());
			throw returnException;
		}

		if (cForm.getAction().equals(UIConstants.UPDATE))
		{
			return aMapping.findForward(UIConstants.UPDATE_AGENCY_SUCCESS);
		}
		return aMapping.findForward(UIConstants.RESET_SUCCESS);
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
		return aMapping.findForward(UIConstants.RESET_SUCCESS);
	}

	private void sendErrorMessage(HttpServletRequest aRequest, String errorKey)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey));
		saveErrors(aRequest, errors);
	}

}
