//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\UpdateJuvenileReleaseToPersonInfoEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

/**
 * 
 * @author asrvastava
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UpdateJuvenileReleaseToPersonInfoEvent extends RequestEvent 
{
	private String warrantNum;
    private CreateJuvenileAssociateEvent associateEvent;
    private String associateId;
   
   /**
    * @roseuid 41FFDC15004E
    */
   public UpdateJuvenileReleaseToPersonInfoEvent() 
   {
    
   }
   
   /**
    * @return String
    * @roseuid 41F95F4402D0
    */
   public String getWarrantNum() 
   {
    return warrantNum;
   }
   
	/**
	 * @return
	 */
	public CreateJuvenileAssociateEvent getAssociateEvent()
	{
		return associateEvent;
	}

	/**
	 * @param event
	 */
	public void setAssociateEvent(CreateJuvenileAssociateEvent event)
	{
		associateEvent = event;
	}

	/**
	 * @param string
	 */
	public void setWarrantNum(String string)
	{
		warrantNum = string;
	}

	/**
	 * @return
	 */
	public String getAssociateId()
	{
		return associateId;
	}

	/**
	 * @param string
	 */
	public void setAssociateId(String string)
	{
		associateId = string;
	}

}
