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
public class AFTestResponseEvent extends ResponseEvent implements Comparable{

	private Date testDate;
	private String	programReferralNum ;
	private String	serviceProviderName ;
	private String testSessId;
	private String testId;
	private String instructorName; 
	private String testName;
	private String serviceEventId;
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
	 * @return Returns the testName.
	 */
	public String getTestName() {
		return testName;
	}
	/**
	 * @param testName The testName to set.
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
	 * @param testSessId The testSessId to set.
	 */
	public void setTestSessId(String testSessId) {
		this.testSessId = testSessId;
	}
	private String standardScore;

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
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		AFTestResponseEvent rsp = (AFTestResponseEvent)obj;
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
