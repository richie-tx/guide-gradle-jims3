package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class DeleteProductionSupportMaysiAssessmentEvent extends RequestEvent 
{
   public String assessmentId;
     
   /**
    * @roseuid 456F33E603CA
    */
   public DeleteProductionSupportMaysiAssessmentEvent() {}

	public String getAssessmentId() {
		return assessmentId;
	}
	
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}   
}
