//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenile\\GetJuvenilePhotosEvent.java

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCasesByJuvenileNumberEvent extends RequestEvent 
{
   public String juvenileNum;
   
   /**
    * @roseuid 42B19684008C
    */
   public GetJuvenileCasesByJuvenileNumberEvent() 
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
