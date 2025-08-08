//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\calendar\\transactions\\GetJuvenileAttendanceCommand.java

package pd.supervision.calendar.transactions;

import java.util.Iterator;

import naming.PDCalendarConstants;

import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.calendar.ServiceEventAttendance;

public class GetProgramAttendanceCommand implements ICommand 
{
   
   /**
    * @roseuid 45702FB40066
    */
   public GetProgramAttendanceCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850264
    */
	public void execute(IEvent event) 
	{
		Iterator attenIter = ServiceEventAttendance.findAll(event);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		while( attenIter.hasNext() ) 
		{
			ServiceEventAttendance eventAttendance = (ServiceEventAttendance)attenIter.next();
			if( eventAttendance.getAttendanceStatusCd().equals(PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT) ||
					eventAttendance.getAttendanceStatusCd().equals(PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED) ||
					eventAttendance.getAttendanceStatusCd().equals(PDCalendarConstants.JUV_ATTEND_STATUS_ATTENDED))
			{
				ServiceEventAttendanceResponseEvent respEvent = eventAttendance.getServiceAttendanceResponseEvent();
				dispatch.postEvent(respEvent);
			}			
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
