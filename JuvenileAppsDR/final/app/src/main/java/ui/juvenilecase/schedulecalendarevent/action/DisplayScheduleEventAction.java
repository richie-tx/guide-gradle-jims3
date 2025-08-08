//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\schedulecalendarevent\\action\\DisplayCalendarEventListAction.java

package ui.juvenilecase.schedulecalendarevent.action;

import java.util.ArrayList ;
import java.util.Collection ;
import java.util.Collections ;
import java.util.HashMap ;
import java.util.Iterator ;
import java.util.List ;
import java.util.Map ;

import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import messaging.codetable.GetServiceTypeCdEvent ;
import messaging.codetable.criminal.reply.ServiceTypeCdResponseEvent ;
import mojo.km.dispatch.EventManager ;
import mojo.km.dispatch.IDispatch ;
import mojo.km.messaging.EventFactory ;
import mojo.km.messaging.Composite.CompositeResponse ;
import mojo.km.utilities.MessageUtil ;
import naming.CodeTableControllerServiceNames ;
import naming.UIConstants ;

import org.apache.struts.action.ActionForm ;
import org.apache.struts.action.ActionForward ;
import org.apache.struts.action.ActionMapping ;
import org.apache.struts.actions.LookupDispatchAction ;

import ui.juvenilecase.workshopcalendar.form.ScheduleNewEventForm ;

public class DisplayScheduleEventAction extends LookupDispatchAction
{
	/**
	 * @roseuid 4576E78400F1
	 */
	public DisplayScheduleEventAction( )
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
	public ActionForward scheduleNewEvent( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ScheduleNewEventForm newEventForm = (ScheduleNewEventForm)aForm ;
		GetServiceTypeCdEvent serviceTypeEvent = (GetServiceTypeCdEvent)
				EventFactory.getInstance( CodeTableControllerServiceNames.GETSERVICETYPECD ) ;

		serviceTypeEvent.setCodeTableName( "SERVICE_TYPE" ) ;
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( serviceTypeEvent ) ;
		
		// Getting PD Response Event
		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply( ) ;
		
		Map dataMap = MessageUtil.groupByTopic( compositeResponse ) ;
		MessageUtil.processReturnException( dataMap ) ;
		
		Collection<ServiceTypeCdResponseEvent> serviceTypeResponses = 
					MessageUtil.compositeToCollection( compositeResponse, ServiceTypeCdResponseEvent.class ) ;
		Collections.sort( (List)serviceTypeResponses ) ;
		
		ArrayList finalTypeList = new ArrayList( ) ;
		String category = null ;
		for( ServiceTypeCdResponseEvent respEv: serviceTypeResponses )
		{
			category = respEv.getCategory( ).trim( );
			if( categoryMatch( category )  &&
					!respEv.getServiceTypeCode( ).equals( UIConstants.INTERVIEW_UNSCHEDULED_CODE ) )
			{
				finalTypeList.add( respEv ) ;
			}
		}
		
		newEventForm.getCurrentService( ).setServiceTypeList( finalTypeList ) ;
		return aMapping.findForward( UIConstants.SUCCESS ) ;
	}

	/* given a string, return true if the string matches 
	 * one of a number of strings
	 * @param category
	 * @return
	 */
	private boolean categoryMatch( String category )
	{
		return( category != null &&
				(category.equals( UIConstants.PRESCHEDULED_SERVICE_TYPE ) ||
				category.equals( UIConstants.INTERVIEW_SERVICE_TYPE ) ||
				category.equals( UIConstants.NONINTERVIEW_SERVICE_TYPE )) ) ;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap( )
	{
		Map buttonMap = new HashMap( ) ;
		buttonMap.put( "button.scheduleNewEvent", "scheduleNewEvent" ) ;
		return buttonMap ;
	}
}
