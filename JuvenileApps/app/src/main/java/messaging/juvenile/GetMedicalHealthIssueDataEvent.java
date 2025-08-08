//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenile\\GetMedicalHealthIssueDataEvent.java

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetMedicalHealthIssueDataEvent extends RequestEvent 
{
   public String healthIssueId;
   
   /**
    * @roseuid 462CE3B00275
    */
   public GetMedicalHealthIssueDataEvent() 
   {
    
   }
   
   /**
    * @param healthIssueId
    * @roseuid 462CBCCC037F
    */
   public void setHealthIssueId(String healthIssueId) 
   {
    this.healthIssueId = healthIssueId;
   }
   
   /**
    * @return String
    * @roseuid 462CBCCC038D
    */
   public String getHealthIssueId() 
   {
    return healthIssueId;    
   }
}
