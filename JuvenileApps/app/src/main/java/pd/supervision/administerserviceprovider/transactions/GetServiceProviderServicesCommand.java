//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\GetServiceProviderServicesCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import messaging.administerserviceprovider.GetServiceProviderServicesEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;

public class GetServiceProviderServicesCommand implements ICommand
{
    /**
     * @roseuid 44805C7E0327
     */
    public GetServiceProviderServicesCommand()
    {
    }

    /**
     * @param event
     * @roseuid 447F49A502A4
     */
    public void execute(IEvent event)
    {
	GetServiceProviderServicesEvent sps = (GetServiceProviderServicesEvent) event;
	HashMap map = new HashMap();

	Iterator spIter = JuvenileServiceProvider.findAll(sps);
	while (spIter.hasNext())
	{
	    JuvenileServiceProvider sp = (JuvenileServiceProvider) spIter.next();
	    if (sp != null)
	    {
		if (sps.getInHouse() == 0 && Integer.parseInt(sp.getServiceProviderId()) != sps.getServiceProviderId())
		{ // if not in-house and provider ID's dont match
		    continue;
		}

		if (!map.containsKey(sp.getServiceProviderId()))
		{
		    ServiceProviderResponseEvent spEvent = new ServiceProviderResponseEvent();
		    spEvent.setJuvServProviderName(sp.getServiceProviderName());
		    spEvent.setJuvServProviderId(sp.getServiceProviderId());
		    //spEvent.setProgramId(sp.getProviderProgramId());

		    Collection col = new ArrayList();
		    col.add(this.createServiceResponseEvent(sp));
		    spEvent.setServiceResponseEvents(col);
		    map.put(sp.getServiceProviderId(), spEvent);
		}
		else
		{
		    ServiceProviderResponseEvent oldSpEvent = (ServiceProviderResponseEvent) map.get(sp.getServiceProviderId());
		    Collection col = oldSpEvent.getServiceResponseEvents();
		    col.add(this.createServiceResponseEvent(sp));
		    oldSpEvent.setServiceResponseEvents(col);
		}
	    }
	}

	this.postResponseEvent(map);
    }

    /**
     * @param sp
     * @return resp
     */
    private ServiceResponseEvent createServiceResponseEvent(JuvenileServiceProvider sp)
    {
	ServiceResponseEvent resp = new ServiceResponseEvent();

	resp.setServiceName(sp.getServiceName());
	resp.setServiceId("" + sp.getServiceId());
	resp.setMaxEnrollment(sp.getMaxEnrollment());
	resp.setProgramStartDate(sp.getProgramStartDate());
	resp.setProgramEndDate(sp.getProgramEndDate());
	resp.setProgramId(String.valueOf(sp.getProviderProgramId()));

	return resp;
    }

    /**
     * @param map
     */
    private void postResponseEvent(HashMap map)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator iter = map.values().iterator();
	while (iter.hasNext())
	{
	    ServiceProviderResponseEvent spEvent = (ServiceProviderResponseEvent) iter.next();
	    dispatch.postEvent(spEvent);
	}
    }

    /**
     * @param event
     * @roseuid 447F49A502A6
     */
    public void onRegister(IEvent event)
    {
    }

    /**
     * @param event
     * @roseuid 447F49A502B2
     */
    public void onUnregister(IEvent event)
    {
    }

    /**
     * @param anObject
     * @roseuid 447F49A502B4
     */
    public void update(Object anObject)
    {
    }
}