package ui.juvenilecase.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.ResidentialPreConditionFailedResponseEvent;
import messaging.juvenilecase.reply.ResidentialPrefillResponseEvent;
import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
import messaging.riskanalysis.CheckResidentialPreConditionsEvent;
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
import ui.juvenilecase.form.riskanalysis.RiskAssessmentResidentialForm;

public class DisplayNewResidentialAssessmentAction extends Action
{

    /**
     * @roseuid 4357DD4202B0
     */
    public DisplayNewResidentialAssessmentAction()
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
        RiskAssessmentResidentialForm residentialForm = (RiskAssessmentResidentialForm) aRequest.getSession().getAttribute("riskResidentialForm");
		if (residentialForm == null){
			residentialForm = new RiskAssessmentResidentialForm();
			aRequest.getSession().setAttribute("riskResidentialForm", residentialForm);
		}

       	riskForm.setMode(UIConstants.EMPTY_STRING);
       	riskForm.setModReason(UIConstants.EMPTY_STRING);
        riskForm.setRiskAssessmentType(RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL);
      	riskForm.setRiskAssessmentTypeDesc(SimpleCodeTableHelper.getDescrByCode(RiskAnalysisConstants.JUV_RISK_ASSESSMENT_TYPE, RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL));

        riskForm.setRiskAssessmentDate(new Date());
        
        JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) aRequest.getSession().getAttribute(
                "juvenileCasefileForm");
        // original casefileID may have changed if user has selected entry date hyperlink
        // modified 08/21/12 as part of changes for defect 74100
        riskForm.setCasefileID(casefileForm.getSupervisionNum());

        CheckResidentialPreConditionsEvent event = (CheckResidentialPreConditionsEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.CHECKRESIDENTIALPRECONDITIONS);

        event.setCaseFileId(riskForm.getCasefileID());
        event.setJuvenileNumber(riskForm.getJuvenileNum());

        CompositeResponse response = MessageUtil.postRequest(event);

        ResidentialPreConditionFailedResponseEvent errorEvt = (ResidentialPreConditionFailedResponseEvent) MessageUtil
                .filterComposite(response, ResidentialPreConditionFailedResponseEvent.class);
        
        //If Preconditions fail, forward elsewhere
        if (errorEvt != null) {
        	
        	// pre conditions not met
            ActionErrors errors = new ActionErrors();
        	if (errorEvt.getMessage() != null && errorEvt.getMessage().length() > 0) {
        		
        		if (errorEvt.getMessage().equalsIgnoreCase(RiskAnalysisConstants.SAME_HOUR_SAME_CASEFILE)) {
        			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.assessmenttypecannotbedonewithinhour", 
        					riskForm.getRiskAssessmentType(), casefileForm.getSupervisionNum()));
        		} else if (errorEvt.getMessage().equalsIgnoreCase(RiskAnalysisConstants.NO_ACTIVE_FORMULA)) {
    				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noActiveRiskFormula"));
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

        ResidentialPrefillResponseEvent prefillEvent = (ResidentialPrefillResponseEvent) MessageUtil.filterComposite(
                response, ResidentialPrefillResponseEvent.class);
        
        residentialForm.setJuvTraitsDetails(prefillEvent.getJuvTraitsDetails());
        residentialForm.setRootTraitList(prefillEvent.getRootTraitList());
        residentialForm.setChildTraitList(prefillEvent.getChildTraitList());

        // list of question answers
       List <RiskQuestionResponseEvent> qaGroupList = UIRiskAnalysisHelper.groupQuestionsAndAnswers(response);
        
        if (qaGroupList.size() > 0){
        	RiskQuestionResponseEvent qre = qaGroupList.get(0);
        	riskForm.setRiskFormulaId(qre.getRiskFormulaId());
        } else {
        	riskForm.setRiskFormulaId(UIConstants.EMPTY_STRING);
        }
        riskForm.setQuestionAnswers(qaGroupList);

        return aMapping.findForward("success");
    }
}
