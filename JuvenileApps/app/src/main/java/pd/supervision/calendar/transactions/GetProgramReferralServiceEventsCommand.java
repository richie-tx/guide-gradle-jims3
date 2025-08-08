//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\calendar\\transactions\\GetJuvenileAttendanceCommand.java

package pd.supervision.calendar.transactions;

import java.util.Iterator;

import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;
import pd.supervision.calendar.ServiceEvent;

public class GetProgramReferralServiceEventsCommand implements ICommand 
{
   
   /**
    * @roseuid 45702FB40066
    */
   public GetProgramReferralServiceEventsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850264
    */
	public void execute(IEvent event) 
	{
		Iterator iter = ServiceEvent.findAll(event);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while (iter.hasNext()) 
		{
			ServiceEvent servEvent = (ServiceEvent) iter.next();			
			CalendarServiceEventResponseEvent respEvent = new CalendarServiceEventResponseEvent();
			respEvent.setLocationId(servEvent.getJuvLocUnitId());

			JuvLocationUnit juvLocUnit = servEvent.getJuvLocUnit();
			if (juvLocUnit != null)
			{
				respEvent.setLocation(juvLocUnit.getLocationUnitName());
				respEvent.setJuvUnitCd(juvLocUnit.getJuvUnitCd());
			}
			respEvent.setEventId(servEvent.getServiceEventId());
			respEvent.setEventDate(servEvent.getEndDatetime());
			respEvent.setStartDatetime(servEvent.getStartDatetime());
			respEvent.setEndDatetime(servEvent.getEndDatetime());
			respEvent.setServiceName(servEvent.getServiceName());
			respEvent.setProgramReferralId(servEvent.getProgramReferralId());

			if(servEvent.getInstructorId() != null)
				respEvent.setServiceProviderId(new Integer(servEvent.getInstructorId()).intValue());
			
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
