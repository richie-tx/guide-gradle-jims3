//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\ui\\supervision\\cscdcalendar\\action\\SubmitCSOtherEventUpdateAction.java

package ui.supervision.cscdcalendar.otherEvent.action;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdcalendar.SaveCSOtherEventEvent;
import messaging.cscdcalendar.reply.CSOtherResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSEventControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.StringUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.cscdcalendar.form.CSCalendarOtherForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class SubmitCSOtherEventUpdateAction extends JIMSBaseAction {

	/**
	 * @roseuid 479A0F0C0099
	 */
	public SubmitCSOtherEventUpdateAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.link", "saveMultipleResults");
	}

	/**
	 * @throws GeneralFeedbackMessageException
	 * @roseuid 47A228E301B2
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {
		CSCalendarOtherForm form = (CSCalendarOtherForm) aForm;

		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);

		SaveCSOtherEventEvent saveEvent = (SaveCSOtherEventEvent) EventFactory
				.getInstance(CSEventControllerServiceNames.SAVECSOTHEREVENT);

		if (UIConstants.CREATE.equals(form.getActivityFlow())) {
			saveEvent.setCreate(true);
			saveEvent.setEventId(null);
		} else if (UIConstants.UPDATE.equals(form.getActivityFlow())) {
			saveEvent.setUpdate(true);

		} else if ("reschedule".equals(form.getActivityFlow())) {
			saveEvent.setReschedule(true);
			saveEvent.setRescheduleEventId(form.getEventId());
		} else if ("enterResults".equals(form.getActivityFlow())) {
			saveEvent.setResults(true);
			saveEvent.setResultPositionId(form.getPositionId());

		} else if (UIConstants.DELETE.equals(form.getActivityFlow())) {
			saveEvent.setDelete(true);
			saveEvent.setDeleteEventId(form.getEventId());
			if (form.getStatusCd() != null && form.getStatusCd().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE)) {
				aRequest.setAttribute("state", UIConstants.SUCCESS);
			} else {
				aRequest.setAttribute("state", UIConstants.CONFIRM);
			}
		}

		saveEvent.setEventId(form.getEventId());
		saveEvent.setEventDate(form.getEventDate());
		if (!StringUtil.isEmpty(form.getStartTime())) {
			saveEvent.setStartTime(form.getStartTime() + " "
					+ form.getAMPMId1());
		} else {
			saveEvent.setStartTime(form.getStartTime());
		}
		if (!StringUtil.isEmpty(form.getEndTime())) {
			saveEvent.setEndTime(form.getEndTime() + " " + form.getAMPMId2());
		} else {
			saveEvent.setEndTime(form.getEndTime());
		}
		saveEvent.setEventName(form.getEventName());
		saveEvent.setPurpose(form.getPurpose());

		saveEvent.setContext(form.getContext());
		if (PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(form
				.getContext())) {
			saveEvent.setSuperviseeId(form.getSuperviseeId());
			saveEvent.setPositionId(myHeaderForm.getOfficerPositionId());

		}

		else {
			saveEvent.setPositionId(form.getPositionId());
		}

		saveEvent.setEventType(form.getEventTypeCd());
		saveEvent.setOtherType(form.getOtherEventTypeName());
		saveEvent.setOutcome(form.getOutcomeCd());
		saveEvent.setNarrative(form.getNarrative());

		if (UIConstants.CREATE.equals(form.getActivityFlow())) {
			// saveEvent.setEventType(form.getEventTypeCd());
		}

		CompositeResponse response = postRequestEvent(saveEvent);

		ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil
				.filterComposite(response, ErrorResponseEvent.class);

		if (error != null) {
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
					error.getMessage());
			aRequest.setAttribute("state", "summary");
			return aMapping.findForward(UIConstants.FAILURE);
		}

		if (!UIConstants.DELETE.equals(form.getActivityFlow())) {
			aRequest.setAttribute("state", UIConstants.CONFIRM);
		}
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	public ActionForward saveMultipleResults(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {
		CSCalendarOtherForm form = (CSCalendarOtherForm) aForm;

		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		for (Iterator iter = form.getSelectedEvents().iterator(); iter.hasNext();) {
			CSOtherResponseEvent event = (CSOtherResponseEvent) iter.next();

			SaveCSOtherEventEvent saveEvent = (SaveCSOtherEventEvent) EventFactory
					.getInstance(CSEventControllerServiceNames.SAVECSOTHEREVENT);

			saveEvent.setResults(true);
			saveEvent.setResultPositionId(event.getPositionId());

			saveEvent.setEventId(event.getEventId());
			saveEvent.setEventDate(event.getEventDate());
			if (!StringUtil.isEmpty(event.getStartTime1())) {
				saveEvent.setStartTime(event.getStartTime1() + " "
						+ event.getAMPMId1());
			} else {
				saveEvent.setStartTime(event.getStartTime());
			}
			if (!StringUtil.isEmpty(event.getEndTime1())) {
				saveEvent.setEndTime(event.getEndTime1() + " "
						+ event.getAMPMId2());
			} else {
				saveEvent.setEndTime(event.getEndTime());
			}

			saveEvent.setContext(form.getContext());
			if (PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(form.getContext())) {
				saveEvent.setSuperviseeId(event.getSuperviseeId());
				saveEvent.setPositionId(myHeaderForm.getOfficerPositionId());

			}

			else {
				saveEvent.setPositionId(event.getPositionId());
			}

			saveEvent.setEventType(event.getEventType());
			saveEvent.setOtherType(event.getOtherType());
			saveEvent.setOutcome(event.getOutcome());
			saveEvent.setNarrative(form.getNarrative());

			postRequestEvent(saveEvent);
		}

		aRequest.setAttribute("state", UIConstants.SUMMARY);
		form.setActivityFlow("");
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @roseuid 47A228EE0377
	 */
	public ActionForward finishResults(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(null);
	}

	/**
	 * @roseuid 47A228FA026D
	 */
	public ActionForward finishReschedule(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(null);
	}

	/**
	 * @roseuid 47A22907001C
	 */
	public ActionForward finishDelete(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(null);
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {

		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {

		return aMapping.findForward(UIConstants.CANCEL);
	}
}
