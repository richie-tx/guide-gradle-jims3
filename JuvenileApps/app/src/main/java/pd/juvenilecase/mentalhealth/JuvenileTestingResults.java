package pd.juvenilecase.mentalhealth;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.supervision.calendar.ServiceEvent;

/**
 * @roseuid 45D4B4BD004C
 */
public class JuvenileTestingResults extends PersistentObject
{
	private String actualSessionLength;
	private String psychologicalAssessment;
	private String psychiatricAssessment;
	private String mentalRetardationDiagnosis;
	private String mentalillnessDiagnosis;
	private String recommendations;
	private String  testSessID;
	private Date sessionDate;
	private String programReferralNum;
	private String programStatus;
	private String eventSessionLength;
	private String eventStatus;
	private String eventTime;
	private String eventType;
	private String juvenileNum;
    private String locationDetails;
	private String locationUnitName;
	private Date referralDate ;
	private String serviceEventId;
	private String juvenileDSMIVResultsId;
	
	private Date sentDate;
	private String referralStatusCd;
	private String referralSubStatusCd;
	
	/**
	* @detailerDoNotGenerate true
	* @referencedType pd.codetable.Code
	* @contextKey TEST_TYPE
	*/
	
	public Code  testType;
	private String testTypeId;
	
	//Just for Display purpose
	private String serviceProviderName;
	private String instructorName ;
	private String instrLastName;
	private String instrFirstName;
	private String instrMiddleName;
	
	/**
	 * 
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum()
	{
		fetch();
		return juvenileNum;
	}

	/**
	 * 
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		if (this.juvenileNum == null || !this.juvenileNum.equals(juvenileNum))
		{
			markModified();
		}
		this.juvenileNum = juvenileNum;
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
		if (this.testSessID == null || !this.testSessID.equals(testSessID))
		{
			markModified();
		}
		this.testSessID = testSessID;
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
	 * @return Returns the eventType.
	 */
	public String getEventType() {
		return eventType;
	}
	/**
	 * @param eventType The eventType to set.
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
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
	 * @return Returns the sessionDate.
	 */
	public Date getSessionDate() {
		return sessionDate;
	}
	/**
	 * @param sessionDate The sessionDate to set.
	 */
	public void setSessionDate(Date sessionDate) {
		this.sessionDate = sessionDate;
	}
	
	
	
	
	
	/**
	 * Properties for serviceEvent
	 */
	private ServiceEvent serviceEvent = null;
	/**
	 * Properties for testType
	 */
	//private Code testType = null;
	/**
	 * Properties for juvenileDSMIVResults
	 */
	private JuvenileDSMIVResults juvenileDSMIVResults = null;
	/**
	 * Properties for juvenileIQResults
	 * @referencedType pd.juvenilecase.mentalhealth.JuvenileIQResults
	 */
	private java.util.Collection juvenileIQResults = null;
	/**
	 * Properties for juvenileAchievementResults
	 * @referencedType pd.juvenilecase.mentalhealth.JuvenileAchievementResults
	 */
	private java.util.Collection juvenileAchievementResults = null;
	/**
	 * Properties for juvenileAdaptiveFunctioningResults
	 * @referencedType pd.juvenilecase.mentalhealth.JuvenileAdaptiveFunctioningResults
	 */
	private java.util.Collection juvenileAdaptiveFunctioningResults = null;
	

	/**
	 * @roseuid 45D4B4BD004C
	 */
	public JuvenileTestingResults()
	{
	}

	/** 
	 * @roseuid 45AF7A0A0192
	 * @return Iterator of JuvenileTestingResults
	* @param event
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileTestingResults.class);
	}
	
	 public static Iterator findAll(String attributeName, String attributeValue)
	 {
	     IHome home = new Home();
	     return home.findAll(attributeName, attributeValue, JuvenileTestingResults.class);
	     
	 }
	
	/**
	* @return JuvenileTestingResults
	* @param event
	* @roseuid 45AF7A0A0190
	*/
	static public JuvenileTestingResults find(String testSessID)
	{
		JuvenileTestingResults juvenileTestingDetails = null;
		IHome home = new Home();
		juvenileTestingDetails = (JuvenileTestingResults) home.find(testSessID, JuvenileTestingResults.class);
		return juvenileTestingDetails;
	}	
	
	/**
	 * 
	 * @return Returns the actualSessionLength.
	 */
	public String getActualSessionLength()
	{
		fetch();
		return actualSessionLength;
	}

	/**
	 * 
	 * @param actualSessionLength The actualSessionLength to set.
	 */
	public void setActualSessionLength(String actualSessionLength)
	{
		if (this.actualSessionLength == null || !this.actualSessionLength.equals(actualSessionLength))
		{
			markModified();
		}
		this.actualSessionLength = actualSessionLength;
	}

	/**
	 * 
	 * @return Returns the juvenileAchievementResults.
	 */
	public java.util.Collection getJuvenileAchievementResults()
	{
		initJuvenileAchievementResults();
		return juvenileAchievementResults;
	}

	/**
	 * 
	 * @param juvenileAchievementResults The juvenileAchievementResults to set.
	 */
	public void setJuvenileAchievementResults(java.util.Collection juvenileAchievementResults)
	{
		if (this.juvenileAchievementResults == null
				|| !this.juvenileAchievementResults.equals(juvenileAchievementResults))
		{
			markModified();
		}
		this.juvenileAchievementResults = juvenileAchievementResults;
	}

	/**
	 * 
	 * @return Returns the juvenileAdaptiveFunctioningResults.
	 */
	public java.util.Collection getJuvenileAdaptiveFunctioningResults()
	{
		initJuvenileAdaptiveFunctioningResults();
		return juvenileAdaptiveFunctioningResults;
	}

	/**
	 * 
	 * @param juvenileAdaptiveFunctioningResults The juvenileAdaptiveFunctioningResults to set.
	 */
	public void setJuvenileAdaptiveFunctioningResults(java.util.Collection juvenileAdaptiveFunctioningResults)
	{
		if (this.juvenileAdaptiveFunctioningResults == null
				|| !this.juvenileAdaptiveFunctioningResults.equals(juvenileAdaptiveFunctioningResults))
		{
			markModified();
		}
		this.juvenileAdaptiveFunctioningResults = juvenileAdaptiveFunctioningResults;
	}

	/**
	 * 
	 * @return Returns the juvenileDSMIVResults.
	 */
	public JuvenileDSMIVResults getJuvenileDSMIVResults()
	{
		initJuvenileDSMIVResults();
		return juvenileDSMIVResults;
	}

	/**
	 * 
	 * @return Returns the juvenileDSMIVResultsId.
	 */
	public String getJuvenileDSMIVResultsId()
	{
		fetch();
		return juvenileDSMIVResultsId;
	}

	/**
	 * 
	 * @param juvenileDSMIVResultsId The juvenileDSMIVResultsId to set.
	 */
	public void setJuvenileDSMIVResultsId(String juvenileDSMIVResultsId)
	{
		if (this.juvenileDSMIVResultsId == null || !this.juvenileDSMIVResultsId.equals(juvenileDSMIVResultsId))
		{
			markModified();
		}
		this.juvenileDSMIVResultsId = juvenileDSMIVResultsId;
	}

	/**
	 * 
	 * @return Returns the juvenileIQResults.
	 */
	public java.util.Collection getJuvenileIQResults()
	{
		initJuvenileIQResults();
		return juvenileIQResults;
	}

	/**
	 * 
	 * @param juvenileIQResults The juvenileIQResults to set.
	 */
	public void setJuvenileIQResults(java.util.Collection juvenileIQResults)
	{
		if (this.juvenileIQResults == null || !this.juvenileIQResults.equals(juvenileIQResults))
		{
			markModified();
		}
		this.juvenileIQResults = juvenileIQResults;
	}

		

	/**
	 * 
	 * @return Returns the mentalRetardationDiagnosis.
	 */
	public String getMentalRetardationDiagnosis()
	{
		fetch();
		return mentalRetardationDiagnosis;
	}

	/**
	 * 
	 * @param mentalRetardationDiagnosis The mentalRetardationDiagnosis to set.
	 */
	public void setMentalRetardationDiagnosis(String mentalRetardationDiagnosis)
	{
		if (this.mentalRetardationDiagnosis  == null || !this.mentalRetardationDiagnosis.equals(mentalRetardationDiagnosis))
		{
			markModified();
		}
		this.mentalRetardationDiagnosis = mentalRetardationDiagnosis;
	}

	/**
	 * 
	 * @return Returns the psychiatricAssessment.
	 */
	public String getPsychiatricAssessment()
	{
		fetch();
		return psychiatricAssessment;
	}

	/**
	 * 
	 * @param psychiatricAssessment The psychiatricAssessment to set.
	 */
	public void setPsychiatricAssessment(String psychiatricAssessment)
	{
		if (this.psychiatricAssessment  == null || !this.psychiatricAssessment.equals(psychiatricAssessment))
		{
			markModified();
		}
		this.psychiatricAssessment = psychiatricAssessment;
	}

	/**
	 * 
	 * @return Returns the psychologicalAssessment.
	 */
	public String getPsychologicalAssessment()
	{
		fetch();
		return psychologicalAssessment;
	}

	/**
	 * 
	 * @param psychologicalAssessment The psychologicalAssessment to set.
	 */
	public void setPsychologicalAssessment(String psychologicalAssessment)
	{
		if (this.psychologicalAssessment  == null || !this.psychologicalAssessment.equals(psychologicalAssessment))
		{
			markModified();
		}
		this.psychologicalAssessment = psychologicalAssessment;
	}

	/**
	 * 
	 * @return Returns the recommendations.
	 */
	public String getRecommendations()
	{
		fetch();
		return recommendations;
	}

	/**
	 * 
	 * @param recommendations The recommendations to set.
	 */
	public void setRecommendations(String recommendations)
	{
		if (this.recommendations == null || !this.recommendations.equals(recommendations))
		{
			markModified();
		}
		this.recommendations = recommendations;
	}

	/**
	 * 
	 * @return Returns the serviceEvent.
	 */
	public ServiceEvent getServiceEvent()
	{
		initServiceEvent();
		return serviceEvent;
	}

	/**
	 * 
	 * @return Returns the serviceEventId.
	 */
	public String getServiceEventId()
	{
		fetch();
		return serviceEventId;
	}

	/**
	 * 
	 * @param serviceEventId The serviceEventId to set.
	 */
	public void setServiceEventId(String serviceEventId)
	{
		if (this.serviceEventId == null || !this.serviceEventId.equals(serviceEventId))
		{
			markModified();
		}
		this.serviceEventId = serviceEventId;
	}

	//begin
	/**
	 * 
	 * @return Returns the testType.
	 */
	public Code getTestType()
	{
		initTestType();
		return testType;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initTestType()
	{
		if (testType == null)
		{
			testType = (Code) new mojo.km.persistence.Reference(testTypeId, Code.class)
					.getObject();
		}
	}
	/**
	 * 
	 * @param testType The testType to set.
	 */
	public void setTestType(Code testType)
	{
		if (this.testType == null || !this.testType.equals(testType))
		{
			markModified();
		}
		this.testType = testType;
	}

	/**
	 * 
	 * @return Returns the testTypeId.
	 */
	public String getTestTypeId()
	{
		fetch();
		return testTypeId;
	}

	/**
	 * 
	 * @param testTypeId The testTypeId to set.
	 */
	public void setTestTypeId(String testTypeId)
	{
		if (this.testTypeId == null || !this.testTypeId.equals(testTypeId))
		{
			markModified();
		}
		this.testTypeId = testTypeId;
	}
	//end

	/**
	 * Initialize class relationship to class pd.supervision.calendar.ServiceEvent
	 */
	private void initServiceEvent()
	{
		if (serviceEvent == null)
		{
			serviceEvent = (ServiceEvent) new mojo.km.persistence.Reference(serviceEventId,
					ServiceEvent.class).getObject();
		}
	}

	/**
	 * set the type reference for class member serviceEvent
	 */
	public void setServiceEvent(ServiceEvent serviceEvent)
	{
		if (this.serviceEvent == null || !this.serviceEvent.equals(serviceEvent))
		{
			markModified();
		}
		if (serviceEvent.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(serviceEvent);
		}
		setServiceEventId("" + serviceEvent.getOID());
		this.serviceEvent = (ServiceEvent) new mojo.km.persistence.Reference(serviceEvent)
				.getObject();
	}

	

	/**
	 * Initialize class relationship to class pd.juvenilecase.mentalhealth.JuvenileDSMIVResults
	 */
	private void initJuvenileDSMIVResults()
	{
		if (juvenileDSMIVResults == null)
		{
			juvenileDSMIVResults = (JuvenileDSMIVResults) new mojo.km.persistence.Reference(
					juvenileDSMIVResultsId, JuvenileDSMIVResults.class).getObject();
		}
	}

	/**
	 * set the type reference for class member juvenileDSMIVResults
	 */
	public void setJuvenileDSMIVResults(JuvenileDSMIVResults juvenileDSMIVResults)
	{
		if (this.juvenileDSMIVResults == null || !this.juvenileDSMIVResults.equals(juvenileDSMIVResults))
		{
			markModified();
		}
		if (juvenileDSMIVResults.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(juvenileDSMIVResults);
		}
		setJuvenileDSMIVResultsId("" + juvenileDSMIVResults.getOID());
		this.juvenileDSMIVResults = (JuvenileDSMIVResults) new mojo.km.persistence.Reference(
				juvenileDSMIVResults).getObject();
	}

	/**
	 * Initialize class relationship implementation for pd.juvenilecase.mentalhealth.JuvenileIQResults
	 */
	private void initJuvenileIQResults()
	{
		if (juvenileIQResults == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			juvenileIQResults = new mojo.km.persistence.ArrayList(JuvenileIQResults.class,
					"juvenileTestingResultsId", "" + getOID());
		}
	}

	/**
	 * insert a pd.juvenilecase.mentalhealth.JuvenileIQResults into class relationship collection.
	 */
	public void insertJuvenileIQResults(JuvenileIQResults anObject)
	{
		initJuvenileIQResults();
		juvenileIQResults.add(anObject);
	}

	/**
	 * Removes a pd.juvenilecase.mentalhealth.JuvenileIQResults from class relationship collection.
	 */
	public void removeJuvenileIQResults(JuvenileIQResults anObject)
	{
		initJuvenileIQResults();
		juvenileIQResults.remove(anObject);
	}

	/**
	 * Clears all pd.juvenilecase.mentalhealth.JuvenileIQResults from class relationship collection.
	 */
	public void clearJuvenileIQResults()
	{
		initJuvenileIQResults();
		juvenileIQResults.clear();
	}

	/**
	 * Initialize class relationship implementation for pd.juvenilecase.mentalhealth.JuvenileAchievementResults
	 */
	private void initJuvenileAchievementResults()
	{
		if (juvenileAchievementResults == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			juvenileAchievementResults = new mojo.km.persistence.ArrayList(
					JuvenileAchievementResults.class, "juvenileTestingResultsId", ""
							+ getOID());
		}
	}

	/**
	 * insert a pd.juvenilecase.mentalhealth.JuvenileAchievementResults into class relationship collection.
	 */
	public void insertJuvenileAchievementResults(JuvenileAchievementResults anObject)
	{
		initJuvenileAchievementResults();
		juvenileAchievementResults.add(anObject);
	}

	/**
	 * Removes a pd.juvenilecase.mentalhealth.JuvenileAchievementResults from class relationship collection.
	 */
	public void removeJuvenileAchievementResults(JuvenileAchievementResults anObject)
	{
		initJuvenileAchievementResults();
		juvenileAchievementResults.remove(anObject);
	}

	/**
	 * Clears all pd.juvenilecase.mentalhealth.JuvenileAchievementResults from class relationship collection.
	 */
	public void clearJuvenileAchievementResults()
	{
		initJuvenileAchievementResults();
		juvenileAchievementResults.clear();
	}

	/**
	 * Initialize class relationship implementation for pd.juvenilecase.mentalhealth.JuvenileAdaptiveFunctioningResults
	 */
	private void initJuvenileAdaptiveFunctioningResults()
	{
		if (juvenileAdaptiveFunctioningResults == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			juvenileAdaptiveFunctioningResults = new mojo.km.persistence.ArrayList(
					JuvenileAdaptiveFunctioningResults.class, "juvenileTestingResultsId",
					"" + getOID());
		}
	}

	/**
	 * insert a pd.juvenilecase.mentalhealth.JuvenileAdaptiveFunctioningResults into class relationship collection.
	 */
	public void insertJuvenileAdaptiveFunctioningResults(
			JuvenileAdaptiveFunctioningResults anObject)
	{
		initJuvenileAdaptiveFunctioningResults();
		juvenileAdaptiveFunctioningResults.add(anObject);
	}

	/**
	 * Removes a pd.juvenilecase.mentalhealth.JuvenileAdaptiveFunctioningResults from class relationship collection.
	 */
	public void removeJuvenileAdaptiveFunctioningResults(
			JuvenileAdaptiveFunctioningResults anObject)
	{
		initJuvenileAdaptiveFunctioningResults();
		juvenileAdaptiveFunctioningResults.remove(anObject);
	}

	/**
	 * Clears all pd.juvenilecase.mentalhealth.JuvenileAdaptiveFunctioningResults from class relationship collection.
	 */
	public void clearJuvenileAdaptiveFunctioningResults()
	{
		initJuvenileAdaptiveFunctioningResults();
		juvenileAdaptiveFunctioningResults.clear();
	}
	
	/**
	 * @return Returns the instrFirstName.
	 */
	public String getInstrFirstName() {
		return instrFirstName;
	}
	/**
	 * @param instrFirstName The instrFirstName to set.
	 */
	public void setInstrFirstName(String instrFirstName) {
		this.instrFirstName = instrFirstName;
	}
	/**
	 * @return Returns the instrLastName.
	 */
	public String getInstrLastName() {
		return instrLastName;
	}
	/**
	 * @param instrLastName The instrLastName to set.
	 */
	public void setInstrLastName(String instrLastName) {
		this.instrLastName = instrLastName;
	}
	/**
	 * @return Returns the instrMiddleName.
	 */
	public String getInstrMiddleName() {
		return (this.instrMiddleName == null)?"":this.instrMiddleName;
	}
	/**
	 * @param instrMiddleName The instrMiddleName to set.
	 */
	public void setInstrMiddleName(String instrMiddleName) {
		this.instrMiddleName = instrMiddleName;
	}
	/**
	 * @return Returns the mentalillnessDiagnosis.
	 */
	public String getMentalillnessDiagnosis() {
		fetch();
		return mentalillnessDiagnosis;
	}
	/**
	 * @param mentalillnessDiagnosis The mentalillnessDiagnosis to set.
	 */
	public void setMentalillnessDiagnosis(String mentalillnessDiagnosis) {
		if (this.mentalillnessDiagnosis  == null || !this.mentalillnessDiagnosis.equals(mentalillnessDiagnosis))
		{
			markModified();
		}
		this.mentalillnessDiagnosis = mentalillnessDiagnosis;
	}
	/**
	 * @return Returns the referralStatusCd.
	 */
	public String getReferralStatusCd() {
		fetch();
		return referralStatusCd;
	}
	/**
	 * @param referralStatusCd The referralStatusCd to set.
	 */
	public void setReferralStatusCd(String referralStatusCd) {
		if (this.referralStatusCd  == null || !this.referralStatusCd.equals(referralStatusCd))
		{
			markModified();
		}
		this.referralStatusCd = referralStatusCd;
	}
	/**
	 * @return Returns the referralSubStatusCd.
	 */
	public String getReferralSubStatusCd() {
		fetch();
		return referralSubStatusCd;
	}
	/**
	 * @param referralSubStatusCd The referralSubStatusCd to set.
	 */
	public void setReferralSubStatusCd(String referralSubStatusCd) {
		if (this.referralSubStatusCd  == null || !this.referralSubStatusCd.equals(referralSubStatusCd))
		{
			markModified();
		}
		this.referralSubStatusCd = referralSubStatusCd;
	}
	/**
	 * @return Returns the sentDate.
	 */
	public Date getSentDate() {
		fetch();
		return sentDate;
	}
	/**
	 * @param sentDate The sentDate to set.
	 */
	public void setSentDate(Date sentDate) {
		if (this.sentDate  == null || !this.sentDate.equals(sentDate))
		{
			markModified();
		}
		this.sentDate = sentDate;
	}
}
