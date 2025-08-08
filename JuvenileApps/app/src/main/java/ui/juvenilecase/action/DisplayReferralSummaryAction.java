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

import ui.common.CodeHelper;
import ui.common.Name;
import ui.juvenilecase.UIRiskAnalysisHelper;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;
import ui.juvenilecase.form.riskanalysis.RiskAssessmentReferralForm;
import ui.security.SecurityUIHelper;
import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
import messaging.riskanalysis.RiskQuestionAnswerEvent;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.actions.LookupDispatchAction;

public class DisplayReferralSummaryAction extends LookupDispatchAction
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
     * @roseuid 433D8A04002A
     */
    public DisplayReferralSummaryAction()
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
//        RiskAssessmentReferralForm refForm = (RiskAssessmentReferralForm) aForm;
//        RiskAnalysisForm riskAnalysisForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
    	RiskAssessmentReferralForm refForm = (RiskAssessmentReferralForm) aRequest.getSession().getAttribute("riskReferralForm");
    	RiskAnalysisForm riskAnalysisForm = (RiskAnalysisForm) aForm;
    	
    	
    	String modReason = aRequest.getParameter("modReason");
    	if ( modReason != null ){
    	    	IUserInfo user = SecurityUIHelper.getUser();
    	    	Name userName = new Name( user.getFirstName(), UIConstants.EMPTY_STRING, user.getLastName() );
    	    	modReason = new StringBuilder( modReason ).append( " [" ).append( DateUtil.getCurrentDateString( UIConstants.DATETIME24_FMT_1 ) ).append( " - " ).append( userName.getFormattedName() ).append( "]" ).toString();
    	    	riskAnalysisForm.setModReason( modReason );
        	riskAnalysisForm.setFilteredModReason( modReason.replaceAll("\\s*\\[.*?\\]\\s*", " ") );
    	}
        
        refForm.setMoreThanOneFailure(false);
        
        List qAns = riskAnalysisForm.getQuestionAnswers();
        if ( qAns != null 
        	&& qAns.size() > 0 ) {
            List<RiskQuestionResponseEvent> riskResps = qAns;
            for (int i = 0; i < riskResps.size(); i++ ){
        	RiskQuestionResponseEvent riskResp = riskResps.get(i);
        	if ( riskResp.getUiControlType().equals(UIConstants.CHECKBOX)) {
        	    String [] answers = aRequest.getParameterValues("questionAnswers["+String.valueOf(i)+ "].selectedAnswerIDs");
        	    riskResp.setSelectedAnswerIDs(answers);
        	}
            }
        }
                
        // This list is for processing in DB - Separated by WeightReponseId       
            
        List questionRequestEvents = UIRiskAnalysisHelper.processIndividualQuestionAnswers(qAns);
        Collections.sort((ArrayList) questionRequestEvents);
        
        // This list if a view only for JSP - Consolidated by QuestionNumber
        List viewOnlyRequestEvents = new ArrayList();
        if(riskAnalysisForm.getAction()!= null && (riskAnalysisForm.getAction().equalsIgnoreCase("newPCSRisk") || riskAnalysisForm.getAction().equalsIgnoreCase("updatePCSRisk")) )
            viewOnlyRequestEvents = UIRiskAnalysisHelper.viewModifiedMultiQuestionAnswers(questionRequestEvents);
        else
            viewOnlyRequestEvents = UIRiskAnalysisHelper.viewOnlyMultiQuestionAnswers(questionRequestEvents);
        Collections.sort((ArrayList) viewOnlyRequestEvents);         
            
        riskAnalysisForm.setProcessedQuestionAnswers(questionRequestEvents); 
        riskAnalysisForm.setProcessedViewQuestionAnswers(viewOnlyRequestEvents);
   
        if ((refForm.getRiskMandatoryDetentionCd() != null) && (refForm.getRiskMandatoryDetentionCd().length() > 0)) {
        	String riskMandatoryDetentionDesc = CodeHelper.getCodeDescription(PDCodeTableConstants.RISK_MANDATORY_DETENTION, refForm.getRiskMandatoryDetentionCd());
        	refForm.setRiskMandatoryDetentionDesc(riskMandatoryDetentionDesc);
        }
        
        if(refForm.getIsNewReferral().equalsIgnoreCase("false"))
        {
            riskAnalysisForm.setIsPCSPrintable("true");
        }
        else
        {
            riskAnalysisForm.setIsPCSPrintable("false");
        }

        
        if (refForm.getIsNewReferral().equalsIgnoreCase("true")) {
			return aMapping.findForward("successNew");
		} else {
			return aMapping.findForward("successOld");
		}
        //return aMapping.findForward("success");
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
	RiskAnalysisForm riskAnalysisForm = (RiskAnalysisForm) aForm;
	riskAnalysisForm.setAction("");
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
	RiskAnalysisForm riskAnalysisForm = (RiskAnalysisForm) aForm;
	riskAnalysisForm.setAction("");
        ActionForward forward = aMapping.findForward(UIConstants.BACK);
        return forward;
    }
}
