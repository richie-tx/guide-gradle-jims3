package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileContactNamesForGoalEvent extends RequestEvent 
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
