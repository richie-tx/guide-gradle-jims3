package ui.supervision.managetasks.reassigncases.allocatesuperviseetosupervisor.action;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetLightProgramUnitsForDivisionEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.LightProgramUnitResponseEvent;
import messaging.contact.to.NameBean;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.cscdstaffposition.reply.OrganizationResponseEvent;
import messaging.supervisionstaff.GetLightParentOrganizationEvent;
import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.Name;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

public class SelectSupervisorAction extends JIMSBaseAction {
	/**
	 * @roseuid 464368F103D5
	 */
	public SelectSupervisorAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("assign.supervisee.setup", "displaySupervisor");
		keyMap.put("button.allocatesuperviseetosupervisor.selectsupervisor.selectsupervisor", "selectSupervisor");
	}

	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward displaySupervisor(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		caseAssignmentForm.setIsDivisionReassignment(false); 

		String programUnitId = caseAssignmentForm.getProgramUnitId();
		AssignSuperviseeService assignmentService = AssignSuperviseeService.getInstance();

		GetLightParentOrganizationEvent event = new GetLightParentOrganizationEvent();
		
		event.setProgramUnitId( programUnitId );
		OrganizationResponseEvent orgResponse = (OrganizationResponseEvent) 
									MessageUtil.postRequest( event, OrganizationResponseEvent.class );
		String divisionId = orgResponse.getOrganizationId();
		List supervisors = assignmentService.getLightSupervisorsForOrg( divisionId );
		
		Collections.sort(supervisors, new Comparator() {
			public int compare(Object o1, Object o2) {
				if (!(o1 instanceof CSCDSupervisionStaffResponseEvent)) 
					throw new ClassCastException();
				if (!(o2 instanceof CSCDSupervisionStaffResponseEvent)) 
					throw new ClassCastException();
				String name1 = ((CSCDSupervisionStaffResponseEvent) o1).getAssignedName().getFullNameLast();
				String name2 = ((CSCDSupervisionStaffResponseEvent) o2).getAssignedName().getFullNameLast();
				if (name1.equalsIgnoreCase("no officer assigned")) {
					return 1;
				}
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
		}
		);				
		caseAssignmentForm.setSupervisionStaff(supervisors);
	    caseAssignmentForm.setDivisionName( orgResponse.getDescription() );
	    
	    GetLightProgramUnitsForDivisionEvent gEvent = new GetLightProgramUnitsForDivisionEvent();
		gEvent.setDivisionId(divisionId);
		
		List programUnitList = MessageUtil.postRequestListFilter( gEvent, LightProgramUnitResponseEvent.class );
		
		Collections.sort( programUnitList , new Comparator() {
			public int compare(Object o1, Object o2) {
				if (!(o1 instanceof LightProgramUnitResponseEvent)) 
					throw new ClassCastException();
				if (!(o2 instanceof LightProgramUnitResponseEvent)) 
					throw new ClassCastException();
				String name1 = ((LightProgramUnitResponseEvent) o1).getProgramUnitName();
				String name2 = ((LightProgramUnitResponseEvent) o2).getProgramUnitName();
				if (name1.equalsIgnoreCase("no officer assigned")) {
					return 1;
				}
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
		}
		);				
		
		caseAssignmentForm.setProgramUnitList( programUnitList );
		return aMapping.findForward("displaySupervisorList");
	}
	
	public ActionForward selectSupervisor(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {		
		String forward = null; 
		if (validateProgramUnitAssignmentDate(aForm, aRequest)) {			
			CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
			String supervisorPositionId = caseAssignmentForm.getSupervisorPositionId();
			CSCDSupervisionStaffResponseEvent staffDetails = null;
			Iterator staffIterator = caseAssignmentForm.getSupervisionStaff().iterator();
			while (staffIterator.hasNext()) {
				staffDetails = (CSCDSupervisionStaffResponseEvent) staffIterator.next();
				if (staffDetails.getStaffPositionId().equals(supervisorPositionId)) {
					String firstName = "";
					String lastName = "";
					String middleName = "";
					Name assignedName = staffDetails.getAssignedName();
					if (assignedName != null) {//check if staff is assigned to
											   // position
						firstName = staffDetails.getAssignedName().getFirstName();
						lastName = staffDetails.getAssignedName().getLastName();
						if (staffDetails.getAssignedName().getMiddleName() != null){
							middleName = staffDetails.getAssignedName().getMiddleName();
						}	
					}
					caseAssignmentForm.setSupervisorFirstName(firstName);
					caseAssignmentForm.setSupervisorLastName(lastName);
					caseAssignmentForm.setSupervisorMiddleName(middleName);
					caseAssignmentForm.setSupervisorName(new NameBean(firstName, middleName, lastName));
					String positionTypeId = staffDetails.getPositionTypeId();
					String positionTypeName = staffDetails.getPositionName();
					caseAssignmentForm.setStaffPositionDescription(positionTypeName);
					caseAssignmentForm.setAgencyId(SecurityUIHelper.getUserAgencyId());
					break;
				}
			}
			forward = "addCaseNote";			
		} else {
			forward = "displaySupervisorList";
		}
		return aMapping.findForward(forward);
	}
	
	/**
	 * Business Rule: The current program unit assignment date cannot be a future date
	 * and should be greater than last program unit assignment date.
	 * Previous PU Assign Date <= PU Assign Date <= Current Date
	 */
	private boolean validateProgramUnitAssignmentDate(ActionForm aForm, HttpServletRequest request) {
		boolean validDate = false;
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		String puAssignmentDate = caseAssignmentForm.getProgramUnitAllocationDate();
		if (puAssignmentDate == null || puAssignmentDate.trim().length() == 0) {
			sendToErrorPage(request, "error.programunitassignmentdate.invalid");
		} else {
			try {
				Date currentPUAssignmentDate = DateUtil.stringToDate(puAssignmentDate, DateUtil.DATE_FMT_1);
				List casesForReassignment = caseAssignmentForm.getActiveCases();
				ICaseAssignment activeCase = (ICaseAssignment) casesForReassignment.get(0);
				Date previousPUAssignmentDate = activeCase.getProgramUnitAssignDate();			
				Date today = new Date();
				if ((currentPUAssignmentDate.getTime() >= previousPUAssignmentDate.getTime()) &&
						(currentPUAssignmentDate.getTime() <= today.getTime())) {
					validDate = true;
				} else {
					validDate = false;
					sendToErrorPage(request, "error.programunitassignmentdate.invalid");								
				}
			} catch (ParseRuntimeException e) {
				validDate = false;				
				sendToErrorPage(request, "error.programunitassignmentdate.invalid");								
				e.printStackTrace();
			}
		}
		return validDate;
	}
}
