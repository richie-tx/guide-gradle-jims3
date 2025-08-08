//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\calendar\\transactions\\GetJuvenileAttendanceCommand.java

package pd.supervision.calendar.transactions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import messaging.calendar.SaveJuvenileAttendanceEvent;
import messaging.calendar.reply.AttendeeNameResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import naming.InterviewConstants;
import naming.PDCalendarConstants;
import naming.UIConstants;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilecase.interviewinfo.Interview;
import pd.security.PDSecurityHelper;
import pd.supervision.administerserviceprovider.ProviderProgram;
import pd.supervision.calendar.AdditionalAttendee;
import pd.supervision.calendar.ServiceEvent;
import pd.supervision.calendar.ServiceEventAttendance;
import ui.common.UINotificationHelper;

public class SaveJuvenileAttendanceCommand implements ICommand
{
	/**
	 * @roseuid 45702FB40066
	 */
	public SaveJuvenileAttendanceCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 456F2D850264
	 */
	public void execute( IEvent event )
	{
		SaveJuvenileAttendanceEvent attEvent = (SaveJuvenileAttendanceEvent)event;
		String serviceEventId = attEvent.getServiceEventId();
		String juvenileId = attEvent.getJuvenileId();
		ArrayList allAttItems = new ArrayList();

		Iterator<ServiceEventAttendance> attenIter = 
				ServiceEventAttendance.findAll( "serviceEventId", serviceEventId );
		while(attenIter.hasNext())
		{
			ServiceEventAttendance eventAttendance = attenIter.next();
			allAttItems.add( eventAttendance );
			
			if( eventAttendance.getJuvenileId().equals( juvenileId ) )
			{
				eventAttendance.setAttendanceStatusCd( attEvent.getAttendanceStatusCd() );
				
				if (eventAttendance.getProgressNotes() != null && attEvent.isAppendProgressNotes()) {
					StringBuffer notes = new StringBuffer(eventAttendance.getProgressNotes()) ;
					notes = notes.append( " " ) ;
					notes = notes.append(attEvent.getProgressNotes());
					eventAttendance.setProgressNotes(notes.toString());
				} else {
					eventAttendance.setProgressNotes(attEvent.getProgressNotes());
				}
				eventAttendance.setAddlAttendees( attEvent.getAddlAttendees() );

				// First delete the existing additional attendees if there any 
				// JIMS200059212 : MJCW: Document attendance for all int.types(PD)-KK
				Iterator<AdditionalAttendee> attendees = eventAttendance.getAddlAttendeeNames().iterator();
				while(attendees.hasNext())
				{
					AdditionalAttendee attendee = attendees.next();
					attendee.delete();
				}

				Iterator<AttendeeNameResponseEvent> attendeeNames = 
						attEvent.getAddlAttendeeNames().iterator();
				while(attendeeNames.hasNext())
				{
					AttendeeNameResponseEvent attendeeName = attendeeNames.next();
					
					AdditionalAttendee attendee = new AdditionalAttendee();
					
					attendee.setFirstName( attendeeName.getFirstName() );
					attendee.setMiddleName( attendeeName.getMiddleName() );
					attendee.setLastName( attendeeName.getLastName() );
					attendee.setJuvenileId( attEvent.getJuvenileId() );
					eventAttendance.insertAddlAttendeeNames( attendee );
				}
			}
		}

		if( !attEvent.getEventCategory().equals( UIConstants.PRESCHEDULED_SERVICE_TYPE ) )
		{
			ServiceEvent serv = (ServiceEvent)ServiceEvent.find( serviceEventId );
			serv.setEventStatusId( PDCalendarConstants.SERVICE_EVENT_STATUS_COMPLETED );

			if( attEvent.getEventCategory().equals( UIConstants.INTERVIEW_SERVICE_TYPE ) )
			{
				String interviewId = serv.getInterviewId();
				Interview interview = Interview.find( interviewId );
				
				if( interview != null )
				{	/* check if the current Interview Status is Complete ... 
					* if it is Complete, leave the Status alone. otherwise, set it appropriately 
					*/
					if( ! interview.getInterviewStatusCd().equals( InterviewConstants.INTERVIEW_STATUS_COMPLETE ))
					{
						if( attEvent.getAttendanceStatusCd().equals( PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT ) || 
								attEvent.getAttendanceStatusCd().equals( PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED ) )
						{
							interview.setInterviewStatusCd( InterviewConstants.INTERVIEW_STATUS_COMPLETE );
						}
						else if( attEvent.getAttendanceStatusCd().equals( PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED ) )
						{
							interview.setInterviewStatusCd( InterviewConstants.INTERVIEW_STATUS_INCOMPLETE );
						}
					}
				}
			}
			return;
		}
		ServiceEvent serv = (ServiceEvent)ServiceEvent.find( serviceEventId );
		Date rightNow = new Date();
		Date eventDate = attEvent.getEventStartDate();
		if( eventDate.before( rightNow ) )
		{
			boolean completed = true;
			attenIter = allAttItems.iterator();
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
		
		if( attEvent.getAttendanceStatusCd().equals( PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED ) ||
					attEvent.getAttendanceStatusCd().equals( PDCalendarConstants.JUV_ATTEND_STATUS_CANCELLED ))
		{
 			if( serv != null )
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
	}

	/**
	 * @param event
	 * @roseuid 456F2D850272
	 */
	public void onRegister( IEvent event )
	{
	}

	/**
	 * @param event
	 * @roseuid 456F2D850274
	 */
	public void onUnregister( IEvent event )
	{
	}

	/**
	 * @param anObject
	 * @roseuid 456F2D850276
	 */
	public void update( Object anObject )
	{
	}
}
