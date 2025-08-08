package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class DeleteProductionSupportMaysiDetailEvent extends RequestEvent 
{
   public String maysiDetailId;
     
   /**
    * @roseuid 456F33E603CA
    */
   public DeleteProductionSupportMaysiDetailEvent() {}

	public String getMaysiDetailId() {
		return maysiDetailId;
	}	
	
	public void setMaysiDetailId(String maysiDetailId) {
		this.maysiDetailId = maysiDetailId;
	}   
}
