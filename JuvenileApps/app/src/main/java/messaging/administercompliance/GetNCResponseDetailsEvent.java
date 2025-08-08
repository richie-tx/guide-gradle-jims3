//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\GetNCResponseDetailsEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;


public class GetNCResponseDetailsEvent extends RequestEvent 
{
   private String ncResponseId;
   
   /**
    * @roseuid 47D9BBF40161
    */
   public GetNCResponseDetailsEvent() 
   {
    
   }
   
   /**
	 * @return Returns the ncResponseId.
	 */
	public String getNcResponseId() {
		return this.ncResponseId;
	}
	/**
	 * @param casenoteDate The ncResponseId to set.
	 */
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
}
