//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\posttrial\\action\\DisplayCaseAssignmentDataControlSearchAction.java

package ui.supervision.posttrial.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.posttrial.form.CaseAssignmentDataControlForm;

/*
 * 
 * @author cshimek
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class DisplayCaseAssignmentDataControlSearchAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplayCaseAssignmentDataControlSearchAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link", "link");
		keyMap.put("button.backToSearch", "backToSearch");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseAssignmentDataControlForm cadsForm = (CaseAssignmentDataControlForm) aForm;
		cadsForm.clear();
		cadsForm.setUserAgencyId(SecurityUIHelper.getUserAgencyId());
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward backToSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseAssignmentDataControlForm cadsForm = (CaseAssignmentDataControlForm) aForm;
		cadsForm.clear();		
		return aMapping.findForward(UIConstants.SUCCESS);
	}
}
