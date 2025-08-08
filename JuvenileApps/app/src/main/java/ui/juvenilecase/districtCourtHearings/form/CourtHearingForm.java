package ui.juvenilecase.districtCourtHearings.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCourtDecisionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileHearingTypeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.districtCourtHearings.reply.NumberInquiryResponse;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenile.reply.JuvenileDetentionVisitResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenilecase.reply.AttorneyNameAndAddressResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import net.minidev.json.JSONObject;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.list.LazyList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import pd.codetable.criminal.JuvenileDispositionCode;
import pd.juvenilecase.JJSLastAttorney;
import pd.juvenilecase.referral.JJSOffense;
import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.juvenilecase.casefile.form.GuardianBean;
import ui.juvenilecase.form.JuvenileMemberForm;

/**
 * CourtHearingForm
 */
public class CourtHearingForm extends ActionForm
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String amendmentDate;
    private String attorneyName;
    private String galName;
    private String attorneyConnection; //attorney Connection;
    private String attorneyNameHistory;
    private String barNumber;
    private String galBarNumber;
    private boolean adlitemUpdateFlag;
    private String chainNumber;
    private String comments;
    private String courtDate;
    private Date courtDateWithTime;
    private String courtId;
    private String courtIdWithSuffix;
    private String courtTime;
    private String filingDate;

    private String hearingCategory;
    private String hearingDisposition;
    private String hearingDispositionDesc;
    private String hearingResult;
    private String hearingResultDesc;
    private String hearingType;
    private String hearingTypeDesc;

    // Properties for hearingTypes

    private String issueFlag;
    private String juryFlag;

    private String juvenileFirstName;
    private String juvenileLastName;
    private String juvenileMiddleName;
    private String juvenileNumber;
    private String juvenileName;
    
    // Dual status for Juvenile
    private String dualStatus;

    private Date lcDate;
    private String lcTime;
    private String lcUser;
    private String optionFlag;

    private String petitionAllegation;
    private String petitionAllegationDesc;
    private String petitionAmendment;
    private String petitionNumber;
    private String petitionStatus;
    private String petitionType;
    private String petitionAllegationSev;
    private String petitionSeqNum;
    private String prevNotes;
    private String referralNumber;
    private String referralDate;
    private String resetHearingType;
    private String seqNumber;
    private String updateFlag;
    
    //US 105193
    
    private String daLogNum;
    private Date daDateOut;
    private JJSOffense offenseDetail;
    private String offenseCodeIds;
    private String offenseCategories;
    private String offenseDescriptions;
    private String logStatus;
    private List<JJSOffenseResponseEvent> offenseDetails = new ArrayList<JJSOffenseResponseEvent>();

    private int courtSeqNumber;
    private int courtChainNumber;

    private String displaying;
    private Collection<DocketEventResponseEvent> dktSearchResults;

    //court action
    private List<DocketEventResponseEvent> allDktSearchResults = new ArrayList<DocketEventResponseEvent>();

    private List<DocketEventResponseEvent> updatedDocketList = new ArrayList<DocketEventResponseEvent>();
    private String[] updatedDocketEventIds;

    private Map<String, DocketEventResponseEvent> dktSearchResultsMap;
    private Map<String, DocketEventResponseEvent> originalDktSearchResultsMap;
    private Collection<DocketEventResponseEvent> ancillaryDockets; //added for court Docket display iterative search
    private Collection<DocketEventResponseEvent> delinquencyDockets; //added for court Docket display iterative search
    private Collection<DocketEventResponseEvent> allDockets; //added for court Docket display iterative search
    private String dcktEvtId;
    private String dcktEvtIdKey;
    private String docketEventsRec;

    // added for Facility
    private String rectype;

    // juvenile Profile Information:
    private JuvenileProfileDetailResponseEvent profileDetail;
    private JuvenileFacilityHeaderResponseEvent facilityHeader;
    private String facilityStatus;
    private String facilityStatusDesc;
    private String detainedFacility;
    private String detainedFacilityDesc;
    private DocketEventResponseEvent selectedDocket;

    // family information
    private Address memberAddress;
    private JuvenileMemberForm.MemberContact memberContact;
    private Collection<JuvenileDetentionVisitResponseEvent> detVisits;
    private Collection<GuardianBean> guardians;
    private FamilyMemberDetailResponseEvent memberDetail;
    private Name father;
    private Address fatherAddress;
    private Name mother;
    private Address motherAddress;
    private Name guardian;
    private Address guardianAddress;
    private Name careGiver;
    private Address careGiverAddress;
    private JuvenileMemberForm.MemberContact fatherContact;
    private JuvenileMemberForm.MemberContact motherContact;
    private JuvenileMemberForm.MemberContact guardianContact;
    private JuvenileMemberForm.MemberContact careGiverContact;

    //codetables
    private Collection<CodeResponseEvent> petitionStatuses;
    private Collection<CodeResponseEvent> petitionTypes;
    private Collection<CodeResponseEvent> petitionAmendments;
    private PetitionResponseEvent petitionResp;

    private Collection<CodeResponseEvent> subpoenasToBePrinted;
    private Collection<JuvenileHearingTypeResponseEvent> hearingTypes = null;
    private Collection<JuvenileHearingTypeResponseEvent> ancillaryHearingTypes = null;
    private Collection<JuvenileHearingTypeResponseEvent> courtHearingTypes = null;
    private Collection<CodeResponseEvent> typeCase;

    private String finalDispEntered; 
    private String finalReleaseDate; //88723

    //added for ancillary setting

    private String typeCaseStr;
    private String respondentName;
    private String settingReason;
    // private Collection ancillarysettings;
    private int jjsCLAncillaryId;

    //added for court activity

    private int searchResultSize;

    //added for NameSearch
    private Collection juvenileProfiles;
    private String sexId;
    private String juvenileDOB;
    private String dateOfBirth;
    int aSearchResultSize;
    private String raceId;
    private String searchType;
    private String restrictedAccessFeature;
    private String ssn;
    private String resultsPage;
    private String hispanic;
    private String multiracial;
    private String ethnicity;
    private JuvenileSchoolHistoryResponseEvent school;
    private boolean detVisitBanned;
    private boolean hasPendingCasefiles;
    private boolean hasActiveCasefiles;
    private boolean hasCasefiles;
    private boolean hasPostAdjCatCasefile;
    private String jpoOfRecord;
    private String jpoOfRecID;
    private String masterStatus;
    private String masterStatusId;

    //Referral Inquiry
    private String verificationStatus;
    private String profileStatus;
    private String supervisionCategory;
    private String supervisionType;
    private String supervisionCategoryId;
    private String supervisionTypeId;
    private Collection<JuvenileProfileReferralListResponseEvent> referralList;
    private Collection<PetitionResponseEvent> petitionList;   

    private String referralRec;

    //subpoena information
    private String preparationDate;
    private String cert;
    private String plaintiff;
    private String relationship;
    private String[] selectedSubpoenasToBePrinted;
    private String selectedSubpoenas;
    private String reportsToBePrinted;

    private String validateMsg;
    private String categoryId;
    private String confirmMsg;
    private String countyId;
    private String dpsCodeId;
    private String errMessage;
    private String fromPage;
    private String selectedValue;
    private String alphaCodeId;
    private List offenseResultList;

    private String shortDesc;
    private String cursorPosition;

    private String offenseDateStr;

    private Collection<JuvenileCasefileOffenseCodeResponseEvent> codetableDataList;
    private Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList;
    private Collection<NumberInquiryResponse> juvCourtHearingList;
    private Collection<NumberInquiryResponse> juvCourtHearingHist;

    private Collection<JuvenileDispositionCode> courtDecisions;
    private Collection<JuvenileDispositionCodeResponseEvent> courtDecisionsResponses;
    private Collection<JuvenileDispositionCodeResponseEvent> courtDispositionResponses;
    private Collection<JuvenileDispositionCodeResponseEvent> courtResultResponses;
    private String courtResult;

    private String action;
    private String actionError;
    private Collection<JSONObject> holidaysList;
    private Collection<JSONObject> courtDecisionsList; //detention validation.
    private Collection<JSONObject> districtcourtDecisionsList;
    private Collection<JSONObject> hearingTypesList;
    private JuvenileDispositionCodeResponseEvent courtDisposition;
    private String hearingCorrectionFlag;
    private String petitionCorrectionFlag;
    

    private String trackingNum;
    private String juvAge;
    private boolean subpoenaPrinted;
    private String queryString;
    private String currAction;
    private String prevAction;

    //Bug #69323
    private String transferTo;

    //Bug #69865
    private String pagerOffset;
    private boolean barNumberValidated;

    //delete functionality for court action
    private String deleteFlag;
    private String deleteDocketEventId;
    private String deleteDocketEventIdKey;
    private String deleteSettingDate;
    private String deleteDocketEventType;
    private String currentSettingDocketEventId;
    private String currentSettingDocketEventIdKey;//bug: 81539
    private String currentSettingChainNumber;
    private DocketEventResponseEvent deletedDocket;
    private String isLastSetting;
    private String isOnlySetting;
    private boolean disableResetDate;
    private List<JuvenileCourtDecisionCodeResponseEvent> detentionHearingResults;
    private String actionMessage;
    private boolean inSpecialtyCourt = false;
    private String specialtyCourtDescription="";
    private String SID;
    private String jotPetitionnum;
    private String lastprobendDate; //150850 
    private boolean isCourtActionReady = false;
    private String ncicCode; 
    private String assignedAttorney="0"; 
    private JJSLastAttorney jjslastAttorney;

    //US 67132 changes
    private String isFatherIncarcerated;
    private String isMotherIncarcerated;
    private String isFatherDeceased;
    private String isMotherDeceased;
    private boolean showmessage = false;
    private String CJISNum;
    private String typeCode;
    private String origjuvNum;

    
    //US 67132 changes ends
    /**
     * 
     */
    public void clearForm()
    {
	this.juvenileFirstName = "";
	this.juvenileLastName = "";
	this.juvenileMiddleName = "";
	this.juvenileName = "";
	this.raceId = "";
	this.sexId = "";
	this.juvenileDOB = "";
	this.juvAge = "";
	this.docketEventsRec = null;
	this.dktSearchResults = new ArrayList<DocketEventResponseEvent>();
	this.detentionHearingResults = new ArrayList<JuvenileCourtDecisionCodeResponseEvent>();
	this.searchResultSize = 0;
	this.attorneyName = "";
	this.barNumber = "";
	this.galName="";
	this.galBarNumber="";
	this.courtDate = "";
	this.adlitemUpdateFlag = false;
	this.daLogNum = "";
	this.offenseDetail = null;
	this.offenseCodeIds = "";
	this.offenseCategories = "";
	this.offenseDescriptions = "";
	this.offenseDetails = new ArrayList<>();
	this.logStatus = null;
	this.daDateOut = null;
	this.masterStatus = null;
	this.masterStatusId = null;
	this.SID=null;	
	this.lastprobendDate=null;
	this.isCourtActionReady = false;
	this.ncicCode="";
	this.isFatherIncarcerated = "";
	this.isMotherIncarcerated = "";
	this.isFatherDeceased = "";
	this.isMotherDeceased = "";
	this.jjslastAttorney=null;
	this.CJISNum="";
    }

    /**
     * Used in court action. Could not use other methods as court id and date
     * are reset.
     */
    public void clear()
    {
	allDktSearchResults = new ArrayList<DocketEventResponseEvent>(); // courtDockets
	updatedDocketList = new ArrayList<DocketEventResponseEvent>();
	pagerOffset = null;
	attorneyName = "";
	galName="";
	galBarNumber="";
	attorneyDataList = Collections.emptyList();
	originalDktSearchResultsMap = new HashMap<String, DocketEventResponseEvent>();
	detentionHearingResults = new ArrayList<JuvenileCourtDecisionCodeResponseEvent>();
	prevAction="";

	deleteFlag = "";
	deleteDocketEventId = "";
	deleteDocketEventType = "";
	deleteDocketEventIdKey="";
	currentSettingDocketEventIdKey="";
	currentSettingDocketEventId = "";
	isLastSetting = "";
	ncicCode="";
	disableResetDate = false;
	isCourtActionReady = false;
	jjslastAttorney=null;
	CJISNum="";
    }

    /**
     * Reset form fields.
     */
    public void reset()
    {
	deleteFlag = "";
	deleteDocketEventId = "";
	juvenileName = "";
	subpoenaPrinted = false;
	barNumberValidated = false;
	selectedSubpoenasToBePrinted = null;
	selectedSubpoenasToBePrinted = new String[] {};
	detentionHearingResults = new ArrayList<JuvenileCourtDecisionCodeResponseEvent>();

	selectedSubpoenas = "";
	attorneyDataList = null;
	action = "";
	cert = "";
	prevAction = "";
	currAction = "";
	amendmentDate = "";
	attorneyName = "";
	attorneyNameHistory = "";
	barNumber = "";
	galName="";
	galBarNumber="";
	chainNumber = "";
	comments = "";
	courtDate = "";
	courtDateWithTime = new Date();
	courtId = "";
	courtTime = "";
	filingDate = "";
	hearingCategory = "";
	hearingDisposition = "";
	hearingDispositionDesc = "";
	hearingResult = "";
	hearingResultDesc = "";
	hearingType = "";
	hearingTypeDesc = "";
	facilityHeader = new JuvenileFacilityHeaderResponseEvent();
	validateMsg = "";
	facilityStatus = "";
	facilityStatusDesc = "";
	detainedFacility = "";
	detainedFacilityDesc = "";
	offenseResultList = new ArrayList();
	finalDispEntered = "";
	cursorPosition = "";
	hearingCorrectionFlag = "";
	petitionCorrectionFlag = "";
	// Properties for hearingTypes
	//hearingTypes = new ArrayList<JuvenileHearingTypeResponseEvent>(); //commented to not clear the list/drop down for the next page
	memberAddress = null;//new Address();
	memberContact = null;//new JuvenileMemberForm.MemberContact();
	issueFlag = "";
	juryFlag = "";
	typeCaseStr = "";
	juvenileFirstName = "";
	juvenileLastName = "";
	juvenileMiddleName = "";
	juvenileNumber = "";

	juvenileProfiles = null;
	aSearchResultSize = 0;
	juvenileFirstName = null;
	juvenileLastName = null;
	juvenileDOB = null;
	raceId = null;
	sexId = null;
	dateOfBirth = "";
	resultsPage = null;
	school = new JuvenileSchoolHistoryResponseEvent();
	guardians = null;
	ethnicity = null;
	jpoOfRecord = null;
	jpoOfRecID = null;
	ssn = "";

	lcDate = new Date();
	lcTime = "";
	lcUser = "";
	optionFlag = "";
	attorneyConnection = "";

	petitionAllegation = "";
	petitionAllegationDesc = "";
	petitionAmendment = "";
	petitionNumber = "";
	petitionStatus = "";
	petitionType = "";
	plaintiff = "";

	mother = null;
	father = null;
	relationship = "";
	careGiver = null;
	guardian = null;
	careGiverAddress = null;
	guardianAddress = null;
	fatherAddress = null;
	motherAddress = null;
	guardianContact = null;
	careGiverContact = null;
	motherContact = null;
	fatherContact = null;

	prevNotes = "";
	referralNumber = "";
	resetHearingType = "";
	seqNumber = "";
	updateFlag = "";
	courtSeqNumber = 0;
	courtChainNumber = 0;
	dktSearchResults = new ArrayList<DocketEventResponseEvent>(); // courtDockets
	allDktSearchResults = new ArrayList<DocketEventResponseEvent>(); // courtDockets
	ancillaryDockets = new ArrayList<DocketEventResponseEvent>();
	delinquencyDockets = new ArrayList<DocketEventResponseEvent>();
	allDockets = new ArrayList<DocketEventResponseEvent>();
	updatedDocketList = new ArrayList<DocketEventResponseEvent>();

	// added for Facility
	rectype = "";
	petitionAllegationSev = "";
	respondentName = "";
	settingReason = "";
	actionError = "";
	
	daLogNum = "";
	offenseDetail = null;
	offenseCodeIds = "";
	offenseCategories = "";
	offenseDescriptions = "";
	offenseDetails = new ArrayList<JJSOffenseResponseEvent>();
	
	logStatus = null;
	daDateOut = null;
	
	masterStatus = null;
	masterStatusId = null;
	lastprobendDate=null;
	this.isCourtActionReady = false;
	jjslastAttorney=null;
	showmessage=false;
	CJISNum="";
    }

    /**
     * Reset - called every submit
     */
    public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
    {
	if (prevAction==null || (prevAction!=null && !prevAction.equalsIgnoreCase("searchAttorneyFromCourtAction")&& !prevAction.equalsIgnoreCase("findAttorney")))// commented !prevAction.equalsIgnoreCase("findAttorney") as the docketlist was not getting updated if the action was changing attorney bug 142990 but later added back as there was offset issues after selecting attorney-bug 143633 
	{
	    String pagerOffset = (String) aRequest.getParameter("pager.offset");
	    if (allDktSearchResults.size() > 3)
	    {
		if (pagerOffset != null && !pagerOffset.isEmpty())
		{ // do the sub listing only when there is the pagination.
		    if (allDktSearchResults != null && !allDktSearchResults.isEmpty())
		    {
			this.updatedDocketList = new ArrayList<DocketEventResponseEvent>(); //initialize the list everytime.
			setUpdatedDocketList(new ArrayList<DocketEventResponseEvent>(currentlyViewedList((List<DocketEventResponseEvent>) allDktSearchResults, pagerOffset)));
		    }
		}
	    }
	    else
	    {
		//API link - https://www.javatips.net/api/EclipseTrader-master/org.apache.commons.collections/src/org/apache/commons/collections/list/LazyList.java
		Factory factory = new Factory() {
		    public Object create()
		    {
			return new DocketEventResponseEvent();
		    }
		};
		allDktSearchResults = LazyList.decorate(allDktSearchResults, factory);
		this.updatedDocketList = new ArrayList<DocketEventResponseEvent>(); //initialize the list everytime.
		setUpdatedDocketList(allDktSearchResults);
	    }
	}
    }


    /**
     * currentlyViewedList
     * 
     * @param list
     * @param pagerOffset
     * @return List<DocketEventResponseEvent>-Sub List
     */
    private List<DocketEventResponseEvent> currentlyViewedList(List<DocketEventResponseEvent> list, String pagerOffset)
    {
	if (pagerOffset != null && pagerOffset.length() > 0)
	{
	    try
	    {
		int from = Integer.parseInt(pagerOffset);
		int to = from + 3;
		if (to > list.size())
		{
		    to = list.size();
		}
		return list.subList(from, to);
	    }
	    catch (NumberFormatException nfe)
	    { //empty
	    }
	}
	return list;
    }

    /**
     * @return the allDktSearchResults
     */
    public List<DocketEventResponseEvent> getAllDktSearchResults()
    {
	return allDktSearchResults;
    }

    /**
     * @param allDktSearchResults
     *            the allDktSearchResults to set
     */
    public void setAllDktSearchResults(List<DocketEventResponseEvent> allDktSearchResults)
    {
	this.allDktSearchResults = allDktSearchResults;
    }

    /**
     * Should have worked when using indexed property. But it did not. Used the
     * alternative using lazy list on reset method.
     ***/

    /*    // non-bean version so as not to confuse struts.
        public void populateItems(ArrayList<DocketEventResponseEvent> dockets)
        {
    	this.allDktSearchResults.addAll(dockets);
        }

        *//**
     * getAllDktSearchResults
     * 
     * @param index
     * @return DocketEventResponseEvent
     */
    /*
    public DocketEventResponseEvent getAllDktSearchResults(int index)
    {
    // automatically grow List size
    if (index <= allDktSearchResults.size())
    {
        allDktSearchResults.add(new DocketEventResponseEvent());
    }

    return allDktSearchResults.get(index);
    }

    
    *//**
     * setAllDktSearchResults
     * 
     * @param docket
     */
    /*
    public void setAllDktSearchResults(DocketEventResponseEvent docket)
    {
    this.allDktSearchResults.add(docket);
    }
    */
    /**
     * Should have worked when using indexed property. But it did not. Used the
     * alternative using lazy list.
     ***/

    public String getCategoryId()
    {
	return categoryId;
    }

    public void setCategoryId(String categoryId)
    {
	this.categoryId = categoryId;
    }

    public String getConfirmMsg()
    {
	return confirmMsg;
    }

    public void setConfirmMsg(String confirmMsg)
    {
	this.confirmMsg = confirmMsg;
    }

    public String getCountyId()
    {
	return countyId;
    }

    public void setCountyId(String countyId)
    {
	this.countyId = countyId;
    }

    public String getDpsCodeId()
    {
	return dpsCodeId;
    }

    public void setDpsCodeId(String dpsCodeId)
    {
	this.dpsCodeId = dpsCodeId;
    }

    public String getErrMessage()
    {
	return errMessage;
    }

    public void setErrMessage(String errMessage)
    {
	this.errMessage = errMessage;
    }

    public String getFromPage()
    {
	return fromPage;
    }

    public void setFromPage(String fromPage)
    {
	this.fromPage = fromPage;
    }

    public String getSelectedValue()
    {
	return selectedValue;
    }

    public void setSelectedValue(String selectedValue)
    {
	this.selectedValue = selectedValue;
    }

    public static long getSerialversionuid()
    {
	return serialVersionUID;
    }

    /**
     * @return the amendmentDate
     */
    public String getAmendmentDate()
    {
	return amendmentDate;
    }

    /**
     * @param amendmentDate
     *            the amendmentDate to set
     */
    public void setAmendmentDate(String amendmentDate)
    {
	this.amendmentDate = amendmentDate;
    }

    /**
     * @return the attorneyName
     */
    public String getAttorneyName()
    {
	return attorneyName;
    }

    /**
     * @param attorneyName
     *            the attorneyName to set
     */
    public void setAttorneyName(String attorneyName)
    {
	this.attorneyName = attorneyName;
    }

    /**
     * @return the barNumber
     */
    public String getBarNumber()
    {
	return barNumber;
    }

    /**
     * @param barNumber
     *            the barNumber to set
     */
    public void setBarNumber(String barNumber)
    {
	this.barNumber = barNumber;
    }

    /**
     * @return the chainNumber
     */
    public String getChainNumber()
    {
	return chainNumber;
    }

    /**
     * @param chainNumber
     *            the chainNumber to set
     */
    public void setChainNumber(String chainNumber)
    {
	this.chainNumber = chainNumber;
    }

    /**
     * @return the comments
     */
    public String getComments()
    {
	return comments;
    }

    /**
     * @param comments
     *            the comments to set
     */
    public void setComments(String comments)
    {
	this.comments = comments;
    }

    /**
     * @return the courtDate
     */
    public String getCourtDate()
    {
	return courtDate;
    }

    /**
     * @param courtDate
     *            the courtDate to set
     */
    public void setCourtDate(String courtDate)
    {
	this.courtDate = courtDate;
    }

    /**
     * @return the courtDateWithTime
     */
    public Date getCourtDateWithTime()
    {
	return courtDateWithTime;
    }

    /**
     * @param courtDateWithTime
     *            the courtDateWithTime to set
     */
    public void setCourtDateWithTime(Date courtDateWithTime)
    {
	this.courtDateWithTime = courtDateWithTime;
    }

    /**
     * @return the courtId
     */
    public String getCourtId()
    {
	return courtId;
    }

    /**
     * @param courtId
     *            the courtId to set
     */
    public void setCourtId(String courtId)
    {
	this.courtId = courtId;
    }

    /**
     * @return the courtTime
     */
    public String getCourtTime()
    {
	return courtTime;
    }

    /**
     * @param courtTime
     *            the courtTime to set
     */
    public void setCourtTime(String courtTime)
    {
	this.courtTime = courtTime;
    }

    /**
     * @return the filingDate
     */
    public String getFilingDate()
    {
	return filingDate;
    }

    /**
     * @param filingDate
     *            the filingDate to set
     */
    public void setFilingDate(String filingDate)
    {
	this.filingDate = filingDate;
    }

    /**
     * @return the hearingCategory
     */
    public String getHearingCategory()
    {
	return hearingCategory;
    }

    /**
     * @param hearingCategory
     *            the hearingCategory to set
     */
    public void setHearingCategory(String hearingCategory)
    {
	this.hearingCategory = hearingCategory;
    }

    /**
     * @return the hearingDisposition
     */
    public String getHearingDisposition()
    {
	return hearingDisposition;
    }

    /**
     * @param hearingDisposition
     *            the hearingDisposition to set
     */
    public void setHearingDisposition(String hearingDisposition)
    {
	this.hearingDisposition = hearingDisposition;
    }

    /**
     * @return the hearingDispositionDesc
     */
    public String getHearingDispositionDesc()
    {
	return hearingDispositionDesc;
    }

    /**
     * @param hearingDispositionDesc
     *            the hearingDispositionDesc to set
     */
    public void setHearingDispositionDesc(String hearingDispositionDesc)
    {
	this.hearingDispositionDesc = hearingDispositionDesc;
    }

    /**
     * @return the hearingResult
     */
    public String getHearingResult()
    {
	return hearingResult;
    }

    /**
     * @param hearingResult
     *            the hearingResult to set
     */
    public void setHearingResult(String hearingResult)
    {
	this.hearingResult = hearingResult;
    }

    /**
     * @return the hearingResultDesc
     */
    public String getHearingResultDesc()
    {
	return hearingResultDesc;
    }

    /**
     * @param hearingResultDesc
     *            the hearingResultDesc to set
     */
    public void setHearingResultDesc(String hearingResultDesc)
    {
	this.hearingResultDesc = hearingResultDesc;
    }

    /**
     * @return the hearingType
     */
    public String getHearingType()
    {
	return hearingType;
    }

    /**
     * @param hearingType
     *            the hearingType to set
     */
    public void setHearingType(String hearingType)
    {
	this.hearingType = hearingType;
    }

    /**
     * @return the hearingTypeDesc
     */
    public String getHearingTypeDesc()
    {
	return hearingTypeDesc;
    }

    /**
     * @param hearingTypeDesc
     *            the hearingTypeDesc to set
     */
    public void setHearingTypeDesc(String hearingTypeDesc)
    {
	this.hearingTypeDesc = hearingTypeDesc;
    }

    public Collection<JuvenileHearingTypeResponseEvent> getHearingTypes()
    {
	return hearingTypes;
    }

    public void setHearingTypes(Collection<JuvenileHearingTypeResponseEvent> hearingTypes)
    {
	this.hearingTypes = hearingTypes;
    }

    /**
     * @return the issueFlag
     */
    public String getIssueFlag()
    {
	return issueFlag;
    }

    /**
     * @param issueFlag
     *            the issueFlag to set
     */
    public void setIssueFlag(String issueFlag)
    {
	this.issueFlag = issueFlag;
    }

    /**
     * @return the juryFlag
     */
    public String getJuryFlag()
    {
	return juryFlag;
    }

    /**
     * @param juryFlag
     *            the juryFlag to set
     */
    public void setJuryFlag(String juryFlag)
    {
	this.juryFlag = juryFlag;
    }

    /**
     * @return the juvenileFirstName
     */
    public String getJuvenileFirstName()
    {
	return juvenileFirstName;
    }

    /**
     * @param juvenileFirstName
     *            the juvenileFirstName to set
     */
    public void setJuvenileFirstName(String juvenileFirstName)
    {
	this.juvenileFirstName = juvenileFirstName;
    }

    /**
     * @return the juvenileLastName
     */
    public String getJuvenileLastName()
    {
	return juvenileLastName;
    }

    /**
     * @param juvenileLastName
     *            the juvenileLastName to set
     */
    public void setJuvenileLastName(String juvenileLastName)
    {
	this.juvenileLastName = juvenileLastName;
    }

    /**
     * @return the juvenileMiddleName
     */
    public String getJuvenileMiddleName()
    {
	return juvenileMiddleName;
    }

    /**
     * @param juvenileMiddleName
     *            the juvenileMiddleName to set
     */
    public void setJuvenileMiddleName(String juvenileMiddleName)
    {
	this.juvenileMiddleName = juvenileMiddleName;
    }

    /**
     * @return the juvenileNumber
     */
    public String getJuvenileNumber()
    {
	return juvenileNumber;
    }

    /**
     * @param juvenileNumber
     *            the juvenileNumber to set
     */
    public void setJuvenileNumber(String juvenileNumber)
    {
	this.juvenileNumber = juvenileNumber;
    }

    /**
     * @return the lcDate
     */
    public Date getLcDate()
    {
	return lcDate;
    }

    /**
     * @param lcDate
     *            the lcDate to set
     */
    public void setLcDate(Date lcDate)
    {
	this.lcDate = lcDate;
    }

    /**
     * @return the lcTime
     */
    public String getLcTime()
    {
	return lcTime;
    }

    /**
     * @param lcTime
     *            the lcTime to set
     */
    public void setLcTime(String lcTime)
    {
	this.lcTime = lcTime;
    }

    /**
     * @return the lcUser
     */
    public String getLcUser()
    {
	return lcUser;
    }

    /**
     * @param lcUser
     *            the lcUser to set
     */
    public void setLcUser(String lcUser)
    {
	this.lcUser = lcUser;
    }

    /**
     * @return the optionFlag
     */
    public String getOptionFlag()
    {
	return optionFlag;
    }

    /**
     * @param optionFlag
     *            the optionFlag to set
     */
    public void setOptionFlag(String optionFlag)
    {
	this.optionFlag = optionFlag;
    }

    /**
     * @return the petitionAllegation
     */
    public String getPetitionAllegation()
    {
	return petitionAllegation;
    }

    /**
     * @param petitionAllegation
     *            the petitionAllegation to set
     */
    public void setPetitionAllegation(String petitionAllegation)
    {
	this.petitionAllegation = petitionAllegation;
    }

    /**
     * @return the petitionAllegationDesc
     */
    public String getPetitionAllegationDesc()
    {
	return petitionAllegationDesc;
    }

    /**
     * @param petitionAllegationDesc
     *            the petitionAllegationDesc to set
     */
    public void setPetitionAllegationDesc(String petitionAllegationDesc)
    {
	this.petitionAllegationDesc = petitionAllegationDesc;
    }

    /**
     * @return the petitionAmendment
     */
    public String getPetitionAmendment()
    {
	return petitionAmendment;
    }

    /**
     * @param petitionAmendment
     *            the petitionAmendment to set
     */
    public void setPetitionAmendment(String petitionAmendment)
    {
	this.petitionAmendment = petitionAmendment;
    }

    /**
     * @return the petitionNumber
     */
    public String getPetitionNumber()
    {
	return petitionNumber;
    }

    /**
     * @param petitionNumber
     *            the petitionNumber to set
     */
    public void setPetitionNumber(String petitionNumber)
    {
	this.petitionNumber = petitionNumber.toUpperCase();

    }

    /**
     * @return the petitionStatus
     */
    public String getPetitionStatus()
    {
	return petitionStatus;
    }

    /**
     * @param petitionStatus
     *            the petitionStatus to set
     */
    public void setPetitionStatus(String petitionStatus)
    {
	this.petitionStatus = petitionStatus;
    }

    /**
     * @return the prevNotes
     */
    public String getPrevNotes()
    {
	return prevNotes;
    }

    /**
     * @param prevNotes
     *            the prevNotes to set
     */
    public void setPrevNotes(String prevNotes)
    {
	this.prevNotes = prevNotes;
    }

    /**
     * @return the referralNumber
     */
    public String getReferralNumber()
    {
	return referralNumber;
    }

    /**
     * @param referralNumber
     *            the referralNumber to set
     */
    public void setReferralNumber(String referralNumber)
    {
	this.referralNumber = referralNumber;
    }

    /**
     * @return the resetHearingType
     */
    public String getResetHearingType()
    {
	return resetHearingType;
    }

    /**
     * @param resetHearingType
     *            the resetHearingType to set
     */
    public void setResetHearingType(String resetHearingType)
    {
	this.resetHearingType = resetHearingType;
    }

    /**
     * @return the seqNumber
     */
    public String getSeqNumber()
    {
	return seqNumber;
    }

    /**
     * @param seqNumber
     *            the seqNumber to set
     */
    public void setSeqNumber(String seqNumber)
    {
	this.seqNumber = seqNumber;
    }

    /**
     * @return the updateFlag
     */
    public String getUpdateFlag()
    {
	return updateFlag;
    }

    /**
     * @param updateFlag
     *            the updateFlag to set
     */
    public void setUpdateFlag(String updateFlag)
    {
	this.updateFlag = updateFlag;
    }

    /**
     * @return the courtSeqNumber
     */
    public int getCourtSeqNumber()
    {
	return courtSeqNumber;
    }

    /**
     * @param courtSeqNumber
     *            the courtSeqNumber to set
     */
    public void setCourtSeqNumber(int courtSeqNumber)
    {
	this.courtSeqNumber = courtSeqNumber;
    }

    /**
     * @return the courtChainNumber
     */
    public int getCourtChainNumber()
    {
	return courtChainNumber;
    }

    /**
     * @param courtChainNumber
     *            the courtChainNumber to set
     */
    public void setCourtChainNumber(int courtChainNumber)
    {
	this.courtChainNumber = courtChainNumber;
    }

    /**
     * @return the rectype
     */
    public String getRectype()
    {
	return rectype;
    }

    /**
     * @param rectype
     *            the rectype to set
     */
    public void setRectype(String rectype)
    {
	this.rectype = rectype;
    }

    /**
     * @return the displaying
     */
    public String getDisplaying()
    {
	return displaying;
    }

    /**
     * @param displaying
     *            the displaying to set
     */
    public void setDisplaying(String displaying)
    {
	this.displaying = displaying;
    }

    /**
     * @return the courtIdWithSuffix
     */
    public String getCourtIdWithSuffix()
    {
	return courtIdWithSuffix;
    }

    /**
     * @param courtIdWithSuffix
     *            the courtIdWithSuffix to set
     */
    public void setCourtIdWithSuffix(String courtIdWithSuffix)
    {
	this.courtIdWithSuffix = courtIdWithSuffix;
    }

    /**
     * @return
     */
    public JuvenileProfileDetailResponseEvent getProfileDetail()
    {
	return profileDetail;
    }

    /**
     * @param profileDetail
     */
    public void setProfileDetail(JuvenileProfileDetailResponseEvent profileDetail)
    {
	this.profileDetail = profileDetail;
    }

    public String getJuvenileName()
    {
	return juvenileName;
    }

    public void setJuvenileName(String juvenileName)
    {
	this.juvenileName = juvenileName;
    }

    public JuvenileFacilityHeaderResponseEvent getFacilityHeader()
    {
	return facilityHeader;
    }

    public void setFacilityHeader(JuvenileFacilityHeaderResponseEvent facilityHeader)
    {
	this.facilityHeader = facilityHeader;
    }

    public JuvenileMemberForm.MemberContact getMemberContact()
    {
	return memberContact;
    }

    public void setMemberContact(JuvenileMemberForm.MemberContact memberContact)
    {
	this.memberContact = memberContact;
    }

    public Address getMemberAddress()
    {
	return memberAddress;
    }

    public void setMemberAddress(Address memberAddress)
    {
	this.memberAddress = memberAddress;
    }

    public FamilyMemberDetailResponseEvent getMemberDetail()
    {
	return memberDetail;
    }

    public void setMemberDetail(FamilyMemberDetailResponseEvent memberDetail)
    {
	this.memberDetail = memberDetail;
    }

    public Collection<JuvenileDetentionVisitResponseEvent> getDetVisits()
    {
	return detVisits;
    }

    public void setDetVisits(Collection<JuvenileDetentionVisitResponseEvent> detVisits)
    {
	this.detVisits = detVisits;
    }

    public String getPetitionType()
    {
	return petitionType;
    }

    public void setPetitionType(String petitionType)
    {
	this.petitionType = petitionType;
    }

    public Collection<CodeResponseEvent> getPetitionStatuses()
    {
	return petitionStatuses;
    }

    public void setPetitionStatuses(Collection<CodeResponseEvent> petitionStatuses)
    {
	this.petitionStatuses = petitionStatuses;
    }

    public Collection<CodeResponseEvent> getPetitionTypes()
    {
	return petitionTypes;
    }

    public void setPetitionTypes(Collection<CodeResponseEvent> petitionTypes)
    {
	this.petitionTypes = petitionTypes;
    }

    public Collection<CodeResponseEvent> getPetitionAmendments()
    {
	return petitionAmendments;
    }

    public void setPetitionAmendments(Collection<CodeResponseEvent> petitionAmendments)
    {
	this.petitionAmendments = petitionAmendments;
    }

    public Collection<CodeResponseEvent> getSubpoenasToBePrinted()
    {
	return subpoenasToBePrinted;
    }

    public void setSubpoenasToBePrinted(Collection<CodeResponseEvent> subpoenasToBePrinted)
    {
	this.subpoenasToBePrinted = subpoenasToBePrinted;
    }

    public Collection<GuardianBean> getGuardians()
    {
	return guardians;
    }

    public void setGuardians(Collection<GuardianBean> guardians)
    {
	this.guardians = guardians;
    }

    public String getPreparationDate()
    {
	return preparationDate;
    }

    public void setPreparationDate(String preparationDate)
    {
	this.preparationDate = preparationDate;
    }

    public String getCert()
    {
	return cert;
    }

    public void setCert(String cert)
    {
	this.cert = cert;
    }

    public String getPlaintiff()
    {
	return plaintiff;
    }

    public void setPlaintiff(String plaintiff)
    {
	this.plaintiff = plaintiff;
    }

    public String getRelationship()
    {
	return relationship;
    }

    public void setRelationship(String relationship)
    {
	this.relationship = relationship;
    }

    public String getAttorneyConnection()
    {
	return attorneyConnection;
    }

    public void setAttorneyConnection(String attorneyConnection)
    {
	this.attorneyConnection = attorneyConnection;
    }

    public String getValidateMsg()
    {
	return validateMsg;
    }

    public void setValidateMsg(String validateMsg)
    {
	this.validateMsg = validateMsg;
    }

    public String getAlphaCodeId()
    {
	return alphaCodeId;
    }

    public void setAlphaCodeId(String alphaCodeId)
    {
	this.alphaCodeId = alphaCodeId;
    }

    public List getOffenseResultList()
    {
	return offenseResultList;
    }

    public void setOffenseResultList(List offenseResultList)
    {
	this.offenseResultList = offenseResultList;
    }

    public String getShortDesc()
    {
	return shortDesc;
    }

    public void setShortDesc(String shortDesc)
    {
	this.shortDesc = shortDesc;
    }

    public Collection<JuvenileCasefileOffenseCodeResponseEvent> getCodetableDataList()
    {
	return codetableDataList;
    }

    public void setCodetableDataList(Collection<JuvenileCasefileOffenseCodeResponseEvent> codetableDataList)
    {
	this.codetableDataList = codetableDataList;
    }

    public Name getFather()
    {
	return father;
    }

    public void setFather(Name father)
    {
	this.father = father;
    }

    public Name getMother()
    {
	return mother;
    }

    public void setMother(Name mother)
    {
	this.mother = mother;
    }

    public String getOffenseDateStr()
    {
	return offenseDateStr;
    }

    public void setOffenseDateStr(String offenseDateStr)
    {
	this.offenseDateStr = offenseDateStr;
    }

    public Address getFatherAddress()
    {
	return fatherAddress;
    }

    public void setFatherAddress(Address fatherAddress)
    {
	this.fatherAddress = fatherAddress;
    }

    public Address getMotherAddress()
    {
	return motherAddress;
    }

    public void setMotherAddress(Address motherAddress)
    {
	this.motherAddress = motherAddress;
    }

    public JuvenileMemberForm.MemberContact getMotherContact()
    {
	return motherContact;
    }

    public void setMotherContact(JuvenileMemberForm.MemberContact motherContact)
    {
	this.motherContact = motherContact;
    }

    public JuvenileMemberForm.MemberContact getFatherContact()
    {
	return fatherContact;
    }

    public void setFatherContact(JuvenileMemberForm.MemberContact fatherContact)
    {
	this.fatherContact = fatherContact;
    }

    public String getCursorPosition()
    {
	return cursorPosition;
    }

    public void setCursorPosition(String cursorPosition)
    {
	this.cursorPosition = cursorPosition;
    }

    public String getReferralDate()
    {
	return referralDate;
    }

    public void setReferralDate(String referralDate)
    {
	this.referralDate = referralDate;
    }

    public String getAction()
    {
	return action;
    }

    public void setAction(String action)
    {
	this.action = action;
    }

    public String getDetainedFacility()
    {
	return detainedFacility;
    }

    public void setDetainedFacility(String detainedFacility)
    {
	this.detainedFacility = detainedFacility;
    }

    public String getDetainedFacilityDesc()
    {
	return detainedFacilityDesc;
    }

    public void setDetainedFacilityDesc(String detainedFacilityDesc)
    {
	this.detainedFacilityDesc = detainedFacilityDesc;
    }

    public String getFacilityStatusDesc()
    {
	return facilityStatusDesc;
    }

    public void setFacilityStatusDesc(String facilityStatusDesc)
    {
	this.facilityStatusDesc = facilityStatusDesc;
    }

    public String getFacilityStatus()
    {
	return facilityStatus;
    }

    public void setFacilityStatus(String facilityStatus)
    {
	this.facilityStatus = facilityStatus;
    }

    public Address getCareGiverAddress()
    {
	return careGiverAddress;
    }

    public void setCareGiverAddress(Address careGiverAddress)
    {
	this.careGiverAddress = careGiverAddress;
    }

    public Address getGuardianAddress()
    {
	return guardianAddress;
    }

    public void setGuardianAddress(Address guardianAddress)
    {
	this.guardianAddress = guardianAddress;
    }

    public Name getCareGiver()
    {
	return careGiver;
    }

    public void setCareGiver(Name careGiver)
    {
	this.careGiver = careGiver;
    }

    public Name getGuardian()
    {
	return guardian;
    }

    public void setGuardian(Name guardian)
    {
	this.guardian = guardian;
    }

    public JuvenileMemberForm.MemberContact getGuardianContact()
    {
	return guardianContact;
    }

    public void setGuardianContact(JuvenileMemberForm.MemberContact guardianContact)
    {
	this.guardianContact = guardianContact;
    }

    public JuvenileMemberForm.MemberContact getCareGiverContact()
    {
	return careGiverContact;
    }

    public void setCareGiverContact(JuvenileMemberForm.MemberContact careGiverContact)
    {
	this.careGiverContact = careGiverContact;
    }

    public String getFinalDispEntered()
    {
	return finalDispEntered;
    }

    public void setFinalDispEntered(String finalDispEntered)
    {
	this.finalDispEntered = finalDispEntered;
    }

    public String getPetitionAllegationSev()
    {
	return petitionAllegationSev;
    }

    public void setPetitionAllegationSev(String petitionAllegationSev)
    {
	this.petitionAllegationSev = petitionAllegationSev;
    }

    public Collection<JSONObject> getHolidaysList()
    {
	return holidaysList;
    }

    public void setHolidaysList(Collection<JSONObject> holidaysList)
    {
	this.holidaysList = holidaysList;
    }

    public Collection<CodeResponseEvent> getTypeCase()
    {
	return typeCase;
    }

    public void setTypeCase(Collection<CodeResponseEvent> typeCase)
    {
	this.typeCase = typeCase;
    }

    public String getTypeCaseStr()
    {
	return typeCaseStr;
    }

    public void setTypeCaseStr(String typeCaseStr)
    {
	this.typeCaseStr = typeCaseStr;
    }

    public String getRespondentName()
    {
	return respondentName;
    }

    public void setRespondentName(String respondentName)
    {
	this.respondentName = respondentName;
    }

    public String getSettingReason()
    {
	return settingReason;
    }

    public void setSettingReason(String settingReason)
    {
	this.settingReason = settingReason;
    }

    public String getActionError()
    {
	return actionError;
    }

    public void setActionError(String actionError)
    {
	this.actionError = actionError;
    }

    public JuvenileDispositionCodeResponseEvent getCourtDisposition()
    {
	return courtDisposition;
    }

    public void setCourtDisposition(JuvenileDispositionCodeResponseEvent courtDisposition)
    {
	this.courtDisposition = courtDisposition;
    }

    public Collection<JSONObject> getHearingTypesList()
    {
	return hearingTypesList;
    }

    public void setHearingTypesList(Collection<JSONObject> hearingTypesList)
    {
	this.hearingTypesList = hearingTypesList;
    }

    public int getJjsCLAncillaryId()
    {
	return jjsCLAncillaryId;
    }

    public void setJjsCLAncillaryId(int jjsCLAncillaryId)
    {
	this.jjsCLAncillaryId = jjsCLAncillaryId;
    }

    public String getDcktEvtId()
    {
	return dcktEvtId;
    }

    public void setDcktEvtId(String dcktEvtId)
    {
	this.dcktEvtId = dcktEvtId;
    }

    public String getDocketEventsRec()
    {
	return docketEventsRec;
    }

    public void setDocketEventsRec(String docketEventsRec)
    {
	this.docketEventsRec = docketEventsRec;
    }

    /**
     * @return the attorneyNameHistory
     */
    public String getAttorneyNameHistory()
    {
	return attorneyNameHistory;
    }

    /**
     * @param attorneyNameHistory
     *            the attorneyNameHistory to set
     */
    public void setAttorneyNameHistory(String attorneyNameHistory)
    {
	this.attorneyNameHistory = attorneyNameHistory;
    }

    public Collection<AttorneyNameAndAddressResponseEvent> getAttorneyDataList()
    {
	return attorneyDataList;
    }

    public void setAttorneyDataList(Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList)
    {
	this.attorneyDataList = attorneyDataList;
    }

    public String getHearingCorrectionFlag()
    {
	return hearingCorrectionFlag;
    }

    public void setHearingCorrectionFlag(String hearingCorrectionFlag)
    {
	this.hearingCorrectionFlag = hearingCorrectionFlag;
    }

    /**
     * @return the selectedDocket
     */
    public DocketEventResponseEvent getSelectedDocket()
    {
	return selectedDocket;
    }

    /**
     * @param selectedDocket
     *            the selectedDocket to set
     */
    public void setSelectedDocket(DocketEventResponseEvent selectedDocket)
    {
	this.selectedDocket = selectedDocket;
    }

    public String[] getSelectedSubpoenasToBePrinted()
    {
	return selectedSubpoenasToBePrinted;
    }

    public void setSelectedSubpoenasToBePrinted(String[] selectedSubpoenasToBePrinted)
    {
	this.selectedSubpoenasToBePrinted = selectedSubpoenasToBePrinted;
    }

    public String getTrackingNum()
    {
	return trackingNum;
    }

    public void setTrackingNum(String trackingNum)
    {
	this.trackingNum = trackingNum;
    }

    public String getJuvAge()
    {
	return juvAge;
    }

    public void setJuvAge(String juvAge)
    {
	this.juvAge = juvAge;
    }

    public String getReportsToBePrinted()
    {
	return reportsToBePrinted;
    }

    public void setReportsToBePrinted(String reportsToBePrinted)
    {
	this.reportsToBePrinted = reportsToBePrinted;
    }

    public boolean isSubpoenaPrinted()
    {
	return subpoenaPrinted;
    }

    public void setSubpoenaPrinted(boolean subpoenaPrinted)
    {
	this.subpoenaPrinted = subpoenaPrinted;
    }

    public boolean getSubpoenaPrinted()
    {
	return subpoenaPrinted;
    }

    public int getSearchResultSize()
    {
	return searchResultSize;
    }

    public void setSearchResultSize(int searchResultSize)
    {
	this.searchResultSize = searchResultSize;
    }

    public Collection getJuvenileProfiles()
    {
	return juvenileProfiles;
    }

    public void setJuvenileProfiles(Collection juvenileProfiles)
    {
	this.juvenileProfiles = juvenileProfiles;
    }

    public String getSexId()
    {
	return sexId;
    }

    public void setSexId(String sexId)
    {
	this.sexId = sexId;
    }

    public String getJuvenileDOB()
    {
	return juvenileDOB;
    }

    public void setJuvenileDOB(String juvenileDOB)
    {
	this.juvenileDOB = juvenileDOB;
    }

    public String getDateOfBirth()
    {
	return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth)
    {
	this.dateOfBirth = dateOfBirth;
    }

    public int getaSearchResultSize()
    {
	return aSearchResultSize;
    }

    public void setaSearchResultSize(int aSearchResultSize)
    {
	this.aSearchResultSize = aSearchResultSize;
    }

    public String getRaceId()
    {
	return raceId;
    }

    public void setRaceId(String raceId)
    {
	this.raceId = raceId;
    }

    /**
     * Returns the collection code response events for the race types
     * 
     * @return The races.
     */
    public Collection getRaces()
    {
	return CodeHelper.getRaces();
    } //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getRaces

    /**
     * Returns the collection code response events for the sex types
     * 
     * @return The sexes.
     */
    public Collection getSexes()
    {
	return CodeHelper.getJJSSexCodes();
    } //end of ui.juvenilecase.form.JuvenileProfileSearchForm.getSexes

    public Collection<NumberInquiryResponse> getJuvCourtHearingList()
    {
	return juvCourtHearingList;
    }

    public void setJuvCourtHearingList(Collection<NumberInquiryResponse> juvCourtHearingList)
    {
	this.juvCourtHearingList = juvCourtHearingList;
    }

    public Collection<NumberInquiryResponse> getJuvCourtHearingHist()
    {
	return juvCourtHearingHist;
    }

    public void setJuvCourtHearingHist(Collection<NumberInquiryResponse> juvCourtHearingHist)
    {
	this.juvCourtHearingHist = juvCourtHearingHist;
    }

    public String getSearchType()
    {
	return searchType;
    }

    public void setSearchType(String searchType)
    {
	this.searchType = searchType;
    }

    public String getRestrictedAccessFeature()
    {
	return restrictedAccessFeature;
    }

    public void setRestrictedAccessFeature(String restrictedAccessFeature)
    {
	this.restrictedAccessFeature = restrictedAccessFeature;
    }

    public String getResultsPage()
    {
	return resultsPage;
    }

    public void setResultsPage(String resultsPage)
    {
	this.resultsPage = resultsPage;
    }

    public String getQueryString()
    {
	return queryString;
    }

    public void setQueryString(String queryString)
    {
	this.queryString = queryString;
    }

    /**
     * @return the hispanic
     */
    public String getHispanic()
    {
	return hispanic;
    }

    /**
     * @param hispanic
     *            the hispanic to set
     */
    public void setHispanic(String hispanic)
    {
	this.hispanic = hispanic;
    }

    /**
     * @return the ssn
     */
    public String getSsn()
    {
	return ssn;
    }

    /**
     * @param ssn
     *            the ssn to set
     */
    public void setSsn(String ssn)
    {
	this.ssn = ssn;
    }

    /**
     * @return the multiracial
     */
    public String getMultiracial()
    {
	return multiracial;
    }

    /**
     * @param multiracial
     *            the multiracial to set
     */
    public void setMultiracial(String multiracial)
    {
	this.multiracial = multiracial;
    }

    /**
     * @return the ethnicity
     */
    public String getEthnicity()
    {
	return ethnicity;
    }

    /**
     * @param ethnicity
     *            the ethnicity to set
     */
    public void setEthnicity(String ethnicity)
    {
	this.ethnicity = ethnicity;
    }

    /**
     * @return the school
     */
    public JuvenileSchoolHistoryResponseEvent getSchool()
    {
	return school;
    }

    /**
     * @param school
     *            the school to set
     */
    public void setSchool(JuvenileSchoolHistoryResponseEvent school)
    {
	this.school = school;
    }

    public PetitionResponseEvent getPetitionResp()
    {
	return petitionResp;
    }

    public void setPetitionResp(PetitionResponseEvent petitionResp)
    {
	this.petitionResp = petitionResp;
    }

    public String getPetitionSeqNum()
    {
	return petitionSeqNum;
    }

    public void setPetitionSeqNum(String petitionSeqNum)
    {
	this.petitionSeqNum = petitionSeqNum;
    }

    /**
     * @return the detVisitBanned
     */
    public boolean isDetVisitBanned()
    {
	return detVisitBanned;
    }

    /**
     * @param detVisitBanned
     *            the detVisitBanned to set
     */
    public void setDetVisitBanned(boolean detVisitBanned)
    {
	this.detVisitBanned = detVisitBanned;
    }

    public boolean isHasPendingCasefiles()
    {
	return hasPendingCasefiles;
    }

    public void setHasPendingCasefiles(boolean hasPendingCasefiles)
    {
	this.hasPendingCasefiles = hasPendingCasefiles;
    }

    public boolean isHasActiveCasefiles()
    {
	return hasActiveCasefiles;
    }

    public void setHasActiveCasefiles(boolean hasActiveCasefiles)
    {
	this.hasActiveCasefiles = hasActiveCasefiles;
    }

    public boolean isHasCasefiles()
    {
	return hasCasefiles;
    }

    public void setHasCasefiles(boolean hasCasefiles)
    {
	this.hasCasefiles = hasCasefiles;
    }

    public boolean isHasPostAdjCatCasefile()
    {
	return hasPostAdjCatCasefile;
    }

    public void setHasPostAdjCatCasefile(boolean hasPostAdjCatCasefile)
    {
	this.hasPostAdjCatCasefile = hasPostAdjCatCasefile;
    }

    public String getJpoOfRecord()
    {
	return jpoOfRecord;
    }

    public void setJpoOfRecord(String jpoOfRecord)
    {
	this.jpoOfRecord = jpoOfRecord;
    }

    public String getJpoOfRecID()
    {
	return jpoOfRecID;
    }

    public void setJpoOfRecID(String jpoOfRecID)
    {
	this.jpoOfRecID = jpoOfRecID;
    }

    public String getCurrAction()
    {
	return currAction;
    }

    public void setCurrAction(String currAction)
    {
	this.currAction = currAction;
    }

    public String getPrevAction()
    {
	return prevAction;
    }

    public void setPrevAction(String prevAction)
    {
	this.prevAction = prevAction;
    }

    public String getSelectedSubpoenas()
    {
	return selectedSubpoenas;
    }

    public void setSelectedSubpoenas(String selectedSubpoenas)
    {
	this.selectedSubpoenas = selectedSubpoenas;
    }

    public String getVerificationStatus()
    {
	return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus)
    {
	this.verificationStatus = verificationStatus;
    }

    public String getProfileStatus()
    {
	return profileStatus;
    }

    public void setProfileStatus(String profileStatus)
    {
	this.profileStatus = profileStatus;
    }

    public String getSupervisionCategory()
    {
	return supervisionCategory;
    }

    public void setSupervisionCategory(String supervisionCategory)
    {
	this.supervisionCategory = supervisionCategory;
    }

    public String getSupervisionType()
    {
	return supervisionType;
    }

    public void setSupervisionType(String supervisionType)
    {
	this.supervisionType = supervisionType;
    }

    public Collection<JuvenileProfileReferralListResponseEvent> getReferralList()
    {
	return referralList;
    }

    public void setReferralList(Collection<JuvenileProfileReferralListResponseEvent> referralList)
    {
	this.referralList = referralList;
    }

    public String getSupervisionCategoryId()
    {
	return supervisionCategoryId;
    }

    public void setSupervisionCategoryId(String supervisionCategoryId)
    {
	this.supervisionCategoryId = supervisionCategoryId;
    }

    public String getSupervisionTypeId()
    {
	return supervisionTypeId;
    }

    public void setSupervisionTypeId(String supervisionTypeId)
    {
	this.supervisionTypeId = supervisionTypeId;
    }

    /**
     * @return the referralRec
     */
    public String getReferralRec()
    {
	return referralRec;
    }

    /**
     * @param referralRec
     *            the referralRec to set
     */
    public void setReferralRec(String referralRec)
    {
	this.referralRec = referralRec;
    }

    public Collection<JuvenileDispositionCode> getCourtDecisions()
    {
	return courtDecisions;
    }

    public void setCourtDecisions(Collection<JuvenileDispositionCode> courtDecisions)
    {
	this.courtDecisions = courtDecisions;
    }

    public String getCourtResult()
    {
	return courtResult;
    }

    public void setCourtResult(String courtResult)
    {
	this.courtResult = courtResult;
    }

    /**
     * @return the ancillaryDockets
     */
    public Collection<DocketEventResponseEvent> getAncillaryDockets()
    {
	return ancillaryDockets;
    }

    /**
     * @param ancillaryDockets
     *            the ancillaryDockets to set
     */
    public void setAncillaryDockets(Collection<DocketEventResponseEvent> ancillaryDockets)
    {
	this.ancillaryDockets = ancillaryDockets;
    }

    /**
     * @return the delinquencyDockets
     */
    public Collection<DocketEventResponseEvent> getDelinquencyDockets()
    {
	return delinquencyDockets;
    }

    /**
     * @param delinquencyDockets
     *            the delinquencyDockets to set
     */
    public void setDelinquencyDockets(Collection<DocketEventResponseEvent> delinquencyDockets)
    {
	this.delinquencyDockets = delinquencyDockets;
    }

    /**
     * @return the allDockets
     */
    public Collection<DocketEventResponseEvent> getAllDockets()
    {
	return allDockets;
    }

    /**
     * @param allDockets
     *            the allDockets to set
     */
    public void setAllDockets(Collection<DocketEventResponseEvent> allDockets)
    {
	this.allDockets = allDockets;
    }

    public Collection<JuvenileDispositionCodeResponseEvent> getCourtDecisionsResponses()
    {
	return courtDecisionsResponses;
    }

    public void setCourtDecisionsResponses(Collection<JuvenileDispositionCodeResponseEvent> courtDecisionsResponses)
    {
	this.courtDecisionsResponses = courtDecisionsResponses;
    }

    public Map<String, DocketEventResponseEvent> getDktSearchResultsMap()
    {
	return dktSearchResultsMap;
    }

    public void setDktSearchResultsMap(Map<String, DocketEventResponseEvent> dktSearchResultsMap)
    {
	this.dktSearchResultsMap = dktSearchResultsMap;
    }

    public String getTransferTo()
    {
	return transferTo;
    }

    public void setTransferTo(String transferTo)
    {
	this.transferTo = transferTo;
    }

    public String getPagerOffset()
    {
	return pagerOffset;
    }

    public void setPagerOffset(String pagerOffset)
    {
	this.pagerOffset = pagerOffset;
    }

    /**
     * @return the barNumberValidated
     */
    public boolean isBarNumberValidated()
    {
	return barNumberValidated;
    }

    /**
     * @param barNumberValidated
     *            the barNumberValidated to set
     */
    public void setBarNumberValidated(boolean barNumberValidated)
    {
	this.barNumberValidated = barNumberValidated;
    }

    /**
     * @return the dktSearchResults
     */
    public Collection<DocketEventResponseEvent> getDktSearchResults()
    {
	return dktSearchResults;
    }

    /**
     * @param dktSearchResults
     *            the dktSearchResults to set
     */
    public void setDktSearchResults(Collection<DocketEventResponseEvent> dktSearchResults)
    {
	this.dktSearchResults = dktSearchResults;
    }

    /**
     * @return the ancillaryHearingTypes
     */
    public Collection<JuvenileHearingTypeResponseEvent> getAncillaryHearingTypes()
    {
	return ancillaryHearingTypes;
    }

    /**
     * @param ancillaryHearingTypes
     *            the ancillaryHearingTypes to set
     */
    public void setAncillaryHearingTypes(Collection<JuvenileHearingTypeResponseEvent> ancillaryHearingTypes)
    {
	this.ancillaryHearingTypes = ancillaryHearingTypes;
    }

    /**
     * @return the courtHearingTypes
     */
    public Collection<JuvenileHearingTypeResponseEvent> getCourtHearingTypes()
    {
	return courtHearingTypes;
    }

    /**
     * @param courtHearingTypes
     *            the courtHearingTypes to set
     */
    public void setCourtHearingTypes(Collection<JuvenileHearingTypeResponseEvent> courtHearingTypes)
    {
	this.courtHearingTypes = courtHearingTypes;
    }

    /**
     * @return the updatedDocketList
     */
    public Collection<DocketEventResponseEvent> getUpdatedDocketList()
    {
	return updatedDocketList;
    }

    /**
     * @param updatedDocketList
     *            the updatedDocketList to set
     */
    public void setUpdatedDocketList(Collection<DocketEventResponseEvent> updatedDocketList)
    {
	this.updatedDocketList.addAll(updatedDocketList);
    }

    /**
     * @return the updatedDocketEventIds
     */
    public String[] getUpdatedDocketEventIds()
    {
	return updatedDocketEventIds;
    }

    /**
     * @param updatedDocketEventIds
     *            the updatedDocketEventIds to set
     */
    public void setUpdatedDocketEventIds(String[] updatedDocketEventIds)
    {
	this.updatedDocketEventIds = updatedDocketEventIds;
    }

    /**
     * @return the originalDktSearchResultsMap
     */
    public Map<String, DocketEventResponseEvent> getOriginalDktSearchResultsMap()
    {
	return originalDktSearchResultsMap;
    }

    /**
     * @param originalDktSearchResultsMap
     *            the originalDktSearchResultsMap to set
     */
    public void setOriginalDktSearchResultsMap(Map<String, DocketEventResponseEvent> originalDktSearchResultsMap)
    {
	this.originalDktSearchResultsMap = originalDktSearchResultsMap;
    }

    /**
     * @return the deleteFlag
     */
    public String getDeleteFlag()
    {
	return deleteFlag;
    }

    /**
     * @param deleteFlag
     *            the deleteFlag to set
     */
    public void setDeleteFlag(String deleteFlag)
    {
	this.deleteFlag = deleteFlag;
    }

    /**
     * @return the deleteDocketEventId
     */
    public String getDeleteDocketEventId()
    {
	return deleteDocketEventId;
    }

    /**
     * @param deleteDocketEventId
     *            the deleteDocketEventId to set
     */
    public void setDeleteDocketEventId(String deleteDocketEventId)
    {
	this.deleteDocketEventId = deleteDocketEventId;
    }

    /**
     * @return the deleteSettingDate
     */
    public String getDeleteSettingDate()
    {
	return deleteSettingDate;
    }

    /**
     * @param deleteSettingDate
     *            the deleteSettingDate to set
     */
    public void setDeleteSettingDate(String deleteSettingDate)
    {
	this.deleteSettingDate = deleteSettingDate;
    }

    /**
     * @return the deleteDocketEventType
     */
    public String getDeleteDocketEventType()
    {
	return deleteDocketEventType;
    }

    /**
     * @param deleteDocketEventType
     *            the deleteDocketEventType to set
     */
    public void setDeleteDocketEventType(String deleteDocketEventType)
    {
	this.deleteDocketEventType = deleteDocketEventType;
    }

    /**
     * @return the currentSettingDocketEventId
     */
    public String getCurrentSettingDocketEventId()
    {
	return currentSettingDocketEventId;
    }

    /**
     * @param currentSettingDocketEventId
     *            the currentSettingDocketEventId to set
     */
    public void setCurrentSettingDocketEventId(String currentSettingDocketEventId)
    {
	this.currentSettingDocketEventId = currentSettingDocketEventId;
    }

    /**
     * @return the deletedDocket
     */
    public DocketEventResponseEvent getDeletedDocket()
    {
	return deletedDocket;
    }

    /**
     * @param deletedDocket
     *            the deletedDocket to set
     */
    public void setDeletedDocket(DocketEventResponseEvent deletedDocket)
    {
	this.deletedDocket = deletedDocket;
    }

    /**
     * @return the isLastSetting
     */
    public String getIsLastSetting()
    {
	return isLastSetting;
    }

    /**
     * @param isLastSetting
     *            the isLastSetting to set
     */
    public void setIsLastSetting(String isLastSetting)
    {
	this.isLastSetting = isLastSetting;
    }

    /**
     * @return the disableResetDate
     */
    public boolean isDisableResetDate()
    {
	return disableResetDate;
    }

    /**
     * @param disableResetDate
     *            the disableResetDate to set
     */
    public void setDisableResetDate(boolean disableResetDate)
    {
	this.disableResetDate = disableResetDate;
    }

    /**
     * @return the detentionHearingResults
     */
    public List<JuvenileCourtDecisionCodeResponseEvent> getDetentionHearingResults()
    {
	return detentionHearingResults;
    }

    /**
     * @param detentionHearingResults
     *            the detentionHearingResults to set
     */
    public void setDetentionHearingResults(List<JuvenileCourtDecisionCodeResponseEvent> detentionHearingResults)
    {
	this.detentionHearingResults = detentionHearingResults;
    }

    /**
     * @return the courtDecisionsList
     */
    public Collection<JSONObject> getCourtDecisionsList()
    {
	return courtDecisionsList;
    }

    /**
     * @param courtDecisionsList the courtDecisionsList to set
     */
    public void setCourtDecisionsList(Collection<JSONObject> courtDecisionsList)
    {
	this.courtDecisionsList = courtDecisionsList;
    }

    /**
     * @return the currentSettingChainNumber
     */
    public String getCurrentSettingChainNumber()
    {
	return currentSettingChainNumber;
    }

    /**
     * @param currentSettingChainNumber the currentSettingChainNumber to set
     */
    public void setCurrentSettingChainNumber(String currentSettingChainNumber)
    {
	this.currentSettingChainNumber = currentSettingChainNumber;
    }

    public String getCurrentSettingDocketEventIdKey()
    {
	return currentSettingDocketEventIdKey;
    }

    public void setCurrentSettingDocketEventIdKey(String currentSettingDocketEventIdKey)
    {
	this.currentSettingDocketEventIdKey = currentSettingDocketEventIdKey;
    }

    public String getDcktEvtIdKey()
    {
	return dcktEvtIdKey;
    }

    public void setDcktEvtIdKey(String dcktEvtIdKey)
    {
	this.dcktEvtIdKey = dcktEvtIdKey;
    }

    public String getDeleteDocketEventIdKey()
    {
	return deleteDocketEventIdKey;
    }

    public void setDeleteDocketEventIdKey(String deleteDocketEventIdKey)
    {
	this.deleteDocketEventIdKey = deleteDocketEventIdKey;
    }

    public String getIsOnlySetting()
    {
	return isOnlySetting;
    }

    public void setIsOnlySetting(String isOnlySetting)
    {
	this.isOnlySetting = isOnlySetting;
    }

    /**
     * @return the finalReleaseDate
     */
    public String getFinalReleaseDate()
    {
	return finalReleaseDate;
    }

    /**
     * @param finalReleaseDate the finalReleaseDate to set
     */
    public void setFinalReleaseDate(String finalReleaseDate)
    {
	this.finalReleaseDate = finalReleaseDate;
    }

    public String getDualStatus()
    {
	return dualStatus;
    }

    public void setDualStatus(String dualStatus)
    {
	this.dualStatus = dualStatus;
    }

    public String getGalName()
    {
        return galName;
    }

    public void setGalName(String galName)
    {
        this.galName = galName;
    }

    public String getGalBarNumber()
    {
        return galBarNumber;
    }

    public void setGalBarNumber(String galBarNumber)
    {
        this.galBarNumber = galBarNumber;
    }
    public String getPetitionCorrectionFlag()
    {
        return petitionCorrectionFlag;
    }

    public void setPetitionCorrectionFlag(String petitionCorrectionFlag)
    {
        this.petitionCorrectionFlag = petitionCorrectionFlag;
    }

    public boolean isAdlitemUpdateFlag()
    {
        return adlitemUpdateFlag;
    }

    public void setAdlitemUpdateFlag(boolean adlitemUpdateFlag)
    {
        this.adlitemUpdateFlag = adlitemUpdateFlag;
    }
    public String getActionMessage()
    {
        return actionMessage;
    }

    public void setActionMessage(String actionMessage)
    {
        this.actionMessage = actionMessage;
    }

    public String getDaLogNum()
    {
	return daLogNum;
    }

    public void setDaLogNum(String dalLogNum)
    {
	this.daLogNum = dalLogNum;
    }

    public JJSOffense getOffenseDetail()
    {
	return offenseDetail;
    }

    public void setOffenseDetail(JJSOffense offenseDetail)
    {
	this.offenseDetail = offenseDetail;
    }

    public String getOffenseCodeIds()
    {
	return offenseCodeIds;
    }

    public void setOffenseCodeIds(String offenseCodeIds)
    {
	this.offenseCodeIds = offenseCodeIds;
    }

    public String getOffenseCategories()
    {
	return offenseCategories;
    }

    public void setOffenseCategories(String offenseCategories)
    {
	this.offenseCategories = offenseCategories;
    }

    public String getOffenseDescriptions()
    {
	return offenseDescriptions;
    }

    public void setOffenseDescriptions(String offenseDescriptions)
    {
	this.offenseDescriptions = offenseDescriptions;
    }

    public List<JJSOffenseResponseEvent> getOffenseDetails()
    {
	return offenseDetails;
    }

    public void setOffenseDetails(List<JJSOffenseResponseEvent> offenseDetails)
    {
	this.offenseDetails = offenseDetails;
    }

    public Date getDaDateOut()
    {
	return daDateOut;
    }

    public void setDaDateOut(Date daDateOut)
    {
	this.daDateOut = daDateOut;
    }

    public String getLogStatus()
    {
	return logStatus;
    }

    public void setLogStatus(String logStatus)
    {
	this.logStatus = logStatus;
    }

    public boolean isInSpecialtyCourt()
    {
        return inSpecialtyCourt;
    }

    public void setInSpecialtyCourt(boolean inSpecialtyCourt)
    {
        this.inSpecialtyCourt = inSpecialtyCourt;
    }

    public String getSpecialtyCourtDescription()
    {
        return specialtyCourtDescription;
    }

    public void setSpecialtyCourtDescription(String specialtyCourtDescription)
    {
        this.specialtyCourtDescription = specialtyCourtDescription;
    }

    public String getMasterStatus()
    {
	return masterStatus;
    }

    public void setMasterStatus(String masterStatus)
    {
	this.masterStatus = masterStatus;
    }

    public String getMasterStatusId()
    {
	return masterStatusId;
    }

    public void setMasterStatusId(String masterStatusId)
    {
	this.masterStatusId = masterStatusId;
    }
    public String getSID()
    {
        return SID;
    }

    public void setSID(String sID)
    {
        SID = sID;
    }
    public String getJotPetitionnum()
    {
        return jotPetitionnum;
    }

    public void setJotPetitionnum(String jotPetitionnum)
    {
        this.jotPetitionnum = jotPetitionnum;
    }
    public String getLastprobendDate()
    {
        return lastprobendDate;
    }

    public void setLastprobendDate(String lastprobendDate)
    {
        this.lastprobendDate = lastprobendDate;
    }
    
    public boolean getIsCourtActionReady()
    {
        return isCourtActionReady;
    }

    public void setIsCourtActionReady(boolean isCourtActionReady)
    {
        this.isCourtActionReady = isCourtActionReady;
    }
    public String getNcicCode()
    {
        return ncicCode;
    }

    public void setNcicCode(String ncicCode)
    {
        this.ncicCode = ncicCode;
    }
    public String getAssignedAttorney()
    {
        return assignedAttorney;
    }

    public void setAssignedAttorney(String assignedAttorney)
    {
        this.assignedAttorney = assignedAttorney;
    }

    public String getIsFatherIncarcerated()
    {
        return isFatherIncarcerated;
    }

    public void setIsFatherIncarcerated(String isFatherIncarcerated)
    {
        this.isFatherIncarcerated = isFatherIncarcerated;
    }

    public String getIsMotherIncarcerated()
    {
        return isMotherIncarcerated;
    }

    public void setIsMotherIncarcerated(String isMotherIncarcerated)
    {
        this.isMotherIncarcerated = isMotherIncarcerated;
    }

    public String getIsFatherDeceased()
    {
        return isFatherDeceased;
    }

    public void setIsFatherDeceased(String isFatherDeceased)
    {
        this.isFatherDeceased = isFatherDeceased;
    }

    public String getIsMotherDeceased()
    {
        return isMotherDeceased;
    }

    public void setIsMotherDeceased(String isMotherDeceased)
    {
        this.isMotherDeceased = isMotherDeceased;
    }

    public JJSLastAttorney getJjslastAttorney()
    {
	return jjslastAttorney;
    }

    public void setJjslastAttorney(JJSLastAttorney jjslastAttorney)
    {
	this.jjslastAttorney = jjslastAttorney;
    }
    public Collection<JSONObject> getDistrictcourtDecisionsList()
    {
        return districtcourtDecisionsList;
    }

    public void setDistrictcourtDecisionsList(Collection<JSONObject> districtcourtDecisionsList)
    {
        this.districtcourtDecisionsList = districtcourtDecisionsList;
    }
    public boolean isShowmessage()
    {
        return showmessage;
    }

    public void setShowmessage(boolean showmessage)
    {
        this.showmessage = showmessage;
    }

    public Collection<JuvenileDispositionCodeResponseEvent> getCourtDispositionResponses()
    {
	return courtDispositionResponses;
    }

    public void setCourtDispositionResponses(Collection<JuvenileDispositionCodeResponseEvent> courtDispositionResponses)
    {
	this.courtDispositionResponses = courtDispositionResponses;
    }

    public Collection<JuvenileDispositionCodeResponseEvent> getCourtResultResponses()
    {
	return courtResultResponses;
    }

    public void setCourtResultResponses(Collection<JuvenileDispositionCodeResponseEvent> courtResultResponses)
    {
	this.courtResultResponses = courtResultResponses;
    }
    public String getCJISNum()
    {
        return CJISNum;
    }

    public void setCJISNum(String cJISNum)
    {
        CJISNum = cJISNum;
    }
    public String getTypeCode()
    {
        return typeCode;
    }

    public void setTypeCode(String typeCode)
    {
        this.typeCode = typeCode;
    }
    public Collection<PetitionResponseEvent> getPetitionList()
    {
        return petitionList;
    }

    public void setPetitionList(Collection<PetitionResponseEvent> petitionList)
    {
        this.petitionList = petitionList;
    }
    public String getOrigjuvNum()
    {
        return origjuvNum;
    }

    public void setOrigjuvNum(String origjuvNum)
    {
        this.origjuvNum = origjuvNum;
    }

}
