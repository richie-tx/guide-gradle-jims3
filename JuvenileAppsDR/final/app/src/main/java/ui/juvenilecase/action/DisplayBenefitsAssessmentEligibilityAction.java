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

import ui.common.CodeHelper;
import ui.juvenilecase.form.ProcessBenefitsAssessmentForm;



public class DisplayBenefitsAssessmentEligibilityAction extends LookupDispatchAction
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
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
	
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
	HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
		
	public ActionForward next(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse)
	{
		
		ProcessBenefitsAssessmentForm form = (ProcessBenefitsAssessmentForm)aForm;
		
		ProcessBenefitsAssessmentForm.BenefitAssessment.TitleIVEQuestion question = form.getCurrentAssessment().getQuestion();
		String[] selectedSourceInfoId = question.getSelectedInfoSourceId();
		
		StringBuffer sb = new StringBuffer();
		
		if(selectedSourceInfoId != null && selectedSourceInfoId.length > 0)
		{	
			for(int i=0;i<selectedSourceInfoId.length;i++)
			{
				//translate Id to description
				sb.append(CodeHelper.getCodeDescriptionByCode(form.getBenefitsAssessmentInfoSourceCodeTable(), selectedSourceInfoId[i]));
				if(i < selectedSourceInfoId.length-1)
					sb.append(", ");
			}
			question.setInfoSource(sb.toString());
		}
		else
		{
			question.setInfoSource("");
		}
		
		//set pwe relationship if it has one
		/* HARDCODED RIGHT NOW TO FATHER/MOTHER, WILL NEED TO RESOLVE FROM ID
		if(question.isPrimaryWageEarnerUnderemployment())
		{
			question.setPweRelationshipToJuvenile(form.getCurrentAssessment().getGuardian().getRelationship());
			//how to get the ID?
		}*/
		
		//go into each one of the income determination object and resolve income source desc from id
		 
		 
		//Create a new instance of Benefits Assessment Calculator and get calculated values
		form.getCurrentAssessment().getQuestion().setBeneCalc(form);

		
		return aMapping.findForward(UIConstants.NEXT);
	}
	
	
}