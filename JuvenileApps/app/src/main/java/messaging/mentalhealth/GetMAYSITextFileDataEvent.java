//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetMAYSITextFileDataEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

public class GetMAYSITextFileDataEvent extends RequestEvent 
{
   public String juvenileNumber;
   
   /**
    * @roseuid 42790E0800DA
    */
   public GetMAYSITextFileDataEvent() 
   {
    
   }
   
   
/**
 * @return Returns the juvenileNumber.
 */
public String getJuvenileNumber() {
	return juvenileNumber;
}
/**
 * @param juvenileNumber The juvenileNumber to set.
 */
public void setJuvenileNumber(String juvenileNumber) {
	this.juvenileNumber = juvenileNumber;
}
}
