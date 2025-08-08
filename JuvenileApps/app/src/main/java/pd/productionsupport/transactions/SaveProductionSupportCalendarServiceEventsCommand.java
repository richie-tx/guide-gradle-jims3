package pd.productionsupport.transactions;

import messaging.productionsupport.SaveProductionSupportCalendarServiceEventsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.calendar.ServiceEvent;

public class SaveProductionSupportCalendarServiceEventsCommand implements ICommand
{
	/**
	 * @roseuid 45702FC1023B
	 */
	public SaveProductionSupportCalendarServiceEventsCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 456F4CE103A9
	 */
	public void execute( IEvent event ){
		SaveProductionSupportCalendarServiceEventsEvent calEvent = (SaveProductionSupportCalendarServiceEventsEvent)event;
		String serviceEventId = calEvent.getServiceEventId();

		ServiceEvent serviceEvent = (ServiceEvent) ServiceEvent.find(serviceEventId);
			if(calEvent.getMaxAttendance() != null && !calEvent.getMaxAttendance().equals(serviceEvent.getEventMaximum())){
				serviceEvent.setEventMaximum(new Integer(calEvent.getMaxAttendance()));
			}
			if(calEvent.getEventStatusCode() != null && !calEvent.getEventStatusCode().equals(serviceEvent.getEventStatusId())){
				serviceEvent.setEventStatusId(calEvent.getEventStatusCode());
			}
			if(calEvent.getJuvlocationUnitId() != null && !calEvent.getJuvlocationUnitId().equals(serviceEvent.getJuvLocUnitId())){
				serviceEvent.setJuvLocUnitId(calEvent.getJuvlocationUnitId());
			}
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
