// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\DisplayDecrementNCCountSummaryAction.java

package ui.supervision.administercompliance.administerconditioncompliance.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.reply.NonComplianceEventResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercompliance.administerconditioncompliance.form.ComplianceForm;

/*
 * 
 * @author mchowdhury
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class DisplayDecrementNCCountSummaryAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplayDecrementNCCountSummaryAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ComplianceForm compForm = (ComplianceForm) aForm;
		String eventId = aRequest.getParameter("ncEventId");

		if (compForm.getSelectedConditions() != null) {
			int len = compForm.getSelectedConditions().size();
			for (int x = 0; x < len; x++) {
				NonComplianceEventResponseEvent resp = (NonComplianceEventResponseEvent) compForm
						.getSelectedConditions().get(x);
				if (resp.getNonComplianceEventId().equalsIgnoreCase(eventId)) {
					compForm.setEventDate(resp.getDateTime());
					compForm.setEventId(eventId);
					compForm.setEventDetails(resp.getDetails());
					compForm.setEventTypes(resp.getEventTypes());
					break;
				}
			}
		}
		return aMapping.findForward(UIConstants.SUCCESS);
	}
}
