//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\mentalhealth\\GetMentalHealthHospitalizationDataEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

public class GetMentalHealthHospitalizationDataEvent extends RequestEvent 
{
   public String hospitalizationId;
   
   /**
    * @roseuid 45B0DCAB0166
    */
   public GetMentalHealthHospitalizationDataEvent() 
   {}
   
   /**
    * @param hospitalizationId
    * @roseuid 45B0CB760256
    */
   public void setHospitalizationId(String hospitalizationId) {
	this.hospitalizationId = hospitalizationId;
   }
   
   /**
    * @return String
    * @roseuid 45B0CB760263
    */
   public String getHospitalizationId() {
	return hospitalizationId;
   }


}
