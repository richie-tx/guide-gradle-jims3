/*
 * Created on Jun 27, 2007
 */
package ui.supervision.administercaseload.action.reassignbyofficemanager.reassigntoclo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.contact.to.NameBean;
import messaging.cscdstaffposition.GetCourtStaffPositionEvent;
import messaging.manageworkgroup.GetWorkGroupsEvent;
import messaging.manageworkgroup.reply.WorkGroupResponseEvent;
import messaging.supervision.reply.SupervisionStaffResponseEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.CSCDStaffPositionControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.WorkGroupControllerServiceNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.security.SecurityUIHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

/**
 * @author cc_rbhat
 */
public class ReassignSuperviseeToCLOAction extends JIMSBaseAction {
	private static String FWD_SETUP = "initialSetup";

	private static String FWD_REASSIGN_TO_CLO_CASENOTE = "reassignToCLOCasenote";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.toCLO", "setup");
		keyMap.put("button.next", "reassignToCLO");
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

		String officerPositionId = caseAssignmentForm.getOfficerPositionId();
		caseAssignmentForm.setStaffPositionIdBeforeReassignment(officerPositionId);

		List courtServicesWorkGroups = caseAssignmentForm.getCourtServicesWorkGroups();
		if (courtServicesWorkGroups == null || courtServicesWorkGroups.size() == 0) {
			GetWorkGroupsEvent event = (GetWorkGroupsEvent) EventFactory
					.getInstance(WorkGroupControllerServiceNames.GETWORKGROUPS);

			event.setAgencyId(SecurityUIHelper.getUserAgencyId());
			event.setType("RC");
			courtServicesWorkGroups = MessageUtil.postRequestListFilter(event, WorkGroupResponseEvent.class);
			caseAssignmentForm.setCourtServicesWorkGroups(courtServicesWorkGroups);
		}
		List courts = caseAssignmentForm.getCourts();
		if (courts == null || courts.size() == 0) {
			courts = new ArrayList(UISupervisionOptionHelper.fetchCSCDFilteredCourts());
//			testPrintCourts(courts);
			List temp = new ArrayList();
			String category = "";
// CS 8/19/2010 part of defect 66961 changes per discussion with DJ		
// list should only contain criminal misd, felony and out of county courts 
			for (int y=0; y<courts.size(); y++){
				CourtResponseEvent cre = (CourtResponseEvent) courts.get(y);
				category = cre.getCourtCategory();
				if ("CC".equals(category) || "CR".equals(category) || "OC".equals(category) ) {
					temp.add(cre);
				}
			}
//			courts = temp;
			caseAssignmentForm.setCourts(temp);
		}
// need to get courtId for court select pre-select on jsp		
		List activeCases = caseAssignmentForm.getActiveCases();
		String crtNumStr = "";
		int totalCases = activeCases.size();
// more than 1 active case, compare courtId values to be same		
		if (totalCases > 1){
			for (int y=0; y<activeCases.size(); y++){
				CaseAssignmentTO cato = (CaseAssignmentTO) activeCases.get(y);
				if (y == 0){
					crtNumStr = cato.getCourtId();
					if (crtNumStr == null){
						crtNumStr = "";
					}
					totalCases = 1;
				} else if (!crtNumStr.equals(cato.getCourtId())){
					totalCases = 2;
					break;
				}
			}
		}
		if (totalCases == 1){
			CaseAssignmentTO cato = (CaseAssignmentTO) activeCases.get(0);
			crtNumStr = cato.getCourtId();
			if (crtNumStr != null & !"".equals(crtNumStr)) {
				try {
					int num = Integer.parseInt(crtNumStr);
					while(crtNumStr.length()< 4){
					  crtNumStr = "0" + crtNumStr;
					}
				} catch (NumberFormatException e){
// do nothing, courtId is not numeric	
				}
				if (cato.getCdi() == null || "".equals(cato.getCdi())){
					cato.setCdi(cato.getCriminalCaseId().substring(0, 3) );
				}
				if ("002".equals(cato.getCdi() ) ){
					crtNumStr = "CC " + crtNumStr;
				}else if ("003".equals(cato.getCdi() ) ){
					crtNumStr = "CR " + crtNumStr;
					} else if ("010".equals(cato.getCdi() ) ) {
						crtNumStr = "OC " + crtNumStr;
						}
			}
		}
		caseAssignmentForm.setCourtNumber(crtNumStr);
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
	public ActionForward reassignToCLO(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		caseAssignmentForm.setAgencyId(SecurityUIHelper.getUserAgencyId());
		String officerPositionId = getOfficersPositionId(caseAssignmentForm);
		if (officerPositionId == null || officerPositionId.length() == 0) {
        	sendToErrorPage(request, "error.administerCaseload.noCloFound");								
    		return mapping.findForward(FWD_SETUP);
		} else {
			return mapping.findForward(FWD_REASSIGN_TO_CLO_CASENOTE);			
		}
	}

	private String getOfficersPositionId(CaseAssignmentForm caseAssignmentForm) {
		GetCourtStaffPositionEvent event = (GetCourtStaffPositionEvent) EventFactory
				.getInstance(CSCDStaffPositionControllerServiceNames.GETCOURTSTAFFPOSITION);
		event.setCourtId(caseAssignmentForm.getReassignedCourtId());
		event.setJobTitleId(PDCodeTableConstants.STAFF_JOB_TITLE_CLO);
		List results = MessageUtil.postRequestListFilter(event, SupervisionStaffResponseEvent.class);
		String officerPositionId = null;
		if (results != null && results.size() > 0) {
			//As per business rule, a court has one and only one CLO. Right now
			//there is no data integrity constraint to enforce this rule.
			//Therefore, in the results we might get a list of CLOs for a court. 
			//We only use the first non-null entry in the list.
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				SupervisionStaffResponseEvent sre = (SupervisionStaffResponseEvent) iterator.next();
				officerPositionId = sre.getSupervisionStaffId();
				if (officerPositionId != null && officerPositionId.length() != 0) {
					caseAssignmentForm.setOfficerPositionId(officerPositionId);		
					if (sre.getLogonId() != null) {
						caseAssignmentForm.setOfficerName(new NameBean(sre.getFirstName(), sre.getMiddleName(), sre.getLastName()));						
					} else {
						caseAssignmentForm.setOfficerName(new NameBean("No Officer Assigned", "", ""));						
					}
					break;
				}
			}
		}
		return officerPositionId;
	}

	private void testPrintCourts(List courts) {
		Iterator iterator = courts.iterator();
		while (iterator.hasNext()) {
			CourtResponseEvent event = (CourtResponseEvent) iterator.next();
			System.err.println(event.getCourtCategory() + " " + event.getCourtCategoryDesc() + " " + event.getCourtId()
					+ " " + event.getCourtNumber());
		}
	}

}
