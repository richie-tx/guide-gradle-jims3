//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\mentalhealth\\GetMentalHealthIQResultsEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

public class GetMentalHealthIQResultsEvent extends RequestEvent 
{
   public String juvenileNum;
   
   /**
    * @roseuid 45D4AF5C021C
    */
   public GetMentalHealthIQResultsEvent() 
   {
    
   }
   
   /**
    * @param juvenileNum
    * @roseuid 45D36FDB0188
    */
   public void setJuvenileNum(String juvenileNum) 
   {
    this.juvenileNum = juvenileNum;
   }
   
   /**
    * @return String
    * @roseuid 45D36FDB0194
    */
   public String getJuvenileNum() 
   {
    return juvenileNum;
   }
}
