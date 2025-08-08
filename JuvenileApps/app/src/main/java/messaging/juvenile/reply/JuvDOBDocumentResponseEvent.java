/*
 * Created on Nov 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenile.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JuvDOBDocumentResponseEvent extends ResponseEvent{
	private boolean isAvailable;
	/**
	 * @return Returns the isAvailable.
	 */
	public boolean isAvailable() {
		return isAvailable;
	}
	/**
	 * @param isAvailable The isAvailable to set.
	 */
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
}
