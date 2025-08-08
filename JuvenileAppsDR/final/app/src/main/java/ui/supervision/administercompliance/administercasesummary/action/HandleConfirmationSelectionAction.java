// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasesummary\\HandleConfirmationSelectionAction.java

package ui.supervision.administercompliance.administercasesummary.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;
import ui.supervision.posttrial.form.CSCDTaskForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class HandleConfirmationSelectionAction extends JIMSBaseAction {

	/**
	 * @roseuid 464368F103D5
	 */
	public HandleConfirmationSelectionAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.caseHistory", "caseHistory");
		keyMap.put("button.tasks", "tasks");		
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward caseHistory(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		aRequest.setAttribute("superviseeId", csForm.getSuperviseeId());
		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		myHeaderForm.setSuperviseeId(csForm.getSuperviseeId());
		UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
		return aMapping.findForward(UIConstants.CASE_HISTORY_SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward tasks(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CSCDTaskForm ctForm = (CSCDTaskForm) getSessionForm(aMapping, aRequest, "cscdTaskForm", true);
		ctForm.setSearchById("");
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		ctForm.setTaskCdi(csForm.getCdi());
		ctForm.setTaskCaseNumber(csForm.getCaseNum());	
		return aMapping.findForward(UIConstants.TASK_SUCCESS);
	}	

}