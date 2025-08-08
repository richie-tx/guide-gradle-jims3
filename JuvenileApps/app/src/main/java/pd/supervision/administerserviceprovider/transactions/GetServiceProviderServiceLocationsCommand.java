//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\GetServiceProviderServiceLocationsCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.administerserviceprovider.GetServiceProviderServiceLocationsEvent;
import messaging.administerserviceprovider.reply.ServiceTypeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.administerserviceprovider.PDAdminsterServiceProviderHelper;

public class GetServiceProviderServiceLocationsCommand implements ICommand
{

    /**
     * @roseuid 44805C7E0077
     */
    public GetServiceProviderServiceLocationsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 447F4996016E
     */
    public void execute(IEvent event)
    {
	GetServiceProviderServiceLocationsEvent spsLocationsEvent = (GetServiceProviderServiceLocationsEvent) event;

	Iterator serviceLocationIterator = JuvenileServiceProvider.findAll(spsLocationsEvent);

	HashMap map = new HashMap();
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	//REWRITE BELOW
	/*while (serviceLocationIterator .hasNext())
	{
		JuvenileServiceProvider juvenileServiceProvider = (JuvenileServiceProvider)serviceLocationIterator .next();			
		
		if (Integer.parseInt(juvenileServiceProvider.getServiceProviderId())!=spsLocationsEvent.getServiceProviderId()){				
			continue;
		}
		
		if (spsLocationsEvent.isInHouse() && !juvenileServiceProvider.getInHouse()){
			continue;
		}			
			if(!map.containsKey("" + juvenileServiceProvider.getJuvLocUnitId())){
				LocationResponseEvent serviceLocationResponseEvent = 
						PDAdminsterServiceProviderHelper.getServiceLocationResponseEvent(juvenileServiceProvider);		
				ServiceTypeResponseEvent resp = this.setServiceTypeResponseEvent(juvenileServiceProvider);
				Collection col = new ArrayList();
				col.add(resp);
				serviceLocationResponseEvent.setServiceTypeResponseEvents(col);	
				map.put("" + juvenileServiceProvider.getJuvLocUnitId(),serviceLocationResponseEvent);
			}else{
				LocationResponseEvent serviceLocationResponseEvent = (LocationResponseEvent) map.get("" + juvenileServiceProvider.getJuvLocUnitId());
				Collection col = serviceLocationResponseEvent.getServiceTypeResponseEvents();
				if(!isServiceTypeExistInList(col,juvenileServiceProvider.getServiceTypeId())){
				    ServiceTypeResponseEvent resp = this.setServiceTypeResponseEvent(juvenileServiceProvider);
					col.add(resp);
		         	serviceLocationResponseEvent.setServiceTypeResponseEvents(col);
		         	map.put("" + juvenileServiceProvider.getJuvLocUnitId(),serviceLocationResponseEvent);
				}
			}
		
	}
	PDAdminsterServiceProviderHelper.sendServiceLocationResponseEvent(map);*/
	while (serviceLocationIterator.hasNext())
	{
	    JuvenileServiceProvider juvenileServiceProvider = (JuvenileServiceProvider) serviceLocationIterator.next();
	    LocationResponseEvent serviceLocationResponseEvent = PDAdminsterServiceProviderHelper.getServiceLocationResponseEvent(juvenileServiceProvider);

	    if (!map.containsKey("" + juvenileServiceProvider.getJuvLocUnitId()))
	    {
		serviceLocationResponseEvent = PDAdminsterServiceProviderHelper.getServiceLocationResponseEvent(juvenileServiceProvider);
		ServiceTypeResponseEvent resp = this.setServiceTypeResponseEvent(juvenileServiceProvider);
		Collection col = new ArrayList();
		col.add(resp);
		serviceLocationResponseEvent.setServiceTypeResponseEvents(col);
		map.put("" + juvenileServiceProvider.getJuvLocUnitId(), serviceLocationResponseEvent);
	    }
	    else
	    {
		serviceLocationResponseEvent = (LocationResponseEvent) map.get("" + juvenileServiceProvider.getJuvLocUnitId());
		Collection col = serviceLocationResponseEvent.getServiceTypeResponseEvents();
		if (!isServiceTypeExistInList(col, juvenileServiceProvider.getServiceTypeId()))
		{
		    ServiceTypeResponseEvent resp = this.setServiceTypeResponseEvent(juvenileServiceProvider);
		    col.add(resp);
		    serviceLocationResponseEvent.setServiceTypeResponseEvents(col);
		    map.put("" + juvenileServiceProvider.getJuvLocUnitId(), serviceLocationResponseEvent);
		}
	    }
	    //dispatch.postEvent(serviceLocationResponseEvent);

	}
	PDAdminsterServiceProviderHelper.sendServiceLocationResponseEvent(map);
    }

    /**
     * @param col
     * @param serviceTypeId
     * @return
     */
    private boolean isServiceTypeExistInList(Collection col, String serviceTypeId)
    {
	Iterator iter = col.iterator();
	while (iter.hasNext())
	{
	    ServiceTypeResponseEvent resp = (ServiceTypeResponseEvent) iter.next();
	    if (resp.getServiceTypeId().equalsIgnoreCase(serviceTypeId))
	    {
		return true;
	    }
	}
	return false;
    }

    /**
     * @param juvenileServiceProvider
     */
    private ServiceTypeResponseEvent setServiceTypeResponseEvent(JuvenileServiceProvider juvenileServiceProvider)
    {
	ServiceTypeResponseEvent resp = new ServiceTypeResponseEvent();
	resp.setServiceTypeId(juvenileServiceProvider.getServiceTypeId());
	resp.setServiceTypeName(juvenileServiceProvider.getEventType().getDescription());
	return resp;
    }

    /**
     * @param event
     * @roseuid 447F49960170
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 447F4996017D
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 447F4996017F
     */
    public void update(Object anObject)
    {

    }
}