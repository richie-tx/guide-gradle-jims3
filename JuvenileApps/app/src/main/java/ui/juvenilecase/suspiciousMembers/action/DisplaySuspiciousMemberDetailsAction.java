//Source file: C:\\views\\juvenilecasework\\app\\src\\ui\\juvenilecase\\suspiciousMembers\\action\\DisplaySuspiciousMemberSearchAction.java

package ui.juvenilecase.suspiciousMembers.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.suspiciousMembers.UISuspiciousMemberDetailsHelper;
import ui.juvenilecase.suspiciousMembers.form.SuspiciousMemberForm;

/*
 * 
 * @author cShimek
 * 
  */

public class DisplaySuspiciousMemberDetailsAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplaySuspiciousMemberDetailsAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link", "link");

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
		SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
		String memberNum = aRequest.getParameter("memberID");
		UISuspiciousMemberDetailsHelper.loadMemberDetails(memberNum, smForm);
		aRequest.setAttribute("memberID", null);
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
}