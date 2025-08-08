//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\SubmitRoleCreateAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.security.CreateRoleEvent;
import messaging.security.DeleteRoleEvent;
import messaging.security.UpdateRoleEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import messaging.security.reply.FeaturesResponseEvent;
import messaging.user.GetUsersEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDContactConstants;
import naming.UIConstants;
import naming.UserControllerServiceNames;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.security.SecurityUIHelper;
import ui.security.form.RoleSAForm;

public class SubmitSARoleAction extends LookupDispatchAction
{

	/**
	 * @roseuid 425AB72B0213
	 */
	public SubmitSARoleAction()
	{

	}
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.mainPage", "mainpage");
		buttonMap.put("button.delete", "delete");
		buttonMap.put("button.cancel", "cancel");
		return buttonMap;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 425551F90223
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleSAForm saRoleForm = (RoleSAForm) aForm;
		String forward = UIConstants.FAILURE;
		/** forward values to be based of value of action */
		//		saRoleForm.setAction(UIConstants.CREATE);
		String action = saRoleForm.getAction();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		IEvent iEvent = null;
		if (action != null)
		{
			if (action.equalsIgnoreCase(UIConstants.CREATE))
			{
				CreateRoleEvent createEvent = new CreateRoleEvent();
				createEvent.setRoleName(saRoleForm.getRoleName());
				createEvent.setRoleDescription(saRoleForm.getRoleDescription());
				createEvent.setRoleType(UIConstants.SA_ROLETYPE);
				createEvent.setRoleCreatorId(SecurityUIHelper.getJIMSLogonId());

				String agencyId = saRoleForm.getAgencyId();
				if (agencyId == null || agencyId.equals(""))
				{
					return aMapping.findForward(UIConstants.FAILURE);
				}
				Collection agencyIds = new ArrayList();
				agencyIds.add(agencyId);
				createEvent.setAgenciesList(agencyIds);

				Collection featureList = saRoleForm.getCurrentFeatures();
				if (featureList == null || featureList.isEmpty())
				{
					return aMapping.findForward(UIConstants.FAILURE);
				}
				Iterator featuresIter = featureList.iterator();
				Collection featureIdList = new ArrayList();
				while (featuresIter.hasNext())
				{
					FeaturesResponseEvent fEvent = (FeaturesResponseEvent) featuresIter.next();
					featureIdList.add(fEvent.getFeatureId());
				}
				createEvent.setFeaturesList(featureIdList);
				iEvent = createEvent;
				forward = UIConstants.CREATE_SUCCESS;
			}
			else
				if (action.equalsIgnoreCase(UIConstants.DELETE))
				{
					DeleteRoleEvent deleteEvent = new DeleteRoleEvent();
					deleteEvent.setRoleId(saRoleForm.getRoleId());
					iEvent = deleteEvent;
					forward = UIConstants.DELETE_SUCCESS;
				}
				else
					if (action.equalsIgnoreCase(UIConstants.UPDATE))
					{
						UpdateRoleEvent updateEvent = new UpdateRoleEvent();
						updateEvent.setRoleName(saRoleForm.getRoleName());
						updateEvent.setRoleDescription(saRoleForm.getRoleDescription());
						updateEvent.setRoleId(saRoleForm.getRoleId());
						updateEvent.setRoleType(UIConstants.SA_ROLETYPE);
						updateEvent.setRoleCreatorId(SecurityUIHelper.getJIMSLogonId());

						String agencyId = saRoleForm.getAgencyId();
						if (agencyId == null || agencyId.equals(""))
						{
							return aMapping.findForward(UIConstants.FAILURE);
						}
						Collection agencyIds = new ArrayList();
						agencyIds.add(agencyId);
						updateEvent.setAgenciesList(agencyIds);

						Collection featureList = saRoleForm.getCurrentFeatures();
						if (featureList == null || featureList.isEmpty())
						{
							return aMapping.findForward(UIConstants.FAILURE);
						}
						Iterator featuresIter = featureList.iterator();
						Collection featureIdList = new ArrayList();
						while (featuresIter.hasNext())
						{
							FeaturesResponseEvent fEvent = (FeaturesResponseEvent) featuresIter.next();
							featureIdList.add(fEvent.getFeatureId());
						}
						updateEvent.setFeaturesList(featureIdList);
						iEvent = updateEvent;
						forward = UIConstants.UPDATE_SUCCESS;
					}
					else
					{
						return aMapping.findForward(UIConstants.FAILURE);
					}
			dispatch.postEvent(iEvent);
			// Get the reply
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);

			DuplicateRecordErrorResponseEvent duplicateErrorMessage = (DuplicateRecordErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, DuplicateRecordErrorResponseEvent.class);

			if (duplicateErrorMessage != null)
				saRoleForm.setErrorMessage(duplicateErrorMessage.getMessage());
			else
				saRoleForm.setErrorMessage("");
		}
		return aMapping.findForward(forward);
	}
	/**
	  * @param aMapping
	  * @param aForm
	  * @param aRequest
	  * @param aResponse
	  * @return ActionForward
	  */
	public ActionForward delete(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleSAForm roleSAForm = (RoleSAForm) aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetUsersEvent requestEvent = (GetUsersEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERS);
		requestEvent.setAgencyId(roleSAForm.getAgencyId());
		requestEvent.setUserTypeId(UIConstants.SA_ROLETYPE);
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection users = (Collection) dataMap.get(PDContactConstants.USER_EVENT_TOPIC);
		if (users == null || users.isEmpty())
		{
			finish(aMapping, aForm, aRequest, aResponse);
			return aMapping.findForward("deleteSuccess");
		}
		else
		{
			roleSAForm.setUsers(users);
			return aMapping.findForward("confirmDeleteSuccess");
		}
	}

	/**
	  * @param aMapping
	  * @param aForm
	  * @param aRequest
	  * @param aResponse
	  * @return ActionForward
	  */
	public ActionForward continueDelete(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward("deleteSuccess");
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
		RoleSAForm saRoleForm = (RoleSAForm) aForm;
		saRoleForm.clear();
		aRequest.setAttribute("action", "");
		return aMapping.findForward(UIConstants.CANCEL);
	}

	/**
	  * @param aMapping
	  * @param aForm
	  * @param aRequest
	  * @param aResponse
	  * @return ActionForward
	  */
	public ActionForward mainpage(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleSAForm saRoleForm = (RoleSAForm) aForm;
		saRoleForm.clear();
		aRequest.setAttribute("action", "");
		return aMapping.findForward(UIConstants.MAIN_MENU);
	}
}