//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\referral\\GetJuvenileCasefileFeeReceiptEvent.java

package messaging.referral;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCasefileFeeReceiptEvent extends RequestEvent 
{
   public String petitionNum;
   public String transactionNum;
  
   
   /**
    * @roseuid 467FB1BF036B
    */
   public GetJuvenileCasefileFeeReceiptEvent() 
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
/**
 * @return Returns the transactionNum.
 */
public String getTransactionNum() {
	return transactionNum;
}
/**
 * @param transactionNum The transactionNum to set.
 */
public void setTransactionNum(String transactionNum) {
	this.transactionNum = transactionNum;
}
}
