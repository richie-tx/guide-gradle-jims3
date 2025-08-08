//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\mentalhealth\\GetMentalHealthDSMTestDataEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

public class GetMentalHealthDSMTestDataEvent extends RequestEvent 
{
   public String testId;
   
   /**
    * @roseuid 45DB19A902DC
    */
   public GetMentalHealthDSMTestDataEvent() 
   {
    
   }
   
   /**
    * @param testId
    * @roseuid 45DB18B30132
    */
   public void setTestId(String testId) 
   {
    this.testId = testId;
   }
   
   /**
    * @return String
    * @roseuid 45DB18B30141
    */
   public String getTestId() 
   {
    return testId;
   }
}
