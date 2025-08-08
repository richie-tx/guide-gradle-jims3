//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\mentalhealth\\GetMentalHealthIQTestDataEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

public class GetMentalHealthIQTestDataEvent extends RequestEvent 
{
   public String testId;
   
   /**
    * @roseuid 45DB19AA02BD
    */
   public GetMentalHealthIQTestDataEvent() 
   {
    
   }
   
   /**
    * @param testId
    * @roseuid 45DB18B3024C
    */
   public void setTestId(String testId) 
   {
   	this.testId = testId;
   }
   
   /**
    * @return String
    * @roseuid 45DB18B30259
    */
   public String getTestId() 
   {
    return testId;
   }
}
