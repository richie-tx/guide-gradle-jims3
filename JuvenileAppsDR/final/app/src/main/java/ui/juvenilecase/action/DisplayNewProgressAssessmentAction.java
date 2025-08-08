//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\action\\DisplayNewProgressAssessmentAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.ProgressPreConditionFailedResponseEvent;
import messaging.juvenilecase.reply.ProgressPrefillResponseEvent;
import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
import messaging.riskanalysis.CheckProgressPreConditionsEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.CollectionUtil;
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

public class DisplayNewProgressAssessmentAction extends Action
{
	/**
	 * @roseuid 4357DD4202B0
	 */
	public DisplayNewProgressAssessmentAction()
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
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		RiskAnalysisForm riskForm = (RiskAnalysisForm) aForm;
		//RiskAssessmentProgressForm refForm = (RiskAssessmentProgressForm) aRequest.getSession().getAttribute("riskProgressForm");
		riskForm.setMode(UIConstants.EMPTY_STRING);
		riskForm.setModReason(UIConstants.EMPTY_STRING);
		riskForm.setRiskAssessmentDate(new Date());
		
      	riskForm.setRiskAssessmentTypeDesc(SimpleCodeTableHelper.getDescrByCode(RiskAnalysisConstants.JUV_RISK_ASSESSMENT_TYPE, RiskAnalysisConstants.RISK_TYPE_PROGRESS));

		JuvenileCasefileForm casefileForm = 
			(JuvenileCasefileForm) aRequest.getSession().getAttribute("juvenileCasefileForm");
        // original casefileID may have changed if user has selected entry date hyperlink
        // modified 08/21/12 as part of changes for defect 74100
        riskForm.setCasefileID(casefileForm.getSupervisionNum());
        
//		refForm.setSupervisionLevel("");

		CheckProgressPreConditionsEvent event = (CheckProgressPreConditionsEvent)
				EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.CHECKPROGRESSPRECONDITIONS);

		event.setCaseFileId(riskForm.getCasefileID());
		event.setJuvenileNumber(riskForm.getJuvenileNum());
		event.setAssessmentDate(new Date());
		if(riskForm.getRiskAssessmentType()!=null && riskForm.getRiskAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_RES_PROGRESS))
			event.setFormula(RiskAnalysisConstants.RISK_TYPE_RES_PROGRESS);
		CompositeResponse response = MessageUtil.postRequest(event);

		ProgressPreConditionFailedResponseEvent errorEvt = (ProgressPreConditionFailedResponseEvent)
				MessageUtil.filterComposite(response, ProgressPreConditionFailedResponseEvent.class);
		if( errorEvt != null )
		{	// pre conditions not met
			ActionErrors errors = new ActionErrors();
			ActionMessage newErr = null;
			
			if( errorEvt.getMessage().equals("1") )
			{ // no Community assesment
				newErr = new ActionMessage("error.priorAssessmentNotDone", "Community", "Progress");
			}
			else if( errorEvt.getMessage().equals("2") )
			{ // no Residential assessment
				if(riskForm.getRiskAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_RES_PROGRESS))
					newErr = new ActionMessage("error.priorAssessmentNotDone", "Residential", "Residential Progress");
				else
					newErr = new ActionMessage("error.priorAssessmentNotDone", "Residential", "Progress");
			}
			else if( errorEvt.getMessage().equals("4") )
			{ // no Residential assessment
				newErr = new ActionMessage("error.priorAssessmentNotDone", "Either Community or Residential", "Progress");
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
			{


				String superVType = "",  caseStatus = "" ;
				if( casefileForm != null )
				{
					superVType = casefileForm.getSupervisionType() ;
					caseStatus = casefileForm.getCaseStatus() ;
				}

				newErr = new ActionMessage("error.assessmenttypecannotbedone", 
						riskForm.getRiskAssessmentType(), superVType, caseStatus);
			}
			errors.add(ActionErrors.GLOBAL_MESSAGE, newErr);
			saveErrors(aRequest, errors);
			return aMapping.findForward("preconditionFailed");
		}
		
		ProgressPrefillResponseEvent preEvt = (ProgressPrefillResponseEvent)
				MessageUtil.filterComposite(response, ProgressPrefillResponseEvent.class);


//		if (preEvt != null){
//			refForm.setSupervisionMonth(preEvt.getSupervisionMonths());
//		}
		/** Start - Get Dynamic questions & answners **/
        List <RiskQuestionResponseEvent> qaGroupList = UIRiskAnalysisHelper.groupQuestionsAndAnswers(response);
        
        if (qaGroupList.size() > 0){
        	RiskQuestionResponseEvent qre = qaGroupList.get(0);
        	riskForm.setRiskFormulaId(qre.getRiskFormulaId());
        } else {
        	riskForm.setRiskFormulaId(UIConstants.EMPTY_STRING);
        }
/** End - Get Dynamic questions & answners **/
        
 /** Start - Set Dynamic List of Question & Answers **/
        List myList = new ArrayList(); //Master List of Questions & Answers
        RiskQuestionResponseEvent questionResponse = null;
        List <RiskQuestionResponseEvent> qaList = CollectionUtil.iteratorToList(qaGroupList.iterator());

        for (int i = 0; i < qaList.size(); i++) 
        {
        	questionResponse = qaList.get(i);
             
            /** The application keeps track of certain questions via a control code placed in the question
             *  There are certain questions that must remain dynamic but also be preset. These questions
             *  can still be modified and removed. However if a question that must be preset with data 
             *  needs to be added. It must have a control code and be added to this list of if/else.
             * 
            **/
            if (questionResponse.getControlCode() != null && questionResponse.getControlCode().length() > 0) {
        		if (questionResponse.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.PROGRESS_SUPERVISION_MONTHS)) {
        			String months = String.valueOf(preEvt.getSupervisionMonths());
        			questionResponse.setSelectedAnswerID(months);
        			//question.setUseAnswerText(true);
        		} 
            }
            
            myList.add(questionResponse);
        }
/** End - Set Dynamic List of Progress Information Question & Answers **/

/** Start - Set master list questions & answers (Static & Dynamic) in refForm **/
        riskForm.setQuestionAnswers(myList);
		
		return aMapping.findForward("success");

	}
}
