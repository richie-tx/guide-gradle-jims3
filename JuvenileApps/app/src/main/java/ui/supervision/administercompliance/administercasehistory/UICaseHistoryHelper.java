//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasehistory\\CaseHistoryHelper.java


package ui.supervision.administercompliance.administercasehistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import messaging.administercaseload.GetActiveCasesByCaseEvent;
import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.administercompliance.GetNCResponsesEvent;
import messaging.administercompliance.reply.NCResponseResponseEvent;
import messaging.cscdstaffposition.GetCourtStaffPositionEvent;
import messaging.managetask.UpdateCSTaskEvent;
import messaging.supervision.reply.SupervisionStaffResponseEvent;
import messaging.supervisionorder.reply.SuperviseeCaseOrderResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.CSCDStaffPositionControllerServiceNames;
import naming.CSTaskControllerServiceNames;
import naming.CaseloadControllerServiceNames;
import naming.ComplianceControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import naming.ViolationReportConstants;
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

/**
 * @author cshimek
 * 
 */
public class UICaseHistoryHelper {
	
	/**
	 * @param orderId
	 */
	public static List getViolationReports(String caseId){
		List reports = new ArrayList();
		GetNCResponsesEvent event = (GetNCResponsesEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSES);
        //event.setSprOrderId(orderId);
		event.setCriminalcaseId(caseId);
        event.setReportType(ViolationReportConstants.REPORTTYPE_VIOLATION);        

        reports = MessageUtil.postRequestListFilter(event, NCResponseResponseEvent.class);
		if (reports != null && !reports.isEmpty()){	
			String createName = "";
			for (int y=0; y<reports.size(); y++){
				NCResponseResponseEvent ncre = (NCResponseResponseEvent) reports.get(y);
				createName = "";
				if (ncre.getCreatedBy() != null && !ncre.getCreatedBy().equals("")){
					createName = SecurityUIHelper.getUserName(ncre.getCreatedBy()).toString();
				}
				ncre.setCreatedByName(createName);
			}
		}
		if (reports == null){
			reports = new ArrayList();
		}
		return reports;
	} 

	/**
	 * @param orderId
	 */
	public static List getCaseSummaries(String caseId){
		List reports = new ArrayList();
		GetNCResponsesEvent event = (GetNCResponsesEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSES);
		//event.setSprOrderId(orderId);
		event.setCriminalcaseId(caseId);
		event.setReportType(ViolationReportConstants.REPORTTYPE_CASESUMMARY);  

        reports = MessageUtil.postRequestListFilter(event, NCResponseResponseEvent.class);
		if (reports != null && !reports.isEmpty()) {
			String createName = "";
			for (int y=0; y<reports.size(); y++){
				NCResponseResponseEvent ncre = (NCResponseResponseEvent) reports.get(y);
				createName = "";
				if (ncre.getCreatedBy() != null && !ncre.getCreatedBy().equals("")){
					createName = SecurityUIHelper.getUserName(ncre.getCreatedBy()).toString();
				}
				ncre.setCreatedByName(createName);
			}
		} 
		if (reports == null){
			reports = new ArrayList();
		}
		return reports;
	}	
	
	public static List formatEventInfo(List cases){
		Iterator iter = cases.iterator();
		String caseNum = "";
		String courtNum = "";
		int courtNumInt = 0;
		while(iter.hasNext()){
			SuperviseeCaseOrderResponseEvent score = (SuperviseeCaseOrderResponseEvent) iter.next();
			if (score.getCaseNumber() != null && !score.getCaseNumber().equalsIgnoreCase("")) {
				caseNum = score.getCaseNumber().substring(3);
				score.setCaseNumber(caseNum);
			}
			score.setDisplayCourtId("");
			if (score.getCourtId() != null && !score.getCourtId().equalsIgnoreCase("")) {
				courtNum = score.getCourtId();
				if(courtNum.length() > 3 ) {
					String crtIds [] = courtNum.split(" ");
					if(crtIds != null && crtIds.length > 1){
						courtNum = crtIds[1];
					}
					try {
						courtNumInt = new Integer(courtNum);
						courtNum = "" + courtNumInt;
						while(courtNum.length() < 3){
							courtNum = "0" + courtNum;
						}
					}catch(NumberFormatException e){
//do nothing, court number is not integer
					}	
				}
				score.setDisplayCourtId(courtNum);
			}
		}
		return cases;
	}
	
	/**
	 *  Mainly used for Case Summary and Violation Reports
	 * @param myForm
	 * @return
	 */
    public Map buildOfficerHiearchyMap( CaseAssignmentForm myForm ){
		
		Map map = new HashMap();

		String currentUserId = SecurityUIHelper.getJIMSLogonId();
		GetLightCSCDStaffForUserEvent currentUserEvent = new GetLightCSCDStaffForUserEvent();
		currentUserEvent.setLogonId(currentUserId);	
		LightCSCDStaffResponseEvent currentPosition = (LightCSCDStaffResponseEvent) MessageUtil.postRequest( currentUserEvent, LightCSCDStaffResponseEvent.class);
		
		
		map.put("currentUserPosition", currentPosition.getStaffPositionId());
		
		GetActiveCasesByCaseEvent event = (GetActiveCasesByCaseEvent) 
										EventFactory.getInstance(CaseloadControllerServiceNames.GETACTIVECASESBYCASE);

        StringBuffer sb = new StringBuffer();
        sb.append( myForm.getCdi() ).append( myForm.getCaseNum() ) ;
        event.setCriminalCaseId( sb.toString() );
        
        CaseAssignmentResponseEvent assignmentResponse = (CaseAssignmentResponseEvent) 
        						MessageUtil.postRequest(event, CaseAssignmentResponseEvent.class);
        
        if ( assignmentResponse != null ){
        	
        	List activeCases = new ArrayList();
        	activeCases = assignmentResponse.getCaseAssignments();
        	
        	for ( int x = 0; x < activeCases.size(); x++ ){
            	
        		CaseAssignmentTO cato = ( CaseAssignmentTO ) activeCases.get( x );

     			 map.put("csoPosition", cato.getAssignedStaffPositionId());
    			 
    			 GetLightCSCDStaffForUserEvent csoEvent = new GetLightCSCDStaffForUserEvent();
    			 csoEvent.setStaffPositionId( cato.getAssignedStaffPositionId() );
    	    		
	    		LightCSCDStaffResponseEvent csoPosition = (LightCSCDStaffResponseEvent) MessageUtil.postRequest( csoEvent, LightCSCDStaffResponseEvent.class);
    	    		if( csoPosition != null ){
    	    			
    	    			 map.put("csoSupervisor", csoPosition.getParentPositionId() );
    	    		}
             }
        }
        
        // Fill in details for the court
        GetCourtStaffPositionEvent courtEvent = (GetCourtStaffPositionEvent) EventFactory
        					.getInstance(CSCDStaffPositionControllerServiceNames.GETCOURTSTAFFPOSITION);
		StringBuffer courtNumber = new StringBuffer();
		if ( StringUtils.isNotEmpty( myForm.getCourtNumber() ) ) {
			String fd = myForm.getCourtNumber().substring(0,1);
			if (fd.equals("0")){
				courtNumber.append("CC 0");
			    courtNumber.append(myForm.getCourtNumber());
			}else {
				courtNumber.append("CR 0");
			    courtNumber.append(myForm.getCourtNumber());
			}
		}
		courtEvent.setCourtId(courtNumber.toString());
		courtEvent.setJobTitleId(PDCodeTableConstants.STAFF_JOB_TITLE_CLO);
		List results = MessageUtil.postRequestListFilter( courtEvent, SupervisionStaffResponseEvent.class );
        
		if (results != null && results.size() > 0) {
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
	            SupervisionStaffResponseEvent sre = (SupervisionStaffResponseEvent) iterator.next();
			
	            map.put("cloPosition", sre.getSupervisionStaffId() );
	            
	            GetLightCSCDStaffForUserEvent gEvent = new GetLightCSCDStaffForUserEvent();
	    		gEvent.setStaffPositionId( sre.getSupervisionStaffId() );
	    		
	    		LightCSCDStaffResponseEvent userStaffPosition = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(gEvent, LightCSCDStaffResponseEvent.class);
	    		if(userStaffPosition != null){
	    			
	    			 map.put("cloSupervisor", userStaffPosition.getParentPositionId() );
	    		}
			}
		}
		return map;
	}
    
    /**
     * 
     * @param taskId
     */
	public void closeCsTaskById( String taskId ) 
	{
		if ( !"".equals( taskId ) ){
			
			UpdateCSTaskEvent updateTaskEvent = 
				(UpdateCSTaskEvent) EventFactory.getInstance(CSTaskControllerServiceNames.UPDATECSTASK);
			
			updateTaskEvent.setCsTaskId( taskId );
			updateTaskEvent.setStatusId( UIConstants.CLOSED_STATUS_ID );
			updateTaskEvent.setCloseTask(true);
			MessageUtil.postRequest(updateTaskEvent);
			
		}
	}
	
	public boolean isCaseAssigned( CaseAssignmentForm caForm )
	{
		
		boolean caseAssignedToOfficer = false;
		GetActiveCasesByCaseEvent event = (GetActiveCasesByCaseEvent) 
									EventFactory.getInstance(CaseloadControllerServiceNames.GETACTIVECASESBYCASE);

		StringBuffer sb = new StringBuffer();
		sb.append( caForm.getCdi() ).append( caForm.getCaseNum() ) ;
		event.setCriminalCaseId( sb.toString() );

		CaseAssignmentResponseEvent assignmentResponse = (CaseAssignmentResponseEvent) 
									MessageUtil.postRequest(event, CaseAssignmentResponseEvent.class);
		
		if ( assignmentResponse != null ){
        	
        	List activeCases = new ArrayList();
        	activeCases = assignmentResponse.getCaseAssignments();
        	
        	for ( int x = 0; x < activeCases.size(); x++ ){
            	
        		CaseAssignmentTO cato = ( CaseAssignmentTO ) activeCases.get( x );

     			 if ( cato.getAssignedStaffPositionId()!= null ){
     				 
     				 caseAssignedToOfficer = true;
     			 }
    			 
             }
        }
		return caseAssignedToOfficer;
		
	}
}