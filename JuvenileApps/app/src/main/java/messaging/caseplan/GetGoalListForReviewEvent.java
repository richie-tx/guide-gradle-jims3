//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\caseplan\\GetCaseplanDetailsEvent.java

package messaging.caseplan;

import mojo.km.messaging.RequestEvent;

public class GetGoalListForReviewEvent extends RequestEvent 
{
   private String caseplanID;
   
   /**
    * @roseuid 4533BCF102F8
    */
   public GetGoalListForReviewEvent() 
   {
    
   }
   
	/**
	 * @return Returns the caseplanID.
	 */
	public String getCaseplanID() {
		return caseplanID;
	}
	/**
	 * @param caseplanID The caseplanID to set.
	 */
	public void setCaseplanID(String caseplanID) {
		this.caseplanID = caseplanID;
	}
}
