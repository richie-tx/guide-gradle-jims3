//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\ui\\supervision\\cscdcalendar\\action\\DisplayCSOtherEventUpdateAction.java

package ui.supervision.cscdcalendar.otherEvent.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdcalendar.reply.CSOtherResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.StringUtil;
import ui.supervision.cscdcalendar.form.CSCalendarOtherForm;

public class HandleCSOtherEventMultipleResultsAction extends JIMSBaseAction {

	/**
	 * @roseuid 479A0F0A03B6
	 */
	public HandleCSOtherEventMultipleResultsAction() {

	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {

		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {

		return aMapping.findForward(UIConstants.CANCEL);
	}

	protected void addButtonMapping(Map keyMap) {

		keyMap.put("button.link", "enterResults");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.next", "next");
	}

	/**
	 * @roseuid 47A22846002B
	 */
	public ActionForward enterResults(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CSCalendarOtherForm form = (CSCalendarOtherForm) aForm;
		form.setActivityFlow("");
		String[] otherEventIds = aRequest.getParameterValues("selectedEventId");

		List resultsList = new ArrayList();
		boolean isEmpty = true;
		form.setNarrative("");

		if (otherEventIds != null && otherEventIds.length > 0) {
			for (String otherEventId : otherEventIds) {

				for (int i = 0; i < form.getFilteredEvents().size(); i++) {
					CSOtherResponseEvent resp = (CSOtherResponseEvent) form.getFilteredEvents().get(i);
					if (resp.getEventId().equalsIgnoreCase(otherEventId)) {
						resultsList.add(resp);
						break;
					}
				}
			}
		}
		if (resultsList != null) {
			form.setSelectedEvents(resultsList);
			if (resultsList.size() > 0) {
				for (int i = 0; i < resultsList.size(); i++) {
					CSOtherResponseEvent resp = (CSOtherResponseEvent) resultsList.get(i);
					if (!StringUtil.isEmpty(resp.getPurpose()) && isEmpty) {
						form.setNarrative(resp.getPurpose());
						isEmpty = false;

					}
				}
			}
		}

		return aMapping.findForward(UIConstants.SUCCESS);
	}

	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CSCalendarOtherForm form = (CSCalendarOtherForm) aForm;

		List resultsList = new ArrayList();

		for (int i = 0; i < form.getSelectedEvents().size(); i++) {
			CSOtherResponseEvent resp = (CSOtherResponseEvent) form.getSelectedEvents().get(i);
			resultsList.add(resp);

		}

		if (resultsList != null) {
			form.setSelectedEvents(resultsList);
		}
		form.setActivityFlow(UIConstants.SUMMARY);

		return aMapping.findForward(UIConstants.SUCCESS);
	}

}
