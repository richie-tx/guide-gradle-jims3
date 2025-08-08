//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\GetNCResponsesEvent.java

package messaging.administercompliance;

import mojo.km.messaging.Composite.CompositeRequest;


public class GetNCResponseComponentsEvent extends CompositeRequest 
{
   private String requestType;
   private String mode;
   private String ncResponseId;
   private String activationDate;

	/**
 * @return the activationDate
 */
public String getActivationDate() {
	return activationDate;
}

/**
 * @param activationDate the activationDate to set
 */
public void setActivationDate(String activationDate) {
	this.activationDate = activationDate;
}

	/**
	 * @return Returns the ncResponseId.
	 */
	public String getNcResponseId() {
		return ncResponseId;
	}
	
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
	
   /**
    * @roseuid 47D9BBF50048
    */
   public GetNCResponseComponentsEvent() 
   {
    
   }
		
    /**
	 * @return Returns the mode.
	 */
	public String getMode() {
		return mode;
	}
	/**
	 * @param mode The mode to set.
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	/**
	 * @return Returns the requestType.
	 */
	public String getRequestType() {
		return requestType;
	}
	/**
	 * @param requestType The requestType to set.
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
}
