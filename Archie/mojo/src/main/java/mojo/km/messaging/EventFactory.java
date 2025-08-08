package mojo.km.messaging;

import mojo.km.config.MojoProperties;
import mojo.km.config.ServiceProperties;

import mojo.km.messaging.exception.EventNotFoundException;

/**
 * Responsible for creating event by passing a service name to the factory to produce the required event.
 * @author Eric A Amundson
 * @modelguid {C6C7813E-4F0E-4F43-A17E-3D4F056A0C02}
 */
public class EventFactory {
    /**
     * Return a default contructed event.  It will return null if no event related to name.
     * @param serviceName - the service the event is related.
     * @return
     * @pre mojo.km.config.MojoProperties.getInstance().getService(serviceName) != null
     * @modelguid {15ED409A-0CB9-4F11-B1F8-172BD546A027}
     */
    public static IEvent getInstance(String serviceName) {
		//IEvent event = new NoReply();
		IEvent event = null;
		MojoProperties props = MojoProperties.getInstance();
		ServiceProperties lService = props.getService(serviceName);
		if (lService != null) { 
			String eventName = lService.getEventName();
			try {
				Class eventClass = Class.forName(eventName);
				event = (IEvent) eventClass.newInstance();
			} catch (ClassNotFoundException e) {
				//e.printStackTrace();
				throw new EventNotFoundException(serviceName + " not found");
			} catch (InstantiationException e)
			{
				//e.printStackTrace();
				throw new EventNotFoundException(serviceName + " not found");
			} catch (IllegalAccessException e)
			{
				//e.printStackTrace();
				throw new EventNotFoundException(serviceName + " not found");
			}			
		}
		// Throw EventNotFoundException due to a problem not being 
		// able to located the service/event
		if (event == null) {
			throw new EventNotFoundException(serviceName + " not found");
		}
        return event;
    }

    /**
     * Return a event will values set from the EventStubData.xml file.  It will return null if no event related to name.
     * @param serviceName - the service the event is related.
     * @return  
     * @modelguid {1EF6A46C-D51F-44C7-8F61-C3AB7676533F}
     */

}
