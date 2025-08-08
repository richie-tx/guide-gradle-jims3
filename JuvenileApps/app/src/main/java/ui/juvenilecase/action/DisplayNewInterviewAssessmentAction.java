// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\action\\DisplayNewInterviewAssessmentAction.java

package ui.juvenilecase.action;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.juvenilecase.reply.InterviewPreConditionFailedResponseEvent;
import messaging.juvenilecase.reply.InterviewPrefillResponseEvent;
import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
import messaging.juvenilecase.reply.RiskWeightedResponseEvent;
import messaging.riskanalysis.CheckInterviewPreconditionsEvent;
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
public class DisplayNewInterviewAssessmentAction extends Action
{
	/**
	 * @roseuid 4357EECE020F
	 */
	public DisplayNewInterviewAssessmentAction()
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
		//RiskAssessmentInterviewForm refForm = (RiskAssessmentInterviewForm)aForm;
		RiskAnalysisForm riskForm = (RiskAnalysisForm) aForm;
		String casefileID = UIConstants.EMPTY_STRING ;
		String juvNum = UIConstants.EMPTY_STRING ;
        JuvenileCasefileForm casefileForm =
        	(JuvenileCasefileForm) aRequest.getSession().getAttribute("juvenileCasefileForm");
        // original casefileID may have changed if user has selected entry date hyperlink
        // modified 08/21/12 as part of changes for defect 74100
        riskForm.setCasefileID(casefileForm.getSupervisionNum()); 
        
		if( riskForm != null )
		{
			riskForm.setMode(UIConstants.EMPTY_STRING);
			riskForm.setModReason(UIConstants.EMPTY_STRING);
			casefileID = riskForm.getCasefileID();
			juvNum = riskForm.getJuvenileNum();
		}
		riskForm.setRiskAssessmentDate(new Date());
		
		riskForm.setCasefileID( casefileID );
		riskForm.setJuvenileNum( juvNum );
		riskForm.setRiskAssessmentType(RiskAnalysisConstants.RISK_TYPE_PREA_FOLLOW_UP);
      	riskForm.setRiskAssessmentTypeDesc(SimpleCodeTableHelper.getDescrByCode(RiskAnalysisConstants.JUV_RISK_ASSESSMENT_TYPE, RiskAnalysisConstants.RISK_TYPE_PREA_FOLLOW_UP));
      	 
		riskForm.setRiskAssessmentDate(new Date());

		CheckInterviewPreconditionsEvent event = (CheckInterviewPreconditionsEvent)
				EventFactory.getInstance( JuvenileRiskAnalysisControllerServiceNames.CHECKINTERVIEWPRECONDITIONS );

		event.setCaseFileId( casefileID );
		event.setJuvenileNumber( juvNum );

		CompositeResponse response = MessageUtil.postRequest( event );

		InterviewPreConditionFailedResponseEvent errorEvt = (InterviewPreConditionFailedResponseEvent)
				MessageUtil.filterComposite( response, InterviewPreConditionFailedResponseEvent.class );

		if( errorEvt != null )
		{
			// pre conditions not met
			ActionErrors errors = new ActionErrors();
			String message = errorEvt.getMessage();
			ActionMessage newErr = null;
			if( message.equals( "1" ) )
			{
				newErr = new ActionMessage( "error.priorAssessmentNotDone", "Detention or TJJD Risk", "PREA Follow-up" );
			}
			else if( message.equals( "5" ) )
			{
				newErr = new ActionMessage( "error.generic", "A Detention or TJJD risk assessment needs to be completed" );
			}
			else if( message.equalsIgnoreCase(RiskAnalysisConstants.SAME_HOUR_SAME_CASEFILE))
			{
				newErr = new ActionMessage( "error.assessmenttypecannotbedonewithinhour", 
						riskForm.getRiskAssessmentType(), riskForm.getCasefileID() );
			}
			else if (errorEvt.getMessage().equalsIgnoreCase(RiskAnalysisConstants.NO_ACTIVE_FORMULA)) {
				newErr = new ActionMessage("error.noActiveRiskFormula");
			}
			else
			{
				String cStatus = UIConstants.EMPTY_STRING ;
//				JuvenileCasefileForm casefileForm = (JuvenileCasefileForm)
//						aRequest.getSession().getAttribute( "juvenileCasefileForm" );
				if( casefileForm != null )
				{
					cStatus = casefileForm.getCaseStatus() ;
				}
				newErr = new ActionMessage( "error.assessmenttypecannotbedone", 
						riskForm.getRiskAssessmentType(), casefileForm.getSupervisionType(), cStatus );
			}
			
			errors.add( ActionErrors.GLOBAL_MESSAGE, newErr );
			saveErrors( aRequest, errors );
			return aMapping.findForward( "preconditionFailed" );
		}

		// pre-fill attributes for Interview assessment
		InterviewPrefillResponseEvent prefillEvent = (InterviewPrefillResponseEvent)
				MessageUtil.filterComposite( response, InterviewPrefillResponseEvent.class );
		
/*		if( prefillEvent != null )
		{
			if (new Integer(prefillEvent.getOnsetAge()) != null){
				refForm.setOnsetAge( Integer.toString( prefillEvent.getOnsetAge() ) );
			} else {
				refForm.setOnsetAge("0");
			}
			refForm.setSex( prefillEvent.getJuvSex() ); 
			refForm.setSexCd( prefillEvent.getJuvSexId() );
		}
*/	    
		List <RiskQuestionResponseEvent> qaList = UIRiskAnalysisHelper.groupQuestionsAndAnswers(response);
		RiskQuestionResponseEvent question = null;	        
	   
	    for (int i = 0; i < qaList.size(); i++) {
	    	question = qaList.get(i);
	    	if (i == 0){
	    		riskForm.setRiskFormulaId(question.getRiskFormulaId());
	    	}
	    	if (question.getControlCode() != null && question.getControlCode().length() > 0) {
	    		if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.INTERVIEW_CONTROL_CODE_SEX)) {
        			
        			String gender = "";
        			if (prefillEvent.getJuvSexId() != null && prefillEvent.getJuvSexId().equalsIgnoreCase("M")) {
        				gender = RiskAnalysisConstants.MALE;
        			} else if (prefillEvent.getJuvSexId() != null && prefillEvent.getJuvSexId().equalsIgnoreCase("F")) {
        				gender = RiskAnalysisConstants.FEMALE;
        			} else {
        				gender = "";
        			}
        			preSetOnSelectedAnswerId(gender, question);
        			question.setUseAnswerText(true);
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.INTERVIEW_CONTROL_CODE_LATEST_REFERRAL_SCORE)){
        			question.setSelectedAnswerID(new Integer(prefillEvent.getLatestReferralScore()).toString());
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.INTERVIEW_CONTROL_CODE_OSA)){
        			String ageAnswer;
        			if (prefillEvent.getOnsetAge() == 16) {
        				ageAnswer = RiskAnalysisConstants.AGE16;
        			} else if (prefillEvent.getOnsetAge() == 15) {
        				ageAnswer = RiskAnalysisConstants.AGE15;
        			} else if (prefillEvent.getOnsetAge() == 14) {
        				ageAnswer = RiskAnalysisConstants.AGE14;
        			} else if (prefillEvent.getOnsetAge() <= 13) {
        				ageAnswer = RiskAnalysisConstants.AGELESSTHEN13;
        			} else {
        				ageAnswer = "";
        			}
        			
        			preSetOnSelectedAnswerId(ageAnswer, question);
        			question.setUseAnswerText(true);
        			//hot fix: #32710 starts
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_PID_NUMBER)) {
        			question.setSelectedAnswerID(prefillEvent.getJuvNum());
        		}else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_JUVENILE_NAME)) {
        			question.setSelectedAnswerID(prefillEvent.getJuvenileName());
        		} //hot fix: #32710 ends
	    	}
		}

		riskForm.setQuestionAnswers( qaList );

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
