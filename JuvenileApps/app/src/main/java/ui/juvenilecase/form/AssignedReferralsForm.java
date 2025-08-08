package ui.juvenilecase.form;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;

import org.apache.struts.action.ActionForm;

import ui.juvenilecase.form.JuvenileMemberForm.MemberContact;

public class AssignedReferralsForm extends ActionForm
{
    private String juvenileNum;

    private String referralNum;

    private String closeDate;

    private String courtDate;

    private String intakeDate;

    private String supervisionBeginDate;

    private String supervisionEndDate;

    private String courtId;

    private String courtDispositionDesc;

    private String courtResultDesc;

    private String intakeDecision;

    private String sequenceNum;

    private String transactionNum;

    private String caseType;

    private String referralTypeInd;

    private Collection offenses;

    private Collection petitions;

    private Collection referralList;
    
    private Collection<JuvenileProfileReferralListResponseEvent> referralPopList;
    
    private Collection<JuvenileDetentionFacilitiesResponseEvent> facilityHistoryList;

    //Fields added for updated Assigned Referrals pages
    private String daLogNum = "";

    private String levelOfCare = "";

    //private Date referralDate = new Date();//changed the datatype for #JIMS200045945
    private String referralDate;

    private String actionType = "";

    private JuvenileDetentionFacilitiesResponseEvent admissionInfo;

    private Collection<JuvenileTraitResponseEvent> traits;

    private JuvenileProfileDetailResponseEvent profileDetail;

    private MemberContact memberContact;

    private String bookingOffenseCd;

    private String bookingOffenseCdDesc;

    private boolean isResidentialCasefile; // bug fix 51500

    private String daStatus;

    private Date DAdateOut;
    private String terminationDate;
    
    private String firstDetainedDate;
    private String lastDetainedDate;

    /**
     * @return
     */
    public String getCourtId()
    {
	return courtId;
    }

    /**
     * @return
     */
    public void setCourtId(String aCourtId)
    {
	this.courtId = aCourtId;
    }

    /**
     * @return
     */
    public String getCloseDate()
    {
	return closeDate;
    }

    public String getCloseDateFormatted()
    {
	if (this.closeDate != null)
	{
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	    return formatter.format(this.closeDate);
	}
	return "";
    }

    /**
     * @return
     */
    public String getCourtDate()
    {
	return courtDate;
    }

    public String getCourtDateFormatted()
    {
	if (this.courtDate != null)
	{
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	    return formatter.format(this.courtDate);
	}
	return "";
    }

    /**
     * @return
     */
    public String getCourtDispositionDesc()
    {
	return courtDispositionDesc;
    }

    /**
     * @return
     */
    public String getCourtResultDesc()
    {
	return courtResultDesc;
    }

    /**
     * @return
     */
    public String getIntakeDate()
    {
	return intakeDate;
    }

    public String getIntakeDateFormatted()
    {
	if (this.intakeDate != null)
	{
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	    return formatter.format(this.intakeDate);
	}
	return "";
    }

    /**
     * @return
     */
    public String getSupervisionBeginDate()
    {
	return supervisionBeginDate;
    }

    public String getSupervisionBeginDateFormatted()
    {
	if (this.supervisionBeginDate != null)
	{
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	    return formatter.format(this.supervisionBeginDate);
	}
	return "";
    }

    public void setSupervisionBeginDate(String supervisionBeginDate)
    {
	this.supervisionBeginDate = supervisionBeginDate;
    }

    /**
     * @return
     */
    public String getSupervisionEndDate()
    {
	return supervisionEndDate;
    }

    public String getSupervisionEndDateFormatted()
    {
	if (this.supervisionEndDate != null)
	{
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	    return formatter.format(this.supervisionEndDate);
	}
	return "";
    }

    public void setSupervisionEndDate(String supervisionEndDate)
    {
	this.supervisionEndDate = supervisionEndDate;
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
    public String getJuvenileNum()
    {
	return juvenileNum;
    }

    /**
     * @return
     */
    public String getReferralNum()
    {
	return referralNum;
    }

    /**
     * @return
     */
    public String getSequenceNum()
    {
	return sequenceNum;
    }

    /**
     * @return
     */
    public String getTransactionNum()
    {
	return transactionNum;
    }

    /**
     * @param date
     */
    public void setCloseDate(String date)
    {
	closeDate = date;
    }

    /**
     * @param date
     */
    public void setCourtDate(String date)
    {
	courtDate = date;
    }

    /**
     * @param string
     */
    public void setCourtDispositionDesc(String string)
    {
	courtDispositionDesc = string;
    }

    /**
     * @param string
     */
    public void setCourtResultDesc(String string)
    {
	courtResultDesc = string;
    }

    /**
     * @param date
     */
    public void setIntakeDate(String date)
    {
	intakeDate = date;
    }

    /**
     * @param string
     */
    public void setIntakeDecision(String string)
    {
	intakeDecision = string;
    }

    /**
     * @param string
     */
    public void setJuvenileNum(String string)
    {
	juvenileNum = string;
    }

    /**
     * @param string
     */
    public void setReferralNum(String string)
    {
	referralNum = string;
    }

    /**
     * @param string
     */
    public void setSequenceNum(String string)
    {
	sequenceNum = string;
    }

    /**
     * @param string
     */
    public void setTransactionNum(String string)
    {
	transactionNum = string;
    }

    /**
     * sets the assigned offenses
     * 
     * @param aOffenses
     */
    public void setOffenses(Collection aOffenses)
    {
	this.offenses = aOffenses;
    }

    /**
     * returns the assigned offenses by referral
     * 
     * @return collection
     */
    public Collection getOffenses()
    {
	return offenses;
    }

    /**
     * @return collection of petitions JJSChargeResponseEvents
     */
    public Collection getPetitions()
    {
	return petitions;
    }

    /**
     * @param aPetitions
     *            (JJSChargeResponseEvent)
     */
    public void setPetitions(Collection aPetitions)
    {
	this.petitions = aPetitions;
    }

    public void clear()
    {
	this.courtId = null;
	this.transactionNum = null;
	this.referralNum = null;
	//this.juvenileNum = null;
	this.sequenceNum = null;
	this.courtDate = null;
	this.intakeDate = null;
	this.closeDate = null;
	this.courtDispositionDesc = null;
	this.courtResultDesc = null;
	this.intakeDecision = null;
	this.offenses = null;
	this.petitions = null;
	//this.referralList = null; //BUG 88261 //commented for VOP BUG# on 03/13/2024
	this.referralPopList=null;
	this.daStatus = null; //BUG 158553
	this.daLogNum = null; //BUG 158553
	this.DAdateOut = null;//BUG 158553

    }

    /**
     * @return
     */
    public Collection getReferralList()
    {
	return referralList;
    }

    /**
     * @param collection
     */
    public void setReferralList(Collection collection)
    {
	referralList = collection;
    }

    /**
     * @return Returns the daLogNum.
     */
    public String getDaLogNum()
    {
	return daLogNum;
    }

    /**
     * @param daLogNum
     *            The daLogNum to set.
     */
    public void setDaLogNum(String daLogNum)
    {
	this.daLogNum = daLogNum;
    }

    /**
     * @return Returns the levelOfCare.
     */
    public String getLevelOfCare()
    {
	return levelOfCare;
    }

    /**
     * @param levelOfCare
     *            The levelOfCare to set.
     */
    public void setLevelOfCare(String levelOfCare)
    {
	this.levelOfCare = levelOfCare;
    }

    /**
     * @return Returns the referralDate.
     */
    /*
     * public Date getReferralDate() { return referralDate; }
     *//**
     * @param referralDate
     *            The referralDate to set.
     */
    /*
     * public void setReferralDate(Date referralDate) { this.referralDate = referralDate; }
     */

    /**
     * @return Returns the referralDate.
     */
    public String getReferralDate()
    {
	return referralDate;
    }

    /**
     * @param referralDate
     *            The referralDate to set.
     */
    public void setReferralDate(String referralDate)
    {
	this.referralDate = referralDate;
    }

    /**
     * @return Returns the actionType.
     */
    public String getActionType()
    {
	return actionType;
    }

    /**
     * @param actionType
     *            The actionType to set.
     */
    public void setActionType(String actionType)
    {
	this.actionType = actionType;
    }

    public String getCaseType()
    {
	return caseType;
    }

    public void setCaseType(String caseType)
    {
	this.caseType = caseType;
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

    public Collection<JuvenileDetentionFacilitiesResponseEvent> getFacilityHistoryList()
    {
	return facilityHistoryList;
    }

    public void setFacilityHistoryList(Collection<JuvenileDetentionFacilitiesResponseEvent> facilityHistoryList)
    {
	this.facilityHistoryList = facilityHistoryList;
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

    public String getDaStatus()
    {
	return daStatus;
    }

    public void setDaStatus(String daStatus)
    {
	this.daStatus = daStatus;
    }

    public Date getDAdateOut()
    {
	return DAdateOut;
    }

    public void setDAdateOut(Date dAdateOut)
    {
	DAdateOut = dAdateOut;
    }

    public Collection<JuvenileProfileReferralListResponseEvent> getReferralPopList()
    {
	return referralPopList;
    }

    public void setReferralPopList(Collection<JuvenileProfileReferralListResponseEvent> referralPopList)
    {
	this.referralPopList = referralPopList;
    }
    
    public String getTerminationDate()
    {
	return terminationDate;
    }

    public String getTerminationDateFormatted()
    {
	if (this.terminationDate != null)
	{
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	    return formatter.format(this.terminationDate);
	}
	return "";
    }

    public void setTerminationDate(String TerminationDate)
    {
	this.terminationDate = TerminationDate;
    }

    public String getFirstDetainedDate()
    {
        return firstDetainedDate;
    }

    public void setFirstDetainedDate(String firstDetainedDate)
    {
        this.firstDetainedDate = firstDetainedDate;
    }

    public String getLastDetainedDate()
    {
        return lastDetainedDate;
    }

    public void setLastDetainedDate(String lastDetainedDate)
    {
        this.lastDetainedDate = lastDetainedDate;
    }


}
