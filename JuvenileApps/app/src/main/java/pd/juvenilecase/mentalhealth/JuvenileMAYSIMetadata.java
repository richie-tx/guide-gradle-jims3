package pd.juvenilecase.mentalhealth;

import java.util.Date;
import java.util.Iterator;

import naming.PDCodeTableConstants;

import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import messaging.mentalhealth.reply.MAYSIAssessResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import pd.codetable.Code;
import pd.contact.user.UserProfile;
import pd.juvenilecase.referral.JJSTransferredOffenseReferral;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;
import pd.transferobjects.helper.UserProfileHelper;

/**
* @roseuid 427B8D0D02BF
* @author Anand Thorat
* @version 1.0.0
*/
public class JuvenileMAYSIMetadata extends mojo.km.persistence.PersistentObject {
	
	private String referralNumber;
	private boolean hasPreviousMAYSI;
	private boolean administered;
	private String juvenileNumber;
	private String facilityTypeId;
	private String lengthOfStayId;
	private String locationUnitId;
	private String assessmentOptionId;
	private String requestingOfficerId;
	private Date requestDate;
	private String reasonNotDoneId;
	private String raceId;
	private Code race = null;
	private String sexId;
	private Code sex = null;
	private int testAge;
	private Date scheduledOffIntDate;
	private String otherReasonNotDone;
	/**
	* @referencedType pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit
	* @detailerDoNotGenerate false
	Properties for location
	* @contextKey LOCATION
	*/
	private JuvLocationUnit locationUnit = null;
	private UserProfile requestingOfficer = null;
	
	private Code reasonNotDone = null;
	/**
	* Properties for facilityType
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate false
	* @contextKey PLACEMENT_TYPE
	*/
	private Code facilityType = null;
	
	/**
	* Properties for assessmentOption
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate false
	* @contextKey MAYSI_ASSESSMENT_OPTIONS
	*/
	private Code assessmentOption = null;
	/**
	* Properties for lengthOfStay
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate false
	Properties for lengthOfStay
	* @contextKey LENGTH_OF_STAY
	*/
	private Code lengthOfStay = null;

	
	
	/**
	* @roseuid 427B8D0D02BF
	*/
	public JuvenileMAYSIMetadata() {
	}
	
	/**
	* @roseuid 4236ED9502A8
	* @param aMAYSINumber
	* @return pd.juvenilecase.JuvenileMAYSI
	*/
	static public JuvenileMAYSIMetadata find(String aAssessId) {
		return (JuvenileMAYSIMetadata) new Home().find(aAssessId, JuvenileMAYSIMetadata.class);
	}
	/**
	* Finds JuvenileMAYSIMetadata by a certain event
	* @return Iterator of JuvenileMAYSIMetadata
	* @param event
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, JuvenileMAYSIMetadata.class);
	}
	
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		return home.findAll(attributeName,attributeValue,JuvenileMAYSIMetadata.class);
	}	
	
	/**
	* @roseuid 42791D5703CA
	*/
	public void create() {
		markModified();
	}
	/**
	* @return The boolean.
	*/
	public boolean isHasPreviousMAYSI() {
		fetch();
		return hasPreviousMAYSI;
	}
	/**
	* @return The request date.
	*/
	public Date getRequestDate() {
		fetch();
		return requestDate;
	}
	/**
	* @param b The has previous m a y s i.
	*/
	public void setHasPreviousMAYSI(boolean b) {
		if (this.hasPreviousMAYSI != b) {
			markModified();
		}
		hasPreviousMAYSI = b;
	}
	/**
	* @param date The request date.
	*/
	public void setRequestDate(Date date) {
		if (this.requestDate == null || !this.requestDate.equals(date)) {
			markModified();
		}
		requestDate = date;
	}
	/**
	* Get the reference value to class :: pd.contact.user.UserProfile
	* @return The requesting officer id.
	*/
	public String getRequestingOfficerId() {
		fetch();
		return requestingOfficerId;
	}
	/**
	* @return The juvenile number.
	*/
	public String getJuvenileNumber() {
		fetch();
		return juvenileNumber;
	}
	/**
	* @return The referral number.
	*/
	public String getReferralNumber() {
		fetch();
		return referralNumber;
	}
	/**
	* @param string The juvenile number.
	*/
	public void setJuvenileNumber(String string) {
		if (this.juvenileNumber != string || !this.juvenileNumber.equals(string)) {
			markModified();
		}
		juvenileNumber = string;
	}
	/**
	* @param string The referral number.
	*/
	public void setReferralNumber(String string) {
		if (this.referralNumber != string || !this.referralNumber.equals(string)) {
			markModified();
		}
		referralNumber = string;
	}
	/**
	* @param string The requesting officer id.
	*/
	public void setRequestingOfficerId(String string) {
		if (this.requestingOfficerId == null || !this.requestingOfficerId.equals(string)) {
			markModified();
		}
		requestingOfficer=null;
		requestingOfficerId = string;
	}
	/**
	* Initialize class relationship to class pd.contact.user.UserProfile
	*/
	private void initRequestingOfficer() {
		if (requestingOfficer == null) {
			try {
			//87191
				requestingOfficer = UserProfile.find(requestingOfficerId);//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(requestingOfficerId, pd.contact.user.UserProfile.class).getObject();
			} catch (Throwable t) {
				requestingOfficer = null;
			}
		}
	}
	/**
	* Gets referenced type pd.contact.user.UserProfile
	*/
	public UserProfile getRequestingOfficer() {
		fetch();
		initRequestingOfficer();
		return requestingOfficer;
	}
	/**
	* set the type reference for class member reviewer
	*/
	//87191
	public void setRequestingOfficer(UserProfile aRequestingOfficer) {
		/*if (this.requestingOfficer == null || !this.requestingOfficer.equals(aRequestingOfficer)) {
			markModified();
		}
		if (aRequestingOfficer.getOID() == null) {
			new mojo.km.persistence.Home().bind(aRequestingOfficer);
		}*/
		setRequestingOfficerId("" + aRequestingOfficer.getUserID());
		this.requestingOfficer = aRequestingOfficer;//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(aRequestingOfficer).getObject();
	}
	
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getLocationUnitId() {
		fetch();
		return locationUnitId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getLengthOfStayId() {
		fetch();
		return lengthOfStayId;
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setFacilityTypeId(String aFacilityTypeId) {
		if (this.facilityTypeId == null || !this.facilityTypeId.equals(aFacilityTypeId)) {
			markModified();
		}
		facilityType = null;
		this.facilityTypeId = aFacilityTypeId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getFacilityTypeId() {
		fetch();
		return facilityTypeId;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initFacilityType() {
		if (facilityType == null) {
			try {
				facilityType = (Code) new mojo.km.persistence.Reference(facilityTypeId, Code.class, "PLACEMENT_TYPE").getObject();
			} catch (Throwable t) {
				facilityType = null;
			}
		}
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
	* set the type reference for class member facilityType
	*/
	public void setFacilityType(Code aFacilityType) {
		if (this.facilityType == null || !this.facilityType.equals(aFacilityType)) {
			markModified();
		}
		if (aFacilityType.getOID() == null) {
			new mojo.km.persistence.Home().bind(aFacilityType);
		}
		setFacilityTypeId("" + aFacilityType.getOID());
		facilityType.setContext("PLACEMENT_TYPE");
		this.facilityType = (Code) new mojo.km.persistence.Reference(aFacilityType).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setLocationUnitId(String aLocationId) {
		if (this.locationUnitId == null || !this.locationUnitId.equals(aLocationId)) {
			markModified();
		}
		locationUnit = null;
		this.locationUnitId = aLocationId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initLocation() {
		if (locationUnit == null) {
			try{
				locationUnit =
					(JuvLocationUnit) new mojo
						.km
						.persistence
						.Reference(locationUnitId, JuvLocationUnit.class)
						.getObject();
				}
				catch(Throwable t){
					locationUnit = null;
				}
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public JuvLocationUnit getLocationUnit() {
		fetch();
		initLocation();
		return locationUnit;
	}
	/**
	* set the type reference for class member location
	*/
	public void setLocationUnit(JuvLocationUnit aLocation) {
		if (this.locationUnit == null || !this.locationUnit.equals(aLocation)) {
			markModified();
		}
		if (aLocation.getOID() == null) {
			new mojo.km.persistence.Home().bind(aLocation);
		}
		setLocationUnitId("" + aLocation.getOID());
		this.locationUnit = (JuvLocationUnit) new mojo.km.persistence.Reference(aLocation).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setLengthOfStayId(String aLengthOfStayId) {
		if (this.lengthOfStayId == null || !this.lengthOfStayId.equals(aLengthOfStayId)) {
			markModified();
		}
		lengthOfStay = null;
		this.lengthOfStayId = aLengthOfStayId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initLengthOfStay() {
		if (lengthOfStay == null) {
			try {
				lengthOfStay = (Code) new mojo.km.persistence.Reference(lengthOfStayId, Code.class, "LENGTH_OF_STAY").getObject();
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
		setLengthOfStayId("" + aLengthOfStay.getOID());
		aLengthOfStay.setContext("LENGTH_OF_STAY");
		this.lengthOfStay = (Code) new mojo.km.persistence.Reference(aLengthOfStay).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setAssessmentOptionId(String aAssessmentOptionId) {
		if (this.assessmentOptionId == null || !this.assessmentOptionId.equals(aAssessmentOptionId)) {
			markModified();
		}
		assessmentOption = null;
		this.assessmentOptionId = aAssessmentOptionId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getAssessmentOptionId() {
		fetch();
		return assessmentOptionId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initAssessmentOption() {
		if (assessmentOption == null) {
			try {
				assessmentOption =
					(Code) new mojo.km.persistence.Reference(assessmentOptionId, Code.class, "MAYSI_ASSESSMENT_OPTIONS").getObject();
			} catch (Throwable t) {
				assessmentOption = null;
			}
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getAssessmentOption() {
		initAssessmentOption();
		return assessmentOption;
	}
	/**
	* set the type reference for class member assessmentOption
	*/
	public void setAssessmentOption(Code aAssessmentOption) {
		if (this.assessmentOption == null || !this.assessmentOption.equals(aAssessmentOption)) {
			markModified();
		}
		if (aAssessmentOption.getOID() == null) {
			new mojo.km.persistence.Home().bind(aAssessmentOption);
		}
		setAssessmentOptionId("" + aAssessmentOption.getOID());
		aAssessmentOption.setContext("MAYSI_ASSESSMENT_OPTIONS");
		this.assessmentOption = (Code) new mojo.km.persistence.Reference(aAssessmentOption).getObject();
	}
	
	/**
	* @return The boolean.
	*/
	public boolean isAdministered() {
		fetch();
		return administered;
	}
	
	/**
	* @param b The has previous m a y s i.
	*/
	public void setAdministered(boolean b) {
		if (this.administered != b) {
			markModified();
		}
		administered = b;
	}
	
	/**
	* @return 
	*/
	public String getReasonNotDoneId() {
		fetch();
		return reasonNotDoneId;
	}
	
	
	/**
	* @param string
	*/
	public void setReasonNotDoneId(String string) {
		if (this.reasonNotDoneId == null || !this.reasonNotDoneId.equals(string)) {
			markModified();
		}
		reasonNotDone=null;
		reasonNotDoneId = string;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initReasonNotDone() {
		if (reasonNotDone == null) {
			try {
				reasonNotDone = (Code) new mojo.km.persistence.Reference(reasonNotDoneId, Code.class, "REASON_NOT_DONE").getObject();
			} catch (Throwable t) {
				reasonNotDone = null;
			}
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getReasonNotDone() {
		fetch();
		initReasonNotDone();
		return reasonNotDone;
	}
	/**
	* set the type reference for class member lengthOfStay
	*/
	public void setReasonNotDone(Code aReasonNotDone) {
		if (this.reasonNotDone == null || !this.reasonNotDone.equals(aReasonNotDone)) {
			markModified();
		}
		if (aReasonNotDone.getOID() == null) {
			new mojo.km.persistence.Home().bind(aReasonNotDone);
		}
		setReasonNotDoneId("" + aReasonNotDone.getOID());
		aReasonNotDone.setContext("REASON_NOT_DONE");
		this.reasonNotDone = (Code) new mojo.km.persistence.Reference(aReasonNotDone).getObject();
	}
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param raceId The race id.
	*/
	public void setRaceId(String raceId) {
		if (this.raceId == null || !this.raceId.equals(raceId)) {
			markModified();
		}
		race = null;
		this.raceId = raceId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return The race id.
	*/
	public String getRaceId() {
		fetch();
		return raceId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return The race.
	*/
	public Code getRace() {
		fetch();
		initRace();
		return race;
	}
	/**
	* set the type reference for class member race
	* @param race The race.
	*/
	public void setRace(Code race) {
		if (this.race == null || !this.race.equals(race)) {
			markModified();
		}
		if (race.getOID() == null) {
			new mojo.km.persistence.Home().bind(race);
		}
		setRaceId("" + race.getOID());
		race.setContext("RACE");
		this.race = (Code) new mojo.km.persistence.Reference(race).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param sexId The sex id.
	*/
	public void setSexId(String sexId) {
		if (this.sexId == null || !this.sexId.equals(sexId)) {
			markModified();
		}
		sex = null;
		this.sexId = sexId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return The sex id.
	*/
	public String getSexId() {
		fetch();
		return sexId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return The sex.
	*/
	public Code getSex() {
		fetch();
		initSex();
		return sex;
	}
	/**
	* set the type reference for class member sex
	* @param sex The sex.
	*/
	public void setSex(Code sex) {
		if (this.sex == null || !this.sex.equals(sex)) {
			markModified();
		}
		if (sex.getOID() == null) {
			new mojo.km.persistence.Home().bind(sex);
		}
		setSexId("" + sex.getOID());
		sex.setContext("SEX");
		this.sex = (Code) new mojo.km.persistence.Reference(sex).getObject();
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initRace() {
		if (race == null) {
			race = (Code) new mojo.km.persistence.Reference(raceId, Code.class, PDCodeTableConstants.RACE).getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initSex() {
		if (sex == null) {
			sex = (Code) new mojo.km.persistence.Reference(sexId, Code.class, "SEX").getObject();
		}
	}
	
	public MAYSIAssessResponseEvent getResponseEvent(){
		MAYSIAssessResponseEvent myRespEvt=new MAYSIAssessResponseEvent();
		myRespEvt.setAssessmentId(this.getOID().toString());
		myRespEvt.setAdministered(this.isAdministered());
		myRespEvt.setOtherReasonNotDone( this.getOtherReasonNotDone());
		myRespEvt.setReasonNotDoneId(this.getReasonNotDoneId());
		if(this.getReasonNotDone()!=null){
			myRespEvt.setReasonNotDone(this.getReasonNotDone().getDescription());
		}
		myRespEvt.setReferralNumber(this.getReferralNumber());
		myRespEvt.setLocationUnitId(this.getLocationUnitId());
		if(this.getLocationUnit()!=null){
			myRespEvt.setLocationUnit(this.getLocationUnit().getLocationUnitName());
		}
		myRespEvt.setFacilityTypeId(this.getFacilityTypeId());
		if(this.getFacilityType()!=null){
			myRespEvt.setFacilityType(this.getFacilityType().getDescription());
		}
		myRespEvt.setHasPreviousMAYSI(this.isHasPreviousMAYSI());
		myRespEvt.setJuvenileNum(this.getJuvenileNumber());
		
		myRespEvt.setLengthOfStayId(this.getLengthOfStayId());
		if(this.getLengthOfStay()!=null){
			myRespEvt.setLengthOfStay(this.getLengthOfStay().getDescription());
		}
		myRespEvt.setAssessmentOptionId(this.getAssessmentOptionId());
		if(this.getAssessmentOption()!=null){
			myRespEvt.setAssessmentOption(this.getAssessmentOption().getDescription());
		}
		
		myRespEvt.setAssessmentDate(this.getRequestDate());
		if(this.getRequestDate()!=null){
			myRespEvt.setAssessmentTime(DateUtil.getHHMMSSWithColonFromDate(this.getRequestDate()));
		}
		myRespEvt.setAssessOfficerId(this.getRequestingOfficerId());
		IName myName=new NameBean();
		myRespEvt.setAssessOfficerName(myName);
		if(this.getRequestingOfficer()!=null){
			myName.setFirstName(this.getRequestingOfficer().getFirstName());
			myName.setMiddleName(this.getRequestingOfficer().getMiddleName());
			myName.setLastName(this.getRequestingOfficer().getLastName());
			
		}
		if(this.getRace()!=null){
		    myRespEvt.setRace(this.getRace().getDescription());
		}
		myRespEvt.setRaceId(this.getRaceId());
		if(this.getSex()!=null){
		    myRespEvt.setSex(this.getSex().getDescription());
		}
		myRespEvt.setSexId(this.getSexId());
		myRespEvt.setRequestingOfficerId(this.getRequestingOfficerId());
		return myRespEvt;
	}
	
	/**
	 * @return Returns the testAge.
	 */
	public int getTestAge() {
		return testAge;
	}
	/**
	 * @param testAge The testAge to set.
	 */
	public void setTestAge(int testAge) {
		
		if (this.testAge != testAge) {
			markModified();
		}
		this.testAge = testAge;
	}
	/**
	 * @return the scheduledOffIntDate
	 */
	public Date getScheduledOffIntDate() {
		fetch();
		return scheduledOffIntDate;
	}

	/**
	 * @param scheduledOffIntDate the aScheduledOffIntDate to set
	 */
	public void setScheduledOffIntDate(Date aScheduledOffIntDate) {
		if (this.scheduledOffIntDate == null || !this.scheduledOffIntDate.equals(aScheduledOffIntDate)) {
			markModified();
		}
		this.scheduledOffIntDate = aScheduledOffIntDate;
	}

	public String getOtherReasonNotDone()
	{
	    fetch();
	    return otherReasonNotDone;
	}

	public void setOtherReasonNotDone(String otherReasonNotDone)
	{
	    if (this.otherReasonNotDone == null || !this.otherReasonNotDone.equals(otherReasonNotDone)) {
		markModified();
	}
	    this.otherReasonNotDone = otherReasonNotDone;
	}
	
}
