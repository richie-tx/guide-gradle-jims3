//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administersupervisee\\action\\HandleLOSSelectionAction.java

package ui.supervision.administersupervisee.action;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administersupervisee.reply.ProgramTrackerResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisee.form.SuperviseeForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class HandleProgramTrackerSelectionAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.correct", "correct");
		keyMap.put("button.delete", "delete");
	}

	/**
	 * @roseuid 484E86E80300
	 */
	public HandleProgramTrackerSelectionAction() {

	}
    private static final String HISTORY_ID_MISSING = "HttpRequest info missing after CANCEL.  Please try again.";

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward correct(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {

		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		String superviseeId=superviseeForm.getSuperviseeId();
		superviseeForm.setAction(UIConstants.UPDATE);
		superviseeForm.setConfirmMessage("");
		
		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		myHeaderForm.setSuperviseeId(superviseeId);
		UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);

		String selectedTrackerHistoryId = superviseeForm.getSelectedValue();
		
		ActionForward forward = null;
		if (selectedTrackerHistoryId == null ||
				selectedTrackerHistoryId.equals("")){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, HISTORY_ID_MISSING);
			forward = aMapping.findForward(UIConstants.CANCEL);
		} else {
			superviseeForm.setSelectedValue("");
			this.setProgramTrackerHistoryInfo(superviseeForm, selectedTrackerHistoryId);
			forward = aMapping.findForward(UIConstants.CORRECT_SUCCESS);
		}
		return forward;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward delete(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {

		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		String superviseeId=superviseeForm.getSuperviseeId();
		superviseeForm.setAction(UIConstants.DELETE);
		superviseeForm.setConfirmMessage("");
		
		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		myHeaderForm.setSuperviseeId(superviseeId);
		UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);

		String selectedTrackerHistoryId = superviseeForm.getSelectedValue();
		
		ActionForward forward = null;
		if (selectedTrackerHistoryId == null ||
				selectedTrackerHistoryId.equals("")){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, HISTORY_ID_MISSING);
			forward = aMapping.findForward(UIConstants.CANCEL);
		} else {		
			superviseeForm.setSelectedValue("");
			
			this.setProgramTrackerHistoryInfo(superviseeForm, selectedTrackerHistoryId);
			forward = aMapping.findForward(UIConstants.VIEW_PROGRAM_TRACKER_SUMMARY);
		}
		return forward;
	}

	private void setProgramTrackerHistoryInfo(SuperviseeForm superviseeForm, String selectedRecordId){
		
		List histList = superviseeForm.getProgramTrackerHistories();

		for (int i = 0; i < histList.size(); i++) {
			
			ProgramTrackerResponseEvent re = (ProgramTrackerResponseEvent) histList.get(i);
			if (re.getSuperviseeHistoryId().equals(selectedRecordId)){
				superviseeForm.setProgramTrackerHistoryId(re.getSuperviseeHistoryId());
				superviseeForm.setProgramTrackerId(re.getProgramTrackerId());
				superviseeForm.setProgramTrackerEffectiveDate(DateUtil.dateToString(
						re.getProgramTrackerEffectiveDate(),
						UIConstants.DATE_FMT_1));
				superviseeForm.setProgramTrackerEndDate(DateUtil.dateToString(
						re.getProgramTrackerEndDate(),
						UIConstants.DATE_FMT_1));
				if (re.getProgramTrackerEndDate() != null){
					superviseeForm.setProgramTrackerEnded("Y");
				} else {
					superviseeForm.setProgramTrackerEnded("N");
				}
				break;
			}
			
		}
	}
}
