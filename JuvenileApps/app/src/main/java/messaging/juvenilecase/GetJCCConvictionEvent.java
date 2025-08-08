package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;
/**
 * 
 *
 */
public class GetJCCConvictionEvent extends RequestEvent 
{
	private String m204JuvNumber;
   
   /**
    * 
    */
   public GetJCCConvictionEvent() 
   {
    
   }
   
	/**
	 * @return Returns the m204JuvNumber.
	 */
	public String getM204JuvNumber() {
		return m204JuvNumber;
	}
	/**
	 * @param juvNumber The m204JuvNumber to set.
	 */
	public void setM204JuvNumber(String juvNumber) {
		m204JuvNumber = juvNumber;
	}
	
}
