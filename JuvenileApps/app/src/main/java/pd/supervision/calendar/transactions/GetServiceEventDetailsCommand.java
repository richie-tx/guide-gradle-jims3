package pd.supervision.calendar.transactions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import pd.codetable.Code;
import pd.codetable.criminal.JuvenileEventTypeCode;
import pd.common.calendar.CalendarEvent;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.administerserviceprovider.ProviderProgram;
import pd.supervision.administerserviceprovider.SP_Profile;
import pd.supervision.administerserviceprovider.Service;
import pd.supervision.administerserviceprovider.ServiceProvider;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;
import pd.supervision.calendar.ServiceEvent;
import messaging.calendar.GetServiceEventDetailsEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.Name;

public class GetServiceEventDetailsCommand implements ICommand {


	public void execute(IEvent event) {
		
		GetServiceEventDetailsEvent reqEvent = (GetServiceEventDetailsEvent) event;
		
		ServiceEvent serviceEvent = (ServiceEvent) ServiceEvent.find(reqEvent.getServiceEventId());
		
		CalendarEvent calendarEvent = CalendarEvent.find(String.valueOf(serviceEvent.getCalendarEventId()));

        CalendarServiceEventResponseEvent resp = new CalendarServiceEventResponseEvent();
        
		JuvLocationUnit juvLocUnit = serviceEvent.getJuvLocUnit();
		if (juvLocUnit != null)
		{
			resp.setServiceLocationName(juvLocUnit.getLocationUnitName());
		}
        resp.setMaxAttendance(Integer.toString(serviceEvent.getEventMaximum()));
        resp.setMinAttendance(Integer.toString(serviceEvent.getEventMinimum()));
        resp.setCurrentEnrollment(Integer.toString(serviceEvent.getCurrentEnrollment()));
        resp.setEventId(serviceEvent.getServiceEventId());
        resp.setCalendarEventId(serviceEvent.getCalendarEventId());
        resp.setBodyText(serviceEvent.getBodyText());
        resp.setCreatedBy(serviceEvent.getCreatedBy());
        resp.setSubject(serviceEvent.getSubject());
        resp.setEventComments(serviceEvent.getEventComments());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date startDate = calendarEvent.getStartDatetime();
        Date endDate = calendarEvent.getEndDatetime();

        String eventTime = dateFormat.format(startDate);

        long eventLength = endDate.getTime() - startDate.getTime();
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String eventSessionLength = dateFormat.format(new Date(eventLength));
        resp.setStartDatetime(startDate);
        resp.setEndDatetime(endDate);
        resp.setEventDate(startDate);
        resp.setEventSessionLength(eventSessionLength);
        resp.setEventTime(eventTime);

        JuvenileEventTypeCode eventType = serviceEvent.getEventType();
        if (eventType != null)
        {            
            resp.setEventTypeCode(eventType.getCode());
            resp.setEventType(eventType.getDescription());
            resp.setEventTypeCategory(eventType.getGroup());
        }

        Code eventStatus = serviceEvent.getEventStatus();
        if (eventStatus != null)
        {
            resp.setEventStatusCode(eventStatus.getCode());
            resp.setEventStatus(eventStatus.getDescription());
        }
        
        SP_Profile spProfile = serviceEvent.getInstructor();
        if (spProfile != null){
	        Name name = new Name(spProfile.getFirstName(), spProfile.getMiddleName(), spProfile.getLastName());
	        resp.setInstructorName(name.getCompleteFullNameLast());
	        name = null;
        }
        Service service = Service.find(serviceEvent.getServiceId());
        resp.setMaxAttendance(Integer.toString(service.getMaxEnrollment()));
        //resp.setMinAttendance();
        
        ProviderProgram program = ProviderProgram.find(service.getProviderProgramId());

        ServiceProvider sp = (ServiceProvider) JuvenileServiceProvider.find(program.getJuvenileServiceProviderId());
        resp.setServiceProviderName(sp.getServiceProviderName());
        resp.setServiceProviderId(Integer.parseInt(sp.getOID()));

        MessageUtil.postReply(resp);
        
        reqEvent = null;
        serviceEvent = null;
        resp = null;
        eventTime = null;
        eventSessionLength = null;
        eventType = null;
        eventStatus = null;
        dateFormat = null;
        startDate = null;
        endDate = null;

	}



}
