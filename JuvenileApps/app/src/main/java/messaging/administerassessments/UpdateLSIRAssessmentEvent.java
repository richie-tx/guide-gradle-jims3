//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerassessments\\UpdateLSIRAssessmentEvent.java

package messaging.administerassessments;

import java.util.Date;


public class UpdateLSIRAssessmentEvent extends UpdateCSAssessmentEvent 
{
    private String comments;
    private boolean isReassessment;
   /**
    * @roseuid 4791040E027B
    */
   public UpdateLSIRAssessmentEvent() 
   {
   }
    /**
     * @return Returns the comments.
     */
    public String getComments() {
        return comments;
    }
    /**
     * @param comments The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    /**
     * @return Returns the isReassessment.
     */
    public boolean getIsReassessment() {
        return isReassessment;
    }
    /**
     * @param isReassessment The isReassessment to set.
     */
    public void setIsReassessment(boolean isReassessment) {
        this.isReassessment = isReassessment;
    }
}
