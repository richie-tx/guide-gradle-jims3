/*
 * Created on Jul 25, 2007
 */
package ui.supervision.administercaseload.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetLightSupervisorsEvent;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.reply.LightSupervisorResponseEvent;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.cscdstaffposition.GetLightOrganizationStaffEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercaseload.form.OfficerCaseload;
import ui.supervision.administercaseload.form.SuperviseeTransferCasesInfo;
import ui.supervision.administercaseload.helper.SearchSupervisee;
import ui.supervision.cscdcalendar.form.CSEventsSearchForm;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

/**
 * @author cc_rbhat
 */
public class AdminCaseloadAction extends JIMSBaseAction {
	private static final String FWD_SUPERVISEE_SEARCH = "superviseeSearch";

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.search.setup", "setup");
	}

	public ActionForward setup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws GeneralFeedbackMessageException {
		//long startTime = System.currentTimeMillis();
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		//clear the form to remove data from previous states.
		caseAssignmentForm.clear();
		CSEventsSearchForm csEventsSearchForm = (CSEventsSearchForm)this.getSessionForm(mapping, request, "csCalendarEventsSearchForm", false);
		if(csEventsSearchForm != null)
		{
			csEventsSearchForm.clear();
		}
		checkSecurityFeature(caseAssignmentForm, request);
		//System.out.println("Elapsed Time= " + (System.currentTimeMillis()-startTime)/1000F);
		return mapping.findForward(FWD_SUPERVISEE_SEARCH);
	}

	private void checkSecurityFeature(CaseAssignmentForm caseAssignmentForm, HttpServletRequest request) {
		ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        Set userFeatures = securityManager.getFeatures();    
		String userType = securityManager.getIUserInfo().getUserTypeId();
		boolean hasAdminFeature = false;
		//check for reassign features
		//Changed to default to CS_REASSIGN_ADMIN for SA and MA only.
		if (userType.equals("MA") || userType.equals("SA") || userFeatures.contains("CSCD-CASELOAD-ADMIN")) {
			hasAdminFeature = true;
			setStaffPositionCaseload(caseAssignmentForm, request, hasAdminFeature);	
			if (userFeatures.contains("CSCD-CASELOAD-CLO")){
				caseAssignmentForm.setUserSecurityFeature(UIConstants.CS_REASSIGN_CASE_CLO);
			} else if (userFeatures.contains("CSCD-CASELOAD-CSO")){
				caseAssignmentForm.setUserSecurityFeature(UIConstants.CS_REASSIGN_CASE_CSO);				
			} else if (userFeatures.contains("CSCD-CASELOAD-OM")) {
				setSupervisorsInDivision(caseAssignmentForm, request);
				caseAssignmentForm.setUserSecurityFeature(UIConstants.CS_REASSIGN_SUP);
			} else if (userType.equals("MA") || userType.equals("SA")){
				caseAssignmentForm.setUserSecurityFeature(UIConstants.CS_REASSIGN_ADMIN);
			}
		} else if (userFeatures.contains("CSCD-CASELOAD-OM")) {
			setStaffPositionCaseload(caseAssignmentForm, request, true);
			setSupervisorsInDivision(caseAssignmentForm, request);
			caseAssignmentForm.setUserSecurityFeature(UIConstants.CS_REASSIGN_SUP);
		} else if (userFeatures.contains("CSCD-CASELOAD-CLO")) {
			setStaffPositionCaseload(caseAssignmentForm, request, hasAdminFeature);
			List defendantsSupervised = caseAssignmentForm.getOfficerCaseload().getDefendantsSupervised();
			if (defendantsSupervised != null && defendantsSupervised.size() > 0) {
				caseAssignmentForm.setUserSecurityFeature(UIConstants.CS_REASSIGN_CASE_CLO);				
			}
		} else if (userFeatures.contains("CSCD-CASELOAD-CSO")) {
			setStaffPositionCaseload(caseAssignmentForm, request, hasAdminFeature);	
			List defendantsSupervised = caseAssignmentForm.getOfficerCaseload().getDefendantsSupervised();
			if (defendantsSupervised != null && defendantsSupervised.size() > 0) {
				caseAssignmentForm.setUserSecurityFeature(UIConstants.CS_REASSIGN_CASE_CSO);				
			}
//			caseAssignmentForm.setViewProgramReferralCaseload(true);
		} else {
			setSupervisorsInDivision(caseAssignmentForm, request);						
			List supervisorsInDivision = caseAssignmentForm.getOfficerCaseload().getSupervisorsInDivision();
			if (supervisorsInDivision != null && supervisorsInDivision.size() > 0) {
				caseAssignmentForm.setUserSecurityFeature(UIConstants.CS_VIEW_CASELOAD);
			}
		}
		//check for transfer case features
		if (userFeatures.contains("CSCD-TRANSFER-CASES")) {
			SuperviseeTransferCasesInfo superviseeTransferCases = caseAssignmentForm.getSuperviseeTransferCases();
			superviseeTransferCases.setTransferType(UIConstants.CS_TRANSFER_CASES);
			caseAssignmentForm.setAllowTransfers(UIConstants.YES);
		}
		//check for View Calendar Report features
		if(userFeatures.contains("CSCD-CASELOAD-VIEW") || userFeatures.contains("CSCD-CASELOAD-ADMIN"))
		{
			caseAssignmentForm.setViewCalReportFeatureAssigned(true);
		}
	}
	
	private void setSupervisorsInDivision(CaseAssignmentForm caseAssignmentForm, HttpServletRequest request) {
		GetLightSupervisorsEvent gEvent = new GetLightSupervisorsEvent();
		gEvent.setLogonId(SecurityUIHelper.getLogonId());
		gEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());			
		CompositeResponse response = MessageUtil.postRequest(gEvent);
		
		LightSupervisorResponseEvent staff = (LightSupervisorResponseEvent) MessageUtil.filterComposite(response, LightSupervisorResponseEvent.class);
		if (staff == null) {
		    sendToErrorPage(request, "error.administerCaseload.userNotAssignedToStaffPosition");
		    return;
	    }		
		List list = MessageUtil.compositeToList(response, CSCDSupervisionStaffResponseEvent.class);
		List supervisorsInDivision = new ArrayList();
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				CSCDSupervisionStaffResponseEvent org = (CSCDSupervisionStaffResponseEvent) list.get(i);
				if(org.getPositionTypeId() != null && !PDCodeTableConstants.STAFF_POSITION_TYPE_OFFICER.equals(org.getPositionTypeId())){
					supervisorsInDivision.add(org);
				}
			}
		}		
		if (supervisorsInDivision.isEmpty()) {
			sendToErrorPage(request, "error.administerCaseload.supervisorsInDivisionNotFound");	
		} else {
			caseAssignmentForm.getOfficerCaseload().setSelectedDivisionId(staff.getDivisionId());		
			caseAssignmentForm.getOfficerCaseload().setSupervisorsInDivision(supervisorsInDivision);
		}
	}
	
	private void setStaffPositionCaseload(CaseAssignmentForm caseAssignmentForm, HttpServletRequest request,
			boolean hasAdminFeature) {
		GetLightSupervisorsEvent gEvent = new GetLightSupervisorsEvent();
		gEvent.setLogonId(SecurityUIHelper.getLogonId());
		gEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());	
		gEvent.setOfficerInfoNeeded(true);
		CompositeResponse response = MessageUtil.postRequest(gEvent);
		
		LightSupervisorResponseEvent staff = 
			(LightSupervisorResponseEvent) MessageUtil.filterComposite(response, LightSupervisorResponseEvent.class);
		if (staff == null || staff.getStaffPositionId() == null || staff.getStaffPositionId().trim().length() == 0) {
		    sendToErrorPage(request, "error.administerCaseload.userNotAssignedToStaffPosition");
	    } else {
	    	String positionTypeCode = staff.getPositionTypeCode();
	    	if (PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR.equals(positionTypeCode)) {
	    		setDivisionManagerCaseload(caseAssignmentForm, request, staff, hasAdminFeature);		    	
	    	} else if(PDCodeTableConstants.STAFF_POSITION_TYPE_SUPERVISOR.equals(positionTypeCode) || 
	    			PDCodeTableConstants.STAFF_POSITION_TYPE_ASSISTANTSUP.equals(positionTypeCode)) {
	    		setSupervisorCaseload(caseAssignmentForm, request, staff, response, hasAdminFeature);
	    	} else {
	    		setOfficerCaseload(caseAssignmentForm, request, staff, response, hasAdminFeature);
	    	}
	    }
	}

	private void setDivisionManagerCaseload(CaseAssignmentForm caseAssignmentForm, HttpServletRequest request,
			LightSupervisorResponseEvent staff, boolean hasAdminFeature) {
    	String divisionId = staff.getDivisionId();
    	String staffPositionId = staff.getStaffPositionId();
		OfficerCaseload caseloadDetail = caseAssignmentForm.getOfficerCaseload(); 		
		String staffPositionOfficerName = "";
		String staffPositionSupervisorName = "";
		GetLightOrganizationStaffEvent getLightOrganizationStaffEvent = new GetLightOrganizationStaffEvent();
		getLightOrganizationStaffEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
		List divisionsInAgency = MessageUtil.postRequestListFilter(getLightOrganizationStaffEvent, CSCDSupervisionStaffResponseEvent.class);		
		if (divisionsInAgency.isEmpty()) {
			sendToErrorPage(request, "error.administerCaseload.divisionsInAgencyNotFound");	
		} else {
    		List staffDivision = new ArrayList();
    		for (int i = 0; i < divisionsInAgency.size(); i++) {
    			CSCDSupervisionStaffResponseEvent division = (CSCDSupervisionStaffResponseEvent) divisionsInAgency.get(i);
    			if (division.getStaffPositionId() != null && division.getStaffPositionId().equals(staffPositionId)) {
    				staffDivision.add(division);
					staffPositionOfficerName = division.getAssignedNameQualifiedByPositionName();
					String parentPositionId = division.getParentPositionId();
					CSCDSupervisionStaffResponseEvent parentPosition = AssignSuperviseeService.getInstance().getCSCDStaff(parentPositionId);
					if (parentPosition != null) {							
						staffPositionSupervisorName = parentPosition.getAssignedNameQualifiedByPositionName();
					} else {
						staffPositionSupervisorName = "No officer assigned";
					}
					break;
				}
    		}
    		if (hasAdminFeature) {
    			staffDivision = divisionsInAgency;
    		}
    		
    		if (staffDivision.isEmpty()) {
				sendToErrorPage(request, "error.administerCaseload.divisionForCurrentUserNotFound");	
    		} else {    	
    			caseloadDetail.setSelectedDivisionId(divisionId);    			
    			caseloadDetail.setDivisions(AdminCaseloadAction.sortDivisionsList(staffDivision));
    			caseloadDetail.setSelectedOfficerName(staffPositionOfficerName);
    			caseloadDetail.setSelectedSupervisorName(staffPositionSupervisorName);
    			List officerCaseload = getCaseloadDetails(staffPositionId, divisionId);
    			if (officerCaseload == null || officerCaseload.size() == 0) {
    				sendToErrorPage(request, "error.administerCaseload.superviseeUnderOfficerNotFound");	
    			} else {
    				caseloadDetail.setDefendantsSupervised(officerCaseload);			
    				int noOfSuperivsees = officerCaseload.size();
    				int noOfCases = 0;
    				for (Iterator iterator = officerCaseload.iterator(); iterator.hasNext();) {
    					CaseAssignmentResponseEvent responseEvent = (CaseAssignmentResponseEvent) iterator.next();
    					noOfCases += responseEvent.getCaseAssignments().size();
    				}
    				caseloadDetail.setTotalSuperviseesInCaseload(String.valueOf(noOfSuperivsees));
    				caseloadDetail.setToalCasesInCaseload(String.valueOf(noOfCases));	
    			}					    					    			
    		}
		}		
	}

	private void setSupervisorCaseload(CaseAssignmentForm caseAssignmentForm, HttpServletRequest request,
			LightSupervisorResponseEvent staff, CompositeResponse results, boolean hasAdminFeature) {
    	String divisionId = staff.getDivisionId();
    	String staffPositionId = staff.getStaffPositionId();
    	String staffPositionOfficerName = "";    	
    	String staffPositionSupervisorName = "";
		OfficerCaseload caseloadDetail = caseAssignmentForm.getOfficerCaseload(); 		
		if (hasAdminFeature) {
			GetLightOrganizationStaffEvent getLightOrganizationStaffEvent = new GetLightOrganizationStaffEvent();
			getLightOrganizationStaffEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
			List divisionsInAgency = MessageUtil.postRequestListFilter(getLightOrganizationStaffEvent, CSCDSupervisionStaffResponseEvent.class);
			if (divisionsInAgency.isEmpty()) {
				sendToErrorPage(request, "error.administerCaseload.divisionsInAgencyNotFound");					
				return;
			} else {
    			caseloadDetail.setDivisions(divisionsInAgency);
			}
		}
		List supervisorsInDivision = new ArrayList();
		List list = MessageUtil.compositeToList(results, CSCDSupervisionStaffResponseEvent.class); 
		if(!list.isEmpty()) {
			for(int i = 0; i < list.size(); i++) {
				CSCDSupervisionStaffResponseEvent org = (CSCDSupervisionStaffResponseEvent) list.get(i);
				String positionTypeId = org.getPositionTypeId();
				if (positionTypeId != null &&
					(PDCodeTableConstants.STAFF_POSITION_TYPE_SUPERVISOR.equals(positionTypeId) ||
					 PDCodeTableConstants.STAFF_POSITION_TYPE_ASSISTANTSUP.equals(positionTypeId))) {
					supervisorsInDivision.add(org);
					if (org.getStaffPositionId().equals(staffPositionId)) {
						staffPositionOfficerName = org.getAssignedNameQualifiedByPositionName();
						String parentPositionId = org.getParentPositionId();
						CSCDSupervisionStaffResponseEvent parentPosition = AssignSuperviseeService.getInstance().getCSCDStaff(parentPositionId);
						if (parentPosition != null) {							
							staffPositionSupervisorName = parentPosition.getAssignedNameQualifiedByPositionName();												
						} else {
							staffPositionSupervisorName = "No officer assigned";
						}
					}
				}
			}
		} else {
			sendToErrorPage(request, "error.administerCaseload.supervisorsInDivisionNotFound");	
		}
		if (supervisorsInDivision.isEmpty()) {
			sendToErrorPage(request, "error.administerCaseload.supervisorsInDivisionNotFound");	
		} else {
			caseloadDetail.setSelectedDivisionId(divisionId);
			caseloadDetail.setSupervisorsInDivision(supervisorsInDivision);			
			caseloadDetail.setSelectedSupervisorId(staffPositionId);	
			caseloadDetail.setSelectedOfficerName(staffPositionOfficerName);
			caseloadDetail.setSelectedSupervisorName(staffPositionSupervisorName);
			
			List officerCaseload = getCaseloadDetails(staffPositionId, divisionId);
			if (officerCaseload == null || officerCaseload.size() == 0) {
				sendToErrorPage(request, "error.administerCaseload.superviseeUnderOfficerNotFound");	
			} else {					
				caseloadDetail.setDefendantsSupervised(officerCaseload); 			
				int noOfSuperivsees = officerCaseload.size();
				int noOfCases = 0;
				for (Iterator iterator = officerCaseload.iterator(); iterator.hasNext();) {
					CaseAssignmentResponseEvent responseEvent = (CaseAssignmentResponseEvent) iterator.next();
					noOfCases += responseEvent.getCaseAssignments().size();
				}
				caseloadDetail.setTotalSuperviseesInCaseload(String.valueOf(noOfSuperivsees));
				caseloadDetail.setToalCasesInCaseload(String.valueOf(noOfCases));	    		
			}
		}		
	}
	
	private void setOfficerCaseload(CaseAssignmentForm caseAssignmentForm, HttpServletRequest request,
			LightSupervisorResponseEvent staff, CompositeResponse results, boolean hasAdminFeature) {
    	String divisionId = staff.getDivisionId();
    	String staffPositionId = staff.getStaffPositionId();
    	String supervisorPositionId = staff.getSupervisorPositionId();
		OfficerCaseload caseloadDetail = caseAssignmentForm.getOfficerCaseload(); 	
		if (hasAdminFeature) {
			GetLightOrganizationStaffEvent getLightOrganizationStaffEvent = new GetLightOrganizationStaffEvent();
			getLightOrganizationStaffEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
			List divisionsInAgency = MessageUtil.postRequestListFilter(getLightOrganizationStaffEvent, CSCDSupervisionStaffResponseEvent.class);
			if (divisionsInAgency.isEmpty()) {
				sendToErrorPage(request, "error.administerCaseload.divisionsInAgencyNotFound");					
				return;
			} else {    			
    			caseloadDetail.setDivisions(AdminCaseloadAction.sortDivisionsList(divisionsInAgency));
			}
		}		
		List supervisorsInDivision = new ArrayList();
		List officersUnderSupervisor = new ArrayList();
		List list = MessageUtil.compositeToList(results, CSCDSupervisionStaffResponseEvent.class);
		if(!list.isEmpty()) {
			for(int i = 0; i < list.size(); i++){
				CSCDSupervisionStaffResponseEvent org = (CSCDSupervisionStaffResponseEvent) list.get(i);
				if(org.getPositionTypeId() != null && PDCodeTableConstants.STAFF_POSITION_TYPE_OFFICER.equals(org.getPositionTypeId())){
					officersUnderSupervisor.add(org);
				}else {
					supervisorsInDivision.add(org);
				}
			}
		}
		caseloadDetail.setSelectedDivisionId(divisionId);
		if (supervisorsInDivision.isEmpty()) {
			sendToErrorPage(request, "error.administerCaseload.supervisorsInDivisionNotFound");	
			return;
		} else {
			caseloadDetail.setSupervisorsInDivision(supervisorsInDivision);		
			if (officersUnderSupervisor.isEmpty()) {
				sendToErrorPage(request, "error.administerCaseload.officersUnderSupervisiorNotFound");	
				return;
			} else {
				caseloadDetail.setSelectedSupervisorId(supervisorPositionId);
				caseloadDetail.setOfficersUnderSupervisor(officersUnderSupervisor);				
				String supervisorName = "";
				for (int i = 0; i < supervisorsInDivision.size(); i++) {
					CSCDSupervisionStaffResponseEvent sup = (CSCDSupervisionStaffResponseEvent) supervisorsInDivision.get(i);
					if (sup.getStaffPositionId().equals(supervisorPositionId)) {
						supervisorName = sup.getAssignedNameQualifiedByPositionName();
						break;
					}
				}
				caseloadDetail.setSelectedSupervisorName(supervisorName);
				caseloadDetail.setSelectedOfficerId(staffPositionId);
				String officerName = "";
				for (int i = 0; i < officersUnderSupervisor.size(); i++) {
					CSCDSupervisionStaffResponseEvent officer = (CSCDSupervisionStaffResponseEvent) officersUnderSupervisor.get(i);
					if (officer.getStaffPositionId().equals(staffPositionId)) {
						officerName = officer.getAssignedNameQualifiedByPositionName();
						break;
					}						
				}
				caseloadDetail.setSelectedOfficerName(officerName);				
				List officerCaseload = getCaseloadDetails(staffPositionId, divisionId);
				if (officerCaseload == null || officerCaseload.size() == 0) {
					sendToErrorPage(request, "error.administerCaseload.superviseeUnderOfficerNotFound");	
				} else {
					caseloadDetail.setDefendantsSupervised(officerCaseload);	
					int noOfSuperivsees = officerCaseload.size();
					int noOfCases = 0;
					for (Iterator iterator = officerCaseload.iterator(); iterator.hasNext();) {
						CaseAssignmentResponseEvent responseEvent = (CaseAssignmentResponseEvent) iterator.next();
						noOfCases += responseEvent.getCaseAssignments().size();
					}
					caseloadDetail.setTotalSuperviseesInCaseload(String.valueOf(noOfSuperivsees));
					caseloadDetail.setToalCasesInCaseload(String.valueOf(noOfCases));	    		
				}
			}
		}		
	}
	
	private List getCaseloadDetails(String officerId, String divisionId) {
		SearchSupervisee search = new SearchSupervisee();
		List caseload = search.searchCasesByOfficerPositionId(officerId, divisionId);
		if (caseload != null && !caseload.isEmpty()){
			String caseNum = "";
			for (int g = 0; g < caseload.size(); g++){
				CaseAssignmentResponseEvent care = (CaseAssignmentResponseEvent) caseload.get(g);
				if (care.getCaseAssignments() != null && !care.getCaseAssignments().isEmpty()){
					for (int y = 0; y < care.getCaseAssignments().size(); y++){
						CaseAssignmentTO cato = (CaseAssignmentTO) care.getCaseAssignments().get(y);
						if (cato.getCriminalCaseId() != null && !cato.getCriminalCaseId().equals("")){
							caseNum = cato.getCriminalCaseId().substring(3);
							cato.setDisplayCaseNum(caseNum);
						}
					}
				}
			}
		}
		return caseload;
	}
	
	public static List sortDivisionsList(List divisionsList){
		if (divisionsList.size() > 1){
			SortedMap map = new TreeMap();
			String divisionName = "";
			String divisionId = "";
			for (int x = 0; x < divisionsList.size(); x++){
				CSCDSupervisionStaffResponseEvent sspr = (CSCDSupervisionStaffResponseEvent) divisionsList.get(x);
				divisionName = "";
				divisionId = "";
				if (sspr.getDivisionName() != null){
					divisionName = sspr.getDivisionName();
				}
				if (sspr.getDivisionId() != null){
					divisionId = sspr.getDivisionId();
				}
				map.put(divisionName + divisionId, sspr);
			}
			divisionsList = new ArrayList(map.values());
		} 
		return divisionsList;
	}

}
