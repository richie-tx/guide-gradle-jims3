/*
 * Created on Oct 30, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.caseplan.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dapte
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GoalOIDEvent extends ResponseEvent {
	private String goalID;
	
	public GoalOIDEvent() {
		
	}
	
	/**
	 * @return Returns the caseplanID.
	 */
	public String getGoalID() {
		return goalID;
	}
	/**
	 * @param caseplanID The caseplanID to set.
	 */
	public void setGoalID(String caseplanID) {
		this.goalID = caseplanID;
	}
}
