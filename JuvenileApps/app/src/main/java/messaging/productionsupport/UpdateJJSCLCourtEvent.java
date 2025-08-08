package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.RequestEvent;
import pd.codetable.Code;

public class UpdateJJSCLCourtEvent extends RequestEvent
{
   
    private static final long serialVersionUID = 1L;

    String docketEventId;  

    private String juvenileNumber;
    private String referralNumber;
    private String amendmentDate; 

    private String attorneyName;
    private String attorneyConnection;
    private String barNumber;
    private String chainNumber;
    private String comments;
    private String courtDate;
    private String courtId;
    private String courtTime;
    private String filingDate;  

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
    private String resetHearingType;
    private String seqNumber;
    private String updateFlag;
    private String tjpcSeqNumber;
    private String trasferTo;
    
    
    public String getJuvenileNumber()
    {
        return juvenileNumber;
    }
    
    public void setJuvenileNumber(String juvenileNumber)
    {
        this.juvenileNumber = juvenileNumber;
    }
   
    public String getReferralNumber()
    {
        return referralNumber;
    }
    
    public void setReferralNumber(String referralNumber)
    {
        this.referralNumber = referralNumber;
    }    
    
    
    public String getAttorneyName()
    {
        return attorneyName;
    }
   
    public void setAttorneyName(String attorneyName)
    {
        this.attorneyName = attorneyName;
    }
    
    public String getAttorneyConnection()
    {
        return attorneyConnection;
    }
    
    public void setAttorneyConnection(String attorneyConnection)
    {
        this.attorneyConnection = attorneyConnection;
    }
    
    public String getBarNumber()
    {
        return barNumber;
    }
    
    public void setBarNumber(String barNumber)
    {
        this.barNumber = barNumber;
    }
    
    public String getChainNumber()
    {
        return chainNumber;
    }
    
    public void setChainNumber(String chainNumber)
    {
        this.chainNumber = chainNumber;
    }
    
    public String getComments()
    {
        return comments;
    }
    
    public void setComments(String comments)
    {
        this.comments = comments;
    }
   
    
    public String getCourtId()
    {
        return courtId;
    }
    
    public void setCourtId(String courtId)
    {
        this.courtId = courtId;
    }
    
    public String getCourtTime()
    {
        return courtTime;
    }
    
    public void setCourtTime(String courtTime)
    {
        this.courtTime = courtTime;
    } 
    
    
    public String getHearingCategory()
    {
        return hearingCategory;
    }
   
    public void setHearingCategory(String hearingCategory)
    {
        this.hearingCategory = hearingCategory;
    }
    
    public String getHearingDisposition()
    {
        return hearingDisposition;
    }
    
    public void setHearingDisposition(String hearingDisposition)
    {
        this.hearingDisposition = hearingDisposition;
    }
    
    public String getHearingResult()
    {
        return hearingResult;
    }
    
    public void setHearingResult(String hearingResult)
    {
        this.hearingResult = hearingResult;
    }
    
    public String getHearingType()
    {
        return hearingType;
    }
   
    public void setHearingType(String hearingType)
    {
        this.hearingType = hearingType;
    }
    
    public Code getHearingTypes()
    {
        return hearingTypes;
    }
    
    public void setHearingTypes(Code hearingTypes)
    {
        this.hearingTypes = hearingTypes;
    }
    
    public String getIssueFlag()
    {
        return issueFlag;
    }
    
    public void setIssueFlag(String issueFlag)
    {
        this.issueFlag = issueFlag;
    }
   
    public String getJuryFlag()
    {
        return juryFlag;
    }
   
    public void setJuryFlag(String juryFlag)
    {
        this.juryFlag = juryFlag;
    }
    
    public Date getLcDate()
    {
        return lcDate;
    }
    
    public void setLcDate(Date lcDate)
    {
        this.lcDate = lcDate;
    }
    
    public Date getLcTime()
    {
        return lcTime;
    }
    
    public void setLcTime(Date lcTime)
    {
        this.lcTime = lcTime;
    }
    
    public String getLcUser()
    {
        return lcUser;
    }
    
    public void setLcUser(String lcUser)
    {
        this.lcUser = lcUser;
    }
    
    public String getOptionFlag()
    {
        return optionFlag;
    }
    
    public void setOptionFlag(String optionFlag)
    {
        this.optionFlag = optionFlag;
    }
    
    public String getPetitionAllegation()
    {
        return petitionAllegation;
    }
    
    public void setPetitionAllegation(String petitionAllegation)
    {
        this.petitionAllegation = petitionAllegation;
    }
    
    public String getPetitionAmendment()
    {
        return petitionAmendment;
    }
    
    public void setPetitionAmendment(String petitionAmendment)
    {
        this.petitionAmendment = petitionAmendment;
    }
    
    public String getPetitionNumber()
    {
        return petitionNumber;
    }
    
    public void setPetitionNumber(String petitionNumber)
    {
        this.petitionNumber = petitionNumber;
    }
    
    public String getPetitionStatus()
    {
        return petitionStatus;
    }
    
    public void setPetitionStatus(String petitionStatus)
    {
        this.petitionStatus = petitionStatus;
    }
    
    public String getPrevNotes()
    {
        return prevNotes;
    }
    
    public void setPrevNotes(String prevNotes)
    {
        this.prevNotes = prevNotes;
    }
    
    public String getResetHearingType()
    {
        return resetHearingType;
    }
    
    public void setResetHearingType(String resetHearingType)
    {
        this.resetHearingType = resetHearingType;
    }
    
    public String getSeqNumber()
    {
        return seqNumber;
    }
    
    public void setSeqNumber(String seqNumber)
    {
        this.seqNumber = seqNumber;
    }
    
    public String getUpdateFlag()
    {
        return updateFlag;
    }
    
    public void setUpdateFlag(String updateFlag)
    {
        this.updateFlag = updateFlag;
    }
   
    public String getTjpcSeqNumber()
    {
        return tjpcSeqNumber;
    }
    
    public void setTjpcSeqNumber(String tjpcSeqNumber)
    {
        this.tjpcSeqNumber = tjpcSeqNumber;
    }
    
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }
    public String getDocketEventId()
    {
        return docketEventId;
    }

    public void setDocketEventId(String docketEventId)
    {
        this.docketEventId = docketEventId;
    }
    public String getCourtDate()
    {
        return courtDate;
    }

    public void setCourtDate(String courtDate)
    {
        this.courtDate = courtDate;
    }
    public String getFilingDate()
    {
        return filingDate;
    }

    public void setFilingDate(String filingDate)
    {
        this.filingDate = filingDate;
    }
    public String getAmendmentDate()
    {
        return amendmentDate;
    }

    public void setAmendmentDate(String amendmentDate)
    {
        this.amendmentDate = amendmentDate;
    }
    public String getTrasferTo()
    {
        return trasferTo;
    }

    public void setTrasferTo(String trasferTo)
    {
        this.trasferTo = trasferTo;
    }

}
