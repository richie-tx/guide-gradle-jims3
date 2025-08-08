//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\GetSupervisionConditionDetailsEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class GetSupervisionConditionDetailsEvent extends RequestEvent 
{
   private String conditionId;
   
   /**
    * @roseuid 42F7C5090232
    */
   public GetSupervisionConditionDetailsEvent() 
   {
    
   }
   
   /**
    * @param conditionId
    * @roseuid 42F79A39035E
    */
   public void setConditionId(String aConditionId) 
   {
		conditionId = aConditionId;
   }
   
   /**
    * @roseuid 42F79A390360
    */
   public String getConditionId() 
   {
		return conditionId;
   }
}
