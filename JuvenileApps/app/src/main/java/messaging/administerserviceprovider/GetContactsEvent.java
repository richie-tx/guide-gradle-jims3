//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetContactsEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetContactsEvent extends RequestEvent 
{
   public String serviceProviderId;
   
   /**
    * @roseuid 450ACF500085
    */
   public GetContactsEvent() 
   {
    
   }
   
/**
 * @return Returns the serviceProviderId.
 */
public String getServiceProviderId() {
	return serviceProviderId;
}
/**
 * @param serviceProviderId The serviceProviderId to set.
 */
public void setServiceProviderId(String serviceProviderId) {
	this.serviceProviderId = serviceProviderId;
}
}
