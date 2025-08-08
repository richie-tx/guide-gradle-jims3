//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\caseplan\\GetCaseplanDetailsEvent.java

package messaging.caseplan;

import mojo.km.messaging.RequestEvent;

public class GetCaseplansByJuvenileNumberEvent extends RequestEvent 
{
   private String juvenileNum;
   
   /**
    * @roseuid 4533BCF102F8
    */
   public GetCaseplansByJuvenileNumberEvent() 
   {
    
   }
   
	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	
}