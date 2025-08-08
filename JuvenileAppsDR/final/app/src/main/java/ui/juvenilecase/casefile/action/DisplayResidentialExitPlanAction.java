package ui.juvenilecase.casefile.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.GetCasefileClosingDetailsEvent;
import messaging.casefile.GetResponseEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.casefile.reply.QuestionAnswerResponseEvent;
import messaging.caseplan.GetCaseplanDetailsEvent;
import messaging.caseplan.reply.PlacementInfoResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasePlanControllerServiceNames;
import naming.JuvenileCasefileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.form.CasefileClosingForm;
import ui.juvenilecase.casefile.form.ResidentialExitPlanForm;
import ui.juvenilecase.form.JuvenileCasefileForm;

public class DisplayResidentialExitPlanAction extends LookupDispatchAction
{

    /**
     * @roseuid 439608BB0278
     */
    public DisplayResidentialExitPlanAction()
    {

    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.CANCEL);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }

    public ActionForward displayResidentialExitPlan(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
	    //#bug fix 35750 starts
    	JuvenileCasefileForm casefileForm=(JuvenileCasefileForm)UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
        CasefileClosingForm myClosingForm = (CasefileClosingForm) UIJuvenileHelper.getJuvenileCasefileClosingForm(aRequest,casefileForm.getSupervisionNum());
        //#bug fix 35750 ends
        ResidentialExitPlanForm myResidenitalForm = (ResidentialExitPlanForm) aForm;
        myResidenitalForm.clearAll();
        myResidenitalForm.setClosingInfoId(myClosingForm.getClosingInfoId());
       
        myResidenitalForm.setSecondaryAction("");
        myResidenitalForm.setSelectedValue("");

        GetCasefileClosingDetailsEvent casefileClosingDetailEvent = (GetCasefileClosingDetailsEvent) EventFactory
                .getInstance(JuvenileCasefileControllerServiceNames.GETCASEFILECLOSINGDETAILS);
        casefileClosingDetailEvent.setSupervisionNumber(myClosingForm.getSupervisionNumber());

        CompositeResponse compositeResponse = MessageUtil.postRequest(casefileClosingDetailEvent);
        
        CasefileClosingResponseEvent event = (CasefileClosingResponseEvent) MessageUtil.filterComposite(compositeResponse,
                CasefileClosingResponseEvent.class);
        if (event != null)
        {
            UIJuvenileCasefileClosingHelper.setResidentialExitPlanInfoFROMClosingRespEvt(myResidenitalForm, event);
            myResidenitalForm.setAction(UIConstants.UPDATE);
            myClosingForm.setReportFileLocOnServer(event.getExitPlanTemplateLocation());
        }
        else
        	 myResidenitalForm.setAction(UIConstants.CREATE);
        // Get the residential exit plan questions
        GetResponseEvent rEvent = (GetResponseEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETRESPONSE);
        rEvent.setReferenceId(myClosingForm.getClosingInfoId());
        rEvent.setResponseType(myResidenitalForm.getReportType());
        rEvent.setResponseTemplateLocation(myClosingForm.getReportFileLocOnServer());

        CompositeResponse composite = MessageUtil.postRequest(rEvent);

        QuestionAnswerResponseEvent questionAnsResponseEvent = (QuestionAnswerResponseEvent) MessageUtil.filterComposite(
                composite, QuestionAnswerResponseEvent.class);
        
        if (questionAnsResponseEvent != null)
        {
            myClosingForm.setReportFileLocOnServer(questionAnsResponseEvent.getName());
            Collection questionGroupResponseEvents = questionAnsResponseEvent.getQuestionGroupResponseEvents();
            Collection uiQuestionGroups = UIUtil.mapQuestion_GroupRespEvtsToUIQuestionGroup(questionGroupResponseEvents);
            myResidenitalForm.setExitPlanQuestions(uiQuestionGroups);
        }
//		ER JIMS200055760 & JIMS200055760 start here
		GetCaseplanDetailsEvent det = (GetCaseplanDetailsEvent) EventFactory
        .getInstance(JuvenileCasePlanControllerServiceNames.GETCASEPLANDETAILS);
		det.setSupervisionNumber(myClosingForm.getSupervisionNumber());
		det.setForClmReview(false);
		compositeResponse = MessageUtil.postRequest(det);
		PlacementInfoResponseEvent cpEvt = (PlacementInfoResponseEvent) MessageUtil.filterComposite(
				compositeResponse, PlacementInfoResponseEvent.class);
		
		if(cpEvt != null){
			myResidenitalForm.setFacilityId(cpEvt.getFacilityId());
		}
//		ER JIMS200055760 & JIMS200055760 end here
		
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        ResidentialExitPlanForm myResidenitalForm = (ResidentialExitPlanForm) aForm;
        myResidenitalForm.setFacility(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, myResidenitalForm.getFacilityId()));
        Collection errors = UIUtil.checkQuestionResponses(myResidenitalForm.getExitPlanQuestions());
        if (errors != null && errors.size() > 0)
        {
            sendToErrorPage(aRequest, errors);
            return aMapping.findForward(UIConstants.FAILURE);
        }
        myResidenitalForm.setAction(UIConstants.UPDATE_SUMMARY);
        return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map buttonMap = new HashMap();
        buttonMap.put("button.cancel", "cancel");
        buttonMap.put("button.back", "back");
        buttonMap.put("button.link", "displayResidentialExitPlan");
        buttonMap.put("button.next", "next");
        return buttonMap;
    }

    /**
     * @param aRequest
     */
    private void sendToErrorPage(HttpServletRequest aRequest, Collection aActionErrors)
    {
        ActionErrors errors = new ActionErrors();
        if (aActionErrors != null && aActionErrors.size() > 0)
        {
            Iterator i = aActionErrors.iterator();
            while (i.hasNext())
            {
                ActionMessage error = (ActionMessage) i.next();
                errors.add(ActionErrors.GLOBAL_MESSAGE, error);
            }
            saveErrors(aRequest, errors);
        }
    }
}// End CLASS
