//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\manageassociate\\SubmitCopySuperviseeResidentialAddressEvent.java

package messaging.manageassociate;

import mojo.km.messaging.RequestEvent;

public class GetCopySuperviseeResidenceAddressEvent extends RequestEvent 
{
	private String spn;
   
   /**
    * @roseuid 45E5E81701C5
    */
   public GetCopySuperviseeResidenceAddressEvent() 
   {
    
   }
	/**
	 * @return Returns the spn.
	 */
	public String getSpn() {
		return spn;
	}
	/**
	 * @param spn The spn to set.
	 */
	public void setSpn(String spn) {
		this.spn = spn;
	}
}
