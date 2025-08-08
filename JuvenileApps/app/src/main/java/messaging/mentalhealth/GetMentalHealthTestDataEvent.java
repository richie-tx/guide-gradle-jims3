// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\mentalhealth\\GetMentalHealthTestDataEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

public class GetMentalHealthTestDataEvent extends RequestEvent {
	public String testSessID;

	/**
	 * @roseuid 45D4AF5D0171
	 */
	public GetMentalHealthTestDataEvent() {

	}

	/**
	 * @param testId
	 * @roseuid 45D49C770114
	 */
	public void setTestSessID(String testSessID) {
		this.testSessID = testSessID;
	}

	/**
	 * @return String
	 * @roseuid 45D49C770116
	 */
	public String getTestSessID() {
		return testSessID;
	}
}
