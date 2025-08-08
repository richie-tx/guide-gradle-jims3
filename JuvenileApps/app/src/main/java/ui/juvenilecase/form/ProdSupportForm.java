package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.casefile.reply.CasefileDocumentsResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCourtDecisionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileHearingTypeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileRefAssgnmtResponseEvent;
import messaging.codetable.drug.reply.DrugTypeCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenile.reply.DrugTestingResponseEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenilecase.reply.AttorneyNameAndAddressResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.JJSJuvenileResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileReferralVOPResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileReferralResponseEvent;
import messaging.productionsupport.reply.ProductionSupportTestingSessionInfoResponseEvent;
import messaging.programreferral.reply.ProgramReferralCommentResponseEvent;
import messaging.productionsupport.reply.ProductionSupportSubstanceAbuseInfoResponseEvent;
import net.minidev.json.JSONObject;
import pd.codetable.CodeTable;
import pd.codetable.criminal.JuvenileMasterStatus;
import pd.juvenilecase.JJSLastAttorney;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.PACTRiskNeedLevel;
import pd.juvenilecase.caseplan.CasePlan;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;
import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.referral.JCVOP;
import pd.juvenilecase.referral.JJSSVIntakeHistory;
import pd.supervision.administerserviceprovider.SP_Profile;
import ui.common.CodeHelper;
import ui.common.SocialSecurity;

public class ProdSupportForm extends ActionForm
{

    String selectedMenuItem;

    String casefileId;
    //String juvenileId;
    //String courtId;

    String traitId;
    String calEventId;
    String activityId;
    String schoolhistId;
    String assignmentId;
    String referralNum;
    String constellationId;

    
    CasePlan caseplan;
    JuvenileCasefile casefile;
    JuvenileCasefileResponseEvent casefileDet;
    JuvenileCasefileResponseEvent originalCasefile;
    CasefileClosingResponseEvent casefileClosing;

    ArrayList menuOptions;
    ArrayList activities;
    ArrayList casefiles;
    ArrayList assignments;
    ArrayList caseplans;
    ArrayList casefileclosings;
    ArrayList interviews;
    ArrayList iviewdocs;
    ArrayList riskanalyses;
    ArrayList juvprogrefs;
    ArrayList beneasmnts;
    ArrayList suprules;
    ArrayList caleventconts;
    ArrayList eventtasks;
    ArrayList nttasks;
    ArrayList assnmnthists;
    ArrayList riskresponses;
    ArrayList juveniles;
    ArrayList progrefcomments;
    ArrayList eventreferrals;
    ArrayList progrfasgnhists;
    ArrayList traits;
    ArrayList subAbuseInfos;
    ArrayList servattends;
    ArrayList maysis;
    ArrayList maysidetails;
    ArrayList servevents;
    ArrayList calevents;
    ArrayList schoolhists;
    ArrayList bookingReferrals;
    ArrayList bookingSprvisionNumbers;
    ArrayList referralOffenses;
    ArrayList controllingReferrals;
    ArrayList riskAnalysisIds;
    ArrayList transOffenseReferrals;
    ArrayList juvenileWarrants;
    ArrayList juvenileWarrantAssociates;
    ArrayList juvenileInactivatedWarrants;
    ArrayList juvenileWarrantFields;
    ArrayList juvenileWarrantCharges;
    ArrayList juvenileWarrantRecalls;
    ArrayList juvenileWarrantServes;
    ArrayList juvenileWarrantServiceOfficers;
    ArrayList riskNeedLevels;
    ArrayList juvenileReferrals;
    ArrayList programReferrals;
    ArrayList associatedCautions;
    ArrayList associatedCharges;
    ArrayList associatedScarMarks;
    ArrayList associatedTattoos;
    ArrayList associatedAddresses;
    ArrayList casefileclosingsDelete;
    ArrayList programReferralsDelete;
    ArrayList assignmentsDelete;
    ArrayList corespondents;
    ArrayList filteredCasefileIds;
    ArrayList filteredCasefileClosingIds;
    ArrayList filteredProgramReferralIds;
    ArrayList filteredCasefiles;
    ArrayList filteredCasefileClosings;
    ArrayList filteredProgramReferrals;
    JuvenileTraitResponseEvent originalTrait;
  
  //added for US 180996 
    ArrayList juvSourceFunds; 
    //String fundSource
    String newFundSource;

   

    //For iviewdocs, noncompdocs, and commonappdocs
    ArrayList casefiledocs;
    ArrayList casedocchildren;

    //For Master Status Codes Drop-Down
    ArrayList masterStatusCodes;

    //For Referral Drop-Down Menus
    ArrayList outcomeCodes;
    ArrayList statusCodes;
    ArrayList substatusCodes;
    ArrayList attendstatusCodes;
    ArrayList assessoptionCodes;
    CodeTable supervisionCodes;
    ArrayList outcomeDescCodes;
    ArrayList referralSrcCodes;
    ArrayList provProgramsList; //added for US 176693

    // offense code for the allegation -  For update petition detail 
    ArrayList offenseCodes;

    //For School Hist Drop-downs
    ArrayList currentGradeCodes;
    ArrayList exitTypeCodes;
    ArrayList appropGradeCodes;
    ArrayList gradesRepeatCodes;

    // facilities
    ArrayList facilityHeaderList;
    ArrayList facilityDetentionList;
    ArrayList activeFacilitiesList;
    ArrayList facilityStatusList;
    ArrayList admitReasonsList;
    private Collection releaseTo;
    private Collection<CodeResponseEvent> returnReasons;
    private Collection<CodeResponseEvent> tempReleaseReasons;
    private Collection<JuvenileCasefileOffenseCodeResponseEvent> codetableDataList;

    // intake history records
    List intakeHistoryRecords;
    JJSSVIntakeHistory intakeHistoryRecord;
    JJSSVIntakeHistory originalIntakeHistoryRecord;
    JuvenileCasefileTransferredOffenseResponseEvent transferredOffenseRecord;
    JuvenileCasefileTransferredOffenseResponseEvent originalTransferredOffenseRecord;
    String intakeHistoryId;
    String transOffenseId;
    String intakeDate;
    String assignmentDate;
    String originalAssmntType;
    String originalSupervisionType;
    Date originalAssignmenDate;
    Date originalIntakeDate;
    String originalIntakeDecision;
    String originalIntakeJPO;
    String originalJuvenileNumber;
    String originalReferralNumber;
    ProductionSupportJuvenileReferralResponseEvent referralDetail;
    List<CodeResponseEvent> supervisionTypeCodes;

    String admittanceCodeDesc;

    String activeFacilityCodeDesc;

    String sequenceNum;
    //String detentionId;

    String newadmitReasonCd;
    String activeFacilityCd;
    String newHeaderFacilityCd;
    String newTransferToFacility;
    private String admitBy;
    private String detainedDate;
    private String bookingReferral;
    private String currentReferral;
    private String admitDate;
    private String admitTime;
    private String originalAdmitDate;
    private String originalAdmitTime;
    private String releaseDate;
    private String releaseTime;
    private String returnDate;
    private String returnStatus;
    private String returnTime;
    private String bookingSupervisionNum = "";
    private String currentSupervisionNum = "";
    private String currentOffense;
    private String facilityStatus;
    private String valuablesReceipt;
    private String admitAuthority;
    private String floorLocation;
    private String unitLocation;
    private String roomLocation;
    private String multipleOccupancyUnit;
    private String locker;
    private String outcome;
    private String referralId;
    private String releasedTo;
    private String releaseReason;
    private String returnReason;
    private String releaseAuthority;
    private String releasedBy;
    private String tempReleaseReason;
    private String facilitySeqNum;
    private Date birthDate;
    private String realDOB;
    private String juvenileName;
    private String juvenileSsn;
    private String statusId;
    private String rectype;
    private JuvenileFacilityHeaderResponseEvent headerInfo = new JuvenileFacilityHeaderResponseEvent();
    private String msg;
    private Collection<JSONObject> pactReferralsJson; //used in jquery
    private String riskAnalysisId;
    private boolean pactOIDChanged;

    // user-story 

    private String courtDate;
    private String courtId;
    private String originalCourtId;
    private Collection<DocketEventResponseEvent> dktSearchResults;
    private ArrayList<DocketEventResponseEvent> juvDistCourtRecords;
    private ArrayList<DocketEventResponseEvent> originalJuvDistCourtRecords;
    private ArrayList<DocketEventResponseEvent> juvDetCourtRecords;
    private ArrayList<DocketEventResponseEvent> originalJuvDetCourtRecords;
    private ArrayList<DocketEventResponseEvent> ancillaryCalendarRecords;
    private DocketEventResponseEvent ancillaryCalendarRecord;
    private int searchResultSize; 
    private Collection<JuvenileDispositionCodeResponseEvent> courtDecisionsResponses;
    //private List<JuvenileCourtDecisionCodeResponseEvent> detentionHearingResults;	
    private Collection<JuvenileDispositionCodeResponseEvent> detentionHearingResults;
    private List<JuvenileCourtDecisionCodeResponseEvent> hearingDecisionResults;
    private Collection<JuvenileHearingTypeResponseEvent> courtHearingTypes = null;
    private Collection courtTypes = null;
    private Collection<PetitionResponseEvent> petitionDetails = null;
    private JJSJuvenileResponseEvent juvenileDetail;
    private Collection<ProductionSupportJuvenileReferralResponseEvent> referralDetails;
    private Collection<ProgramReferralCommentResponseEvent> referralComments;   
    private List<JuvenileRefAssgnmtResponseEvent> referralAssignmentCodes;
    private List<FamilyConstellationListResponseEvent>familyConstellationResults;
    private String oldRefAssmntType;
    private String oldRefSeqNum;
    private String referralAssmntType;
    private String refSeqNum;

    private String validateMsg;
    private String cursorPosition;
    private Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList;
    private String attorneyName;
    private String errMessage;
    private String selectedValue;
    private String juvenileId;
    private String detentionId;
    private String originalDetentionId;
    private Map<String, DocketEventResponseEvent> dktSearchResultsMap;
    private Map<String, DocketEventResponseEvent> updateDocketMap;
    private String hiddenForward;
    private String sex;
    private String race;
    private String originalRace;
    private String hispanic;
    private int hdnCount;
    //Sealing
    private String sealedDate;
    private String sealComments;
    private String delComments;

    //US 71582
    private SocialSecurity newSSN = new SocialSecurity("");
    private String purgeBoxNum;
    private String purgeDate;
    private String purgeSerNum;
    private String caseNotePart1;
    private String purgeFlag;
    private String detentionFacilityId;
    private String detentionStatusId;
    private String checkedOutTo;
    private String checkedOutDate;
    private String juvenileFName;
    private String juvenileMName;
    private String juvenileLName;
    private String juvenileNameSuffix;
    private String lastReferral;
    // Code Table Collections (make static)
    private static Collection raceList;
    private static Collection originalRaceList;
    private static Collection sexList;
    private String raceId;
    private String originalRaceId;
    private String sexId;
    private String masterDOB;
    private String oldStatusId;
    private String hasActiveReferral;
    private String hasActiveCasefile;
    private String hasActiveFacility;
    private String lctime;
    private String referralDate;
    private String alphaCodeId;
    private String shortDesc;
    private String dpsCodeId;
    private String categoryId;
    private List offenseResultList;
    private String chainNum;
    private String purgeComments;
    private String respondentName;
    private String docketEventId;
    private String newRecordCLM;
    private List<JJSCLDetention> sequenceNumberList;
    private String activityCd;
    private String newComments;
    private String newupdatedComments;
    private String activityDate;
    private String activityTime;
    private String activityendTime;
    private String detentionTime;
    ActivityResponseEvent activityResp;
    ActivityResponseEvent originalactivityResp;
    private List<CalendarServiceEventResponseEvent>services;
    private SP_Profile instructor;
    private List<SP_Profile>instructors;
    private String instructorId;
    private String selectedTransferInstructorId;
    private String serviceEventId;
    private List<ProviderProgramResponseEvent>programs;
    private String fromStartDate;
    private String toStartDate;
    private CasefileDocumentsResponseEvent commonAppDocument;
    private PetitionResponseEvent petitionDetail;
    private String offenseId;
    private JJSOffenseResponseEvent referralOffense;
    private JJSOffenseResponseEvent originalReferralOffense;
    private ProductionSupportJuvenileReferralResponseEvent juvprogref;
    private ProductionSupportJuvenileReferralResponseEvent originalJuvprogref;
    private String CJISNumber;
    private String latitude;
    private String longitude;
    private String familyMemberId;    
    private String familyMemberFirstName;
    private String familyMemberLastName;
    private FamilyMember familyMember;
    private FamilyMember familyMemberUpdate;
    private List<FamilyMember> familyMemberList = new ArrayList<FamilyMember>();
    private String familyConstellationId;
    private String constellationQueryType;
    private FamilyConstellation familyConstellation;
    private List<FamilyConstellation> familyConstellationList = new ArrayList<FamilyConstellation>();
    private static Collection relationshipToJuvenileList;
    private static Collection stateList;
    private static Collection isIncarceratedList;
    private static Collection statusList;
    private static Collection booleanList;
    private String searchedJuvenileId; 
    private FamilyConstellationMember familyConstellationMember;
    private List<FamilyConstellationMember> familyConstellationMemberList = new ArrayList<FamilyConstellationMember>();
    private String DPSCode;
    private String traitTypeId;
    private String commentId;
    private String commentDate;
    private boolean isIntakeHistoryDelete;
    
    private String assessOfficerId;
    private boolean hasPreviousMaysi;
    private boolean isAdministered;
    private String reasonNotDone;
    private String otherReasonNotDone;
    
    private String newProgramRefId;
    private String originalAdmitOID;
    private String avgCostPerDay;
    private String detainedByInd;
    private String tjjdFundingSrc;
    private String  originaladmitSEQNUM;
    private String postAdmitOID;
    private int tjjdFacilityId;
    private String custodyfirstName;
    private String custodylastName;
    
    //US 189272 / 190449 
    private String dateOfDeath;
    private int ageAtDeath ;
    private int juvExcluded;
    private String recType;
    private String livewith;
    private String causeOfDeath;
    private String deathVerification;
    private Collection causesOfDeath;
    private Collection deathVerficationCodes;
    //US 189272 / 190449  ENDS

    /*private String purgeBox;
    private String purgeSeries;

    public String getPurgeSeries()
    {
        return purgeSeries;
    }

    public void setPurgeSeries(String purgeSeries)
    {
        this.purgeSeries = purgeSeries;
    }

    public String getPurgeBox()
    {
        return purgeBox;
    }

    public void setPurgeBox(String purgeBox)
    {
        this.purgeBox = purgeBox;
    }*/
    /**
     * resets the list and flag attributes.
     */
    public void clear()
    {
	this.currentOffense = "";
	this.floorLocation = "";
	this.unitLocation = "";
	this.roomLocation = "";
	this.multipleOccupancyUnit = "";
	this.locker = "";
	this.admitBy = "";
	this.admitTime = "";
	this.admitAuthority = "";
	this.originalAdmitTime = "";
	this.releaseAuthority = "";
	this.releasedBy = "";
	this.outcome = "";
	this.releaseAuthority = "";
	this.releaseDate = "";
	this.releasedBy = "";
	this.releasedTo = "";
	this.releaseReason = "";
	this.releaseTime = "";
	this.returnDate = "";
	this.returnReason = "";
	this.returnTime = "";
	this.activeFacilityCd = "";
	this.bookingReferral = "";
	this.currentReferral = "";
	this.bookingSupervisionNum = "";
	this.currentSupervisionNum = "";
	this.sequenceNum = "";
	this.newadmitReasonCd = "";
	this.newTransferToFacility = "";
	this.detainedDate = "";
	this.admitDate = "";
	this.originalAdmitDate = "";
	this.outcome = "";
	this.tempReleaseReason = "";
	this.valuablesReceipt = "";
	this.facilitySeqNum = "";
	this.newReferralNum = "";
	this.newHeaderFacilityCd = "";
	this.newcasefileId = "";
	this.dktSearchResults = new ArrayList<DocketEventResponseEvent>();
	//this.detentionHearingResults = new ArrayList<JuvenileCourtDecisionCodeResponseEvent>();
	this.searchResultSize = 0;
	this.msg = "";
	this.validateMsg = "";
	this.sealComments = "";
	this.delComments = "";
	this.purgeComments = "";
	this.sealedDate = "";
	this.hasActiveCasefile = "";
	this.hasActiveFacility = "";
	this.hasActiveReferral = "";
	this.petitionAllegation = "";
	this.petitionAmended = "";
	this.newPetitionDate = "";
	this.petitionNum = "";
	this.petitionSeverity = "";
	this.petitionStatus = "";
	this.petitionType = "";
	this.purgeSerNum = "";
	this.purgeBoxNum = "";
	this.courtDate = "";
	this.headerInfo = new JuvenileFacilityHeaderResponseEvent();
	this.newRecordCLM ="";
	this.newJuvLocationUnitId="";
	this.activityCd="";
	this.newComments="";
	this.newupdatedComments="";
	this.activityDate=null;
	this.activityTime="";
	this.activityendTime="";
	this.detentionTime="";	
	this.CJISNumber="";
	this.DPSCode="";
	this.familyMemberId="";
	this.pactId = "";
	this.newPactId="";
	this.pactOIDChanged = false;
    this.commentId="";
	this.commentDate="";
	this.assessOfficerId = "";
	this.hasPreviousMaysi = false;
	this.isAdministered = false;
	this.reasonNotDone = "";
	this.otherReasonNotDone = "";
	this.setNewProgramRefId("");
	this.originalAdmitOID = "";
	this.avgCostPerDay = "";
	this.detainedByInd = "";
	this.tjjdFundingSrc = "";
	this.originaladmitSEQNUM = "";
	this.postAdmitOID = "";
	this.tjjdFacilityId = 0;
	this.custodyfirstName = "";
	this.custodylastName = "";

    }
    
    public String getCustodylastName()
	{
	  return custodylastName;
	}

	public void setCustodylastName(String custodylastName)
	{
	    this.custodylastName = custodylastName;
	}
	
	public String getCustodyfirstName()
	{
	    return custodyfirstName;
	}

	public void setCustodyfirstName(String custodyfirstName)
	{
	    this.custodyfirstName = custodyfirstName;
	}
	
	public int getTjjdFacilityId()
	{
	    return tjjdFacilityId;
	}

	public void setTjjdFacilityId(int tjjdFacilityId)
	{
	     this.tjjdFacilityId = tjjdFacilityId;
	}
	
	public String getPostAdmitOID()
	{
	    return postAdmitOID;
	}

	public void setPostAdmitOID(String postAdmitOID)
	{
	    this.postAdmitOID = postAdmitOID;
	}
	
	public String getOriginaladmitSEQNUM()
	{
	    return originaladmitSEQNUM;
	}

	public void setOriginaladmitSEQNUM(String originaladmitSEQNUM)
	{
	    this.originaladmitSEQNUM = originaladmitSEQNUM;
	}
	
	public String getTjjdFundingSrc()
	{
	    return tjjdFundingSrc;
	}

	public void setTjjdFundingSrc(String tjjdFundingSrc)
	{
	      this.tjjdFundingSrc = tjjdFundingSrc;
	}
	
	
	/**
	 * @return the detainedByInd
	 */
	public String getDetainedByInd() {		
		return detainedByInd;
	}

	/**
	 * @param detainedByInd the detainedByInd to set
	 */
	public void setDetainedByInd(String detainedByInd) {
		this.detainedByInd = detainedByInd;
	}
	
	public String getAvgCostPerDay()
	{	   
	    return avgCostPerDay;
	}
	
	public void setAvgCostPerDay(String avgCostPerDay)
	{
	    this.avgCostPerDay = avgCostPerDay;
	}
	
	/**
	 * @return the originalAdmitOID
	 */
	public String getOriginalAdmitOID() {
		return originalAdmitOID;
	}

	/**
	 * @param originalAdmitOID the originalAdmitOID to set
	 */
	public void setOriginalAdmitOID(String originalAdmitOID) {
		this.originalAdmitOID = originalAdmitOID;
	}
    
    /**
     * @return the traitTypeId
     */
    public String getTraitTypeId() {
        return traitTypeId;
    }

    /**
     * @param traitTypeId the traitTypeId to set
     */
    public void setTraitTypeId(String traitTypeId) {
        this.traitTypeId = traitTypeId;
    }


    public String getFloorLocation()
    {
	return floorLocation;
    }

    public void setFloorLocation(String floorLocation)
    {
	this.floorLocation = floorLocation;
    }

    public String getUnitLocation()
    {
	return unitLocation;
    }

    public void setUnitLocation(String unitLocation)
    {
	this.unitLocation = unitLocation;
    }

    public String getRoomLocation()
    {
	return roomLocation;
    }

    public void setRoomLocation(String roomLocation)
    {
	this.roomLocation = roomLocation;
    }

    public String getMultipleOccupancyUnit()
    {
	return multipleOccupancyUnit;
    }

    public void setMultipleOccupancyUnit(String multipleOccupancyUnit)
    {
	this.multipleOccupancyUnit = multipleOccupancyUnit;
    }

    // other
    String attday;
    String attmonth;
    String attyear;
    String lastAttendedDate;

    String newAttDate = "";

    String menuOptionsBox;
    String menuOptionsBox2;
    String menuOptionsBox3;
    String menuOptionsBox4;
    String menuOptionsBox5;
    String menuOptionsBox6;
    String menuOptionsBox7;
    String menuOptionsBox8;
    String menuOptionsBox9;
    String menuOptionsBox10;
    String menuOptionsBox11;
    String menuOptionsBox12;
    String menuOptionsBox13;
    String menuOptionsBox14;
    String menuOptionsBox15;
    String menuOptionsBox16;
    String menuOptionsBox17;
    String menuOptionsBox18;

    String statusBox;
    String substatusBox;
    String outcomeBox;
    String outcomeDescBox;
    String providerPgmDescBox; //added for US 176693
    String attendstatusBox;
    String assessoptionBox;
    String supervisionBox;
    String currentGradeBox;
    String exitTypeBox;
    String appropGradeBox;
    String gradesRepeatCodeBox;
    String oldAssignmentDate;

    //For Referral Date Drop-Down Menus
    String refBeginDate;
    String refEndDate;
    String refAckDate;

    String refSentDate;
    String beginday;
    String beginmonth;
    String beginyear;

    String endday;
    String endmonth;
    String endyear;

    String newBeginDate = "";
    String newEndDate = "";
    
    String programReferralAssignmentDate;
    String originalProgramReferralAssignmentDate;
    String programReferralAssignmentId;

    //For Casefile Update Drop-Down Menus
    String actdate;
    String createdate;
    String enddate;
    String actday;
    String actmonth;
    String actyear;

    String createday;
    String createmonth;
    String createyear;

    //For Casefile closing
    String closingEndDate;

    String newActDate = "";

    String oldActDate = "";
    String newCreateDate = "";
    String oldCreateDate = "";
    String newMaysineeded = "";
    String oldMaysineeded = "";
    String newActivationDate = "";
    String oldActivationDate = "";
    String newSupervisionEndDate = "";
    String oldSupervisionEndDateString = "";
    String newPactRiskNeeded = "";
    String newHispanicIndicatorNeeded = "";
    String newSchoolHistoryNeeded = "";
    String newVopEntryNeeded = "";
    String newSubstanceAbuseNeeded = "";

    //For Casefile Closing text box
    String newControllingReferral = "";
    String newJuvseqnum = "";
    String oldJuvseqnum = "";

    //For Casefile Closing summary
    String oldSupervisionOutcome = "";
    String oldControllingReferral = "";
    Date oldSupervisionEndDate;
    String oldSupervisionOutcomeDescCd = "";

    //For displaying results
    String tableId = "";

    //For Casefile Merge
    String fromCasefile;
    String toCasefile;

    //For Juvenile Merge
    String toJuvenileId;
    String fromJuvenileId;
    String mergeStatusMsg;

    //For Risk Assessment Deletes
    String riskanalysisId;

    //For Program Referral Modifications
    String juvprogrefId;
    String newReferralNum;
    String newcasefileId;

    //Pact data
    String offCode;
    String pactDate;
    String needValue;
    String riskValue;
    String riskLevel;
    String needLevel;
    String pactStatus;
    String newPactDate;
    String newPactStatus;
    private String pactId;
    private String newPactId;
    ArrayList pactReferrals;
    ArrayList pactReferralsTotal;
    String riskneedID;
    PACTRiskNeedLevel pactRecord;
    String newReferralID;
    String newCaseID;
    String newRiskNeedLvlId;
    String statusFlag;
    boolean moreThanOneCurrent = false;;//US 106777
    private int currentPactReferrals = 0;

    //For LastAttorney
    JJSLastAttorney attRecord;
    String attorneyConnection;
    String atyBarNum;
    String atyName;
    String galBarNum;
    String galAttorneyName;
    String action;

    //For Calevent Modifications
    String serveventId;
    String newAddlAttendees;
    String newEventMaximum;
    String prevEventMaximum;
    ArrayList eventStatusCodes;
    ArrayList juvLocationUnitCodes;
    String newEventStatusCd;
    String newJuvLocationUnitId;

    Date newCalEventStartDate;
    String newStartCalDate;
    String startCalEventDay;
    String startCalEventMonth;
    String startCalEventYear;

    Date newCalEventEndDate;

    String newEndCalDate;
    String endCalEventDay;
    String endCalEventMonth;
    String endCalEventYear;

    String startCalTimeHours;
    String startCalTimeMinutes;
    String endCalTimeHours;
    String endCalTimeMinutes;

    //For Case Modify JPO
    String newJpoId;
    String newJpoCode;
    String previousJpoId;
    String previousJpoCode;

    //For Program Referral Assignment History
    String progrfasgnhistId;
    String progrfasgndate;
    String[] selectedHists;

    //For Assignment Deletes
    String[] selectedAssignments;

    //For Casefile Document Deletes
    String documentId;
    String documentType;
    String casenoncomnoteId;

    //For MAYSI menu selection
    String maysiassessmntId;
    String maysidetailId;

    //Counts for various queries
    int activityCount;
    int casefileCount;
    int assignmentCount;
    int caseplanCount;
    int casefileClosingCount;
    int interviewCount;
    int iviewdocCount;
    int riskanalysisCount;
    int riskresponsesCount;
    int juvprogrefCount;
    int juvcourtDocketsCount;
    int beneasmntCount;
    int supruleCount;
    int caleventcontCount;
    int eventtaskCount;
    int nttaskCount;
    int assnmnthistCount;
    int juvenileCount;
    int progrefcommentsCount;
    int eventreferralCount;
    int progrfasgnhistCount;
    int traitCount;
    int drugTestingCount;
    int subAbuseCount;
    int searchTraitCount;
    int searchCaseDocumentCount;
    int servattendCount;
    int maysiCount;
    int maysidetailCount;
    int serveventCount;
    int caleventCount;
    int schoolhistCount;
    int casefiledocCount;
    int casedocchildrenCount;
    int facilityHeaderCount;
    int facilityDetentionCount;
    int juvRefOffensesCount;
    int intakeHistCount;
    int transOffenseReferralsCount;
    int juvDetCourtRecordCount;
    int juvDistCourtRecordCount;
    int petitionRecordCount;
    int juvenileWarrantCount;
    int juvenileWarrantAssociateCount;
    int juvenileInactivatedWarrantCount;
    int juvenileWarrantFieldCount;
    int juvenileWarrantChargeCount;
    int juvenileWarrantRecallCount;
    int juvenileWarrantServeCount;
    int juvenileWarrantServiceOfficerCount;
    int riskNeedLevelCount;
    int programReferralCount;
    int associatedCautionCount;
    int associatedChargeCount;
    int associatedScarMarkCount;
    int associatedTattooCount;
    int associatedAddressCount;
    int casefileclosingsDeleteCount;
    int programReferralsDeleteCount;
    int assignmentsDeleteCount;
    int casefileOneReferralDeleteCount;
    int corespondentCount;
   


    

    //For petition detail
    String petitionStatus = "";
    String petitionType = "";
    private String newPetitionDate = "";
    String petitionNum = "";
    String petitionAmended = "";
    String petitionAllegation = "";
    String petitionSeverity = "";
    String lastChangeDate = "";
    String lastChangeUser = "";
    String lastChangeTime = "";
    String OID = "";
    String sequenceNumber = "";
    String lastSeqNum = "";
    String newLastSeqNum = "";
    Date terminationDate = null; //Added for US 167561
    private String newTerminationDate = "";
    Date terminationCreateDate = null;
    private String newTerminationCreateDate = "";

  
    

    //For referral
    String referralOID = "";

    private String isRefBeginDateChanged = "false";
    private String isRefEndDateChanged = "false";
    private String isRefAckDateChanged = "false";
    private String isRefSentDateChanged = "false";
    private String isControllingReferralChanged = "false";
    private String isCaseFileIdChanged = "false";
    private String isLastSeqNum = "false";
    private String moreThanOneReferralNumber = "false";
    private String isProgramReferralAssignmentDateChanged = "false";
    private String isfundSourceChanged = "false";
    
    //US 161883 VOP 
    private String refNumVOP;
    private String refDateVOP;
    private String VOPOffenseCode;
    private String offenseChargeVOP;
    private String offenseChargeDateVOP;
    private String inCCountyOrigPetitionedRefNumVOPstr;
    private int inCCountyOrigPetitionedRefNumVOP;
    private String locationIndicatorVOP;
    private String adultIndicatorVOP;
    String vopOID = "";
    JCVOP jcVOP;
    JCVOP jcVOPOriginal;
    
    //US 164319
    private Collection<JuvenileReferralVOPResponseEvent> jcVOPs; 
    private int jcVOPCount;
    private String drugTestingId;
    private String testDate;
    private String originalTestDate;
    private String testTime;
    
    private DrugTestingResponseEvent drugTestingInfo;
    private DrugTestingResponseEvent originalDrugTestingInfo;
    private List drugTestingInfos;
    private String testingSessionId;
    private List testingSessionInfos;

    private String originalCountyId;

    private String originalOffenseCode;

    private String originalCategory;

    private String originalDpsCode;

    private Date originalOffenseDate;

    private Date originalAdjudicationDate;

    private String originalPersonId;  
    private String substanceAbuseId;
    
    private List<ProductionSupportSubstanceAbuseInfoResponseEvent> substanceAbuseInfos;
    private ProductionSupportSubstanceAbuseInfoResponseEvent originalSubstanceAbuseInfo;
    private ProductionSupportSubstanceAbuseInfoResponseEvent substanceAbuseInfo;
    private List<CodeResponseEvent>tjjdSubstanceAbuseCodes;
    private List<DrugTypeCodeResponseEvent>drugTypeCodes;

    

    public void clearSpecialProcessingResults()
    {

	//selectedMenuItem = "";

	activities = null;
	//	casefiles        = null;
	assignments = null;

	//	caseplans        = null;
	casefileclosings = null;
	interviews = null;
	iviewdocs = null;
	riskanalyses = null;
	riskresponses = null;
	juvprogrefs = null;
	juvDistCourtRecords = null;
	originalJuvDistCourtRecords = null;
	juvDetCourtRecords = null;
	originalJuvDetCourtRecords = null;
	beneasmnts = null;
	suprules = null;
	caleventconts = null;
	eventtasks = null;
	nttasks = null;
	assnmnthists = null;
	juveniles = null;
	progrefcomments = null;
	eventreferrals = null;
	progrfasgnhists = null;
	traits = null;
	servattends = null;
	maysis = null;
	maysidetails = null;
	servevents = null;
	calevents = null;
	schoolhists = null;
	casefiledocs = null;
	casedocchildren = null;
	facilityHeaderList = null;
	facilityDetentionList = null;
	transOffenseReferrals = null;
	juvenileWarrants = null;
	juvenileWarrantAssociates = null;
	juvenileInactivatedWarrants = null;
	juvenileWarrantFields = null;
	juvenileWarrantCharges = null;
	juvenileWarrantRecalls = null;
	juvenileWarrantServes = null;
	juvenileWarrantServiceOfficers = null;
	riskNeedLevels = null;


	tableId = "";

	//Counts for various queries
	activityCount = 0;
	//	casefileCount       = 0;
	assignmentCount = 0;
	//	caseplanCount       = 0;
	casefileClosingCount = 0;
	interviewCount = 0;
	iviewdocCount = 0;
	riskanalysisCount = 0;
	riskresponsesCount = 0;
	juvprogrefCount = 0;
	beneasmntCount = 0;
	supruleCount = 0;
	caleventcontCount = 0;
	eventtaskCount = 0;
	nttaskCount = 0;
	assnmnthistCount = 0;
	juvenileCount = 0;
	progrefcommentsCount = 0;
	eventreferralCount = 0;
	progrfasgnhistCount = 0;
	traitCount = 0;
	searchTraitCount = 0;
	searchCaseDocumentCount = 0;
	servattendCount = 0;
	maysiCount = 0;
	maysidetailCount = 0;
	serveventCount = 0;
	caleventCount = 0;
	schoolhistCount = 0;
	casefiledocCount = 0;
	casedocchildrenCount = 0;
	facilityHeaderCount = 0;
	juvRefOffensesCount = 0;
	intakeHistCount = 0;
	transOffenseReferralsCount = 0;
	juvDetCourtRecordCount =0;
	juvDistCourtRecordCount =0;
	petitionRecordCount =0;
	juvenileWarrantCount =0;
	juvenileWarrantAssociateCount =0;
	juvenileInactivatedWarrantCount = 0;
	juvenileWarrantFieldCount = 0;
	juvenileWarrantChargeCount = 0;
	juvenileWarrantRecallCount = 0;
	juvenileWarrantServeCount = 0;
	juvenileWarrantServiceOfficerCount = 0;
	riskNeedLevelCount = 0;
	programReferralCount = 0;


	//Merge variables
	toJuvenileId = "";
	mergeStatusMsg = "";

	//Other variables
	riskanalysisId = "";
	juvprogrefId = "";
	newBeginDate = "";
	newEndDate = "";
	oldCreateDate = "";
	oldMaysineeded = "";
	oldActivationDate = "";
	oldSupervisionEndDateString = "";
	newActDate = "";
	oldActDate = "";
	newCreateDate = "";
	newControllingReferral = "";
	newJuvseqnum = "";
	oldJuvseqnum = "";
	oldSupervisionOutcome = "";
	oldControllingReferral = "";
	oldSupervisionEndDate = null;
	oldSupervisionOutcomeDescCd = "";
	newAddlAttendees = "";
	newAttDate = "";
	documentId = "";
	documentType = "";
	newEventMaximum = "";
	prevEventMaximum = "";
	previousJpoId = "";
	newReferralNum = "";
	newHeaderFacilityCd = "";
	newcasefileId = "";

	//Menu boxes
	statusBox = "";
	substatusBox = "";
	outcomeBox = "";
	attendstatusBox = "";
	assessoptionBox = "";
	supervisionBox = "";
	menuOptionsBox = "-1";
	outcomeDescBox = "";
	exitTypeBox = "";
	appropGradeBox = "";
	gradesRepeatCodeBox = "";
	currentGradeBox = "";
	newEventStatusCd = "";
	newJuvLocationUnitId = "";
	newCalEventStartDate = null;
	newCalEventEndDate = null;
	this.newStartCalDate = null;
	this.newEndCalDate = null;
	startCalEventDay = "";
	startCalEventMonth = "";
	startCalEventYear = "";

	endCalEventDay = "";
	endCalEventMonth = "";
	endCalEventYear = "";
	startCalTimeHours = "";
	startCalTimeMinutes = "";
	endCalTimeHours = "";
	endCalTimeMinutes = "";

	beginday = "";
	beginmonth = "";
	beginyear = "";

	endday = "";
	endmonth = "";
	endyear = "";

	createday = "";
	createmonth = "";
	createyear = "";

	attday = "";
	attmonth = "";
	attyear = "";

	refBeginDate = null;
	refEndDate = null;

	//facilities 
	detentionId = "";
	this.dktSearchResults = new ArrayList<DocketEventResponseEvent>();
	this.juvDistCourtRecords = null;
	this.originalJuvDistCourtRecords = null;
	this.juvDetCourtRecords = null;
	this.originalJuvDetCourtRecords = null;
	juvenileId = "";
	courtId = "";
	courtDate = "";
	this.msg = "";
	this.sealComments = "";
	this.delComments = "";
	this.purgeComments = "";
	this.sealedDate = "";
	headerInfo = null;
	this.activityCd="";
	this.newComments="";
	this.newupdatedComments="";
	this.activityDate=null;
	this.activityTime="";
	this.activityendTime="";
	this.detentionTime="";	
    }

    public ArrayList getJuvenileWarrantServiceOfficers()
    {
        return juvenileWarrantServiceOfficers;
    }

    public void setJuvenileWarrantServiceOfficers(ArrayList juvenileWarrantServiceOfficers)
    {
        this.juvenileWarrantServiceOfficers = juvenileWarrantServiceOfficers;
    }

    public int getJuvenileWarrantServiceOfficerCount()
    {
        return juvenileWarrantServiceOfficerCount;
    }

    public void setJuvenileWarrantServiceOfficerCount(int juvenileWarrantServiceOfficerCount)
    {
        this.juvenileWarrantServiceOfficerCount = juvenileWarrantServiceOfficerCount;
    }

    public ArrayList getRiskNeedLevels()
    {
        return riskNeedLevels;
    }

    public void setRiskNeedLevels(ArrayList riskNeedLevels)
    {
        this.riskNeedLevels = riskNeedLevels;
    }

    public int getRiskNeedLevelCount()
    {
        return riskNeedLevelCount;
    }

    public void setRiskNeedLevelCount(int riskNeedLevelCount)
    {
        this.riskNeedLevelCount = riskNeedLevelCount;
    }

    public ArrayList getJuvenileInactivatedWarrants()
    {
        return juvenileInactivatedWarrants;
    }

    public void setJuvenileInactivatedWarrants(ArrayList juvenileInactivatedWarrants)
    {
        this.juvenileInactivatedWarrants = juvenileInactivatedWarrants;
    }

    public ArrayList getJuvenileWarrantFields()
    {
        return juvenileWarrantFields;
    }

    public void setJuvenileWarrantFields(ArrayList juvenileWarrantFields)
    {
        this.juvenileWarrantFields = juvenileWarrantFields;
    }

    public ArrayList getJuvenileWarrantCharges()
    {
        return juvenileWarrantCharges;
    }

    public void setJuvenileWarrantCharges(ArrayList juvenileWarrantCharges)
    {
        this.juvenileWarrantCharges = juvenileWarrantCharges;
    }

    public ArrayList getJuvenileWarrantRecalls()
    {
        return juvenileWarrantRecalls;
    }

    public void setJuvenileWarrantRecalls(ArrayList juvenileWarrantRecalls)
    {
        this.juvenileWarrantRecalls = juvenileWarrantRecalls;
    }

    public ArrayList getJuvenileWarrantServes()
    {
        return juvenileWarrantServes;
    }

    public void setJuvenileWarrantServes(ArrayList juvenileWarrantServes)
    {
        this.juvenileWarrantServes = juvenileWarrantServes;
    }

    public int getJuvenileInactivatedWarrantCount()
    {
        return juvenileInactivatedWarrantCount;
    }

    public void setJuvenileInactivatedWarrantCount(int juvenileInactivatedWarrantCount)
    {
        this.juvenileInactivatedWarrantCount = juvenileInactivatedWarrantCount;
    }

    public int getJuvenileWarrantFieldCount()
    {
        return juvenileWarrantFieldCount;
    }

    public void setJuvenileWarrantFieldCount(int juvenileWarrantFieldCount)
    {
        this.juvenileWarrantFieldCount = juvenileWarrantFieldCount;
    }

    public int getJuvenileWarrantChargeCount()
    {
        return juvenileWarrantChargeCount;
    }

    public void setJuvenileWarrantChargeCount(int juvenileWarrantChargeCount)
    {
        this.juvenileWarrantChargeCount = juvenileWarrantChargeCount;
    }

    public int getJuvenileWarrantRecallCount()
    {
        return juvenileWarrantRecallCount;
    }

    public void setJuvenileWarrantRecallCount(int juvenileWarrantRecallCount)
    {
        this.juvenileWarrantRecallCount = juvenileWarrantRecallCount;
    }

    public int getJuvenileWarrantServeCount()
    {
        return juvenileWarrantServeCount;
    }

    public void setJuvenileWarrantServeCount(int juvenileWarrantServeCount)
    {
        this.juvenileWarrantServeCount = juvenileWarrantServeCount;
    }

    public void clearAllResults()
    {
	selectedMenuItem = "";

	activities = null;
	//	casefiles        = null;
	assignments = null;

	//	caseplans        = null;
	casefileclosings = null;
	interviews = null;
	iviewdocs = null;
	riskanalyses = null;
	riskresponses = null;
	juvprogrefs = null;
	juvDistCourtRecords = null;
	originalJuvDistCourtRecords = null;
	juvDetCourtRecords = null;
	originalJuvDetCourtRecords = null;
	beneasmnts = null;
	suprules = null;
	caleventconts = null;
	eventtasks = null;
	nttasks = null;
	assnmnthists = null;
	juveniles = null;
	progrefcomments = null;
	eventreferrals = null;
	progrfasgnhists = null;
	traits = null;
	servattends = null;
	maysis = null;
	maysidetails = null;
	servevents = null;
	calevents = null;
	schoolhists = null;
	casefiledocs = null;
	casedocchildren = null;
	facilityHeaderList = null;
	facilityDetentionList = null;
	casefiles = null;
	transOffenseReferrals = null;
	juvenileWarrants = null;
	juvenileWarrantAssociates = null;
	juvenileInactivatedWarrants = null;
	juvenileWarrantFields = null;
	juvenileWarrantCharges = null;
	juvenileWarrantRecalls = null;
	juvenileWarrantServes = null;
	juvenileReferrals = null;
	programReferrals = null;
	corespondents	= null;
	filteredCasefiles = null;
	filteredCasefileClosings = null;
	filteredProgramReferrals = null;

	tableId = "";

	//Counts for various queries
	activityCount = 0;
	casefileCount       = 0;
	assignmentCount = 0;
	//	caseplanCount       = 0;
	casefileClosingCount = 0;
	interviewCount = 0;
	iviewdocCount = 0;
	riskanalysisCount = 0;
	riskresponsesCount = 0;
	juvprogrefCount = 0;
	beneasmntCount = 0;
	supruleCount = 0;
	caleventcontCount = 0;
	eventtaskCount = 0;
	nttaskCount = 0;
	assnmnthistCount = 0;
	juvenileCount = 0;
	progrefcommentsCount = 0;
	eventreferralCount = 0;
	progrfasgnhistCount = 0;
	traitCount = 0;
	servattendCount = 0;
	maysiCount = 0;
	maysidetailCount = 0;
	serveventCount = 0;
	caleventCount = 0;
	schoolhistCount = 0;
	casefiledocCount = 0;
	casedocchildrenCount = 0;
	facilityHeaderCount = 0;
	facilityDetentionCount = 0;
	intakeHistCount = 0;
	transOffenseReferralsCount = 0;
	juvDetCourtRecordCount =0;
	juvDistCourtRecordCount =0;
	petitionRecordCount =0;
	juvenileWarrantCount =0;
	juvenileWarrantAssociateCount =0;
	juvenileInactivatedWarrantCount = 0;
	juvenileWarrantFieldCount = 0;
	juvenileWarrantChargeCount = 0;
	juvenileWarrantRecallCount = 0;
	juvenileWarrantServeCount = 0;
	programReferralCount = 0;
	associatedCautionCount = 0;
	associatedChargeCount = 0;
	associatedScarMarkCount = 0;
	associatedTattooCount = 0;
	associatedAddressCount = 0;
	corespondentCount     = 0;

	//Merge variables
	toJuvenileId = "";
	mergeStatusMsg = "";

	//Other variables
	riskanalysisId = "";
	juvprogrefId = "";
	newBeginDate = "";
	newEndDate = "";
	oldCreateDate = "";
	oldMaysineeded = "";
	oldActivationDate = "";
	oldSupervisionEndDateString = "";
	newActDate = "";
	oldActDate = "";
	newCreateDate = "";
	newControllingReferral = "";
	newJuvseqnum = "";
	oldJuvseqnum = "";
	oldSupervisionOutcome = "";
	oldControllingReferral = "";
	oldSupervisionEndDate = null;
	oldSupervisionOutcomeDescCd = "";
	newAddlAttendees = "";
	newAttDate = "";
	documentId = "";
	documentType = "";
	newEventMaximum = "";
	prevEventMaximum = "";
	previousJpoId = "";
	newReferralNum = "";
	newHeaderFacilityCd = "";
	newcasefileId = "";
	facilityStatus = "";

	//Menu boxes
	statusBox = "";
	substatusBox = "";
	outcomeBox = "";
	attendstatusBox = "";
	assessoptionBox = "";
	supervisionBox = "";
	menuOptionsBox = "-1";
	outcomeDescBox = "";
	exitTypeBox = "";
	appropGradeBox = "";
	gradesRepeatCodeBox = "";
	currentGradeBox = "";
	newEventStatusCd = "";
	newJuvLocationUnitId = "";
	newCalEventStartDate = null;
	newCalEventEndDate = null;
	startCalEventDay = "";
	startCalEventMonth = "";
	startCalEventYear = "";
	newStartCalDate = null;
	newEndCalDate = null;

	endCalEventDay = "";
	endCalEventMonth = "";
	endCalEventYear = "";
	startCalTimeHours = "";
	startCalTimeMinutes = "";
	endCalTimeHours = "";
	endCalTimeMinutes = "";

	beginday = "";
	beginmonth = "";
	beginyear = "";

	endday = "";
	endmonth = "";
	endyear = "";

	createday = "";
	createmonth = "";
	createyear = "";

	attday = "";
	attmonth = "";
	attyear = "";

	//facilities 
	detentionId = "";
	this.dktSearchResults = new ArrayList<DocketEventResponseEvent>();
	juvenileId = "";
	courtId = "";
	courtDate = "";
	this.msg = "";
	this.sealComments = "";
	this.delComments = "";
	this.sealedDate = "";
	this.chainNum = "";
	this.referralId = "";

	//petition detail
	referralId = "";
	petitionStatus = "";
	petitionType = "";
	newPetitionDate = "";
	petitionNum = "";
	petitionAmended = "";
	petitionAllegation = "";
	petitionSeverity = "";
	lastChangeDate = "";
	lastChangeUser = "";
	lastChangeTime = "";
	OID = "";
	sequenceNumber = "";
	petitionDetails = null;

	//referral
	referralNum = "";
	referralOID = "";
	referralAssignmentCodes = new ArrayList<JuvenileRefAssgnmtResponseEvent>();
	oldRefAssmntType = "";
	referralAssmntType = "";
	oldRefSeqNum = "";
	refSeqNum = "";
	oldAssignmentDate = "";
	offCode = "";
	isRefBeginDateChanged = "false";
	isRefEndDateChanged = "false";
	isRefAckDateChanged = "false";
	isRefSentDateChanged = "false";
	isControllingReferralChanged = "false";
	isCaseFileIdChanged = "false";
	programReferralAssignmentDate = "";
	originalProgramReferralAssignmentDate = "";
	isProgramReferralAssignmentDateChanged="false";
	isLastSeqNum = "false";
	programReferralAssignmentId = "";

	//intake history
	intakeHistoryRecord = null;
	intakeHistoryRecords = null;
	transferredOffenseRecord = null;
	intakeHistoryId = "";
	transOffenseId = "";
	assignmentDate = "";
	intakeDate = "";
	originalIntakeHistoryRecord = null;
	originalTransferredOffenseRecord = null;
	originalAssmntType = "";
	originalSupervisionType = "";
	originalAssignmenDate = null;
	originalIntakeDate = null;
	originalIntakeDecision = "";
	originalIntakeJPO = "";

	//Pact
	newPactDate = null;
	pactReferrals = null;
	pactReferralsTotal = null;
	riskneedID = "";
	pactRecord = null;
	pactStatus = null;
	newPactStatus = null;
	newCaseID = null;
	newReferralID = null;
	newRiskNeedLvlId = null;
	statusFlag = null;

	//LastAttorney
	//attRecord		= null;
	//attorneyConnection	= null;
	atyBarNum = null;
	atyName = null;
	galBarNum = null;
	galAttorneyName = null;
	moreThanOneCurrent = false;

	masterStatusCodes = new ArrayList<JuvenileMasterStatus>();
	controllingReferrals = new ArrayList<ProductionSupportJuvenileReferralResponseEvent>();
	familyConstellationResults = null;

	//School History
	exitTypeBox = "";
	appropGradeBox = "";
	gradesRepeatCodeBox = "";
	currentGradeBox = "";
	
	constellationId = "";
	
	this.ancillaryCalendarRecords = new ArrayList<DocketEventResponseEvent>();
	this.respondentName = "";
	this.docketEventId = null;
	this.ancillaryCalendarRecord = null;
	
	associatedCautions = null;
	associatedCharges = null;
	associatedScarMarks = null;
	associatedTattoos = null;
	associatedAddresses = null;
	moreThanOneReferralNumber = "false";
	casefileclosingsDelete = null;
	programReferralsDelete = null;
	assignmentsDelete      = null;
	filteredCasefileIds    = null;
	filteredCasefileClosingIds = null;
	filteredProgramReferralIds = null;
	
	   
	casefileclosingsDeleteCount = 0;
	programReferralsDeleteCount = 0;
	assignmentsDeleteCount	    = 0;
	casefileOneReferralDeleteCount    = 0;
	headerInfo = null;
	this.newRecordCLM ="";
	this.newJuvLocationUnitId="";
	
	//vop
	refNumVOP = null;
	refDateVOP = null;
	VOPOffenseCode = null;
	offenseChargeVOP = null;
	offenseChargeDateVOP = null;
	setInCCountyOrigPetitionedRefNumVOPstr("");
	locationIndicatorVOP = null;
	adultIndicatorVOP = "";
	vopOID = "";
	this.activityCd="";
	this.newComments="";
	this.newupdatedComments="";
	this.activityDate=null;
	this.activityTime="";
	this.activityendTime="";
	this.detentionTime="";
	
	this.services = new ArrayList<CalendarServiceEventResponseEvent>();
	this.instructorId = "";
	this.selectedTransferInstructorId = "";
	this.instructor = null;
	this.instructors =  new ArrayList<SP_Profile>();
	this.serviceEventId = "";
	this.programs = new ArrayList<ProviderProgramResponseEvent>();
	this.jcVOPCount = 0;
	this.commonAppDocument = null;
	//setJcVOPCount(0);
	this.referralOffense = null;
	this.originalReferralOffense = null;
	this.offenseId = "";
	this.statusId = "";
	
	this.juvprogref = null;
	this.originalJuvprogref = null;
	this.juvcourtDocketsCount = 0;
	this.drugTestingId = "";
	this.testDate = "";
	this.originalTestDate = "";
	this.testTime = "";
	this.drugTestingInfos = new ArrayList<DrugTestingResponseEvent>();
	this.selectedValue = "";
	this.drugTestingInfo = null;
	this.originalDrugTestingInfo = null;
	this.testingSessionId = "";
	this.testingSessionInfos = new ArrayList<ProductionSupportTestingSessionInfoResponseEvent>();
	this.commentId="";
	this.commentDate="";
	this.substanceAbuseId = "";
	this.substanceAbuseInfos = new ArrayList<ProductionSupportSubstanceAbuseInfoResponseEvent>();
	this.substanceAbuseInfo = null;
	this.originalSubstanceAbuseInfo = null;
	this.tjjdSubstanceAbuseCodes = null;
	this.drugTypeCodes = null;
	this.selectedHists = null;
    }

    public ArrayList getCorespondents()
    {
        return corespondents;
    }

    public void setCorespondents(ArrayList corespondents)
    {
        this.corespondents = corespondents;
    }

    public int getCorespondentCount()
    {
        return corespondentCount;
    }

    public void setCorespondentCount(int corespondentCount)
    {
        this.corespondentCount = corespondentCount;
    }

    public int getCasefileOneReferralDeleteCount()
    {
        return casefileOneReferralDeleteCount;
    }

    public void setCasefileOneReferralDeleteCount(int casefileOneReferralDeleteCount)
    {
        this.casefileOneReferralDeleteCount = casefileOneReferralDeleteCount;
    }

    public ArrayList getAssignmentsDelete()
    {
        return assignmentsDelete;
    }

    public void setAssignmentsDelete(ArrayList assignmentsDelete)
    {
        this.assignmentsDelete = assignmentsDelete;
    }

    public int getAssignmentsDeleteCount()
    {
        return assignmentsDeleteCount;
    }

    public void setAssignmentsDeleteCount(int assignmentsDeleteCount)
    {
        this.assignmentsDeleteCount = assignmentsDeleteCount;
    }

    public ArrayList getJuvenileReferrals()
    {
        return juvenileReferrals;
    }

    public void setJuvenileReferrals(ArrayList juvenileReferrals)
    {
        this.juvenileReferrals = juvenileReferrals;
    }

    public ArrayList getCaleventconts()
    {
	return caleventconts;
    }

    public void setCaleventconts(ArrayList caleventconts)
    {
	this.caleventconts = caleventconts;
    }

    public ArrayList getEventtasks()
    {
	return eventtasks;
    }

    public void setEventtasks(ArrayList eventtasks)
    {
	this.eventtasks = eventtasks;
    }

    public ArrayList getNttasks()
    {
	return nttasks;
    }

    public void setNttasks(ArrayList nttasks)
    {
	this.nttasks = nttasks;
    }

    /**
     * Begin Generated methods.
     */

    public String getCasefileId()
    {
	return casefileId;
    }

    public void setCasefileId(String casefileId)
    {
	this.casefileId = casefileId;
    }

    public String getMsg()
    {
	return msg;
    }

    public void setMsg(String msg)
    {
	this.msg = msg;
    }

    public ArrayList getActivities()
    {
	return activities;
    }

    public void setActivities(ArrayList activities)
    {
	this.activities = activities;
    }

    public ArrayList getAssignments()
    {
	return assignments;
    }

    public void setAssignments(ArrayList assignments)
    {
	this.assignments = assignments;
    }

    public ArrayList getCasefileclosings()
    {
	return casefileclosings;
    }

    public void setCasefileclosings(ArrayList casefileclosings)
    {
	this.casefileclosings = casefileclosings;
    }

    public ArrayList getInterviews()
    {
	return interviews;
    }

    public void setInterviews(ArrayList interviews)
    {
	this.interviews = interviews;
    }

    public ArrayList getIviewdocs()
    {
	return iviewdocs;
    }

    public void setIviewdocs(ArrayList iviewdocs)
    {
	this.iviewdocs = iviewdocs;
    }

    public ArrayList getRiskanalyses()
    {
	return riskanalyses;
    }

    public void setRiskanalyses(ArrayList riskanalyses)
    {
	this.riskanalyses = riskanalyses;
    }

    public ArrayList getJuvprogrefs()
    {
	return juvprogrefs;
    }

    /*public ArrayList getJuvDistCourtRecords() {
    	return juvDistCourtRecords;
    }*/

    public void setJuvprogrefs(ArrayList juvprogrefs)
    {
	this.juvprogrefs = juvprogrefs;
    }

    /*public void setJuvDistCourtRecords(ArrayList juvDistCourtRecords) {
    	this.juvDistCourtRecords = juvDistCourtRecords;
    }*/

    public ArrayList getBeneasmnts()
    {
	return beneasmnts;
    }

    public void setBeneasmnts(ArrayList beneasmnts)
    {
	this.beneasmnts = beneasmnts;
    }

    public ArrayList getSuprules()
    {
	return suprules;
    }

    public void setSuprules(ArrayList suprules)
    {
	this.suprules = suprules;
    }

    public int getActivityCount()
    {
	return activityCount;
    }

    public void setActivityCount(int activityCount)
    {
	this.activityCount = activityCount;
    }

    public int getCasefileCount()
    {
	return casefileCount;
    }

    public void setCasefileCount(int casefileCount)
    {
	this.casefileCount = casefileCount;
    }

    public int getAssignmentCount()
    {
	return assignmentCount;
    }

    public void setAssignmentCount(int assignmentCount)
    {
	this.assignmentCount = assignmentCount;
    }

    public int getCaseplanCount()
    {
	return caseplanCount;
    }

    public void setCaseplanCount(int caseplanCount)
    {
	this.caseplanCount = caseplanCount;
    }

    public int getCasefileClosingCount()
    {
	return casefileClosingCount;
    }

    public void setCasefileClosingCount(int casefileClosingCount)
    {
	this.casefileClosingCount = casefileClosingCount;
    }

    public int getInterviewCount()
    {
	return interviewCount;
    }

    public void setInterviewCount(int interviewCount)
    {
	this.interviewCount = interviewCount;
    }

    public int getIviewdocCount()
    {
	return iviewdocCount;
    }

    public void setIviewdocCount(int iviewdocCount)
    {
	this.iviewdocCount = iviewdocCount;
    }

    public int getRiskanalysisCount()
    {
	return riskanalysisCount;
    }

    public void setRiskanalysisCount(int riskanalysisCount)
    {
	this.riskanalysisCount = riskanalysisCount;
    }

    public int getJuvprogrefCount()
    {
	return juvprogrefCount;
    }

    public void setJuvprogrefCount(int juvprogrefCount)
    {
	this.juvprogrefCount = juvprogrefCount;
    }

    public int getJuvcourtDocketsCount()
    {
	return juvcourtDocketsCount;
    }

    public void setJuvcourtDocketsCount(int juvcourtDocketsCount)
    {
	this.juvcourtDocketsCount = juvcourtDocketsCount;
    }

    public int getBeneasmntCount()
    {
	return beneasmntCount;
    }

    public void setBeneasmntCount(int beneasmntCount)
    {
	this.beneasmntCount = beneasmntCount;
    }

    public int getSupruleCount()
    {
	return supruleCount;
    }

    public void setSupruleCount(int supruleCount)
    {
	this.supruleCount = supruleCount;
    }

    public int getCaleventcontCount()
    {
	return caleventcontCount;
    }

    public void setCaleventcontCount(int caleventcontCount)
    {
	this.caleventcontCount = caleventcontCount;
    }

    public int getEventtaskCount()
    {
	return eventtaskCount;
    }

    public void setEventtaskCount(int eventtaskCount)
    {
	this.eventtaskCount = eventtaskCount;
    }

    public int getNttaskCount()
    {
	return nttaskCount;
    }

    public void setNttaskCount(int nttaskCount)
    {
	this.nttaskCount = nttaskCount;
    }

    public String getTableId()
    {
	return tableId;
    }

    public void setTableId(String tableId)
    {
	this.tableId = tableId;
    }

    public String getToJuvenileId()
    {
	return toJuvenileId;
    }

    public void setToJuvenileId(String toJuvenileId)
    {
	this.toJuvenileId = toJuvenileId;
    }

    public String getFromJuvenileId()
    {
	return fromJuvenileId;
    }

    public void setFromJuvenileId(String fromJuvenileId)
    {
	this.fromJuvenileId = fromJuvenileId;
    }

    public String getMergeStatusmsg()
    {
	return mergeStatusMsg;
    }

    public void setMergeStatusmsg(String mergeStatusmsg)
    {
	this.mergeStatusMsg = mergeStatusmsg;
    }

    public String getMergeStatusMsg()
    {
	return mergeStatusMsg;
    }

    public void setMergeStatusMsg(String mergeStatusMsg)
    {
	this.mergeStatusMsg = mergeStatusMsg;
    }

    public ArrayList getAssnmnthists()
    {
	return assnmnthists;
    }

    public void setAssnmnthists(ArrayList assnmnthists)
    {
	this.assnmnthists = assnmnthists;
    }

    public int getAssnmnthistCount()
    {
	return assnmnthistCount;
    }

    public void setAssnmnthistCount(int assnmnthistCount)
    {
	this.assnmnthistCount = assnmnthistCount;
    }

    public ArrayList getRiskresponses()
    {
	return riskresponses;
    }

    public void setRiskresponses(ArrayList responses)
    {
	this.riskresponses = responses;
    }

    public String getRiskanalysisId()
    {
	return riskanalysisId;
    }

    public void setRiskanalysisId(String riskanalysisId)
    {
	this.riskanalysisId = riskanalysisId;
    }

    public int getRiskresponsesCount()
    {
	return riskresponsesCount;
    }

    public void setRiskresponsesCount(int responseCount)
    {
	this.riskresponsesCount = responseCount;
    }

    public String getSelectedMenuItem()
    {
	return selectedMenuItem;
    }

    public void setSelectedMenuItem(String selectedMenuItem)
    {
	this.selectedMenuItem = selectedMenuItem;
    }

    public String getReferralNum()
    {
	return referralNum;
    }

    public void setReferralNum(String referralNum)
    {
	this.referralNum = referralNum;
    }

    public String getReferralId()
    {
	return referralId;
    }

    public void setReferralId(String referralId)
    {
	this.referralId = referralId;
    }

    public String getCourtId()
    {
	return courtId;
    }

    public void setCourtId(String courtId)
    {
	this.courtId = courtId;
    }

    public ArrayList getOutcomeCodes()
    {
	return outcomeCodes;
    }

    public void setOutcomeCodes(ArrayList outcomeCodes)
    {
	this.outcomeCodes = outcomeCodes;
    }

    public ArrayList getStatusCodes()
    {
	return statusCodes;
    }

    public void setStatusCodes(ArrayList statusCodes)
    {
	this.statusCodes = statusCodes;
    }

    public ArrayList getSubstatusCodes()
    {
	return substatusCodes;
    }

    public void setSubstatusCodes(ArrayList substatusCodes)
    {
	this.substatusCodes = substatusCodes;
    }

    public String getStatusBox()
    {
	return statusBox;
    }

    public void setStatusBox(String statusBox)
    {
	this.statusBox = statusBox;
    }

    public String getSubstatusBox()
    {
	return substatusBox;
    }

    public void setSubstatusBox(String substatusBox)
    {
	this.substatusBox = substatusBox;
    }

    public String getOutcomeBox()
    {
	return outcomeBox;
    }

    public void setOutcomeBox(String outcomeBox)
    {
	this.outcomeBox = outcomeBox;
    }

    public ArrayList getJuveniles()
    {
	return juveniles;
    }

    public void setJuveniles(ArrayList juveniles)
    {
	this.juveniles = juveniles;
    }

    public int getJuvenileCount()
    {
	return juvenileCount;
    }

    public void setJuvenileCount(int juvenileCount)
    {
	this.juvenileCount = juvenileCount;
    }

    public ArrayList getProgrefcomments()
    {
	return progrefcomments;
    }

    public void setProgrefcomments(ArrayList progrefcomments)
    {
	this.progrefcomments = progrefcomments;
    }

    public int getProgrefcommentsCount()
    {
	return progrefcommentsCount;
    }

    public void setProgrefcommentsCount(int progrefcommentsCount)
    {
	this.progrefcommentsCount = progrefcommentsCount;
    }

    public ArrayList getEventreferrals()
    {
	return eventreferrals;
    }

    public void setEventreferrals(ArrayList eventreferrals)
    {
	this.eventreferrals = eventreferrals;
    }

    public int getEventreferralCount()
    {
	return eventreferralCount;
    }

    public void setEventreferralCount(int eventreferralCount)
    {
	this.eventreferralCount = eventreferralCount;
    }

    public ArrayList getCsprogrfasgnhists()
    {
	return progrfasgnhists;
    }

    public void setCsprogrfasgnhists(ArrayList csprogrfasgnhists)
    {
	this.progrfasgnhists = csprogrfasgnhists;
    }

    public int getCsprogrfasgnhistCount()
    {
	return progrfasgnhistCount;
    }

    public void setCsprogrfasgnhistCount(int csprogrfasgnhistCount)
    {
	this.progrfasgnhistCount = csprogrfasgnhistCount;
    }

    public ArrayList getProgrfasgnhists()
    {
	return progrfasgnhists;
    }

    public void setProgrfasgnhists(ArrayList progrfasgnhists)
    {
	this.progrfasgnhists = progrfasgnhists;
    }

    public int getProgrfasgnhistCount()
    {
	return progrfasgnhistCount;
    }

    public void setProgrfasgnhistCount(int progrfasgnhistCount)
    {
	this.progrfasgnhistCount = progrfasgnhistCount;
    }

    public String getJuvprogrefId()
    {
	return juvprogrefId;
    }

    public void setJuvprogrefId(String juvprogrefId)
    {
	this.juvprogrefId = juvprogrefId;
    }

    public String getFromCasefile()
    {
	return fromCasefile;
    }

    public void setFromCasefile(String fromCasefile)
    {
	this.fromCasefile = fromCasefile;
    }

    public String getToCasefile()
    {
	return toCasefile;
    }

    public void setToCasefile(String toCasefile)
    {
	this.toCasefile = toCasefile;
    }

    public ArrayList getTraits()
    {
	return traits;
    }

    public void setTraits(ArrayList traits)
    {
	this.traits = traits;
    }

    public int getTraitCount()
    {
	return traitCount;
    }
    
    public void setSubAbuseInfos(ArrayList subAbuseInfos)
    {
	this.subAbuseInfos = subAbuseInfos;
    }

    public ArrayList getSubAbuseInfos()
    {
	return subAbuseInfos;
    }

    public void setTraitCount(int traitCount)
    {
	this.traitCount = traitCount;
    }
    
    public int getDrugTestingCount()
    {
	return drugTestingCount;
    }

    public void setDrugTestingCount(int drugTestingCount)
    {
	this.drugTestingCount = drugTestingCount;	
    }
    
    public int getSubAbuseCount()
    {
	return subAbuseCount;
    }

    public void setSubAbuseCount(int subAbuseCount)
    {
	this.subAbuseCount = subAbuseCount;	
    }  
        
    public int getSearchTraitCount()
    {
	return searchTraitCount;
    }  
    

    public void setSearchTraitCount(int searchTraitCount)
    {
	this.searchTraitCount = searchTraitCount;
    }
    
    public int getSearchCaseDocumentCount()
    {
	return searchCaseDocumentCount;
    }

    public void setSearchCaseDocumentCount(int searchCaseDocumentCount)
    {
	this.searchCaseDocumentCount = searchCaseDocumentCount;
    }
    
    

    public String getBeginday()
    {
	return beginday;
    }

    public void setBeginday(String beginday)
    {
	this.beginday = beginday;
    }

    public String getBeginmonth()
    {
	return beginmonth;
    }

    public void setBeginmonth(String beginmonth)
    {
	this.beginmonth = beginmonth;
    }

    public String getBeginyear()
    {
	return beginyear;
    }

    public void setBeginyear(String beginyear)
    {
	this.beginyear = beginyear;
    }

    public String getActdate()
    {
	return actdate;
    }

    public void setActdate(String actdate)
    {
	this.actdate = actdate;
    }

    public String getEndday()
    {
	return endday;
    }

    public String getCreatedate()
    {
	return createdate;
    }

    public void setCreatedate(String createdate)
    {
	this.createdate = createdate;
    }

    public String getEnddate()
    {
	return enddate;
    }

    public void setEnddate(String enddate)
    {
	this.enddate = enddate;
    }

    public String getClosingEndDate()
    {
	return closingEndDate;
    }

    public void setClosingEndDate(String closingEndDate)
    {
	this.closingEndDate = closingEndDate;
    }

    public String getRefBeginDate()
    {
	return refBeginDate;
    }

    public void setRefBeginDate(String refBeginDate)
    {
	this.refBeginDate = refBeginDate;
    }

    public String getRefEndDate()
    {
	return refEndDate;
    }

    public void setRefEndDate(String refEndDate)
    {
	this.refEndDate = refEndDate;
    }

    public void setEndday(String endday)
    {
	this.endday = endday;
    }

    public String getEndmonth()
    {
	return endmonth;
    }

    public void setEndmonth(String endmonth)
    {
	this.endmonth = endmonth;
    }

    public String getEndyear()
    {
	return endyear;
    }

    public void setEndyear(String endyear)
    {
	this.endyear = endyear;
    }

    public String getNewBeginDate()
    {
	return newBeginDate;
    }

    public void setNewBeginDate(String newBeginDate)
    {
	this.newBeginDate = newBeginDate;
    }

    public String getNewEndDate()
    {
	return newEndDate;
    }

    public void setNewEndDate(String newEndDate)
    {
	this.newEndDate = newEndDate;
    }

    public String getProgrfasgnhistId()
    {
	return progrfasgnhistId;
    }

    public void setProgrfasgnhistId(String progrfasgnhistId)
    {
	this.progrfasgnhistId = progrfasgnhistId;
    }

    public String getProgrfasgndate()
    {
	return progrfasgndate;
    }

    public void setProgrfasgndate(String progrfasgndate)
    {
	this.progrfasgndate = progrfasgndate;
    }

    public String getActday()
    {
	return actday;
    }

    public void setActday(String actday)
    {
	this.actday = actday;
    }

    public String getActmonth()
    {
	return actmonth;
    }

    public void setActmonth(String actmonth)
    {
	this.actmonth = actmonth;
    }

    public String getActyear()
    {
	return actyear;
    }

    public void setActyear(String actyear)
    {
	this.actyear = actyear;
    }

    public String getNewActDate()
    {
	return newActDate;
    }

    public void setNewActDate(String newActDate)
    {
	this.newActDate = newActDate;
    }

    public String getNewControllingReferral()
    {
	return newControllingReferral;
    }

    public void setNewControllingReferral(String newControllingReferral)
    {
	this.newControllingReferral = newControllingReferral;
    }

    public String getActivityId()
    {
	return activityId;
    }

    public void setActivityId(String activityId)
    {
	this.activityId = activityId;
    }

    public String getNewJpoId()
    {
	return newJpoId;
    }

    public void setNewJpoId(String newJpoId)
    {
	this.newJpoId = newJpoId;
    }

    /**
     * @return the newJpoCode
     */
    public String getNewJpoCode()
    {
	return newJpoCode;
    }

    /**
     * @param newJpoCode
     *            the newJpoCode to set
     */
    public void setNewJpoCode(String newJpoCode)
    {
	this.newJpoCode = newJpoCode;
    }

    public String getAssignmentId()
    {
	return assignmentId;
    }

    public void setAssignmentId(String assignmentId)
    {
	this.assignmentId = assignmentId;
    }

    public String getPreviousJpoId()
    {
	return previousJpoId;
    }

    public void setPreviousJpoId(String previousJpoId)
    {
	this.previousJpoId = previousJpoId;
    }

    /**
     * @return the previousJpoCode
     */
    public String getPreviousJpoCode()
    {
	return previousJpoCode;
    }

    /**
     * @param previousJpoCode
     *            the previousJpoCode to set
     */
    public void setPreviousJpoCode(String previousJpoCode)
    {
	this.previousJpoCode = previousJpoCode;
    }

    public String[] getSelectedHists()
    {
	return selectedHists;
    }

    public void setSelectedHists(String[] selectedHists)
    {
	this.selectedHists = selectedHists;
    }

    public String[] getSelectedAssignments()
    {
	return selectedAssignments;
    }

    public void setSelectedAssignments(String[] selectedAssignments)
    {
	this.selectedAssignments = selectedAssignments;
    }

    public String getServeventId()
    {
	return serveventId;
    }

    public void setServeventId(String serveventId)
    {
	this.serveventId = serveventId;
    }

    public ArrayList getServattends()
    {
	return servattends;
    }

    public void setServattends(ArrayList servattends)
    {
	this.servattends = servattends;
    }

    public int getServattendCount()
    {
	return servattendCount;
    }

    public void setServattendCount(int servattendCount)
    {
	this.servattendCount = servattendCount;
    }

    public ArrayList getAttendstatusCodes()
    {
	return attendstatusCodes;
    }

    public void setAttendstatusCodes(ArrayList attendstatusCodes)
    {
	this.attendstatusCodes = attendstatusCodes;
    }

    public String getAttendstatusBox()
    {
	return attendstatusBox;
    }

    public void setAttendstatusBox(String attendstatusBox)
    {
	this.attendstatusBox = attendstatusBox;
    }

    public String getNewReferralNum()
    {
	return newReferralNum;
    }

    public void setNewReferralNum(String newReferralNum)
    {
	this.newReferralNum = newReferralNum;
    }

    public String getNewAddlAttendees()
    {
	return newAddlAttendees;
    }

    public void setNewAddlAttendees(String newAddlAttendees)
    {
	this.newAddlAttendees = newAddlAttendees;
    }

    public ArrayList getMaysis()
    {
	return maysis;
    }

    public void setMaysis(ArrayList maysis)
    {
	this.maysis = maysis;
    }

    public int getMaysiCount()
    {
	return maysiCount;
    }

    public void setMaysiCount(int maysiCount)
    {
	this.maysiCount = maysiCount;
    }

    public ArrayList getAssessoptionCodes()
    {
	return assessoptionCodes;
    }

    public void setAssessoptionCodes(ArrayList assessoptionCodes)
    {
	this.assessoptionCodes = assessoptionCodes;
    }

    public String getAssessoptionBox()
    {
	return assessoptionBox;
    }

    public void setAssessoptionBox(String assessoptionBox)
    {
	this.assessoptionBox = assessoptionBox;
    }

    public String getMaysiassessmntId()
    {
	return maysiassessmntId;
    }

    public void setMaysiassessmntId(String maysiassessmntId)
    {
	this.maysiassessmntId = maysiassessmntId;
    }

    public ArrayList getMaysidetails()
    {
	return maysidetails;
    }

    public void setMaysidetails(ArrayList maysidetails)
    {
	this.maysidetails = maysidetails;
    }

    public int getMaysidetailCount()
    {
	return maysidetailCount;
    }

    public void setMaysidetailCount(int maysidetailCount)
    {
	this.maysidetailCount = maysidetailCount;
    }

    public String getMaysidetailId()
    {
	return maysidetailId;
    }

    public void setMaysidetailId(String maysidetailId)
    {
	this.maysidetailId = maysidetailId;
    }

    public String getNewPetitionDate()
    {
	return newPetitionDate;
    }

    public void setNewPetitionDate(String newPetitionDate)
    {
	this.newPetitionDate = newPetitionDate;
    }

    public Collection getSupervisionCodes()
    {
	return CodeHelper.getSupervisionTypes();
    }

    public void setSupervisionCodes(CodeTable supervisionCodes)
    {
	this.supervisionCodes = supervisionCodes;
    }

    public String getSupervisionBox()
    {
	return supervisionBox;
    }

    public void setSupervisionBox(String supervisionBox)
    {
	this.supervisionBox = supervisionBox;
    }

    public ArrayList getServevents()
    {
	return servevents;
    }

    public void setServevents(ArrayList servevents)
    {
	this.servevents = servevents;
    }

    public int getServeventCount()
    {
	return serveventCount;
    }

    public void setServeventCount(int serveventCount)
    {
	this.serveventCount = serveventCount;
    }

    public ArrayList getCalevents()
    {
	return calevents;
    }

    public void setCalevents(ArrayList calevents)
    {
	this.calevents = calevents;
    }

    public int getCaleventCount()
    {
	return caleventCount;
    }

    public void setCaleventCount(int caleventCount)
    {
	this.caleventCount = caleventCount;
    }

    public ArrayList getMenuOptions()
    {
	return menuOptions;
    }

    public void setMenuOptions(ArrayList menuOptions)
    {
	this.menuOptions = menuOptions;
    }

    public String getMenuOptionsBox()
    {
	return menuOptionsBox;
    }

    public void setMenuOptionsBox(String menuOptionsBox)
    {
	this.menuOptionsBox = menuOptionsBox;
    }

    public String getMenuOptionsBox2()
    {
	return menuOptionsBox2;
    }

    public void setMenuOptionsBox2(String menuOptionsBox2)
    {
	this.menuOptionsBox2 = menuOptionsBox2;
    }

    public String getMenuOptionsBox3()
    {
	return menuOptionsBox3;
    }

    public void setMenuOptionsBox3(String menuOptionsBox3)
    {
	this.menuOptionsBox3 = menuOptionsBox3;
    }

    public String getMenuOptionsBox4()
    {
	return menuOptionsBox4;
    }

    public void setMenuOptionsBox4(String menuOptionsBox4)
    {
	this.menuOptionsBox4 = menuOptionsBox4;
    }

    public String getMenuOptionsBox5()
    {
	return menuOptionsBox5;
    }

    public void setMenuOptionsBox5(String menuOptionsBox5)
    {
	this.menuOptionsBox5 = menuOptionsBox5;
    }

    public String getMenuOptionsBox6()
    {
	return menuOptionsBox6;
    }

    public void setMenuOptionsBox6(String menuOptionsBox6)
    {
	this.menuOptionsBox6 = menuOptionsBox6;
    }

    public String getMenuOptionsBox7()
    {
	return menuOptionsBox7;
    }

    public void setMenuOptionsBox7(String menuOptionsBox7)
    {
	this.menuOptionsBox7 = menuOptionsBox7;
    }

    public String getMenuOptionsBox12()
    {
	return menuOptionsBox12;
    }

    public String getMenuOptionsBox13()
    {
	return menuOptionsBox13;
    }

    public void setMenuOptionsBox12(String menuOptionsBox12)
    {
	this.menuOptionsBox12 = menuOptionsBox12;
    }

    public String getMenuOptionsBox15()
    {
	return menuOptionsBox15;
    }

    public void setMenuOptionsBox15(String menuOptionsBox15)
    {
	this.menuOptionsBox15 = menuOptionsBox15;
    }

    public String getMenuOptionsBox17()
    {
	return menuOptionsBox17;
    }

    public void setMenuOptionsBox17(String menuOptionsBox17)
    {
	this.menuOptionsBox17 = menuOptionsBox17;
    }

    public String getMenuOptionsBox18()
    {
	return menuOptionsBox18;
    }

    public void setMenuOptionsBox18(String menuOptionsBox18)
    {
	this.menuOptionsBox18 = menuOptionsBox18;
    }

    public ArrayList getOutcomeDescCodes()
    {
	return outcomeDescCodes;
    }

    public void setOutcomeDescCodes(ArrayList outcomeDescCodes)
    {
	this.outcomeDescCodes = outcomeDescCodes;
    }

    public ArrayList getReferralSrcCodes()
    {
	return referralSrcCodes;
    }

    public void setReferralSrcCodes(ArrayList referralSrcCodes)
    {
	this.referralSrcCodes = referralSrcCodes;
    }

    public String getOutcomeDescBox()
    {
	return outcomeDescBox;
    }

    public void setOutcomeDescBox(String outcomeDescBox)
    {
	this.outcomeDescBox = outcomeDescBox;
    }

    public String getNewJuvseqnum()
    {
	return newJuvseqnum;
    }

    public void setNewJuvseqnum(String newJuvseqnum)
    {
	this.newJuvseqnum = newJuvseqnum;
    }

    public String getSchoolhistId()
    {
	return schoolhistId;
    }

    public void setSchoolhistId(String schoolhistId)
    {
	this.schoolhistId = schoolhistId;
    }

    public ArrayList getCurrentGradeCodes()
    {
	return currentGradeCodes;
    }

    public void setCurrentGradeCodes(ArrayList gradeLevelCodes)
    {
	this.currentGradeCodes = gradeLevelCodes;
    }

    public ArrayList getSchoolhists()
    {
	return schoolhists;
    }

    public void setSchoolhists(ArrayList schoolhists)
    {
	this.schoolhists = schoolhists;
    }

    public int getSchoolhistCount()
    {
	return schoolhistCount;
    }

    public void setSchoolhistCount(int schoolhistCount)
    {
	this.schoolhistCount = schoolhistCount;
    }

    public String getCurrentGradeBox()
    {
	return currentGradeBox;
    }

    public void setCurrentGradeBox(String currentGradeBox)
    {
	this.currentGradeBox = currentGradeBox;
    }

    public String getMenuOptionsBox8()
    {
	return menuOptionsBox8;
    }

    public void setMenuOptionsBox8(String menuOptionsBox8)
    {
	this.menuOptionsBox8 = menuOptionsBox8;
    }

    /**
     * @return the menuOptionsBox9
     */
    public String getMenuOptionsBox9()
    {
	return menuOptionsBox9;
    }

    /**
     * @param menuOptionsBox9
     *            the menuOptionsBox9 to set
     */
    public void setMenuOptionsBox9(String menuOptionsBox9)
    {
	this.menuOptionsBox9 = menuOptionsBox9;
    }

    /**
     * @return the menuOptionsBox10
     */
    public String getMenuOptionsBox10()
    {
	return menuOptionsBox10;
    }

    /**
     * @param menuOptionsBox10
     *            the menuOptionsBox10 to set
     */
    public void setMenuOptionsBox10(String menuOptionsBox10)
    {
	this.menuOptionsBox10 = menuOptionsBox10;
    }

    public String getMenuOptionsBox11()
    {
	return menuOptionsBox11;
    }

    public void setMenuOptionsBox11(String menuOptionsBox11)
    {
	this.menuOptionsBox11 = menuOptionsBox11;
    }

    public ArrayList getExitTypeCodes()
    {
	return exitTypeCodes;
    }

    public void setExitTypeCodes(ArrayList exitTypeCodes)
    {
	this.exitTypeCodes = exitTypeCodes;
    }

    public String getExitTypeBox()
    {
	return exitTypeBox;
    }

    public void setExitTypeBox(String exitTypeBox)
    {
	this.exitTypeBox = exitTypeBox;
    }

    public String getCreateday()
    {
	return createday;
    }

    public void setCreateday(String createday)
    {
	this.createday = createday;
    }

    public String getCreatemonth()
    {
	return createmonth;
    }

    public void setCreatemonth(String createmonth)
    {
	this.createmonth = createmonth;
    }

    public String getCreateyear()
    {
	return createyear;
    }

    public void setCreateyear(String createyear)
    {
	this.createyear = createyear;
    }

    public String getNewCreateDate()
    {
	return newCreateDate;
    }

    public void setNewCreateDate(String newCreateDate)
    {
	this.newCreateDate = newCreateDate;
    }

    public ArrayList getAppropGradeCodes()
    {
	return appropGradeCodes;
    }

    public void setAppropGradeCodes(ArrayList appropGradeCodes)
    {
	this.appropGradeCodes = appropGradeCodes;
    }

    public String getAppropGradeBox()
    {
	return appropGradeBox;
    }

    public void setAppropGradeBox(String appropGradeBox)
    {
	this.appropGradeBox = appropGradeBox;
    }

    public ArrayList getGradesRepeatCodes()
    {
	return gradesRepeatCodes;
    }

    public void setGradesRepeatCodes(ArrayList gradesRepeatCodes)
    {
	this.gradesRepeatCodes = gradesRepeatCodes;
    }

    public String getGradesRepeatCodeBox()
    {
	return gradesRepeatCodeBox;
    }

    public void setGradesRepeatCodeBox(String gradesRepeatCodeBox)
    {
	this.gradesRepeatCodeBox = gradesRepeatCodeBox;
    }

    public String getAttday()
    {
	return attday;
    }

    public void setAttday(String attday)
    {
	this.attday = attday;
    }

    public String getAttmonth()
    {
	return attmonth;
    }

    public void setAttmonth(String attmonth)
    {
	this.attmonth = attmonth;
    }

    public String getAttyear()
    {
	return attyear;
    }

    public void setAttyear(String attyear)
    {
	this.attyear = attyear;
    }
    
    public String getLastAttendedDate()
    {
	return lastAttendedDate;
    }
    
    public void setLastAttendedDate(String lastAttendedDate)
    {
	if(lastAttendedDate != null){	   
	    
	    this.lastAttendedDate = lastAttendedDate;
	}
	
    }

    public String getNewAttDate()
    {
	return newAttDate;
    }

    public void setNewAttDate(String newAttDate)
    {
	this.newAttDate = newAttDate;
    }

    public String getNewcasefileId()
    {
	return newcasefileId;
    }

    public void setNewcasefileId(String newcasefileId)
    {
	this.newcasefileId = newcasefileId;
    }

    public ArrayList getCasefiledocs()
    {
	return casefiledocs;
    }

    public void setCasefiledocs(ArrayList casefiledocs)
    {
	this.casefiledocs = casefiledocs;
    }

    public int getCasefiledocCount()
    {
	return casefiledocCount;
    }

    public void setCasefiledocCount(int casefiledocCount)
    {
	this.casefiledocCount = casefiledocCount;
    }

    public String getDocumentId()
    {
	return documentId;
    }

    public void setDocumentId(String documentId)
    {
	this.documentId = documentId;
    }

    public String getDocumentType()
    {
	return documentType;
    }

    public void setDocumentType(String documentType)
    {
	this.documentType = documentType;
    }

    public String getNewMaysineeded()
    {
	return newMaysineeded;
    }

    public void setNewMaysineeded(String newMaysineeded)
    {
	this.newMaysineeded = newMaysineeded;
    }

    public String getOldMaysineeded()
    {
	return oldMaysineeded;
    }

    public void setOldMaysineeded(String oldMaysineeded)
    {
	this.oldMaysineeded = oldMaysineeded;
    }

    public ArrayList getCasedocchildren()
    {
	return casedocchildren;
    }

    public void setCasedocchildren(ArrayList casedocchildren)
    {
	this.casedocchildren = casedocchildren;
    }

    public int getCasedocchildrenCount()
    {
	return casedocchildrenCount;
    }

    public void setCasedocchildrenCount(int casedocchildrenCount)
    {
	this.casedocchildrenCount = casedocchildrenCount;
    }

    public String getCasenoncomnoteId()
    {
	return casenoncomnoteId;
    }

    public void setCasenoncomnoteId(String casenoncomnoteId)
    {
	this.casenoncomnoteId = casenoncomnoteId;
    }

    public String getNewEventMaximum()
    {
	return newEventMaximum;
    }

    public void setNewEventMaximum(String newEventMaximum)
    {
	this.newEventMaximum = newEventMaximum;
    }

    public String getPrevEventMaximum()
    {
	return prevEventMaximum;
    }

    public void setPrevEventMaximum(String prevEventMaximum)
    {
	this.prevEventMaximum = prevEventMaximum;
    }

    public JuvenileCasefile getCasefile()
    {
	return casefile;
    }

    public void setCasefile(JuvenileCasefile casefile)
    {
	this.casefile = casefile;
    }

    public JuvenileCasefileResponseEvent getCasefileDet()
    {
	return casefileDet;
    }

    public void setCasefileDet(JuvenileCasefileResponseEvent casefileDet)
    {
	this.casefileDet = casefileDet;
    }

    public CasefileClosingResponseEvent getCasefileClosing()
    {
	return casefileClosing;
    }

    public void setCasefileClosing(CasefileClosingResponseEvent casefileClosing)
    {
	this.casefileClosing = casefileClosing;
    }

    public CasePlan getCaseplan()
    {
	return caseplan;
    }

    public void setCaseplan(CasePlan caseplan)
    {
	this.caseplan = caseplan;
    }

    public ArrayList getCasefiles()
    {
	return casefiles;
    }

    public void setCasefiles(ArrayList casefiles)
    {
	this.casefiles = casefiles;
    }

    public ArrayList getCaseplans()
    {
	return caseplans;
    }

    public void setCaseplans(ArrayList caseplans)
    {
	this.caseplans = caseplans;
    }

    public String getCalEventId()
    {
	return calEventId;
    }

    public void setCalEventId(String calEventId)
    {
	this.calEventId = calEventId;
    }

    public String getNewActivationDate()
    {
	return newActivationDate;
    }

    public void setNewActivationDate(String newActivationDate)
    {
	this.newActivationDate = newActivationDate;
    }

    public String getNewSupervisionEndDate()
    {
	return newSupervisionEndDate;
    }

    public void setNewSupervisionEndDate(String newSupervisionEndDate)
    {
	this.newSupervisionEndDate = newSupervisionEndDate;
    }

    /**
     * @return the oldAssignmentDate
     */
    public String getOldAssignmentDate()
    {
	return oldAssignmentDate;
    }

    /**
     * @param oldAssignmentDate
     *            the oldAssignmentDate to set
     */
    public void setOldAssignmentDate(String oldAssignmentDate)
    {
	this.oldAssignmentDate = oldAssignmentDate;
    }

    /**
     * @return the oldSupervisionOutcome
     */
    public String getOldSupervisionOutcome()
    {
	return oldSupervisionOutcome;
    }

    /**
     * @param oldSupervisionOutcome
     *            the oldSupervisionOutcome to set
     */
    public void setOldSupervisionOutcome(String oldSupervisionOutcome)
    {
	this.oldSupervisionOutcome = oldSupervisionOutcome;
    }

    /**
     * @return the oldControllingReferral
     */
    public String getOldControllingReferral()
    {
	return oldControllingReferral;
    }

    /**
     * @param oldControllingReferral
     *            the oldControllingReferral to set
     */
    public void setOldControllingReferral(String oldControllingReferral)
    {
	this.oldControllingReferral = oldControllingReferral;
    }

    /**
     * @return the oldSupervisionEndDate
     */
    public Date getOldSupervisionEndDate()
    {
	return oldSupervisionEndDate;
    }

    /**
     * @param oldSupervisionEndDate
     *            the oldSupervisionEndDate to set
     */
    public void setOldSupervisionEndDate(Date oldSupervisionEndDate)
    {
	this.oldSupervisionEndDate = oldSupervisionEndDate;
    }

    /**
     * @return the oldSupervisionOutcomeDescCd
     */
    public String getOldSupervisionOutcomeDescCd()
    {
	return oldSupervisionOutcomeDescCd;
    }

    /**
     * @param oldSupervisionOutcomeDescCd
     *            the oldSupervisionOutcomeDescCd to set
     */
    public void setOldSupervisionOutcomeDescCd(String oldSupervisionOutcomeDescCd)
    {
	this.oldSupervisionOutcomeDescCd = oldSupervisionOutcomeDescCd;
    }

    /**
     * @return the eventStatusCodes
     */
    public ArrayList getEventStatusCodes()
    {
	return eventStatusCodes;
    }

    /**
     * @param eventStatusCodes
     *            the eventStatusCodes to set
     */
    public void setEventStatusCodes(ArrayList eventStatusCodes)
    {
	this.eventStatusCodes = eventStatusCodes;
    }

    /**
     * @return the juvLocationUnitCodes
     */
    public ArrayList getJuvLocationUnitCodes()
    {
	return juvLocationUnitCodes;
    }

    /**
     * @param juvLocationUnitCodes
     *            the juvLocationUnitCodes to set
     */
    public void setJuvLocationUnitCodes(ArrayList juvLocationUnitCodes)
    {
	this.juvLocationUnitCodes = juvLocationUnitCodes;
    }

    /**
     * @return the newEventStatusCd
     */
    public String getNewEventStatusCd()
    {
	return newEventStatusCd;
    }

    /**
     * @param newEventStatusCd
     *            the newEventStatusCd to set
     */
    public void setNewEventStatusCd(String newEventStatusCd)
    {
	this.newEventStatusCd = newEventStatusCd;
    }

    /**
     * @return the newjuvLocationUnitId
     */
    public String getNewJuvLocationUnitId()
    {
	return newJuvLocationUnitId;
    }

    /**
     * @param newjuvLocationUnitId
     *            the newjuvLocationUnitId to set
     */
    public void setNewJuvLocationUnitId(String newJuvLocationUnitId)
    {
	this.newJuvLocationUnitId = newJuvLocationUnitId;
    }

    /**
     * @return the newCalEventStartDate
     */
    public Date getNewCalEventStartDate()
    {
	return newCalEventStartDate;
    }

    /**
     * @param newCalEventStartDate
     *            the newCalEventStartDate to set
     */
    public void setNewCalEventStartDate(Date newCalEventStartDate)
    {
	this.newCalEventStartDate = newCalEventStartDate;
    }

    /**
     * @return the newCalEventEndDate
     */
    public Date getNewCalEventEndDate()
    {
	return newCalEventEndDate;
    }

    /**
     * @param newCalEventEndDate
     *            the newCalEventEndDate to set
     */
    public void setNewCalEventEndDate(Date newCalEventEndDate)
    {
	this.newCalEventEndDate = newCalEventEndDate;
    }

    /**
     * @return the startCalEventDay
     */
    public String getStartCalEventDay()
    {
	return startCalEventDay;
    }

    /**
     * @param startCalEventDay
     *            the startCalEventDay to set
     */
    public void setStartCalEventDay(String startCalEventDay)
    {
	this.startCalEventDay = startCalEventDay;
    }

    /**
     * @return the startCalEventMonth
     */
    public String getStartCalEventMonth()
    {
	return startCalEventMonth;
    }

    /**
     * @param startCalEventMonth
     *            the startCalEventMonth to set
     */
    public void setStartCalEventMonth(String startCalEventMonth)
    {
	this.startCalEventMonth = startCalEventMonth;
    }

    /**
     * @return the startCalEventYear
     */
    public String getStartCalEventYear()
    {
	return startCalEventYear;
    }

    /**
     * @param startCalEventYear
     *            the startCalEventYear to set
     */
    public void setStartCalEventYear(String startCalEventYear)
    {
	this.startCalEventYear = startCalEventYear;
    }

    /**
     * @return the endCalEventDay
     */
    public String getEndCalEventDay()
    {
	return endCalEventDay;
    }

    /**
     * @param endCalEventDay
     *            the endCalEventDay to set
     */
    public void setEndCalEventDay(String endCalEventDay)
    {
	this.endCalEventDay = endCalEventDay;
    }

    /**
     * @return the endCalEventMonth
     */
    public String getEndCalEventMonth()
    {
	return endCalEventMonth;
    }

    /**
     * @param endCalEventMonth
     *            the endCalEventMonth to set
     */
    public void setEndCalEventMonth(String endCalEventMonth)
    {
	this.endCalEventMonth = endCalEventMonth;
    }

    /**
     * @return the endCalEventYear
     */
    public String getEndCalEventYear()
    {
	return endCalEventYear;
    }

    /**
     * @param endCalEventYear
     *            the endCalEventYear to set
     */
    public void setEndCalEventYear(String endCalEventYear)
    {
	this.endCalEventYear = endCalEventYear;
    }

    /**
     * @return the startCalTimeHours
     */
    public String getStartCalTimeHours()
    {
	return startCalTimeHours;
    }

    /**
     * @param startCalTimeHours
     *            the startCalTimeHours to set
     */
    public void setStartCalTimeHours(String startCalTimeHours)
    {
	this.startCalTimeHours = startCalTimeHours;
    }

    /**
     * @return the startCalTimeMinutes
     */
    public String getStartCalTimeMinutes()
    {
	return startCalTimeMinutes;
    }

    /**
     * @param startCalTimeMinutes
     *            the startCalTimeMinutes to set
     */
    public void setStartCalTimeMinutes(String startCalTimeMinutes)
    {
	this.startCalTimeMinutes = startCalTimeMinutes;
    }

    /**
     * @return the endCalTimeHours
     */
    public String getEndCalTimeHours()
    {
	return endCalTimeHours;
    }

    /**
     * @param endCalTimeHours
     *            the endCalTimeHours to set
     */
    public void setEndCalTimeHours(String endCalTimeHours)
    {
	this.endCalTimeHours = endCalTimeHours;
    }

    /**
     * @return the endCalTimeMinutes
     */
    public String getEndCalTimeMinutes()
    {
	return endCalTimeMinutes;
    }

    /**
     * @param endCalTimeMinutes
     *            the endCalTimeMinutes to set
     */
    public void setEndCalTimeMinutes(String endCalTimeMinutes)
    {
	this.endCalTimeMinutes = endCalTimeMinutes;
    }

    /**
     * @return the oldActDate
     */
    public String getOldActDate()
    {
	return oldActDate;
    }

    /**
     * @param oldActDate
     *            the oldActDate to set
     */
    public void setOldActDate(String oldActDate)
    {
	this.oldActDate = oldActDate;
    }

    /**
     * @return the oldCreateDate
     */
    public String getOldCreateDate()
    {
	return oldCreateDate;
    }

    /**
     * @param oldCreateDate
     *            the oldCreateDate to set
     */
    public void setOldCreateDate(String oldCreateDate)
    {
	this.oldCreateDate = oldCreateDate;
    }

    /**
     * @return the oldActivationDate
     */
    public String getOldActivationDate()
    {
	return oldActivationDate;
    }

    /**
     * @param oldActivationDate
     *            the oldActivationDate to set
     */
    public void setOldActivationDate(String oldActivationDate)
    {
	this.oldActivationDate = oldActivationDate;
    }

    /**
     * @return the oldSupervisionEndDateString
     */
    public String getOldSupervisionEndDateString()
    {
	return oldSupervisionEndDateString;
    }

    /**
     * @param oldSupervisionEndDateString
     *            the oldSupervisionEndDateString to set
     */
    public void setOldSupervisionEndDateString(String oldSupervisionEndDateString)
    {
	this.oldSupervisionEndDateString = oldSupervisionEndDateString;
    }

    /**
     * @return the oldJuvseqnum
     */
    public String getOldJuvseqnum()
    {
	return oldJuvseqnum;
    }

    /**
     * @param oldJuvseqnum
     *            the oldJuvseqnum to set
     */
    public void setOldJuvseqnum(String oldJuvseqnum)
    {
	this.oldJuvseqnum = oldJuvseqnum;
    }

    /**
     * @return the juvenileId
     */
    public String getJuvenileId()
    {
	return juvenileId;
    }

    /**
     * @param juvenileId
     *            the juvenileId to set
     */
    public void setJuvenileId(String juvenileId)
    {
	this.juvenileId = juvenileId;
    }

    /**
     * @return the traitId
     */
    public String getTraitId()
    {
	return traitId;
    }

    /**
     * @param traitId
     *            the traitId to set
     */
    public void setTraitId(String traitId)
    {
	this.traitId = traitId;
    }
    
    /**
     * @return the originalTrait
     */
    public JuvenileTraitResponseEvent getOriginalTrait() {
        return originalTrait;
    }

    /**
     * @param originalTrait the originalTrait to set
     */
    public void setOriginalTrait(JuvenileTraitResponseEvent originalTrait) {
        this.originalTrait = originalTrait;
    }
    

    /**
     * @return the sequenceNum
     */
    public String getSequenceNum()
    {
	return sequenceNum;
    }

    /**
     * @param sequenceNum
     *            the sequenceNum to set
     */
    public void setSequenceNum(String sequenceNum)
    {
	this.sequenceNum = sequenceNum;
    }

    /**
     * @return the detentionId
     */
    public String getDetentionId()
    {
	return detentionId;
    }

    /**
     * @param detentionId
     *            the detentionId to set
     */
    public void setDetentionId(String detentionId)
    {
	this.detentionId = detentionId;
    }

    /**
     * @return the facilityHeaderList
     */
    public ArrayList getFacilityHeaderList()
    {
	return facilityHeaderList;
    }

    /**
     * @param facilityHeaderList
     *            the facilityHeaderList to set
     */
    public void setFacilityHeaderList(ArrayList facilityHeaderList)
    {
	this.facilityHeaderList = facilityHeaderList;
    }

    /**
     * @return the facilityDetentionList
     */
    public ArrayList getFacilityDetentionList()
    {
	return facilityDetentionList;
    }

    /**
     * @param facilityDetentionList
     *            the facilityDetentionList to set
     */
    public void setFacilityDetentionList(ArrayList facilityDetentionList)
    {
	this.facilityDetentionList = facilityDetentionList;
    }

    /**
     * @return the facilityHeaderCount
     */
    public int getFacilityHeaderCount()
    {
	return facilityHeaderCount;
    }

    /**
     * @param facilityHeaderCount
     *            the facilityHeaderCount to set
     */
    public void setFacilityHeaderCount(int facilityHeaderCount)
    {
	this.facilityHeaderCount = facilityHeaderCount;
    }

    /**
     * @return the facilityDetentionCount
     */
    public int getFacilityDetentionCount()
    {
	return facilityDetentionCount;
    }

    /**
     * @param facilityDetentionCount
     *            the facilityDetentionCount to set
     */
    public void setFacilityDetentionCount(int facilityDetentionCount)
    {
	this.facilityDetentionCount = facilityDetentionCount;
    }

    /**
     * @return the activeFacilitiesList
     */
    public ArrayList getActiveFacilitiesList()
    {
	return activeFacilitiesList;
    }

    /**
     * @param activeFacilitiesList
     *            the activeFacilitiesList to set
     */
    public void setActiveFacilitiesList(ArrayList activeFacilitiesList)
    {
	this.activeFacilitiesList = activeFacilitiesList;
    }

    /**
     * @return the facilityStatusList
     */
    public ArrayList getFacilityStatusList()
    {
	return facilityStatusList;
    }

    /**
     * @param facilityStatusList
     *            the facilityStatusList to set
     */
    public void setFacilityStatusList(ArrayList facilityStatusList)
    {
	this.facilityStatusList = facilityStatusList;
    }

    /**
     * @return the admitReasonsList
     */
    public ArrayList getAdmitReasonsList()
    {
	return admitReasonsList;
    }

    /**
     * @param admitReasonsList
     *            the admitReasonsList to set
     */
    public void setAdmitReasonsList(ArrayList admitReasonsList)
    {
	this.admitReasonsList = admitReasonsList;
    }

    /**
     * @return the admittanceCodeDesc
     */
    public String getAdmittanceCodeDesc()
    {
	return admittanceCodeDesc;
    }

    /**
     * @param admittanceCodeDesc
     *            the admittanceCodeDesc to set
     */
    public void setAdmittanceCodeDesc(String admittanceCodeDesc)
    {
	this.admittanceCodeDesc = admittanceCodeDesc;
    }

    /**
     * @return the activeFacilityCodeDesc
     */
    public String getActiveFacilityCodeDesc()
    {
	return activeFacilityCodeDesc;
    }

    /**
     * @param activeFacilityCodeDesc
     *            the activeFacilityCodeDesc to set
     */
    public void setActiveFacilityCodeDesc(String activeFacilityCodeDesc)
    {
	this.activeFacilityCodeDesc = activeFacilityCodeDesc;
    }

    /**
     * @return the newadmitReasonCd
     */
    public String getNewadmitReasonCd()
    {
	return newadmitReasonCd;
    }

    /**
     * @param newadmitReasonCd
     *            the newadmitReasonCd to set
     */
    public void setNewadmitReasonCd(String newadmitReasonCd)
    {
	this.newadmitReasonCd = newadmitReasonCd;
    }

    /**
     * @return the activeFacilityCd
     */
    public String getActiveFacilityCd()
    {
	return activeFacilityCd;
    }

    /**
     * @param activeFacilityCd
     *            the activeFacilityCd to set
     */
    public void setActiveFacilityCd(String activeFacilityCd)
    {
	this.activeFacilityCd = activeFacilityCd;
    }

    /**
     * @return the newHeaderFacilityCd
     */
    public String getNewHeaderFacilityCd()
    {
	return newHeaderFacilityCd;
    }

    /**
     * @param newHeaderFacilityCd
     *            the newHeaderFacilityCd to set
     */
    public void setNewHeaderFacilityCd(String newHeaderFacilityCd)
    {
	this.newHeaderFacilityCd = newHeaderFacilityCd;
    }

    public String getNewTransferToFacility()
    {
	return newTransferToFacility;
    }

    public void setNewTransferToFacility(String newTransferToFacility)
    {
	this.newTransferToFacility = newTransferToFacility;
    }

    public String getAdmitBy()
    {
	return admitBy;
    }

    public void setAdmitBy(String admitBy)
    {
	this.admitBy = admitBy;
    }

    public String getDetainedDate()
    {
	return detainedDate;
    }

    public void setDetainedDate(String detainedDate)
    {
	this.detainedDate = detainedDate;
    }

    public String getBookingReferral()
    {
	return bookingReferral;
    }

    public void setBookingReferral(String bookingReferral)
    {
	this.bookingReferral = bookingReferral;
    }

    public String getCurrentReferral()
    {
	return currentReferral;
    }

    public void setCurrentReferral(String currentReferral)
    {
	this.currentReferral = currentReferral;
    }

    public String getAdmitDate()
    {
	return admitDate;
    }

    public void setAdmitDate(String admitDate)
    {
	this.admitDate = admitDate;
    }

    public String getAdmitTime()
    {
	return admitTime;
    }

    public void setAdmitTime(String admitTime)
    {
	this.admitTime = admitTime;
    }

    public String getOriginalAdmitDate()
    {
	return originalAdmitDate;
    }

    public void setOriginalAdmitDate(String originalAdmitDate)
    {
	this.originalAdmitDate = originalAdmitDate;
    }

    public String getOriginalAdmitTime()
    {
	return originalAdmitTime;
    }

    public void setOriginalAdmitTime(String originalAdmitTime)
    {
	this.originalAdmitTime = originalAdmitTime;
    }

    public String getReleaseDate()
    {
	return releaseDate;
    }

    public void setReleaseDate(String releaseDate)
    {
	this.releaseDate = releaseDate;
    }

    public String getReleaseTime()
    {
	return releaseTime;
    }

    public void setReleaseTime(String releaseTime)
    {
	this.releaseTime = releaseTime;
    }

    public String getReturnDate()
    {
	return returnDate;
    }

    public void setReturnDate(String returnDate)
    {
	this.returnDate = returnDate;
    }

    public String getReturnTime()
    {
	return returnTime;
    }

    public void setReturnTime(String returnTime)
    {
	this.returnTime = returnTime;
    }

    public String getBookingSupervisionNum()
    {
	return bookingSupervisionNum;
    }

    public void setBookingSupervisionNum(String bookingSupervisionNum)
    {
	this.bookingSupervisionNum = bookingSupervisionNum;
    }

    public String getCurrentSupervisionNum()
    {
	return currentSupervisionNum;
    }

    public void setCurrentSupervisionNum(String currentSupervisionNum)
    {
	this.currentSupervisionNum = currentSupervisionNum;
    }

    public String getCurrentOffense()
    {
	return currentOffense;
    }

    public void setCurrentOffense(String currentOffense)
    {
	this.currentOffense = currentOffense;
    }

    public String getFacilityStatus()
    {
	return facilityStatus;
    }

    public void setFacilityStatus(String facilityStatus)
    {
	this.facilityStatus = facilityStatus;
    }

    public String getValuablesReceipt()
    {
	return valuablesReceipt;
    }

    public void setValuablesReceipt(String valuablesReceipt)
    {
	this.valuablesReceipt = valuablesReceipt;
    }

    public String getAdmitAuthority()
    {
	return admitAuthority;
    }

    public void setAdmitAuthority(String admitAuthority)
    {
	this.admitAuthority = admitAuthority;
    }

    public String getLocker()
    {
	return locker;
    }

    public void setLocker(String locker)
    {
	this.locker = locker;
    }

    public String getOutcome()
    {
	return outcome;
    }

    public void setOutcome(String outcome)
    {
	this.outcome = outcome;
    }

    public Collection getReleaseTo()
    {
	return releaseTo;
    }

    public void setReleaseTo(Collection releaseTo)
    {
	this.releaseTo = releaseTo;
    }

    public String getReleasedTo()
    {
	return releasedTo;
    }

    public void setReleasedTo(String releasedTo)
    {
	this.releasedTo = releasedTo;
    }

    public String getReleaseReason()
    {
	return releaseReason;
    }

    public void setReleaseReason(String releaseReason)
    {
	this.releaseReason = releaseReason;
    }

    public String getReturnReason()
    {
	return returnReason;
    }

    public void setReturnReason(String returnReason)
    {
	this.returnReason = returnReason;
    }

    public Collection<CodeResponseEvent> getReturnReasons()
    {
	return returnReasons;
    }

    public void setReturnReasons(Collection<CodeResponseEvent> returnReasons)
    {
	this.returnReasons = returnReasons;
    }

    public String getReleaseAuthority()
    {
	return releaseAuthority;
    }

    public void setReleaseAuthority(String releaseAuthority)
    {
	this.releaseAuthority = releaseAuthority;
    }

    public String getReleasedBy()
    {
	return releasedBy;
    }

    public void setReleasedBy(String releasedBy)
    {
	this.releasedBy = releasedBy;
    }

    public String getTempReleaseReason()
    {
	return tempReleaseReason;
    }

    public void setTempReleaseReason(String tempReleaseReason)
    {
	this.tempReleaseReason = tempReleaseReason;
    }

    public Collection<CodeResponseEvent> getTempReleaseReasons()
    {
	return tempReleaseReasons;
    }

    public void setTempReleaseReasons(Collection<CodeResponseEvent> tempReleaseReasons)
    {
	this.tempReleaseReasons = tempReleaseReasons;
    }

    public String getFacilitySeqNum()
    {
	return facilitySeqNum;
    }

    public void setFacilitySeqNum(String facilitySeqNum)
    {
	this.facilitySeqNum = facilitySeqNum;
    }

    public JuvenileFacilityHeaderResponseEvent getHeaderInfo()
    {
	return headerInfo;
    }

    public void setHeaderInfo(JuvenileFacilityHeaderResponseEvent headerInfo)
    {
	this.headerInfo = headerInfo;
    }

    public Date getBirthDate()
    {
	return birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
	this.birthDate = birthDate;
    }
    
    public String getRealDOB()
    {
	return this.realDOB;
    } 
    
    public void setRealDOB(String realDOB)
    {
	this.realDOB = realDOB;

    } 

    public String getJuvenileName()
    {
	return juvenileName;
    }

    public void setJuvenileName(String juvenileName)
    {
	this.juvenileName = juvenileName;
    }

    public String getJuvenileSsn()
    {
	return juvenileSsn;
    }

    public void setJuvenileSsn(String juvenileSsn)
    {
	this.juvenileSsn = juvenileSsn;
    }

    public String getStatusId()
    {
	return statusId;
    }

    public void setStatusId(String statusId)
    {
	this.statusId = statusId;
    }

    public String getRectype()
    {
	return rectype;
    }

    public void setRectype(String rectype)
    {
	this.rectype = rectype;
    }

    public ArrayList getBookingReferrals()
    {
	return bookingReferrals;
    }

    public void setBookingReferrals(ArrayList bookingReferrals)
    {
	this.bookingReferrals = bookingReferrals;
    }

    public ArrayList getBookingSprvisionNumbers()
    {
	return bookingSprvisionNumbers;
    }

    public void setBookingSprvisionNumbers(ArrayList bookingSprvisionNumbers)
    {
	this.bookingSprvisionNumbers = bookingSprvisionNumbers;
    }

    public String getCourtDate()
    {
	return courtDate;
    }

    public void setCourtDate(String courtDate)
    {
	this.courtDate = courtDate;
    }

    public Collection<DocketEventResponseEvent> getDktSearchResults()
    {
	return dktSearchResults;
    }

    public void setDktSearchResults(Collection<DocketEventResponseEvent> dktSearchResults)
    {
	this.dktSearchResults = dktSearchResults;
    }

    public void setSearchResultSize(int searchResultSize)
    {
	this.searchResultSize = searchResultSize;
    }

    public int getSearchResultSize()
    {
	return searchResultSize;
    }

    public ArrayList getJuvDistCourtRecords()
    {
	return juvDistCourtRecords;
    }

    public void setJuvDistCourtRecords(ArrayList juvDistCourtRecords)
    {
	this.juvDistCourtRecords = juvDistCourtRecords;
    }

    public Collection<JuvenileDispositionCodeResponseEvent> getCourtDecisionsResponses()
    {
	return courtDecisionsResponses;
    }

    public void setCourtDecisionsResponses(Collection<JuvenileDispositionCodeResponseEvent> courtDecisionsResponses)
    {
	this.courtDecisionsResponses = courtDecisionsResponses;
    }

    public Collection<JuvenileDispositionCodeResponseEvent> getDetentionHearingResults()
    {
	return detentionHearingResults;
    }

    public void setDetentionHearingResults(Collection<JuvenileDispositionCodeResponseEvent> detentionHearingResults)
    {
	this.detentionHearingResults = detentionHearingResults;
    }

    /*public List<JuvenileCourtDecisionCodeResponseEvent> getDetentionHearingResults()
    {
        return detentionHearingResults;
    }

    public void setDetentionHearingResults(List<JuvenileCourtDecisionCodeResponseEvent> detentionHearingResults)
    {
        this.detentionHearingResults = detentionHearingResults;
    }*/
    public Collection<JuvenileHearingTypeResponseEvent> getCourtHearingTypes()
    {
	return courtHearingTypes;
    }

    public void setCourtHearingTypes(Collection<JuvenileHearingTypeResponseEvent> courtHearingTypes)
    {
	this.courtHearingTypes = courtHearingTypes;
    }

    public String getValidateMsg()
    {
	return validateMsg;
    }

    public void setValidateMsg(String validateMsg)
    {
	this.validateMsg = validateMsg;
    }

    public String getCursorPosition()
    {
	return cursorPosition;
    }

    public void setCursorPosition(String cursorPosition)
    {
	this.cursorPosition = cursorPosition;
    }

    public Collection<AttorneyNameAndAddressResponseEvent> getAttorneyDataList()
    {
	return attorneyDataList;
    }

    public void setAttorneyDataList(Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList)
    {
	this.attorneyDataList = attorneyDataList;
    }

    public String getAttorneyName()
    {
	return attorneyName;
    }

    public void setAttorneyName(String attorneyName)
    {
	this.attorneyName = attorneyName;
    }

    public String getErrMessage()
    {
	return errMessage;
    }

    public void setErrMessage(String errMessage)
    {
	this.errMessage = errMessage;
    }

    public String getSelectedValue()
    {
	return selectedValue;
    }

    public void setSelectedValue(String selectedValue)
    {
	this.selectedValue = selectedValue;
    }

    public Map<String, DocketEventResponseEvent> getDktSearchResultsMap()
    {
	return dktSearchResultsMap;
    }

    public void setDktSearchResultsMap(Map<String, DocketEventResponseEvent> dktSearchResultsMap)
    {
	this.dktSearchResultsMap = dktSearchResultsMap;
    }

    public Map<String, DocketEventResponseEvent> getUpdateDocketMap()
    {
	return updateDocketMap;
    }

    public void setUpdateDocketMap(Map<String, DocketEventResponseEvent> updateDocketMap)
    {
	this.updateDocketMap = updateDocketMap;
    }

    public String getHiddenForward()
    {
	return hiddenForward;
    }

    public void setHiddenForward(String hiddenForward)
    {
	this.hiddenForward = hiddenForward;
    }

    public Collection getCourtTypes()
    {
	return courtTypes;
    }

    public void setCourtTypes(Collection courtTypes)
    {
	this.courtTypes = courtTypes;
    }

    public String getSex()
    {
	return sex;
    }

    public void setSex(String sex)
    {
	this.sex = sex;
    }

    public String getRace()
    {
	return race;
    }

    public void setRace(String race)
    {
	this.race = race;
    }

    public String getOriginalRace()
    {
	return originalRace;
    }

    public void setOriginalRace(String originalRace)
    {
	this.originalRace = originalRace;
    }

    public String getHispanic()
    {
	return hispanic;
    }

    public void setHispanic(String hispanic)
    {
	this.hispanic = hispanic;
    }

    public String getMenuOptionsBox14()
    {
	return menuOptionsBox14;
    }

    public void setMenuOptionsBox14(String menuOptionsBox14)
    {
	this.menuOptionsBox14 = menuOptionsBox14;
    }

    public String getMenuOptionsBox16()
    {
	return menuOptionsBox16;
    }

    public void setMenuOptionsBox16(String menuOptionsBox16)
    {
	this.menuOptionsBox16 = menuOptionsBox16;
    }

    /**
     * @return the newSSN
     */
    public SocialSecurity getNewSSN()
    {
	return newSSN;
    }

    /**
     * @param newSSN
     *            the newSSN to set
     */
    public void setNewSSN(SocialSecurity newSSN)
    {
	this.newSSN = newSSN;
    }

    public String getPurgeBoxNum()
    {
	return purgeBoxNum;
    }

    public void setPurgeBoxNum(String purgeBoxNum)
    {
	this.purgeBoxNum = purgeBoxNum;
    }

    public String getPurgeDate()
    {
	return purgeDate;
    }

    public void setPurgeDate(String purgeDate)
    {
	this.purgeDate = purgeDate;
    }

    public String getPurgeSerNum()
    {
	return purgeSerNum;
    }

    public void setPurgeSerNum(String purgeSerNum)
    {
	this.purgeSerNum = purgeSerNum;
    }

    public String getCaseNotePart1()
    {
	return caseNotePart1;
    }

    public void setCaseNotePart1(String caseNotePart1)
    {
	this.caseNotePart1 = caseNotePart1;
    }

    /**
     * @return the purgeFlag
     */
    public String getPurgeFlag()
    {
	return purgeFlag;
    }

    /**
     * @param purgeFlag
     *            the purgeFlag to set
     */
    public void setPurgeFlag(String purgeFlag)
    {
	this.purgeFlag = purgeFlag;
    }

    /**
     * @return the detentionFacilityId
     */
    public String getDetentionFacilityId()
    {
	return detentionFacilityId;
    }

    /**
     * @param detentionFacilityId
     *            the detentionFacilityId to set
     */
    public void setDetentionFacilityId(String detentionFacilityId)
    {
	this.detentionFacilityId = detentionFacilityId;
    }

    /**
     * @return the detentionStatusId
     */
    public String getDetentionStatusId()
    {
	return detentionStatusId;
    }

    /**
     * @param detentionStatusId
     *            the detentionStatusId to set
     */
    public void setDetentionStatusId(String detentionStatusId)
    {
	this.detentionStatusId = detentionStatusId;
    }

    /**
     * @return the checkedOutTo
     */
    public String getCheckedOutTo()
    {
	return checkedOutTo;
    }

    /**
     * @param checkedOutTo
     *            the checkedOutTo to set
     */
    public void setCheckedOutTo(String checkedOutTo)
    {
	this.checkedOutTo = checkedOutTo;
    }

    /**
     * @return the checkedOutDate
     */
    public String getCheckedOutDate()
    {
	return checkedOutDate;
    }

    /**
     * @param checkedOutDate
     *            the checkedOutDate to set
     */
    public void setCheckedOutDate(String checkedOutDate)
    {
	this.checkedOutDate = checkedOutDate;
    }

    public String getJuvenileFName()
    {
	return juvenileFName;
    }

    public void setJuvenileFName(String juvenileFName)
    {
	this.juvenileFName = juvenileFName;
    }

    public String getJuvenileMName()
    {
	return juvenileMName;
    }

    public void setJuvenileMName(String juvenileMName)
    {
	this.juvenileMName = juvenileMName;
    }

    public String getJuvenileLName()
    {
	return juvenileLName;
    }

    public void setJuvenileLName(String juvenileLName)
    {
	this.juvenileLName = juvenileLName;
    }

    /**
     * @return the juvenileNameSuffix
     */
    public String getJuvenileNameSuffix()
    {
	return juvenileNameSuffix;
    }

    /**
     * @param juvenileNameSuffix
     *            the juvenileNameSuffix to set
     */
    public void setJuvenileNameSuffix(String juvenileNameSuffix)
    {
	this.juvenileNameSuffix = juvenileNameSuffix;
    }

    /**
     * @return the lastReferral
     */
    public String getLastReferral()
    {
	return lastReferral;
    }

    /**
     * @param lastReferral
     *            the lastReferral to set
     */
    public void setLastReferral(String lastReferral)
    {
	this.lastReferral = lastReferral;
    }

    /**
     * @return the raceList
     */
    public Collection getRaceList()
    {
	return raceList;
    }

    /**
     * @param raceList
     *            the raceList to set
     */
    public void setRaceList(Collection raceList)
    {
	ProdSupportForm.raceList = raceList;
    }

    /**
     * @return the originalRaceList
     */
    public Collection getOriginalRaceList()
    {
	return originalRaceList;
    }

    /**
     * @param raceList
     *            the originalRaceList to set
     */
    public void setOriginalRaceList(Collection originalRaceList)
    {
	ProdSupportForm.originalRaceList = originalRaceList;
    }

    /**
     * @return the sexList
     */
    public Collection getSexList()
    {
	return sexList;
    }

    /**
     * @param sexList
     *            the sexList to set
     */
    public void setSexList(Collection sexList)
    {
	ProdSupportForm.sexList = sexList;
    }

    /**
     * @return the raceId
     */
    public String getRaceId()
    {
	return raceId;
    }

    /**
     * @param raceId
     *            the raceId to set
     */
    public void setRaceId(String raceId)
    {
	this.raceId = raceId;
    }

    /**
     * @return the originalRaceId
     */
    public String getOriginalRaceId()
    {
	return originalRaceId;
    }

    /**
     * @param originalRace
     *            the originalRaceId to set
     */
    public void setOriginalRaceId(String originalRaceId)
    {
	this.originalRaceId = originalRaceId;
    }

    /**
     * @return the sexId
     */
    public String getSexId()
    {
	return sexId;
    }

    /**
     * @param sexId
     *            the sexId to set
     */
    public void setSexId(String sexId)
    {
	this.sexId = sexId;
    }

    /**
     * @return the masterDOB
     */
    public String getMasterDOB()
    {
	return masterDOB;
    }

    /**
     * @param masterDOB
     *            the masterDOB to set
     */
    public void setMasterDOB(String masterDOB)
    {
	this.masterDOB = masterDOB;
    }

    /**
     * @return the oldStatusId
     */
    public String getOldStatusId()
    {
	return oldStatusId;
    }

    /**
     * @param oldStatusId
     *            the oldStatusId to set
     */
    public void setOldStatusId(String oldStatusId)
    {
	this.oldStatusId = oldStatusId;
    }

    public String getSealedDate()
    {
	return sealedDate;
    }

    public void setSealedDate(String sealedDate)
    {
	this.sealedDate = sealedDate;
    }

    public String getSealComments()
    {
	return sealComments;
    }

    public void setSealComments(String sealComments)
    {
	this.sealComments = sealComments;
    }
    
    public String getDelComments()
    {
	return delComments;
    }

    public void setDelComments(String delComments)
    {
	this.delComments = delComments;
    }

    public int getHdnCount()
    {
	return hdnCount;
    }

    public void setHdnCount(int hdnCount)
    {
	this.hdnCount = hdnCount;
    }

    public String getHasActiveReferral()
    {
	return hasActiveReferral;
    }

    public void setHasActiveReferral(String hasActiveReferral)
    {
	this.hasActiveReferral = hasActiveReferral;
    }

    public String getHasActiveCasefile()
    {
	return hasActiveCasefile;
    }

    public void setHasActiveCasefile(String hasActiveCasefile)
    {
	this.hasActiveCasefile = hasActiveCasefile;
    }

    public String getHasActiveFacility()
    {
	return hasActiveFacility;
    }

    public void setHasActiveFacility(String hasActiveFacility)
    {
	this.hasActiveFacility = hasActiveFacility;
    }

    /**
     * @return the lctime
     */
    public String getLctime()
    {
	return lctime;
    }

    /**
     * @param lctime
     *            the lctime to set
     */
    public void setLctime(String lctime)
    {
	this.lctime = lctime;
    }

    public int getJuvRefOffensesCount()
    {
	return juvRefOffensesCount;
    }

    public void setJuvRefOffensesCount(int juvRefOffensesCount)
    {
	this.juvRefOffensesCount = juvRefOffensesCount;
    }

    public ArrayList getReferralOffenses()
    {
	return referralOffenses;
    }

    public void setReferralOffenses(ArrayList referralOffenses)
    {
	this.referralOffenses = referralOffenses;
    }

    public String getReferralDate()
    {
	return referralDate;
    }

    public void setReferralDate(String referralDate)
    {
	this.referralDate = referralDate;
    }

    public Collection<JuvenileCasefileOffenseCodeResponseEvent> getCodetableDataList()
    {
	return codetableDataList;
    }

    public void setCodetableDataList(Collection<JuvenileCasefileOffenseCodeResponseEvent> codetableDataList)
    {
	this.codetableDataList = codetableDataList;
    }

    public String getAlphaCodeId()
    {
	return alphaCodeId;
    }

    public void setAlphaCodeId(String alphaCodeId)
    {
	this.alphaCodeId = alphaCodeId;
    }

    public String getShortDesc()
    {
	return shortDesc;
    }

    public void setShortDesc(String shortDesc)
    {
	this.shortDesc = shortDesc;
    }

    public String getDpsCodeId()
    {
	return dpsCodeId;
    }

    public void setDpsCodeId(String dpsCodeId)
    {
	this.dpsCodeId = dpsCodeId;
    }

    public String getCategoryId()
    {
	return categoryId;
    }

    public void setCategoryId(String categoryId)
    {
	this.categoryId = categoryId;
    }

    public List getOffenseResultList()
    {
	return offenseResultList;
    }

    public void setOffenseResultList(List offenseResultList)
    {
	this.offenseResultList = offenseResultList;
    }

    public Collection<PetitionResponseEvent> getPetitionDetails()
    {
	return petitionDetails;
    }

    public void setPetitionDetails(Collection<PetitionResponseEvent> petitionDetails)
    {
	this.petitionDetails = petitionDetails;
    }

    public JJSJuvenileResponseEvent getJuvenileDetail()
    {
	return juvenileDetail;
    }

    public void setJuvenileDetail(JJSJuvenileResponseEvent juvenileDetail)
    {
	this.juvenileDetail = juvenileDetail;
    }

    public Collection<ProductionSupportJuvenileReferralResponseEvent> getReferralDetails()
    {
	return referralDetails;
    }

    public void setReferralDetails(Collection<ProductionSupportJuvenileReferralResponseEvent> referralDetails)
    {
	this.referralDetails = referralDetails;
    }

    public String getPetitionStatus()
    {
	return petitionStatus;
    }

    public void setPetitionStatus(String petitionStatus)
    {
	this.petitionStatus = petitionStatus;
    }

    public String getPetitionType()
    {
	return petitionType;
    }

    public void setPetitionType(String petitionType)
    {
	this.petitionType = petitionType;
    }

    public String getPetitionAmended()
    {
	return petitionAmended;
    }

    public void setPetitionAmended(String petitionAmended)
    {
	this.petitionAmended = petitionAmended;
    }

    public String getPetitionAllegation()
    {
	return petitionAllegation;
    }

    public void setPetitionAllegation(String petitionAllegation)
    {
	this.petitionAllegation = petitionAllegation;
    }

    public ArrayList<DocketEventResponseEvent> getJuvDetCourtRecords()
    {
	return juvDetCourtRecords;
    }

    public void setJuvDetCourtRecords(ArrayList<DocketEventResponseEvent> juvDetCourtRecords)
    {
	this.juvDetCourtRecords = juvDetCourtRecords;
    }

    public String getChainNum()
    {
	return chainNum;
    }

    public void setChainNum(String chainNum)
    {
	this.chainNum = chainNum;
    }

    public String getPetitionSeverity()
    {
	return petitionSeverity;
    }

    public void setPetitionSeverity(String petitionSeverity)
    {
	this.petitionSeverity = petitionSeverity;
    }

    public String getLastChangeDate()
    {
	return lastChangeDate;
    }

    public void setLastChangeDate(String lastChangeDate)
    {
	this.lastChangeDate = lastChangeDate;
    }

    public String getLastChangeUser()
    {
	return lastChangeUser;
    }

    public void setLastChangeUser(String lastChangeUser)
    {
	this.lastChangeUser = lastChangeUser;
    }

    public void setMenuOptionsBox13(String menuOptionsBox13)
    {
	this.menuOptionsBox13 = menuOptionsBox13;
    }

    public ArrayList getOffenseCodes()
    {
	return offenseCodes;
    }

    public void setOffenseCodes(ArrayList offenseCodes)
    {
	this.offenseCodes = offenseCodes;
    }

    public String getOffCode()
    {
	return offCode;
    }

    public void setOffCode(String offCode)
    {
	this.offCode = offCode;
    }

    public String getNewReferralID()
    {
	return newReferralID;
    }

    public void setNewReferralID(String newReferralID)
    {
	this.newReferralID = newReferralID;
    }

    public String getStatusFlag()
    {
	return statusFlag;
    }

    public void setStatusFlag(String statusFlag)
    {
	this.statusFlag = statusFlag;
    }

    public JJSLastAttorney getAttRecord()
    {
	return attRecord;
    }

    public void setAttRecord(JJSLastAttorney attRecord)
    {
	this.attRecord = attRecord;
    }

    public String getAction()
    {
	return action;
    }

    public void setAction(String action)
    {
	this.action = action;
    }

    public String getAttorneyConnection()
    {
	return attorneyConnection;
    }

    public void setAttorneyConnection(String attorneyConnection)
    {
	this.attorneyConnection = attorneyConnection;
    }

    public String getAtyBarNum()
    {
	return atyBarNum;
    }

    public void setAtyBarNum(String atyBarNum)
    {
	this.atyBarNum = atyBarNum;
    }

    public String getAtyName()
    {
	return atyName;
    }

    public void setAtyName(String atyName)
    {
	this.atyName = atyName;
    }

    public String getGalBarNum()
    {
	return galBarNum;
    }

    public void setGalBarNum(String galBarNum)
    {
	this.galBarNum = galBarNum;
    }

    public String getGalAttorneyName()
    {
	return galAttorneyName;
    }

    public void setGalAttorneyName(String galAttorneyName)
    {
	this.galAttorneyName = galAttorneyName;
    }

    public String getNewRiskNeedLvlId()
    {
	return newRiskNeedLvlId;
    }

    public void setNewRiskNeedLvlId(String newRiskNeedLvlId)
    {
	this.newRiskNeedLvlId = newRiskNeedLvlId;
    }

    public String getNewCaseID()
    {
	return newCaseID;
    }

    public void setNewCaseID(String newCaseID)
    {
	this.newCaseID = newCaseID;
    }

    public String getPactDate()
    {
	return pactDate;
    }

    public void setPactDate(String pactDate)
    {
	this.pactDate = pactDate;
    }

    public String getNewPactStatus()
    {
	return newPactStatus;
    }

    public String getPactId()
    {
        return pactId;
    }

    public void setPactId(String pactId)
    {
        this.pactId = pactId;
    }

    public String getNewPactId()
    {
        return newPactId;
    }

    public void setNewPactId(String newPactId)
    {
        this.newPactId = newPactId;
    }

    public void setNewPactStatus(String newPactStatus)
    {
	this.newPactStatus = newPactStatus;
    }

    public String getNewPactDate()
    {
	return newPactDate;
    }

    public void setNewPactDate(String newPactDate)
    {
	this.newPactDate = newPactDate;
    }

    public String getPactStatus()
    {
	return pactStatus;
    }

    public void setPactStatus(String pactStatus)
    {
	this.pactStatus = pactStatus;
    }

    public String getNeedValue()
    {
	return needValue;
    }

    public void setNeedValue(String needValue)
    {
	this.needValue = needValue;
    }

    public String getRiskValue()
    {
	return riskValue;
    }

    public void setRiskValue(String riskValue)
    {
	this.riskValue = riskValue;
    }

    public String getRiskLevel()
    {
	return riskLevel;
    }

    public void setRiskLevel(String riskLevel)
    {
	this.riskLevel = riskLevel;
    }

    public String getNeedLevel()
    {
	return needLevel;
    }

    public void setNeedLevel(String needLevel)
    {
	this.needLevel = needLevel;
    }

    public ArrayList getPactReferrals()
    {
	return pactReferrals;
    }

    public void setPactReferrals(ArrayList pactReferrals)
    {
	this.pactReferrals = pactReferrals;
    }

    public String getRiskneedID()
    {
	return riskneedID;
    }

    public void setRiskneedID(String riskneedID)
    {
	this.riskneedID = riskneedID;
    }

    public PACTRiskNeedLevel getPactRecord()
    {
	return pactRecord;
    }

    public void setPactRecord(PACTRiskNeedLevel pactRecord)
    {
	this.pactRecord = pactRecord;
    }

    public String getPetitionNum()
    {
	return petitionNum;
    }

    public void setPetitionNum(String petitionNum)
    {
	this.petitionNum = petitionNum;
    }

    public String getLastChangeTime()
    {
	return lastChangeTime;
    }

    public void setLastChangeTime(String lastChangeTime)
    {
	this.lastChangeTime = lastChangeTime;
    }

    public String getOID()
    {
	return OID;
    }

    public void setOID(String oID)
    {
	OID = oID;
    }

    public String getSequenceNumber()
    {
	return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber)
    {
	this.sequenceNumber = sequenceNumber;
    }

    public String getReferralOID()
    {
	return referralOID;
    }

    public void setReferralOID(String referralOID)
    {
	this.referralOID = referralOID;
    }

    public String getReturnStatus()
    {
	return returnStatus;
    }

    public void setReturnStatus(String returnStatus)
    {
	this.returnStatus = returnStatus;
    }

    public List<JuvenileRefAssgnmtResponseEvent> getReferralAssignmentCodes()
    {
	return referralAssignmentCodes;
    }

    public void setReferralAssignmentCodes(List<JuvenileRefAssgnmtResponseEvent> referralAssignmentCodes)
    {
	this.referralAssignmentCodes = referralAssignmentCodes;
    }

    public String getReferralAssmntType()
    {
	return referralAssmntType;
    }

    public void setReferralAssmntType(String referralAssmntType)
    {
	this.referralAssmntType = referralAssmntType;
    }

    public String getRefSeqNum()
    {
	return refSeqNum;
    }

    public void setRefSeqNum(String refSeqNum)
    {
	this.refSeqNum = refSeqNum;
    }

    public String getOldRefSeqNum()
    {
	return oldRefSeqNum;
    }

    public void setOldRefSeqNum(String oldRefSeqNum)
    {
	this.oldRefSeqNum = oldRefSeqNum;
    }

    public String getOldRefAssmntType()
    {
	return oldRefAssmntType;
    }

    public void setOldRefAssmntType(String oldRefAssmntType)
    {
	this.oldRefAssmntType = oldRefAssmntType;
    }

    public List<JuvenileCourtDecisionCodeResponseEvent> getHearingDecisionResults()
    {
	return hearingDecisionResults;
    }

    public void setHearingDecisionResults(List<JuvenileCourtDecisionCodeResponseEvent> hearingDecisionResults)
    {
	this.hearingDecisionResults = hearingDecisionResults;
    }

    public List getIntakeHistoryRecords()
    {
	return intakeHistoryRecords;
    }

    public void setIntakeHistoryRecords(List intakeHistoryRecords)
    {
	this.intakeHistoryRecords = intakeHistoryRecords;
    }

    public String getIntakeHistoryId()
    {
	return intakeHistoryId;
    }

    public void setIntakeHistoryId(String intakeHistoryId)
    {
	this.intakeHistoryId = intakeHistoryId;
    }

    public JJSSVIntakeHistory getIntakeHistoryRecord()
    {
	return intakeHistoryRecord;
    }

    public void setIntakeHistoryRecord(JJSSVIntakeHistory intakeHistoryRecord)
    {
	this.intakeHistoryRecord = intakeHistoryRecord;
    }

    public JJSSVIntakeHistory getOriginalIntakeHistoryRecord()
    {
	return originalIntakeHistoryRecord;
    }

    public void setOriginalIntakeHistoryRecord(JJSSVIntakeHistory originalIntakeHistoryRecord)
    {
	this.originalIntakeHistoryRecord = originalIntakeHistoryRecord;
    }
    
    public JuvenileCasefileTransferredOffenseResponseEvent getTransferredOffenseRecord()
    {
	return transferredOffenseRecord;
    }

    public void setTransferredOffenseRecord(JuvenileCasefileTransferredOffenseResponseEvent transferredOffenseRecord)
    {
	this.transferredOffenseRecord = transferredOffenseRecord;
    }

    public JuvenileCasefileTransferredOffenseResponseEvent getOriginalTransferredOffense()
    {
	return originalTransferredOffenseRecord;
    }

    public void setOriginalTransferredOffense(JuvenileCasefileTransferredOffenseResponseEvent originalIntakeHistoryRecord)
    {
	this.originalTransferredOffenseRecord = originalIntakeHistoryRecord;
    }

    public String getIntakeDate()
    {
	return intakeDate;
    }

    public void setIntakeDate(String intakeDate)
    {
	this.intakeDate = intakeDate;
    }

    public String getAssignmentDate()
    {
	return assignmentDate;
    }

    public void setAssignmentDate(String assignmentDate)
    {
	this.assignmentDate = assignmentDate;
    }

    public String getOriginalAssmntType()
    {
	return originalAssmntType;
    }

    public void setOriginalAssmntType(String originalAssmntType)
    {
	this.originalAssmntType = originalAssmntType;
    }

    public String getOriginalSupervisionType()
    {
	return originalSupervisionType;
    }

    public void setOriginalSupervisionType(String originalSupervisionType)
    {
	this.originalSupervisionType = originalSupervisionType;
    }

    public Date getOriginalAssignmenDate()
    {
	return originalAssignmenDate;
    }

    public void setOriginalAssignmenDate(Date originalAssignmenDate)
    {
	this.originalAssignmenDate = originalAssignmenDate;
    }

    public Date getOriginalIntakeDate()
    {
	return originalIntakeDate;
    }

    public void setOriginalIntakeDate(Date originalIntakeDate)
    {
	this.originalIntakeDate = originalIntakeDate;
    }

    public String getOriginalIntakeDecision()
    {
	return originalIntakeDecision;
    }

    public void setOriginalIntakeDecision(String originalIntakeDecision)
    {
	this.originalIntakeDecision = originalIntakeDecision;
    }

    public String getOriginalIntakeJPO()
    {
	return originalIntakeJPO;
    }

    public void setOriginalIntakeJPO(String originalIntakeJPO)
    {
	this.originalIntakeJPO = originalIntakeJPO;
    }

    public String getOriginalJuvenileNumber()
    {
	return originalJuvenileNumber;
    }

    public void setOriginalJuvenileNumber(String originalJuvenileNumber)
    {
	this.originalJuvenileNumber = originalJuvenileNumber;
    }

    public String getOriginalReferralNumber()
    {
	return originalReferralNumber;
    }

    public void setOriginalReferralNumber(String originalReferralNumber)
    {
	this.originalReferralNumber = originalReferralNumber;
    }

    public ProductionSupportJuvenileReferralResponseEvent getReferralDetail()
    {
	return referralDetail;
    }

    public void setReferralDetail(ProductionSupportJuvenileReferralResponseEvent referralDetail)
    {
	this.referralDetail = referralDetail;
    }

    public List<CodeResponseEvent> getSupervisionTypeCodes()
    {
	return supervisionTypeCodes;
    }

    public void setSupervisionTypeCodes(List<CodeResponseEvent> supervisionTypeCodes)
    {
	this.supervisionTypeCodes = supervisionTypeCodes;
    }

    public String getOriginalCourtId()
    {
	return originalCourtId;
    }

    public void setOriginalCourtId(String originalCourtId)
    {
	this.originalCourtId = originalCourtId;
    }

    public String getOriginalDetentionId()
    {
	return originalDetentionId;
    }

    public void setOriginalDetentionId(String originalDetentionId)
    {
	this.originalDetentionId = originalDetentionId;
    }

    public String getPurgeComments()
    {
	return purgeComments;
    }

    public void setPurgeComments(String purgeComments)
    {
	this.purgeComments = purgeComments;
    }

    public boolean isMoreThanOneCurrent()
    {
	return moreThanOneCurrent;
    }

    public void setMoreThanOneCurrent(boolean moreThanOneCurrent)
    {
	this.moreThanOneCurrent = moreThanOneCurrent;
    }

    public ArrayList getPactReferralsTotal()
    {
	return pactReferralsTotal;
    }

    public void setPactReferralsTotal(ArrayList pactReferralsTotal)
    {
	this.pactReferralsTotal = pactReferralsTotal;
    }

    public ArrayList getMasterStatusCodes()
    {
	return masterStatusCodes;
    }

    public void setMasterStatusCodes(ArrayList masterStatusCodes)
    {
	this.masterStatusCodes = masterStatusCodes;
    }

    public String getIsRefBeginDateChanged()
    {
	return isRefBeginDateChanged;
    }

    public void setIsRefBeginDateChanged(String isRefBeginDateChanged)
    {
	this.isRefBeginDateChanged = isRefBeginDateChanged;
    }

    public String getIsRefEndDateChanged()
    {
	return isRefEndDateChanged;
    }

    public void setIsRefEndDateChanged(String isRefEndDateChanged)
    {
	this.isRefEndDateChanged = isRefEndDateChanged;
    }

    public String getRefAckDate()
    {
	return refAckDate;
    }

    public void setRefAckDate(String refAckDate)
    {
	this.refAckDate = refAckDate;
    }

    public String getRefSentDate()
    {
	return refSentDate;
    }

    public void setRefSentDate(String refSentDate)
    {
	this.refSentDate = refSentDate;
    }

    public String getIsRefAckDateChanged()
    {
	return isRefAckDateChanged;
    }

    public void setIsRefAckDateChanged(String isRefAckDateChanged)
    {
	this.isRefAckDateChanged = isRefAckDateChanged;
    }

    public String getIsRefSentDateChanged()
    {
	return isRefSentDateChanged;
    }

    public void setIsRefSentDateChanged(String isRefSentDateChanged)
    {
	this.isRefSentDateChanged = isRefSentDateChanged;
    }

    public String getIsControllingReferralChanged()
    {
	return isControllingReferralChanged;
    }

    public void setIsControllingReferralChanged(String isControllingReferralChanged)
    {
	this.isControllingReferralChanged = isControllingReferralChanged;
    }

    public ArrayList getControllingReferrals()
    {
	return controllingReferrals;
    }

    public void setControllingReferrals(ArrayList controllingReferrals)
    {
	this.controllingReferrals = controllingReferrals;
    }

    public String getIsCaseFileIdChanged()
    {
	return isCaseFileIdChanged;
    }

    public void setIsCaseFileIdChanged(String isCaseFileIdChanged)
    {
	this.isCaseFileIdChanged = isCaseFileIdChanged;
    }

    public int getCurrentPactReferrals()
    {
	return currentPactReferrals;
    }

    public void setCurrentPactReferrals(int currentPactReferrals)
    {
	this.currentPactReferrals = currentPactReferrals;
    }

    public Collection<JSONObject> getPactReferralsJson()
    {
	return pactReferralsJson;
    }

    public void setPactReferralsJson(Collection<JSONObject> pactReferralsJson)
    {
	this.pactReferralsJson = pactReferralsJson;
    }

    public String getRiskAnalysisId()
    {
	return riskAnalysisId;
    }

    public void setRiskAnalysisId(String riskAnalysisId)
    {
	this.riskAnalysisId = riskAnalysisId;
    }

    public ArrayList getRiskAnalysisIds()
    {
	return riskAnalysisIds;
    }

    public void setRiskAnalysisIds(ArrayList riskAnalysisIds)
    {
	this.riskAnalysisIds = riskAnalysisIds;
    }

    public String getNewStartCalDate()
    {
	return newStartCalDate;
    }

    public void setNewStartCalDate(String newStartCalDate)
    {
	this.newStartCalDate = newStartCalDate;
    }

    public String getNewEndCalDate()
    {
	return newEndCalDate;
    }

    public void setNewEndCalDate(String newEndCalDate)
    {
	this.newEndCalDate = newEndCalDate;
    }

    public Collection<FamilyConstellationListResponseEvent> getFamilyConstellationResults()
    {
	return familyConstellationResults;
    }

    public void setFamilyConstellationResults(List<FamilyConstellationListResponseEvent> familyConstellationResults)
    {
	this.familyConstellationResults = familyConstellationResults;
    }
    
    public String getConstellationId()
    {
        return constellationId;
    }

    public void setConstellationId(String constellationId)
    {
        this.constellationId = constellationId;
    }

    public ArrayList<DocketEventResponseEvent> getAncillaryCalendarRecords()
    {
	return ancillaryCalendarRecords;
    }

    public void setAncillaryCalendarRecords(ArrayList<DocketEventResponseEvent> ancillaryCalendarRecords)
    {
	this.ancillaryCalendarRecords = ancillaryCalendarRecords;
    }

    public String getRespondentName()
    {
	return respondentName;
    }

    public void setRespondentName(String respondentName)
    {
	this.respondentName = respondentName;
    }
    
    public String getDocketEventId()
    {
	return this.docketEventId;
    }

    public void setDcketEventId(String docketEventId)
    {
	this.docketEventId = docketEventId;
    }

    public DocketEventResponseEvent getAncillaryCalendarRecord()
    {
	return ancillaryCalendarRecord;
    }

    public void setAncillaryCalendarRecord(DocketEventResponseEvent ancillaryCalendarRecord)
    {
	this.ancillaryCalendarRecord = ancillaryCalendarRecord;
    }

    public String getIsLastSeqNum()
    {
	return isLastSeqNum;
    }

    public void setIsLastSeqNum(String isLastSeqNum)
    {
	this.isLastSeqNum = isLastSeqNum;
    }  
    
    public ArrayList getJuvenileWarrantAssociates()
    {
        return juvenileWarrantAssociates;
    }

    public void setJuvenileWarrantAssociates(ArrayList juvenileWarrantAssociates)
    {
        this.juvenileWarrantAssociates = juvenileWarrantAssociates;
    }

    public int getJuvenileWarrantAssociateCount()
    {
        return juvenileWarrantAssociateCount;
    }

    public void setJuvenileWarrantAssociateCount(int juvenileWarrantAssociateCount)
    {
        this.juvenileWarrantAssociateCount = juvenileWarrantAssociateCount;
    }

    public String getLastSeqNum()
    {
        return lastSeqNum;
    }

    public void setLastSeqNum(String lastSeqNum)
    {
        this.lastSeqNum = lastSeqNum;
    }

    public String getNewLastSeqNum()
    {
        return newLastSeqNum;
    }

    public void setNewLastSeqNum(String newLastSeqNum)
    {
        this.newLastSeqNum = newLastSeqNum;
    }
    
    public int getIntakeHistCount()
    {
        return intakeHistCount;
    }

    public void setIntakeHistCount(int intakeHistCount)
    {
        this.intakeHistCount = intakeHistCount;
    }
    
    public ArrayList getTransOffenseReferrals()
    {
        return transOffenseReferrals;
    }

    public void setTransOffenseReferrals(ArrayList transOffenseReferrals)
    {
        this.transOffenseReferrals = transOffenseReferrals;
    }
    
    public int getTransOffenseReferralsCount()
    {
        return transOffenseReferralsCount;
    }

    public void setTransOffenseReferralsCount(int transOffenseReferralsCount)
    {
        this.transOffenseReferralsCount = transOffenseReferralsCount;
    }
    
    public int getJuvDetCourtRecordCount()
    {
        return juvDetCourtRecordCount;
    }

    public void setJuvDetCourtRecordCount(int juvDetCourtRecordCount)
    {
        this.juvDetCourtRecordCount = juvDetCourtRecordCount;
    }

    public int getJuvDistCourtRecordCount()
    {
        return juvDistCourtRecordCount;
    }

    public void setJuvDistCourtRecordCount(int juvDistCourtRecordCount)
    {
        this.juvDistCourtRecordCount = juvDistCourtRecordCount;
    }
    
    public int getPetitionRecordCount()
    {
        return petitionRecordCount;
    }

    public void setPetitionRecordCount(int petitionRecordCount)
    {
        this.petitionRecordCount = petitionRecordCount;
    }
    
    public ArrayList getJuvenileWarrants()
    {
        return juvenileWarrants;
    }

    public void setJuvenileWarrants(ArrayList juvenileWarrants)
    {
        this.juvenileWarrants = juvenileWarrants;
    }

    public int getJuvenileWarrantCount()
    {
        return juvenileWarrantCount;
    }

    public void setJuvenileWarrantCount(int juvenileWarrantCount)
    {
        this.juvenileWarrantCount = juvenileWarrantCount;
    }
    public ArrayList getProgramReferrals()
    {
        return programReferrals;
    }

    public void setProgramReferrals(ArrayList programReferrals)
    {
        this.programReferrals = programReferrals;
    }

    public int getProgramReferralCount()
    {
        return programReferralCount;
    }

    public void setProgramReferralCount(int programReferralCount)
    {
        this.programReferralCount = programReferralCount;
    }

    public ArrayList getAssociatedCautions()
    {
        return associatedCautions;
    }

    public void setAssociatedCautions(ArrayList associatedCautions)
    {
        this.associatedCautions = associatedCautions;
    }

    public ArrayList getAssociatedCharges()
    {
        return associatedCharges;
    }

    public void setAssociatedCharges(ArrayList associatedCharges)
    {
        this.associatedCharges = associatedCharges;
    }

    public ArrayList getAssociatedScarMarks()
    {
        return associatedScarMarks;
    }

    public void setAssociatedScarMarks(ArrayList associatedScarMarks)
    {
        this.associatedScarMarks = associatedScarMarks;
    }

    public ArrayList getAssociatedTattoos()
    {
        return associatedTattoos;
    }

    public void setAssociatedTattoos(ArrayList associatedTattoos)
    {
        this.associatedTattoos = associatedTattoos;
    }

    public ArrayList getAssociatedAddresses()
    {
        return associatedAddresses;
    }

    public void setAssociatedAddresses(ArrayList associatedAddresses)
    {
        this.associatedAddresses = associatedAddresses;
    }
    
    public int getAssociatedCautionCount()
    {
        return associatedCautionCount;
    }

    public void setAssociatedCautionCount(int associatedCautionCount)
    {
        this.associatedCautionCount = associatedCautionCount;
    }

    public int getAssociatedChargeCount()
    {
        return associatedChargeCount;
    }

    public void setAssociatedChargeCount(int associatedChargeCount)
    {
        this.associatedChargeCount = associatedChargeCount;
    }

    public int getAssociatedScarMarkCount()
    {
        return associatedScarMarkCount;
    }

    public void setAssociatedScarMarkCount(int associatedScarMarkCount)
    {
        this.associatedScarMarkCount = associatedScarMarkCount;
    }

    public int getAssociatedTattooCount()
    {
        return associatedTattooCount;
    }

    public void setAssociatedTattooCount(int associatedTattooCount)
    {
        this.associatedTattooCount = associatedTattooCount;
    }

    public int getAssociatedAddressCount()
    {
        return associatedAddressCount;
    }

    public void setAssociatedAddressCount(int associatedAddressCount)
    {
        this.associatedAddressCount = associatedAddressCount;
    }

    public String getMoreThanOneReferralNumber()
    {
        return moreThanOneReferralNumber;
    }

    public void setMoreThanOneReferralNumber(String moreThanOneReferralNumber)
    {
        this.moreThanOneReferralNumber = moreThanOneReferralNumber;
    }

    public ArrayList getCasefileclosingsDelete()
    {
        return casefileclosingsDelete;
    }

    public void setCasefileclosingsDelete(ArrayList casefileclosingsDelete)
    {
        this.casefileclosingsDelete = casefileclosingsDelete;
    }

    public ArrayList getProgramReferralsDelete()
    {
        return programReferralsDelete;
    }

    public void setProgramReferralsDelete(ArrayList programReferralsDelete)
    {
        this.programReferralsDelete = programReferralsDelete;
    }

    public int getCasefileclosingsDeleteCount()
    {
        return casefileclosingsDeleteCount;
    }

    public void setCasefileclosingsDeleteCount(int casefileclosingsDeleteCount)
    {
        this.casefileclosingsDeleteCount = casefileclosingsDeleteCount;
    }

    public int getProgramReferralsDeleteCount()
    {
        return programReferralsDeleteCount;
    }

    public void setProgramReferralsDeleteCount(int programReferralsDeleteCount)
    {
        this.programReferralsDeleteCount = programReferralsDeleteCount;
    }

    public ArrayList getFilteredCasefileIds()
    {
        return filteredCasefileIds;
    }

    public void setFilteredCasefileIds(ArrayList filteredCasefileIds)
    {
        this.filteredCasefileIds = filteredCasefileIds;
    }

    public ArrayList getFilteredCasefileClosingIds()
    {
        return filteredCasefileClosingIds;
    }

    public void setFilteredCasefileClosingIds(ArrayList filteredCasefileClosingIds)
    {
        this.filteredCasefileClosingIds = filteredCasefileClosingIds;
    }

    public ArrayList getFilteredProgramReferralIds()
    {
        return filteredProgramReferralIds;
    }

    public void setFilteredProgramReferralIds(ArrayList filteredProgramReferralIds)
    {
        this.filteredProgramReferralIds = filteredProgramReferralIds;
    }

    public String getNewRecordCLM()
    {
        return newRecordCLM;
    }

    public void setNewRecordCLM(String newRecordCLM)
    {
        this.newRecordCLM = newRecordCLM;
    }

    public ArrayList getFilteredCasefiles()
    {
        return filteredCasefiles;
    }

    public void setFilteredCasefiles(ArrayList filteredCasefiles)
    {
        this.filteredCasefiles = filteredCasefiles;
    }

    public ArrayList getFilteredCasefileClosings()
    {
        return filteredCasefileClosings;
    }

    public void setFilteredCasefileClosings(ArrayList filteredCasefileClosings)
    {
        this.filteredCasefileClosings = filteredCasefileClosings;
    }

    public ArrayList getFilteredProgramReferrals()
    {
        return filteredProgramReferrals;
    }

    public void setFilteredProgramReferrals(ArrayList filteredProgramReferrals)
    {
        this.filteredProgramReferrals = filteredProgramReferrals;
    }
    
    public List<JJSCLDetention> getSequenceNumberList()
    {
	return this.sequenceNumberList;
    }

    public void setSequenceNumberList(List<JJSCLDetention> sequenceNumbers)
    {
	this.sequenceNumberList = sequenceNumbers;
    }

    public String getProgramReferralAssignmentDate()
    {
        return programReferralAssignmentDate;
    }

    public void setProgramReferralAssignmentDate(String programReferralAssignmentDate)
    {
        this.programReferralAssignmentDate = programReferralAssignmentDate;
    }

    public String getOriginalProgramReferralAssignmentDate()
    {
        return originalProgramReferralAssignmentDate;
    }

    public void setOriginalProgramReferralAssignmentDate(String originalProgramReferralAssignmentDate)
    {
        this.originalProgramReferralAssignmentDate = originalProgramReferralAssignmentDate;
    }

    public String getIsProgramReferralAssignmentDateChanged()
    {
        return isProgramReferralAssignmentDateChanged;
    }

    public void setIsProgramReferralAssignmentDateChanged(String isProgramReferralAssignmentDateChanged)
    {
        this.isProgramReferralAssignmentDateChanged = isProgramReferralAssignmentDateChanged;
    }

    public String getProgramReferralAssignmentId()
    {
        return programReferralAssignmentId;
    }

    public void setProgramReferralAssignmentId(String programReferralAssignmentId)
    {
        this.programReferralAssignmentId = programReferralAssignmentId;
    }

    public String getRefNumVOP()
    {
        return refNumVOP;
    }

    public void setRefNumVOP(String refNumVOP)
    {
        this.refNumVOP = refNumVOP;
    }

    public String getRefDateVOP()
    {
        return refDateVOP;
    }

    public void setRefDateVOP(String refDateVOP)
    {
        this.refDateVOP = refDateVOP;
    }

    public String getVOPOffenseCode()
    {
        return VOPOffenseCode;
    }

    public void setVOPOffenseCode(String vOPOffenseCode)
    {
        VOPOffenseCode = vOPOffenseCode;
    }

    public String getOffenseChargeVOP()
    {
        return offenseChargeVOP;
    }

    public void setOffenseChargeVOP(String offenseChargeVOP)
    {
        this.offenseChargeVOP = offenseChargeVOP;
    }

    public String getOffenseChargeDateVOP()
    {
        return offenseChargeDateVOP;
    }

    public void setOffenseChargeDateVOP(String offenseChargeDateVOP)
    {
        this.offenseChargeDateVOP = offenseChargeDateVOP;
    }

    public String getInCCountyOrigPetitionedRefNumVOPstr()
    {
	return inCCountyOrigPetitionedRefNumVOPstr;
    }

    public void setInCCountyOrigPetitionedRefNumVOPstr(String inCCountyOrigPetitionedRefNumVOPstr)
    {
	this.inCCountyOrigPetitionedRefNumVOPstr = inCCountyOrigPetitionedRefNumVOPstr;
    }

    public String getLocationIndicatorVOP()
    {
        return locationIndicatorVOP;
    }

    public void setLocationIndicatorVOP(String locationIndicatorVOP)
    {
        this.locationIndicatorVOP = locationIndicatorVOP;
    }

    public String getAdultIndicatorVOP()
    {
        return adultIndicatorVOP;
    }

    public void setAdultIndicatorVOP(String adultIndicatorVOP)
    {
        this.adultIndicatorVOP = adultIndicatorVOP;
    }

    public String getVopOID()
    {
        return vopOID;
    }

    public void setVopOID(String vopOID)
    {
        this.vopOID = vopOID;
    }

    public int getInCCountyOrigPetitionedRefNumVOP()
    {
	return inCCountyOrigPetitionedRefNumVOP;
    }

    public void setInCCountyOrigPetitionedRefNumVOP(int inCCountyOrigPetitionedRefNumVOP)
    {
	this.inCCountyOrigPetitionedRefNumVOP = inCCountyOrigPetitionedRefNumVOP;
    }

    public JCVOP getJcVOP()
    {
        return jcVOP;
    }

    public void setJcVOP(JCVOP jcVOP)
    {
        this.jcVOP = jcVOP;
    }
    public JCVOP getJcVOPOriginal()
    {
        return jcVOPOriginal;
    }

    public void setJcVOPOriginal(JCVOP jcVOPOriginal)
    {
        this.jcVOPOriginal = jcVOPOriginal;
    }

    public String getActivityCd()
    {
        return activityCd;
    }

    public void setActivityCd(String activityCd)
    {
        this.activityCd = activityCd;
    }
    public String getNewComments()
    {
        return newComments;
    }

    public void setNewComments(String newComments)
    {
        this.newComments = newComments;
    }
    public String getNewupdatedComments()
    {
        return newupdatedComments;
    }

    public void setNewupdatedComments(String newupdatedComments)
    {
        this.newupdatedComments = newupdatedComments;
    }
    public String getDetentionTime()
    {
        return detentionTime;
    }

    public void setDetentionTime(String detentionTime)
    {
        this.detentionTime = detentionTime;
    }

    public String getActivityendTime()
    {
        return activityendTime;
    }

    public void setActivityendTime(String activityendTime)
    {
        this.activityendTime = activityendTime;
    }

    
    public String getActivityTime()
    {
        return activityTime;
    }

    public void setActivityTime(String activityTime)
    {
        this.activityTime = activityTime;
    }
    public String getActivityDate()
    {
        return activityDate;
    }

    public void setActivityDate(String activityDate)
    {
        this.activityDate = activityDate;
    }
    public ActivityResponseEvent getActivityResp()
    {
        return activityResp;
    }

    public void setActivityResp(ActivityResponseEvent activityResp)
    {
        this.activityResp = activityResp;
    }
    public ActivityResponseEvent getOriginalactivityResp()
    {
        return originalactivityResp;
    }

    public void setOriginalactivityResp(ActivityResponseEvent originalactivityResp)
    {
        this.originalactivityResp = originalactivityResp;
    }

    public List<CalendarServiceEventResponseEvent> getServices()
    {
        return services;
    }

    public void setServices(List<CalendarServiceEventResponseEvent> services)
    {
        this.services = services;
    }
    
    

    public String getInstructorId()
    {
        return instructorId;
    }

    public void setInstructorId(String instructorId)
    {
        this.instructorId = instructorId;
    }
    
    

    public String getSelectedTransferInstructorId()
    {
        return selectedTransferInstructorId;
    }

    public void setSelectedTransferInstructorId(String selectedTransferInstructorId)
    {
        this.selectedTransferInstructorId = selectedTransferInstructorId;
    }

    public SP_Profile getInstructor()
    {
	if (this.instructorId != null
		&& this.instructorId.length() > 0 ) {
	    this.instructor = SP_Profile.findInstructor(instructorId);
	}
        return instructor;
    }

    public void setInstructor(SP_Profile instructor)
    {
        this.instructor = instructor;
    }
    
    

    public List<SP_Profile> getInstructors()
    {
	if ( this.instructor != null
		&& ( this.instructors == null
		|| this.instructors.size() == 0 ) ) {
	    Iterator<SP_Profile>instructorIter = SP_Profile.findAll("serviceProviderId", instructor.getServiceProviderId());
	    while( instructorIter.hasNext() ){
		SP_Profile instructorProfile = (SP_Profile) instructorIter.next();
		if ( "A".equals(instructorProfile.getStatusId() )
			&& ! this.instructors.contains(instructorProfile) ){
		    this.instructors.add(instructorProfile);
		}
	    }
	}
        return instructors;
    }

    public void setInstructors(List<SP_Profile> instructors)
    {
        this.instructors = instructors;
    }

    public String getServiceEventId()
    {
        return serviceEventId;
    }

    public void setServiceEventId(String serviceEventId)
    {
        this.serviceEventId = serviceEventId;
    }

    public List<ProviderProgramResponseEvent> getPrograms()
    {
        return programs;
    }

    public void setPrograms(List<ProviderProgramResponseEvent> programs)
    {
        this.programs = programs;
    }

    public String getFromStartDate()
    {
        return fromStartDate;
    }

    public void setFromStartDate(String fromStartDate)
    {
        this.fromStartDate = fromStartDate;
    }

    public String getToStartDate()
    {
        return toStartDate;
    }

    public void setToStartDate(String toStartDate)
    {
        this.toStartDate = toStartDate;
    }

    public  Collection<JuvenileReferralVOPResponseEvent> getJcVOPs()
    {
	return jcVOPs;
    }

    public void setJcVOPs(Collection<JuvenileReferralVOPResponseEvent> jcVOPs)
    {
	this.jcVOPs = jcVOPs;
    }

    public int getJcVOPCount()
    {
	return jcVOPCount;
    }

    public void setJcVOPCount(int jcVOPCount)
    {
	this.jcVOPCount = jcVOPCount;
    }

    public CasefileDocumentsResponseEvent getCommonAppDocument()
    {
        return commonAppDocument;
    }

    public void setCommonAppDocument(CasefileDocumentsResponseEvent commonAppDocument)
    {
        this.commonAppDocument = commonAppDocument;
    }

    public Date getTerminationDate()
    {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate)
    {
        this.terminationDate = terminationDate;
    }

    public String getNewTerminationDate()
    {
	return newTerminationDate;
    }

    public void setNewTerminationDate(String newTerminationDate)
    {
	this.newTerminationDate = newTerminationDate;
    }
    
    //termination create date
    public Date getTerminationCreateDate()
    {
        return terminationCreateDate;
    }

    public void setTerminationCreateDate(Date terminationCreateDate)
    {
        this.terminationCreateDate = terminationCreateDate;
    }

    public String getNewTerminationCreateDate()
    {
	return newTerminationCreateDate;
    }

    public void setNewTerminationCreateDate(String newTerminationCreateDate)
    {
	this.newTerminationCreateDate = newTerminationCreateDate;
    }

    public PetitionResponseEvent getPetitionDetail()
    {
        return petitionDetail;
    }

    public void setPetitionDetail(PetitionResponseEvent petitionDetail)
    {
        this.petitionDetail = petitionDetail;
    }

    public String getOffenseId()
    {
        return offenseId;
    }

    public void setOffenseId(String offenseId)
    {
        this.offenseId = offenseId;
    }

    public JJSOffenseResponseEvent getReferralOffense()
    {
        return referralOffense;
    }

    public void setReferralOffense(JJSOffenseResponseEvent referralOffense)
    {
        this.referralOffense = referralOffense;
    }

    public JJSOffenseResponseEvent getOriginalReferralOffense()
    {
        return originalReferralOffense;
    }

    public void setOriginalReferralOffense(JJSOffenseResponseEvent originalReferralOffense)
    {
        this.originalReferralOffense = originalReferralOffense;
    }

    public ProductionSupportJuvenileReferralResponseEvent getJuvprogref()
    {
        return juvprogref;
    }

    public void setJuvprogref(ProductionSupportJuvenileReferralResponseEvent juvprogref)
    {
        this.juvprogref = juvprogref;
    }

    public ProductionSupportJuvenileReferralResponseEvent getOriginalJuvprogref()
    {
        return originalJuvprogref;
    }

    public void setOriginalJuvprogref(ProductionSupportJuvenileReferralResponseEvent originalJuvprogref)
    {
        this.originalJuvprogref = originalJuvprogref;
    }

    public ArrayList<DocketEventResponseEvent> getOriginalJuvDistCourtRecords()
    {
        return originalJuvDistCourtRecords;
    }

    public void setOriginalJuvDistCourtRecords(ArrayList<DocketEventResponseEvent> originalJuvDistCourtRecords)
    {
        this.originalJuvDistCourtRecords = originalJuvDistCourtRecords;
    }

    public ArrayList<DocketEventResponseEvent> getOriginalJuvDetCourtRecords()
    {
        return originalJuvDetCourtRecords;
    }

    public void setOriginalJuvDetCourtRecords(ArrayList<DocketEventResponseEvent> originalJuvDetCourtRecords)
    {
        this.originalJuvDetCourtRecords = originalJuvDetCourtRecords;
    }  
    public String getCJISNumber()
    {
        return CJISNumber;
    }

    public void setCJISNumber(String cJISNumber)
    {
        CJISNumber = cJISNumber;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public ArrayList getProvProgramsList()
    {
        return provProgramsList;
    }

    public void setProvProgramsList(ArrayList provProgramsList)
    {
        this.provProgramsList = provProgramsList;
    }

    public String getProviderPgmDescBox()
    {
        return providerPgmDescBox;
    }

    public void setProviderPgmDescBox(String providerPgmDescBox)
    {
        this.providerPgmDescBox = providerPgmDescBox;
    }

    public String getDrugTestingId()
    {
        return drugTestingId;
    }

    public void setDrugTestingId(String drugTestingId)
    {
        this.drugTestingId = drugTestingId;
    }
    
    
    public String getTestDate()
    {
        return testDate;
    }

    public void setTestDate(String testDate)
    {
        this.testDate = testDate;
    }

    public String getTestTime()
    {
        return testTime;
    }

    public void setTestTime(String testTime)
    {
        this.testTime = testTime;
    }

    public List<DrugTestingResponseEvent> getDrugTestingInfos()
    {
        return drugTestingInfos;
    }

    public void setDrugTestingInfos(List<DrugTestingResponseEvent> drugTestingInfos)
    {
        this.drugTestingInfos = drugTestingInfos;
    }

    public DrugTestingResponseEvent getDrugTestingInfo()
    {
        return drugTestingInfo;
    }

    public void setDrugTestingInfo(DrugTestingResponseEvent drugTestingInfo)
    {
        this.drugTestingInfo = drugTestingInfo;
    }

    public DrugTestingResponseEvent getOriginalDrugTestingInfo()
    {
        return originalDrugTestingInfo;
    }

    public void setOriginalDrugTestingInfo(DrugTestingResponseEvent originalDrugTestingInfo)
    {
        this.originalDrugTestingInfo = originalDrugTestingInfo;
    }

    public String getTestingSessionId()
    {
        return testingSessionId;
    }

    public void setTestingSessionId(String testingSessionId)
    {
        this.testingSessionId = testingSessionId;
    }

    public List<ProductionSupportTestingSessionInfoResponseEvent> getTestingSessionInfos()
    {
        return testingSessionInfos;
    }

    public void setTestingSessionInfos(List<ProductionSupportTestingSessionInfoResponseEvent> testingSessionInfos)
    {
        this.testingSessionInfos = testingSessionInfos;
    }

    public String getOriginalTestDate()
    {
        return originalTestDate;
    }

    public void setOriginalTestDate(String originalTestDate)
    {
        this.originalTestDate = originalTestDate;
    }

    public ArrayList getJuvSourceFunds()
    {
        return (ArrayList) CodeHelper.getActiveCodesByStatus( "JUVENILE_SOURCE_FUND", true );
    }

    public void setJuvSourceFunds(ArrayList juvSourceFunds)
    {
        this.juvSourceFunds = juvSourceFunds;
    }

    public String getNewFundSource()
    {
        return newFundSource;
    }

    public void setNewFundSource(String newFundSource)
    {
        this.newFundSource = newFundSource;
    }

    public String getIsfundSourceChanged()
    {
	return isfundSourceChanged;
    }

    public void setIsfundSourceChanged(String isfundSourceChanged)
    {
	this.isfundSourceChanged = isfundSourceChanged;
    }
    
    public String getFamilyMemberId()
    {
        return this.familyMemberId;
    }

    public void setFamilyMemberId(String familyMemberId)
    {
        this.familyMemberId = familyMemberId;
    }
    
    public String getFamilyMemberFirstName()
    {
        return this.familyMemberFirstName;
    }

    public void setFamilyMemberFirstName(String firstName)
    {
        this.familyMemberFirstName = firstName;
    }
    
    public String getFamilyMemberLastName()
    {
        return this.familyMemberLastName;
    }

    public void setFamilyMemberLastName(String lastName)
    {
        this.familyMemberLastName = lastName;
    }
    
    public List<FamilyMember> getFamilyMemberList()
    {
        return this.familyMemberList;
    }

    public void setFamilyMemberList(List<FamilyMember> familyMemberList)
    {
	this.familyMemberList = familyMemberList;
    }
    
    public FamilyMember getFamilyMember()
    {
        return this.familyMember;
    }

    public void setFamilyMember(FamilyMember familyMember)
    {
	this.familyMember = familyMember;
    }
    
    public FamilyMember getFamilyMemberUpdate()
    {
        return this.familyMemberUpdate;
    }

    public void setFamilyMemberUpdate(FamilyMember familyMember)
    {
	this.familyMemberUpdate = familyMember;
    }
    
    public String getFamilyConstellationId()
    {
        return this.familyConstellationId;
    }

    public void setFamilyConstellationId(String familyConstellationId)
    {
        this.familyConstellationId = familyConstellationId;
    }
    
    public FamilyConstellation getFamilyConstellation()
    {
        return this.familyConstellation;
    }

    public void setFamilyConstellation(FamilyConstellation familyConstellation)
    {
	this.familyConstellation = familyConstellation;
    }
    
    public List<FamilyConstellation> getFamilyConstellationList()
    {
        return this.familyConstellationList;
    }

    public void setFamilyConstellationList(List<FamilyConstellation> familyConstellationList)
    {
	this.familyConstellationList = familyConstellationList;
    }
    
    public String getConstellationQueryType()
    {
        return this.constellationQueryType;
    }

    public void setConstellationQueryType(String queryType)
    {
        this.constellationQueryType = queryType;
    }
    
    public Collection getRelationshipToJuvenileList()
    {
	return relationshipToJuvenileList;
    }
    
    public void setRelationshipToJuvenileList(Collection collection)
    {
	relationshipToJuvenileList = collection;
    }
    
    public Collection getStateList()
    {
	return stateList;
    }
    
    public void setStateList(Collection collection)
    {
	stateList = collection;
    }
    
    public Collection getIsIncarceratedList()
    {
	return this.isIncarceratedList;
    }
    
    public void setIsIncarceratedList(Collection collection)
    {
	this.isIncarceratedList = collection;
    }

    public String getTransOffenseId()
    {
	// TODO Auto-generated method stub
	return transOffenseId;
    }
    
    public void setTransOffenseId(String transOffenseId)
    {
	// TODO Auto-generated method stub
	this.transOffenseId = transOffenseId;
    }

        
    /**
	 * @return the countyId
	 */
	public String getOriginalCountyId() {
		return originalCountyId;
	}
	/**
	 * @param countyId the countyId to set
	 */
	public void setOriginalCountyId(String originalCountyId) {
		this.originalCountyId = originalCountyId;
	}
	
	
	public String getOriginalOffenseCode() {
		return originalOffenseCode;
	}
	/**
	 * @param offenseCode the offenseCode to set
	 */
	public void setOriginalOffenseCode(String originalOffenseCode) {
		this.originalOffenseCode = originalOffenseCode;
	}
	
	
	/**
	 * @return the category
	 */
	public String getOriginalCategory() {
		return originalCategory;
	}
	/**
	 * @param category the category to set
	 */
	public void setOriginalCategory(String originalCategory) {
		this.originalCategory = originalCategory;
	}	
	
	/**
	 * @param category the category to set
	 */
	public void setOriginalDpsCode(String originalDpsCode) {
		this.originalDpsCode = originalDpsCode;
	}
	/**
	 * @return the dpsCode
	 */
	public String getOriginalDpsCode() {
		return originalDpsCode;
	}	
	
	/**
	 * @return the offenseDate
	 */
	public Date getOriginalOffenseDate() {
		return originalOffenseDate;
	}
	/**
	 * @param offenseDate the offenseDate to set
	 */
	public void setOriginalOffenseDate(Date originalOffenseDate) {
		this.originalOffenseDate = originalOffenseDate;
	}
	
	/**
	 * @return the adjudicationDate
	 */
	public Date getOriginalAdjudicationDate() {
		return originalAdjudicationDate;
	}
	/**
	 * @param adjudicationDate the adjudicationDate to set
	 */
	public void setOriginalAdjudicationDate(Date originalAdjudicationDate) {
		this.originalAdjudicationDate = originalAdjudicationDate;
	}
	
	/**
	 * @return
	 */
	public String getOriginalPersonId() {
		return originalPersonId;
	}
	
	/**
	 * @param pidNum
	 */
	public void setOriginalPersonId(String originalPersonId) {
		this.originalPersonId = originalPersonId;
	}
    
    public Collection getStatusList()
    {
	return this.statusList;
    }
    
    public void setStatusList(Collection statusList)
    {
	this.statusList = statusList;
    }
    
    public String getSearchedJuvenileId()
    {
        return this.searchedJuvenileId;
    }

    public void setSearchedJuvenileId(String juvenileId)
    {
        this.searchedJuvenileId = juvenileId;
    }
    
    public FamilyConstellationMember getFamilyConstellationMember()
    {
        return this.familyConstellationMember;
    }

    public void setFamilyConstellationMember(FamilyConstellationMember familyConstellationMember)
    {
	this.familyConstellationMember = familyConstellationMember;
    }
    
    public List<FamilyConstellationMember> getFamilyConstellationMemberList()
    {
        return this.familyConstellationMemberList;
    }

    public void setFamilyConstellationMemberList(List<FamilyConstellationMember> familyConstellationMemberList)
    {
	this.familyConstellationMemberList = familyConstellationMemberList;
    }

    public boolean isPactOIDChanged()
    {
        return pactOIDChanged;
    }

    public void setPactOIDChanged(boolean pactOIDChanged)
    {
        this.pactOIDChanged = pactOIDChanged;
    }
    
    public Collection getBooleanList()
    {
	return this.booleanList;
    }
    
    public void setBooleanList(Collection booleanList)
    {
	this.booleanList = booleanList;
    }

    public String getNewPactRiskNeeded()
    {
        return newPactRiskNeeded;
    }

    public void setNewPactRiskNeeded(String newPactRiskNeeded)
    {
        this.newPactRiskNeeded = newPactRiskNeeded;
    }

    public String getNewHispanicIndicatorNeeded()
    {
        return newHispanicIndicatorNeeded;
    }

    public void setNewHispanicIndicatorNeeded(String newHispanicIndicatorNeeded)
    {
        this.newHispanicIndicatorNeeded = newHispanicIndicatorNeeded;
    }

    public String getNewSchoolHistoryNeeded()
    {
        return newSchoolHistoryNeeded;
    }

    public void setNewSchoolHistoryNeeded(String newSchoolHistoryNeeded)
    {
        this.newSchoolHistoryNeeded = newSchoolHistoryNeeded;
    }

    public String getNewVopEntryNeeded()
    {
        return newVopEntryNeeded;
    }

    public void setNewVopEntryNeeded(String newVopEntryNeeded)
    {
        this.newVopEntryNeeded = newVopEntryNeeded;
    }

    public String getNewSubstanceAbuseNeeded()
    {
        return newSubstanceAbuseNeeded;
    }

    public void setNewSubstanceAbuseNeeded(String newSubstanceAbuseNeeded)
    {
        this.newSubstanceAbuseNeeded = newSubstanceAbuseNeeded;
    }

    public JuvenileCasefileResponseEvent getOriginalCasefile()
    {
        return originalCasefile;
    }

    public void setOriginalCasefile(JuvenileCasefileResponseEvent originalCasefile)
    {
        this.originalCasefile = originalCasefile;
    }
    public String getDPSCode()
    {
        return DPSCode;
    }

    public void setDPSCode(String dPSCode)
    {
        DPSCode = dPSCode;
    }

    public String getCommentDate()
    {
        return commentDate;
    }

    public void setCommentDate(String commentDate)
    {
        this.commentDate = commentDate;
    }
    public String getCommentId()
    {
        return commentId;
    }

    public void setCommentId(String commentId)
    {
        this.commentId = commentId;
    }
    public Collection<ProgramReferralCommentResponseEvent> getReferralComments()
    {
        return referralComments;
    }

    public void setReferralComments(Collection<ProgramReferralCommentResponseEvent> referralComments)
    {
        this.referralComments = referralComments;
    }
    
    public boolean getIsIntakeHistoryDelete()
    {
   	return isIntakeHistoryDelete;
    }

    public void setIsIntakeHistoryDelete(boolean isIntakeHistoryDelete)
    {
   	this.isIntakeHistoryDelete = isIntakeHistoryDelete;
    }
    
    public String getAssessOfficerId()
    {
        return assessOfficerId;
    }

    public void setAssessOfficerId(String assessOfficerId)
    {
        this.assessOfficerId = assessOfficerId;
    }

    public boolean getHasPreviousMaysi() {
	return hasPreviousMaysi;
    }

    public void setHasPreviousMaysi(boolean hasPreviousMaysi) {
	this.hasPreviousMaysi = hasPreviousMaysi;
    }

    public boolean getIsAdministered() {
	return isAdministered;
    }

    public void setIsAdministered(boolean isAdministered) {
	this.isAdministered = isAdministered;
    }

    public String getReasonNotDone()
    {
        return reasonNotDone;
    }

    public void setReasonNotDone(String reasonNotDone)
    {
        this.reasonNotDone = reasonNotDone;
    }

    public String getOtherReasonNotDone()
    {
        return otherReasonNotDone;
    }

    public void setOtherReasonNotDone(String otherReasonNotDone)
    {
        this.otherReasonNotDone = otherReasonNotDone;
    }

    public String getSubstanceAbuseId()
    {
        return substanceAbuseId;
    }

    public void setSubstanceAbuseId(String substanceAbuseId)
    {
        this.substanceAbuseId = substanceAbuseId;
    }

    public List<ProductionSupportSubstanceAbuseInfoResponseEvent> getSubstanceAbuseInfos()
    {
        return substanceAbuseInfos;
    }

    public void setSubstanceAbuseInfos(List<ProductionSupportSubstanceAbuseInfoResponseEvent> substanceAbuseInfos)
    {
        this.substanceAbuseInfos = substanceAbuseInfos;
    }

    public ProductionSupportSubstanceAbuseInfoResponseEvent getOriginalSubstanceAbuseInfo()
    {
        return originalSubstanceAbuseInfo;
    }

    public void setOriginalSubstanceAbuseInfo(ProductionSupportSubstanceAbuseInfoResponseEvent originalSubstanceAbuseInfo)
    {
        this.originalSubstanceAbuseInfo = originalSubstanceAbuseInfo;
    }

    public ProductionSupportSubstanceAbuseInfoResponseEvent getSubstanceAbuseInfo()
    {
        return substanceAbuseInfo;
    }

    public void setSubstanceAbuseInfo(ProductionSupportSubstanceAbuseInfoResponseEvent substanceAbuseInfo)
    {
        this.substanceAbuseInfo = new ProductionSupportSubstanceAbuseInfoResponseEvent( substanceAbuseInfo );
    }

    public List<CodeResponseEvent> getTjjdSubstanceAbuseCodes()
    {
	tjjdSubstanceAbuseCodes = CodeHelper.getTjjdSubstanceAbuseCodes();
        return tjjdSubstanceAbuseCodes;
    }

    public void setTjjdSubstanceAbuseCodes(List<CodeResponseEvent> tjjdSubstanceAbuseCodes)
    {
        this.tjjdSubstanceAbuseCodes = tjjdSubstanceAbuseCodes;
    }

    public List<DrugTypeCodeResponseEvent> getDrugTypeCodes()
    {
	drugTypeCodes = CodeHelper.getDrugTypes();
        return drugTypeCodes;
    }

    public void setDrugTypeCodes(List<DrugTypeCodeResponseEvent> drugTypeCodes)
    {
        this.drugTypeCodes = drugTypeCodes;
    }

    public String getNewProgramRefId()
    {
	return newProgramRefId;
    }

    public void setNewProgramRefId(String newProgramRefId)
    {
	this.newProgramRefId = newProgramRefId;
    }

    public String getDateOfDeath()
    {
	return dateOfDeath;
    }

    public void setDateOfDeath(String dateOfDeath)
    {
	this.dateOfDeath = dateOfDeath;
    }

    public int getAgeAtDeath()
    {
	return ageAtDeath;
    }

    public void setAgeAtDeath(int ageAtDeath)
    {
	this.ageAtDeath = ageAtDeath;
    }

    public int getJuvExcluded()
    {
	return juvExcluded;
    }

    public void setJuvExcluded(int juvExcluded)
    {
	this.juvExcluded = juvExcluded;
    }

    public String getRecType()
    {
	return recType;
    }

    public void setRecType(String recType)
    {
	this.recType = recType;
    }

    public String getLivewith()
    {
	return livewith;
    }

    public void setLivewith(String livewith)
    {
	this.livewith = livewith;
    }

    public String getCauseOfDeath()
    {
	return causeOfDeath;
    }

    public void setCauseOfDeath(String causeOfDeath)
    {
	this.causeOfDeath = causeOfDeath;
    }

    public String getDeathVerification()
    {
	return deathVerification;
    }

    public void setDeathVerification(String deathVerification)
    {
	this.deathVerification = deathVerification;
    }

    public Collection getCausesOfDeath()
    {
	return CodeHelper.getCauseOfDeathCodes();
    }

    public void setCausesOfDeath(Collection causesOfDeath)
    {
	this.causesOfDeath = causesOfDeath;
    }

    public Collection getDeathVerficationCodes()
    {
	return deathVerficationCodes;
    }

    public void setDeathVerficationCodes(Collection deathVerficationCodes)
    {
	this.deathVerficationCodes = deathVerficationCodes;
    }
    
    
    
    
}
