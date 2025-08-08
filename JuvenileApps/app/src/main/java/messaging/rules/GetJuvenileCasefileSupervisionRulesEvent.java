//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefileSupervisionRulesEvent.java

package messaging.rules;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCasefileSupervisionRulesEvent extends RequestEvent 
{
   public String supervisionNumber;
   
   /**
    * @roseuid 43833BC40118
    */
   public GetJuvenileCasefileSupervisionRulesEvent() 
   {
    
   }
   
   /**
    * @param supervisionNumber
    * @roseuid 4381F46B009C
    */
   public void setSupervisionNumber(String supervisionNumber) 
   {
   	
    	this.supervisionNumber = supervisionNumber;
   }
   

   
   /**
    * @roseuid 4381F46B00AB
    */
   public String getSupervisionNumber() 
   {
    	return supervisionNumber;
   }
}
