package ui.juvenilecase.caseplan.action;

import java.util.ArrayList ;
import java.util.Collections ;
import java.util.Date ;
import java.util.HashMap ;
import java.util.Iterator ;
import java.util.List ;
import java.util.Map ;
import org.apache.commons.lang.StringUtils ;

import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;
import javax.servlet.http.HttpSession ;

import messaging.calendar.CalendarContextEvent;
import messaging.calendar.GetCalendarServiceEventsEvent ;
import messaging.calendar.GetProgramReferralServiceEventsEvent ;
import messaging.calendar.ServiceEventAttribute ;
import messaging.calendar.reply.CalendarServiceEventResponseEvent ;
import messaging.casefile.reply.ActivityResponseEvent ;
import messaging.caseplan.GetGenerateCaseplanDetailsEvent;
import messaging.caseplan.GetJPOReviewDataEvent ;
import messaging.caseplan.GetJPOReviewReportEvent ;
import messaging.caseplan.RequestForCaseplanReviewEvent ;
import messaging.caseplan.SaveCLMReviewEvent ;
import messaging.caseplan.SaveJPOReviewEvent ;
import messaging.caseplan.reply.CaseplanDocJuvDetailsResponseEvent ;
import messaging.caseplan.reply.JPOReviewReportResponseEvent ;
import messaging.caseplan.reply.JPOReviewRiskAnalysisResponseEvent ;
import messaging.caseplan.reply.SaveCaseplanDataResponseEvent;
import messaging.juvenilecase.reply.RuleDetailResponseEvent ;
import messaging.programreferral.reply.ProgramReferralResponseEvent ;
import mojo.km.dispatch.EventManager ;
import mojo.km.dispatch.IDispatch ;
import mojo.km.messaging.EventFactory ;
import mojo.km.messaging.Composite.CompositeResponse ;
import mojo.km.messaging.reporting.ReportResponseEvent ;
import mojo.km.utilities.MessageUtil ;
import naming.ActivityConstants ;
import naming.CasePlanConstants;
import naming.PDCodeTableConstants;
import naming.ServiceEventControllerServiceNames ;
import naming.UIConstants ;

import org.apache.struts.action.ActionForm ;
import org.apache.struts.action.ActionForward ;
import org.apache.struts.action.ActionMapping ;

import ui.action.JIMSBaseAction ;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException ;
import ui.juvenilecase.Rule ;
import ui.juvenilecase.UIJuvenileCasefileSupervisionRulesHelper ;
import ui.juvenilecase.UIJuvenileCaseworkHelper ;

import ui.juvenilecase.casefile.JournalHelper ;

import ui.juvenilecase.UIJuvenileHelper ;
import ui.juvenilecase.caseplan.form.CaseplanForm ;
import ui.juvenilecase.caseplan.form.JPOReviewPrintBean ;
import ui.juvenilecase.form.JuvenileCasefileForm ;
import ui.juvenilecase.helper.JuvenileCaseworkAlertsHelper ;
import ui.juvenilecase.programreferral.UIProgramReferralBean ;
import ui.security.SecurityUIHelper ;

/**
 * 
 * @author awidjaja
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitCaseplanCommentsAction extends JIMSBaseAction
{
	private final String STATUS_STR = "status" ;
	private final String CONFIRM_STR = "confirm" ;
	private final String ERROR_GENERIC_STR = "error.generic" ;
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward finish( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm form = (CaseplanForm)aForm ;
		
		if( form.getAction().equalsIgnoreCase( "requestReview" ) )
		{
			return requestForCLMReview( form, aRequest, aMapping ) ;
		}
		else if( form.getAction().equalsIgnoreCase( "review" ) )
		{
			return jpoReview( form, aRequest, aMapping, aResponse ) ;
		}
		else if( form.getAction().equalsIgnoreCase( CasePlanConstants.CLM_REVIEW_IN_PROGRESS ) )
		{
			return clmReview( form, aRequest, aMapping ) ;
		}

		// Adding record in activity table
		UIJuvenileHelper.createActivity( form.getCasefileId(), ActivityConstants.CASE_PLAN_MODIFIED, form.getComments() ) ;
		aRequest.setAttribute( STATUS_STR, CONFIRM_STR ) ;

		return aMapping.findForward( UIConstants.SUCCESS ) ;
	}

	/*
	 * @param form
	 * @param aRequest
	 * @param aMapping
	 * @return
	 */
	public ActionForward requestForCLMReview( CaseplanForm form, 
			HttpServletRequest aRequest, ActionMapping aMapping )
	{
		RequestForCaseplanReviewEvent evt = new RequestForCaseplanReviewEvent() ;
		
		evt.setCasefileID( form.getCasefileId() ) ;
		evt.setCaseplanID( form.getCurrentCaseplan().getCaseplanId() ) ;
		evt.setComments( form.getComments() ) ;
		evt.setJuvenileNum( form.getJuvenileNum() ) ;

		// get clm logon Id from session
		HttpSession session = aRequest.getSession() ;
		JuvenileCasefileForm casefileForm = (JuvenileCasefileForm)session.getAttribute( UIConstants.CASEFILE_HEADER_FORM ) ;
		evt.setClmLogonID( casefileForm.getCaseloadManagerId() ) ;

		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( evt ) ;
		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply() ;
		MessageUtil.processReturnException( compositeResponse ) ;

		// Send Task to CLM for Review for all supervisionTypes.
		String subject = new StringBuffer( "Caseplan needs CLM review for Supervision # " )
				.append( form.getCasefileId() ).append( ", Juvenile # " )
				.append( form.getJuvenileNum() ).toString() ;

		JuvenileCaseworkAlertsHelper helper = new JuvenileCaseworkAlertsHelper() ;
		helper.scheduleCasePlanReviewTask( casefileForm.getCaseloadManagerId(), 
				"MJCW.CLM.SUPERVISION.REVIEW.NOTIFICATION", subject, form, 120, 
				mojo.naming.CalendarConstants.ONCE_SCHEDULE_CLASS, "View", "clm" ) ;
		// End

		aRequest.setAttribute( STATUS_STR, CONFIRM_STR ) ;
		
		return aMapping.findForward( UIConstants.SUCCESS ) ;
	}

	/*
	 * @param form
	 * @param aRequest
	 * @param aMapping
	 * @param aResponse
	 * @return
	 */
	public ActionForward jpoReview( CaseplanForm form, 
			HttpServletRequest aRequest, ActionMapping aMapping, HttpServletResponse aResponse )
	{
		JPOReviewPrintBean printBean = new JPOReviewPrintBean() ;
		CompositeResponse compositeResponse = new CompositeResponse()  ;

		{ GetJPOReviewDataEvent reqEvent = new GetJPOReviewDataEvent() ;
			
			reqEvent.setCasefileId( form.getCasefileId() ) ;
			reqEvent.setJuvenileNum( form.getJuvenileNum() ) ;
			
			JuvenileCasefileForm casefileForm = UIJuvenileCaseworkHelper.getHeaderForm( aRequest ) ;
			printBean.setCasefileId( casefileForm.getSupervisionNum() ) ;
			printBean.setExpectedSupervisionEndDate( casefileForm.getExpectedSupervisionEndDate() ) ;
			CompositeResponse cr = postRequestEvent( reqEvent ) ;
			((ArrayList)compositeResponse.getResponses()).ensureCapacity( cr.getResponses().size() ) ;
			compositeResponse.getResponses().addAll( cr.getResponses() )  ;

			// Set juvDetails
			CaseplanDocJuvDetailsResponseEvent juvDetails = (CaseplanDocJuvDetailsResponseEvent)
					MessageUtil.filterComposite( compositeResponse, CaseplanDocJuvDetailsResponseEvent.class ) ;

			printBean.setJuvDetails( juvDetails ) ;
		}


		// Set Activities
		{ ArrayList activities = (ArrayList)MessageUtil.compositeToCollection( compositeResponse, ActivityResponseEvent.class ) ;
			Collections.sort( activities ) ;
			for( Iterator<ActivityResponseEvent> activitiesIter = activities.iterator(); 
					activitiesIter.hasNext(); /*empty*/ )
			{
				ActivityResponseEvent activity = activitiesIter.next() ;
				if( activity.getCodeId().equals( ActivityConstants.MODIFY_SUPERVISION_RULES ) )
				{
					printBean.getMsrActivities().add( activity ) ;
				}
				else if( activity.getCodeId().equals( ActivityConstants.CASE_PLAN_REVIEWED ) )
				{
					printBean.getRevActivities().add( activity ) ;
				}
			}

			ArrayList progressRiskAnalysis = (ArrayList)
					MessageUtil.compositeToCollection( compositeResponse, JPOReviewRiskAnalysisResponseEvent.class ) ;
			if( progressRiskAnalysis != null )
			{
				Collections.sort( progressRiskAnalysis ) ;
			}
			printBean.setRiskAnalysisProgressAssessments( progressRiskAnalysis ) ;
		}

		// GoalDetailsResponseEvent
		printBean.setGoals( (ArrayList)form.getJpoReviewGoalList() ) ;

		HashMap ruleMap = new HashMap() ;

		{ ArrayList rules = (ArrayList)MessageUtil.compositeToCollection( 
					compositeResponse, RuleDetailResponseEvent.class ) ;
			for( Iterator<RuleDetailResponseEvent> ruleIter = rules.iterator(); 
					ruleIter.hasNext(); /*empty*/ )
			{
				RuleDetailResponseEvent rule = ruleIter.next() ;
	
				Rule uiRule = UIJuvenileCasefileSupervisionRulesHelper.getDetailRule( rule ) ;
				if( ! ruleMap.containsKey( uiRule.getRuleId() ) )
				{
					ruleMap.put( uiRule.getRuleId(), uiRule ) ;
				}
			}
		}

		printBean.getRules().addAll( ruleMap.values() ) ;

		List programReferralsRE = (List)MessageUtil.compositeToCollection( 
				compositeResponse, ProgramReferralResponseEvent.class ) ;
		List uiProgReferrals = new ArrayList() ;
		for( Iterator<ProgramReferralResponseEvent> progReferralIter = programReferralsRE.iterator(); 
				progReferralIter.hasNext(); /*empty*/ )
		{
			ProgramReferralResponseEvent eachProgReferral = progReferralIter.next() ;
			
			UIProgramReferralBean uiProgReferral = new UIProgramReferralBean( eachProgReferral ) ;
			uiProgReferrals.add( uiProgReferral ) ;

			GetProgramReferralServiceEventsEvent gprs = (GetProgramReferralServiceEventsEvent)
					EventFactory.getInstance( ServiceEventControllerServiceNames.GETPROGRAMREFERRALSERVICEEVENTS ) ;

			gprs.setJuvenileNum( uiProgReferral.getJuvenileId() ) ;
			gprs.setProgramId( uiProgReferral.getProviderProgramId() ) ;
			gprs.setProgramReferralId( uiProgReferral.getReferralId() ) ;

			CompositeResponse resp = (CompositeResponse)MessageUtil.postRequest( gprs ) ;

			List calendarEvents = (List)MessageUtil.compositeToCollection( resp, 
					CalendarServiceEventResponseEvent.class ) ;

			if( calendarEvents != null  &&  calendarEvents.size() > 1 )
			{
				Collections.sort( (List)calendarEvents ) ;
			}

			List detailedCalendarEvents = new ArrayList() ;
			// get service events
			for( Iterator<CalendarServiceEventResponseEvent> calendarEventIter = calendarEvents.iterator(); 
					calendarEventIter.hasNext(); /*empty*/)
			{
				String eventId = calendarEventIter.next().getEventId() ;
				if( StringUtils.isNotEmpty( eventId ) )
				{
					ArrayList attributes = new ArrayList() ;
					ServiceEventAttribute sa = new ServiceEventAttribute() ;
					sa.setServiceEventId( new Integer( eventId ) ) ;
					attributes.add( sa ) ;

					GetCalendarServiceEventsEvent gce = (GetCalendarServiceEventsEvent)
							EventFactory.getInstance( ServiceEventControllerServiceNames.GETCALENDARSERVICEEVENTS ) ;
					
					CalendarContextEvent calendarContextEvent = new CalendarContextEvent();
					gce.setCalendarContextEvent(calendarContextEvent);
					gce.setCalendarAttributes( attributes ) ;

					CompositeResponse cResp = (CompositeResponse)MessageUtil.postRequest( gce ) ;
					CalendarServiceEventResponseEvent cser = (CalendarServiceEventResponseEvent)
							MessageUtil.filterComposite( cResp, CalendarServiceEventResponseEvent.class ) ;
					if( cser != null )
					{
						detailedCalendarEvents.add( cser ) ;
					}
				}
			}//for
			uiProgReferral.setJuvenileEvents( detailedCalendarEvents ) ;

		}// outer for

		printBean.setProgramReferrals( uiProgReferrals ) ;

		{ String str = form.getComments() ;
			if( str != null )
			{
				printBean.setComments( str ) ;
			}
		}

		//added for user story 11146 for generate caseplan review
		printBean.setSupLevelApproStr(form.getSupLevelApproStr());
		printBean.setRecomSupervisionLevel(form.getRecomSupervisionLevel());
		printBean.setSupervisionLevel(form.getSupervisionLevel());
		printBean.setContactInformation(form.getContactInformation());
		printBean.setJpoMaintainContactStr(form.getJpoMaintainContactStr());
		printBean.setJpoMaintainExplain(form.getJpoMaintainExplain());
		//ended
		
		
		CompositeResponse compResp = sendPrintRequest( "REPORTING::JPO_CASEPLAN_REVIEW", printBean, null ) ;
		ReportResponseEvent aReportRespEvt = (ReportResponseEvent)
				MessageUtil.filterComposite( compResp, ReportResponseEvent.class ) ;

		if( aReportRespEvt == null ||
				aReportRespEvt.getContent() == null || aReportRespEvt.getContent().length < 1 )
		{
			sendToErrorPage( aRequest, ERROR_GENERIC_STR, "Problems generating report" ) ;
			return aMapping.findForward( UIConstants.FAILURE ) ;
		}
		
		SaveJPOReviewEvent saveEvt = new SaveJPOReviewEvent() ;
		saveEvt.setCaseplanId( form.getCurrentCaseplan().getCaseplanId() ) ;
		saveEvt.setReport( aReportRespEvt.getContent() ) ;
		saveEvt.setJpoMaintainContact(form.isJpoMaintainContact());
		saveEvt.setJpoMaintainExplain(form.getJpoMaintainExplain());
		saveEvt.setSupLevelAppro(form.isSupLevelAppro());
		saveEvt.setRecomSupLevelId(form.getRecomSupervisionLevelId());
		saveEvt.setReviewComments( form.getComments() ) ;
		saveEvt.setReviewDate( new Date() ) ;
		saveEvt.setReviewUserId( SecurityUIHelper.getLogonId() ) ;
		CompositeResponse response1 = postRequestEvent( saveEvt ) ;

		JPOReviewReportResponseEvent respEvt = (JPOReviewReportResponseEvent)
				MessageUtil.filterComposite( response1, JPOReviewReportResponseEvent.class ) ;
		if( respEvt == null )
		{
			sendToErrorPage( aRequest, ERROR_GENERIC_STR, "Problems saving report" ) ;
			return aMapping.findForward( UIConstants.FAILURE ) ;
		}
		
		form.setSelectedValue( respEvt.getCaseplanRevId() ) ;
		
		// Adding record in activity table
		UIJuvenileHelper.createActivity( form.getCasefileId(), 
				ActivityConstants.CASE_PLAN_REVIEWED, form.getComments() ) ;
		
		aRequest.setAttribute( STATUS_STR, "confirmJPO" ) ;

		return aMapping.findForward( UIConstants.SUCCESS ) ;
	}

	/*
	 * @param form
	 * @param aRequest
	 * @param aMapping
	 * @return
	 */
	public ActionForward clmReview( CaseplanForm form, 
			HttpServletRequest aRequest, ActionMapping aMapping )
	{
		SaveCLMReviewEvent saveEvt = new SaveCLMReviewEvent() ;
		saveEvt.setAccept( false ) ;
		saveEvt.setCaseplanID( form.getCurrentCaseplan().getCaseplanId() ) ;
		saveEvt.setActivityComments( form.getComments() ) ;

		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( saveEvt ) ;
		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply() ;
		MessageUtil.processReturnException( compositeResponse ) ;
		
		JuvenileCaseworkAlertsHelper helper = new JuvenileCaseworkAlertsHelper() ;
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm( aRequest, true ) ;
		String officerId = casefileForm.getProbationOfficerLogonId() ;
		
		if( StringUtils.isEmpty( form.getJuvenileNum() ))
		{
			form.setJuvenileNum( casefileForm.getJuvenileNum() ) ;
		}
		
		helper.clmRejectNotification( form, officerId, casefileForm.getCaseloadManagerName(), casefileForm.getJuvenileFullName() ) ;
		aRequest.setAttribute( STATUS_STR, "confirmCLM" ) ;
		
		return aMapping.findForward( UIConstants.SUCCESS ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward print( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm form = (CaseplanForm)aForm ;
		GetJPOReviewReportEvent evt = new GetJPOReviewReportEvent() ;
		evt.setReviewRepId( form.getSelectedValue() ) ;

		CompositeResponse response1 = postRequestEvent( evt ) ;
		JPOReviewReportResponseEvent respEvt = (JPOReviewReportResponseEvent)
				MessageUtil.filterComposite( response1, JPOReviewReportResponseEvent.class ) ;

		if( respEvt == null || respEvt.getReport() == null )
		{
			sendToErrorPage( aRequest, ERROR_GENERIC_STR, "Problems displaying report" ) ;
			return aMapping.findForward( UIConstants.FAILURE ) ;
		}

		try
		{
			byte [ ] contentRep = (byte [ ])respEvt.getReport() ;
			setPrintContentResp( aResponse, contentRep, "JPO_CASEPLAN_REVIEW", UIConstants.PRINT_AS_PDF_DOC ) ;
			// postRequestEvent(saveReview);
		}
		catch( GeneralFeedbackMessageException e )
		{
			sendToErrorPage( aRequest, ERROR_GENERIC_STR, "Problems displaying report" ) ;
			return aMapping.findForward( UIConstants.FAILURE ) ;
		}
		
		return null ;
	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#cancel(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ActionForward forward = aMapping.findForward( UIConstants.CANCEL ) ;
		return forward ;
	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.BACK ) ;
	}

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping( Map keyMap )
	{
		keyMap.put( "button.finish", "finish" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
		keyMap.put( "button.back", "back" ) ;
		keyMap.put( "button.print", "print" ) ;

	}
}