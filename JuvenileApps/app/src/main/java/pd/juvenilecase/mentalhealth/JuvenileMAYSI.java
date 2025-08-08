package pd.juvenilecase.mentalhealth;

import java.util.Date;
import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.juvenile.Juvenile;
import pd.supervision.administerserviceprovider.administerlocation.Location;

/**
* @roseuid 427B8795035B
*/
public class JuvenileMAYSI extends PersistentObject {
	/**
	* @detailerDoNotGenerate false
	Properties for facilityType
	* @referencedType pd.codetable.Code
	* @contextKey PLACEMENT_TYPE
	*/
	private Code facilityType = null;
	/**
	* @detailerDoNotGenerate false
	Properties for facilityName
	* @referencedType pd.supervision.administerserviceprovider.administerlocation.Location
	*/
	private Location facilityName = null;
	/**
	* Properties for juvenileSubMAYSI
	*/
	private JuvenileSubMAYSI juvenileSubMAYSI = null;
	private String juvenileNumber;
	private short facilityTypeId;
	private String juvenileId;
	private short lengthOfStayId;
	private String previousMAYSI;
	private String referralNum;
	/**
	* Properties for juvenile
	*/
	private Juvenile juvenile = null;
	
	
	
	private int depressionAnxiety;
	private int suicideIdeation;
	private int angryIrritable;
	private int alcoholDrug;
	private int somaticComplaint;
	private Date screenDate;
	private int traumaticExpression;
	private int testAge;
	private int thoughtDisturbance;
	
	private String detailRaceId;
	private String detailSexId;
	private String reasonNotDoneId;
	
	/**
	* @detailerDoNotGenerate false
	Properties for lengthOfStay
	* @referencedType pd.codetable.Code
	* @contextKey LENGTH_OF_STAY
	*/
	private Code lengthOfStay = null;
	private String facilityNameId;
	
	
	// Added for ER 25992
	private boolean administer;
	private String reasonNotDone;
	private boolean subsequentAssessmentComplete; 
	
	// Added for production support User Story 11165
	private boolean juvenileAge;
	private int gender;
	private boolean assessmentFound;
	private String ethnicBackground;
	private String facilityNameString;
	

	/**
	* @roseuid 4236ED9502A8
	* @param aMAYSINumber
	* @return pd.juvenilecase.JuvenileMAYSI
	*/
	static public JuvenileMAYSI find(String aMAYSINumber) {
		return (JuvenileMAYSI) new Home().find(aMAYSINumber, JuvenileMAYSI.class);
	}
	/**
	* @return java.util.Iterator
	* @param attrName
	* @param attrValue
	* @roseuid 4236ED950316
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		return (Iterator) home.findAll(attrName, attrValue, JuvenileMAYSI.class);
	}
	/**
	* @return 
	*/
	public int getAlcoholDrug() {
		fetch();
		return alcoholDrug;
	}
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param raceId The race id.
	*/
	public void setDetailRaceId(String raceId) {
		if (this.detailRaceId == null || !this.detailRaceId.equals(raceId)) {
			markModified();
		}
		this.detailRaceId = raceId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return The race id.
	*/
	public String getDetailRaceId() {
		fetch();
		return detailRaceId;
	}

	/**
	*/
	public void setReasonNotDoneId(String raceId) {
		if (this.reasonNotDoneId == null || !this.reasonNotDoneId.equals(raceId)) {
			markModified();
		}
		this.reasonNotDoneId = raceId;
	}
	/**
	*/
	public String getReasonNotDoneId() {
		fetch();
		return reasonNotDoneId;
	}
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param sexId The sex id.
	*/
	public void setDetailSexId(String sexId) {
		if (this.detailSexId == null || !this.detailSexId.equals(sexId)) {
			markModified();
		}
		this.detailSexId = sexId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return The sex id.
	*/
	public String getDetailSexId() {
		fetch();
		return detailSexId;
	}
	
	
	/**
	* @return 
	*/
	public int getAngryIrritable() {
		fetch();
		return angryIrritable;
	}
	/**
	* @return 
	*/
	public int getDepressionAnxiety() {
		fetch();
		return depressionAnxiety;
	}
	
	/**
	* @return 
	*/
	public Date getScreenDate() {
		fetch();
		return screenDate;
	}
	/**
	* @return 
	*/
	public int getSomaticComplaint() {
		fetch();
		return somaticComplaint;
	}
	/**
	* @return 
	*/
	public int getSuicideIdeation() {
		fetch();
		return suicideIdeation;
	}
	/**
	* @return 
	*/
	public int getTestAge() {
		fetch();
		return testAge;
	}
	/**
	* @return 
	*/
	public int getThoughtDisturbance() {
		fetch();
		return thoughtDisturbance;
	}
	/**
	* @return 
	*/
	public int getTraumaticExpression() {
		fetch();
		return traumaticExpression;
	}
	/**
	* @param i
	*/
	public void setAlcoholDrug(int i) {
		if (this.alcoholDrug != i) {
			markModified();
		}
		alcoholDrug = i;
	}
	/**
	* @param i
	*/
	public void setAngryIrritable(int i) {
		if (this.angryIrritable != i) {
			markModified();
		}
		angryIrritable = i;
	}
	/**
	* @param i
	*/
	public void setDepressionAnxiety(int i) {
		if (this.depressionAnxiety != i) {
			markModified();
		}
		depressionAnxiety = i;
	}
	
	
	/**
	* @param date
	*/
	public void setScreenDate(Date date) {
		if (this.screenDate == null || !this.screenDate.equals(date)) {
			markModified();
		}
		screenDate = date;
	}
	/**
	* @param i
	*/
	public void setSomaticComplaint(int i) {
		if (this.somaticComplaint != i) {
			markModified();
		}
		somaticComplaint = i;
	}
	/**
	* @param i
	*/
	public void setSuicideIdeation(int i) {
		if (this.suicideIdeation != i) {
			markModified();
		}
		suicideIdeation = i;
	}
	/**
	* @param i
	*/
	public void setTestAge(int i) {
		if (this.testAge != i) {
			markModified();
		}
		testAge = i;
	}
	/**
	* @param i
	*/
	public void setThoughtDisturbance(int i) {
		if (this.thoughtDisturbance != i) {
			markModified();
		}
		thoughtDisturbance = i;
	}
	/**
	* @param i
	*/
	public void setTraumaticExpression(int i) {
		if (this.traumaticExpression != i) {
			markModified();
		}
		traumaticExpression = i;
	}
	
	
	
	
	
	/**
	* @return 
	*/
	public String getJuvenileNumber() {
		fetch();
		return juvenileNumber;
	}
	/**
	* @return 
	*/
	public String getPreviousMAYSI() {
		fetch();
		return previousMAYSI;
	}
	/**
	* @return 
	*/
	public String getReferralNum() {
		fetch();
		return referralNum;
	}
	/**
	* @param string
	*/
	public void setJuvenileNumber(String string) {
		if (this.juvenileNumber == null || !this.juvenileNumber.equals(string)) {
			markModified();
		}
		juvenileNumber = string;
	}
	/**
	* @param string
	*/
	public void setPreviousMAYSI(String string) {
		if (this.previousMAYSI == null || !this.previousMAYSI.equals(string)) {
			markModified();
		}
		previousMAYSI = string;
	}
	/**
	* @param string
	*/
	public void setReferralNum(String string) {
		if (this.referralNum == null || !this.referralNum.equals(string)) {
			markModified();
		}
		referralNum = string;
	}
	/**
	* Set the reference value to class :: pd.juvenilecase.Juvenile
	*/
	public void setJuvenileId(String aJuvenileId) {
		if (this.juvenileId == null || !this.juvenileId.equals(aJuvenileId)) {
			markModified();
		}
		juvenile = null;
		this.juvenileId = aJuvenileId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.Juvenile
	*/
	public String getJuvenileId() {
		fetch();
		return juvenileId;
	}
	/**
	* Initialize class relationship to class pd.juvenilecase.Juvenile
	*/
	private void initJuvenile() {
		if (juvenile == null) {
			try {
//				juvenile = (pd.juvenile.Juvenile) new mojo.km.persistence.Reference(juvenileId, pd.juvenile.Juvenile.class).getObject();
				juvenile = Juvenile.find(juvenileId);
			} catch (Throwable t) {
				juvenile = null;
			}
		}
	}
	/**
	* Gets referenced type pd.juvenilecase.Juvenile
	*/
	public Juvenile getJuvenile() {
		fetch();
		initJuvenile();
		return juvenile;
	}
	/**
	* set the type reference for class member juvenile
	*/
	public void setJuvenile(Juvenile aJuvenile) {
		if (this.juvenile == null || !this.juvenile.equals(aJuvenile)) {
			markModified();
		}
		if (aJuvenile.getOID() == null) {
			new mojo.km.persistence.Home().bind(aJuvenile);
		}
		setJuvenileId("" + aJuvenile.getOID());
		this.juvenile = (Juvenile) new mojo.km.persistence.Reference(aJuvenile).getObject();
	}
	/**
	* @return 
	*/
	public String getSequenceNumber() {
		fetch();
		return "" + getOID();
	}
	/**
	* Initialize class relationship to class pd.juvenilecase.JuvenileSubMAYSI
	*/
	private void initJuvenileSubMAYSI() {
		/* 
		 * JuvenileMAYSI has one to one relationship with JuvenileSubMAYSI
		 * however JuvenileMAYSI has not have key of JuvenileSubMAYSI
		 * 
		 * Here the code will find  JuvenileSubMAYSI based on sequenceNumber
		 */
		if (juvenileSubMAYSI == null) {
			try {
				Iterator juvenileSubMAYSIIterator = JuvenileSubMAYSI.findAll("juvenileMAYSIDetailId",(String)this.getOID());
				if(juvenileSubMAYSIIterator.hasNext())
				{
					//should get only one instance of JuvenileSubMAYSI
					juvenileSubMAYSI = (JuvenileSubMAYSI)juvenileSubMAYSIIterator.next();
				}
				//juvenileSubMAYSI = (pd.juvenilecase.JuvenileSubMAYSI) new mojo.km.persistence.Reference(juvenileSubMAYSIId, pd.juvenilecase.JuvenileSubMAYSI.class).getObject();
			} catch (Throwable t) {
				juvenileSubMAYSI = null;
			}
		}

	}

	
	/**
	* Finds JuvenileMAYSI by a certain event
	* @return Iterator of JuvenileMAYSI
	* @param event
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, JuvenileMAYSI.class);
	}
	
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setFacilityNameId(String aFacilityNameId) {
		if (this.facilityNameId == null || !this.facilityNameId.equals(aFacilityNameId)) {
			markModified();
		}
		facilityName = null;
		this.facilityNameId = aFacilityNameId;
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setFacilityTypeId(short aFacilityTypeId) {
		if (this.facilityTypeId != aFacilityTypeId) {
			markModified();
		}
		facilityType = null;
		this.facilityTypeId = aFacilityTypeId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getFacilityNameId() {
		fetch();
		return facilityNameId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public short getFacilityTypeId() {
		fetch();
		return facilityTypeId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initFacilityName() {
		if (facilityName == null) {
			try{
			facilityName =
				(Location) new mojo
					.km
					.persistence
					.Reference(facilityNameId, Location.class)
					.getObject();
			}
			catch(Throwable t){
				facilityName = null;
			}
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initFacilityType() {
		if (facilityType == null) {
			try {
				facilityType = (Code) new mojo.km.persistence.Reference(facilityTypeId+"", Code.class, "PLACEMENT_TYPE").getObject();
			} catch (Throwable t) {
				facilityType = null;
			}
		}
	}
	/**
	* Gets referenced type pd.supervision.administerserviceprovider.administerlocation.Location
	*/
	public Location getFacilityName() {
		fetch();
		initFacilityName();
		return facilityName;
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getFacilityType() {
		fetch();
		initFacilityType();
		return facilityType;
	}
	/**
	* set the type reference for class member facilityName
	*/
	public void setFacilityName(Code aFacilityName) {
		if (this.facilityName == null || !this.facilityName.equals(aFacilityName)) {
			markModified();
		}
		if (aFacilityName.getOID() == null) {
			new mojo.km.persistence.Home().bind(aFacilityName);
		}
		setFacilityNameId("" + aFacilityName.getOID());
		this.facilityName = (Location) new mojo.km.persistence.Reference(aFacilityName).getObject();
	}
	/**
	* set the type reference for class member facilityType
	*/
	public void setFacilityType(Code aFacilityType) {
		if (this.facilityType == null || !this.facilityName.equals(aFacilityType)) {
			markModified();
		}
		if (aFacilityType.getOID() == null) {
			new mojo.km.persistence.Home().bind(aFacilityType);
		}
		setFacilityTypeId((short)Integer.parseInt(aFacilityType.getOID()+""));
		this.facilityType = (Code) new mojo.km.persistence.Reference(aFacilityType).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setLengthOfStayId(short aLengthOfStayId) {
		if (this.lengthOfStayId !=aLengthOfStayId) {
			markModified();
		}
		lengthOfStay = null;
		this.lengthOfStayId = aLengthOfStayId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public short getLengthOfStayId() {
		fetch();
		return lengthOfStayId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initLengthOfStay() {
		if (lengthOfStay == null) {
			try {
				lengthOfStay = (Code) new mojo.km.persistence.Reference(lengthOfStayId+"", Code.class, "LENGTH_OF_STAY").getObject();
			} catch (Throwable t) {
				lengthOfStay = null;
			}
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getLengthOfStay() {
		fetch();
		initLengthOfStay();
		return lengthOfStay;
	}
	/**
	* set the type reference for class member lengthOfStay
	*/
	public void setLengthOfStay(Code aLengthOfStay) {
		if (this.lengthOfStay == null || !this.lengthOfStay.equals(aLengthOfStay)) {
			markModified();
		}
		if (aLengthOfStay.getOID() == null) {
			new mojo.km.persistence.Home().bind(aLengthOfStay);
		}
		setLengthOfStayId((short)Integer.parseInt(aLengthOfStay.getOID()+""));
		this.lengthOfStay = (Code) new mojo.km.persistence.Reference(aLengthOfStay).getObject();
	}
	/**
	 * @return
	 */
	public boolean getAdminister()
	{
		fetch();
		return administer;
	}

	/**
	 * @return
	 */
	public String getReasonNotDone()
	{
		fetch();
		return reasonNotDone;
	}

	/**
	 * @return
	 */
	public boolean getSubsequentAssessmentComplete()
	{
		fetch();
		return subsequentAssessmentComplete;
	}

	/**
	 * @param b
	 */
	public void setAdminister(boolean b)
	{
		if ( this.administer != b ) {
			markModified();
		}
		administer = b;
	}

	/**
	 * @param string
	 */
	public void setReasonNotDone(String string)
	{
		if ( this.reasonNotDone == null || ! this.reasonNotDone.equals(string) ) {
			markModified();
		}
		reasonNotDone = string;
	}

	/**
	 * @param b
	 */
	public void setSubsequentAssessmentComplete(boolean b)
	{
		if ( this.subsequentAssessmentComplete != b ) {
			markModified();
		}
		subsequentAssessmentComplete = b;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getJuvenileAge() {
		fetch();
		return juvenileAge;
	}
	
	/**
	 * 
	 * @param juvenileAge
	 */
	public void setJuvenileAge(boolean juvenileAge) {
		if ( this.juvenileAge != juvenileAge ) {
			markModified();
		}
		this.juvenileAge = juvenileAge;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getGender() {
		fetch();
		return gender;
	}
	
	/**
	 * 
	 * @param gender
	 */
	public void setGender(int gender) {
		if ( this.gender != gender ) {
			markModified();
		}
		this.gender = gender;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getAssessmentFound() {
		fetch();
		return assessmentFound;
	}
	
	/**
	 * 
	 * @param assessmentFound
	 */
	public void setAssessmentFound(boolean assessmentFound) {
		if ( this.assessmentFound != assessmentFound ) {
			markModified();
		}
		this.assessmentFound = assessmentFound;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getEthnicBackground() {
		fetch();
		return ethnicBackground;
	}
	
	/**
	 * 
	 * @param ethnicBackground
	 */
	public void setEthnicBackground(String ethnicBackground) {
		if (this.ethnicBackground == null || !this.ethnicBackground.equals(ethnicBackground)) {
			markModified();
		}
			this.ethnicBackground = ethnicBackground;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFacilityNameString() {
		fetch();
		return facilityNameString;
	}
	
	/**
	 * 
	 * @param facilityNameString
	 */
	public void setFacilityNameString(String facilityNameString) {
		if (this.facilityNameString == null || !this.facilityNameString.equals(facilityNameString)) {
			markModified();
		}
			this.facilityNameString = facilityNameString;
	}

}
