/**
 * 
 */
package pd.juvenilecase.referral;

import java.util.Date;
import pd.codetable.criminal.JuvenileCourt;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileOffenseCode;

/** 
 * @stereotype entity
 * @author dnikolis
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class JuvenileLegacyCourtOrder {

	private String courtOrderID;
	private boolean amendedPetition;
	private boolean angerManagementCounseling;
	private boolean anyOtherRulesSetByHCJPD;
	private String attorneyFee;
	private Date birthDate;
	private boolean cAvgSConduct;
	private String childRemovedFrom;
	private String childSupport;
	private boolean cjpoInstructions;
	private Date commitmentDate;
	private boolean committedToTYC;
	private String communityService;
	private Date continuouslyDetainedDate;
	private String courtCost;
	private boolean courtCostWaived;
	private Date courtDate;
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
	private String deadlyweaponDesc;
	private boolean deferredSORegistration;
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
	/** 
	 * @referencedType pd.codetable.Code
	 * @contextKey COURTORDERHEARINGTYPE
	 * @detailerDoNoGenerate false
	 */
	private Code hearingType;
	private boolean individualManagementCounseling;
	private boolean inNeedOfRehab;
	private boolean inNeedOfSupervision;
	private boolean intensiveSupervisionProgram;
	private String intensiveSupervisionProgramTimeFrame;
	private boolean jointCustody;
	private Date judgementDate;
	private Date judgementTYCDate;
	private String judgeName;
	/** 
	 * @referencedType pd.codetable.criminal.JuvenileCourt
	 * @detailerDoNoGenerate false
	 */
	private JuvenileCourt juvenileCourt;
	/** 
	 * @referencedType pd.codetable.criminal.JuvenileOffenseCode
	 * @detailerDoNoGenerate false
	 */
	private JuvenileOffenseCode juvenileOffenseCode;
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
	private boolean placementOutsideHome;
	private boolean previousRulesInEffect;
	private boolean priorRestitutioniEnded;
	private Date probationBeginDate;
	private Date probationEndDate;
	private boolean probationExtended;
	private boolean probationRevoked;
	private boolean randomDrugScreens;
	private boolean reasonableEffortsMade;
	private String respondentAliasName;
	private String respondentAttorneyName;
	private String respondentName;
	private String responsiblePartyAttorneyFee;
	private String responsiblePartyChildSupport;
	private String responsiblePartyCommunityService;
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
	private String specialInstructions;
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
	private boolean weaponsWorkshop;
	private String weekdayCurfewTimes;
	private String weekendCurfewHours;
	public boolean isAmendedPetition() {
		return amendedPetition;
	}
	public void setAmendedPetition(boolean amendedPetition) {
		this.amendedPetition = amendedPetition;
	}
	public boolean isAngerManagementCounseling() {
		return angerManagementCounseling;
	}
	public void setAngerManagementCounseling(boolean angerManagementCounseling) {
		this.angerManagementCounseling = angerManagementCounseling;
	}
	public boolean isAnyOtherRulesSetByHCJPD() {
		return anyOtherRulesSetByHCJPD;
	}
	public void setAnyOtherRulesSetByHCJPD(boolean anyOtherRulesSetByHCJPD) {
		this.anyOtherRulesSetByHCJPD = anyOtherRulesSetByHCJPD;
	}
	public String getAttorneyFee() {
		return attorneyFee;
	}
	public void setAttorneyFee(String attorneyFee) {
		this.attorneyFee = attorneyFee;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public boolean isCAvgSConduct() {
		return cAvgSConduct;
	}
	public void setCAvgSConduct(boolean avgSConduct) {
		cAvgSConduct = avgSConduct;
	}
	public String getChildRemovedFrom() {
		return childRemovedFrom;
	}
	public void setChildRemovedFrom(String childRemovedFrom) {
		this.childRemovedFrom = childRemovedFrom;
	}
	public String getChildSupport() {
		return childSupport;
	}
	public void setChildSupport(String childSupport) {
		this.childSupport = childSupport;
	}
	public boolean isCjpoInstructions() {
		return cjpoInstructions;
	}
	public void setCjpoInstructions(boolean cjpoInstructions) {
		this.cjpoInstructions = cjpoInstructions;
	}
	public Date getCommitmentDate() {
		return commitmentDate;
	}
	public void setCommitmentDate(Date commitmentDate) {
		this.commitmentDate = commitmentDate;
	}
	public boolean isCommittedToTYC() {
		return committedToTYC;
	}
	public void setCommittedToTYC(boolean committedToTYC) {
		this.committedToTYC = committedToTYC;
	}
	public String getCommunityService() {
		return communityService;
	}
	public void setCommunityService(String communityService) {
		this.communityService = communityService;
	}
	public Date getContinuouslyDetainedDate() {
		return continuouslyDetainedDate;
	}
	public void setContinuouslyDetainedDate(Date continuouslyDetainedDate) {
		this.continuouslyDetainedDate = continuouslyDetainedDate;
	}
	public String getCourtCost() {
		return courtCost;
	}
	public void setCourtCost(String courtCost) {
		this.courtCost = courtCost;
	}
	public boolean isCourtCostWaived() {
		return courtCostWaived;
	}
	public void setCourtCostWaived(boolean courtCostWaived) {
		this.courtCostWaived = courtCostWaived;
	}
	public Date getCourtDate() {
		return courtDate;
	}
	public void setCourtDate(Date courtDate) {
		this.courtDate = courtDate;
	}
	
	public boolean isCustodyToCJPO() {
		return custodyToCJPO;
	}
	public void setCustodyToCJPO(boolean custodyToCJPO) {
		this.custodyToCJPO = custodyToCJPO;
	}
	public boolean isCustodyToFather() {
		return custodyToFather;
	}
	public void setCustodyToFather(boolean custodyToFather) {
		this.custodyToFather = custodyToFather;
	}
	public boolean isCustodyToGuardian() {
		return custodyToGuardian;
	}
	public void setCustodyToGuardian(boolean custodyToGuardian) {
		this.custodyToGuardian = custodyToGuardian;
	}
	public boolean isCustodyToMHMRA() {
		return custodyToMHMRA;
	}
	public void setCustodyToMHMRA(boolean custodyToMHMRA) {
		this.custodyToMHMRA = custodyToMHMRA;
	}
	public boolean isCustodyToMother() {
		return custodyToMother;
	}
	public void setCustodyToMother(boolean custodyToMother) {
		this.custodyToMother = custodyToMother;
	}
	public boolean isCustodyToOther() {
		return custodyToOther;
	}
	public void setCustodyToOther(boolean custodyToOther) {
		this.custodyToOther = custodyToOther;
	}
	public boolean isCustodyToParents() {
		return custodyToParents;
	}
	public void setCustodyToParents(boolean custodyToParents) {
		this.custodyToParents = custodyToParents;
	}
	public boolean isCustodyToRelative() {
		return custodyToRelative;
	}
	public void setCustodyToRelative(boolean custodyToRelative) {
		this.custodyToRelative = custodyToRelative;
	}
	public boolean isCustodyToTDPRS() {
		return custodyToTDPRS;
	}
	public void setCustodyToTDPRS(boolean custodyToTDPRS) {
		this.custodyToTDPRS = custodyToTDPRS;
	}
	public int getDaysInDetention() {
		return daysInDetention;
	}
	public void setDaysInDetention(int daysInDetention) {
		this.daysInDetention = daysInDetention;
	}
	public boolean isDeadlyWeapon() {
		return deadlyWeapon;
	}
	public void setDeadlyWeapon(boolean deadlyWeapon) {
		this.deadlyWeapon = deadlyWeapon;
	}
	public String getDeadlyweaponDesc() {
		return deadlyweaponDesc;
	}
	public void setDeadlyweaponDesc(String deadlyweaponDesc) {
		this.deadlyweaponDesc = deadlyweaponDesc;
	}
	public boolean isDeferredSORegistration() {
		return deferredSORegistration;
	}
	public void setDeferredSORegistration(boolean deferredSORegistration) {
		this.deferredSORegistration = deferredSORegistration;
	}
	public boolean isDelinquentConduct() {
		return delinquentConduct;
	}
	public void setDelinquentConduct(boolean delinquentConduct) {
		this.delinquentConduct = delinquentConduct;
	}
	public boolean isDenyTDL() {
		return denyTDL;
	}
	public void setDenyTDL(boolean denyTDL) {
		this.denyTDL = denyTDL;
	}
	public boolean isDeterminatePlacement() {
		return determinatePlacement;
	}
	public void setDeterminatePlacement(boolean determinatePlacement) {
		this.determinatePlacement = determinatePlacement;
	}
	public boolean isDeterminateProbation() {
		return determinateProbation;
	}
	public void setDeterminateProbation(boolean determinateProbation) {
		this.determinateProbation = determinateProbation;
	}
	public String getDeterminateReason1() {
		return determinateReason1;
	}
	public void setDeterminateReason1(String determinateReason1) {
		this.determinateReason1 = determinateReason1;
	}
	public String getDeterminateReason2() {
		return determinateReason2;
	}
	public void setDeterminateReason2(String determinateReason2) {
		this.determinateReason2 = determinateReason2;
	}
	public String getDeterminateReason3() {
		return determinateReason3;
	}
	public void setDeterminateReason3(String determinateReason3) {
		this.determinateReason3 = determinateReason3;
	}
	public String getDeterminateReason4() {
		return determinateReason4;
	}
	public void setDeterminateReason4(String determinateReason4) {
		this.determinateReason4 = determinateReason4;
	}
	public String getDeterminateReason5() {
		return determinateReason5;
	}
	public void setDeterminateReason5(String determinateReason5) {
		this.determinateReason5 = determinateReason5;
	}
	public Date getDispositionDate() {
		return dispositionDate;
	}
	public void setDispositionDate(Date dispositionDate) {
		this.dispositionDate = dispositionDate;
	}
	public boolean isDrugFreeYouth() {
		return drugFreeYouth;
	}
	public void setDrugFreeYouth(boolean drugFreeYouth) {
		this.drugFreeYouth = drugFreeYouth;
	}
	public boolean isEducationalSpecialist() {
		return educationalSpecialist;
	}
	public void setEducationalSpecialist(boolean educationalSpecialist) {
		this.educationalSpecialist = educationalSpecialist;
	}
	public boolean isFamilyManagementCounseling() {
		return familyManagementCounseling;
	}
	public void setFamilyManagementCounseling(boolean familyManagementCounseling) {
		this.familyManagementCounseling = familyManagementCounseling;
	}
	public boolean isFelony1() {
		return felony1;
	}
	public void setFelony1(boolean felony1) {
		this.felony1 = felony1;
	}
	public boolean isFelony2() {
		return felony2;
	}
	public void setFelony2(boolean felony2) {
		this.felony2 = felony2;
	}
	public boolean isFelony3() {
		return felony3;
	}
	public void setFelony3(boolean felony3) {
		this.felony3 = felony3;
	}
	public boolean isFelonyCapital() {
		return felonyCapital;
	}
	public void setFelonyCapital(boolean felonyCapital) {
		this.felonyCapital = felonyCapital;
	}
	public boolean isFelonyStateJail() {
		return felonyStateJail;
	}
	public void setFelonyStateJail(boolean felonyStateJail) {
		this.felonyStateJail = felonyStateJail;
	}
	public boolean isFireSetters() {
		return fireSetters;
	}
	public void setFireSetters(boolean fireSetters) {
		this.fireSetters = fireSetters;
	}
	public boolean isGangCaseload() {
		return gangCaseload;
	}
	public void setGangCaseload(boolean gangCaseload) {
		this.gangCaseload = gangCaseload;
	}
	public boolean isGangWorkshop() {
		return gangWorkshop;
	}
	public void setGangWorkshop(boolean gangWorkshop) {
		this.gangWorkshop = gangWorkshop;
	}
	public boolean isGedProgram() {
		return gedProgram;
	}
	public void setGedProgram(boolean gedProgram) {
		this.gedProgram = gedProgram;
	}
	public Date getGjApprovalDate() {
		return gjApprovalDate;
	}
	public void setGjApprovalDate(Date gjApprovalDate) {
		this.gjApprovalDate = gjApprovalDate;
	}
	public boolean isGjApproved() {
		return gjApproved;
	}
	public void setGjApproved(boolean gjApproved) {
		this.gjApproved = gjApproved;
	}
	public boolean isGjBeyondReasonableDoubt() {
		return gjBeyondReasonableDoubt;
	}
	public void setGjBeyondReasonableDoubt(boolean gjBeyondReasonableDoubt) {
		this.gjBeyondReasonableDoubt = gjBeyondReasonableDoubt;
	}
	public String getGjChildsAge() {
		return gjChildsAge;
	}
	public void setGjChildsAge(String gjChildsAge) {
		this.gjChildsAge = gjChildsAge;
	}
	public boolean isGjDelinquentConduct() {
		return gjDelinquentConduct;
	}
	public void setGjDelinquentConduct(boolean gjDelinquentConduct) {
		this.gjDelinquentConduct = gjDelinquentConduct;
	}
	public String getGjNumber() {
		return gjNumber;
	}
	public void setGjNumber(String gjNumber) {
		this.gjNumber = gjNumber;
	}
	public boolean isGjStateReferral() {
		return gjStateReferral;
	}
	public void setGjStateReferral(boolean gjStateReferral) {
		this.gjStateReferral = gjStateReferral;
	}
	public Date getGjWaiverDate() {
		return gjWaiverDate;
	}
	public void setGjWaiverDate(Date gjWaiverDate) {
		this.gjWaiverDate = gjWaiverDate;
	}
	public Code getHearingType() {
		return hearingType;
	}
	public void setHearingType(Code hearingType) {
		this.hearingType = hearingType;
	}
	public boolean isIndividualManagementCounseling() {
		return individualManagementCounseling;
	}
	public void setIndividualManagementCounseling(
			boolean individualManagementCounseling) {
		this.individualManagementCounseling = individualManagementCounseling;
	}
	public boolean isInNeedOfRehab() {
		return inNeedOfRehab;
	}
	public void setInNeedOfRehab(boolean inNeedOfRehab) {
		this.inNeedOfRehab = inNeedOfRehab;
	}
	public boolean isInNeedOfSupervision() {
		return inNeedOfSupervision;
	}
	public void setInNeedOfSupervision(boolean inNeedOfSupervision) {
		this.inNeedOfSupervision = inNeedOfSupervision;
	}
	public boolean isIntensiveSupervisionProgram() {
		return intensiveSupervisionProgram;
	}
	public void setIntensiveSupervisionProgram(boolean intensiveSupervisionProgram) {
		this.intensiveSupervisionProgram = intensiveSupervisionProgram;
	}
	public String getIntensiveSupervisionProgramTimeFrame() {
		return intensiveSupervisionProgramTimeFrame;
	}
	public void setIntensiveSupervisionProgramTimeFrame(
			String intensiveSupervisionProgramTimeFrame) {
		this.intensiveSupervisionProgramTimeFrame = intensiveSupervisionProgramTimeFrame;
	}
	public boolean isJointCustody() {
		return jointCustody;
	}
	public void setJointCustody(boolean jointCustody) {
		this.jointCustody = jointCustody;
	}
	public Date getJudgementDate() {
		return judgementDate;
	}
	public void setJudgementDate(Date judgementDate) {
		this.judgementDate = judgementDate;
	}
	public Date getJudgementTYCDate() {
		return judgementTYCDate;
	}
	public void setJudgementTYCDate(Date judgementTYCDate) {
		this.judgementTYCDate = judgementTYCDate;
	}
	public String getJudgeName() {
		return judgeName;
	}
	public void setJudgeName(String judgeName) {
		this.judgeName = judgeName;
	}
	public JuvenileCourt getJuvenileCourt() {
		return juvenileCourt;
	}
	public void setJuvenileCourt(JuvenileCourt juvenileCourt) {
		this.juvenileCourt = juvenileCourt;
	}
	public JuvenileOffenseCode getJuvenileOffenseCode() {
		return juvenileOffenseCode;
	}
	public void setJuvenileOffenseCode(JuvenileOffenseCode juvenileOffenseCode) {
		this.juvenileOffenseCode = juvenileOffenseCode;
	}
	public boolean isJuvenileProbationPlacement() {
		return juvenileProbationPlacement;
	}
	public void setJuvenileProbationPlacement(boolean juvenileProbationPlacement) {
		this.juvenileProbationPlacement = juvenileProbationPlacement;
	}
	public boolean isLetterOfApology() {
		return letterOfApology;
	}
	public void setLetterOfApology(boolean letterOfApology) {
		this.letterOfApology = letterOfApology;
	}
	public boolean isMentor() {
		return mentor;
	}
	public void setMentor(boolean mentor) {
		this.mentor = mentor;
	}
	public boolean isMhmraAssessment() {
		return mhmraAssessment;
	}
	public void setMhmraAssessment(boolean mhmraAssessment) {
		this.mhmraAssessment = mhmraAssessment;
	}
	public boolean isMisdemeanorA() {
		return misdemeanorA;
	}
	public void setMisdemeanorA(boolean misdemeanorA) {
		this.misdemeanorA = misdemeanorA;
	}
	public boolean isMisdemeanorB() {
		return misdemeanorB;
	}
	public void setMisdemeanorB(boolean misdemeanorB) {
		this.misdemeanorB = misdemeanorB;
	}
	public boolean isMisdemeanorC() {
		return misdemeanorC;
	}
	public void setMisdemeanorC(boolean misdemeanorC) {
		this.misdemeanorC = misdemeanorC;
	}
	public boolean isNoContactCoActors() {
		return noContactCoActors;
	}
	public void setNoContactCoActors(boolean noContactCoActors) {
		this.noContactCoActors = noContactCoActors;
	}
	public boolean isNoContactComplainant() {
		return noContactComplainant;
	}
	public void setNoContactComplainant(boolean noContactComplainant) {
		this.noContactComplainant = noContactComplainant;
	}
	public boolean isNoContactGangMembers() {
		return noContactGangMembers;
	}
	public void setNoContactGangMembers(boolean noContactGangMembers) {
		this.noContactGangMembers = noContactGangMembers;
	}
	public boolean isNoDelinquentConduct() {
		return noDelinquentConduct;
	}
	public void setNoDelinquentConduct(boolean noDelinquentConduct) {
		this.noDelinquentConduct = noDelinquentConduct;
	}
	public boolean isNoDispositionNecessary() {
		return noDispositionNecessary;
	}
	public void setNoDispositionNecessary(boolean noDispositionNecessary) {
		this.noDispositionNecessary = noDispositionNecessary;
	}
	public Date getOffenseDate() {
		return offenseDate;
	}
	public void setOffenseDate(Date offenseDate) {
		this.offenseDate = offenseDate;
	}
	public String getOtherRulesSetByHCJPD() {
		return otherRulesSetByHCJPD;
	}
	public void setOtherRulesSetByHCJPD(String otherRulesSetByHCJPD) {
		this.otherRulesSetByHCJPD = otherRulesSetByHCJPD;
	}
	public String getParentGuardianCustodian() {
		return parentGuardianCustodian;
	}
	public void setParentGuardianCustodian(String parentGuardianCustodian) {
		this.parentGuardianCustodian = parentGuardianCustodian;
	}
	public boolean isPeerPressure() {
		return peerPressure;
	}
	public void setPeerPressure(boolean peerPressure) {
		this.peerPressure = peerPressure;
	}
	public boolean isPlacementOutsideHome() {
		return placementOutsideHome;
	}
	public void setPlacementOutsideHome(boolean placementOutsideHome) {
		this.placementOutsideHome = placementOutsideHome;
	}
	public boolean isPreviousRulesInEffect() {
		return previousRulesInEffect;
	}
	public void setPreviousRulesInEffect(boolean previousRulesInEffect) {
		this.previousRulesInEffect = previousRulesInEffect;
	}
	public boolean isPriorRestitutioniEnded() {
		return priorRestitutioniEnded;
	}
	public void setPriorRestitutioniEnded(boolean priorRestitutioniEnded) {
		this.priorRestitutioniEnded = priorRestitutioniEnded;
	}
	public Date getProbationBeginDate() {
		return probationBeginDate;
	}
	public void setProbationBeginDate(Date probationBeginDate) {
		this.probationBeginDate = probationBeginDate;
	}
	public Date getProbationEndDate() {
		return probationEndDate;
	}
	public void setProbationEndDate(Date probationEndDate) {
		this.probationEndDate = probationEndDate;
	}
	public boolean isProbationExtended() {
		return probationExtended;
	}
	public void setProbationExtended(boolean probationExtended) {
		this.probationExtended = probationExtended;
	}
	public boolean isProbationRevoked() {
		return probationRevoked;
	}
	public void setProbationRevoked(boolean probationRevoked) {
		this.probationRevoked = probationRevoked;
	}
	public boolean isRandomDrugScreens() {
		return randomDrugScreens;
	}
	public void setRandomDrugScreens(boolean randomDrugScreens) {
		this.randomDrugScreens = randomDrugScreens;
	}
	public boolean isReasonableEffortsMade() {
		return reasonableEffortsMade;
	}
	public void setReasonableEffortsMade(boolean reasonableEffortsMade) {
		this.reasonableEffortsMade = reasonableEffortsMade;
	}
	public String getRespondentAliasName() {
		return respondentAliasName;
	}
	public void setRespondentAliasName(String respondentAliasName) {
		this.respondentAliasName = respondentAliasName;
	}
	public String getRespondentAttorneyName() {
		return respondentAttorneyName;
	}
	public void setRespondentAttorneyName(String respondentAttorneyName) {
		this.respondentAttorneyName = respondentAttorneyName;
	}
	public String getRespondentName() {
		return respondentName;
	}
	public void setRespondentName(String respondentName) {
		this.respondentName = respondentName;
	}
	public String getResponsiblePartyAttorneyFee() {
		return responsiblePartyAttorneyFee;
	}
	public void setResponsiblePartyAttorneyFee(String responsiblePartyAttorneyFee) {
		this.responsiblePartyAttorneyFee = responsiblePartyAttorneyFee;
	}
	public String getResponsiblePartyChildSupport() {
		return responsiblePartyChildSupport;
	}
	public void setResponsiblePartyChildSupport(String responsiblePartyChildSupport) {
		this.responsiblePartyChildSupport = responsiblePartyChildSupport;
	}
	public String getResponsiblePartyCommunityService() {
		return responsiblePartyCommunityService;
	}
	public void setResponsiblePartyCommunityService(
			String responsiblePartyCommunityService) {
		this.responsiblePartyCommunityService = responsiblePartyCommunityService;
	}
	public String getResponsiblePartyCourtCost() {
		return responsiblePartyCourtCost;
	}
	public void setResponsiblePartyCourtCost(String responsiblePartyCourtCost) {
		this.responsiblePartyCourtCost = responsiblePartyCourtCost;
	}
	public String getResponsiblePartyRestitution() {
		return responsiblePartyRestitution;
	}
	public void setResponsiblePartyRestitution(String responsiblePartyRestitution) {
		this.responsiblePartyRestitution = responsiblePartyRestitution;
	}
	public String getResponsiblePartyRestitution2() {
		return responsiblePartyRestitution2;
	}
	public void setResponsiblePartyRestitution2(String responsiblePartyRestitution2) {
		this.responsiblePartyRestitution2 = responsiblePartyRestitution2;
	}
	public String getResponsiblePartySupervisoryFee() {
		return responsiblePartySupervisoryFee;
	}
	public void setResponsiblePartySupervisoryFee(
			String responsiblePartySupervisoryFee) {
		this.responsiblePartySupervisoryFee = responsiblePartySupervisoryFee;
	}
	public String getRestitutionAmountOrdered() {
		return restitutionAmountOrdered;
	}
	public void setRestitutionAmountOrdered(String restitutionAmountOrdered) {
		this.restitutionAmountOrdered = restitutionAmountOrdered;
	}
	public String getRestitutionAmountOrdered2() {
		return restitutionAmountOrdered2;
	}
	public void setRestitutionAmountOrdered2(String restitutionAmountOrdered2) {
		this.restitutionAmountOrdered2 = restitutionAmountOrdered2;
	}
	public String getRestitutionPayeeCity() {
		return restitutionPayeeCity;
	}
	public void setRestitutionPayeeCity(String restitutionPayeeCity) {
		this.restitutionPayeeCity = restitutionPayeeCity;
	}
	public String getRestitutionPayeeCity2() {
		return restitutionPayeeCity2;
	}
	public void setRestitutionPayeeCity2(String restitutionPayeeCity2) {
		this.restitutionPayeeCity2 = restitutionPayeeCity2;
	}
	public String getRestitutionPayeeFirstName() {
		return restitutionPayeeFirstName;
	}
	public void setRestitutionPayeeFirstName(String restitutionPayeeFirstName) {
		this.restitutionPayeeFirstName = restitutionPayeeFirstName;
	}
	public String getRestitutionPayeeFirstName2() {
		return restitutionPayeeFirstName2;
	}
	public void setRestitutionPayeeFirstName2(String restitutionPayeeFirstName2) {
		this.restitutionPayeeFirstName2 = restitutionPayeeFirstName2;
	}
	public String getRestitutionPayeeLastName() {
		return restitutionPayeeLastName;
	}
	public void setRestitutionPayeeLastName(String restitutionPayeeLastName) {
		this.restitutionPayeeLastName = restitutionPayeeLastName;
	}
	public String getRestitutionPayeeLastName2() {
		return restitutionPayeeLastName2;
	}
	public void setRestitutionPayeeLastName2(String restitutionPayeeLastName2) {
		this.restitutionPayeeLastName2 = restitutionPayeeLastName2;
	}
	public String getRestitutionPayeePhoneNum() {
		return restitutionPayeePhoneNum;
	}
	public void setRestitutionPayeePhoneNum(String restitutionPayeePhoneNum) {
		this.restitutionPayeePhoneNum = restitutionPayeePhoneNum;
	}
	public String getRestitutionPayeePhoneNum2() {
		return restitutionPayeePhoneNum2;
	}
	public void setRestitutionPayeePhoneNum2(String restitutionPayeePhoneNum2) {
		this.restitutionPayeePhoneNum2 = restitutionPayeePhoneNum2;
	}
	public String getRestitutionPayeeState() {
		return restitutionPayeeState;
	}
	public void setRestitutionPayeeState(String restitutionPayeeState) {
		this.restitutionPayeeState = restitutionPayeeState;
	}
	public String getRestitutionPayeeState2() {
		return restitutionPayeeState2;
	}
	public void setRestitutionPayeeState2(String restitutionPayeeState2) {
		this.restitutionPayeeState2 = restitutionPayeeState2;
	}
	public String getRestitutionPayeeStreetAddress() {
		return restitutionPayeeStreetAddress;
	}
	public void setRestitutionPayeeStreetAddress(
			String restitutionPayeeStreetAddress) {
		this.restitutionPayeeStreetAddress = restitutionPayeeStreetAddress;
	}
	public String getRestitutionPayeeStreetAddress2() {
		return restitutionPayeeStreetAddress2;
	}
	public void setRestitutionPayeeStreetAddress2(
			String restitutionPayeeStreetAddress2) {
		this.restitutionPayeeStreetAddress2 = restitutionPayeeStreetAddress2;
	}
	public String getRestitutionPayeeZipCode() {
		return restitutionPayeeZipCode;
	}
	public void setRestitutionPayeeZipCode(String restitutionPayeeZipCode) {
		this.restitutionPayeeZipCode = restitutionPayeeZipCode;
	}
	public String getRestitutionPayeeZipCode2() {
		return restitutionPayeeZipCode2;
	}
	public void setRestitutionPayeeZipCode2(String restitutionPayeeZipCode2) {
		this.restitutionPayeeZipCode2 = restitutionPayeeZipCode2;
	}
	public String getRestitutionPaymentTimeFrame() {
		return restitutionPaymentTimeFrame;
	}
	public void setRestitutionPaymentTimeFrame(String restitutionPaymentTimeFrame) {
		this.restitutionPaymentTimeFrame = restitutionPaymentTimeFrame;
	}
	public String getRestitutionPaymentTimeFrame2() {
		return restitutionPaymentTimeFrame2;
	}
	public void setRestitutionPaymentTimeFrame2(String restitutionPaymentTimeFrame2) {
		this.restitutionPaymentTimeFrame2 = restitutionPaymentTimeFrame2;
	}
	public Date getRestitutionStartDate() {
		return restitutionStartDate;
	}
	public void setRestitutionStartDate(Date restitutionStartDate) {
		this.restitutionStartDate = restitutionStartDate;
	}
	public Date getRestitutionStartDate2() {
		return restitutionStartDate2;
	}
	public void setRestitutionStartDate2(Date restitutionStartDate2) {
		this.restitutionStartDate2 = restitutionStartDate2;
	}
	public String getRestitutionTotal() {
		return restitutionTotal;
	}
	public void setRestitutionTotal(String restitutionTotal) {
		this.restitutionTotal = restitutionTotal;
	}
	public String getRestitutionTotal2() {
		return restitutionTotal2;
	}
	public void setRestitutionTotal2(String restitutionTotal2) {
		this.restitutionTotal2 = restitutionTotal2;
	}
	public boolean isSexOffenderBloodSample() {
		return sexOffenderBloodSample;
	}
	public void setSexOffenderBloodSample(boolean sexOffenderBloodSample) {
		this.sexOffenderBloodSample = sexOffenderBloodSample;
	}
	public boolean isSexOffenderCounseling() {
		return sexOffenderCounseling;
	}
	public void setSexOffenderCounseling(boolean sexOffenderCounseling) {
		this.sexOffenderCounseling = sexOffenderCounseling;
	}
	public boolean isSexOffenderPolygraph() {
		return sexOffenderPolygraph;
	}
	public void setSexOffenderPolygraph(boolean sexOffenderPolygraph) {
		this.sexOffenderPolygraph = sexOffenderPolygraph;
	}
	public boolean isSexOffenderRegistration() {
		return sexOffenderRegistration;
	}
	public void setSexOffenderRegistration(boolean sexOffenderRegistration) {
		this.sexOffenderRegistration = sexOffenderRegistration;
	}
	public boolean isSexOffenderRegistrationNonPublic() {
		return sexOffenderRegistrationNonPublic;
	}
	public void setSexOffenderRegistrationNonPublic(
			boolean sexOffenderRegistrationNonPublic) {
		this.sexOffenderRegistrationNonPublic = sexOffenderRegistrationNonPublic;
	}
	public String getSpecialInstructions() {
		return specialInstructions;
	}
	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}
	public String getStateAttorneyName() {
		return stateAttorneyName;
	}
	public void setStateAttorneyName(String stateAttorneyName) {
		this.stateAttorneyName = stateAttorneyName;
	}
	public boolean isStatusOffense() {
		return statusOffense;
	}
	public void setStatusOffense(boolean statusOffense) {
		this.statusOffense = statusOffense;
	}
	public String getSupervisoryFee() {
		return supervisoryFee;
	}
	public void setSupervisoryFee(String supervisoryFee) {
		this.supervisoryFee = supervisoryFee;
	}
	public boolean isTdcOutreach() {
		return tdcOutreach;
	}
	public void setTdcOutreach(boolean tdcOutreach) {
		this.tdcOutreach = tdcOutreach;
	}
	public boolean isTechnicalViolation() {
		return technicalViolation;
	}
	public void setTechnicalViolation(boolean technicalViolation) {
		this.technicalViolation = technicalViolation;
	}
	public boolean isThumbprintOrdered() {
		return thumbprintOrdered;
	}
	public void setThumbprintOrdered(boolean thumbprintOrdered) {
		this.thumbprintOrdered = thumbprintOrdered;
	}
	public boolean isTitleIIIChild() {
		return titleIIIChild;
	}
	public void setTitleIIIChild(boolean titleIIIChild) {
		this.titleIIIChild = titleIIIChild;
	}
	public String getTxDLRestrictions() {
		return txDLRestrictions;
	}
	public void setTxDLRestrictions(String txDLRestrictions) {
		this.txDLRestrictions = txDLRestrictions;
	}
	public boolean isTycDetention() {
		return tycDetention;
	}
	public void setTycDetention(boolean tycDetention) {
		this.tycDetention = tycDetention;
	}
	public String getTycSentenceLength() {
		return tycSentenceLength;
	}
	public void setTycSentenceLength(String tycSentenceLength) {
		this.tycSentenceLength = tycSentenceLength;
	}
	public boolean isViolatedCourtOrders() {
		return violatedCourtOrders;
	}
	public void setViolatedCourtOrders(boolean violatedCourtOrders) {
		this.violatedCourtOrders = violatedCourtOrders;
	}
	public boolean isWeaponsWorkshop() {
		return weaponsWorkshop;
	}
	public void setWeaponsWorkshop(boolean weaponsWorkshop) {
		this.weaponsWorkshop = weaponsWorkshop;
	}
	public String getWeekdayCurfewTimes() {
		return weekdayCurfewTimes;
	}
	public void setWeekdayCurfewTimes(String weekdayCurfewTimes) {
		this.weekdayCurfewTimes = weekdayCurfewTimes;
	}
	public String getWeekendCurfewHours() {
		return weekendCurfewHours;
	}
	public void setWeekendCurfewHours(String weekendCurfewHours) {
		this.weekendCurfewHours = weekendCurfewHours;
	}
	public void findAll() {
		// begin-user-code
		// end-user-code
	}

	public void find() {
		// begin-user-code
		// end-user-code
	}
	public String getCourtOrderID() {
		return courtOrderID;
	}
	public void setCourtOrderID(String courtOrderID) {
		this.courtOrderID = courtOrderID;
	}
}