package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;
/**
 * 
 *
 */
public class GetJPCourtReferralsEvent extends RequestEvent 
{
	 private String m204JuvNumber;
   
   /**
    * 
    */
   public GetJPCourtReferralsEvent() 
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
