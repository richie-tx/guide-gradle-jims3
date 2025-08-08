// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\mentalhealth\\CreateMentalHealthDSMIVEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class CreateMentalHealthDSMIVEvent extends RequestEvent {
	public String serviceProviderName;

	public String instructorName;

	public Date testDate;

	public String axisIPrimaryScore;

	public String axisISecondaryScore;

	public String axisITertiaryScore;

	public String axisIFourth;

	public String axisIFifth;

	public String axisIIPrimaryScore;

	public String axisIISecondaryScore;

	public String axisIIIPrimaryScore;

	public String axisIIISecondaryScore;

	public boolean educationalProblems;

	public boolean housingProblems;

	public boolean occupationalProblems;

	public boolean healthCareAccessProblems;

	public boolean legalSystemProblems;

	public boolean otherPsychoEnvProblems;

	public boolean socialEnvProblems;

	public boolean primarySuppGrpProblems;

	public boolean economicProblems;

	public boolean mentalHealthNeeds;
	
	public boolean mentalHealthTreatment;
	
	public String dsmivId;
	
	public String axisIVComments;

	public String axisVScore;

	private String testId;

	private String testSessId;
	
	private String juvenileNum;
	
	//added for change from DSM IV to DSM V ER# 75795	
	private String medicalDiagnosis;	
	private String diagnosis10 = "";	

	/**
	 * @roseuid 45D4AF550067
	 */
	public CreateMentalHealthDSMIVEvent() {

	}

	/**
	 * @param serviceProviderName
	 * @roseuid 45D49C840069
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}

	/**
	 * @return String
	 * @roseuid 45D49C84006B
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}

	/**
	 * @param instructorName
	 * @roseuid 45D49C840078
	 */
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	/**
	 * @return String
	 * @roseuid 45D49C84007A
	 */
	public String getInstructorName() {
		return instructorName;
	}

	/**
	 * @param testDate
	 * @roseuid 45D49C840088
	 */
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	/**
	 * @return java.util.Date
	 * @roseuid 45D49C84008A
	 */
	public Date getTestDate() {
		return testDate;
	}

	/**
	 * @param axisIPrimaryScore
	 * @roseuid 45D49C840097
	 */
	public void setAxisIPrimaryScore(String axisIPrimaryScore) {
		this.axisIPrimaryScore = axisIPrimaryScore;
	}

	/**
	 * @return String
	 * @roseuid 45D49C840099
	 */
	public String getAxisIPrimaryScore() {
		return axisIPrimaryScore;
	}

	/**
	 * @param axisISecondaryScore
	 * @roseuid 45D49C8400A7
	 */
	public void setAxisISecondaryScore(String axisISecondaryScore) {
		this.axisISecondaryScore = axisISecondaryScore;
	}

	/**
	 * @return String
	 * @roseuid 45D49C8400A9
	 */
	public String getAxisISecondaryScore() {
		return axisISecondaryScore;
	}

	/**
	 * @param axisITertiaryScore
	 * @roseuid 45D49C8400B7
	 */
	public void setAxisITertiaryScore(String axisITertiaryScore) {
		this.axisITertiaryScore = axisITertiaryScore;
	}

	/**
	 * @return String
	 * @roseuid 45D49C8400B9
	 */
	public String getAxisITertiaryScore() {
		return axisITertiaryScore;
	}

	/**
	 * @param axisIFourth
	 * @roseuid 45D49C8400C6
	 */
	public void setAxisIFourth(String axisIFourth) {
		this.axisIFourth = axisIFourth;
	}

	/**
	 * @return String
	 * @roseuid 45D49C8400C8
	 */
	public String getAxisIFourth() {
		return axisIFourth;
	}

	/**
	 * @param axisIFifth
	 * @roseuid 45D49C8400D6
	 */
	public void setAxisIFifth(String axisIFifth) {
		this.axisIFifth = axisIFifth;
	}

	/**
	 * @return String
	 * @roseuid 45D49C8400D8
	 */
	public String getAxisIFifth() {
		return axisIFifth;
	}

	/**
	 * @param axisIIPrimaryScore
	 * @roseuid 45D49C8400E5
	 */
	public void setAxisIIPrimaryScore(String axisIIPrimaryScore) {
		this.axisIIPrimaryScore = axisIIPrimaryScore;
	}

	/**
	 * @return String
	 * @roseuid 45D49C8400E7
	 */
	public String getAxisIIPrimaryScore() {
		return axisIIPrimaryScore;
	}

	/**
	 * @param axisIISecondaryScore
	 * @roseuid 45D49C8400F5
	 */
	public void setAxisIISecondaryScore(String axisIISecondaryScore) {
		this.axisIISecondaryScore = axisIISecondaryScore;
	}

	/**
	 * @return String
	 * @roseuid 45D49C8400F7
	 */
	public String getAxisIISecondaryScore() {
		return axisIISecondaryScore;
	}

	/**
	 * @param axisIIIPrimaryScore
	 * @roseuid 45D49C8400F9
	 */
	public void setAxisIIIPrimaryScore(String axisIIIPrimaryScore) {
		this.axisIIIPrimaryScore = axisIIIPrimaryScore;
	}

	/**
	 * @return String
	 * @roseuid 45D49C840106
	 */
	public String getAxisIIIPrimaryScore() {
		return axisIIIPrimaryScore;
	}

	/**
	 * @return Returns the axisIIISecondaryScore.
	 * @roseuid 45D49C84007A
	 */
	public String getAxisIIISecondaryScore() {
		return axisIIISecondaryScore;
	}

	/**
	 * @param axisIIISecondaryScore
	 *            The axisIIISecondaryScore to set.
	 */
	public void setAxisIIISecondaryScore(String axisIIISecondaryScore) {
		this.axisIIISecondaryScore = axisIIISecondaryScore;
	}

	/**
	 * @return Returns the axisIVComments.
	 */
	public String getAxisIVComments() {
		return axisIVComments;
	}

	/**
	 * @param axisIVComments
	 *            The axisIVComments to set.
	 */
	public void setAxisIVComments(String axisIVComments) {
		this.axisIVComments = axisIVComments;
	}

	/**
	 * @return Returns the axisVScore.
	 */
	public String getAxisVScore() {
		return axisVScore;
	}

	/**
	 * @param axisVScore
	 *            The axisVScore to set.
	 */
	public void setAxisVScore(String axisVScore) {
		this.axisVScore = axisVScore;
	}

	/**
	 * @return Returns the economicProblems.
	 */
	public boolean isEconomicProblems() {
		return economicProblems;
	}

	/**
	 * @param economicProblems
	 *            The economicProblems to set.
	 */
	public void setEconomicProblems(boolean economicProblems) {
		this.economicProblems = economicProblems;
	}

	/**
	 * @return Returns the educationalProblems.
	 */
	public boolean isEducationalProblems() {
		return educationalProblems;
	}

	/**
	 * @param educationalProblems
	 *            The educationalProblems to set.
	 */
	public void setEducationalProblems(boolean educationalProblems) {
		this.educationalProblems = educationalProblems;
	}

	/**
	 * @return Returns the healthCareAccessProblems.
	 */
	public boolean isHealthCareAccessProblems() {
		return healthCareAccessProblems;
	}

	/**
	 * @param healthCareAccessProblems
	 *            The healthCareAccessProblems to set.
	 */
	public void setHealthCareAccessProblems(boolean healthCareAccessProblems) {
		this.healthCareAccessProblems = healthCareAccessProblems;
	}

	/**
	 * @return Returns the housingProblems.
	 */
	public boolean isHousingProblems() {
		return housingProblems;
	}

	/**
	 * @param housingProblems
	 *            The housingProblems to set.
	 */
	public void setHousingProblems(boolean housingProblems) {
		this.housingProblems = housingProblems;
	}

	/**
	 * @return Returns the legalSystemProblems.
	 */
	public boolean isLegalSystemProblems() {
		return legalSystemProblems;
	}

	/**
	 * @param legalSystemProblems
	 *            The legalSystemProblems to set.
	 */
	public void setLegalSystemProblems(boolean legalSystemProblems) {
		this.legalSystemProblems = legalSystemProblems;
	}

	/**
	 * @return Returns the occupationalProblems.
	 */
	public boolean isOccupationalProblems() {
		return occupationalProblems;
	}

	/**
	 * @param occupationalProblems
	 *            The occupationalProblems to set.
	 */
	public void setOccupationalProblems(boolean occupationalProblems) {
		this.occupationalProblems = occupationalProblems;
	}

	/**
	 * @return Returns the otherPsychoEnvProblems.
	 */
	public boolean isOtherPsychoEnvProblems() {
		return otherPsychoEnvProblems;
	}

	/**
	 * @param otherPsychoEnvProblems
	 *            The otherPsychoEnvProblems to set.
	 */
	public void setOtherPsychoEnvProblems(boolean otherPsychoEnvProblems) {
		this.otherPsychoEnvProblems = otherPsychoEnvProblems;
	}

	/**
	 * @return Returns the primarySuppGrpProblems.
	 */
	public boolean isPrimarySuppGrpProblems() {
		return primarySuppGrpProblems;
	}

	/**
	 * @param primarySuppGrpProblems
	 *            The primarySuppGrpProblems to set.
	 */
	public void setPrimarySuppGrpProblems(boolean primarySuppGrpProblems) {
		this.primarySuppGrpProblems = primarySuppGrpProblems;
	}

	/**
	 * @return Returns the socialEnvProblems.
	 */
	public boolean isSocialEnvProblems() {
		return socialEnvProblems;
	}

	/**
	 * @param socialEnvProblems
	 *            The socialEnvProblems to set.
	 */
	public void setSocialEnvProblems(boolean socialEnvProblems) {
		this.socialEnvProblems = socialEnvProblems;
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

	public boolean isMentalHealthNeeds() {
		return mentalHealthNeeds;
	}

	public void setMentalHealthNeeds(boolean mentalHealthNeeds) {
		this.mentalHealthNeeds = mentalHealthNeeds;
	}

	public boolean isMentalHealthTreatment() {
		return mentalHealthTreatment;
	}

	public void setMentalHealthTreatment(boolean mentalHealthTreatment) {
		this.mentalHealthTreatment = mentalHealthTreatment;
	}

	public String getDsmivId() {
		return dsmivId;
	}

	public void setDsmivId(String dsmivId) {
		this.dsmivId = dsmivId;
	}

	public String getJuvenileNum() {
		return juvenileNum;
	}

	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
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
	/**
	 * @return Returns the diagnosis10.
	 */
	public String getDiagnosis10() {
		return diagnosis10;
	}
	/**
	 * @param diagnosis10 The diagnosis10 to set.
	 */
	public void setDiagnosis10(String diagnosis10) {
		this.diagnosis10 = diagnosis10;
	}
}
