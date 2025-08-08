//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\GetProviderServicesCommand.java
//Modified 01/11/07 UGopinath - Changed Location to Location Unit (ER#35164)

package pd.supervision.administerserviceprovider.transactions;

import java.util.Iterator;

import messaging.administerserviceprovider.GetServiceByServiceIdEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.Service;
import pd.supervision.administerserviceprovider.ServiceLocation;
//import pd.supervision.administerserviceprovider.administerlocation.Location;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class GetServiceByServiceIdCommand implements ICommand 
{
   
   /**
    * @roseuid 450ACDB1009C
    */
   public GetServiceByServiceIdCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 450AA17902C3
    */
   public void execute(IEvent event) 
   {
	   	GetServiceByServiceIdEvent reqEvent = (GetServiceByServiceIdEvent)event;
	
		Service service = Service.find(reqEvent.getServiceId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		if(service != null){
			ServiceResponseEvent respEvent = new ServiceResponseEvent();
		    respEvent.setServiceName(service.getServiceName());
		    respEvent.setServiceId(service.getServiceId());
		    pd.codetable.criminal.JuvenileEventTypeCode code = service.getServiceType();
			if(service.getServiceTypeId() != null && !service.getServiceTypeId().equals("") && code != null){
				respEvent.setServiceType(code.getDescription());
			}
			respEvent.setServiceCode(service.getServiceCode());
			StringBuffer loc = new StringBuffer();
			Iterator locIter = ServiceLocation.findAll("serviceId", service.getServiceId());
			/*while(locIter.hasNext()){
				ServiceLocation servLoc =  (ServiceLocation) locIter.next();
				if(servLoc != null){
					Location location = servLoc.getLocation();
					if(location != null){
						loc.append(location.getLocationName());
						if(locIter.hasNext()){
							loc.append(", ");
						}
					}
				}
			}*/
			
			while(locIter.hasNext()){
				ServiceLocation servLoc =  (ServiceLocation) locIter.next();
				if(servLoc != null){
					JuvLocationUnit locUnit = servLoc.getLocationUnit();
					if(locUnit != null){
						loc.append(locUnit.getLocationUnitName());
						if(locIter.hasNext()){
							loc.append(", ");
						}
					}
				}
			}
			respEvent.setLocationName(loc.toString());
			respEvent.setCost(service.getCost());
			respEvent.setMaxEnrollment(new Integer(service.getMaxEnrollment()).toString());
			respEvent.setRateId(service.getRateId());
			respEvent.setDescription(service.getDescription());
			respEvent.setStatusId(service.getStatusId());
			dispatch.postEvent(respEvent);	 
		}    
   }
   
   /**
    * @param event
    * @roseuid 450AA17902D0
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 450AA17902D2
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 450AA17902E1
    */
   public void update(Object anObject) 
   {
    
   }
   
  
}
