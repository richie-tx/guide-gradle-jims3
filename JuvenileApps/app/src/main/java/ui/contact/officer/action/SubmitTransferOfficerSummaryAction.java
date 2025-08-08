package ui.contact.officer.action;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.GetOfficerProfilesByAttributeEvent;
import messaging.officer.UpdateOfficerProfilesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.OfficerProfileControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import ui.contact.officer.form.OfficerForm;
import ui.exception.GeneralFeedbackMessageException;
import org.apache.struts.action.ActionForward;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;

public class SubmitTransferOfficerSummaryAction extends JIMSBaseAction {
	public SubmitTransferOfficerSummaryAction() {
	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.transferManagerSearch", "transferManagerSearch");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {
		OfficerForm officerForm = (OfficerForm) aForm;
		officerForm.setAction("confirm");
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		//create save event to persist data.
		UpdateOfficerProfilesEvent updateEvent = (UpdateOfficerProfilesEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.UPDATEOFFICERPROFILES);		
		updateEvent.setNewManagerId(officerForm.getNewManagerId());
		updateEvent.setNewManagerFirstName(officerForm.getNewManagerFirstName());
		updateEvent.setNewManagerMiddleName(officerForm.getNewManagerMiddleName());
		updateEvent.setNewManagerLastName(officerForm.getNewManagerLastName());
		updateEvent.setUpdateOfficerProfilesEvents(officerForm.getSelectedList());
		dispatch.postEvent(updateEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		ActionForward forward = aMapping.findForward(UIConstants.FINISH);
		return forward;
	}

	public ActionForward transferManagerSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {
		OfficerForm officerForm = (OfficerForm) aForm;
		officerForm.clear();
		List profiles = new ArrayList<OfficerProfileResponseEvent>();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetOfficerProfilesByAttributeEvent event = (GetOfficerProfilesByAttributeEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILESBYATTRIBUTE);
		event.setAttributeName(UIConstants.OFFICER_SUBTYPE);
		event.setAttributeValue(UIConstants.OFFICER_SUBTYPE_CLM);
		dispatch.postEvent(event);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		List officerprofiles = MessageUtil.compositeToList(response, OfficerProfileResponseEvent.class);

		if (officerprofiles != null && officerprofiles.size() > 0) {
			Collections.sort(officerprofiles);
			Iterator<OfficerProfileResponseEvent> events = officerprofiles.iterator();
			while (events.hasNext()) {
				OfficerProfileResponseEvent resp = events.next();
				// Here we need to retrieve only the officers who are of type
				// Juvenile
				if (UIConstants.OFFICER_TYPE_JUVENILE.equalsIgnoreCase(resp
						.getOfficerTypeId())&&(resp.getStatusId().equals("A"))) {
					profiles.add(resp);
				}
			}
		}
		officerForm.setManagerProfiles(profiles);
		if (profiles.size() == 0) {
			// zero results: forward 'searchFailure'
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecords"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.SEARCH_FAILURE);
		}
		ActionForward forward = aMapping.findForward(UIConstants.SEARCH_SUCCESS);
		return forward;
	}
}
