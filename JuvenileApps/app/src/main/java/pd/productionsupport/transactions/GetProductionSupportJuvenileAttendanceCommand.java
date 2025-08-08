//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\calendar\\transactions\\GetJuvenileAttendanceCommand.java

package pd.productionsupport.transactions;

import java.util.Iterator;

import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.productionsupport.GetProductionSupportJuvenileAttendanceEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.km.util.Name;
import pd.supervision.calendar.ServiceEventAttendance;

public class GetProductionSupportJuvenileAttendanceCommand implements ICommand 
{
   
   /**
    * @roseuid 45702FB40066
    */
   public GetProductionSupportJuvenileAttendanceCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850264
    */
	public void execute(IEvent event) {
		
		GetProductionSupportJuvenileAttendanceEvent attEv = (GetProductionSupportJuvenileAttendanceEvent) event;		
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
			if(juv != null){
				Name name = new Name(juv.getFirstName(), juv.getMiddleName(), juv.getLastName());
				respEvent.setJuvenileName(name.getFormattedName());
			}
			
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
