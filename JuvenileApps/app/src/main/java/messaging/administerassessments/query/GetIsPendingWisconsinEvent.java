//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerassessments\\GetIsPendingWisconsinEvent.java

package messaging.administerassessments.query;

import mojo.km.messaging.RequestEvent;


public class GetIsPendingWisconsinEvent extends RequestEvent
{
    private String defendantId;
    /**
    * @roseuid 47ACD49A02AD
    */
   public GetIsPendingWisconsinEvent() 
   {
   }
    /**
     * @return Returns the defendantId.
     */
    public String getDefendantId() {
        return defendantId;
    }
    /**
     * @param defendantId The defendantId to set.
     */
    public void setDefendantId(String defendantId) {
        this.defendantId = defendantId;
    }
}
