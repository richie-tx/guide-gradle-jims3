//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenile\\GetJuvenileMedicalHospitalizationListEvent.java

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileMedicalHospitalizationListEvent extends RequestEvent 
{
   public String juvenileNum;
   
   /**
    * @roseuid 462CE3AE0227
    */
   public GetJuvenileMedicalHospitalizationListEvent() 
   {
    
   }
   
   /**
    * @param juvenileNum
    * @roseuid 462CBCCC0245
    */
   public void setJuvenileNum(String juvenileNum) 
   {
    this.juvenileNum = juvenileNum;
   }
   
   /**
    * @return String
    * @roseuid 462CBCCC0247
    */
   public String getJuvenileNum() 
   {
    return juvenileNum;
   }
}
