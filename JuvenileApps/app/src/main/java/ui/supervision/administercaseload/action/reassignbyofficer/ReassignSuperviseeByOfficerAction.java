/*
 * Created on Jul 25, 2007
 *
 */
package ui.supervision.administercaseload.action.reassignbyofficer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.contact.domintf.IName;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.utilities.MessageUtil;

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
public class ReassignSuperviseeByOfficerAction extends JIMSBaseAction {
	private static String FWD_SETUP = "initialSetup";
	private static String FWD_REASSIGN_BY_OFFICER_SUMMARY = "reassignByOfficerSummary";

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.reviewActiveCases", "setup");
		keyMap.put("button.next", "createTaskInfo");
	}

	public ActionForward setup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
	        
		List usersList = getUsersPosition( );
		for ( int x =0; x < usersList.size(); x ++ ){
			
			LightCSCDStaffResponseEvent lightStaff = (LightCSCDStaffResponseEvent) usersList.get( x );
			
			if ( !"SU".equals( lightStaff.getStaffPositionType() )){
				
				getSupervisors(caseAssignmentForm);
				
			}
		}

		initializeTaskDetails(caseAssignmentForm);
		return mapping.findForward(FWD_SETUP);
	}

	public ActionForward createTaskInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form; 	
		String taskSubject = caseAssignmentForm.getRequestReassignmentTaskSubject();
		String taskNote = caseAssignmentForm.getRequestReassignmentTaskNote();		
		if (taskSubject != null && taskSubject.trim().length() > 0 &&
				taskNote != null && taskNote.trim().length() > 0) {
			return mapping.findForward(FWD_REASSIGN_BY_OFFICER_SUMMARY);
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
		final String taskSubject = "Reassignment to CLO";
		StringBuffer taskNote = new StringBuffer();
		taskNote.append("Reassignment to CLO of ");
		IName superviseeName = caseAssignmentForm.getSuperviseeName();
		if(superviseeName != null && superviseeName.getFormattedName().length() > 0){
			taskNote.append(superviseeName.getFormattedName());
		}else{
			taskNote.append(caseAssignmentForm.getSuperviseeNameStr().trim());
		}
		taskNote.append(", ");
		taskNote.append(caseAssignmentForm.getDefendantId());
		Iterator iterator = caseAssignmentForm.getActiveCasesSelectedForReassignment().iterator();
		for (; iterator.hasNext();) {
			CaseAssignmentTO activeCase = (CaseAssignmentTO) iterator.next();
			taskNote.append(", ");			
			taskNote.append(activeCase.getCriminalCaseId());
			taskNote.append(", ");						
			taskNote.append(activeCase.getCourtId());
		}
		caseAssignmentForm.setRequestReassignmentTaskSubject(taskSubject);
		caseAssignmentForm.setRequestReassignmentTaskNote(taskNote.toString());
	}


	private void getSupervisors(CaseAssignmentForm caseAssignmentForm) throws Exception {
		List supervisors = new ArrayList();
		
		GetLightCSCDStaffForUserEvent ev = new GetLightCSCDStaffForUserEvent();
		ev.setLogonId(SecurityUIHelper.getLogonId());
		ev.setSupervisorNameNeeded(true);
		ev.setSupervisorSupervisorNeeded(true);
		
		LightCSCDStaffResponseEvent lResp = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(ev, LightCSCDStaffResponseEvent.class);
		if (lResp != null) {
			CSCDSupervisionStaffResponseEvent supervisorPosition = null;
			if(lResp.getSupervisorName() != null && !"".equals(lResp.getSupervisorName())){
				supervisorPosition = new CSCDSupervisionStaffResponseEvent();
				supervisorPosition.setAssignedName(lResp.getSupervisorName());
				supervisorPosition.setStaffPositionId(lResp.getSupervisorPositionId());
				supervisors.add(supervisorPosition);
			}
			
			if(lResp.getSupervisorSupervisorName() != null && !"".equals(lResp.getSupervisorSupervisorName())){			
				supervisorPosition = new CSCDSupervisionStaffResponseEvent();
				supervisorPosition.setAssignedName(lResp.getSupervisorSupervisorName());
				supervisorPosition.setStaffPositionId(lResp.getSupervisorSupervisorPositionId());
				supervisors.add(supervisorPosition);
			}	
		}		
		caseAssignmentForm.setSupervisionStaff(supervisors);	
	}
	
	/**
	 * 
	 * @return
	 */
	 public static List getUsersPosition( ){
	        
		  GetLightCSCDStaffForUserEvent ev = new GetLightCSCDStaffForUserEvent();
			
		  ev.setLogonId( SecurityUIHelper.getLogonId() );
		  List aList = MessageUtil.postRequestListFilter( ev, LightCSCDStaffResponseEvent.class );

		  return aList;
	    }
}
