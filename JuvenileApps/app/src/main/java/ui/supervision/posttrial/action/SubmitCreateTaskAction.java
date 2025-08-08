//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\action\\DisplayCaseHistoryListAction.java

package ui.supervision.posttrial.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import messaging.posttrial.CreateWorkflowTaskEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.posttrial.form.CSCDTaskForm;

/*
 * 
 * @author mchowdhury
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class SubmitCreateTaskAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public SubmitCreateTaskAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CSCDTaskForm cForm = ( CSCDTaskForm ) aForm;
		
		    CreateWorkflowTaskEvent cEvent = new CreateWorkflowTaskEvent();
		    
			cEvent.setSubject( cForm.getTaskSubject());
			cEvent.setCaseNumber( cForm.getTaskCaseNumber() );
			cEvent.setCdi( cForm.getTaskCdi() );
			cEvent.setDueDate(cForm.getTaskDueDateStr());
			cEvent.setSpn( cForm.getSpn());
			cEvent.setNextActionId(cForm.getTaskNextActionId());
			cEvent.setPositionId(cForm.getSelectedValue());
			cEvent.setWorkgroupId(cForm.getWorkgroupId());
			cEvent.setText(cForm.getTaskText());
			cEvent.setSuperviseeName(cForm.getSuperviseeName());
			cEvent.setOfficerName(cForm.getOfficerName());
			cEvent.setProgramUnit(cForm.getProgramUnitDesc());
			cEvent.setNextActionParent(cForm.getTaskNextActionGroupId());
					
			CompositeResponse comp = MessageUtil.postRequest(cEvent);
		
		
		    ErrorResponseEvent err = (ErrorResponseEvent) MessageUtil.filterComposite(comp, ErrorResponseEvent.class);
		
			if( err != null ){
				//There is no active supervision order for this case number
				
				sendToErrorPage(aRequest,"error.generic", err.getMessage() );
				return aMapping.findForward(UIConstants.FAILURE);
			}		
			return aMapping.findForward(UIConstants.FINISH);
	}
}
