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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.GetSuperviseeByDefendantIdEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administercaseload.reply.LightSuperviseeResponseEvent;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.administersupervisee.GetTransfersEvent;
import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import messaging.organization.GetProgramUnitEvent;
import messaging.organization.reply.GetProgramUnitResponseEvent;
import messaging.supervisionorder.GetUnfinishedOrdersEvent;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
import messaging.transfers.reply.TransferResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.Name;
import naming.PDCodeTableConstants;
import naming.SuperviseeControllerServiceNames;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;

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

/**
 * @author cc_rbhat
 *  
 */
public class HandleSuperviseeSearchAction extends JIMSBaseAction {
	private static String FWD_RESULTS_BY_ID = "searchResultsByDefendantId";

	private static String FWD_REVIEW_ACTIVE_CASES = "reviewActiveCases";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.searchByDefendantId", "defendantSearchById");
		keyMap.put("button.reassign", "reviewSuperviseeActiveCases");
		keyMap.put("button.requestReassignment", "reviewSuperviseeActiveCases");
		keyMap.put("button.closeCase", "reviewSuperviseeActiveCasesToClose");
		keyMap.put("button.requestReassignment", "reviewSuperviseeActiveCases");	
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
	public ActionForward defendantSearchById(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		
		caseAssignmentForm.getOfficerCaseload().clear();
		
		OfficerCaseload caseloadDetail = caseAssignmentForm.getOfficerCaseload();
		
		String creditStaff = "";
        String programUnitId = "";
		
		GetSuperviseeByDefendantIdEvent reqEvent = new GetSuperviseeByDefendantIdEvent();		
		reqEvent.setDefendantId( caseAssignmentForm.getDefendantId() );
		
		LightSuperviseeResponseEvent supResponse = (LightSuperviseeResponseEvent) 
									MessageUtil.postRequest( reqEvent , LightSuperviseeResponseEvent.class );
		
		if ( supResponse != null ){
			
			creditStaff = supResponse.getCreditStaffPositionId();
			programUnitId = supResponse.getAssignedProgramUnitId();
			
			if ( creditStaff == null ){
				
				sendToErrorPage(request, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Case not appropriately assigned.  Contact your supervisor.");
				return mapping.findForward(FWD_RESULTS_BY_ID);
				
			}
		}
		
		CaseAssignmentResponseEvent responseEvent = getDefendant( caseAssignmentForm.getDefendantId(), request );
		if (responseEvent != null) {
			String caseId = "";
			List activeCases = responseEvent.getCaseAssignments();
	        int noOfCases = activeCases.size();
	        String[] casesSelectedForReassignment = new String[noOfCases];
	        ICaseAssignment activeCase = null;		       
	        int caseIndex = 0;
	        for (Iterator caseIterator = activeCases.iterator(); caseIterator.hasNext();) {
	        	activeCase = (ICaseAssignment) caseIterator.next();
	        	caseId = activeCase.getCriminalCaseId();
	        	
	        	if ( caseId != null ){
	        		
	        		caseId = caseId.substring( 3 );
	        		activeCase.setDisplayCaseNum( caseId );
	        	}
       	
	        	LightCSCDStaffResponseEvent assignOffResp = getStaffPosition( activeCase.getAssignedStaffPositionId() );
				
	        	if ( assignOffResp != null ){
	        		
	        		activeCase.setOfficerName( new NameBean( null, null, assignOffResp.getOfficerName() ));
	        		activeCase.setProgramUnitName( assignOffResp.getDivisionName() );
	        	}
	        	
	        	casesSelectedForReassignment[caseIndex++] = "";
	        }
	        //In a program unit, all the cases of a supervisee are assigned to only one officer.
	        String supervisorNameStr = "";
	        String assignedOfficerName = "";        
	        String supervisorPositionId = "";
	        Name supervisorName = null;
	        IName supervisorsName = new NameBean();
	        
	        LightCSCDStaffResponseEvent supResp = getStaffPosition( creditStaff );
	        
	        if ( supResp != null ){
	        	
	        	supervisorNameStr = supResp.getSupervisorNameQualifiedByPosition();
	    		assignedOfficerName = supResp.getOfficerNameQualifiedByPosition();
	    		supervisorPositionId = supResp.getSupervisorPositionId();
	    		supervisorName = supResp.getSupervisorName();
	        }
    		
	        SearchSupervisee search = new SearchSupervisee();
	        List caseload = search.searchCasesByOfficerPositionId( creditStaff , programUnitId );	                	
    		
	        if (caseload != null && caseload.size() != 0) {
        		int noOfSuperivsees = caseload.size();
        		int caseCt = 0;
        		for (Iterator iterator = caseload.iterator(); iterator.hasNext();) {
        			CaseAssignmentResponseEvent care = (CaseAssignmentResponseEvent) iterator.next();
        			caseCt += care.getCaseAssignments().size();
        		}
        		caseloadDetail.setTotalSuperviseesInCaseload(String.valueOf(noOfSuperivsees));
        		caseloadDetail.setToalCasesInCaseload(String.valueOf(caseCt));
    		}		  
	        
	      //In a program unit, all the cases of a supervisee are assigned to only one officer.
	        String officerPositionId = activeCase.getAssignedStaffPositionId();
	        String programUnitName = activeCase.getProgramUnitName();
	        
	        caseAssignmentForm.setProgramUnitId(programUnitId);
	        caseAssignmentForm.setSupervisorPositionId(supervisorPositionId);
	        
	        if ( supervisorName != null ){
	        	
	        	supervisorsName = new NameBean(supervisorName.getFirstName(), 
		        					supervisorName.getMiddleName(), supervisorName.getLastName());
		        caseAssignmentForm.setSupervisorName( supervisorsName );
	        }
	        
	        caseAssignmentForm.setOfficerPositionId(officerPositionId);
	        caseAssignmentForm.setProgramUnitName(programUnitName);
	        
	        caseloadDetail.setSelectedSupervisorName( supervisorNameStr );
	        caseloadDetail.setSelectedOfficerId( officerPositionId );
	        caseloadDetail.setSelectedSupervisorId( supervisorPositionId );
	        caseAssignmentForm.setOfficerNameStr( assignedOfficerName );
	        
	        caseAssignmentForm.setDaysLeft(responseEvent.getDaysLeft());
	        caseAssignmentForm.setActiveCases(activeCases);
	        caseAssignmentForm.setCaseAssignments(activeCases);
	        
	        caseAssignmentForm.setDefendantId(responseEvent.getDefendantId());
	        caseAssignmentForm.setSuperviseeNameStr(responseEvent.getDefendantFullName());
	        caseAssignmentForm.setLevelOfSupervision(responseEvent.getLevelOfSupervision());
	        caseAssignmentForm.setJailIndicator(responseEvent.getJailIndicator());
	        caseAssignmentForm.setWarrantIndicator(responseEvent.getWarrantIndicator());
	        caseAssignmentForm.setCasesSelectedForReassignment(casesSelectedForReassignment);
	        
	        String dateFormat = "MM/dd/yyyy";
	        String lastFaceToFaceDate = DateUtil.dateToString(responseEvent.getLastFaceToFaceDate(), dateFormat);
	        String nextOfficeVisitDate = DateUtil.dateToString(responseEvent.getNextOfficeVisitDate(), dateFormat);
	        caseAssignmentForm.setLastFaceToFaceDate(lastFaceToFaceDate);
	        caseAssignmentForm.setNextOfficeVisitDate(nextOfficeVisitDate);
	        
	}
        return mapping.findForward(FWD_RESULTS_BY_ID);
	}
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward reviewSuperviseeActiveCases(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		caseAssignmentForm.setCloseCases(false);
		return mapping.findForward(FWD_REVIEW_ACTIVE_CASES);
	}
	
	public ActionForward reviewSuperviseeActiveCasesToClose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;
		
		String[] casesSelectedToClose = caseAssignmentForm.getCasesSelectedForReassignment();

		List activeCases = caseAssignmentForm.getCaseAssignments();
		for (int index = 0; index < casesSelectedToClose.length; index++) {			
			for (Iterator iterator = activeCases.iterator(); iterator.hasNext();)
			{
				CaseAssignmentTO activeCase = (CaseAssignmentTO) iterator.next();
				
				if (activeCase.getCaseAssignmentId().equalsIgnoreCase(casesSelectedToClose[index]))
				{
					String criminalCaseId = activeCase.getCriminalCaseId();
					if((criminalCaseId.startsWith(PDCodeTableConstants.CSCD, 0)) || (criminalCaseId.startsWith(PDCodeTableConstants.PTS, 0)))
					{
						sendToErrorPage(request, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Please select only Harris County Cases to close");
						return mapping.findForward(FWD_RESULTS_BY_ID);
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
			            return mapping.findForward(FWD_RESULTS_BY_ID);
					}

				}
			}

		}
		
		caseAssignmentForm.setCloseCases(true);
		return mapping.findForward(FWD_REVIEW_ACTIVE_CASES);
	}

	private CaseAssignmentResponseEvent getDefendant(String defendantId, HttpServletRequest request) {
		SearchSupervisee search = new SearchSupervisee();
		
		GetLightCSCDStaffForUserEvent gEvent = new GetLightCSCDStaffForUserEvent();
		gEvent.setLogonId(SecurityUIHelper.getLogonId());
		CompositeResponse resp = MessageUtil.postRequest(gEvent);
		
		LightCSCDStaffResponseEvent response = (LightCSCDStaffResponseEvent) MessageUtil.filterComposite(resp, LightCSCDStaffResponseEvent.class);
		String divisionId = "";
		if(response != null){
			divisionId = response.getDivisionId();
		}
		
		CaseAssignmentResponseEvent responseEvent = search.searchCasesByDefendantId(defendantId, divisionId);		
		if (responseEvent == null || 
				responseEvent.getCaseAssignments() == null || 
				responseEvent.getCaseAssignments().size() == 0) {
        	sendToErrorPage(request, "error.administerCaseload.defendantNotFound");
        	return null;
		} else {
			return responseEvent;
		}
	}
	
	private String getDivisionIdOfProgramUnit(String programUnitId) {
		GetProgramUnitEvent requestEvent = new GetProgramUnitEvent();
		requestEvent.setProgramUnitId(programUnitId);
		
		GetProgramUnitResponseEvent responseEvent = (GetProgramUnitResponseEvent) MessageUtil.postRequest(
				requestEvent, GetProgramUnitResponseEvent.class);
		return responseEvent.getOrganizationTO().getParentOrganizationId();
	}
	
	public ActionForward transferIn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;	
		SuperviseeTransferCasesInfo superviseeTransferCases = caseAssignmentForm.getSuperviseeTransferCases();
		List allCases = caseAssignmentForm.getActiveCases();
		List casesToTransfer = new ArrayList(); 

		for (int i = 0; i < caseAssignmentForm.getCasesSelectedForReassignment().length; i++) {
			String selectedCaseOid = (String) caseAssignmentForm.getCasesSelectedForReassignment()[i];
			for (int j = 0; j < allCases.size(); j++) {
				CaseAssignmentTO caseAssignment = (CaseAssignmentTO) allCases.get(j);
				if (caseAssignment.getCaseAssignmentId().equals(selectedCaseOid)){
					if (caseAssignment.getCriminalCaseId().startsWith("010")){
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","Out of County case cannot be transferred"));		
						saveErrors(request, errors);
					}
					casesToTransfer.add(caseAssignment);
				}
			}
		}
		superviseeTransferCases.setCasesToTransfer(casesToTransfer);
		caseAssignmentForm.setActiveCases(casesToTransfer);
		superviseeTransferCases.setTransferType(UIConstants.CS_TRANSFER_IN_CASES);
		return mapping.findForward("transferIn");
	}
	
// coding in this method needs to be optimized and is not fully tested as of 3/11/09
	public ActionForward transferOut(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) form;				
		SuperviseeTransferCasesInfo superviseeTransferCases = caseAssignmentForm.getSuperviseeTransferCases();
		List allCases = caseAssignmentForm.getActiveCases();
		List casesToTransfer = new ArrayList(); 
		for (int i = 0; i < caseAssignmentForm.getCasesSelectedForReassignment().length; i++) {
			String selectedCaseOid = (String) caseAssignmentForm.getCasesSelectedForReassignment()[i];
			for (int j = 0; j < allCases.size(); j++) {
				CaseAssignmentTO caseAssignment = (CaseAssignmentTO) allCases.get(j);
				if (caseAssignment.getCaseAssignmentId().equals(selectedCaseOid)){
					casesToTransfer.add(caseAssignment);
				}
			}
		}
		if (casesToTransfer != null && !casesToTransfer.isEmpty()){
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			GetTransfersEvent sps = (GetTransfersEvent)EventFactory.getInstance(SuperviseeControllerServiceNames.GETTRANSFERS);		
			sps.setSuperviseeId(caseAssignmentForm.getDefendantId()); 
			sps.setSearchType("C");
			dispatch.postEvent(sps);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Collection transfers = MessageUtil.compositeToCollection(compositeResponse, TransferResponseEvent.class);
			if (transfers != null && !transfers.isEmpty()){
				String cdiCase = "";
				Iterator itr = transfers.iterator();
				while (itr.hasNext()){
					TransferResponseEvent evt = (TransferResponseEvent)itr.next();
					cdiCase = evt.getCdi() + evt.getCaseNum();
					for (int x=0; x < casesToTransfer.size(); x++){
						CaseAssignmentTO caseAssignment = (CaseAssignmentTO) casesToTransfer.get(x);
						if (cdiCase.equals(caseAssignment.getCriminalCaseId()) && !"".equals(evt.getHcTransferOutDate()) ){
							ActionErrors errors = new ActionErrors();
							String errMsg = "Case " + caseAssignment.getCriminalCaseId().toString() + " is already transferred out";
							errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",errMsg));		
							saveErrors(request, errors);
							return mapping.findForward(UIConstants.FAILURE);
						}
					}
				}
			}
		}
		superviseeTransferCases.setCasesToTransfer(casesToTransfer);
		caseAssignmentForm.setActiveCases(casesToTransfer);
		superviseeTransferCases.setTransferType(UIConstants.CS_TRANSFER_OUT_CASES);
		return mapping.findForward("reviewActiveCases");
	}
	
	private LightCSCDStaffResponseEvent getStaffPosition( String positionId ){
		
		GetLightCSCDStaffForUserEvent gEvent = new GetLightCSCDStaffForUserEvent();
		
		gEvent.setStaffPositionId ( positionId );
        gEvent.setOfficerNameNeeded( true );
        gEvent.setSupervisorNameNeeded( true );
        
        LightCSCDStaffResponseEvent supResp = (LightCSCDStaffResponseEvent) 
					MessageUtil.postRequest( gEvent, LightCSCDStaffResponseEvent.class );

        return supResp;
	}
}
