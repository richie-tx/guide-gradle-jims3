/*
 * Created on Mar 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.mentalhealth.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class IQTestResponseEvent extends ResponseEvent implements Comparable{
	private Date testDate;

	private String fullScore;

	private String performanceScore;

	private String verbalScore;

	private String recommendation;

	private String juvenileTestingResultsId;

	private String testName;

	private String testSessId;

	private String testId;

	private String programReferralNum;

	private String serviceProviderName;

	private String instructorName;

	private String serviceEventId;

	private String verbalComprehension;

	private String perceptualReasoning;

	private String nonVerbalIQ;

	private String processingSpeed;

	private String workingMemory;

	private String pictorialIQ;

	private String geometricIQ;

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
	 * @return Returns the juvenileTestingResultsId.
	 */
	public String getJuvenileTestingResultsId() {
		return juvenileTestingResultsId;
	}

	/**
	 * @param juvenileTestingResultsId
	 *            The juvenileTestingResultsId to set.
	 */
	public void setJuvenileTestingResultsId(String juvenileTestingResultsId) {
		this.juvenileTestingResultsId = juvenileTestingResultsId;
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
	 * @return Returns the programReferralNum.
	 */
	public String getProgramReferralNum() {
		return programReferralNum;
	}

	/**
	 * @param programReferralNum
	 *            The programReferralNum to set.
	 */
	public void setProgramReferralNum(String programReferralNum) {
		this.programReferralNum = programReferralNum;
	}

	/**
	 * @return Returns the recommendation.
	 */
	public String getRecommendation() {
		return recommendation;
	}

	/**
	 * @param recommendation
	 *            The recommendation to set.
	 */
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
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
	 * @return Returns the serviceEventId.
	 */
	public String getServiceEventId() {
		return serviceEventId;
	}

	/**
	 * @param serviceEventId
	 *            The serviceEventId to set.
	 */
	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}

	/**
	 * @return Returns the geometricIQ.
	 */
	public String getGeometricIQ() {
		return geometricIQ;
	}

	/**
	 * @param geometricIQ
	 *            The geometricIQ to set.
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
	 * @param nonVerbalIQ
	 *            The nonVerbalIQ to set.
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
	 * @param perceptualReasoning
	 *            The perceptualReasoning to set.
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
	 * @param pictorialIQ
	 *            The pictorialIQ to set.
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
	 * @param processingSpeed
	 *            The processingSpeed to set.
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
	 * @param verbalComprehension
	 *            The verbalComprehension to set.
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
	 * @param workingMemory
	 *            The workingMemory to set.
	 */
	public void setWorkingMemory(String workingMemory) {
		this.workingMemory = workingMemory;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		IQTestResponseEvent rsp = (IQTestResponseEvent)obj;
		Date eventDateA = getTestDate();
		Date eventDateB = rsp.getTestDate();
		
		if(obj==null)
			return -1;
		if(eventDateA==null)
			return 1;		
		if(eventDateB == null)
			return -1;
		return eventDateB.compareTo(eventDateA);
	}
}
