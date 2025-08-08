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
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ATTestResponseEvent extends ResponseEvent implements Comparable{

	private Date testDate;
	private String	programReferralNum ;
	private String	serviceProviderName ;
	private String testSessId;
	private String testId;
	private String	instructorName;
	private String	testname;
	private String	arithmeticGradeLevel;
	private String	arithmeticScore;
	private String	readingGradeLevel;
	private String	readingScore;
	private String	spellingGradeLevel;
	private String	spellingScore;
	private String	recommendations;
	private String serviceEventId;
	private String sentenceCompletionLevel;
	private String sentenceCompletionScore;
	private String readingCompositeLevel;
	private String readingCompositeScore;

	/**
	 * @return Returns the arithmeticGradeLevel.
	 */
	public String getArithmeticGradeLevel() {
		return arithmeticGradeLevel;
	}
	/**
	 * @param arithmeticGradeLevel The arithmeticGradeLevel to set.
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
	 * @param arithmeticScore The arithmeticScore to set.
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
	 * @param instructorName The instructorName to set.
	 */
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	/**
	 * @return Returns the programReferralNum.
	 */
	public String getProgramReferralNum() {
		return programReferralNum;
	}
	/**
	 * @param programReferralNum The programReferralNum to set.
	 */
	public void setProgramReferralNum(String programReferralNum) {
		this.programReferralNum = programReferralNum;
	}
	/**
	 * @return Returns the readingGradeLevel.
	 */
	public String getReadingGradeLevel() {
		return readingGradeLevel;
	}
	/**
	 * @param readingGradeLevel The readingGradeLevel to set.
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
	 * @param readingScore The readingScore to set.
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
	 * @param recommendations The recommendations to set.
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
	 * @param serviceProviderName The serviceProviderName to set.
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
	 * @param spellingGradeLevel The spellingGradeLevel to set.
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
	 * @param spellingScore The spellingScore to set.
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
	 * @param testDate The testDate to set.
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
	 * @param testId The testId to set.
	 */
	public void setTestId(String testId) {
		this.testId = testId;
	}
	/**
	 * @return Returns the testname.
	 */
	public String getTestname() {
		return testname;
	}
	/**
	 * @param testname The testname to set.
	 */
	public void setTestname(String testname) {
		this.testname = testname;
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
	
	/**
	 * @return Returns the serviceEventId.
	 */
	public String getServiceEventId() {
		return serviceEventId;
	}
	/**
	 * @param serviceEventId The serviceEventId to set.
	 */
	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}
	
	/**
	 * @return Returns the readingCompositeLevel.
	 */
	public String getReadingCompositeLevel() {
		return readingCompositeLevel;
	}
	/**
	 * @param readingCompositeLevel The readingCompositeLevel to set.
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
	 * @param readingCompositeScore The readingCompositeScore to set.
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
	 * @param sentenceCompletionLevel The sentenceCompletionLevel to set.
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
	 * @param sentenceCompletionScore The sentenceCompletionScore to set.
	 */
	public void setSentenceCompletionScore(String sentenceCompletionScore) {
		this.sentenceCompletionScore = sentenceCompletionScore;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		ATTestResponseEvent rsp = (ATTestResponseEvent)obj;
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
