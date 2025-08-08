//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\officer\\action\\DisplayUserProfileSearchResultsAction.java

package ui.contact.officer.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.user.GetUsersEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.InfoMessageEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import naming.UserControllerServiceNames;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.contact.officer.form.OfficerForm;
import ui.security.SecurityUIHelper;

public class DisplayUserProfileSearchResultsAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42E6766C016E
	 */
	public DisplayUserProfileSearchResultsAction()
	{

	}
	/**
	 * @return Map
	 * @roseuid 4294862303BD
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();

		buttonMap.put("button.findUserProfiles", "findUserProfiles");
		buttonMap.put("button.refresh", "refresh");
		return buttonMap;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42E6757C02C6
	 */
	public ActionForward findUserProfiles(
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
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetUsersEvent userEvent = (GetUsersEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERS);
		userEvent.setLastName(officerForm.getLastNamePrompt());
		userEvent.setFirstName(officerForm.getFirstNamePrompt());
		userEvent.setLogonId(officerForm.getLogonIdPrompt());
		userEvent.setDepartmentName(officerForm.getDepartmentNamePrompt());
		dispatch.postEvent(userEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection userProfiles = MessageUtil.compositeToCollection(compositeResponse, SecurityUserResponseEvent.class);
		if (userProfiles != null && userProfiles.size() > 0)
		{
			officerForm.setUserProfiles(userProfiles);
		}
		else
		{
	       	CountInfoMessage infoEvent = new CountInfoMessage();
	       	CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,InfoMessageEvent.class);
	       	if (iMessage != null ){
	       		ActionErrors errors = new ActionErrors();
	       		ActionMessage error = new ActionMessage("error.max.limit.exceeded");
	       		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
	       		saveErrors(aRequest, errors);
	       		return aMapping.findForward(UIConstants.SEARCH_FAILURE);
	       	} 
	       	else
	       	{	
	       		this.sendToErrorPage(aRequest, "error.no.userProfile.found");
	       	}
		}
		return aMapping.findForward(UIConstants.FIND_SUCCESS);
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
		OfficerForm officerForm = (OfficerForm) aForm;

		//empty all collection so that the display page does not display it
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		officerForm.setUserProfiles(emptyColl);
		//reset all input search values
		officerForm.setLastNamePrompt("");
		officerForm.setFirstNamePrompt("");
		officerForm.setLogonIdPrompt("");
		officerForm.setDepartmentNamePrompt("");
		
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