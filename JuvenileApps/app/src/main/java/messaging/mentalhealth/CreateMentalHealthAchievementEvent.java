// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\mentalhealth\\CreateMentalHealthAchievementEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class CreateMentalHealthAchievementEvent extends RequestEvent {
	public String serviceProviderName;

	public String instructorName;

	public Date testDate;

	public String testName;

	public String arithmeticGradeLevel;

	public String arithmeticScore;

	public String readingGradeLevel;

	public String readingScore;

	public String spellingGradeLevel;

	public String spellingScore;

	public String recommendations;

	private String testId;

	private String testSessId;
	
	private String sentenceCompletionLevel;

	private String sentenceCompletionScore;

	private String readingCompositeLevel;

	private String readingCompositeScore;

	/**
	 * @roseuid 45D4AF5103A3
	 */
	public CreateMentalHealthAchievementEvent() {

	}

	/**
	 * @return Returns the arithmeticGradeLevel.
	 */
	public String getArithmeticGradeLevel() {
		return arithmeticGradeLevel;
	}

	/**
	 * @param arithmeticGradeLevel
	 *            The arithmeticGradeLevel to set.
	 */
	public void setArithmeticGradeLevel(String arithmeticGradeLevel) {
		this.arithmeticGradeLevel = arithmeticGradeLevel;
	}

	/**
	 * @return Returns the arithmeticScore.
	 */
	public String getArithmeticScore() {
		return arithmeticScore;
	}

	/**
	 * @param arithmeticScore
	 *            The arithmeticScore to set.
	 */
	public void setArithmeticScore(String arithmeticScore) {
		this.arithmeticScore = arithmeticScore;
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
	 * @return Returns the readingGradeLevel.
	 */
	public String getReadingGradeLevel() {
		return readingGradeLevel;
	}

	/**
	 * @param readingGradeLevel
	 *            The readingGradeLevel to set.
	 */
	public void setReadingGradeLevel(String readingGradeLevel) {
		this.readingGradeLevel = readingGradeLevel;
	}

	/**
	 * @return Returns the readingScore.
	 */
	public String getReadingScore() {
		return readingScore;
	}

	/**
	 * @param readingScore
	 *            The readingScore to set.
	 */
	public void setReadingScore(String readingScore) {
		this.readingScore = readingScore;
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
	 * @return Returns the spellingGradeLevel.
	 */
	public String getSpellingGradeLevel() {
		return spellingGradeLevel;
	}

	/**
	 * @param spellingGradeLevel
	 *            The spellingGradeLevel to set.
	 */
	public void setSpellingGradeLevel(String spellingGradeLevel) {
		this.spellingGradeLevel = spellingGradeLevel;
	}

	/**
	 * @return Returns the spellingScore.
	 */
	public String getSpellingScore() {
		return spellingScore;
	}

	/**
	 * @param spellingScore
	 *            The spellingScore to set.
	 */
	public void setSpellingScore(String spellingScore) {
		this.spellingScore = spellingScore;
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
	 * @return Returns the readingCompositeLevel.
	 */
	public String getReadingCompositeLevel() {
		return readingCompositeLevel;
	}

	/**
	 * @param readingCompositeLevel
	 *            The readingCompositeLevel to set.
	 */
	public void setReadingCompositeLevel(String readingCompositeLevel) {
		this.readingCompositeLevel = readingCompositeLevel;
	}

	/**
	 * @return Returns the readingCompositeScore.
	 */
	public String getReadingCompositeScore() {
		return readingCompositeScore;
	}

	/**
	 * @param readingCompositeScore
	 *            The readingCompositeScore to set.
	 */
	public void setReadingCompositeScore(String readingCompositeScore) {
		this.readingCompositeScore = readingCompositeScore;
	}

	/**
	 * @return Returns the sentenceCompletionLevel.
	 */
	public String getSentenceCompletionLevel() {
		return sentenceCompletionLevel;
	}

	/**
	 * @param sentenceCompletionLevel
	 *            The sentenceCompletionLevel to set.
	 */
	public void setSentenceCompletionLevel(String sentenceCompletionLevel) {
		this.sentenceCompletionLevel = sentenceCompletionLevel;
	}

	/**
	 * @return Returns the sentenceCompletionScore.
	 */
	public String getSentenceCompletionScore() {
		return sentenceCompletionScore;
	}

	/**
	 * @param sentenceCompletionScore
	 *            The sentenceCompletionScore to set.
	 */
	public void setSentenceCompletionScore(String sentenceCompletionScore) {
		this.sentenceCompletionScore = sentenceCompletionScore;
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
}
