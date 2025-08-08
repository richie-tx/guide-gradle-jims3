package pd.juvenilecase.casefile;

/**
 * @author mchowdhury
*/


import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import naming.PDCodeTableConstants;

import messaging.casefile.UpdateCasefileClosingEvent;
import messaging.casefile.UpdateCourtExitPlanEvent;
import messaging.casefile.UpdateResidentialExitPlanEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.security.ISecurityManager;
import pd.codetable.Code;

/**
* Properties for supervisionOutcome
* @referencedType pd.codetable.Code
* @contextKey SUPERVISION_OUTCOME
* @detailerDoNotGenerate false
*/
public class CasefileClosingInfo extends PersistentObject {
	private String petitionNumber;
	/**
	* Properties for responses
	* @referencedType pd.juvenilecase.casefile.Response
	*/
	private Collection responses = null;
	private String closingEvaluation;
	private String rejectionReason;
	
	/**
	* Properties for levelOfCare
	* @referencedType pd.codetable.Code
	* @contextKey LEVEL_OF_CARE
	* @detailerDoNotGenerate false
	*/
	private Code levelOfCare = null;
	/**
	* Properties for facility
	* @referencedType pd.codetable.Code
	* @contextKey JUVENILE_DETENTION_FACILITY
	* @detailerDoNotGenerate false
	*/
	private Code facility = null;
	private String facilityReleaseReasonId;
	private String supervisionOutcomeId;
	private String supervisionOutcomeDescriptionId;
	private String facilityReleaseReasonName;
	private String permanencyPlanName;
	private String facilityId;
	private String facilityName;
	private String supervisionNumber;
	private Date supervisionEndDate = null;

	/**
	* Properties for permanencyPlan
	* @referencedType pd.codetable.Code
	* @contextKey PERMANENCY_PLAN
	* @detailerDoNotGenerate false
	*/
	private Code permanencyPlan = null;
	private String levelOfCareName;
	private String levelOfCareId;
	/**
	* Properties for supervisionOutcome
	* @referencedType pd.codetable.Code
	* @contextKey SUPERVISION_OUTCOME
	* @detailerDoNotGenerate false
	*/
	private Code supervisionOutcome = null;
	private String casefileClosingStatus;
	/**
	* Properties for facilityReleaseReason
	* @referencedType pd.codetable.Code
	* @contextKey FACILITY_RELEASE_REASON
	* @detailerDoNotGenerate false
	*/
	private Code facilityReleaseReason = null;
	private String permanencyPlanId;
	private String specialNotes;
	private String exitPlanTemplateLocation;
	private String supervisionOutcomeName;
	private String controllingReferralId;
	private Date expectedReleaseDate = null;
	private String createUserId;
	private String closingComments;
	private String juvLocUnitId;
	private String recordJuvUnit;
	private String recordCLM;
	
	private boolean closingPktGenerated;
	private boolean closingLetterGenerated;
	

	
	
	/**
	* @roseuid 439602DA01D3
	*/
	public CasefileClosingInfo() {
	}
	/**
	* @return pd.juvenilecase.casefile
	* @param casefileClosingInfoId
	* @roseuid 4107B06D01B5
	*/
	static public CasefileClosingInfo find(String casefileClosingInfoId) {
		CasefileClosingInfo casefileClosingInfo = null;
		IHome home = new Home();
		casefileClosingInfo = (CasefileClosingInfo) home.find(casefileClosingInfoId, CasefileClosingInfo.class);
		return casefileClosingInfo;
	}

	/**
	* @return java.util.Iterator
	* @param event
	* @roseuid 4107B06D01BB
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		Iterator iter = home.findAll(event, CasefileClosingInfo.class);
		return iter;
	}
	/**
	* @return java.util.Iterator
	* @param casefileClosingInfoId
	* @roseuid 4177C29D03A9
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		Iterator iter = null;
		iter = home.findAll(attrName, attrValue, CasefileClosingInfo.class);
		return iter;
	}
	/**
	* @roseuid 4395C2370008
	*/
	public void bind() {
		markModified();
	}
	
	/**
	* @return 
	*/
	public String getCasefileClosingStatus() {
		fetch();
		return casefileClosingStatus;
	}
	/**
	* @return 
	*/
	public String getClosingEvaluation() {
		fetch();
		return closingEvaluation;
	}
	/**
	* @return 
	*/
	public String getClosingComments() {
		fetch();
		return closingComments;
	}
	
   /**
	* @return 
	*/
	public String getRejectionReason() {
		fetch();
		return rejectionReason;
	}
		
	/**
	* @return 
	*/
	public String getControllingReferralId() {
		fetch();
		return controllingReferralId;
	}
	/**
	* @return 
	*/
	public String getExitPlanTemplateLocation() {
		fetch();
		return exitPlanTemplateLocation;
	}
	/**
	* @return 
	*/
	public Date getExpectedReleaseDate() {
		fetch();
		return expectedReleaseDate;
	}
	/**
	* @return 
	*/
	public Collection getResponses() {
		initResponses();
		fetch();
		return responses;
	}
	/**
	* @return 
	*/
	public String getPetitionNumber() {
		fetch();
		return petitionNumber;
	}
	/**
	* @return 
	*/
	public String getSpecialNotes() {
		fetch();
		return specialNotes;
	}
	/**
	* @return 
	*/
	public Date getSupervisionEndDate() {
		fetch();
		return supervisionEndDate;
	}
	/**
	* @return 
	*/
	public String getSupervisionNumber() {
		fetch();
		return supervisionNumber;
	}
	/**
	* @return 
	*/
	public String getSupervisionOutcomeId() {
		fetch();
		return supervisionOutcomeId;
	}
	/**
	* @return 
	*/
	public String getSupervisionOutcomeName() {
		fetch();
		return supervisionOutcomeName;
	}
	/**
	* @return 
	*/
	public String getFacilityId() {
		fetch();
		return facilityId;
	}
	/**
	* @return 
	*/
	public String getFacilityName() {
		fetch();
		return facilityName;
	}
	/**
	* @return 
	*/
	public String getFacilityReleaseReasonId() {
		fetch();
		return facilityReleaseReasonId;
	}
	/**
	* @return 
	*/
	public String getFacilityReleaseReasonName() {
		fetch();
		return facilityReleaseReasonName;
	}
	/**
	* @return 
	*/
	public String getLevelOfCareId() {
		fetch();
		return levelOfCareId;
	}
	/**
	* @return 
	*/
	public String getLevelOfCareName() {
		fetch();
		return levelOfCareName;
	}
	/**
	* @return 
	*/
	public String getPermanencyPlanId() {
		fetch();
		return permanencyPlanId;
	}
	/**
	* @return 
	*/
	public String getPermanencyPlanName() {
		fetch();
		return permanencyPlanName;
	}
	/**
	 * @return the supervisionOutcomeDescriptionId
	 */
	public String getSupervisionOutcomeDescriptionId() {
		fetch();
		return supervisionOutcomeDescriptionId;
	}
	/**
	 * @param supervisionOutcomeDescriptionId the supervisionOutcomeDescriptionId to set
	 */
	public void setSupervisionOutcomeDescriptionId(
			String supervisionOutcomeDescriptionId) {
		if (this.supervisionOutcomeDescriptionId == null || !this.supervisionOutcomeDescriptionId.equals(supervisionOutcomeDescriptionId)) {
			markModified();
		}
		this.supervisionOutcomeDescriptionId = supervisionOutcomeDescriptionId;
	}
	/**
	* Sets the value of the casefileClosingStatus property.
	* @param aCasefileClosingStatus the new value of the casefileClosingStatus property
	*/
	public void setCasefileClosingStatus(String aCasefileClosingStatus) {
		if (this.casefileClosingStatus == null || !this.casefileClosingStatus.equals(aCasefileClosingStatus)) {
			markModified();
		}
		casefileClosingStatus = aCasefileClosingStatus;
	}
	/**
	* Sets the value of the closingEvaluation property.
	* @param aClosingEvaluation the new value of the closingEvaluation property
	*/
	public void setClosingEvaluation(String aClosingEvaluation) {
		if (this.closingEvaluation == null || !this.closingEvaluation.equals(aClosingEvaluation)) {
			markModified();
		}
		closingEvaluation = aClosingEvaluation;
	}
	/**
	* Sets the value of the closingComments property.
	* @param aClosingComments the new value of the closingComments property
	*/
	public void setClosingComments(String aClosingComments) {
		if (this.closingComments == null || !this.closingComments.equals(aClosingComments)) {
			markModified();
		}
		closingComments = aClosingComments;
	}
	
	/**
	* Sets the value of the rejectionReason property.
	* @param aRejectionReason the new value of the rejectionReason property
	*/
	public void setRejectionReason(String aRejectionReason) {
		if (this.rejectionReason == null || !this.rejectionReason.equals(aRejectionReason)) {
			markModified();
		}
		rejectionReason = aRejectionReason;
	}

	/**
	* Sets the value of the controllingReferralId property.
	* @param aControllingReferralId the new value of the controllingReferralId property
	*/
	public void setControllingReferralId(String aControllingReferralId) {
		if (this.controllingReferralId == null || !this.controllingReferralId.equals(aControllingReferralId)) {
			markModified();
		}
		controllingReferralId = aControllingReferralId;
	}
	/**
	* Sets the value of the exitPlanTemplateLocation property.
	* @param aEexitPlanTemplateLocation the new value of the exitPlanTemplateLocation property
	*/
	public void setExitPlanTemplateLocation(String aExitPlanTemplateLocation) {
		if (this.exitPlanTemplateLocation == null || !this.exitPlanTemplateLocation.equals(aExitPlanTemplateLocation)) {
			markModified();
		}
		exitPlanTemplateLocation = aExitPlanTemplateLocation;
	}
	/**
	* Sets the value of the expectedReleaseDate property.
	* @param aExpectedReleaseDate the new value of the expectedReleaseDate property
	*/
	public void setExpectedReleaseDate(Date aExpectedReleaseDate) {
		if (this.expectedReleaseDate == null || !this.expectedReleaseDate.equals(aExpectedReleaseDate)) {
			markModified();
		}
		expectedReleaseDate = aExpectedReleaseDate;
	}
	/**
	* Sets the value of the petitionNumber property.
	* @param aPetitionNumber the new value of the petitionNumber property
	*/
	public void setPetitionNumber(String aPetitionNumber) {
		if (this.petitionNumber == null || !this.petitionNumber.equals(aPetitionNumber)) {
			markModified();
		}
		petitionNumber = aPetitionNumber;
	}
	/**
	* Sets the value of the specialNotes property.
	* @param aSpecialNotes the new value of the specialNotes property
	*/
	public void setSpecialNotes(String aSpecialNotes) {
		if (this.specialNotes == null || !this.specialNotes.equals(aSpecialNotes)) {
			markModified();
		}
		specialNotes = aSpecialNotes;
	}
	/**
	* Sets the value of the supervisionEndDate property.
	* @param aSupervisionEndDate the new value of the supervisionEndDate property
	*/
	public void setSupervisionEndDate(Date aSupervisionEndDate) {
		if (this.supervisionEndDate == null || !this.supervisionEndDate.equals(aSupervisionEndDate)) {
			markModified();
		}
		supervisionEndDate = aSupervisionEndDate;
	}
	/**
	* Sets the value of the supervisionNumber property.
	* @param aSupervisionNumber the new value of the supervisionNumber property
	*/
	public void setSupervisionNumber(String aSupervisionNumber) {
		if (this.supervisionNumber == null || !this.supervisionNumber.equals(aSupervisionNumber)) {
			markModified();
		}
		supervisionNumber = aSupervisionNumber;
	}
	/**
	* Sets the value of the supervisionOutcomeId property.
	* @param aSupervisionOutcomeId the new value of the supervisionOutcomeId property
	*/
	public void setSupervisionOutcomeId(String aSupervisionOutcomeId) {
		if (this.supervisionOutcomeId == null || !this.supervisionOutcomeId.equals(aSupervisionOutcomeId)) {
			markModified();
		}
		supervisionOutcomeId = aSupervisionOutcomeId;
	}
	/**
	* Sets the value of the supervisionOutcomeName property.
	* @param aSupervisionOutcomeName the new value of the supervisionOutcomeName property
	*/
	public void setSupervisionOutcomeName(String aSupervisionOutcomeName) {
		if (this.supervisionOutcomeName == null || !this.supervisionOutcomeName.equals(aSupervisionOutcomeName)) {
			markModified();
		}
		supervisionOutcomeId = aSupervisionOutcomeName;
	}
	/**
	* Sets the value of the responses property.
	* @param collection the new value of the responses property
	*/
	public void setResponses(Collection collection) {
		if (this.responses == null || !this.responses.equals(collection)) {
			markModified();
		}
		responses = collection;
	}
	/**
	* Sets the value of the facilityId property.
	* @param aFacilityId the new value of the aFacilityId property
	*/
	public void setFacilityId(String aFacilityId) {
		if (this.facilityId == null || !this.facilityId.equals(aFacilityId)) {
			markModified();
		}
		facilityId = aFacilityId;
	}
	/**
	* Sets the value of the facilityName property.
	* @param aFacilityName the new value of the aFacilityName property
	*/
	public void setFacilityName(String aFacilityName) {
		if (this.facilityName == null || !this.facilityName.equals(aFacilityName)) {
			markModified();
		}
		facilityName = aFacilityName;
	}
	/**
	* Sets the value of the facilityReleaseReasonId property.
	* @param aFacilityReleaseReasonId the new value of the facilityReleaseReasonId property
	*/
	public void setFacilityReleaseReasonId(String aFacilityReleaseReasonId) {
		if (this.facilityReleaseReasonId == null || !this.facilityReleaseReasonId.equals(aFacilityReleaseReasonId)) {
			markModified();
		}
		facilityReleaseReasonId = aFacilityReleaseReasonId;
	}
	/**
	* Sets the value of the facilityReleaseReasonName property.
	* @param aFacilityReleaseReasonName the new value of the facilityReleaseReasonName property
	*/
	public void setFacilityReleaseReasonName(String aFacilityReleaseReasonName) {
		if (this.facilityReleaseReasonName == null || !this.facilityReleaseReasonName.equals(aFacilityReleaseReasonName)) {
			markModified();
		}
		facilityReleaseReasonName = aFacilityReleaseReasonName;
	}
	/**
	* Sets the value of the levelOfCareId property.
	* @param aLevelOfCareId the new value of the levelOfCareId property
	*/
	public void setLevelOfCareId(String aLevelOfCareId) {
		if (this.levelOfCareId == null || !this.levelOfCareId.equals(aLevelOfCareId)) {
			markModified();
		}
		levelOfCareId = aLevelOfCareId;
	}
	/**
	* Sets the value of the levelOfCareName property.
	* @param aLevelOfCareName the new value of the levelOfCareName property
	*/
	public void setLevelOfCareName(String aLevelOfCareName) {
		if (this.levelOfCareName == null || !this.levelOfCareName.equals(aLevelOfCareName)) {
			markModified();
		}
		levelOfCareName = aLevelOfCareName;
	}
	/**
	* Sets the value of the permanencyPlanId property.
	* @param aPermanencyPlanId the new value of the permanencyPlanId property
	*/
	public void setPermanencyPlanId(String aPermanencyPlanId) {
		if (this.permanencyPlanId == null || !this.permanencyPlanId.equals(aPermanencyPlanId)) {
			markModified();
		}
		permanencyPlanId = aPermanencyPlanId;
	}
	/**
	* Sets the value of the permanencyPlanName property.
	* @param aPermanencyPlanName the new value of the permanencyPlanName property
	*/
	public void setPermanencyPlanName(String aPermanencyPlanName) {
		if (this.permanencyPlanName == null || !this.permanencyPlanName.equals(aPermanencyPlanName)) {
			markModified();
		}
		permanencyPlanName = aPermanencyPlanName;
	}
	/**
	* set the type reference for class member facilityReleaseReason
	*/
	public void setFacilityReleaseReason(Code facilityReleaseReason) {
		if (this.facilityReleaseReason == null || !this.facilityReleaseReason.equals(facilityReleaseReason)) {
			markModified();
		}
		if (facilityReleaseReason.getOID() == null) {
			new mojo.km.persistence.Home().bind(facilityReleaseReason);
		}
		setFacilityReleaseReasonId("" + facilityReleaseReason.getOID());
		this.facilityReleaseReason = (Code) new mojo.km.persistence.Reference(facilityReleaseReason).getObject();
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getFacilityReleaseReason() {
		fetch();
		initFacilityReleaseReason();
		return facilityReleaseReason;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initFacilityReleaseReason() {
		if (facilityReleaseReason == null) {
			facilityReleaseReason =
				(Code) new mojo.km.persistence.Reference(facilityReleaseReasonId, Code.class, "FACILITY_RELEASE_REASON").getObject();
		}
	}
	/**
	* set the type reference for class member levelOfCare
	*/
	public void setLevelOfCare(Code levelOfCare) {
		if (this.levelOfCare == null || !this.levelOfCare.equals(levelOfCare)) {
			markModified();
		}
		if (levelOfCare.getOID() == null) {
			new mojo.km.persistence.Home().bind(levelOfCare);
		}
		setLevelOfCareId("" + levelOfCare.getOID());
		this.levelOfCare = (Code) new mojo.km.persistence.Reference(levelOfCare).getObject();
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getLevelOfCare() {
		fetch();
		initLevelOfCare();
		return levelOfCare;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initLevelOfCare() {
		if (levelOfCare == null) {
			levelOfCare = (Code) new mojo.km.persistence.Reference(levelOfCareId, Code.class, "LEVEL_OF_CARE").getObject();
		}
	}
	/**
	* set the type reference for class member permanencyPlan
	*/
	public void setPermanencyPlan(Code permanencyPlan) {
		if (this.permanencyPlan == null || !this.permanencyPlan.equals(permanencyPlan)) {
			markModified();
		}
		if (permanencyPlan.getOID() == null) {
			new mojo.km.persistence.Home().bind(permanencyPlan);
		}
		setPermanencyPlanId("" + permanencyPlan.getOID());
		this.permanencyPlan = (Code) new mojo.km.persistence.Reference(permanencyPlan).getObject();
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getPermanencyPlan() {
		fetch();
		initPermanencyPlan();
		return permanencyPlan;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initPermanencyPlan() {
		if (permanencyPlan == null) {
			permanencyPlan = (Code) new mojo.km.persistence.Reference(permanencyPlanId, Code.class, "PERMANENCY_PLAN").getObject();
		}
	}
	/**
	* set the type reference for class member supervisionOutcome
	*/
	public void setSupervisionOutcome(Code supervisionOutcome) {
		if (this.supervisionOutcome == null || !this.supervisionOutcome.equals(supervisionOutcome)) {
			markModified();
		}
		if (supervisionOutcome.getOID() == null) {
			new mojo.km.persistence.Home().bind(supervisionOutcome);
		}
		setSupervisionOutcomeId("" + supervisionOutcome.getOID());
		this.supervisionOutcome = (Code) new mojo.km.persistence.Reference(supervisionOutcome).getObject();
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getSupervisionOutcome() {
		fetch();
		initSupervisionOutcome();
		return supervisionOutcome;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initSupervisionOutcome() {
		if (supervisionOutcome == null) {
			supervisionOutcome = (Code) new mojo.km.persistence.Reference(supervisionOutcomeId, Code.class, "SUPERVISION_OUTCOME").getObject();
		}
	}
	/**
	* set the type reference for class member facility
	*/
	public void setFacility(Code facility) {
		if (this.facility == null || !this.facility.equals(facility)) {
			markModified();
		}
		if (facility.getOID() == null) {
			new mojo.km.persistence.Home().bind(facility);
		}
		setFacilityId("" + facility.getOID());
		this.facility = (Code) new mojo.km.persistence.Reference(facility).getObject();
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getFacility() {
		fetch();
		initFacility();
		return facility;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initFacility() {
		if (facility == null) {
			facility = (Code) new mojo.km.persistence.Reference(facilityId, Code.class, PDCodeTableConstants.JUVENILE_DETENTION_FACILITY).getObject();
		}
	}
	/**
	* Initialize class relationship implementation for pd.juvenilecase.casefile.Response
	*/
	private void initResponses() {
		if (responses == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			try
			{
				responses =
					new mojo.km.persistence.ArrayList(
				    Response.class, "referenceId", "" + getOID());
			}
			catch (Throwable t)
			{
				responses = new java.util.ArrayList();
			}
		}
	}
	/**
	* insert a pd.juvenilecase.casefile.Response into class relationship collection.
	*/
	public void insertResponses(Response anObject) {
		initResponses();
		responses.add(anObject);
	}
	/**
	* Removes a pd.juvenilecase.casefile.Response from class relationship collection.
	*/
	public void removeResponses(Response anObject) {
		initResponses();
		responses.remove(anObject);
	}
	/**
	* Clears all pd.juvenilecase.casefile.Response from class relationship collection.
	*/
	public void clearResponses() {
		initResponses();
		responses.clear();
	}
	
	/**
	 * description: delete Response
	 */
	public void delecteResponses()
	{
		Collection collection = this.getResponses();
		if (collection != null && collection.size() > 0)
		{
			Iterator iter = collection.iterator();
			while (iter.hasNext())
			{
				Response response = (Response) iter.next();
				response.delete();
			}
		}
	}
	
	public void createOID()
	{
		IHome home = new Home();
		home.bind(this);
	}
	
	/**
	 * @param updateEvent
	 */
	public void setCasefileClosingDetails(UpdateCasefileClosingEvent updateEvent)
	{
		this.setClosingEvaluation(updateEvent.getClosingEvaluation());
		this.setSupervisionOutcomeId(updateEvent.getSupervisionOutcomeId());
		this.setSupervisionOutcomeDescriptionId(updateEvent.getSupervisionOutcomeDescriptionId() );
		this.setSupervisionNumber(updateEvent.getSupervisionNumber());
		this.setSupervisionEndDate(updateEvent.getSupervisionEndDate());
		this.setCasefileClosingStatus(updateEvent.getCasefileClosingStatus());
		this.setControllingReferralId(updateEvent.getControllingReferralId());
		this.setClosingComments(updateEvent.getClosingComments());
		this.setRejectionReason(updateEvent.getRejectionReason());
		this.setClosingPktGenerated(updateEvent.isClosingPktGenerated());
		this.setClosingLetterGenerated(updateEvent.isClosingLetterGenerated());
		if ( updateEvent.getJuvUnitId() != null
			&& updateEvent.getJuvUnitId().length() > 0 ) {
		    this.setJuvLocUnitId(updateEvent.getJuvUnitId());
		}
		if ( updateEvent.getRecordJuvUnit() != null){
		    this.setRecordJuvUnit(updateEvent.getRecordJuvUnit());
		}
		if ( updateEvent.getRecordCLM() != null){
		    this.setRecordCLM(updateEvent.getRecordCLM());
		}
		
		ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		String loginId = "";
		if(manager != null){
		    loginId = manager.getIUserInfo().getJIMSLogonId();
		}
		this.setCreateUserID(loginId);
	}
	
   /**
	* Sets the value of the createUserId property.
	* @param aCreateUserId the new value of the createUserId property
	*/
	public void setCreatorUserId(String aCreateUserId) {
		if (this.createUserId == null || !this.createUserId.equals(aCreateUserId)) {
			markModified();
		}
		createUserId = aCreateUserId;
	}

	
	public String getCreatorUserId()
	{
		fetch();
		return createUserId;
		
	}
	
	/**
	 * @param updateEvent
	 */
	public void setResidentialExitPlanDetails(UpdateResidentialExitPlanEvent updateEvent)
	{
		this.setFacilityReleaseReasonId(updateEvent.getFacilityReleaseReasonId());
		this.setPermanencyPlanId(updateEvent.getPermanencyPlanId());
		this.setFacilityId(updateEvent.getFacilityId());
		this.setLevelOfCareId(updateEvent.getLevelOfCareId());
		this.setSpecialNotes(updateEvent.getSpecialNotes());
		this.setExitPlanTemplateLocation(updateEvent.getExitPlanTemplateLocation());
		this.setExpectedReleaseDate(updateEvent.getExpectedReleaseDate());
	}
	
	/**
	 * @param updateEvent
	 */
	public void setCourtExitPlanDetails(UpdateCourtExitPlanEvent updateEvent)
	{
		this.setPetitionNumber(updateEvent.getPetitionNumber());
		this.setExitPlanTemplateLocation(updateEvent.getExitPlanTemplateLocation());
	}

	/**
	 * @return Returns the closingPktGenerated.
	 */
	public boolean isClosingPktGenerated() {
		return closingPktGenerated;
	}
	/**
	 * @param closingPktGenerated The closingPktGenerated to set.
	 */
	public void setClosingPktGenerated(boolean closingPktGenerated) {
		this.closingPktGenerated = closingPktGenerated;
	}
	
	/**
	 * @return Returns the closingLetterGenerated.
	 */
	public boolean isClosingLetterGenerated() {
		return closingLetterGenerated;
	}
	/**
	 * @param closingLetterGenerated The closingLetterGenerated to set.
	 */
	public void setClosingLetterGenerated(boolean closingLetterGenerated) {
		this.closingLetterGenerated = closingLetterGenerated;
	}
	public String getJuvLocUnitId()
	{
	    fetch();
	    return juvLocUnitId;
	}
	public void setJuvLocUnitId(String juvLocUnitId)
	{
	    if ( this.juvLocUnitId == null
		    || !this.juvLocUnitId.equals(juvLocUnitId)  ) {
		markModified();
	    }
	    this.juvLocUnitId = juvLocUnitId;
	}
	public String getRecordJuvUnit()
	{
	    fetch();
	    return recordJuvUnit;
	}
	public void setRecordJuvUnit(String recordJuvUnit)
	{
	    if ( this.recordJuvUnit == null
		    || !this.recordJuvUnit.equals(recordJuvUnit)  ) {
		markModified();
	    }
	    this.recordJuvUnit = recordJuvUnit;
	}
	public String getRecordCLM()
	{
	    fetch();
	    return recordCLM;
	}
	public void setRecordCLM(String recordCLM)
	{
	    if ( this.recordCLM == null
		    || !this.recordCLM.equals(recordCLM)  ) {
		markModified();
	    }
	    this.recordCLM = recordCLM;
	}
	
	
}
