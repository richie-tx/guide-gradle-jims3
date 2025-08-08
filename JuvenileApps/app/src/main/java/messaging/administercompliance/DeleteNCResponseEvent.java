//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\DeleteNCResponseEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

public class DeleteNCResponseEvent extends RequestEvent 
{
   private String ncResponseId;
   
   /**
    * @roseuid 47D9BBE903B3
    */
   public DeleteNCResponseEvent() 
   {
    
   }

	public String getNcResponseId() {
		return ncResponseId;
	}
	
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
}
