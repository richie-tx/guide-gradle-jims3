package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class MoveProductionSupportMaysiAssessmentEvent extends RequestEvent 
{
   public String assessmentId;
   public String juvenileNumber;
     
   /**
    * @roseuid 456F33E603CA
    */
   public MoveProductionSupportMaysiAssessmentEvent() {}

public String getAssessmentId() {
	return assessmentId;
}

public void setAssessmentId(String assessmentId) {
	this.assessmentId = assessmentId;
}

public String getJuvenileNumber() {
	return juvenileNumber;
}

public void setJuvenileNumber(String juvenileNumber) {
	this.juvenileNumber = juvenileNumber;
}

   
	   
}
