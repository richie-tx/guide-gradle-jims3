//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\DisplayUserGroupCreateSummaryAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.agency.GetAgencyEvent;
import messaging.codetable.GetCodesEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.security.ValidateUserGroupDetailsEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.CodeTableControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.SecurityAdminControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.common.Name;
import ui.security.form.UserGroupForm;

public class DisplayUserGroupCreateSummaryAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42971FCD002F
	 */
	public DisplayUserGroupCreateSummaryAction()
	{

	}
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", "next");
		buttonMap.put("button.cancel", "cancel");
		return buttonMap;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BC02C5
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		userGroupForm.setAction(UIConstants.CREATE);
		String forward = UIConstants.FAILURE;
		if (userGroupForm.getUserType().equalsIgnoreCase(UIConstants.MA_ROLETYPE))
		{
			forward = validateUserGroup(userGroupForm);
			Collection agencyTypes = fetchDropDownCodes(PDCodeTableConstants.AGENCY_TYPE);
			userGroupForm.setAgencyTypes(agencyTypes);
		}
		else
		{
			forward = validateUserGroup(userGroupForm);
		}
		return aMapping.findForward(forward);
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BC02C5
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}

	public Collection fetchDropDownCodes(String codeTableName)
	{
		GetCodesEvent codesRequestEvent = (GetCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETCODES);
		codesRequestEvent.setCodeTableName(codeTableName);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(codesRequestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection codes = (Collection) dataMap.get(PDCodeTableConstants.getCodeTableTopic(codeTableName));
		codes = MessageUtil.processEmptyCollection(codes);
		Collections.sort((ArrayList) codes);
		return codes;
	}

	public String fetchAgencyName(String agencyId)
	{
		GetAgencyEvent agencyRequestEvent =	(GetAgencyEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETAGENCY);
		agencyRequestEvent.setAgencyId(agencyId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(agencyRequestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		AgencyResponseEvent agencyEvent = (AgencyResponseEvent) MessageUtil.filterComposite(compositeResponse, AgencyResponseEvent.class);
		String agencyName = agencyEvent.getAgencyName();
		return agencyName;
	}

	public String validateUserGroup(UserGroupForm userGroupForm)
	{
		ValidateUserGroupDetailsEvent validateEvent = (ValidateUserGroupDetailsEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.VALIDATEUSERGROUPDETAILS);
		validateEvent.setAgencyId(userGroupForm.getAgencyId());
		validateEvent.setUserGroupDescription(userGroupForm.getUserGroupDescription());
		validateEvent.setUserGroupName(userGroupForm.getUserGroupName());
		if (userGroupForm.getUserType().equals(UIConstants.SA_ROLETYPE))
		{
			validateEvent.setCategory(UIConstants.ROLETYPE_CREATEDBY_SA);
		}
		else
		{
			validateEvent.setCategory(UIConstants.ROLETYPE_CREATEDBY_MA);
		}

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(validateEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		String forward = UIConstants.FAILURE;
		DuplicateRecordErrorResponseEvent duplicateErrorMessage = (DuplicateRecordErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, DuplicateRecordErrorResponseEvent.class);
		if (duplicateErrorMessage != null)
		{
			userGroupForm.setErrorMessage(duplicateErrorMessage.getMessage());
			forward = UIConstants.CREATE_FAILURE;
		}
		else
		{
			userGroupForm.setErrorMessage("");
			ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
			IUserInfo user = manager.getIUserInfo();
			Name creatorName = new Name(user.getFirstName(), user.getMiddleName(), user.getLastName());
			userGroupForm.setCreatorName(creatorName);
			if (userGroupForm.getAgencyId() != null)
			{
				String agencyName = fetchAgencyName(userGroupForm.getAgencyId());
				userGroupForm.setAgencyName(agencyName);
			}
			if (userGroupForm.getUserType().equals(UIConstants.SA_ROLETYPE))
			{
				forward = UIConstants.SA_SUCCESS;
			}
			else
			{
				forward = UIConstants.MA_SUCCESS;
			}
		}
		return forward;
	}
}