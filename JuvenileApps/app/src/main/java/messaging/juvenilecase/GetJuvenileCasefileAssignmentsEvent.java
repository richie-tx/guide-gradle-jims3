//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetNewCaseFileAssignmentsEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCasefileAssignmentsEvent extends RequestEvent 
{
   private String juvenileNum;
   private String referralNum;
   
   /**
    * @roseuid 4277C5F20290
    */
   public GetJuvenileCasefileAssignmentsEvent() 
   {
    
   }

/**
 * @param juvenileNum the juvenileNum to set
 */
public void setJuvenileNum(String juvenileNum) {
	this.juvenileNum = juvenileNum;
}

/**
 * @return the juvenileNum
 */
public String getJuvenileNum() {
	return juvenileNum;
}

/**
 * @param referralNum the referralNum to set
 */
public void setReferralNum(String referralNum) {
	this.referralNum = referralNum;
}

/**
 * @return the referralNum
 */
public String getReferralNum() {
	return referralNum;
}
}
