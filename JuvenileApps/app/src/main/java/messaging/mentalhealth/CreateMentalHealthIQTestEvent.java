// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\mentalhealth\\CreateMentalHealthIQTestEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class CreateMentalHealthIQTestEvent extends RequestEvent {
	public String serviceProviderName;

	public String instructorName;

	public Date testDate;

	public String recommendations;

	public String testName;

	public String fullScore;

	public String performanceScore;

	public String verbalScore;

	private String testId;

	private String testSessId;
		
	private String verbalComprehension;
	
	private String perceptualReasoning;
	
	private String nonVerbalIQ;
	 
	private String processingSpeed;
	 
	private String workingMemory;
	 
	private String pictorialIQ;
	 
	private String geometricIQ;
	 
	
	

	/**
	 * @roseuid 45D4AF560355
	 */
	public CreateMentalHealthIQTestEvent() {

	}

	/**
	 * @return Returns the fullScore.
	 */
	public String getFullScore() {
		return fullScore;
	}

	/**
	 * @param fullScore
	 *            The fullScore to set.
	 */
	public void setFullScore(String fullScore) {
		this.fullScore = fullScore;
	}

	/**
	 * @return Returns the instructorName.
	 */
	public String getInstructorName() {
		return instructorName;
	}

	/**
	 * @param instructorName
	 *            The instructorName to set.
	 */
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	/**
	 * @return Returns the performanceScore.
	 */
	public String getPerformanceScore() {
		return performanceScore;
	}

	/**
	 * @param performanceScore
	 *            The performanceScore to set.
	 */
	public void setPerformanceScore(String performanceScore) {
		this.performanceScore = performanceScore;
	}

	/**
	 * @return Returns the recommendations.
	 */
	public String getRecommendations() {
		return recommendations;
	}

	/**
	 * @param recommendations
	 *            The recommendations to set.
	 */
	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}


	/**
	 * @return Returns the serviceProviderName.
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}

	/**
	 * @param serviceProviderName
	 *            The serviceProviderName to set.
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}

	/**
	 * @return Returns the testDate.
	 */
	public Date getTestDate() {
		return testDate;
	}

	/**
	 * @param testDate
	 *            The testDate to set.
	 */
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
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

	/**
	 * @return Returns the testName.
	 */
	public String getTestName() {
		return testName;
	}

	/**
	 * @param testName
	 *            The testName to set.
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}

	/**
	 * @return Returns the testSessId.
	 */
	public String getTestSessId() {
		return testSessId;
	}

	/**
	 * @param testSessId
	 *            The testSessId to set.
	 */
	public void setTestSessId(String testSessId) {
		this.testSessId = testSessId;
	}

	/**
	 * @return Returns the verbalScore.
	 */
	public String getVerbalScore() {
		return verbalScore;
	}

	/**
	 * @param verbalScore
	 *            The verbalScore to set.
	 */
	public void setVerbalScore(String verbalScore) {
		this.verbalScore = verbalScore;
	}
	
	/**
	 * @return Returns the geometricIQ.
	 */
	public String getGeometricIQ() {
		return geometricIQ;
	}
	/**
	 * @param geometricIQ The geometricIQ to set.
	 */
	public void setGeometricIQ(String geometricIQ) {
		this.geometricIQ = geometricIQ;
	}
	/**
	 * @return Returns the nonVerbalIQ.
	 */
	public String getNonVerbalIQ() {
		return nonVerbalIQ;
	}
	/**
	 * @param nonVerbalIQ The nonVerbalIQ to set.
	 */
	public void setNonVerbalIQ(String nonVerbalIQ) {
		this.nonVerbalIQ = nonVerbalIQ;
	}
	/**
	 * @return Returns the perceptualReasoning.
	 */
	public String getPerceptualReasoning() {
		return perceptualReasoning;
	}
	/**
	 * @param perceptualReasoning The perceptualReasoning to set.
	 */
	public void setPerceptualReasoning(String perceptualReasoning) {
		this.perceptualReasoning = perceptualReasoning;
	}
	/**
	 * @return Returns the pictorialIQ.
	 */
	public String getPictorialIQ() {
		return pictorialIQ;
	}
	/**
	 * @param pictorialIQ The pictorialIQ to set.
	 */
	public void setPictorialIQ(String pictorialIQ) {
		this.pictorialIQ = pictorialIQ;
	}
	/**
	 * @return Returns the processingSpeed.
	 */
	public String getProcessingSpeed() {
		return processingSpeed;
	}
	/**
	 * @param processingSpeed The processingSpeed to set.
	 */
	public void setProcessingSpeed(String processingSpeed) {
		this.processingSpeed = processingSpeed;
	}
	/**
	 * @return Returns the verbalComprehension.
	 */
	public String getVerbalComprehension() {
		return verbalComprehension;
	}
	/**
	 * @param verbalComprehension The verbalComprehension to set.
	 */
	public void setVerbalComprehension(String verbalComprehension) {
		this.verbalComprehension = verbalComprehension;
	}
	/**
	 * @return Returns the workingMemory.
	 */
	public String getWorkingMemory() {
		return workingMemory;
	}
	/**
	 * @param workingMemory The workingMemory to set.
	 */
	public void setWorkingMemory(String workingMemory) {
		this.workingMemory = workingMemory;
	}
}
