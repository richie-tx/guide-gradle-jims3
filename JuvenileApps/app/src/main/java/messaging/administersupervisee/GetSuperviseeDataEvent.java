//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administersupervisee\\GetSuperviseeDataEvent.java

package messaging.administersupervisee;

import mojo.km.messaging.RequestEvent;

public class GetSuperviseeDataEvent extends RequestEvent 
{
   private String superviseeId;

   /**
    * @roseuid 484E803D0111
    */
   public GetSuperviseeDataEvent() 
   {
   }

public String getSuperviseeId() {
	return superviseeId;
}

public void setSuperviseeId(String superviseeId) {
	this.superviseeId = superviseeId;
}
}
