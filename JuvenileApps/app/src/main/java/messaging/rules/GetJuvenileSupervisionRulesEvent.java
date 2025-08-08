package messaging.rules;

import mojo.km.messaging.RequestEvent;


public class GetJuvenileSupervisionRulesEvent extends RequestEvent 
{
   public String juvenileNumber;
   
   /**
    * @roseuid 43833BC40118
    */
   public GetJuvenileSupervisionRulesEvent() 
   {
    
   }
   
   /**
    * @param supervisionNumber
    * @roseuid 4381F46B009C
    */
   public void setJuvenileNumber(String juvenileNumber) 
   {
   	
    	this.juvenileNumber = juvenileNumber;
   }
   

   
   /**
    * @roseuid 4381F46B00AB
    */
   public String getJuvenileNumber() 
   {
    	return juvenileNumber;
   }
}
