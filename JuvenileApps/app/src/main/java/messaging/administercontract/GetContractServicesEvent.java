//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerContract\\GetContractServicesEvent.java

package messaging.administercontract;

import mojo.km.messaging.RequestEvent;

public class GetContractServicesEvent extends RequestEvent 
{
   private String contractId;
     
   /**
    * @roseuid 451C4FAF0069
    */
   public GetContractServicesEvent() 
   {
    
   }

	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
}
