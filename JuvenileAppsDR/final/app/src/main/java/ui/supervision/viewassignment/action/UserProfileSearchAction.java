package ui.supervision.viewassignment.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.viewassignment.GetUserProfileInfoEvent;
import messaging.viewassignment.GetUserProfileInfoResponseEvent;
import mojo.km.utilities.MessageUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.viewassignment.form.CaseAssignmentReportForm;

public class UserProfileSearchAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("assign.supervisee.setup", "setup"); 
		keyMap.put("button.next", "next"); 
	}

	public ActionForward setup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String forward = "initialSetup";
		CaseAssignmentReportForm caseAssignmentReportForm = (CaseAssignmentReportForm) form;
		String lastName = caseAssignmentReportForm.getUserReport().getOfficerLastName();
		String firstName = caseAssignmentReportForm.getUserReport().getOfficerFirstName();
		GetUserProfileInfoEvent getUserProfileInfoEvent = new GetUserProfileInfoEvent();
		getUserProfileInfoEvent.setLastName(lastName);		
		getUserProfileInfoEvent.setFirstName(firstName);
		List officersWithMatchingName = null;
		try {
			officersWithMatchingName = MessageUtil.postRequestListFilter(getUserProfileInfoEvent, GetUserProfileInfoResponseEvent.class);
			if (officersWithMatchingName == null || officersWithMatchingName.size() == 0) {
				this.sendToErrorPage(request, "error.generic", "No users found with matching name");
			}
			caseAssignmentReportForm.getUserReport().setOfficersWithMatchingName(officersWithMatchingName);			
		} catch (Exception e) {
			e.printStackTrace();
			this.sendToErrorPage(request, "error.generic", "Error in finding the results. " + e.getMessage());
		}
		return mapping.findForward(forward);
	}

	public ActionForward next(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {		
		String forward = "search";
		CaseAssignmentReportForm caseAssignmentReportForm = (CaseAssignmentReportForm) form;
		List officersWithMatchingName = caseAssignmentReportForm.getUserReport().getOfficersWithMatchingName();
		String selectUserId = caseAssignmentReportForm.getUserReport().getUserId();
		if (selectUserId == null || selectUserId.trim().length() == 0) {
			this.sendToErrorPage(request, "error.generic", "Empty user id selected.");
			forward = "initialSetup";
		} else {
			for (int i = 0; i < officersWithMatchingName.size(); i++) {
				GetUserProfileInfoResponseEvent userProfile = (GetUserProfileInfoResponseEvent) officersWithMatchingName.get(i);
				if (userProfile.getUserId().equals(selectUserId)) {
					String officerName = userProfile.getOfficerName();
					if (officerName != null) {
						String[] tokens = officerName.split(",");
						caseAssignmentReportForm.getUserReport().setOfficerLastName(tokens[0].trim());
						caseAssignmentReportForm.getUserReport().setOfficerFirstName(tokens[1].trim());
					}					
					break;
				}
			}			
		}
		return mapping.findForward(forward);
	}
}
