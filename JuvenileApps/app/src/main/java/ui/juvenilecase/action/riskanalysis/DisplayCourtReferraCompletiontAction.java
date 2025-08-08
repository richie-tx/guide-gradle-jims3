// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\action\\DisplayNewInterviewAssessmentAction.java

package ui.juvenilecase.action.riskanalysis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.CourtReferralAssessmentEvent;
import messaging.juvenilecase.reply.CourtReferralCompletionPreConditionFailedResponseEvent;
import messaging.riskanalysis.CheckCourtReferralCompletionPreconditionsEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;
import ui.juvenilecase.form.riskanalysis.RiskAssessmentCourtReferralForm;

public class DisplayCourtReferraCompletiontAction extends Action
{
	/**
	 * @roseuid 4357EECE020F
	 */
	public DisplayCourtReferraCompletiontAction()
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
		RiskAssessmentCourtReferralForm refForm = (RiskAssessmentCourtReferralForm)aForm;
		RiskAnalysisForm riskForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
		
		refForm.clearCompletionFields();
		
		String casefileID = UIConstants.EMPTY_STRING ;
		String juvNum = UIConstants.EMPTY_STRING ;
		
		if( riskForm != null )
		{
			casefileID = riskForm.getCasefileID();
			juvNum = riskForm.getJuvenileNum();
			refForm.setCasefileID( casefileID );
			refForm.setJuvenileNum( juvNum );
		}
		
		riskForm.setMode(UIConstants.EMPTY_STRING);

		//Code below is ready to go, just temporarily commented out
		
		CheckCourtReferralCompletionPreconditionsEvent event = (CheckCourtReferralCompletionPreconditionsEvent)
				EventFactory.getInstance( JuvenileRiskAnalysisControllerServiceNames.CHECKCOURTREFERRALCOMPLETIONPRECONDITIONS );
		event.setCaseFileId( casefileID );
		event.setJuvenileNumber( juvNum );
		event.setRiskAnalysisId(riskForm.getAssessmentId());

		CompositeResponse response = MessageUtil.postRequest( event );

		CourtReferralCompletionPreConditionFailedResponseEvent errorEvt = (CourtReferralCompletionPreConditionFailedResponseEvent)
			MessageUtil.filterComposite( response, CourtReferralCompletionPreConditionFailedResponseEvent.class );

		
		if( errorEvt != null )
		{
			// pre conditions not met
			ActionErrors errors = new ActionErrors();
			String message = errorEvt.getMessage();
			ActionMessage newErr = null;
			
			if( message.equals( "reportingReferralNoMatch" ) )
			{
				newErr = new ActionMessage( "error.reportingReferralNoMatch" );
			}
			else if( message.equals( "reportingReferralDate" ) )
			{
				newErr = new ActionMessage( "error.reportingReferralDate" );
			}
			else if( message.equals( "reportingReferralNotFinalDisposition" ) )
			{
				newErr = new ActionMessage( "error.reportingReferralNotFinalDisposition" );
			}
			else if( message.equals( "reportingReferralDateEmpty" ) )
			{
				newErr = new ActionMessage( "error.reportingReferralDateEmpty" );
			}
			
			
			errors.add( ActionErrors.GLOBAL_MESSAGE, newErr );
			saveErrors( aRequest, errors );
			return aMapping.findForward( "preconditionFailed" );
		}
		
		CourtReferralAssessmentEvent refAssessEvent = (CourtReferralAssessmentEvent)MessageUtil.filterComposite(response, CourtReferralAssessmentEvent.class);
		if (refAssessEvent != null) 
		{ // means the court referral has been completed
			
			refForm.setCollateralVisits(refAssessEvent.getCollateralVisits());
			refForm.setFace2FaceVisits(refAssessEvent.getFace2FaceVisits());
			refForm.setCourtDispositionTJPC(refAssessEvent.getCourtDispositionTJPC());
			refForm.setJjsCourtDecision(refAssessEvent.getJjsCourtDecision());
			refForm.setJjsCourtDisposition(refAssessEvent.getJjsCourtDisposition());
			aRequest.getSession().setAttribute("riskCourtReferralForm",refForm);
		}
		
		return aMapping.findForward( UIConstants.SUCCESS );
	}
	
}
