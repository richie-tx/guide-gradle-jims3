package pd.juvenilecase.mentalhealth;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

/**
 * 
 * @roseuid 45D4B4AD0126
 */
public class JuvenileAdaptiveFunctioningResults extends PersistentObject {
	private Date testDate;

	private String standardScore;

	private String juvenileTestingResultsId;

	private String testSessId;

	private String testId;
	
	private String testNameId;
	
	/**
	* @detailerDoNotGenerate true
	* @referencedType pd.codetable.Code
	* @contextKey AF_TEST_NAME
	*/
	
	public Code testName;

	//	Just for display purpose
	private String programReferralNum;

	private String serviceProviderName;

	private String instructorName;

	private String serviceEventId;

	private String instrLastName;

	private String instrFirstName;

	private String instrMiddleName;
	
	

	/**
	 * 
	 * @roseuid 45D4B4AD0126
	 */
	public JuvenileAdaptiveFunctioningResults() {
	}

	/**
	 * @roseuid 45AF7A0A0192
	 * @return Iterator of JuvenileAdaptiveFunctioningResults
	 * @param event
	 */
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, JuvenileAdaptiveFunctioningResults.class);
	}
	
	 public static Iterator findAll(String attributeName, String attributeValue)
	 {
	     IHome home = new Home();
	     return home.findAll(attributeName, attributeValue, JuvenileAdaptiveFunctioningResults.class);
	     
	 }

	/**
	 * @return JuvenileAdaptiveFunctioningResults
	 * @param event
	 * @roseuid 45AF7A0A0190
	 */
	static public JuvenileAdaptiveFunctioningResults find(String testID) {
		JuvenileAdaptiveFunctioningResults juvenileAFResults = null;
		IHome home = new Home();
		juvenileAFResults = (JuvenileAdaptiveFunctioningResults) home.find(testID,
				JuvenileAdaptiveFunctioningResults.class);
		return juvenileAFResults;
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
	 * @return Returns the instructorName.
	 */
	public String getInstructorName() {
		fetch();
		return instructorName;
	}

	/**
	 * @param instructorName
	 *            The instructorName to set.
	 */
	public void setInstructorName(String instructorName) {
		if (this.instructorName == null || !this.instructorName.equals(instructorName)) {
			markModified();
		}
		this.instructorName = instructorName;
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
	 * @return Returns the standardScore.
	 */
	public String getStandardScore() {
		fetch();
		return standardScore;
	}

	/**
	 * @param standardScore
	 *            The standardScore to set.
	 */
	public void setStandardScore(String standardScore) {
		if (this.standardScore == null || !this.standardScore.equals(standardScore)) {
			markModified();
		}
		this.standardScore = standardScore;
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

	
	//begin
	
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
						.Reference(testNameId, Code.class, "AF_TEST_NAME")
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
		testName.setContext("AF_TEST_NAME");
		this.testName = (Code) new mojo.km.persistence.Reference(testName).getObject();
	}
	
	//end
	
	
	

	/**
	 * @return Returns the testSessID.
	 */
	public String getTestSessId() {
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
