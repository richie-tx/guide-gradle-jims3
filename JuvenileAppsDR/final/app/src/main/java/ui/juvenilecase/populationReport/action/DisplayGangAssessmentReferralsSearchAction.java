package ui.juvenilecase.populationReport.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetJuvenileActivityTypeCodesEvent;
import messaging.codetable.criminal.reply.JuvenileActivityTypeCodeResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.activities.form.ActivitiesForm;
import ui.juvenilecase.casefile.form.AssessmentReferralForm;

/**
 * 
 * @author anpillai
 *
 * 
 */
public class DisplayGangAssessmentReferralsSearchAction extends Action
{

	/**
	 * @roseuid 4278CA1C002D
	 */
	public DisplayGangAssessmentReferralsSearchAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4278C7B803C9
	 */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	AssessmentReferralForm assessmentRefForm = (AssessmentReferralForm) aForm;
	assessmentRefForm.setStartDateAsStr("");
	assessmentRefForm.setEndDateAsStr("");
	assessmentRefForm.setStatus("");
	return aMapping.findForward("success");

    }
	
	
}
