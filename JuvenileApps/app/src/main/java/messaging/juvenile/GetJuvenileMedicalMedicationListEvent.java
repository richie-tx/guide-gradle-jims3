//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenile\\GetJuvenileMedicalMedicationListEvent.java

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileMedicalMedicationListEvent extends RequestEvent 
{
   public String juvenileNum;
   
   /**
    * @roseuid 462CE3AF02A4
    */
   public GetJuvenileMedicalMedicationListEvent() 
   {
    
   }
   
   /**
    * @param juvenileNum
    * @roseuid 462CBCCC018A
    */
   public void setJuvenileNum(String juvenileNum) 
   {
   	this.juvenileNum = juvenileNum;
   }
   
   /**
    * @return String
    * @roseuid 462CBCCC018C
    */
   public String getJuvenileNum() 
   {
    return juvenileNum;
   }
}
