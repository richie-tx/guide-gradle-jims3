package messaging.districtCourtHearings;

import java.util.Date;

import pd.codetable.Code;
import mojo.km.messaging.RequestEvent;
/**
 * Created for court action.
 * @author sthyagarajan
 *
 */
public class UpdateJJSCLCourtSettingEvent extends RequestEvent
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String juvenileNumber;
    private String referralNumber;
    private Date amendmentDate;
    private String attorneyName;
    private String attorneyConnection;
    private String atyConfirmation;    
    private String barNumber;
    private String galBarNum;
    private String galName;
    private String chainNumber;
    private String comments;
    private Date courtDate;
    private String courtId;
    private String courtTime;
    private Date filingDate;
    private String hearingCategory;
    private String hearingDisposition;
    private String hearingResult;
    private String hearingType;

    // Properties for hearingTypes
    private Code hearingTypes = null;
    private String issueFlag;
    private String juryFlag;
    private Date lcDate;
    private Date lcTime;
    private String lcUser;
    private String optionFlag;
    private String petitionAllegation;
    private String petitionAmendment;
    private String petitionNumber;
    private String petitionStatus;
    private String prevNotes;
    private String recType;
    private String resetHearingType;
    private String seqNumber;
    private String updateFlag;
    private String tjpcSeqNumber;
    private String docketEventId;
    private String transferTo;
    
    private boolean isNewRecordCreated;
    private boolean updateSubAdLitem;
    private String attorney2BarNum;   
    private String attorney2Name; 
    private String attorney2Connection;
    private String appAttorney;
    //  task 168662
    private String interpreter;   
    // task 186479
    private String tjpcDisp;
    //task 187260
    private Date resetDate;
    
    
    /**
     * @return the juvenileNumber
     */
    public String getJuvenileNumber()
    {
        return juvenileNumber;
    }
    /**
     * @param juvenileNumber the juvenileNumber to set
     */
    public void setJuvenileNumber(String juvenileNumber)
    {
        this.juvenileNumber = juvenileNumber;
    }
    /**
     * @return the referralNumber
     */
    public String getReferralNumber()
    {
        return referralNumber;
    }
    /**
     * @param referralNumber the referralNumber to set
     */
    public void setReferralNumber(String referralNumber)
    {
        this.referralNumber = referralNumber;
    }
    /**
     * @return the amendmentDate
     */
    public Date getAmendmentDate()
    {
        return amendmentDate;
    }
    /**
     * @param amendmentDate the amendmentDate to set
     */
    public void setAmendmentDate(Date amendmentDate)
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
     * @param attorneyName the attorneyName to set
     */
    public void setAttorneyName(String attorneyName)
    {
        this.attorneyName = attorneyName;
    }
    /**
     * @return the attorneyConnection
     */
    public String getAttorneyConnection()
    {
        return attorneyConnection;
    }
    /**
     * @param attorneyConnection the attorneyConnection to set
     */
    public void setAttorneyConnection(String attorneyConnection)
    {
        this.attorneyConnection = attorneyConnection;
    }
    /**
     * @return the barNumber
     */
    public String getBarNumber()
    {
        return barNumber;
    }
    /**
     * @param barNumber the barNumber to set
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
     * @param chainNumber the chainNumber to set
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
     * @param comments the comments to set
     */
    public void setComments(String comments)
    {
        this.comments = comments;
    }
    /**
     * @return the courtDate
     */
    public Date getCourtDate()
    {
        return courtDate;
    }
    /**
     * @param courtDate the courtDate to set
     */
    public void setCourtDate(Date courtDate)
    {
        this.courtDate = courtDate;
    }
    /**
     * @return the courtId
     */
    public String getCourtId()
    {
        return courtId;
    }
    /**
     * @param courtId the courtId to set
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
     * @param courtTime the courtTime to set
     */
    public void setCourtTime(String courtTime)
    {
        this.courtTime = courtTime;
    }
    /**
     * @return the filingDate
     */
    public Date getFilingDate()
    {
        return filingDate;
    }
    /**
     * @param filingDate the filingDate to set
     */
    public void setFilingDate(Date filingDate)
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
     * @param hearingCategory the hearingCategory to set
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
     * @param hearingDisposition the hearingDisposition to set
     */
    public void setHearingDisposition(String hearingDisposition)
    {
        this.hearingDisposition = hearingDisposition;
    }
    /**
     * @return the hearingResult
     */
    public String getHearingResult()
    {
        return hearingResult;
    }
    /**
     * @param hearingResult the hearingResult to set
     */
    public void setHearingResult(String hearingResult)
    {
        this.hearingResult = hearingResult;
    }
    /**
     * @return the hearingType
     */
    public String getHearingType()
    {
        return hearingType;
    }
    /**
     * @param hearingType the hearingType to set
     */
    public void setHearingType(String hearingType)
    {
        this.hearingType = hearingType;
    }
    /**
     * @return the hearingTypes
     */
    public Code getHearingTypes()
    {
        return hearingTypes;
    }
    /**
     * @param hearingTypes the hearingTypes to set
     */
    public void setHearingTypes(Code hearingTypes)
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
     * @param issueFlag the issueFlag to set
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
     * @param juryFlag the juryFlag to set
     */
    public void setJuryFlag(String juryFlag)
    {
        this.juryFlag = juryFlag;
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
    public Date getLcTime()
    {
        return lcTime;
    }
    /**
     * @param lcTime the lcTime to set
     */
    public void setLcTime(Date lcTime)
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
    /**
     * @return the optionFlag
     */
    public String getOptionFlag()
    {
        return optionFlag;
    }
    /**
     * @param optionFlag the optionFlag to set
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
     * @param petitionAllegation the petitionAllegation to set
     */
    public void setPetitionAllegation(String petitionAllegation)
    {
        this.petitionAllegation = petitionAllegation;
    }
    /**
     * @return the petitionAmendment
     */
    public String getPetitionAmendment()
    {
        return petitionAmendment;
    }
    /**
     * @param petitionAmendment the petitionAmendment to set
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
     * @return the prevNotes
     */
    public String getPrevNotes()
    {
        return prevNotes;
    }
    /**
     * @param prevNotes the prevNotes to set
     */
    public void setPrevNotes(String prevNotes)
    {
        this.prevNotes = prevNotes;
    }
    /**
     * @return the resetHearingType
     */
    public String getResetHearingType()
    {
        return resetHearingType;
    }
    /**
     * @param resetHearingType the resetHearingType to set
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
     * @param seqNumber the seqNumber to set
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
     * @param updateFlag the updateFlag to set
     */
    public void setUpdateFlag(String updateFlag)
    {
        this.updateFlag = updateFlag;
    }
    /**
     * @return the tjpcSeqNumber
     */
    public String getTjpcSeqNumber()
    {
        return tjpcSeqNumber;
    }
    /**
     * @param tjpcSeqNumber the tjpcSeqNumber to set
     */
    public void setTjpcSeqNumber(String tjpcSeqNumber)
    {
        this.tjpcSeqNumber = tjpcSeqNumber;
    }
    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }
    /**
     * @return the docketEventId
     */
    public String getDocketEventId()
    {
	return docketEventId;
    }
    /**
     * @param docketEventId the docketEventId to set
     */
    public void setDocketEventId(String docketEventId)
    {
	this.docketEventId = docketEventId;
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
     * @return the transferTo
     */
    public String getTransferTo()
    {
	return transferTo;
    }
    /**
     * @param transferTo the transferTo to set
     */
    public void setTransferTo(String transferTo)
    {
	this.transferTo = transferTo;
    }
    /**
     * @return the isNewRecordCreated
     */
    public boolean isNewRecordCreated()
    {
	return isNewRecordCreated;
    }
    /**
     * @param isNewRecordCreated the isNewRecordCreated to set
     */
    public void setNewRecordCreated(boolean isNewRecordCreated)
    {
	this.isNewRecordCreated = isNewRecordCreated;
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
    public boolean isUpdateSubAdLitem()
    {
        return updateSubAdLitem;
    }
    public void setUpdateSubAdLitem(boolean updateSubAdLitem)
    {
        this.updateSubAdLitem = updateSubAdLitem;
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
    public String getAppAttorney()
    {
        return appAttorney;
    }
    public void setAppAttorney(String appAttorney)
    {
        this.appAttorney = appAttorney;
    }
    //  task 168662
    public String getInterpreter()
    {
        return interpreter;
    }
    public void setInterpreter(String interpreter)
    {
        this.interpreter = interpreter;
    }
    //
    public String getTJPCDisp()
    {
        return this.tjpcDisp;
    }

    public void setTJPCDisp(String tjpcDisp)
    {
        this.tjpcDisp = tjpcDisp;
    }
    public Date getResetDate()
    {
        return resetDate;
    }
    public void setResetDate(Date resetDate)
    {
        this.resetDate = resetDate;
    }
}
