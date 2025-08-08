package ui.juvenilecase.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.form.ProcessBenefitsAssessmentForm;


public class DisplayBenefitsAssessmentReviewAction extends LookupDispatchAction
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.reviewAssessment", "reviewAssessment");
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
		
	
	public ActionForward reviewAssessment(
						ActionMapping aMapping,
						ActionForm aForm,
						HttpServletRequest aRequest,
						HttpServletResponse aResponse)
	{
		ProcessBenefitsAssessmentForm form = (ProcessBenefitsAssessmentForm)aForm;
		form.getCurrentAssessment().setComments("");
		form.setReview(true);
		aRequest.setAttribute("pageType", "review");
 	
		return aMapping.findForward("reviewAssessment");
	}
	
	
	
	
}