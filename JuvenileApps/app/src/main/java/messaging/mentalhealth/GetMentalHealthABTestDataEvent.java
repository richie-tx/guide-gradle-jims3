package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

public class GetMentalHealthABTestDataEvent extends RequestEvent
{
	private String testId;

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}
}
