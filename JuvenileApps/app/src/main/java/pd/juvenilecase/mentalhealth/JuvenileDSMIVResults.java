package pd.juvenilecase.mentalhealth;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Date;
import java.util.Iterator;

/**
 * @roseuid 45D4B49E02BC
 */
public class JuvenileDSMIVResults extends PersistentObject
{
	private Date testDate;
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
	private String testSessId;
	private String testId;
	private String serviceEventId;
	private String medicalDiagnosis;
	private Date createDate;
	//Just for display purpose
	private String programReferralNum; 
	private String serviceProviderName; 
	private String instrLastName;
	private String instrFirstName;
	private String instrMiddleName;
	
	//additional field for DSM V
	private String diagnosis10;
	
	
	/**
	 * @roseuid 45D4B49E02BC
	 */
	public JuvenileDSMIVResults()
	{
	}

	/** 
	 * @roseuid 45AF7A0A0192
	 * @return Iterator of JuvenileDSMIVResults
	* @param event
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileDSMIVResults.class);
	}
	
	 public static Iterator findAll(String attributeName, String attributeValue)
	 {
	     IHome home = new Home();
	     return home.findAll(attributeName, attributeValue, JuvenileDSMIVResults.class);
	     
	 }
	
	/**
	* @return JuvenileDSMIVResults
	* @param event
	* @roseuid 45AF7A0A0190
	*/
	static public JuvenileDSMIVResults find(String testID)
	{
		JuvenileDSMIVResults juvenileDSMIVResults = null;
		IHome home = new Home();
		juvenileDSMIVResults = (JuvenileDSMIVResults) home.find(testID, JuvenileDSMIVResults.class);
		return juvenileDSMIVResults;
	}	
	
	
	
	/**
	 * @return Returns the programReferralNum.
	 * @roseuid 45AF7A0A0190
	 */
	public String getProgramReferralNum() {
		fetch();
		return programReferralNum;
	}
	/**
	 * @param programReferralNum The programReferralNum to set.
	 * @roseuid 45AF7A0A0190
	 */
	public void setProgramReferralNum(String programReferralNum) {
		if (this.programReferralNum == null || !this.programReferralNum.equals(programReferralNum))
		{
			markModified();
		}
		this.programReferralNum = programReferralNum;
	}
	/**
	 * @return Returns the serviceProviderName.
	 * @roseuid 45AF7A0A0190
	 */
	public String getServiceProviderName() {
		fetch();
		return serviceProviderName;
	}
	/**
	 * @param serviceProviderName The serviceProviderName to set.
	 * @roseuid 45AF7A0A0190
	 */
	public void setServiceProviderName(String serviceProviderName) {
		if (this.serviceProviderName == null || !this.serviceProviderName.equals(serviceProviderName))
		{
			markModified();
		}
		this.serviceProviderName = serviceProviderName;
	}
	/**
	 * @return Returns the axis1FifthScore.
	 */
	public String getAxis1FifthScore()
	{
		fetch();
		return axis1FifthScore;
	}

	/**
	 * @param axis1FifthScore The axis1FifthScore to set.
	 */
	public void setAxis1FifthScore(String axis1FifthScore)
	{
		if (this.axis1FifthScore == null || !this.axis1FifthScore.equals(axis1FifthScore))
		{
			markModified();
		}
		this.axis1FifthScore = axis1FifthScore;
	}

	/**
	 * @return Returns the axis1FourthScore.
	 */
	public String getAxis1FourthScore()
	{
		fetch();
		return axis1FourthScore;
	}

	/**
	 * @param axis1FourthScore The axis1FourthScore to set.
	 */
	public void setAxis1FourthScore(String axis1FourthScore)
	{
		if (this.axis1FourthScore == null || !this.axis1FourthScore.equals(axis1FourthScore))
		{
			markModified();
		}
		this.axis1FourthScore = axis1FourthScore;
	}

	/**
	 * @return Returns the axis1PrimaryScore.
	 */
	public String getAxis1PrimaryScore()
	{
		fetch();
		return axis1PrimaryScore;
	}

	/**
	 * @param axis1PrimaryScore The axis1PrimaryScore to set.
	 */
	public void setAxis1PrimaryScore(String axis1PrimaryScore)
	{
		if (this.axis1PrimaryScore == null || !this.axis1PrimaryScore.equals(axis1PrimaryScore))
		{
			markModified();
		}
		this.axis1PrimaryScore = axis1PrimaryScore;
	}

	/**
	 * @return Returns the axis1SecondaryScore.
	 */
	public String getAxis1SecondaryScore()
	{
		fetch();
		return axis1SecondaryScore;
	}

	/**
	 * @param axis1SecondaryScore The axis1SecondaryScore to set.
	 */
	public void setAxis1SecondaryScore(String axis1SecondaryScore)
	{
		if (this.axis1SecondaryScore == null || !this.axis1SecondaryScore.equals(axis1SecondaryScore))
		{
			markModified();
		}
		this.axis1SecondaryScore = axis1SecondaryScore;
	}

	/**
	 * @return Returns the axis1TertiaryScore.
	 */
	public String getAxis1TertiaryScore()
	{
		fetch();
		return axis1TertiaryScore;
	}

	/**
	 * @param axis1TertiaryScore The axis1TertiaryScore to set.
	 */
	public void setAxis1TertiaryScore(String axis1TertiaryScore)
	{
		if (this.axis1TertiaryScore == null || !this.axis1TertiaryScore.equals(axis1TertiaryScore))
		{
			markModified();
		}
		this.axis1TertiaryScore = axis1TertiaryScore;
	}

	/**
	 * @return Returns the axis2PrimaryScore.
	 */
	public String getAxis2PrimaryScore()
	{
		fetch();
		return axis2PrimaryScore;
	}

	/**
	 * @param axis2PrimaryScore The axis2PrimaryScore to set.
	 */
	public void setAxis2PrimaryScore(String axis2PrimaryScore)
	{
		if (this.axis2PrimaryScore == null || !this.axis2PrimaryScore.equals(axis2PrimaryScore))
		{
			markModified();
		}
		this.axis2PrimaryScore = axis2PrimaryScore;
	}

	/**
	 * @return Returns the axis2SecondaryScore.
	 */
	public String getAxis2SecondaryScore()
	{
		fetch();
		return axis2SecondaryScore;
	}

	/**
	 * @param axis2SecondaryScore The axis2SecondaryScore to set.
	 */
	public void setAxis2SecondaryScore(String axis2SecondaryScore)
	{
		if (this.axis2SecondaryScore == null || !this.axis2SecondaryScore.equals(axis2SecondaryScore))
		{
			markModified();
		}
		this.axis2SecondaryScore = axis2SecondaryScore;
	}

	/**
	 * @return Returns the axis3PrimaryScore.
	 */
	public String getAxis3PrimaryScore()
	{
		fetch();
		return axis3PrimaryScore;
	}

	/**
	 * @param axis3PrimaryScore The axis3PrimaryScore to set.
	 */
	public void setAxis3PrimaryScore(String axis3PrimaryScore)
	{
		if (this.axis3PrimaryScore == null || !this.axis3PrimaryScore.equals(axis3PrimaryScore))
		{
			markModified();
		}
		this.axis3PrimaryScore = axis3PrimaryScore;
	}

	/**
	 * @return Returns the axis3SecondaryScore.
	 */
	public String getAxis3SecondaryScore()
	{
		fetch();
		return axis3SecondaryScore;
	}

	/**
	 * @param axis3SecondaryScore The axis3SecondaryScore to set.
	 */
	public void setAxis3SecondaryScore(String axis3SecondaryScore)
	{
		if (this.axis3SecondaryScore == null || !this.axis3SecondaryScore.equals(axis3SecondaryScore))
		{
			markModified();
		}
		this.axis3SecondaryScore = axis3SecondaryScore;
	}

	/**
	 * @return Returns the axis4Comments.
	 */
	public String getAxis4Comments()
	{
		fetch();
		return axis4Comments;
	}

	/**
	 * @param axis4Comments The axis4Comments to set.
	 */
	public void setAxis4Comments(String axis4Comments)
	{
		if (this.axis4Comments == null || !this.axis4Comments.equals(axis4Comments))
		{
			markModified();
		}
		this.axis4Comments = axis4Comments;
	}

	/**
	 * @return Returns the axis4EconomicProblems.
	 */
	public boolean isAxis4EconomicProblems()
	{
		fetch();
		return axis4EconomicProblems;
	}

	/**
	 * @param axis4EconomicProblems The axis4EconomicProblems to set.
	 */
	public void setAxis4EconomicProblems(boolean axis4EconomicProblems)
	{
		if (this.axis4EconomicProblems != axis4EconomicProblems)
		{
			markModified();
		}
		this.axis4EconomicProblems = axis4EconomicProblems;
	}

	/**
	 * @return Returns the axis4EducationalProblems.
	 */
	public boolean isAxis4EducationalProblems()
	{
		fetch();
		return axis4EducationalProblems;
	}

	/**
	 * @param axis4EducationalProblems The axis4EducationalProblems to set.
	 */
	public void setAxis4EducationalProblems(boolean axis4EducationalProblems)
	{
		if (this.axis4EducationalProblems != axis4EducationalProblems)
		{
			markModified();
		}
		this.axis4EducationalProblems = axis4EducationalProblems;
	}

	/**
	 * @return Returns the axis4HealthProblems.
	 */
	public boolean isAxis4HealthProblems()
	{
		fetch();
		return axis4HealthProblems;
	}

	/**
	 * @param axis4HealthProblems The axis4HealthProblems to set.
	 */
	public void setAxis4HealthProblems(boolean axis4HealthProblems)
	{
		if (this.axis4HealthProblems != axis4HealthProblems)
		{
			markModified();
		}
		this.axis4HealthProblems = axis4HealthProblems;
	}

	/**
	 * @return Returns the axis4HousingProblems.
	 */
	public boolean isAxis4HousingProblems()
	{
		fetch();
		return axis4HousingProblems;
	}

	/**
	 * @param axis4HousingProblems The axis4HousingProblems to set.
	 */
	public void setAxis4HousingProblems(boolean axis4HousingProblems)
	{
		if (this.axis4HousingProblems != axis4HousingProblems)
		{
			markModified();
		}
		this.axis4HousingProblems = axis4HousingProblems;
	}

	/**
	 * @return Returns the axis4LegalProblems.
	 */
	public boolean isAxis4LegalProblems()
	{
		fetch();
		return axis4LegalProblems;
	}

	/**
	 * @param axis4LegalProblems The axis4LegalProblems to set.
	 */
	public void setAxis4LegalProblems(boolean axis4LegalProblems)
	{
		if (this.axis4LegalProblems != axis4LegalProblems)
		{
			markModified();
		}
		this.axis4LegalProblems = axis4LegalProblems;
	}

	/**
	 * @return Returns the axis4OccupationalProblems.
	 */
	public boolean isAxis4OccupationalProblems()
	{
		fetch();
		return axis4OccupationalProblems;
	}

	/**
	 * @param axis4OccupationalProblems The axis4OccupationalProblems to set.
	 */
	public void setAxis4OccupationalProblems(boolean axis4OccupationalProblems)
	{
		if (this.axis4OccupationalProblems != axis4OccupationalProblems)
		{
			markModified();
		}
		this.axis4OccupationalProblems = axis4OccupationalProblems;
	}

	/**
	 * @return Returns the axis4PsychosocialProblems.
	 */
	public boolean isAxis4PsychosocialProblems()
	{
		fetch();
		return axis4PsychosocialProblems;
	}

	/**
	 * @param axis4PsychosocialProblems The axis4PsychosocialProblems to set.
	 */
	public void setAxis4PsychosocialProblems(boolean axis4PsychosocialProblems)
	{
		if (this.axis4PsychosocialProblems != axis4PsychosocialProblems)
		{
			markModified();
		}
		this.axis4PsychosocialProblems = axis4PsychosocialProblems;
	}

	/**
	 * @return Returns the axis4SocialEnvironmentProblems.
	 */
	public boolean isAxis4SocialEnvironmentProblems()
	{
		fetch();
		return axis4SocialEnvironmentProblems;
	}

	/**
	 * @param axis4SocialEnvironmentProblems The axis4SocialEnvironmentProblems to set.
	 */
	public void setAxis4SocialEnvironmentProblems(boolean axis4SocialEnvironmentProblems)
	{
		if (this.axis4SocialEnvironmentProblems != axis4SocialEnvironmentProblems)
		{
			markModified();
		}
		this.axis4SocialEnvironmentProblems = axis4SocialEnvironmentProblems;
	}

	/**
	 * @return Returns the axis4SupportGroupProblems.
	 */
	public boolean isAxis4SupportGroupProblems()
	{
		fetch();
		return axis4SupportGroupProblems;
	}

	/**
	 * @param axis4SupportGroupProblems The axis4SupportGroupProblems to set.
	 */
	public void setAxis4SupportGroupProblems(boolean axis4SupportGroupProblems)
	{
		if (this.axis4SupportGroupProblems != axis4SupportGroupProblems)
		{
			markModified();
		}
		this.axis4SupportGroupProblems = axis4SupportGroupProblems;
	}

	/**
	 * @return Returns the axis4MentalHealthNeeds.
	 */
	public boolean isAxis4MentalHealthNeeds()
	{
		fetch();
		return axis4MentalHealthNeeds;
	}

	/**
	 * @param axis4SupportGroupProblems The axis4SupportGroupProblems to set.
	 */
	public void setAxis4MentalHealthNeeds(boolean axis4MentalHealthNeeds)
	{
		if (this.axis4MentalHealthNeeds != axis4MentalHealthNeeds)
		{
			markModified();
		}
		this.axis4MentalHealthNeeds = axis4MentalHealthNeeds;
	}
	
	
	
	/**
	 * @return Returns the axis5Score.
	 */
	public String getAxis5Score()
	{
		fetch();
		return axis5Score;
	}

	/**
	 * @param axis5Score The axis5Score to set.
	 */
	public void setAxis5Score(String axis5Score)
	{
		if (this.axis5Score == null || !this.axis5Score.equals(axis5Score))
		{
			markModified();
		}
		this.axis5Score = axis5Score;
	}

	/**
	 * @return Returns the testDate.
	 */
	public Date getTestDate()
	{
		fetch();
		return testDate;
	}

	/**
	 * @param testDate The testDate to set.
	 */
	public void setTestDate(Date testDate)
	{
		if (this.testDate == null || !this.testDate.equals(testDate))
		{
			markModified();
		}
		this.testDate = testDate;
	}
	
	
	/**
	 * @return Returns the testSessID.
	 */
	public String getTestSessId() {
		fetch();
		return testSessId;
	}

	/**
	 * @param testSessID
	 *            The testSessID to set.
	 */
	public void setTestSessId(String testSessId) {
		if (this.testSessId == null || !this.testSessId.equals(testSessId)) {
			markModified();
		}
		this.testSessId = testSessId;
	}
	
	

	/**
	 * @return Returns the serviceEventId.
	 */
	public String getServiceEventId() {
		fetch();
		return serviceEventId;
	}
	/**
	 * @param serviceEventId The serviceEventId to set.
	 */
	public void setServiceEventId(String serviceEventId) {
		if (this.serviceEventId == null || !this.serviceEventId.equals(serviceEventId)) {
			markModified();
		}
		this.serviceEventId = serviceEventId;
	}
	
	
	/**
	 * @return Returns the testId.
	 */
	public String getTestId() {
		fetch();
		return testId;
	}

	/**
	 * @param testId
	 *            The testId to set.
	 */
	public void setTestId(String testId) {
		if (this.testId == null || !this.testId.equals(testId)) {
			markModified();
		}
		this.testId = testId;
	}

	
	/**
	 * @return the medicalDiagnosis
	 */
	public String getMedicalDiagnosis() {
		fetch();
		return medicalDiagnosis;
	}

	/**
	 * @param medicalDiagnosis the aMedicalDiagnosis to set
	 */
	public void setMedicalDiagnosis(String aMedicalDiagnosis) {
		if (this.medicalDiagnosis == null || !this.medicalDiagnosis.equals(aMedicalDiagnosis)) {
			markModified();
		}
		this.medicalDiagnosis = aMedicalDiagnosis;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		fetch();
		return createDate;
	}

	/**
	 * @param createDate the aCreateDate to set
	 */
	public void setCreateDate(Date aCreateDate) {
		if (this.createDate == null || !this.createDate.equals(aCreateDate)) {
			markModified();
		}
		this.createDate = aCreateDate;
	}

	/**
	 * @return Returns the instrFirstName.
	 */
	public String getInstrFirstName() {
		return (this.instrFirstName == null)?"":this.instrFirstName;
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
		return (this.instrLastName == null)?"":this.instrLastName;
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

	public boolean isAxis4MentalHealthTreatment() {
		return axis4MentalHealthTreatment;
	}

	public void setAxis4MentalHealthTreatment(boolean axis4MentalHealthTreatment) {
		this.axis4MentalHealthTreatment = axis4MentalHealthTreatment;
	}

	public String getDsmivId() {
		return dsmivId;
	}

	public void setDsmivId(String dsmivId) {
		this.dsmivId = dsmivId;
	}
	
	public String getDiagnosis10() {
		fetch();
		return diagnosis10;
	}

	public void setDiagnosis10(String diagnosis10) {
		
		if (this.diagnosis10 == null || !this.diagnosis10.equals(diagnosis10))
		{
			markModified();
		}
		
		this.diagnosis10 = diagnosis10;
	}
}
