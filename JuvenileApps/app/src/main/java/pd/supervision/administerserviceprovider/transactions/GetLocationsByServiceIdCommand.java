/*
 * Created on Oct 6, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

//Modified 01/11/07 UGopinath - Change Location to Location Unit
package pd.supervision.administerserviceprovider.transactions;

import java.util.Iterator;

import naming.PDAdministerServiceProviderConstants;
import pd.supervision.administerserviceprovider.ServiceLocation;
import pd.supervision.administerserviceprovider.administerlocation.Location;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;
import messaging.administerserviceprovider.GetLocationsByServiceIdEvent;
import messaging.administerserviceprovider.reply.ServiceLocationResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetLocationsByServiceIdCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) {
		GetLocationsByServiceIdEvent reqEvent = (GetLocationsByServiceIdEvent)event; 
		
		Iterator iter = 
			ServiceLocation.findAll(PDAdministerServiceProviderConstants.SERVICE_ID, reqEvent.getServiceId());
				
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while(iter.hasNext()){
			ServiceLocation serviceLocation = (ServiceLocation) iter.next();
			if(serviceLocation != null){
				ServiceLocationResponseEvent respEvent = new ServiceLocationResponseEvent();
				/*Location location = null;
				if(serviceLocation.getLocationId() != null || !serviceLocation.getLocationId().equals("")) {
					location = Location.find(serviceLocation.getLocationId());
				}
				if(location != null) {
					respEvent.setLocationName(location.getLocationName());	
				}
				respEvent.setLocationId(serviceLocation.getLocationId());*/
				JuvLocationUnit locUnit = null;
				if(serviceLocation.getJuvLocUnitId() != null)
				{
					if(!"".equals(serviceLocation.getJuvLocUnitId())) {
					locUnit = JuvLocationUnit.find(serviceLocation.getJuvLocUnitId());
					}
				}
				if(locUnit != null) {
					respEvent.setLocationUnitName(locUnit.getLocationUnitName());	
				}
				respEvent.setJuvLocUnitId(serviceLocation.getJuvLocUnitId());
				
				dispatch.postEvent(respEvent);
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
