//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\schedulecalendarevent\\action\\DisplayCalendarAction.java

package ui.juvenilecase.schedulecalendarevent.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.GetProviderProgramsEvent;
import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import messaging.codetable.GetServiceTypeCdEvent;
import messaging.codetable.criminal.reply.ServiceTypeCdResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.juvenilecase.UIJuvenileCasefileStatusHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.workshopcalendar.form.ScheduleNewEventForm;
import ui.security.SecurityUIHelper;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;

public class DisplayCalendarAction extends JIMSBaseAction
{
	/**
	 * @roseuid 4576E75D02E4
	 */
	public DisplayCalendarAction()
	{
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49A50033
	 */
	public ActionForward displayCalendar( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ScheduleNewEventForm form = (ScheduleNewEventForm)aForm ;
		form.setAllowUpdates(UIJuvenileCasefileStatusHelper.casefileStatusClosed(aRequest));
		
		JuvenileCasefileForm juvenileCasefileForm = (JuvenileCasefileForm)aRequest.getSession().getAttribute( "juvenileCasefileForm" ) ;
		
	
		if( juvenileCasefileForm != null )
		{
			form.setCaseFileId( juvenileCasefileForm.getSupervisionNum() ) ;
			form.setJuvenileNum( juvenileCasefileForm.getJuvenileNum() ) ;
			form.setJuvenileName( juvenileCasefileForm.getJuvenileFullName() ) ;
			form.setOfficerId( juvenileCasefileForm.getProbationOfficerId() ) ;
			form.setOfficerName( juvenileCasefileForm.getProbationOfficerName().toString() ) ;
			//bug 26711
			form.setOfficerFirstName(juvenileCasefileForm.getProbationOfficerFirstName());
			form.setOfficerLastName(juvenileCasefileForm.getProbationOfficerLastName());
			
			if(juvenileCasefileForm.getJuvenileName()!=null){
				form.setJuvenileFirstName(juvenileCasefileForm.getJuvenileName().getFirstName());
				form.setJuvenileLastName(juvenileCasefileForm.getJuvenileName().getLastName());
			}
			//bug 26711
			form.setOfficerId( juvenileCasefileForm.getProbationOfficerId()) ;
			form.setAllowPrePetitionUpdates(UIJuvenileCasefileStatusHelper.supervisionCategoryCheck(juvenileCasefileForm.getSupervisionTypeId(), form.isAllowUpdates() ));
		}

		return aMapping.findForward( UIConstants.SUCCESS ) ;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49A50033
	 */
	public ActionForward scheduleNewEventSPOnly( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ScheduleNewEventForm newEventForm = (ScheduleNewEventForm)aForm ;
		boolean spOnlyEvents = true;
				
		newEventForm.clear() ;
		newEventForm.getCurrentService().getCurrentEvent().setWorkDays( CodeHelper.getWorkDayCodes() ) ;
		
		GetServiceTypeCdEvent serviceTypeEvent = (GetServiceTypeCdEvent)
				EventFactory.getInstance( CodeTableControllerServiceNames.GETSERVICETYPECD ) ;

		serviceTypeEvent.setCodeTableName( "SERVICE_TYPE" ) ;
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( serviceTypeEvent ) ;
		
		// Getting PD Response Event
		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply() ;
		Map dataMap = MessageUtil.groupByTopic( compositeResponse ) ;
		MessageUtil.processReturnException( dataMap ) ;
		
		Collection<ServiceTypeCdResponseEvent> serviceTypeResponses = 
				MessageUtil.compositeToCollection( compositeResponse, ServiceTypeCdResponseEvent.class ) ;
		Collections.sort( (List)serviceTypeResponses ) ;

		ArrayList finalTypeList = new ArrayList() ;
		finalTypeList.ensureCapacity( serviceTypeResponses.size() ) ;
		
		String category = null ;
		for( ServiceTypeCdResponseEvent respEv : serviceTypeResponses)
		{
			category = respEv.getCategory() ;
			if( categoryMatch( category, spOnlyEvents ) && 
					!respEv.getServiceTypeCode().trim().equals( UIConstants.INTERVIEW_UNSCHEDULED_CODE ) )
			{
					finalTypeList.add( respEv ) ;
			}
		}

		newEventForm.getCurrentService().setServiceTypeList( finalTypeList ) ;
		newEventForm.getCurrentService().setInterviewTypeList( new ArrayList() ) ;
		
		// mostly for the JSP for specializing titles, etc
		newEventForm.setSecondaryAction( UIConstants.SCHEDULE_PAST_PRESCHEDULED_STR ) ;
		
		return aMapping.findForward( UIConstants.ADD_SUCCESS ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49A50033
	 */
	public ActionForward scheduleNewEvent( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ScheduleNewEventForm newEventForm = (ScheduleNewEventForm)aForm ;
				
		newEventForm.clear() ;
		newEventForm.getCurrentService().getCurrentEvent().setWorkDays( CodeHelper.getWorkDayCodes() ) ;
		
		GetServiceTypeCdEvent serviceTypeEvent = (GetServiceTypeCdEvent)
				EventFactory.getInstance( CodeTableControllerServiceNames.GETSERVICETYPECD ) ;

		serviceTypeEvent.setCodeTableName( "SERVICE_TYPE" ) ;
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( serviceTypeEvent ) ;
		
		// Getting PD Response Event
		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply() ;
		Map dataMap = MessageUtil.groupByTopic( compositeResponse ) ;
		MessageUtil.processReturnException( dataMap ) ;
		
		Collection<ServiceTypeCdResponseEvent> serviceTypeResponses = 
				MessageUtil.compositeToCollection( compositeResponse, ServiceTypeCdResponseEvent.class ) ;
		Collections.sort( (List)serviceTypeResponses ) ;

		boolean spOnlyEvents = false;
		ArrayList finalTypeList = new ArrayList() ;
		finalTypeList.ensureCapacity( serviceTypeResponses.size() ) ;
		
		String category = null ;
		for( ServiceTypeCdResponseEvent respEv : serviceTypeResponses)
		{
			category = respEv.getCategory() ;
			if( categoryMatch( category, spOnlyEvents ) && 
					!respEv.getServiceTypeCode().trim().equals( UIConstants.INTERVIEW_UNSCHEDULED_CODE ) )
			{
					finalTypeList.add( respEv ) ;
			}
		}

		newEventForm.getCurrentService().setServiceTypeList( finalTypeList ) ;
		newEventForm.getCurrentService().setInterviewTypeList( new ArrayList() ) ;

		// mostly for the JSP for specializing titles, etc
		newEventForm.setSecondaryAction( UIConstants.EMPTY_STRING ) ;

		return aMapping.findForward( UIConstants.ADD_SUCCESS ) ;
	}

	/* given a string, return true if the string matches 
	 * one of a number of strings
	 * @param category
	 * @return
	 */
	private boolean categoryMatch( String category, boolean spOnlyEvents )
	{
		if( category == null )
		{
			return( false ) ;
		}
		
		String cat = category.trim();
		
		if (spOnlyEvents) 
		{
			return( cat.equals( UIConstants.PRESCHEDULED_SERVICE_TYPE ) );	
		} 
		else 
		{
			return( cat.equals( UIConstants.INTERVIEW_SERVICE_TYPE ) ||
					cat.equals( UIConstants.NONINTERVIEW_SERVICE_TYPE ) ) ;
		}
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49A50033
	 */
	public ActionForward scheduleNewEventfromTask( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ScheduleNewEventForm newEventForm = (ScheduleNewEventForm)aForm ;
		boolean spOnlyEvents = false;
		
		newEventForm.clear() ;
		newEventForm.getCurrentService().getCurrentEvent().setWorkDays( CodeHelper.getWorkDayCodes() ) ;
		
		GetServiceTypeCdEvent serviceTypeEvent = (GetServiceTypeCdEvent)
				EventFactory.getInstance( CodeTableControllerServiceNames.GETSERVICETYPECD ) ;

		serviceTypeEvent.setCodeTableName( "SERVICE_TYPE" ) ;
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( serviceTypeEvent ) ;
		
		// Getting PD Response Event
		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply() ;
		Map dataMap = MessageUtil.groupByTopic( compositeResponse ) ;
		
		MessageUtil.processReturnException( dataMap ) ;
		Collection<ServiceTypeCdResponseEvent> serviceTypeResponses = 
				MessageUtil.compositeToCollection( compositeResponse, ServiceTypeCdResponseEvent.class ) ;
		Collections.sort( (List)serviceTypeResponses ) ;

		ArrayList finalTypeList = new ArrayList() ;
		finalTypeList.ensureCapacity( serviceTypeResponses.size() ) ;
		
		String category = null ;
		for( ServiceTypeCdResponseEvent respEv : serviceTypeResponses )
		{
			category = respEv.getCategory() ;
			if( categoryMatch( category, spOnlyEvents ) &&
					!respEv.getServiceTypeCode().trim().equals( UIConstants.INTERVIEW_UNSCHEDULED_CODE ) )
			{
				finalTypeList.add( respEv ) ;
			}
		}

		newEventForm.getCurrentService().setServiceTypeList( finalTypeList ) ;
		newEventForm.getCurrentService().setInterviewTypeList( new ArrayList() ) ;

		UIJuvenileCaseworkHelper.populateJuvenileCasefileForm( 
				UIJuvenileCaseworkHelper.getHeaderForm( aRequest, true ), 
				(String)aRequest.getParameter( "casefileID" ) ) ;
		
		newEventForm.setJuvenileNum( (String)aRequest.getParameter( "juvenileID" ) ) ;
		newEventForm.setCaseFileId( (String)aRequest.getParameter( "casefileID" ) ) ;
		newEventForm.setOfficerId( (String)aRequest.getParameter( "officerID" ) ) ;

		return aMapping.findForward( UIConstants.ADD_SUCCESS ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49A50033
	 */
	public ActionForward scheduleServiceProviderEvents( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ScheduleNewEventForm sneForm = (ScheduleNewEventForm)aForm ;
		sneForm.spClear();
		sneForm.setServiceProviderId("");
//		ActionForward forward = aMapping.findForward(UIConstants.FAILURE);
		List spTemp = ComplexCodeTableHelper.getServiceProvidersWithPrograms();
		List allsps = ComplexCodeTableHelper.getAllServiceProviders();
		List spActives = new ArrayList();
//keep only the active service providers with programs
		for (int a = 0; a< allsps.size(); a++)
		{
			ServiceProviderResponseEvent aspt = (ServiceProviderResponseEvent) allsps.get(a);
			if ("A".equalsIgnoreCase(aspt.getStatusId() ) )
			{
				for (int b = 0; b< spTemp.size(); b++)
				{
					ServiceProviderResponseEvent spt = (ServiceProviderResponseEvent) spTemp.get(b);
					if (spt.getJuvServProviderId().equals(aspt.getJuvServProviderId() ) )
					{
						spActives.add(spt);
						break;
					}
				}	
			}
		}
		
/**  begin program retrieval */	
		GetProviderProgramsEvent prEvent = (GetProviderProgramsEvent)
		EventFactory.getInstance( ServiceProviderControllerServiceNames.GETPROVIDERPROGRAMS );
		prEvent.setServiceProviderId("");
		prEvent.setProgramName("");
		prEvent.setStateProgramCode( "");
		prEvent.setTargetInterventionId("");
		prEvent.setStatusId( "A");
		prEvent.setAgencyId( SecurityUIHelper.getUserAgencyId() );
	
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
		dispatch.postEvent( prEvent );
		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();

		MessageUtil.processReturnException( compositeResponse );
		List pgmList = MessageUtil.compositeToList(compositeResponse, ProviderProgramResponseEvent.class);
		Collections.sort( (ArrayList)pgmList );   //coll
/**  end program retrieval */	
		for (int x = 0; x< spActives.size(); x++)	
		{
			ServiceProviderResponseEvent sppe = (ServiceProviderResponseEvent) spActives.get(x);
			List workList = new ArrayList();
			for (int y=0; y< pgmList.size(); y++)
			{	
				ProviderProgramResponseEvent ppre = (ProviderProgramResponseEvent) pgmList.get(y); //   .get(location)  itr2.next();
				if (ppre.getServiceProviderId().equalsIgnoreCase(sppe.getJuvServProviderId()))
				{
				    if(!ppre.getIsProgramDiscontinued()){ // US 174149
					
					ServiceResponseEvent spe = new ServiceResponseEvent();
					spe.setProgramId(ppre.getProviderProgramId());
					spe.setProgramName(ppre.getProgramName());
					spe.setServiceProviderId(ppre.getServiceProviderId());
					spe.setInHouse(ppre.isInHouse());
					Collection tSvcList = UIServiceProviderHelper.getServicesByProgram( ppre.getProviderProgramId()); 
					Collection<ServiceResponseEvent> servicesList = UIServiceProviderHelper.sortResults( tSvcList, "S" ) ;
					spe.setServices((List) servicesList ) ;						
					workList.add(spe);
				    }					
				}
			}
			sppe.setServiceResponseEvents(workList);
		}
		sneForm.setServiceProviderList(spActives);
		//Defect JIMS200076898 fix starts.
		//Set Secondary action when the user selects the schedule events by service provider first without scheduling any other events.
		//This resolves the inconsistent behavior of begin date populated with today's date instead of event date.
		if(sneForm.getSecondaryAction().equals(UIConstants.EMPTY_STRING))
		{
			sneForm.setSecondaryAction( UIConstants.SCHEDULE_PAST_PRESCHEDULED_STR ) ;
		}
		//Defect JIMS200076898 fix ends.		
		return aMapping.findForward( UIConstants.SERVICE_PROVIDER_SUCCESS) ;
	}	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping( Map keyMap )
	{
		keyMap.put( "button.link", "displayCalendar" ) ;
		keyMap.put( "button.scheduleJuvenileEvents", "scheduleNewEvent" ) ;
		keyMap.put( "button.scheduleEventsByServiceProvider", "scheduleServiceProviderEvents" ) ;
		keyMap.put( "button.scheduleEventsBySPEventTypes", "scheduleNewEventSPOnly" ) ;
		keyMap.put( "button.back", "back" ) ;
		keyMap.put( "button.linkCreate", "scheduleNewEventfromTask" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;

	}
}
