package ui.juvenilecase.viewcalendar.action;

import java.util.ArrayList ;
import java.util.Calendar ;
import java.util.Collection ;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator ;
import java.util.List ;
import java.util.Map ;
import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import messaging.administerserviceprovider.GetServiceProviderActiveInstructorsEvent;
import messaging.administerserviceprovider.GetServiceProviderInstructorsEvent;
import messaging.administerserviceprovider.reply.JuvenileServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent ;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import messaging.calendar.GetCalendarServiceEventsEvent;
import messaging.calendar.GetViewCalendarDocketEventsByJuvenilesEvent ;
import messaging.calendar.GetOfficerAssociatedJuvenileCasefilesEvent ;
import messaging.calendar.GetViewCalendarDocketEventsEvent ;
import messaging.calendar.GetViewCalendarEventsEvent ;
import messaging.calendar.ServiceInstructorAttribute;
import messaging.calendar.reply.CalendarServiceEventResponseEvent ;
import messaging.calendar.reply.DocketEventResponseEvent ;
import messaging.codetable.criminal.reply.ServiceTypeCdResponseEvent ;
import messaging.contact.officer.reply.OfficerProfileResponseEvent ;
import messaging.error.reply.ErrorResponseEvent ;
import messaging.juvenile.SearchJuvenileProfilesEvent ;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent ;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent ;
import messaging.officer.SearchJuvenileOfficerEvent ;
import mojo.km.messaging.EventFactory ;
import mojo.km.messaging.Composite.CompositeResponse ;
import mojo.km.utilities.DateUtil ;
import mojo.km.utilities.MessageUtil ;
import naming.JuvenileControllerServiceNames ;
import naming.OfficerProfileControllerServiceNames ;
import naming.PDCalendarConstants ;
import naming.PDConstants ;
import naming.ServiceEventControllerServiceNames ;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants ;
import org.apache.struts.action.ActionMessage ;
import org.apache.struts.action.ActionErrors ;
import org.apache.struts.action.ActionForm ;
import org.apache.struts.action.ActionForward ;
import org.apache.struts.action.ActionMapping ;
import ui.action.JIMSBaseAction ;
import ui.common.Name ;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileHelper ;
import ui.juvenilecase.schedulecalendarevent.CalendarRetrieverFactory;
import ui.juvenilecase.workshopcalendar.form.CalendarEventListForm ;
import ui.juvenilecase.workshopcalendar.form.CalendarEventListForm.SearchEvent ;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;
import ui.supervision.administerserviceprovider.form.ServiceProviderForm;

public class DisplayViewCalendarSearchResultsAction extends JIMSBaseAction
{
	private static final String ERROR_GENERIC = "error.generic" ;
	private static final String COMMA_SPACE = ", " ;
	private static final boolean DONT_FILTER_FOR_SERVICES = false ;
	private static final boolean FILTER_FOR_SERVICES = true ;
	private static final Collection emptyCollection = new ArrayList() ;
	
	/*
	 */
	public DisplayViewCalendarSearchResultsAction()
	{
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward searchCalendarEvents( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CalendarEventListForm form = (CalendarEventListForm)aForm ;
		
		{ List emptyList = new ArrayList() ;
			form.setDayEvents( emptyList ) ;
			form.setIndividualDayEvents( emptyList ) ;
			form.setDocketEvents( emptyList ) ;
		}

		form.setListCount( 0 ) ;

		SearchEvent search = form.getSearchEvent() ;
		String searchType = search.getSearchType() ;
		String actionURL = PDConstants.BLANK ;
		
		if( searchType.equals( PDCalendarConstants.CALENDAR_SEARCH ) )
		{
			actionURL = this.findCalendarEvents( form, aRequest ) ;
		}
		else if( searchType.equals( PDCalendarConstants.DOCKET_SEARCH ) )
		{
			actionURL = this.findDocketEvents( form, aRequest ) ;
		}
		else if( searchType.equals( PDCalendarConstants.JUVENILE_SEARCH ) )
		{
			actionURL = this.findJuvenileEvents( form, aRequest ) ;
		}
		else if( searchType.equals( PDCalendarConstants.INSTRUCTOR_SEARCH) )
		{
			actionURL = this.searchEventsForInstructors( form, aRequest ) ;
		}
		else if( searchType.equals( PDCalendarConstants.FUTURE_EVENTS_JUVENILE ) )
		{
			actionURL = this.findCalendarEvents( form, aRequest ) ;
			form.clearCancelEvents() ;
			UIJuvenileHelper.populateJuvenileProfileHeaderForm( aRequest, search.getJuvenileNum() ) ;
			getNonCancelledAndNonCalledJuvenileEvents( form, DONT_FILTER_FOR_SERVICES ) ;
			if( actionURL.equals( UIConstants.LIST_SUCCESS ) )
			{
				actionURL = PDCalendarConstants.FUTURE_JUV_SUCCESS ;
			}
		}
		else if( searchType.equals( PDCalendarConstants.FUTURE_EVENTS_SVC_PROVIDER ) )
		{
			form.setJuvenileNum(null);
			search.setJuvenileNum(null);
			form.setEventType(null);
			search.setEventTypeId(null);
			actionURL = this.findCalendarEvents( form, aRequest ) ;
			form.clearCancelEvents() ;
			//<KISHORE>JIMS200056905 : Service should not be required to search for future Service Provider events in View Calendar
			String [] selectedServices = ( (search == null) ? null : search.getSelectedServices()) ;
			if(selectedServices != null && selectedServices.length > 0)
			{
				getSingleNonCancelledEvents( form, FILTER_FOR_SERVICES ) ;
			}
			else
			{
				getSingleNonCancelledEvents( form, DONT_FILTER_FOR_SERVICES ) ;
			}
			
			if( actionURL.equals( UIConstants.LIST_SUCCESS ) )
			{
				actionURL = PDCalendarConstants.FUTURE_PROVIDER_SUCCESS ;
			}
		}
		else if( searchType.equals( UIConstants.EMPTY_STRING ) )
		{ // somehow, the dropdown got reset to Please Select
			ErrorResponseEvent ere = new ErrorResponseEvent();
			ere.setMessage( "Please select a Search Type (Search By)" );
			saveActionErrors( aRequest , ere) ;
			return( aMapping.findForward( UIConstants.SEARCH_SUCCESS ) );
		}

		// tell the form if we have search results or not
		if( UIConstants.SEARCH_NORESULTS.equals( actionURL ) )
		{
			search.setHaveSearchResults( false ) ;
			ActionErrors errors = new ActionErrors() ;
			errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( "error.noRecords" ) ) ;
			saveErrors( aRequest, errors ) ;
			actionURL = UIConstants.SEARCH_FAILURE ;
		}
		else
		{
			search.setHaveSearchResults( true ) ;
		}

		search.setGoneToSearchResultsPage( true ) ;
		
		return aMapping.findForward( actionURL ) ;
	}

	/* checks to see if the event has a selected service.
	 */
	private boolean eventHasService( 
			CalendarEventListForm form, CalendarServiceEventResponseEvent tEvent )
	{
		boolean hasService = false ;
		String [] selectedServices = form.getSearchEvent().getSelectedServices() ;

		if( selectedServices != null )
		{
			for( int i = 0; !hasService  &&  i < selectedServices.length; i++ )
			{
				hasService = selectedServices[i].equalsIgnoreCase( tEvent.getServiceId() )  ;
			}
		}

		return( hasService ) ;
	}
	
	/*
	 *  Method to get the single events whose eventStatus is not canceled.
	 * @param form CalendarEventListForm
	 */
	private void getSingleNonCancelledEvents(CalendarEventListForm form, boolean filterForServices )
	{
		List nonCancelledEvents = new ArrayList();
		Collection<CalendarServiceEventResponseEvent> indivEventsList = form.getIndividualDayEvents();
		
		if( indivEventsList != null  &&  !indivEventsList.isEmpty() )
		{
			for( CalendarServiceEventResponseEvent tEvent : indivEventsList )
			{
				if( !tEvent.getEventStatusCode().equalsIgnoreCase("CC") &&
						(!filterForServices || eventHasService( form, tEvent )) )
				{
					boolean eventExistsInArrayList = false;
					Iterator iter = nonCancelledEvents.iterator();
					while( iter.hasNext() ) 
					{
						CalendarServiceEventResponseEvent calServRespEvent = (CalendarServiceEventResponseEvent)iter.next();						
						if (calServRespEvent.getEventId().equals(tEvent.getEventId())) 
						{
							eventExistsInArrayList = true;
							break;
						}
					}
					
					if (!eventExistsInArrayList) 
					{
						nonCancelledEvents.add( tEvent );
					}						
				}
			}  // foreach
			form.setIndividualDayEvents(nonCancelledEvents);
		}
	}

	/*
	 *  Method to get the events whose eventStatus is not canceled.
	 * @param form CalendarEventListForm
	 */
	private void getNonCancelledAndNonCalledJuvenileEvents(CalendarEventListForm form, boolean filterForServices )
	{
		List nonCancelledEvents = new ArrayList();
		Collection<CalendarServiceEventResponseEvent> indivEventsList = form.getIndividualDayEvents();
		
		if( indivEventsList != null  &&  !indivEventsList.isEmpty() )
		{
			for( CalendarServiceEventResponseEvent tEvent : indivEventsList )
			{
				if( ( (!tEvent.getEventStatusCode().equalsIgnoreCase("CC")) && 
						(!tEvent.getJuvenileAttendanceStatus().equals(PDCalendarConstants.JUV_ATTEND_STATUS_CANCELLED)) ) &&
						(!filterForServices || eventHasService( form, tEvent )) )
				{
						nonCancelledEvents.add( tEvent );
				}
			}
			form.setIndividualDayEvents(nonCancelledEvents);
		}
	}

	/*
	 * @param form
	 * @param aRequest
	 * @return
	 */
	protected String findCalendarEvents( CalendarEventListForm form, 
			HttpServletRequest aRequest )
	{
		GetViewCalendarEventsEvent vce = (GetViewCalendarEventsEvent)
				EventFactory.getInstance( ServiceEventControllerServiceNames.GETVIEWCALENDAREVENTS ) ;

		SearchEvent search = form.getSearchEvent() ;

		vce.setCaseLoadManager( form.isCaseloadManagerSearch() ) ;
		vce.setOfficerId( form.getOfficerId() ) ;
		vce.setOfficerFirstName( search.getOfficerFirstName() ) ;
		vce.setOfficerMiddleName( search.getOfficerMiddleName() ) ;
		vce.setOfficerLastName( search.getOfficerLastName() ) ;
		
		vce.setJuvenileNum( 
				(form.getJuvenileNum() == null) ? search.getJuvenileNum() : form.getJuvenileNum() ) ;

		String startDateStr = search.getStartDateStr() ;
		Calendar cal = Calendar.getInstance() ;

		if( notNullNotEmptyString( startDateStr ) )
		{
			cal.setTime( DateUtil.stringToDate( startDateStr, UIConstants.DATE_FMT_1 ) ) ;
			cal.set( Calendar.HOUR_OF_DAY, 0 ) ;
			cal.set( Calendar.MINUTE, 0 ) ;
			vce.setEventStartDate( cal.getTime() ) ;
		}

		String endDateStr = search.getEndDateStr() ;
		if( notNullNotEmptyString( endDateStr ) )
		{
			cal.setTime( DateUtil.stringToDate( endDateStr, UIConstants.DATE_FMT_1 ) ) ;
			cal.set( Calendar.HOUR_OF_DAY, 23 ) ;
			cal.set( Calendar.MINUTE, 59 ) ;
			vce.setEventEndDate( cal.getTime() ) ;
		}
		else
		{ /* ER 49499: if the user leaves the "To" date empty, then make it the same
			 * as the "From" date (but end of day hour/minute) */
			cal.setTime( DateUtil.stringToDate( startDateStr, UIConstants.DATE_FMT_1 ) ) ;
			cal.set( Calendar.HOUR_OF_DAY, 23 ) ;
			cal.set( Calendar.MINUTE, 59 ) ;
			vce.setEventEndDate( cal.getTime() ) ;
		}

		vce.setServiceProviderName( search.getServiceProviderName() ) ;

		vce.setJuvenileFirstName( search.getJuvenileFirstName() ) ;
		vce.setJuvenileLastName( search.getJuvenileLastName() ) ;
		vce.setJuvenileMiddleName( search.getJuvenileMiddleName() ) ;

		vce.setJuvUnitCd( search.getJuvLocUnitId() ) ;
		vce.setEventStatusCd( search.getEventStatusCd() ) ;
		vce.setEventTypeId( search.getEventTypeId() ) ;

		CompositeResponse response = MessageUtil.postRequest( vce ) ;

		ErrorResponseEvent errResp = (ErrorResponseEvent)
				MessageUtil.filterComposite( response, ErrorResponseEvent.class ) ;
		List indivEvents = new ArrayList() ;
		form.setIndividualDayEvents( indivEvents );
		if( errResp == null )
		{
			if( !response.hasResponses() )
			{ // short-circuiting the function to return no search results
				return( UIConstants.SEARCH_NORESULTS  ) ;
			}

			List responses = MessageUtil.compositeToList( response, CalendarServiceEventResponseEvent.class ) ;
			form.setDayEvents( responses ) ;
			getIndividualDayEvents( responses, indivEvents ) ;		
		}
		else
		{
			return saveActionErrors(aRequest, errResp);
		}

		return UIConstants.LIST_SUCCESS ;
	}

	/*
	 * @param responses
	 * @param indivEvents
	 * @return
	 */
	public static void getIndividualDayEvents( Collection responses, List indivEvents )
	{
		if( responses == null )
		{
			return ;
		}
		
		Collection<CalendarServiceEventResponseEvent> respList = responses ;
		for( CalendarServiceEventResponseEvent myRespEvt : respList )
		{
			List contexts = myRespEvt.getAssociatedContexts() ;

			JuvenileCasefileResponseEvent casefileContext = null ;
			int contextLen = contexts.size() ;
 			for( int i = 0; i < contextLen; i++ )
			{
				Object obj = contexts.get( i ) ;
				if( obj instanceof JuvenileCasefileResponseEvent )
				{
					casefileContext = (JuvenileCasefileResponseEvent)obj ;
					break ;
				}
			} // for

			CalendarServiceEventResponseEvent myNewRespEvt = new CalendarServiceEventResponseEvent() ;
			
			indivEvents.add( myNewRespEvt ) ;
			
			myNewRespEvt.setJuvenileAttendanceStatus(myRespEvt.getJuvenileAttendanceStatus());
			myNewRespEvt.setEventDate( myRespEvt.getEventDate() ) ;
			myNewRespEvt.setEventId( myRespEvt.getEventId() ) ;
			myNewRespEvt.setProbationOfficerId(myRespEvt.getProbationOfficerId());
			myNewRespEvt.setEventTypeCode( myRespEvt.getEventTypeCode() ) ;
			myNewRespEvt.setFamilyLocation( myRespEvt.getFamilyLocation() ) ;
			myNewRespEvt.setSchoolName( myRespEvt.getSchoolName() ) ;
			myNewRespEvt.setSchoolDistrictName( myRespEvt.getSchoolDistrictName() ) ;
			myNewRespEvt.setJuvUnitCd( myRespEvt.getJuvUnitCd() ) ;
			myNewRespEvt.setServiceLocationName( myRespEvt.getServiceLocationName() ) ;
			myNewRespEvt.setEventType( myRespEvt.getEventType() ) ;
			myNewRespEvt.setEventTypeCategory( myRespEvt.getEventTypeCategory() ) ;
			myNewRespEvt.setEventTypeCode( myRespEvt.getEventTypeCode() ) ;
			myNewRespEvt.setServiceId( myRespEvt.getServiceId() ) ;
			myNewRespEvt.setServiceName( myRespEvt.getServiceName() ) ;
			myNewRespEvt.setServiceProviderName( myRespEvt.getServiceProviderName() ) ;
			myNewRespEvt.setServiceProviderId( myRespEvt.getServiceProviderId() ) ;
			myNewRespEvt.setInstructorId( myRespEvt.getInstructorId() ) ;
			myNewRespEvt.setInstructorName( myRespEvt.getInstructorName() ) ;
			myNewRespEvt.setProgramId( myRespEvt.getProgramId() ) ;
			myNewRespEvt.setEventStatusCode( myRespEvt.getEventStatusCode() ) ;
			myNewRespEvt.setEventStatus(myRespEvt.getEventStatus());
			myNewRespEvt.setCalendarAssociatedCasefile( false ) ;
			myNewRespEvt.setProgramName( myRespEvt.getProgramName() ) ;
		
			if( nullOrEmptyString( myRespEvt.getJuvenileLastName()) ) 
			{
				if(myRespEvt.getJuvenileNum()!=null)
				{
					if (!myRespEvt.getJuvenileNum().equalsIgnoreCase(PDCalendarConstants.INVALID_CONTEXT_ID)) 
					{
						SearchJuvenileProfilesEvent searchEvent = (SearchJuvenileProfilesEvent) 
								EventFactory.getInstance(JuvenileControllerServiceNames.SEARCHJUVENILEPROFILES);
	
						searchEvent.setJuvenileNum(myRespEvt.getJuvenileNum());
						// empty string: dont include alias records  
						searchEvent.setSearchType( UIConstants.EMPTY_STRING ) ; 

						CompositeResponse response = MessageUtil.postRequest(searchEvent);
						JuvenileProfileDetailResponseEvent aRespEvent = (JuvenileProfileDetailResponseEvent)MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);
						
						if( aRespEvent != null )
						{
							myRespEvt.setJuvenileLastName( aRespEvent.getLastName() ) ;
							myRespEvt.setJuvenileFirstName( aRespEvent.getFirstName() ) ;
							myRespEvt.setJuvenileMiddleName( aRespEvent.getMiddleName() ) ;
						}
						else
						{
							myRespEvt.setJuvenileLastName( UIConstants.EMPTY_STRING ) ;
							myRespEvt.setJuvenileFirstName( UIConstants.EMPTY_STRING ) ;
							myRespEvt.setJuvenileMiddleName( UIConstants.EMPTY_STRING ) ;
						}
				
					}
				}
			}
		
			myNewRespEvt.setJuvenileLastName( myRespEvt.getJuvenileLastName() ) ;
			myNewRespEvt.setJuvenileFirstName( myRespEvt.getJuvenileFirstName() ) ;
			myNewRespEvt.setJuvenileMiddleName( myRespEvt.getJuvenileMiddleName() ) ;

			if( nullOrEmptyString( myRespEvt.getJuvenileFullName() ) )
			{	
				Name myName = new Name( myRespEvt.getJuvenileFirstName(), myRespEvt.getJuvenileMiddleName(), myRespEvt.getJuvenileLastName() ) ;
				myRespEvt.setJuvenileFullName( myName.getFormattedName() ) ;
			}
			myNewRespEvt.setJuvenileFullName( myRespEvt.getJuvenileFullName() ) ;
		
			if( notNullAndEquals( myNewRespEvt.getEventTypeCode(), UIConstants.HOME_VISIT ) || 
				notNullAndEquals( myNewRespEvt.getEventTypeCode(), UIConstants.HOME_DIAGNOSTIC_VISIT ))
			{
				myNewRespEvt.setFinalLocation( myRespEvt.getFamilyLocation() ) ;
			}
			else if(notNullAndEquals( myNewRespEvt.getEventTypeCode(), UIConstants.JOB_VISIT ) )
			{
				myNewRespEvt.setLocation( myRespEvt.getLocation() ) ;
			}
			else if( notNullAndEquals( myNewRespEvt.getEventTypeCode(), UIConstants.SCHOOL_VISIT ) )
			{
				myNewRespEvt.setFinalLocation( myRespEvt.getSchoolName() + COMMA_SPACE + myRespEvt.getSchoolDistrictName() ) ;
			}
			else if( notNullAndEquals( myNewRespEvt.getEventTypeCode(), UIConstants.PLACEMENT_VISIT ) )
			{
				myNewRespEvt.setFinalLocation( myRespEvt.getFacilityCd() ) ;
			}
			else if( nullOrEmptyString( myNewRespEvt.getJuvUnitCd() ) )
			{
				myNewRespEvt.setFinalLocation( myRespEvt.getServiceLocationName() ) ;
			}
			else
			{
				myNewRespEvt.setFinalLocation( myRespEvt.getJuvUnitCd() ) ;
			}

			if( casefileContext != null )
			{	
				myNewRespEvt.setJuvenileNum( casefileContext.getJuvenileNum() ) ;

				if( casefileContext.isCalendarAssociatedCasefile() )
				{
					myNewRespEvt.setCalendarAssociatedCasefile( casefileContext.isCalendarAssociatedCasefile() ) ;
					myNewRespEvt.setJuvenileFirstName( casefileContext.getJuvenileFirstName() ) ;
					myNewRespEvt.setJuvenileMiddleName( casefileContext.getJuvenileMiddleName() ) ;
					myNewRespEvt.setJuvenileLastName( casefileContext.getJuvenileLastName() ) ;

					Name myName = new Name( casefileContext.getJuvenileFirstName(), 
							casefileContext.getJuvenileMiddleName(), casefileContext.getJuvenileLastName() ) ;
					myNewRespEvt.setJuvenileFullName( myName.getFormattedName() ) ;
					myNewRespEvt.setJuvenileNum( casefileContext.getJuvenileNum() ) ;
				}
			}// if (casefileContext != null)
			else
			{
				myNewRespEvt.setJuvenileNum( myRespEvt.getJuvenileNum() ) ;
			}
		} // foreach
	}

	/*
	 * @param str
	 * @return
	 */
	private static boolean nullOrEmptyString( String str )
	{
		return( str == null  ||  str.trim().length() == 0 ) ;
	}

	/*
	 * @param str
	 * @return
	 */
	private static boolean notNullAndEquals( String str, String str2 )
	{
		return( (str != null && str2 != null) && str.equals(str2) ) ;
	}

	/*
	 * @param str
	 * @return
	 */
	private static boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  str.trim().length() > 0 ) ;
	}
	
	/* Sets Docket Responses on Form and forwards to correct JSP
	 * @param form CalendarEventListForm
	 * @param aRequest HttpServletRequest
	 * @return ActionForward
	 */
	protected String findDocketEvents( CalendarEventListForm form, HttpServletRequest aRequest )
	{
		SearchEvent search = form.getSearchEvent();
		String actionForward;		
		
		if( notNullNotEmptyString( search.getJpoUserId() ) )
		{
			actionForward = findDocketEventsByJPOSearch(form, aRequest, search);
		} 
		else 
		{
			actionForward = findDocketEventsByNonJPOSearch(form, aRequest, search);
		}
				
		return actionForward ;
	}
	
	/**
	 * Performs a Docket Event Search without considering JPO ID
	 * @param form CalendarEventListForm
	 * @param aRequest HttpServletRequest
	 * @param ser SearchEvent
	 * @return String
	 */
	private String findDocketEventsByNonJPOSearch(CalendarEventListForm form, 
			HttpServletRequest aRequest, SearchEvent ser) 
	{
		GetViewCalendarDocketEventsEvent gvev = populateGetViewCalendarDocketEventsEvent(form, ser); 
		CompositeResponse response = MessageUtil.postRequest( gvev ) ;
		gvev = null ;
		
		ErrorResponseEvent errResp = (ErrorResponseEvent)
				MessageUtil.filterComposite( response, ErrorResponseEvent.class ) ;
		if( errResp == null )
		{
			if( !response.hasResponses() )
			{ // short-circuiting the function to return no search results
				return( UIConstants.SEARCH_NORESULTS  ) ;
			}

			List docketResponses = MessageUtil.compositeToList( response, DocketEventResponseEvent.class ) ;
			if( docketResponses != null )
			{
				form.setDocketEvents( docketResponses ) ;
			}
		}
		else
		{
			return saveActionErrors(aRequest, errResp);
		}
		
		return UIConstants.LIST_SUCCESS ;	
	}
	
	/**
	 * Performs a Docket Event Search considering JPO ID
	 * @param form CalendarEventListForm
	 * @param aRequest HttpServletRequest
	 * @param ser SearchEvent
	 * @return String
	 */
	private String findDocketEventsByJPOSearch(CalendarEventListForm form, 
			HttpServletRequest aRequest, SearchEvent search) 
	{
		GetOfficerAssociatedJuvenileCasefilesEvent oajc = 
				populateGetOfficerAssociatedJuvenileCasefilesEvent(form, search);
		CompositeResponse response = MessageUtil.postRequest( oajc ) ; //Get Juveniles associated with JPO
		oajc = null ;
		
		ErrorResponseEvent errResp = (ErrorResponseEvent)
				MessageUtil.filterComposite( response, ErrorResponseEvent.class ) ; 
		
		if( errResp == null ) 
		{
			if( !response.hasResponses() )
			{ // short-circuiting the function to return no search results
				return( UIConstants.SEARCH_NORESULTS  ) ;
			}				
			
			List allJuvCasefileResponses = MessageUtil.compositeToList( response, JuvenileCasefileResponseEvent.class ) ;
			if( allJuvCasefileResponses != null )
			{
				HashMap juvCasefilesIndex = new HashMap();

				{ List indexJuvCaseFileResponses = new ArrayList();
					int pageIndex = 1, listSize = allJuvCasefileResponses.size();
					for( int i = 0; i < listSize; i++ )
					{
						JuvenileCasefileResponseEvent juvCaseFileResp = (JuvenileCasefileResponseEvent)allJuvCasefileResponses.get(i);
						indexJuvCaseFileResponses.add(juvCaseFileResp);
											
						if( (indexJuvCaseFileResponses.size() == 30) || ( (i + 1) == listSize ) ) 
						{ 
							juvCasefilesIndex.put(pageIndex++, indexJuvCaseFileResponses);
							indexJuvCaseFileResponses = new ArrayList();
						}					
					}
				}
				
				List mainDocketReponses = new ArrayList();
				int listSize = juvCasefilesIndex.size() ;
				for( int i = 0; i < listSize; i++ ) 
				{
					List juvCasefileResponses = (List)juvCasefilesIndex.get(i + 1);
					GetViewCalendarDocketEventsByJuvenilesEvent gvevByJuveniles = 
							populateGetViewCalendarDocketEventsByJuvenilesEvent(form, search, juvCasefileResponses);

					response = MessageUtil.postRequest( gvevByJuveniles ) ;
					errResp = (ErrorResponseEvent)MessageUtil.filterComposite( response, ErrorResponseEvent.class ) ;
					
					if( errResp == null )
					{
						List docketResponses = MessageUtil.compositeToList( response, DocketEventResponseEvent.class ) ;
						for( Iterator j = docketResponses.iterator(); j.hasNext(); /*empty*/ ) 
						{
							mainDocketReponses.add( j.next() );
						}
						
						if( mainDocketReponses.size() >= PDConstants.SEARCH_LIMIT ) 
						{
							ErrorResponseEvent ere = new ErrorResponseEvent();
							ere.setMessage("Number of Docket Events matching this criteria is greater than" +PDConstants.SEARCH_LIMIT );
							return saveActionErrors(aRequest, ere);
						}
					}
					else 
					{
						return saveActionErrors(aRequest, errResp);
					}					
				}
				
				if( mainDocketReponses.size() > 0 ) 
				{
					form.setDocketEvents( mainDocketReponses ) ;
				}			
			}
		} // if errResp == null
		else
		{
			return saveActionErrors(aRequest, errResp);
		}
		
		return UIConstants.LIST_SUCCESS ;
	}
	
	/**
	 * Save errors
	 * @param aRequest HttpServletRequest
	 * @param errResp ErrorResponseEvent
	 * @return
	 */
	private String saveActionErrors(HttpServletRequest aRequest, ErrorResponseEvent errResp) 
	{
		ActionErrors errors = new ActionErrors() ;
		errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( ERROR_GENERIC, errResp.getMessage() ) ) ;
		saveErrors( aRequest, errors ) ;

		return UIConstants.SEARCH_SUCCESS ;
	}
	
	/*
	 * Populates a GetViewCalendarDocketEventsEvent based on CalendarEventListForm values
	 * @param form CalendarEventListForm
	 * @param ser SearchEvent
	 * @return
	 */
	private GetOfficerAssociatedJuvenileCasefilesEvent populateGetOfficerAssociatedJuvenileCasefilesEvent(CalendarEventListForm form, SearchEvent ser) 
	{
		GetOfficerAssociatedJuvenileCasefilesEvent gvev = (GetOfficerAssociatedJuvenileCasefilesEvent)
				EventFactory.getInstance( ServiceEventControllerServiceNames.GETOFFICERASSOCIATEDJUVENILECASEFILES ) ;
		
		gvev.setJuvenileFirstName( ser.getSearchFirstName() ) ;
		gvev.setJuvenileLastName( ser.getSearchLastName() ) ;
		gvev.setJuvenileMiddleName( ser.getSearchMiddleName() ) ;
		gvev.setJpoUserId( ser.getJpoUserId() ) ;
				
		return gvev;
	}
	
	/*
	 * Populates a GetViewCalendarDocketEventsEvent based on CalendarEventListForm values
	 * @param form CalendarEventListForm
	 * @param ser SearchEvent
	 * @return
	 */
	private GetViewCalendarDocketEventsByJuvenilesEvent populateGetViewCalendarDocketEventsByJuvenilesEvent(CalendarEventListForm form, SearchEvent ser, List juvCasefileResponses) 
	{
		GetViewCalendarDocketEventsByJuvenilesEvent gvev = (GetViewCalendarDocketEventsByJuvenilesEvent)
				EventFactory.getInstance( ServiceEventControllerServiceNames.GETVIEWCALENDARDOCKETEVENTSBYJUVENILES ) ;
		
		gvev.setJuvCasefileResponses(juvCasefileResponses) ;
		gvev.setEventTypeId( ser.getEventTypeId() ) ;

		String dateStr = ser.getStartDateStr() ;
		if( notNullNotEmptyString( dateStr ) )
		{
			gvev.setStartDate( DateUtil.stringToDate( dateStr, UIConstants.DATE_FMT_1 ) ) ;
		}

		dateStr = ser.getEndDateStr() ;
		if( notNullNotEmptyString( dateStr ) )
		{
			gvev.setEndDate( DateUtil.stringToDate( dateStr, UIConstants.DATE_FMT_1 ) ) ;
		}
		
		return gvev;
	}

	/*
	 * Populates a GetViewCalendarDocketEventsEvent based on CalendarEventListForm values
	 * @param form CalendarEventListForm
	 * @param ser SearchEvent
	 * @return
	 */
	private GetViewCalendarDocketEventsEvent populateGetViewCalendarDocketEventsEvent(
			CalendarEventListForm form, SearchEvent search) 
	{				
		GetViewCalendarDocketEventsEvent gvev = (GetViewCalendarDocketEventsEvent)
				EventFactory.getInstance( ServiceEventControllerServiceNames.GETVIEWCALENDARDOCKETEVENTS ) ;
		
		gvev.setJuvenileFirstName( search.getSearchFirstName() ) ;
		gvev.setJuvenileLastName( search.getSearchLastName() ) ;
		gvev.setJuvenileMiddleName( search.getSearchMiddleName() ) ;
		gvev.setJuvenileNum( search.getJuvenileNum() ) ;
		gvev.setJpoUserId( search.getJpoUserId() ) ;
		gvev.setEventTypeId( search.getEventTypeId() ) ;

		if( nullOrEmptyString( search.getJuvenileNum() ) )
		{
			gvev.setJuvenileNum( form.getJuvenileNum() ) ;
		}
		else
		{
			gvev.setJuvenileNum( search.getJuvenileNum() ) ;
		}

		String dateStr = search.getStartDateStr() ;
		if( notNullNotEmptyString( dateStr ) )
		{
			gvev.setStartDate( DateUtil.stringToDate( dateStr, UIConstants.DATE_FMT_1 ) ) ;
		}

		dateStr = search.getEndDateStr() ;
		if( notNullNotEmptyString( dateStr ) )
		{
			gvev.setEndDate( DateUtil.stringToDate( dateStr, UIConstants.DATE_FMT_1 ) ) ;
		}
		
		return gvev;
	}

	/*
	 * @param form
	 * @param aRequest
	 * @return
	 */
	protected String findJuvenileEvents( CalendarEventListForm form, 
			HttpServletRequest aRequest )
	{
		String actionUrl = this.findCalendarEvents( form, aRequest ) ;

		if( UIConstants.LIST_SUCCESS.equals( actionUrl ) )
		{
			this.findDocketEvents( form, aRequest ) ;

			String juvenileNum = form.getSearchEvent().getJuvenileNum() ;
			Collection<CalendarServiceEventResponseEvent> dayEvents = form.getDayEvents() ;
			if( dayEvents != null )
			{
				for( CalendarServiceEventResponseEvent resp : dayEvents )
				{
					Collection<JuvenileCasefileResponseEvent> associatedContexts = resp.getAssociatedContexts() ;
					if( associatedContexts != null )
					{
						for( JuvenileCasefileResponseEvent juvResp : associatedContexts )
						{
							if( !juvResp.getJuvenileNum().equals( juvenileNum ) )
							{
								juvResp.setCalendarAssociatedCasefile( false ) ;
							}
						}
					}
				}// outer for
			}
		} 
		else 
		{
			this.findDocketEvents( form, aRequest ) ;
		}

		return UIConstants.LIST_SUCCESS ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward changeEventType( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CalendarEventListForm form = (CalendarEventListForm)aForm ;
		SearchEvent searchEvent = form.getSearchEvent() ;
		
		form.clear() ;
		if( (form.getCalendarType() != null) && 
				(!form.getCalendarType().equals(PDCalendarConstants.CALENDAR_TYPE_PROVIDER)) )
		{
			searchEvent.clear() ;
		} 
		else if ((form.getCalendarType() != null) && 
				(form.getCalendarType().equals(PDCalendarConstants.CALENDAR_TYPE_PROVIDER)) ) 
		{
			searchEvent.clearSections();
		}

		List<ServiceTypeCdResponseEvent> serviceTypeList = searchEvent.getServiceTypePermList() ;
		String searchType = searchEvent.getSearchType() ;

		
		if( PDCalendarConstants.JUVENILE_SEARCH.equals( searchType ) )
		{ // alias for "all events" drop-down option
			searchEvent.setServiceTypeList( serviceTypeList ) ;

			//display required sections of the form
			searchEvent.setShowJuvNumSection( true ) ;
			searchEvent.setShowSPSection( true ) ;
			searchEvent.setShowStatusSection( true ) ;
			searchEvent.setShowDateSection( true ) ;
			searchEvent.setShowButtonSection( true ) ;
		}
		else if( PDCalendarConstants.CALENDAR_SEARCH.equals( searchType ) )
		{
			ArrayList eventTypeList = new ArrayList() ;
			
			for( ServiceTypeCdResponseEvent eventType : serviceTypeList )
			{
				if( !eventType.getCategory().equals( UIConstants.DOCKET_SERVICE_TYPE ) )
				{
					eventTypeList.add( eventType ) ;
				}
			}

			searchEvent.setServiceTypeList( eventTypeList ) ;
			//display required sections of the form
			searchEvent.setShowNameSection( true ) ;
		}
		else if( PDCalendarConstants.DOCKET_SEARCH.equals( searchType ) )
		{
			ArrayList eventTypeList = new ArrayList() ;
			for( ServiceTypeCdResponseEvent eventType : serviceTypeList )
			{
				if( eventType.getCategory().equals( UIConstants.DOCKET_SERVICE_TYPE ) )
				{
					eventTypeList.add( eventType ) ;
				}
			}
			searchEvent.setServiceTypeList( eventTypeList ) ;

			//display required sections of the form
			searchEvent.setNameSearchType( "none" ) ;
			searchEvent.setDoNameSearch( false ) ;
			searchEvent.setShowJuvNameSection( true ) ;
			searchEvent.setShowEventTypeSection( true ) ;
			searchEvent.setShowJPOSection( true ) ;
			searchEvent.setShowDateSection( true ) ;
			searchEvent.setShowButtonSection( true ) ;
			form.setNameSearchResults( emptyCollection ) ;
		}
		else if( PDCalendarConstants.FUTURE_EVENTS_JUVENILE.equals( searchType ) )
		{
			ArrayList eventTypeList = new ArrayList() ;
			for( ServiceTypeCdResponseEvent eventType : serviceTypeList )
			{
				if( !eventType.getCategory().equals( UIConstants.DOCKET_SERVICE_TYPE ) )
				{
					eventTypeList.add( eventType ) ;
				}
			}
			searchEvent.setServiceTypeList( eventTypeList ) ;

			// display required sections of the form
			searchEvent.setShowJuvNumSection( true ) ;
			searchEvent.setShowEventTypeSection( true ) ;
			searchEvent.setShowDateSection( true ) ;
			searchEvent.setShowButtonSection( true ) ;
		}
		else if( PDCalendarConstants.FUTURE_EVENTS_SVC_PROVIDER.equals( searchType ) )
		{
			if( form.getCalendarType().equals( PDCalendarConstants.CALENDAR_TYPE_PROVIDER ) )
			{
				searchForSPServices( aMapping, aForm, aRequest, aResponse ) ;
				searchEvent.setShowProgramSelectionSection( true ) ;
			}
			
			// display required sections of the form
			searchEvent.setShowFutureSPSection( true ) ;
			searchEvent.setShowDateSection( true ) ;
			searchEvent.setShowButtonSection( true ) ;
		}
		else if( PDCalendarConstants.INSTRUCTOR_SEARCH.equals( searchType ) )
		{
			if( form.getCalendarType().equals( PDCalendarConstants.CALENDAR_TYPE_PROVIDER ) )
			{
				ServiceProviderResponseEvent selectedSvcProvider = new ServiceProviderResponseEvent() ;
				selectedSvcProvider.setJuvServProviderId( form.getSearchEvent().getServiceProviderId() ) ;
				form.getSearchEvent().setInstructorList(getInstructors(selectedSvcProvider.getJuvServProviderId(), selectedSvcProvider.isInHouse()));
			}
			searchEvent.setShowSPForInstructorSelectionSection( true ) ;					
			// display required sections of the form			
			searchEvent.setShowDateSection( true ) ;
			searchEvent.setShowButtonSection( true ) ;
		}

		return aMapping.findForward( UIConstants.SEARCH_SUCCESS ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward searchForName( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CalendarEventListForm form = (CalendarEventListForm)aForm ;
		SearchEvent searchEvent = form.getSearchEvent() ;

		// clear out juvenile, in case the search is for CLM or JPO
		{ Collection ns = form.getNameSearchResults() ;
			if( ns != null )
			{
				ns.clear() ;
			}
		}
		form.setJuvenileNum( null ) ;
		form.setListCount( 0 ) ;

		/*
		 * in the JSP, a "name search" can be done in either the 'docket events' or
		 * 'calendar events' calendar search type. if the form's variable
		 * 'searchType' is equal to DOCKET_SEARCH, then we are obviously in the
		 * context of a 'docket events' calendar search type. more specifically, it
		 * can be: PDCalendarConstants.CALENDAR_SEARCH [...].DOCKET_SEARCH
		 * [...].JUVENILE_SEARCH
		 */
		String searchType = searchEvent.getSearchType() ;

		if( PDCalendarConstants.DOCKET_SEARCH.equals( searchType ) )
		{ // get the name the user wants to search for
			String lastname = searchEvent.getSearchLastName() ;
			String middlename = searchEvent.getSearchMiddleName() ;
			String firstname = searchEvent.getSearchFirstName() ;

			SearchJuvenileProfilesEvent juvProfileEvent = (SearchJuvenileProfilesEvent)
          EventFactory.getInstance( JuvenileControllerServiceNames.SEARCHJUVENILEPROFILES ) ;

			juvProfileEvent.setFirstName( firstname ) ;
			juvProfileEvent.setMiddleName( middlename ) ;
			juvProfileEvent.setLastName( lastname ) ;
			// empty string means bring back only core juv records (no alias records) 
			juvProfileEvent.setSearchType( UIConstants.EMPTY_STRING ) ;  

			List results = super.postRequestListFilter( juvProfileEvent, JuvenileProfileDetailResponseEvent.class ) ;
			form.setNameSearchResults( results ) ;

			if( results.size() > 0 )
			{
				searchEvent.setShowSPSection( true ) ;
				searchEvent.setShowJPOSection( true ) ;
				searchEvent.setShowStatusSection( true ) ;
				searchEvent.setShowDateSection( true ) ;
				searchEvent.setShowButtonSection( true ) ;
				form.setListCount( results.size() ) ;
			}

			searchEvent.setDoNameSearch( false ) ;
		}
		else if( PDCalendarConstants.CALENDAR_SEARCH.equals( searchType ) )
		{ // get the name the user wants to search for
			String lname = searchEvent.getSearchLastName() ;
			String mname = searchEvent.getSearchMiddleName() ;
			String fname = searchEvent.getSearchFirstName() ;

			/* now we have to see "what type of name" we are searching for, one of:
			 * JPO, CLM, or Juvenile name
			 */
			String nameSearchType = searchEvent.getNameSearchType() ;

			if( PDCalendarConstants.JPO_NAME_SEARCH.equals( nameSearchType ) )
			{
				SearchJuvenileOfficerEvent juvOfficerEvent = (SearchJuvenileOfficerEvent)
						EventFactory.getInstance( OfficerProfileControllerServiceNames.SEARCHJUVENILEOFFICER ) ;

				juvOfficerEvent.setJpo( true ) ;
				juvOfficerEvent.setFirstName( fname ) ;
				juvOfficerEvent.setLastName( lname ) ;
				juvOfficerEvent.setMiddleName( mname ) ;

				List results = super.postRequestListFilter( juvOfficerEvent, OfficerProfileResponseEvent.class ) ;
				form.setNameSearchResults( results ) ;

				if( results.size() > 0 )
				{
					searchEvent.setShowSPSection( true ) ;
					searchEvent.setShowJPOSection( true ) ;
					searchEvent.setShowStatusSection( true ) ;
					searchEvent.setShowDateSection( true ) ;
					searchEvent.setShowButtonSection( true ) ;
					form.setListCount( results.size() ) ;
				}
			}
			else if( PDCalendarConstants.CLM_NAME_SEARCH.equals( nameSearchType ) )
			{
				SearchJuvenileOfficerEvent juvOfficerEvent = (SearchJuvenileOfficerEvent)
						EventFactory.getInstance( OfficerProfileControllerServiceNames.SEARCHJUVENILEOFFICER ) ;

				juvOfficerEvent.setCaseloadManager( true ) ;
				juvOfficerEvent.setFirstName( fname ) ;
				juvOfficerEvent.setLastName( lname ) ;
				juvOfficerEvent.setMiddleName( mname ) ;

				List results = super.postRequestListFilter( juvOfficerEvent, OfficerProfileResponseEvent.class ) ;
				form.setNameSearchResults( results ) ;

				if( results.size() > 0 )
				{
					searchEvent.setShowSPSection( true ) ;
					searchEvent.setShowJPOSection( true ) ;
					searchEvent.setShowStatusSection( true ) ;
					searchEvent.setShowDateSection( true ) ;
					searchEvent.setShowButtonSection( true ) ;
					form.setListCount( results.size() ) ;
				}
			}
			else if( PDCalendarConstants.JUVENILE_NAME_SEARCH.equals( nameSearchType ) )
			{
				SearchJuvenileProfilesEvent juvProfileEvent = (SearchJuvenileProfilesEvent)
						EventFactory.getInstance( JuvenileControllerServiceNames.SEARCHJUVENILEPROFILES ) ;

				juvProfileEvent.setFirstName( fname ) ;
				juvProfileEvent.setLastName( lname ) ;
				juvProfileEvent.setMiddleName( mname ) ;
				// empty string: dont include alias records  
				juvProfileEvent.setSearchType( UIConstants.EMPTY_STRING ) ; 

				List results = super.postRequestListFilter( juvProfileEvent, JuvenileProfileDetailResponseEvent.class ) ;
				if( results.size() > 0 )
				{
					searchEvent.setShowSPSection( true ) ;
					searchEvent.setShowJPOSection( true ) ;
					searchEvent.setShowStatusSection( true ) ;
					searchEvent.setShowDateSection( true ) ;
					searchEvent.setShowButtonSection( true ) ;
				}
				form.setListCount( results.size() ) ;
				form.setNameSearchResults( results ) ;
			}
			searchEvent.setDoNameSearch( true ) ;
		}// else if PDCalendarConstants.CALENDAR_SEARCH

		return aMapping.findForward( UIConstants.NAME_LIST_SUCCESS ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward refreshFields( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CalendarEventListForm form = (CalendarEventListForm)aForm ;
		SearchEvent searchEvent = form.getSearchEvent() ;
		
		searchEvent.clear() ;
		// empty string: dont include alias records  
		searchEvent.setSearchType( PDConstants.BLANK ) ;
		searchEvent.setNameSearchType( PDCalendarConstants.JPO_NAME_SEARCH ) ;

		return aMapping.findForward( UIConstants.SEARCH_SUCCESS ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward searchForSPServices( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CalendarEventListForm form = (CalendarEventListForm)aForm ;
		SearchEvent searchEvent = form.getSearchEvent() ;
		ServiceProviderResponseEvent selectedSvcProvider = null ;
		
		if( form.getCalendarType().equals( PDCalendarConstants.CALENDAR_TYPE_PROVIDER ) )
		{
			selectedSvcProvider = new ServiceProviderResponseEvent() ;
			selectedSvcProvider.setJuvServProviderId( form.getSearchEvent().getServiceProviderId() ) ;
		}
		else
		{
			selectedSvcProvider = getProvider( searchEvent ) ;
		}

		form.setServiceProvider( null ) ;
		
		if( selectedSvcProvider != null )
		{
			// get the service provider		
			JuvenileServiceProviderResponseEvent resp = 
				UIServiceProviderHelper.getProvider( selectedSvcProvider.getJuvServProviderId() ) ;
			if( resp != null )
			{
				ServiceProviderForm spForm = new ServiceProviderForm() ;
				UIServiceProviderHelper.fillServiceProviderDetails( spForm, resp ) ;
				
				//get the service provider's programs		
				Collection pgmList = UIServiceProviderHelper.getPrograms( spForm, selectedSvcProvider.getJuvServProviderId() ) ;
				spForm.setPrograms( UIServiceProviderHelper.sortResults( pgmList, "P" ) ) ;

				// fill the Services for each Program
				getServicesForPrograms( spForm ) ;

				// set the ServiceProviderForm to the CalendarEventListForm
				form.setServiceProvider( spForm ) ;
				
				// tell the JSP which section to show
				searchEvent.setShowProgramSelectionSection( true ) ;
			}
		}
		
		return aMapping.findForward( UIConstants.SEARCH_SUCCESS ) ;
	}

	/* find the ServiceProvider, based on the user's selection
	 *   
	 * @param searchEvent
	 * @return
	 */
	private ServiceProviderResponseEvent getProvider( SearchEvent searchEvent )
	{
		ServiceProviderResponseEvent svcProvider = null ;
		
		// find the service provider by name
		List<ServiceProviderResponseEvent> spList = searchEvent.getServiceProviderList() ;
		if( spList != null )
		{
			for( ServiceProviderResponseEvent sp : spList )
			{
				if( sp.getJuvServProviderName().equals( searchEvent.getServiceProviderName() ) )
				{
					svcProvider = sp ;
					break ;
				}
			}
		}
		
		return( svcProvider ) ;
	}
	
	/* for each Program in the collection, 
	 * get and add its collection of Services
	 *  
	 * @param spForm
	 */
	private void getServicesForPrograms( ServiceProviderForm spForm )
	{
		Collection<ProviderProgramResponseEvent> programList = spForm.getPrograms();
		if( programList != null )
		{
			for( ProviderProgramResponseEvent aProgram : programList )
			{
				if( aProgram.getProgramName().equalsIgnoreCase( aProgram.getProgramName() ) )
				{
					Collection tSvcList = UIServiceProviderHelper.getServicesByProgram( aProgram.getProviderProgramId() ) ;
					Collection<ServiceResponseEvent> servicesList = UIServiceProviderHelper.sortResults( tSvcList, "S" ) ;
					aProgram.setServices( servicesList ) ;						
				}
			} // for
		}
	}
	
	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward searchForInstructors( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CalendarEventListForm form = (CalendarEventListForm)aForm ;
		SearchEvent searchEvent = form.getSearchEvent() ;
		searchEvent.setInstructorList(new ArrayList());
		ServiceProviderResponseEvent selectedSvcProvider = null ;
		
		if( form.getCalendarType().equals( PDCalendarConstants.CALENDAR_TYPE_PROVIDER ) )
		{
			selectedSvcProvider = new ServiceProviderResponseEvent() ;
			selectedSvcProvider.setJuvServProviderId( form.getSearchEvent().getServiceProviderId() ) ;
		}
		else
		{
			selectedSvcProvider = getProvider( searchEvent ) ;
		}
		form.setServiceProvider( null ) ;
		searchEvent.setShowInstructorSelectionSection(true);
		if( selectedSvcProvider != null )
		{			
			form.getSearchEvent().setInstructorList(getInstructors(
						selectedSvcProvider.getJuvServProviderId(), selectedSvcProvider.isInHouse()));			
		}
		return aMapping.findForward( UIConstants.SEARCH_SUCCESS ) ;
		
	}
	
	/*
	 * 
	 */
	private List getInstructors(String serviceProviderId, boolean isInHouse)
	{	    

	  /*  List<ServiceProviderContactResponseEvent> instructors = new ArrayList<ServiceProviderContactResponseEvent>();
	    Collection<ServiceProviderContactResponseEvent> contacts =UIServiceProviderHelper.getContactsFromSecurityManager( UIUtil.getCurrentUserID() );
	    
	    Iterator iter = contacts.iterator();
	    
	    while( iter.hasNext()){
		
		ServiceProviderContactResponseEvent contact = (ServiceProviderContactResponseEvent) iter.next();
		if( contact.isInactivated() == false ){
		    
		    instructors.add(contact);
		    
		}
		
	    }	*/   
	    //task 163965 for bug 163750 - above direct function from SM do not load the id property which is needed for the search. so update jims2 table with SM then refresh the dropdown.
	    	ServiceProviderForm sp = new ServiceProviderForm();
	  	sp.setProviderId(serviceProviderId);
	  	JuvenileServiceProviderResponseEvent resp = UIServiceProviderHelper.getProvider(serviceProviderId);
	  	if(resp != null)
		{
	  		UIServiceProviderHelper.fillServiceProviderDetails(sp, resp);	  				
			Collection<ServiceProviderContactResponseEvent> contacts =UIServiceProviderHelper.getContactsFromSecurityManager(resp.getAdminUserProfileId()); 
			UIServiceProviderHelper.updateContactInJims2FromSM(contacts,sp);
		}
	  	GetServiceProviderInstructorsEvent event = new GetServiceProviderInstructorsEvent() ;
		event.setInHouse( isInHouse) ;
		event.setServiceProviderId( serviceProviderId ) ;

		List instructors = MessageUtil.postRequestListFilter( event, ServiceProviderContactResponseEvent.class ) ;
		Collections.sort(instructors);
		////
	    
           return instructors;
	}
	
	
	private String searchEventsForInstructors( CalendarEventListForm form, 
			HttpServletRequest aRequest )
	{		
		SearchEvent event = form.getSearchEvent();
		Iterator iter = event.getInstructorList().iterator();
		while(iter.hasNext())
		{
			ServiceProviderContactResponseEvent resp = (ServiceProviderContactResponseEvent) iter.next();
			if(resp.getContactName().getFormattedName().equalsIgnoreCase(event.getInstructorName()))
			{
				Collection attributes = new ArrayList();
				ServiceInstructorAttribute attr = new ServiceInstructorAttribute();				
				//attr.setIntstructorId(Integer.parseInt(resp.getJuvServProviderProfileId().trim()));
				// bug fix for 109431
				attr.setIntstructorId(Integer.parseInt(resp.getId().trim()));//id assigned was different 
				attributes.add(attr);

				GetCalendarServiceEventsEvent gce = (GetCalendarServiceEventsEvent) 
						EventFactory.getInstance(ServiceEventControllerServiceNames.GETCALENDARSERVICEEVENTS);
				
				gce.setRetriever(CalendarRetrieverFactory.getRetrieverName(CalendarRetrieverFactory.ACTIVEEVENTS_RETRIEVER));
				gce.setStartDatetime(DateUtil.stringToDate(event.getStartDateStr(), DateUtil.DATE_FMT_1));
				gce.setEndDatetime(DateUtil.stringToDate(event.getEndDateStr(), DateUtil.DATE_FMT_1));
				gce.setCalendarAttributes(attributes);
				
				List events = MessageUtil.postRequestListFilter(gce, CalendarServiceEventResponseEvent.class);
				form.setDayEvents( events ) ;
				List indivEvents = new ArrayList() ;
				form.setIndividualDayEvents( indivEvents ) ;
				getIndividualDayEvents( events, indivEvents ) ;		
			}
		}
		
		return ("instructorListSuccess") ;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping( Map keyMap )
	{
		keyMap.put( "button.submit", "searchCalendarEvents" ) ;
		keyMap.put( "button.changeEventType", "changeEventType" ) ;
		keyMap.put( "button.serviceChange", "searchForSPServices" ) ;
		keyMap.put( "button.instructor", "searchForInstructors" ) ;
		keyMap.put( "button.refresh", "refreshFields" ) ;
		keyMap.put( "button.find", "searchForName" ) ;
		keyMap.put( "button.findName", "searchForName" ) ;
		keyMap.put( "button.search", "searchEventsForInstructors" ) ;
	}
}
