package ui.juvenilecase.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.CommunityPreConditionFailedResponseEvent;
import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
import messaging.riskanalysis.CheckCommunityPreConditionsEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.RiskAnalysisConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.UIRiskAnalysisHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;

public class DisplayNewCommunityAssessmentAction extends Action
{

    /**
     * @roseuid 4357DD4202B0
     */
    public DisplayNewCommunityAssessmentAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 4357D9AF0161
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	RiskAnalysisForm riskForm = (RiskAnalysisForm) aForm;
       	riskForm.setMode(UIConstants.EMPTY_STRING);
       	riskForm.setModReason(UIConstants.EMPTY_STRING);
       	riskForm.setRiskAssessmentType(RiskAnalysisConstants.RISK_TYPE_COMMUNITY);
       	riskForm.setRiskAssessmentTypeDesc(SimpleCodeTableHelper.getDescrByCode(RiskAnalysisConstants.JUV_RISK_ASSESSMENT_TYPE, RiskAnalysisConstants.RISK_TYPE_COMMUNITY));
       	riskForm.setRiskAssessmentDate(new Date());
        JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) aRequest.getSession().getAttribute(
        "juvenileCasefileForm");
        // original casefileID may have changed if user has selected entry date hyperlink
        // modified 08/21/12 per defect 74100
        riskForm.setCasefileID(casefileForm.getSupervisionNum());  

        CheckCommunityPreConditionsEvent event = (CheckCommunityPreConditionsEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.CHECKCOMMUNITYPRECONDITIONS);

        event.setCaseFileId(riskForm.getCasefileID());

        CompositeResponse response = MessageUtil.postRequest(event);

        CommunityPreConditionFailedResponseEvent errorEvt = (CommunityPreConditionFailedResponseEvent) MessageUtil
                .filterComposite(response, CommunityPreConditionFailedResponseEvent.class);
        
      //If Preconditions fail, forward elsewhere
        if (errorEvt != null) {
        	
        	// pre conditions not met
            ActionErrors errors = new ActionErrors();
        	if (errorEvt.getMessage() != null && errorEvt.getMessage().length() > 0) {
        		
        		if (errorEvt.getMessage().equalsIgnoreCase(RiskAnalysisConstants.SAME_HOUR_SAME_CASEFILE)) {
        			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.assessmenttypecannotbedonewithinhour", 
        					riskForm.getRiskAssessmentType(), casefileForm.getSupervisionNum()));
        		} else {
            		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.assessmenttypecannotbedone", 
            				riskForm.getRiskAssessmentType(), casefileForm.getSupervisionType(), casefileForm.getCaseStatus()));
            	}
        		
        	} else {
        		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.assessmenttypecannotbedone", 
        				riskForm.getRiskAssessmentType(), casefileForm.getSupervisionType(), casefileForm.getCaseStatus()));
        	}
        	
            saveErrors(aRequest, errors);
            return aMapping.findForward("preconditionFailed");
        }

        // list of question answers
        List <RiskQuestionResponseEvent> qaGroupList = UIRiskAnalysisHelper.groupQuestionsAndAnswers(response);
        riskForm.setQuestionAnswers(qaGroupList);
        
        if (qaGroupList.size() > 0){
        	RiskQuestionResponseEvent qre = qaGroupList.get(0);
        	riskForm.setRiskFormulaId(qre.getRiskFormulaId());
        } else {
        	riskForm.setRiskFormulaId(UIConstants.EMPTY_STRING);
        }

        return aMapping.findForward("success");

    }
}
