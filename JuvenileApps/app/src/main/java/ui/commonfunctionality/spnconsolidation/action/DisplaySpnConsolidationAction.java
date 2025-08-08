// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\commonfunctionality\\spnconsolidation\\action\\DisplaySpnConsolidationAction.java

package ui.commonfunctionality.spnconsolidation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.commonfunctionality.spnconsolidation.form.SpnConsolidationForm;

public class DisplaySpnConsolidationAction extends Action {

	/**
	 * @roseuid 452BA2CD027A
	 */
	public DisplaySpnConsolidationAction() {

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 438F22CC0018
	 */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {

		ActionForward forward = new ActionForward();
		SpnConsolidationForm spnConForm = (SpnConsolidationForm) aForm;
		spnConForm.clear();
		spnConForm.setAction("");
		forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}
}
