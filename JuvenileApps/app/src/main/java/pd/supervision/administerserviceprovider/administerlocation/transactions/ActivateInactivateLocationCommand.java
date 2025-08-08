//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administerlocation\\transactions\\InactivateLocationCommand.java

package pd.supervision.administerserviceprovider.administerlocation.transactions;

import java.util.Iterator;

import messaging.administerlocation.ActivateInactivateLocationEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.ServiceLocation;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;
import pd.supervision.administerserviceprovider.administerlocation.Location;

public class ActivateInactivateLocationCommand implements ICommand 
{
   
   /**
    * @roseuid 451156BB0125
    */
   public ActivateInactivateLocationCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45104B4B0170
    */
   public void execute(IEvent event) 
   {
	   ActivateInactivateLocationEvent locationEvent = (ActivateInactivateLocationEvent) event;
	   String locationId =  locationEvent.getLocationId();
	   String action = locationEvent.getAction();
	   if (locationId!=null && !locationId.equals("")){
		   Location location = Location.find(locationId);
		   if(location != null){
				//<KISHORE>JIMS200060421 : Inactivating a location removes agency code
			   location.setAgencyId(locationEvent.getAgencyId());
			   if (action.equals("activate")){
				   location.setStatusId("A");
			   }else if (action.equals("inactivate")){
				   location.setStatusId("I");			
				   Iterator i = JuvLocationUnit.findAll("locationId",locationId);	
				   while(i.hasNext())
				   {
					   JuvLocationUnit juvUnit = (JuvLocationUnit)i.next();
					   juvUnit.setUnitStatusId("I");
					   Iterator iter = ServiceLocation.findAll("juvLocUnitId",(String)juvUnit.getOID());
					   while (iter.hasNext()){
						   ServiceLocation serviceLocation = (ServiceLocation)iter.next();
						   serviceLocation.delete();
					   }
				   }
			   }	
		   }
	   }	    
   }
   
   /**
    * @param event
    * @roseuid 45104B4B0172
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45104B4B017F
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45104B4B0181
    */
   public void update(Object anObject) 
   {
    
   }
   
}
