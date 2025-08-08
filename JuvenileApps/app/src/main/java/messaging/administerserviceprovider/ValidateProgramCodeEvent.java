//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\ValidateProgramCodeEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class ValidateProgramCodeEvent extends RequestEvent 
{
   public String programCode;
   
   /**
    * @roseuid 447458790204
    */
   public ValidateProgramCodeEvent() 
   {
    
   }
   
   /**
    * Access method for the programCode property.
    * 
    * @return   the current value of the programCode property
    */
   public String getProgramCode()
   {
      return programCode;
   }
   
   /**
    * Sets the value of the programCode property.
    * 
    * @param aProgramCode the new value of the programCode property
    */
   public void setProgramCode(String aProgramCode)
   {
      programCode = aProgramCode;
   }
   

}
