/**
 * 
 */
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
import ui.supervision.supervisee.form.SuperviseeForm;

/**
 * @author dwilliamson
 *
 */
public class DisplayAddDNASummaryAction extends JIMSBaseAction {
	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.submit", "viewDNASummary");
	}

	/**
	 * 
	 */
	public DisplayAddDNASummaryAction() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward viewDNASummary(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {

		return aMapping.findForward(UIConstants.VIEW_DNA_SUMMARY);

	}

	@Override
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		
		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		
		// cancel button - put the original DNA Date and DNA flag back onto the form
		superviseeForm.setDnaCollectedDate(superviseeForm.getDnaCurrentRecordDate());
		superviseeForm.setDnaFlagInd(superviseeForm.isDnaCurrentRecordFlagInd());
		
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}
	
	
}
