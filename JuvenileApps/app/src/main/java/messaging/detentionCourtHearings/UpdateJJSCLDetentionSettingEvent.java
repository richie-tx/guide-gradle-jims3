package messaging.detentionCourtHearings;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * Used in district court action only.
 */
public class UpdateJJSCLDetentionSettingEvent extends RequestEvent
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String docketEventId;
    private String alternateSettingInd;
    private String attorneyName;
    private String barNumber;
    private String galBarNum;
    private String galName;
    private String chainNumber;
    private Date courtDate;
    private Date courtDateWithTime;
    private String courtId;
    private String hearingDisposition;
    private String hearingDispositionDesc;
    private String hearingResult;
    private String hearingResultDesc;
    private String issueFlag;
    private String juvenileNumber;
    private String petitionNumber;
    private String referralNumber;
    private String seqNumber;
    private String updateFlag;
    private String attorneyConnection;
    private String atyConfirmation;
    private String resetReason;
    private Date resetDate;

    //added for facility
    private String recType;
    private String tjpcseqnum;

    private String transferTo;
    private String hearingType;
    private boolean isNewRecordCreated;
    private String comments;
    private String courtTime;
    
    private String detentionId;

    private Date lcDate;
    private String lcTime;
    private String lcUser;
  //task 147422
    private String attorney2BarNum;   
    private String attorney2Name; 
    private String attorney2Connection; 
//  task 168662
    private String interpreter;
    // task 187260
    private Date resettoDate;  

    

    /**
     * @return the docketEventId
     */
    public String getDocketEventId()
    {
	return docketEventId;
    }

    /**
     * @param docketEventId
     *            the docketEventId to set
     */
    public void setDocketEventId(String docketEventId)
    {
	this.docketEventId = docketEventId;
    }

    /**
     * @return the alternateSettingInd
     */
    public String getAlternateSettingInd()
    {
	return alternateSettingInd;
    }

    /**
     * @param alternateSettingInd
     *            the alternateSettingInd to set
     */
    public void setAlternateSettingInd(String alternateSettingInd)
    {
	this.alternateSettingInd = alternateSettingInd;
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
     * @return the courtDate
     */
    public Date getCourtDate()
    {
	return courtDate;
    }

    /**
     * @param courtDate
     *            the courtDate to set
     */
    public void setCourtDate(Date courtDate)
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
	this.petitionNumber = petitionNumber;
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
     * @return the attorneyConnection
     */
    public String getAttorneyConnection()
    {
	return attorneyConnection;
    }

    /**
     * @param attorneyConnection
     *            the attorneyConnection to set
     */
    public void setAttorneyConnection(String attorneyConnection)
    {
	this.attorneyConnection = attorneyConnection;
    }

    /**
     * @return the resetReason
     */
    public String getResetReason()
    {
	return resetReason;
    }

    /**
     * @param resetReason
     *            the resetReason to set
     */
    public void setResetReason(String resetReason)
    {
	this.resetReason = resetReason;
    }

    /**
     * @return the resetDate
     */
    public Date getResetDate()
    {
	return resetDate;
    }

    /**
     * @param resetDate
     *            the resetDate to set
     */
    public void setResetDate(Date resetDate)
    {
	this.resetDate = resetDate;
    }

    /**
     * @return the recType
     */
    public String getRecType()
    {
	return recType;
    }

    /**
     * @param recType
     *            the recType to set
     */
    public void setRecType(String recType)
    {
	this.recType = recType;
    }

    /**
     * @return the tjpcseqnum
     */
    public String getTjpcseqnum()
    {
	return tjpcseqnum;
    }

    /**
     * @param tjpcseqnum
     *            the tjpcseqnum to set
     */
    public void setTjpcseqnum(String tjpcseqnum)
    {
	this.tjpcseqnum = tjpcseqnum;
    }

    /**
     * @return the transferTo
     */
    public String getTransferTo()
    {
	return transferTo;
    }

    /**
     * @param transferTo
     *            the transferTo to set
     */
    public void setTransferTo(String transferTo)
    {
	this.transferTo = transferTo;
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
     * @return the serialversionuid
     */
    public static long getSerialversionuid()
    {
	return serialVersionUID;
    }

    /**
     * @return the isNewRecordCreated
     */
    public boolean isNewRecordCreated()
    {
	return isNewRecordCreated;
    }

    /**
     * @param isNewRecordCreated
     *            the isNewRecordCreated to set
     */
    public void setNewRecordCreated(boolean isNewRecordCreated)
    {
	this.isNewRecordCreated = isNewRecordCreated;
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
     * @return the lcDate
     */
    public Date getLcDate()
    {
	return lcDate;
    }

    /**
     * @param lcDate the lcDate to set
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
     * @param lcTime the lcTime to set
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
     * @param lcUser the lcUser to set
     */
    public void setLcUser(String lcUser)
    {
	this.lcUser = lcUser;
    }

    public String getGalBarNum()
    {
        return galBarNum;
    }

    public void setGalBarNum(String galBarNum)
    {
        this.galBarNum = galBarNum;
    }

    public String getGalName()
    {
        return galName;
    }

    public void setGalName(String galName)
    {
        this.galName = galName;
    }

    public String getDetentionId()
    {
	return detentionId;
    }

    public void setDetentionId(String detentionId)
    {
	this.detentionId = detentionId;
    }
    public String getAtyConfirmation()
    {
        return atyConfirmation;
    }

    public void setAtyConfirmation(String atyConfirmation)
    {
        this.atyConfirmation = atyConfirmation;
    }
  //task 147422
    public String getAttorney2Connection()
    {
        return attorney2Connection;
    }

    public void setAttorney2Connection(String attorney2Connection)
    {
        this.attorney2Connection = attorney2Connection;
    }

    public String getAttorney2Name()
    {
        return attorney2Name;
    }

    public void setAttorney2Name(String attorney2Name)
    {
        this.attorney2Name = attorney2Name;
    }

    
    public String getAttorney2BarNum()
    {
        return attorney2BarNum;
    }

    public void setAttorney2BarNum(String attorney2BarNum)
    {
        this.attorney2BarNum = attorney2BarNum;
    }

    public String getInterpreter()
    {
        return interpreter;
    }

    public void setInterpreter(String interpreter)
    {
        this.interpreter = interpreter;
    }
    
    public Date getResettoDate()
    {
        return resettoDate;
    }

    public void setResettoDate(Date resettoDate)
    {
        this.resettoDate = resettoDate;
    }

}
