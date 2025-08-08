package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;


/**
 * @author rcarter
 */

public class DeleteProductionSupportTraitEvent extends RequestEvent 
{	
	private String traitId;

	/**
	 * @return the traitId
	 */
	public String getTraitId() {
		return traitId;
	}

	/**
	 * @param traitId the traitId to set
	 */
	public void setTraitId(String traitId) {
		this.traitId = traitId;
	}
	
}
