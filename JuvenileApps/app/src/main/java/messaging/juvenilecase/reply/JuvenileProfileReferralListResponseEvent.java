/*
 * Created on Sep 20, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import mojo.km.messaging.ResponseEvent;
import pd.juvenilecase.JuvenileCasefileReferral;

/**
 * @author ryoung To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileProfileReferralListResponseEvent extends ResponseEvent implements Comparable
{
    private String referralNumber;
    private String referralOID;// do not change rry
    private String courtResult;
    private String courtDisposition;
    private String intakeDecision;
    private Date referralDate;

    private Date courtDate;
    private String courtId;
    private String transferredFrom;

    private boolean referralFound;
    private String finalDisposition;
    private String finalDispositionDescription;
    private String referralTypeInd;
    //ER changes:11054
    private String referralTypeDesc;
    private boolean offensesAvailable;
    private boolean petitionsAvailable;
    private Collection offenses;
    private Collection<JuvenileOffenseCodeResponseEvent> offenseCodes = new ArrayList<JuvenileOffenseCodeResponseEvent>();
    private Collection petitions;
    private long offenseCollectionSize;
    private long petitionCollectionSize;
    private Date closeDate;

    //userstory 11030 changes
    private String tjpcCode;
    private Collection courts;
    private JuvenileDispositionCodeResponseEvent juvDispositionCode;
    //userstory 11030 changes 

    //added for Facility
    private JJSOffenseResponseEvent mostSevereOffense;
    private Collection<JuvenileCasefileReferral> casefileReferrals = new ArrayList<JuvenileCasefileReferral>();
    private Collection<JuvenileCasefileResponseEvent> casefiles = new ArrayList<JuvenileCasefileResponseEvent>();
    private Collection<JuvenileProfileReferralListResponseEvent> adminReferrals = new ArrayList<JuvenileProfileReferralListResponseEvent>();
    private boolean hasCasefiles;
    private boolean isEligableForBooking;
    private String petitionNumber;
    private String sequenceNum;
    private String piaStatus;
    private String referralSource;
    private String referralOfficer;
    private String referralSourceDesc;
    private String petitionAllegation;

    //added for Court, referral Inquiry
    private String offense;
    private String offenseDesc;
    private String jpo;
    private String jpoId;
    private String ctAssignJpoId;
    private String ctAssignLevel;
    private String supervisionCategory;
    private String supervisionCategoryId;
    private String supervisionType;
    private String supervisionTypeId;
    private String currentSupervision; //manage assignment.
    private String intakeDecisionId;
    private String courtResultDesc;
    private JuvenileDispositionCodeResponseEvent courtResultDisposition;
    
    //number of days detained
    private long detDays;
    
    //added for referral - Override assignment
    private String offenseCategory;
    private String intakeDecDate;
    
    private String offenseSeverity; //manage assignment
    private String offenseInvestNum;
    private Date offenseDate;
    
    //US 71184
    private String petitionStatus;
    private String petitionStatusDescr;
    
    //Bug #80120
    private String petitionAllegationDescr;
    private String petitionallegationCategory;
    private String petitionAllegationDescrExtended;
   

    //US 71177
    private String referralStatus;
    private String TJJDDate;
    private String recType;
    private String assignmentDate;
    private String assignmentType;
    private String probationJPOId;
    private String probationLevel;
    private Date probationStartDate;
    private Date probationEndDate;
    private String daLogNum;
    private String lcUser;
    private String caseStatus;
    private String casefileId;
    
    private String probationStartDateStr;
    private String probationEndDateStr;
    private String controllingReferralId;
    private String refSeqNum;
    private String fund;
    
    private String daStatus;
    private Date daDateOut;
    private String inactiveInd;
    private String severitySubtype;
    private String ncicCode;
    private String terminationDate;
    private String cjisNum;
    private String tjpcDisp;
    private Date dispositionDate;
    private String juvNum;   
    private Date pdaDate;

    

    /**
     * @return
     */
    public String getCourtDisposition()
    {
	return courtDisposition;
    }

    /**
     * @return
     */
    public String getCourtResult()
    {
	return courtResult;
    }

    /**
     * @return
     */
    public String getIntakeDecision()
    {
	return intakeDecision;
    }

    /**
     * @return
     */
    public Date getReferralDate()
    {
	return referralDate;
    }

    /**
     * @return
     */
    public String getReferralNumber()
    {
	return referralNumber;
    }

    
    public String getReferralOID()
    {
        return referralOID;
    }

    public void setReferralOID(String referralOID)
    {
        this.referralOID = referralOID;
    }

    /**
     * @param string
     */
    public void setCourtDisposition(String string)
    {
	courtDisposition = string;
    }

    /**
     * @param string
     */
    public void setCourtResult(String string)
    {
	courtResult = string;
    }

    /**
     * @param string
     */
    public void setIntakeDecision(String string)
    {
	intakeDecision = string;
    }

    /**
     * @param date
     */
    public void setReferralDate(Date date)
    {
	referralDate = date;
    }

    /**
     * @param string
     */
    public void setReferralNumber(String string)
    {
	referralNumber = string;
    }

    /**
     * @return Returns the courtDate.
     */
    public Date getCourtDate()
    {
	return courtDate;
    }

    /**
     * @param courtDate
     *            The courtDate to set.
     */
    public void setCourtDate(Date courtDate)
    {
	this.courtDate = courtDate;
    }

    /**
     * @return Returns the courtId.
     */
    public String getCourtId()
    {
	return courtId;
    }

    /**
     * @param courtId
     *            The courtId to set.
     */
    public void setCourtId(String courtId)
    {
	this.courtId = courtId;
    }
    
    public String getTransferredFrom()
    {
	return transferredFrom;
    }
    
    public void setTransferredFrom(String transferredFrom)
    {
	this.transferredFrom = transferredFrom;
    }

    /**
     * @return Returns the referralFound.
     */
    public boolean isReferralFound()
    {
	return referralFound;
    }

    /**
     * @param referralFound
     *            The referralFound to set.
     */
    public void setReferralFound(boolean referralFound)
    {
	this.referralFound = referralFound;
    }
    
    public boolean getIsEligableForBooking()
    {
	return isEligableForBooking;
    }
    
    public void setIsEligableForBooking(boolean isEligableForBooking)
    {
	this.isEligableForBooking = isEligableForBooking;
    }
    
    
    

    /**
     * @return finalDisposition
     */
    public String getFinalDisposition()
    {
	return finalDisposition;
    }

    /**
     * @param finalDisposition
     */
    public void setFinalDisposition(String finalDisposition)
    {
	this.finalDisposition = finalDisposition;
    }

    /**
     * @return finalDispositionDescription
     */
    public String getFinalDispositionDescription()
    {
	return finalDispositionDescription;
    }

    /**
     * @param finalDispositionDescription
     */
    public void setFinalDispositionDescription(String finalDispositionDescription)
    {
	this.finalDispositionDescription = finalDispositionDescription;
    }

    /**
     * @return the referralTypeInd
     */
    public String getReferralTypeInd()
    {
	return referralTypeInd;
    }

    /**
     * @param referralTypeInd
     *            the referralTypeInd to set
     */
    public void setReferralTypeInd(String referralTypeInd)
    {
	this.referralTypeInd = referralTypeInd;
    }

    /**
     * @return petitionsAvailable
     */
    public boolean isPetitionsAvailable()
    {
	return petitionsAvailable;
    }

    /**
     * @param petitionsAvailable
     */
    public void setPetitionsAvailable(boolean petitionsAvailable)
    {
	this.petitionsAvailable = petitionsAvailable;
    }

    /**
     * @return offenses
     */
    public Collection getOffenses()
    {
	return offenses;
    }

    /**
     * @param offenses
     */
    public void setOffenses(Collection offenses)
    {
	this.offenses = offenses;
    }

    /**
     * @return petitions
     */
    public Collection getPetitions()
    {
	return petitions;
    }

    /**
     * @param petitions
     */
    public void setPetitions(Collection petitions)
    {
	this.petitions = petitions;
    }

    /**
     * @return offensesAvailable
     */
    public boolean isOffensesAvailable()
    {
	return offensesAvailable;
    }

    /**
     * @param offensesAvailable
     */
    public void setOffensesAvailable(boolean offensesAvailable)
    {
	this.offensesAvailable = offensesAvailable;
    }

    /**
     * @return offenseCollectionSize
     */
    public long getOffenseCollectionSize()
    {

	if (isOffensesAvailable())
	{
	    setOffenseCollectionSize(offenses.size() - 1);
	}
	else
	{
	    setOffenseCollectionSize(0);
	}

	return offenseCollectionSize;
    }

    /**
     * @param offenseCollectionSize
     */
    public void setOffenseCollectionSize(long offenseCollectionSize)
    {
	this.offenseCollectionSize = offenseCollectionSize;
    }

    /**
     * @return petitionCollectionSize
     */
    public long getPetitionCollectionSize()
    {

	if (isPetitionsAvailable())
	{
	    setPetitionCollectionSize(petitions.size() - 1);
	}
	else
	{
	    setPetitionCollectionSize(0);
	}

	return petitionCollectionSize;
    }

    /**
     * @param petitionCollectionSize
     */
    public void setPetitionCollectionSize(long petitionCollectionSize)
    {
	this.petitionCollectionSize = petitionCollectionSize;
    }

    public void setMostSevereOffense(JJSOffenseResponseEvent mostSevereOffense)
    {
	this.mostSevereOffense = mostSevereOffense;
    }

    public JJSOffenseResponseEvent getMostSevereOffense()
    {
	return mostSevereOffense;
    }

    public void setHasCasefiles(boolean hasCasefiles)
    {
	this.hasCasefiles = hasCasefiles;
    }

    public boolean isHasCasefiles()
    {
	return hasCasefiles;
    }

    public void setPetitionNumber(String petitionNumber)
    {
	this.petitionNumber = petitionNumber;
    }

    public String getPetitionNumber()
    {
	return petitionNumber;
    }

    /**
     * @param referralTypeDesc
     *            the referralTypeDesc to set
     */
    public void setReferralTypeDesc(String referralTypeDesc)
    {
	this.referralTypeDesc = referralTypeDesc;
    }

    /**
     * @return the referralTypeDesc
     */
    public String getReferralTypeDesc()
    {
	return referralTypeDesc;
    }

    public void setPiaStatus(String piaStatus)
    {
	this.piaStatus = piaStatus;
    }

    public String getPiaStatus()
    {
	return piaStatus;
    }

    /**
     * @param referralSource
     *            the referralSource to set
     */
    public void setReferralSource(String referralSource)
    {
	this.referralSource = referralSource;
    }

    /**
     * @return the referralSource
     */
    public String getReferralSource()
    {
	return referralSource;
    }

    /**
     * @param referralOfficer
     *            the referralOfficer to set
     */
    public void setReferralOfficer(String referralOfficer)
    {
	this.referralOfficer = referralOfficer;
    }

    /**
     * @return the referralOfficer
     */
    public String getReferralOfficer()
    {
	return referralOfficer;
    }

    /**
     * @param referralSourceDesc
     *            the referralSourceDesc to set
     */
    public void setReferralSourceDesc(String referralSourceDesc)
    {
	this.referralSourceDesc = referralSourceDesc;
    }

    /**
     * @return the referralSourceDesc
     */
    public String getReferralSourceDesc()
    {
	return referralSourceDesc;
    }

    /**
     * @param tjpcCode
     *            the tjpcCode to set
     */
    public void setTjpcCode(String tjpcCode)
    {
	this.tjpcCode = tjpcCode;
    }

    /**
     * @return the tjpcCode
     */
    public String getTjpcCode()
    {
	return tjpcCode;
    }

    /**
     * @param courts
     *            the courts to set
     */
    public void setCourts(Collection courts)
    {
	this.courts = courts;
    }

    /**
     * @return the courts
     */
    public Collection getCourts()
    {
	return courts;
    }

    /**
     * @param juvDispositionCode
     *            the juvDispositionCode to set
     */
    public void setJuvDispositionCode(JuvenileDispositionCodeResponseEvent juvDispositionCode)
    {
	this.juvDispositionCode = juvDispositionCode;
    }

    /**
     * @return the juvDispositionCode
     */
    public JuvenileDispositionCodeResponseEvent getJuvDispositionCode()
    {
	return juvDispositionCode;
    }

    /**
     * @return the closeDate
     */
    public Date getCloseDate()
    {
	return closeDate;
    }

    /**
     * @param closeDate
     *            the closeDate to set
     */
    public void setCloseDate(Date closeDate)
    {
	this.closeDate = closeDate;
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
     * @return the casefileReferrals
     */
    public Collection<JuvenileCasefileReferral> getCasefileReferrals()
    {
	return casefileReferrals;
    }

    /**
     * @param casefileReferrals
     *            the casefileReferrals to set
     */
    public void setCasefileReferrals(Collection<JuvenileCasefileReferral> casefileReferrals)
    {
	this.casefileReferrals = casefileReferrals;
    }

    /**
     * @return the casefiles
     */
    public Collection<JuvenileCasefileResponseEvent> getCasefiles()
    {
	return casefiles;
    }

    /**
     * @param casefiles
     *            the casefiles to set
     */
    public void setCasefiles(Collection<JuvenileCasefileResponseEvent> casefiles)
    {
	this.casefiles = casefiles;
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

    public String getOffense()
    {
	return offense;
    }

    public void setOffense(String offense)
    {
	this.offense = offense;
    }

    public String getJpo()
    {
	return jpo;
    }

    public void setJpo(String jpo)
    {
	this.jpo = jpo;
    }

    public String getSupervisionCategory()
    {
	return supervisionCategory;
    }

    public void setSupervisionCategory(String supervisionCategory)
    {
	this.supervisionCategory = supervisionCategory;
    }

    public String getSupervisionCategoryId()
    {
	return supervisionCategoryId;
    }

    public void setSupervisionCategoryId(String supervisionCategoryId)
    {
	this.supervisionCategoryId = supervisionCategoryId;
    }

    public String getSupervisionType()
    {
	return supervisionType;
    }

    public void setSupervisionType(String supervisionType)
    {
	this.supervisionType = supervisionType;
    }

    public String getSupervisionTypeId()
    {
	return supervisionTypeId;
    }

    public void setSupervisionTypeId(String supervisionTypeId)
    {
	this.supervisionTypeId = supervisionTypeId;
    }

    public String getIntakeDecisionId()
    {
	return intakeDecisionId;
    }

    public void setIntakeDecisionId(String intakeDecisionId)
    {
	this.intakeDecisionId = intakeDecisionId;
    }

    public String getOffenseDesc()
    {
	return offenseDesc;
    }

    public void setOffenseDesc(String offenseDesc)
    {
	this.offenseDesc = offenseDesc;
    }

    public String getJpoId()
    {
	return jpoId;
    }

    public void setJpoId(String jpoId)
    {
	this.jpoId = jpoId;
    }

    /**
     * @return the courtResultDesc
     */
    public String getCourtResultDesc()
    {
	return courtResultDesc;
    }

    /**
     * @param courtResultDesc
     *            the courtResultDesc to set
     */
    public void setCourtResultDesc(String courtResultDesc)
    {
	this.courtResultDesc = courtResultDesc;
    }

    public int compareTo(Object obj)
    {
	if (obj == null)
	    return -1;
	JuvenileProfileReferralListResponseEvent evt = (JuvenileProfileReferralListResponseEvent) obj;
	return referralNumber.compareToIgnoreCase(evt.getReferralNumber());
    }

    /**
     * @return the ctAssignJpoId
     */
    public String getCtAssignJpoId()
    {
	return ctAssignJpoId;
    }

    /**
     * @param ctAssignJpoId
     *            the ctAssignJpoId to set
     */
    public void setCtAssignJpoId(String ctAssignJpoId)
    {
	this.ctAssignJpoId = ctAssignJpoId;
    }

    /**
     * @return the ctAssignLevel
     */
    public String getCtAssignLevel()
    {
	return ctAssignLevel;
    }

    /**
     * @param ctAssignLevel the ctAssignLevel to set
     */
    public void setCtAssignLevel(String ctAssignLevel)
    {
	this.ctAssignLevel = ctAssignLevel;
    }

    /**
     * @return the courtResultDisposition
     */
    public JuvenileDispositionCodeResponseEvent getCourtResultDisposition()
    {
	return courtResultDisposition;
    }

    /**
     * @param courtResultDisposition the courtResultDisposition to set
     */
    public void setCourtResultDisposition(JuvenileDispositionCodeResponseEvent courtResultDisposition)
    {
	this.courtResultDisposition = courtResultDisposition;
    }

    /**
     * @return the detDays
     */
    public long getDetDays()
    {
	return detDays;
    }

    /**
     * @param detDays the detDays to set
     */
    public void setDetDays(long detDays)
    {
	this.detDays = detDays;
    }

    public String getPetitionStatus()
    {
	return petitionStatus;
    }

    public void setPetitionStatus(String petitionStatus)
    {
	this.petitionStatus = petitionStatus;
    }

    public String getPetitionStatusDescr()
    {
	return petitionStatusDescr;
    }

    public void setPetitionStatusDescr(String petitionStatusDescr)
    {
	this.petitionStatusDescr = petitionStatusDescr;
    }

    /**
     * @return the petitionAllegationDescr
     */
    public String getPetitionAllegationDescr()
    {
	return petitionAllegationDescr;
    }

    /**
     * @param petitionAllegationDescr the petitionAllegationDescr to set
     */
    public void setPetitionAllegationDescr(String petitionAllegationDescr)
    {
	this.petitionAllegationDescr = petitionAllegationDescr;
    }
    
    public String getPetitionAllegationDescrExtended()
    {
	return petitionAllegationDescrExtended;
    }

    /**
     * @param petitionAllegationDescr the petitionAllegationDescr to set
     */
    public void setPetitionAllegationDescrExtended(String petitionAllegationDescrExtended)
    {
	this.petitionAllegationDescrExtended = petitionAllegationDescrExtended;
    }
    

    public String getOffenseCategory()
    {
	return offenseCategory;
    }

    public void setOffenseCategory(String offenseCategory)
    {
	this.offenseCategory = offenseCategory;
    }

    public String getIntakeDecDate()
    {
	return intakeDecDate;
    }

    public void setIntakeDecDate(String intakeDecDate)
    {
	this.intakeDecDate = intakeDecDate;
    }

    public String getCurrentSupervision()
    {
	return currentSupervision;
    }

    public void setCurrentSupervision(String currentSupervision)
    {
	this.currentSupervision = currentSupervision;
    }

    public String getOffenseSeverity()
    {
	return offenseSeverity;
    }

    public void setOffenseSeverity(String offenseSeverity)
    {
	this.offenseSeverity = offenseSeverity;
    }

    /**
     * @return the referralStatus
     */
    public String getReferralStatus()
    {
	return referralStatus;
    }

    /**
     * @param referralStatus the referralStatus to set
     */
    public void setReferralStatus(String referralStatus)
    {
	this.referralStatus = referralStatus;
    }

    /**
     * @return the tJJDDate
     */
    public String getTJJDDate()
    {
	return TJJDDate;
    }

    /**
     * @param tJJDDate the tJJDDate to set
     */
    public void setTJJDDate(String tJJDDate)
    {
	TJJDDate = tJJDDate;
    }

    /**
     * @return the recType
     */
    public String getRecType()
    {
	return recType;
    }

    /**
     * @param recType the recType to set
     */
    public void setRecType(String recType)
    {
	this.recType = recType;
    }

    /**
     * @return the assignmentDate
     */
    public String getAssignmentDate()
    {
	return assignmentDate;
    }

    /**
     * @param assignmentDate the assignmentDate to set
     */
    public void setAssignmentDate(String assignmentDate)
    {
	this.assignmentDate = assignmentDate;
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
     * @return the probationJPOId
     */
    public String getProbationJPOId()
    {
	return probationJPOId;
    }

    /**
     * @param probationJPOId the probationJPOId to set
     */
    public void setProbationJPOId(String probationJPOId)
    {
	this.probationJPOId = probationJPOId;
    }

    /**
     * @return the probationLevel
     */
    public String getProbationLevel()
    {
	return probationLevel;
    }

    /**
     * @param probationLevel the probationLevel to set
     */
    public void setProbationLevel(String probationLevel)
    {
	this.probationLevel = probationLevel;
    }

    /**
     * @return the probationStartDate
     */
    public Date getProbationStartDate()
    {
	return probationStartDate;
    }

    /**
     * @param probationStartDate the probationStartDate to set
     */
    public void setProbationStartDate(Date probationStartDate)
    {
	this.probationStartDate = probationStartDate;
    }

    /**
     * @return the probationEndDate
     */
    public Date getProbationEndDate()
    {
	return probationEndDate;
    }

    /**
     * @param probationEndDate the probationEndDate to set
     */
    public void setProbationEndDate(Date probationEndDate)
    {
	this.probationEndDate = probationEndDate;
    }

    /**
     * @return the daLogNum
     */
    public String getDaLogNum()
    {
	return daLogNum;
    }

    /**
     * @param daLogNum the daLogNum to set
     */
    public void setDaLogNum(String daLogNum)
    {
	this.daLogNum = daLogNum;
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
     * @return the caseStatus
     */
    public String getCaseStatus()
    {
	return caseStatus;
    }

    /**
     * @param caseStatus the caseStatus to set
     */
    public void setCaseStatus(String caseStatus)
    {
	this.caseStatus = caseStatus;
    }

    /**
     * @return the casefileId
     */
    public String getCasefileId()
    {
	return casefileId;
    }

    /**
     * @param casefileId the casefileId to set
     */
    public void setCasefileId(String casefileId)
    {
	this.casefileId = casefileId;
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

    public String getControllingReferralId()
    {
        return controllingReferralId;
    }

    public void setControllingReferralId(String controllingReferralId)
    {
        this.controllingReferralId = controllingReferralId;
    }

    public Collection<JuvenileOffenseCodeResponseEvent> getOffenseCodes()
    {
	return offenseCodes;
    }

    public void setOffenseCodes(Collection<JuvenileOffenseCodeResponseEvent> offenseCodes)
    {
	this.offenseCodes = offenseCodes;
    }

    public String getRefSeqNum()
    {
        return refSeqNum;
    }

    public void setRefSeqNum(String refSeqNum)
    {
        this.refSeqNum = refSeqNum;
    }

    public String getFund()
    {
	return fund;
    }

    public void setFund(String fund)
    {
	this.fund = fund;
    }

    public String getDaStatus()
    {
	return daStatus;
    }

    public void setDaStatus(String daStatus)
    {
	this.daStatus = daStatus;
    }

    public Date getDaDateOut()
    {
	return daDateOut;
    }

    public void setDaDateOut(Date daDateOut)
    {
	this.daDateOut = daDateOut;
    }

    public String getInactiveInd()
    {
        return inactiveInd;
    }

    public void setInactiveInd(String inactiveInd)
    {
        this.inactiveInd = inactiveInd;
    }

    public String getSeveritySubtype()
    {
        return severitySubtype;
    }

    public void setSeveritySubtype(String severitySubtype)
    {
        this.severitySubtype = severitySubtype;
    }

    public Collection<JuvenileProfileReferralListResponseEvent> getAdminReferrals()
    {
        return adminReferrals;
    }

    public void setAdminReferrals(Collection<JuvenileProfileReferralListResponseEvent> adminReferrals)
    {
        this.adminReferrals = adminReferrals;
    }

    public String getOffenseInvestNum()
    {
        return offenseInvestNum;
    }

    public void setOffenseInvestNum(String offenseInvestNum)
    {
        this.offenseInvestNum = offenseInvestNum;
    }

    public Date getOffenseDate()
    {
        return offenseDate;
    }

    public void setOffenseDate(Date offenseDate)
    {
        this.offenseDate = offenseDate;
    }
    public String getNcicCode()
    {
        return ncicCode;
    }

    public void setNcicCode(String ncicCode)
    {
        this.ncicCode = ncicCode;
    }
    public String getCjisNum()
    {
        return cjisNum;
    }

    public void setCjisNum(String cjisNum)
    {
        this.cjisNum = cjisNum;
    }
    public String getTerminationDate()
    {
        return terminationDate;
    }

    public void setTerminationDate(String terminationDate)
    {
        this.terminationDate = terminationDate;
    }
    public String getPetitionallegationCategory()
    {
        return petitionallegationCategory;
    }

    public void setPetitionallegationCategory(String petitionallegationCategory)
    {
        this.petitionallegationCategory = petitionallegationCategory;
    }

    public String getTjpcDisp()
    {
        return tjpcDisp;
    }

    public void setTjpcDisp(String tjpcDisp)
    {
        this.tjpcDisp = tjpcDisp;
    }

    public Date getDispositionDate()
    {
        return dispositionDate;
    }

    public void setDispositionDate(Date dispositionDate)
    {
        this.dispositionDate = dispositionDate;
    }
    
    public String getJuvNum()
    {
        return juvNum;
    }

    public void setJuvNum(String juvNum)
    {
        this.juvNum = juvNum;
    }

    public Date getPdaDate()
    {
        return pdaDate;
    }

    public void setPdaDate(Date pdaDate)
    {
        this.pdaDate = pdaDate;
    }
    
    
    
    
}
