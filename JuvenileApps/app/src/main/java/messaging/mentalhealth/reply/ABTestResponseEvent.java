package messaging.mentalhealth.reply;

import java.util.Date;
import mojo.km.messaging.ResponseEvent;

public class ABTestResponseEvent extends ResponseEvent implements Comparable {
	private Date testDate;
	private String programReferralNum ;
	private String serviceProviderName ;
	private String testSessId;
	private String testId;
	private String instructorName; 
	private String testName;
	private String serviceEventId;
	private String communicationScore;
	private String livingScore;
	private String socialScore;
	private String compositeScore;
	private String testNameId;
	private String instrLastName;
	private String instrFirstName;
	private String instrMiddleName;
	
	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public String getProgramReferralNum() {
		return programReferralNum;
	}

	public void setProgramReferralNum(String programReferralNum) {
		this.programReferralNum = programReferralNum;
	}

	public String getServiceProviderName() {
		return serviceProviderName;
	}

	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}

	public String getTestSessId() {
		return testSessId;
	}

	public void setTestSessId(String testSessId) {
		this.testSessId = testSessId;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getServiceEventId() {
		return serviceEventId;
	}

	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}

	public String getCommunicationScore() {
		return communicationScore;
	}

	public void setCommunicationScore(String communicationScore) {
		this.communicationScore = communicationScore;
	}

	public String getLivingScore() {
		return livingScore;
	}

	public void setLivingScore(String livingScore) {
		this.livingScore = livingScore;
	}

	public String getSocialScore() {
		return socialScore;
	}

	public void setSocialScore(String socialScore) {
		this.socialScore = socialScore;
	}

	public String getCompositeScore() {
		return compositeScore;
	}

	public void setCompositeScore(String compositeScore) {
		this.compositeScore = compositeScore;
	}

	public String getTestNameId() {
		return testNameId;
	}

	public void setTestNameId(String testNameId) {
		this.testNameId = testNameId;
	}

	public String getInstrLastName() {
		return instrLastName;
	}

	public void setInstrLastName(String instrLastName) {
		this.instrLastName = instrLastName;
	}

	public String getInstrFirstName() {
		return instrFirstName;
	}

	public void setInstrFirstName(String instrFirstName) {
		this.instrFirstName = instrFirstName;
	}

	public String getInstrMiddleName() {
		return instrMiddleName;
	}

	public void setInstrMiddleName(String instrMiddleName) {
		this.instrMiddleName = instrMiddleName;
	}
	
	public int compareTo(Object obj) {
		ABTestResponseEvent rsp = (ABTestResponseEvent)obj;
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
