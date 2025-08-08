//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\caseplan\\GetGoalDetailsEvent.java

package messaging.caseplan;

import mojo.km.messaging.RequestEvent;

public class GetGoalDetailsEvent extends RequestEvent 
{
   private String goalID;
   
   /**
    * @roseuid 4533BCF70058
    */
   public GetGoalDetailsEvent() 
   {
    
   }
	/**
	 * @return Returns the goalID.
	 */
	public String getGoalID() {
		return goalID;
	}
	/**
	 * @param goalID The goalID to set.
	 */
	public void setGoalID(String goalID) {
		this.goalID = goalID;
	}
}
