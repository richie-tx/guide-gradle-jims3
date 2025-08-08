//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\HandleRoleAgencySelectionAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetAgenciesEvent;
import messaging.info.reply.CountInfoMessage;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.PDContactConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.IShoppingCart;
import ui.common.ShoppingCartImpl;
import ui.contact.UIContactHelper;
import ui.security.form.RoleForm;

/*
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
 
public class HandleRoleAgencySearchAction extends LookupDispatchAction
{

	/**
	 * @roseuid 425AB74701C5
	 */
	public HandleRoleAgencySearchAction()
	{

	}

	/**
	 * @return Map
	 * @roseuid 4294862303BD
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.find", "find");
		buttonMap.put("button.refresh", "refresh");
		buttonMap.put("button.cancel", "cancel");		
		return buttonMap;
	}

	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BB030F
	 */
	public ActionForward find(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleForm roleForm = (RoleForm) aForm;
		String forward = UIConstants.FAILURE;

		/** use agnecy name, agency type, and/or jmc representative values to find agency information from form Collection roleNames for display */
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetAgenciesEvent event = (GetAgenciesEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETAGENCIES);
		event.setJmcRepId(roleForm.getJmcRepId());
		event.setAgencyTypeId(roleForm.getAgencyTypeId());
		event.setAgencyName(roleForm.getAgencyName());
		event.setAgencyId(roleForm.getAgencyCode());
		dispatch.postEvent(event);
		roleForm.setAgencyCode("");

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
			roleForm.setAgencySearchResultSize(String.valueOf(size));
		}
		if (size == 0 || agencies == null)
		{
			roleForm.setAvailableAgencies(new ArrayList());
			CountInfoMessage infoEvent = new CountInfoMessage();
	       	CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,CountInfoMessage.class);
	       	if (iMessage != null ){
	       		ActionErrors errors = new ActionErrors();
	       		ActionMessage error = new ActionMessage("error.max.limit.exceeded");
	       		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
	       		saveErrors(aRequest, errors);
	       	} 
	       	else
	       	{	
	       		this.sendErrorMessage(aRequest, "error.no.agency.found");
	       	}
       		return aMapping.findForward(UIConstants.FIND_SUCCESS);
		}
		IShoppingCart sCart = new ShoppingCartImpl();
		String agencyEventName = UIConstants.AGENCY_RESPONSE_EVENT;
		String agencyEventIdName = UIConstants.AGENCY_RESPONSE_EVENT_ID;
		Collection availableAgencyList;
		try
		{
			availableAgencyList =
				sCart.removeFromAvailableShoppingItemList(
					agencyEventName,
					agencyEventIdName,
					roleForm.getCurrentAgencies(),
					agencies);

			availableAgencyList = UIContactHelper.sortAgencyList(availableAgencyList);
			roleForm.setAvailableAgencies(availableAgencyList);
			roleForm.setAgencies(availableAgencyList);
		}
		catch (Exception e){
		   e.printStackTrace();
		   ReturnException returnException = new ReturnException(e.getMessage());
		   throw returnException;
		}
		forward = UIConstants.FIND_SUCCESS;
		return aMapping.findForward(forward);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward refresh(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleForm roleForm = (RoleForm) aForm;
		//empty all collection so that the display page does not display it
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		roleForm.setAvailableAgencies(emptyColl);
		roleForm.setAgencyName("");
		roleForm.setAgencyCode("");
		roleForm.setAgencyTypeId("");
		roleForm.setJmcRepId("");

		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleForm roleForm = (RoleForm) aForm;
		String masterAdmin = roleForm.getMasterAdmin();
		roleForm.clear();
		roleForm.setMasterAdmin(masterAdmin);
		return aMapping.findForward(UIConstants.CANCEL);	
	}	
	
	/**
	 * @param aRequest
	 * @param string
	 */
	private void sendErrorMessage(HttpServletRequest aRequest, String errorKey)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey));
		saveErrors(aRequest, errors);
	}
}