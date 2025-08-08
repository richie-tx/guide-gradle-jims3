//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenile\\GetMedicalHospitalizationDataEvent.java

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetMedicalHospitalizationDataEvent extends RequestEvent 
{
   public String hospitalizationId;
   
   /**
    * @roseuid 462CE3B10246
    */
   public GetMedicalHospitalizationDataEvent() 
   {
    
   }
   
   /**
    * @param hospitalizationId
    * @roseuid 462CBCCC02A3
    */
   public void setHospitalizationId(String hospitalizationId) 
   {
    this.hospitalizationId = hospitalizationId;
   }
   
   /**
    * @return String
    * @roseuid 462CBCCC02A5
    */
   public String getHospitalizationId() 
   {
    return hospitalizationId;
   }
}
