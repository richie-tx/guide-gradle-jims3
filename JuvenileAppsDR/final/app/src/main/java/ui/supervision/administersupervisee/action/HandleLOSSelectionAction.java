//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administersupervisee\\action\\HandleLOSSelectionAction.java

package ui.supervision.administersupervisee.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.administersupervisee.reply.SupervisionLevelResponseEvent;
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

public class HandleLOSSelectionAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.correct", "correct");
		keyMap.put("button.delete", "delete");
	}

	/**
	 * @roseuid 484E86E80300
	 */
	public HandleLOSSelectionAction() {

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
		
		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		myHeaderForm.setSuperviseeId(superviseeId);
		UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);

		String selectedSupervisionLevelHistoryId = superviseeForm
				.getSelectedValue();
		
		ActionForward forward = null;
		if (selectedSupervisionLevelHistoryId == null ||
				selectedSupervisionLevelHistoryId.equals("")){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, HISTORY_ID_MISSING);
			forward = aMapping.findForward(UIConstants.CANCEL);
		} else {
			superviseeForm.setSelectedValue("");
			List losHistoriesList = superviseeForm.getLosHistories();
			Iterator iter = losHistoriesList.iterator();

			while (iter.hasNext()) {
				SupervisionLevelResponseEvent supervisionLevel = (SupervisionLevelResponseEvent) iter
					.next();

				if (selectedSupervisionLevelHistoryId
					.equalsIgnoreCase(supervisionLevel
							.getSupervisionLevelHistoryId())) {
					if (supervisionLevel.isCurrentLOS()){
			            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Correction not allowed on current LOS");
						forward = aMapping.findForward(UIConstants.FAILURE);
						break;
					} else {
						superviseeForm.setSupervisionLevelHistoryId(supervisionLevel
						.getSupervisionLevelHistoryId());
						superviseeForm.setEffectiveDate(DateUtil.dateToString(
								supervisionLevel.getLosEffectiveDate(),
								UIConstants.DATE_FMT_1));
						superviseeForm.setSupervisionLevel(supervisionLevel
								.getSupervisionLevelId());
						superviseeForm.setLosComments(supervisionLevel.getComments());
				
						superviseeForm.setPriorEffectiveDate("");
						superviseeForm.setSubsequentEffectiveDate("");
						forward = aMapping.findForward(UIConstants.LOS_CORRECT);
						break;
					}
				}
			}
			
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
		
		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		myHeaderForm.setSuperviseeId(superviseeId);
		UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);

		String selectedSupervisionLevelHistoryId = superviseeForm
				.getSelectedValue();
		
		ActionForward forward = null;
		if (selectedSupervisionLevelHistoryId == null ||
				selectedSupervisionLevelHistoryId.equals("")){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, HISTORY_ID_MISSING);
			forward = aMapping.findForward(UIConstants.CANCEL);
		} else {		
			superviseeForm.setSelectedValue("");
			List losHistoriesList = superviseeForm.getLosHistories();
			Iterator iter = losHistoriesList.iterator();

			while (iter.hasNext()) {
				SupervisionLevelResponseEvent supervisionLevel = (SupervisionLevelResponseEvent) iter
					.next();

				if (selectedSupervisionLevelHistoryId
					.equalsIgnoreCase(supervisionLevel
							.getSupervisionLevelHistoryId())) {

						superviseeForm.setSupervisionLevelHistoryId(supervisionLevel
								.getSupervisionLevelHistoryId());
						superviseeForm.setEffectiveDate(DateUtil.dateToString(
								supervisionLevel.getLosEffectiveDate(),
								UIConstants.DATE_FMT_1));
						superviseeForm.setSupervisionLevel(supervisionLevel
								.getSupervisionLevelId());
						superviseeForm.setLosComments(supervisionLevel.getComments());
						break;
				}
			}
			forward = aMapping.findForward(UIConstants.LOS_DELETE);
		}
		return forward;
	}
	
	/**
	 * Override Cancel method implementation
	 * @param aMapping  - the struts mapping
	 * @param aForm -- the struts form
	 * @param aRequest -- the request object
	 * @param aResponse -- the response object
	 * @return -- an action forward for "Cancel"
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		
		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		// cancel button - put the original LOS Effective Date and Supervision Level back onto the form
		superviseeForm.setEffectiveDate(superviseeForm.getEffectiveLosDateCurrentRecord());
		superviseeForm.setSupervisionLevel(superviseeForm.getSupervisionLevelCurrentRecord());
		superviseeForm.setLosComments(superviseeForm.getLosCommentsCurrentRecord());
		
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}
	
	/**
	 * Override Back method implementation
	 * @param aMapping  - the struts mapping
	 * @param aForm -- the struts form
	 * @param aRequest -- the request object
	 * @param aResponse -- the response object
	 * @return -- an action forward for "Back"
	 */
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		
		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		// cancel button - put the original LOS Effective Date and Supervision Level back onto the form
		superviseeForm.setEffectiveDate(superviseeForm.getEffectiveLosDateCurrentRecord());
		superviseeForm.setSupervisionLevel(superviseeForm.getSupervisionLevelCurrentRecord());
		superviseeForm.setLosComments(superviseeForm.getLosCommentsCurrentRecord());
		
		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		return forward;
	}
	
}
