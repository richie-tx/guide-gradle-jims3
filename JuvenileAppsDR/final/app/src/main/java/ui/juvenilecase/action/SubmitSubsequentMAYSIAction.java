package ui.juvenilecase.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.mentalhealth.SaveSubsequentMAYSIDataEvent;
import messaging.mentalhealth.reply.MAYSISubAssessResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.juvenilecase.form.MentalHealthForm;

/**
 * @author dgibler
 *  
 */
public class SubmitSubsequentMAYSIAction extends LookupDispatchAction
{

    /**
     * @roseuid 42791FD2005D
     */
    public SubmitSubsequentMAYSIAction()
    {

    }

    protected Map getKeyMethodMap()
    {
        Map buttonMap = new HashMap();
        buttonMap.put("button.finish", "finish");
        buttonMap.put("button.back", "back");
        buttonMap.put("button.cancel", "cancel");
        return buttonMap;
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42791D7B0234
     */
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {

        MentalHealthForm mhForm = (MentalHealthForm) aForm;

        SaveSubsequentMAYSIDataEvent request = (SaveSubsequentMAYSIDataEvent) EventFactory
                .getInstance(JuvenileMentalHealthControllerServiceNames.SAVESUBSEQUENTMAYSIDATA);

        request.setJuvenileMAYSIAssessId(mhForm.getMaysiId());
        request.setProviderTypeReferredId(mhForm.getProviderReferredTypeId());
        request.setAssessmentComplete(Boolean.valueOf(mhForm.getWasSubsAssessmentCompleted()).booleanValue());
        request.setReviewComments(mhForm.getSubsAssessmentComments());
        request.setReviewDate(new Date());
        request.setReviewUserId(UIUtil.getCurrentUserID());
        request.setSubReferral(Boolean.valueOf(mhForm.getSubsAssessmentReferral()).booleanValue());

        MAYSISubAssessResponseEvent detail = (MAYSISubAssessResponseEvent) MessageUtil.postRequest(request,
                MAYSISubAssessResponseEvent.class);
        if (detail == null)
        {
            return aMapping.findForward(UIConstants.FAILURE);
        }

        // Adding record in activity table
        //		UIJuvenileHelper.createActivity(mhForm.getCasefileId(),
        // ActivityConstants.SUBSEQUENT_ASSESSMENT_FOR_MENTAL_HEALTH, "");
        // The activity was removed because it is not needed (from Pam B) and it dies anyway since
        // casefileId is required for an activity but is always null.
        mhForm.setAssessmentReviewDate(detail.getAssessmentReviewdate());
        mhForm.setAssessmentReviewTime(detail.getAssessmentReviewtime());
        mhForm.setAssessmentOfficerName(detail.getSubAssessOfficerName().getFormattedName());
        mhForm.setAssessmentOption(detail.getAssessmentOption());
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.CANCEL);
    }
}
