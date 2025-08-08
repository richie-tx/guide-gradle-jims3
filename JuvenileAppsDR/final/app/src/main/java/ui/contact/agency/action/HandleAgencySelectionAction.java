//Source file: C:\\views\\archproduction\\app\\src\\ui\\contact\\agency\\action\\HandleAgencySelectionAction.java
package ui.contact.agency.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.ValidateAgencyDeleteRequirementsEvent;
import messaging.contact.agency.reply.AgencyInUseErrorResponseEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.PDContactConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.contact.agency.form.AgencyForm;

/**
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class HandleAgencySelectionAction extends Action
{

	/**
	 * @roseuid 4113F60D0332
	 */
	public HandleAgencySelectionAction()
	{
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{

		if (!ui.security.SecurityUIHelper.isUserMA())
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.MAOnly.login"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}

		ActionForward forward = new ActionForward();
		String action = aRequest.getParameter("action");
		String agencyId = aRequest.getParameter("agencyId");

		if (action == null || action.equals(""))
		{
			return aMapping.findForward(UIConstants.FAILURE);
		}

		AgencyForm agencyForm = (AgencyForm) aForm;
		agencyForm.setAction(action);
		agencyForm.setPageType("");
		agencyForm.setErrorMessage("");

		Iterator iter = agencyForm.getAgencies().iterator();
		while (iter.hasNext())
		{
			AgencyResponseEvent agency = (AgencyResponseEvent) iter.next();
			if (agency.getAgencyId().equals(agencyId))
			{
				agencyForm.setAgencyId(agency.getAgencyId());
				agencyForm.setAgencyName(agency.getAgencyName());
				agencyForm.setAgencyType(agency.getAgencyType());
				agencyForm.setAgencyTypeId(agency.getAgencyTypeId());
				agencyForm.setJmcRep(agency.getProjectAnalystInd());
				break;
			}
		}

		if (action.equals(UIConstants.DELETE))
		{
			forward = aMapping.findForward(validateDelete(aMapping, agencyForm, aRequest));
		}
		else
			if (action.equalsIgnoreCase(UIConstants.VIEW))
			{
				agencyForm.setOriginalAgencyName(agencyForm.getAgencyName());
				forward = aMapping.findForward(UIConstants.VIEW_SUCCESS);
			}
			else
				if (action.equalsIgnoreCase(UIConstants.UPDATE))
				{
					agencyForm.setOriginalAgencyName(agencyForm.getAgencyName());
					agencyForm.setAgencyNamePrompt(agencyForm.getAgencyName());
					forward = aMapping.findForward(UIConstants.UPDATE_SUCCESS);
				}
				else
				{
					forward = aMapping.findForward(UIConstants.FAILURE);
				}
		return forward;
	}

	/**
	 * @param aMapping
	 * @param agencyForm
	 * @param aRequest
	 * @return ActionForward
	 */
	private String validateDelete(ActionMapping aMapping, AgencyForm agencyForm, HttpServletRequest aRequest)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		ValidateAgencyDeleteRequirementsEvent requestEvent =
			(ValidateAgencyDeleteRequirementsEvent) EventFactory.getInstance(
				AgencyControllerServiceNames.VALIDATEAGENCYDELETEREQUIREMENTS);
		requestEvent.setAgencyId(agencyForm.getAgencyId());
		dispatch.postEvent(requestEvent);

		IEvent reply = dispatch.getReply();
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		Collection exceptions = (Collection) dataMap.get(PDContactConstants.ERROR_VALIDATE_AGENCY_DELETE_EVENT_TOPIC);
		if (exceptions != null)
		{
			Iterator i = exceptions.iterator();
			while (i.hasNext())
			{
				Object exceptionObj = i.next();

				AgencyInUseErrorResponseEvent event = (AgencyInUseErrorResponseEvent) exceptionObj;
				String errorType = event.getMessage();
				if (errorType.equals("Users"))
				{
					saveError(agencyForm, aRequest, "errors.delete.hasUsers");
					return UIConstants.DELETE_SUCCESS;
				}
				if (errorType.equals("UserGroups"))
				{
					saveError(agencyForm, aRequest, "errors.delete.hasUserGroups");
					return UIConstants.DELETE_SUCCESS;
				}
				if (errorType.equals("Roles"))
				{
					saveError(agencyForm, aRequest, "errors.delete.hasRoles");
					return UIConstants.DELETE_SUCCESS;
				}
			}
			}
			//		GetUsersEvent requestEvent = (GetUsersEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERS);
			//		requestEvent.setAgencyId(agencyForm.getAgencyId());
			//		dispatch.postEvent(requestEvent);
			//
			//		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			//		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			//		MessageUtil.processReturnException(dataMap);
			//
//			Collection users = (Collection) dataMap.get(PDContactConstants.USER_EVENT_TOPIC);
			//		if (users != null && users.size() > 0)
			//		{
			//			saveError(agencyForm, aRequest, "errors.delete.hasUsers");
			//			return UIConstants.DELETE_SUCCESS;
			//		}
			//
			//		IDispatch dispatchUserEvent = EventManager.getSharedInstance(EventManager.REQUEST);
			//		GetUserGroupsEvent userGroupsEvent =
			//			(GetUserGroupsEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.GETUSERGROUPS);
			//		userGroupsEvent.setAgencyId(agencyForm.getAgencyId());
			//		dispatchUserEvent.postEvent(userGroupsEvent);
			//
			//		CompositeResponse replies = (CompositeResponse) dispatch.getReply();
			//		Map map = MessageUtil.groupByTopic(replies);
			//		MessageUtil.processReturnException(map);
			//
			//		Collection userGroups = (Collection) map.get(PDSecurityConstants.USER_GROUP_EVENT_TOPIC);
			//		if (userGroups != null && userGroups.size() > 0)
			//		{
			//			saveError(agencyForm, aRequest, "errors.delete.hasUserGroups");
			//			return UIConstants.DELETE_SUCCESS;
			//		}
			//		IDispatch dispatchConstraintsEvent = EventManager.getSharedInstance(EventManager.REQUEST);
			//		Collection constraints = Constraint.findByConstrainerId(agencyForm.getAgencyId(), "AGENCY","ROLE");
			//		if (constraints != null && constraints.size() > 0)
			//		{
			//			saveError(agencyForm, aRequest, "errors.delete.hasRoles");
			//			return UIConstants.DELETE_SUCCESS;
			//		}
			return UIConstants.DELETE_SUCCESS;
		}

		/**
		 * @param agencyForm
		 * @param aRequest
		 * @param errorKey String
		 */
		private void saveError(AgencyForm agencyForm, HttpServletRequest aRequest, String errorKey)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey, agencyForm.getAgencyName()));
			saveErrors(aRequest, errors);
		}
	}
