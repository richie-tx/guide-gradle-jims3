//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administersupervisee\\action\\DisplayCorrectLOSSummaryAction.java

package ui.supervision.administersupervisee.action;

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
import ui.supervision.administersupervisee.UIAdministerSuperviseeHelper;
import ui.supervision.supervisee.form.SuperviseeForm; 
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class DisplayCorrectProgramTrackerSummaryAction extends JIMSBaseAction
{

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.submit", "viewProgramTrackerSummary");
	}

	/**
	 * @roseuid 484E86E801E7
	 */
	public DisplayCorrectProgramTrackerSummaryAction() {

	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward viewProgramTrackerSummary(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {

		return aMapping.findForward(UIConstants.VIEW_PROGRAM_TRACKER_SUMMARY);

	}
}
