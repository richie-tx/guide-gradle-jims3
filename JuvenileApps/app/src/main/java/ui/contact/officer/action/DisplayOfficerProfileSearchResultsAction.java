//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\officer\\action\\DisplayOfficerProfileSearchResultsAction.java

package ui.contact.officer.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.officer.GetOfficerProfilesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.OfficerProfileControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.contact.officer.form.OfficerForm;

public class DisplayOfficerProfileSearchResultsAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42E67669016E
	 */
	public DisplayOfficerProfileSearchResultsAction()
	{

	}

	/**
	 * @return Map
	 * @roseuid 4294862303BD
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();

		buttonMap.put("button.find", "findOfficers");
		buttonMap.put("button.refresh", "refresh");
		return buttonMap;
	}	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42E65EA702E5
	 */
	public ActionForward findOfficers(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OfficerForm officerForm = (OfficerForm) aForm;

		//empty all collection so that the display page does not display it
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		officerForm.setOfficerProfiles((List) emptyColl);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetOfficerProfilesEvent officerEvent = (GetOfficerProfilesEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILES);
		officerEvent.setLastName(officerForm.getLastNamePrompt());
		officerEvent.setFirstName(officerForm.getFirstNamePrompt());
		officerEvent.setDepartmentName(officerForm.getDepartmentNamePrompt());
		officerEvent.setDepartmentId(officerForm.getDepartmentIdPrompt());
		officerEvent.setOtherIdNum(officerForm.getOtherIdNumPrompt());
		officerEvent.setBadgeNum(officerForm.getBadgeNumPrompt());
		officerEvent.setUserID(officerForm.getUserIdPrompt());
		officerEvent.setManagerId(officerForm.getManagerId());
		officerEvent.setOfficerTypeId(officerForm.getOfficerTypeId());
		officerEvent.setStatus(officerForm.getStatusId());
		dispatch.postEvent(officerEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection officerProfiles = MessageUtil.compositeToCollection(compositeResponse, OfficerProfileResponseEvent.class);
		if (officerProfiles != null && officerProfiles.size() > 0)
		{
			officerForm.setOfficerProfiles((List) officerProfiles);
		}
		else {
			if (officerProfiles != null && officerProfiles.size() == 0)
			{
//		CWS check for too many records
	       	CountInfoMessage infoEvent = new CountInfoMessage();
	       	CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,CountInfoMessage.class);
			if (iMessage != null ){
				ActionErrors errors = new ActionErrors();
				ActionMessage error = new ActionMessage("error.max.limit.exceeded");
				errors.add(ActionErrors.GLOBAL_MESSAGE, error);
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.FAILURE);
			}
			this.sendToErrorPage(aRequest, "error.no.matchingOfficerProfile.found");
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
		officerForm.setOfficerProfiles((List) emptyColl);
		//reset all input search values
		officerForm.setLastNamePrompt("");
		officerForm.setFirstNamePrompt("");
		officerForm.setDepartmentNamePrompt("");
		officerForm.setDepartmentIdPrompt("");
		officerForm.setOtherIdNumPrompt("");
		officerForm.setBadgeNumPrompt("");
		officerForm.setUserIdPrompt("");
		officerForm.setOfficerTypeId("");
		officerForm.setStatusId("");
		officerForm.setManagerId("");
		
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