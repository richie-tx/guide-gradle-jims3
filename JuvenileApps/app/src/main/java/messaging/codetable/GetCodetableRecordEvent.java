//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\codetable\\GetCodetableRecordEvent.java

package messaging.codetable;

/*
 * @author mchowdhury
 */
import mojo.km.messaging.RequestEvent;

public class GetCodetableRecordEvent extends RequestEvent 
{
   private String codetableRegId;
   private String type;
   
   /**
    * @roseuid 45B956990233
    */
   public GetCodetableRecordEvent() 
   {
    
   }
	/**
	 * @return Returns the codetableRegId.
	 */
	public String getCodetableRegId() {
		return codetableRegId;
	}
	/**
	 * @param codetableRegId The codetableRegId to set.
	 */
	public void setCodetableRegId(String codetableRegId) {
		this.codetableRegId = codetableRegId;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
}
