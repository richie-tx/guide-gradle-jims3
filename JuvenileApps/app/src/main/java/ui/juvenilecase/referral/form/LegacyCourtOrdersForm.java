package ui.juvenilecase.referral.form;

import java.util.Collection;
import java.util.Date;

import naming.UIConstants;
import org.apache.struts.action.ActionForm;

public class LegacyCourtOrdersForm extends ActionForm{
	private String action ;
	private boolean angerManagementCounseling;
	private boolean anyOtherRulesSetByHCJPD;
	private String attorneyFee;
	private Date birthDate;
	private boolean cavgsConduct;
	private String childRemovedFrom;
	private String childSupport;
	private String cjpoInstructions;
	private Date commitmentDate;
	private boolean committedToTYC;
	private String communityService;
	private Date continuouslyDetainedDate;
	private String courtCost;
	private boolean courtCostWaived;
	private Date courtDate;
	private String courtDateStr;
	private String courtOrderID;
	private boolean custodyToCJPO;
	private boolean custodyToFather;
	private boolean custodyToGuardian;
	private boolean custodyToMHMRA;
	private boolean custodyToMother;
	private boolean custodyToOther;
	private boolean custodyToParents;
	private boolean custodyToRelative;
	private boolean custodyToTDPRS;
	private int daysInDetention;
	private boolean deadlyWeapon;
	private String deadlyWeaponDesc;
	private boolean deferredSORegistration;
	private String degree;
	private boolean delinquentConduct;
	private boolean denyTDL;
	private boolean determinatePlacement;
	private boolean determinateProbation;
	private String determinateReason1;
	private String determinateReason2;
	private String determinateReason3;
	private String determinateReason4;
	private String determinateReason5;
	private Date dispositionDate;
	private boolean drugFreeYouth;
	private boolean educationalSpecialist;
	private boolean familyManagementCounseling;
	private boolean felony1;
	private boolean felony2;
	private boolean felony3;
	private boolean felonyCapital;
	private boolean felonyStateJail;
	private boolean fireSetters;
	private boolean gangCaseload;
	private boolean gangWorkshop;
	private boolean gedProgram;
	private Date gjApprovalDate;
	private boolean gjApproved;
	private boolean gjBeyondReasonableDoubt;
	private String gjChildsAge;
	private boolean gjDelinquentConduct;
	private String gjNumber;
	private boolean gjStateReferral;
	private Date gjWaiverDate;
	private String hearingTypeCode;
	private String hearingTypeDescription;
	private boolean individualManagementCounseling;
	private boolean inNeedOfRehab;
	private boolean inNeedOfSupervision;
	private boolean intensiveSupervisionProgram;
	private String intensiveSupervisionProgramTimeFrame;
	private boolean jointCustody;
	private Date judgementDate;
	private Date judgementTYCDate;
	private String judgeName;
	private String juvenileCourt;
	private String juvenileCourtName;
	private String juvenileOffenseCode;
	private String juvenileOffenseCodeDescription;
	private boolean juvenileProbationPlacement;
	private boolean letterOfApology;
	private boolean mentor;
	private boolean mhmraAssessment;
	private boolean misdemeanorA;
	private boolean misdemeanorB;
	private boolean misdemeanorC;
	private boolean noContactCoActors;
	private boolean noContactComplainant;
	private boolean noContactGangMembers;
	private boolean noDelinquentConduct;
	private boolean noDispositionNecessary;
	private Date offenseDate;
	private String otherRulesSetByHCJPD;
	private String parentGuardianCustodian;
	private boolean peerPressure;
	private String petitionNum;
	private boolean placementOutsideHome;
	private boolean previousRulesInEffect;
	private boolean priorRestitutionEnded;
	private Date probationBeginDate;
	private Date probationEndDate;
	private boolean probationExtended;
	private boolean probationRevoked;
	private boolean randomDrugScreens;
	private boolean reasonableEffortsMade;
	private String respondentAttorneyName;
	private String responsiblePartyAttorneyFee;
	private String responsiblePartyChildSupport;
	private String responsiblePartyCourtCost;
	private String responsiblePartyRestitution;
	private String responsiblePartyRestitution2;
	private String responsiblePartySupervisoryFee;
	private String restitutionAmountOrdered;
	private String restitutionAmountOrdered2;
	private String restitutionPayeeCity;
	private String restitutionPayeeCity2;
	private String restitutionPayeeFirstName;
	private String restitutionPayeeFirstName2;
	private String restitutionPayeeLastName;
	private String restitutionPayeeLastName2;
	private String restitutionPayeePhoneNum;
	private String restitutionPayeePhoneNum2;
	private String restitutionPayeeState;
	private String restitutionPayeeState2;
	private String restitutionPayeeStreetAddress;
	private String restitutionPayeeStreetAddress2;
	private String restitutionPayeeZipCode;
	private String restitutionPayeeZipCode2;
	private String restitutionPaymentTimeFrame;
	private String restitutionPaymentTimeFrame2;
	private Date restitutionStartDate;
	private Date restitutionStartDate2;
	private String restitutionTotal;
	private String restitutionTotal2;
	private boolean sexOffenderBloodSample;
	private boolean sexOffenderCounseling;
	private boolean sexOffenderPolygraph;
	private boolean sexOffenderRegistration;
	private boolean sexOffenderRegistrationNonPublic;
	private String stateAttorneyName;
	private boolean statusOffense;
	private String supervisoryFee;
	private boolean tdcOutreach;
	private boolean technicalViolation;
	private boolean thumbprintOrdered;
	private boolean titleIIIChild;
	private String txDLRestrictions;
	private boolean tycDetention;
	private String tycSentenceLength;
	private boolean violatedCourtOrders;
	private String weekdayCurfewTimes;
	private String weekendCurfewHours;
	
	private Collection courtOrders;
	
	
	public void clear()
	{
		hearingTypeCode = null ;
		hearingTypeDescription = null ;
		action = UIConstants.EMPTY_STRING ;
	}


	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}


	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}


	/**
	 * @return the courtDate
	 */
	public Date getCourtDate() {
		return courtDate;
	}


	/**
	 * @param courtDate the courtDate to set
	 */
	public void setCourtDate(Date courtDate) {
		this.courtDate = courtDate;
	}


	/**
	 * @return the hearingTypeCode
	 */
	public String getHearingTypeCode() {
		return hearingTypeCode;
	}


	/**
	 * @param hearingTypeCode the hearingTypeCode to set
	 */
	public void setHearingTypeCode(String hearingTypeCode) {
		this.hearingTypeCode = hearingTypeCode;
	}


	/**
	 * @return the hearingTypeDescription
	 */
	public String getHearingTypeDescription() {
		return hearingTypeDescription;
	}


	/**
	 * @param hearingTypeDescription the hearingTypeDescription to set
	 */
	public void setHearingTypeDescription(String hearingTypeDescription) {
		this.hearingTypeDescription = hearingTypeDescription;
	}


	/**
	 * @return the juvenileCourt
	 */
	public String getJuvenileCourt() {
		return juvenileCourt;
	}


	/**
	 * @param juvenileCourt the juvenileCourt to set
	 */
	public void setJuvenileCourt(String juvenileCourt) {
		this.juvenileCourt = juvenileCourt;
	}


	/**
	 * @return the juvenileCourtName
	 */
	public String getJuvenileCourtName() {
		return juvenileCourtName;
	}


	/**
	 * @param juvenileCourtName the juvenileCourtName to set
	 */
	public void setJuvenileCourtName(String juvenileCourtName) {
		this.juvenileCourtName = juvenileCourtName;
	}


	/**
	 * @return the juvenileOffenseCode
	 */
	public String getJuvenileOffenseCode() {
		return juvenileOffenseCode;
	}


	/**
	 * @param juvenileOffenseCode the juvenileOffenseCode to set
	 */
	public void setJuvenileOffenseCode(String juvenileOffenseCode) {
		this.juvenileOffenseCode = juvenileOffenseCode;
	}


	/**
	 * @return the juvenileOffenseCodeDescription
	 */
	public String getJuvenileOffenseCodeDescription() {
		return juvenileOffenseCodeDescription;
	}


	/**
	 * @param juvenileOffenseCodeDescription the juvenileOffenseCodeDescription to set
	 */
	public void setJuvenileOffenseCodeDescription(
			String juvenileOffenseCodeDescription) {
		this.juvenileOffenseCodeDescription = juvenileOffenseCodeDescription;
	}


	/**
	 * @return the respondentAttorneyName
	 */
	public String getRespondentAttorneyName() {
		return respondentAttorneyName;
	}


	/**
	 * @param respondentAttorneyName the respondentAttorneyName to set
	 */
	public void setRespondentAttorneyName(String respondentAttorneyName) {
		this.respondentAttorneyName = respondentAttorneyName;
	}


	/**
	 * @return the angerManagementCounseling
	 */
	public boolean isAngerManagementCounseling() {
		return angerManagementCounseling;
	}


	/**
	 * @param angerManagementCounseling the angerManagementCounseling to set
	 */
	public void setAngerManagementCounseling(boolean angerManagementCounseling) {
		this.angerManagementCounseling = angerManagementCounseling;
	}


	/**
	 * @return the anyOtherRulesSetByHCJPD
	 */
	public boolean isAnyOtherRulesSetByHCJPD() {
		return anyOtherRulesSetByHCJPD;
	}


	/**
	 * @param anyOtherRulesSetByHCJPD the anyOtherRulesSetByHCJPD to set
	 */
	public void setAnyOtherRulesSetByHCJPD(boolean anyOtherRulesSetByHCJPD) {
		this.anyOtherRulesSetByHCJPD = anyOtherRulesSetByHCJPD;
	}


	/**
	 * @return the attorneyFee
	 */
	public String getAttorneyFee() {
		return attorneyFee;
	}


	/**
	 * @param attorneyFee the attorneyFee to set
	 */
	public void setAttorneyFee(String attorneyFee) {
		this.attorneyFee = attorneyFee;
	}


	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}


	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	/**
	 * @return the cAvgSConduct
	 */
	public boolean getCavgsConduct() {
		return cavgsConduct;
	}


	/**
	 * @param avgSConduct the cAvgSConduct to set
	 */
	public void setCavgsConduct(boolean cavgsConduct) {
		this.cavgsConduct = cavgsConduct;
	}


	/**
	 * @return the childRemovedFrom
	 */
	public String getChildRemovedFrom() {
		return childRemovedFrom;
	}


	/**
	 * @param childRemovedFrom the childRemovedFrom to set
	 */
	public void setChildRemovedFrom(String childRemovedFrom) {
		this.childRemovedFrom = childRemovedFrom;
	}


	/**
	 * @return the childSupport
	 */
	public String getChildSupport() {
		return childSupport;
	}


	/**
	 * @param childSupport the childSupport to set
	 */
	public void setChildSupport(String childSupport) {
		this.childSupport = childSupport;
	}


	/**
	 * @return the cjpoInstructions
	 */
	public String getCjpoInstructions() {
		return cjpoInstructions;
	}


	/**
	 * @param string the cjpoInstructions to set
	 */
	public void setCjpoInstructions(String string) {
		this.cjpoInstructions = string;
	}


	/**
	 * @return the commitmentDate
	 */
	public Date getCommitmentDate() {
		return commitmentDate;
	}


	/**
	 * @param commitmentDate the commitmentDate to set
	 */
	public void setCommitmentDate(Date commitmentDate) {
		this.commitmentDate = commitmentDate;
	}


	/**
	 * @return the committedToTYC
	 */
	public boolean getCommittedToTYC() {
		return committedToTYC;
	}


	/**
	 * @param committedToTYC the committedToTYC to set
	 */
	public void setCommittedToTYC(boolean committedToTYC) {
		this.committedToTYC = committedToTYC;
	}


	/**
	 * @return the communityService
	 */
	public String getCommunityService() {
		return communityService;
	}


	/**
	 * @param communityService the communityService to set
	 */
	public void setCommunityService(String communityService) {
		this.communityService = communityService;
	}


	/**
	 * @return the continuouslyDetainedDate
	 */
	public Date getContinuouslyDetainedDate() {
		return continuouslyDetainedDate;
	}


	/**
	 * @param continuouslyDetainedDate the continuouslyDetainedDate to set
	 */
	public void setContinuouslyDetainedDate(Date continuouslyDetainedDate) {
		this.continuouslyDetainedDate = continuouslyDetainedDate;
	}


	/**
	 * @return the courtCost
	 */
	public String getCourtCost() {
		return courtCost;
	}


	/**
	 * @param courtCost the courtCost to set
	 */
	public void setCourtCost(String courtCost) {
		this.courtCost = courtCost;
	}


	/**
	 * @return the courtCostWaived
	 */
	public boolean getCourtCostWaived() {
		return courtCostWaived;
	}


	/**
	 * @param courtCostWaived the courtCostWaived to set
	 */
	public void setCourtCostWaived(boolean courtCostWaived) {
		this.courtCostWaived = courtCostWaived;
	}


	/**
	 * @return the courtOrderNumber
	 */
	public String getCourtOrderID() {
		return courtOrderID;
	}


	/**
	 * @param courtOrderNumber the courtOrderNumber to set
	 */
	public void setCourtOrderID(String courtOrderID) {
		this.courtOrderID = courtOrderID;
	}


	/**
	 * @return the custodyToCJPO
	 */
	public boolean getCustodyToCJPO() {
		return custodyToCJPO;
	}


	/**
	 * @param custodyToCJPO the custodyToCJPO to set
	 */
	public void setCustodyToCJPO(boolean custodyToCJPO) {
		this.custodyToCJPO = custodyToCJPO;
	}


	/**
	 * @return the custodyToFather
	 */
	public boolean getCustodyToFather() {
		return custodyToFather;
	}


	/**
	 * @param custodyToFather the custodyToFather to set
	 */
	public void setCustodyToFather(boolean custodyToFather) {
		this.custodyToFather = custodyToFather;
	}


	/**
	 * @return the custodyToGuardian
	 */
	public boolean getCustodyToGuardian() {
		return custodyToGuardian;
	}


	/**
	 * @param custodyToGuardian the custodyToGuardian to set
	 */
	public void setCustodyToGuardian(boolean custodyToGuardian) {
		this.custodyToGuardian = custodyToGuardian;
	}


	/**
	 * @return the custodyToMHMRA
	 */
	public boolean getCustodyToMHMRA() {
		return custodyToMHMRA;
	}


	/**
	 * @param custodyToMHMRA the custodyToMHMRA to set
	 */
	public void setCustodyToMHMRA(boolean custodyToMHMRA) {
		this.custodyToMHMRA = custodyToMHMRA;
	}


	/**
	 * @return the custodyToMother
	 */
	public boolean getCustodyToMother() {
		return custodyToMother;
	}


	/**
	 * @param custodyToMother the custodyToMother to set
	 */
	public void setCustodyToMother(boolean custodyToMother) {
		this.custodyToMother = custodyToMother;
	}


	/**
	 * @return the custodyToOther
	 */
	public boolean getCustodyToOther() {
		return custodyToOther;
	}


	/**
	 * @param custodyToOther the custodyToOther to set
	 */
	public void setCustodyToOther(boolean custodyToOther) {
		this.custodyToOther = custodyToOther;
	}


	/**
	 * @return the custodyToParents
	 */
	public boolean getCustodyToParents() {
		return custodyToParents;
	}


	/**
	 * @param custodyToParents the custodyToParents to set
	 */
	public void setCustodyToParents(boolean custodyToParents) {
		this.custodyToParents = custodyToParents;
	}


	/**
	 * @return the custodyToRelative
	 */
	public boolean getCustodyToRelative() {
		return custodyToRelative;
	}


	/**
	 * @param custodyToRelative the custodyToRelative to set
	 */
	public void setCustodyToRelative(boolean custodyToRelative) {
		this.custodyToRelative = custodyToRelative;
	}


	/**
	 * @return the custodyToTDPRS
	 */
	public boolean getCustodyToTDPRS() {
		return custodyToTDPRS;
	}


	/**
	 * @param custodyToTDPRS the custodyToTDPRS to set
	 */
	public void setCustodyToTDPRS(boolean custodyToTDPRS) {
		this.custodyToTDPRS = custodyToTDPRS;
	}


	/**
	 * @return the daysInDetention
	 */
	public int getDaysInDetention() {
		return daysInDetention;
	}


	/**
	 * @param daysInDetention the daysInDetention to set
	 */
	public void setDaysInDetention(int daysInDetention) {
		this.daysInDetention = daysInDetention;
	}


	/**
	 * @return the deadlyWeapon
	 */
	public boolean getDeadlyWeapon() {
		return deadlyWeapon;
	}


	/**
	 * @param deadlyWeapon the deadlyWeapon to set
	 */
	public void setDeadlyWeapon(boolean deadlyWeapon) {
		this.deadlyWeapon = deadlyWeapon;
	}


	/**
	 * @return the deadlyweaponDesc
	 */
	public String getDeadlyWeaponDesc() {
		return deadlyWeaponDesc;
	}


	/**
	 * @param deadlyweaponDesc the deadlyweaponDesc to set
	 */
	public void setDeadlyWeaponDesc(String deadlyWeaponDesc) {
		this.deadlyWeaponDesc = deadlyWeaponDesc;
	}


	/**
	 * @return the deferredSORegistration
	 */
	public boolean getDeferredSORegistration() {
		return deferredSORegistration;
	}


	/**
	 * @param deferredSORegistration the deferredSORegistration to set
	 */
	public void setDeferredSORegistration(boolean deferredSORegistration) {
		this.deferredSORegistration = deferredSORegistration;
	}


	/**
	 * @return the delinquentConduct
	 */
	public boolean getDelinquentConduct() {
		return delinquentConduct;
	}


	/**
	 * @param delinquentConduct the delinquentConduct to set
	 */
	public void setDelinquentConduct(boolean delinquentConduct) {
		this.delinquentConduct = delinquentConduct;
	}


	/**
	 * @return the denyTDL
	 */
	public boolean getDenyTDL() {
		return denyTDL;
	}


	/**
	 * @param denyTDL the denyTDL to set
	 */
	public void setDenyTDL(boolean denyTDL) {
		this.denyTDL = denyTDL;
	}


	/**
	 * @return the determinatePlacement
	 */
	public boolean getDeterminatePlacement() {
		return determinatePlacement;
	}


	/**
	 * @param determinatePlacement the determinatePlacement to set
	 */
	public void setDeterminatePlacement(boolean determinatePlacement) {
		this.determinatePlacement = determinatePlacement;
	}


	/**
	 * @return the determinateProbation
	 */
	public boolean getDeterminateProbation() {
		return determinateProbation;
	}


	/**
	 * @param determinateProbation the determinateProbation to set
	 */
	public void setDeterminateProbation(boolean determinateProbation) {
		this.determinateProbation = determinateProbation;
	}


	/**
	 * @return the determinateReason1
	 */
	public String getDeterminateReason1() {
		return determinateReason1;
	}


	/**
	 * @param determinateReason1 the determinateReason1 to set
	 */
	public void setDeterminateReason1(String determinateReason1) {
		this.determinateReason1 = determinateReason1;
	}


	/**
	 * @return the determinateReason2
	 */
	public String getDeterminateReason2() {
		return determinateReason2;
	}


	/**
	 * @param determinateReason2 the determinateReason2 to set
	 */
	public void setDeterminateReason2(String determinateReason2) {
		this.determinateReason2 = determinateReason2;
	}


	/**
	 * @return the determinateReason3
	 */
	public String getDeterminateReason3() {
		return determinateReason3;
	}


	/**
	 * @param determinateReason3 the determinateReason3 to set
	 */
	public void setDeterminateReason3(String determinateReason3) {
		this.determinateReason3 = determinateReason3;
	}


	/**
	 * @return the determinateReason4
	 */
	public String getDeterminateReason4() {
		return determinateReason4;
	}


	/**
	 * @param determinateReason4 the determinateReason4 to set
	 */
	public void setDeterminateReason4(String determinateReason4) {
		this.determinateReason4 = determinateReason4;
	}


	/**
	 * @return the determinateReason5
	 */
	public String getDeterminateReason5() {
		return determinateReason5;
	}


	/**
	 * @param determinateReason5 the determinateReason5 to set
	 */
	public void setDeterminateReason5(String determinateReason5) {
		this.determinateReason5 = determinateReason5;
	}


	/**
	 * @return the dispositionDate
	 */
	public Date getDispositionDate() {
		return dispositionDate;
	}


	/**
	 * @param dispositionDate the dispositionDate to set
	 */
	public void setDispositionDate(Date dispositionDate) {
		this.dispositionDate = dispositionDate;
	}


	/**
	 * @return the drugFreeYouth
	 */
	public boolean getDrugFreeYouth() {
		return drugFreeYouth;
	}


	/**
	 * @param drugFreeYouth the drugFreeYouth to set
	 */
	public void setDrugFreeYouth(boolean drugFreeYouth) {
		this.drugFreeYouth = drugFreeYouth;
	}


	/**
	 * @return the educationalSpecialist
	 */
	public boolean getEducationalSpecialist() {
		return educationalSpecialist;
	}


	/**
	 * @param educationalSpecialist the educationalSpecialist to set
	 */
	public void setEducationalSpecialist(boolean educationalSpecialist) {
		this.educationalSpecialist = educationalSpecialist;
	}


	/**
	 * @return the familyManagementCounseling
	 */
	public boolean getFamilyManagementCounseling() {
		return familyManagementCounseling;
	}


	/**
	 * @param familyManagementCounseling the familyManagementCounseling to set
	 */
	public void setFamilyManagementCounseling(boolean familyManagementCounseling) {
		this.familyManagementCounseling = familyManagementCounseling;
	}


	/**
	 * @return the felony1
	 */
	public boolean getFelony1() {
		return felony1;
	}


	/**
	 * @param felony1 the felony1 to set
	 */
	public void setFelony1(boolean felony1) {
		this.felony1 = felony1;
	}


	/**
	 * @return the felony2
	 */
	public boolean getFelony2() {
		return felony2;
	}


	/**
	 * @param felony2 the felony2 to set
	 */
	public void setFelony2(boolean felony2) {
		this.felony2 = felony2;
	}


	/**
	 * @return the felony3
	 */
	public boolean getFelony3() {
		return felony3;
	}


	/**
	 * @param felony3 the felony3 to set
	 */
	public void setFelony3(boolean felony3) {
		this.felony3 = felony3;
	}


	/**
	 * @return the felonyCapital
	 */
	public boolean getFelonyCapital() {
		return felonyCapital;
	}


	/**
	 * @param felonyCapital the felonyCapital to set
	 */
	public void setFelonyCapital(boolean felonyCapital) {
		this.felonyCapital = felonyCapital;
	}


	/**
	 * @return the felonyStateJail
	 */
	public boolean getFelonyStateJail() {
		return felonyStateJail;
	}


	/**
	 * @param felonyStateJail the felonyStateJail to set
	 */
	public void setFelonyStateJail(boolean felonyStateJail) {
		this.felonyStateJail = felonyStateJail;
	}


	/**
	 * @return the fireSetters
	 */
	public boolean getFireSetters() {
		return fireSetters;
	}


	/**
	 * @param fireSetters the fireSetters to set
	 */
	public void setFireSetters(boolean fireSetters) {
		this.fireSetters = fireSetters;
	}


	/**
	 * @return the gangCaseload
	 */
	public boolean getGangCaseload() {
		return gangCaseload;
	}


	/**
	 * @param gangCaseload the gangCaseload to set
	 */
	public void setGangCaseload(boolean gangCaseload) {
		this.gangCaseload = gangCaseload;
	}


	/**
	 * @return the gangWorkshop
	 */
	public boolean getGangWorkshop() {
		return gangWorkshop;
	}


	/**
	 * @param gangWorkshop the gangWorkshop to set
	 */
	public void setGangWorkshop(boolean gangWorkshop) {
		this.gangWorkshop = gangWorkshop;
	}


	/**
	 * @return the gedProgram
	 */
	public boolean getGedProgram() {
		return gedProgram;
	}


	/**
	 * @param gedProgram the gedProgram to set
	 */
	public void setGedProgram(boolean gedProgram) {
		this.gedProgram = gedProgram;
	}


	/**
	 * @return the gjApprovalDate
	 */
	public Date getGjApprovalDate() {
		return gjApprovalDate;
	}


	/**
	 * @param gjApprovalDate the gjApprovalDate to set
	 */
	public void setGjApprovalDate(Date gjApprovalDate) {
		this.gjApprovalDate = gjApprovalDate;
	}


	/**
	 * @return the gjApproved
	 */
	public boolean getGjApproved() {
		return gjApproved;
	}


	/**
	 * @param gjApproved the gjApproved to set
	 */
	public void setGjApproved(boolean gjApproved) {
		this.gjApproved = gjApproved;
	}


	/**
	 * @return the gjBeyondReasonableDoubt
	 */
	public boolean getGjBeyondReasonableDoubt() {
		return gjBeyondReasonableDoubt;
	}


	/**
	 * @param gjBeyondReasonableDoubt the gjBeyondReasonableDoubt to set
	 */
	public void setGjBeyondReasonableDoubt(boolean gjBeyondReasonableDoubt) {
		this.gjBeyondReasonableDoubt = gjBeyondReasonableDoubt;
	}


	/**
	 * @return the gjChildsAge
	 */
	public String getGjChildsAge() {
		return gjChildsAge;
	}


	/**
	 * @param gjChildsAge the gjChildsAge to set
	 */
	public void setGjChildsAge(String gjChildsAge) {
		this.gjChildsAge = gjChildsAge;
	}


	/**
	 * @return the gjDelinquentConduct
	 */
	public boolean getGjDelinquentConduct() {
		return gjDelinquentConduct;
	}


	/**
	 * @param gjDelinquentConduct the gjDelinquentConduct to set
	 */
	public void setGjDelinquentConduct(boolean gjDelinquentConduct) {
		this.gjDelinquentConduct = gjDelinquentConduct;
	}


	/**
	 * @return the gjNumber
	 */
	public String getGjNumber() {
		return gjNumber;
	}


	/**
	 * @param gjNumber the gjNumber to set
	 */
	public void setGjNumber(String gjNumber) {
		this.gjNumber = gjNumber;
	}


	/**
	 * @return the gjStateReferral
	 */
	public boolean getGjStateReferral() {
		return gjStateReferral;
	}


	/**
	 * @param gjStateReferral the gjStateReferral to set
	 */
	public void setGjStateReferral(boolean gjStateReferral) {
		this.gjStateReferral = gjStateReferral;
	}


	/**
	 * @return the gjWaiverDate
	 */
	public Date getGjWaiverDate() {
		return gjWaiverDate;
	}


	/**
	 * @param gjWaiverDate the gjWaiverDate to set
	 */
	public void setGjWaiverDate(Date gjWaiverDate) {
		this.gjWaiverDate = gjWaiverDate;
	}


	/**
	 * @return the individualManagementCounseling
	 */
	public boolean getIndividualManagementCounseling() {
		return individualManagementCounseling;
	}


	/**
	 * @param individualManagementCounseling the individualManagementCounseling to set
	 */
	public void setIndividualManagementCounseling(
			boolean individualManagementCounseling) {
		this.individualManagementCounseling = individualManagementCounseling;
	}


	/**
	 * @return the inNeedOfRehab
	 */
	public boolean getInNeedOfRehab() {
		return inNeedOfRehab;
	}


	/**
	 * @param inNeedOfRehab the inNeedOfRehab to set
	 */
	public void setInNeedOfRehab(boolean inNeedOfRehab) {
		this.inNeedOfRehab = inNeedOfRehab;
	}


	/**
	 * @return the inNeedOfSupervision
	 */
	public boolean getInNeedOfSupervision() {
		return inNeedOfSupervision;
	}


	/**
	 * @param inNeedOfSupervision the inNeedOfSupervision to set
	 */
	public void setInNeedOfSupervision(boolean inNeedOfSupervision) {
		this.inNeedOfSupervision = inNeedOfSupervision;
	}


	/**
	 * @return the intensiveSupervisionProgram
	 */
	public boolean getIntensiveSupervisionProgram() {
		return intensiveSupervisionProgram;
	}


	/**
	 * @param intensiveSupervisionProgram the intensiveSupervisionProgram to set
	 */
	public void setIntensiveSupervisionProgram(boolean intensiveSupervisionProgram) {
		this.intensiveSupervisionProgram = intensiveSupervisionProgram;
	}


	/**
	 * @return the intensiveSupervisionProgramTimeFrame
	 */
	public String getIntensiveSupervisionProgramTimeFrame() {
		return intensiveSupervisionProgramTimeFrame;
	}


	/**
	 * @param intensiveSupervisionProgramTimeFrame the intensiveSupervisionProgramTimeFrame to set
	 */
	public void setIntensiveSupervisionProgramTimeFrame(
			String intensiveSupervisionProgramTimeFrame) {
		this.intensiveSupervisionProgramTimeFrame = intensiveSupervisionProgramTimeFrame;
	}


	/**
	 * @return the jointCustody
	 */
	public boolean getJointCustody() {
		return jointCustody;
	}


	/**
	 * @param jointCustody the jointCustody to set
	 */
	public void setJointCustody(boolean jointCustody) {
		this.jointCustody = jointCustody;
	}


	/**
	 * @return the judgementDate
	 */
	public Date getJudgementDate() {
		return judgementDate;
	}


	/**
	 * @param judgementDate the judgementDate to set
	 */
	public void setJudgementDate(Date judgementDate) {
		this.judgementDate = judgementDate;
	}


	/**
	 * @return the judgementTYCDate
	 */
	public Date getJudgementTYCDate() {
		return judgementTYCDate;
	}


	/**
	 * @param judgementTYCDate the judgementTYCDate to set
	 */
	public void setJudgementTYCDate(Date judgementTYCDate) {
		this.judgementTYCDate = judgementTYCDate;
	}


	/**
	 * @return the judgeName
	 */
	public String getJudgeName() {
		return judgeName;
	}


	/**
	 * @param judgeName the judgeName to set
	 */
	public void setJudgeName(String judgeName) {
		this.judgeName = judgeName;
	}


	/**
	 * @return the juvenileProbationPlacement
	 */
	public boolean getJuvenileProbationPlacement() {
		return juvenileProbationPlacement;
	}


	/**
	 * @param juvenileProbationPlacement the juvenileProbationPlacement to set
	 */
	public void setJuvenileProbationPlacement(boolean juvenileProbationPlacement) {
		this.juvenileProbationPlacement = juvenileProbationPlacement;
	}


	/**
	 * @return the letterOfApology
	 */
	public boolean getLetterOfApology() {
		return letterOfApology;
	}


	/**
	 * @param letterOfApology the letterOfApology to set
	 */
	public void setLetterOfApology(boolean letterOfApology) {
		this.letterOfApology = letterOfApology;
	}


	/**
	 * @return the mentor
	 */
	public boolean getMentor() {
		return mentor;
	}


	/**
	 * @param mentor the mentor to set
	 */
	public void setMentor(boolean mentor) {
		this.mentor = mentor;
	}


	/**
	 * @return the mhmraAssessment
	 */
	public boolean getMhmraAssessment() {
		return mhmraAssessment;
	}


	/**
	 * @param mhmraAssessment the mhmraAssessment to set
	 */
	public void setMhmraAssessment(boolean mhmraAssessment) {
		this.mhmraAssessment = mhmraAssessment;
	}


	/**
	 * @return the misdemeanorA
	 */
	public boolean getMisdemeanorA() {
		return misdemeanorA;
	}


	/**
	 * @param misdemeanorA the misdemeanorA to set
	 */
	public void setMisdemeanorA(boolean misdemeanorA) {
		this.misdemeanorA = misdemeanorA;
	}


	/**
	 * @return the misdemeanorB
	 */
	public boolean getMisdemeanorB() {
		return misdemeanorB;
	}


	/**
	 * @param misdemeanorB the misdemeanorB to set
	 */
	public void setMisdemeanorB(boolean misdemeanorB) {
		this.misdemeanorB = misdemeanorB;
	}


	/**
	 * @return the misdemeanorC
	 */
	public boolean getMisdemeanorC() {
		return misdemeanorC;
	}


	/**
	 * @param misdemeanorC the misdemeanorC to set
	 */
	public void setMisdemeanorC(boolean misdemeanorC) {
		this.misdemeanorC = misdemeanorC;
	}


	/**
	 * @return the noContactCoActors
	 */
	public boolean getNoContactCoActors() {
		return noContactCoActors;
	}


	/**
	 * @param noContactCoActors the noContactCoActors to set
	 */
	public void setNoContactCoActors(boolean noContactCoActors) {
		this.noContactCoActors = noContactCoActors;
	}


	/**
	 * @return the noContactComplainant
	 */
	public boolean getNoContactComplainant() {
		return noContactComplainant;
	}


	/**
	 * @param noContactComplainant the noContactComplainant to set
	 */
	public void setNoContactComplainant(boolean noContactComplainant) {
		this.noContactComplainant = noContactComplainant;
	}


	/**
	 * @return the noContactGangMembers
	 */
	public boolean getNoContactGangMembers() {
		return noContactGangMembers;
	}


	/**
	 * @param noContactGangMembers the noContactGangMembers to set
	 */
	public void setNoContactGangMembers(boolean noContactGangMembers) {
		this.noContactGangMembers = noContactGangMembers;
	}


	/**
	 * @return the noDelinquentConduct
	 */
	public boolean isNoDelinquentConduct() {
		return noDelinquentConduct;
	}


	/**
	 * @param noDelinquentConduct the noDelinquentConduct to set
	 */
	public void setNoDelinquentConduct(boolean noDelinquentConduct) {
		this.noDelinquentConduct = noDelinquentConduct;
	}


	/**
	 * @return the noDispositionNecessary
	 */
	public boolean getNoDispositionNecessary() {
		return noDispositionNecessary;
	}


	/**
	 * @param noDispositionNecessary the noDispositionNecessary to set
	 */
	public void setNoDispositionNecessary(boolean noDispositionNecessary) {
		this.noDispositionNecessary = noDispositionNecessary;
	}


	/**
	 * @return the offenseDate
	 */
	public Date getOffenseDate() {
		return offenseDate;
	}


	/**
	 * @param offenseDate the offenseDate to set
	 */
	public void setOffenseDate(Date offenseDate) {
		this.offenseDate = offenseDate;
	}


	/**
	 * @return the otherRulesSetByHCJPD
	 */
	public String getOtherRulesSetByHCJPD() {
		return otherRulesSetByHCJPD;
	}


	/**
	 * @param otherRulesSetByHCJPD the otherRulesSetByHCJPD to set
	 */
	public void setOtherRulesSetByHCJPD(String otherRulesSetByHCJPD) {
		this.otherRulesSetByHCJPD = otherRulesSetByHCJPD;
	}


	/**
	 * @return the parentGuardianCustodian
	 */
	public String getParentGuardianCustodian() {
		return parentGuardianCustodian;
	}


	/**
	 * @param parentGuardianCustodian the parentGuardianCustodian to set
	 */
	public void setParentGuardianCustodian(String parentGuardianCustodian) {
		this.parentGuardianCustodian = parentGuardianCustodian;
	}


	/**
	 * @return the peerPressure
	 */
	public boolean getPeerPressure() {
		return peerPressure;
	}


	/**
	 * @param peerPressure the peerPressure to set
	 */
	public void setPeerPressure(boolean peerPressure) {
		this.peerPressure = peerPressure;
	}


	/**
	 * @return the placementOutsideHome
	 */
	public boolean getPlacementOutsideHome() {
		return placementOutsideHome;
	}


	/**
	 * @param placementOutsideHome the placementOutsideHome to set
	 */
	public void setPlacementOutsideHome(boolean placementOutsideHome) {
		this.placementOutsideHome = placementOutsideHome;
	}


	/**
	 * @return the previousRulesInEffect
	 */
	public boolean getPreviousRulesInEffect() {
		return previousRulesInEffect;
	}


	/**
	 * @param previousRulesInEffect the previousRulesInEffect to set
	 */
	public void setPreviousRulesInEffect(boolean previousRulesInEffect) {
		this.previousRulesInEffect = previousRulesInEffect;
	}


	/**
	 * @return the priorRestitutioniEnded
	 */
	public boolean getPriorRestitutionEnded() {
		return priorRestitutionEnded;
	}


	/**
	 * @param priorRestitutioniEnded the priorRestitutioniEnded to set
	 */
	public void setPriorRestitutionEnded(boolean priorRestitutionEnded) {
		this.priorRestitutionEnded = priorRestitutionEnded;
	}


	/**
	 * @return the probationBeginDate
	 */
	public Date getProbationBeginDate() {
		return probationBeginDate;
	}


	/**
	 * @param probationBeginDate the probationBeginDate to set
	 */
	public void setProbationBeginDate(Date probationBeginDate) {
		this.probationBeginDate = probationBeginDate;
	}


	/**
	 * @return the probationEndDate
	 */
	public Date getProbationEndDate() {
		return probationEndDate;
	}

	/**
	 * @param probationEndDate the probationEndDate to set
	 */
	public void setProbationEndDate(Date probationEndDate) {
		this.probationEndDate = probationEndDate;
	}


	/**
	 * @return the probationExtended
	 */
	public boolean getProbationExtended() {
		return probationExtended;
	}


	/**
	 * @param probationExtended the probationExtended to set
	 */
	public void setProbationExtended(boolean probationExtended) {
		this.probationExtended = probationExtended;
	}


	/**
	 * @return the probationRevoked
	 */
	public boolean getProbationRevoked() {
		return probationRevoked;
	}


	/**
	 * @param probationRevoked the probationRevoked to set
	 */
	public void setProbationRevoked(boolean probationRevoked) {
		this.probationRevoked = probationRevoked;
	}


	/**
	 * @return the randomDrugScreens
	 */
	public boolean getRandomDrugScreens() {
		return randomDrugScreens;
	}


	/**
	 * @param randomDrugScreens the randomDrugScreens to set
	 */
	public void setRandomDrugScreens(boolean randomDrugScreens) {
		this.randomDrugScreens = randomDrugScreens;
	}


	/**
	 * @return the reasonableEffortsMade
	 */
	public boolean getReasonableEffortsMade() {
		return reasonableEffortsMade;
	}


	/**
	 * @param reasonableEffortsMade the reasonableEffortsMade to set
	 */
	public void setReasonableEffortsMade(boolean reasonableEffortsMade) {
		this.reasonableEffortsMade = reasonableEffortsMade;
	}


	/**
	 * @return the responsiblePartyAttorneyFee
	 */
	public String getResponsiblePartyAttorneyFee() {
		return responsiblePartyAttorneyFee;
	}


	/**
	 * @param responsiblePartyAttorneyFee the responsiblePartyAttorneyFee to set
	 */
	public void setResponsiblePartyAttorneyFee(String responsiblePartyAttorneyFee) {
		this.responsiblePartyAttorneyFee = responsiblePartyAttorneyFee;
	}


	/**
	 * @return the responsiblePartyChildSupport
	 */
	public String getResponsiblePartyChildSupport() {
		return responsiblePartyChildSupport;
	}


	/**
	 * @param responsiblePartyChildSupport the responsiblePartyChildSupport to set
	 */
	public void setResponsiblePartyChildSupport(String responsiblePartyChildSupport) {
		this.responsiblePartyChildSupport = responsiblePartyChildSupport;
	}


	/**
	 * @return the responsiblePartyCourtCost
	 */
	public String getResponsiblePartyCourtCost() {
		return responsiblePartyCourtCost;
	}


	/**
	 * @param responsiblePartyCourtCost the responsiblePartyCourtCost to set
	 */
	public void setResponsiblePartyCourtCost(String responsiblePartyCourtCost) {
		this.responsiblePartyCourtCost = responsiblePartyCourtCost;
	}


	/**
	 * @return the responsiblePartyRestitution
	 */
	public String getResponsiblePartyRestitution() {
		return responsiblePartyRestitution;
	}


	/**
	 * @param responsiblePartyRestitution the responsiblePartyRestitution to set
	 */
	public void setResponsiblePartyRestitution(String responsiblePartyRestitution) {
		this.responsiblePartyRestitution = responsiblePartyRestitution;
	}


	/**
	 * @return the responsiblePartyRestitution2
	 */
	public String getResponsiblePartyRestitution2() {
		return responsiblePartyRestitution2;
	}


	/**
	 * @param responsiblePartyRestitution2 the responsiblePartyRestitution2 to set
	 */
	public void setResponsiblePartyRestitution2(String responsiblePartyRestitution2) {
		this.responsiblePartyRestitution2 = responsiblePartyRestitution2;
	}


	/**
	 * @return the responsiblePartySupervisoryFee
	 */
	public String getResponsiblePartySupervisoryFee() {
		return responsiblePartySupervisoryFee;
	}


	/**
	 * @param responsiblePartySupervisoryFee the responsiblePartySupervisoryFee to set
	 */
	public void setResponsiblePartySupervisoryFee(
			String responsiblePartySupervisoryFee) {
		this.responsiblePartySupervisoryFee = responsiblePartySupervisoryFee;
	}


	/**
	 * @return the restitutionAmountOrdered
	 */
	public String getRestitutionAmountOrdered() {
		return restitutionAmountOrdered;
	}


	/**
	 * @param restitutionAmountOrdered the restitutionAmountOrdered to set
	 */
	public void setRestitutionAmountOrdered(String restitutionAmountOrdered) {
		this.restitutionAmountOrdered = restitutionAmountOrdered;
	}


	/**
	 * @return the restitutionAmountOrdered2
	 */
	public String getRestitutionAmountOrdered2() {
		return restitutionAmountOrdered2;
	}


	/**
	 * @param restitutionAmountOrdered2 the restitutionAmountOrdered2 to set
	 */
	public void setRestitutionAmountOrdered2(String restitutionAmountOrdered2) {
		this.restitutionAmountOrdered2 = restitutionAmountOrdered2;
	}


	/**
	 * @return the restitutionPayeeCity
	 */
	public String getRestitutionPayeeCity() {
		return restitutionPayeeCity;
	}


	/**
	 * @param restitutionPayeeCity the restitutionPayeeCity to set
	 */
	public void setRestitutionPayeeCity(String restitutionPayeeCity) {
		this.restitutionPayeeCity = restitutionPayeeCity;
	}


	/**
	 * @return the restitutionPayeeCity2
	 */
	public String getRestitutionPayeeCity2() {
		return restitutionPayeeCity2;
	}


	/**
	 * @param restitutionPayeeCity2 the restitutionPayeeCity2 to set
	 */
	public void setRestitutionPayeeCity2(String restitutionPayeeCity2) {
		this.restitutionPayeeCity2 = restitutionPayeeCity2;
	}


	/**
	 * @return the restitutionPayeeFirstName
	 */
	public String getRestitutionPayeeFirstName() {
		return restitutionPayeeFirstName;
	}


	/**
	 * @param restitutionPayeeFirstName the restitutionPayeeFirstName to set
	 */
	public void setRestitutionPayeeFirstName(String restitutionPayeeFirstName) {
		this.restitutionPayeeFirstName = restitutionPayeeFirstName;
	}


	/**
	 * @return the restitutionPayeeFirstName2
	 */
	public String getRestitutionPayeeFirstName2() {
		return restitutionPayeeFirstName2;
	}


	/**
	 * @param restitutionPayeeFirstName2 the restitutionPayeeFirstName2 to set
	 */
	public void setRestitutionPayeeFirstName2(String restitutionPayeeFirstName2) {
		this.restitutionPayeeFirstName2 = restitutionPayeeFirstName2;
	}


	/**
	 * @return the restitutionPayeeLastName
	 */
	public String getRestitutionPayeeLastName() {
		return restitutionPayeeLastName;
	}


	/**
	 * @param restitutionPayeeLastName the restitutionPayeeLastName to set
	 */
	public void setRestitutionPayeeLastName(String restitutionPayeeLastName) {
		this.restitutionPayeeLastName = restitutionPayeeLastName;
	}


	/**
	 * @return the restitutionPayeeLastName2
	 */
	public String getRestitutionPayeeLastName2() {
		return restitutionPayeeLastName2;
	}


	/**
	 * @param restitutionPayeeLastName2 the restitutionPayeeLastName2 to set
	 */
	public void setRestitutionPayeeLastName2(String restitutionPayeeLastName2) {
		this.restitutionPayeeLastName2 = restitutionPayeeLastName2;
	}


	/**
	 * @return the restitutionPayeePhoneNum
	 */
	public String getRestitutionPayeePhoneNum() {
		return restitutionPayeePhoneNum;
	}


	/**
	 * @param restitutionPayeePhoneNum the restitutionPayeePhoneNum to set
	 */
	public void setRestitutionPayeePhoneNum(String restitutionPayeePhoneNum) {
		this.restitutionPayeePhoneNum = restitutionPayeePhoneNum;
	}


	/**
	 * @return the restitutionPayeePhoneNum2
	 */
	public String getRestitutionPayeePhoneNum2() {
		return restitutionPayeePhoneNum2;
	}


	/**
	 * @param restitutionPayeePhoneNum2 the restitutionPayeePhoneNum2 to set
	 */
	public void setRestitutionPayeePhoneNum2(String restitutionPayeePhoneNum2) {
		this.restitutionPayeePhoneNum2 = restitutionPayeePhoneNum2;
	}


	/**
	 * @return the restitutionPayeeState
	 */
	public String getRestitutionPayeeState() {
		return restitutionPayeeState;
	}


	/**
	 * @param restitutionPayeeState the restitutionPayeeState to set
	 */
	public void setRestitutionPayeeState(String restitutionPayeeState) {
		this.restitutionPayeeState = restitutionPayeeState;
	}


	/**
	 * @return the restitutionPayeeState2
	 */
	public String getRestitutionPayeeState2() {
		return restitutionPayeeState2;
	}


	/**
	 * @param restitutionPayeeState2 the restitutionPayeeState2 to set
	 */
	public void setRestitutionPayeeState2(String restitutionPayeeState2) {
		this.restitutionPayeeState2 = restitutionPayeeState2;
	}


	/**
	 * @return the restitutionPayeeStreetAddress
	 */
	public String getRestitutionPayeeStreetAddress() {
		return restitutionPayeeStreetAddress;
	}


	/**
	 * @param restitutionPayeeStreetAddress the restitutionPayeeStreetAddress to set
	 */
	public void setRestitutionPayeeStreetAddress(
			String restitutionPayeeStreetAddress) {
		this.restitutionPayeeStreetAddress = restitutionPayeeStreetAddress;
	}


	/**
	 * @return the restitutionPayeeStreetAddress2
	 */
	public String getRestitutionPayeeStreetAddress2() {
		return restitutionPayeeStreetAddress2;
	}


	/**
	 * @param restitutionPayeeStreetAddress2 the restitutionPayeeStreetAddress2 to set
	 */
	public void setRestitutionPayeeStreetAddress2(
			String restitutionPayeeStreetAddress2) {
		this.restitutionPayeeStreetAddress2 = restitutionPayeeStreetAddress2;
	}


	/**
	 * @return the restitutionPayeeZipCode
	 */
	public String getRestitutionPayeeZipCode() {
		return restitutionPayeeZipCode;
	}


	/**
	 * @param restitutionPayeeZipCode the restitutionPayeeZipCode to set
	 */
	public void setRestitutionPayeeZipCode(String restitutionPayeeZipCode) {
		this.restitutionPayeeZipCode = restitutionPayeeZipCode;
	}


	/**
	 * @return the restitutionPayeeZipCode2
	 */
	public String getRestitutionPayeeZipCode2() {
		return restitutionPayeeZipCode2;
	}


	/**
	 * @param restitutionPayeeZipCode2 the restitutionPayeeZipCode2 to set
	 */
	public void setRestitutionPayeeZipCode2(String restitutionPayeeZipCode2) {
		this.restitutionPayeeZipCode2 = restitutionPayeeZipCode2;
	}


	/**
	 * @return the restitutionPaymentTimeFrame
	 */
	public String getRestitutionPaymentTimeFrame() {
		return restitutionPaymentTimeFrame;
	}


	/**
	 * @param restitutionPaymentTimeFrame the restitutionPaymentTimeFrame to set
	 */
	public void setRestitutionPaymentTimeFrame(String restitutionPaymentTimeFrame) {
		this.restitutionPaymentTimeFrame = restitutionPaymentTimeFrame;
	}


	/**
	 * @return the restitutionPaymentTimeFrame2
	 */
	public String getRestitutionPaymentTimeFrame2() {
		return restitutionPaymentTimeFrame2;
	}


	/**
	 * @param restitutionPaymentTimeFrame2 the restitutionPaymentTimeFrame2 to set
	 */
	public void setRestitutionPaymentTimeFrame2(String restitutionPaymentTimeFrame2) {
		this.restitutionPaymentTimeFrame2 = restitutionPaymentTimeFrame2;
	}


	/**
	 * @return the restitutionStartDate
	 */
	public Date getRestitutionStartDate() {
		return restitutionStartDate;
	}


	/**
	 * @param restitutionStartDate the restitutionStartDate to set
	 */
	public void setRestitutionStartDate(Date restitutionStartDate) {
		this.restitutionStartDate = restitutionStartDate;
	}


	/**
	 * @return the restitutionStartDate2
	 */
	public Date getRestitutionStartDate2() {
		return restitutionStartDate2;
	}


	/**
	 * @param restitutionStartDate2 the restitutionStartDate2 to set
	 */
	public void setRestitutionStartDate2(Date restitutionStartDate2) {
		this.restitutionStartDate2 = restitutionStartDate2;
	}


	/**
	 * @return the restitutionTotal
	 */
	public String getRestitutionTotal() {
		return restitutionTotal;
	}


	/**
	 * @param restitutionTotal the restitutionTotal to set
	 */
	public void setRestitutionTotal(String restitutionTotal) {
		this.restitutionTotal = restitutionTotal;
	}


	/**
	 * @return the restitutionTotal2
	 */
	public String getRestitutionTotal2() {
		return restitutionTotal2;
	}


	/**
	 * @param restitutionTotal2 the restitutionTotal2 to set
	 */
	public void setRestitutionTotal2(String restitutionTotal2) {
		this.restitutionTotal2 = restitutionTotal2;
	}


	/**
	 * @return the sexOffenderBloodSample
	 */
	public boolean getSexOffenderBloodSample() {
		return sexOffenderBloodSample;
	}


	/**
	 * @param sexOffenderBloodSample the sexOffenderBloodSample to set
	 */
	public void setSexOffenderBloodSample(boolean sexOffenderBloodSample) {
		this.sexOffenderBloodSample = sexOffenderBloodSample;
	}


	/**
	 * @return the sexOffenderCounseling
	 */
	public boolean getSexOffenderCounseling() {
		return sexOffenderCounseling;
	}


	/**
	 * @param sexOffenderCounseling the sexOffenderCounseling to set
	 */
	public void setSexOffenderCounseling(boolean sexOffenderCounseling) {
		this.sexOffenderCounseling = sexOffenderCounseling;
	}


	/**
	 * @return the sexOffenderPolygraph
	 */
	public boolean getSexOffenderPolygraph() {
		return sexOffenderPolygraph;
	}


	/**
	 * @param sexOffenderPolygraph the sexOffenderPolygraph to set
	 */
	public void setSexOffenderPolygraph(boolean sexOffenderPolygraph) {
		this.sexOffenderPolygraph = sexOffenderPolygraph;
	}


	/**
	 * @return the sexOffenderRegistration
	 */
	public boolean getSexOffenderRegistration() {
		return sexOffenderRegistration;
	}


	/**
	 * @param sexOffenderRegistration the sexOffenderRegistration to set
	 */
	public void setSexOffenderRegistration(boolean sexOffenderRegistration) {
		this.sexOffenderRegistration = sexOffenderRegistration;
	}


	/**
	 * @return the sexOffenderRegistrationNonPublic
	 */
	public boolean getSexOffenderRegistrationNonPublic() {
		return sexOffenderRegistrationNonPublic;
	}


	/**
	 * @param sexOffenderRegistrationNonPublic the sexOffenderRegistrationNonPublic to set
	 */
	public void setSexOffenderRegistrationNonPublic(
			boolean sexOffenderRegistrationNonPublic) {
		this.sexOffenderRegistrationNonPublic = sexOffenderRegistrationNonPublic;
	}


	/**
	 * @return the stateAttorneyName
	 */
	public String getStateAttorneyName() {
		return stateAttorneyName;
	}


	/**
	 * @param stateAttorneyName the stateAttorneyName to set
	 */
	public void setStateAttorneyName(String stateAttorneyName) {
		this.stateAttorneyName = stateAttorneyName;
	}


	/**
	 * @return the statusOffense
	 */
	public boolean getStatusOffense() {
		return statusOffense;
	}


	/**
	 * @param statusOffense the statusOffense to set
	 */
	public void setStatusOffense(boolean statusOffense) {
		this.statusOffense = statusOffense;
	}


	/**
	 * @return the supervisoryFee
	 */
	public String getSupervisoryFee() {
		return supervisoryFee;
	}


	/**
	 * @param supervisoryFee the supervisoryFee to set
	 */
	public void setSupervisoryFee(String supervisoryFee) {
		this.supervisoryFee = supervisoryFee;
	}


	/**
	 * @return the tdcOutreach
	 */
	public boolean getTdcOutreach() {
		return tdcOutreach;
	}


	/**
	 * @param tdcOutreach the tdcOutreach to set
	 */
	public void setTdcOutreach(boolean tdcOutreach) {
		this.tdcOutreach = tdcOutreach;
	}


	/**
	 * @return the technicalViolation
	 */
	public boolean getTechnicalViolation() {
		return technicalViolation;
	}


	/**
	 * @param technicalViolation the technicalViolation to set
	 */
	public void setTechnicalViolation(boolean technicalViolation) {
		this.technicalViolation = technicalViolation;
	}


	/**
	 * @return the thumbprintOrdered
	 */
	public boolean getThumbprintOrdered() {
		return thumbprintOrdered;
	}


	/**
	 * @param thumbprintOrdered the thumbprintOrdered to set
	 */
	public void setThumbprintOrdered(boolean thumbprintOrdered) {
		this.thumbprintOrdered = thumbprintOrdered;
	}


	/**
	 * @return the titleIIIChild
	 */
	public boolean getTitleIIIChild() {
		return titleIIIChild;
	}


	/**
	 * @param titleIIIChild the titleIIIChild to set
	 */
	public void setTitleIIIChild(boolean titleIIIChild) {
		this.titleIIIChild = titleIIIChild;
	}


	/**
	 * @return the txDLRestrictions
	 */
	public String getTxDLRestrictions() {
		return txDLRestrictions;
	}


	/**
	 * @param txDLRestrictions the txDLRestrictions to set
	 */
	public void setTxDLRestrictions(String txDLRestrictions) {
		this.txDLRestrictions = txDLRestrictions;
	}


	/**
	 * @return the tycDetention
	 */
	public boolean getTycDetention() {
		return tycDetention;
	}


	/**
	 * @param tycDetention the tycDetention to set
	 */
	public void setTycDetention(boolean tycDetention) {
		this.tycDetention = tycDetention;
	}


	/**
	 * @return the tycSentenceLength
	 */
	public String getTycSentenceLength() {
		return tycSentenceLength;
	}


	/**
	 * @param tycSentenceLength the tycSentenceLength to set
	 */
	public void setTycSentenceLength(String tycSentenceLength) {
		this.tycSentenceLength = tycSentenceLength;
	}


	/**
	 * @return the violatedCourtOrders
	 */
	public boolean getViolatedCourtOrders() {
		return violatedCourtOrders;
	}


	/**
	 * @param violatedCourtOrders the violatedCourtOrders to set
	 */
	public void setViolatedCourtOrders(boolean violatedCourtOrders) {
		this.violatedCourtOrders = violatedCourtOrders;
	}


	/**
	 * @return the weekdayCurfewTimes
	 */
	public String getWeekdayCurfewTimes() {
		return weekdayCurfewTimes;
	}


	/**
	 * @param weekdayCurfewTimes the weekdayCurfewTimes to set
	 */
	public void setWeekdayCurfewTimes(String weekdayCurfewTimes) {
		this.weekdayCurfewTimes = weekdayCurfewTimes;
	}


	/**
	 * @return the weekendCurfewHours
	 */
	public String getWeekendCurfewHours() {
		return weekendCurfewHours;
	}


	/**
	 * @param weekendCurfewHours the weekendCurfewHours to set
	 */
	public void setWeekendCurfewHours(String weekendCurfewHours) {
		this.weekendCurfewHours = weekendCurfewHours;
	}


	/**
	 * @return the petitionNum
	 */
	public String getPetitionNum() {
		return petitionNum;
	}


	/**
	 * @param petitionNum the petitionNum to set
	 */
	public void setPetitionNum(String petitionNum) {
		this.petitionNum = petitionNum;
	}
	
	/**
	 * @return the courtDateStr
	 */
	public String getCourtDateStr() {
		return courtDateStr;
	}


	/**
	 * @param courtDateStr the courtDateStr to set
	 */
	public void setCourtDateStr(String courtDateStr) {
		this.courtDateStr = courtDateStr;
	}


	/**
	 * @return the courtOrders
	 */
	public Collection getCourtOrders() {
		return courtOrders;
	}


	/**
	 * @param courtOrders the courtOrders to set
	 */
	public void setCourtOrders(Collection courtOrders) {
		this.courtOrders = courtOrders;
	}


	public String getDegree() {
		return degree;
	}


	public void setDegree(String degree) {
		this.degree = degree;
	}



	
}
