//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\mentalhealth\\GetMentalHealthAchievementResultsEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

public class GetMentalHealthAchievementResultsEvent extends RequestEvent 
{
   public String juvenileNum;
   
   /**
    * @roseuid 45D4AF5A01FD
    */
   public GetMentalHealthAchievementResultsEvent() 
   {
    
   }
   
   /**
    * @param juvenileNum
    * @roseuid 45D36FDA0379
    */
   public void setJuvenileNum(String juvenileNum) 
   {
    this.juvenileNum = juvenileNum;
   }
   
   /**
    * @return String
    * @roseuid 45D36FDA037B
    */
   public String getJuvenileNum() 
   {
    return juvenileNum;
   }
}
