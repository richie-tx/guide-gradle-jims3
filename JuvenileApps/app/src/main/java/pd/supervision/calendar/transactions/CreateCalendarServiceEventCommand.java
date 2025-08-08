//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\calendar\\transactions\\CreateCalendarServiceEventCommand.java

package pd.supervision.calendar.transactions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import messaging.administerserviceprovider.GetServiceLocationEvent;
import messaging.administerserviceprovider.reply.ServiceProviderErrorResponseEvent;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.CreateCalendarServiceEventEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.context.multidatasource.Home;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.IHome;
import mojo.km.util.MessageUtil;
import naming.PDCalendarConstants;
import pd.common.calendar.CalendarEvent;
import pd.common.calendar.CalendarManager;
import pd.common.calendar.ICalendarRecurrenceType;
import pd.common.calendar.OneRecurrence;
import pd.security.PDSecurityHelper;
import pd.supervision.administerserviceprovider.ServiceLocation;
import pd.supervision.calendar.ServiceEvent;

public class CreateCalendarServiceEventCommand implements ICommand
{

    /**
     * @roseuid 44805C5701F8
     */
    public CreateCalendarServiceEventCommand()
    {

    }

    /**
     * @param event
     * @roseuid 447F49B500FA
     */
    public void execute(IEvent event)
    {
	CreateCalendarServiceEventEvent createEvent = (CreateCalendarServiceEventEvent) event;
	IDispatch requestDispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	// Referential Integrity Check
	GetServiceLocationEvent ev = new GetServiceLocationEvent();
	ev.setJuvLocUnitId(Integer.parseInt(createEvent.getLocationId()));
	ev.setServiceId(Integer.parseInt(createEvent.getServiceId()));
	Iterator serviceLocationIter = ServiceLocation.findAll(ev);
	if (!serviceLocationIter.hasNext())
	{
	    sendErrorResponseEvent(createEvent, "error.createServiceEventFailure");
	    return;
	}

	String logonId = (PDSecurityHelper.getLogonId() == null) ? "JIMS" : PDSecurityHelper.getLogonId();
	createEvent.setCreatedBy(logonId);
	createEvent.setEventStatusId(PDCalendarConstants.SERVICE_EVENT_STATUS_PENDING);
	ICalendarRecurrenceType recurr = new OneRecurrence();
	CalendarManager manager = new CalendarManager();
	Iterator events = createEvent.getEvents().iterator();
	String eventSeriesId = null;
	while (events.hasNext())
	{
	    CalendarServiceEventResponseEvent sre = (CalendarServiceEventResponseEvent) events.next();
	    createEvent.setStartDatetime(sre.getStartDatetime());
	    createEvent.setEndDatetime(sre.getEndDatetime());

	    if (eventSeriesId == null || "".equals(eventSeriesId))
	    {
		Collection col = manager.saveCalendarEvent(createEvent, recurr, Calendar.getInstance().getTimeZone());
		eventSeriesId = this.saveServiceEvent(col, createEvent, logonId);
		//CompositeResponse cr = MessageUtil.postRequest(event);				
		CompositeResponse resp = (CompositeResponse) requestDispatch.getReply();
		//CalendarServiceEventResponseEvent codeResponse = (CalendarServiceEventResponseEvent) MessageUtil.filterComposite(resp, CalendarServiceEventResponseEvent.class);

	    }
	    else
	    {
		CalendarEvent calEvent = manager.saveCalendarEventToExistingSeries(createEvent, eventSeriesId, Calendar.getInstance().getTimeZone());
		ArrayList col = new ArrayList();
		col.add(calEvent);
		this.saveServiceEvent(col, createEvent, logonId);
	    }
	}
    }

    private String saveServiceEvent(Collection eventCollection, CreateCalendarServiceEventEvent createEvent, String logonId)
    {

	CalendarServiceEventResponseEvent re = null;
	List serviceEvents = new ArrayList();

	String seriesId = null;
	if (eventCollection != null)
	{
	    Iterator iter = eventCollection.iterator();

	    CalendarEvent calEvent = null;
	    while (iter.hasNext())
	    {
		calEvent = (CalendarEvent) iter.next();
	    }
	    ServiceEvent se = new ServiceEvent();
	    se.setServiceEvent(createEvent, logonId, new Integer(calEvent.getOID()));
	    seriesId = calEvent.getCalendarEventSeriesId().toString();

	    IHome home = new Home();
	    home.bind(se);
	    re = new CalendarServiceEventResponseEvent();
	    re.setServiceEventId(se.getOID());
	    re.setStartDatetime(calEvent.getStartDatetime());
	    serviceEvents.add(re);

	    // Create INVALID contexts to enable context queries without OUTER JOIN
	    CalendarContextEvent context = new CalendarContextEvent();
	    context.setProbationOfficerId(PDCalendarConstants.INVALID_CONTEXT_ID);
	    context.setCaseFileId(PDCalendarConstants.INVALID_CONTEXT_ID);
	    context.setJuvenileId(PDCalendarConstants.INVALID_CONTEXT_ID);

	    Integer calendarEventId = calEvent.getCalendarEventId();
	    new CalendarManager().addCalendarEventContext(calendarEventId, context);
	    //re = null;
	    home = null;
	}

	//MessageUtil.postReplies(serviceEvents);
	MessageUtil.postReply(re);

	return seriesId;
    }

    /**
     * @param createEvent
     * @param errorKey
     */
    private void sendErrorResponseEvent(CreateCalendarServiceEventEvent createEvent, String errorKey)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	ServiceProviderErrorResponseEvent errorEvent = new ServiceProviderErrorResponseEvent();
	errorEvent.setMessage(errorKey);
	errorEvent.setServiceName(createEvent.getServiceName());
	errorEvent.setLocationName(createEvent.getLocation());
	dispatch.postEvent(errorEvent);
    }

    /**
     * @param event
     * @roseuid 447F49B50109
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 447F49B5010B
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 447F49B5010D
     */
    public void update(Object anObject)
    {

    }
}
