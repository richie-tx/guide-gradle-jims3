package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportTraitsEvent extends RequestEvent 
{
   
	private String casefileId;	 
	private String juvenileId;
	private String traitId;
   
   /**
    * @roseuid 45702FFC0393
    */
   public GetProductionSupportTraitsEvent() 
   {
    
   }

	/**
	 * @return the casefileId
	 */
	public String getCasefileId() {
		return casefileId;
	}
	
	/**
	 * @param casefileId the casefileId to set
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}

	/**
	 * @return the juvenileId
	 */
	public String getJuvenileId() {
		return juvenileId;
	}

	/**
	 * @param juvenileId the juvenileId to set
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}

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
