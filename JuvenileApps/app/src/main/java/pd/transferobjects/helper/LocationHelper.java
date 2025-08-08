/*
 * Created on Aug 29, 2007
 *
 */
package pd.transferobjects.helper;

import messaging.transferobjects.LocationTO;
import messaging.transferobjects.TransferObjectInterface;
import mojo.km.persistence.PersistentObject;
import pd.supervision.administerserviceprovider.administerlocation.Location;

/**
 * @author cc_mdsouza
 *
 */
public class LocationHelper {

	
	public static LocationTO initializeTransferObjectFromPersistentObject( PersistentObject persistentObject ) 
	throws Exception 
	{
	  LocationTO locationTO = new LocationTO() ; 
	  try
	  {
	  	Location location = (Location) persistentObject ; 
	  	
	  	locationTO.addressId = location.getAddressId() ; 
	  	locationTO.locationName = location.getLocationName() ; 
	  	locationTO.agencyId = location.getAgencyId() ; 
	  	locationTO.locationId = location.getLocationId() ; 
	  	locationTO.inHouse = location.getInHouse() ; 
	  	locationTO.statusId = location.getStatusId() ; 
	  	locationTO.serviceProviderName = location.getServiceProviderName() ; 
	  	locationTO.facilityTypeId = location.getFacilityTypeId() ; 
	  	locationTO.phoneNumber = location.getPhoneNumber() ; 
	  	locationTO.streetName = location.getStreetName() ; 
	  	locationTO.streetNum = location.getStreetNum() ; 
	  	locationTO.city = location.getCity() ; 
	  	locationTO.aptNumber = location.getAptNumber() ; 
	  	locationTO.stateId = location.getStateId() ; 
	  	locationTO.zipCode = location.getZipCode() ; 
	  	locationTO.locationCd = location.getLocationCd() ; 
	  	
// 		TODO : Add in code to copy over the Address and Code objects from the 		
//	  	private Address address = null;
//		private Code status = null;
//		private Code facilityType;
	  	
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  	throw new Exception(e) ; 
	  }
	  return locationTO ; 
	} 
	
	public void putTransferObjectToPersistentObject ( TransferObjectInterface transferObjectInterface ) 
	throws Exception 
	{
	  try
	  {
	  	throw new UnsupportedOperationException() ; 
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  	throw new Exception(e) ; 
	  }
		
	} 
	
	
	
}
