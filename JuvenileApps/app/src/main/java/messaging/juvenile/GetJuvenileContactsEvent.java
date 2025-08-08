//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenile\\GetJuvenileContactsEvent.java

//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileContactsEvent.java

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileContactsEvent extends RequestEvent 
{
   private String juvenileNum;
   
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
