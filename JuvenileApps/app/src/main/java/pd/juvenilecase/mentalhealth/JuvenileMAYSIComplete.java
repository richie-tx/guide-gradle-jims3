/*
 * Created on Jun 28, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.mentalhealth;

import java.util.Date;
import java.util.Iterator;

import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
import messaging.mentalhealth.reply.MAYSISearchResultResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import pd.codetable.Code;
import pd.codetable.person.ReasonNotDone;
import pd.contact.user.UserProfile;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;



/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JuvenileMAYSIComplete extends PersistentObject {
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
	private String otherReasonNotDone;
	private String reasonNotDoneId;
	private Code reasonNotDone=null;  
	private String raceId;
	private Code race = null;
	private String sexId;
	private Code sex = null;
	private String hispanic; //U.S 88526
	private String ethnicityId;
	/**
	* @referencedType pd.codetable.Code
	* @contextKey ETHNICITY
	* @detailerDoNotGenerate true
	*/
	public Code ethnicity = null;
	
	/**
	* @referencedType pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit
	* @detailerDoNotGenerate false
	Properties for location
	* @contextKey LOCATION
	*/
	private JuvLocationUnit locationUnit = null;
	private UserProfile requestingOfficer = null;
	
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

	
	// SUB ASSESMENT FIELDS
	
	private Date reviewDate;
	private String reviewerId;
	private String juvenileMAYSIAssessId;
	private String juvenileMAYSISubAssessId;
	private String reviewComments;
	private String providerTypeReferredId;
	private boolean subReferral;
	private boolean assessComplete;
	
	private Code providerTypeReferred;
	/**
	* Properties for reviewer
	*/
	private UserProfile reviewer = null;
	
	
	// DETAIL FIELDS
	
	private String juvenileMAYSIDetailId;
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
	private Date scheduledOffIntDate;
	
	
	/**
	* @roseuid 4236ED9502A8
	* @param aMAYSINumber
	* @return pd.juvenilecase.JuvenileMAYSI
	*/
	static public JuvenileMAYSIComplete find(String aMAYSICompleteId) {
		return (JuvenileMAYSIComplete) new Home().find(aMAYSICompleteId, JuvenileMAYSIComplete.class);
	}
	/**
	* @return java.util.Iterator
	* @param attrName
	* @param attrValue
	* @roseuid 4236ED950316
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		return (Iterator) home.findAll(attrName, attrValue, JuvenileMAYSIComplete.class);
	}
	
	/**
	* Finds JuvenileMAYSI by a certain event
	* @return Iterator of JuvenileMAYSI
	* @param event
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, JuvenileMAYSIComplete.class);
	}
	
	/**
	* @roseuid 42791D5703CA
	*/
	public void create() {
		//markModified();
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
			//markModified();
		}
		hasPreviousMAYSI = b;
	}
	/**
	* @param date The request date.
	*/
	public void setRequestDate(Date date) {
		if (this.requestDate == null || !this.requestDate.equals(date)) {
			//markModified();
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
			//markModified();
		}
		juvenileNumber = string;
	}
	/**
	* @param string The referral number.
	*/
	public void setReferralNumber(String string) {
		if (this.referralNumber != string || !this.referralNumber.equals(string)) {
			//markModified();
		}
		referralNumber = string;
	}
	/**
	* @param string The requesting officer id.
	*/
	public void setRequestingOfficerId(String string) {
		if (this.requestingOfficerId == null || !this.requestingOfficerId.equals(string)) {
			//markModified();
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
			//markModified();
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
			//markModified();
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
			//markModified();
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
			//markModified();
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
			//markModified();
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
			//markModified();
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
			//markModified();
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
			//markModified();
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
			//markModified();
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
			//markModified();
		}
		administered = b;
	}
	
	
	// SUB ASSESS METHODS
	
	/**
	* @return 
	*/
	public String getReviewComments() {
		fetch();
		return reviewComments;
	}
	/**
	* @return 
	*/
	public Date getReviewDate() {
		fetch();
		return reviewDate;
	}
	
	/**
	* @param string
	*/
	public void setReviewComments(String string) {
		if (this.reviewComments == null || !this.reviewComments.equals(string)) {
			//markModified();
		}
		reviewComments = string;
	}
	/**
	* @param date
	*/
	public void setReviewDate(Date date) {
		if (this.reviewDate == null || !this.reviewDate.equals(date)) {
			//markModified();
		}
		reviewDate = date;
	}

	/**
	* Set the reference value to class :: pd.contact.user.UserProfile
	*/
	public void setReviewerId(String aReviewerId) {
		if (this.reviewerId == null || !this.reviewerId.equals(aReviewerId)) {
			//markModified();
		}
		reviewer = null;
		this.reviewerId = aReviewerId;
	}
	/**
	* Get the reference value to class :: pd.contact.user.UserProfile
	*/
	public String getReviewerId() {
		fetch();
		return reviewerId;
	}
	/**
	* Initialize class relationship to class pd.contact.user.UserProfile
	*/
	private void initReviewer() {
		if (reviewer == null) {
			try {
			//87191
				reviewer = UserProfile.find(reviewerId);//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(reviewerId, pd.contact.user.UserProfile.class).getObject();
			} catch (Throwable t) {
				reviewer = null;
			}
		}
	}
	/**
	* Gets referenced type pd.contact.user.UserProfile
	*/
	public UserProfile getReviewer() {
		fetch();
		initReviewer();
		return reviewer;
	}
	/**
	* set the type reference for class member reviewer
	*/
	//87191
	public void setReviewer(UserProfile aReviewer) {
		/*if (this.reviewer == null || !this.reviewer.equals(aReviewer)) {
			//markModified();
		}
		if (aReviewer.getOID() == null) {
			new mojo.km.persistence.Home().bind(aReviewer);
		}*/
		setReviewerId("" + aReviewer.getUserID());
		this.reviewer = aReviewer;//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(aReviewer).getObject();
	}
	
	/**
	 * @return
	 */
	public String getProviderTypeReferredId()
	{
		fetch();
		return providerTypeReferredId;
	}

	/**
	 * @param string
	 */
	public void setProviderTypeReferredId(String string)
	{
		if ( this.providerTypeReferredId == null || ! this.providerTypeReferredId.equals(string) ) {
			//markModified();
		}
		providerTypeReferredId = string;
	}

	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initProviderTypeReferred() {
		if (providerTypeReferred == null) {
			try {
				providerTypeReferred = (Code) new mojo.km.persistence.Reference(providerTypeReferredId, Code.class, "MAYSI_PROVIDER_TYPE_REFERRED").getObject();
			} catch (Throwable t) {
				providerTypeReferred = null;
			}
		}
	}

	/**
	 * @return
	 */
	public Code getProviderTypeReferred()
	{
		initProviderTypeReferred();
		return providerTypeReferred;
	}

	/**
	 * @param string
	 */
	public void setProviderTypeReferred( Code aCode )
	{
		setProviderTypeReferredId( aCode.getOID().toString() );
		providerTypeReferred = null;
	}

	/**
	* @return The boolean.
	*/
	public boolean isSubReferral() {
		fetch();
		return subReferral;
	}
	
	/**
	* @param b The has previous m a y s i.
	*/
	public void setSubReferral(boolean b) {
		if (this.subReferral != b) {
			//markModified();
		}
		subReferral = b;
	}
	
	/**
	* @return The boolean.
	*/
	public boolean isAssessComplete() {
		fetch();
		return assessComplete;
	}
	
	/**
	* @param b The has previous m a y s i.
	*/
	public void setAssessComplete(boolean b) {
		if (this.assessComplete != b) {
			//markModified();
		}
		assessComplete = b;
	}
	/**
	* Set the reference value to class :: pd.juvenilecase.JuvenileMAYSI
	*/
	public void setJuvenileMAYSIAssessId(String aJuvenileMAYSIAssessId) {
		if (this.juvenileMAYSIAssessId == null || !this.juvenileMAYSIAssessId.equals(aJuvenileMAYSIAssessId)) {
			//markModified();
		}
		this.juvenileMAYSIAssessId = aJuvenileMAYSIAssessId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.JuvenileMAYSI
	*/
	public String getJuvenileMAYSIAssessId() {
		fetch();
		return juvenileMAYSIAssessId;
	}
	
	/**
	* Set the reference value to class :: pd.juvenilecase.JuvenileMAYSI
	*/
	public void setJuvenileMAYSISubAssessId(String aJuvenileMAYSISubAssessId) {
		if (this.juvenileMAYSISubAssessId == null || !this.juvenileMAYSISubAssessId.equals(aJuvenileMAYSISubAssessId)) {
			//markModified();
		}
		this.juvenileMAYSISubAssessId = aJuvenileMAYSISubAssessId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.JuvenileMAYSI
	*/
	public String getJuvenileMAYSISubAssessId() {
		fetch();
		return juvenileMAYSISubAssessId;
	}
	/**
	* Set the reference value to class :: pd.juvenilecase.JuvenileMAYSI
	*/
	public void setJuvenileMAYSIDetailId(String aJuvenileMAYSIDetailId) {
		if (this.juvenileMAYSIDetailId == null || !this.juvenileMAYSIDetailId.equals(aJuvenileMAYSIDetailId)) {
			//markModified();
		}
		this.juvenileMAYSIDetailId = aJuvenileMAYSIDetailId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.JuvenileMAYSI
	*/
	public String getJuvenileMAYSIDetailId() {
		fetch();
		return juvenileMAYSIDetailId;
	}
	
	//DETAIL METHODS
	/**
	* @return 
	*/
	public int getAlcoholDrug() {
		fetch();
		return alcoholDrug;
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
			//markModified();
		}
		alcoholDrug = i;
	}
	/**
	* @param i
	*/
	public void setAngryIrritable(int i) {
		if (this.angryIrritable != i) {
			//markModified();
		}
		angryIrritable = i;
	}
	/**
	* @param i
	*/
	public void setDepressionAnxiety(int i) {
		if (this.depressionAnxiety != i) {
			//markModified();
		}
		depressionAnxiety = i;
	}
	
	
	/**
	* @param date
	*/
	public void setScreenDate(Date date) {
		if (this.screenDate == null || !this.screenDate.equals(date)) {
			//markModified();
		}
		screenDate = date;
	}
	/**
	* @param i
	*/
	public void setSomaticComplaint(int i) {
		if (this.somaticComplaint != i) {
			//markModified();
		}
		somaticComplaint = i;
	}
	/**
	* @param i
	*/
	public void setSuicideIdeation(int i) {
		if (this.suicideIdeation != i) {
			//markModified();
		}
		suicideIdeation = i;
	}
	/**
	* @param i
	*/
	public void setTestAge(int i) {
		if (this.testAge != i) {
			//markModified();
		}
		testAge = i;
	}
	/**
	* @param i
	*/
	public void setThoughtDisturbance(int i) {
		if (this.thoughtDisturbance != i) {
			//markModified();
		}
		thoughtDisturbance = i;
	}
	/**
	* @param i
	*/
	public void setTraumaticExpression(int i) {
		if (this.traumaticExpression != i) {
			//markModified();
		}
		traumaticExpression = i;
	}
	
	public MAYSIDetailsResponseEvent getResponseEvent(){
		MAYSIDetailsResponseEvent myRespEvt=new MAYSIDetailsResponseEvent();
		myRespEvt.setAssessFullId(this.getOID().toString());
		// Set assessment items
		myRespEvt.setAssessmentId(this.getJuvenileMAYSIAssessId());
		if(this.getTestAge()==0){
			myRespEvt.setTestAge("");
		}
		else
			myRespEvt.setTestAge(String.valueOf(this.getTestAge()));
		
		myRespEvt.setAdministered(this.isAdministered());
		myRespEvt.setOtherReasonNotDone(this.getOtherReasonNotDone());
		myRespEvt.setReasonNotDone(this.getReasonNotDoneId());
		if(this.getReasonNotDone()!=null)
			myRespEvt.setReasonNotDone(this.getReasonNotDone().getDescription());
		if(this.getScheduledOffIntDate() != null){
			myRespEvt.setScheduledOffIntDate(this.getScheduledOffIntDate());
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
		
		myRespEvt.setHispanic(this.getHispanic()); //U.S 88526
		myRespEvt.setEthnicity(this.getEthnicityId());
		
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
		// subassessment items
		myRespEvt.setSubAssessId(this.getJuvenileMAYSISubAssessId());
		if(this.getJuvenileMAYSISubAssessId()!=null && !this.getJuvenileMAYSISubAssessId().equals("") && !this.getJuvenileMAYSISubAssessId().equals("0")){
				myRespEvt.setAssessmentReviewdate(this.getReviewDate());
				if(this.getReviewDate()!=null){
					myRespEvt.setAssessmentReviewtime(DateUtil.getHHMMSSWithColonFromDate(this.getReviewDate()));
				}
				myRespEvt.setAssessComplete(this.isAssessComplete());
				myRespEvt.setProviderTypeId(this.getProviderTypeReferredId());
				if(this.getProviderTypeReferred()!=null)
					myRespEvt.setProviderType(this.getProviderTypeReferred().getDescription());
				
				myRespEvt.setReviewComments(this.getReviewComments());
				myRespEvt.setSubAssessOfficerId(this.getReviewerId());
				IName myName1=new NameBean();
				myRespEvt.setSubAssessOfficerName(myName1);
				if(this.getReviewer()!=null){
					myName1.setFirstName(this.getReviewer().getFirstName());
					myName1.setMiddleName(this.getReviewer().getMiddleName());
					myName1.setLastName(this.getReviewer().getLastName());
					
				}
				myRespEvt.setSubReferral(this.isSubReferral());
		}
		// detail items
		myRespEvt.setMaysiDetailId(this.getJuvenileMAYSIDetailId());
		if(this.getJuvenileMAYSIDetailId()!=null && !this.getJuvenileMAYSIDetailId().equals("") && !this.getJuvenileMAYSIDetailId().equals("0")){
			myRespEvt.setAlcoholDrug(this.getAlcoholDrug());
			myRespEvt.setAngryIrritable(this.getAngryIrritable());
			myRespEvt.setDepressionAnxiety(this.getDepressionAnxiety());
			myRespEvt.setScreenDate(this.getScreenDate());
			myRespEvt.setSomaticComplaint(this.getSomaticComplaint());	
			myRespEvt.setTraumaticExpression(this.getTraumaticExpression());
			myRespEvt.setSuicideIdetaion(this.getSuicideIdeation());
			myRespEvt.setThoughtDisturbance(this.getThoughtDisturbance());
			
		}
		myRespEvt.setDetailRaceId(this.getDetailRaceId());
		myRespEvt.setDetailSexId(this.getDetailSexId());
		if(this.getRace()!=null)
			myRespEvt.setRace(this.getRace().getDescription());
		myRespEvt.setRaceId(this.getRaceId());
		myRespEvt.setSexId(this.getSexId());
		if(this.getSex()!=null){
			myRespEvt.setSex(this.getSex().getDescription());
		}
		return myRespEvt;
	}
	
	public MAYSISearchResultResponseEvent getSearchResponseEvent(){
		MAYSISearchResultResponseEvent myRespEvt=new MAYSISearchResultResponseEvent();
		myRespEvt.setMaysiFullAssessId(this.getOID().toString());
		
		/* The following three sets make up the MaysiFullAssessId; */
		myRespEvt.setAssessmentId(this.getJuvenileMAYSIAssessId());
		myRespEvt.setSubAssessId(this.getJuvenileMAYSISubAssessId());
		myRespEvt.setMaysiDetailId(this.getJuvenileMAYSIDetailId());
		
		myRespEvt.setReferralNumber(this.getReferralNumber());
		myRespEvt.setLocationUnitId(this.getLocationUnitId());
		if(this.getLocationUnit()!=null){
			myRespEvt.setLocationUnit(this.getLocationUnit().getLocationUnitName());
		}
		myRespEvt.setFacilityTypeId(this.getFacilityTypeId());
		if(this.getFacilityType()!=null){
			myRespEvt.setFacilityType(this.getFacilityType().getDescription());
		}
	
		
		myRespEvt.setAssessmentOptionId(this.getAssessmentOptionId());
		if(this.getAssessmentOption()!=null){
			myRespEvt.setAssessmentOption(this.getAssessmentOption().getDescription());
		}
		
		myRespEvt.setAssessDate(this.getRequestDate());
		if(this.getTestAge()==0){
			myRespEvt.setTestAge("");
		}
		else
			myRespEvt.setTestAge(String.valueOf(this.getTestAge()));
		
		
		// detail items
		myRespEvt.setDetailsAvailable(false);
		if(this.getJuvenileMAYSIDetailId()!=null && !this.getJuvenileMAYSIDetailId().equals("") && !this.getJuvenileMAYSIDetailId().equals("0")){
			myRespEvt.setDetailsAvailable(true);
			
		}
		
		ReasonNotDone rnd =null;
		if ( this.getReasonNotDoneId() != null 
			&& this.getReasonNotDoneId().length() > 0 ){
		    Iterator<ReasonNotDone> rndIter = ReasonNotDone.findAll("description", this.getReasonNotDoneId());
        		while (rndIter.hasNext()) 
        		{
        		    rnd = (ReasonNotDone) rndIter.next();
        		    break;
        		}
        		
        		if(rnd.getFinalReason()!=null
        			&& "NO".equals( rnd.getFinalReason() ))
        		{
        		    myRespEvt.setReasonNotDone(this.getReasonNotDoneId());
        		}
		}
		
		
		return myRespEvt;
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
	
	/**
	 * @return the hispanic
	 */
	public String getHispanic()
	{
	    return hispanic;
	}
	/**
	 * @param hispanic the hispanic to set
	 */
	public void setHispanic(String hispanic) //U.S 88526
	{
	    this.hispanic = hispanic;
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param ethnicityId The ethnicity id.
	*/
	public void setEthnicityId(String ethnicityId) {
		if (this.ethnicityId == null || !this.ethnicityId.equals(ethnicityId)) {
			markModified();
		}
		ethnicity = null;
		this.ethnicityId = ethnicityId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return The ethnicity id.
	*/
	public String getEthnicityId() {
		fetch();
		return ethnicityId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return The ethnicity.
	*/
	public Code getEthnicity() {
		fetch();
		initEthnicity();
		return ethnicity;
	}
	/**
	* set the type reference for class member ethnicity
	* @param ethnicity The ethnicity.
	*/
	public void setEthnicity(Code ethnicity) {
		if (this.ethnicity == null || !this.ethnicity.equals(ethnicity)) {
			markModified();
		}
		if (ethnicity.getOID() == null) {
			new mojo.km.persistence.Home().bind(ethnicity);
		}
		setEthnicityId("" + ethnicity.getOID());
		ethnicity.setContext(PDCodeTableConstants.ETHNICITY);
		this.ethnicity = (Code) new mojo.km.persistence.Reference(ethnicity).getObject();
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initEthnicity() {
		if (ethnicity == null) {
			ethnicity = (Code) new mojo.km.persistence.Reference(ethnicityId, Code.class, PDCodeTableConstants.ETHNICITY).getObject();
		}
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
		if (this.requestDate == null || !this.requestDate.equals(aScheduledOffIntDate)) {
			//markModified();
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
	    this.otherReasonNotDone = otherReasonNotDone;
	}
	
}
