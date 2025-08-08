/*
 * Created on Jun 27, 2007
 */
package ui.supervision.administercaseload.action.reassignbyofficemanager.reassigntoprogramunit;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.manageworkgroup.GetWorkGroupsEvent;
import messaging.manageworkgroup.reply.WorkGroupResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.WorkGroupControllerServiceNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

/**
 * @author cc_rbhat
 */
public class ReassignSuperviseeToPUWorkgroupAction extends JIMSBaseAction {
	private static String FWD_SETUP = "initialSetup";

	private static String FWD_REASSIGN_TO_PU_WORKGROUP_CASENOTE = "reassignToPUWorkgroupCasenote";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.toProgramUnit.workgroup", "setup");
		keyMap.put("button.next", "assignWorkgroup");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward setup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		if (caseAssignmentForm.getProgramUnitAllocationDate() == null || caseAssignmentForm.getProgramUnitAllocationDate().equals("")){
			caseAssignmentForm.setProgramUnitAllocationDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));
		}
		setIntakeWorkgroups(caseAssignmentForm);

		return mapping.findForward(FWD_SETUP);
	}

	public ActionForward assignWorkgroup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		String reassignedWorkGroupId = caseAssignmentForm.getWorkGroupId();
		caseAssignmentForm.setReassignedWorkGroupId(reassignedWorkGroupId);

		return mapping.findForward(FWD_REASSIGN_TO_PU_WORKGROUP_CASENOTE);
	}
	
	private void setIntakeWorkgroups(CaseAssignmentForm caseAssignmentForm) {
        GetWorkGroupsEvent event = 
        	(GetWorkGroupsEvent) EventFactory.getInstance(WorkGroupControllerServiceNames.GETWORKGROUPS);

        event.setAgencyId(SecurityUIHelper.getUserAgencyId());
        event.setType("IN");
        List workGroups = MessageUtil.postRequestListFilter(event, WorkGroupResponseEvent.class);
        caseAssignmentForm.setWorkGroupsList(workGroups);
	}

}
