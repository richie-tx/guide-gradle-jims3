/*
 * Created on Feb 6, 2008
 *
 */
package ui.supervision.cscdcalendar.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.security.ISecurityManager;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercaseload.helper.SearchSupervisee;
import ui.supervision.cscdcalendar.form.CSCalendarDisplayForm;
import ui.supervision.cscdcalendar.form.CSCalendarFVForm;

/**
 * @author awidjaja
 * This action will fetch a list of Supervisor, with corresponding
 * officers and have the current logged on user pre-selected.
 * It will also have the caseload for the officer, with the list
 * of supervisee associated to that caseload
 * 
 * The following values need to be set in CSCalendarSearchForm:
 * - activityFlow: create/addAttendees
 * - selectedEventTypeCd: OV, FV, GV
 * 
 * Currently, the following pages will forward to this action:
 * CREATE FIELD VISIT:  
 * - addCSEvent.jsp (selectedEventTypeCd=FV) -> 
 * 		handleCSEventType (activityFlow=create) -> ... -> 
 * 		itineraryUpdate.jsp -> displayCSFVItinerarySummary -> displayCaseloadSearch
 * ADD FIELD VISIT (FROM EVENTS LIST):
 * - fieldVisitEventsList.jsp (set selectedEventTypeCd=FV & eventDateAsLog) -> 
 * 		handleCSEventType -> displayCSFVEventUpdate -> displayCaseloadSearch
 * 
 * CREATE OV/GOV:
 * - addCSEvent.jsp (set selectedEventTypeCd) -> handleCSEventType (activityFlow=create) 
 * 		-> displayCaseloadSearch (for both OV and GOV)
 * 
 * ADD OV (FROM EVENTS LIST):
 * - officeVisitEventsList.jsp (selectedEventTypeCd=OV & eventDateAsLong)
 * 		-> handleCSEventType (activityFlow=create) -> displayCaseloadSearch
 * 
 * ADD ATTENDEES - GOV (FROM EVENTS LIST):
 * - officeVisitEventsList.jsp (selectedEventTypeCd=GOV & eventDateAsLong)
 * 		-> handleCSGVSelection (activityFlow=addAttendees) -> displayCaseloadSearch
 * 
 */
public class DisplayCaseloadSearchAction extends JIMSBaseAction {

	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException  {
		CSCalendarDisplayForm this_form = (CSCalendarDisplayForm)aForm;
		//this_form.clear();
		ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	    Set userFeatures = securityManager.getFeatures(); 
	    
        if ("CCO".equals(this_form.getJobTitleCd()) && userFeatures.contains(naming.Features.CSCD_CALFV_QUAD_SEARCH)){
			getCCOOfficerCaseload(aRequest, this_form, this_form.getSelectedQuadrantId());
		}else{
			getOfficerCaseload(aRequest, this_form);
		}
        if ("GV".equals(this_form.getSelectedEventTypeCd()) ) {        	
        	CSCalendarFVForm cfvForm = (CSCalendarFVForm) getSessionForm(aMapping, aRequest, "csCalendarFVForm", true); //loading csCalendarFVFormform for Sex Offender Quadrants
        }
		return aMapping.findForward(UIConstants.SUCCESS);
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
	
	public void getCCOOfficerCaseload(HttpServletRequest request, CSCalendarDisplayForm calendarDisplayForm, String quadrantId ) 
	{
        if (quadrantId != null && quadrantId.length() != 0) {
           SearchSupervisee search = new SearchSupervisee();
    		List caseload = search.searchCasesByQuadrantId(quadrantId);
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
    			sendToErrorPage(request, "error.administerCaseload.superviseeUnderOfficerNotFound");	        	        	    			
    		}
        } else {
			sendToErrorPage(request, "error.administerCaseload.superviseeUnderOfficerNotFound");	        	        	
        }
	}
	
	public void getOfficerCaseload(HttpServletRequest request, CSCalendarDisplayForm calendarDisplayForm ) 
	{
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
        		
        		caseload = getFilteredCaseload(caseload, courtIdFilter);
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
	}
	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next","next");
	}
	
}
