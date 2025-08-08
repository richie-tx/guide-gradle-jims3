package pd.juvenilecase.mentalhealth;

import java.util.Date;
import java.util.Iterator;

import pd.codetable.Code;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * 
 * @roseuid 45D4B4A70164
 */
public class JuvenileAchievementResults extends PersistentObject {
	private Date testDate;

	private String arithmeticGradeLevel;

	private String arithmeticScore;

	private String readingGradeLevel;

	private String readingScore;

	private String spellingGradeLevel;

	private String spellingScore;

	private String recommendation;

	private JuvenileAchievementResults[] juvenileAchievementResults;

	private String juvenileTestingResultsId;

	private String testSessId;

	private String testId;

	private String serviceEventId;

	private String sentenceCompletionLevel;

	private String sentenceCompletionScore;

	private String readingCompositeLevel;

	private String readingCompositeScore;
	
	private String testNameId;
	
	/**
	* @detailerDoNotGenerate true
	* @referencedType pd.codetable.Code
	* @contextKey AT_TEST_NAME
	*/
	
	public Code testName;

	//	Just for display purpose
	//	Just for display purpose
	private String programReferralNum;

	private String serviceProviderName;

	private String instrLastName;

	private String instrFirstName;

	private String instrMiddleName;
	
	

	/**
	 * 
	 * @roseuid 45D4B4A70164
	 */
	public JuvenileAchievementResults() {
	}

	/**
	 * @roseuid 45AF7A0A0192
	 * @return Iterator of JuvenileAchievementResults
	 * @param event
	 */
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, JuvenileAchievementResults.class);
	}
	
	 public static Iterator findAll(String attributeName, String attributeValue)
	 {
	     IHome home = new Home();
	     return home.findAll(attributeName, attributeValue, JuvenileAchievementResults.class);
	     
	 }

	/**
	 * @return JuvenileAchievementResults
	 * @param event
	 * @roseuid 45AF7A0A0190
	 */
	static public JuvenileAchievementResults find(String testID) {
		JuvenileAchievementResults juvenileAchievementResults = null;
		IHome home = new Home();
		juvenileAchievementResults = (JuvenileAchievementResults) home.find(testID, JuvenileAchievementResults.class);
		return juvenileAchievementResults;
	}

	
//	begin
	
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @roseuid 42A882800272
	* @return java.lang.String
	*/
	public String getTestNameId() {
		fetch();
		return testNameId;
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @roseuid 42A882800262
	* @param testNameId
	*/
	public void setTestNameId(String testNameId) {
		if (this.testNameId == null || !this.testNameId.equals(testNameId))
		{
			markModified();
		}
		testName = null;
		this.testNameId = testNameId;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initTestName()
	{
		if (testName == null)
		{
			try
			{
				testName =
					(Code) new mojo
						.km
						.persistence
						.Reference(testNameId, Code.class, "AT_TEST_NAME")
						.getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getTestName()
	{
		fetch();
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
		if (testName.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(testName);
		}
		setTestNameId("" + testName.getOID());
		testName.setContext("AT_TEST_NAME");
		this.testName = (Code) new mojo.km.persistence.Reference(testName).getObject();
	}
	
	//end
	
	
	/**
	 * @return Returns the programReferralNum.
	 * @roseuid 45AF7A0A0190
	 */
	public String getProgramReferralNum() {
		fetch();
		return programReferralNum;
	}

	/**
	 * @param programReferralNum
	 *            The programReferralNum to set.
	 * @roseuid 45AF7A0A0190
	 */
	public void setProgramReferralNum(String programReferralNum) {
		if (this.programReferralNum == null || !this.programReferralNum.equals(programReferralNum)) {
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
	 * @param serviceProviderName
	 *            The serviceProviderName to set.
	 * @roseuid 45AF7A0A0190
	 */
	public void setServiceProviderName(String serviceProviderName) {
		if (this.serviceProviderName == null || !this.serviceProviderName.equals(serviceProviderName)) {
			markModified();
		}
		this.serviceProviderName = serviceProviderName;
	}

	/**
	 * Set the reference value to class ::
	 * pd.juvenilecase.mentalhealth.JuvenileTestingResults
	 */
	public void setJuvenileTestingResultsId(String juvenileTestingResultsId) {
		if (this.juvenileTestingResultsId == null || !this.juvenileTestingResultsId.equals(juvenileTestingResultsId)) {
			markModified();
		}
		this.juvenileTestingResultsId = juvenileTestingResultsId;
	}

	/**
	 * Get the reference value to class ::
	 * pd.juvenilecase.mentalhealth.JuvenileTestingResults
	 */
	public String getJuvenileTestingResultsId() {
		fetch();
		return juvenileTestingResultsId;
	}

	/**
	 * 
	 * @return Returns the arithmeticGradeLevel.
	 */
	public String getArithmeticGradeLevel() {
		fetch();
		return arithmeticGradeLevel;
	}

	/**
	 * 
	 * @param arithmeticGradeLevel
	 *            The arithmeticGradeLevel to set.
	 */
	public void setArithmeticGradeLevel(String arithmeticGradeLevel) {
		if (this.arithmeticGradeLevel == null || !this.arithmeticGradeLevel.equals(arithmeticGradeLevel)) {
			markModified();
		}
		this.arithmeticGradeLevel = arithmeticGradeLevel;
	}

	/**
	 * 
	 * @return Returns the arithmeticScore.
	 */
	public String getArithmeticScore() {
		fetch();
		return arithmeticScore;
	}

	/**
	 * 
	 * @param arithmeticScore
	 *            The arithmeticScore to set.
	 */
	public void setArithmeticScore(String arithmeticScore) {
		if (this.arithmeticScore == null || !this.arithmeticScore.equals(arithmeticScore)) {
			markModified();
		}
		this.arithmeticScore = arithmeticScore;
	}

	/**
	 * 
	 * @return Returns the juvenileAchievementResults.
	 */
	public JuvenileAchievementResults[] getJuvenileAchievementResults() {
		fetch();
		return juvenileAchievementResults;
	}

	/**
	 * 
	 * @param juvenileAchievementResults
	 *            The juvenileAchievementResults to set.
	 */
	public void setJuvenileAchievementResults(
			JuvenileAchievementResults[] juvenileAchievementResults) {
		if (this.juvenileAchievementResults == null
				|| !this.juvenileAchievementResults.equals(juvenileAchievementResults)) {
			markModified();
		}
		this.juvenileAchievementResults = juvenileAchievementResults;
	}

	/**
	 * 
	 * @return Returns the readingGradeLevel.
	 */
	public String getReadingGradeLevel() {
		fetch();
		return readingGradeLevel;
	}

	/**
	 * 
	 * @param readingGradeLevel
	 *            The readingGradeLevel to set.
	 */
	public void setReadingGradeLevel(String readingGradeLevel) {
		if (this.readingGradeLevel == null || !this.readingGradeLevel.equals(readingGradeLevel)) {
			markModified();
		}
		this.readingGradeLevel = readingGradeLevel;
	}

	/**
	 * 
	 * @return Returns the readingScore.
	 */
	public String getReadingScore() {
		fetch();
		return readingScore;
	}

	/**
	 * 
	 * @param readingScore
	 *            The readingScore to set.
	 */
	public void setReadingScore(String readingScore) {
		if (this.readingScore == null || !this.readingScore.equals(readingScore)) {
			markModified();
		}
		this.readingScore = readingScore;
	}

	/**
	 * 
	 * @return Returns the recommendation.
	 */
	public String getRecommendation() {
		fetch();
		return recommendation;
	}

	/**
	 * 
	 * @param recommendation
	 *            The recommendation to set.
	 */
	public void setRecommendation(String recommendation) {
		if (this.recommendation == null || !this.recommendation.equals(recommendation)) {
			markModified();
		}
		this.recommendation = recommendation;
	}

	/**
	 * 
	 * @return Returns the spellingGradeLevel.
	 */
	public String getSpellingGradeLevel() {
		fetch();
		return spellingGradeLevel;
	}

	/**
	 * 
	 * @param spellingGradeLevel
	 *            The spellingGradeLevel to set.
	 */
	public void setSpellingGradeLevel(String spellingGradeLevel) {
		if (this.spellingGradeLevel == null || !this.spellingGradeLevel.equals(spellingGradeLevel)) {
			markModified();
		}
		this.spellingGradeLevel = spellingGradeLevel;
	}

	/**
	 * 
	 * @return Returns the spellingScore.
	 */
	public String getSpellingScore() {
		fetch();
		return spellingScore;
	}

	/**
	 * 
	 * @param spellingScore
	 *            The spellingScore to set.
	 */
	public void setSpellingScore(String spellingScore) {
		if (this.spellingScore == null || !this.spellingScore.equals(spellingScore)) {
			markModified();
		}
		this.spellingScore = spellingScore;
	}

	/**
	 * 
	 * @return Returns the testDate.
	 */
	public Date getTestDate() {
		fetch();
		return testDate;
	}

	/**
	 * 
	 * @param testDate
	 *            The testDate to set.
	 */
	public void setTestDate(Date testDate) {
		if (this.testDate == null || !this.testDate.equals(testDate)) {
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
	 * @return Returns the readingCompositeLevel.
	 * @roseuid 45D4AF5103A3
	 */
	public String getReadingCompositeLevel() {
		fetch();
		return readingCompositeLevel;
	}

	/**
	 * @param readingCompositeLevel
	 *            The readingCompositeLevel to set.
	 * @roseuid 45D4AF5103A3
	 */
	public void setReadingCompositeLevel(String readingCompositeLevel) {
		if (this.readingCompositeLevel == null || !this.readingCompositeLevel.equals(readingCompositeLevel)) {
			markModified();
		}
		this.readingCompositeLevel = readingCompositeLevel;
	}

	/**
	 * @return Returns the readingCompositeScore.
	 * @roseuid 45D4AF5103A3
	 */
	public String getReadingCompositeScore() {
		fetch();
		return readingCompositeScore;
	}

	/**
	 * @param readingCompositeScore
	 *            The readingCompositeScore to set.
	 * @roseuid 45D4AF5103A3
	 */
	public void setReadingCompositeScore(String readingCompositeScore) {
		if (this.readingCompositeScore == null || !this.readingCompositeScore.equals(readingCompositeScore)) {
			markModified();
		}
		this.readingCompositeScore = readingCompositeScore;
	}

	/**
	 * @return Returns the sentenceCompletionLevel.
	 * @roseuid 45D4AF5103A3
	 */
	public String getSentenceCompletionLevel() {
		fetch();
		return sentenceCompletionLevel;
	}

	/**
	 * @param sentenceCompletionLevel
	 *            The sentenceCompletionLevel to set.
	 * @roseuid 45D4AF5103A3
	 */
	public void setSentenceCompletionLevel(String sentenceCompletionLevel) {
		if (this.sentenceCompletionLevel == null || !this.sentenceCompletionLevel.equals(sentenceCompletionLevel)) {
			markModified();
		}
		this.sentenceCompletionLevel = sentenceCompletionLevel;
	}

	/**
	 * @return Returns the sentenceCompletionScore.
	 * @roseuid 45D4AF5103A3
	 */
	public String getSentenceCompletionScore() {
		fetch();
		return sentenceCompletionScore;
	}

	/**
	 * @param sentenceCompletionScore
	 *            The sentenceCompletionScore to set.
	 * @roseuid 45D4AF5103A3
	 */
	public void setSentenceCompletionScore(String sentenceCompletionScore) {
		if (this.sentenceCompletionScore == null || !this.sentenceCompletionScore.equals(sentenceCompletionScore)) {
			markModified();
		}
		this.sentenceCompletionScore = sentenceCompletionScore;
	}

	/**
	 * @return Returns the serviceEventId.
	 * @roseuid 45D4AF5103A3
	 */
	public String getServiceEventId() {
		fetch();
		return serviceEventId;
	}

	/**
	 * @param serviceEventId
	 *            The serviceEventId to set.
	 * @roseuid 45D4AF5103A3
	 */
	public void setServiceEventId(String serviceEventId) {
		if (this.serviceEventId == null || !this.serviceEventId.equals(serviceEventId)) {
			markModified();
		}
		this.serviceEventId = serviceEventId;
	}

	/**
	 * @return Returns the instrFirstName.
	 */
	public String getInstrFirstName() {
		return instrFirstName;
	}

	/**
	 * @param instrFirstName
	 *            The instrFirstName to set.
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
	 * @param instrLastName
	 *            The instrLastName to set.
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
	 * @param instrMiddleName
	 *            The instrMiddleName to set.
	 */
	public void setInstrMiddleName(String instrMiddleName) {
		this.instrMiddleName = instrMiddleName;
	}
}
