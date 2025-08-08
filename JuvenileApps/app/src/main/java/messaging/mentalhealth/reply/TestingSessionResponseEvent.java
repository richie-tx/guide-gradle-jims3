/*
 * Created on Feb 20, 2007
 *
 */
package messaging.mentalhealth.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_vnarsingoju
 * 
 */
public class TestingSessionResponseEvent extends ResponseEvent implements Comparable{

	private Date sessionDate;

	private String programReferralNum;
	private Date referralDate ; 
	private String referralStatusCd;	
	private String referralSubStatusCd;

	private String programStatus;

	private String serviceProviderName;

	private String eventSessionLength;
	private String actualSessionLength;

	private String eventStatus;
	private String eventTime;
	private String eventType;
	
	private String locationDetails;
	private String locationUnitName;

	private String testType;
	
	private String psychologicalAssessment;
	private String psychiatricAssessment;

	private String mentalRetardationDiagnosis;
	private String mentalIllnessDiagnosis;

	private String recommendations;
	private String instructorName ;
	
	private String testSessID ; 	
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
	 * @return Returns the referralDate.
	 */
	public Date getReferralDate() {
		return referralDate;
	}
	/**
	 * @param referralDate The referralDate to set.
	 */
	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}
	
	/**
	 * @return Returns the actualSessionLength.
	 */
	public String getActualSessionLength() {
		return actualSessionLength;
	}
	/**
	 * @param actualSessionLength The actualSessionLength to set.
	 */
	public void setActualSessionLength(String actualSessionLength) {
		this.actualSessionLength = actualSessionLength;
	}
	/**
	 * @return Returns the eventSessionLength.
	 */
	public String getEventSessionLength() {
		return eventSessionLength;
	}
	/**
	 * @param eventSessionLength The eventSessionLength to set.
	 */
	public void setEventSessionLength(String eventSessionLength) {
		this.eventSessionLength = eventSessionLength;
	}
	/**
	 * @return Returns the eventStatus.
	 */
	public String getEventStatus() {
		return eventStatus;
	}
	/**
	 * @param eventStatus The eventStatus to set.
	 */
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
	/**
	 * @return Returns the eventTime.
	 */
	public String getEventTime() {
		return eventTime;
	}
	/**
	 * @param eventTime The eventTime to set.
	 */
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	/**
	 * @return Returns the locationDetails.
	 */
	public String getLocationDetails() {
		return locationDetails;
	}
	/**
	 * @param locationDetails The locationDetails to set.
	 */
	public void setLocationDetails(String locationDetails) {
		this.locationDetails = locationDetails;
	}
	/**
	 * @return Returns the locationUnitName.
	 */
	public String getLocationUnitName() {
		return locationUnitName;
	}
	/**
	 * @param locationUnitName The locationUnitName to set.
	 */
	public void setLocationUnitName(String locationUnitName) {
		this.locationUnitName = locationUnitName;
	}
	/**
	 * @return Returns the mentalIllnessDiagnosis.
	 */
	public  String getMentalIllnessDiagnosis() {
		return mentalIllnessDiagnosis;
	}
	/**
	 * @param mentalIllnessDiagnosis The mentalIllnessDiagnosis to set.
	 */
	public void setMentalIllnessDiagnosis(String mentalIllnessDiagnosis) {
		this.mentalIllnessDiagnosis = mentalIllnessDiagnosis;
	}
	/**
	 * @return Returns the mentalRetardationDiagnosis.
	 */
	public  String getMentalRetardationDiagnosis() {
		return mentalRetardationDiagnosis;
	}
	/**
	 * @param mentalRetardationDiagnosis The mentalRetardationDiagnosis to set.
	 */
	public void setMentalRetardationDiagnosis(String mentalRetardationDiagnosis) {
		this.mentalRetardationDiagnosis = mentalRetardationDiagnosis;
	}
	/**
	 * @return Returns the programStatus.
	 */
	public String getProgramStatus() {
		return programStatus;
	}
	/**
	 * @param programStatus The programStatus to set.
	 */
	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}
	/**
	 * @return Returns the psychiatricAssessment.
	 */
	public  String getPsychiatricAssessment() {
		return psychiatricAssessment;
	}
	/**
	 * @param psychiatricAssessment The psychiatricAssessment to set.
	 */
	public void setPsychiatricAssessment(String psychiatricAssessment) {
		this.psychiatricAssessment = psychiatricAssessment;
	}
	/**
	 * @return Returns the psychologicalAssessment.
	 */
	public  String getPsychologicalAssessment() {
		return psychologicalAssessment;
	}
	/**
	 * @param psychologicalAssessment The psychologicalAssessment to set.
	 */
	public void setPsychologicalAssessment(String psychologicalAssessment) {
		this.psychologicalAssessment = psychologicalAssessment;
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
	 * @return Returns the testType.
	 */
	public String getTestType() {
		return testType;
	}
	/**
	 * @param testType The testType to set.
	 */
	public void setTestType(String testType) {
		this.testType = testType;
	}


	/**
	 * @return Returns the eventType.
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType
	 *            The eventType to set.
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
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
	 * @return Returns the sessionDate.
	 */
	public Date getSessionDate() {
		return sessionDate;
	}

	/**
	 * @param sessionDate
	 *            The sessionDate to set.
	 */
	public void setSessionDate(Date sessionDate) {
		this.sessionDate = sessionDate;
	}

	/**
	 * @return Returns the testSessID.
	 */
	public String getTestSessID() {
		return testSessID;
	}
	/**
	 * @param testSessID The testSessID to set.
	 */
	public void setTestSessID(String testSessID) {
		this.testSessID = testSessID;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) 
	{
		if( obj == null )
		{
			return -1;
		}

		Date eventDateA = getSessionDate();
		Date eventDateB = null ;
		
		if( eventDateA == null )
		{
			return 1;		
		}
		else
		{
			TestingSessionResponseEvent rsp = (TestingSessionResponseEvent)obj;
			eventDateB = rsp.getSessionDate() ;
			if( eventDateB == null )
			{
				return -1;
			}
		}
		
		return eventDateB.compareTo(eventDateA);
	}
	/**
	 * @return Returns the referralStatusCd.
	 */
	public String getReferralStatusCd() {
		return referralStatusCd;
	}
	/**
	 * @param referralStatusCd The referralStatusCd to set.
	 */
	public void setReferralStatusCd(String referralStatusCd) {
		this.referralStatusCd = referralStatusCd;
	}
	/**
	 * @return Returns the referralSubStatusCd.
	 */
	public String getReferralSubStatusCd() {
		return referralSubStatusCd;
	}
	/**
	 * @param referralSubStatusCd The referralSubStatusCd to set.
	 */
	public void setReferralSubStatusCd(String referralSubStatusCd) {
		this.referralSubStatusCd = referralSubStatusCd;
	}
}
