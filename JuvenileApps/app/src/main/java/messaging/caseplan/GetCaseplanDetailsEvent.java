//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\caseplan\\GetCaseplanDetailsEvent.java

package messaging.caseplan;

import mojo.km.messaging.RequestEvent;

public class GetCaseplanDetailsEvent extends RequestEvent 
{
   private String supervisionNumber;
   private boolean forClmReview;
   
   /**
    * @roseuid 4533BCF102F8
    */
   public GetCaseplanDetailsEvent() 
   {
    
   }
   
   /**
    * @param supervisionNumber
    * @roseuid 45119A640183
    */
   public void setSupervisionNumber(String supervisionNumber) 
   {
   		this.supervisionNumber = supervisionNumber;
   }
   
   /**
    * @return String
    * @roseuid 45119A64018E
    */
   public String getSupervisionNumber() 
   {
   		return supervisionNumber;
   }
   
	/**
	 * @return Returns the forClmReview.
	 */
	public boolean isForClmReview() {
		return forClmReview;
	}
	
	/**
	 * @param forClmReview The forClmReview to set.
	 */
	public void setForClmReview(boolean forClmReview) {
		this.forClmReview = forClmReview;
	}
}
