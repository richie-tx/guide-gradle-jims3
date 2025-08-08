package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilecase.reply.TraitTypeResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIRiskAnalysisHelper;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;
import ui.juvenilecase.form.riskanalysis.RiskAssessmentResidentialForm;

public class DisplayResidentialSummaryAction extends LookupDispatchAction
{

    /**
     * @roseuid 4357DD4903BE
     */
    public DisplayResidentialSummaryAction()
    {

    }

    /**
     * @see LookupDispatchAction#getKeyMethodMap()
     * @return Map
     */
    protected Map getKeyMethodMap()
    {
        Map buttonMap = new HashMap();
        buttonMap.put("button.view", "viewJuvTraitDetails");
        buttonMap.put("button.next", "nextResidentialSummary");
        buttonMap.put("button.cancel", "cancel");
        buttonMap.put("button.back", "back");
        return buttonMap;
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 4357D9AF0392
     */
    public ActionForward nextResidentialSummary(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        //RiskAssessmentResidentialForm refForm = (RiskAssessmentResidentialForm) aForm;
        RiskAnalysisForm riskAnalysisForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
        String modReason = aRequest.getParameter("modReason");
        riskAnalysisForm.setModReason(modReason);

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

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 4357D9AF0392
     */
    public ActionForward viewJuvTraitDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        RiskAssessmentResidentialForm refForm = (RiskAssessmentResidentialForm) aForm;
        String traitTypeId = refForm.getTraitTypeId();
        List traitDetailsList = refForm.getJuvTraitsDetails();
        List childTraitList = refForm.getChildTraitList();

        List diplayJuvTraitsDetails = new ArrayList();

        for (int i = 0; i < childTraitList.size(); i++)
        {
            TraitTypeResponseEvent traitType = (TraitTypeResponseEvent) childTraitList.get(i);

            //if the parentid of the trait is the same as the trait choosen by the user then get
            // the details matching that id's
            if (traitType.getParentTraitId().equals(traitTypeId))
            {

                for (int j = 0; j < traitDetailsList.size(); j++)
                {
                    String childTraitID = traitType.getTraitTypeId();
                    JuvenileTraitResponseEvent juvTrait = (JuvenileTraitResponseEvent) traitDetailsList.get(j);
                    if (juvTrait.getTraitTypeId().equals(childTraitID))
                    {
                        diplayJuvTraitsDetails.add(juvTrait);
                    }
                }
            }

        }

        refForm.setDisplayJuvTraitsDetails(diplayJuvTraitsDetails);

        return aMapping.findForward("viewJuvTraitsDetails");
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward aForward = aMapping.findForward(UIConstants.CANCEL);
        return aForward;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward aForward = aMapping.findForward(UIConstants.BACK);
        return aForward;

    }
}
