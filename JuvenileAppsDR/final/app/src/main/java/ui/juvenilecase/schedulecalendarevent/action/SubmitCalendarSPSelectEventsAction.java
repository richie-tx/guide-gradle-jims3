package ui.juvenilecase.schedulecalendarevent.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.CreateCalendarServiceEventEvent;
import messaging.calendar.CreateScheduleCalendarEventsEvent;
import messaging.calendar.GetProgramAttendanceEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.productionsupport.RetrieveJuvPgmRefsByProvPgmIdEvent;
import messaging.programreferral.SaveProgramReferralAssociationsEvent;
import messaging.programreferral.SaveProgramReferralEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileProgramReferralControllerServiceNames;
import naming.PDCalendarConstants;
import naming.ProgramReferralConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.programreferral.JuvenileProgramReferral;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.helper.JuvenileCaseworkAlertsHelper;
import ui.juvenilecase.programreferral.ProgramReferralAction;
import ui.juvenilecase.programreferral.ProgramReferralStateManager;
import ui.juvenilecase.programreferral.UIProgramReferralBean;
import ui.juvenilecase.workshopcalendar.form.ScheduleNewEventForm;

public class SubmitCalendarSPSelectEventsAction extends JIMSBaseAction
{
	
	/**
	 * @roseuid 4576E78400F1
	 */
	public SubmitCalendarSPSelectEventsAction()
	{
	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.createProgramReferral", "linkToCreateReferral");
		keyMap.put("button.returnToCalendar", "linkToCalendar");
	}	

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ScheduleNewEventForm sneForm = (ScheduleNewEventForm)aForm ;
		sneForm.setConfirmationMsg("");
// used SubmitScheduleEventAction.goAhead. goAheadAndSchedule() as example
		CreateScheduleCalendarEventsEvent createEvent = 
			(CreateScheduleCalendarEventsEvent)	EventFactory.getInstance( ServiceEventControllerServiceNames.CREATESCHEDULECALENDAREVENTS ) ;

		CreateCalendarServiceEventEvent calendarServiceEvent = new CreateCalendarServiceEventEvent() ;
// currentService is blank????
		calendarServiceEvent.setEventTypeId( sneForm.getCurrentService().getServiceTypeId() ) ;
		calendarServiceEvent.setEventStatusId( PDCalendarConstants.SERVICE_EVENT_STATUS_AVAILABLE ) ;
		calendarServiceEvent.setEventComments( sneForm.getCurrentService().getCurrentEvent().getComments() ) ;
		calendarServiceEvent.setEventTypeCategory( UIConstants.PRESCHEDULED_SERVICE_TYPE ) ;

		CalendarContextEvent calendarContextEvent = new CalendarContextEvent();
		
		calendarContextEvent.setProbationOfficerId(sneForm.getOfficerId());
		calendarContextEvent.setJuvenileId(sneForm.getJuvenileNum());
		calendarContextEvent.setCaseFileId(sneForm.getCaseFileId());
		
		calendarServiceEvent.setCalendarContextEvent(calendarContextEvent);
		
		//Nothing has been inserted into the database at this point.
		
		ArrayList<CalendarServiceEventResponseEvent> scheduledEvents = new ArrayList() ;
		
		{ Collection<CalendarServiceEventResponseEvent> serviceEventList = sneForm.getSelectedEventsList();
			scheduledEvents.ensureCapacity( serviceEventList.size() ) ;
			for( CalendarServiceEventResponseEvent anEvent : serviceEventList )
			{
//				if( anEvent.isAddConflictingEvent() )
//				{
  					scheduledEvents.add( anEvent ) ;
//				}
			}
		} // end code block

		if( scheduledEvents.isEmpty() )
		{
			ActionErrors errors = new ActionErrors() ;
			errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( "error.no.event.selected" ) ) ;
			saveErrors( aRequest, errors ) ;
			return aMapping.findForward( UIConstants.SUMMARY ) ;
		}

		calendarServiceEvent.setEvents( scheduledEvents ) ;
		sneForm.setAllEvents( scheduledEvents ) ;
		createEvent.setCreateCalendarEvent( calendarServiceEvent ) ; //Makes first event

		CompositeResponse response = (CompositeResponse)MessageUtil.postRequest( createEvent ) ;

		// Generating Notice for Pending Appointments to JPO
		JuvenileCaseworkAlertsHelper helper = new JuvenileCaseworkAlertsHelper() ;
		JuvenileCasefileForm juvenileCasefileForm = (JuvenileCasefileForm) aRequest.getSession().getAttribute( "juvenileCasefileForm" ) ;
//		String jpoId = juvenileCasefileForm.getProbationOfficerLogonId().trim() ;

		if( sneForm.getJuvenileName() == null )
		{
			sneForm.setJuvenileName( juvenileCasefileForm.getJuvenileName().getFormattedName() ) ;
		}
		
//		String programReferralId = sneForm.getProgramReferral().getReferralId() ;
		String programReferralId = sneForm.getJuvPgmReferralId();
		if( sneForm.isProgramReferralNew() )
		{
			UIProgramReferralBean programReferral = sneForm.getProgramReferral() ;
			programReferral.executeAction() ;
			
			SaveProgramReferralEvent saveRefEvent = programReferral.getSaveProgramReferralEvent() ;
			saveRefEvent.setAttachedEvents( scheduledEvents ) ;
			saveRefEvent.setCasefileId( sneForm.getCaseFileId() ) ;
			saveRefEvent.setProgramId( sneForm.getProgramId() ) ;
			saveRefEvent.setControllingReferralNum(sneForm.getProgramReferral().getControllingReferralNum());

			response = MessageUtil.postRequest( saveRefEvent ) ;
			if( response != null )
			{
				ProgramReferralResponseEvent resp = 
					(ProgramReferralResponseEvent)MessageUtil.filterComposite( response, ProgramReferralResponseEvent.class ) ;
				programReferralId = resp.getReferralId() ;
				resp.setJuvServiceProviderId( programReferral.getJuvServiceProviderId() ) ;
				resp.setJuvenileId( programReferral.getJuvenileId() ) ;
				resp.setProviderProgramName( programReferral.getProviderProgramName() ) ;
				helper.sendSPProgramReferralActionAlert( resp, juvenileCasefileForm ) ;
			}
				
			UIProgramReferralBean.ProgramReferralTaskInfo taskInfo = programReferral.getReferralTaskInfo() ;
			if( taskInfo != null )
			{
				Map map = taskInfo.getParameterMap() ;
				map.put( "referralId", programReferralId ) ;
				programReferral.getReferralTaskInfo().createTask() ;
			}
		}

		if( programReferralId != null )
		{
			SaveProgramReferralAssociationsEvent saveRefAssocEvent = (SaveProgramReferralAssociationsEvent)
					EventFactory.getInstance( JuvenileProgramReferralControllerServiceNames.SAVEPROGRAMREFERRALASSOCIATIONS ) ;
			saveRefAssocEvent.setProgramReferralId( programReferralId ) ;

			List eventIdList = new ArrayList() ;
			Collection<CalendarServiceEventResponseEvent> eventList = sneForm.getAllEvents() ;
			if( eventList != null )
			{
				((ArrayList)eventIdList).ensureCapacity( eventList.size() ) ;
				
				for( CalendarServiceEventResponseEvent anEvent : eventList )
				{
					eventIdList.add( anEvent.getEventId() ) ;
				}
			}
			saveRefAssocEvent.setAttachedEvents( eventIdList ) ;
			response = MessageUtil.postRequest( saveRefAssocEvent ) ;
		}

		sneForm.setPageState("Confirmation");
		String msg = "The following event(s) has been scheduled.";
// add check for program referral added and append msg.
		if (sneForm.isProgramReferralNew() == true)
		{
			msg += " Program Referral has been created.";
		}
		sneForm.setConfirmationMsg(msg);
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward linkToCreateReferral(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ScheduleNewEventForm form = (ScheduleNewEventForm)aForm ;
		
		form.setJuvPgmReferralId("");  // as a precaution
		
		// US 177340 - Code for MAX YOUTH for Service Provider STARTS 
		JuvenileServiceProvider juvServiceProvider = new JuvenileServiceProvider();
		//below check added for BUG 180737
        	if (form.getServiceProviderId() != null)
        	{
        	    juvServiceProvider = JuvenileServiceProvider.find(Integer.valueOf(form.getServiceProviderId()));
        	}
        	else if (form.getProgramReferral()!= null)
        	{
        	    juvServiceProvider = JuvenileServiceProvider.find(Integer.valueOf(form.getProgramReferral().getJuvServiceProviderId()));
        	} //check added for BUG 180737 ENDS
		ActionForward forward = aMapping.findForward( UIConstants.SUCCESS ) ;
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm( aRequest ) ;
		String juvNum = casefileForm.getJuvenileNum();
		//get the MAX YOUTH number for the selected vendor from the table: CSJUVSERVPROV
		if (juvServiceProvider != null && juvServiceProvider.getMaxYouth() != null)
		{
		    int maxYouth = Integer.parseInt(juvServiceProvider.getMaxYouth());
		    if (maxYouth != 0)
		    {
			//String selectedSPVendorId = form.getProgramReferral().getJuvServiceProviderId(); //commented for a BUG 180737
			String selectedSPVendorId = juvServiceProvider.getOID(); //or getServiceProviderID()
			//get all the providerProgramIds for selectedServProvider/selectedSPVendorId
			Collection spList = form.getServiceProviderList();
			ServiceProviderResponseEvent selectedSP = new ServiceProviderResponseEvent();
			Iterator servProviderItr = spList.iterator();
			while (servProviderItr.hasNext())
			{
			    ServiceProviderResponseEvent sp = (ServiceProviderResponseEvent) servProviderItr.next();
			    if (sp.getJuvServProviderId().equalsIgnoreCase(selectedSPVendorId))
			    {
				selectedSP = sp;
				break;
			    }
			}
			Set<Integer> distinctJuvenileIds = new HashSet<>(); // Set to hold distinct juvenile IDs
			List<ServiceResponseEvent> activeProgramsList = (List<ServiceResponseEvent>) selectedSP.getServiceResponseEvents(); //get all the ACTIVE programs for the selected vendor Service Provider Name

			for (ServiceResponseEvent program : activeProgramsList)
			{
			    RetrieveJuvPgmRefsByProvPgmIdEvent getJuvProgramReferralEvent = new RetrieveJuvPgmRefsByProvPgmIdEvent();
			    getJuvProgramReferralEvent.setProvProgramId(program.getProgramId());
			    ArrayList<JuvenileProgramReferral> juvenileProgramReferralList = (ArrayList) CollectionUtil.iteratorToList(JuvenileProgramReferral.findAll(getJuvProgramReferralEvent));
			    // call above gets data FROM JIMS2.CSJUVPROGREF WHERE PROVPROGRAM_ID = program.getProgramId() 
			    Iterator juvPgrmRefItr = juvenileProgramReferralList.iterator();
			    while (juvPgrmRefItr.hasNext())
			    {
				JuvenileProgramReferral jpr = (JuvenileProgramReferral) juvPgrmRefItr.next();
				if (jpr.getEndDate() == null) //
				{
				    String casefileID = jpr.getCasefileId();
				    GetJuvenileCasefileEvent getCasefileEvent = (GetJuvenileCasefileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE);
				    getCasefileEvent.setSupervisionNumber(casefileID); //call to JCCASEFILE
				    CompositeResponse response = MessageUtil.postRequest(getCasefileEvent);
				    JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent) MessageUtil.filterComposite(response, JuvenileCasefileResponseEvent.class);
				    if (program.getServiceProviderId() != null && program.getServiceProviderId().equalsIgnoreCase(juvServiceProvider.getOID()) && program.getProgramEndDate() == null)
				    {
					distinctJuvenileIds.add(Integer.parseInt(casefile.getJuvenileNum()));
				    }
				}
			    }
			}
			if (distinctJuvenileIds.size() >= maxYouth) //check if the total UNIQUE juv count is less than the MAXYOUTH number 
			{
			    //TO DO check if the juvenileNum is already in the set, if yes, go forward with addition of program ref
			    if (distinctJuvenileIds.contains(Integer.parseInt(juvNum)))
			    {
				System.out.println(distinctJuvenileIds);
				System.out.println(juvNum);
			    }
			    else
			    {
				ActionErrors errors = new ActionErrors();
				String msg = "Maximum Youth Limit Exceeded for this Service Provider";
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", msg));
				saveErrors(aRequest, errors);
				forward = aMapping.findForward(UIConstants.FAILURE);
				return forward;
			    }
			}
		    }
		}
		//US 177340 - Code for MAX YOUTH for Service Provider ENDS
		
		UIProgramReferralBean programReferral = new UIProgramReferralBean() ;
		Date saveEventDateYMD = new Date();
		List selEvents = new ArrayList(form.getSelectedEventsList());
		for (int x = 0; x<selEvents.size(); x++)
		{
			CalendarServiceEventResponseEvent cser = (CalendarServiceEventResponseEvent) selEvents.get(x);
			if (x == 0)
			{
				saveEventDateYMD = cser.getEventDate();
			} else {
				if (cser.getEventDate().before(saveEventDateYMD))
				{
					saveEventDateYMD = cser.getEventDate();
				}	
			}
			if (cser.isServiceProvideInhouse()){
				programReferral.setReferralState( ProgramReferralStateManager.getReferralState( 
						ProgramReferralConstants.ACCEPTED, null) ) ;
			} else {
				programReferral.setReferralState( ProgramReferralStateManager.getReferralState( 
						ProgramReferralConstants.TENTATIVE, ProgramReferralConstants.REFERRED ) ) ;
			}
		}
//		Date saveEventDateYMD =(DateUtil.stringToDate(form.getSaveEventDate(), DateUtil.DATE_FMT_1));
		Date currentDateYMD = form.getCurrentDate();
		if (form.getSecondaryAction() == UIConstants.SCHEDULE_PAST_PRESCHEDULED_STR &&
		    currentDateYMD.after(saveEventDateYMD)) {
		    programReferral.setBeginDateStr(DateUtil.dateToString(saveEventDateYMD, DateUtil.DATE_FMT_1)) ;
		  //Defect JIMS200076898 fix starts.
		    form.setSecondaryAction( UIConstants.EMPTY_STRING ) ;
		  //Defect JIMS200076898 fix ends.
		}else{    
		    programReferral.setBeginDateStr( DateUtil.dateToString( form.getCurrentDate( ), DateUtil.DATE_FMT_1 ) ) ;
		}
			
		programReferral.setCurrentAction( ProgramReferralAction.CREATE ) ;
		programReferral.setProviderProgramId( form.getProgramId() ) ;
		programReferral.setProviderProgramName( form.getProgramName() ) ;
		programReferral.setJuvServiceProviderId( form.getServiceProviderId() ) ;
		programReferral.setCasefileId( form.getCaseFileId() ) ;
		programReferral.setJuvenileId( form.getJuvenileNum() ) ;

		form.setProgramReferralNew( true ) ;
		GetProgramAttendanceEvent attendanceEvent = (GetProgramAttendanceEvent)
				EventFactory.getInstance( ServiceEventControllerServiceNames.GETPROGRAMATTENDANCE ) ;
		attendanceEvent.setProgramId( form.getProgramId() ) ;
		attendanceEvent.setJuvenileNum( form.getJuvenileNum() ) ;

		List attendanceEvents = MessageUtil.postRequestListFilter( attendanceEvent, ServiceEventAttendanceResponseEvent.class ) ;
		if( attendanceEvents != null )
		{
			programReferral.setExistingReferrals( attendanceEvents ) ;
		}

		form.setProgramReferral( programReferral ) ;
		form.setFromPage("SPSummary");
		return aMapping.findForward( UIConstants.CREATE_NEW_REFERRAL ) ;
	}
	/**
	 * @param aMapping  
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward linkToCalendar(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ScheduleNewEventForm sneForm = (ScheduleNewEventForm)aForm ;
		sneForm.clear();
		sneForm.spClear();
		aRequest.setAttribute("pageType", "");
		return aMapping.findForward(UIConstants.RETURN_SUCCESS);
	}
}