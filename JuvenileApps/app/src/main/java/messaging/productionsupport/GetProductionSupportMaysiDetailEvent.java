package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportMaysiDetailEvent extends RequestEvent 
{
   public String juvenileId;
   public String referralNumber;
   public Integer maysiDetailId;
     
   /**
    * @roseuid 456F33E603CA
    */
   public GetProductionSupportMaysiDetailEvent() {}
	
	public String getJuvenileId() {
		return juvenileId;
	}
	
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
	
	public String getReferralNumber() {
		return referralNumber;
	}
	
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}

	public Integer getMaysiDetailId() {
		return maysiDetailId;
	}

	public void setMaysiDetailId(Integer maysiDetailId) {
		this.maysiDetailId = maysiDetailId;
	}
}
