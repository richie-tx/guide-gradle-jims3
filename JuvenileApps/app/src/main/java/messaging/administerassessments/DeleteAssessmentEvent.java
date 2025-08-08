//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerassessments\\DeleteAssessmentEvent.java

package messaging.administerassessments;

import mojo.km.messaging.RequestEvent;


public class DeleteAssessmentEvent extends RequestEvent 
{
    private String masterAssessmentId;
    
   /**
    * @roseuid 479103FD025C
    */
   public DeleteAssessmentEvent() 
   {
    
   }
    /**
     * @return Returns the masterAssessmentId.
     */
    public String getMasterAssessmentId() {
        return masterAssessmentId;
    }
    /**
     * @param masterAssessmentId The masterAssessmentId to set.
     */
    public void setMasterAssessmentId(String masterAssessmentId) {
        this.masterAssessmentId = masterAssessmentId;
    }
}
