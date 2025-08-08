//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\calendar\\transactions\\GetJuvenileAttendanceCommand.java

package pd.supervision.calendar.transactions;

import java.util.Iterator;
import messaging.calendar.GetServiceEventAttendanceEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.km.util.Name;
import pd.supervision.calendar.ServiceEventAttendance;

public class GetServiceEventAttendanceCommand implements ICommand 
{
   
   /**
    * @roseuid 45702FB40066
    */
   public GetServiceEventAttendanceCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850264
    */
	public void execute(IEvent event) {
		
		GetServiceEventAttendanceEvent attEv = (GetServiceEventAttendanceEvent) event;		
		String serviceEventId = attEv.getServiceEventId();

		Iterator attendIter = ServiceEventAttendance.findAll("serviceEventId", serviceEventId);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		while (attendIter.hasNext()) {
			ServiceEventAttendance eventAttendance = (ServiceEventAttendance) attendIter.next();
			ServiceEventAttendanceResponseEvent respEvent = new ServiceEventAttendanceResponseEvent();
			respEvent = eventAttendance.getServiceAttendanceResponseEvent();
			// Profile stripping fix - task 97643			
			JuvenileCore juv = eventAttendance.getJuvenile();
			//
			if (juv != null) {
				Name name = new Name(juv.getFirstName(), juv.getMiddleName(), juv.getLastName());
				respEvent.setJuvenileName(name.getFormattedName());
				respEvent.setJuvenileId(juv.getJuvenileNum()); //added for #36737
				//Defect Fix: JIMS200076767 starts. 
				//Dispatching the events only for juvenile non-sealed records(with data),hence juv details cannot be null.
				dispatch.postEvent(respEvent);
				//Defect Fix: JIMS200076767 ends. 
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
