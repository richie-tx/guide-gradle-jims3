/*
 * Created on Jul 27, 2007
 */
package ui.supervision.administercaseload.action.reassignbyclo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.contact.domintf.IName;
import messaging.manageworkgroup.GetWorkGroupsEvent;
import messaging.manageworkgroup.reply.WorkGroupResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.WorkGroupControllerServiceNames;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

/**
 * @author cc_rbhat
 *
 */
public class ReassignSuperviseeByCLOAction extends JIMSBaseAction {

	private static String FWD_SETUP = "initialSetup";
	private static String FWD_REASSIGN_BY_CLO_SUMMARY = "reassignByCLOSummary";

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.reviewActiveCases", "setup");
		keyMap.put("button.next", "createTaskInfo");
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
		getWorkgroups(caseAssignmentForm);
		filterSelectedCases(caseAssignmentForm);
		initializeTaskDetails(caseAssignmentForm);
		return mapping.findForward(FWD_SETUP);
	}
    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
	public ActionForward createTaskInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		String taskSubject = caseAssignmentForm.getRequestReassignmentTaskSubject();
		String taskNote = caseAssignmentForm.getRequestReassignmentTaskNote();		
		if (taskSubject != null && taskSubject.trim().length() > 0 &&
				taskNote != null && taskNote.trim().length() > 0) {
			return mapping.findForward(FWD_REASSIGN_BY_CLO_SUMMARY);
		} else {
			if (taskSubject == null || taskSubject.trim().length() == 0) {
				sendToErrorPage(request, "errors.required", "Subject");					
			} else if (taskNote == null || taskNote.trim().length() == 0) {
				sendToErrorPage(request, "errors.required", "Task Text");					
			}			
			return mapping.findForward(FWD_SETUP);
		}		
	}

	private void initializeTaskDetails(CaseAssignmentForm caseAssignmentForm) {
		final String taskSubject = "Reassign case back to region";
		StringBuffer taskNote = new StringBuffer();
		taskNote.append("Reassignment of ");
		IName superviseeName = caseAssignmentForm.getSuperviseeName();
		if(superviseeName != null && superviseeName.getFormattedName().length() > 0){
			taskNote.append(superviseeName.getFormattedName());
			taskNote.append(" ");
		}else{
			taskNote.append(caseAssignmentForm.getSuperviseeNameStr().trim());
			taskNote.append(" ");
		}
		if(StringUtils.isNotEmpty(caseAssignmentForm.getDefendantId())){
			taskNote.append("SPN ");
			taskNote.append(caseAssignmentForm.getDefendantId());
		}
		
		Iterator iterator = caseAssignmentForm.getActiveCasesSelectedForReassignment().iterator();
		for (; iterator.hasNext();) {
			CaseAssignmentTO activeCase = (CaseAssignmentTO) iterator.next();
			taskNote.append(", ");
			taskNote.append(activeCase.getCdi());
			taskNote.append(" / ");
			taskNote.append(activeCase.getDisplayCaseNum());
			taskNote.append(", CRT: ");						
			taskNote.append(activeCase.getCourtId());		
		}
		caseAssignmentForm.setRequestReassignmentTaskSubject(taskSubject);
		caseAssignmentForm.setRequestReassignmentTaskNote(taskNote.toString());
	}
	
	private void getWorkgroups(CaseAssignmentForm caseAssignmentForm) {
		List courtServicesWorkGroups = caseAssignmentForm.getCourtServicesWorkGroups();		
		if (courtServicesWorkGroups == null || courtServicesWorkGroups.size() == 0) {
	        GetWorkGroupsEvent event = 
	        	(GetWorkGroupsEvent) EventFactory.getInstance(WorkGroupControllerServiceNames.GETWORKGROUPS);	
	        event.setAgencyId(SecurityUIHelper.getUserAgencyId());
	        event.setType("RC");	
	        courtServicesWorkGroups = MessageUtil.postRequestListFilter(event, WorkGroupResponseEvent.class);	       
	        caseAssignmentForm.setCourtServicesWorkGroups(courtServicesWorkGroups);
		}		
	}	

	private void filterSelectedCases(CaseAssignmentForm caseAssignmentForm) {
		List activeCasesSelectedForReassignment = new ArrayList();
		String[] casesSelectedForReassignment = caseAssignmentForm.getCasesSelectedForReassignment();
		List activeCases = caseAssignmentForm.getActiveCases();
		
		for (int index = 0; index < casesSelectedForReassignment.length; index++) {			
			for (Iterator iterator = activeCases.iterator(); iterator.hasNext();) {
				CaseAssignmentTO activeCase = (CaseAssignmentTO) iterator.next();				
				if (activeCase.getCaseAssignmentId().equalsIgnoreCase(casesSelectedForReassignment[index])) {
					activeCasesSelectedForReassignment.add(activeCase);
				}
			}
		}
		caseAssignmentForm.setActiveCasesSelectedForReassignment(activeCasesSelectedForReassignment);		
	}
}
