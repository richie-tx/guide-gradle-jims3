package ui.supervision.managetasks.action;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.codetable.GetSupervisionCodesEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.task.CreateTaskEvent;
import messaging.task.GetTaskEvent;
import messaging.task.domintf.ITask;
import messaging.task.domintf.ITaskState;
import messaging.task.reply.TaskResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

public class CaseAssignmentSummaryAction extends JIMSBaseAction
{
    
    
    /**
     * @roseuid 464368F103D5
     */
    public CaseAssignmentSummaryAction()
    {

    }

    protected void addButtonMapping(Map keyMap)
    {
    	keyMap.put("button.summary" , "summary") ;
    	keyMap.put("button.finish" , "finish") ;
    }
    
    
    public ActionForward summary(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
      try
	  {
        CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm ;
        String taskId = caseAssignmentForm.getTaskId() ; 
        caseAssignmentForm.setIsDivisionReassignment(false) ; 
        // TODO : reset the division for the case assignment 
	  }
      catch (Exception e)
	  {
        e.printStackTrace() ; 
	  }
      return aMapping.findForward("summary");
    }

    
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
      try
	  {
        CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm ;

        String taskId = caseAssignmentForm.getTaskId() ;
        // get task given a taskId 
        GetTaskEvent getTaskEvent = new GetTaskEvent() ;
        getTaskEvent.setTaskId( taskId ) ;
        TaskResponseEvent taskResponseEvent = (TaskResponseEvent) MessageUtil.postRequest(getTaskEvent, TaskResponseEvent.class);
        ITask task = taskResponseEvent.getTask() ;
        ITaskState taskState = task.getTaskState() ;

        // create a new task for the workgroup

        CreateTaskEvent createTaskEvent = new CreateTaskEvent() ; 
        createTaskEvent.setTaskTopic(task.getTopic());
//		createTaskEvent.setDueDate(task.getDueDate());
		Calendar calendar = new GregorianCalendar( ) ;
		calendar.add(Calendar.DAY_OF_YEAR , 1) ; 
        createTaskEvent.setDueDate(calendar.getTime());
		createTaskEvent.setOwnerId(  CaseloadConstants.WORKGROUP_OID_PREPEND_STRING + caseAssignmentForm.getWorkGroupId() );
//		createTaskEvent.setTaskSubject(task.getTaskSubject());
		createTaskEvent.setTaskSubject( CaseloadConstants.NEW_ORDER_FOR_SUPERVISION) ; 
		createTaskEvent.addTaskStateItem(CaseloadConstants.SCENARIO, CaseloadConstants.SC_ASSIGN_NEW_CASE);
		createTaskEvent.addTaskStateItem(CaseloadConstants.ACTIVITY_IND, CaseloadConstants.WF_ALLOCATE_SUPERVISEE_TO_SUPERVISOR);
		createTaskEvent.addTaskStateItem(CaseloadConstants.CASE_ASSIGNMENT_ID, (String)task.getTaskState().get(CaseloadConstants.CASE_ASSIGNMENT_ID));
		createTaskEvent.addTaskStateItem(CaseloadConstants.TASK_TEXT, (String)task.getTaskState().get(CaseloadConstants.TASK_TEXT));
		createTaskEvent.addTaskStateItem(CaseloadConstants.DEFENDANT_ID, (String) task.getTaskState().get(CaseloadConstants.DEFENDANT_ID));
		createTaskEvent.setStatusCode(task.getStatusCode());
		createTaskEvent.setSeverityLevel(task.getSeverityLevel());
		
		MessageUtil.postRequest(createTaskEvent , CreateTaskEvent.class) ; 
        
        // create a new casenote 
        
        UpdateCasenoteEvent updateCasenote = new UpdateCasenoteEvent() ; 
        updateCasenote.setNotes(caseAssignmentForm.getCasenoteText());
		updateCasenote.setEntryDate(new Date());
		updateCasenote.setSupervisionOrderId(caseAssignmentForm.getSupervisionOrderId());
		
        GetSupervisionCodesEvent request = new GetSupervisionCodesEvent();
		request.setCodeTableName(CaseloadConstants.CASENOTE_SUBJECT_CODE_TABLE_NAME);
		request.setCode(CaseloadConstants.ASSIGNMENT_SUBJECT_CD);
		request.setAgencyId(SecurityUIHelper.getUserAgencyId());
		
		CodeResponseEvent codeResponse = (CodeResponseEvent) MessageUtil.postRequest(request, CodeResponseEvent.class);
		
		String assignmentCodeId = codeResponse.getCodeId();
		
		Collection subjects = new ArrayList();
		subjects.add(assignmentCodeId);
		
		updateCasenote.setSubjects(subjects);
		
		updateCasenote.setContactMethodId(CaseloadConstants.NONE_OTHER_CONTACTMETHOD);
		
		//   updateCasenote.setContextType(CaseloadConstants.SUPERVISEE_CONTEXT);
		//updateCasenote.setContextType(CasenoteContext.SUPERVISEE);
        updateCasenote.setContextType(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);

		
		MessageUtil.postRequest(updateCasenote);
		
        // setup the confirmation message 
        aRequest.setAttribute(UIConstants.CONFIRMATION_MESSAGE_REQUEST_ATTRIBUTE_NAME , UIConstants.REASSIGN_DIVISION_CONFIRMATION_MESSAGE ) ;

        caseAssignmentForm.setIsDivisionReassignment(false) ; 
	  }
      catch (Exception e)
	  {
        e.printStackTrace() ; 
	  }
      return aMapping.findForward("confirmation");

    }
    

}
