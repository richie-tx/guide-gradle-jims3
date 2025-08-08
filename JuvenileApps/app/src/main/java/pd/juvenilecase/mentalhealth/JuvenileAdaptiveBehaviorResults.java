package pd.juvenilecase.mentalhealth;

import pd.codetable.Code;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Date;
import java.util.Iterator;

/**
 * 
 * @stereotype entity
 * @author dnikolis
 */
public class JuvenileAdaptiveBehaviorResults extends PersistentObject
{
	/**
	 * 
	 * @detailerDoNotGenerate false
	 */
	private String communicationScore;
	/**
	 * 
	 * @detailerDoNotGenerate false
	 */
	private String compositeScore;
	/**
	 * 
	 * @detailerDoNotGenerate false
	 */
	private String livingScore;
	/**
	 * 
	 * @detailerDoNotGenerate false
	 */
	private String socialScore;
	/**
	 * 
	 * @detailerDoNotGenerate false
	 */
	private Date testDate;
	/**
	 * 
	 * @contextKey AB_TEST_NAME
	 * @referencedType pd.codetable.Code
	 * @detailerDoNotGenerate false
	 */
	private Code testName = null;
	/**
	 * 
	 * @detailerDoNotGenerate false
	 */
	private String testSessId;
	private String testNameId;

	//	Just for display purpose
	private String programReferralNum;

	private String serviceProviderName;

	private String instructorName;

	private String serviceEventId;

	private String instrLastName;

	private String instrFirstName;

	private String instrMiddleName;
	
	/**
	 * @return 
	 * 
	 */
	public static JuvenileAdaptiveBehaviorResults find(String testID)
	{
		JuvenileAdaptiveBehaviorResults juvenileABResults = null;
		IHome home = new Home();
		juvenileABResults = (JuvenileAdaptiveBehaviorResults) home.find(testID,
				JuvenileAdaptiveBehaviorResults.class);
		return juvenileABResults;
	}

	/**
	 * 
	 */
	public static Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, JuvenileAdaptiveBehaviorResults.class);
	}
	
	 public static Iterator findAll(String attributeName, String attributeValue)
	 {
	     IHome home = new Home();
	     return home.findAll(attributeName, attributeValue, JuvenileAdaptiveBehaviorResults.class);
	     
	 }

	/**
	 * 
	 * @return the communicationScore
	 */
	public String getCommunicationScore()
	{
		fetch();
		return communicationScore;
	}

	/**
	 * 
	 * @return the compositeScore
	 */
	public String getCompositeScore()
	{
		fetch();
		return compositeScore;
	}

	/**
	 * 
	 * @return the livingScore
	 */
	public String getLivingScore()
	{
		fetch();
		return livingScore;
	}

	/**
	 * 
	 * @return the socialScore
	 */
	public String getSocialScore()
	{
		fetch();
		return socialScore;
	}

	/**
	 * 
	 * @return the testDate
	 */
	public Date getTestDate()
	{
		fetch();
		return testDate;
	}

	/**
	 * 
	 * @return the testSessId
	 */
	public String getTestSessId()
	{
		fetch();
		return testSessId;
	}

	/**
	 * 
	 * @param communicationscore the communicationScore to set
	 */
	public void setCommunicationScore(String communicationScore)
	{
		if (this.communicationScore == null || !this.communicationScore.equals(communicationScore))
		{
			markModified();
		}
		this.communicationScore = communicationScore;
	}

	/**
	 * 
	 * @param compositeScore the compositeScore to set
	 */
	public void setCompositeScore(String compositeScore)
	{
		if (this.compositeScore == null || !this.compositeScore.equals(compositeScore))
		{
			markModified();
		}
		this.compositeScore = compositeScore;
	}

	/**
	 * 
	 * @param livingScore the livingScore to set
	 */
	public void setLivingScore(String livingScore)
	{
		if (this.livingScore == null || !this.livingScore.equals(livingScore))
		{
			markModified();
		}
		this.livingScore = livingScore;
	}

	/**
	 * 
	 * @param socialScore the socialScore to set
	 */
	public void setSocialScore(String socialScore)
	{
		if (this.socialScore == null || !this.socialScore.equals(socialScore))
		{
			markModified();
		}
		this.socialScore = socialScore;
	}

	/**
	 * 
	 * @param testDate the testDate to set
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
	 * 
	 * @param testSessId the testSessId to set
	 */
	public void setTestSessId(String testSessId)
	{
		if (this.testSessId == null || !this.testSessId.equals(testSessId))
		{
			markModified();
		}
		this.testSessId = testSessId;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setTestNameId(String testNameId)
	{
		if (this.testNameId == null || !this.testNameId.equals(testNameId))
		{
			markModified();
		}
		testName = null;
		this.testNameId = testNameId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getTestNameId()
	{
		fetch();
		return testNameId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initTestName()
	{
		if (testName == null)
		{
			testName = (Code) new mojo.km.persistence.Reference(testNameId, Code.class,
					"AB_TEST_NAME").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getTestName()
	{
		initTestName();
		return testName;
	}

	/**
	 * set the type reference for class member testName
	 */
	public void setTestName(Code testName)
	{
		if (this.testName == null || !this.testName.equals(testName))
		{
			markModified();
		}
		setTestNameId("" + testName.getOID());
		testName.setContext("AB_TEST_NAME");
		this.testName = (Code) new mojo.km.persistence.Reference(testName).getObject();
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

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String getServiceEventId() {
		return serviceEventId;
	}

	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
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
}
