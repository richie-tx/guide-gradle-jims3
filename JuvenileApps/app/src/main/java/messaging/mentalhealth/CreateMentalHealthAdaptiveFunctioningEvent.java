// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\mentalhealth\\CreateMentalHealthAdaptiveFunctioningEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class CreateMentalHealthAdaptiveFunctioningEvent extends RequestEvent {
	public String serviceProviderName;

	public String instructorName;

	public Date testDate;

	public String testName;

	public String standardScore;

	private String testId;
	
	private String testSessId;
	
	
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
	/**
	 * @roseuid 45D4AF53028A
	 */
	public CreateMentalHealthAdaptiveFunctioningEvent() {

	}

	/**
	 * @param serviceProviderName
	 * @roseuid 45D49C83031B
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}

	/**
	 * @return String
	 * @roseuid 45D49C830328
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}

	/**
	 * @param instructorName
	 * @roseuid 45D49C83032A
	 */
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	/**
	 * @return String
	 * @roseuid 45D49C83032C
	 */
	public String getInstructorName() {
		return instructorName;
	}

	/**
	 * @param testDate
	 * @roseuid 45D49C830338
	 */
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	/**
	 * @return java.util.Date
	 * @roseuid 45D49C83033A
	 */
	public Date getTestDate() {
		return testDate;
	}

	/**
	 * @param testName
	 * @roseuid 45D49C830348
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}

	/**
	 * @return String
	 * @roseuid 45D49C83034A
	 */
	public String getTestName() {
		return testName;
	}

	
	/**
	 * @return Returns the standardScore.
	 */
	public String getStandardScore() {
		return standardScore;
	}
	/**
	 * @param standardScore The standardScore to set.
	 */
	public void setStandardScore(String standardScore) {
		this.standardScore = standardScore;
	}
	/**
	 * @return Returns the testId.
	 */
	public String getTestId() {
		return testId;
	}

	/**
	 * @param testId
	 *            The testId to set.
	 */
	public void setTestId(String testId) {
		this.testId = testId;
	}
		
}
