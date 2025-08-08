//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\officer\\action\\DisplayManagerSearchResultsAction.java

package ui.contact.officer.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.officer.GetOfficerProfilesEvent;
import messaging.user.GetUserProfilesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.OfficerProfileControllerServiceNames;
import naming.UIConstants;
import naming.UserControllerServiceNames;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.contact.officer.form.OfficerForm;

public class DisplayManagerSearchResultsAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42E676610045
	 */
	public DisplayManagerSearchResultsAction()
	{

	}
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.find", "find");
		buttonMap.put("button.refresh", "refresh");
		return buttonMap;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42E65EA50372
	 */
	public ActionForward find(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OfficerForm officerForm = (OfficerForm) aForm;

		//empty all collection so that the display page does not display it
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		officerForm.setManagerProfiles(emptyColl);

		/*
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetUserProfilesEvent managerEvent = (GetUserProfilesEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERPROFILES);
		*/
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetOfficerProfilesEvent managerEvent = (GetOfficerProfilesEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILES);
		managerEvent.setLastName(officerForm.getLastNamePrompt());
		managerEvent.setFirstName(officerForm.getFirstNamePrompt());
		managerEvent.setUserID(officerForm.getManagerId());
		managerEvent.setDepartmentId(officerForm.getDepartmentId());
		managerEvent.setStatus("A");
		dispatch.postEvent(managerEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection managerProfiles = MessageUtil.compositeToCollection(compositeResponse, OfficerProfileResponseEvent.class);
		if (managerProfiles != null && managerProfiles.size() > 0)
		{
			officerForm.setManagerProfiles(managerProfiles);
			officerForm.setManagerProfilesSize("" + managerProfiles.size());
		}
		else
		{
			this.sendToErrorPage(aRequest, "error.no.managerProfile.found");
		}
		return aMapping.findForward(UIConstants.FIND_SUCCESS);
	}

	public ActionForward refresh(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			OfficerForm officerForm = (OfficerForm) aForm;

			//empty all collection so that the display page does not display it
			officerForm.setManagerProfiles(new ArrayList());
			//reset all input search values
			officerForm.setManagerId("");
			officerForm.setLastNamePrompt("");
			officerForm.setFirstNamePrompt("");
						
			return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
		}
	
	/**
	 * @param aRequest
	 */
	private void sendToErrorPage(HttpServletRequest aRequest, String errorKey)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey));
		saveErrors(aRequest, errors);
	}
}