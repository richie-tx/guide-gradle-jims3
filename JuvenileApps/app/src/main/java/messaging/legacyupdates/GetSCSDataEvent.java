/*
 * Created on Dec 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.legacyupdates;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetSCSDataEvent extends RequestEvent {
    private String seqNum;

	/**
	 * @return the seqNum
	 */
	public String getSeqNum() {
		return seqNum;
	}

	/**
	 * @param seqNum the seqNum to set
	 */
	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}    
}
