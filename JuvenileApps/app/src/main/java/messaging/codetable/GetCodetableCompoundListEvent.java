//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\codetable\\GetCodetableCompoundListEvent.java

package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class GetCodetableCompoundListEvent extends RequestEvent 
{
   private String codetableAttributeId;
   /**
    * @roseuid 45B956920197
    */
   public GetCodetableCompoundListEvent() 
   {
    
   }
	/**
	 * @return Returns the codetableAttributeId.
	 */
	public String getCodetableAttributeId() {
		return codetableAttributeId;
	}
	/**
	 * @param codetableAttributeId The codetableAttributeId to set.
	 */
	public void setCodetableAttributeId(String codetableAttributeId) {
		this.codetableAttributeId = codetableAttributeId;
	}
}
