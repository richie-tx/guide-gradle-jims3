package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class MoveProductionSupportMaysiDetailEvent extends RequestEvent 
{
   public String maysiDetailId;
   public String juvenileNumber;
     
   /**
    * @roseuid 456F33E603CA
    */
   public MoveProductionSupportMaysiDetailEvent() {}

	public String getMaysiDetailId() {
		return maysiDetailId;
	}	
	
	public void setMaysiDetailId(String maysiDetailId) {
		this.maysiDetailId = maysiDetailId;
	}

	public String getJuvenileNumber() {
		return juvenileNumber;
	}

	public void setJuvenileNumber(String juvenileNumber) {
		this.juvenileNumber = juvenileNumber;
	}
	   
}
