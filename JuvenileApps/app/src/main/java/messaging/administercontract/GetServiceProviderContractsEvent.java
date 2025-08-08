//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerContract\\GetContractsEvent.java

package messaging.administercontract;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetServiceProviderContractsEvent extends GetContractsEvent 
{
   private String serviceId;
   
   /**
    * @roseuid 451C4FB001FF
    */
   public GetServiceProviderContractsEvent() 
   {
    
   }
   
	/**
	 * @return Returns the serviceId.
	 */
	public String getServiceId() {
		return serviceId;
	}
	/**
	 * @param serviceId The serviceId to set.
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
}
