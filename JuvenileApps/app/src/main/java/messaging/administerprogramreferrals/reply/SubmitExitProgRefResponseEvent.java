package messaging.administerprogramreferrals.reply;

import mojo.km.messaging.ResponseEvent;

public class SubmitExitProgRefResponseEvent extends ResponseEvent
{
	private boolean isTRecordGenerated;
	

	/**
	 * @return the isTRecordGenerated
	 */
	public boolean isTRecordGenerated() {
		return isTRecordGenerated;
	}

	/**
	 * @param isTRecordGenerated the isTRecordGenerated to set
	 */
	public void setTRecordGenerated(boolean isTRecordGenerated) {
		this.isTRecordGenerated = isTRecordGenerated;
	}
	
	
}
