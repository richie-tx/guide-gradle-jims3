//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\posttrial\\action\\DisplayCreateTaskSummaryAction.java

package ui.supervision.posttrial.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetActiveCasesByCaseEvent;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.task.reply.TaskNextActionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.managetasks.helper.UIManagetasksHelper;
import ui.supervision.posttrial.form.CSCDTaskForm;

/*
 * 
 * @author mchowdhury
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class DisplayCreateTaskSummaryAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplayCreateTaskSummaryAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CSCDTaskForm cForm = (CSCDTaskForm) aForm;
		cForm.setTaskSeverityLevel("");
		cForm.setTaskNextActionGroup("");
		cForm.setTaskNextAction("");
		
		UIManagetasksHelper taskHelper = UIManagetasksHelper.getInstance();
		
		if (cForm.getTaskSeverityLevelId() != null && !cForm.getTaskSeverityLevelId().equals("")){
			Iterator tslIter = cForm.getTaskSeverityLevels().iterator();
			while (tslIter.hasNext())
			{
				CodeResponseEvent cre1 = (CodeResponseEvent)tslIter.next();
				if (cre1.getCode().equalsIgnoreCase(cForm.getTaskSeverityLevelId())){
					cForm.setTaskSeverityLevel(cre1.getDescription());
					break;
				}
			}
		}
		if (cForm.getTaskNextActionGroupId() != null && !cForm.getTaskNextActionGroupId().equals("")){
			Iterator tnagIter = cForm.getTaskNextActionGroups().iterator();
			while (tnagIter.hasNext())
			{
				TaskNextActionResponseEvent tnar = (TaskNextActionResponseEvent)tnagIter.next();
				if (tnar.getParentAction().equalsIgnoreCase(cForm.getTaskNextActionGroupId())){
					Iterator tnaIter = tnar.getNextActions().iterator();
					while (tnaIter.hasNext())
					{
						CodeResponseEvent cre1 = (CodeResponseEvent)tnaIter.next();
						if (cre1.getCode().equalsIgnoreCase(cForm.getTaskNextActionId())){
							cForm.setTaskNextAction(cre1.getDescription());
							break;
						}
					}
					
				}
			}
		}
		
		// Check for active order/case
		GetActiveCasesByCaseEvent event = (GetActiveCasesByCaseEvent) EventFactory.getInstance(CaseloadControllerServiceNames.GETACTIVECASESBYCASE);
        
		StringBuffer sb = new StringBuffer();
		sb.append( cForm.getTaskCdi()).append( cForm.getTaskCaseNumber() );
		
		String crimCaseId = sb.toString();
		
		event.setCriminalCaseId( crimCaseId );
		
        CaseAssignmentResponseEvent assignmentResponse = (CaseAssignmentResponseEvent) 
        							MessageUtil.postRequest( event, CaseAssignmentResponseEvent.class);
        
        List activeCases = assignmentResponse.getCaseAssignments();
        if(activeCases == null || activeCases.isEmpty()){
	    	
        	sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"No Active Cases Found");
	    	
        	return aMapping.findForward( UIConstants.FAILURE );
	    }

        for ( int x = 0; x < activeCases.size(); x++ ){
        	
        	CaseAssignmentTO cato = (CaseAssignmentTO) activeCases.get(x);
        	if (cato.getCriminalCaseId() != null && !cato.getCriminalCaseId().equals("")){
        		
         		if ( crimCaseId.equals( cato.getCriminalCaseId() )){
         			
         			 if ( taskHelper.hasCurrentTasks( cato.getDefendantId(), crimCaseId )){
                     	
                     	 sendToErrorPage( aRequest, "error.generic", "Pending tasks exist for a workgroup or staff position for this case.  Process existing tasks before reassigning case");
                     	 return aMapping.findForward( UIConstants.FAILURE );
                      }
         				 
         			cForm.setSpn( cato.getDefendantId());
         			cForm.setCaseAssignIds( cato.getCaseAssignmentId() );
         			cForm.setSupervisionOrderIds( cato.getSupervisionOrderId() );
         			String s = cato.getSuperviseeName().trim();
         			cForm.setSuperviseeName( s );
        			
        			return aMapping.findForward(UIConstants.NEXT);
        			
        		}
        	}
        }
		
        sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Case not on Active Supervision");
		return aMapping.findForward( UIConstants.FAILURE );
	}

}
