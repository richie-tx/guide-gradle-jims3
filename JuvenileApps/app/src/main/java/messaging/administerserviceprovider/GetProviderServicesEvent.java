//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetProviderServicesEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetProviderServicesEvent extends RequestEvent 
{
   public String serviceName;
   public String serviceTypeId;
   public String statusId;
   public String agencyId;
   
   /**
    * @roseuid 450ACF6100E3
    */
   public GetProviderServicesEvent() 
   {
    
   }
/**
 * @return Returns the serviceName.
 */
public String getServiceName() {
	return serviceName;
}
/**
 * @param serviceName The serviceName to set.
 */
public void setServiceName(String serviceName) {
	this.serviceName = serviceName;
}
/**
 * @return Returns the serviceTypeId.
 */
public String getServiceTypeId() {
	return serviceTypeId;
}
/**
 * @param serviceTypeId The serviceTypeId to set.
 */
public void setServiceTypeId(String serviceTypeId) {
	this.serviceTypeId = serviceTypeId;
}
/**
 * @return Returns the statusId.
 */
public String getStatusId() {
	return statusId;
}
/**
 * @param statusId The statusId to set.
 */
public void setStatusId(String statusId) {
	this.statusId = statusId;
}
/**
 * @return Returns the agencyId.
 */
public String getAgencyId() {
	return agencyId;
}
/**
 * @param agencyId The agencyId to set.
 */
public void setAgencyId(String agencyId) {
	this.agencyId = agencyId;
}
}
