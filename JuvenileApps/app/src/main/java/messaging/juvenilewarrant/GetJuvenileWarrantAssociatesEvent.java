//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJuvenileWarrantAssociatesEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileWarrantAssociatesEvent extends RequestEvent 
{
   public String warrantNum;
   
   /**
    * @roseuid 421A46AD0119
    */
   public GetJuvenileWarrantAssociatesEvent() 
   {
    
   }
   
   /**
    * @param warrantNum
    * @roseuid 421A433E009C
    */
   public void setWarrantNum(String warrantNum) 
   {
      this.warrantNum = warrantNum;
   }
   
   /**
    * @return String
    * @roseuid 421A433E00AB
    */
   public String getWarrantNum() 
   {
    return warrantNum;
   }
}
