/*
 * Created on Sep 11, 2006
 *
 */
package messaging.supervisionorder.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
 */
public class ActivateSupervisionOrderErrorEvent extends ResponseEvent {
	public boolean isOocTransferInDateMissing() {
		return oocTransferInDateMissing;
	}

	public void setOocTransferInDateMissing(boolean oocTransferInDateMissing) {
		this.oocTransferInDateMissing = oocTransferInDateMissing;
	}

	boolean oocTransferInDateMissing;
}
