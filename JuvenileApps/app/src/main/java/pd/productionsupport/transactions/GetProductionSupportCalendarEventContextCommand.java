package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.calendar.reply.CalendarEventContextResponse;
import messaging.productionsupport.GetProductionSupportCalendarEventContextEvent;
import messaging.productionsupport.reply.ProductionSupportCalendarResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.common.calendar.CalendarEvent;
import pd.common.calendar.CalendarEventContext;

public class GetProductionSupportCalendarEventContextCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public GetProductionSupportCalendarEventContextCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
		GetProductionSupportCalendarEventContextEvent calendarEvent = (GetProductionSupportCalendarEventContextEvent) event;  
		if(calendarEvent.getCalendarEventId() != null){
			Iterator<CalendarEventContext> calendarContextIter = CalendarEventContext.findAll("calendarEventId", calendarEvent.getCalendarEventId());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			while(calendarContextIter.hasNext()){
				CalendarEventContext calendarContext = (CalendarEventContext)calendarContextIter.next();
				CalendarEventContextResponse calendarContextEventResponse = new CalendarEventContextResponse();
				calendarContextEventResponse.setCalendarEventContextId(calendarContext.getCalendarEventContextId());
				calendarContextEventResponse.setCalendarEventId(calendarContext.getCalendarEventId());
				calendarContextEventResponse.setCaseFileId(calendarContext.getCaseFileId());
				calendarContextEventResponse.setJuvenileId(calendarContext.getJuvenileId());
				calendarContextEventResponse.setProbationOfficerId(calendarContext.getProbationOfficerId());
				if(calendarContext.getCreateUserID() != null){
					calendarContextEventResponse.setCreateUser(calendarContext.getCreateUserID());
				}
				if(calendarContext.getCreateTimestamp() != null){
					calendarContextEventResponse.setCreateDate(new Date(calendarContext.getCreateTimestamp().getTime()));
				}
				if(calendarContext.getUpdateUserID() != null){
					calendarContextEventResponse.setUpdateUser(calendarContext.getUpdateUserID());
				}
				if(calendarContext.getUpdateTimestamp() != null){
					calendarContextEventResponse.setUpdateDate(new Date(calendarContext.getUpdateTimestamp().getTime()));
				}
				if(calendarContext.getCreateJIMS2UserID() != null){
					calendarContextEventResponse.setCreateJims2User(calendarContext.getCreateJIMS2UserID());
				}
				if(calendarContext.getUpdateJIMS2UserID() != null){
					calendarContextEventResponse.setUpdateJims2User(calendarContext.getUpdateJIMS2UserID());
				}
				
				dispatch.postEvent(calendarContextEventResponse);
			}
		}else if(calendarEvent.getCasefileId() != null){
			Iterator<CalendarEventContext> calendarContextIter = CalendarEventContext.findAll("caseFileId", calendarEvent.getCasefileId());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			while(calendarContextIter.hasNext()){
				CalendarEventContext calendarContext = (CalendarEventContext)calendarContextIter.next();
				CalendarEventContextResponse calendarContextEventResponse = new CalendarEventContextResponse();
				calendarContextEventResponse.setCalendarEventContextId(calendarContext.getCalendarEventContextId());
				calendarContextEventResponse.setCalendarEventId(calendarContext.getCalendarEventId());
				calendarContextEventResponse.setCaseFileId(calendarContext.getCaseFileId());
				calendarContextEventResponse.setJuvenileId(calendarContext.getJuvenileId());
				calendarContextEventResponse.setProbationOfficerId(calendarContext.getProbationOfficerId());
				if(calendarContext.getCreateUserID() != null){
					calendarContextEventResponse.setCreateUser(calendarContext.getCreateUserID());
				}
				if(calendarContext.getCreateTimestamp() != null){
					calendarContextEventResponse.setCreateDate(new Date(calendarContext.getCreateTimestamp().getTime()));
				}
				if(calendarContext.getUpdateUserID() != null){
					calendarContextEventResponse.setUpdateUser(calendarContext.getUpdateUserID());
				}
				if(calendarContext.getUpdateTimestamp() != null){
					calendarContextEventResponse.setUpdateDate(new Date(calendarContext.getUpdateTimestamp().getTime()));
				}
				if(calendarContext.getCreateJIMS2UserID() != null){
					calendarContextEventResponse.setCreateJims2User(calendarContext.getCreateJIMS2UserID());
				}
				if(calendarContext.getUpdateJIMS2UserID() != null){
					calendarContextEventResponse.setUpdateJims2User(calendarContext.getUpdateJIMS2UserID());
				}
				
				dispatch.postEvent(calendarContextEventResponse);
			}
		}
    	
 
    }

    /**
     * @param event
     * @roseuid 4526B083011E
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 4526B083012B
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 4526B083012D
     */
    public void update(Object anObject)
    {

    }
    
    private ProductionSupportCalendarResponseEvent getCalendarResponseEvent(CalendarEvent serviceEvent){
    	ProductionSupportCalendarResponseEvent calendarEvent = new ProductionSupportCalendarResponseEvent();
    	
    	calendarEvent.setCalendarEventId(new Integer(serviceEvent.getOID()));
    	calendarEvent.setBodyText(serviceEvent.getBodyText());
    	calendarEvent.setCalendarEventType(serviceEvent.getCalendarEventType());
    	
    	calendarEvent.setStartDatetime(serviceEvent.getStartDatetime());
    	calendarEvent.setEndDatetime(serviceEvent.getEndDatetime());
    	calendarEvent.setLocation(serviceEvent.getLocation());
    	calendarEvent.setStatus(serviceEvent.getStatus());
    	calendarEvent.setSubject(serviceEvent.getSubject());
    	calendarEvent.setTimeZone(serviceEvent.getTimeZone());
    	calendarEvent.setCalendarEventId(serviceEvent.getCalendarEventId());
    	calendarEvent.setCalendarEventDate(serviceEvent.getCalendarEventDate());
    	calendarEvent.setCalendarSeriesId(serviceEvent.getCalendarEventSeriesId());
    	calendarEvent.setCreatedBy(serviceEvent.getCreatedBy());
		if(serviceEvent.getCreateUserID() != null){
			calendarEvent.setCreateUser(serviceEvent.getCreateUserID());
		}
		if(serviceEvent.getCreateTimestamp() != null){
			calendarEvent.setCreateDate(new Date(serviceEvent.getCreateTimestamp().getTime()));
		}
		if(serviceEvent.getUpdateUserID() != null){
			calendarEvent.setUpdateUser(serviceEvent.getUpdateUserID());
		}
		if(serviceEvent.getUpdateTimestamp() != null){
			calendarEvent.setUpdateDate(new Date(serviceEvent.getUpdateTimestamp().getTime()));
		}
		if(serviceEvent.getCreateJIMS2UserID() != null){
			calendarEvent.setCreateJims2User(serviceEvent.getCreateJIMS2UserID());
		}
		if(serviceEvent.getUpdateJIMS2UserID() != null){
			calendarEvent.setUpdateJims2User(serviceEvent.getUpdateJIMS2UserID());
		}
    	
    	return calendarEvent;
    	
    }

}
