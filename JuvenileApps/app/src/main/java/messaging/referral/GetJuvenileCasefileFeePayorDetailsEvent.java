//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\referral\\GetJuvenileCasefileFeePayorDetailsEvent.java

package messaging.referral;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCasefileFeePayorDetailsEvent extends RequestEvent 
{
   public String payorId;
   public String payorType;
   
   /**
    * @roseuid 467FB1BE030D
    */
   public GetJuvenileCasefileFeePayorDetailsEvent() 
   {
    
   }
   
  
/**
 * @return Returns the payorId.
 */
public String getPayorId() {
	return payorId;
}
/**
 * @param payorId The payorId to set.
 */
public void setPayorId(String payorId) {
	this.payorId = payorId;
}
/**
 * @return Returns the payorType.
 */
public String getPayorType() {
	return payorType;
}
/**
 * @param payorType The payorType to set.
 */
public void setPayorType(String payorType) {
	this.payorType = payorType;
}
}
