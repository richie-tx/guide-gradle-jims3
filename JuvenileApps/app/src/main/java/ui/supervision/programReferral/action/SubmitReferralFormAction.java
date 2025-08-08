package ui.supervision.programReferral.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerprogramreferrals.SaveProgRefCasenoteEvent;
import messaging.administerprogramreferrals.SaveReferralFormEvent;
import naming.CSAdministerProgramReferralsConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.programReferral.CSCProgRefCasenoteUIHelper;
import ui.supervision.programReferral.CSCProgRefUIHelper;
import ui.supervision.programReferral.form.CSCProgRefForm;

/**
 * 
 * @author cc_bjangay
 *
 */
public class SubmitReferralFormAction extends JIMSBaseAction
{

	@Override
	protected void addButtonMapping(Map keyMap) 
	{
		keyMap.put("button.finish", "finish");
		keyMap.put("button.referralList", "referralsView");
		keyMap.put("button.backToSelectServiceProvider", "selectServiceProviderView");
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
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progRefForm = (CSCProgRefForm)aForm;
		progRefForm.setSecondaryAction(UIConstants.CONFIRM);
		
		SaveReferralFormEvent saveRefFormEvent = CSCProgRefUIHelper.getSaveReferralFormEvent(progRefForm);
		postRequestEvent(saveRefFormEvent);
		
//		create auto casenote
		SaveProgRefCasenoteEvent saveCasenoteEvent = CSCProgRefCasenoteUIHelper.getSaveCasenoteEventForGenerateForm(progRefForm);
		this.postRequestEvent(saveCasenoteEvent);

		return aMapping.findForward(UIConstants.CONFIRM_SUCCESS);
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
	public ActionForward referralsView(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		return aMapping.findForward(CSAdministerProgramReferralsConstants.REFERRAL_SUCCESS);
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
	public ActionForward selectServiceProviderView(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progRefForm = (CSCProgRefForm)aForm;
		progRefForm.setSecondaryAction("");
		
		return aMapping.findForward(CSAdministerProgramReferralsConstants.SELECT_SP_SUCCESS);
	}
}
