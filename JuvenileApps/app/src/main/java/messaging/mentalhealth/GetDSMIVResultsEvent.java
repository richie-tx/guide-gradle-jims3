//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\mentalhealth\\GetDSMIVResultsEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

public class GetDSMIVResultsEvent extends RequestEvent 
{
   public String juvenileNum;
   
   /**
    * @roseuid 45D4AF59025B
    */
   public GetDSMIVResultsEvent() 
   {
    
   }
   
   /**
    * @param juvenileNum
    * @roseuid 45D36FDB00CC
    */
   public void setJuvenileNum(String juvenileNum) 
   {
    this.juvenileNum = juvenileNum;
   }
   
   /**
    * @return String
    * @roseuid 45D36FDB00DA
    */
   public String getJuvenileNum() 
   {
    return juvenileNum;
   }
}
