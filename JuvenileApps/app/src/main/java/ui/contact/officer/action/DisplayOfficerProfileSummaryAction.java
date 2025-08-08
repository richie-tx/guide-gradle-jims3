//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\officer\\action\\DisplayOfficeProfileSummaryAction.java

package ui.contact.officer.action;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.ValidateOfficerProfileEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.OfficerProfileControllerServiceNames;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.contact.UIContactHelper;
import ui.contact.officer.form.OfficerForm;

public class DisplayOfficerProfileSummaryAction extends Action
{

	/**
	 * @roseuid 42E6766200A3
	 */
	public DisplayOfficerProfileSummaryAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42E65EA50064
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OfficerForm officerForm = (OfficerForm) aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		ValidateOfficerProfileEvent event =
			(ValidateOfficerProfileEvent) EventFactory.getInstance(
				OfficerProfileControllerServiceNames.VALIDATEOFFICERPROFILE);
		event.setLogonId(officerForm.getUserIdPrompt());
		event.setDepartmentId(officerForm.getDepartmentIdPrompt());
		event.setBadgeNum(officerForm.getBadgeNumPrompt());
		event.setOtherIdNum(officerForm.getOtherIdNumPrompt());
		dispatch.postEvent(event);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Object obj = MessageUtil.filterComposite(compositeResponse, DuplicateRecordErrorResponseEvent.class);
		if (obj != null)
		{
			DuplicateRecordErrorResponseEvent duplicateEvent = (DuplicateRecordErrorResponseEvent) obj;
			if (duplicateEvent.getMessage() != null && duplicateEvent.getMessage().startsWith("Badge"))
			{
				this.sendToErrorPage(aRequest, "error.badge.exist");
			}
			else
				if (duplicateEvent.getMessage() != null && duplicateEvent.getMessage().startsWith("Other"))
				{
					this.sendToErrorPage(aRequest, "error.otherId.exist");
				}
				else
				{
					this.sendToErrorPage(aRequest, "error.common");
				}
				return aMapping.findForward(naming.UIConstants.SUMMARY_SUCCESS);
		}
		obj = MessageUtil.filterComposite(compositeResponse, OfficerProfileResponseEvent.class);
		if (obj != null)
		{
			OfficerProfileResponseEvent officerprofileEvent = (OfficerProfileResponseEvent) obj;
			ActionErrors errors = new ActionErrors();
			errors.add(
				ActionErrors.GLOBAL_MESSAGE,
				new ActionMessage(
					"error.officer.userId.duplicate",
					officerprofileEvent.getUserId(),
					officerprofileEvent.getDepartmentName()));
			saveErrors(aRequest, errors);
			return aMapping.findForward(naming.UIConstants.SUMMARY_SUCCESS);
		}

		// check badge and other ID Number
		checkBadgeOrOtherIdNumber(officerForm, aRequest);

		// set the values from dropdownlist
		UIContactHelper.setManageOfficerValuesFromDropDownList(officerForm);
		return aMapping.findForward(naming.UIConstants.SUMMARY_SUCCESS);
	}

	/**
	 * @param officerForm
	 * @param aRequest
	 */
	private void checkBadgeOrOtherIdNumber(OfficerForm officerForm, HttpServletRequest aRequest)
	{
		String badgeNumber = officerForm.getBadgeNum();
		String idNumber = officerForm.getOtherIdNum();
		if (badgeNumber == null || badgeNumber.equals("") && (idNumber == null || idNumber.equals("")))
		{
			sendToErrorPage(aRequest, "error.eitherBadgeOrPayrollNumberEmpty");
		}
	}

	/**
	 * @param aRequest
	 * @param errorKey
	 */
	private void sendToErrorPage(HttpServletRequest aRequest, String errorKey)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey));
		saveErrors(aRequest, errors);
	}
}