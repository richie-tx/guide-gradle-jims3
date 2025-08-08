/*
 * Created on April 17, 2008
 *
 */
package ui.supervision.cscdcalendar.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.GetLightSupervisorsEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.common.StringUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.helper.SearchSupervisee;
import ui.supervision.cscdcalendar.form.CSCalendarDisplayForm;

/**
 * @author awidjaja This action will fetch a list of Supervisor, with
 *         corresponding officers and have the current logged on user
 *         pre-selected. It will also have the caseload for the officer, with
 *         the list of supervisee associated to that caseload
 */
public class DisplayCaseloadSearchResultsAction extends JIMSBaseAction {

	public ActionForward createFieldVisit(ActionMapping mapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {
		return mapping.findForward("createFieldVisit");
	}

	public ActionForward createOfficeVisit(ActionMapping mapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {
		return mapping.findForward("createOfficeVisit");
	}

	public ActionForward createGroupOfficeVisit(ActionMapping mapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {
		return mapping.findForward("createGroupOfficeVisit");
	}

	public ActionForward getSupervisors(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CSCalendarDisplayForm calendarDisplayForm = (CSCalendarDisplayForm) form;
		String selectedDivisionId = calendarDisplayForm.getSelectedDivisionId();
//		calendarDisplayForm.setSupervisorList(new ArrayList());
		if (selectedDivisionId != null && selectedDivisionId.length() != 0) {

	        GetLightSupervisorsEvent glEvent = new GetLightSupervisorsEvent();
			glEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
			glEvent.setDivisionId(selectedDivisionId);
			CompositeResponse res = MessageUtil.postRequest(glEvent);
			
			List list = MessageUtil.compositeToList(res, CSCDSupervisionStaffResponseEvent.class);
			List supervisorsInDivision = new ArrayList();
			if(list != null && !list.isEmpty()){
				for(int i=0;i<list.size();i++){
					CSCDSupervisionStaffResponseEvent org = (CSCDSupervisionStaffResponseEvent) list.get(i);
					if(org.getPositionTypeId() != null && !PDCodeTableConstants.STAFF_POSITION_TYPE_OFFICER.equals(org.getPositionTypeId())){
						supervisorsInDivision.add(org);
					}
				}
			}
			
			if (supervisorsInDivision == null
					|| supervisorsInDivision.size() == 0) {
				sendToErrorPage(request,
						"error.administerCaseload.supervisorsInDivisionNotFound");
			}
			calendarDisplayForm.setSupervisorList(supervisorsInDivision);
			calendarDisplayForm.setOfficerList(null);
			calendarDisplayForm.setDefendantsSupervised(null);
			calendarDisplayForm.setSelectedSupervisorId("");
			calendarDisplayForm.setSelectedOfficerId("");
			calendarDisplayForm.setSuperviseeId("");
			calendarDisplayForm.setCaseSuperviseeId("");
			calendarDisplayForm.setCourtIdFilter("");
			calendarDisplayForm.setZipCode("");
		}
		calendarDisplayForm.setQuadrantSearch( "CASELOAD" );
		return mapping.findForward("refreshSuccess");
	}

	public ActionForward getOfficers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CSCalendarDisplayForm calendarDisplayForm = (CSCalendarDisplayForm) form;
		String supervisorPositionId = calendarDisplayForm
				.getSelectedSupervisorId();

		if (supervisorPositionId != null && supervisorPositionId.length() != 0) {
	        GetLightSupervisorsEvent glEvent = new GetLightSupervisorsEvent();
			glEvent.setSupervisorId(supervisorPositionId);
			glEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());	
			CompositeResponse res = MessageUtil.postRequest(glEvent);
			
			List list = MessageUtil.compositeToList(res, CSCDSupervisionStaffResponseEvent.class);
			List officersUnderSupervisor = new ArrayList();
			if(list != null && !list.isEmpty()){
				for(int i=0;i<list.size();i++){
					CSCDSupervisionStaffResponseEvent org = (CSCDSupervisionStaffResponseEvent) list.get(i);
					if(org.getPositionTypeId() != null && PDCodeTableConstants.STAFF_POSITION_TYPE_OFFICER.equals(org.getPositionTypeId())){
						officersUnderSupervisor.add(org);
					}
				}
			}

			if (officersUnderSupervisor == null
					|| officersUnderSupervisor.size() == 0) {
				sendToErrorPage(request,
						"error.administerCaseload.officersUnderSupervisiorNotFound");
			}
			calendarDisplayForm.setOfficerList(officersUnderSupervisor);

			calendarDisplayForm.setSuperviseeId("");
			calendarDisplayForm.setCaseSuperviseeId("");

			calendarDisplayForm.setDefendantsSupervised(null);
			calendarDisplayForm.setSelectedOfficerId("");
		}
		calendarDisplayForm.setQuadrantSearch( "CASELOAD" );
		return mapping.findForward("refreshSuccess");
	}

	private List getFilteredCaseload( List caseload, String courtIdFilter, String zip ) {
		boolean courtFilterFlag = (courtIdFilter != null && courtIdFilter.length() != 0) ? true
				: false;
		boolean zipFilterFlag = ( zip != null && zip.length() != 0) ? true
				: false;
		List filteredCaseload = new ArrayList();
		if ( courtFilterFlag  && zipFilterFlag ) {
			// now apply court id filter and zip code filter.
			for (Iterator iterator = caseload.iterator(); iterator.hasNext();) {
				CaseAssignmentResponseEvent responseEvent = (CaseAssignmentResponseEvent) iterator
						.next();
				for (Iterator iter = responseEvent.getCaseAssignments()
						.iterator(); iter.hasNext();) {
					ICaseAssignment caseAssignment = (ICaseAssignment) iter
							.next();
					if (caseAssignment.getCourtId().equals(courtIdFilter) && zip.equals( responseEvent.getZipCode())) {
						filteredCaseload.add(responseEvent);
					}
				}
			}
		} else if ( courtFilterFlag ) {
			// now apply court id filter.
			for (Iterator iterator = caseload.iterator(); iterator.hasNext();) {
				CaseAssignmentResponseEvent responseEvent = (CaseAssignmentResponseEvent) iterator
						.next();
				for (Iterator iter = responseEvent.getCaseAssignments()
						.iterator(); iter.hasNext();) {
					ICaseAssignment caseAssignment = (ICaseAssignment) iter
							.next();
					if (caseAssignment.getCourtId().equals(courtIdFilter)) {
						filteredCaseload.add(responseEvent);
					}
				}
			}
		} else if ( zipFilterFlag ) {
			// now apply Zip Code filter.
			for (Iterator iterator = caseload.iterator(); iterator.hasNext();) {
				CaseAssignmentResponseEvent responseEvent = (CaseAssignmentResponseEvent) iterator.next();
				
					if ( zip.equals( responseEvent.getZipCode().trim())) {
							filteredCaseload.add(responseEvent);
					}
				}
		}else {
				
			filteredCaseload = caseload;
		}
		return filteredCaseload;
	}
	
	public ActionForward getOfficerCaseload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{
		CSCalendarDisplayForm calendarDisplayForm = (CSCalendarDisplayForm) form;
		
        String supervisorId = calendarDisplayForm.getSelectedSupervisorId();
        if (supervisorId != null && supervisorId.length() != 0) {
            String officerId = calendarDisplayForm.getSelectedOfficerId(); 
            String positionId = null;
            if (officerId == null || officerId.length() == 0) {
            	positionId = supervisorId;
            } else {
            	positionId = officerId;
            }            
            String courtIdFilter = calendarDisplayForm.getCourtIdFilter();
            String zipCodeFilter = calendarDisplayForm.getZipCode();

				//retrieve supervisor name
       		GetLightCSCDStaffForUserEvent gEvent = new GetLightCSCDStaffForUserEvent();
       		gEvent.setStaffPositionId( positionId );
       		gEvent.setOfficerNameNeeded(true);
       		gEvent.setSupervisorNameNeeded(true);
       		LightCSCDStaffResponseEvent resp = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(gEvent, LightCSCDStaffResponseEvent.class);

    		calendarDisplayForm.setSelectedSupervisorName(resp.getSupervisorNameQualifiedByPosition());
            
				//retrieve officer name
    		resp.setStaffPositionId(positionId);    	
    		calendarDisplayForm.setSelectedOfficerName(resp.getOfficerNameQualifiedByPosition());
            
            SearchSupervisee search = new SearchSupervisee();
    		List caseload = search.searchCasesByOfficerPositionId(positionId, resp.getDivisionId());
    		if (caseload != null && caseload.size() != 0) {
        		int noOfSuperivsees = caseload.size();
        		int noOfCases = 0;
        		for (Iterator iterator = caseload.iterator(); iterator.hasNext();) {
        			CaseAssignmentResponseEvent responseEvent = (CaseAssignmentResponseEvent) iterator.next();
        			noOfCases += responseEvent.getCaseAssignments().size();
        		}
        		calendarDisplayForm.setTotalSuperviseesInCaseload(String.valueOf(noOfSuperivsees));
        		calendarDisplayForm.setTotalCasesInCaseload(String.valueOf(noOfCases));
        		
        		if ( !"".equals( courtIdFilter )){
        			
        			caseload = getFilteredCaseload(caseload, courtIdFilter);
        		}
        		
        		if ( !"".equals(zipCodeFilter)){
        			
        			caseload = getFilteredCaseload( caseload, courtIdFilter, zipCodeFilter);
        		}
        		
        		if (caseload != null && caseload.size() != 0) {
        			calendarDisplayForm.setDefendantsSupervised(caseload);
        		} else {
        			sendToErrorPage(request, "error.administerCaseload.superviseeInCourtNotFound");	
        			calendarDisplayForm.setDefendantsSupervised(caseload);
        		}		    			
    		} else {
    			calendarDisplayForm.setDefendantsSupervised(caseload);
    			sendToErrorPage(request, "error.administerCaseload.superviseeUnderOfficerNotFound");	        	        	    			
    		}
        } else {
			sendToErrorPage(request, "error.administerCaseload.superviseeUnderOfficerNotFound");	        	        	
        }
        calendarDisplayForm.setQuadrantSearch( "CASELOAD" );
		return mapping.findForward("refreshSuccess");
	}
	
	private List getFilteredCaseload(List caseload, String courtIdFilter) {
		boolean filterFlag = (courtIdFilter != null && courtIdFilter.length() != 0) ? true : false;
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
	
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CSCalendarDisplayForm form = (CSCalendarDisplayForm) aForm;
		
		List spnCases = new ArrayList();
		form.setDefendantsSupervised(new ArrayList());
		form.setSearchBySPNResult(null);
		form.setOfficerCaseload(null);
		CaseAssignmentResponseEvent caseAssignmentRE = null;
		SearchSupervisee helper = new SearchSupervisee();		
		if(form.getSoZipCode() != null && !"".equals(form.getSoZipCode())){
			form.setZipCode(form.getSoZipCode());
			form.setSoZipCode("");
			form.setQuadrantSearch( "SOZIP" );
		}
		if(form.getZipCode() != null && !"".equals(form.getZipCode())){
			List caseload = helper.searchCasesByZipCode(form.getZipCode());
			form.setDefendantsSupervised(caseload);
			if (caseload != null && caseload.size() != 0) {
	    		int noOfSuperivsees = caseload.size();
	    		int noOfCases = 0;
	    		for (Iterator iterator = caseload.iterator(); iterator.hasNext();) {
	    			CaseAssignmentResponseEvent responseEvent = (CaseAssignmentResponseEvent) iterator.next();
	    			noOfCases += responseEvent.getCaseAssignments().size();
	    		}
	    		form.setTotalSuperviseesInCaseload(String.valueOf(noOfSuperivsees));
	    		form.setTotalCasesInCaseload(String.valueOf(noOfCases));
	    		
	    		if (caseload != null && caseload.size() != 0) {
	    			 	    			 
	    			form.setSelectedDivisionId(null);
	    			form.setSelectedOfficerId(null);
	    			
	    		} else {
	    			sendToErrorPage(aRequest, "error.administerCaseload.superviseeInCourtNotFound");	
	    		}		    			
			} else {
				sendToErrorPage(aRequest, "error.administerCaseload.superviseeInZipNotFound");	        	        	    			
			}
		}else{
			String defendant_id = form.getSuperviseeId();
			form.setQuadrantSearch( "SPN" );
			if (StringUtil.isEmpty(defendant_id)) {
				defendant_id = "" + form.getCaseSuperviseeId();
			}			
			while(defendant_id.length() < 8){
				defendant_id = "0" + defendant_id;
			}
			form.clear();
			caseAssignmentRE = helper.searchCasesByDefendantId(defendant_id);
			spnCases.add( caseAssignmentRE );
			if (caseAssignmentRE == null) {
				sendToErrorPage(aRequest, "error.administerCaseload.defendantNotFound");
				spnCases = new ArrayList();
			}
			if (caseAssignmentRE != null && caseAssignmentRE.getCaseAssignments() != null && caseAssignmentRE.getCaseAssignments().size() == 0) {
				sendToErrorPage(aRequest, "error.administerCaseload.defendantNotFound");
				spnCases = new ArrayList();
			}

			form.setDefendantsSupervised( spnCases );
		}
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CSCalendarDisplayForm csCalendarDisplayForm = (CSCalendarDisplayForm) aForm;
		
		csCalendarDisplayForm.setCourtIdFilter("");
		csCalendarDisplayForm.setZipCode("");
		ActionForward forward = aMapping
				.findForward(UIConstants.REFRESH_SUCCESS);
		return forward;
	}

	public ActionForward resetOfficerCaseload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CSCalendarDisplayForm csCalendarDisplayForm = (CSCalendarDisplayForm) form;

		if (((String) request.getParameter("divisionSelected")) != null) {
			csCalendarDisplayForm.setSupervisorList(new ArrayList());
		}
		csCalendarDisplayForm.setSuperviseeId("");
		csCalendarDisplayForm.setCaseSuperviseeId("");
		csCalendarDisplayForm.setOfficerList(new ArrayList());
		csCalendarDisplayForm.setDefendantsSupervised(new ArrayList());
		csCalendarDisplayForm.setSelectedOfficerId("");

		csCalendarDisplayForm.setQuadrantSearch( "CASELOAD" );
		return mapping.findForward("refreshSuccess");
	}
	
	public ActionForward viewSupervisees(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response ) {
		CSCalendarDisplayForm calendarDisplayForm = (CSCalendarDisplayForm) form;

        SearchSupervisee search = new SearchSupervisee();
		List caseload = search.searchCasesByQuadrantId(calendarDisplayForm.getSelectedQuadrantId());
		if (caseload != null && caseload.size() != 0) {
    		int noOfSuperivsees = caseload.size();
    		int noOfCases = 0;
    		for (Iterator iterator = caseload.iterator(); iterator.hasNext();) {
    			CaseAssignmentResponseEvent responseEvent = (CaseAssignmentResponseEvent) iterator.next();
    			noOfCases += responseEvent.getCaseAssignments().size();
    		}
    		calendarDisplayForm.setTotalSuperviseesInCaseload(String.valueOf(noOfSuperivsees));
    		calendarDisplayForm.setTotalCasesInCaseload(String.valueOf(noOfCases));
    		
    		if (caseload != null && caseload.size() != 0) {
    			calendarDisplayForm.setDefendantsSupervised(caseload);
    			calendarDisplayForm.setSelectedDivisionId(null);
    			calendarDisplayForm.setSelectedOfficerId(null);
    		} else {
    			sendToErrorPage(request, "error.administerCaseload.superviseeInCourtNotFound");	
    			calendarDisplayForm.setDefendantsSupervised(caseload);
    		}		    			
		} else {
			calendarDisplayForm.setDefendantsSupervised(caseload);
			calendarDisplayForm.setTotalSuperviseesInCaseload(String.valueOf(0));
			sendToErrorPage(request, "error.administerCaseload.superviseeUnderOfficerNotFound");	        	        	    			
		}
		calendarDisplayForm.setQuadrantSearch( "SOQUAD" );
		calendarDisplayForm.setQuadrantDescription( getQuadrantDesc( calendarDisplayForm.getSelectedQuadrantId()) ) ;
		return mapping.findForward("viewSuperviseesSuccess");
	}
	
	private String getQuadrantDesc( String quadrantCd ) {
		
		List quadrantList = ComplexCodeTableHelper.getSupervisionCodes(
				SecurityUIHelper.getUserAgencyId(), PDCodeTableConstants.QUADRANT);
		
		return ComplexCodeTableHelper.getDescrBySupervisionCode( quadrantList,
				quadrantCd);
	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.submit", "submit");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.getSupervisors", "getSupervisors");
		keyMap.put("button.getOfficers", "getOfficers");
		keyMap.put("button.viewCaseload", "getOfficerCaseload");
		keyMap.put("button.viewSupervisees", "viewSupervisees");
		keyMap.put("button.clear", "resetOfficerCaseload");
		keyMap.put("button.createFieldVisit", "createFieldVisit");
		keyMap.put("button.createOfficeVisit", "createOfficeVisit");
		keyMap.put("button.createGroupOfficeVisit", "createGroupOfficeVisit");
	}
}
