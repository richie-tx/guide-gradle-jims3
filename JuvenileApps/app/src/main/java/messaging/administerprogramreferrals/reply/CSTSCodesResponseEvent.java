package messaging.administerprogramreferrals.reply;

import java.util.Set;

import mojo.km.messaging.ResponseEvent;

public class CSTSCodesResponseEvent extends ResponseEvent
{
	private Set cstsCodesSet;

	/**
	 * @return the cstsCodesSet
	 */
	public Set getCstsCodesSet() {
		return cstsCodesSet;
	}

	/**
	 * @param cstsCodesSet the cstsCodesSet to set
	 */
	public void setCstsCodesSet(Set cstsCodesSet) {
		this.cstsCodesSet = cstsCodesSet;
	}
}
