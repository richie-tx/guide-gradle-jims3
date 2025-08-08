package ui.supervision.programReferral.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerprogramreferrals.SaveProgRefCasenoteEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.programReferral.CSCProgRefCasenoteUIHelper;
import ui.supervision.programReferral.form.CSCProgRefForm;

/**
 * 
 * @author cc_bjangay
 *
 */
public class SubmitProgRefCasenoteAction extends JIMSBaseAction
{

	@Override
	protected void addButtonMapping(Map keyMap) 
	{
		keyMap.put("button.finish", "finish");
	}
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm = (CSCProgRefForm)aForm;
		
		SaveProgRefCasenoteEvent requestEvent = CSCProgRefCasenoteUIHelper.getSaveCasenoteEventForCasenoteCreate(progReferralForm);
		this.postRequestEvent(requestEvent);
		
		String confirmMessage = "Program Referral casenote successfully created.";
		aRequest.setAttribute("confirmMsg", confirmMessage);
		
		return aMapping.findForward(UIConstants.CONFIRM_SUCCESS);
	}
}
