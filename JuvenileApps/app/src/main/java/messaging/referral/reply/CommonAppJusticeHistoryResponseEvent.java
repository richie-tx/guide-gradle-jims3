/*
 * Created on Oct 31, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.referral.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CommonAppJusticeHistoryResponseEvent extends ResponseEvent {
	
	private int adjudicationEvents; 
	private int CINSAdjudicationNumber;
	


	/**
	 * @return Returns the adjudicationEvents.
	 */
	public int getAdjudicationEvents() {
		return adjudicationEvents;
	}
	/**
	 * @param adjudicationEvents The adjudicationEvents to set.
	 */
	public void setAdjudicationEvents(int adjudicationEvents) {
		this.adjudicationEvents = adjudicationEvents;
	}
	/**
	 * @return Returns the cINSAdjudicationNumber.
	 */
	public int getCINSAdjudicationNumber() {
		return CINSAdjudicationNumber;
	}
	/**
	 * @param adjudicationNumber The cINSAdjudicationNumber to set.
	 */
	public void setCINSAdjudicationNumber(int adjudicationNumber) {
		CINSAdjudicationNumber = adjudicationNumber;
	}
}
