//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\ui\\supervision\\cscdcalendar\\action\\DisplayMonthlyCSCalendarAction.java

package ui.supervision.cscdcalendar.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.GetLightSupervisorsEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.cscdstaffposition.GetLightOrganizationStaffEvent;
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
import ui.security.SecurityUIHelper;
import ui.supervision.cscdcalendar.form.CSCalendarDisplayForm;


public class DisplayMonthlyCSCalendarAction extends JIMSBaseAction {
   
	/**
	 * @roseuid 479A0F0B00A8
	 */
	public DisplayMonthlyCSCalendarAction() {
    
	}
   
	protected void addButtonMapping(Map keyMap) {
   		keyMap.put("button.link","link");
   		keyMap.put("button.getOfficers","getOfficers");
   		keyMap.put("button.getSupervisors","getSupervisors");
   		keyMap.put("button.viewCalendar","viewCalendar");
   		keyMap.put("button.addNewEvent","addNewEvent");
   	}
   
   	/**
   	 * @roseuid 47A2278B0339
   	 */
   	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
   		//long startTime = System.currentTimeMillis();
   		//TODO:This might need to be controlled somewhere else
   		CSCalendarDisplayForm form = (CSCalendarDisplayForm)aForm;
   		form.clear();
   		
   		if(!UIConstants.CSC.equals(SecurityUIHelper.getUserAgencyId())) {
   			form.setSelectedOfficerName(SecurityUIHelper.getUser().getLastName());
   			form.setPositionId("-1");
  			form.setDivisionList(new ArrayList());
  			form.setSupervisorList(new ArrayList());
  			form.setOfficerList(new ArrayList());
   			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"This functionality is available only for CSC users.");
   			return aMapping.findForward(UIConstants.SUCCESS);
   		}  
   		
   		GetLightCSCDStaffForUserEvent gEvent = new GetLightCSCDStaffForUserEvent();
   		gEvent.setLogonId(SecurityUIHelper.getLogonId());
   		gEvent.setOfficerNameNeeded(true);
   		LightCSCDStaffResponseEvent resp = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(gEvent, LightCSCDStaffResponseEvent.class);
   		
   		if(resp == null){
   			form.setSelectedOfficerName(SecurityUIHelper.getUser().getLastName());
   			form.setPositionId("-1");
  			form.setDivisionList(new ArrayList());
  			form.setSupervisorList(new ArrayList());
  			form.setOfficerList(new ArrayList());
        	sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"This functionality is available only for users with assigned position(s)");
   			return aMapping.findForward(UIConstants.SUCCESS);
   		}
   		
        form.setDivisionList(new ArrayList());
        
   		form.setJobTitleCd(resp.getJobTitleCD());
   		form.setPositionId(resp.getStaffPositionId());
   		form.setSelectedOfficerName(resp.getOfficerName());
   		form.setSelectedOfficerId(resp.getStaffPositionId());
   		form.setSelectedDivisionId(resp.getDivisionId());
   		form.setSelectedSupervisorId(resp.getSupervisorPositionId());

   		GetLightOrganizationStaffEvent divisionEvent = new GetLightOrganizationStaffEvent();
   		divisionEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
   		
   		List divisions = MessageUtil.postRequestListFilter(divisionEvent, CSCDSupervisionStaffResponseEvent.class);
   		
        if (divisions != null && !divisions.isEmpty()){
        	form.setDivisionList(divisions);
        }

        GetLightSupervisorsEvent glEvent = new GetLightSupervisorsEvent();
        String currentUserLogonId = SecurityUIHelper.getLogonId();
		glEvent.setLogonId(currentUserLogonId);
		glEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());	
		glEvent.setOfficerInfoNeeded(true);
		CompositeResponse res = MessageUtil.postRequest(glEvent);
		
		List list = MessageUtil.compositeToList(res, CSCDSupervisionStaffResponseEvent.class);
		List supervisorsInDivision = new ArrayList();
		List officersUnderSupervisors = new ArrayList();
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				CSCDSupervisionStaffResponseEvent org = (CSCDSupervisionStaffResponseEvent) list.get(i);
				if(org.getPositionTypeId() != null && !PDCodeTableConstants.STAFF_POSITION_TYPE_OFFICER.equals(org.getPositionTypeId())){
					supervisorsInDivision.add(org);
					if (currentUserLogonId != null && currentUserLogonId.equals( org.getSupervisorLogonId())){
						form.setSelectedSupervisorId(org.getStaffPositionId());
					}
				}else{
					officersUnderSupervisors.add(org);
				}
			}
		}
		form.setSupervisorList(supervisorsInDivision);
		form.setOfficerList(sortOfficersList(officersUnderSupervisors));
   		form.setContext(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION);
   		//System.out.println("Elapsed Time1= " + (System.currentTimeMillis()-startTime)/1000F);
   		return aMapping.findForward(UIConstants.SUCCESS);
   	}
   	

   	//  Advanced search options
    public ActionForward getSupervisors(
    		ActionMapping aMapping, 
			ActionForm aForm, 
			HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CSCalendarDisplayForm form = (CSCalendarDisplayForm) aForm;

   		GetLightCSCDStaffForUserEvent gEvent = new GetLightCSCDStaffForUserEvent();
   		gEvent.setLogonId(SecurityUIHelper.getLogonId());
   		LightCSCDStaffResponseEvent resp = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(gEvent, LightCSCDStaffResponseEvent.class);
   		
   		if(resp == null){
   			form.setSelectedOfficerName(SecurityUIHelper.getUser().getLastName());
   			form.setPositionId("-1");
        	sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"This functionality is available only for users with assigned position(s)");
   			return aMapping.findForward(UIConstants.SUCCESS);
   		}
        
   		form.setOfficerList(new ArrayList());
        form.setSupervisorList(new ArrayList());
        
        GetLightSupervisorsEvent glEvent = new GetLightSupervisorsEvent();
		glEvent.setDivisionId(form.getSelectedDivisionId());
		glEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
		
		List list = MessageUtil.postRequestListFilter(glEvent, CSCDSupervisionStaffResponseEvent.class);
		if(list != null && !list.isEmpty()){
			form.setSupervisorList(sortOfficersList(list));
		}
		
        aRequest.setAttribute("calendarNeedsRefresh", new Boolean(false));
        return aMapping.findForward(UIConstants.SUCCESS);
    }
    
    public ActionForward getOfficers(
    		ActionMapping aMapping, 
			ActionForm aForm, 
			HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CSCalendarDisplayForm form = (CSCalendarDisplayForm) aForm;        
          		
   		GetLightCSCDStaffForUserEvent gEvent = new GetLightCSCDStaffForUserEvent();
   		gEvent.setLogonId(SecurityUIHelper.getLogonId());
   		LightCSCDStaffResponseEvent resp = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(gEvent, LightCSCDStaffResponseEvent.class);
   		
   		if(resp == null){
   			form.setSelectedOfficerName(SecurityUIHelper.getUser().getLastName());
   			form.setPositionId("-1");
        	sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"This functionality is available only for users with assigned position(s)");
   			return aMapping.findForward(UIConstants.SUCCESS);
   		}
        
   		form.setOfficerList(new ArrayList());
        
        GetLightSupervisorsEvent glEvent = new GetLightSupervisorsEvent();
		glEvent.setSupervisorId(form.getSelectedSupervisorId());
		
		List list = MessageUtil.postRequestListFilter(glEvent, CSCDSupervisionStaffResponseEvent.class);
		if(list != null && !list.isEmpty()){
			form.setOfficerList(sortOfficersList(list));
		}
        aRequest.setAttribute("calendarNeedsRefresh", new Boolean(false));        
        return aMapping.findForward(UIConstants.SUCCESS);
    }
   	
    public ActionForward viewCalendar(
    		ActionMapping aMapping, 
			ActionForm aForm, 
			HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CSCalendarDisplayForm form = (CSCalendarDisplayForm) aForm;
        
   		GetLightCSCDStaffForUserEvent gEvent = new GetLightCSCDStaffForUserEvent();
   		gEvent.setLogonId(SecurityUIHelper.getLogonId());
   		LightCSCDStaffResponseEvent resp = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(gEvent, LightCSCDStaffResponseEvent.class);
   		
   		if(resp == null){
   			form.setSelectedOfficerName(SecurityUIHelper.getUser().getLastName());
   			form.setPositionId("-1");
        	sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"This functionality is available only for users with assigned position(s)");
   			return aMapping.findForward(UIConstants.SUCCESS);
   		}
   		
        if(form.getSelectedOfficerId() != null && form.getSelectedOfficerId().length() > 0) {
        	form.setPositionId(form.getSelectedOfficerId());
        	StringBuffer jobTitleId = new StringBuffer();
        	form.setSelectedOfficerName(
        			getOfficerName(form.getOfficerList(), form.getSelectedOfficerId(), jobTitleId));
        	if(jobTitleId != null && jobTitleId.length() > 0) {
        		List jobTitleList = ComplexCodeTableHelper.getSupervisionStaffJobTitles(PDCodeTableConstants.CSCD_AGENCY);
        		for(Iterator jobTitleIter = jobTitleList.iterator(); jobTitleIter.hasNext();) {
        			CodeResponseEvent code = (CodeResponseEvent)jobTitleIter.next();
        			if(code.getCodeId().equals(jobTitleId)) {
        				form.setJobTitleCd(code.getSupervisionCode());
        			}
        		}
        	}
        	
        } else if(form.getSelectedSupervisorId() != null && form.getSelectedSupervisorId().length() > 0) {
        	String positionId = form.getSelectedSupervisorId();
        	form.setPositionId(positionId);
        	StringBuffer jobTitleId = new StringBuffer();
        	form.setSelectedOfficerName(
        			getOfficerName(form.getSupervisorList(), positionId, jobTitleId));
        	if(jobTitleId != null && jobTitleId.length() > 0) {
        		List jobTitleList = ComplexCodeTableHelper.getSupervisionStaffJobTitles(PDCodeTableConstants.CSCD_AGENCY);
        		for(Iterator jobTitleIter = jobTitleList.iterator(); jobTitleIter.hasNext();) {
        			CodeResponseEvent code = (CodeResponseEvent)jobTitleIter.next();
        			if(code.getCodeId().equals(jobTitleId.toString())) {
        				form.setJobTitleCd(code.getSupervisionCode());
        			}
        		}
        	}
        } else if(form.getSelectedDivisionId() != null && form.getSelectedDivisionId().length() > 0) {
        	String divisionId = form.getSelectedDivisionId();
        	List divisions = form.getDivisionList();
        	for(Iterator divisionIter = divisions.iterator();divisionIter.hasNext();) {
        		CSCDSupervisionStaffResponseEvent division = 
        			(CSCDSupervisionStaffResponseEvent)divisionIter.next();
        		if(division.getDivisionId().equals(divisionId)) {
        			if(division.getAssignedName()==null) {
        				form.setSelectedOfficerName("NO OFFICER ASSIGNED");
        			} else {
        				form.setSelectedOfficerName(division.getAssignedName().getFormattedName());
        			}
        			form.setPositionId(division.getStaffPositionId());
        			if(division.getJobTitleId() != null && division.getJobTitleId().length() > 0) {
                		List jobTitleList = ComplexCodeTableHelper.getSupervisionStaffJobTitles(PDCodeTableConstants.CSCD_AGENCY);
                		for(Iterator jobTitleIter = jobTitleList.iterator(); jobTitleIter.hasNext();) {
                			CodeResponseEvent code = (CodeResponseEvent)jobTitleIter.next();
                			if(code.getCodeId().equals(division.getJobTitleId())) {
                				form.setJobTitleCd(code.getSupervisionCode());
                			}
                		}
                	}
        		}
        	}        
        }
        return aMapping.findForward(UIConstants.SUCCESS);
    }
    
    private String getOfficerName(List officerList, String positionId, StringBuffer jobTitleCd) {    	
    	if(officerList!= null && officerList.size()>0) {
    		for(Iterator officerIter = officerList.iterator();
    				officerIter.hasNext();){
    			CSCDSupervisionStaffResponseEvent officer = 
    				(CSCDSupervisionStaffResponseEvent)officerIter.next();
    			if(officer.getStaffPositionId().equals(positionId)) {
    				if(officer.getAssignedName()!=null) {
    					jobTitleCd.append(officer.getJobTitleId());
    					return officer.getAssignedName().getFormattedName();
    				}
    				
    			}
    		}
    	}
    	return "NO OFFICER ASSIGNED";
    }
    
	/**
	 * @param officerList
	 * @return List
	 */
	public static List sortOfficersList(List officerList){
		if (officerList.size() > 1){
			SortedMap map = new TreeMap();
			String lastName = "";
			String firstName = "";
			String middleName = "";
			String posName = "";
			for (int x = 0; x < officerList.size(); x++){
				CSCDSupervisionStaffResponseEvent sspr = (CSCDSupervisionStaffResponseEvent) officerList.get(x);
				lastName = "";
				firstName = "";
				middleName = "";
				posName = "";

				if (sspr.getAssignedName().getLastName() != null){
					lastName = sspr.getAssignedName().getLastName().toUpperCase();
				}
				if (sspr.getAssignedName().getFirstName() != null){
					firstName = sspr.getAssignedName().getFirstName().toUpperCase();
				}
				if (sspr.getAssignedName().getMiddleName() != null){
					middleName = sspr.getAssignedName().getMiddleName().toUpperCase();
				}
				if (sspr.getPositionName() != null){
					posName = sspr.getPositionName();
				}
				map.put(lastName + firstName + middleName + posName, sspr);
			}
			officerList = new ArrayList(map.values());
			} 
		return officerList;
	} 
	
 	/**
   	 * 
   	 */
 public ActionForward addNewEvent(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
   		return aMapping.findForward("addNewEvent");
   }	
}