//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\referral\\GetJuvenileCasefilePropertyLossDetailsEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCasefilePropertyLossDetailsEvent extends RequestEvent 
{
   public String daLogNum;
   
   /**
    * @roseuid 467FB1C003D8
    */
   public GetJuvenileCasefilePropertyLossDetailsEvent() 
   {
    
   }
   
 
/**
 * @return Returns the daLogNum.
 */
public String getDaLogNum() {
	return daLogNum;
}
/**
 * @param daLogNum The daLogNum to set.
 */
public void setDaLogNum(String daLogNum) {
	this.daLogNum = daLogNum;
}
}
