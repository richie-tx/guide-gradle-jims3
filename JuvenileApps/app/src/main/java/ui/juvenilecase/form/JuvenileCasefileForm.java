/*
 * Created on May 3, 2005
 *
 */
package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.GetReferralsByCasefileIdEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.officer.GetOfficerProfileEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;

import pd.contact.officer.OfficerProfile;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.casefile.JuvenileReferralHelper;
import ui.juvenilecase.form.JuvenileMemberForm.MemberContact;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;


/**
 * @author dapte This is the master form that will hold all the header
 *         information common to all the tabs. It will also hold all the forms
 *         corresponding to each of the tabs.
 */
public class JuvenileCasefileForm extends ActionForm
{
    private static Collection emptyColl = new ArrayList();
    private static Name emptyName = new Name();

    /* there's is a difference between these next two "CLM" booleans.
     * isCurrUserCaseloadMgr is true if the logged in user *IS* the
     * CLM for the current Casefile
     * isLoggedInUserACLM is true if the current user *IS* a CLM,
     * even if they are NOT the CLM for the current casefile
     */
    private boolean isCurrUserCaseloadMgr = false;
    private boolean isLoggedInUserACLM = false;

    private boolean listsSet = false;

    private String action = "";
    private String secondaryAction = "";
    private boolean update = false;
    private boolean delete = false;
    private String selectedValue = "";
    private boolean juvUnder21 = false; // used for juvenile age check

    // HEADER INFORMATION
    private String supervisionNum = "";
    private String sequenceNum = "";

    private Name probationOfficerName = emptyName;
    //bug 26711
    private String probationOfficerFirstName = "";
    private String probationOfficerMiddleName = "";
    private String probationOfficerLastName = "";
    // This would be the currently logged in user Name
    private String probationOfficerLogonId;
    private String probationOfficerId;
    private String probationOfficerEmail;
    private PhoneNumber probationOfficerWorkPhone = new PhoneNumber("");
    private String probationOfficerWorkPhoneExtn;
    private String caseloadManagerEmail = "";
    private PhoneNumber caseloadManagerPhone = new PhoneNumber("");

    private String juvenileNum = "";
    private Name juvenileName;
    private String preferredFirstName;

    private String supervisionTypeId = "";
    private String caseStatusId = "";
    private Date expectedSupervisionEndDate;
    private String priorServices; //added for generate caseplan tab
    private String contactInfo; //added for generate caseplan tab

    private String supervisionEndDateStr = "";
    private String courtOrderedProbationStartDateStr;

    private Date activationDate;
    private String activationDateStr;
    private String activationTime = "";

    private String currentAge = "";
    private String raceId = "";
    private String originalRaceId = "";
    private String race;
    private String sexId = "";
    private String hispanic = "";

    private String caseloadManagerId;
    private Name caseloadManagerName;

    private String supervisionOutcome;
    private String supervisionOutcomeDesc;
    private Date closingDate;
    private String closingDateStr = "";
    private String controllingReferralNumber;
    private String closingEvaluation;

    // detail information. For this iteration, only assignmentDate is being
    private Date assignmentDate;

    /* This flag is at the casefile level and not at the
     *  Individual MAYSI level. That is why it needs to be 
     *  here and not in the MentalHealthForm.
     */
    private boolean isNewMAYSINeeded;
       

    // benefits assessment indicator for display
    private boolean isBenefitsAssessmentNeeded;

    // Other Member Forms
    private MentalHealthForm mentalHealthForm;

    // Juvenile Offenses for a Assigned Referral
    private Collection offenses;

    // Previous Benefits Assessment
    private Collection previousBenefitsAssessments;

    //Assigned Referrals List
    private Collection juvenileCasefileReferralsList;

    //Facility History List
    private Collection<JuvenileDetentionFacilitiesResponseEvent> juvenileFacilityList;

    //JPO Assignment History
    private Collection jpoAssignmentHistories;

    // Risk Analysis Indicators for display
    private boolean isRiskAssessmentNeeded;
    private boolean isReferralAssessmentNeeded;
    private boolean isInterviewAssessmentNeeded;
    private boolean isTestingAssessmentNeeded;
    private boolean isCommunityAssessmentNeeded;
    private boolean isResidentialAssessmentNeeded;
    private boolean isProgressAssessmentNeeded;
    private boolean isResidentialProgressAssessNeeded;
    private boolean hispanicIndicatorNeedsUpdate;
    private boolean isSubstanceAbuseTjjdRequired;
    private boolean schoolHistoryNeedsUpdate;

    private boolean hasReferrals = false;

    private String detentionFacility;
    private String detentionFacilityId;
    private String detentionStatus;
    private String detentionStatusId;

    private Collection juvenileGuardianList;

    //Changes for US 31029
    private boolean restrictedAccess;

    //added for user-story 32796
    private String supervisionCategoryId;

    //pact attributes task 41028.
    private String pactAuthKey;
    private String userId;

    //# task 44099 . U.S #41378
    private String pactServerName;

    //added for task #43956
    private String riskLvl;
    private String needsLvl;
    private String lastPactDate;

    //added for US 41483
    private String userRiskLvl;
    private String userNeedsLvl;
    private String riskLvlDesc;
    private String needLvlDesc;
    private String userPACTDate;
    private String userPACTDateHover;
    private String userPactId;

    private String pactApplicationName;//bug #46110

    private JuvenileDetentionFacilitiesResponseEvent admissionInfo;
    private Collection<JuvenileTraitResponseEvent> traits;
    private JuvenileProfileDetailResponseEvent profileDetail;
    private MemberContact memberContact;
    private String bookingOffenseCd;
    private String bookingOffenseCdDesc;
    private boolean isResidentialCasefile;

    //#task 97826
    private String dualStatus;
    // 105403
    private String specialCategoryCd;

    private String pdaReadOnly = "false";
    private String pactReadOnly = "false";
    private String juvUnitId;
    private String recordJuvUnit;
    private String recordCLM;
    private String traitTypeId;
    private String traitTypeDescription;

    //US 90880
    private String dob;
    private PhoneNumber caseloadManagerWorkPhone = new PhoneNumber();
    private String caseloadManagerWorkPhoneExtn;

    private int activeProgramReferralsCnt;
    private String isNoblePactNeeded;
    private String isVOPEntryNeeded;
    private boolean onlyNosRecords;

    public JuvenileCasefileForm()
    {
	emptyColl = new ArrayList();
	listsSet = false;
	action = "";
	secondaryAction = "";
	update = false;
	delete = false;
	hasReferrals = false;
	selectedValue = "";
	probationOfficerName = emptyName;
	previousBenefitsAssessments = new ArrayList();
	riskLvl = "";
	needsLvl = "";
	lastPactDate = null;
	userPACTDate = null;
	pactServerName = "";
	pactApplicationName = "";
    }

    /*
     * Resets/clears all properties for the form.
     */
    public void clear()
    {
	isResidentialCasefile = false;
	this.pactServerName = "";
	this.riskLvl = "";
	this.needsLvl = "";
	this.lastPactDate = null;
	this.supervisionNum = null;
	this.sequenceNum = null;
	this.assignmentDate = null;
	this.caseStatusId = null;
	this.currentAge = null;
	this.expectedSupervisionEndDate = null;
	this.supervisionEndDateStr = null;
	this.courtOrderedProbationStartDateStr = null;
	this.activationDate = null;
	this.activationDateStr = null;
	this.isNewMAYSINeeded = false;
	this.isBenefitsAssessmentNeeded = false;
	this.isRiskAssessmentNeeded = false;
	this.isReferralAssessmentNeeded = false;
	this.isCommunityAssessmentNeeded = false;
	this.isInterviewAssessmentNeeded = false;
	this.isResidentialAssessmentNeeded = false;
	this.isTestingAssessmentNeeded = false;
	this.isProgressAssessmentNeeded = false;
	this.isResidentialProgressAssessNeeded = false;
	this.juvenileName = null;
	this.juvenileNum = null;
	this.preferredFirstName = null;
	this.mentalHealthForm = null;
	this.probationOfficerName = emptyName;
	this.probationOfficerId = null;
	this.raceId = null;
	this.sexId = null;
	this.race = null;
	this.supervisionTypeId = null;
	this.priorServices = null;
	this.contactInfo = null;
	this.offenses = null;
	previousBenefitsAssessments.clear();
	this.caseloadManagerId = null;
	this.caseloadManagerName = null;
	this.supervisionOutcome = null;
	this.supervisionOutcomeDesc = null;
	this.closingEvaluation = null;
	this.isCurrUserCaseloadMgr = false;
	this.isLoggedInUserACLM = false;
	this.hasReferrals = false;
	this.supervisionCategoryId = "";
	//this.jpoAssignmentHistories = null ;
	this.pactApplicationName = "";
	this.userPACTDate = null;
	this.userNeedsLvl = "";
	this.userRiskLvl = "";
	this.userPactId = "";
	this.userPACTDateHover = null;
	this.juvUnitId = null;
	this.recordJuvUnit = null;
	this.recordCLM = null;
	this.caseloadManagerEmail = "";
	this.caseloadManagerPhone = null;
	this.activeProgramReferralsCnt = 0;
	this.isNoblePactNeeded = "";
	this.isVOPEntryNeeded = "";
	this.hispanicIndicatorNeedsUpdate= false;
	this.isSubstanceAbuseTjjdRequired = false;
	this.schoolHistoryNeedsUpdate = false;
    }

    /**
     * @return assignmentDate
     */
    public Date getAssignmentDate()
    {
	return assignmentDate;
    }

    /**
     * @return caseStatus
     */
    public String getCaseStatus()
    {
	String description = "";
	if (caseStatusId != null)
	{
	    description = CodeHelper.getCodeDescriptionByCode(CodeHelper.getJuvenileCaseStatuses(), caseStatusId);
	}
	return description;
    }

    /**
     * @return currentAge
     */
    public String getCurrentAge()
    {
	return currentAge;
    }

    /**
     * @return expectedSupervisionEndDate
     */
    public Date getExpectedSupervisionEndDate()
    {
	return expectedSupervisionEndDate;
    }

    /**
     * @return isNewMAYSINeeded
     */
    public boolean getIsNewMAYSINeeded()
    {
	return isNewMAYSINeeded;
    }

    /**
     * @return juvenileName
     */
    public Name getJuvenileName()
    {
	return juvenileName;
    }

    /**
     * Returns the formatted juvenileName. Unknown if null
     * 
     * @return juvenilename
     */
    public String getJuvenileFullName()
    {
	if (juvenileName != null)
	{
	    return juvenileName.getFormattedName();
	}
	return "Unknown";
    }

    /**
     * @return juvenileNum
     */
    public String getJuvenileNum()
    {
	return juvenileNum;
    }

    /**
     * @return mentalHealthForm
     */
    public MentalHealthForm getMentalHealthForm()
    {
	return mentalHealthForm;
    }

    /**
     * @return probationOfficerName
     */
    public Name getProbationOfficerName()
    {
	if (probationOfficerName == null)
	{
	    probationOfficerName = emptyName;
	}

	return probationOfficerName;
    }

    /**
     * Returns the formatted probation officer Name. Unknown if null
     * 
     * @return officername
     */
    public String getProbationOfficerFullName()
    {
	if (probationOfficerName != null)
	{
	    return probationOfficerName.getFormattedName();
	}
	return "Unknown";
    }

    /**
     * @return race
     */
    public String getRace()
    {
	return race;
    }

    public void setRace(String race)
    {
	this.race = race;
    }

    /**
     * @return getSex
     */
    public String getSex()
    {
	String description = "";
	if (sexId != null)
	{
	    description = CodeHelper.getCodeDescriptionByCode(CodeHelper.getJJSSexCodes(false), sexId);
	}

	return description;
    }

    /**
     * @return sequenceNum
     */
    public String getSequenceNum()
    {
	return sequenceNum;
    }

    /**
     * @return supervisionNum
     */
    public String getSupervisionNum()
    {
	return supervisionNum;
    }

    /**
     * @return supervisionType
     */
    public String getSupervisionType()
    {
	String description = "";
	if (supervisionTypeId != null)
	{
	    description = CodeHelper.getCodeDescriptionByCode(CodeHelper.getSupervisionTypes(), supervisionTypeId);
	}

	return description;
    }

    /**
     * @param aAssignmentDate
     */
    public void setAssignmentDate(Date aAssignmentDate)
    {
	assignmentDate = aAssignmentDate;
    }

    /**
     * @param aCurrentAge
     */
    public void setCurrentAge(String aCurrentAge)
    {
	currentAge = aCurrentAge;
    }

    /**
     * @param aExpectedSupervisionEndDate
     */
    public void setExpectedSupervisionEndDate(Date aExpectedSupervisionEndDate)
    {
	expectedSupervisionEndDate = aExpectedSupervisionEndDate;
    }

    /**
     * @param aIsNewMAYSINeeded
     */
    public void setIsNewMAYSINeeded(boolean aIsNewMAYSINeeded)
    {
	isNewMAYSINeeded = aIsNewMAYSINeeded;
    }

    /**
     * @param aJuvenileName
     */
    public void setJuvenileName(Name aJuvenileName)
    {
	juvenileName = aJuvenileName;
    }

    /**
     * @param aJuvenileNum
     */
    public void setJuvenileNum(String aJuvenileNum)
    {
	juvenileNum = aJuvenileNum;
    }

    /**
     * @param form
     */
    public void setMentalHealthForm(MentalHealthForm form)
    {
	mentalHealthForm = form;
    }

    /**
     * @param aProbationOfficerName
     */
    public void setProbationOfficerName(Name aProbationOfficerName)
    {
	probationOfficerName = aProbationOfficerName;
    }

    /**
     * @param aSequenceNum
     */
    public void setSequenceNum(String aSequenceNum)
    {
	sequenceNum = aSequenceNum;
    }

    /**
     * @param aSupervisionNum
     */
    public void setSupervisionNum(String aSupervisionNum)
    {
	supervisionNum = aSupervisionNum;
    }

    /**
     * Returns a collection of assigned referrals for a juvenile casefile
     * 
     * @return collection
     */
    public Collection getJuvenileCasefileReferralsList()
    {
	return juvenileCasefileReferralsList;
    }

    /**
     * Populates the juvenileCasefile form if the casefileId param is different
     * from the existing form or the supervision(aka casefileid) is null
     * 
     * @param casefileId
     */
    public void populateJuvenileCasefileForm(String casefileId)
    {
	this.clear();
	GetJuvenileCasefileEvent getCasefileEvent = (GetJuvenileCasefileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE);
	getCasefileEvent.setSupervisionNumber(casefileId);

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(getCasefileEvent);

	CompositeResponse response = (CompositeResponse) dispatch.getReply();

	JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent) MessageUtil.filterComposite(response, JuvenileCasefileResponseEvent.class);

	if (casefile != null)
	{
	    // Set properties on the casefile form
	    this.setActivationDate(casefile.getActivationDate());
	    this.setAssignmentDate(casefile.getAssignmentDate());
	    this.setCourtOrderedProbationStartDateStr(DateUtil.dateToString(casefile.getCourtOrderedProbationStartDate(), DateUtil.DATE_FMT_1));
	    this.setSupervisionEndDateStr(DateUtil.dateToString(casefile.getSupervisionEndDate(), DateUtil.DATE_FMT_1)); //added as a defectJIMS200077447
	    this.setCaseStatusId(casefile.getCaseStatusId());
	    this.setCurrentAge(casefile.getJuvenileCurrentAge());
	    this.setJuvUnder21(true);
	    this.setDob(casefile.getDobStr());
	    if (casefile.getJuvenileCurrentAge() != null && !"".equals(casefile.getJuvenileCurrentAge()))
	    {
		try
		{
		    int age = Integer.parseInt(this.getCurrentAge());
		    if (age > 20)
		    {
			this.setJuvUnder21(false);
		    }
		}
		catch (Exception e)
		{
		    // do nothing, use default of true
		}
	    }

	    this.setIsNewMAYSINeeded(casefile.getIsMAYSINeeded());
	    this.setBenefitsAssessmentNeeded(casefile.isBenefitsAssessmentNeeded());

	    // set the Risk Assessment Indicators
	    this.setReferralAssessmentNeeded(casefile.isReferralRiskNeeded());
	    this.setInterviewAssessmentNeeded(casefile.isInterviewRiskNeeded());
	    this.setCommunityAssessmentNeeded(casefile.isCommunityRiskNeeded());
	    //this.setResidentialAssessmentNeeded( casefile.isResidentialRiskNeeded( ) ) ;
	    this.setTestingAssessmentNeeded(casefile.isTestingRiskNeeded());
	    this.setProgressAssessmentNeeded(casefile.isProgressRiskNeeded());
	    //this.setResidentialProgressAssessNeeded(casefile.isResProgressRiskNeeded());

	    if (casefile.getSupervisionCategoryId() != null && casefile.getSupervisionCategoryId().equalsIgnoreCase("AR"))
	    { //US 106778 - Post Adjudicated Residential (AR)

		this.setRiskAssessmentNeeded(false);
		this.setResidentialAssessmentNeeded(false);
		this.setResidentialProgressAssessNeeded(false);
		casefile.setResidentialRiskNeeded(false);

	    }
	    else
	    {

		this.setResidentialAssessmentNeeded(casefile.isResidentialRiskNeeded());
		this.setResidentialProgressAssessNeeded(casefile.isResProgressRiskNeeded());

		if (isReferralAssessmentNeeded || isInterviewAssessmentNeeded || isCommunityAssessmentNeeded || isResidentialAssessmentNeeded || isTestingAssessmentNeeded || isProgressAssessmentNeeded || isResidentialProgressAssessNeeded)
		{
		    this.setRiskAssessmentNeeded(true);
		}
	    }
	    
	    this.setSubstanceAbuseTjjdRequired(casefile.getSubabuse());
	    this.setHispanicIndicatorNeedsUpdate(casefile.getHispanic());
	    if ( casefile.getVop() ){
		this.setIsVOPEntryNeeded("Yes");
	    } else {
		this.setIsVOPEntryNeeded("No");
	    }
	

	    // "" is a place holder for name prefix
	    this.setJuvenileName(new Name(casefile.getJuvenileFirstName(), casefile.getJuvenileMiddleName(), casefile.getJuvenileLastName(), "", casefile.getJuvenileNameSuffix()));

	    this.setPreferredFirstName(casefile.getJuvenilePreferredFirstName());
	    this.setJuvenileNum(casefile.getJuvenileNum());
	    this.setProbationOfficerId(casefile.getProbationOfficeId());
	    this.setProbationOfficerName(new Name(casefile.getProbationOfficerFirstName(), casefile.getProbationOfficerMiddleName(), casefile.getProbationOfficerLastName()));
	    //bug 26711
	    this.setProbationOfficerFirstName(casefile.getProbationOfficerFirstName());
	    this.setProbationOfficerLastName(casefile.getProbationOfficerLastName());
	    this.setProbationOfficerMiddleName(casefile.getProbationOfficerMiddleName());

	    this.setProbationOfficerLogonId(casefile.getProbationOfficerLogonId());
	    this.setCaseloadManagerId(casefile.getCaseloadManagerId());
	    this.setCaseloadManagerName(new Name(casefile.getCaseloadManagerFirstName(), casefile.getCaseloadManagerMiddleName(), casefile.getCaseloadManagerLastName()));

	    this.setRaceId(casefile.getJuvenileRaceId());
	    this.setOriginalRaceId(casefile.getJuvenileOriginalRaceId());
	    this.setRace(casefile.getJuvenileRaceDescription());

	    this.setSexId(casefile.getJuvenileSexId());
	    this.setSupervisionNum(casefile.getSupervisionNum());
	    this.setExpectedSupervisionEndDate(casefile.getSupervisionEndDate());
	    this.setSequenceNum(casefile.getSequenceNum());
	    this.setSupervisionTypeId(casefile.getSupervisionTypeId());
	    this.setSupervisionCategoryId(casefile.getSupervisionCategoryId()); // added for #32796.
	    if (notNullNotEmptyString(casefile.getSupervisionOutcome()))
	    {
		this.setSupervisionOutcome(UIJuvenileCasefileClosingHelper.getSupervisionOutcomeCodesDescription(casefile.getSupervisionOutcome()));
	    }

	    if (notNullNotEmptyString(casefile.getSupervisionOutcomeDescriptionId()))
	    {
		//				List temp1 = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_OUTCOME_DESC");
		this.setSupervisionOutcomeDesc(UIJuvenileCasefileClosingHelper.getSupervisionOutcomeDescCodesDescription(casefile.getSupervisionOutcomeDescriptionId()));
	    }
	    this.setClosingEvaluation(casefile.getClosingEvaluation());
	    this.setDetentionFacility(casefile.getDetentionFacility());
	    this.setDetentionFacilityId(casefile.getDetentionFacilityId());
	    this.setDetentionStatus(casefile.getDetentionStatus());
	    this.setDetentionStatusId(casefile.getDetentionStatusId());
	    this.setCurrUserCaseloadMgr(false);
	    this.setControllingReferralNumber("");
	    

	    // load the cl controlling referral from JCCASECLOSEINF table if casefile is closed
	    // if pre adjudication, the controlling referral is the lowest referral in JCASSIGNMENT table
	    // else load the controlling referral from JCCASECLOSEINF
	    System.out.println("SupervisionCategoryId:" + casefile.getSupervisionCategoryId());
	    String cntrlRef = "";
	    if (casefile.getCaseStatusId().equals("C") || casefile.getCaseStatusId().equals("CP"))
	    {
		cntrlRef = casefile.getControllingReferral();
	    }

	    if (cntrlRef == null || "".equals(cntrlRef))
	    {
		/*if (casefile.getSupervisionCategoryId().equals(UIConstants.PRE_ADJUDICATION) || casefile.getSupervisionCategoryId().equals(UIConstants.PRE_PETITION))
		{
		    cntrlRef = UIProgramReferralHelper.getLowestCtrlngRefNbr((String) casefile.getSupervisionNum());
		}
		else
		{ commentd out this as AD/PP controlling referral happens in activation section task 185084- US 182130*/
		    if (StringUtils.isNotEmpty(casefile.getControllingReferralId()))
		    {

			cntrlRef = casefile.getControllingReferralId();
		    }
		    else
		    {
			cntrlRef = casefile.getControllingReferral();
		    }

		//}
	    }

	    if (cntrlRef != null && !"".equals(cntrlRef))
	    {
		String refNumberWithOffense = (JuvenileReferralHelper.getMaxSeverityOffense(cntrlRef, casefile.getJuvenileNum()));
		String shortDesc = (JuvenileReferralHelper.getFinalDisposition(cntrlRef, casefile.getJuvenileNum(), casefile.getSupervisionNum()));
		if (notNullNotEmptyString(refNumberWithOffense))
		{
		    this.setControllingReferralNumber(refNumberWithOffense);
		    if (notNullNotEmptyString(shortDesc))
		    {
			this.setControllingReferralNumber(refNumberWithOffense + " - " + shortDesc);
		    }
		}
		else
		{
		    this.setControllingReferralNumber(cntrlRef);
		}
	    }
	    this.setClosingDate(casefile.getClosingDate());

	    GetOfficerProfileEvent gofe = (GetOfficerProfileEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILE);
	    gofe.setOfficerProfileId(this.getProbationOfficerId());
	    CompositeResponse resp = MessageUtil.postRequest(gofe);
	    OfficerProfileResponseEvent officerProfileResponse = (OfficerProfileResponseEvent) MessageUtil.filterComposite(resp, OfficerProfileResponseEvent.class);

	    if (officerProfileResponse != null)
	    {
		this.setProbationOfficerEmail(officerProfileResponse.getEmail());
		this.setProbationOfficerWorkPhone(new PhoneNumber(officerProfileResponse.getWorkPhone()));
		this.setProbationOfficerWorkPhoneExtn(officerProfileResponse.getExtn());
		this.setJuvUnitId(officerProfileResponse.getJuvUnitId());
		this.setRecordJuvUnit(officerProfileResponse.getJuvUnitId());
		this.setRecordCLM(officerProfileResponse.getManagerId());
	    }

	    OfficerProfile caseloadManagerProfile = UIJuvenileCaseworkHelper.getCaseloadManager(casefile.getCaseloadManagerId());

	    if (caseloadManagerProfile != null)
	    {

		this.setCaseloadManagerEmail(caseloadManagerProfile.getEmail());
		this.setCaseloadManagerPhone(new PhoneNumber(caseloadManagerProfile.getWorkPhoneNum()));

		this.setCaseloadManagerEmail(officerProfileResponse.getCaseLoadManagerEmail());
		this.setCaseloadManagerWorkPhone(new PhoneNumber(officerProfileResponse.getCaseLoadManagerWorkPhone()));
		this.setCaseloadManagerWorkPhoneExtn(officerProfileResponse.getCaseLoadManagerWorkPhoneExtn());
	    }
	}
    }

    /**
     * getReferralsByCasefileId
     * 
     * @param juvNum
     * @param refNum
     * @return
     */
    public static List<JuvenileCasefileReferralResponseEvent> getReferralsByCasefileId(String juvNum, String casefileId)
    {
	GetReferralsByCasefileIdEvent casefileEvt = (GetReferralsByCasefileIdEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETREFERRALSBYCASEFILEID);
	casefileEvt.setJuvenileNum(juvNum);
	casefileEvt.setCaseFileId(casefileId);
	CompositeResponse response = MessageUtil.postRequest(casefileEvt);

	List<JuvenileCasefileReferralResponseEvent> casefiles = MessageUtil.compositeToList(response, JuvenileCasefileReferralResponseEvent.class);
	return casefiles;
    }

    /**
     * @return
     */
    public String getAction()
    {
	return action;
    }

    /**
     * /**
     * 
     * @return
     */
    public boolean isDelete()
    {
	return delete;
    }

    /**
     * @return
     */
    public boolean isListsSet()
    {
	return listsSet;
    }

    /**
     * @return
     */
    public Collection getOffenses()
    {
	return offenses;
    }

    /**
     * @return
     */
    public String getSecondaryAction()
    {
	return secondaryAction;
    }

    /**
     * @return
     */
    public String getSelectedValue()
    {
	return selectedValue;
    }

    /**
     * @return the juvUnder21
     */
    public boolean isJuvUnder21()
    {
	return juvUnder21;
    }

    /**
     * @param juvUnder21
     *            the juvUnder21 to set
     */
    public void setJuvUnder21(boolean juvUnder21)
    {
	this.juvUnder21 = juvUnder21;
    }

    /**
     * @return
     */
    public boolean isUpdate()
    {
	return update;
    }

    /**
     * @param string
     */
    public void setAction(String string)
    {
	action = string;
    }

    /**
     * @param b
     */
    public void setDelete(boolean b)
    {
	delete = b;
    }

    /**
     * @param b
     */
    public void setListsSet(boolean b)
    {
	listsSet = b;
    }

    /**
     * @param collection
     */
    public void setOffenses(Collection collection)
    {
	offenses = collection;
    }

    /**
     * @param string
     */
    public void setSecondaryAction(String string)
    {
	secondaryAction = string;
    }

    /**
     * @param string
     */
    public void setSelectedValue(String string)
    {
	selectedValue = string;
    }

    /**
     * @param b
     */
    public void setUpdate(boolean b)
    {
	update = b;
    }

    /**
     * @return
     */
    public Collection getPreviousBenefitsAssessments()
    {
	return previousBenefitsAssessments;
    }

    /**
     * @param collection
     */
    public void setPreviousBenefitsAssessments(Collection collection)
    {
	previousBenefitsAssessments = collection;
    }

    /**
     * @return Returns the juvenileGuardianList.
     */
    public Collection getJuvenileGuardianList()
    {
	return juvenileGuardianList;
    }

    /**
     * @param juvenileGuardianList
     *            The juvenileGuardianList to set.
     */
    public void setJuvenileGuardianList(Collection juvenileGuardianList)
    {
	this.juvenileGuardianList = juvenileGuardianList;
    }

    /**
     * @return
     */
    public String getCaseStatusId()
    {
	return caseStatusId;
    }

    /**
     * @return
     */
    public String getRaceId()
    {
	return raceId;
    }

    /**
     * @return
     */
    public String getSexId()
    {
	return sexId;
    }

    /**
     * @return
     */
    public String getSupervisionTypeId()
    {
	return supervisionTypeId;
    }

    /**
     * @return
     */
    public String getPriorServices()
    {
	return priorServices;
    }

    /**
     * @return
     */
    public String getContactInfo()
    {
	return contactInfo;
    }

    /**
     * @param string
     */
    public void setCaseStatusId(String string)
    {
	caseStatusId = string;
    }

    /**
     * @param string
     */
    public void setRaceId(String string)
    {
	raceId = string;
    }

    /**
     * @param string
     */
    public void setSexId(String string)
    {
	sexId = string;
    }

    /**
     * @param string
     */
    public void setSupervisionTypeId(String string)
    {
	supervisionTypeId = string;
    }

    /**
     * @param string
     */
    public void setPriorServices(String string)
    {
	priorServices = string;
    }

    /**
     * @param string
     */
    public void setContactInfo(String string)
    {
	contactInfo = string;
    }

    /**
     * @return
     */
    public String getCaseloadManagerId()
    {
	return caseloadManagerId;
    }

    /**
     * @return
     */
    public Name getCaseloadManagerName()
    {
	return caseloadManagerName;
    }

    /**
     * @param string
     */
    public void setCaseloadManagerId(String string)
    {
	caseloadManagerId = string;
    }

    /**
     * @param name
     */
    public void setCaseloadManagerName(Name name)
    {
	caseloadManagerName = name;
    }

    /**
     * @return
     */
    public String getClosingEvaluation()
    {
	return closingEvaluation;
    }

    /**
     * @return
     */
    public String getSupervisionOutcome()
    {
	return supervisionOutcome;
    }

    /**
     * @param string
     */
    public void setClosingEvaluation(String string)
    {
	closingEvaluation = string;
    }

    /**
     * @param string
     */
    public void setSupervisionOutcome(String string)
    {
	supervisionOutcome = string;
    }

    /**
     * @return the supervisionOutcomeDesc
     */
    public String getSupervisionOutcomeDesc()
    {
	return supervisionOutcomeDesc;
    }

    /**
     * @param supervisionOutcomeDesc
     *            the supervisionOutcomeDesc to set
     */
    public void setSupervisionOutcomeDesc(String supervisionOutcomeDesc)
    {
	this.supervisionOutcomeDesc = supervisionOutcomeDesc;
    }

    public String getSupervisionEndDateStr()
    {
	return supervisionEndDateStr;
    }

    /**
     * @param supervisionEndDateStr
     *            The supervisionEndDateStr to set.
     */
    public void setSupervisionEndDateStr(String supervisionEndDateStr)
    {
	this.supervisionEndDateStr = supervisionEndDateStr;

	if (notNullNotEmptyString(supervisionEndDateStr))
	{
	    expectedSupervisionEndDate = DateUtil.stringToDate(supervisionEndDateStr, UIConstants.DATE_FMT_1);
	}
    }

    public String getCourtOrderedProbationStartDateStr()
    {
	return courtOrderedProbationStartDateStr;
    }

    /**
     * @param courtOrderedProbationStartDateSt
     *            The courtOrderedProbationStartDateSt to set.
     */
    public void setCourtOrderedProbationStartDateStr(String courtOrderedProbationStartDateStr)
    {
	this.courtOrderedProbationStartDateStr = courtOrderedProbationStartDateStr;

    }

    /**
     * @return Returns the isCommunityAssessmentNeeded.
     */
    public boolean getIsCommunityAssessmentNeeded()
    {
	return isCommunityAssessmentNeeded;
    }

    /**
     * @return Returns the isInterviewAssessmentNeeded.
     */
    public boolean getIsInterviewAssessmentNeeded()
    {
	return isInterviewAssessmentNeeded;
    }

    /**
     * @return Returns the isProgressAssessmentNeeded.
     */
    public boolean getIsProgressAssessmentNeeded()
    {
	return isProgressAssessmentNeeded;
    }

    /**
     * @return the isResidentialProgressAssessNeeded
     */
    public boolean getIsResidentialProgressAssessNeeded()
    {
	return isResidentialProgressAssessNeeded;
    }

    /**
     * @return Returns the isReferralAssessmentNeeded.
     */
    public boolean getIsReferralAssessmentNeeded()
    {
	return isReferralAssessmentNeeded;
    }

    /**
     * @return Returns the isResidentialAssessmentNeeded.
     */
    public boolean getIsResidentialAssessmentNeeded()
    {
	return isResidentialAssessmentNeeded;
    }

    /**
     * @return Returns the isTestingAssessmentNeeded.
     */
    public boolean getIsTestingAssessmentNeeded()
    {
	return isTestingAssessmentNeeded;
    }

    /**
     * @param isCommunityAssessmentNeeded
     *            The isCommunityAssessmentNeeded to set.
     */
    public void setCommunityAssessmentNeeded(boolean isCommunityAssessmentNeeded)
    {
	this.isCommunityAssessmentNeeded = isCommunityAssessmentNeeded;
    }

    /**
     * @param isInterviewAssessmentNeeded
     *            The isInterviewAssessmentNeeded to set.
     */
    public void setInterviewAssessmentNeeded(boolean isInterviewAssessmentNeeded)
    {
	this.isInterviewAssessmentNeeded = isInterviewAssessmentNeeded;
    }

    /**
     * @param isProgressAssessmentNeeded
     *            The isProgressAssessmentNeeded to set.
     */
    public void setProgressAssessmentNeeded(boolean isProgressAssessmentNeeded)
    {
	this.isProgressAssessmentNeeded = isProgressAssessmentNeeded;
    }

    /**
     * @param isResidentialProgressAssessNeeded
     *            the isResidentialProgressAssessNeeded to set
     */
    public void setResidentialProgressAssessNeeded(boolean isResidentialProgressAssessNeeded)
    {
	this.isResidentialProgressAssessNeeded = isResidentialProgressAssessNeeded;
    }

    /**
     * @param isReferralAssessmentNeeded
     *            The isReferralAssessmentNeeded to set.
     */
    public void setReferralAssessmentNeeded(boolean isReferralAssessmentNeeded)
    {
	this.isReferralAssessmentNeeded = isReferralAssessmentNeeded;
    }

    /**
     * @param isResidentialAssessmentNeeded
     *            The isResidentialAssessmentNeeded to set.
     */
    public void setResidentialAssessmentNeeded(boolean isResidentialAssessmentNeeded)
    {
	this.isResidentialAssessmentNeeded = isResidentialAssessmentNeeded;
    }

    /**
     * @param isTestingAssessmentNeeded
     *            The isTestingAssessmentNeeded to set.
     */
    public void setTestingAssessmentNeeded(boolean isTestingAssessmentNeeded)
    {
	this.isTestingAssessmentNeeded = isTestingAssessmentNeeded;
    }

    /**
     * @return Returns the isRiskAssessmentNeeded.
     */
    public boolean getIsRiskAssessmentNeeded()
    {
	return isRiskAssessmentNeeded;
    }

    /**
     * @param isRiskAssessmentNeeded
     *            The isRiskAssessmentNeeded to set.
     */
    public void setRiskAssessmentNeeded(boolean isRiskAssessmentNeeded)
    {
	this.isRiskAssessmentNeeded = isRiskAssessmentNeeded;
    }

    public String getActivationTime()
    {
	if (activationDate != null)
	{
	    activationTime = DateUtil.dateToString(activationDate, UIConstants.DATE_FMT_1);
	}
	else
	{
	    activationTime = "";
	}

	return activationTime;
    }

    /**
     * @param supervisionEndDateStr
     *            The supervisionEndDateStr to set.
     */
    public void setActivationTime(String activationTime)
    {
	this.activationTime = activationTime;

	if (notNullNotEmptyString(activationTime))
	{
	    activationDate = DateUtil.stringToDate(activationTime, UIConstants.DATE_FMT_1);
	}
    }

    /**
     * @return Returns the activationDateStr.
     */
    public String getActivationDateStr()
    {
	if (activationDate != null)
	{
	    activationDateStr = DateUtil.dateToString(activationDate, UIConstants.DATE_FMT_1);
	}
	else
	{
	    activationDateStr = "";
	}

	return activationDateStr;
    }

    /**
     * @param activationDateStr
     *            The activationDateStr to set.
     */
    public void setActivationDateStr(String activationDateStr)
    {
	this.activationDateStr = activationDateStr;
    }

    /**
     * @return Returns the isBenefitsAssessmentNeeded.
     */
    public boolean getIsBenefitsAssessmentNeeded()
    {
	return isBenefitsAssessmentNeeded;
    }

    /**
     * @param isBenefitsAssessmentNeeded
     *            The isBenefitsAssessmentNeeded to set.
     */
    public void setBenefitsAssessmentNeeded(boolean isBenefitsAssessmentNeeded)
    {
	this.isBenefitsAssessmentNeeded = isBenefitsAssessmentNeeded;
    }

    /**
     * @return Returns the activationDate.
     */
    public Date getActivationDate()
    {
	return activationDate;
    }

    /**
     * @param activationDate
     *            The activationDate to set.
     */
    public void setActivationDate(Date activationDate)
    {
	this.activationDate = activationDate;
    }

    /**
     * @return Returns the probationOfficerId.
     */
    public String getProbationOfficerId()
    {
	return probationOfficerId;
    }

    /**
     * @param probationOfficerId
     *            The probationOfficerId to set.
     */
    public void setProbationOfficerId(String probationOfficerId)
    {
	this.probationOfficerId = probationOfficerId;
    }

    /**
     * @return Returns the probationOfficerLogonId.
     */
    public String getProbationOfficerLogonId()
    {
	return probationOfficerLogonId;
    }

    /**
     * @param probationOfficerLogonId
     *            The probationOfficerLogonId to set.
     */
    public void setProbationOfficerLogonId(String probationOfficerLogonId)
    {
	this.probationOfficerLogonId = probationOfficerLogonId;
    }

    /**
     * @return Returns the detentionFacility.
     */
    public String getDetentionFacility()
    {
	return detentionFacility;
    }

    /**
     * @param detentionFacility
     *            The detentionFacility to set.
     */
    public void setDetentionFacility(String detentionFacility)
    {
	this.detentionFacility = detentionFacility;
    }

    /**
     * @return Returns the detentionFacilityId.
     */
    public String getDetentionFacilityId()
    {
	return detentionFacilityId;
    }

    /**
     * @param detentionFacilityId
     *            The detentionFacilityId to set.
     */
    public void setDetentionFacilityId(String detentionFacilityId)
    {
	this.detentionFacilityId = detentionFacilityId;
    }

    /**
     * @return Returns the detentionStatus.
     */
    public String getDetentionStatus()
    {
	return detentionStatus;
    }

    /**
     * @param detentionStatus
     *            The detentionStatus to set.
     */
    public void setDetentionStatus(String detentionStatus)
    {
	this.detentionStatus = detentionStatus;
    }

    /**
     * @return Returns the detentionStatusId.
     */
    public String getDetentionStatusId()
    {
	return detentionStatusId;
    }

    /**
     * @param detentionStatusId
     *            The detentionStatusId to set.
     */
    public void setDetentionStatusId(String detentionStatusId)
    {
	this.detentionStatusId = detentionStatusId;
    }

    public String getSupervisionCategoryId()
    {
	if (notNullNotEmptyString(supervisionTypeId))
	{
	    String cat = UIJuvenileCaseworkHelper.getSupCatFromType(supervisionTypeId);
	    if (cat != null)
	    {
		return cat;
	    }
	}

	return "";
    }

    /**
     * @param juvenileCasefileReferralsList
     *            The juvenileCasefileReferralsList to set.
     */
    public void setJuvenileCasefileReferralsList(Collection juvenileCasefileReferralsList)
    {
	this.juvenileCasefileReferralsList = juvenileCasefileReferralsList;
    }

    /**
     * @return Returns the isCurrUserCaseloadMgr.
     */
    public boolean isCurrUserCaseloadMgr()
    {
	this.setCurrUserCaseloadMgr(false);
	try
	{
	    String caseMgrId = this.getCaseloadManagerId();
	    String userId = UIUtil.getCurrentUserID();
	    if (caseMgrId != null && userId != null)
	    {
		if (caseMgrId.equalsIgnoreCase(userId))
		{
		    this.setCurrUserCaseloadMgr(true);
		}
	    }
	}
	catch (Exception e)
	{
	    // do nothing
	}

	return isCurrUserCaseloadMgr;
    }

    /**
     * @param isCurrUserCaseloadMgr
     *            The isCurrUserCaseloadMgr to set.
     */
    public void setCurrUserCaseloadMgr(boolean isCurrUserCaseloadMgr)
    {
	this.isCurrUserCaseloadMgr = isCurrUserCaseloadMgr;
    }

    /*
     * 
     */
    public Collection<JuvenileDetentionFacilitiesResponseEvent> getJuvenileFacilityList()
    {
	return juvenileFacilityList;
    }

    public boolean isHasReferrals()
    {
	return hasReferrals;
    }

    public Collection getJpoAssignmentHistories()
    {
	return jpoAssignmentHistories;
    }

    public void setJpoAssignmentHistories(Collection jpoAssignmentHistories)
    {
	this.jpoAssignmentHistories = jpoAssignmentHistories;
    }

    public void setHasReferrals(boolean hasReferrals)
    {
	this.hasReferrals = hasReferrals;
    }

    /*
     * 
     */
    public void setJuvenileFacilityList(Collection<JuvenileDetentionFacilitiesResponseEvent> juvenileFacilityList)
    {
	this.juvenileFacilityList = juvenileFacilityList;
    }

    /*
     * given a string, return true it it's not null and not empty
     * @param str
     * @return
     */
    private boolean notNullNotEmptyString(String str)
    {
	return (str != null && (str.trim().length() > 0));
    }

    public boolean isLoggedInUserACLM()
    {
	return isLoggedInUserACLM;
    }

    public void setLoggedInUserACLM(boolean isLoggedInUserACLM)
    {
	this.isLoggedInUserACLM = isLoggedInUserACLM;
    }

    public String getControllingReferralNumber()
    {
	return controllingReferralNumber;
    }

    public void setControllingReferralNumber(String controllingReferralNumber)
    {
	this.controllingReferralNumber = controllingReferralNumber;
    }

    public Date getClosingDate()
    {
	return closingDate;
    }

    public void setClosingDate(Date closingDate)
    {
	this.closingDate = closingDate;
    }

    public String getClosingDateStr()
    {
	return closingDateStr;
    }

    public void setClosingDateStr(String closingDateStr)
    {
	this.closingDateStr = closingDateStr;
	if (notNullNotEmptyString(closingDateStr))
	{
	    closingDate = DateUtil.stringToDate(closingDateStr, UIConstants.DATE_FMT_1);
	}
    }

    /**
     * @return the probationOfficerEmail
     */
    public String getProbationOfficerEmail()
    {
	return probationOfficerEmail;
    }

    /**
     * @param probationOfficerEmail
     *            the probationOfficerEmail to set
     */
    public void setProbationOfficerEmail(String probationOfficerEmail)
    {
	this.probationOfficerEmail = probationOfficerEmail;
    }

    /**
     * @return the probationOfficerWorkPhone
     */
    public PhoneNumber getProbationOfficerWorkPhone()
    {
	return probationOfficerWorkPhone;
    }

    /**
     * @param probationOfficerWorkPhone
     *            the probationOfficerWorkPhone to set
     */
    public void setProbationOfficerWorkPhone(PhoneNumber probationOfficerWorkPhone)
    {
	this.probationOfficerWorkPhone = probationOfficerWorkPhone;
    }

    /**
     * @return the probationOfficerWorkPhoneExtn
     */
    public String getProbationOfficerWorkPhoneExtn()
    {
	return probationOfficerWorkPhoneExtn;
    }

    /**
     * @param probationOfficerWorkPhoneExtn
     *            the probationOfficerWorkPhoneExtn to set
     */
    public void setProbationOfficerWorkPhoneExtn(String probationOfficerWorkPhoneExtn)
    {
	this.probationOfficerWorkPhoneExtn = probationOfficerWorkPhoneExtn;
    }

    public String getProbationOfficerFirstName()
    {
	return probationOfficerFirstName;
    }

    public void setProbationOfficerFirstName(String probationOfficerFirstName)
    {
	this.probationOfficerFirstName = probationOfficerFirstName;
    }

    public String getProbationOfficerLastName()
    {
	return probationOfficerLastName;
    }

    public void setProbationOfficerLastName(String probationOfficerLastName)
    {
	this.probationOfficerLastName = probationOfficerLastName;
    }

    public String getProbationOfficerMiddleName()
    {
	return probationOfficerMiddleName;
    }

    public void setProbationOfficerMiddleName(String probationOfficerMiddleName)
    {
	this.probationOfficerMiddleName = probationOfficerMiddleName;
    }

    public PhoneNumber getCaseloadManagerPhone()
    {
	return this.caseloadManagerPhone;
    }

    public void setCaseloadManagerPhone(PhoneNumber caseloadManagerPhone)
    {
	this.caseloadManagerPhone = caseloadManagerPhone;
    }

    public boolean isRestrictedAccess()
    {
	return restrictedAccess;
    }

    public void setRestrictedAccess(boolean restrictedAccess)
    {
	this.restrictedAccess = restrictedAccess;
    }

    /**
     * @param supervisionCategoryId
     *            the supervisionCategoryId to set
     */
    public void setSupervisionCategoryId(String supervisionCategoryId)
    {
	this.supervisionCategoryId = supervisionCategoryId;
    }

    /**
     * @return the pactAuthKey
     */
    public String getPactAuthKey()
    {
	return pactAuthKey;
    }

    /**
     * @param pactAuthKey
     *            the pactAuthKey to set
     */
    public void setPactAuthKey(String pactAuthKey)
    {
	this.pactAuthKey = pactAuthKey;
    }

    /**
     * @return the userId
     */
    public String getUserId()
    {
	return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId)
    {
	this.userId = userId;
    }

    /**
     * @return the riskLvl
     */
    public String getRiskLvl()
    {
	return riskLvl;
    }

    /**
     * @param riskLvl
     *            the riskLvl to set
     */
    public void setRiskLvl(String riskLvl)
    {
	this.riskLvl = riskLvl;
    }

    /**
     * @return the needsLvl
     */
    public String getNeedsLvl()
    {
	return needsLvl;
    }

    /**
     * @param needsLvl
     *            the needsLvl to set
     */
    public void setNeedsLvl(String needsLvl)
    {
	this.needsLvl = needsLvl;
    }

    /**
     * @return the lastPactDate
     */
    public String getLastPactDate()
    {
	return lastPactDate;
    }

    /**
     * @param lastPactDate
     *            the lastPactDate to set
     */
    public void setLastPactDate(String lastPactDate)
    {
	this.lastPactDate = lastPactDate;
    }

    /**
     * @return the pactServerName
     */
    public String getPactServerName()
    {
	return pactServerName;
    }

    /**
     * @param pactServerName
     *            the pactServerName to set
     */
    public void setPactServerName(String pactServerName)
    {
	this.pactServerName = pactServerName;
    }

    /**
     * @return the riskLvlDesc
     */
    public String getRiskLvlDesc()
    {
	return riskLvlDesc;
    }

    /**
     * @param riskLvlDesc
     *            the riskLvlDesc to set
     */
    public void setRiskLvlDesc(String riskLvlDesc)
    {
	this.riskLvlDesc = riskLvlDesc;
    }

    /**
     * @return the needLvlDesc
     */
    public String getNeedLvlDesc()
    {
	return needLvlDesc;
    }

    /**
     * @param needLvlDesc
     *            the needLvlDesc to set
     */
    public void setNeedLvlDesc(String needLvlDesc)
    {
	this.needLvlDesc = needLvlDesc;
    }

    public String getUserPACTDate()
    {
	return userPACTDate;
    }

    public void setUserPACTDate(String userPACTDate)
    {
	this.userPACTDate = userPACTDate;
    }

    public String getUserRiskLvl()
    {
	return userRiskLvl;
    }

    public void setUserRiskLvl(String userRiskLvl)
    {
	this.userRiskLvl = userRiskLvl;
    }

    public String getUserNeedsLvl()
    {
	return userNeedsLvl;
    }

    public void setUserNeedsLvl(String userNeedsLvl)
    {
	this.userNeedsLvl = userNeedsLvl;
    }

    /**
     * @return the pactApplicationName
     */
    public String getPactApplicationName()
    {
	return pactApplicationName;
    }

    /**
     * @param pactApplicationName
     *            the pactApplicationName to set
     */
    public void setPactApplicationName(String pactApplicationName)
    {
	this.pactApplicationName = pactApplicationName;
    }

    /**
     * @return the admissionInfo
     */
    public JuvenileDetentionFacilitiesResponseEvent getAdmissionInfo()
    {
	return admissionInfo;
    }

    /**
     * @param admissionInfo
     *            the admissionInfo to set
     */
    public void setAdmissionInfo(JuvenileDetentionFacilitiesResponseEvent admissionInfo)
    {
	this.admissionInfo = admissionInfo;
    }

    /**
     * @return the traits
     */
    public Collection<JuvenileTraitResponseEvent> getTraits()
    {
	return traits;
    }

    /**
     * @param traits
     *            the traits to set
     */
    public void setTraits(Collection<JuvenileTraitResponseEvent> traits)
    {
	this.traits = traits;
    }

    /**
     * @return the profileDetail
     */
    public JuvenileProfileDetailResponseEvent getProfileDetail()
    {
	return profileDetail;
    }

    /**
     * @param profileDetail
     *            the profileDetail to set
     */
    public void setProfileDetail(JuvenileProfileDetailResponseEvent profileDetail)
    {
	this.profileDetail = profileDetail;
    }

    /**
     * @return the memberContact
     */
    public MemberContact getMemberContact()
    {
	return memberContact;
    }

    /**
     * @param memberContact
     *            the memberContact to set
     */
    public void setMemberContact(MemberContact memberContact)
    {
	this.memberContact = memberContact;
    }

    /**
     * @return the bookingOffenseCd
     */
    public String getBookingOffenseCd()
    {
	return bookingOffenseCd;
    }

    /**
     * @param bookingOffenseCd
     *            the bookingOffenseCd to set
     */
    public void setBookingOffenseCd(String bookingOffenseCd)
    {
	this.bookingOffenseCd = bookingOffenseCd;
    }

    /**
     * @return the bookingOffenseCdDesc
     */
    public String getBookingOffenseCdDesc()
    {
	return bookingOffenseCdDesc;
    }

    /**
     * @param bookingOffenseCdDesc
     *            the bookingOffenseCdDesc to set
     */
    public void setBookingOffenseCdDesc(String bookingOffenseCdDesc)
    {
	this.bookingOffenseCdDesc = bookingOffenseCdDesc;
    }

    public boolean getIsResidentialCasefile()
    {
	return isResidentialCasefile;
    }

    /**
     * @return the isResidentialCasefile
     */
    public boolean isResidentialCasefile()
    {
	return isResidentialCasefile;
    }

    /**
     * @param isResidentialCasefile
     *            the isResidentialCasefile to set
     */
    public void setResidentialCasefile(boolean isResidentialCasefile)
    {
	this.isResidentialCasefile = isResidentialCasefile;
    }

    public String getDob()
    {
	return dob;
    }

    public void setDob(String dob)
    {
	this.dob = dob;
    }

    public String getDualStatus()
    {
	return dualStatus;
    }

    public void setDualStatus(String dualStatus)
    {
	this.dualStatus = dualStatus;
    }

    public String getSpecialCategoryCd()
    {
	return specialCategoryCd;
    }

    public void setSpecialCategoryCd(String specialCategoryCd)
    {
	this.specialCategoryCd = specialCategoryCd;
    }

    public String getHispanic()
    {
	return hispanic;
    }

    public void setHispanic(String hispanic)
    {
	this.hispanic = hispanic;
    }

    public String getOriginalRaceId()
    {
	return originalRaceId;
    }

    public void setOriginalRaceId(String originalRaceId)
    {
	this.originalRaceId = originalRaceId;
    }

    public String getPdaReadOnly()
    {
	return pdaReadOnly;
    }

    public void setPdaReadOnly(String pdaReadOnly)
    {
	this.pdaReadOnly = pdaReadOnly;
    }

    public String getPactReadOnly()
    {
	return pactReadOnly;
    }

    public void setPactReadOnly(String pactReadOnly)
    {
	this.pactReadOnly = pactReadOnly;
    }

    public String getPreferredFirstName()
    {
	return preferredFirstName;
    }

    public void setPreferredFirstName(String preferredFirstName)
    {
	this.preferredFirstName = preferredFirstName;
    }

    public String getJuvUnitId()
    {
	return juvUnitId;
    }

    public void setJuvUnitId(String juvUnitId)
    {
	this.juvUnitId = juvUnitId;
    }

    public String getRecordJuvUnit()
    {
	return recordJuvUnit;
    }

    public void setRecordJuvUnit(String recordJuvUnit)
    {
	this.recordJuvUnit = recordJuvUnit;
    }

    public String getRecordCLM()
    {
	return recordCLM;
    }

    public void setRecordCLM(String recordCLM)
    {
	this.recordCLM = recordCLM;
    }

    public String getUserPACTDateHover()
    {
	return userPACTDateHover;
    }

    public void setUserPACTDateHover(String userPACTDateHover)
    {
	this.userPACTDateHover = userPACTDateHover;
    }

    public String getUserPactId()
    {
	return userPactId;
    }

    public void setUserPactId(String userPactId)
    {
	this.userPactId = userPactId;
    }

    public String getTraitTypeId()
    {
	return this.traitTypeId;
    }

    public void setTraitTypeId(String traittypeId)
    {
	this.traitTypeId = traittypeId;
    }

    public String getTraitTypeDescription()
    {
	return this.traitTypeDescription;
    }

    public void setTraitTypeDescription(String traitTypeDesc)
    {
	this.traitTypeDescription = traitTypeDesc;
    }

    public String getCaseloadManagerEmail()
    {
	return caseloadManagerEmail;
    }

    public void setCaseloadManagerEmail(String caseloadManagerEmail)
    {
	this.caseloadManagerEmail = caseloadManagerEmail;
    }

    public PhoneNumber getCaseloadManagerWorkPhone()
    {
	return caseloadManagerWorkPhone;
    }

    public void setCaseloadManagerWorkPhone(PhoneNumber caseloadManagerWorkPhone)
    {
	this.caseloadManagerWorkPhone = caseloadManagerWorkPhone;
    }

    public String getCaseloadManagerWorkPhoneExtn()
    {
	return caseloadManagerWorkPhoneExtn;
    }

    public void setCaseloadManagerWorkPhoneExtn(String caseloadManagerWorkPhoneExtn)
    {
	this.caseloadManagerWorkPhoneExtn = caseloadManagerWorkPhoneExtn;
    }

    public void setNewMAYSINeeded(boolean isNewMAYSINeeded)
    {
	this.isNewMAYSINeeded = isNewMAYSINeeded;
    }    
    
    public int getActiveProgramReferralsCnt()
    {
        return activeProgramReferralsCnt;
    }

    public void setActiveProgramReferralsCnt(int activeProgramReferralsCnt)
    {
        this.activeProgramReferralsCnt = activeProgramReferralsCnt;
    }

    public String getIsNoblePactNeeded()
    {
        return isNoblePactNeeded;
    }

    public void setIsNoblePactNeeded(String isNoblePactNeeded)
    {
        this.isNoblePactNeeded = isNoblePactNeeded;
    }

    public String getIsVOPEntryNeeded()
    {
        return isVOPEntryNeeded;
    }

    public void setIsVOPEntryNeeded(String isVOPEntryNeeded)
    {
        this.isVOPEntryNeeded = isVOPEntryNeeded;
    }

    public boolean getHispanicIndicatorNeedsUpdate()
    {
        return hispanicIndicatorNeedsUpdate;
    }

    public void setHispanicIndicatorNeedsUpdate(boolean hispanicIndicatorNeedsUpdate)
    {
        this.hispanicIndicatorNeedsUpdate = hispanicIndicatorNeedsUpdate;
    }

    public boolean getIsSubstanceAbuseTjjdRequired()
    {
        return isSubstanceAbuseTjjdRequired;
    }

    public void setSubstanceAbuseTjjdRequired(boolean isSubstanceAbuseTjjdRequired)
    {
        this.isSubstanceAbuseTjjdRequired = isSubstanceAbuseTjjdRequired;
    }

    public boolean getSchoolHistoryNeedsUpdate()
    {
        return schoolHistoryNeedsUpdate;
    }

    public void setSchoolHistoryNeedsUpdate(boolean schoolHistoryNeedsUpdate)
    {
        this.schoolHistoryNeedsUpdate = schoolHistoryNeedsUpdate;
    }

    public boolean isOnlyNosRecords()
    {
        return onlyNosRecords;
    }

    public void setOnlyNosRecords(boolean onNosRecords)
    {
        this.onlyNosRecords = onNosRecords;
    }   
    

}
