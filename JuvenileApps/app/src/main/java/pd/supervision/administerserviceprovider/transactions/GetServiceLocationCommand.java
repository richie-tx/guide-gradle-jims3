//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\GetServiceProviderServicesCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.HashMap;
import java.util.Iterator;

import messaging.administerserviceprovider.GetServiceLocationEvent;
import messaging.administerserviceprovider.reply.ServiceLocationResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.ServiceLocation;

/*
 * author: mchowdhury 
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetServiceLocationCommand implements ICommand 
{
   
   /**
    * @roseuid 44805C7E0327
    */
   public GetServiceLocationCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 447F49A502A4
    */
   public void execute(IEvent event) 
   {
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	    GetServiceLocationEvent sps = (GetServiceLocationEvent) event;
		Iterator spIter = ServiceLocation.findAll(sps);
	    while(spIter.hasNext()){
			ServiceLocation sl = (ServiceLocation) spIter.next();
			if(sl != null){
				ServiceLocationResponseEvent slEvent = new ServiceLocationResponseEvent();
				slEvent.setLocationId(sl.getOID().toString());
				dispatch.postEvent(slEvent);
			}
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