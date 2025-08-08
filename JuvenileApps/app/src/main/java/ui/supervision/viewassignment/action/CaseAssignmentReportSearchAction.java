/*
 * Created on Jan 18, 2008
 *
 */
package ui.supervision.viewassignment.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdstaffposition.reply.OrganizationResponseEvent;
import messaging.manageworkgroup.GetWorkGroupsEvent;
import messaging.manageworkgroup.reply.WorkGroupResponseEvent;
import messaging.supervisionstaff.GetAllProgramUnitsEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.OrganizationControllerServiceNames;
import naming.WorkGroupControllerServiceNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.security.SecurityUIHelper;
import ui.supervision.viewassignment.form.CaseAssignmentReportForm;

/**
 * @author cc_rbhat
 * 
 */
public class CaseAssignmentReportSearchAction extends JIMSBaseAction {
	private static String FWD_SETUP = "initialSetup";

	private static String FWD_SEARCH = "search";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("assign.supervisee.setup", "setup");
		keyMap.put("button.submit", "search");
	}

	public ActionForward setup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CaseAssignmentReportForm caseAssignmentReportForm = (CaseAssignmentReportForm) form;
		caseAssignmentReportForm.initialize();

		List programUnits = initializeProgramUnits();
		List workgroups = initializeWorkgroups();

		caseAssignmentReportForm.getProgramUnitReport().setProgramUnits(
				programUnits);
		caseAssignmentReportForm.getUserReport().setProgramUnits(programUnits);
		caseAssignmentReportForm.getWorkgroupReport().setProgramUnits(
				programUnits);
		caseAssignmentReportForm.getWorkgroupReport().setWorkgroups(workgroups);

		return mapping.findForward(FWD_SETUP);
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String forward = FWD_SEARCH;
		CaseAssignmentReportForm caseAssignmentReportForm = (CaseAssignmentReportForm) form;
		String searchBy = caseAssignmentReportForm.getSearchBy();
		if (searchBy.equalsIgnoreCase("user")) {
			String userId = caseAssignmentReportForm.getUserReport()
					.getUserId();
			String lastName = caseAssignmentReportForm.getUserReport()
					.getOfficerLastName();
			String firstName = caseAssignmentReportForm.getUserReport()
					.getOfficerFirstName();
			if (userId != null && userId.trim().length() > 0
					&& lastName != null && lastName.trim().length() > 0
					&& firstName != null && firstName.trim().length() > 0) {
				// All three are entered by user.
				this.sendToErrorPage(request, "error.generic",
						"User ID or Last Name and First Name are required");
				forward = FWD_SETUP;
			} else if ((userId == null || userId.trim().length() == 0)
					&& (lastName == null || lastName.trim().length() == 0)
					&& (firstName == null || firstName.trim().length() == 0)) {
				// None of three are entered by user
				this.sendToErrorPage(request, "error.generic",
						"User ID or Last Name and First Name are required");
				forward = FWD_SETUP;
			} else if ((userId != null && userId.trim().length() > 0)
					&& (lastName == null || lastName.trim().length() == 0)
					&& (firstName == null || firstName.trim().length() == 0)) {
				// Only userId is entered by user
				forward = FWD_SEARCH;
			} else if ((userId == null || userId.trim().length() == 0)
					&& (lastName != null && lastName.trim().length() > 0)
					&& (firstName != null && firstName.trim().length() > 0)) {
				// UserId is not provided, last name and first name are entered
				// by user.
				forward = "userProfileSearch";
			} else {
				this.sendToErrorPage(request, "error.generic",
						"User ID or Last Name and First Name are required");
				forward = FWD_SETUP;
			}
		}
		return mapping.findForward(forward);
	}

	private List initializeWorkgroups() {
		GetWorkGroupsEvent event = (GetWorkGroupsEvent) EventFactory
				.getInstance(WorkGroupControllerServiceNames.GETWORKGROUPS);
		event.setAgencyId(SecurityUIHelper.getUserAgencyId());
		List workgroups = MessageUtil.postRequestListFilter(event,
				WorkGroupResponseEvent.class);
		sortByWorkgroupName(workgroups);
		return workgroups;
	}

	private List initializeProgramUnits() {

		List programUnits = new ArrayList();

		GetAllProgramUnitsEvent getProgramUnitsRequest = (GetAllProgramUnitsEvent) EventFactory
				.getInstance(OrganizationControllerServiceNames.GETALLPROGRAMUNITS);

		programUnits = MessageUtil.postRequestListFilter(
				getProgramUnitsRequest, OrganizationResponseEvent.class);
		sortByProgramUnitName(programUnits);
		return programUnits;
	}

	private void sortByProgramUnitName(List programUnits) {
		Collections.sort(programUnits, new Comparator() {
			public int compare(Object o1, Object o2) {
				if (!(o1 instanceof OrganizationResponseEvent))
					throw new ClassCastException();
				if (!(o2 instanceof OrganizationResponseEvent))
					throw new ClassCastException();
				String name1 = ((OrganizationResponseEvent) o1)
						.getDescription();
				String name2 = ((OrganizationResponseEvent) o2)
						.getDescription();
				if (name1 == null) {
					name1 = "";
				}
				if (name2 == null) {
					name2 = "";
				}
				return name1.compareTo(name2);
			}

			public boolean equals(Object o1) {
				if ((o1 instanceof Comparator) && (this == o1))
					return true;
				else
					return false;
			}
		});
	}

	private void sortByWorkgroupName(List workgroups) {
		Collections.sort(workgroups, new Comparator() {
			public int compare(Object o1, Object o2) {
				if (!(o1 instanceof WorkGroupResponseEvent))
					throw new ClassCastException();
				if (!(o2 instanceof WorkGroupResponseEvent))
					throw new ClassCastException();
				String name1 = ((WorkGroupResponseEvent) o1).getWorkgroupName();
				String name2 = ((WorkGroupResponseEvent) o2).getWorkgroupName();
				if (name1 == null) {
					name1 = "";
				}
				if (name2 == null) {
					name2 = "";
				}
				return name1.compareTo(name2);
			}

			public boolean equals(Object o1) {
				if ((o1 instanceof Comparator) && (this == o1))
					return true;
				else
					return false;
			}
		});
	}
}
