/*
 * Created on Oct 5, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.transactions;


import java.util.Iterator;
import naming.PDAdministerServiceProviderConstants;
import pd.supervision.administerserviceprovider.Service;
import messaging.administerserviceprovider.GetServicesByProgramEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
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
public class GetServicesByProgramCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) {
		GetServicesByProgramEvent reqEvent = (GetServicesByProgramEvent)event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		Iterator iter = Service.findAll(PDAdministerServiceProviderConstants.PROVIDERPROGRAM_ID, reqEvent.getProviderProgramId());
	    while(iter.hasNext()){
			Service service = (Service) iter.next();
			if(service != null){		
				ServiceResponseEvent respEvent = new ServiceResponseEvent(); 
				respEvent.setServiceId(service.getOID().toString());
				respEvent.setServiceName(service.getServiceName());
				respEvent.setServiceCode(service.getServiceCode());
				respEvent.setServiceTypeId(service.getServiceTypeId());
				respEvent.setMaxEnrollment((new Integer(service.getMaxEnrollment())).toString());
				respEvent.setCost(service.getCost());
				respEvent.setRateId(service.getRateId());
				respEvent.setDescription(service.getDescription());
				respEvent.setStatusId(service.getStatusId());
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
