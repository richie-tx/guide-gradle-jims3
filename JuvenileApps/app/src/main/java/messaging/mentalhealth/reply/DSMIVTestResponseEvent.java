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
public class DSMIVTestResponseEvent extends ResponseEvent implements Comparable{

	private Date testDate;
	private String	programReferralNum ;
	private String	serviceProviderName ;
	private String testSessId;
	private String testId;
	private String axis1PrimaryScore;
	private String axis1SecondaryScore;
	private String axis1TertiaryScore;
	private String axis1FourthScore;
	private String axis1FifthScore;
	private String axis2PrimaryScore;
	private String axis2SecondaryScore;
	private String axis3PrimaryScore;
	private String axis3SecondaryScore;
	private boolean axis4EducationalProblems;
	private boolean axis4HousingProblems;
	private boolean axis4OccupationalProblems;
	private boolean axis4HealthProblems;
	private boolean axis4LegalProblems;
	private boolean axis4PsychosocialProblems;
	private boolean axis4SocialEnvironmentProblems;
	private boolean axis4SupportGroupProblems;
	private boolean axis4EconomicProblems;
	private boolean axis4MentalHealthNeeds;
	private boolean axis4MentalHealthTreatment;
	private String dsmivId;
	private String axis4Comments;
	private String axis5Score;
	private String serviceEventId;
	private String instructorName;
	
	//added for fields for change to DSM V
	private Date createDate;
	private String medicalDiagnosis;
	private String diagnosis10;
	
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
	 * @return Returns the axis1FifthScore.
	 */
	public String getAxis1FifthScore() {
		return axis1FifthScore;
	}
	/**
	 * @param axis1FifthScore The axis1FifthScore to set.
	 */
	public void setAxis1FifthScore(String axis1FifthScore) {
		this.axis1FifthScore = axis1FifthScore;
	}
	/**
	 * @return Returns the axis1FourthScore.
	 */
	public String getAxis1FourthScore() {
		return axis1FourthScore;
	}
	/**
	 * @param axis1FourthScore The axis1FourthScore to set.
	 */
	public void setAxis1FourthScore(String axis1FourthScore) {
		this.axis1FourthScore = axis1FourthScore;
	}
	/**
	 * @return Returns the axis1PrimaryScore.
	 */
	public String getAxis1PrimaryScore() {
		return axis1PrimaryScore;
	}
	/**
	 * @param axis1PrimaryScore The axis1PrimaryScore to set.
	 */
	public void setAxis1PrimaryScore(String axis1PrimaryScore) {
		this.axis1PrimaryScore = axis1PrimaryScore;
	}
	/**
	 * @return Returns the axis1SecondaryScore.
	 */
	public String getAxis1SecondaryScore() {
		return axis1SecondaryScore;
	}
	/**
	 * @param axis1SecondaryScore The axis1SecondaryScore to set.
	 */
	public void setAxis1SecondaryScore(String axis1SecondaryScore) {
		this.axis1SecondaryScore = axis1SecondaryScore;
	}
	/**
	 * @return Returns the axis1TertiaryScore.
	 */
	public String getAxis1TertiaryScore() {
		return axis1TertiaryScore;
	}
	/**
	 * @param axis1TertiaryScore The axis1TertiaryScore to set.
	 */
	public void setAxis1TertiaryScore(String axis1TertiaryScore) {
		this.axis1TertiaryScore = axis1TertiaryScore;
	}
	/**
	 * @return Returns the axis2PrimaryScore.
	 */
	public String getAxis2PrimaryScore() {
		return axis2PrimaryScore;
	}
	/**
	 * @param axis2PrimaryScore The axis2PrimaryScore to set.
	 */
	public void setAxis2PrimaryScore(String axis2PrimaryScore) {
		this.axis2PrimaryScore = axis2PrimaryScore;
	}
	/**
	 * @return Returns the axis2SecondaryScore.
	 */
	public String getAxis2SecondaryScore() {
		return axis2SecondaryScore;
	}
	/**
	 * @param axis2SecondaryScore The axis2SecondaryScore to set.
	 */
	public void setAxis2SecondaryScore(String axis2SecondaryScore) {
		this.axis2SecondaryScore = axis2SecondaryScore;
	}
	/**
	 * @return Returns the axis3PrimaryScore.
	 */
	public String getAxis3PrimaryScore() {
		return axis3PrimaryScore;
	}
	/**
	 * @param axis3PrimaryScore The axis3PrimaryScore to set.
	 */
	public void setAxis3PrimaryScore(String axis3PrimaryScore) {
		this.axis3PrimaryScore = axis3PrimaryScore;
	}
	/**
	 * @return Returns the axis3SecondaryScore.
	 */
	public String getAxis3SecondaryScore() {
		return axis3SecondaryScore;
	}
	/**
	 * @param axis3SecondaryScore The axis3SecondaryScore to set.
	 */
	public void setAxis3SecondaryScore(String axis3SecondaryScore) {
		this.axis3SecondaryScore = axis3SecondaryScore;
	}
	/**
	 * @return Returns the axis4Comments.
	 */
	public String getAxis4Comments() {
		return axis4Comments;
	}
	/**
	 * @param axis4Comments The axis4Comments to set.
	 */
	public void setAxis4Comments(String axis4Comments) {
		this.axis4Comments = axis4Comments;
	}
	/**
	 * @return Returns the axis4EconomicProblems.
	 */
	public boolean isAxis4EconomicProblems() {
		return axis4EconomicProblems;
	}
	/**
	 * @param axis4EconomicProblems The axis4EconomicProblems to set.
	 */
	public void setAxis4EconomicProblems(boolean axis4EconomicProblems) {
		this.axis4EconomicProblems = axis4EconomicProblems;
	}
	/**
	 * @return Returns the axis4EducationalProblems.
	 */
	public boolean isAxis4EducationalProblems() {
		return axis4EducationalProblems;
	}
	/**
	 * @param axis4EducationalProblems The axis4EducationalProblems to set.
	 */
	public void setAxis4EducationalProblems(boolean axis4EducationalProblems) {
		this.axis4EducationalProblems = axis4EducationalProblems;
	}
	/**
	 * @return Returns the axis4HealthProblems.
	 */
	public boolean isAxis4HealthProblems() {
		return axis4HealthProblems;
	}
	/**
	 * @param axis4HealthProblems The axis4HealthProblems to set.
	 */
	public void setAxis4HealthProblems(boolean axis4HealthProblems) {
		this.axis4HealthProblems = axis4HealthProblems;
	}
	/**
	 * @return Returns the axis4HousingProblems.
	 */
	public boolean isAxis4HousingProblems() {
		return axis4HousingProblems;
	}
	/**
	 * @param axis4HousingProblems The axis4HousingProblems to set.
	 */
	public void setAxis4HousingProblems(boolean axis4HousingProblems) {
		this.axis4HousingProblems = axis4HousingProblems;
	}
	/**
	 * @return Returns the axis4LegalProblems.
	 */
	public boolean isAxis4LegalProblems() {
		return axis4LegalProblems;
	}
	/**
	 * @param axis4LegalProblems The axis4LegalProblems to set.
	 */
	public void setAxis4LegalProblems(boolean axis4LegalProblems) {
		this.axis4LegalProblems = axis4LegalProblems;
	}
	/**
	 * @return Returns the axis4OccupationalProblems.
	 */
	public boolean isAxis4OccupationalProblems() {
		return axis4OccupationalProblems;
	}
	/**
	 * @param axis4OccupationalProblems The axis4OccupationalProblems to set.
	 */
	public void setAxis4OccupationalProblems(boolean axis4OccupationalProblems) {
		this.axis4OccupationalProblems = axis4OccupationalProblems;
	}
	/**
	 * @return Returns the axis4PsychosocialProblems.
	 */
	public boolean isAxis4PsychosocialProblems() {
		return axis4PsychosocialProblems;
	}
	/**
	 * @param axis4PsychosocialProblems The axis4PsychosocialProblems to set.
	 */
	public void setAxis4PsychosocialProblems(boolean axis4PsychosocialProblems) {
		this.axis4PsychosocialProblems = axis4PsychosocialProblems;
	}
	/**
	 * @return Returns the axis4SocialEnvironmentProblems.
	 */
	public boolean isAxis4SocialEnvironmentProblems() {
		return axis4SocialEnvironmentProblems;
	}
	/**
	 * @param axis4SocialEnvironmentProblems The axis4SocialEnvironmentProblems to set.
	 */
	public void setAxis4SocialEnvironmentProblems(boolean axis4SocialEnvironmentProblems) {
		this.axis4SocialEnvironmentProblems = axis4SocialEnvironmentProblems;
	}
	/**
	 * @return Returns the axis4SupportGroupProblems.
	 */
	public boolean isAxis4SupportGroupProblems() {
		return axis4SupportGroupProblems;
	}
	/**
	 * @param axis4SupportGroupProblems The axis4SupportGroupProblems to set.
	 */
	public void setAxis4SupportGroupProblems(boolean axis4SupportGroupProblems) {
		this.axis4SupportGroupProblems = axis4SupportGroupProblems;
	}
	/**
	 * @return Returns the axis5Score.
	 */
	public String getAxis5Score() {
		return axis5Score;
	}
	/**
	 * @param axis5Score The axis5Score to set.
	 */
	public void setAxis5Score(String axis5Score) {
		this.axis5Score = axis5Score;
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
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the medicalDiagnosis
	 */
	public String getMedicalDiagnosis() {
		return medicalDiagnosis;
	}
	/**
	 * @param medicalDiagnosis the medicalDiagnosis to set
	 */
	public void setMedicalDiagnosis(String medicalDiagnosis) {
		this.medicalDiagnosis = medicalDiagnosis;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		DSMIVTestResponseEvent rsp = (DSMIVTestResponseEvent)obj;
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

	public boolean isAxis4MentalHealthNeeds() {
		return axis4MentalHealthNeeds;
	}
	public void setAxis4MentalHealthNeeds(boolean axis4MentalHealthNeeds) {
		this.axis4MentalHealthNeeds = axis4MentalHealthNeeds;
	}
	public String getDsmivId() {
		return dsmivId;
	}
	public void setDsmivId(String dsmivId) {
		this.dsmivId = dsmivId;
	}
	public boolean isAxis4MentalHealthTreatment() {
		return axis4MentalHealthTreatment;
	}
	public void setAxis4MentalHealthTreatment(boolean axis4MentalHealthTreatment) {
		this.axis4MentalHealthTreatment = axis4MentalHealthTreatment;
	}
	
	/**
	 * @param diagnosis10 The diagnosis10 to set.
	 */
	public void setDiagnosis10(String diagnosis10) {
		this.diagnosis10 = diagnosis10;
	}
	/**
	 * @return Returns the diagnosis10.
	 */
	public String getDiagnosis10() {
		return diagnosis10;
	}
}
