package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIRiskAnalysisHelper;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;
import naming.UIConstants;

import org.apache.struts.actions.LookupDispatchAction;

public class DisplayCourtReferralSummaryAction extends LookupDispatchAction
{

    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.next", "next");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        return keyMap;
    }

    /**
     * @roseuid 4357EEBC029F
     */
    public DisplayCourtReferralSummaryAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 4357ECF10229
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	//RiskAssessmentCourtReferralForm refForm = (RiskAssessmentCourtReferralForm) aForm;
        //RiskAnalysisForm riskAnalysisForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
        RiskAnalysisForm riskAnalysisForm = (RiskAnalysisForm) aForm;
    	
    	String modReason = aRequest.getParameter("modReason");
    	String riskCasefileId = aRequest.getParameter("casefileID");
        riskAnalysisForm.setModReason(modReason);
        if(riskCasefileId!=null){
        	riskAnalysisForm.setCasefileID(riskCasefileId);
        }

        List qAns = riskAnalysisForm.getQuestionAnswers();
        
        // This list is for processing in DB - Seperated by WeightReponseId
        List questionRequestEvents = UIRiskAnalysisHelper.processIndividualQuestionAnswers(qAns);
        Collections.sort((ArrayList) questionRequestEvents);
        
        // This list if a view only for JSP - Consolidated by QuestionNumber
        List viewOnlyRequestEvents = UIRiskAnalysisHelper.viewOnlyMultiQuestionAnswers(questionRequestEvents);
        Collections.sort((ArrayList) viewOnlyRequestEvents);
        
        riskAnalysisForm.setProcessedQuestionAnswers(questionRequestEvents); 
        riskAnalysisForm.setProcessedViewQuestionAnswers(viewOnlyRequestEvents); 
        
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
