/*
 * Created on May 29, 2007
 *
 */
package messaging.programreferral;

import java.util.List;

import mojo.km.messaging.RequestEvent;

/**
 */
public class SaveProgramReferralAssociationsEvent extends RequestEvent {
	
	private String programReferralId;
	private List attachedEvents;

	/**
	 * @return Returns the programReferralId.
	 */
	public String getProgramReferralId() {
		return programReferralId;
	}
	/**
	 * @param programReferralId The programReferralId to set.
	 */
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}
	/**
	 * @return Returns the attachedEvents.
	 */
	public List getAttachedEvents() {
		return attachedEvents;
	}
	/**
	 * @param attachedEvents The attachedEvents to set.
	 */
	public void setAttachedEvents(List attachedEvents) {
		this.attachedEvents = attachedEvents;
	}
}
