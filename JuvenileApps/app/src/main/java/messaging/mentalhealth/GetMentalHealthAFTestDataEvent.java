//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\mentalhealth\\GetMentalHealthAFTestDataEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

public class GetMentalHealthAFTestDataEvent extends RequestEvent 
{
   public String testId;
   
   /**
    * @roseuid 45DB19A70194
    */
   public GetMentalHealthAFTestDataEvent() 
   {
    
   }
   
   /**
    * @param testId
    * @roseuid 45DB18B30373
    */
   public void setTestId(String testId) 
   {
    this.testId = testId;
   }
   
   /**
    * @return String
    * @roseuid 45DB18B30375
    */
   public String getTestId() 
   {
    return testId;
   }
}
