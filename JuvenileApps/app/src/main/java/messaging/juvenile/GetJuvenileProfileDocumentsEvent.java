//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileProfileDocumentsEvent.java

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileProfileDocumentsEvent extends RequestEvent 
{
   public String juvenileNum;
   
   /**
    * @roseuid 42A5E5D2034B
    */
   public GetJuvenileProfileDocumentsEvent() 
   {
    
   }

public String getJuvenileNum() {
	return juvenileNum;
}

public void setJuvenileNum(String juvenileNum) {
	this.juvenileNum = juvenileNum;
}
   
}
