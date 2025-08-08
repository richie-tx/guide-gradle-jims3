//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenile\\GetJuvenilePhysicalAttributesEvent.java

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetJuvenilePhysicalAttributesEvent extends RequestEvent 
{
   private String juvenileNum;
   
   /**
    * @roseuid 42B1968502BF
    */
   public GetJuvenilePhysicalAttributesEvent() 
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
