//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercasenotes\\GetSuperviseeAssociatesEvent.java

package messaging.manageassociate;

import mojo.km.messaging.RequestEvent;

public class GetSuperviseeAssociatesEvent extends RequestEvent 
{
   private String superviseeId;
   /**
    * @roseuid 44F461720177
    */
   public GetSuperviseeAssociatesEvent() 
   {
   	}
   
	/**
	 * @return Returns the superviseeId.
	 */
	public String getSuperviseeId()
	{
		return superviseeId;
	}
	/**
	 * @param aSuperviseeId The superviseeId to set.
	 */
	public void setSuperviseeId(String aSuperviseeId)
	{
		this.superviseeId = aSuperviseeId;
	}
}
