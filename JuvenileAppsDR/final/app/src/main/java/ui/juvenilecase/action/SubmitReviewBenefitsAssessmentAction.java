package ui.juvenilecase.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.SaveBenefitsAssessmentReviewEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import naming.ActivityConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.ProcessBenefitsAssessmentForm;


public class SubmitReviewBenefitsAssessmentAction extends LookupDispatchAction
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.finish", "finish");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}
		
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}
		
	public ActionForward finish(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse)
	{
		ProcessBenefitsAssessmentForm form = (ProcessBenefitsAssessmentForm)aForm;
				 
		SaveBenefitsAssessmentReviewEvent event = new SaveBenefitsAssessmentReviewEvent(); 
		event.setAssessmentId( form.getCurrentAssessment().getAssessmentId() );
		event.setComments( form.getCurrentAssessment().getComments() );

		// Adding record in activity table
		JuvenileCasefileForm juvenileCasefileForm = (JuvenileCasefileForm)aRequest.getSession().getAttribute("juvenileCasefileForm");
		UIJuvenileHelper.createActivity(juvenileCasefileForm.getSupervisionNum(), ActivityConstants.TITLE_IV_E_ASSESSMENT_REVIEWED, form.getCurrentAssessment().getComments());
		UIJuvenileHelper.createActivity(juvenileCasefileForm.getSupervisionNum(), ActivityConstants.BENEFITS_ASSESSMENT_NOT_REQUESTED, form.getCurrentAssessment().getComments());
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		dispatch.postEvent(event);
		aRequest.setAttribute("pageType", "confirm");
		return aMapping.findForward(UIConstants.CONFIRM_SUCCESS);
	}
	
	
}