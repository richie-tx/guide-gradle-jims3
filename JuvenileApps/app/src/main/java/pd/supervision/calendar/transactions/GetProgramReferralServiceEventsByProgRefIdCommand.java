//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\calendar\\transactions\\GetJuvenileAttendanceCommand.java

package pd.supervision.calendar.transactions;

import java.util.Iterator;

import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.calendar.ServiceEvent;

public class GetProgramReferralServiceEventsByProgRefIdCommand implements ICommand 
{
   
   /**
    * @roseuid 45702FB40066
    */
   public GetProgramReferralServiceEventsByProgRefIdCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850264
    */
   public void execute(IEvent event) {

		Iterator iter = ServiceEvent.findAll(event);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while (iter.hasNext()) {
			ServiceEvent servEvent = (ServiceEvent) iter.next();
			CalendarServiceEventResponseEvent respEvent = new CalendarServiceEventResponseEvent();
			respEvent.setEventId(servEvent.getServiceEventId());
			respEvent.setEventDate(servEvent.getStartDatetime());
			respEvent.setServiceName(servEvent.getServiceName());
			respEvent.setEventStatusCode(servEvent.getEventStatusId());
			dispatch.postEvent(respEvent);
		}
	}
   
   /**
    * @param event
    * @roseuid 456F2D850272
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850274
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 456F2D850276
    */
   public void update(Object anObject) 
   {
    
   }
   

}
