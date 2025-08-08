//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\calendar\\transactions\\SaveServiceEventAttendanceCommand.java

package pd.supervision.calendar.transactions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import messaging.calendar.CalendarContextEvent;
import messaging.calendar.GetCalendarServiceEventsEvent;
import messaging.calendar.SaveJuvenileAttendanceEvent;
import messaging.calendar.SaveServiceEventAttendanceEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import naming.PDCalendarConstants;
import pd.common.calendar.CalendarEvent;
import pd.common.calendar.CalendarEventIdAttribute;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.supervision.administerserviceprovider.ProviderProgram;
import pd.supervision.calendar.ServiceEvent;
import pd.supervision.calendar.ServiceEventAttendance;
import ui.common.UINotificationHelper;
import ui.juvenilecase.schedulecalendarevent.CalendarRetrieverFactory;
import ui.juvenilecase.workshopcalendar.form.CalendarEventListForm;
import ui.juvenilecase.workshopcalendar.form.ServiceEventDetailsForm;

public class SaveServiceEventAttendanceCommand implements ICommand
{
	/**
	 * @roseuid 45702FC1023B
	 */
	public SaveServiceEventAttendanceCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 456F4CE103A9
	 */
	public void execute( IEvent event )
	{
		SaveServiceEventAttendanceEvent attEvent = (SaveServiceEventAttendanceEvent)event;
		String serviceEventId = attEvent.getServiceEventId();
		CalendarServiceEventResponseEvent resp = null;
		HashMap allAttItems = new HashMap();

		Iterator<ServiceEventAttendance> attenIter = 
			ServiceEventAttendance.findAll( "serviceEventId", serviceEventId );
		while(attenIter.hasNext())
		{
			ServiceEventAttendance eventAttendance = attenIter.next();
			allAttItems.put( eventAttendance.getJuvenileId(), eventAttendance );
		}

		Date rightNow = new Date();
		Date eventDate = attEvent.getEventStartDate();
		ServiceEvent serv = (ServiceEvent)ServiceEvent.find( serviceEventId );
		
		Iterator eventIter = attEvent.getJuvenileAttendanceEvents().iterator();
		//Iterator eventItr = attEvent.get.iterator();
		while(eventIter.hasNext())
		{
			SaveJuvenileAttendanceEvent juvEvent = (SaveJuvenileAttendanceEvent)eventIter.next();
			ServiceEventAttendance eventAttendance = (ServiceEventAttendance)allAttItems.get( juvEvent.getJuvenileId() );
			eventAttendance.setAttendanceStatusCd( juvEvent.getAttendanceStatusCd() );
			
			if (juvEvent.getProgressNotes() != null && !juvEvent.getProgressNotes().isEmpty())
			{
			    eventAttendance.setProgressNotes( juvEvent.getProgressNotes() );
			}
			
			eventAttendance.setAddlAttendees( juvEvent.getAddlAttendees() );
			
			if( juvEvent.getAttendanceStatusCd().equals( PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED ))  
			{ // don't allow currentEnrollment count to go less than zero
				int ce = serv.getCurrentEnrollment() ;
				serv.setCurrentEnrollment( (ce < 1) ? 0 : (ce -1) );
				if (serv.getEventStatusId().equals(PDCalendarConstants.SERVICE_EVENT_STATUS_SCHEDULED))
				{
					serv.setEventStatusId(PDCalendarConstants.SERVICE_EVENT_STATUS_AVAILABLE);
					// task 153583
					if(attEvent.getCurrentEvent()!=null)
					{
					  //find the proglocationcd from table with attEvent.getCurrentEvent().getProgramId() and check if its 1 or 6
					    	ProviderProgram program=null;
					    	if(attEvent.getCurrentEvent().getProgramId()!=null)
					    	{
					    	    program = ProviderProgram.find(attEvent.getCurrentEvent().getProgramId());
					    	}
					    	
        					if(!attEvent.getCurrentEvent().isServiceProvideInhouse() && program!=null &&!program.getProgramLocation().isEmpty()&&(program.getProgramLocation().equalsIgnoreCase("1")||program.getProgramLocation().equalsIgnoreCase("6")))
        					{
                                        		Collection<OfficerProfileResponseEvent>   securityRespEvent =  PDOfficerProfileHelper.getOfficerProfilesInUserGroup("Available Calendar Session Notification Group");
                                        		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);	    
                                        		if(securityRespEvent!=null){
                                        			 Iterator<OfficerProfileResponseEvent> securityRespIter = securityRespEvent.iterator();
                                        		
                                        		    while(securityRespIter.hasNext())
                                        		    {
                                        			OfficerProfileResponseEvent	securityResEvent =	securityRespIter.next();
                                        						
                                        			if(securityResEvent.getEmail()!=null && !securityResEvent.getEmail().equals(""))
                                        		    	{
                                                			//send email
                                                			SendEmailEvent sendEmailEvent = new SendEmailEvent();
                                                			StringBuffer message = new StringBuffer(100);
                                                			SimpleDateFormat time = new SimpleDateFormat("HH:MM");
                                                			//String timeNow = time.format(currDate);
                                                			sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");
                                            		    		UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent,securityResEvent.getEmail());
                                                			sendEmailEvent.setSubject("Calendar Event has become available");
                                                			sendEmailEvent.setContentType("text/html");
                                                			message.append("An opening has become available in the following Calendar Event:");  
                                                			message.append("<br/>");
                                                			message.append("Service Provider: "+attEvent.getCurrentEvent().getServiceProviderName());
                                                			message.append("<br/>");
                                                			message.append("Program Name: "+attEvent.getCurrentEvent().getProgramName());
                                                			message.append("<br/>");
                                                			if(attEvent.getCurrentEvent().getEventDate()!=null)
                                                			{
                                                			    message.append("Event Date: "+DateUtil.dateToString(attEvent.getCurrentEvent().getEventDate(),DateUtil.DATE_FMT_1));
                                                			    message.append("<br/>");
                                                			}   
                                                			
                                                			message.append("Event Time: "+attEvent.getCurrentEvent().getEventTime());
                                                			if(attEvent.getCurrentEvent().getEventTimeFormatted()!=null)
                                                			{
                                                			    message.append(" "+attEvent.getCurrentEvent().getEventTimeFormatted().substring(attEvent.getCurrentEvent().getEventTimeFormatted().length()-3));                                                			   
                                                			} 
                                                			message.append("<br/>");
                                                			message.append("Location Unit: "+attEvent.getCurrentEvent().getServiceLocationName());
                                                			message.append("<br/>");
                                                			message.append("Event Type: "+attEvent.getCurrentEvent().getEventType());
                                                			message.append("<br/>");
                                                			message.append("Session Length: "+attEvent.getCurrentEvent().getEventSessionLength());
                                                			message.append("<br/>");
                                                			message.append("Instructor: "+attEvent.getCurrentEvent().getInstructorName());
                                                			sendEmailEvent.setMessage(message.toString());
                                                		    	dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
                                            		    		dispatch.postEvent(sendEmailEvent);
                                        		    	}//end if officer email
                                        		    }//end while
                                        		}
                                        		//email end
        					}
        				}//end if current event not null
					// task 153583
				}
			}
		}

		if( eventDate.before( rightNow ) )
		{
			boolean completed = true;
			attenIter = allAttItems.values().iterator();
			while(attenIter.hasNext())
			{
				ServiceEventAttendance eventAttendance = attenIter.next();
				
				if( !eventAttendance.getAttendanceStatusCd().equals( PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED ) && 
						!eventAttendance.getAttendanceStatusCd().equals( PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED ) && 
						!eventAttendance.getAttendanceStatusCd().equals( PDCalendarConstants.JUV_ATTEND_STATUS_CANCELLED ) && 
						!eventAttendance.getAttendanceStatusCd().equals( PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT ) )
				{
					completed = false;
					break;
				}
			}
			if( completed )
			{
				serv.setEventStatusId( PDCalendarConstants.SERVICE_EVENT_STATUS_COMPLETED );
			}
		}

		resp = new CalendarServiceEventResponseEvent();
		// might be overkill, but ensures currentEnrollment doesn't go less than zero
		int ce = serv.getCurrentEnrollment() ;
		if( ce < 0 )
		{
			ce = 0 ;
		}
		resp.setCurrentEnrollment( ce + "" );
		
		resp.setEventStatusCode( serv.getEventStatus().getCode() );
		resp.setEventStatus( serv.getEventStatus().getDescription() );		

		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REPLY );
		dispatch.postEvent( resp );
	}

	/**
	 * @param event
	 * @roseuid 456F4CE103AB
	 */
	public void onRegister( IEvent event )
	{
	}

	/**
	 * @param event
	 * @roseuid 456F4CE103B9
	 */
	public void onUnregister( IEvent event )
	{
	}

	/**
	 * @param anObject
	 * @roseuid 456F4CE103C8
	 */
	public void update( Object anObject )
	{
	}
}
