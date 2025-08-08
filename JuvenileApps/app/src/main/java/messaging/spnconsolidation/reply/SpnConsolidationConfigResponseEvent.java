/*
 * Created on Oct 18, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.spnconsolidation.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author ryoung
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SpnConsolidationConfigResponseEvent extends ResponseEvent{
	
	private int sequenceNum;

	private String handler;

	/**
	 * @return Returns the handler.
	 */
	public String getHandler() {
		return handler;
	}
	/**
	 * @param handler The handler to set.
	 */
	public void setHandler(String handler) {
		this.handler = handler;
	}
	/**
	 * @return Returns the sequenceNum.
	 */
	public int getSequenceNum() {
		return sequenceNum;
	}
	/**
	 * @param sequenceNum The sequenceNum to set.
	 */
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
}
