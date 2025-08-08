/*
 * Created on Apr 25, 2005
 */
package messaging.juvenilecase.reply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * Returns a thinly populated version of a casefile referral for displaying
 * only needed data required data.  
 * 
 * @author glyons
 */
public class JuvenileCasefileReferralsResponseEvent extends ResponseEvent implements Comparable
{
	private String referralNumber; // looks like: "1010"
	private String referralNumberWithSeverity = null ; // looks like: "1010 - F2"

	private String casefileId;
	private String assignmentLevel;
	private Date assignmentDate;	
	private String serviceUnit;
	private String assignmentId;
	private Date referralDate;
	private String referralDateString;
	private Date probationEndDate;  // Task 36529
	private String probationEndDateString; // Task 36529
	private String probationOfficerId;
	//added for task 126756
	private Date probationstartDate;
	private Date probationendDate;
	
	
	//Fields added for Assigned Referrals
	private Date courtDate;
	private String courtDateString;
	private String intakeDecision;
	private String intakeDecisionCode;
	private String intakeDecisionTJPCCode;
	private String courtId;
	private boolean referralFound;
	private String finalDisposition;
	private String finalDispositionDescription;
	private String finalDispositionTJPCCode;
	private String referralTypeInd;
	//ER changes:11054
	private String referralTypeDesc;
	private boolean offensesAvailable;
	private boolean petitionsAvailable; 
	private String dispositionSubgroup;
	private String pdaDate;
	private String intakeDate;   

    

    private Collection offenses;
    private Collection petitions;
    private Collection<JuvenileOffenseCodeResponseEvent> offenseCodes = new ArrayList<JuvenileOffenseCodeResponseEvent>();
  
    private Collection offenseResponseEvents;
  
    private long offenseCollectionSize;
    private long petitionCollectionSize;

    //added for US 11109
    private String referralNumberWithPetition;
    
    //added for US 41483
    private String riskNeedLvl;
    private String riskNeedLvlDesc;
    private String lastPactDate;
    private String refCloseDate;
    private String pactStatus;
    
    //US 71184
    private String supervisionCategoryId;
    private String supervisionType;
    private String supervisionTypeId;
    private String supervisionCategory;
    private String supervisioncategoryPact;
    

    private String specialCategoryCd;
    
    private String jpoId;
    private String jpo;
    private String courtResult;
    private String courtResultDesc;
    private JuvenileDispositionCodeResponseEvent juvDispositionCode;
    private String petitionNumber;
    private String petitionStatus;
    private String petitionStatusDescr;
    
    //Bug #80120
    private String petitionAllegation;
    private String petitionAllegDscr;
    
    //Bug #91993
    private String refSeqNum;
    private String TJJDCode; 
    private String dispositionCode;
    private String offense; 
    private String offenseDesc;	
    private String recType;

	/**
	 * @return Returns the referralDate.
	 */
	public Date getReferralDate() {
		return referralDate;
	}
	
	/**
	 * @return the referralDateString
	 */
	public String getReferralDateString() {
		return referralDateString;
	}
	
	/**
	 * @return Returns the courtDate.
	 */
	public Date getCourtDate() {
		return courtDate;
	}
	
	/**
	 * @return the courtDateString
	 */
	public String getCourtDateString() {
		return courtDateString;
	}
	
	/**
	 * @return Returns the courtId.
	 */
	public String getCourtId() {
		return courtId;
	}
	
	/**
	 * @return String
	 */
	public String getCasefileId()
	{
		return casefileId;
	}
	
	/**
	 * @param aCasefileId
	 */
	public void setCasefileId(String aCasefileId)
	{
		casefileId = aCasefileId;
	}
	
	/**
	 * @return String
	 */
	public String getAssignmentId()
	{
		return assignmentId;
	}
	
	/**
	 * @param aAssignmentId
	 */
	public void setAssignmentId(String aAssignmentId)
	{
		assignmentId = aAssignmentId;
	}
	
	/**
	 * @return assignmentDate
	 */
	public Date getAssignmentDate()
	{
		return assignmentDate;
	}

	/**
	 * @return asssignmentLevel
	 */
	public String getAssignmentLevel()
	{
		return assignmentLevel;
	}
	
	/**
	 * @return referralNumber
	 */
	public String getReferralNumber()
	{
		return referralNumber;
	}

	/**
	 * @return serviceUnit
	 */
	public String getServiceUnit()
	{
		return serviceUnit;
	}

	/**
	 * @param assignmentDate
	 */
	public void setAssignmentDate(Date aAssignmentDate)
	{
		assignmentDate = aAssignmentDate;
	}

	/**
	 * @param assignmentLevel
	 */
	public void setAssignmentLevel(String aAssignmentLevel)
	{
		assignmentLevel = aAssignmentLevel;
	}
	
	/**
	 * @param string
	 */
	public void setReferralNumber(String aReferralNumber)
	{
		referralNumber = aReferralNumber;
	}

	/**
	 * @param serviceUnit
	 */
	public void setServiceUnit(String aServiceUnit)
	{
		serviceUnit = aServiceUnit;
	}
	
	/**
	 * @param referralDate The referralDate to set.
	 */
	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}

	/**
	 * @param referralDateString the referralDateString to set
	 */
	public void setReferralDateString(String referralDateString) {
		this.referralDateString = referralDateString;
	}
	
	/**
	 * @param courtDate The courtDate to set.
	 */
	public void setCourtDate(Date courtDate) {
		this.courtDate = courtDate;
	}

	/**
	 * @param courtDateString the courtDateString to set
	 */
	public void setCourtDateString(String courtDateString) {
		this.courtDateString = courtDateString;
	}
	
	/**
	 * @param courtId The courtId to set.
	 */
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
	
	/**
	 * @return Returns the intakeDecision.
	 */
	public String getIntakeDecision() {
		return intakeDecision;
	}
	
	/**
	 * @param intakeDecision The intakeDecision to set.
	 */
	public void setIntakeDecision(String intakeDecision) {
		this.intakeDecision = intakeDecision;
	}
	
	/**
	 * @return Returns the referralFound.
	 */
	public boolean isReferralFound() {
		return referralFound;
	}
	
	/**
	 * @param referralFound The referralFound to set.
	 */
	public void setReferralFound(boolean referralFound) {
		this.referralFound = referralFound;
	}
		
	/**
	 * @return finalDisposition
	 */
	public String getFinalDisposition() {
		return finalDisposition;
	}
	
	/**
	 * @param finalDisposition
	 */
	public void setFinalDisposition(String finalDisposition) {
		this.finalDisposition = finalDisposition;
	}
	
	/**
	 * @return finalDispositionDescription
	 */
	public String getFinalDispositionDescription() {
		return finalDispositionDescription;
	}
	
	/**
	 * @param finalDispositionDescription
	 */
	public void setFinalDispositionDescription(String finalDispositionDescription) {
		this.finalDispositionDescription = finalDispositionDescription;
	}
	
	
	
	/**
	 * @return the referralTypeInd
	 */
	public String getReferralTypeInd() {
		return referralTypeInd;
	}
	/**
	 * @param referralTypeInd the referralTypeInd to set
	 */
	public void setReferralTypeInd(String referralTypeInd) {
		this.referralTypeInd = referralTypeInd;
	}
	/**
	 * @return petitionsAvailable
	 */
	public boolean isPetitionsAvailable() {
		return petitionsAvailable;
	}
	
	/**
	 * @param petitionsAvailable
	 */
	public void setPetitionsAvailable(boolean petitionsAvailable) {
		this.petitionsAvailable = petitionsAvailable;
	}
	
	/**
	 * @return offenses
	 */
	public Collection getOffenses() {
		return offenses;
	}
	
	/**
	 * @param offenses
	 */
	public void setOffenses(Collection offenses) {
		this.offenses = offenses;
	}
	
	/**
	 * @return petitions
	 */
	public Collection getPetitions() {
		return petitions;
	}
	
	/**
	 * @param petitions
	 */
	public void setPetitions(Collection petitions) {
		this.petitions = petitions;
	}
	
	/**
	 * @return offensesAvailable
	 */
	public boolean isOffensesAvailable() {
		return offensesAvailable;
	}
	
	/**
	 * @param offensesAvailable
	 */
	public void setOffensesAvailable(boolean offensesAvailable) {
		this.offensesAvailable = offensesAvailable;
	}
	
	/**
	 * @return offenseCollectionSize
	 */
	public long getOffenseCollectionSize() {
		
		if (isOffensesAvailable()) {
			setOffenseCollectionSize(offenses.size() - 1);
		} else {
			setOffenseCollectionSize(0);
		}
		
		return offenseCollectionSize;
	}
	
	/**
	 * @param offenseCollectionSize
	 */
	public void setOffenseCollectionSize(long offenseCollectionSize) {
		this.offenseCollectionSize = offenseCollectionSize;
	}
	
	/**
	 * @return petitionCollectionSize
	 */
	public long getPetitionCollectionSize() {
		
		if (isPetitionsAvailable()) {
			setPetitionCollectionSize(petitions.size() - 1);
		} else {
			setPetitionCollectionSize(0);
		}
		
		return petitionCollectionSize;
	}
	
	/**
	 * @param petitionCollectionSize
	 */
	public void setPetitionCollectionSize(long petitionCollectionSize) {
		this.petitionCollectionSize = petitionCollectionSize;
	}	
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj)
	{
		if(obj==null)
			return -1;
		JuvenileCasefileReferralsResponseEvent evt = (JuvenileCasefileReferralsResponseEvent) obj;
		return referralNumber.compareToIgnoreCase(evt.getReferralNumber());		
	}

	/*
	 * 
	 */
	public String getReferralNumberWithSeverity()
	{
		return referralNumberWithSeverity;
	}

	/*
	 * 
	 */
	public void setReferralNumberWithSeverity( String referralNumberWithSeverity )
	{
		this.referralNumberWithSeverity = referralNumberWithSeverity;
	}
	
	/**
	 * @param offenseResponseEvents
	 */
	public void setOffenseResponseEvents(Collection offenseResponseEvents) {
		this.offenseResponseEvents = offenseResponseEvents;
	}
	
	/**
	 * @return offenseResponseEvents
	 */
	public Collection getOffenseResponseEvents() {
		return offenseResponseEvents;
	}
	public void setIntakeDecisionCode(String intakeDecisionCode) {
		this.intakeDecisionCode = intakeDecisionCode;
	}
	public String getIntakeDecisionCode() {
		return intakeDecisionCode;
	}
	public String getIntakeDecisionTJPCCode() {
		return intakeDecisionTJPCCode;
	}
	public void setIntakeDecisionTJPCCode(String intakeDecisionTJPCCode) {
		this.intakeDecisionTJPCCode = intakeDecisionTJPCCode;
	}
	public String getFinalDispositionTJPCCode() {
		return finalDispositionTJPCCode;
	}
	public void setFinalDispositionTJPCCode(String finalDispositionTJPCCode) {
		this.finalDispositionTJPCCode = finalDispositionTJPCCode;
	}

	/**
	 * @param referralTypeDesc the referralTypeDesc to set
	 */
	public void setReferralTypeDesc(String referralTypeDesc) {
		this.referralTypeDesc = referralTypeDesc;
	}

	/**
	 * @return the referralTypeDesc
	 */
	public String getReferralTypeDesc() {
		return referralTypeDesc;
	}

	public String getReferralNumberWithPetition() {
		return referralNumberWithPetition;
	}

	public void setReferralNumberWithPetition(String referralNumberWithPetition) {
		this.referralNumberWithPetition = referralNumberWithPetition;
	}

	public Date getProbationEndDate() {
		return probationEndDate;
	}

	public void setProbationEndDate(Date probationEndDate) {
		this.probationEndDate = probationEndDate;
	}

	public String getProbationEndDateString() {
		return probationEndDateString;
	}

	public void setProbationEndDateString(String probationEndDateString) {
		this.probationEndDateString = probationEndDateString;
	}

	/**
	 * @return the riskNeedLvl
	 */
	public String getRiskNeedLvl() {
		return riskNeedLvl;
	}

	/**
	 * @param riskNeedLvl the riskNeedLvl to set
	 */
	public void setRiskNeedLvl(String riskNeedLvl) {
		this.riskNeedLvl = riskNeedLvl;
	}

	/**
	 * @return the riskNeedLvlDesc
	 */
	public String getRiskNeedLvlDesc() {
		return riskNeedLvlDesc;
	}

	/**
	 * @param riskNeedLvlDesc the riskNeedLvlDesc to set
	 */
	public void setRiskNeedLvlDesc(String riskNeedLvlDesc) {
		this.riskNeedLvlDesc = riskNeedLvlDesc;
	}

	/**
	 * @return the lastPactDate
	 */
	public String getLastPactDate() {
		return lastPactDate;
	}

	/**
	 * @param lastPactDate the lastPactDate to set
	 */
	public void setLastPactDate(String lastPactDate) {
		this.lastPactDate = lastPactDate;
	}

	/**
	 * @return the refCloseDate
	 */
	public String getRefCloseDate() {
		return refCloseDate;
	}

	/**
	 * @param refCloseDate the refCloseDate to set
	 */
	public void setRefCloseDate(String refCloseDate) {
		this.refCloseDate = refCloseDate;
	}

	/**
	 * @return the pactStatus
	 */
	public String getPactStatus() {
		return pactStatus;
	}

	/**
	 * @param pactStatus the pactStatus to set
	 */
	public void setPactStatus(String pactStatus) {
		this.pactStatus = pactStatus;
	}

	/**
	 * @return the probationOfficerId
	 */
	public String getProbationOfficerId() {
		return probationOfficerId;
	}

	/**
	 * @param probationOfficerId the probationOfficerId to set
	 */
	public void setProbationOfficerId(String probationOfficerId) {
		this.probationOfficerId = probationOfficerId;
	}

	/**
	 * @return the supervisionCategoryId
	 */
	public String getSupervisionCategoryId()
	{
	    return supervisionCategoryId;
	}

	/**
	 * @param supervisionCategoryId the supervisionCategoryId to set
	 */
	public void setSupervisionCategoryId(String supervisionCategoryId)
	{
	    this.supervisionCategoryId = supervisionCategoryId;
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
	 * @return the supervisionTypeId
	 */
	public String getSupervisionTypeId()
	{
	    return supervisionTypeId;
	}

	/**
	 * @param supervisionTypeId the supervisionTypeId to set
	 */
	public void setSupervisionTypeId(String supervisionTypeId)
	{
	    this.supervisionTypeId = supervisionTypeId;
	}

	/**
	 * @return the supervisionCategory
	 */
	public String getSupervisionCategory()
	{
	    return supervisionCategory;
	}

	/**
	 * @param supervisionCategory the supervisionCategory to set
	 */
	public void setSupervisionCategory(String supervisionCategory)
	{
	    this.supervisionCategory = supervisionCategory;
	}

	/**
	 * @return the jpoId
	 */
	public String getJpoId()
	{
	    return jpoId;
	}

	/**
	 * @param jpoId the jpoId to set
	 */
	public void setJpoId(String jpoId)
	{
	    this.jpoId = jpoId;
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
	 * @return the courtResult
	 */
	public String getCourtResult()
	{
	    return courtResult;
	}

	/**
	 * @param courtResult the courtResult to set
	 */
	public void setCourtResult(String courtResult)
	{
	    this.courtResult = courtResult;
	}

	/**
	 * @return the courtResultDesc
	 */
	public String getCourtResultDesc()
	{
	    return courtResultDesc;
	}

	/**
	 * @param courtResultDesc the courtResultDesc to set
	 */
	public void setCourtResultDesc(String courtResultDesc)
	{
	    this.courtResultDesc = courtResultDesc;
	}

	/**
	 * @return the juvDispositionCode
	 */
	public JuvenileDispositionCodeResponseEvent getJuvDispositionCode()
	{
	    return juvDispositionCode;
	}

	/**
	 * @param juvDispositionCode the juvDispositionCode to set
	 */
	public void setJuvDispositionCode(JuvenileDispositionCodeResponseEvent juvDispositionCode)
	{
	    this.juvDispositionCode = juvDispositionCode;
	}

	/**
	 * @return the petitionNumber
	 */
	public String getPetitionNumber()
	{
	    return petitionNumber;
	}

	/**
	 * @param petitionNumber the petitionNumber to set
	 */
	public void setPetitionNumber(String petitionNumber)
	{
	    this.petitionNumber = petitionNumber;
	}

	/**
	 * @return the petitionStatus
	 */
	public String getPetitionStatus()
	{
	    return petitionStatus;
	}

	/**
	 * @param petitionStatus the petitionStatus to set
	 */
	public void setPetitionStatus(String petitionStatus)
	{
	    this.petitionStatus = petitionStatus;
	}

	/**
	 * @return the petitionStatusDescr
	 */
	public String getPetitionStatusDescr()
	{
	    return petitionStatusDescr;
	}

	/**
	 * @param petitionStatusDescr the petitionStatusDescr to set
	 */
	public void setPetitionStatusDescr(String petitionStatusDescr)
	{
	    this.petitionStatusDescr = petitionStatusDescr;
	}

	/**
	 * @return the petitionAllegation
	 */
	public String getPetitionAllegation()
	{
	    return petitionAllegation;
	}

	/**
	 * @param petitionAllegation the petitionAllegation to set
	 */
	public void setPetitionAllegation(String petitionAllegation)
	{
	    this.petitionAllegation = petitionAllegation;
	}

	/**
	 * @return the petitionAllegDscr
	 */
	public String getPetitionAllegDscr()
	{
	    return petitionAllegDscr;
	}

	/**
	 * @param petitionAllegDscr the petitionAllegDscr to set
	 */
	public void setPetitionAllegDscr(String petitionAllegDscr)
	{
	    this.petitionAllegDscr = petitionAllegDscr;
	}

	public String getRefSeqNum()
	{
	    return refSeqNum;
	}

	public void setRefSeqNum(String refSeqNum)
	{
	    this.refSeqNum = refSeqNum;
	}	
	
	public String getSpecialCategoryCd()
	{
	    return specialCategoryCd;
	}

	public void setSpecialCatagoryCd(String specialCatagoryCd)
	{
	    this.specialCategoryCd = specialCatagoryCd;
	}
	public String getDispositionSubgroup()
	{
	    return dispositionSubgroup;
	}

	public void setDispositionSubgroup(String dispositionSubgroup)
	{
	    this.dispositionSubgroup = dispositionSubgroup;
	}
	 public String getPdaDate()
	{
	   return pdaDate;
	}

	public void setPdaDate(String pdaDate)
	{
	   this.pdaDate = pdaDate;
	}
	public String getTJJDCode()
	{
	    return TJJDCode;
	}

	public void setTJJDCode(String tJJDCode)
	{
	    TJJDCode = tJJDCode;
	}
	public String getIntakeDate()
	{
	    return intakeDate;
	}

	public void setIntakeDate(String intakeDate)
	{
	    this.intakeDate = intakeDate;
	}
	
	public String getDispositionCode()
	    {
	        return dispositionCode;
	    }

	public void setDispositionCode(String dispositionCode)
	    {
	        this.dispositionCode = dispositionCode;
	    }
	
	public String getSupervisioncategoryPact()
	    {
	        return supervisioncategoryPact;
	    }

	public void setSupervisioncategoryPact(String supervisioncategoryPact)
	    {
	        this.supervisioncategoryPact = supervisioncategoryPact;
	    }
	
	public Date getProbationstartDate()
		{
		    return probationstartDate;
		}

	public void setProbationstartDate(Date probationstartDate)
		{
		    this.probationstartDate = probationstartDate;
		}
	
	public Date getProbationendDate()
		{
		    return probationendDate;
		}

	public void setProbationendDate(Date probationendDate)
	{
	    this.probationendDate = probationendDate;	
	}

	public String getOffense()
	{
	    return offense;
	}

	public void setOffense(String offense)
	{
	    this.offense = offense;
	}
	
	public String getOffenseDesc()
	{
	    return offenseDesc;
	}

	public void setOffenseDesc(String offenseDesc)
	{
	    this.offenseDesc = offenseDesc;
	}

	public Collection<JuvenileOffenseCodeResponseEvent> getOffenseCodes()
	{
	    return offenseCodes;
	}

	public void setOffenseCodes(Collection<JuvenileOffenseCodeResponseEvent> offenseCodes)
	{
	    this.offenseCodes = offenseCodes;
	}

	public String getRecType()
	{
	    return recType;
	}

	public void setRecType(String recType)
	{
	    this.recType = recType;
	}

	
	
	
		    
	
}