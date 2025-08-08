/*
 * Created on Oct 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.casefile;

import mojo.km.messaging.RequestEvent;

/**
 * @author ugopinath
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GetDSMDiagnosisByClosingInfoIdEvent extends RequestEvent {
	public String closingInfoId;
	public String testSessId;

	/**
	 *  
	 */
	public GetDSMDiagnosisByClosingInfoIdEvent() {
	}

	
	/**
	 * @return Returns the closingInfoId.
	 */
	public String getClosingInfoId() {
		return closingInfoId;
	}
	/**
	 * @param closingInfoId The closingInfoId to set.
	 */
	public void setClosingInfoId(String closingInfoId) {
		this.closingInfoId = closingInfoId;
	}
	/**
	 * @return Returns the testSessId.
	 */
	public String getTestSessId() {
		return testSessId;
	}
	/**
	 * @param testSessId The testSessId to set.
	 */
	public void setTestSessId(String testSessId) {
		this.testSessId = testSessId;
	}
}
