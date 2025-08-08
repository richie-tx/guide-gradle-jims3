//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\manageassociate\\GetAssociatesListEvent.java

package messaging.manageassociate;

import mojo.km.messaging.RequestEvent;

public class GetSuperviseeAssociatesListBySuperviseeIdEvent extends RequestEvent 
{
	private int superviseeId;
	
   /**
    * @roseuid 45E5E81100BB
    */
   public GetSuperviseeAssociatesListBySuperviseeIdEvent() 
   {
    
   }

	/**
	 * @return Returns the superviseeId.
	 */
	public int getSuperviseeId() {
		return superviseeId;
	}
	/**
	 * @param superviseeId The superviseeId to set.
	 */
	public void setSuperviseeId(int superviseeId) {
		this.superviseeId = superviseeId;
	}
}
