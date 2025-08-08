//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\DeleteCourtPolicyEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class DeleteCourtPolicyEvent extends RequestEvent 
{
   private String policyId;
   private boolean inUse=false;
   private String notes;
   
   /**
    * @roseuid 42F7C4E702DE
    */
   public DeleteCourtPolicyEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getNotes()
	{
		return notes;
	}
	
	/**
	 * @return
	 */
	public String getPolicyId()
	{
		return policyId;
	}
	
	/**
	 * @param string
	 */
	public void setNotes(String string)
	{
		notes = string;
	}
	
	/**
	 * @param string
	 */
	public void setPolicyId(String string)
	{
		policyId = string;
	}

/**
 * @return Returns the inUse.
 */
public boolean isInUse() {
	return inUse;
}
/**
 * @param inUse The inUse to set.
 */
public void setInUse(boolean inUse) {
	this.inUse = inUse;
}
}
