package ui.supervision.managetasks.action;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetActiveSuperviseeCasesEvent;
import messaging.administercaseload.GetCaseAssignmentEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.codetable.GetSupervisionCodesEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.domintf.IName;
import messaging.task.GetTaskEvent;
import messaging.task.UpdateTaskStatusEvent;
import messaging.task.reply.TaskResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;
import naming.CasenoteControllerServiceNames;
import naming.CodeTableControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

public class HandleCaseAssignmentAction extends JIMSBaseAction
{
    
    
    /**
     * @roseuid 464368F103D5
     */
    public HandleCaseAssignmentAction()
    {

    }

    protected void addButtonMapping(Map keyMap)
    {
    	keyMap.put("button.next" , "next") ; 
    	keyMap.put("button.paperFileReceived" , "paperFileReceived") ; 
    	
    }
    
    public ActionForward paperFileReceived(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
      try
	  {
        CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm ;
        String taskId = caseAssignmentForm.getTaskId() ; 
        GetTaskEvent getTaskEvent = new GetTaskEvent() ; 
        getTaskEvent.setTaskId(taskId) ; 
        
        TaskResponseEvent taskResponseEvent = (TaskResponseEvent) MessageUtil.postRequest(getTaskEvent , TaskResponseEvent.class ) ;
        if ( taskResponseEvent == null ) 
        {
        	throw new Exception("No task found for task id : " + taskId ) ; 
        }

        String defendantId = (String)taskResponseEvent.getTask().getTaskState().get(CaseloadConstants.TASK_STATE_SPN) ; 
        
        UpdateTaskStatusEvent updateTaskStatusEvent = new UpdateTaskStatusEvent() ; 
        updateTaskStatusEvent.setStatusCode(UIConstants.ACCEPTED_STATUS_ID) ;
        updateTaskStatusEvent.setTaskId(taskId) ; 
        CompositeResponse compositeResponse = MessageUtil.postRequest(updateTaskStatusEvent) ; 
        String caseNumber = caseAssignmentForm.getCaseNum() ; 
        String courtNumber = caseAssignmentForm.getCourtNumber() ; 
        
        // Update Case Note
        UpdateCasenoteEvent updateCasenote = (UpdateCasenoteEvent) EventFactory.getInstance(CasenoteControllerServiceNames.UPDATECASENOTE);
		updateCasenote.setContactMethodId(CaseloadConstants.NONE_OTHER_CONTACTMETHOD);
        updateCasenote.setEntryDate( new Date()) ; 
        updateCasenote.setNotes( "Paper Casefile for case number : " + caseNumber + ", court number : " +  courtNumber +" received from Intake."  ) ; 
		String supervisionOrderId = caseAssignmentForm.getSupervisionOrderId() ; 
        updateCasenote.setSupervisionOrderId( supervisionOrderId) ; 
		//updateCasenote.setContextType(CasenoteContext.SUPERVISEE);
        updateCasenote.setContextType(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
		
		GetSupervisionCodesEvent request = (GetSupervisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETSUPERVISIONCODES);
		request.setCodeTableName(CaseloadConstants.CASENOTE_SUBJECT_CODE_TABLE_NAME);
		request.setCode(CaseloadConstants.ASSIGNMENT_SUBJECT_CD);
		request.setAgencyId(SecurityUIHelper.getUserAgencyId());
		CodeResponseEvent codeResponse = (CodeResponseEvent) MessageUtil.postRequest(request, CodeResponseEvent.class);
		String assignmentCodeId = codeResponse.getCodeId();
		Collection subjects = new ArrayList();
		subjects.add(assignmentCodeId);
		updateCasenote.setSubjects(subjects);
		
        updateCasenote.setAssociates(new LinkedList()) ; 
        updateCasenote.setCollateralId(new String() ) ; 
        updateCasenote.setHowGeneratedId( new String()) ; 
        //updateCasenote.setSuperviseeId( new String()) ; 
        //updateCasenote.setSupervisionPeriodId( new String() ) ; 
        updateCasenote.setAgencyId(new String()) ; 
        MessageUtil.postRequest(updateCasenote);
	  }
      catch (Exception e)
	  {
        e.printStackTrace() ; 
	  }
      return aMapping.findForward("paperFileReceived");

    }
    

    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
      try
	  {
        CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm ;
        if (caseAssignmentForm == null )
        {
            caseAssignmentForm = new CaseAssignmentForm() ; 
        }

        String taskId = (String)aRequest.getAttribute("taskId") ;
        caseAssignmentForm.setTaskId(taskId) ; 
        
        // Get task 
        GetTaskEvent getTaskEvent = new GetTaskEvent() ; 
        getTaskEvent.setTaskId(taskId) ; 
        TaskResponseEvent taskResponseEvent = (TaskResponseEvent) MessageUtil.postRequest(getTaskEvent , TaskResponseEvent.class ) ;
        if ( taskResponseEvent == null ) 
        {
        	throw new Exception("No task found for task id : " + taskId ) ; 
        }
        
        // From task obtain defendant id and set in case assignment form 
        String defendantId = (String)taskResponseEvent.getTask().getTaskState().get(CaseloadConstants.TASK_STATE_SPN) ; 
        caseAssignmentForm.setDefendantId(defendantId) ; 

        // Obtain case assignment and set in case assignment form 
        String caseAssignmentId = (String)taskResponseEvent.getTask().getTaskState().get(CaseloadConstants.TASK_STATE_CASE_ASSIGNMENT_ID) ;
        String[] caseAssignmentStringArray  = new String[1] ; 
        caseAssignmentStringArray[0] = caseAssignmentId ; 
        caseAssignmentForm.setSelectedCaseAssignments(caseAssignmentStringArray) ; 
        
        // obtain active cases for supervisee and set in form 
        GetActiveSuperviseeCasesEvent getActiveSuperviseeCasesEvent = new GetActiveSuperviseeCasesEvent(); 
        getActiveSuperviseeCasesEvent.setDefendantId(defendantId ) ;
        getActiveSuperviseeCasesEvent.setFilteredByCaseStatus(true);
        List caseAssignmentOrderList = MessageUtil.postRequestListFilter( getActiveSuperviseeCasesEvent , ICaseAssignment.class ) ;
        caseAssignmentForm.setActiveCases( caseAssignmentOrderList ) ;
        
        // get case assignment that is about to be acknowledged 
        caseAssignmentStringArray = caseAssignmentForm.getSelectedCaseAssignments() ; 
        GetCaseAssignmentEvent getCaseAssignmentEvent = new GetCaseAssignmentEvent()  ; 
        getCaseAssignmentEvent.setCaseAssignments( caseAssignmentStringArray  ) ; 
        CaseAssignmentResponseEvent caseAssignmentResponseEvent = (CaseAssignmentResponseEvent)MessageUtil.postRequest( getCaseAssignmentEvent , CaseAssignmentResponseEvent.class ) ;
        List caseAssignmentList = caseAssignmentResponseEvent.getCaseAssignments(); 
        ICaseAssignment caseAssignmentToAcknowledge = (ICaseAssignment) caseAssignmentList.get(0) ; 
        caseAssignmentForm.setCaseToAcknowledge(caseAssignmentToAcknowledge) ;

        // get defendant name 
        IName defendantName = caseAssignmentToAcknowledge.getDefendantName();
        caseAssignmentForm.setSuperviseeName(defendantName) ; 
		if(defendantName != null){
			caseAssignmentForm.setSuperviseeNameStr(defendantName.getFormattedName());
		}
        
        // get court id from case assignment
        String courtId = caseAssignmentToAcknowledge.getCourtId() ;
        String criminalCaseId = caseAssignmentToAcknowledge.getCriminalCaseId() ; 
        String supervisionOrderId = caseAssignmentToAcknowledge.getSupervisionOrderId() ; 
        caseAssignmentForm.setCourtNumber( courtId ) ;
        caseAssignmentForm.setCaseNum( criminalCaseId ) ;
        caseAssignmentForm.setSupervisionOrderId( supervisionOrderId ) ; 
        
        
	  }
      catch (Exception e)
	  {
        e.printStackTrace() ; 
	  }
      return aMapping.findForward("reviewActiveCases");

    }

}
