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

public class DisplayProgressSummaryAction extends LookupDispatchAction
{
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.next", "next");
        keyMap.put("button.back", "back");
        keyMap.put("button.cancel", "cancel");
        return keyMap;
    }

    /**
     * @roseuid 433D8A04002A
     */
    public DisplayProgressSummaryAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 433C3D3D02B3
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	
    	RiskAnalysisForm riskAnalysisForm = (RiskAnalysisForm) aForm;
    	
        String modReason = aRequest.getParameter("modReason");
        riskAnalysisForm.setModReason(modReason);

        // This list is for processing in DB - Separated by WeightReponseId
        List qAns = riskAnalysisForm.getQuestionAnswers();
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
    public void reset(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest){
    	
    }
}
