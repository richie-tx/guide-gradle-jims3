/*
 * Created on Sep 14, 2007
 */
package ui.supervision.administercaseload.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import messaging.administercaseload.GetCaseloadEvent;
import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.GetLightProgramUnitsForDivisionEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administercaseload.reply.LightProgramUnitResponseEvent;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.organization.GetProgramUnitEvent;
import messaging.organization.reply.GetProgramUnitResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;
import naming.CaseloadControllerServiceNames;
import ui.security.SecurityUIHelper;

/**
 * @author cc_rbhat
 */
public class SearchSupervisee {
	/**
	 *  
	 */
	public SearchSupervisee() {
	}

	public CaseAssignmentResponseEvent searchCasesByDefendantId(String defendantId, String divisionId) {
		CaseAssignmentResponseEvent responseEvent = null;
		GetCaseloadEvent event = (GetCaseloadEvent) EventFactory
				.getInstance(CaseloadControllerServiceNames.GETCASELOAD);
		event.setWorkflowInd(CaseloadConstants.SEARCH_CASELOAD_BY_SPN);
		event.setDefendantId(defendantId);
		List casesBySupervisee = MessageUtil.postRequestListFilter(event, CaseAssignmentResponseEvent.class);

		if (casesBySupervisee != null && casesBySupervisee.size() > 0) {
			//only one response for a defendant.
			responseEvent = (CaseAssignmentResponseEvent) casesBySupervisee.get(0);
			if (responseEvent != null) {
				//filter directly and indirectly supervised cases.
				GetLightProgramUnitsForDivisionEvent gEvent = new GetLightProgramUnitsForDivisionEvent();
				gEvent.setDivisionId(divisionId);
				CompositeResponse response = MessageUtil.postRequest(gEvent);
				gEvent = null;
				
				Collection col = MessageUtil.compositeToCollection(response, LightProgramUnitResponseEvent.class);
				Map programUnits = new HashMap();
				if(col != null && !col.isEmpty()){
					Iterator pIter = col.iterator();
					while(pIter.hasNext()){
						LightProgramUnitResponseEvent resp = (LightProgramUnitResponseEvent) pIter.next();
						if(!programUnits.containsKey(resp.getProgramUnitId())){
							programUnits.put(resp.getProgramUnitId(), resp.getProgramUnitId());
						}
					}
				}
				List filteredCases = filterCasesByAssignedFeature(responseEvent, programUnits);
				//set program unit name
				if (filteredCases != null) {
					for (int i = 0; i < filteredCases.size(); i++) {
						ICaseAssignment caseAssignment = (ICaseAssignment) filteredCases.get(i);
						String programUnitId = caseAssignment.getProgramUnitId();
						for (Iterator iterator = col.iterator(); iterator.hasNext();) {
							LightProgramUnitResponseEvent progUnit = (LightProgramUnitResponseEvent) iterator.next();
							if (programUnitId.equals(progUnit.getProgramUnitId())) {
								caseAssignment.setProgramUnitName(progUnit.getProgramUnitName());
								break;
							}
						}
					}	
				}
				responseEvent.setCaseAssignments(filteredCases);
				filteredCases = null;
				col = null;
			}
		}
		event = null;
		casesBySupervisee = null;
		
		return responseEvent;
	}
	
	public CaseAssignmentResponseEvent searchCasesByDefendantId(String defendantId) {
		CaseAssignmentResponseEvent responseEvent = null;
		GetCaseloadEvent event = (GetCaseloadEvent) EventFactory
				.getInstance(CaseloadControllerServiceNames.GETCASELOAD);
		event.setWorkflowInd(CaseloadConstants.SEARCH_CASELOAD_BY_SPN);
		event.setDefendantId(defendantId);
		List casesBySupervisee = MessageUtil.postRequestListFilter(event, CaseAssignmentResponseEvent.class);

		if (casesBySupervisee != null && casesBySupervisee.size() > 0) {
			//only one response for a defendant.
			responseEvent = (CaseAssignmentResponseEvent) casesBySupervisee.get(0);
			if (responseEvent != null) {
				//filter directly and indirectly supervised cases.
				List filteredCases = filterCasesByAssignedFeature(responseEvent);
				responseEvent.setCaseAssignments(filteredCases);
			}
		}
		casesBySupervisee = null;
		return responseEvent;
	}

	public List searchCasesByOfficerPositionId(String officerPositionId) {
		GetCaseloadEvent event = (GetCaseloadEvent) EventFactory
				.getInstance(CaseloadControllerServiceNames.GETCASELOAD);
		event.setWorkflowInd(CaseloadConstants.SEARCH_CASELOAD_BY_OFFICER);
		event.setOfficerPositionId(officerPositionId);
		List casesBySupervisee = MessageUtil.postRequestListFilter(event, CaseAssignmentResponseEvent.class);
		event = null;

		if (casesBySupervisee != null) {
			CaseAssignmentResponseEvent responseEvent = null;
			for (Iterator iterator = casesBySupervisee.iterator(); iterator.hasNext();) {
				responseEvent = (CaseAssignmentResponseEvent) iterator.next();
				if (responseEvent != null) {
					//filter directly and indirectly supervised cases.
					List filteredCases = filterCasesByAssignedFeature(responseEvent);
					responseEvent.setCaseAssignments(filteredCases);
				}				
			}
		}
		return casesBySupervisee;
	}
	
	public List searchCasesByOfficerPositionId(String officerPositionId, String divisionId) {
		GetCaseloadEvent event = (GetCaseloadEvent) EventFactory
				.getInstance(CaseloadControllerServiceNames.GETCASELOAD);
		event.setWorkflowInd(CaseloadConstants.SEARCH_CASELOAD_BY_OFFICER);
		event.setOfficerPositionId(officerPositionId);
		List casesBySupervisee = MessageUtil.postRequestListFilter(event, CaseAssignmentResponseEvent.class);

		if (casesBySupervisee != null) {			
			GetLightProgramUnitsForDivisionEvent gEvent = new GetLightProgramUnitsForDivisionEvent();
			gEvent.setDivisionId(divisionId);
			CompositeResponse response = MessageUtil.postRequest(gEvent);
			gEvent = null;
			
			Collection col = MessageUtil.compositeToCollection(response, LightProgramUnitResponseEvent.class);
			Map programUnits = new HashMap();
			if(col != null && !col.isEmpty()){
				Iterator pIter = col.iterator();
				while(pIter.hasNext()){
					LightProgramUnitResponseEvent resp = (LightProgramUnitResponseEvent) pIter.next();
					if(!programUnits.containsKey(resp.getProgramUnitId())){
						programUnits.put(resp.getProgramUnitId(), resp.getProgramUnitId());
					}
				}
				col = null;
			}
			
			CaseAssignmentResponseEvent responseEvent = null;
			for (Iterator iterator = casesBySupervisee.iterator(); iterator.hasNext();) {
				responseEvent = (CaseAssignmentResponseEvent) iterator.next();
				if (responseEvent != null) {
					//filter directly and indirectly supervised cases.
					List filteredCases = filterCasesByAssignedFeature(responseEvent, programUnits);
					if (filteredCases != null && !filteredCases.isEmpty()){
						String caseNum = "";
						for (int g = 0; g < filteredCases.size(); g++){
							CaseAssignmentTO cato = (CaseAssignmentTO) filteredCases.get(g);
							if (cato.getCriminalCaseId() != null && !cato.getCriminalCaseId().equals("")){
								caseNum = cato.getCriminalCaseId().substring(3);
								cato.setDisplayCaseNum(caseNum);
							}
						}	
					}
					responseEvent.setCaseAssignments(filteredCases);
				}	
				responseEvent = null;
			}
		}
		event = null;
		return casesBySupervisee;
	}

	private List filterCasesByAssignedFeature(CaseAssignmentResponseEvent responseEvent, Map programUnits) {
		List filteredCases = null;
		List cases = responseEvent.getCaseAssignments();
		if (cases != null && cases.size() > 0) {
			ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
			if (securityManager !=  null){
				Set userFeatures = securityManager.getFeatures(); 
				String userType = securityManager.getIUserInfo().getUserTypeId();
				if (userType.equals("MA") || userType.equals("SA") || userFeatures.contains("CSCD-CASELOAD-ADMIN")) {
					filteredCases = cases; //MA & SA can see all cases.
				} else if ((userFeatures.contains("CSCD-CASELOAD-OM") || userFeatures.contains("CSCD-CASELOAD-CSO"))) {
					filteredCases = filterDirectlySupervisedCases(cases);
					//select the cases belonging to user's division.  
					filteredCases = filterSuperviseeCasesByUserDivision(filteredCases, programUnits);				
				} else if (userFeatures.contains("CSCD-CASELOAD-CLO")) { 
					filteredCases = filterIndirectlySupervisedCases(cases);
					//select the cases belonging to user's division. 
					filteredCases = filterSuperviseeCasesByUserDivision(filteredCases, programUnits);				
				} else {
					filteredCases = filterSuperviseeCasesByUserDivision(cases, programUnits);								
				}
			} else {
				filteredCases = new ArrayList();
			}
		}
		cases = null;
		return filteredCases;
	}
	
	private List filterCasesByAssignedFeature(CaseAssignmentResponseEvent responseEvent) {
		List filteredCases = null;
		List cases = responseEvent.getCaseAssignments();
		if (cases != null && cases.size() > 0) {
			ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
			Set userFeatures = securityManager.getFeatures(); 
			String userType = securityManager.getIUserInfo().getUserTypeId();
			if (userType.equals("MA") || userType.equals("SA") || userFeatures.contains("CSCD-CASELOAD-ADMIN")) {
				filteredCases = cases; //MA & SA can see all cases.
			} else if ((userFeatures.contains("CSCD-CASELOAD-OM") || userFeatures.contains("CSCD-CASELOAD-CSO"))) {
				filteredCases = filterDirectlySupervisedCases(cases);
				//select the cases belonging to user's division.  
				filteredCases = filterSuperviseeCasesByUserDivision(filteredCases);				
			} else if (userFeatures.contains("CSCD-CASELOAD-CLO")) { 
				filteredCases = filterIndirectlySupervisedCases(cases);
				//select the cases belonging to user's division. 
				filteredCases = filterSuperviseeCasesByUserDivision(filteredCases);				
			} else {
				filteredCases = filterSuperviseeCasesByUserDivision(cases);								
			}
		}
		cases = null;
		return filteredCases;
	}

	private List filterDirectlySupervisedCases(List cases) {
		List filteredCases = new ArrayList();
		for (Iterator iterator = cases.iterator(); iterator.hasNext();) {
			ICaseAssignment activeCase = (ICaseAssignment) iterator.next();
				// Removed Direct filter RRY
				filteredCases.add(activeCase);

		}
		return filteredCases;
	}

	private List filterIndirectlySupervisedCases(List cases) {
		List filteredCases = new ArrayList();
		for (Iterator iterator = cases.iterator(); iterator.hasNext();) {
			ICaseAssignment activeCase = (ICaseAssignment) iterator.next();
				// remove indirect filter RRY
				filteredCases.add(activeCase);
		}
		return filteredCases;
	}
	
	private List filterSuperviseeCasesByUserDivision(List cases, Map programUnits) {		
		List filteredCases = new ArrayList();
		String programUnitId = null; 
		for (Iterator iterator = cases.iterator(); iterator.hasNext();) {
			ICaseAssignment activeCase = (ICaseAssignment) iterator.next();
			programUnitId = activeCase.getProgramUnitId();
			if (programUnits.containsKey(programUnitId)) {
				filteredCases.add(activeCase);
			}
		}
		return filteredCases;	
	}
	
	private List filterSuperviseeCasesByUserDivision(List cases) {
		String divisionIdOfUser = getDivisionIdOfUser(); 
		List filteredCases = new ArrayList();
		String programUnitId = null; 
		String divisionId = null;
		for (Iterator iterator = cases.iterator(); iterator.hasNext();) {
			ICaseAssignment activeCase = (ICaseAssignment) iterator.next();
			programUnitId = activeCase.getProgramUnitId();
			divisionId = getDivisionIdOfProgramUnit(programUnitId);				
			if (divisionIdOfUser != null && divisionIdOfUser.equalsIgnoreCase(divisionId)) {
				filteredCases.add(activeCase);
			}
		}
		return filteredCases;		
	}

	private String getDivisionIdOfUser() {
		String divisionId = null;
		GetLightCSCDStaffForUserEvent ev = new GetLightCSCDStaffForUserEvent();
		ev.setLogonId(SecurityUIHelper.getLogonId());		
		LightCSCDStaffResponseEvent staffResponseEvent = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(ev, LightCSCDStaffResponseEvent.class);

		if(staffResponseEvent != null){
			divisionId = staffResponseEvent.getDivisionId();	
			if (divisionId == null) {//Use other means to get division id.
				String programUnitId = staffResponseEvent.getProgramUnitId();
				divisionId = getDivisionIdOfProgramUnit(programUnitId);
			}			
		}
		return divisionId;
	}
	
	private String getDivisionIdOfProgramUnit(String programUnitId) {
		GetProgramUnitEvent requestEvent = new GetProgramUnitEvent();
		requestEvent.setProgramUnitId(programUnitId);
		
		GetProgramUnitResponseEvent responseEvent = (GetProgramUnitResponseEvent) MessageUtil.postRequest(
				requestEvent, GetProgramUnitResponseEvent.class);
		return responseEvent.getOrganizationTO().getParentOrganizationId();
	}
	
	public List searchCasesByQuadrantId(String quardantId) {
		GetCaseloadEvent event = (GetCaseloadEvent) EventFactory
				.getInstance(CaseloadControllerServiceNames.GETCASELOAD);
		event.setWorkflowInd(CaseloadConstants.SEARCH_CASELOAD_BY_QUADRANT);
		event.setQuadrantId(quardantId);
		List casesBySupervisee = MessageUtil.postRequestListFilter(event, CaseAssignmentResponseEvent.class);
		
		event = null;
		return casesBySupervisee;
	}
	
	public List searchCasesByZipCode(String zipCode) {
		GetCaseloadEvent event = (GetCaseloadEvent) EventFactory
				.getInstance(CaseloadControllerServiceNames.GETCASELOAD);
		event.setWorkflowInd(CaseloadConstants.SEARCH_CASELOAD_BY_ZIPCODE);
		event.setZipCode(zipCode);
		List casesBySupervisee = MessageUtil.postRequestListFilter(event, CaseAssignmentResponseEvent.class);
		return casesBySupervisee;
	}
}
