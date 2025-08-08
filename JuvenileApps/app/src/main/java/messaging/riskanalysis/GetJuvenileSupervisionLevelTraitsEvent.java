//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\CheckInterviewPreconditionsEvent.java

package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileSupervisionLevelTraitsEvent extends RequestEvent 
{
   private String juvenileNumber;
   
   /**
    * @roseuid 4342C3B102E1
    */
   public GetJuvenileSupervisionLevelTraitsEvent() 
   {
    
   }
    
   /**
    * @param juvenileNumber
    * @roseuid 433C3D3C0387
    */
   public void setJuvenileNumber(final String juvNum) 
   {
		juvenileNumber = juvNum;
   }
   
   /**
    * @roseuid 433C3D3C0389
    */
   public String getJuvenileNumber() 
   {
   		return juvenileNumber;    
   }
}
