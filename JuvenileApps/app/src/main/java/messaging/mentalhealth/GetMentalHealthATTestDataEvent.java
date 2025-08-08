//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\mentalhealth\\GetMentalHealthATTestDataEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

public class GetMentalHealthATTestDataEvent extends RequestEvent 
{
   public String testId;
   
   /**
    * @roseuid 45DB19A8034A
    */
   public GetMentalHealthATTestDataEvent() 
   {
    
   }
   
   /**
    * @param testId
    * @roseuid 45DB18B203A1
    */
   public void setTestId(String testId) 
   {
   	this.testId = testId;
    
   }
   
   /**
    * @return String
    * @roseuid 45DB18B203B2
    */
   public String getTestId() 
   {
    return testId;
   }
}
