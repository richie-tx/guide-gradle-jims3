package ui.juvenilecase.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
import messaging.juvenilecase.reply.TestingPreConditionFailedResponseEvent;
import messaging.riskanalysis.CheckTestingPreConditionsEvent;
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

public class DisplayNewTestingAssessmentAction extends Action
{

    /**
     * @roseuid 4357EECE020F
     */
    public DisplayNewTestingAssessmentAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 4357ECF0026D
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	RiskAnalysisForm riskForm = (RiskAnalysisForm) aForm; 
    	
       	riskForm.setMode(UIConstants.EMPTY_STRING);
       	riskForm.setModReason(UIConstants.EMPTY_STRING);
       	riskForm.setRiskAssessmentType(RiskAnalysisConstants.RISK_TYPE_TESTING);
      	riskForm.setRiskAssessmentTypeDesc(SimpleCodeTableHelper.getDescrByCode(RiskAnalysisConstants.JUV_RISK_ASSESSMENT_TYPE, RiskAnalysisConstants.RISK_TYPE_TESTING));

       	riskForm.setRiskAssessmentDate(new Date());
        JuvenileCasefileForm casefileForm = 
        	(JuvenileCasefileForm) aRequest.getSession().getAttribute("juvenileCasefileForm");
        // original casefileID may have changed if user has selected entry date hyperlink
        // modified 08/21/12 as part of changes for defect 74100
        riskForm.setCasefileID(casefileForm.getSupervisionNum());
        
        CheckTestingPreConditionsEvent event = (CheckTestingPreConditionsEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.CHECKTESTINGPRECONDITIONS);

        event.setCaseFileId(riskForm.getCasefileID());
        event.setJuvenileNumber(riskForm.getJuvenileNum());

        CompositeResponse response = MessageUtil.postRequest(event);

        TestingPreConditionFailedResponseEvent errorEvt = (TestingPreConditionFailedResponseEvent) MessageUtil
                .filterComposite(response, TestingPreConditionFailedResponseEvent.class);

        if (errorEvt != null)
        {
            // pre conditions not met
            ActionErrors errors = new ActionErrors();
            ActionMessage newErr = null;
            if (errorEvt.getMessage().equals("1"))
            { // no interview assesment
                newErr = new ActionMessage("error.priorAssessmentNotDone", "Interview", "Testing");
            }
            else if (errorEvt.getMessage().equals("2"))
            { // no referral assessment
                newErr = new ActionMessage("error.priorAssessmentNotDone", "Referral", "Testing");
            }
            else if( errorEvt.getMessage().equalsIgnoreCase(RiskAnalysisConstants.SAME_HOUR_SAME_CASEFILE))
			{
				newErr = new ActionMessage( "error.assessmenttypecannotbedonewithinhour", 
						riskForm.getRiskAssessmentType(), riskForm.getCasefileID() );
			}
            else if (errorEvt.getMessage().equalsIgnoreCase(RiskAnalysisConstants.NO_ACTIVE_FORMULA)) {
				newErr = new ActionMessage("error.noActiveRiskFormula");
			}
            else
            { // 
                newErr = new ActionMessage("error.assessmenttypecannotbedone", 
                		riskForm.getRiskAssessmentType(), casefileForm
                        .getSupervisionType(), casefileForm.getCaseStatus());
            }
            errors.add(ActionErrors.GLOBAL_MESSAGE, newErr);
            saveErrors(aRequest, errors);
            return aMapping.findForward("preconditionFailed");
        }

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
