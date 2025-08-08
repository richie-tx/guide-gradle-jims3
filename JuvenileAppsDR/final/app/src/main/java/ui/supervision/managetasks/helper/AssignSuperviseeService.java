/*
 * Created on Nov 1, 2007
 *
 */
package ui.supervision.managetasks.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.administercaseload.GetActiveCasesByCaseEvent;
import messaging.administercaseload.GetActiveCasesEvent;
import messaging.administercaseload.GetActiveSuperviseeCasesEvent;
import messaging.administercaseload.GetCaseloadSummaryEvent;
import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.GetLightSupervisorsEvent;
import messaging.administercaseload.UpdateCaseAssignmentEvent;
import messaging.administercaseload.UpdateSuperviseeEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.reply.CaseloadSummaryResponseEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.administercompliance.UpdateUrinalysisEvent;
import messaging.administerprogramreferrals.ExitProgramReferralEvent;
import messaging.administerprogramreferrals.SaveProgRefCasenoteEvent;
import messaging.administerprogramreferrals.SaveProgramUnitReferralEvent;
import messaging.administersupervisee.reply.SuperviseeDetailResponseEvent;
import messaging.codetable.GetSupervisionCodesEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.domintf.IName;
import messaging.criminalcase.GetCaseEvent;
import messaging.criminalcase.reply.GetCaseResponseEvent;
import messaging.cscdstaffposition.GetCSCDSupervisionStaffEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.csserviceprovider.GetProgramUnitProgramEvent;
import messaging.csserviceprovider.reply.CSProgramResponseEvent;
import messaging.managetask.CreateCSTaskEvent;
import messaging.manageworkgroup.GetWorkGroupsEvent;
import messaging.manageworkgroup.reply.WorkGroupResponseEvent;
import messaging.organization.GetDivisionForAgencyEvent;
import messaging.organization.GetProgramUnitEvent;
import messaging.organization.reply.GetDivisionForAgencyResponseEvent;
import messaging.organization.reply.GetProgramUnitResponseEvent;
import messaging.supervisionorder.GetLightSupervisionOrdersEvent;
import messaging.supervisionorder.GetSupervisionOrderDetailsEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import messaging.transferobjects.OrganizationTO;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.Name;
import naming.CSAdministerProgramReferralsConstants;
import naming.CSAdministerServiceProviderConstants;
import naming.CSCDStaffPositionControllerServiceNames;
import naming.CSProgramReferralControllerServiceNames;
import naming.CaseloadConstants;
import naming.CaseloadControllerServiceNames;
import naming.CasenoteControllerServiceNames;
import naming.CodeTableControllerServiceNames;
import naming.CriminalCaseControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.SupervisionConstants;
import naming.SupervisionOrderControllerServiceNames;
import naming.WorkGroupControllerServiceNames;

import org.apache.commons.lang.StringUtils;

import ui.common.FormCollectionsHelper;
import ui.common.UIUtil;
import ui.security.SecurityUIHelper;
import ui.supervision.AssignReassignSuperviseeHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

/**
 * @author cc_rbhat
 */
public class AssignSuperviseeService {

	private static AssignSuperviseeService service;

	private static boolean serviceInitialized = false;

	/**
	 *  
	 */
	private AssignSuperviseeService() {
	}

	public static AssignSuperviseeService getInstance() {
		if (service == null) {
			synchronized (AssignSuperviseeService.class) {
				if (!serviceInitialized) {
					service = new AssignSuperviseeService();
					serviceInitialized = true;
				}
			}
		}
		return service;
	}

	public List getActiveCasesOfDefendant( String defendantId ) {
		List activeCases = null;
		
		GetActiveSuperviseeCasesEvent getActiveSuperviseeCasesEvent = new GetActiveSuperviseeCasesEvent();
		getActiveSuperviseeCasesEvent.setDefendantId(defendantId);
		getActiveSuperviseeCasesEvent.setFilteredByCaseStatus(true);

		CaseAssignmentResponseEvent caseAssignmentResponse = (CaseAssignmentResponseEvent) MessageUtil.postRequest(
				getActiveSuperviseeCasesEvent, CaseAssignmentResponseEvent.class);
		
		if ( caseAssignmentResponse != null ){
			
			activeCases = caseAssignmentResponse.getCaseAssignments();
		}
		return activeCases;
	}

	public List filterCasesByProgramUnit(List activeCases, String programUnitId) {
		List filteredList = null;
		if (activeCases != null && activeCases.size() > 0) {
			filteredList = new ArrayList();
			for (Iterator iterator = activeCases.iterator(); iterator.hasNext();) {
				ICaseAssignment activeCase = (ICaseAssignment) iterator.next();
				if (activeCase.getProgramUnitId().equals(programUnitId)) {
					filteredList.add(activeCase);
				}
			}
		} else {
			filteredList = activeCases;
		}
		return filteredList;
	}

	public List getDivisionsForAgency(String agency) {
		List divisionList = null;
		GetDivisionForAgencyEvent getDivisionForAgencyEvent = new GetDivisionForAgencyEvent();
		getDivisionForAgencyEvent.setAgencyCode(agency);
		GetDivisionForAgencyResponseEvent getDivisionForAgencyResponseEvent = (GetDivisionForAgencyResponseEvent) MessageUtil
				.postRequest(getDivisionForAgencyEvent, GetDivisionForAgencyResponseEvent.class);
		Collection divisions = getDivisionForAgencyResponseEvent.getAgencyDivisionsCollection();
		if (divisions != null) {
			divisionList = new ArrayList(divisions);
			//first sort by program unit name in each division, then sort by divisions in agency
			for (Iterator iterator = divisionList.iterator(); iterator.hasNext();) {
				OrganizationTO division = (OrganizationTO)iterator.next();
				List programUnits = new ArrayList(division.getChildOrganizations());
				sortByOrganizationUnitName(programUnits);
				division.setChildOrganizations(programUnits);
			}		
			sortByOrganizationUnitName(divisionList);			
		}
		return divisionList;
	}

	private void sortByOrganizationUnitName(List organizationUnits) {
		Collections.sort(organizationUnits, new Comparator() {
			public int compare(Object o1, Object o2) {
				if (!(o1 instanceof OrganizationTO)) 
					throw new ClassCastException();
				if (!(o2 instanceof OrganizationTO)) 
					throw new ClassCastException();
				String name1 = ((OrganizationTO) o1).getDescription();
				String name2 = ((OrganizationTO) o2).getDescription();
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
	}
	
	public List getWorkgroupsForAgency(String agencyId, String workgroupType) {
		GetWorkGroupsEvent event = 
			(GetWorkGroupsEvent) EventFactory.getInstance(WorkGroupControllerServiceNames.GETWORKGROUPS);
		event.setAgencyId(agencyId);
		event.setType(workgroupType);
		List workgroups = MessageUtil.postRequestListFilter(event, WorkGroupResponseEvent.class);

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
		}
		);				
		return workgroups;
	}

	public List getSupervisorsInDivision(String divisionId) {
		if (divisionId != null && divisionId.length() > 0) {
			ArrayList supervisorsInDivision = new ArrayList();
			String agencyId = SecurityUIHelper.getUserAgencyId();
			List divisions = getDivisionsForAgency(agencyId);
			for (Iterator iterator = divisions.iterator(); iterator.hasNext();) {
				OrganizationTO organizationTO = (OrganizationTO) iterator.next();
				if (divisionId.equals(organizationTO.getOID())) {
					Collection programUnits = organizationTO.getChildOrganizations();
					for (Iterator iter = programUnits.iterator(); iter.hasNext();) {
						OrganizationTO programUnit = (OrganizationTO) iter.next();
						String programUnitId = programUnit.getOID();
						List supervisors = getSupervisorsForProgramUnits(programUnitId);
						supervisorsInDivision.addAll(supervisors);
					}
				}
			}
			Collections.sort(supervisorsInDivision, new Comparator() {
				public int compare (Object o1, Object o2) {
					if (!(o1 instanceof CSCDSupervisionStaffResponseEvent)) 
						throw new ClassCastException();
					if (!(o2 instanceof CSCDSupervisionStaffResponseEvent)) 
						throw new ClassCastException();
					Name assignedName1 = ((CSCDSupervisionStaffResponseEvent) o1).getAssignedName();
					Name assignedName2 = ((CSCDSupervisionStaffResponseEvent) o2).getAssignedName();
					String formattedName1 = "";
					String formattedName2 = "";
					if (assignedName1 != null) {
						formattedName1 = assignedName1.getFormattedName();
					} 
					if (assignedName2 != null) {
						formattedName2 = assignedName2.getFormattedName();
					}
					return formattedName1.compareTo(formattedName2);					
				}
				public boolean equals(Object o1) {
					if ((o1 instanceof Comparator) && (this == o1)) 
						return true;
					else
						return false;
				}
			
			});
			return supervisorsInDivision;					
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param programUnitId
	 * @return
	 */
	public OrganizationTO getProgramUnitDetails(String programUnitId) {
		OrganizationTO organization = null;
		GetProgramUnitEvent getProgramUnitEvent = new GetProgramUnitEvent();
		getProgramUnitEvent.setProgramUnitId(programUnitId);
		GetProgramUnitResponseEvent getProgramUnitResponseEvent = (GetProgramUnitResponseEvent) MessageUtil
				.postRequest(getProgramUnitEvent, GetProgramUnitResponseEvent.class);
		organization = getProgramUnitResponseEvent.getOrganizationTO();
		return organization;
	}

	public List getSupervisorsForProgramUnits(String programUnitId) {
		List supervisors = new ArrayList();

		GetCSCDSupervisionStaffEvent requestEvent = (GetCSCDSupervisionStaffEvent) EventFactory
				.getInstance(CSCDStaffPositionControllerServiceNames.GETCSCDSUPERVISIONSTAFF);

		requestEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
		requestEvent.setProgramUnitId(programUnitId);
		requestEvent.setStatusId( "A" );
		List supervisionStaff = MessageUtil
				.postRequestListFilter(requestEvent, CSCDSupervisionStaffResponseEvent.class);

		for (Iterator iterator = supervisionStaff.iterator(); iterator.hasNext();) {
			CSCDSupervisionStaffResponseEvent staff = (CSCDSupervisionStaffResponseEvent) iterator.next();
			String staffPositionTypeId = staff.getPositionTypeId();
			if ((PDCodeTableConstants.STAFF_POSITION_TYPE_SUPERVISOR.equals(staffPositionTypeId))
					|| (PDCodeTableConstants.STAFF_POSITION_TYPE_ASSISTANTSUP.equals(staffPositionTypeId))) {
				supervisors.add(staff);
				if (staff.getAssignedName() == null || staff.getAssignedName().getFormattedName().length() == 0) {
					staff.getAssignedName().setLastName("No Officer Assigned");
				}
			}
		}
		Collections.sort(supervisors, new Comparator() {
			public int compare (Object o1, Object o2) {
				if (!(o1 instanceof CSCDSupervisionStaffResponseEvent)) 
					throw new ClassCastException();
				if (!(o2 instanceof CSCDSupervisionStaffResponseEvent)) 
					throw new ClassCastException();
				Name assignedName1 = ((CSCDSupervisionStaffResponseEvent) o1).getAssignedName();
				Name assignedName2 = ((CSCDSupervisionStaffResponseEvent) o2).getAssignedName();
				String formattedName1 = "";
				String formattedName2 = "";
				if (assignedName1 != null) {
					formattedName1 = assignedName1.getFormattedName();
				} 
				if (assignedName2 != null) {
					formattedName2 = assignedName2.getFormattedName();
				}
				return formattedName1.compareTo(formattedName2);					
			}
			public boolean equals(Object o1) {
				if ((o1 instanceof Comparator) && (this == o1)) 
					return true;
				else
					return false;
			}
		
		});
		return supervisors;
	}

	/**
	 * 
	 * @param organizationId
	 * @return
	 */
	public List getLightSupervisorsForOrg( String orgId ){
		
		
	    List supervisors = new ArrayList();
		
	    	    	
	    	GetLightSupervisorsEvent gEvent = new GetLightSupervisorsEvent();
			gEvent.setDivisionId( orgId );
			gEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
			
			supervisors = MessageUtil.postRequestListFilter(gEvent, CSCDSupervisionStaffResponseEvent.class);
		
			return supervisors;
	}
	
	public List getOfficersUnderSupervisor(String supervisorPositionId) {
		List officersUnderSupervisor = null;
		CSCDSupervisionStaffResponseEvent supervisor = getCSCDStaff(supervisorPositionId);
		if (supervisor != null) {
			officersUnderSupervisor = new ArrayList();
			Collection subordinates = supervisor.getChildren(); 
			if (subordinates != null) {
				for (Iterator iterator = subordinates.iterator(); iterator.hasNext();) {
					CSCDSupervisionStaffResponseEvent subordinate = (CSCDSupervisionStaffResponseEvent) iterator.next();
					String subordinatePositionTypeId = subordinate.getPositionTypeId();
					if (subordinatePositionTypeId.equals(PDCodeTableConstants.STAFF_POSITION_TYPE_OFFICER)) {
						officersUnderSupervisor.add(subordinate);
					}
				}							
			}
			Collections.sort(officersUnderSupervisor, new Comparator() {
				public int compare (Object o1, Object o2) {
					if (!(o1 instanceof CSCDSupervisionStaffResponseEvent)) 
						throw new ClassCastException();
					if (!(o2 instanceof CSCDSupervisionStaffResponseEvent)) 
						throw new ClassCastException();
					Name assignedName1 = ((CSCDSupervisionStaffResponseEvent) o1).getAssignedName();
					Name assignedName2 = ((CSCDSupervisionStaffResponseEvent) o2).getAssignedName();
					String formattedName1 = "";
					String formattedName2 = "";
					if (assignedName1 != null) {
						formattedName1 = assignedName1.getFormattedName();
					} 
					if (assignedName2 != null) {
						formattedName2 = assignedName2.getFormattedName();
					}
					return formattedName1.compareTo(formattedName2);					
				}
				public boolean equals(Object o1) {
					if ((o1 instanceof Comparator) && (this == o1)) 
						return true;
					else
						return false;
				}
			
			});
		}
		return officersUnderSupervisor;		
	}


	public List getOfficerCaseload(String supervisorPositionId) {
		List caseload = new ArrayList();
		GetCaseloadSummaryEvent getCaseloadSummaryEvent = new GetCaseloadSummaryEvent();
		getCaseloadSummaryEvent.setSupervisorPositionId(supervisorPositionId);
		getCaseloadSummaryEvent.setOfficerPositionId(supervisorPositionId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(getCaseloadSummaryEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		CaseloadSummaryResponseEvent caseloadSummaryResponse = (CaseloadSummaryResponseEvent) MessageUtil
				.filterComposite(response, CaseloadSummaryResponseEvent.class);
		if (caseloadSummaryResponse != null) {
			caseload = caseloadSummaryResponse.getCaseloads();
		}
		return caseload;
	}

	/**
	 * Creates the tasks to the assigned workgroup for
	 * the Allocate Supervisee to Supervisor flow
	 * @param caseAssignmentForm
	 */
	public void assignSuperviseeToProgramUnit(CaseAssignmentForm caseAssignmentForm) {
		String supervisionOrderId = caseAssignmentForm.getCaseToAcknowledge().getSupervisionOrderId();
		SupervisionOrderDetailResponseEvent supervisionOrderDetailResponse = getSupervisionOrderDetails(supervisionOrderId);
		String supervisionPeriodId = supervisionOrderDetailResponse.getSupervisionPeriodId();

		String caseState = CaseloadConstants.PROGRAM_UNIT_ASSIGNED;
		String supervisionStyle = CaseloadConstants.SUPERVISION_STYLE_DIRECT;
		acknowledgePaperFile(caseAssignmentForm, caseState, supervisionStyle);
		updateAutoTextCaseNote(caseAssignmentForm, supervisionPeriodId);
		updatePrefillCaseNote(caseAssignmentForm, supervisionOrderId, supervisionPeriodId);
		// Close previous task and check for Program Unit Change
		AssignReassignSuperviseeHelper myHelper = AssignReassignSuperviseeHelper.getInstance();
		myHelper.closePreviousTask( caseAssignmentForm.getTaskId() );
		myHelper.checkProgramUnitChange( caseAssignmentForm );
		
		createTaskForWorkgroup( caseAssignmentForm );
		String superviseeUpdateType = CaseloadConstants.SUPERVISEE_ASSIGNED_TO_PU;
		caseAssignmentForm.setTaskProcessed( true );
		updateSuperviseeDetails(caseAssignmentForm, superviseeUpdateType);		
	}

	/**
	 *  Creates the tasks for Assign Supervisee to Officer
	 * @param caseAssignmentForm
	 */
	public void allocateSuperviseeToSupervisor(CaseAssignmentForm caseAssignmentForm) {
		String supervisionOrderId = caseAssignmentForm.getCaseToAcknowledge().getSupervisionOrderId();
		SupervisionOrderDetailResponseEvent supervisionOrderDetailResponse = getSupervisionOrderDetails(supervisionOrderId);
		String supervisionPeriodId = supervisionOrderDetailResponse.getSupervisionPeriodId();

		String caseState = CaseloadConstants.SUPERVISOR_ALLOCATED;
		String supervisionStyle = "";
		acknowledgePaperFile(caseAssignmentForm, caseState, supervisionStyle);
		updatePrefillCaseNote(caseAssignmentForm, supervisionOrderId, supervisionPeriodId);
		AssignReassignSuperviseeHelper.getInstance().closePreviousTask( caseAssignmentForm.getTaskId() );
		String pageFlowScenario = CaseloadConstants.ASSIGN_OFFICER_PAGEFLOW;
		createTaskForOfficer( caseAssignmentForm, pageFlowScenario );
		caseAssignmentForm.setTaskProcessed( true );
		String superviseeUpdateType = CaseloadConstants.SUPERVISEE_ALLOCATE_TO_SUPERVISOR;
		updateSuperviseeDetails(caseAssignmentForm, superviseeUpdateType);
		//checkProgramUnitChange(caseAssignmentForm); RRY Not sure this is needed since it is already done at the PU level
	}

	public void assignSuperviseeToOfficer(CaseAssignmentForm caseAssignmentForm, SuperviseeDetailResponseEvent superviseeDetailResponseEvent) {
		// #76765 - Only write urinalysis record on NEW Original where no assignment to officer has occurred before (get data before any changes)		
		boolean isValidCscdUrinalysisRecord = isValidUrinalysisRecordForCscd(caseAssignmentForm);
		String supervisionOrderId = caseAssignmentForm.getCaseToAcknowledge().getSupervisionOrderId();
		SupervisionOrderDetailResponseEvent supervisionOrderDetailResponse = getSupervisionOrderDetails(supervisionOrderId);
		String supervisionPeriodId = supervisionOrderDetailResponse.getSupervisionPeriodId();

		String caseState = CaseloadConstants.OFFICER_ASSIGNED;
		String supervisionStyle = "";
		acknowledgePaperFile(caseAssignmentForm, caseState, supervisionStyle);
		updatePrefillCaseNote(caseAssignmentForm, supervisionOrderId, supervisionPeriodId);
		AssignReassignSuperviseeHelper helper = AssignReassignSuperviseeHelper.getInstance();
		helper.closePreviousTask( caseAssignmentForm.getTaskId() );
		
		String pageFlowScenario = CaseloadConstants.OFFICER_ACCEPT_PAGEFLOW;
		createTaskToOfficerInfo( caseAssignmentForm, pageFlowScenario );
		caseAssignmentForm.setTaskProcessed( true );
		
		String officerPositionId = caseAssignmentForm.getOfficerPositionId();
		String superviseeUpdateType = CaseloadConstants.SUPERVISEE_ASSIGNED_TO_STAFF;
		// check if clo then last case
		if ( "CLO".equalsIgnoreCase( helper.findUserPosition( officerPositionId ))){
			
				if ( helper.isLastCaseAssigned( caseAssignmentForm )){
					
					updateSuperviseeDetails( caseAssignmentForm, superviseeUpdateType);
					if(isValidCscdUrinalysisRecord){
						insertCscdUrinalysisRecord(caseAssignmentForm, superviseeDetailResponseEvent);
					}
					
				}else {
					
					helper.recalculateWorkloadCredit( caseAssignmentForm );
					updateSuperviseeDetails( caseAssignmentForm, superviseeUpdateType);
					if(isValidCscdUrinalysisRecord){
						insertCscdUrinalysisRecord(caseAssignmentForm, superviseeDetailResponseEvent);
					}
				}
		}else {		
							
			updateSuperviseeDetails( caseAssignmentForm, superviseeUpdateType);
			if(isValidCscdUrinalysisRecord){
				insertCscdUrinalysisRecord(caseAssignmentForm, superviseeDetailResponseEvent);
			}
		}
	}
	
	public void returnAssignmentToWorkgroup(CaseAssignmentForm caseAssignmentForm) {
		ICaseAssignment activeCase = caseAssignmentForm.getCaseToAcknowledge();
		String programUnitAssignmentDate = DateUtil.dateToString(activeCase.getProgramUnitAssignDate(), DateUtil.DATE_FMT_1); ;
		caseAssignmentForm.setProgramUnitAllocationDate(programUnitAssignmentDate);
		String caseState = CaseloadConstants.PROGRAM_UNIT_ASSIGNED;
		String supervisionStyle = CaseloadConstants.SUPERVISION_STYLE_DIRECT;
		acknowledgePaperFile(caseAssignmentForm, caseState, supervisionStyle);
		AssignReassignSuperviseeHelper.getInstance().closePreviousTask( caseAssignmentForm.getTaskId() );	
		createReturnCsTask( caseAssignmentForm );
	}

	public void superviseeAcceptedByOfficer(CaseAssignmentForm caseAssignmentForm) {
		String supervisionOrderId = caseAssignmentForm.getCaseToAcknowledge().getSupervisionOrderId();
		SupervisionOrderDetailResponseEvent supervisionOrderDetailResponse = getSupervisionOrderDetails(supervisionOrderId);
		String supervisionPeriodId = supervisionOrderDetailResponse.getSupervisionPeriodId();

		String caseState = CaseloadConstants.OFFICER_ACKNOWLEDGED;
		String supervisionStyle = "";
		
		acknowledgePaperFile(caseAssignmentForm, caseState, supervisionStyle);
		updatePrefillCaseNote(caseAssignmentForm, supervisionOrderId, supervisionPeriodId);
		AssignReassignSuperviseeHelper.getInstance().closePreviousTask( caseAssignmentForm.getTaskId() );
	}

	/**
	 * 
	 * @param caseAssignmentForm
	 * @param caseState
	 * @param supervisionStyle
	 */
	public void acknowledgePaperFile(CaseAssignmentForm caseAssignmentForm, String caseState, 
			String supervisionStyle) {
		ICaseAssignment activeCase = caseAssignmentForm.getCaseToAcknowledge();
		if (caseState.equals(CaseloadConstants.PROGRAM_UNIT_ASSIGNED)) {
			String programUnitId = caseAssignmentForm.getProgramUnitId();
			Date programUnitAssignDate = DateUtil.stringToDate(caseAssignmentForm.getProgramUnitAllocationDate(), DateUtil.DATE_FMT_1);
			String allocatedStaffPositionId = null;
			String assignedStaffPositionId = null;
			Date officerAssignDate = null;

			if (programUnitId != null && programUnitId.trim().length() != 0) {
				//Program unit id corresponds to a numeric field in database table.
				//If it's value is an empty string the mojo persistence layer throws an exception. 
				activeCase.setProgramUnitId(programUnitId);
			}
			activeCase.setCaseAssignmentState(caseState);
			activeCase.setProgramUnitAssignDate(programUnitAssignDate);
			activeCase.setAllocatedStaffPositionId(allocatedStaffPositionId);
			activeCase.setAssignedStaffPositionId(assignedStaffPositionId);
			activeCase.setOfficerAssignDate(officerAssignDate);
			
			String courtNumber = caseAssignmentForm.getCourtNumber();
			if (courtNumber != null && !courtNumber.equals("")) {
				activeCase.setCourtId(courtNumber);
			}
			activeCase.setAcknowledgeStatusCode(CaseloadConstants.ACKNOWLEDGMENT_STATUS_ASSUMED);
			activeCase.setSupervisionStyleCode(supervisionStyle);			
		} else if (caseState.equals(CaseloadConstants.SUPERVISOR_ALLOCATED)) {
			String programUnitId = caseAssignmentForm.getProgramUnitId();
			Date programUnitAssignDate = DateUtil.stringToDate(caseAssignmentForm.getProgramUnitAllocationDate(), DateUtil.DATE_FMT_1);			
			String allocatedStaffPositionId = caseAssignmentForm.getSupervisorPositionId();;
			Date supervisorAllocationDate = DateUtil.getCurrentDate();	
			String assignedStaffPositionId = null;
			Date officerAssignDate = null;			

			if (programUnitId != null && programUnitId.trim().length() != 0) {
				//Program unit id corresponds to a numeric field in database table.
				//If it's value is an empty string the mojo persistence layer throws an exception. 
				activeCase.setProgramUnitId(programUnitId);
			}
			activeCase.setProgramUnitAssignDate(programUnitAssignDate);			
			activeCase.setCaseAssignmentState(caseState);
			activeCase.setAllocatedStaffPositionId(allocatedStaffPositionId);
			activeCase.setSupervisorAllocationDate(supervisorAllocationDate);
			activeCase.setAssignedStaffPositionId(assignedStaffPositionId);
			activeCase.setOfficerAssignDate(officerAssignDate);
		} else if (caseState.equals(CaseloadConstants.OFFICER_ASSIGNED)) {
			String assignedStaffPositionId = caseAssignmentForm.getOfficerPositionId();
			Date officerAssignDate = DateUtil.stringToDate(caseAssignmentForm.getOfficerAssignmentDate(), DateUtil.DATE_FMT_1);				

			activeCase.setCaseAssignmentState(caseState);
			activeCase.setAssignedStaffPositionId(assignedStaffPositionId);
			activeCase.setOfficerAssignDate(officerAssignDate);
		} else if (caseState.equals(CaseloadConstants.OFFICER_ACKNOWLEDGED)) {
			activeCase.setCaseAssignmentState(caseState);			
		}

		LightCSCDStaffResponseEvent cscdStaff = getCSCDStaffPosition();
		String ackStaffPositionId = "";
		if (cscdStaff != null) {
			ackStaffPositionId = cscdStaff.getStaffPositionId();			
		}
		String ackUserLogonId = SecurityUIHelper.getLogonId();
		Date acknowledgeDate = DateUtil.getCurrentDate();
		
		activeCase.setAcknowledgeUserId(ackUserLogonId);
		activeCase.setAcknowledgePositionId(ackStaffPositionId);
		activeCase.setAcknowledgeDate(acknowledgeDate);

		UpdateCaseAssignmentEvent updateCaseAssignmentEvent = (UpdateCaseAssignmentEvent) EventFactory
				.getInstance(CaseloadControllerServiceNames.UPDATECASEASSIGNMENT);
		updateCaseAssignmentEvent.setCaseAssignment(activeCase);
		CaseAssignmentResponseEvent caseAssignmentResponseEvent = (CaseAssignmentResponseEvent) MessageUtil
				.postRequest(updateCaseAssignmentEvent, CaseAssignmentResponseEvent.class);

		if (activeCase.getCaseAssignmentId() == null || activeCase.getCaseAssignmentId().length() == 0) {
			List caseAssignmentList = caseAssignmentResponseEvent.getCaseAssignments();
			ICaseAssignment updatedCaseAssignment = (ICaseAssignment) caseAssignmentList.get(0);
			String caseAssignmentId = updatedCaseAssignment.getCaseAssignmentId();

			activeCase.setCaseAssignmentId(caseAssignmentId);
		}
	}

	public LightCSCDStaffResponseEvent getCSCDStaffPosition() {
		GetLightCSCDStaffForUserEvent ev = new GetLightCSCDStaffForUserEvent();
		ev.setLogonId(SecurityUIHelper.getLogonId());		
		return (LightCSCDStaffResponseEvent) MessageUtil.postRequest(ev, LightCSCDStaffResponseEvent.class);
	} 
	

	public CSCDSupervisionStaffResponseEvent getCSCDStaff(String staffPositionId) {
		CSCDSupervisionStaffResponseEvent responseEvent = null;
		if (staffPositionId != null && staffPositionId.length() != 0) {
			GetCSCDSupervisionStaffEvent staffEvent = (GetCSCDSupervisionStaffEvent) EventFactory
							.getInstance(CSCDStaffPositionControllerServiceNames.GETCSCDSUPERVISIONSTAFF);			
			staffEvent.setStaffPositionId(staffPositionId);			
			responseEvent = (CSCDSupervisionStaffResponseEvent) MessageUtil.postRequest(
					staffEvent, CSCDSupervisionStaffResponseEvent.class);			
		}
		return responseEvent;
	} 
	
	public CSCDSupervisionStaffResponseEvent getCSCDStaff() {
		String logonId = SecurityUIHelper.getLogonId();

		GetCSCDSupervisionStaffEvent staffEvent = (GetCSCDSupervisionStaffEvent) EventFactory
				.getInstance(CSCDStaffPositionControllerServiceNames.GETCSCDSUPERVISIONSTAFF);

		staffEvent.setStaffLogonId(logonId);

		CSCDSupervisionStaffResponseEvent responseEvent = (CSCDSupervisionStaffResponseEvent) MessageUtil.postRequest(
				staffEvent, CSCDSupervisionStaffResponseEvent.class);

		return responseEvent;
	} 
	
	public LightCSCDStaffResponseEvent getCSCDStaffPosition(String staffPositionId) {
		GetLightCSCDStaffForUserEvent ev = new GetLightCSCDStaffForUserEvent();
		ev.setStaffPositionId(staffPositionId);		
		ev.setOfficerNameNeeded( true );
		return (LightCSCDStaffResponseEvent) MessageUtil.postRequest(ev, LightCSCDStaffResponseEvent.class);
	} 

	private void updateAutoTextCaseNote(CaseAssignmentForm caseAssignmentForm, String supervisionPeriodId) {
		List activeCases = caseAssignmentForm.getActiveCases();
		if (activeCases != null && activeCases.size() > 0) {
			StringBuffer sb = new StringBuffer();
			String programUnitName = "";
			String assignedStaffPositionId = "";
			int size = activeCases.size();
			for ( int s = 0; s < size; s++ ) {
				CaseAssignmentTO activeCase = (CaseAssignmentTO) activeCases.get(s);
				//Only directly supervised cases are included in case notes.
				if (activeCase.getSupervisionStyleCode().equalsIgnoreCase(CaseloadConstants.SUPERVISION_STYLE_DIRECT)) {
					if ( s == size-1 ) {
						sb.append( " and " );
					} 
					sb.append("CASE ");
					sb.append( activeCase.getCriminalCaseId() );
					sb.append( ", CRT " );
					sb.append( activeCase.getCourtId() );
					if ( s < size-2 ) {
						sb.append( ", " );
					}
					//all cases assigned to same program unit/officer.
					programUnitName = activeCase.getProgramUnitName();
					assignedStaffPositionId = activeCase.getAssignedStaffPositionId();
				}
			}
			if (sb.length() != 0) {
				StringBuffer caseNoteText = new StringBuffer();
				caseNoteText.append( "Other cases under supervision: " );
				caseNoteText.append( sb.deleteCharAt( sb.lastIndexOf(",") ).toString());
				caseNoteText.append( " by Program Unit: " );
				caseNoteText.append( programUnitName );
				caseNoteText.append( " Supervising Officer: " );
				 
				String supervisingOfficerName = "No Officer Assigned";
				LightCSCDStaffResponseEvent response = getCSCDStaffPosition(assignedStaffPositionId);
				if (response != null) {
					supervisingOfficerName = response.getOfficerName();
				}
				caseNoteText.append( supervisingOfficerName );
				
				UpdateCasenoteEvent updateCaseNoteEvent = (UpdateCasenoteEvent) EventFactory
						.getInstance(CasenoteControllerServiceNames.UPDATECASENOTE);
				updateCaseNoteEvent.setNotes(caseNoteText.toString());
				updateCaseNoteEvent.setUserID(SecurityUIHelper.getLogonId());

				String assignmentCodeId = getCasenoteSubjectCodeId();
				List subjects = new ArrayList();
				subjects.add(assignmentCodeId);
				updateCaseNoteEvent.setSubjects(subjects);
				
				updateCaseNoteEvent.setContactMethodId(CaseloadConstants.NONE_OTHER_CONTACTMETHOD);
				updateCaseNoteEvent.setContextType(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
				updateCaseNoteEvent.setEntryDate(new Date());
				updateCaseNoteEvent.setSupervisionPeriodId(supervisionPeriodId);
				updateCaseNoteEvent.setSaveAsDraft(false);
				updateCaseNoteEvent.setHowGeneratedId(SupervisionConstants.HOW_GENERATED_BY_SYSTEM);
				updateCaseNoteEvent.setSuperviseeId(caseAssignmentForm.getDefendantId());
				
				MessageUtil.postRequest(updateCaseNoteEvent);
				caseNoteText = new StringBuffer();
				supervisingOfficerName = null;
				response = null;	
				updateCaseNoteEvent = null;
				assignmentCodeId = null;
				subjects = new ArrayList();
			}
			sb = new StringBuffer();
			programUnitName = null;
			assignedStaffPositionId = null;
		}
		activeCases = null;
	}

	public void updatePrefillCaseNote(CaseAssignmentForm caseAssignmentForm, String supervisionOrderId,
			String supervisionPeriodId) {
		//update case notes
		UpdateCasenoteEvent updateCaseNoteEvent = (UpdateCasenoteEvent) EventFactory
				.getInstance(CasenoteControllerServiceNames.UPDATECASENOTE);

		updateCaseNoteEvent.setNotes(caseAssignmentForm.getCasenoteText());
		updateCaseNoteEvent.setUserID(SecurityUIHelper.getLogonId());

		String assignmentCodeId = getCasenoteSubjectCodeId();
		List subjects = new ArrayList();
		subjects.add(assignmentCodeId);

		updateCaseNoteEvent.setSubjects(subjects);
		updateCaseNoteEvent.setContactMethodId(CaseloadConstants.NONE_OTHER_CONTACTMETHOD);
		updateCaseNoteEvent.setContextType(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
		updateCaseNoteEvent.setEntryDate(new Date());
		updateCaseNoteEvent.setSupervisionPeriodId(supervisionPeriodId);
		updateCaseNoteEvent.setSaveAsDraft(false);
		updateCaseNoteEvent.setHowGeneratedId(SupervisionConstants.HOW_GENERATED_CREATED_BY);
		updateCaseNoteEvent.setSuperviseeId(caseAssignmentForm.getDefendantId());
		if (supervisionOrderId != null && !supervisionOrderId.equals("")) {
			updateCaseNoteEvent.setSupervisionOrderId(supervisionOrderId);			
		}

		MessageUtil.postRequest(updateCaseNoteEvent);
		updateCaseNoteEvent = null;
		assignmentCodeId = null;
		subjects = new ArrayList();
	}

	private String getCasenoteSubjectCodeId() {
		GetSupervisionCodesEvent request = (GetSupervisionCodesEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.GETSUPERVISIONCODES);
		request.setCodeTableName(CaseloadConstants.CASENOTE_SUBJECT_CODE_TABLE_NAME);
		request.setCode(CaseloadConstants.ASSIGNMENT_SUBJECT_CD);
		request.setAgencyId(SecurityUIHelper.getUserAgencyId());

		CodeResponseEvent codeResponse = (CodeResponseEvent) MessageUtil.postRequest(request, CodeResponseEvent.class);

		return codeResponse.getCodeId();
	}

	/**
	 *  Allocate Supervisee to Supervisor CSTASK
	 * @param caseAssignmentForm
	 * @param ntTaskId
	 */
	public void createTaskForWorkgroup( CaseAssignmentForm caseAssignmentForm ) {
		
		StringBuffer padCrt = new StringBuffer( caseAssignmentForm.getCaseToAcknowledge().getCourtId());
		while ( padCrt.length() < 3 ){
    		padCrt.insert( 0, "0" );
    	}		
		//due date
		Calendar calendar = Calendar.getInstance();
		// Get the weekday and print it 
		int weekday = calendar.get(Calendar.DAY_OF_WEEK); 
		
			switch ( weekday) {
				
			case 5:
				calendar.add(Calendar.DATE, 4);
				break;
				
			case 6:
				calendar.add(Calendar.DATE, 4);
				break;
				
			default:
				calendar.add(Calendar.DATE, 2);
				break;
				
			}			
			
		StringBuffer subject2 = new StringBuffer();
		subject2.append( "Allocate supervisee to Supervisor in " );
		subject2.append( caseAssignmentForm.getProgramUnitName() );
		
		CreateCSTaskEvent createCSTaskEvent = new CreateCSTaskEvent();
		createCSTaskEvent.setCaseAssignId( caseAssignmentForm.getCaseToAcknowledge().getCaseAssignmentId() );
		createCSTaskEvent.setSupervisionOrderId( caseAssignmentForm.getCaseToAcknowledge().getSupervisionOrderId() );
		createCSTaskEvent.setCourtId( padCrt.toString() );
		createCSTaskEvent.setDefendantId(caseAssignmentForm.getDefendantId());
		createCSTaskEvent.setWorkGroupId( caseAssignmentForm.getWorkGroupId() );
		createCSTaskEvent.setDueDate( calendar.getTime());
		createCSTaskEvent.setCriminalCaseId( caseAssignmentForm.getCaseToAcknowledge().getCriminalCaseId() );
		createCSTaskEvent.setScenario( CaseloadConstants.ALLOCATE_SUPERVISOR_PAGEFLOW );
		createCSTaskEvent.setSuperviseeName( caseAssignmentForm.getSuperviseeNameStr() );
		createCSTaskEvent.setSubject2( subject2.toString() );
		createCSTaskEvent.setTaskSubject( "New Order Allocate to Supervisor" );
		createCSTaskEvent.setTopic( CaseloadConstants.CSTASK_TOPIC_ALLOCATE_SUPERVISEE_TO_SUP );
		
		StringBuffer taskTextBuffer = new StringBuffer();
		taskTextBuffer.append( caseAssignmentForm.getSuperviseeName().getFormattedName() );
		taskTextBuffer.append( ", SPN " );
		taskTextBuffer.append( caseAssignmentForm.getDefendantId() );
		taskTextBuffer.append( ", CASE " );
		taskTextBuffer.append( caseAssignmentForm.getCaseToAcknowledge().getCriminalCaseId() );
		taskTextBuffer.append( ", CRT " );
		taskTextBuffer.append( padCrt.toString() );
		taskTextBuffer.append( "." );

		String assignmentReturnReason = caseAssignmentForm.getAssignmentReturnReason();
		StringBuffer ctText = new StringBuffer();
		if(assignmentReturnReason != null && assignmentReturnReason.trim().length() > 0) {
			createCSTaskEvent.setTaskSubject(CaseloadConstants.ASSIGNMENT_RETURNED);
			taskTextBuffer.append( " " );
			taskTextBuffer.append( assignmentReturnReason );			
			ctText.append( CaseloadConstants.ASSIGNMENT_RETURNED );
			ctText.append( " for " );
			ctText.append( taskTextBuffer.toString() );
		
		} else {
			createCSTaskEvent.setTaskSubject( "New Order Allocate to Supervisor");			
			ctText.append( CaseloadConstants.NEW_ORDER_FOR_SUPERVISION );
			ctText.append( " for " );
			ctText.append( taskTextBuffer.toString() );
		}
		createCSTaskEvent.setTastText( ctText.toString() );
		padCrt = null;
		subject2 = null;
		taskTextBuffer = null;
		ctText = null;
		MessageUtil.postRequest(createCSTaskEvent);
	}
	
	/**
	 *  Allocate Supervisee to Supervisor CSTASK
	 * @param caseAssignmentForm
	 * @param ntTaskId
	 */
	public void createReturnCsTask( CaseAssignmentForm caseAssignmentForm ) {
		StringBuffer padCrt = new StringBuffer( caseAssignmentForm.getCourtNumber() );
		while ( padCrt.length() < 3 ){
    		padCrt.insert( 0, "0" );
    	}
		String defendantId = caseAssignmentForm.getDefendantId();
		
		//due date
		Calendar calendar = Calendar.getInstance();
		// Get the weekday and print it 
		int weekday = calendar.get(Calendar.DAY_OF_WEEK); 
		
			switch ( weekday) {
				
			case 5:
				calendar.add(Calendar.DATE, 4);
				break;
				
			case 6:
				calendar.add(Calendar.DATE, 4);
				break;
				
			default:
				calendar.add(Calendar.DATE, 2);
				break;
				
			}

		StringBuffer subject2 = new StringBuffer();
		subject2.append( "(Re)assignment returned to " );
		subject2.append( caseAssignmentForm.getProgramUnitName() );
		
		CreateCSTaskEvent createCSTaskEvent = new CreateCSTaskEvent();
		createCSTaskEvent.setCourtId( padCrt.toString() );
		createCSTaskEvent.setDefendantId(defendantId);
		createCSTaskEvent.setWorkGroupId( caseAssignmentForm.getWorkGroupId() );
		createCSTaskEvent.setDueDate( calendar.getTime());
		createCSTaskEvent.setCriminalCaseId( caseAssignmentForm.getCaseToAcknowledge().getCriminalCaseId() );
		createCSTaskEvent.setScenario( CaseloadConstants.REALLOCATE_SUPERVISOR_PAGEFLOW );
		createCSTaskEvent.setSuperviseeName( caseAssignmentForm.getSuperviseeNameStr() );
		createCSTaskEvent.setCaseAssignId( caseAssignmentForm.getCaseToAcknowledge().getCaseAssignmentId() );
		createCSTaskEvent.setSupervisionOrderId( caseAssignmentForm.getCaseToAcknowledge().getSupervisionOrderId() );
		createCSTaskEvent.setSubject2( subject2.toString() );
		createCSTaskEvent.setTaskSubject( "Reassignment to officer" );
		
		StringBuffer taskText = new StringBuffer();
		taskText.append( CaseloadConstants.REASSIGNMENT_RETURNED );
		taskText.append( " of " );
		taskText.append( caseAssignmentForm.getSuperviseeName().getFormattedName() );
		taskText.append(  ", SPN " );
		taskText.append( defendantId );
		taskText.append(  ", CASE " );
		taskText.append( caseAssignmentForm.getCaseToAcknowledge().getCriminalCaseId() );
		taskText.append(  ", CRT " );
		taskText.append( padCrt.toString() );
		taskText.append(  "." );
		taskText.append( " Please setup any appropriate future scheduled events such as field or office visits." );
		
		createCSTaskEvent.setTastText( taskText.toString() );

		MessageUtil.postRequest(createCSTaskEvent);
		
		padCrt = null;
		defendantId = null;
		subject2 = null;
		createCSTaskEvent = null;
		taskText = null;
	}
	/**
	 * Assign supervisee to Officer CSTASK
	 * @param caseAssignmentForm
	 * @param ntTaskId
	 */
	public void createTaskForOfficer( CaseAssignmentForm caseAssignmentForm, String scenario ) {
		StringBuffer padCrt = new StringBuffer( caseAssignmentForm.getCourtNumber() );
		while ( padCrt.length() < 3 ){
    		padCrt.insert( 0, "0" );
    	}		
		String defendantId = caseAssignmentForm.getDefendantId();
		
		//due date
		Calendar calendar = Calendar.getInstance();
		int weekday = calendar.get(Calendar.DAY_OF_WEEK); 
		
			switch ( weekday) {
				
			case 5:
				calendar.add(Calendar.DATE, 4);
				break;
				
			case 6:
				calendar.add(Calendar.DATE, 4);
				break;
				
			default:
				calendar.add(Calendar.DATE, 2);
				break;
				
			}	    	
					
		StringBuffer subject2 = new StringBuffer();
		subject2.append( "Assign supervisee to officer under " );
		subject2.append( caseAssignmentForm.getStaffPositionDescription() );
		
		CreateCSTaskEvent createCSTaskEvent = new CreateCSTaskEvent();
		
		createCSTaskEvent.setCaseAssignId( caseAssignmentForm.getCaseToAcknowledge().getCaseAssignmentId() );
		createCSTaskEvent.setSupervisionOrderId( caseAssignmentForm.getCaseToAcknowledge().getSupervisionOrderId() );
		createCSTaskEvent.setCourtId( padCrt.toString() );
		createCSTaskEvent.setDefendantId(defendantId);
		createCSTaskEvent.setStaffPositionId( caseAssignmentForm.getSupervisorPositionId() );
		createCSTaskEvent.setDueDate( calendar.getTime());
		createCSTaskEvent.setCriminalCaseId( caseAssignmentForm.getCaseToAcknowledge().getCriminalCaseId() );
		createCSTaskEvent.setScenario( scenario );
		createCSTaskEvent.setSuperviseeName( caseAssignmentForm.getSuperviseeNameStr() );
		createCSTaskEvent.setSubject2( subject2.toString() );
		createCSTaskEvent.setTaskSubject( "New Order Assign to Officer" );
		createCSTaskEvent.setTopic( CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_PU );
		
		StringBuffer taskText = new StringBuffer();
		taskText.append( CaseloadConstants.NEW_ORDER_FOR_SUPERVISION );
		taskText.append( " for " );
		taskText.append( caseAssignmentForm.getSuperviseeName().getFormattedName() );
		taskText.append( ", SPN " );
		taskText.append( caseAssignmentForm.getDefendantId() );
		taskText.append(  ", CASE " );
		taskText.append( caseAssignmentForm.getCaseToAcknowledge().getCriminalCaseId() ); 
		taskText.append(  ", CRT " );
		taskText.append( caseAssignmentForm.getCourtNumber() );
		taskText.append(  "." );
		
		createCSTaskEvent.setTastText( taskText.toString() );

		MessageUtil.postRequest(createCSTaskEvent);
		
		padCrt = null;
		defendantId = null;
		subject2 = null;
		createCSTaskEvent = null;
		taskText = null;
	}

	/**
	 * Process Officer new case Assignment CSTASK
	 * @param caseAssignmentForm
	 * @param ntTaskId
	 */
	public void createTaskToOfficerInfo( CaseAssignmentForm caseAssignmentForm, String scenario ) {
		StringBuffer padCrt = new StringBuffer( caseAssignmentForm.getCourtNumber() );
		while ( padCrt.length() < 3 ){
    		padCrt.insert( 0, "0" );
    	}
		String defendantId = caseAssignmentForm.getDefendantId();
		
		//due date
		Calendar calendar = Calendar.getInstance();
		int weekday = calendar.get(Calendar.DAY_OF_WEEK); 
		
			switch ( weekday) {
				
			case 5:
				calendar.add(Calendar.DATE, 4);
				break;
				
			case 6:
				calendar.add(Calendar.DATE, 4);
				break;
				
			default:
				calendar.add(Calendar.DATE, 2);
				break;
				
			}

		StringBuffer subject2 = new StringBuffer();
		subject2.append( "Process new case assignment to " );
		subject2.append( caseAssignmentForm.getOfficerPositionName() );
		
		CreateCSTaskEvent createCSTaskEvent = new CreateCSTaskEvent();
		createCSTaskEvent.setCaseAssignId( caseAssignmentForm.getCaseToAcknowledge().getCaseAssignmentId() );
		createCSTaskEvent.setSupervisionOrderId( caseAssignmentForm.getCaseToAcknowledge().getSupervisionOrderId() );
		createCSTaskEvent.setCourtId( padCrt.toString() );
		createCSTaskEvent.setDefendantId(defendantId);
		createCSTaskEvent.setStaffPositionId( caseAssignmentForm.getOfficerPositionId() );
		createCSTaskEvent.setDueDate( calendar.getTime());
		createCSTaskEvent.setCriminalCaseId( caseAssignmentForm.getCaseToAcknowledge().getCriminalCaseId() );
		createCSTaskEvent.setScenario( scenario );
		createCSTaskEvent.setSuperviseeName( caseAssignmentForm.getSuperviseeNameStr() );
		createCSTaskEvent.setSubject2( subject2.toString() );
		createCSTaskEvent.setTaskSubject( "New Order - Officer Acknowledgment" );
		createCSTaskEvent.setTopic( CaseloadConstants.CSTASK_TOPIC_ALLOCATE_SUPERVISEE_TO_SUP );
		
		StringBuffer taskText = new StringBuffer();
		taskText.append( CaseloadConstants.NEW_ORDER_FOR_SUPERVISION );
		taskText.append( " for " );
		taskText.append( caseAssignmentForm.getSuperviseeName().getFormattedName() );
		taskText.append( ", SPN " );
		taskText.append( caseAssignmentForm.getDefendantId() );
		taskText.append( ", CASE " );
		taskText.append( caseAssignmentForm.getCaseToAcknowledge().getCriminalCaseId() );
		taskText.append( ", CRT " );
		taskText.append( caseAssignmentForm.getCourtNumber() );
		taskText.append( "." );
		
		createCSTaskEvent.setTastText( taskText.toString() );

		MessageUtil.postRequest(createCSTaskEvent);
		padCrt = null;
		defendantId = null;
		subject2 = null;
		taskText = null;
		createCSTaskEvent = null;
	}

	public SupervisionOrderDetailResponseEvent getSupervisionOrderDetails(String supervisionOrderId) {
		GetSupervisionOrderDetailsEvent requestEvent = new GetSupervisionOrderDetailsEvent();
		requestEvent.setSupervisionOrderId(supervisionOrderId);

		SupervisionOrderDetailResponseEvent responseEvent = (SupervisionOrderDetailResponseEvent) MessageUtil
				.postRequest(requestEvent, SupervisionOrderDetailResponseEvent.class);
		return responseEvent;
	}
	
	/**
	 * assignment used by the initial assign, close case, and reassign scenarios
	 * @param caseAssignmentForm
	 * @param superviseeUpdateType
	 */
	public void updateSuperviseeDetails(CaseAssignmentForm caseAssignmentForm, String superviseeUpdateType) {
		UpdateSuperviseeEvent requestEvent = (UpdateSuperviseeEvent) EventFactory
				.getInstance(CaseloadControllerServiceNames.UPDATESUPERVISEE);
		requestEvent.setUpdateType(superviseeUpdateType);
		requestEvent.setDefendantId(caseAssignmentForm.getDefendantId());
		requestEvent.setAssignedProgramUnitId(caseAssignmentForm.getProgramUnitId());
		Date programUnitAssignmentDate = DateUtil.stringToDate(caseAssignmentForm.getProgramUnitAllocationDate(), DateUtil.DATE_FMT_1);
		requestEvent.setProgramUnitAssignmentDate(programUnitAssignmentDate);
		requestEvent.setNewCaseToAcknowledge(caseAssignmentForm.getCaseToAcknowledge());
		requestEvent.setActiveCases(caseAssignmentForm.getActiveCases());
		String staffPositionId = caseAssignmentForm.getOfficerPositionId();
		requestEvent.setAssignedStaffPositionId(staffPositionId);
		// these 2 just for debugging - fill null fields in event
		requestEvent.setSupervisionLevelId("");
		requestEvent.setUserID("");
		MessageUtil.postRequest(requestEvent);
	}
	
	/**
	 * method to call the CSCD UA Urinalysis application to insert when a NEW ORIGINAL record is assigned for the first time
	 * @param caseAssignmentForm
	 * @param superviseeInfo
	 */
	public void insertCscdUrinalysisRecord(CaseAssignmentForm caseAssignmentForm, SuperviseeDetailResponseEvent superviseeDetailResponseEvent) {
		
		String fullCaseNumber = caseAssignmentForm.getCaseNum();
		String cdi="";
		String casenum="";	
		GetCaseEvent requestCaseEvent = (GetCaseEvent) EventFactory
		.getInstance(CriminalCaseControllerServiceNames.GETCASE);
		requestCaseEvent.setAgencyId("CSC");
		if(fullCaseNumber != null){
			cdi = fullCaseNumber.substring(0,3);
			casenum = fullCaseNumber.substring(3);
		}
		requestCaseEvent.setCourtDivisionId(cdi);
		requestCaseEvent.setCaseNum(casenum);
		List<GetCaseResponseEvent> criminalCases = MessageUtil.postRequestListFilter(requestCaseEvent, GetCaseResponseEvent.class);
		String fullName = null;
		for(GetCaseResponseEvent event: criminalCases){
			if( (event.getCdi() + event.getCaseNum()).equalsIgnoreCase(caseAssignmentForm.getCaseNum())){
				fullName = event.getDefendantName();
			}
		}		
		UpdateUrinalysisEvent urinalysisEvent = new UpdateUrinalysisEvent();
		urinalysisEvent.setCrt(caseAssignmentForm.getCourtNumber());
		urinalysisEvent.setDefendantId(caseAssignmentForm.getDefendantId());
		urinalysisEvent.setDob(DateUtil.dateToString(superviseeDetailResponseEvent.getDateOfBirth(),"yyyy-MM-dd"));
		if(fullName != null){
			IName aNameBean = (IName)UIUtil.getNameFromString(fullName);
			urinalysisEvent.setfName(aNameBean.getFirstName());
			urinalysisEvent.setmName(aNameBean.getMiddleName());
			urinalysisEvent.setlName(aNameBean.getLastName());
		}else{
			urinalysisEvent.setfName(caseAssignmentForm.getSuperviseeName().getFirstName());
			urinalysisEvent.setlName(caseAssignmentForm.getSuperviseeName().getLastName());
		}
		urinalysisEvent.setPoi(caseAssignmentForm.getOfficerPOI());
		urinalysisEvent.setRace(FormCollectionsHelper.getInstance().getRaceDescr(superviseeDetailResponseEvent.getRaceId()));
		urinalysisEvent.setSex(FormCollectionsHelper.getInstance().getSexDescr(superviseeDetailResponseEvent.getSexId()));
		MessageUtil.postRequest(urinalysisEvent);		
	}

	/**
	 * 
	 * @param caseAssignmentForm
	 */
	public void initiateProgramUnitReferral ( CaseAssignmentForm caseAssignmentForm ){
		
		   char stateRptCd = 'X'; // Default outside of range
	       String stateRptCdString = caseAssignmentForm.getStateReportingCode();
	       if (stateRptCdString != null  && !stateRptCdString.equals( "" )){
	    	   
	    	   stateRptCd = stateRptCdString.charAt( 0 );
	       }
	       
			switch ( stateRptCd ){
			
			case 'Y':
				// Initiate and submit referral
				initiateProgramReferral( caseAssignmentForm, true );
				break;
			
			case 'X': //Do nothing
				// If Transferring to a non-state reported program Unit
				closeExistingProgramUnitReferral( caseAssignmentForm );
				break;
				
			default: // Handles N and W
				// just initiate referral
				initiateProgramReferral( caseAssignmentForm, false );
				break;
			}
		}
	
	/**
	 * 
	 * @param caseAssignmentForm
	 */
	private void initiateProgramReferral( CaseAssignmentForm caseAssignmentForm, boolean subRef ){
		
		
		if ( caseAssignmentForm.isProgramUnitRef() ){
			
			GetProgramUnitProgramEvent getEvent = new GetProgramUnitProgramEvent();
			
			// Get the associated program name for PU
			getEvent.setProgramUnitId( caseAssignmentForm.getProgramUnitId() );
			getEvent.setProgramStatus( CSAdministerServiceProviderConstants.ACTIVE_PROG_STATUS );
			
			CSProgramResponseEvent 	pup_program = ( CSProgramResponseEvent )
									MessageUtil.postRequest( getEvent, 	CSProgramResponseEvent.class);
			
		
			SaveProgramUnitReferralEvent save_event = ( SaveProgramUnitReferralEvent )EventFactory.
										getInstance(CSProgramReferralControllerServiceNames.SAVEPROGRAMUNITREFERRAL );
			
			if ( pup_program.getLocationIds() != null ){
				
				save_event.setReferralTypeCode( pup_program.getReferralTypeCode() );
				List programLocIds = (List) pup_program.getProgramLocationIds();
				
				if ( programLocIds.size() > 0 ){
					
					save_event.setLocationId( String.valueOf( programLocIds.get( 0 )));
				}
	
				save_event.setCriminalCaseId( caseAssignmentForm.getCaseNum() );
				save_event.setDefendantId( caseAssignmentForm.getDefendantId() );
				save_event.setSubmitReferral( subRef );
				save_event.setProgramUnitReferral( true );
				save_event.setPlacementReasonCd( caseAssignmentForm.getReasonForPlacementId());
				save_event.setProgramBeginDate( DateUtil.stringToDate( caseAssignmentForm.getProgramBeginDateAsStr(), DateUtil.DATE_FMT_1 ));
				save_event.setReferralDate( DateUtil.stringToDate( caseAssignmentForm.getReferralDateAsStr(), DateUtil.DATE_FMT_1 ));
				
				MessageUtil.postRequest( save_event );
			}
		}
		
		if ( caseAssignmentForm.isCloseProgramUnitRef() ){
			
			this.closeExistingProgramUnitReferral( caseAssignmentForm );
		}
		
	}
	
	/**
	 * 
	 * @param caseAssignmentForm
	 */
	private static void saveCasenoteForExitReferral( CaseAssignmentForm caseAssignmentForm )
 	{
 		SaveProgRefCasenoteEvent saveCasenoteEvent = new SaveProgRefCasenoteEvent();
 		
 		saveCasenoteEvent.setProgramReferralId( caseAssignmentForm.getCsProgReferralToCloseId() );
 		
 		saveCasenoteEvent.setCasenoteSubjectCd(PDCodeTableConstants.PROGRAM_REFERRAL_SUBJECT_CD);
 		
 		saveCasenoteEvent.setCasenoteTypeId(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
 		saveCasenoteEvent.setContactMethodId(PDCodeTableConstants.CASENOTE_CONTACT_METHOD_NONE);
 		saveCasenoteEvent.setHowGeneratedId(PDCodeTableConstants.CASENOTE_SYSTEM_GENERATED_ID);
 		
 		saveCasenoteEvent.setCasenoteContext(CSAdministerProgramReferralsConstants.CASENOTE_CONTEXT_PROGRAM_REFERAL);
 		
 		StringBuffer notes = new StringBuffer();
 		notes.append( "Program Referral Exited for Case#: " );
 		notes.append( caseAssignmentForm.getCriminalCaseId() );
 		
 		notes.append( ", BeginDate: " );
 		notes.append( caseAssignmentForm.getProgramBeginDateAsStr() );
 		
 		notes.append( ", Reason for Discharge: " );
 		notes.append( caseAssignmentForm.getReasonForDischargeDesc() );
 		
 		notes.append( ", EndDate: " );
 		notes.append( caseAssignmentForm.getProgramEndDateAsStr() );
 
 		
 		saveCasenoteEvent.setCasenoteComments( notes.toString() );
 		
 		MessageUtil.postRequest( saveCasenoteEvent );
 	}
	
	/**
	 * 
	 * @param caseAssignmentForm
	 */
   private void closeExistingProgramUnitReferral( CaseAssignmentForm caseAssignmentForm ){
		
		// Process the exit referral
		if ( caseAssignmentForm.isCloseProgramUnitRef() ){
			
			ExitProgramReferralEvent reqEvent = new ExitProgramReferralEvent();
			
			reqEvent.setProgramReferralId( caseAssignmentForm.getCsProgReferralToCloseId() );			
			reqEvent.setProgramEndDate( DateUtil.stringToDate( caseAssignmentForm.getProgramEndDateAsStr(), DateUtil.DATE_FMT_1 ));
			reqEvent.setDischargeReasonCd( caseAssignmentForm.getReasonForDischargeId());

			MessageUtil.postRequest( reqEvent );
			saveCasenoteForExitReferral( caseAssignmentForm );			
		}
	}
   
   /**
	 * 
	 * @param defendantId
	 * @return
	 */
	public boolean isLastCaseAssigned( CaseAssignmentForm caseAssignmentForm )
	{

		boolean lastCase = true;
		
		GetActiveCasesEvent event = (GetActiveCasesEvent) EventFactory.getInstance(CaseloadControllerServiceNames.GETACTIVECASES);
       event.setDefendantId( caseAssignmentForm.getDefendantId() );

       CaseAssignmentResponseEvent assignmentResponse = ( CaseAssignmentResponseEvent ) 
       MessageUtil.postRequest(event, CaseAssignmentResponseEvent.class);
       
       List activeCases = assignmentResponse.getCaseAssignments();

       for ( int x = 0; x <activeCases.size(); x++ ){
       	
       	CaseAssignmentTO cato = ( CaseAssignmentTO ) activeCases.get( x );
       	
       	if ( cato.getCriminalCaseId() != null && !cato.getCriminalCaseId().equals( caseAssignmentForm.getCriminalCaseId() )){
       		
       		if ( !caseAssignmentForm.getOfficerPositionId().equals( cato.getAssignedStaffPositionId() )){
       			
       			lastCase = false;
       		}
       	} 
       }
		return lastCase;
	}//end of isLastCase()
	
	/**
	 * determine if a urinalysis record should be written to the external CSCD urinalysis SQL Server database.
	 * if there is a give active and original supervision order for the defendant and case, and if the corresponding 
	 * case has never been assigned to an officer in the past then write the record. 
	 * @param caseAssignmentForm
	 */
   private boolean isValidUrinalysisRecordForCscd( CaseAssignmentForm caseAssignmentForm ){
		
		boolean isValidUrinalysisRecord = false;
		// retrieve active orders and get the one with same case number and cdi as the current assignment
	  	String currentCaseNumber = caseAssignmentForm.getCaseNum();
	  	GetLightSupervisionOrdersEvent requestEvent =(GetLightSupervisionOrdersEvent) EventFactory.getInstance(
				SupervisionOrderControllerServiceNames.GETLIGHTSUPERVISIONORDERS);
			requestEvent.setSpn(caseAssignmentForm.getDefendantId());
			requestEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());			  	
		List<SupervisionOrderDetailResponseEvent> soResponseEvents = MessageUtil.postRequestListFilter(requestEvent, SupervisionOrderDetailResponseEvent.class);
		SupervisionOrderDetailResponseEvent activeCaseOrder = null;
		String tempCauseNum = "";
		String tempCdiNum = "";
		String tempCaseNum = "";
		for(int s=0; s < soResponseEvents.size(); s++){
			SupervisionOrderDetailResponseEvent anOrder = (SupervisionOrderDetailResponseEvent) soResponseEvents.get(s);
			tempCauseNum = anOrder.getCaseNum();
			tempCdiNum = anOrder.getCdi();
			tempCaseNum = "";
			String orderStatus = anOrder.getOrderStatusId();
			String versionType = anOrder.getVersionTypeId();
			if(StringUtils.isNotEmpty(tempCauseNum) && StringUtils.isNotEmpty(tempCdiNum) && StringUtils.isNotEmpty(orderStatus) 
					&& StringUtils.isNotEmpty(versionType) && orderStatus.equalsIgnoreCase("A")&& versionType.equals("O")){
				tempCaseNum = tempCdiNum.trim() + tempCauseNum.trim();
				if(tempCaseNum.equalsIgnoreCase(currentCaseNumber) ){
					activeCaseOrder = anOrder;
					break;
				}
			}
		}
		// get case assignment
		GetActiveCasesByCaseEvent event = (GetActiveCasesByCaseEvent) 
			EventFactory.getInstance(CaseloadControllerServiceNames.GETACTIVECASESBYCASE);
		event.setCriminalCaseId( tempCaseNum);		
		CaseAssignmentResponseEvent assignmentResponse = (CaseAssignmentResponseEvent) 
		MessageUtil.postRequest(event, CaseAssignmentResponseEvent.class);	
		CaseAssignmentTO cato = null;
		if ( assignmentResponse != null ){	
			List activeCases = new ArrayList();
			activeCases = assignmentResponse.getCaseAssignments();		
			for ( int x = 0; x < activeCases.size(); x++ ){		
				cato = ( CaseAssignmentTO ) activeCases.get( 0 );
			}
		}
		// check if valid to insert urinalysis record based on results
		if(cato != null && activeCaseOrder != null 
				&& (cato.getAssignedStaffPositionId()== null || cato.getAssignedStaffPositionId().equalsIgnoreCase(""))){
			isValidUrinalysisRecord = true;
		}			
			
		return isValidUrinalysisRecord;
	}
	
}