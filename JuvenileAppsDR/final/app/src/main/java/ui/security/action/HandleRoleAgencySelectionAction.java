//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\HandleRoleAgencySelectionAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.security.ValidateRoleEvent;
import messaging.security.reply.DuplicateRoleErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.SecurityAdminControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.IShoppingCart;
import ui.common.ShoppingCartImpl;
import ui.security.form.RoleForm;

public class HandleRoleAgencySelectionAction extends LookupDispatchAction
{

	/**
	 * @roseuid 425AB74701C5
	 */
	public HandleRoleAgencySelectionAction()
	{

	}
	/**
		* @return buttonMap
		*/
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", "next");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.addSelected", "addSelected");
		return buttonMap;
	}
	
	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	* @roseuid 4256D6310196
	*/
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleForm roleForm = (RoleForm) aForm;
		String forward = UIConstants.CREATE_SUCCESS;
		ValidateRoleEvent validateEvent = (ValidateRoleEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.VALIDATEROLE);
		validateEvent.setRoleName(roleForm.getRoleName());
		validateEvent.setAgencyIdList(this.prepareAgencyIdList(roleForm.getCurrentAgencies()));

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(validateEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		DuplicateRoleErrorResponseEvent dupEvent = (DuplicateRoleErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, DuplicateRoleErrorResponseEvent.class);

		if(dupEvent != null){
			this.processDuplicateRecord(roleForm,aRequest,dupEvent);
			return aMapping.findForward(UIConstants.ADD_SUCCESS);
		}
//		roleForm.setAction(UIConstants.CREATE);
		return aMapping.findForward(forward);
	}

	/**
	 * @param roleForm
	 * @param aRequest
	 * @param dupEvent
	 */
	private void processDuplicateRecord(RoleForm roleForm, HttpServletRequest aRequest, DuplicateRoleErrorResponseEvent dupEvent)
	{
		AgencyResponseEvent response = null;
		Iterator iter = roleForm.getCurrentAgencies().iterator();
		while(iter.hasNext()){
			response = (AgencyResponseEvent) iter.next();
			if(response.getAgencyId().equalsIgnoreCase(dupEvent.getAgencyId())){
				break;
			}
		}
		roleForm.getCurrentAgencies().remove(response);
		sendErrorMessage(aRequest, "error.create.duplicate.roleName.found",dupEvent.getRoleName(),response.getAgencyName());
	}
	/**
	 * @param collection
	 * @return
	 */
	private Collection prepareAgencyIdList(Collection collection)
	{
		Iterator iter = collection.iterator();
		Collection agencyIdList = new ArrayList();
		while(iter.hasNext()){
			AgencyResponseEvent response = (AgencyResponseEvent) iter.next();
			agencyIdList.add(response.getAgencyId());
		}
		return agencyIdList;
	}

	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	* @roseuid 4256D6310196
	*/
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forward = UIConstants.FAILURE;
		RoleForm roleForm = (RoleForm) aForm;
		roleForm.setRoleName("");
		roleForm.setRoleDescription("");
		forward = UIConstants.CANCEL;
		return aMapping.findForward(forward);
	}

	/**
   * @param aMapping
   * @param aForm
   * @param aRequest
   * @param aResponse
   * @return ActionForward
   */
	public ActionForward addSelected(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleForm roleForm = (RoleForm) aForm;
		Collection availableAgencyList = roleForm.getAvailableAgencies();
		Collection currentList = roleForm.getCurrentAgencies();
		String[] selectedAgenciesId = roleForm.getSelectedAgencies();
		IShoppingCart sCart = new ShoppingCartImpl();
		String agencyEventName = UIConstants.AGENCY_RESPONSE_EVENT;
		String agencyEventIdName = UIConstants.AGENCY_RESPONSE_EVENT_ID;
		try
		{
			currentList =
				sCart.addToShoppingCart(
					agencyEventName,
					agencyEventIdName,
					selectedAgenciesId,
					currentList,
					availableAgencyList);
			roleForm.setCurrentAgencies(currentList);

			availableAgencyList =
				sCart.removeFromAvailableShoppingItemList(
					agencyEventName,
					agencyEventIdName,
					currentList,
					availableAgencyList);
			roleForm.setAvailableAgencies(availableAgencyList);
		}
		catch (Exception e){
		   e.printStackTrace();
		   ReturnException returnException = new ReturnException(e.getMessage());
		   throw returnException;
		}

		if (roleForm.getAction().equals(UIConstants.UPDATE))
		{
			return aMapping.findForward(UIConstants.UPDATE_AGENCY_SUCCESS);
		}
		return aMapping.findForward(UIConstants.ADDSELECTED_SUCCESS);
	}

	/**
	 * @param aRequest
	 * @param errorKey
	 * @param roleName
	 * @param agencyName
	 */
	private void sendErrorMessage(HttpServletRequest aRequest, String errorKey, String roleName, String agencyName)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey,roleName,agencyName));
		saveErrors(aRequest, errors);
	}
}