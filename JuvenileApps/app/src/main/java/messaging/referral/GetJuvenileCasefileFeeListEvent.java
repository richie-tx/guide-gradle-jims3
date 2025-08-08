//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\referral\\GetJuvenileCasefileFeeListEvent.java

package messaging.referral;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCasefileFeeListEvent extends RequestEvent 
{
   public String petitionNum;
   
   /**
    * @roseuid 467FB1BD0000
    */
   public GetJuvenileCasefileFeeListEvent() 
   {
    
   }
   
  
/**
 * @return Returns the petitionNum.
 */
public String getPetitionNum() {
	return petitionNum;
}
/**
 * @param petitionNum The petitionNum to set.
 */
public void setPetitionNum(String petitionNum) {
	this.petitionNum = petitionNum;
}
}
