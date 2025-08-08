package pd.juvenilecase.mentalhealth;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Date;
import java.util.Iterator;

import pd.codetable.Code;

/**
 * 
 * @roseuid 45D4B4B400B9
 */
public class JuvenileIQResults extends PersistentObject {
	private Date testDate;

	private String fullScore;

	private String performanceScore;

	private String verbalScore;

	private String recommendation;

	private String juvenileTestingResultsId;

	private String testSessId;

	private String testId;
	
	private String serviceEventId;
	
	private String verbalComprehension;
	
	private String perceptualReasoning;
	
	private String nonVerbalIQ;
	 
	private String processingSpeed;
	 
	private String workingMemory;
	 
	private String pictorialIQ;
	 
	private String geometricIQ;
	
	private String testNameId;
	
	/**
	* @detailerDoNotGenerate true
	* @referencedType pd.codetable.Code
	* @contextKey IQ_TEST_NAME
	*/
	
	public Code testName;
	
//	Just for display purpose
	private String programReferralNum; 
	private String serviceProviderName; 
	private String instrLastName;
	private String instrFirstName;
	private String instrMiddleName;

	/**
	 * 
	 * @roseuid 45D4B4B400B9
	 */
	public JuvenileIQResults() {
	}

	/** 
	 * @roseuid 45AF7A0A0192
	 * @return Iterator of JuvenileIQResults
	* @param event
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileIQResults.class);
	}
	
	 public static Iterator findAll(String attributeName, String attributeValue)
	 {
	     IHome home = new Home();
	     return home.findAll(attributeName, attributeValue, JuvenileIQResults.class);
	     
	 }
	
	/**
	* @return JuvenileIQResults
	* @param event
	* @roseuid 45AF7A0A0190
	*/
	static public JuvenileIQResults find(String testID)
	{
		JuvenileIQResults juvenileIQResults = null;
		IHome home = new Home();
		juvenileIQResults = (JuvenileIQResults) home.find(testID, JuvenileIQResults.class);
		return juvenileIQResults;
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
						.Reference(testNameId, Code.class, "IQ_TEST_NAME")
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
		testName.setContext("IQ_TEST_NAME");
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
	 * @return Returns the fullScore.
	 */
	public String getFullScore() {
		fetch();
		return fullScore;
	}

	/**
	 * @param fullScore
	 *            The fullScore to set.
	 */
	public void setFullScore(String fullScore) {
		if (this.fullScore == null || !this.fullScore.equals(fullScore)) {
			markModified();
		}
		this.fullScore = fullScore;
	}

	/**
	 * @return Returns the juvenileTestingResultsId.
	 */
	public String getJuvenileTestingResultsId() {
		fetch();
		return juvenileTestingResultsId;
	}

	/**
	 * @param juvenileTestingResultsId
	 *            The juvenileTestingResultsId to set.
	 */
	public void setJuvenileTestingResultsId(String juvenileTestingResultsId) {
		if (this.juvenileTestingResultsId == null || !this.juvenileTestingResultsId.equals(juvenileTestingResultsId)) {
			markModified();
		}
		this.juvenileTestingResultsId = juvenileTestingResultsId;
	}

	/**
	 * @return Returns the performanceScore.
	 */
	public String getPerformanceScore() {
		fetch();
		return performanceScore;
	}

	/**
	 * @param performanceScore
	 *            The performanceScore to set.
	 */
	public void setPerformanceScore(String performanceScore) {
		if (this.performanceScore == null || !this.performanceScore.equals(performanceScore)) {
			markModified();
		}
		this.performanceScore = performanceScore;
	}

	/**
	 * @return Returns the recommendation.
	 */
	public String getRecommendation() {
		fetch();
		return recommendation;
	}

	/**
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
	 * @return Returns the testDate.
	 */
	public Date getTestDate() {
		fetch();
		return testDate;
	}

	/**
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
	 * @return Returns the verbalScore.
	 */
	public String getVerbalScore() {
		fetch();
		return verbalScore;
	}

	/**
	 * @param verbalScore
	 *            The verbalScore to set.
	 */
	public void setVerbalScore(String verbalScore) {
		if (this.verbalScore == null || !this.verbalScore.equals(verbalScore)) {
			markModified();
		}
		this.verbalScore = verbalScore;
	}

	
	/**
	 * @return Returns the testSessID.
	 * @roseuid 45AF7A0A0190
	 */
	public String getTestSessId() {
		return testSessId;
	}

	/**
	 * @param testSessID
	 * @roseuid 45AF7A0A0190
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
	 * @roseuid 45AF7A0A0190
	 */
	public String getTestId() {
		return testId;
	}

	/**
	 * @param testId
	 *            The testId to set.
	 * @roseuid 45AF7A0A0190
	 */
	public void setTestId(String testId) {
		if (this.testId == null || !this.testId.equals(testId)) {
			markModified();
		}
		this.testId = testId;
	}

	/**
	 * @return Returns the serviceEventId.
	 * @roseuid 45AF7A0A0190
	 */
	public String getServiceEventId() {
		fetch();
		return serviceEventId;
	}
	/**
	 * @param serviceEventId The serviceEventId to set.
	 * @roseuid 45AF7A0A0190
	 */
	public void setServiceEventId(String serviceEventId) {
		if (this.serviceEventId == null || !this.serviceEventId.equals(serviceEventId)) {
			markModified();
		}
		this.serviceEventId = serviceEventId;
	}
	
	/**
	 * @return Returns the geometricIQ.
	 * @roseuid 45AF7A0A0190
	 */
	public String getGeometricIQ() {
		fetch();
		return geometricIQ;
	}
	/**
	 * @param geometricIQ The geometricIQ to set.
	 * @roseuid 45AF7A0A0190
	 */
	public void setGeometricIQ(String geometricIQ) {
		if (this.geometricIQ == null || !this.geometricIQ.equals(geometricIQ)) {
			markModified();
		}
		this.geometricIQ = geometricIQ;
	}
	/**
	 * @return Returns the nonVerbalIQ.
	 * @roseuid 45AF7A0A0190
	 */
	public String getNonVerbalIQ() {
		fetch();
		return nonVerbalIQ;
	}
	/**
	 * @param nonVerbalIQ The nonVerbalIQ to set.
	 * @roseuid 45AF7A0A0190
	 */
	public void setNonVerbalIQ(String nonVerbalIQ) {
		if (this.nonVerbalIQ == null || !this.nonVerbalIQ.equals(nonVerbalIQ)) {
			markModified();
		}
		this.nonVerbalIQ = nonVerbalIQ;
	}
	/**
	 * @return Returns the perceptualReasoning.
	 * @roseuid 45AF7A0A0190
	 */
	public String getPerceptualReasoning() {
		fetch();
		return perceptualReasoning;
	}
	/**
	 * @param perceptualReasoning The perceptualReasoning to set.
	 * @roseuid 45AF7A0A0190
	 */
	public void setPerceptualReasoning(String perceptualReasoning) {
		if (this.perceptualReasoning == null || !this.perceptualReasoning.equals(perceptualReasoning)) {
			markModified();
		}
		this.perceptualReasoning = perceptualReasoning;
	}
	/**
	 * @return Returns the pictorialIQ.
	 * @roseuid 45AF7A0A0190
	 */
	public String getPictorialIQ() {
		fetch();
		return pictorialIQ;
	}
	/**
	 * @param pictorialIQ The pictorialIQ to set.
	 * @roseuid 45AF7A0A0190
	 */
	public void setPictorialIQ(String pictorialIQ) {
		if (this.pictorialIQ == null || !this.pictorialIQ.equals(pictorialIQ)) {
			markModified();
		}
		this.pictorialIQ = pictorialIQ;
	}
	/**
	 * @return Returns the processingSpeed.
	 * @roseuid 45AF7A0A0190
	 */
	public String getProcessingSpeed() {
		fetch();
		return processingSpeed;
	}
	/**
	 * @param processingSpeed The processingSpeed to set.
	 * @roseuid 45AF7A0A0190
	 */
	public void setProcessingSpeed(String processingSpeed) {
		if (this.processingSpeed == null || !this.processingSpeed.equals(processingSpeed)) {
			markModified();
		}
		this.processingSpeed = processingSpeed;
	}
	/**
	 * @return Returns the verbalComprehension.
	 * @roseuid 45AF7A0A0190
	 */
	public String getVerbalComprehension() {
		fetch();
		return verbalComprehension;
	}
	/**
	 * @param verbalComprehension The verbalComprehension to set.
	 * @roseuid 45AF7A0A0190
	 */
	public void setVerbalComprehension(String verbalComprehension) {
		if (this.verbalComprehension == null || !this.verbalComprehension.equals(verbalComprehension)) {
			markModified();
		}
		this.verbalComprehension = verbalComprehension;
	}
	/**
	 * @return Returns the workingMemory.
	 * @roseuid 45AF7A0A0190
	 */
	public String getWorkingMemory() {
		fetch();
		return workingMemory;
	}
	/**
	 * @param workingMemory The workingMemory to set.
	 * @roseuid 45AF7A0A0190
	 */
	public void setWorkingMemory(String workingMemory) {
		if (this.workingMemory == null || !this.workingMemory.equals(workingMemory)) {
			markModified();
		}
		this.workingMemory = workingMemory;
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
}
