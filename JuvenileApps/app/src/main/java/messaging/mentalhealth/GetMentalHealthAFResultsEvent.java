//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\mentalhealth\\GetMentalHealthAFResultsEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

public class GetMentalHealthAFResultsEvent extends RequestEvent 
{
   public String juvenileNum;
   
   /**
    * @roseuid 45D4AF5B024B
    */
   public GetMentalHealthAFResultsEvent() 
   {
    
   }
   
   /**
    * @param juvenileNum
    * @roseuid 45D36FDB0232
    */
   public void setJuvenileNum(String juvenileNum) 
   {
   	this.juvenileNum = juvenileNum;
   }
   
   /**
    * @return String
    * @roseuid 45D36FDB0234
    */
   public String getJuvenileNum() 
   {
    return juvenileNum;
   }
}
