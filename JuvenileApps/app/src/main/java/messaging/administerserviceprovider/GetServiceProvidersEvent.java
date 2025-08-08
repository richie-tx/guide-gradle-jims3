//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetServiceProvidersEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetServiceProvidersEvent extends RequestEvent 
{
   public String serviceProviderName;
   public String statusId;
   public boolean inHouse;
   public String agencyId;
   public String fundSource;
   
   public boolean allServiceProviders;
   
   /**
    * @roseuid 450EF6930323
    */
   public GetServiceProvidersEvent() 
   {
    
   }

/**
 * @return Returns the serviceProviderName.
 */
public String getServiceProviderName() {
	return serviceProviderName;
}
/**
 * @param serviceProviderName The serviceProviderName to set.
 */
public void setServiceProviderName(String serviceProviderName) {
	this.serviceProviderName = serviceProviderName;
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
 * @return Returns the inHouse.
 */
public boolean isInHouse() {
	return inHouse;
}
/**
 * @param inHouse The inHouse to set.
 */
public void setInHouse(boolean inHouse) {
	this.inHouse = inHouse;
}
/**
 * @return Returns the allServiceProviders.
 */
public boolean isAllServiceProviders() {
	return allServiceProviders;
}
/**
 * @param allServiceProviders The allServiceProviders to set.
 */
public void setAllServiceProviders(boolean allServiceProviders) {
	this.allServiceProviders = allServiceProviders;
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

public String getFundSource() {
	return this.fundSource;
}
/**
* @param agencyId The agencyId to set.
*/
public void setFundSource(String fundSource) {
	this.fundSource = fundSource;
}

}
