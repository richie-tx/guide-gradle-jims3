package ui.juvenilecase.action.riskanalysis;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.ReferralAssessmentEvent;
import messaging.juvenilecase.reply.RiskAnswerResponseEvent;
import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
import messaging.riskanalysis.GetRiskAssessmentDetailsEvent;
import messaging.riskanalysis.GetRiskQuestionAnswersEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.RiskAnalysisConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIRiskAnalysisHelper;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;
import ui.juvenilecase.form.riskanalysis.RiskAssessmentReferralForm;

public class DisplayReferralAssessmentUpdateAction extends Action
{

    /**
     * @roseuid 433D89CB021D
     */
    public DisplayReferralAssessmentUpdateAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 433C3D3D01E1
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	//Get both RiskAnalysis and RiskReferral Forms
//        RiskAnalysisForm riskForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
//        RiskAssessmentReferralForm refForm = (RiskAssessmentReferralForm) aForm;
    	RiskAssessmentReferralForm refForm = (RiskAssessmentReferralForm) aRequest.getSession().getAttribute("riskReferralForm");
    	RiskAnalysisForm riskForm = (RiskAnalysisForm) aForm;
       
        //Get Casefile and Juvenile Number From Risk Form
//        String casefileID = riskForm.getCasefileID();
//        String juvenileNum = riskForm.getJuvenileNum();
        
        //Set Casefile and Juvenile Number on Referral Form
//        refForm.setCasefileID(casefileID);
//        refForm.setJuvenileNum(juvenileNum);
        
        String theMode = aRequest.getParameter("mode");
        riskForm.setMode(theMode);
        
        //Get Assessment ID and Type
		String assessmentId = aRequest.getParameter("assessmentId");
		String assessmentType = aRequest.getParameter(RiskAnalysisConstants.RISK_ASSESSMENT_TYPE);
		if( assessmentId == null ) {
			assessmentId = riskForm.getAssessmentId();
		}
		if( assessmentType == null ) {
			assessmentType = riskForm.getRiskAssessmentType();
		}
		if(assessmentType!=null && assessmentType.equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL))
		{
		    riskForm.setAction("updatePCSRisk");
		}
                
        //Get Questions and Answers
        GetRiskQuestionAnswersEvent riskQuestionAnswersEvent = (GetRiskQuestionAnswersEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETRISKQUESTIONANSWERS);
        riskQuestionAnswersEvent.setAssessmentType(assessmentType); // Either NEWREFERRAL or REFERRAL
        riskQuestionAnswersEvent.setFormulaId(riskForm.getRiskFormulaId());
        
        CompositeResponse questionsAndAnswers = 
        	MessageUtil.postRequest(riskQuestionAnswersEvent); //Builds Questions and Answers in HTML Elements

        
        //Get Assessment Details
		GetRiskAssessmentDetailsEvent event = (GetRiskAssessmentDetailsEvent)EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETRISKASSESSMENTDETAILS);
		event.setAssessmentID(assessmentId);
		event.setAssessmentType(assessmentType);
		
		CompositeResponse composite = MessageUtil.postRequest(event);
		
		//Get User Answers to questions
		List answersToQuestions = 
			MessageUtil.compositeToList(composite, RiskAnswerResponseEvent.class); //Stored user Answers to Questions
		RiskAnswerResponseEvent[] riskAnswerResponseEvents = 
    		(RiskAnswerResponseEvent[]) answersToQuestions.toArray(new RiskAnswerResponseEvent[answersToQuestions.size()]);
		
		//Get Referral Specific Details
		ReferralAssessmentEvent refAssessEvent = (ReferralAssessmentEvent)MessageUtil.filterComposite(composite, ReferralAssessmentEvent.class);

		// Groups Raw Questions & Answners for Display
        List questionAnswerGroup = UIRiskAnalysisHelper.groupQuestionsAndAnswers(questionsAndAnswers);
        RiskQuestionResponseEvent[] riskQuestionResponseEvents = 
    		(RiskQuestionResponseEvent[]) questionAnswerGroup.toArray(new RiskQuestionResponseEvent[questionAnswerGroup.size()]);        
        
        //Create Final List of Refferal Information Question & Answers with User Reponses
        List finalList = UIRiskAnalysisHelper.mergeUserReponsesWithQuestions(riskQuestionResponseEvents,
        		riskAnswerResponseEvents);

        //Set Final List of Refferal Information Question & Answers with User Reponses **/
        riskForm.setQuestionAnswers(finalList);	
		
		//Set Referral Specific Information
        if (refAssessEvent.isMoreThanOneFailure()) {
			refForm.setMoreThanOneFailureString(UIConstants.YES_FULL_TEXT);
		} else {
			refForm.setMoreThanOneFailureString(UIConstants.NO_FULL_TEXT);
		}
        refForm.setMoreThanOneFailure(refAssessEvent.isMoreThanOneFailure()); //Updated from RiskAnalysisReferral
        refForm.setRiskMandatoryDetentionCd(refAssessEvent.getRiskMandatoryDetentionCd()); //Updated from RiskAnalysisReferral
        
        //refForm.setModReason(refAssessEvent.getModReason()); //Updated from RiskAnalysisReferral
		//riskForm.setModReason(refAssessEvent.getModReason());
		
        //Set Static Juvenile Delinquency History Answers on form
        refForm.setCapitalFelonyTotal(refAssessEvent.getTotalCapitalFelony());
        refForm.setMisdClassABTotal(refAssessEvent.getTotalClassAB());
        refForm.setMisdClassCTotal(refAssessEvent.getTotalClassC());
        refForm.setFelony1Total(refAssessEvent.getTotalFelony1());
        refForm.setFelony2Total(refAssessEvent.getTotalFelony2());
        refForm.setFelony3Total(refAssessEvent.getTotalFelony3());
        refForm.setLevelTotal(refAssessEvent.getTotalLevel());
        refForm.setOffensesTotal(refAssessEvent.getTotalOffenses());
        refForm.setReferralHistoryTotal(refAssessEvent.getTotalReferralsHistory());
        refForm.setStatusCityOrdOffensesTotal(refAssessEvent.getTotalStatusCO());
        refForm.setStateJailFelonyTotal(refAssessEvent.getTotalStateJailFelony());
        refForm.setAgeFirstReferred(refAssessEvent.getAgeFirstReferred());
        refForm.setSeriousnessIndex(refAssessEvent.getSeriousnessIndex());
        
        //Set Other Static Answers on Form **/
        /*Currently refForm.getReferralAssessmentDate returns current Date*/
        /*refForm.setReferralAssessmentDate(DateUtil.dateToString(refAssessEvent.getEnteredDate(), "MM/DD/YYYY"));*/
        
        //refForm.setReferralAssessmentDate(refAssessEvent.getEnteredDate());
        //riskForm.setRiskAssessmentDate(refAssessEvent.getEnteredDate());
        
        refForm.setPendingCourt(refAssessEvent.getPendingCourt());
        refForm.setCurrentlyOnProbation(refAssessEvent.getCurrentlyOnProbation());
        refForm.setPendingCourtVOP(refAssessEvent.getPendingCourtVOP());
        refForm.setNumberOfCharges(refAssessEvent.getNumberOfCharges()); 
        refForm.setIsNewReferral(String.valueOf(Boolean.valueOf(refAssessEvent.isNewReferral())));
        
        if(refForm.getIsNewReferral().equalsIgnoreCase("false"))
        {
            riskForm.setIsPCSPrintable("true");
        }
        else
        {
            riskForm.setIsPCSPrintable("false");
        }
        if (refForm.getIsNewReferral().equalsIgnoreCase("true")) {
			return aMapping.findForward("successNew");
		} else {
			return aMapping.findForward("successOld");
		}
    }


}
