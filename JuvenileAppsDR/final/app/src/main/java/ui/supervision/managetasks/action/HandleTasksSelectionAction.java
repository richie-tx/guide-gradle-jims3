/*
 * Created on Apr 13, 2007
 */
package ui.supervision.managetasks.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetCaseAssignmentEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administercompliance.GetNCResponseDetailsEvent;
import messaging.administercompliance.reply.NCResponseResponseEvent;
import messaging.managetask.reply.CSTaskResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.Composite.CompositeResponse;

import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import naming.CSCAssessmentConstants;
import naming.CaseloadConstants;
import naming.SupervisionPlanConstants;
import naming.UIConstants;
import naming.ViolationReportConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercasenotes.form.SuperviseeSearchForm;
import ui.supervision.managetasks.form.TasksSearchForm;
import ui.supervision.managetasks.helper.UIManagetasksHelper;


/**
 * @author hrodriguez
 */
public class HandleTasksSelectionAction extends JIMSBaseAction
{

	private final String DISPLAYCASENOTESPAGEFLOW = "displaycasenotespageflow";
	private final String CASELOADBYPROGREFERPROVPAGEFLOW = "caseloadByProgReferProvPageFlow";
	
    protected void addButtonMapping(Map keyMap)
    {
        keyMap.put("button.next", "next");
        keyMap.put("button.acceptTask", "next");
        keyMap.put("button.closeTask", "closeTask");
        keyMap.put("button.close", "close");
        keyMap.put("button.back", "back");
        keyMap.put("button.viewOrder", "viewOrder");
        keyMap.put("button.transferTask", "transferTask");
        keyMap.put("button.finish", "transferTaskConfirmation");
    }

    //	Future option
    public ActionForward viewOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.VIEW_ORDER_SUCCESS);
    }

    // Forward to close task Summary
    public ActionForward closeTask(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.CLOSE);
    }
    
    public ActionForward close(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        
        ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        IUserInfo userInfo = mgr.getIUserInfo();
        String userId = userInfo.getJIMSLogonId();
        
        UIManagetasksHelper taskHelper = UIManagetasksHelper.getInstance();
        
        LightCSCDStaffResponseEvent respEvent = taskHelper.fetchCSStaffposition(userId);

        if ( respEvent != null )
        {
        	 tsForm.setCurrentUserStaffPositionId( respEvent.getStaffPositionId() );
        }
        
        UIManagetasksHelper tskHelper = UIManagetasksHelper.getInstance();
        //Update task to closed
        tskHelper.updateTaskStatus( tsForm, UIConstants.CLOSED_STATUS_ID , "CLOSE" );
        tsForm.setCourt("");
        tsForm.setCreateDate(null);
        tsForm.setDueDate(null);

        return aMapping.findForward(UIConstants.CLOSE_SUCCESS);
    }
    
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }

    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {

        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        String topic = tsForm.getTaskTopic();
        UIManagetasksHelper taskHelper = UIManagetasksHelper.getInstance();
        
        ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        IUserInfo userInfo = mgr.getIUserInfo();
        String userId = userInfo.getJIMSLogonId();

        LightCSCDStaffResponseEvent respEvent = taskHelper.fetchCSStaffposition(userId);

        if (respEvent != null)
        {
        	 tsForm.setCurrentUserStaffPositionId( respEvent.getStaffPositionId() );
        }
        
        if ("CS.SPECIAL.CONDITION".equalsIgnoreCase(topic))
        {
            
            aRequest.setAttribute("Flag","2");
            aRequest.setAttribute("conditionId",tsForm.getConditionId());
            return aMapping.findForward(UIConstants.NEXTTOPASOREVIEWNEWCONDITION);
        }
        if ("CS.DRAFT.CASENOTE".equalsIgnoreCase(topic))
        {
            
        	SuperviseeSearchForm CasenoteForm = (SuperviseeSearchForm) 
        			getSessionForm(aMapping,aRequest,"superviseeSearchForm",true);
        	CasenoteForm.setSelectedValue(tsForm.getSpn());
            aRequest.setAttribute("defendant_id",tsForm.getSpn());
            return aMapping.findForward(DISPLAYCASENOTESPAGEFLOW);
        }

        CSTaskResponseEvent selectedTask =	tsForm.getSelectedCSTask();
        
        if( selectedTask == null ){
        	this.sendToErrorPage(aRequest, "error.noRecords");
        	return aMapping.findForward( UIConstants.FAILURE );
        }
        String expectedPageFlow = selectedTask.getScenario();
        aRequest.setAttribute(CaseloadConstants.SCENARIO , expectedPageFlow ); 
        
        if( "CS.CASELOAD.BY.PROG.REFER.PROV".equalsIgnoreCase(topic) ){
            //workflow dashboard validation
            tsForm.setSelectedValue(selectedTask.getCaseAssignIds());
        	tsForm.setName(selectedTask.getSuperviseeName());
    		tsForm.setWorkgroupId(selectedTask.getWorkGroupId());
        	
        	return aMapping.findForward(CASELOADBYPROGREFERPROVPAGEFLOW);
        }
        if(topic != null && (topic.indexOf("VIOLATIONREPORT") >= 0 || topic.indexOf("TASK.CASESUMMARY") >= 0)){
            //workflow dashboard validation
        	
        	String forwardValue = ViolationReportCaseSummaryValidator( selectedTask, topic, aRequest );
        	
        	if(UIConstants.FAILURE.equals(forwardValue)){
        		return aMapping.findForward(forwardValue);
        	}
        	aRequest.setAttribute( ViolationReportConstants.PARAM_NCRESPONSE_ID, selectedTask.getNcResponseId() );
	        aRequest.setAttribute( ViolationReportConstants.PARAM_SUPERVISEE_NAME, selectedTask.getSuperviseeName());

        }
        
        if(topic != null  &&
        		   ((topic.indexOf(CaseloadConstants.CSTASK_TOPIC_ALLOCATE_SUPERVISEE_TO_SUP) >= 0) 
    			|| (topic.indexOf(CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_OFF) >= 0) 
    			|| (topic.indexOf(CaseloadConstants.CSTASK_TOPIC_PROCESS_OFF_NEW_CASE_ASSIGN) >= 0))){
    		String forwardValue = assignmentReassignmentValidator(selectedTask, topic, aRequest);
        	if(UIConstants.FAILURE.equals(forwardValue)){
        		return aMapping.findForward(forwardValue);
        	}
    	}
        	
        aRequest.setAttribute(ViolationReportConstants.PARAM_DEFENDANT_ID, selectedTask.getDefendantId() );
        aRequest.setAttribute(CaseloadConstants.CASE_ASSIGNMENT_ID, selectedTask.getCaseAssignIds() );
        aRequest.setAttribute(ViolationReportConstants.PARAM_COURT_NUMBER, selectedTask.getCourtId() );
        aRequest.setAttribute(CaseloadConstants.CRIMINAL_CASE_ID, selectedTask.getCriminalCaseId());
        aRequest.setAttribute(CaseloadConstants.SUPERVISION_ORDER_ID, selectedTask.getSupervisionOrderIds());
        aRequest.setAttribute(SupervisionPlanConstants.SUPERVISION_PLAN_ID, selectedTask.getSprvisionPlanId());
        aRequest.setAttribute(CSCAssessmentConstants.ASSESMENTID, selectedTask.getSprvisionPlanId());
        String csTaskId = tsForm.getTaskId();
        aRequest.setAttribute("taskId", csTaskId);
        
                
        //Reset task completed switch
        CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm)
        					this.getSessionForm(aMapping, aRequest, UIConstants.CS_CASE_ASSIGNMENT_FORM, true);
        caseAssignmentForm.clearAll();
        
        CSTaskResponseEvent checkTask = taskHelper.getTasksByOID( csTaskId );
        if ("C".equals( checkTask.getStatusId()) ){
        	
        	sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Task has been Processed Previously");
			return aMapping.findForward("failure");
         }else{
        	 
        	 caseAssignmentForm.setTaskProcessed( false );
            //Update task to accept
            taskHelper.updateTaskStatus(tsForm, UIConstants.ACCEPTED_STATUS_ID, "ACCEPT");
        	
        }
        caseAssignmentForm.setSuperviseeNameStr(tsForm.getName());
        if (StringUtils.isNotEmpty(selectedTask.getTaskText())) {
        	caseAssignmentForm.setCasenoteText(selectedTask.getTaskText());
        }
        tsForm.clearForm();
        selectedTask = null;
        checkTask = null;

        return aMapping.findForward(expectedPageFlow) ; 
    }
    
    private String ViolationReportCaseSummaryValidator( CSTaskResponseEvent csTask , String topic, HttpServletRequest aRequest){
        //workflow dashboard validation
        
    	String ncResponseId = csTask.getNcResponseId();
        String reportType = "";
        if(topic.indexOf("VIOLATIONREPORT") > 0){
        	reportType = " Violation Report ";
        }else{
        	reportType = " Case Summary ";
        }
        
        GetNCResponseDetailsEvent dEvent = new GetNCResponseDetailsEvent();
         
        dEvent.setNcResponseId( ncResponseId );
        NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil.postRequest( dEvent , NCResponseResponseEvent.class );
        
        if(resp == null){
        	sendToErrorPage(aRequest, "error.wfdashboard.state.validator", reportType);
        	return UIConstants.FAILURE;
        }
    	if(ViolationReportConstants.CSTASK_TOPIC_NEW_VIOLATION_FOR_APPROVAL.equalsIgnoreCase(topic)){
    		if(!ViolationReportConstants.STATUS_PENDING_MANAGER_APPROVAL.equalsIgnoreCase(resp.getStatusId())){
    			sendToErrorPage(aRequest, "error.wfdashboard.state.validator", reportType);
    			return UIConstants.FAILURE;
        	}
        }else if(ViolationReportConstants.CSTASK_TOPIC_VIOLATION_SUBMISSION_REVIEW.equalsIgnoreCase(topic)){
    		if(!ViolationReportConstants.STATUS_MANAGER_APPROVED.equalsIgnoreCase(resp.getStatusId())){
    			sendToErrorPage(aRequest, "error.wfdashboard.state.validator", reportType);
    			return UIConstants.FAILURE;
        	}
        }else if(ViolationReportConstants.CSTASK_TOPIC_VIOLATION_SUBMISSION_APPROVAL.equalsIgnoreCase(topic)){
    		if(!ViolationReportConstants.STATUS_PENDING_SUBMISSION_APPROVAL.equalsIgnoreCase(resp.getStatusId())){
    			sendToErrorPage(aRequest, "error.wfdashboard.state.validator", reportType);
    			return UIConstants.FAILURE;
        	}
        }else if(ViolationReportConstants.CSTASK_TOPIC_VIOLATION_SUBMISSION_REQUIRED.equalsIgnoreCase(topic)){
    		if(!ViolationReportConstants.STATUS_SUBMISSION_APPROVED.equalsIgnoreCase(resp.getStatusId())){
    			sendToErrorPage(aRequest, "error.wfdashboard.state.validator", reportType);
        		return UIConstants.FAILURE;
        	}
        }else if(ViolationReportConstants.CSTASK_TOPIC_CASESUMMARY_APPROVAL.equalsIgnoreCase(topic)){
    		if(!ViolationReportConstants.STATUS_PENDING_MANAGER_APPROVAL.equalsIgnoreCase(resp.getStatusId())){
    			sendToErrorPage(aRequest, "error.wfdashboard.state.validator", reportType);
        		return UIConstants.FAILURE;
        	}
        }else if(ViolationReportConstants.CSTASK_TOPIC_CASESUMMARY_FILING_REQUIRED.equalsIgnoreCase(topic)){
    		if(!ViolationReportConstants.STATUS_MANAGER_APPROVED.equalsIgnoreCase(resp.getStatusId())){
    			sendToErrorPage(aRequest, "error.wfdashboard.state.validator", reportType);
        		return UIConstants.FAILURE;
        	}
        }else if(ViolationReportConstants.CSTASK_TOPIC_CASESUMMARY_UPDATES_REQUIRED.equalsIgnoreCase(topic) || ViolationReportConstants.CSTASK_TOPIC_VIOLATION_UPDATES_REQUIRED.equalsIgnoreCase(topic)){
        	String validationStatusId = resp.getStatusId();
        	if(!resp.getStatusId().equalsIgnoreCase(validationStatusId)){
    			sendToErrorPage(aRequest, "error.wfdashboard.state.validator", reportType);
        		return UIConstants.FAILURE;
        	}
        } 
    	aRequest.setAttribute(ViolationReportConstants.CASE_ID, resp.getCaseId());
        aRequest.setAttribute(ViolationReportConstants.CREATOR_ID, resp.getCreatedBy());	
        aRequest.setAttribute(ViolationReportConstants.STATUS_PENDING_SUBMISSION_CREATOR, resp.getSubmissionApprovedUser());
    	return UIConstants.SUCCESS;
    }
    
    private String assignmentReassignmentValidator(CSTaskResponseEvent taskRes, String topic, HttpServletRequest aRequest){
   		String type = (String) taskRes.getTopic();
   		String caseAssignmentId = "";
   		
   		caseAssignmentId = (String) taskRes.getCaseAssignIds();
   		
    	if( caseAssignmentId == null || caseAssignmentId.equals("") ){
			sendToErrorPage(aRequest, "error.wfdashboard.state.validator", type);
			return UIConstants.FAILURE;
		}
		
		List caseAssignList = new ArrayList();
		caseAssignList.add( taskRes.getCaseAssignIds() );
		if ( caseAssignList != null ){
			
			StringTokenizer caseAssignTokenizer = new StringTokenizer(caseAssignmentId, ",");
			String caseAssignId = null;
			while (caseAssignTokenizer.hasMoreTokens()) {
				
				caseAssignId = caseAssignTokenizer.nextToken();
				
				GetCaseAssignmentEvent event = new GetCaseAssignmentEvent();
				event.setCaseAssignmentId( caseAssignId );
				
				CompositeResponse composite = MessageUtil.postRequest(event);
				CaseAssignmentResponseEvent resp1 = (CaseAssignmentResponseEvent) MessageUtil.filterComposite(composite, CaseAssignmentResponseEvent.class);
				
				if(resp1 == null){
					sendToErrorPage(aRequest, "error.wfdashboard.state.validator", "Assignment/Reassignment");
					return UIConstants.FAILURE;
				}

				List list = resp1.getCaseAssignments();
				if(list == null || list.isEmpty()){
					sendToErrorPage(aRequest, "error.wfdashboard.state.validator", "Assignment/Reassignment");
					return UIConstants.FAILURE;
		    	}
				
				ICaseAssignment caseAssignment = (ICaseAssignment) list.get(0);
				if(topic.indexOf(CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_OFF) >= 0 || topic.indexOf(CaseloadConstants.CSTASK_TOPIC_ALLOCATE_SUPERVISEE_TO_SUP) >= 0){
					if(caseAssignment.getProgramUnitAssignDate() == null){
						sendToErrorPage(aRequest, "error.wfdashboard.state.validator", "Assignment/Reassignment");
		    			return UIConstants.FAILURE;
					}
				}else if(topic.indexOf(CaseloadConstants.CSTASK_TOPIC_PROCESS_OFF_NEW_CASE_ASSIGN) >= 0){
					if(caseAssignment.getOfficerAssignDate() == null){
						sendToErrorPage(aRequest, "error.wfdashboard.state.validator", "Assignment/Reassignment");
						return UIConstants.FAILURE;
					}
				} 
			}
		}		
		
		return UIConstants.SUCCESS;
    }
    
    public ActionForward transferTask(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = new ActionForward();
        TasksSearchForm tsForm = (TasksSearchForm) aForm;

        UIManagetasksHelper taskHelper = UIManagetasksHelper.getInstance();
        
        CSTaskResponseEvent task = taskHelper.fetchTaskFromList(tsForm.getTaskResultList(), tsForm
                .getTaskId());
        taskHelper.populateTaskForm(task, tsForm);

        forward = aMapping.findForward(UIConstants.TRANSFER_SUCCESS);
        return forward;
    }
    
    public ActionForward transferTaskConfirmation(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        
        UIManagetasksHelper helper = UIManagetasksHelper.getInstance();
        String taskOwnerId = "";
        String positionId = tsForm.getTransferToId();
        String transferToType = tsForm.getTransferToTypeId();
        if(transferToType.equalsIgnoreCase("workgroup")){
            
            taskOwnerId = (CaseloadConstants.WORKGROUP_OID_PREPEND_STRING + positionId);
        }else{
            
            taskOwnerId = (CaseloadConstants.POSITION_OID_PREPEND_STRING + positionId);
        }
        
        // Update Task status and owner
        String nTaskId = tsForm.getNtTaskId();
       helper.updateCSTaskStatus(tsForm);
       helper.transferTask(nTaskId, taskOwnerId);
       helper.updateTaskStatus(tsForm, UIConstants.SUBMITTED_STATUS_ID, "");

        return aMapping.findForward(UIConstants.CONFIRM_SUCCESS);
    }
}