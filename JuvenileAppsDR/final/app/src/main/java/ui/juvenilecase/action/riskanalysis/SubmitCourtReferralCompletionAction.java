package ui.juvenilecase.action.riskanalysis;

import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.riskanalysis.SaveCourtReferralAssessmentEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;
import ui.juvenilecase.form.riskanalysis.RiskAssessmentCourtReferralForm;

public class SubmitCourtReferralCompletionAction extends LookupDispatchAction
{
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.finish", "finish");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        return keyMap;
    }

    /**
     * @roseuid 4357DD670231
     */
    public SubmitCourtReferralCompletionAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 4357D9B000AD
     */
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	
    	RiskAssessmentCourtReferralForm refForm = (RiskAssessmentCourtReferralForm) aForm;
    	RiskAnalysisForm riskForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");

    	SaveCourtReferralAssessmentEvent saveEvt = (SaveCourtReferralAssessmentEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVECOURTREFERRALASSESSMENT);
        
    	saveEvt.setAssessmentDate(riskForm.getRiskAssessmentDate());
    	saveEvt.setAssessmentID(riskForm.getAssessmentId());
    	saveEvt.setCompletionUpdate(true);
    	saveEvt.setAssessmentType(riskForm.getRiskAssessmentType());
    	saveEvt.setCasefileID(riskForm.getCasefileID());
        saveEvt.setJuvenileNum(riskForm.getJuvenileNum());
        saveEvt.setJpoUserID(UIUtil.getCurrentUserID());
        saveEvt.setRiskAnalysisId(riskForm.getAssessmentId());        
        
        //Completion Data
        saveEvt.setCollateralVisits(refForm.getCollateralVisits());
        saveEvt.setCourtDispositionTJPC(refForm.getCourtDispositionTJPC());
        saveEvt.setFace2FaceVisits(refForm.getFace2FaceVisits());
        saveEvt.setJjsCourtDecision(refForm.getJjsCourtDecision());
        saveEvt.setJjsCourtDisposition(refForm.getJjsCourtDisposition());

        MessageUtil.postRequest(saveEvt);

        return aMapping.findForward("success");
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.BACK);
        return forward;
    }
}
