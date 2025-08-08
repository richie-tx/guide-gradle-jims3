/*
 * Created on Jul 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.info.reply;

import mojo.km.messaging.InfoMessageEvent;

/**
 * @author racooper
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CountInfoMessage extends InfoMessageEvent {
	public int count;

	/**
	 * @return Returns the count.
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count The count to set.
	 */
	public void setCount(int count) {
		this.count = count;
	}
}
