//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerassessments\\UpdateForceFieldAssessmentEvent.java

package messaging.administerassessments;


public class UpdateForceFieldAssessmentEvent extends UpdateCSAssessmentEvent 
{
   private String scsAssessId;
   private String scsInterviewId;
   
   /**
    * @roseuid 4791040E022D
    */
   public UpdateForceFieldAssessmentEvent() 
   {
       
   }
/**
 * @return the scsAssessId
 */
public String getScsAssessId() {
	return scsAssessId;
}
/**
 * @param scsAssessId the scsAssessId to set
 */
public void setScsAssessId(String scsAssessId) {
	this.scsAssessId = scsAssessId;
}
/**
 * @return the scsInterviewId
 */
public String getScsInterviewId() {
	return scsInterviewId;
}
/**
 * @param scsInterviewId the scsInterviewId to set
 */
public void setScsInterviewId(String scsInterviewId) {
	this.scsInterviewId = scsInterviewId;
}
   
}
