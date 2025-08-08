//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\posttrial\\action\\DisplayCaseAssignmentDataControlSummaryAction.java

package ui.supervision.posttrial.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.posttrial.reply.CaseAssignmentOfficerResponseEvent;
import naming.UIConstants;

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

public class DisplayCaseAssignmentDataControlSummaryAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplayCaseAssignmentDataControlSummaryAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.cancel", "cancel");
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
		CaseAssignmentDataControlForm cadsForm = (CaseAssignmentDataControlForm) aForm;
//		cadsForm.setOfficerName("Tucker, Jennifer | GG GGPosition Name");
		if (cadsForm.getOfficerList() != null){
			int len = cadsForm.getOfficerList().size(); 
			for (int x=0; x<len; x++){
				CaseAssignmentOfficerResponseEvent caore = (CaseAssignmentOfficerResponseEvent) cadsForm.getOfficerList().get(x);
				if (caore.getStaffPositionId() != null && caore.getStaffPositionId().equals(cadsForm.getSelectedOfficerId())){
					cadsForm.setOfficerName(caore.getDisplayLiteral());
					cadsForm.setCurrentOfficerId(cadsForm.getSelectedOfficerId());
					break;
				}
			}
		}	

		return aMapping.findForward(UIConstants.NEXT_SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseAssignmentDataControlForm cadsForm = (CaseAssignmentDataControlForm) aForm;
		cadsForm.clear();
		return aMapping.findForward(UIConstants.CANCEL);
	}
}