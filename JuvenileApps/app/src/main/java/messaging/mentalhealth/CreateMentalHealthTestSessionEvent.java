// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\mentalhealth\\CreateMentalHealthTestSessionEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class CreateMentalHealthTestSessionEvent extends RequestEvent {
	public String serviceProviderName;

	public String serviceLocationName;

	/*
	 * public String eventStatus; public String eventTime; public String
	 * eventType; public String eventSessionLength; public Date
	 * referralSentDate; public String referralNumber; public String
	 * mentalIllnessDiagnosis;
	 *  
	 */
	public String instructorName;

	public Date sessionDate;

	public String locationDetails;

	public String testType;

	public String actualSessionLength;

	public String psychiatricAssessment;

	public String psychologicalAssessment;

	public String mentalRetardationDiagnosis;

	public String recommendations;

	public String programStatus;

	public String serviceEventId;

	

	public String juvenileNum;
	

	public String mentalillnessDiagnosis;

	
	/**
	 * @return Returns the mentalillnessDiagnosis.
	 */
	public String getMentalillnessDiagnosis() {
		return mentalillnessDiagnosis;
	}
	/**
	 * @param mentalillnessDiagnosis The mentalillnessDiagnosis to set.
	 */
	public void setMentalillnessDiagnosis(String mentalillnessDiagnosis) {
		this.mentalillnessDiagnosis = mentalillnessDiagnosis;
	}
	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNum
	 *            The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	
	/**
	 * @roseuid 45D4AF580076
	 */
	public CreateMentalHealthTestSessionEvent() {

	}

	/**
	 * @param serviceProviderName
	 * @roseuid 45D49C91001D
	 */
	public void setServiceProviderName(String serviceProviderName) {
	 this.serviceProviderName = serviceProviderName;
	}

	/**
	 * @return String
	 * @roseuid 45D49C91001F
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}

	/**
	 * @param serviceLocationName
	 * @roseuid 45D49C91003A
	 */
	public void setServiceLocationName(String serviceLocationName) {
		this.serviceLocationName = serviceLocationName;
	}

	/**
	 * @return String
	 * @roseuid 45D49C91003C
	 */
	public String getServiceLocationName() {
		return serviceLocationName;
	}

	
	
	/**
	 * @param sessionDate
	 * @roseuid 45D49C910099
	 */
	public void setSessionDate(Date sessionDate) {
		this.sessionDate = sessionDate;
	}

	/**
	 * @return java.util.Date
	 * @roseuid 45D49C91009B
	 */
	public Date getSessionDate() {
		return sessionDate;
	}


	/**
	 * @param testType
	 * @roseuid 45D49C9100C7
	 */
	public void setTestType(String testType) {
		this.testType = testType;
	}

	/**
	 * @return String
	 * @roseuid 45D49C9100C9
	 */
	public String getTestType() {
		return testType;
	}

	/**
	 * @param actualSessionLength
	 * @roseuid 45D49C9100D6
	 */
	public void setActualSessionLength(String actualSessionLength) {
		this.actualSessionLength = actualSessionLength;
	}

	/**
	 * @return String
	 * @roseuid 45D49C9100D8
	 */
	public String getActualSessionLength() {
		return actualSessionLength;
	}

	/**
	 * @param psychiatricAssessment
	 * @roseuid 45D49C9100DA
	 */
	public void setPsychiatricAssessment(String psychiatricAssessment) {
		this.psychiatricAssessment = psychiatricAssessment;
	}

	/**
	 * @return String
	 * @roseuid 45D49C9100E7
	 */
	public String getPsychiatricAssessment() {
		return psychiatricAssessment;
	}

	/**
	 * @param psychologicalAssessment
	 * @roseuid 45D49C9100E9
	 */
	public void setPsychologicalAssessment(String psychologicalAssessment) {
		this.psychologicalAssessment = psychologicalAssessment;
	}

	/**
	 * @return String
	 * @roseuid 45D49C910106
	 */
	public String getPsychologicalAssessment() {
		return psychologicalAssessment;
	}

	/**
	 * @param mentalRetardationDiagnosis
	 * @roseuid 45D49C910108
	 */
	public void setMentalRetardationDiagnosis(String mentalRetardationDiagnosis) {
		this.mentalRetardationDiagnosis = mentalRetardationDiagnosis;
	}

	/**
	 * @return String
	 * @roseuid 45D49C910115
	 */
	public String getMentalRetardationDiagnosis() {
		return mentalRetardationDiagnosis;
	}

	/**
	 * @param mentalIllnessDiagnosis
	 * @roseuid 45D49C910117
	 */
	public void setMentalIllnessDiagnosis(String mentalillnessDiagnosis) {
		this.mentalillnessDiagnosis = mentalillnessDiagnosis;
	}

	/**
	 * @return String
	 * @roseuid 45D49C910119
	 */
	public String getMentalIllnessDiagnosis() {
		return mentalillnessDiagnosis;
	}

	/**
	 * @param recommendations
	 * @roseuid 45D49C910125
	 */
	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}

	/**
	 * @return String
	 * @roseuid 45D49C910127
	 */
	public String getRecommendations() {
		return recommendations;
	}

	/**
	 * @param programStatus
	 * @roseuid 45D49C910135
	 */
	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;	
	}

	/**
	 * @return String
	 * @roseuid 45D49C910137
	 */
	public String getProgramStatus() {
		return programStatus;
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
}
