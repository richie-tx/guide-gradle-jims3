/*
 * Created on Nov 28, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.casefile;

import mojo.km.messaging.RequestEvent;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetActivityDetailsEvent extends RequestEvent {


	private String activityId;
	/**
	 * @return Returns the activityId.
	 */
	public String getActivityId() {
		return activityId;
	}
	/**
	 * @param activityId The activityId to set.
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	/**
	 * 
	 */
	public GetActivityDetailsEvent() {
	}

}
