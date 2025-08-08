package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportMaysiAssessmentEvent extends RequestEvent 
{
   public String juvenileId;
   public String referralNumber;
   public Integer maysiAssessmentId;
     
   /**
    * @roseuid 456F33E603CA
    */
   public GetProductionSupportMaysiAssessmentEvent() {}
	
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

	public Integer getMaysiAssessmentId() {
		return maysiAssessmentId;
	}

	public void setMaysiAssessmentId(Integer maysiAssessmentId) {
		this.maysiAssessmentId = maysiAssessmentId;
	}
	
	   
   
}
