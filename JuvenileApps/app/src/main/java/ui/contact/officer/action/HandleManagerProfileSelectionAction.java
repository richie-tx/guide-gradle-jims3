//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\officer\\action\\HandleOfficerProfileSelectionAction.java

package ui.contact.officer.action;

import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.contact.officer.form.OfficerForm;
import ui.security.SecurityUIHelper;

public class HandleManagerProfileSelectionAction extends Action
{

	/**
	 * @roseuid 42E6766D016E
	 */
	public HandleManagerProfileSelectionAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42E65EA70334
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OfficerForm officerForm = (OfficerForm) aForm;
		if (!SecurityUIHelper.isLoggedIn())
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.not.logged.in"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		String selectedManagerId = officerForm.getSelectedManager();
		Iterator iter = officerForm.getManagerProfiles().iterator();
		OfficerProfileResponseEvent userResponseEvent = null;
		while (iter.hasNext())
		{
			userResponseEvent = (OfficerProfileResponseEvent) iter.next();
			if (selectedManagerId.equals(userResponseEvent.getUserId()))
			{
				break;
			}
		}
		officerForm.setManagerId(userResponseEvent.getUserId());
		officerForm.setManagerFirstName(userResponseEvent.getFirstName());
		officerForm.setManagerLastName(userResponseEvent.getLastName());
		officerForm.setManagerMiddleName(userResponseEvent.getMiddleName());
		officerForm.setManagerStatus(userResponseEvent.getStatusId());
		return aMapping.findForward(UIConstants.SELECT_MANAGER_SUCCESS);
	}
}