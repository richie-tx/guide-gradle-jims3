//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\posttrial\\action\\SubmitCaseAssignmentDataControlUpdateAction.java

package ui.supervision.posttrial.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.posttrial.DeleteCaseAssignmentDataControlEvent;
import messaging.posttrial.UpdateCaseAssignmentDataControlEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.posttrial.form.CaseAssignmentDataControlForm;

/*
 * 
 * @author cshimek
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class SubmitCaseAssignmentDataControlUpdateAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public SubmitCaseAssignmentDataControlUpdateAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
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
		CaseAssignmentDataControlForm cadsForm = (CaseAssignmentDataControlForm) aForm;
		String msg = "Case Assignment not processed for Case # " + cadsForm.getCaseNum() + ".";;
		if (cadsForm.getSecondaryAction().equals(UIConstants.UPDATE)){
			UpdateCaseAssignmentDataControlEvent uEvent = new UpdateCaseAssignmentDataControlEvent();
		    uEvent.setAssignmentId(cadsForm.getCurrentCaseAssignment().getCaseAssignmentId());
		    uEvent.setOrganizationId(cadsForm.getDivisionPgmUnitId()); 
		    uEvent.setPositionAssignmentDate(DateUtil.stringToDate(cadsForm.getPositionAssignmentDateStr(), DateUtil.DATE_FMT_1));
		    uEvent.setProgramUnitAssignmentDate(DateUtil.stringToDate(cadsForm.getPgmUnitAssignmentDateStr(),DateUtil.DATE_FMT_1));
		    uEvent.setStaffPositionId(cadsForm.getCurrentOfficerId()); 
		    
		    CompositeResponse response = MessageUtil.postRequest(uEvent);        
		    ReturnException returnException = (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);

		    if (returnException == null) {
				msg = "Case Assignment successfully updated for Case # " + cadsForm.getCaseNum() + ".";
		    }else{
		    	ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
								"Error occurred saving Case Assignment updates."));
				saveErrors(aRequest, errors);
		    } 
		} else {
			if (cadsForm.getSecondaryAction().equals(UIConstants.CORRECT)){
				UpdateCaseAssignmentDataControlEvent cEvent = new UpdateCaseAssignmentDataControlEvent();
			    cEvent.setAssignmentHistId(cadsForm.getSelectedValue()); 
			    cEvent.setOrganizationId(cadsForm.getDivisionPgmUnitId()); 
			    cEvent.setPositionAssignmentDate(DateUtil.stringToDate(cadsForm.getPositionAssignmentDateStr(), DateUtil.DATE_FMT_1));
			    cEvent.setProgramUnitAssignmentDate(DateUtil.stringToDate(cadsForm.getPgmUnitAssignmentDateStr(),DateUtil.DATE_FMT_1));
			    cEvent.setStaffPositionId(cadsForm.getCurrentOfficerId()); 
			    
		        CompositeResponse response = MessageUtil.postRequest(cEvent);        
				ReturnException returnException = (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
	
				if (returnException == null) {
					msg = "Case Assignment successfully corrected for Case # " + cadsForm.getCaseNum() + ".";
			    }else{
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
									"Error occurred saving Case Assignment corrections."));
					saveErrors(aRequest, errors);
			    } 
			} else {	
				if (cadsForm.getSecondaryAction().equals(UIConstants.DELETE)){
					DeleteCaseAssignmentDataControlEvent dEvent = new DeleteCaseAssignmentDataControlEvent();
					 dEvent.setCaseAssignmentHistId(cadsForm.getSelectedValue()); //"4300");
			    
			        CompositeResponse response = MessageUtil.postRequest(dEvent);        
					ReturnException returnException = (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
		
					if (returnException == null) {
						msg = "Case Assignment successfully deleted for Case # " + cadsForm.getCaseNum() + ".";
				    }else{
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
										"Error occurred deleting Case Assignment."));
						saveErrors(aRequest, errors);
				    } 
				}	
			}
		}	
		cadsForm.setConfirmMsg(msg); 
		return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest         if (cadsForm.getCaseAssignmentState().equalsIgnoreCase(CaseloadConstants.PROGRAM_UNIT_ASSIGNED) ){
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseAssignmentDataControlForm cadsForm = (CaseAssignmentDataControlForm) aForm;
		cadsForm.clear();
		return aMapping.findForward(UIConstants.CANCEL);
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest         
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseAssignmentDataControlForm cadsForm = (CaseAssignmentDataControlForm) aForm;
		String forwardStr = UIConstants.BACK;
		if (cadsForm.getCaseAssignmentState().equalsIgnoreCase(CaseloadConstants.PROGRAM_UNIT_ASSIGNED) ){
			forwardStr = UIConstants.BACK_MODIFY;
		}
		return aMapping.findForward(forwardStr);
	}
}