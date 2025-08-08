//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJuvenileAssociateDataEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileAssociateDataEvent extends RequestEvent 
{
   public String associateNumber;
   /**
	* @roseuid 42231E170271
	*/
   public GetJuvenileAssociateDataEvent() 
   {
    
   }
   
   /**
	* Access method for the associateNumber property.
	* 
	* @return   the current value of the associateNumber property
	*/
   public String getAssociateNumber()
   {
	  return associateNumber;
   }
   
   /**
	* Sets the value of the associateNumber property.
	* 
	* @param aAssociateNumber the new value of the associateNumber property
	*/
   public void setAssociateNumber(String aAssociateNumber)
   {
	  associateNumber = aAssociateNumber;
   }
   
}