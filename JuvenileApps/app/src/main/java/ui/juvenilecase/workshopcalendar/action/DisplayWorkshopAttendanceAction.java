//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\workshopcalendar\\action\\DisplayServiceEventDetailsAction.java

package ui.juvenilecase.workshopcalendar.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.address.reply.AddressResponseEvent;
import messaging.administerlocation.GetJuvenileLocationEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.calendar.GetServiceEventAttendanceEvent;
import messaging.calendar.SaveJuvenileAttendanceEvent;
import messaging.calendar.SaveMonthlySummaryEvent;
import messaging.calendar.SaveProgressNotesEvent;
import messaging.calendar.reply.AttendeeNameResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileJISResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.mentalhealth.GetDSMIVRecordEvent;
import messaging.mentalhealth.GetMentalHealthTestingSessionEvent;
import messaging.mentalhealth.reply.DSMIVTestResponseEvent;
import messaging.mentalhealth.reply.TestingSessionResponseEvent;
import messaging.programreferral.GetProgramReferralsByServiceEventEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.JuvenileProgramReferralControllerServiceNames;
import naming.PDCalendarConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import pd.security.PDSecurityHelper;

import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.mentalhealth.form.TestingSessionForm;
import ui.juvenilecase.programreferral.ProgramReferralState;
import ui.juvenilecase.programreferral.ProgramReferralStateManager;
import ui.juvenilecase.schedulecalendarevent.UISupervisionCalendarHelper;
import ui.juvenilecase.workshopcalendar.form.ServiceEventDetailsForm;
import ui.security.SecurityUIHelper;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;

public class DisplayWorkshopAttendanceAction extends LookupDispatchAction
{
	static final String JUVENILE_ID_STR = "juvenileId" ;
	static final String ADD_TEST_RESULTS_STR = "addTestResults" ;
	static final String SINGLE_SPACE_STR = " " ;
	
	/**
	 * @roseuid 44805C380387
	 */
	public DisplayWorkshopAttendanceAction()
	{
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm ;
		detailsForm.setTentativeRefPrgmRef(false); //reset it to original
		detailsForm.setSecondaryAction( UIConstants.EMPTY_STRING ) ;
		return aMapping.findForward( UIConstants.BACK ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward closeProgressNotes( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm ;
		detailsForm.setTentativeRefPrgmRef(false); //reset it to original

		detailsForm.setSecondaryAction( UIConstants.EMPTY_STRING ) ;
		return aMapping.findForward( UIConstants.VIEW ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward closeMonthlySummary( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm ;
		detailsForm.setTentativeRefPrgmRef(false); //reset it to original

		detailsForm.setSecondaryAction( UIConstants.EMPTY_STRING ) ;
		return aMapping.findForward( UIConstants.VIEW ) ;
	}
	
	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.CANCEL ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward next( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm ;
		detailsForm.setTentativeRefPrgmRef(false); //reset it to original
		detailsForm.setAction( "attendanceSummary" ) ;
		detailsForm.setSecondaryAction( UIConstants.EMPTY_STRING ) ;

		return aMapping.findForward( UIConstants.NEXT ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward addProgressNotes( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm ;
		detailsForm.setTentativeRefPrgmRef(false);
		String juvenileId = aRequest.getParameter( JUVENILE_ID_STR ) ;
		detailsForm.setFlowInd(aRequest.getParameter("flowInd") ) ;
		
		String operation = detailsForm.getFlowInd();
		String formattedProgressNotes = null;
		if (UIConstants.ADD.equalsIgnoreCase(detailsForm.getFlowInd() ) ){
			Collection<ServiceEventAttendanceResponseEvent> svcEventList = UISupervisionCalendarHelper.getServiceAttendEventList(detailsForm.getEventId());  //detailsForm.getNewAttendanceEvents() ;
			for( ServiceEventAttendanceResponseEvent ev : svcEventList )
			{
				if( ev.getJuvenileId().equals( juvenileId ) )
				{
				    if(ev.getExistingProgressNotes() != null && !ev.getExistingProgressNotes().equalsIgnoreCase("null") && !"".equals(ev.getExistingProgressNotes())){
					
					String existingNotes = this.getFormattedProgressNote(ev, "update");  
				    	ev.setExistingProgressNotes(existingNotes);					
				    }
				    else 
				    {
					ev.setExistingProgressNotes("");
				    }
				    
				    ev.setProgressNotes("");
				    detailsForm.setCurrentAttendanceEvent( ev ) ;
				    break ;				    
				}
			}
		} else { 	
			Collection<ServiceEventAttendanceResponseEvent> svcEventList2 = detailsForm.getExistingAttendanceEvents() ;
			for( ServiceEventAttendanceResponseEvent ev : svcEventList2 )
			{  				    	
				if( ev.getJuvenileId().equals( juvenileId ) )
				{
				    	formattedProgressNotes = this.getFormattedProgressNote(ev, "update");
				    	ev.setExistingProgressNotes(formattedProgressNotes);
				    	
					ev.setProgressNotes("");
					detailsForm.setCurrentAttendanceEvent( ev ) ;
					break ;
				}
			}
		}

		detailsForm.setSecondaryAction( "addProgressNotes" ) ;
		return aMapping.findForward( UIConstants.VIEW ) ;
	}
	
	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward addMonthlySummary( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		String juvenileId = aRequest.getParameter( JUVENILE_ID_STR ) ;
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm ;
		detailsForm.setTentativeRefPrgmRef(false); //reset it to original
		
		Collection<ServiceEventAttendanceResponseEvent> svcEventList = detailsForm.getNewAttendanceEvents() ;
		for( ServiceEventAttendanceResponseEvent ev : svcEventList )
		{
			if( ev.getJuvenileId().equals( juvenileId ) )
			{
				ev.setMonthlySummary("");
				detailsForm.setCurrentAttendanceEvent( ev ) ;
				break ;
			}
		}
		detailsForm.setSecondaryAction( "addMonthlySummary" ) ;
		
		return aMapping.findForward( UIConstants.VIEW ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward link( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		String juvenileId = aRequest.getParameter( JUVENILE_ID_STR ) ;
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm ;
		detailsForm.setTentativeRefPrgmRef(false); //reset it to original
		HttpSession session = aRequest.getSession() ;
		TestingSessionForm sessForm = (TestingSessionForm)session.getAttribute( "testingSessionForm" ) ;
		if( sessForm == null )
		{
			sessForm = new TestingSessionForm() ;
		}

		String locationId = detailsForm.getLocationId() ;
		AddressResponseEvent addrResp = new AddressResponseEvent() ;
		if( locationId != null )
		{
			addrResp = getLocationAddress( locationId ) ;
		}

		StringBuffer buff = new StringBuffer(addrResp.getStreetNum()).append(SINGLE_SPACE_STR)
				.append( addrResp.getStreetName()).append(SINGLE_SPACE_STR)
				.append( addrResp.getCity()).append( SINGLE_SPACE_STR).append( addrResp.getState() )
				.append(SINGLE_SPACE_STR).append( addrResp.getZipCode() ) ;

		if( addrResp.getAdditionalZipCode() != null )
		{
			buff.append( "-" + addrResp.getAdditionalZipCode() ) ;
		}

		sessForm.setLocationDetails( buff.toString() ) ;
		sessForm.setJuvNum( juvenileId ) ;
		sessForm.clear() ;
		sessForm = this.fillDetails( detailsForm, sessForm ) ;

		GetProgramReferralsByServiceEventEvent getPREvent = (GetProgramReferralsByServiceEventEvent)
				EventFactory.getInstance( JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALSBYSERVICEEVENT ) ;

		getPREvent.setServiceEventId( sessForm.getEventId() ) ;
		getPREvent.setJuvenileNum( sessForm.getJuvNum() ) ;

		CompositeResponse response = MessageUtil.postRequest( getPREvent ) ;

		if( response != null )
		{
			ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent)
					MessageUtil.filterComposite( response, ProgramReferralResponseEvent.class ) ;
			if( resp != null )
			{
				sessForm.setProgramReferralNum( resp.getReferralId() ) ;
				sessForm.setReferralDate( resp.getSentDate() ) ;
				ProgramReferralState st = ProgramReferralStateManager.getReferralState( 
							resp.getReferralStatusCd(), resp.getReferralSubStatusCd() ) ;
				sessForm.setProgramStatus( st.getDescription() ) ;
			}
		}

		session.setAttribute( "testingSessionForm", sessForm ) ;
		sessForm.setSecondaryAction( UIConstants.CREATE ) ;
		UIJuvenileHelper.populateJuvenileProfileHeaderForm( aRequest, juvenileId ) ;

		boolean testingSessionAlreadyExists = getTestSessionDetails( juvenileId, aRequest, detailsForm);	
		if (testingSessionAlreadyExists) {
			return aMapping.findForward( "testResultsExist" ) ;
		} else {
			return aMapping.findForward( "testResultsSuccess" ) ;
		}
	}

	/*
	 * @param locationId
	 * @return
	 */
	private AddressResponseEvent getLocationAddress( String locationId )
	{
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		GetJuvenileLocationEvent locationEvent = new GetJuvenileLocationEvent() ;
		locationEvent.setLocationId( locationId ) ;
		dispatch.postEvent( locationEvent ) ;

		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply() ;
		Object obj = MessageUtil.filterComposite( compositeResponse, LocationResponseEvent.class ) ;

		AddressResponseEvent addressRE = null ;
		if( obj != null )
		{
			LocationResponseEvent resp = (LocationResponseEvent)obj ;
			addressRE = resp.getLocationAddress() ;
		}
		
		return addressRE ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward viewProgressNotes( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		String juvenileId = aRequest.getParameter( JUVENILE_ID_STR ) ;
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm ;
		detailsForm.setTentativeRefPrgmRef(false); //reset it to original
		
		boolean exists = false ;
		Collection<ServiceEventAttendanceResponseEvent> svcEventAttendList = detailsForm.getExistingAttendanceEvents() ;
		for( ServiceEventAttendanceResponseEvent ev : svcEventAttendList )
		{
			if( ev.getJuvenileId().equals( juvenileId ) )
			{
			        String pNotes2 = this.getFormattedProgressNote(ev, "update");
				ev.setExistingProgressNotes(pNotes2.toString());
				ev.setProgressNotes(pNotes2.toString());
				detailsForm.setCurrentAttendanceEvent( ev ) ;
				exists = true ;
				break ;
			}
		}

		if( !exists )
		{
			svcEventAttendList = detailsForm.getNewAttendanceEvents() ;
			for( ServiceEventAttendanceResponseEvent ev : svcEventAttendList )
			{
				if( ev.getJuvenileId().equals( juvenileId ) )
				{
					detailsForm.setCurrentAttendanceEvent( ev ) ;
					break ;
				}
			}
		}
		detailsForm.setSecondaryAction( "viewProgressNotes" ) ;
		
		return aMapping.findForward( UIConstants.VIEW ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward viewMonthlySummary( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		String juvenileId = aRequest.getParameter( JUVENILE_ID_STR ) ;
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm ;
		detailsForm.setTentativeRefPrgmRef(false); //reset it to original
		
		boolean exists = false ;
		Collection<ServiceEventAttendanceResponseEvent> svcEventAttendList = detailsForm.getExistingAttendanceEvents() ;
		for( ServiceEventAttendanceResponseEvent ev : svcEventAttendList )
		{
			if( ev.getJuvenileId().equals( juvenileId ) )
			{
				detailsForm.setCurrentAttendanceEvent( ev ) ;
				exists = true ;
				break ;
			}
		}

		if( !exists )
		{
			svcEventAttendList = detailsForm.getNewAttendanceEvents() ;
			for( ServiceEventAttendanceResponseEvent ev : svcEventAttendList )
			{
				if( ev.getJuvenileId().equals( juvenileId ) )
				{
					detailsForm.setCurrentAttendanceEvent( ev ) ;
					break ;
				}
			}
		}
		detailsForm.setSecondaryAction( "viewMonthlySummary" ) ;
		
		return aMapping.findForward( UIConstants.VIEW ) ;
	}

	
	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward saveProgressNotes( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm ;
		detailsForm.setTentativeRefPrgmRef(false); //reset it to original
		String juvenileId = detailsForm.getCurrentAttendanceEvent().getJuvenileId() ;
		
		Collection<ServiceEventAttendanceResponseEvent> svcEventAttendList = new ArrayList();
		if (UIConstants.ADD.equalsIgnoreCase(detailsForm.getFlowInd() ) ){
			svcEventAttendList = detailsForm.getNewAttendanceEvents() ;
		} else {
			svcEventAttendList = detailsForm.getExistingAttendanceEvents() ;
		}	
		String existingProgressNotes = "";
		for( ServiceEventAttendanceResponseEvent ev : svcEventAttendList )
		{
			if( ev.getJuvenileId().equals( juvenileId ) )
			{
				existingProgressNotes = detailsForm.getCurrentAttendanceEvent().getExistingProgressNotes();
				if (existingProgressNotes == null || "".equals(existingProgressNotes)) {
					//StringBuffer notes = new StringBuffer(detailsForm.getCurrentAttendanceEvent().getExistingProgressNotes());
					//notes = notes.append( " " ) ;
					//String pNotes = this.getFormattedProgressNote(ev, "add");
					//notes = notes.append(pNotes);
					ev.setExistingProgressNotes(existingProgressNotes);
					ev.setProgressNotes( detailsForm.getCurrentAttendanceEvent().getProgressNotes() ) ;
				} else {
				    	//StringBuffer notes2 = new StringBuffer(detailsForm.getCurrentAttendanceEvent().getExistingProgressNotes());
					String pNotes2 = this.getFormattedProgressNote(ev, "update");
					ev.setExistingProgressNotes(pNotes2.toString());
					ev.setProgressNotes( detailsForm.getCurrentAttendanceEvent().getProgressNotes() ) ;
				}
				break ;
			}
		}

		detailsForm.setSecondaryAction( "saveProgressNotes" ) ;
		
		ServiceEventAttendanceResponseEvent ev1 = detailsForm.getCurrentAttendanceEvent();
/** 3/10/2011 defect 69467 - status code check no longer valid as attended, absent and excused allow notes update */		
		if( ev1.getAttendanceStatusCd() != null)   // && 
//		    (ev1.getAttendanceStatusCd().equals(PDCalendarConstants.JUV_ATTEND_STATUS_CONFIRMED) || 
//			ev1.getAttendanceStatusCd().equals(PDCalendarConstants.JUV_ATTEND_STATUS_UNCONFIRMED) ))
		{
			SaveProgressNotesEvent saveJuvEvent = (SaveProgressNotesEvent) EventFactory.getInstance( ServiceEventControllerServiceNames.SAVEPROGRESSNOTES ) ;
			saveJuvEvent.setJuvenileId(ev1.getJuvenileId());
			saveJuvEvent.setProgressNotes(ev1.getProgressNotes());
			saveJuvEvent.setServiceEventId(ev1.getServiceEventId());
			MessageUtil.postRequest(saveJuvEvent);
			
			GetServiceEventAttendanceEvent getServiceEvent = (GetServiceEventAttendanceEvent) 
			EventFactory.getInstance(ServiceEventControllerServiceNames.GETSERVICEEVENTATTENDANCE);
			getServiceEvent.setServiceEventId(detailsForm.getEventId());
			ServiceEventAttendanceResponseEvent responseEvent =
				(ServiceEventAttendanceResponseEvent) MessageUtil.postRequest(getServiceEvent, ServiceEventAttendanceResponseEvent.class);
			
			//detailsForm.getCurrentAttendanceEvent().setExistingProgressNotes(responseEvent.getProgressNotes());
			
			if (existingProgressNotes != null && !"".equals(existingProgressNotes)) {
				StringBuffer eNotes = new StringBuffer(existingProgressNotes);
				eNotes = eNotes.append( " " ) ;
				String eNotesUpdated = this.getFormattedProgressNote(ev1, "update"); //notes.append(saveJuvEvent.getProgressNotes());
				eNotes.append(eNotesUpdated);
				existingProgressNotes = eNotes.toString();
				//String note1 = this.getFormattedProgressNote(responseEvent, "update");
				//existingProgressNotes = saveJuvEvent.g ; //responseEvent.getExistingProgressNotes();
			} else {
			    	//String note2 = this.getFormattedProgressNote(responseEvent, "add");
			    	existingProgressNotes = this.formatProgressNote(detailsForm.getCurrentAttendanceEvent(), saveJuvEvent.getProgressNotes());
			}
			
			detailsForm.getCurrentAttendanceEvent().setExistingProgressNotes(existingProgressNotes);
//			detailsForm.getCurrentAttendanceEvent().setProgressNotes("");
			
  		}
		
		return aMapping.findForward( UIConstants.VIEW ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward saveMonthlySummary( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm ;
		detailsForm.setTentativeRefPrgmRef(false); //reset it to original
		String juvenileId = detailsForm.getCurrentAttendanceEvent().getJuvenileId() ;
		
		Collection<ServiceEventAttendanceResponseEvent> svcEventAttendList = detailsForm.getNewAttendanceEvents() ;
		for( ServiceEventAttendanceResponseEvent ev : svcEventAttendList )
		{
			if( ev.getJuvenileId().equals( juvenileId ) )
			{
				ev.setMonthlySummary( detailsForm.getCurrentAttendanceEvent().getMonthlySummary() ) ;
				break ;
			}
		}
		detailsForm.setSecondaryAction( "saveMonthlySummary" ) ;
		
		ServiceEventAttendanceResponseEvent ev1 = detailsForm.getCurrentAttendanceEvent();
		if( ev1.getAttendanceStatusCd() != null && 
		    (ev1.getAttendanceStatusCd().equals(PDCalendarConstants.JUV_ATTEND_STATUS_CONFIRMED) || 
			ev1.getAttendanceStatusCd().equals(PDCalendarConstants.JUV_ATTEND_STATUS_UNCONFIRMED) ))
		{
			SaveMonthlySummaryEvent saveJuvEvent = (SaveMonthlySummaryEvent) EventFactory.getInstance( ServiceEventControllerServiceNames.SAVEMONTHLYSUMMARY ) ;
			saveJuvEvent.setJuvenileId(ev1.getJuvenileId());
			saveJuvEvent.setMonthlySummary(ev1.getMonthlySummary());
			saveJuvEvent.setServiceEventId(ev1.getServiceEventId());
			MessageUtil.postRequest(saveJuvEvent);
			
			detailsForm.getCurrentAttendanceEvent().setMonthlySummary(ev1.getMonthlySummary());
		}
		
		return aMapping.findForward( UIConstants.VIEW ) ;
	}

	
	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancelProgressNotes( ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm ;
		detailsForm.setTentativeRefPrgmRef(false);
		String juvenileId = detailsForm.getCurrentAttendanceEvent().getJuvenileId() ;
		
		Collection<ServiceEventAttendanceResponseEvent> svcEventAttendList = detailsForm.getNewAttendanceEvents() ;
		for( ServiceEventAttendanceResponseEvent ev : svcEventAttendList )
		{
			if( ev.getJuvenileId().equals( juvenileId ) )
			{
				ev.setProgressNotes( UIConstants.EMPTY_STRING ) ;
				break ;
			}
		}
		detailsForm.setSecondaryAction( UIConstants.EMPTY_STRING ) ;
		
		return aMapping.findForward( UIConstants.VIEW ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancelMonthlySummary( ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm ;
		detailsForm.setTentativeRefPrgmRef(false); //reset it to original
		String juvenileId = detailsForm.getCurrentAttendanceEvent().getJuvenileId() ;
		
		Collection<ServiceEventAttendanceResponseEvent> svcEventAttendList = detailsForm.getNewAttendanceEvents() ;
		for( ServiceEventAttendanceResponseEvent ev : svcEventAttendList )
		{
			if( ev.getJuvenileId().equals( juvenileId ) )
			{
				ev.setMonthlySummary( UIConstants.EMPTY_STRING ) ;
				break ;
			}
		}
		detailsForm.setSecondaryAction( UIConstants.EMPTY_STRING ) ;
		
		return aMapping.findForward( UIConstants.VIEW ) ;
	}

	/*
	 * @param sedForm
	 * @param sessForm
	 * @return
	 */
	private TestingSessionForm fillDetails( ServiceEventDetailsForm sedForm, TestingSessionForm sessForm )
	{
		sessForm.setServiceProviderName( sedForm.getServiceProviderName() ) ;
		sessForm.setInstructorName( sedForm.getInstructorName() ) ;
		sessForm.setEventStatus( sedForm.getEventStatus() ) ;
		sessForm.setEvtSessionLength( UIUtil.getTimeInHours( sedForm.getEventSessionLength() ) ) ;
		sessForm.setEventTime( DateUtil.dateToString( sedForm.getEventDate(), UIConstants.TIME24_FMT_1 ) ) ;
		sessForm.setEventType( sedForm.getEventType() ) ;
		sessForm.setServiceLocationUnit( sedForm.getServiceLocationName() ) ;
		sessForm.setSessionDate( DateUtil.dateToString( sedForm.getEventDate(), UIConstants.DATE_FMT_1 ) ) ;
		sessForm.setEventId( sedForm.getEventId() ) ;
		
		return sessForm ;
	}


	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward addTestResults( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm)aForm ;
		detailsForm.setTentativeRefPrgmRef(false); //reset it to original
		// bug fix for 112451
		HttpSession session = aRequest.getSession();
		      JuvenileBriefingDetailsForm juvenileBriefingForm = null;

		      juvenileBriefingForm = UIJuvenileHelper.getJuvBriefingDetailsForm(aRequest);

		      if (juvenileBriefingForm == null)
		      {

		          juvenileBriefingForm = new JuvenileBriefingDetailsForm();
		          setProfileDetail(detailsForm.getJuvenileNum(), juvenileBriefingForm);
		          session.setAttribute("juvenileBriefingDetailsForm", juvenileBriefingForm);
		      }
		//
		GetServiceEventAttendanceEvent getServAttendanceEvent = (GetServiceEventAttendanceEvent)
				EventFactory.getInstance( ServiceEventControllerServiceNames.GETSERVICEEVENTATTENDANCE ) ;
		getServAttendanceEvent.setServiceEventId( detailsForm.getEventId() ) ;
		dispatch.postEvent( getServAttendanceEvent ) ;

		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply() ;
		Map dataMap = MessageUtil.groupByTopic( compositeResponse ) ;
		MessageUtil.processReturnException( dataMap ) ;
		Collection<ServiceEventAttendanceResponseEvent> attendanceEvents = 
				MessageUtil.compositeToCollection( compositeResponse, ServiceEventAttendanceResponseEvent.class ) ;

		ArrayList existingAttendanceEvents = new ArrayList() ;
		ArrayList newAttendanceEvents = new ArrayList() ;
		
		if( attendanceEvents.size() > 0 )
		{
			ServiceEventAttendanceResponseEvent singleEvent = null ;
			int attendCount = 0 ;
			String attendStatus = UIConstants.EMPTY_STRING ;
			detailsForm.setAttendanceEventsPresent( true ) ;
			
			for( ServiceEventAttendanceResponseEvent ev : attendanceEvents )
			{
				attendStatus = ev.getAttendanceStatusCd() ;
				if( attendStatus != null )
				{
					if( attendStatus.equals( PDCalendarConstants.JUV_ATTEND_STATUS_CONFIRMED ) || 
							attendStatus.equals( PDCalendarConstants.JUV_ATTEND_STATUS_UNCONFIRMED ) )
					{
						newAttendanceEvents.add( ev ) ;
					}
					else
					{
						existingAttendanceEvents.add( ev ) ;
						if( attendStatus.equals( PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED ) )
						{
							singleEvent = ev ;
							++attendCount ;
						}
					}
				}
			}
			
			if( attendCount == 1 )
			{//if only one juvenile go straight to Create Test Session page
				detailsForm.setExistingAttendanceEvents( existingAttendanceEvents ) ;
				detailsForm.setNewAttendanceEvents( newAttendanceEvents ) ;
				detailsForm.setSecondaryAction( UIConstants.EMPTY_STRING ) ;
				detailsForm.setAction( "attendanceConfirm" ) ;
				detailsForm.setSecondaryAction( ADD_TEST_RESULTS_STR ) ;
				boolean testingSessionAlreadyExists = getTestSessionDetails( singleEvent.getJuvenileId(), aRequest, detailsForm);	
				if (testingSessionAlreadyExists) {
					return aMapping.findForward( "testResultsExist" ) ;
				} else {
					return aMapping.findForward( "testResultsSuccess" ) ;
				}
			}
		}

		detailsForm.setExistingAttendanceEvents( existingAttendanceEvents ) ;
		detailsForm.setNewAttendanceEvents( newAttendanceEvents ) ;
		detailsForm.setSecondaryAction( UIConstants.EMPTY_STRING ) ;
		detailsForm.setAction( "attendanceConfirm" ) ;
		detailsForm.setSecondaryAction( ADD_TEST_RESULTS_STR ) ;
		return aMapping.findForward( UIConstants.NEXT ) ;
	}
	private void setProfileDetail(String juvenileNum, JuvenileBriefingDetailsForm form)
	    {
		GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

		reqProfileMain.setJuvenileNum(juvenileNum);
		reqProfileMain.setFromProfile(form.getFromProfile());
		CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
		JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);

		form.setJisInfo(new JuvenileJISResponseEvent());
		if (juvProfileRE != null)
		{
		    form.setProfileDetail(juvProfileRE);
		    form.setProfileDescriptions();

		    if (juvProfileRE.getDateOfBirth() != null)
		    { // Get age based on
		      // year
			int age = UIUtil.getAgeInYears(juvProfileRE.getDateOfBirth());
			if (age > 0)
			{
			    form.setAge(String.valueOf(age));
			}
			else
			{
			    form.setAge(UIConstants.EMPTY_STRING);
			}
		    }
		    Collection jisResps = juvProfileRE.getJisInfo();
		    if (jisResps != null)
		    {
			Collections.sort((List) jisResps);
			Iterator jisIter = jisResps.iterator();
			if (jisIter.hasNext())
			{
			    JuvenileJISResponseEvent jis = (JuvenileJISResponseEvent) jisIter.next();
			    form.setJisInfo(jis);
			}
		    }

		    form.setInMentalHealthServices(juvProfileRE.isMentalHealthServices());
		  //U.S 88526
	    	if ( juvProfileRE.getHispanic() != null )
	    	{
	    	    if ( juvProfileRE.getHispanic().equalsIgnoreCase("Y"))
	    	    {
	    		form.setHispanic(UIConstants.YES_FULL_TEXT);
	    	    }
	    	    else
	    	    {
	    		form.setHispanic(UIConstants.NO_FULL_TEXT);
	    	    }
	    	}
	    	else
	    	{
	    	    form.setHispanic(UIConstants.EMPTY_STRING);
	    	}
		}
		
		
	    }
	/*
	 * 
	 */
	private boolean getTestSessionDetails(String juvenileId,HttpServletRequest aRequest,ServiceEventDetailsForm detailsForm)
	{
		boolean testingSessionAlreadyExists = false;
		HttpSession session = aRequest.getSession() ;
		TestingSessionForm sessForm = (TestingSessionForm)session.getAttribute( "testingSessionForm" ) ;
		
		if( sessForm == null )
		{
			sessForm = new TestingSessionForm() ;
		}

		String locationId = detailsForm.getLocationId() ;
		AddressResponseEvent addrResp = new AddressResponseEvent() ;
		
		if( locationId != null )
		{
			addrResp = getLocationAddress( locationId ) ;
		}

		StringBuffer buff = new StringBuffer(addrResp.getStreetNum() ).append(SINGLE_SPACE_STR)
				.append( addrResp.getStreetName()).append(SINGLE_SPACE_STR).append( addrResp.getCity() )
				.append(SINGLE_SPACE_STR).append( addrResp.getState()).append(SINGLE_SPACE_STR).append( addrResp.getZipCode() ) ;

		if( addrResp.getAdditionalZipCode() != null )
		{
			buff.append( "-" + addrResp.getAdditionalZipCode() ) ;
		}

		sessForm.setLocationDetails( buff.toString() ) ;
		sessForm.setJuvNum( juvenileId ) ;
		sessForm.clear() ;
		sessForm = this.fillDetails( detailsForm, sessForm ) ;

		GetProgramReferralsByServiceEventEvent getPREvent = (GetProgramReferralsByServiceEventEvent)
				EventFactory.getInstance( JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALSBYSERVICEEVENT ) ;

		getPREvent.setServiceEventId( sessForm.getEventId() ) ;
		getPREvent.setJuvenileNum( sessForm.getJuvNum() ) ;

		CompositeResponse response = MessageUtil.postRequest( getPREvent ) ;

		if( response != null )
		{
			ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent)
					MessageUtil.filterComposite( response, ProgramReferralResponseEvent.class ) ;
			if( resp != null )
			{
				sessForm.setProgramReferralNum( resp.getReferralId() ) ;
				sessForm.setReferralDate( resp.getSentDate() ) ;
				ProgramReferralState st = ProgramReferralStateManager.getReferralState( 
						resp.getReferralStatusCd(), resp.getReferralSubStatusCd() ) ;
				sessForm.setProgramStatus( st.getDescription() ) ;
			}
		}

		//DJN Check to see if mental health testing session has already been created for this juvenile and this event
		GetMentalHealthTestingSessionEvent getMHTSEvent = (GetMentalHealthTestingSessionEvent)
		EventFactory.getInstance( JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHTESTINGSESSION ) ;
		getMHTSEvent.setServiceEventId( sessForm.getEventId() ) ;
		getMHTSEvent.setJuvenileNum( sessForm.getJuvNum() ) ;
		CompositeResponse cResponse = MessageUtil.postRequest( getMHTSEvent ) ;
		
		if( cResponse != null )
		{
			TestingSessionResponseEvent mentalHealthResponse = (TestingSessionResponseEvent)
					MessageUtil.filterComposite( cResponse, TestingSessionResponseEvent.class ) ;
			if( mentalHealthResponse != null )
			{
				sessForm.setReferralDate(mentalHealthResponse.getReferralDate());
				{ String testType = mentalHealthResponse.getTestType() ;
					sessForm.setTestTypeId( (testType == null) ? "" : testType );
				}
				sessForm.setTestSessId(mentalHealthResponse.getTestSessID());
				sessForm.setActualSessionLength(mentalHealthResponse.getActualSessionLength());
				sessForm.setPsychoAssessment(mentalHealthResponse.getPsychologicalAssessment());
				sessForm.setPsychiatricAssessment(mentalHealthResponse.getPsychiatricAssessment());
				sessForm.setMentalRetardationDiagnosis(mentalHealthResponse.getMentalRetardationDiagnosis());
				sessForm.setMentalIllnessDiagnosis(mentalHealthResponse.getMentalIllnessDiagnosis());
				sessForm.setRecommendations(mentalHealthResponse.getRecommendations());
				sessForm.setActionType("confirm");
				sessForm.setSecondaryAction("update");
				sessForm.setConfirmMessage("");
				testingSessionAlreadyExists = true;
			} else {
				testingSessionAlreadyExists = false;
				sessForm.setSecondaryAction( UIConstants.CREATE ) ;
			}
		}
		
//   	check if DSM test data already exists for this testSession
   		Collection coll = getDSMTestList(sessForm);
   		sessForm.setDsmResultsList(coll);
		
		session.setAttribute( "testingSessionForm", sessForm ) ;
		UIJuvenileHelper.populateJuvenileProfileHeaderForm( aRequest, juvenileId ) ;
		return testingSessionAlreadyExists;
	}
	
	private Collection getDSMTestList(TestingSessionForm sessForm)
	   {
	   	GetDSMIVRecordEvent dsmEvent = (GetDSMIVRecordEvent)EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETDSMIVRECORD);
		dsmEvent.setTestSessID(sessForm.getTestSessId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(dsmEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Collection coll = MessageUtil.compositeToCollection(response, DSMIVTestResponseEvent.class);
		return coll;
	   }

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward addAttendance(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) 
  {
		ServiceEventDetailsForm detailsForm = (ServiceEventDetailsForm) aForm;
		detailsForm.setTentativeRefPrgmRef(false); //reset it to original
		detailsForm.setSecondaryAction("addAttendess");
		String juvenileId = aRequest.getParameter( "juvenileId") ;
		detailsForm.setJuvenileNum(juvenileId);
		boolean exists = false ;
//		Collection<ServiceEventAttendanceResponseEvent> svcEventList = detailsForm.getNewAttendanceEvents() ;
		Collection<ServiceEventAttendanceResponseEvent> svcEventList = detailsForm.getExistingAttendanceEvents() ;
		for( ServiceEventAttendanceResponseEvent ev : svcEventList )
		{
			if( ev.getJuvenileId().equals( juvenileId ) )
			{
				detailsForm.setCurrentAttendanceEvent(ev);
				exists = true;
				break ;
			}
		}
		if( !exists )
		{
			svcEventList = detailsForm.getNewAttendanceEvents() ;
			for( ServiceEventAttendanceResponseEvent ev : svcEventList )
			{
				if( ev.getJuvenileId().equals( juvenileId ) )
				{
					detailsForm.setCurrentAttendanceEvent( ev ) ;
					break ;
				}
			}
		}
		Collection names = UISupervisionCalendarHelper.getJuvenileContacts(juvenileId);
		detailsForm.setContactNames(names);
		
		return( aMapping.findForward(UIConstants.VIEW_DOCUMENT_ATTENDANCE) );

  }
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49A50033
	 */
	public ActionForward saveAttendeeList(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ServiceEventDetailsForm form = (ServiceEventDetailsForm) aForm;
		form.setTentativeRefPrgmRef(false); //reset it to original
		ServiceEventAttendanceResponseEvent ev1 = form.getCurrentAttendanceEvent();
		
		SaveJuvenileAttendanceEvent saveAttendanceEvent = (SaveJuvenileAttendanceEvent)
				EventFactory.getInstance(ServiceEventControllerServiceNames.SAVEJUVENILEATTENDANCE);

		saveAttendanceEvent.setJuvenileId(form.getJuvenileNum());
		saveAttendanceEvent.setServiceEventId(form.getEventId());
		saveAttendanceEvent.setEventStartDate(form.getEventDate());
		saveAttendanceEvent.setEventCategory(UIConstants.PRESCHEDULED_SERVICE_TYPE);
		saveAttendanceEvent.setProgressNotes(ev1.getProgressNotes());
		saveAttendanceEvent.setAttendanceStatusCd(ev1.getAttendanceStatusCd());

		if (saveAttendanceEvent.getServiceEventId() == null || saveAttendanceEvent.getEventStartDate() == null)
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, 
		    		new ActionMessage("error.generic", "Current Event is invalid; please re-select the Event from the Calendar."));
		    saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.CANCEL);
		}

		List attendeeNames = saveAttendanceEvent.getAddlAttendeeNames();
		attendeeNames.clear();
// compare selected Attendee Names to Contacts to get response events		
		if (form.getSelectedAttendeeNames() != null){
			String fullName = "";
			List contactNames = (List)form.getContactNames();
			for (int y=0; y < form.getSelectedAttendeeNames().length; y++)
			{
				for (int x = 0; x<contactNames.size(); x++)
				{
					AttendeeNameResponseEvent anre = (AttendeeNameResponseEvent) contactNames.get(x);
					fullName = anre.getFormattedName();
					if (fullName.equalsIgnoreCase(form.getSelectedAttendeeNames()[y]))
					{
						attendeeNames.add(anre);
						break;
					}
				}	
			}
		}

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(saveAttendanceEvent);

		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
//		form.setAction("attendanceConfirm");
		form.setContactNames(new ArrayList());
		form.setSecondaryAction("saveAttendeeList");
// add the selected attendee names to event so jsp will know to display View instead of Add
		boolean attendeeFound = false;
		Collection<ServiceEventAttendanceResponseEvent> svcEventList = form.getNewAttendanceEvents() ;
		for( ServiceEventAttendanceResponseEvent ev : svcEventList )
		{
			if( ev.getJuvenileId().equals( form.getJuvenileNum() ) )
			{
				ev.setAddlAttendeeNames(attendeeNames);
				attendeeFound = true;
				break ;
			}
		}
		if (!attendeeFound)
		{
			svcEventList = form.getExistingAttendanceEvents() ;
			for( ServiceEventAttendanceResponseEvent ev : svcEventList )
			{
				if( ev.getJuvenileId().equals( form.getJuvenileNum() ) )
				{
					ev.setAddlAttendeeNames(attendeeNames);
					break ;
				}
			}
		}
		return( aMapping.findForward(UIConstants.CONFIRM) );
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward viewAttendance(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) 
  {
		ServiceEventDetailsForm sedForm = (ServiceEventDetailsForm) aForm;
		sedForm.setTentativeRefPrgmRef(false); //reset it to original

		sedForm.setSecondaryAction("viewAttendees");
		String juvenileId = aRequest.getParameter( "juvenileId") ;
		String listInd = aRequest.getParameter( "listInd") ;
		sedForm.setJuvenileNum(juvenileId);
		sedForm.setAttendeeNamesStr("");
		Collection<ServiceEventAttendanceResponseEvent> svcEventList = new ArrayList();
		if (listInd.equalsIgnoreCase("new"))
		{	
			svcEventList = sedForm.getNewAttendanceEvents() ;
		} else {
			svcEventList = sedForm.getExistingAttendanceEvents() ;
		}
		for( ServiceEventAttendanceResponseEvent ev : svcEventList )
		{
			if( ev.getJuvenileId().equals( juvenileId ) )
			{
				StringBuffer sb = new StringBuffer();
				String prefix = "";
				List names = (List) ev.getAddlAttendeeNames();
				for (int x = 0; x<names.size(); x++){
					AttendeeNameResponseEvent anre = (AttendeeNameResponseEvent) names.get(x);
					sb.append(prefix);
					sb.append(anre.getFormattedName());
					prefix = "; ";	
				}	
				sedForm.setAttendeeNamesStr(sb.toString());
				sedForm.setCurrentAttendanceEvent(ev);
				break ;
			}
		}
		return( aMapping.findForward(UIConstants.VIEW_DOCUMENT_ATTENDANCE) );

  }	
	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancelAttendeeList( ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ServiceEventDetailsForm form = (ServiceEventDetailsForm) aForm;
		form.setTentativeRefPrgmRef(false); //reset it to original
		form.setContactNames(new ArrayList());
		form.setAttendeeNamesStr(UIConstants.EMPTY_STRING);
		form.setSecondaryAction(UIConstants.EMPTY_STRING);
		return aMapping.findForward( UIConstants.VIEW ) ;
	}
	
	public String formatProgressNote(ServiceEventAttendanceResponseEvent attendanceEvent, String eventProgressNote){

            int indexOfLeftBracket = 0;
            String eventUpdateDate = "";
            String progressNote = null;
            String progressNoteUpdated = null;
            String updatedUser = null;
            IName userName = null;
            
            if(attendanceEvent != null && attendanceEvent.getUpdateDate() != null && !attendanceEvent.getUpdateDate().equals("")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            eventUpdateDate = dateFormat.format(attendanceEvent.getUpdateDate());
            }
            
            if(attendanceEvent != null && eventProgressNote != null && !"".equals(eventProgressNote) && attendanceEvent.getUpdateUser()!= null)
            {        	
        	indexOfLeftBracket = eventProgressNote.indexOf('[');  //attendanceEvent.getProgressNotes().indexOf('[');             
             
             if(indexOfLeftBracket != -1 && indexOfLeftBracket > 0){
                 progressNote = eventProgressNote.substring(0, indexOfLeftBracket);
             }
             
             //===== get the JIMS2 login information for generic login		 
             Collection<ServiceProviderContactResponseEvent> contacts = UIServiceProviderHelper.getContactsFromSecurityManager(attendanceEvent.getUpdateUser());
             Iterator iter = contacts.iterator();
             
             while (iter.hasNext())
             {
                 ServiceProviderContactResponseEvent ur = (ServiceProviderContactResponseEvent)iter.next();
                 if(ur.getEmail() != null && !"".equals(ur.getEmail()))
                 {
            	 if(ur.getEmail().equalsIgnoreCase(attendanceEvent.getUpdateJims2User()))
            	 {
            	     updatedUser = ur.getLastName() + ", " + ur.getFirstName();
            	     break;
            	 }
                 }					
            }	
             
	    if (updatedUser == null || "".equals(updatedUser))
	    {
		if (attendanceEvent.getUpdateUser() != null
			&& SecurityUIHelper.getJIMSLogonId() != null
			&& attendanceEvent.getUpdateUser().equalsIgnoreCase(SecurityUIHelper.getJIMSLogonId()))
		{
		    updatedUser = SecurityUIHelper.getServiceProviderName();
		}
	    }
            	
            //if non generic login - get the user name instead
            if(updatedUser == null || "".equals(updatedUser))
            {
                userName = (IName)SecurityUIHelper.getUserName(attendanceEvent.getUpdateUser());
            	    
                updatedUser = userName != null && userName.getLastName() != null && userName.getFirstName() != null ? 
            		    userName.getLastName() + ", " + userName.getFirstName() : "";			    
            }
            
            }	   
            
            if(progressNote != null && !progressNote.equals("")){
            progressNoteUpdated = progressNote + "[" + eventUpdateDate + " - " + updatedUser + "]";
            }    
            	    
            return progressNoteUpdated;
    }
	
	String getFormattedProgressNote(ServiceEventAttendanceResponseEvent attendanceEvent, String operation){
	    
	    String updatedNote = null;
	    StringBuilder strBuilder = new StringBuilder();
	    String progressNotes = null;
	    
	    if(attendanceEvent != null && operation != null && !operation.equalsIgnoreCase("add")){
		progressNotes = attendanceEvent.getExistingProgressNotes();
	    } else {
		progressNotes = attendanceEvent.getProgressNotes();
	    }
	    
	    int indexOfRightBracket = 0;
	    if(progressNotes != null && !progressNotes.equals(""))
	    {	      
	       indexOfRightBracket = progressNotes.indexOf("]");
	       
	       if(indexOfRightBracket > 0)
	       {
		   List<String> notes = this.convertProgressNotesToArray(progressNotes);	       

		       if(notes != null && !"".equals(notes) && notes.size() > 1){

			   for(int i = 0; i < notes.size(); i++){
			       String note = notes.get(i);
			       
			       if(note != null && !"".equals(note)){
				   
				   updatedNote = this.formatProgressNote(attendanceEvent, note);
				   
				   strBuilder.append(updatedNote + " ");
			       }	       		       
			   }		   
		       } else {
			   
			   updatedNote = this.formatProgressNote(attendanceEvent, progressNotes);
			   strBuilder.append(updatedNote);
		       }
		       
	       }
	       else {
		   
		   updatedNote = this.formatProgressNote(attendanceEvent, progressNotes);
		   strBuilder.append(updatedNote);
	       }
	       
	   }
	   
	   return strBuilder != null ? strBuilder.toString() : null;
	}
	
	private List<String> convertProgressNotesToArray(String progressNotes)
	{
	    List<String> notes = new ArrayList<String>();

	    int indexOfRightBracket = 0;
	    int start = 0;
	    if(progressNotes != null && !progressNotes.equals("") && progressNotes.length() > 0)
	    {      	       
	       for (int i = 0; i < progressNotes.length(); i++){
		   
		   indexOfRightBracket = progressNotes.indexOf("]", start);
		   
		   if(indexOfRightBracket != -1){
		       String note = progressNotes.substring(start, indexOfRightBracket + 1);
		       notes.add(note);
			   
		       start = indexOfRightBracket + 1;
		       i = start;
		   }
		   		   
	       }
	    }	    
	    
	    return notes;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap() ;
		buttonMap.put( "button.next", "next" ) ;
		buttonMap.put( "button.back", "back" ) ;
		buttonMap.put( "button.cancel", "cancel" ) ;
		buttonMap.put( "button.viewProgressNotes", "viewProgressNotes" ) ;
		buttonMap.put( "button.addProgressNotes", "addProgressNotes" ) ;
		buttonMap.put( "button.saveProgressNotes", "saveProgressNotes" ) ;
		buttonMap.put( "button.cancelProgressNotes", "cancelProgressNotes" ) ;
		buttonMap.put( "button.closeProgressNotes", "closeProgressNotes" ) ;
		buttonMap.put( "button.viewMonthlySummary", "viewMonthlySummary" ) ;
		buttonMap.put( "button.addMonthlySummary", "addMonthlySummary" ) ;
		buttonMap.put( "button.saveMonthlySummary", "saveMonthlySummary" ) ;
		buttonMap.put( "button.cancelMonthlySummary", "cancelMonthlySummary" ) ;
		buttonMap.put( "button.closeMonthlySummary", "closeMonthlySummary" ) ;
		buttonMap.put( "button.link", "link" ) ;
		buttonMap.put( "button.addTestResults", "addTestResults" ) ;
		buttonMap.put( "button.addAttendees","addAttendance");
		buttonMap.put( "button.viewAttendees","viewAttendance");
		buttonMap.put( "button.saveAttendeeList","saveAttendeeList");	
		buttonMap.put( "button.cancelAttendeeList","cancelAttendeeList");
		buttonMap.put( "button.closeAttendeeList","cancelAttendeeList");
		return buttonMap ;
	}
}