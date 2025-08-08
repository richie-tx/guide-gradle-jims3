//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\action\\DisplayNewProgressAssessmentAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.CheckRiskAnalysisOneHourResponseEvent;
import messaging.juvenilecase.reply.GangRiskPreConditionFailedResponseEvent;
import messaging.juvenilecase.reply.GangRiskPrefillResponseEvent;
import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
import messaging.riskanalysis.CheckGangRiskPreConditionsEvent;
import messaging.riskanalysis.CheckRiskAnalysisOneHourEvent;
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

public class DisplayNewGangRiskAssessmentAction extends Action
{
	/**
	 * @roseuid 4357DD4202B0
	 */
	public DisplayNewGangRiskAssessmentAction()
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
		riskForm.setMode(UIConstants.EMPTY_STRING);
		riskForm.setModReason(UIConstants.EMPTY_STRING);
		riskForm.setRiskAssessmentDate(new Date());
		
		if(riskForm.getRiskAssessmentType()!=null && riskForm.getRiskAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_MH_SCREEN)){
			riskForm.setRiskAssessmentTypeDesc(SimpleCodeTableHelper.getDescrByCode(RiskAnalysisConstants.JUV_RISK_ASSESSMENT_TYPE, RiskAnalysisConstants.RISK_TYPE_MH_SCREEN));
		}else{
			riskForm.setRiskAssessmentTypeDesc(SimpleCodeTableHelper.getDescrByCode(RiskAnalysisConstants.JUV_RISK_ASSESSMENT_TYPE, RiskAnalysisConstants.RISK_TYPE_GANG));
		}

		JuvenileCasefileForm casefileForm = 
			(JuvenileCasefileForm) aRequest.getSession().getAttribute("juvenileCasefileForm");
        // original casefileID may have changed if user has selected entry date hyperlink
        // modified 08/21/12 as part of changes for defect 74100
        riskForm.setCasefileID(casefileForm.getSupervisionNum());
        
        
     	CheckRiskAnalysisOneHourEvent event1hr =
        	(CheckRiskAnalysisOneHourEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.CHECKRISKANALYSISONEHOUR);
        event1hr.setCasefileID(riskForm.getCasefileID());
        event1hr.setAssessmentType(riskForm.getRiskAssessmentType()); //for US 28932
        CheckRiskAnalysisOneHourResponseEvent resp = (CheckRiskAnalysisOneHourResponseEvent) MessageUtil.postRequest(event1hr,CheckRiskAnalysisOneHourResponseEvent.class);

        //If one hour check fails, forward back to Risk Analysis Assessment selection page
	      if (resp != null && resp.isMessage() == true) {
	        	ActionErrors errors = new ActionErrors();
	        	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.assessmenttypecannotbedonewithinhour", riskForm.getRiskAssessmentType(), riskForm.getCasefileID()));
	            saveErrors(aRequest, errors); 
				return aMapping.findForward( "preconditionFailed" );
	        }
	      // end 1 hour assessment check
        

        //Precondition check.
        CheckGangRiskPreConditionsEvent event = (CheckGangRiskPreConditionsEvent)
				EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.CHECKGANGRISKPRECONDITIONS);

		event.setCaseFileId(riskForm.getCasefileID());
		event.setJuvenileNumber(riskForm.getJuvenileNum());
		event.setAssessmentDate(new Date());
		if(riskForm.getRiskAssessmentType()!=null && riskForm.getRiskAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_MH_SCREEN)){
			event.setFormula(RiskAnalysisConstants.RISK_TYPE_MH_SCREEN);
		}
		CompositeResponse response = MessageUtil.postRequest(event);

		GangRiskPreConditionFailedResponseEvent errorEvt = (GangRiskPreConditionFailedResponseEvent)MessageUtil.filterComposite(response, GangRiskPreConditionFailedResponseEvent.class);
		if( errorEvt != null )
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage newErr = null;
			if (errorEvt.getMessage().equalsIgnoreCase(RiskAnalysisConstants.NO_ACTIVE_FORMULA)) 
				newErr = new ActionMessage("error.noActiveRiskFormula");
			errors.add(ActionErrors.GLOBAL_MESSAGE, newErr);
			saveErrors(aRequest, errors);
			return aMapping.findForward("preconditionFailed");
		}

		GangRiskPrefillResponseEvent preEvt = (GangRiskPrefillResponseEvent)MessageUtil.filterComposite(response, GangRiskPrefillResponseEvent.class);

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
        	if(riskForm.getRiskAssessmentType()!=null && riskForm.getRiskAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_GANG))
        	{
        		if (questionResponse.getControlCode() != null && questionResponse.getControlCode().length() > 0) {
	            	if (questionResponse.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_JUVENILE_NAME)) {
	            		questionResponse.setSelectedAnswerID(preEvt.getJuvenileName());
	        		} else if (questionResponse.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_BIRTH_DATE)) {
	        			questionResponse.setSelectedAnswerID(preEvt.getDob());
	        		}else if (questionResponse.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_JUVENILE_NAME)) {
	        			questionResponse.setSelectedAnswerID(preEvt.getJuvenileName());
	        		}else if (questionResponse.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_CODE_SEX)) {
	        			questionResponse.setSelectedAnswerID(preEvt.getGender());
	        		}else if (questionResponse.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_PID_NUMBER)) {
	        			questionResponse.setSelectedAnswerID(preEvt.getJuvNum());
	        		}else if (questionResponse.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.ETHNICITY)) {
	        			questionResponse.setSelectedAnswerID(preEvt.getEthnicity());
	        		}else if (questionResponse.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_DOA)) {
	        			questionResponse.setSelectedAnswerID(preEvt.getDateOfAssessment());
	        		}else if (questionResponse.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.SCHOOL)) {
	        			questionResponse.setSelectedAnswerID(preEvt.getSchool());
	        		}else if (questionResponse.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.GRADE_LEVEL)) {
	        			questionResponse.setSelectedAnswerID(preEvt.getGrade());
	        		}else if (questionResponse.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT)) {
	        			questionResponse.setSelectedAnswerID(preEvt.getCourt());
	        		}else if (questionResponse.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.USER_NAME)) {
	        			questionResponse.setSelectedAnswerID(preEvt.getAssessingJPO());
	        		}
        		}
        	}
            myList.add(questionResponse);
        }
        /** End - Set Dynamic List of Gang Information Question & Answers **/

        /** Start - Set master list questions & answers (Static & Dynamic) in refForm **/
        riskForm.setQuestionAnswers(myList);
		return aMapping.findForward("success");
	}
}
