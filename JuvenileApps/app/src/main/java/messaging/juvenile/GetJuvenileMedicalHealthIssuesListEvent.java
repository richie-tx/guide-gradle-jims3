//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenile\\GetJuvenileMedicalHealthIssuesListEvent.java

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileMedicalHealthIssuesListEvent extends RequestEvent 
{
   public String juvenileNum;
   
   /**
    * @roseuid 462CE3AD0207
    */
   public GetJuvenileMedicalHealthIssuesListEvent() 
   {
    
   }
   
   /**
    * @param juvenileNum
    * @roseuid 462CBCCC0311
    */
   public void setJuvenileNum(String juvenileNum) 
   {
    this.juvenileNum = juvenileNum;
   }
   
   /**
    * @return String
    * @roseuid 462CBCCC0313
    */
   public String getJuvenileNum() 
   {
    return juvenileNum;
   }
}
