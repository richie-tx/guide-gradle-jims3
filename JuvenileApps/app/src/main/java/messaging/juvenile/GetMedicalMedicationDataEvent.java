//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenile\\GetMedicalMedicationDataEvent.java

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetMedicalMedicationDataEvent extends RequestEvent 
{
   public String medicationId;
   
   /**
    * @roseuid 462CE3B201C9
    */
   public GetMedicalMedicationDataEvent() 
   {
    
   }
   
   /**
    * @param medicationId
    * @roseuid 462CBCCC01E6
    */
   public void setMedicationId(String medicationId) 
   {
    this.medicationId = medicationId;
   }
   
   /**
    * @return String
    * @roseuid 462CBCCC01E8
    */
   public String getMedicationId() 
   {
    return medicationId;
   }
}
