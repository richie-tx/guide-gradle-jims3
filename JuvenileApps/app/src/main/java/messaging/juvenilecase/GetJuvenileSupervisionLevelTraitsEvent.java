//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileAbuseListEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileSupervisionLevelTraitsEvent extends RequestEvent 
{
   public String juvenileNumber;
   
   /**
    * @roseuid 42B063A60037
    */
   public GetJuvenileSupervisionLevelTraitsEvent() 
   {
    
   }
   
   
	/**
	 * @return
	 */
	public String getJuvenileNumber()
	{
		return juvenileNumber;
	}
	
	/**
	 * @param string
	 */
	public void setJuvenileNumber(String string)
	{
		juvenileNumber = string;
	}

}
