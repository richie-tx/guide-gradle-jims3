// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\action\\DisplayNewInterviewAssessmentAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.CheckRiskAnalysisOneHourResponseEvent;
import messaging.juvenilecase.reply.CourtReferralPreConditionFailedResponseEvent;
import messaging.juvenilecase.reply.CourtReferralPrefillResponseEvent;
import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
import messaging.juvenilecase.reply.RiskWeightedResponseEvent;
import messaging.riskanalysis.CheckCourtReferralPreconditionsEvent;
import messaging.riskanalysis.CheckRiskAnalysisOneHourEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.RiskAnalysisConstants;
import naming.UIConstants;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIRiskAnalysisHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;
import ui.juvenilecase.form.riskanalysis.RiskAssessmentCourtReferralForm;

public class DisplayNewCourtReferralAssessmentAction extends Action
{
	/**
	 * @roseuid 4357EECE020F
	 */
	public DisplayNewCourtReferralAssessmentAction()
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
	public ActionForward execute( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("MM/dd/yyyy");
		
//		{ RiskAnalysisForm riskForm = (RiskAnalysisForm)aRequest.getSession().getAttribute( "riskAnalysisForm" );
		RiskAnalysisForm riskForm = (RiskAnalysisForm) aForm;
		RiskAssessmentCourtReferralForm refForm = (RiskAssessmentCourtReferralForm)aRequest.getSession().getAttribute( "riskCourtReferralForm" );
        JuvenileCasefileForm casefileForm =
        	(JuvenileCasefileForm) aRequest.getSession().getAttribute("juvenileCasefileForm");
        // original casefileID may have changed if user has selected entry date hyperlink
        // modified 08/22/12 as part of changes for defect 74100
        riskForm.setCasefileID(casefileForm.getSupervisionNum()); 
        
// 07/13/2012 defect 73885 -- add 1 hour assessment check		
    	String assessmentType = RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE;
    	JuvenileCasefileForm jhForm = (JuvenileCasefileForm)aRequest.getSession().getAttribute("juvenileCasefileForm");    	
    	if (jhForm.getSexId().equals("F")) {
    		assessmentType = RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE;
    	}
    	CheckRiskAnalysisOneHourEvent event1hr =
        	(CheckRiskAnalysisOneHourEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.CHECKRISKANALYSISONEHOUR);
        event1hr.setCasefileID(riskForm.getCasefileID());
        event1hr.setAssessmentType(assessmentType);
        CheckRiskAnalysisOneHourResponseEvent resp = (CheckRiskAnalysisOneHourResponseEvent) MessageUtil.postRequest(event1hr,CheckRiskAnalysisOneHourResponseEvent.class);

//If one hour check fails, forward back to Risk Analysis Assessment selection page
      if (resp != null && resp.isMessage() == true) {
        	ActionErrors errors = new ActionErrors();
        	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.assessmenttypecannotbedonewithinhour", 
        		assessmentType, riskForm.getCasefileID()));
            saveErrors(aRequest, errors); 
			return aMapping.findForward( "preconditionFailed" );
        }
// end 1 hour assessment check
		
		riskForm.setMode(UIConstants.EMPTY_STRING);
		riskForm.setModReason(UIConstants.EMPTY_STRING);
        riskForm.setRiskAssessmentDate(new Date());
//			}
//		}
		
//		refForm.setCasefileID( casefileID );
//		refForm.setJuvenileNum( juvNum );
		
		if (refForm == null){
			refForm = new RiskAssessmentCourtReferralForm();
			aRequest.getSession().setAttribute("riskCourtReferralForm", refForm);
		}
		
		refForm.setSuggestedCasePlanDomains(null);

		CheckCourtReferralPreconditionsEvent event = (CheckCourtReferralPreconditionsEvent)
				EventFactory.getInstance( JuvenileRiskAnalysisControllerServiceNames.CHECKCOURTREFERRALPRECONDITIONS );

		event.setCaseFileId( riskForm.getCasefileID() );
		event.setJuvenileNumber( riskForm.getJuvenileNum() );

		CompositeResponse response = MessageUtil.postRequest( event );

		CourtReferralPreConditionFailedResponseEvent errorEvt = (CourtReferralPreConditionFailedResponseEvent)
				MessageUtil.filterComposite( response, CourtReferralPreConditionFailedResponseEvent.class );

		if( errorEvt != null )
		{
			// pre conditions not met
			ActionErrors errors = new ActionErrors();
			//String message = errorEvt.getMessage();
			ActionMessage newErr = null;

/*			if( message.equals( "1" ) )
			{
				//newErr = new ActionMessage( "error.priorAssessmentNotDone", "Referral", "Interview" );
			}
			else if( message.equals( "5" ) )
			{
				//newErr = new ActionMessage( "error.generic", "A Detention or Court Referral risk assessment needs to be completed" );
			}
			else if( message.equalsIgnoreCase(RiskAnalysisConstants.SAME_HOUR_SAME_CASEFILE))
			{
				//newErr = new ActionMessage( "error.assessmenttypecannotbedonewithinhour", refForm
                //       .getAssessmentType(), refForm.getCasefileID() );
			}
			else
			{*/
				//String cStatus = UIConstants.EMPTY_STRING ;
				//JuvenileCasefileForm casefileForm = (JuvenileCasefileForm)
				//		aRequest.getSession().getAttribute( "juvenileCasefileForm" );
				//if( casefileForm != null )
				//{
				//	cStatus = casefileForm.getCaseStatus() ;
				//}
				//newErr = new ActionMessage( "error.assessmenttypecannotbedone", 
				//		refForm.getAssessmentType(), casefileForm.getSupervisionType(), cStatus );
			if (errorEvt.getMessage().equalsIgnoreCase(RiskAnalysisConstants.NO_ACTIVE_FORMULA)) {
				newErr = new ActionMessage("error.noActiveRiskFormula");
			}
			errors.add( ActionErrors.GLOBAL_MESSAGE, newErr );
			saveErrors( aRequest, errors );
			return aMapping.findForward( "preconditionFailed" );
		}

		// pre-fill attributes for Interview assessment
		CourtReferralPrefillResponseEvent prefillEvent = (CourtReferralPrefillResponseEvent)
				MessageUtil.filterComposite( response, CourtReferralPrefillResponseEvent.class );
		

		//Collection qaGroup = UIRiskAnalysisHelper.groupQuestionsAndAnswers( response);
		//	Iterator<RiskQuestionResponseEvent> qaGroupIter = qaGroup.iterator();

		List <RiskQuestionResponseEvent> qaList = UIRiskAnalysisHelper.groupQuestionsAndAnswers(response);
        if (qaList.size() > 0){
        	RiskQuestionResponseEvent qre = qaList.get(0);
        	riskForm.setRiskFormulaId(qre.getRiskFormulaId());
        } else {
        	riskForm.setRiskFormulaId(UIConstants.EMPTY_STRING);
        }
        
		ArrayList myList = new ArrayList();
//		qaGroupIter = null;
//		qaGroupIter = qaGroup.iterator();
//		while(qaGroupIter.hasNext())
//		{
        RiskQuestionResponseEvent question = null;
        for (int i = 0; i < qaList.size(); i++) 
        {
			question = qaList.get(i);
			
			/**
             * 
            **/
            if (question.getUiControlType().equalsIgnoreCase("QUESTIONHEADER")) {
            	preSetOnSelectedAnswerId("", question);
            }
			
            /** The application keeps track of certain questions via a control code placed in the question
             *  There are certain questions that must remain dynamic but also be preset. These questions
             *  can still be modified and removed. However if a question that must be preset with data 
             *  needs to be added. It must have a control code and be added to this list of if/else.
             * 
            **/
            if (question.getControlCode() != null && question.getControlCode().length() > 0) {
        		if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_AGE_REFERRAL)) {
        			
        			String ageAnswer;
/*        			if (prefillEvent.getAgeOfFirstReferral() == 21) {
        				ageAnswer = RiskAnalysisConstants.AGE21;
        			} else if (prefillEvent.getAgeOfFirstReferral() == 20) {
        				ageAnswer = RiskAnalysisConstants.AGE20;
        			} else if (prefillEvent.getAgeOfFirstReferral() == 19) {
        				ageAnswer = RiskAnalysisConstants.AGE19;
        			} else if (prefillEvent.getAgeOfFirstReferral() == 18) {
        				ageAnswer = RiskAnalysisConstants.AGE18;
        			} else if (prefillEvent.getAgeOfFirstReferral() == 17) {
        				ageAnswer = RiskAnalysisConstants.AGE17;
        			} else if (prefillEvent.getAgeOfFirstReferral() == 16) {
        				ageAnswer = RiskAnalysisConstants.AGE16;
        			} else if (prefillEvent.getAgeOfFirstReferral() == 15) {
        				ageAnswer = RiskAnalysisConstants.AGE15;
        			} else if (prefillEvent.getAgeOfFirstReferral() == 14) {
        				ageAnswer = RiskAnalysisConstants.AGE14;
        			} else if (prefillEvent.getAgeOfFirstReferral() == 13) {
        				ageAnswer = RiskAnalysisConstants.AGE13;
        			} else if (prefillEvent.getAgeOfFirstReferral() == 12) {
        				ageAnswer = RiskAnalysisConstants.AGE12;
        			} else if (prefillEvent.getAgeOfFirstReferral() == 11) {
        				ageAnswer = RiskAnalysisConstants.AGE11;
        			} else if (prefillEvent.getAgeOfFirstReferral() == 10) {
        				ageAnswer = RiskAnalysisConstants.AGE10;
        			} else {
        				ageAnswer = "";
        			} */
        			if (prefillEvent.getAgeOfFirstReferral() >= 10 
        					&& prefillEvent.getAgeOfFirstReferral() <= 21){
        				ageAnswer = String.valueOf(prefillEvent.getAgeOfFirstReferral());
        			} else {
        				ageAnswer = UIConstants.EMPTY_STRING;
        			}
        			
        			preSetOnSelectedAnswerId(ageAnswer, question);
        			question.setUseAnswerText(true);
        			
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_CODE_SEX)) {
        			
        			String gender = "";
        			if (prefillEvent.getJuvSexId() != null && prefillEvent.getJuvSexId().equalsIgnoreCase("M")) {
        				gender = RiskAnalysisConstants.LOWERCASEMALE;
        				riskForm.setRiskAssessmentType(RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE);
        			} else if (prefillEvent.getJuvSexId() != null && prefillEvent.getJuvSexId().equalsIgnoreCase("F")) {
        				gender = RiskAnalysisConstants.LOWERCASEFEMALE;
        				riskForm.setRiskAssessmentType(RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE);
        			} else {
        				gender = "";
        			}
        			
        			preSetOnSelectedAnswerId(gender, question);
        			question.setUseAnswerText(true);
        			
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_JUVENILE_NAME)) {
        			question.setSelectedAnswerID(prefillEvent.getJuvnileName());
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_BIRTH_DATE)) {
        			question.setSelectedAnswerID(DATE_FORMAT.format(prefillEvent.getDateOfBirth()));
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_PID_NUMBER)) {
        			question.setSelectedAnswerID(prefillEvent.getPidNumber());
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_HEADCOUNTY)) {
        			question.setSelectedAnswerID(prefillEvent.getHeadquarterCounty());
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_TOTAL_REFFERRALS)) {
        			//question.setSelectedAnswerID(String.valueOf(prefillEvent.getTotalReferralsToJuvenile()));
        			String refAnswer = UIConstants.EMPTY_STRING;
        			if (prefillEvent.getTotalReferralsToJuvenile() >= 0) {
        				refAnswer = String.valueOf(prefillEvent.getTotalReferralsToJuvenile());
        			} else {
        				refAnswer = "0";
        			}
        			preSetOnSelectedAnswerId(refAnswer, question);
        			question.setUseAnswerText(true);

        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_VIOLENT_FELONY)) {
        			
        			String violentFelonyAnswer = "";
        			if (prefillEvent.isVoilentFelony()) {
        				violentFelonyAnswer = RiskAnalysisConstants.YES;
        			} else  {
        				violentFelonyAnswer = RiskAnalysisConstants.NO;
        			} 
        			
        			preSetOnSelectedAnswerId(violentFelonyAnswer, question);
        			question.setUseAnswerText(true);
        			
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_DOA)) {
        			question.setSelectedAnswerID(DATE_FORMAT.format(prefillEvent.getDateOfReferral()));
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_REFERRALS_INFORMATION)) {
        			question.setSelectedAnswerID(prefillEvent.getReferralsInformation());
        		}
           }
            
			myList.add( question );
		}

		riskForm.setQuestionAnswers( myList );

		return aMapping.findForward( UIConstants.SUCCESS );
	}
	
	/**
	 * Sets the selectedAnwserId for information that has to be set before display on the screen
	 * i.e. Juvenile History and Prefill Information
	 * @param prefillEvent
	 * @param question
	 */
	private void preSetOnSelectedAnswerId(
			String stringToCompare,
			RiskQuestionResponseEvent question) {
		Iterator<RiskWeightedResponseEvent> iter = question.getAnswers().iterator();
		if( iter != null && iter.hasNext() ) {
			while(iter.hasNext()) {
				RiskWeightedResponseEvent riskWeightedResponseEvent = iter.next();
				
				if (stringToCompare.equalsIgnoreCase(riskWeightedResponseEvent.getAnswerText())) {
					question.setSelectedAnswerID(String.valueOf(riskWeightedResponseEvent.getWeightedResponseID()));
					break;
				}
				
			}
		}
	}
}
