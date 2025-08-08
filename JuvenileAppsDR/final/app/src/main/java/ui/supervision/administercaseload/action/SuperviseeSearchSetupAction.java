/*
 * Created on Jun 27, 2007
 *
 */
package ui.supervision.administercaseload.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetAdditionalCaseInfoEvent;
import messaging.administercaseload.GetLightSupervisorsEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.AdditionalCaseInfoResponseEvent;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.administersupervisee.GetTransfersEvent;
import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.managetask.GetCSTasksEvent;
import messaging.managetask.reply.CSTaskResponseEvent;
import messaging.supervisionorder.GetUnfinishedOrdersEvent;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
import messaging.transfers.reply.TransferResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.EventFactory;
import mojo.km.security.ISecurityManager;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.Name;
import naming.CSTaskControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.SuperviseeControllerServiceNames;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercaseload.form.OfficerCaseload;
import ui.supervision.administercaseload.form.SuperviseeTransferCasesInfo;
import ui.supervision.administercaseload.helper.SearchSupervisee;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

/**
 * @author cc_rbhat
 *  
 */
public class SuperviseeSearchSetupAction extends JIMSBaseAction {

	private static String FWD_SETUP = "initialSetup";
	private static String FWD_SEARCH_USING_DEFENDANT_ID = "searchUsingDefendantId";
	private static String FWD_SEARCH_USING_OFFICER_ID = "searchUsingOfficer";

	private static String SEARCH_USING_DEFENDANT_ID = "SPN";

	private static String SEARCH_USING_NAME = "NAME";

	private static String SEARCH_USING_OFFICER = "CASELOAD";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.search.setup", "setup");
		keyMap.put("button.submit", "selectSearchType");
		keyMap.put("button.reassign", "selectSearchType");		
		keyMap.put("button.requestReassignmentToClo", "selectSearchType");		
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.getSupervisors", "getSupervisors");
		keyMap.put("button.getOfficers", "getOfficers");
		keyMap.put("button.clear", "resetOfficerCaseload");	
		keyMap.put("button.viewCaseload", "getOfficerCaseload");
		keyMap.put("button.closeCase", "reviewSuperviseeActiveCasesToClose");

		keyMap.put("button.transferIn", "transferIn");		
		keyMap.put("button.transferOut", "transferOut");				
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
	public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		caseAssignmentForm.getOfficerCaseload().setCourtIdFilter("");
		caseAssignmentForm.getOfficerCaseload().setZipCodeFilter("");
		caseAssignmentForm.getOfficerCaseload().setSelectedSupervisorId("");
		caseAssignmentForm.getOfficerCaseload().setSelectedOfficerId("");
		caseAssignmentForm.getOfficerCaseload().setZipCodeFilter("");
		caseAssignmentForm.getOfficerCaseload().setDefendantsSupervised(new ArrayList());
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
	public ActionForward selectSearchType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ActionForward forward = null;		
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		caseAssignmentForm.setActiveCases(new ArrayList());
		String searchType = request.getParameter("searchType");
		if(!StringUtils.isNotEmpty(searchType)) {
			searchType = caseAssignmentForm.getDefendantSearchCriteria();
		}else{
			String defendantId = request.getParameter("selectedValue");
			caseAssignmentForm.setDefendantId(defendantId);
		}
		request.setAttribute("searchType", null);
		if (searchType.equalsIgnoreCase(SEARCH_USING_DEFENDANT_ID)) {
			forward = mapping.findForward(FWD_SEARCH_USING_DEFENDANT_ID);
		} else if (searchType.equalsIgnoreCase(SEARCH_USING_NAME)) {
			forward = mapping.findForward("");
		} else if (searchType.equalsIgnoreCase(SEARCH_USING_OFFICER)) {
			String selectedDefendantId = caseAssignmentForm.getDefendantId();
			OfficerCaseload caseloadDetail = caseAssignmentForm.getOfficerCaseload();			
			caseloadDetail.setSelectedDefendantId(selectedDefendantId);
			String[] selectedCasesForReassignment = caseloadDetail.getSelectedCasesForReassignment();
			if (selectedCasesForReassignment.length == 0) {
				sendToErrorPage(request, "error.generic", "No supervisee case selected for reassignment.");	
				return mapping.findForward(FWD_SETUP);
			}
//			SuperviseeTransferCasesInfo superviseeTransferCases = caseAssignmentForm.getSuperviseeTransferCases();
//			List casesToTransfer = getSuperviseeTransferCases (caseAssignmentForm);	
			IName supervisorName = null;
			String programUnitId = "";
			String programUnitName = "";
			String supervisorPositionId = caseloadDetail.getSelectedSupervisorId();
			String officerPositionId = caseloadDetail.getSelectedOfficerId();             
			for (Iterator itr = caseloadDetail.getSupervisorsInDivision().iterator(); itr.hasNext();) {
				CSCDSupervisionStaffResponseEvent staff = (CSCDSupervisionStaffResponseEvent)itr.next();
				if (staff.getStaffPositionId().equals(supervisorPositionId)) {
					programUnitId = staff.getProgramUnitId();
					programUnitName = staff.getProgramUnitName();
					Name sName = staff.getAssignedName();
					supervisorName = new NameBean(sName.getFirstName(), sName.getMiddleName(), sName.getLastName());
				}
			}
			caseAssignmentForm.setSupervisorName(supervisorName);
			caseAssignmentForm.setProgramUnitId(programUnitId);			
	        caseAssignmentForm.setProgramUnitName(programUnitName);		
			
			String defendantName = "";
			List defendantCases = new ArrayList();
			List defendantsSupervised = caseloadDetail.getDefendantsSupervised();
			boolean caseFound = false;
			for (Iterator iterator = defendantsSupervised.iterator(); iterator.hasNext();) {
				CaseAssignmentResponseEvent responseEvent = (CaseAssignmentResponseEvent) iterator.next();
				defendantName = responseEvent.getDefendantFullName();
				defendantCases = responseEvent.getCaseAssignments();
				Iterator caseIter = defendantCases.iterator();
				while (caseIter != null && caseIter.hasNext()){
					ICaseAssignment ca = (ICaseAssignment) caseIter.next();
					for (int i = 0; i < selectedCasesForReassignment.length; i++) {
						String caseOid = selectedCasesForReassignment[i];
						if (ca.getCaseAssignmentId().equals(caseOid)){
							selectedDefendantId = ca.getDefendantId();
							String criminalCaseId = ca.getCriminalCaseId();
							
							if ( hasCurrentTasks( selectedDefendantId, criminalCaseId) ){
								
								sendToErrorPage(request, "error.generic", "Pending tasks exist for a workgroup or staff position for this case.  Process existing tasks before reassigning case");	
								return mapping.findForward(FWD_SETUP);
							}
							caseFound = true;
							ca.setProgramUnitName(programUnitName);
							GetAdditionalCaseInfoEvent event = new GetAdditionalCaseInfoEvent();
							event.setOfficerPositionId(ca.getAssignedStaffPositionId());
							
							AdditionalCaseInfoResponseEvent aResp = (AdditionalCaseInfoResponseEvent) MessageUtil.postRequest( event, AdditionalCaseInfoResponseEvent.class);							
							if ( aResp != null ){
								
								ca.setOfficerName(aResp.getOfficerName());
							}
							break;
						}
					}
				}
				if (caseFound){
					break;
				}
			}	
			caseAssignmentForm.setDefendantId(selectedDefendantId);
			caseAssignmentForm.setCasesSelectedForReassignment(selectedCasesForReassignment);
			caseAssignmentForm.setSuperviseeNameStr(defendantName);			
			
			String positionId = null;
            //swap necessary when supervisor's caseload is needed instead of the officer's caseload
            if (officerPositionId == null || officerPositionId.length() == 0) {
            	positionId = supervisorPositionId;
            } else {
            	positionId = officerPositionId;
            }            
			caseAssignmentForm.setSupervisorPositionId(supervisorPositionId);
			caseAssignmentForm.setOfficerPositionId(positionId);
			caseAssignmentForm.setCaseAssignments(defendantCases);
			caseAssignmentForm.setActiveCases(defendantCases);

			SuperviseeTransferCasesInfo superviseeTransferCases = caseAssignmentForm.getSuperviseeTransferCases();
			List casesToTransfer = new ArrayList();
			for (int i = 0; i < selectedCasesForReassignment.length; i++) {
				String caseAssignmentOid = (String) selectedCasesForReassignment[i];
				List activeCaseList  = caseAssignmentForm.getActiveCases();
				for (int j = 0; j < activeCaseList.size(); j++) {
					CaseAssignmentTO caseTO = (CaseAssignmentTO) activeCaseList.get(j);
					if (caseTO.getCaseAssignmentId().equals(caseAssignmentOid)){
						casesToTransfer.add(caseTO);
					}
				}
			}

			superviseeTransferCases.setCasesToTransfer(casesToTransfer);
	        caseAssignmentForm.setSuperviseeTransferCases(superviseeTransferCases); 
	        caseAssignmentForm.setActiveCasesSelectedForReassignment(casesToTransfer);
	        
	        forward = mapping.findForward(FWD_SEARCH_USING_OFFICER_ID);
	        if (UIConstants.CS_TRANSFER_OUT_CASES.equals(superviseeTransferCases.getTransferType())){
	        	caseAssignmentForm.setSecondaryAction(UIConstants.CS_TRANSFER_OUT_CASES);
	        }
			if (UIConstants.CS_TRANSFER_IN_CASES.equals(superviseeTransferCases.getTransferType())){
	        	forward = mapping.findForward("transferIn");
	        } 
		}
		return forward;
	}

	public ActionForward getSupervisors(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
        OfficerCaseload caseloadDetail = caseAssignmentForm.getOfficerCaseload();
		String selectedDivisionId = caseloadDetail.getSelectedDivisionId();
		
		if (selectedDivisionId != null && selectedDivisionId.length() != 0) {
			GetLightSupervisorsEvent gEvent = new GetLightSupervisorsEvent();
			gEvent.setDivisionId(selectedDivisionId);	
			gEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());

			List supervisorsInDivision = MessageUtil.postRequestListFilter( gEvent, CSCDSupervisionStaffResponseEvent.class);
			
			if ( supervisorsInDivision == null || supervisorsInDivision.isEmpty() ) {
				sendToErrorPage( request, "error.administerCaseload.supervisorsInDivisionNotFound" );	
			}			
			caseloadDetail.setSupervisorsInDivision(supervisorsInDivision);
			caseloadDetail.setOfficersUnderSupervisor(null);			
			caseAssignmentForm.getOfficerCaseload().setDefendantsSupervised(null);
			caseAssignmentForm.getOfficerCaseload().setSelectedSupervisorId("");
			caseAssignmentForm.getOfficerCaseload().setSelectedOfficerId("");
		} else {
			sendToErrorPage(request, "error.administerCaseload.supervisorsInDivisionNotFound");				
		}
		return mapping.findForward("initialSetup");
	}

	public ActionForward getOfficers(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
        OfficerCaseload caseloadDetail = caseAssignmentForm.getOfficerCaseload();
		String supervisorPositionId = caseloadDetail.getSelectedSupervisorId();
		
		if (supervisorPositionId != null && supervisorPositionId.length() != 0) {
			GetLightSupervisorsEvent gEvent = new GetLightSupervisorsEvent();
			gEvent.setSupervisorId(supervisorPositionId);			
			
			List officersUnderSupervisor = MessageUtil.postRequestListFilter( gEvent, CSCDSupervisionStaffResponseEvent.class);
			
			if (officersUnderSupervisor == null || officersUnderSupervisor.isEmpty()) {
				sendToErrorPage(request, "error.administerCaseload.officersUnderSupervisiorNotFound");	
			}			
			caseloadDetail.setOfficersUnderSupervisor(officersUnderSupervisor);			
			caseAssignmentForm.getOfficerCaseload().setDefendantsSupervised(null);
			caseAssignmentForm.getOfficerCaseload().setSelectedOfficerId("");
		} else {
			sendToErrorPage(request, "error.administerCaseload.officersUnderSupervisiorNotFound");				
		}
		return mapping.findForward("initialSetup");
	}

	public ActionForward getOfficerCaseload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
        OfficerCaseload caseloadDetail = caseAssignmentForm.getOfficerCaseload();
        caseloadDetail.setDefendantsSupervised(new ArrayList());
    	String selectedDivisionId = caseloadDetail.getSelectedDivisionId();
        String officerStaffPositionId = caseloadDetail.getSelectedOfficerId(); 
        String supervisorStaffPositionId = caseloadDetail.getSelectedSupervisorId();
    	String staffPositionOfficerName = "";    	
    	String staffPositionSupervisorName = "";
        CSCDSupervisionStaffResponseEvent divisionManagerStaffPos = null;
        List divisions = caseloadDetail.getDivisions();
        if (divisions != null && !divisions.isEmpty()) {
        	for (int i = 0; i < divisions.size(); i++) {
        		CSCDSupervisionStaffResponseEvent staff = (CSCDSupervisionStaffResponseEvent) divisions.get(i);
        		String divisionId = staff.getOrganizationId();
        		if (divisionId != null && divisionId.equals(selectedDivisionId)) {
        			divisionManagerStaffPos = staff;
        		}
        	}
        }
        String caseloadStaffPositionId = null;
        if (officerStaffPositionId == null || officerStaffPositionId.length() == 0) {
        	if (supervisorStaffPositionId == null || supervisorStaffPositionId.length() == 0) {
        		if (divisionManagerStaffPos != null) {
            		caseloadStaffPositionId = divisionManagerStaffPos.getStaffPositionId();
            		staffPositionOfficerName = divisionManagerStaffPos.getAssignedNameQualifiedByPositionName();        		
    				String parentPositionId = divisionManagerStaffPos.getParentPositionId();
    				CSCDSupervisionStaffResponseEvent parentPosition = AssignSuperviseeService.getInstance().getCSCDStaff(parentPositionId);
    				if (parentPosition != null) {							
    					staffPositionSupervisorName = parentPosition.getAssignedNameQualifiedByPositionName();												
    				}        			
        		}
        	} else {
        		caseloadStaffPositionId = supervisorStaffPositionId;
        		List supervisorsInDivision = caseloadDetail.getSupervisorsInDivision();
        		if (supervisorsInDivision != null) {
        			for (Iterator iterator = supervisorsInDivision.iterator(); iterator.hasNext();) {
        				CSCDSupervisionStaffResponseEvent staff = (CSCDSupervisionStaffResponseEvent) iterator.next();
        				if (staff.getStaffPositionId().equals(supervisorStaffPositionId)) {
        					staffPositionOfficerName = staff.getAssignedNameQualifiedByPositionName();
        					String parentPositionId = staff.getParentPositionId();
        					CSCDSupervisionStaffResponseEvent parentStaff = AssignSuperviseeService.getInstance().getCSCDStaff(parentPositionId);
        					if (parentStaff != null && parentStaff.getAssignedNameQualifiedByPositionName().length() > 0) {
            					staffPositionSupervisorName = parentStaff.getAssignedNameQualifiedByPositionName();        						
        					}
        					break;
        				}
        			}			
        		}
        	}
        } else {
        	caseloadStaffPositionId = officerStaffPositionId;
        	List supervisorsInDivision = caseloadDetail.getSupervisorsInDivision();
    		if (supervisorsInDivision != null) {
    			for (Iterator iterator = supervisorsInDivision.iterator(); iterator.hasNext();) {
    				CSCDSupervisionStaffResponseEvent staff = (CSCDSupervisionStaffResponseEvent) iterator.next();
    				if (staff.getStaffPositionId().equals(supervisorStaffPositionId)) {
    					staffPositionSupervisorName = staff.getAssignedNameQualifiedByPositionName();
    					break;
    				}
    			}			
    		}
    		List officersUnderSupervisor = caseloadDetail.getOfficersUnderSupervisor();
    		if (officersUnderSupervisor != null) {
    			for (int i = 0; i < officersUnderSupervisor.size(); i++) {
    				CSCDSupervisionStaffResponseEvent staff = (CSCDSupervisionStaffResponseEvent) officersUnderSupervisor.get(i);
    				if (staff.getStaffPositionId().equals(officerStaffPositionId)) {
    					staffPositionOfficerName = staff.getAssignedNameQualifiedByPositionName();
    					break;
    				}
    			}
    		}
        }
        if (caseloadStaffPositionId != null && caseloadStaffPositionId.length() != 0) {
			caseloadDetail.setSelectedOfficerName(staffPositionOfficerName);
			caseloadDetail.setSelectedSupervisorName(staffPositionSupervisorName);
			
            String courtIdFilter = caseloadDetail.getCourtIdFilter().trim().toUpperCase();
			if (courtIdFilter != null && !courtIdFilter.equals("")){
				while (courtIdFilter.length() < 3){
					courtIdFilter = "0" + courtIdFilter;
				}
			}
            String zipCodeFilter = caseloadDetail.getZipCodeFilter();
            SearchSupervisee search = new SearchSupervisee();
            List caseload = search.searchCasesByOfficerPositionId(caseloadStaffPositionId, selectedDivisionId);
    		if (caseload != null && caseload.size() != 0) {
        		int noOfSuperivsees = caseload.size();
        		int noOfCases = 0;
        		for (Iterator iterator = caseload.iterator(); iterator.hasNext();) {
        			CaseAssignmentResponseEvent responseEvent = (CaseAssignmentResponseEvent) iterator.next();
        			noOfCases += responseEvent.getCaseAssignments().size();
        		}
        		caseloadDetail.setTotalSuperviseesInCaseload(String.valueOf(noOfSuperivsees));
        		caseloadDetail.setToalCasesInCaseload(String.valueOf(noOfCases));
        		
        		caseload = filterCaseloadByCourtId(caseload, courtIdFilter);
        		if (caseload == null || caseload.size() == 0) {
        			sendToErrorPage(request, "error.administerCaseload.superviseeInCourtNotFound");	
        			caseloadDetail.setDefendantsSupervised(caseload);        			
        		} else {
        			caseload = filterCaseloadByZipCode(caseload, zipCodeFilter);
        			if (caseload == null || caseload.size() == 0) {
            			sendToErrorPage(request, "error.generic", "No supervisee in the specified Zip Code supervised by the officer");	
            			caseloadDetail.setDefendantsSupervised(caseload);        			        				
        			} else {
            			caseloadDetail.setDefendantsSupervised(SuperviseeSearchSetupAction.sortCaseLoadList(caseload));
            			resetSecurityFeature(caseAssignmentForm);        				
        			}
        		}
    		} else {
    			sendToErrorPage(request, "error.administerCaseload.superviseeUnderOfficerNotFound");	        	        	    			
    		}
        } else {
			sendToErrorPage(request, "error.administerCaseload.superviseeUnderOfficerNotFound");	        	        	
        }
		return mapping.findForward("initialSetup");
	}
	
	private List filterCaseloadByCourtId(List caseload, String courtIdFilter) {
		boolean filterFlag = (courtIdFilter != null && courtIdFilter.trim().length() != 0) ? true : false;
		if (filterFlag) {
			List filteredCaseload = new ArrayList();
			//now apply court id filter.
			for (Iterator iterator = caseload.iterator(); iterator.hasNext();) {
				CaseAssignmentResponseEvent responseEvent = (CaseAssignmentResponseEvent) iterator.next();
				List filteredCases = null;
				for (Iterator iter = responseEvent.getCaseAssignments().iterator(); iter.hasNext();) {				
					ICaseAssignment caseAssignment = (ICaseAssignment) iter.next();
					if (caseAssignment.getCourtId().equals(courtIdFilter)) {
						if (filteredCases == null) {
							filteredCaseload.add(responseEvent);
							filteredCases = new ArrayList();
							responseEvent.setCaseAssignments(filteredCases);						
						}
						filteredCases.add(caseAssignment);
					}
				}
			}
			return filteredCaseload;							
		} else {
			return caseload;							
		}
	}
	
	private List filterCaseloadByZipCode(List caseload, String zipCodeFilter) {
		boolean filterFlag = (zipCodeFilter != null && zipCodeFilter.trim().length() != 0) ? true : false;
		if (filterFlag) {
			List filteredCaseload = new ArrayList();
			for (Iterator iterator = caseload.iterator(); iterator.hasNext();) {
				CaseAssignmentResponseEvent responseEvent = (CaseAssignmentResponseEvent) iterator.next();
				if (responseEvent.getZipCode() != null) {
					if (responseEvent.getZipCode().equals(zipCodeFilter)) {
						filteredCaseload.add(responseEvent);
					}
				}
			}
			return filteredCaseload;							
		} else {
			return caseload;							
		}
	}
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward reviewSuperviseeActiveCasesToClose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		
		String selectedDefendantId = caseAssignmentForm.getDefendantId();
		OfficerCaseload caseloadDetail = caseAssignmentForm.getOfficerCaseload();			
		String[] selectedCasesForReassignment = caseloadDetail.getSelectedCasesForReassignment();
		IName defendantName = null;
		List defendantCases = null;
		String defendantFullName = "";
		List defendantsSupervised = caseloadDetail.getDefendantsSupervised();
		for (Iterator iterator = defendantsSupervised.iterator(); iterator.hasNext();) 
		{
			CaseAssignmentResponseEvent responseEvent = (CaseAssignmentResponseEvent) iterator.next();
			if (responseEvent.getDefendantId().equals(selectedDefendantId))
			{
				defendantName = responseEvent.getDefendantName();
				defendantFullName = responseEvent.getDefendantFullName();
				defendantCases = responseEvent.getCaseAssignments();		
				break;
			}
		}	
		
		for (int index = 0; index < selectedCasesForReassignment.length; index++) {			
			for (Iterator iterator = defendantCases.iterator(); iterator.hasNext();) {
				CaseAssignmentTO activeCase = (CaseAssignmentTO) iterator.next();
				
				if (activeCase.getCaseAssignmentId().equalsIgnoreCase(selectedCasesForReassignment[index])) 
				{
					String criminalCaseId = activeCase.getCriminalCaseId();
					if((criminalCaseId.startsWith(PDCodeTableConstants.CSCD, 0)) || (criminalCaseId.startsWith(PDCodeTableConstants.PTS, 0)))
					{
						sendToErrorPage(request, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Please select only Harris County Cases to close");
						return mapping.findForward("initialSetup");
					}
					GetUnfinishedOrdersEvent getUnfinishedOrdersEvent = (GetUnfinishedOrdersEvent)EventFactory.getInstance(SupervisionOrderControllerServiceNames.GETUNFINISHEDORDERS);
					if (caseAssignmentForm.getAgencyId() == null || caseAssignmentForm.getAgencyId().equals("")){
						getUnfinishedOrdersEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
					} else {
						getUnfinishedOrdersEvent.setAgencyId(caseAssignmentForm.getAgencyId());
					}
					getUnfinishedOrdersEvent.setCriminalCaseId(activeCase.getCriminalCaseId());
					CaseOrderResponseEvent core = (CaseOrderResponseEvent) MessageUtil.postRequest(getUnfinishedOrdersEvent, CaseOrderResponseEvent.class);
					if (core != null){
			            sendToErrorPage(request, "error.supervisionorder.unfinishedorders");
			            return mapping.findForward("initialSetup");
					}
				}
			}
		}
		
		caseloadDetail.setSelectedDefendantId(selectedDefendantId);
		caseAssignmentForm.setDefendantId(selectedDefendantId);
		caseAssignmentForm.setCasesSelectedForReassignment(selectedCasesForReassignment);
		caseAssignmentForm.setSuperviseeName(defendantName);
		caseAssignmentForm.setSuperviseeNameStr(defendantFullName);
		caseAssignmentForm.setCaseAssignments(defendantCases);
		caseAssignmentForm.setActiveCases(defendantCases);
		
		caseAssignmentForm.setCloseCases(true);
		
		String supervisorPositionId = caseloadDetail.getSelectedSupervisorId();
		String officerPositionId = caseloadDetail.getSelectedOfficerId(); 
        String positionId = null;
        //swap necessary when supervisor's caseload is needed instead of the officer's caseload
        if (officerPositionId == null || officerPositionId.length() == 0) {
        	positionId = supervisorPositionId;
        } else {
        	positionId = officerPositionId;
        }            
		caseAssignmentForm.setSupervisorPositionId(supervisorPositionId);
		caseAssignmentForm.setOfficerPositionId(positionId);
		
		IName supervisorName = null;
		String programUnitId = "";
		String programUnitName = "";
		for (Iterator itr = caseloadDetail.getSupervisorsInDivision().iterator(); itr.hasNext();) {
			CSCDSupervisionStaffResponseEvent staff = (CSCDSupervisionStaffResponseEvent)itr.next();
			if (staff.getStaffPositionId().equals(supervisorPositionId)) {
				programUnitId = staff.getOrganizationId();
				programUnitName = staff.getProgramUnitName();
				Name sName = staff.getAssignedName();
				supervisorName = new NameBean(sName.getFirstName(), sName.getMiddleName(), sName.getLastName());
			}
		}
		caseAssignmentForm.setSupervisorName(supervisorName);
		caseAssignmentForm.setProgramUnitId(programUnitId);			
        caseAssignmentForm.setProgramUnitName(programUnitName);

		return mapping.findForward(FWD_SEARCH_USING_OFFICER_ID);
	}

	public ActionForward transferIn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;				
		
		SuperviseeTransferCasesInfo superviseeTransferCases = caseAssignmentForm.getSuperviseeTransferCases();
		superviseeTransferCases.setTransferType(UIConstants.CS_TRANSFER_IN_CASES);
		return selectSearchType(mapping, form, request, response);
	}
	
// coding in this method needs to be optimized and is not fully tested as of 3/11/09
	public ActionForward transferOut(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;	
		SuperviseeTransferCasesInfo superviseeTransferCases = caseAssignmentForm.getSuperviseeTransferCases();
		List allCases = caseAssignmentForm.getActiveCases();
		// this will only be true on the first time thru as the original author of this action loads the cases in selectSearchType() after exiting this method
		if (allCases == null || allCases.isEmpty()){
			OfficerCaseload caseloadDetail = caseAssignmentForm.getOfficerCaseload();
			List defendantsSupervised = caseloadDetail.getDefendantsSupervised();
			for (Iterator iterator = defendantsSupervised.iterator(); iterator.hasNext();) {
				CaseAssignmentResponseEvent carEvent = (CaseAssignmentResponseEvent) iterator.next();
				for (int x =0; x < carEvent.getCaseAssignments().size(); x++){
					CaseAssignmentTO cato = (CaseAssignmentTO) carEvent.getCaseAssignments().get(x);
					for (int y = 0; y < caseAssignmentForm.getOfficerCaseload().getSelectedCasesForReassignment().length; y++){
						if (cato.getCaseAssignmentId().equals(caseAssignmentForm.getOfficerCaseload().getSelectedCasesForReassignment()[y])){
							allCases.add(cato); 
						}
					}
					
				}
			}	
		}
		List casesToTransfer = new ArrayList();
		String cdi = "";
		for (int i = 0; i < caseAssignmentForm.getOfficerCaseload().getSelectedCasesForReassignment().length; i++) {
			String selectedCaseOid = (String) caseAssignmentForm.getOfficerCaseload().getSelectedCasesForReassignment()[i];
			for (int j = 0; j < allCases.size(); j++) {
				CaseAssignmentTO caseAssignment = (CaseAssignmentTO) allCases.get(j);
				if (caseAssignment.getCaseAssignmentId().equals(selectedCaseOid)){
					cdi = caseAssignment.getCriminalCaseId().substring(0,3);
					if (cdi != null && cdi.equals("010")){
						ActionErrors errors = new ActionErrors();
						String errMsg = "Case " + caseAssignment.getDisplayCaseNum() + " is an out of county case";
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",errMsg));		
						saveErrors(request, errors);
						return mapping.findForward("initialSetup");
					}	
					casesToTransfer.add(caseAssignment);
				}
			}
		}
		if (casesToTransfer != null && !casesToTransfer.isEmpty()){

			GetTransfersEvent sps = (GetTransfersEvent)EventFactory.getInstance(SuperviseeControllerServiceNames.GETTRANSFERS);		
			sps.setSuperviseeId(caseAssignmentForm.getDefendantId()); 
			sps.setSearchType("C");
			
			Collection transfers = MessageUtil.postRequestListFilter( sps, TransferResponseEvent.class);
			if (transfers != null && !transfers.isEmpty()){
				String cdiCase = "";
				Iterator itr = transfers.iterator();

				while (itr.hasNext()){
					TransferResponseEvent evt = (TransferResponseEvent)itr.next();
					cdiCase = evt.getCdi() + evt.getCaseNum();
					for (int x=0; x < casesToTransfer.size(); x++){
						CaseAssignmentTO caseAssignment = (CaseAssignmentTO) casesToTransfer.get(x);
						if (cdiCase.equals(caseAssignment.getCriminalCaseId()) && !evt.getHcTransferOutDate().equals("") ){ 
							ActionErrors errors = new ActionErrors();
							String errMsg = "Case " + caseAssignment.getCriminalCaseId().toString() + " is already transferred out";
							errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",errMsg));		
							saveErrors(request, errors);
							return mapping.findForward("initialSetup");
						}
					}
				} 
			} else { //There are no cases currently transferred out.
				//commented this out.  no longer needed since flow was refactored.
				/* sps.setSuperviseeId(caseAssignmentForm.getDefendantId()); 
				sps.setSearchType("H");
				transfers = MessageUtil.postRequestListFilter( sps, TransferResponseEvent.class);
				 if (transfers != null){
					List transferHistList = CollectionUtil.iteratorToList(transfers.iterator());
					//Sort transfer history by cdi, caseNum, hcTransferInDate to set up for transferOutDate validation. 
					 List sortedList = new ArrayList(transferHistList);
					ArrayList sortFields = new ArrayList();
					sortFields.add(new ComparatorChain(new BeanComparator("cdi")));
					sortFields.add(new ComparatorChain(new BeanComparator("caseNum")));
					sortFields.add(new ReverseComparator(new BeanComparator("hcTransferInDate")));
					ComparatorChain multiSort = new ComparatorChain(sortFields);
					Collections.sort(sortedList, multiSort);
					TransferResponseEvent transfer = null;
					String aDateString = superviseeTransferCases.getTransferOutDate();
					Date proposedTransferOutDate = DateUtil.stringToDate(aDateString, "yyyyMMdd");
					ActionErrors errors =  new ActionErrors();
					String cdiCase = null;
					
					for (int x=0; x < casesToTransfer.size(); x++){
						CaseAssignmentTO caseAssignment = (CaseAssignmentTO) casesToTransfer.get(x);
						
						for (int i = 0; i < sortedList.size(); i++) {
							transfer = (TransferResponseEvent)sortedList.get(i);
							cdiCase = transfer.getCdi() + transfer.getCaseNum();

							if ("010".equals(transfer.getCdi())){
								break;
							} else if (cdiCase.equals(caseAssignment.getCriminalCaseId())){ 
								aDateString = transfer.getHcTransferInDate();
								if (aDateString != null && !aDateString.trim().equals(PDConstants.BLANK)){
									Date aDate = DateUtil.stringToDate(aDateString, "yyyyMMdd");
									if (aDate != null){
										if (proposedTransferOutDate.before(aDate)){
											String errMsg = "Case " + 
												caseAssignment.getCriminalCaseId().toString() + 
												" transfer out date must be greater than transfer in date of " + 
												DateUtil.dateToString(aDate, DateUtil.DATE_FMT_1);
											errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",errMsg));		
										}
									} else {
										String errMsg = "Case " + caseAssignment.getCriminalCaseId().toString() + " is not transferred in";
										errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",errMsg));		
									}
								} else {
									String errMsg = "Case " + caseAssignment.getCriminalCaseId().toString() + " is not transferred in";
									errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",errMsg));		
								}
								break;
							}
						}
					}
					if (errors.size() > 0){ 
						saveErrors(request, errors);
						return mapping.findForward("initialSetup");
					}
				} */
				
			}
		} 


		superviseeTransferCases.setTransferType(UIConstants.CS_TRANSFER_OUT_CASES);
		return selectSearchType(mapping, form, request, response);   
	}

	public ActionForward resetOfficerCaseload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		OfficerCaseload caseloadDetail = caseAssignmentForm.getOfficerCaseload();
		caseloadDetail.setOfficersUnderSupervisor(new ArrayList());	
		caseloadDetail.setDefendantsSupervised(new ArrayList());
		caseAssignmentForm.getOfficerCaseload().setDefendantsSupervised(null);
		caseAssignmentForm.getOfficerCaseload().setSelectedOfficerId("");
		return mapping.findForward("initialSetup");
	}	
	
	private void resetSecurityFeature(CaseAssignmentForm caseAssignmentForm) {
		List defendantsSupervised = caseAssignmentForm.getOfficerCaseload().getDefendantsSupervised();
		ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        Set userFeatures = securityManager.getFeatures();    
		//check for reassign features
        if (defendantsSupervised != null && defendantsSupervised.size() > 0) {        	
    		if (userFeatures.contains("CSCD-CASELOAD-OM")) {
    			caseAssignmentForm.setUserSecurityFeature(UIConstants.CS_REASSIGN_SUP);
    		} else if (userFeatures.contains("CSCD-CASELOAD-CLO")) {
    			caseAssignmentForm.setUserSecurityFeature(UIConstants.CS_REASSIGN_CASE_CLO);				
    		} else if (userFeatures.contains("CSCD-CASELOAD-CSO")) {
    			caseAssignmentForm.setUserSecurityFeature(UIConstants.CS_REASSIGN_CASE_CSO);				
    		} else {
    			caseAssignmentForm.setUserSecurityFeature(UIConstants.CS_VIEW_CASELOAD);
    		}        	
        }        
	}

		
/** xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
		SuperviseeTransferCasesInfo superviseeTransferCases = caForm.getSuperviseeTransferCases();
		List casesToTransfer = new ArrayList();
		for (int i = 0; i < selectedCasesForReassignment.length; i++) {
			String caseAssignmentOid = (String) selectedCasesForReassignment[i];
			List activeCaseList  = caForm.getActiveCases();
			for (int j = 0; j < activeCaseList.size(); j++) {
				CaseAssignmentTO caseTO = (CaseAssignmentTO) activeCaseList.get(j);
				if (caseTO.getCaseAssignmentId().equals(caseAssignmentOid)){
					casesToTransfer.add(caseTO);
				}
			}
		}
		return casesToTransfer;
//		superviseeTransferCases.setCasesToTransfer(casesToTransfer);
//      caForm.setSuperviseeTransferCases(superviseeTransferCases);

	}

xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx	 */
	/**
	 * @param caseload List
	 * @return List
	 */
	public static List sortCaseLoadList(List caseLoadList){
		if (caseLoadList.size() > 1){
			SortedMap map = new TreeMap();
			String fullName = "";
			String defId = "";
			for (int x = 0; x < caseLoadList.size(); x++){
				CaseAssignmentResponseEvent care = (CaseAssignmentResponseEvent) caseLoadList.get(x);
				fullName = "";
				defId = "";
				if (care.getDefendantFullName() != null){
					fullName = care.getDefendantFullName();
				}
				if (care.getDefendantId() != null){
					defId = care.getDefendantId();
				}
				map.put(fullName + defId, care);
			}
			caseLoadList = new ArrayList(map.values());
		} 
		return caseLoadList;
	} 	
	
	/**
	 * 
	 * @param defendantId
	 * @param crimCaseId
	 * @return
	 */
	private boolean hasCurrentTasks( String spn , String crimCaseId ){
		
		boolean hasTasks = false;
		
		GetCSTasksEvent request = (GetCSTasksEvent) EventFactory
        							.getInstance(CSTaskControllerServiceNames.GETCSTASKS );
		
		request.setDefendantId( spn );
		request.setCriminalCaseId( crimCaseId );
		
		CSTaskResponseEvent taskResponse = (CSTaskResponseEvent) 
									MessageUtil.postRequest( request, CSTaskResponseEvent.class );
		if ( taskResponse != null ){
			
			hasTasks = true;
		}
		return hasTasks;
	}
}
