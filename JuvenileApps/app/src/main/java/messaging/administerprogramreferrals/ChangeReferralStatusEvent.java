package messaging.administerprogramreferrals;

import mojo.km.messaging.RequestEvent;

public class ChangeReferralStatusEvent extends RequestEvent
{
	private String programReferralId; 
	
	private boolean isRemoveEntry;
	private boolean isRemoveExit;
	
	
	/**
	 * @return the programReferralId
	 */
	public String getProgramReferralId() {
		return programReferralId;
	}

	/**
	 * @param programReferralId the programReferralId to set
	 */
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}

	/**
	 * @return the isRemoveEntry
	 */
	public boolean isRemoveEntry() {
		return isRemoveEntry;
	}

	/**
	 * @param isRemoveEntry the isRemoveEntry to set
	 */
	public void setRemoveEntry(boolean isRemoveEntry) {
		this.isRemoveEntry = isRemoveEntry;
	}

	/**
	 * @return the isRemoveExit
	 */
	public boolean isRemoveExit() {
		return isRemoveExit;
	}

	/**
	 * @param isRemoveExit the isRemoveExit to set
	 */
	public void setRemoveExit(boolean isRemoveExit) {
		this.isRemoveExit = isRemoveExit;
	}
}
