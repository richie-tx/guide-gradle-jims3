//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\registergenericaccount\\HandleGenericLogonIDSelectionEvent.java

package messaging.registergenericaccount;

import mojo.km.messaging.RequestEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class VerifyJIMSGenericAccountEvent extends RequestEvent 
{
	private String logonId;
   
   /**
    * @roseuid 456220C80219
    */
   public VerifyJIMSGenericAccountEvent() 
   {
    
   }

	/**
	 * @return Returns the logonId.
	 */
	public String getLogonId() {
		return logonId;
	}
	/**
	 * @param logonId The logonId to set.
	 */
	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}
}
