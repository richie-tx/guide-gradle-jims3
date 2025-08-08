//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\calendar\\transactions\\SaveServiceEventAttendanceCommand.java

package pd.productionsupport.transactions;

import java.util.Iterator;

import messaging.productionsupport.SaveProductionSupportServiceEventAttendanceEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.calendar.ServiceEventAttendance;
import messaging.calendar.GetJuvenileAttendanceEvent;

public class SaveProductionSupportServiceEventAttendanceCommand implements ICommand
{
	/**
	 * @roseuid 45702FC1023B
	 */
	public SaveProductionSupportServiceEventAttendanceCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 456F4CE103A9
	 */
	public void execute( IEvent event ){
		SaveProductionSupportServiceEventAttendanceEvent attEvent = (SaveProductionSupportServiceEventAttendanceEvent)event;
		String serviceEventId = attEvent.getServiceEventId();
		String juvenileId = attEvent.getJuvenileId();
		// create unique event for mapping call to get specific attendance event
		GetJuvenileAttendanceEvent getAttendanceEvent = new GetJuvenileAttendanceEvent();
		getAttendanceEvent.setServiceEventId(serviceEventId);
		getAttendanceEvent.setJuvenileId(juvenileId);
		
//		Iterator<ServiceEventAttendance> attenIter = 
//				ServiceEventAttendance.findAll( "serviceEventId", serviceEventId );
		Iterator<ServiceEventAttendance> attenIter = ServiceEventAttendance.findAll(getAttendanceEvent);
		// update the result events
		while(attenIter.hasNext()){
			ServiceEventAttendance eventAttendance = attenIter.next();
			if(attEvent.getAddlAttendees() != null && !attEvent.getAddlAttendees().equals(eventAttendance.getAddlAttendees())){
				eventAttendance.setAddlAttendees(attEvent.getAddlAttendees().intValue());
			}else{
				eventAttendance.setAddlAttendees(eventAttendance.getAddlAttendees());
			}
			if(attEvent.getAttendanceStatusCd() != null){
				eventAttendance.setAttendanceStatusCd(attEvent.getAttendanceStatusCd());
			}else{
				eventAttendance.setAttendanceStatusCd(eventAttendance.getAttendanceStatusCd());
			}
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
