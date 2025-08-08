/*
 * Created on Mar 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.programReferral.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerprogramreferrals.UpdateProgramReferralsEvent;
import naming.CSAdministerProgramReferralsConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.programReferral.CSCProgRefUIHelper;
import ui.supervision.programReferral.form.CSCProgRefForm;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitProgRefInitiateUpdateAction extends JIMSBaseAction {

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		// TODO Auto-generated method stub
		keyMap.put("button.finish","finish");

	}

	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progRefForm = (CSCProgRefForm)aForm;

		UpdateProgramReferralsEvent updateReferralsEvt = CSCProgRefUIHelper.getUpdateProgramReferralsEvent(progRefForm);
		this.postRequestEvent(updateReferralsEvt);

		if(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_CREATE))
		{
			aRequest.setAttribute("confirmMsg", "Program Referral(s) successfully created.");
		}
		else
		if(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT))
		{
			aRequest.setAttribute("confirmMsg", "Program Referral successfully updated.");
		}
		// clean up attribute used in this flow
		aRequest.setAttribute("cautionMsg", "");
		aRequest.setAttribute("cautionMsg2", "");
			// direct to program locations page
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}
	
}
