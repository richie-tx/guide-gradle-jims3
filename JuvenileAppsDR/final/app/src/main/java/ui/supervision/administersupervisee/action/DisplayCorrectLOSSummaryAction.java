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

public class DisplayCorrectLOSSummaryAction extends JIMSBaseAction
{

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.submit", "viewLOSSummary");
	}

	/**
	 * @roseuid 484E86E801E7
	 */
	public DisplayCorrectLOSSummaryAction() {

	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward viewLOSSummary(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {

		return aMapping.findForward(UIConstants.VIEW_LOS_SUMMARY);

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


