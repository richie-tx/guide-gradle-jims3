//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerContract\\UpdateContractServiceEvent.java

package messaging.administercontract;

import mojo.km.messaging.Composite.CompositeRequest;

public class DeleteContractServiceEvent extends CompositeRequest 
{
	private String contractServiceId;
	   
   /**
    * @roseuid 451C4FB200A8
    */
   public DeleteContractServiceEvent() 
   {
    
   }
	/**
	 * @return Returns the contractServiceId.
	 */
	public String getContractServiceId() {
		return contractServiceId;
	}
	/**
	 * @param contractServiceId The contractServiceId to set.
	 */
	public void setContractServiceId(String contractServiceId) {
		this.contractServiceId = contractServiceId;
	}
}
