package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class SaveProductionSupportMaysiDetailEvent extends RequestEvent 
{
   public String maysiDetailId;
   public String referralNumber;
     
   /**
    * @roseuid 456F33E603CA
    */
   public SaveProductionSupportMaysiDetailEvent() {}

	public String getMaysiDetailId() {
		return maysiDetailId;
	}	
	
	public void setMaysiDetailId(String maysiDetailId) {
		this.maysiDetailId = maysiDetailId;
	}

	public String getReferralNumber() {
		return referralNumber;
	}
	
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}
	   
}
