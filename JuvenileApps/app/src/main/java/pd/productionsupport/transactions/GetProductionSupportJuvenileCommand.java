package pd.productionsupport.transactions;

import java.util.Date;

import messaging.juvenile.reply.JuvenileProfilesResponseEvent;
import messaging.productionsupport.GetProductionSupportJuvenileEvent;
import messaging.productionsupport.reply.ProductionSupportCalendarResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.common.calendar.CalendarEvent;
import pd.juvenile.Juvenile;

public class GetProductionSupportJuvenileCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public GetProductionSupportJuvenileCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
		GetProductionSupportJuvenileEvent getJuvenileEvent = (GetProductionSupportJuvenileEvent) event;  
		Juvenile juvenile = Juvenile.findDb2Juvenile(getJuvenileEvent.getJuvenileId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			
		JuvenileProfilesResponseEvent juvenileProfileEventResponse = new JuvenileProfilesResponseEvent();
			
		if(juvenile != null){
			juvenileProfileEventResponse.setJuvenileNum(juvenile.getOID());
			juvenileProfileEventResponse.setDateOfBirthAsDate(juvenile.getDateOfBirth());
			juvenileProfileEventResponse.setEthnicityCd(juvenile.getEthnicityId());
			if(juvenile.getEthnicity() != null){
				juvenileProfileEventResponse.setEthnicityDesc(juvenile.getEthnicity().getCode());
			}
			juvenileProfileEventResponse.setFirstName(juvenile.getFirstName());
			juvenileProfileEventResponse.setLastName(juvenile.getLastName());
			juvenileProfileEventResponse.setMiddleName(juvenile.getMiddleName());
			juvenileProfileEventResponse.setNationalityCd(juvenile.getNationalityId());
			if(juvenile.getNationality() != null){
				juvenileProfileEventResponse.setNationalityDesc(juvenile.getNationality().getCode());
			}
			juvenileProfileEventResponse.setRace(juvenile.getRaceId());
			if(juvenile.getRace() != null){
				juvenileProfileEventResponse.setRaceDesc(juvenile.getRace().getCode());
			}
			juvenileProfileEventResponse.setSex(juvenile.getSexId());
			if(juvenile.getSex() != null){
				juvenileProfileEventResponse.setSexDesc(juvenile.getSex().getCode());
			}
			juvenileProfileEventResponse.setSSN(juvenile.getSIDNumber());
			if(juvenile.getCreateUserID() != null){
				juvenileProfileEventResponse.setCreateUser(juvenile.getCreateUserID());
			}
			if(juvenile.getCreateTimestamp() != null){
				juvenileProfileEventResponse.setCreateDate(new Date(juvenile.getCreateTimestamp().getTime()));
			}
			if(juvenile.getUpdateUserID() != null){
				juvenileProfileEventResponse.setUpdateUser(juvenile.getUpdateUserID());
			}
			if(juvenile.getUpdateTimestamp() != null){
				juvenileProfileEventResponse.setUpdateDate(new Date(juvenile.getUpdateTimestamp().getTime()));
			}
			if(juvenile.getCreateJIMS2UserID() != null){
				juvenileProfileEventResponse.setCreateJims2User(juvenile.getCreateJIMS2UserID());
			}
			if(juvenile.getUpdateJIMS2UserID() != null){
				juvenileProfileEventResponse.setUpdateJims2User(juvenile.getUpdateJIMS2UserID());
			}
			dispatch.postEvent(juvenileProfileEventResponse);
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
