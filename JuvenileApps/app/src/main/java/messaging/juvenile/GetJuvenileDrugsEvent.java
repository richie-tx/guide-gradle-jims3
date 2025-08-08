//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenile\\GetJuvenileDrugsEvent.java

//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileDrugsEvent.java

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileDrugsEvent extends RequestEvent 
{
   private String juvenileNum;
   
   /**
    * @roseuid 42B19681033C
    */
   public GetJuvenileDrugsEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}
	
	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

}
