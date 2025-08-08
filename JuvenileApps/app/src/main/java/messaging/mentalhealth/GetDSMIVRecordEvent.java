/*
 * Created on Apr 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GetDSMIVRecordEvent extends RequestEvent {
	public String testSessID;

	/**
	 *  
	 */
	public GetDSMIVRecordEvent() {
	}

	/**
	 * @return Returns the testSessID.
	 */
	public String getTestSessID() {
		return testSessID;
	}

	/**
	 * @param testSessID
	 *            The testSessID to set.
	 */
	public void setTestSessID(String testSessID) {
		this.testSessID = testSessID;
	}
}
