package ui.supervision.programReferral.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerprogramreferrals.ChangeReferralStatusEvent;
import messaging.administerprogramreferrals.DeleteProgramReferralEvent;
import messaging.administerprogramreferrals.ExitProgramReferralEvent;
import messaging.administerprogramreferrals.ReferProgramReferralEvent;
import messaging.administerprogramreferrals.SaveProgRefCasenoteEvent;
import messaging.administerprogramreferrals.SubmitProgramReferralEvent;
import messaging.administerprogramreferrals.UpdateProgramReferralEvent;
import messaging.administerprogramreferrals.reply.SubmitExitProgRefResponseEvent;
import naming.CSAdministerProgramReferralsConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.managetasks.form.TasksSearchForm;
import ui.supervision.programReferral.CSCProgRefCasenoteUIHelper;
import ui.supervision.programReferral.CSCProgRefUIHelper;
import ui.supervision.programReferral.form.CSCProgRefCaseloadForm;
import ui.supervision.programReferral.form.CSCProgRefForm;

/**
 * 
 * @author cc_bjangay
 *
 */
public class SubmitProgReferralAction extends JIMSBaseAction
{

	@Override
	protected void addButtonMapping(Map keyMap) 
	{
		keyMap.put("button.finish","finish");
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
		CSCProgRefForm progRefForm = (CSCProgRefForm)aForm;
		
		CSCProgRefCaseloadForm progRefCaseloadForm = (CSCProgRefCaseloadForm) this.getSessionForm(aMapping, aRequest, "cscProgRefCaseloadForm" , true);
		TasksSearchForm  taskForm = (TasksSearchForm) this.getSessionForm(aMapping, aRequest, "tasksSearchForm" , true);
		
		if(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_SUBMITREFERRAL))
		{
			SubmitProgramReferralEvent submitProgReferralEvt = CSCProgRefUIHelper.getSubmitProgRefEvent(progRefForm);
			SubmitExitProgRefResponseEvent responseEvent = (SubmitExitProgRefResponseEvent)this.postRequestEvent(submitProgReferralEvt, SubmitExitProgRefResponseEvent.class);
			
//			create auto casenote
			SaveProgRefCasenoteEvent saveCasenoteEvent = CSCProgRefCasenoteUIHelper.getSaveCasenoteEventForSubmitRef(progRefForm);
			this.postRequestEvent(saveCasenoteEvent);
			
			if(responseEvent!=null)
			{
				if(responseEvent.isTRecordGenerated())
				{
					aRequest.setAttribute("confirmMsg", "Program Referral successfully submitted. T33 generated.");
				}
				else
				{
					aRequest.setAttribute("confirmMsg", "Program Referral successfully submitted.");
				}
			}
		}
		else if(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_DELETE))
		{
			DeleteProgramReferralEvent deleteProgReferralEvt = CSCProgRefUIHelper.getDeleteProgRefEvent(progRefForm);
			this.postRequestEvent(deleteProgReferralEvt);
			
			aRequest.setAttribute("confirmMsg", "Program Referral successfully deleted.");
		}
		else if(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_EXITREFERRAL))
		{
			ExitProgramReferralEvent exitProgReferralEvt = CSCProgRefUIHelper.getExitProgRefEvent(progRefForm);
			SubmitExitProgRefResponseEvent responseEvent = (SubmitExitProgRefResponseEvent)this.postRequestEvent(exitProgReferralEvt, SubmitExitProgRefResponseEvent.class);
			
//			create auto casenote
			SaveProgRefCasenoteEvent saveCasenoteEvent = CSCProgRefCasenoteUIHelper.getSaveCasenoteEventForExitRef(progRefForm);
			this.postRequestEvent(saveCasenoteEvent);
			
			if(responseEvent!=null)
			{
				if(responseEvent.isTRecordGenerated())
				{
					aRequest.setAttribute("confirmMsg", "Program Referral successfully exited. T34 generated.");
				}
				else
				{
					aRequest.setAttribute("confirmMsg", "Program Referral successfully exited.");
				}
			}
			
		}
		else if(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_REMOVEENTRY))
		{
			ChangeReferralStatusEvent removeEntryEvent = CSCProgRefUIHelper.getChangeReferralStatusEvent(progRefForm);
			this.postRequestEvent(removeEntryEvent);
			
//			create auto casenote
			SaveProgRefCasenoteEvent saveCasenoteEvent = CSCProgRefCasenoteUIHelper.getSaveCasenoteEventForRemoveEntry(progRefForm);
			this.postRequestEvent(saveCasenoteEvent);
			
			aRequest.setAttribute("confirmMsg", "Program Referral entry successfully removed.");
		}
		else if(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_REMOVEEXIT))
		{
			ChangeReferralStatusEvent removeEntryEvent = CSCProgRefUIHelper.getChangeReferralStatusEvent(progRefForm);
			this.postRequestEvent(removeEntryEvent);
			
//			create auto casenote
			SaveProgRefCasenoteEvent saveCasenoteEvent = CSCProgRefCasenoteUIHelper.getSaveCasenoteEventForRemoveExit(progRefForm);
			this.postRequestEvent(saveCasenoteEvent);
			
			aRequest.setAttribute("confirmMsg", "Program Referral exit successfully removed.");
		}
		else if((progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_UPDATE_SUBMIT)) ||
				(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_UPDATE_EXIT)))	
		{
			UpdateProgramReferralEvent updateProgRefEvent = CSCProgRefUIHelper.getUpdateProgRefEvent(progRefForm);
			this.postRequestEvent(updateProgRefEvent);
			
			aRequest.setAttribute("confirmMsg", "Program Referral successfully updated.");
		}
		else if(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_REREFERRAL))
		{
			ReferProgramReferralEvent referProgRefEvent = CSCProgRefUIHelper.getReferProgRefEvent(progRefForm);
			this.postRequestEvent(referProgRefEvent);
			
//			create auto casenote
			SaveProgRefCasenoteEvent saveCasenoteEvent = CSCProgRefCasenoteUIHelper.getSaveCasenoteEventForRereferral(progRefForm);
			this.postRequestEvent(saveCasenoteEvent);

			aRequest.setAttribute("confirmMsg", "Program Referral re-referral successful.");
		}
		if ( progRefCaseloadForm.getReferralsList().size()> 1 && taskForm.getAction().equalsIgnoreCase("basic")) {
			return aMapping.findForward("caseloadByProgReferProvPageFlow");
		}
		return aMapping.findForward(UIConstants.CONFIRM_SUCCESS);
	}
	
}
