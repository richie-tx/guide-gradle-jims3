//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\calendar\\transactions\\SaveServiceEventAttendanceCommand.java

package pd.supervision.calendar.transactions;

import java.util.Iterator;
import messaging.calendar.SaveServiceEventCancellationEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import naming.PDCalendarConstants;
import pd.supervision.calendar.ServiceEvent;
import pd.supervision.calendar.ServiceEventAttendance;

public class SaveServiceEventCancellationCommand implements ICommand 
{
   
   /**
    * @roseuid 45702FC1023B
    */
   public SaveServiceEventCancellationCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F4CE103A9
    */
   public void execute(IEvent event) {
   		SaveServiceEventCancellationEvent canEvent = (SaveServiceEventCancellationEvent)event;
	    String serviceEventId = canEvent.getServiceEventId();
		Iterator attenIter = ServiceEventAttendance.findAll("serviceEventId",serviceEventId);
		while (attenIter.hasNext()) {
			ServiceEventAttendance eventAttendance = (ServiceEventAttendance) attenIter.next();
			eventAttendance.setAttendanceStatusCd(PDCalendarConstants.JUV_ATTEND_STATUS_CANCELLED);
		}
		ServiceEvent serv = (ServiceEvent)ServiceEvent.find(serviceEventId);
		serv.setEventStatusId(PDCalendarConstants.SERVICE_EVENT_STATUS_CANCELLED);		
   }

   /**
    * @param event
    * @roseuid 456F4CE103AB
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F4CE103B9
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 456F4CE103C8
    */
   public void update(Object anObject) 
   {
    
   }
   
}
