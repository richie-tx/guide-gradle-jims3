package ui.juvenilecase.referral.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.codetable.person.reply.JuvenileSchoolDistrictCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.interviewinfo.to.JOTTO;
import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;

import org.apache.struts.action.ActionForm;

import pd.juvenilecase.referral.JJSSVIntakeHistory;

import ui.common.Address;
import ui.common.SocialSecurity;
import ui.juvenilecase.referral.JuvenileReferralMemberDetailsBean;
import ui.juvenilecase.referral.JuvenileReferralOffenseBean;

public class JuvenileReferralForm extends ActionForm
{

    private String action;

    /** Juvenile Demographics **/
    private String firstName;
    private String middleName;
    private String lastName;
    private String nameSuffix;
    private String formattedName;

    private String race;
    private String raceId;
    private String originalRaceId; 
    private String sex;
    private String sexId;
    private boolean hispanic;
    private String hispanicStr;
    private String hispanicDesc;


    private String dateOfBirth;
    private String realDOB;

    private SocialSecurity SSN = new SocialSecurity("");
    //private String SSN;
    private String formattedSSN;
    private String completeSSN;
    private String comments;
    private String SSN1;
    private String SSN2;
    private String SSN3;
    /** Juvenile Demographics **/

    /** Juvenile Address Information **/
    private String juvenileNum;
    private Collection<String> races;
    private Collection<String> sexes;
    private Address juvAddress;

    private List<String> stateList;
    private List<String> addressTypeList;
    private List<String> countyList;
    private List<String> streetTypeList;
    private List<String> streetNumSuffixList;
    private String juvFormattedAddress;
    /** Juvenile Address Information **/

    /** Juvenile Education Information **/
    private Map schoolDistricts;
    private Map schools;
    private Collection exitTypes;
    private Collection programAttending;
    private Collection gradeLevels;
    private Collection attendanceStatus;
    private String attendanceStatusId;
    private String attendanceStatusDescription;
    private String gradeLevelId;
    private String programAttendingId;
    private String exitTypeId;
    private String exitTypeDescription;
    private String gradeLevelDescription;
    private String programAttendingDescription;
    private String schoolId;
    private String schoolDistrictId;
    private String schoolDistrictDescription;
    private String schoolDescription;
    private String schoolDisplayLiteral;
    private String schoolName;
    private String schoolHistoryId; //71171
	
    private String instructionType;
    
    private String homeSchoolDescription;
    private String homeSchoolDisplayLiteral;
    private String homeSchoolId;
    private String homeSchoolDistrictId;
    private String homeSchoolDistrictDescription;

    private String specificSchoolAddress;
    private String specificSchoolName;
    private String streetNum;
    private String streetName;
    private String city;
    private String stateId;
    private String stateDesc;
    private String zipCode;
    private String zipCodeExt;
    private List states;
    private String unknownNameInd;
    private String backFlowIndicator;
    /** Juvenile Education Information **/

   
    private JuvenileReferralMemberDetailsBean memberBean;
    private JuvenileReferralMemberDetailsBean selectedMemberBean; //for Update Juv  US 71171
    private List<JuvenileReferralMemberDetailsBean> memberDetailsBeanList;
    
    private String lastJuvenileNum;

    //File Check out
    private String checkedOutTo;
    private String dateOut;
    private String lastActionBy;
    private String lastUpdate;
    private String operator;
    
    private String checkedOutToDesc;
    private String lastActionByDesc;
    private String operatorDesc;

    //misc
    private String selectedValue;
    private String adminReferralFlag;
    private String maxCrimReferral;
    
    //school US 71172
    private Collection schoolNDistrictList; // List of SchoolNames and corresponding District Names.
    private String selectedSchoolId;
    private String teacode;

    private boolean hasCasefiles;
    
    //US 71171
    private String guardianEditFlag;
    private String addGuradianFlag = "N";
    private String juvFromM204Flag;
    
    //US 71173
    //Assigned Referrals List
    private Collection<JuvenileCasefileReferralsResponseEvent> juvenileCasefileReferralsList ;
    private Collection<JuvenileProfileReferralListResponseEvent> originalChargeReferrals ;
    private String newRefNum;
    private String newRefDate;
    private String newRefSource;
    private String newRefIntakeDate;
    private String courtdecisionDate;
    private String newRefIntakeDecision;
    private Collection intakeDecisions = new ArrayList();
    private List<JuvenileReferralOffenseBean> offenseList = new ArrayList<JuvenileReferralOffenseBean>();
    private Collection<JuvenileCasefileOffenseCodeResponseEvent> offenseCodes;
    private String offenseCode;
    private String offenseDate;
    private String keyMapLocation;
    private String investigationNum;
    private String assignmentType;
    private String supervisionCat;
    private String supervisionType;
    private String jpo;
    private String assignmentDateStr;
    private String probationStartDateStr;
    private String probationEndDateStr;
    private String updNewRefSource;
    private String updNewRefIntakeDecision;
    private int ageAtOffense;
    private String isNumericCode23;
    private String isTransferredOffense;
    private List<String> offenseCodeList = new ArrayList<String>();
    private String updateOffenseMsg;
    private String updateOffenseError;
    private boolean updateSSN = false;
    private int refExcludedReporting = 0;
    private int juvExcludedReporting = 0;
    
    public String getUpdNewRefSource()
    {
        return updNewRefSource;
    }

    public void setUpdNewRefSource(String updNewRefSource)
    {
        this.updNewRefSource = updNewRefSource;
    }

    private JuvenileReferralOffenseBean referralOffenseBean = new JuvenileReferralOffenseBean();
    private Collection refSources=new ArrayList();
    private Collection assignmentTypes=new ArrayList();
    private Collection supervisionCategories=new ArrayList();
    private Collection supervisionTypes=new ArrayList();
    private String message;
    private String subsequentMessage;
    private String casefileGenerate;
    private String disbleAssignment="N";
    private String disableForTRN="N";
    private String formalReferralType;
    private Date TJJDReferralDate;
    private String subsequentCasefileId;
    
    //US 71174
    private String offenseCodeDesc;
    private Collection referralList = new ArrayList();
    private List offenseResultList;
    private Collection<JuvenileCasefileOffenseCodeResponseEvent> codetableDataList;
    private String alphaCodeId;
    private String dpsCodeId;
    private String shortDesc;
    private String categoryId; //for severity
    
    private String incarceratedFlag; //BUG 80520
    
    //US 80835
    private String overrideReasonStr;
    private Collection<CodeResponseEvent> overrideReason;
    private String overrideOTHComments;
    
    private Collection selectedReferrals = new ArrayList();
    private String[] selectedRefToOverride = new String[30] ;
    private Map <String, JuvenileProfileReferralListResponseEvent> referralListMap;
    
    //US 71177
    private String referralEditFlag="N";
    private String updateOffenseFlag="N";
    private String updateRefStat;
    private String updateRefCloseDt;
    private String offenseId;
    private String updateAsmntTypeFlag="N";
    private String probationStartDate;
    private String probationEndDate;
    private JOTTO jotInfo;
    private String updateRefFlag;
    private String updateRefStatFlag;
    private String loadAssmntFlag;   
    private String lcUser;
    private String lcUserName;
    private String earliestOffenseDate; 
    private String tjjdDateStr;
    private String nextOffenseSeqNum;
    private String updateRefStatFeature;
    private String updateMessage;
    private String updateAction="";
    private String controllingRef=""; //Task# 85484
    private String jpoName="";
    private boolean disableAddRefBtn;
    
    //Bug #83327
    private String activateSupervision;
    private String currentCasefileId;
    
    private String selectedOffense;  //Bug #91834
    
    //Task 99715
    private String officerFirstName;
    private String officerLastName;
    private String officerMiddleName;
    private String officerSearchType;
    private String daLogNum;    
    private Date daDateOut;  
    private String logStatus;
    private String CJIS;  
    private String newRefRecType;  
    
    private boolean isRealDOBUpdateAllowed = false;
    
    //US 158140
    List<JJSSVIntakeHistory>intakeHistoryList = new ArrayList<JJSSVIntakeHistory>();
    
    public String getNewRefRecType()
    {
        return newRefRecType;
    }
    
    public void setNewRefRecType(String newRefRecType)
    {
        this.newRefRecType = newRefRecType;
    }
    
    
    /**
     * @return the assignmentType
     */
    public String getAssignmentType()
    {
        return assignmentType;
    }

    /**
     * @param assignmentType the assignmentType to set
     */
    public void setAssignmentType(String assignmentType)
    {
        this.assignmentType = assignmentType;
    }

    /**
     * @return the supervisionCat
     */
    public String getSupervisionCat()
    {
        return supervisionCat;
    }

    /**
     * @param supervisionCat the supervisionCat to set
     */
    public void setSupervisionCat(String supervisionCat)
    {
        this.supervisionCat = supervisionCat;
    }

    /**
     * @return the supervisionType
     */
    public String getSupervisionType()
    {
        return supervisionType;
    }

    /**
     * @param supervisionType the supervisionType to set
     */
    public void setSupervisionType(String supervisionType)
    {
        this.supervisionType = supervisionType;
    }

    /**
     * @return the jpo
     */
    public String getJpo()
    {
        return jpo;
    }

    /**
     * @param jpo the jpo to set
     */
    public void setJpo(String jpo)
    {
        this.jpo = jpo;
    }

    /**
     * @return the assignmentDateStr
     */
    public String getAssignmentDateStr()
    {
        return assignmentDateStr;
    }

    /**
     * @param assignmentDateStr the assignmentDateStr to set
     */
    public void setAssignmentDateStr(String assignmentDateStr)
    {
        this.assignmentDateStr = assignmentDateStr;
    }

    /**
     * @return the assignmentTypes
     */
    public Collection getAssignmentTypes()
    {
        return assignmentTypes;
    }

    /**
     * @param assignmentTypes the assignmentTypes to set
     */
    public void setAssignmentTypes(Collection assignmentTypes)
    {
        this.assignmentTypes = assignmentTypes;
    }

    /**
     * @return the supervisionCategories
     */
    public Collection getSupervisionCategories()
    {
        return supervisionCategories;
    }

    /**
     * @param supervisionCategories the supervisionCategories to set
     */
    public void setSupervisionCategories(Collection supervisionCategories)
    {
        this.supervisionCategories = supervisionCategories;
    }

    /**
     * @return the supervisionTypes
     */
    public Collection getSupervisionTypes()
    {
        return supervisionTypes;
    }

    /**
     * @param supervisionTypes the supervisionTypes to set
     */
    public void setSupervisionTypes(Collection supervisionTypes)
    {
        this.supervisionTypes = supervisionTypes;
    }


    /**
     * Clear
     */
    public void clear()
    {
	firstName = null;
	lastName = null;
	formattedName = "";
	raceId = null;
	originalRaceId = null;
	race = "";
	dateOfBirth = null;
	realDOB = null;
	juvenileNum = null;
	middleName = null;
	sexId = null;
	sex="";
	SSN = new SocialSecurity("");
	completeSSN = "";
	streetNumSuffixList = new ArrayList<String>();
	streetTypeList = new ArrayList<String>();
	stateList = new ArrayList<String>();
	addressTypeList = new ArrayList<String>();
	countyList = new ArrayList<String>();
	states = new ArrayList<String>();
	juvAddress = new Address();
	memberBean = new JuvenileReferralMemberDetailsBean();
	selectedMemberBean = new JuvenileReferralMemberDetailsBean();
	exitTypes = new ArrayList<String>();
	programAttending = new ArrayList<String>();
	gradeLevels = new ArrayList<String>();
	schoolDistricts = new HashMap();
	schools = new HashMap();
	memberDetailsBeanList = new ArrayList<JuvenileReferralMemberDetailsBean>();
	checkedOutToDesc="";
	lastActionByDesc="";
	operatorDesc="";
	hispanic=false;
	hispanicDesc="";
	hispanicStr="";
	comments="";
	nameSuffix="";
	exitTypeId="";
	programAttendingId="";
	gradeLevelId="";
	hasCasefiles=false;
	schoolDescription="";
	schoolName="";
	schoolDistrictDescription="";
	schoolId="";
	schoolDistrictId="";
	programAttendingDescription="";
	programAttendingId="";
	gradeLevelId="";
	gradeLevelDescription="";
	exitTypeId="";
	exitTypeDescription="";
	attendanceStatusDescription="";
	attendanceStatusId="";
	teacode="";
	guardianEditFlag="N";
	addGuradianFlag = "N";
	incarceratedFlag = "";
	overrideOTHComments="";
	overrideReasonStr="";
	referralList= new ArrayList ();
	referralListMap = new HashMap<String, JuvenileProfileReferralListResponseEvent>();
	action="";
	dateOut=null;
	intakeHistoryList = new ArrayList<JJSSVIntakeHistory>();
    }

    /**
     * Clear
     */
    public void clearRefDetails()
    {
	this.newRefSource="";
	this.newRefIntakeDecision="";
	this.newRefIntakeDate="";
	this.assignmentType="";
	this.supervisionCat="";
	this.supervisionType="";
	this.jpo="";
	this.offenseDate = "";
	//this.referralList=new ArrayList(); DO NOT UN-COMMENT
	this.disbleAssignment="N";
	this.disableForTRN="N";
	this.setOffenseCode("");
	this.setMessage("");
	this.setAction("");
	this.offenseList=new ArrayList();	
	this.supervisionTypes=new ArrayList();
	this.supervisionCategories=new ArrayList();
	this.tjjdDateStr="";
	this.probationEndDate="";
	this.probationStartDate="";
	
    }
    /**
     * @return the firstName
     */
    public String getFirstName()
    {
	return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName)
    {
	this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName()
    {
	return middleName;
    }

    /**
     * @param middleName
     *            the middleName to set
     */
    public void setMiddleName(String middleName)
    {
	this.middleName = middleName;
    }

    /**
     * @return the lastName
     */
    public String getLastName()
    {
	return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName)
    {
	this.lastName = lastName;
    }

    /**
     * @return the nameSuffix
     */
    public String getNameSuffix()
    {
	return nameSuffix;
    }

    /**
     * @param nameSuffix
     *            the nameSuffix to set
     */
    public void setNameSuffix(String nameSuffix)
    {
	this.nameSuffix = nameSuffix;
    }

    /**
     * @return the formattedName
     */
    public String getFormattedName()
    {
	return formattedName;
    }

    /**
     * @param formattedName
     *            the formattedName to set
     */
    public void setFormattedName(String formattedName)
    {
	this.formattedName = formattedName;
    }

    /**
     * @return the juvenileNum
     */
    public String getJuvenileNum()
    {
	return juvenileNum;
    }

    /**
     * @param juvenileNum
     *            the juvenileNum to set
     */
    public void setJuvenileNum(String juvenileNum)
    {
	this.juvenileNum = juvenileNum;
    }

    /**
     * @return the race
     */
    public String getRace()
    {
	return race;
    }

    /**
     * @param race
     *            the race to set
     */
    public void setRace(String race)
    {
	this.race = race;
    }

    /**
     * @return the hispanic
     */
    public boolean isHispanic()
    {
	return hispanic;
    }

    /**
     * @param hispanic
     *            the hispanic to set
     */
    public void setHispanic(boolean hispanic)
    {
	this.hispanic = hispanic;
    }

    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth()
    {
	return dateOfBirth;
    }

    /**
     * @param dateOfBirth
     *            the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth)
    {
	this.dateOfBirth = dateOfBirth;
    }
    
    public String getRealDOB()
    {
	return this.realDOB;
    } 
    
    public void setRealDOB(String realDOB)
    {
	this.realDOB = realDOB;

    } 

    /**
     * @return the formattedSSN
     */
    public String getFormattedSSN()
    {
	return formattedSSN;
    }

    /**
     * @param formattedSSN
     *            the formattedSSN to set
     */
    public void setFormattedSSN(String formattedSSN)
    {
	this.formattedSSN = formattedSSN;
    }

    /**
     * @return the sex
     */
    public String getSex()
    {
	return sex;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(String sex)
    {
	this.sex = sex;
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
     * @return the action
     */
    public String getAction()
    {
	return action;
    }

    /**
     * @param action
     *            the action to set
     */
    public void setAction(String action)
    {
	this.action = action;
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
     * @return the completeSSN
     */
    public String getCompleteSSN()
    {
	return completeSSN;
    }

    /**
     * @param completeSSN
     *            the completeSSN to set
     */
    public void setCompleteSSN(String completeSSN)
    {
	this.completeSSN = completeSSN;
    }

    /**
     * @return the races
     */
    public Collection<String> getRaces()
    {
	return races;
    }

    /**
     * @param races
     *            the races to set
     */
    public void setRaces(Collection<String> races)
    {
	this.races = races;
    }

    /**
     * @return the sexes
     */
    public Collection<String> getSexes()
    {
	return sexes;
    }

    /**
     * @param sexes
     *            the sexes to set
     */
    public void setSexes(Collection<String> sexes)
    {
	this.sexes = sexes;
    }

    /**
     * @return the sSN
     */
    public SocialSecurity getSSN()
    {
	return SSN;
    }

    /**
     * @param sSN
     *            the sSN to set
     */
    public void setSSN(SocialSecurity sSN)
    {
	SSN = sSN;
    }

    /**
     * @return the streetNumSuffixList
     */
    public List<String> getStreetNumSuffixList()
    {
	return streetNumSuffixList;
    }

    /**
     * @param streetNumSuffixList
     *            the streetNumSuffixList to set
     */
    public void setStreetNumSuffixList(List<String> streetNumSuffixList)
    {
	this.streetNumSuffixList = streetNumSuffixList;
    }

    /**
     * @return the streetTypeList
     */
    public List<String> getStreetTypeList()
    {
	return streetTypeList;
    }

    /**
     * @param streetTypeList
     *            the streetTypeList to set
     */
    public void setStreetTypeList(List<String> streetTypeList)
    {
	this.streetTypeList = streetTypeList;
    }

    /**
     * @return the stateList
     */
    public List<String> getStateList()
    {
	return stateList;
    }

    /**
     * @param stateList
     *            the stateList to set
     */
    public void setStateList(List<String> stateList)
    {
	this.stateList = stateList;
    }

    /**
     * @return the addressTypeList
     */
    public List<String> getAddressTypeList()
    {
	return addressTypeList;
    }

    /**
     * @param addressTypeList
     *            the addressTypeList to set
     */
    public void setAddressTypeList(List<String> addressTypeList)
    {
	this.addressTypeList = addressTypeList;
    }

    /**
     * @return the countyList
     */
    public List<String> getCountyList()
    {
	return countyList;
    }

    /**
     * @param countyList
     *            the countyList to set
     */
    public void setCountyList(List<String> countyList)
    {
	this.countyList = countyList;
    }

    /**
     * @param schoolDistricts
     *            the schoolDistricts to set
     */
    public void setSchoolDistricts(Map schoolDistricts)
    {
	this.schoolDistricts = schoolDistricts;
    }

    /**
     * @param schools
     *            the schools to set
     */
    public void setSchools(Map schools)
    {
	this.schools = schools;
    }

    /**
     * @return the exitTypes
     */
    public Collection getExitTypes()
    {
	return exitTypes;
    }

    /**
     * @param exitTypes
     *            the exitTypes to set
     */
    public void setExitTypes(Collection exitTypes)
    {
	this.exitTypes = exitTypes;
    }

    /**
     * @return the programAttending
     */
    public Collection getProgramAttending()
    {
	return programAttending;
    }

    /**
     * @param programAttending
     *            the programAttending to set
     */
    public void setProgramAttending(Collection programAttending)
    {
	this.programAttending = programAttending;
    }

    /**
     * @return the gradeLevels
     */
    public Collection getGradeLevels()
    {
	return gradeLevels;
    }

    /**
     * @param gradeLevels
     *            the gradeLevels to set
     */
    public void setGradeLevels(Collection gradeLevels)
    {
	this.gradeLevels = gradeLevels;
    }

    /**
     * @return the gradeLevelId
     */
    public String getGradeLevelId()
    {
	return gradeLevelId;
    }

    /**
     * @param gradeLevelId
     *            the gradeLevelId to set
     */
    public void setGradeLevelId(String gradeLevelId)
    {
	this.gradeLevelId = gradeLevelId;
    }

    /**
     * @return the programAttendingId
     */
    public String getProgramAttendingId()
    {
	return programAttendingId;
    }

    /**
     * @param programAttendingId
     *            the programAttendingId to set
     */
    public void setProgramAttendingId(String programAttendingId)
    {
	this.programAttendingId = programAttendingId;
    }

    /**
     * @return the exitTypeId
     */
    public String getExitTypeId()
    {
	return exitTypeId;
    }

    /**
     * @param exitTypeId
     *            the exitTypeId to set
     */
    public void setExitTypeId(String exitTypeId)
    {
	this.exitTypeId = exitTypeId;
    }

    /**
     * @return the schoolId
     */
    public String getSchoolId()
    {
	return schoolId;
    }

    /**
     * @param schoolId
     *            the schoolId to set
     */
    public void setSchoolId(String schoolId)
    {
	this.schoolId = schoolId;
    }

    /**
     * @return the schoolDistrictId
     */
    public String getSchoolDistrictId()
    {
	return schoolDistrictId;
    }

    /**
     * @param schoolDistrictId
     *            the schoolDistrictId to set
     */
    public void setSchoolDistrictId(String schoolDistrictId)
    {
	this.schoolDistrictId = schoolDistrictId;
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
     * @return the dateOut
     */
    public String getDateOut()
    {
	return dateOut;
    }

    /**
     * @param dateOut
     *            the dateOut to set
     */
    public void setDateOut(String dateOut)
    {
	this.dateOut = dateOut;
    }

    /**
     * @return the lastActionBy
     */
    public String getLastActionBy()
    {
	return lastActionBy;
    }

    /**
     * @param lastActionBy
     *            the lastActionBy to set
     */
    public void setLastActionBy(String lastActionBy)
    {
	this.lastActionBy = lastActionBy;
    }


    /**
     * @return the lastUpdate
     */
    public String getLastUpdate()
    {
        return lastUpdate;
    }

    /**
     * @param lastUpdate the lastUpdate to set
     */
    public void setLastUpdate(String lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @param schoolDisplayLiteral the schoolDisplayLiteral to set
     */
    public void setSchoolDisplayLiteral(String schoolDisplayLiteral)
    {
        this.schoolDisplayLiteral = schoolDisplayLiteral;
    }

    /**
     * @return the operator
     */
    public String getOperator()
    {
	return operator;
    }

    /**
     * @param operator
     *            the operator to set
     */
    public void setOperator(String operator)
    {
	this.operator = operator;
    }

    /**
     * @return the juvAddress
     */
    public Address getJuvAddress()
    {
	return juvAddress;
    }

    /**
     * @param juvAddress
     *            the juvAddress to set
     */
    public void setJuvAddress(Address juvAddress)
    {
	this.juvAddress = juvAddress;
    }

    /**
     * @return the selectedValue
     */
    public String getSelectedValue()
    {
	return selectedValue;
    }

    /**
     * @param selectedValue
     *            the selectedValue to set
     */
    public void setSelectedValue(String selectedValue)
    {
	this.selectedValue = selectedValue;
    }

    /**
     * @return the memberBean
     */
    public JuvenileReferralMemberDetailsBean getMemberBean()
    {
	return memberBean;
    }

    /**
     * @param memberBean
     *            the memberBean to set
     */
    public void setMemberBean(JuvenileReferralMemberDetailsBean memberBean)
    {
	this.memberBean = memberBean;
    }

    /**
     * @return the memberDetailsBeanList
     */
    public List<JuvenileReferralMemberDetailsBean> getMemberDetailsBeanList()
    {
	return memberDetailsBeanList;
    }

    /**
     * @param memberDetailsBeanList
     *            the memberDetailsBeanList to set
     */
    public void setMemberDetailsBeanList(List<JuvenileReferralMemberDetailsBean> memberDetailsBeanList)
    {
	this.memberDetailsBeanList = memberDetailsBeanList;
    }

    /**
     * @return
     */
    public String getSchoolDisplayLiteral()
    {
	schoolDisplayLiteral = null;
	JuvenileSchoolDistrictCodeResponseEvent js = this.getSchoolCode();
	if (js != null)
	{
	    schoolDisplayLiteral = js.getSchoolDisplayLiteral();
	}
	return schoolDisplayLiteral;
    }

    /**
     * @return
     */
    public Collection getSchoolDistricts()
    {

	if (schoolDistricts == null)
	{
	    schoolDistricts = new HashMap();
	}

	ArrayList myList = new ArrayList(schoolDistricts.values());
	Collections.sort((List) myList);

	return myList;
    }

    public Collection getSchools()
    {
	Collection schoolCodes = null;
	if (schoolDistrictId == null || schools == null || schools.containsKey(schoolDistrictId) == false)
	{
	    schoolCodes = new ArrayList();
	}
	else
	{
	    schoolCodes = (Collection) schools.get(schoolDistrictId);
	    Collections.sort((List) schoolCodes);
	}

	return schoolCodes;
    }

    public JuvenileSchoolDistrictCodeResponseEvent getSchoolCode()
    {
	JuvenileSchoolDistrictCodeResponseEvent foundSchool = null;
	if (this.getSchoolMap().containsKey(this.schoolDistrictId))
	{
	    Collection schoolCodes = (Collection) schools.get(this.schoolDistrictId);
	    if (schoolCodes != null)
	    {
		Iterator i = schoolCodes.iterator();
		while (i.hasNext())
		{
		    JuvenileSchoolDistrictCodeResponseEvent schoolCode = (JuvenileSchoolDistrictCodeResponseEvent) i.next();
		    if (schoolCode.getSchoolCode().equals(this.schoolId))
		    {
			foundSchool = schoolCode;
			break;
		    }
		}
	    }
	}
	return foundSchool;
    }

    /**
	 * 
	 */
    private Map getSchoolMap()
    {
	if (this.schools == null)
	{
	    this.schools = new HashMap();
	}
	return this.schools;

    }

    public boolean getIsAlternativeSchool() {
	
	boolean isAlternativeSchool = false;
	JuvenileSchoolDistrictCodeResponseEvent re = getSchoolCode();
	
	if (re != null ){
		String districtCode = re.getDistrictCode();
		if ( "053".equals(districtCode )
			|| "054".equals(districtCode )
			|| "055".equals(districtCode )
			|| "A".equals(re.getSubGroupInd() ) ) {
				isAlternativeSchool = true;
		}
		districtCode = null;
	}
	
	return isAlternativeSchool;
}
   
    public String getHispanicStr()
    {
	return hispanicStr;
    }

    public void setHispanicStr(String hispanicStr)
    {
	this.hispanicStr = hispanicStr;
    }

    public String getExitTypeDescription()
    {
	return exitTypeDescription;
    }

    public void setExitTypeDescription(String exitTypeDescription)
    {
	this.exitTypeDescription = exitTypeDescription;
    }

    public String getGradeLevelDescription()
    {
	return gradeLevelDescription;
    }

    public void setGradeLevelDescription(String gradeLevelDescription)
    {
	this.gradeLevelDescription = gradeLevelDescription;
    }

    public String getProgramAttendingDescription()
    {
	return programAttendingDescription;
    }

    public void setProgramAttendingDescription(String programAttendingDescription)
    {
	this.programAttendingDescription = programAttendingDescription;
    }

    public String getInstructionType()
    {
	return instructionType;
    }

    public void setInstructionType(String instructionType)
    {
	this.instructionType = instructionType;
    }

    public String getHomeSchoolDescription()
    {
	return homeSchoolDescription;
    }

    public void setHomeSchoolDescription(String homeSchoolDescription)
    {
	this.homeSchoolDescription = homeSchoolDescription;
    }

    public String getHomeSchoolDisplayLiteral()
    {
	return homeSchoolDisplayLiteral;
    }

    public void setHomeSchoolDisplayLiteral(String homeSchoolDisplayLiteral)
    {
	this.homeSchoolDisplayLiteral = homeSchoolDisplayLiteral;
    }

    public String getHomeSchoolId()
    {
	return homeSchoolId;
    }

    public void setHomeSchoolId(String homeSchoolId)
    {
	this.homeSchoolId = homeSchoolId;
    }

    public String getHomeSchoolDistrictId()
    {
	return homeSchoolDistrictId;
    }

    public void setHomeSchoolDistrictId(String homeSchoolDistrictId)
    {
	this.homeSchoolDistrictId = homeSchoolDistrictId;
    }

    public String getHomeSchoolDistrictDescription()
    {
	return homeSchoolDistrictDescription;
    }

    public void setHomeSchoolDistrictDescription(String homeSchoolDistrictDescription)
    {
	this.homeSchoolDistrictDescription = homeSchoolDistrictDescription;
    }

    public String getSpecificSchoolAddress()
    {
	return specificSchoolAddress;
    }

    public void setSpecificSchoolAddress(String specificSchoolAddress)
    {
	this.specificSchoolAddress = specificSchoolAddress;
    }

    public String getSpecificSchoolName()
    {
	return specificSchoolName;
    }

    public void setSpecificSchoolName(String specificSchoolName)
    {
	this.specificSchoolName = specificSchoolName;
    }

    public String getStreetName()
    {
	return streetName;
    }

    public void setStreetName(String streetName)
    {
	this.streetName = streetName;
    }

    public String getStreetNum()
    {
	return streetNum;
    }

    public void setStreetNum(String streetNum)
    {
	this.streetNum = streetNum;
    }

    public String getCity()
    {
	return city;
    }

    public void setCity(String city)
    {
	this.city = city;
    }

    public String getStateId()
    {
	return stateId;
    }

    public void setStateId(String stateId)
    {
	this.stateId = stateId;
    }

    public String getStateDesc()
    {
	return stateDesc;
    }

    public void setStateDesc(String stateDesc)
    {
	this.stateDesc = stateDesc;
    }

    public String getZipCode()
    {
	return zipCode;
    }

    public void setZipCode(String zipCode)
    {
	this.zipCode = zipCode;
    }

    public String getZipCodeExt()
    {
	return zipCodeExt;
    }

    public void setZipCodeExt(String zipCodeExt)
    {
	this.zipCodeExt = zipCodeExt;
    }

    public List getStates()
    {
	return states;
    }

    public void setStates(List states)
    {
	this.states = states;
    }

    public String getUnknownNameInd()
    {
	return unknownNameInd;
    }

    public void setUnknownNameInd(String unknownNameInd)
    {
	this.unknownNameInd = unknownNameInd;
    }

    public String getBackFlowIndicator()
    {
	return backFlowIndicator;
    }

    public void setBackFlowIndicator(String backFlowIndicator)
    {
	this.backFlowIndicator = backFlowIndicator;
    }

    public String getSchoolDistrictDescription()
    {
	return schoolDistrictDescription;
    }

    public void setSchoolDistrictDescription(String schoolDistrictDescription)
    {
	this.schoolDistrictDescription = schoolDistrictDescription;
    }

    public String getSchoolDescription()
    {
	return schoolDescription;
    }

    public void setSchoolDescription(String schoolDescription)
    {
	this.schoolDescription = schoolDescription;
    }

    public String getSchoolName()
    {
	return schoolName;
    }

    public void setSchoolName(String schoolName)
    {
	this.schoolName = schoolName;
    }

    public String getJuvFormattedAddress()
    {
	return juvFormattedAddress;
    }

    public void setJuvFormattedAddress(String juvFormattedAddress)
    {
	this.juvFormattedAddress = juvFormattedAddress;
    }

    public String getCheckedOutToDesc()
    {
	return checkedOutToDesc;
    }

    public void setCheckedOutToDesc(String checkedOutToDesc)
    {
	this.checkedOutToDesc = checkedOutToDesc;
    }

    public String getLastActionByDesc()
    {
	return lastActionByDesc;
    }

    public void setLastActionByDesc(String lastActionByDesc)
    {
	this.lastActionByDesc = lastActionByDesc;
    }


    public String getOperatorDesc()
    {
	return operatorDesc;
    }

    public void setOperatorDesc(String operatorDesc)
    {
	this.operatorDesc = operatorDesc;
    }

    public String getHispanicDesc()
    {
	return hispanicDesc;
    }

    public void setHispanicDesc(String hispanicDesc)
    {
	this.hispanicDesc = hispanicDesc;
    }

    public String getLastJuvenileNum()
    {
	return lastJuvenileNum;
    }

    public void setLastJuvenileNum(String lastJuvenileNum)
    {
	this.lastJuvenileNum = lastJuvenileNum;
    }

    public Collection getSchoolNDistrictList()
    {
	return schoolNDistrictList;
    }

    public void setSchoolNDistrictList(Collection schoolNDistrictList)
    {
	this.schoolNDistrictList = schoolNDistrictList;
    }

    public String getSelectedSchoolId()
    {
	return selectedSchoolId;
    }

    public void setSelectedSchoolId(String selectedSchoolId)
    {
	this.selectedSchoolId = selectedSchoolId;
    }

    public boolean isHasCasefiles()
    {
	return hasCasefiles;
    }

    public void setHasCasefiles(boolean hasCasefiles)
    {
	this.hasCasefiles = hasCasefiles;
    }
    
    public void setSchoolCollections(Collection<JuvenileSchoolDistrictCodeResponseEvent> schoolDistricts, JuvenileReferralForm form)
    {
	Map schoolMap = new HashMap();
	Map schoolDistrictMap = new HashMap();

	if (schoolDistricts != null)
	{
	    for (JuvenileSchoolDistrictCodeResponseEvent school : schoolDistricts)
	    {
		String districtKey = school.getDistrictCode();

		// Add school district
		if (schoolDistrictMap.containsKey(districtKey) == false)
		{
		    CodeResponseEvent schoolDistrict = new CodeResponseEvent();
		    schoolDistrict.setCode(school.getDistrictCode());
		    schoolDistrict.setDescription(school.getDistrictDescription());
		    schoolDistrictMap.put(districtKey, schoolDistrict);
		}

		// Add school
		Collection schools = null;
		if (schoolMap.containsKey(districtKey) == false)
		{ // initialize the schools for this district based on the districtKey
		    schools = new ArrayList();
		    schoolMap.put(districtKey, schools);
		}

		schools = (Collection) schoolMap.get(districtKey);

		schools.add(school);
	    }
	}
	form.setSchoolDistricts(schoolDistrictMap);
	form.setSchools(schoolMap);
    }

    public Collection getAttendanceStatus()
    {
        return attendanceStatus;
    }

    public void setAttendanceStatus(Collection attendanceStatus)
    {
        this.attendanceStatus = attendanceStatus;
    }

    public String getAttendanceStatusId()
    {
        return attendanceStatusId;
    }

    public void setAttendanceStatusId(String attendanceStatusId)
    {
        this.attendanceStatusId = attendanceStatusId;
    }

    public String getAttendanceStatusDescription()
    {
        return attendanceStatusDescription;
    }

    public void setAttendanceStatusDescription(String attendanceStatusDescription)
    {
        this.attendanceStatusDescription = attendanceStatusDescription;
    }

    public String getTeacode()
    {
	return teacode;
    }

    public void setTeacode(String teacode)
    {
	this.teacode = teacode;
    }

    public String getGuardianEditFlag()
    {
	return guardianEditFlag;
    }

    public void setGuardianEditFlag(String guardianEditFlag)
    {
	this.guardianEditFlag = guardianEditFlag;
    }

    public JuvenileReferralMemberDetailsBean getSelectedMemberBean()
    {
	return selectedMemberBean;
    }

    public void setSelectedMemberBean(JuvenileReferralMemberDetailsBean selectedMemberBean)
    {
	this.selectedMemberBean = selectedMemberBean;
    }

    public String getAddGuradianFlag()
    {
	return addGuradianFlag;
    }

    public void setAddGuradianFlag(String addGuradianFlag)
    {
	this.addGuradianFlag = addGuradianFlag;
    }

    public String getSchoolHistoryId()
    {
	return schoolHistoryId;
    }

    public void setSchoolHistoryId(String schoolHistoryId)
    {
	this.schoolHistoryId = schoolHistoryId;
    }

    /**
     * @return the juvenileCasefileReferralsList
     */
    public Collection<JuvenileCasefileReferralsResponseEvent> getJuvenileCasefileReferralsList()
    {
	return juvenileCasefileReferralsList;
    }

    /**
     * @param juvenileCasefileReferralsList the juvenileCasefileReferralsList to set
     */
    public void setJuvenileCasefileReferralsList(Collection<JuvenileCasefileReferralsResponseEvent> juvenileCasefileReferralsList)
    {
	this.juvenileCasefileReferralsList = juvenileCasefileReferralsList;
    }

    /**
     * @return the newRefNum
     */
    public String getNewRefNum()
    {
	return newRefNum;
    }

    /**
     * @param newRefNum the newRefNum to set
     */
    public void setNewRefNum(String newRefNum)
    {
	this.newRefNum = newRefNum;
    }

    /**
     * @return the newRefDate
     */
    public String getNewRefDate()
    {
        return newRefDate;
    }

    /**
     * @param newRefDate the newRefDate to set
     */
    public void setNewRefDate(String newRefDate)
    {
        this.newRefDate = newRefDate;
    }

    /**
     * @return the newRefSource
     */
    public String getNewRefSource()
    {
        return newRefSource;
    }

    /**
     * @param newRefSource the newRefSource to set
     */
    public void setNewRefSource(String newRefSource)
    {
        this.newRefSource = newRefSource;
    }

    /**
     * @return the newRefIntakeDate
     */
    public String getNewRefIntakeDate()
    {
        return newRefIntakeDate;
    }

    /**
     * @param newRefIntakeDate the newRefIntakeDate to set
     */
    public void setNewRefIntakeDate(String newRefIntakeDate)
    {
        this.newRefIntakeDate = newRefIntakeDate;
    }

    /**
     * @return the intakeDecisions
     */
    public Collection getIntakeDecisions()
    {
	return intakeDecisions;
    }

    /**
     * @param intakeDecisions the intakeDecisions to set
     */
    public void setIntakeDecisions(Collection intakeDecisions)
    {
	this.intakeDecisions = intakeDecisions;
    }

    /**
     * @return the offenseList
     */
    public List<JuvenileReferralOffenseBean> getOffenseList()
    {
	return offenseList;
    }

    /**
     * @param offenseList the offenseList to set
     */
    public void setOffenseList(List<JuvenileReferralOffenseBean> offenseList)
    {
	this.offenseList = offenseList;
    }

    /**
     * @return the offenseCode
     */
    public String getOffenseCode()
    {
        return offenseCode;
    }

    /**
     * @param offenseCode the offenseCode to set
     */
    public void setOffenseCode(String offenseCode)
    {
        this.offenseCode = offenseCode;
    }

    /**
     * @return the offenseDate
     */
    public String getOffenseDate()
    {
        return offenseDate;
    }

    /**
     * @param offenseDate the offenseDate to set
     */
    public void setOffenseDate(String offenseDate)
    {
        this.offenseDate = offenseDate;
    }

    /**
     * @return the keyMapLocation
     */
    public String getKeyMapLocation()
    {
        return keyMapLocation;
    }

    /**
     * @param keyMapLocation the keyMapLocation to set
     */
    public void setKeyMapLocation(String keyMapLocation)
    {
        this.keyMapLocation = keyMapLocation;
    }

    /**
     * @return the investigationNum
     */
    public String getInvestigationNum()
    {
        return investigationNum;
    }

    /**
     * @param investigationNum the investigationNum to set
     */
    public void setInvestigationNum(String investigationNum)
    {
        this.investigationNum = investigationNum;
    }

    /**
     * @return the newRefIntakeDecision
     */
    public String getNewRefIntakeDecision()
    {
	return newRefIntakeDecision;
    }

    /**
     * @param newRefIntakeDecision the newRefIntakeDecision to set
     */
    public void setNewRefIntakeDecision(String newRefIntakeDecision)
    {
	this.newRefIntakeDecision = newRefIntakeDecision;
    }

    /**
     * @return the juvFromM204Flag
     */
    public String getJuvFromM204Flag()
    {
	return juvFromM204Flag;
    }

    /**
     * @param juvFromM204Flag the juvFromM204Flag to set
     */
    public void setJuvFromM204Flag(String juvFromM204Flag)
    {
	this.juvFromM204Flag = juvFromM204Flag;
    }

    public String getOffenseCodeDesc()
    {
	return offenseCodeDesc;
    }

    public void setOffenseCodeDesc(String offenseCodeDesc)
    {
	this.offenseCodeDesc = offenseCodeDesc;
    }
    
        /**
     * @return the referralOffenseBean
     */
    public JuvenileReferralOffenseBean getReferralOffenseBean()
    {
	return referralOffenseBean;
    }

    /**
     * @param referralOffenseBean the referralOffenseBean to set
     */
    public void setReferralOffenseBean(JuvenileReferralOffenseBean referralOffenseBean)
    {
	this.referralOffenseBean = referralOffenseBean;
    }

    /**
     * @return the refSources
     */
    public Collection getRefSources()
    {
	return refSources;
    }

    /**
     * @param refSources the refSources to set
     */
    public void setRefSources(Collection refSources)
    {
	this.refSources = refSources;
    }
        /**
     * @return the message
     */
    public String getMessage()
    {
	return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message)
    {
	this.message = message;
    }
    
    /**
     * @return the referralList
     */
    public Collection getReferralList()
    {
	return referralList;
    }

    /**
     * @param referralList the referralList to set
     */
    public void setReferralList(Collection referralList)
    {
	this.referralList = referralList;
    }

    /**
     * @return the casefileGenerate
     */
    public String getCasefileGenerate()
    {
	return casefileGenerate;
    }

    /**
     * @param casefileGenerate the casefileGenerate to set
     */
    public void setCasefileGenerate(String casefileGenerate)
    {
	this.casefileGenerate = casefileGenerate;
    }
    

    public List getOffenseResultList()
    {
	return offenseResultList;
    }

    /**
     * @return the disbleAssignment
     */
    public String getDisbleAssignment()
    {
	return disbleAssignment;
    }

    /**
     * @param disbleAssignment the disbleAssignment to set
     */
    public void setDisbleAssignment(String disbleAssignment)
    {
	this.disbleAssignment = disbleAssignment;
    }

    public void setOffenseResultList(List offenseResultList)
    {
	this.offenseResultList = offenseResultList;
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

    public String getDpsCodeId()
    {
	return dpsCodeId;
    }

    public void setDpsCodeId(String dpsCodeId)
    {
	this.dpsCodeId = dpsCodeId;
    }

    public String getShortDesc()
    {
	return shortDesc;
    }

    public void setShortDesc(String shortDesc)
    {
	this.shortDesc = shortDesc;
    }

    public String getCategoryId()
    {
	return categoryId;
    }

    public void setCategoryId(String categoryId)
    {
	this.categoryId = categoryId;
    }

    /**
     * @return the disableForTRN
     */
    public String getDisableForTRN()
    {
	return disableForTRN;
    }

    /**
     * @param disableForTRN the disableForTRN to set
     */
    public void setDisableForTRN(String disableForTRN)
    {
	this.disableForTRN = disableForTRN;
    }

    /**
     * @return the formalReferralType
     */
    public String getFormalReferralType()
    {
        return formalReferralType;
    }

    /**
     * @param formalReferralType the formalReferralType to set
     */
    public void setFormalReferralType(String formalReferralType)
    {
        this.formalReferralType = formalReferralType;
    }

    /**
     * @return the tJJDReferralDate
     */
    public Date getTJJDReferralDate()
    {
        return TJJDReferralDate;
    }

    /**
     * @param tJJDReferralDate the tJJDReferralDate to set
     */
    public void setTJJDReferralDate(Date tJJDReferralDate)
    {
        TJJDReferralDate = tJJDReferralDate;
    }

    /**
     * @return the subsequentCasefileId
     */
    public String getSubsequentCasefileId()
    {
	return subsequentCasefileId;
    }

    /**
     * @param subsequentCasefileId the subsequentCasefileId to set
     */
    public void setSubsequentCasefileId(String subsequentCasefileId)
    {
	this.subsequentCasefileId = subsequentCasefileId;
    }

    public String getIncarceratedFlag()
    {
	return incarceratedFlag;
    }

    public void setIncarceratedFlag(String incarceratedFlag)
    {
	this.incarceratedFlag = incarceratedFlag;
    }

    public String getOverrideReasonStr()
    {
	return overrideReasonStr;
    }

    public void setOverrideReasonStr(String overrideReasonStr)
    {
	this.overrideReasonStr = overrideReasonStr;
    }

    public Collection<CodeResponseEvent> getOverrideReason()
    {
	return overrideReason;
    }

    public void setOverrideReason(Collection<CodeResponseEvent> overrideReason)
    {
	this.overrideReason = overrideReason;
    }

    public String getOverrideOTHComments()
    {
	return overrideOTHComments;
    }

    public void setOverrideOTHComments(String overrideOTHComments)
    {
	this.overrideOTHComments = overrideOTHComments;
    }

    /**
     * @return the referralEditFlag
     */
    public String getReferralEditFlag()
    {
	return referralEditFlag;
    }

    /**
     * @param referralEditFlag the referralEditFlag to set
     */
    public void setReferralEditFlag(String referralEditFlag)
    {
	this.referralEditFlag = referralEditFlag;
    }

  public Collection getSelectedReferrals()
    {
	return selectedReferrals;
    }

    public void setSelectedReferrals(Collection selectedReferrals)
    {
	this.selectedReferrals = selectedReferrals;
    }

    public String[] getSelectedRefToOverride()
    {
	return selectedRefToOverride;
    }

    public void setSelectedRefToOverride(String[] selectedRefToOverride)
    {
	this.selectedRefToOverride = selectedRefToOverride;
    }

    public Map <String, JuvenileProfileReferralListResponseEvent> getReferralListMap()
    {
	return referralListMap;
    }

    public void setReferralListMap(Map <String, JuvenileProfileReferralListResponseEvent> referralListMap)
    {
	this.referralListMap = referralListMap;
    }

    
    /**
     * @return the activateSupervision
     */
    public String getActivateSupervision()
    {
	return activateSupervision;
    }

    /**
     * @param activateSupervision the activateSupervision to set
     */
    public void setActivateSupervision(String activateSupervision)
    {
	this.activateSupervision = activateSupervision;
    }

    /**
     * @return the currentCasefileId
     */
    public String getCurrentCasefileId()
    {
	return currentCasefileId;
    }

    /**
     * @param currentCasefileId the currentCasefileId to set
     */
    public void setCurrentCasefileId(String currentCasefileId)
    {
	this.currentCasefileId = currentCasefileId;
    }

    /**
     * @return the updateOffenseFlag
     */
    public String getUpdateOffenseFlag()
    {
	return updateOffenseFlag;
    }

    /**
     * @param updateOffenseFlag the updateOffenseFlag to set
     */
    public void setUpdateOffenseFlag(String updateOffenseFlag)
    {
	this.updateOffenseFlag = updateOffenseFlag;
    }

    /**
     * @return the updateRefStat
     */
    public String getUpdateRefStat()
    {
	return updateRefStat;
    }

    /**
     * @param updateRefStat the updateRefStat to set
     */
    public void setUpdateRefStat(String updateRefStat)
    {
	this.updateRefStat = updateRefStat;
    }

 
    /**
     * @return the offenseId
     */
    public String getOffenseId()
    {
	return offenseId;
    }

    /**
     * @param offenseId the offenseId to set
     */
    public void setOffenseId(String offenseId)
    {
	this.offenseId = offenseId;
    }

    /**
     * @return the updateAsmntTypeFlag
     */
    public String getUpdateAsmntTypeFlag()
    {
	return updateAsmntTypeFlag;
    }

    /**
     * @param updateAsmntTypeFlag the updateAsmntTypeFlag to set
     */
    public void setUpdateAsmntTypeFlag(String updateAsmntTypeFlag)
    {
	this.updateAsmntTypeFlag = updateAsmntTypeFlag;
    }

    /**
     * @return the probationStartDate
     */
    public String getProbationStartDate()
    {
	return probationStartDate;
    }

    /**
     * @param probationStartDate the probationStartDate to set
     */
    public void setProbationStartDate(String probationStartDate)
    {
	this.probationStartDate = probationStartDate;
    }

    /**
     * @return the probationEndDate
     */
    public String getProbationEndDate()
    {
	return probationEndDate;
    }

    /**
     * @param probationEndDate the probationEndDate to set
     */
    public void setProbationEndDate(String probationEndDate)
    {
	this.probationEndDate = probationEndDate;
    }

  
    /**
     * @return the lcUser
     */
    public String getLcUser()
    {
	return lcUser;
    }

    /**
     * @param lcUser the lcUser to set
     */
    public void setLcUser(String lcUser)
    {
	this.lcUser = lcUser;
    }

    /**
     * @return the lcUserName
     */
    public String getLcUserName()
    {
	return lcUserName;
    }

    /**
     * @param lcUserName the lcUserName to set
     */
    public void setLcUserName(String lcUserName)
    {
	this.lcUserName = lcUserName;
    }
   
    /**
     * @return the earliestOffenseDate
     */
    public String getEarliestOffenseDate()
    {
	return earliestOffenseDate;
    }

    /**
     * @param earliestOffenseDate the earliestOffenseDate to set
     */
    public void setEarliestOffenseDate(String earliestOffenseDate)
    {
	this.earliestOffenseDate = earliestOffenseDate;
    }

    /**
     * @return the tjjdDateStr
     */
    public String getTjjdDateStr()
    {
	return tjjdDateStr;
    }

    /**
     * @param tjjdDateStr the tjjdDateStr to set
     */
    public void setTjjdDateStr(String tjjdDateStr)
    {
	this.tjjdDateStr = tjjdDateStr;
    }

    /**
     * @return the jotInfo
     */
    public JOTTO getJotInfo()
    {
	return jotInfo;
    }

    /**
     * @param jotInfo the jotInfo to set
     */
    public void setJotInfo(JOTTO jotInfo)
    {
	this.jotInfo = jotInfo;
    }

    /**
     * @return the updateRefFlag
     */
    public String getUpdateRefFlag()
    {
	return updateRefFlag;
    }

    /**
     * @param updateRefFlag the updateRefFlag to set
     */
    public void setUpdateRefFlag(String updateRefFlag)
    {
	this.updateRefFlag = updateRefFlag;
    }

    /**
     * @return the updateRefStatFlag
     */
    public String getUpdateRefStatFlag()
    {
	return updateRefStatFlag;
    }

    /**
     * @param updateRefStatFlag the updateRefStatFlag to set
     */
    public void setUpdateRefStatFlag(String updateRefStatFlag)
    {
	this.updateRefStatFlag = updateRefStatFlag;
    }

    /**
     * @return the loadAssmntFlag
     */
    public String getLoadAssmntFlag()
    {
	return loadAssmntFlag;
    }

    /**
     * @param loadAssmntFlag the loadAssmntFlag to set
     */
    public void setLoadAssmntFlag(String loadAssmntFlag)
    {
	this.loadAssmntFlag = loadAssmntFlag;
    }

    /**
     * @return the nextOffenseSeqNum
     */
    public String getNextOffenseSeqNum()
    {
	return nextOffenseSeqNum;
    }

    /**
     * @param nextOffenseSeqNum the nextOffenseSeqNum to set
     */
    public void setNextOffenseSeqNum(String nextOffenseSeqNum)
    {
	this.nextOffenseSeqNum = nextOffenseSeqNum;
    }

    /**
     * @return the updateRefStatFeature
     */
    public String getUpdateRefStatFeature()
    {
	return updateRefStatFeature;
    }

    /**
     * @param updateRefStatFeature the updateRefStatFeature to set
     */
    public void setUpdateRefStatFeature(String updateRefStatFeature)
    {
	this.updateRefStatFeature = updateRefStatFeature;
    }

    /**
     * @return the updateRefCloseDt
     */
    public String getUpdateRefCloseDt()
    {
	return updateRefCloseDt;
    }

    /**
     * @param updateRefCloseDt the updateRefCloseDt to set
     */
    public void setUpdateRefCloseDt(String updateRefCloseDt)
    {
	this.updateRefCloseDt = updateRefCloseDt;
    }

    /**
     * @return the updateMessage
     */
    public String getUpdateMessage()
    {
	return updateMessage;
    }

    /**
     * @param updateMessage the updateMessage to set
     */
    public void setUpdateMessage(String updateMessage)
    {
	this.updateMessage = updateMessage;
    }

    /**
     * @return the updateAction
     */
    public String getUpdateAction()
    {
	return updateAction;
    }

    /**
     * @param updateAction the updateAction to set
     */
    public void setUpdateAction(String updateAction)
    {
	this.updateAction = updateAction;
    }

    public String getControllingRef()
    {
	return controllingRef;
    }

    public void setControllingRef(String controllingRef)
    {
	this.controllingRef = controllingRef;
    }

    public String getJpoName()
    {
	return jpoName;
    }

    public void setJpoName(String jpoName)
    {
	this.jpoName = jpoName;
    }

    public String getSelectedOffense()
    {
	return selectedOffense;
    }

    public void setSelectedOffense(String selectedOffense)
    {
	this.selectedOffense = selectedOffense;
    }

    public String getOfficerFirstName()
    {
	return officerFirstName;
    }

    public void setOfficerFirstName(String officerFirstName)
    {
	this.officerFirstName = officerFirstName;
    }

    public String getOfficerLastName()
    {
	return officerLastName;
    }

    public void setOfficerLastName(String officerLastName)
    {
	this.officerLastName = officerLastName;
    }

    public String getOfficerMiddleName()
    {
	return officerMiddleName;
    }

    public void setOfficerMiddleName(String officerMiddleName)
    {
	this.officerMiddleName = officerMiddleName;
    }

    public String getOfficerSearchType()
    {
	return officerSearchType;
    }

    public void setOfficerSearchType(String officerSearchType)
    {
	this.officerSearchType = officerSearchType;
    }

    public String getUpdNewRefIntakeDecision()
    {
	return updNewRefIntakeDecision;
    }

    public void setUpdNewRefIntakeDecision(String updNewRefIntakeDecision)
    {
	this.updNewRefIntakeDecision = updNewRefIntakeDecision;
    }

    public int getAgeAtOffense()
    {
	return ageAtOffense;
    }

    public void setAgeAtOffense(int ageAtOffense)
    {
	this.ageAtOffense = ageAtOffense;
    }


    public String getIsTransferredOffense()
    {
	return isTransferredOffense;
    }

    public void setIsTransferredOffense(String isTransferredOffense)
    {
	this.isTransferredOffense = isTransferredOffense;
    }

    public String getIsNumericCode23()
    {
	return isNumericCode23;
    }

    public void setIsNumericCode23(String isNumericCode23)
    {
	this.isNumericCode23 = isNumericCode23;
    }

    public List<String> getOffenseCodeList()
    {
	return offenseCodeList;
    }

    public void setOffenseCodeList(List<String> offenseCodeList)
    {
	this.offenseCodeList = offenseCodeList;
    }

    public String getUpdateOffenseMsg()
    {
	return updateOffenseMsg;
    }

    public void setUpdateOffenseMsg(String updateOffenseMsg)
    {
	this.updateOffenseMsg = updateOffenseMsg;
    }

    public String getUpdateOffenseError()
    {
	return updateOffenseError;
    }

    public void setUpdateOffenseError(String updateOffenseError)
    {
	this.updateOffenseError = updateOffenseError;
    }

    public String getSubsequentMessage()
    {
	return subsequentMessage;
    }

    public void setSubsequentMessage(String subsequentMessage)
    {
	this.subsequentMessage = subsequentMessage;
    }

    public String getOriginalRaceId()
    {
	return originalRaceId;
    }

    public void setOriginalRaceId(String originalRaceId)
    {
	this.originalRaceId = originalRaceId;
    }

    public String getSSN1()
    {
        return SSN1;
    }

    public void setSSN1(String sSN1)
    {
        SSN1 = sSN1;
    }

    public String getSSN2()
    {
        return SSN2;
    }

    public void setSSN2(String sSN2)
    {
        SSN2 = sSN2;
    }

    public String getSSN3()
    {
        return SSN3;
    }

    public void setSSN3(String sSN3)
    {
        SSN3 = sSN3;
    }

    public String getAdminReferralFlag()
    {
        return adminReferralFlag;
    }

    public void setAdminReferralFlag(String adminReferralFlag)
    {
        this.adminReferralFlag = adminReferralFlag;
    }

    public String getMaxCrimReferral()
    {
        return maxCrimReferral;
    }

    public void setMaxCrimReferral(String maxCrimReferral)
    {
        this.maxCrimReferral = maxCrimReferral;
    }

    public Collection<JuvenileProfileReferralListResponseEvent> getOriginalChargeReferrals()
    {
        return originalChargeReferrals;
    }

    public void setOriginalChargeReferrals(Collection<JuvenileProfileReferralListResponseEvent> originalChargeReferrals)
    {
        this.originalChargeReferrals = originalChargeReferrals;
    }

    public String getProbationStartDateStr()
    {
        return probationStartDateStr;
    }

    public void setProbationStartDateStr(String probationStartDateStr)
    {
        this.probationStartDateStr = probationStartDateStr;
    }

    public String getProbationEndDateStr()
    {
        return probationEndDateStr;
    }

    public void setProbationEndDateStr(String probationEndDateStr)
    {
        this.probationEndDateStr = probationEndDateStr;
    }

    public boolean isUpdateSSN()
    {
        return updateSSN;
    }

    public void setUpdateSSN(boolean updateSSN)
    {
        this.updateSSN = updateSSN;
    }

    public int getRefExcludedReporting()
    {
        return refExcludedReporting;
    }

    public void setRefExcludedReporting(int refExcludedReporting)
    {
        this.refExcludedReporting = refExcludedReporting;
    }

    public int getJuvExcludedReporting()
    {
        return juvExcludedReporting;
    }

    public void setJuvExcludedReporting(int juvExcludedReporting)
    {
        this.juvExcludedReporting = juvExcludedReporting;
    }

    public String getDaLogNum()
    {
        return daLogNum;
    }

    public void setDaLogNum(String daLogNum)
    {
        this.daLogNum = daLogNum;
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

    public String getCJIS()
    {
        return CJIS;
    }

    public void setCJIS(String cJIS)
    {
        CJIS = cJIS;
    }

    public Collection<JuvenileCasefileOffenseCodeResponseEvent> getOffenseCodes()
    {
        return offenseCodes;
    }

    public void setOffenseCodes(Collection<JuvenileCasefileOffenseCodeResponseEvent> offenseCodes)
    {
        this.offenseCodes = offenseCodes;
    }

    public boolean isDisableAddRefBtn()
    {
        return disableAddRefBtn;
    }

    public void setDisableAddRefBtn(boolean disableAddRefBtn)
    {
        this.disableAddRefBtn = disableAddRefBtn;
    }

    public List<JJSSVIntakeHistory> getIntakeHistoryList()
    {
        return intakeHistoryList;
    }

    public void setIntakeHistoryList(List<JJSSVIntakeHistory> intakeHistoryList)
    {
        this.intakeHistoryList = intakeHistoryList;
    }
    
    public boolean getIsRealDOBUpdateAllowed()
	{
	        return this.isRealDOBUpdateAllowed;
	}

    public void setIsRealDOBUpdateAllowed(boolean hasFeature)
	{
	        this.isRealDOBUpdateAllowed = hasFeature;
	}	    
    public String getCourtdecisionDate()
    {
        return courtdecisionDate;
    }

    public void setCourtdecisionDate(String courtdecisionDate)
    {
        this.courtdecisionDate = courtdecisionDate;
    }
    
}
