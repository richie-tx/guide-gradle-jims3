//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administerlocation\\transactions\\CreateUpdateLocationCommand.java

package pd.supervision.administerserviceprovider.administerlocation.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.address.AddressRequestEvent;
import messaging.administerlocation.CreateUpdateLocationEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.utilities.MessageUtil;
import pd.address.Address;
import pd.address.PDAddressHelper;
import pd.supervision.administerserviceprovider.administerlocation.Location;

public class CreateUpdateLocationCommand implements ICommand 
{
   
   /**
    * @roseuid 451156B502AB
    */
   public CreateUpdateLocationCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45104B4A0316
    */
   public void execute(IEvent event) {
		CreateUpdateLocationEvent locationEvent = (CreateUpdateLocationEvent) event;
		String locationId =  locationEvent.getLocationId();
		if (locationId==null ||  locationId.equals("")) {
			Location location = new Location();
			location.setLocation(locationEvent);
			this.updateAddress(locationEvent, location);
			location.createOID();				
		} else {
			Location location = Location.find(locationId);
			location.setLocation(locationEvent);
			this.updateAddress(locationEvent, location);		
		}
	}
   
   /**
    * @param event
    * @roseuid 45104B4A0318
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45104B4A031A
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45104B4A0326
    */
   public void update(Object anObject) 
   {
    
   }
   
	private void updateAddress(CreateUpdateLocationEvent locationEvent,Location location)
	{
		Collection addressRequests = MessageUtil.compositeToCollection(locationEvent, AddressRequestEvent.class);
		Iterator iter = addressRequests.iterator();
		while(iter.hasNext()){
			AddressRequestEvent addressRequestEvent = (AddressRequestEvent) iter.next();			
			Address address = location.getAddress();				
			if(address == null){
				address = new Address();   	
			}
			address = PDAddressHelper.getAddress(addressRequestEvent,address); 
			new Home().bind(address);
			location.setAddressId(address.getAddressId());			
		}
	}


}
