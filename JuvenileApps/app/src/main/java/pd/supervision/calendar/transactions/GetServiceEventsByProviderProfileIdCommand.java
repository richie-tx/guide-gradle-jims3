/*
 * Created on Oct 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.calendar.transactions;

import java.util.ArrayList;
import java.util.Iterator;

import messaging.administerserviceprovider.GetServiceProviderFromServiceIdEvent;
import messaging.calendar.GetServiceEventsByProviderProfileIdEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.calendar.ServiceEventContext;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetServiceEventsByProviderProfileIdCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) {
		GetServiceEventsByProviderProfileIdEvent reqEvent = (GetServiceEventsByProviderProfileIdEvent)event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		Iterator iter = ServiceEventContext.findAll(reqEvent);
/*
		while(iter.hasNext()){
			ServiceEventContext serviceEvent = (ServiceEventContext) iter.next();		
			ServiceEventResponseEvent respEvent = new ServiceEventResponseEvent();  
			respEvent.setStartDateTime(serviceEvent.getStartDatetime());
			dispatch.postEvent(respEvent);	 
		}
*/
		while (iter.hasNext()) 
		{
			ServiceEventContext e = (ServiceEventContext) iter.next();
			
			GetServiceProviderFromServiceIdEvent  getServiceProviderFromServiceIdEvent = 
				new GetServiceProviderFromServiceIdEvent();
			ArrayList serviceIdList = new ArrayList();
			serviceIdList.add(e.getServiceId()+"");
			getServiceProviderFromServiceIdEvent.setServiceIdList(serviceIdList);
			JuvenileServiceProvider jsp = null;
			Iterator iterSP =  JuvenileServiceProvider.findAll(getServiceProviderFromServiceIdEvent);			
			while (iterSP.hasNext()){
				jsp = (JuvenileServiceProvider)iterSP.next();				
				CalendarServiceEventResponseEvent resp = new CalendarServiceEventResponseEvent();
		
				if(e.getStartDatetime() != null) {
					resp.setStartDatetime(e.getStartDatetime());
				}
				resp.setServiceProviderId(Integer.parseInt(jsp.getServiceProviderId()));
				resp.setServiceProviderName(jsp.getServiceProviderName());
				resp.setProgramName(jsp.getProgramName());
				resp.setServiceName(jsp.getServiceName());
				resp.setInstructorName(e.getInstructor().getLastName()+", "+e.getInstructor().getFirstName());				
				dispatch.postEvent(resp);			
			}				    		
		}
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub
		
	}

}
