package messaging.detentionCourtHearings;

import java.util.Date;
import mojo.km.messaging.RequestEvent;

public class UpdateJJSCLDetentionEvent extends RequestEvent
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
	private String 		docketEventId;
	private String 		subDocketEventId;
    	private String		alternateSettingInd;
	private String		attorneyName;
	private String		atyConfirmation;
	
	private String		barNumber;
	private String		galName;
	private String		galBarNumber;
	private String		chainNumber;
	private String		subChainNumber;
	private Date		courtDate;
	private Date		subCourtDate;
	private Date		courtDateWithTime;
	private String		courtId;
	private String		subCourtId;
	private String		hearingDisposition;
	private String		hearingDispositionDesc;
	private String		hearingResult;
	private String		hearingResultDesc;
	private String		issueFlag;
	private String		juvenileNumber;
	private String		petitionNumber;
	private String		referralNumber;
	private String		seqNumber;
	private String		subSeqNumber;
	private String		updateFlag;
	private String		updateSub;
	private String		updateHeader;
	private String 		attorneyConnection;
	private String 		resetReason;
	private Date	    	resetDate;
	
	//added for facility
	private String 		recType;
	private String 		tjpcseqnum;

	private String 		transferTo;
	private boolean updateSubAdLitem;
	private String detentionId;
	//task 147423
	private String attorney2BarNum;   
	private String attorney2Name; 
	private String attorney2Connection; 
	   // task 168666
	private String interpreter;	
	

	public String getDocketEventId()
	{
	    return docketEventId;
	}

	public void setDocketEventId(String docketEventId)
	{
	    this.docketEventId = docketEventId;
	}

	public String getSubDocketEventId()
	{
	    return subDocketEventId;
	}

	public void setSubDocketEventId(String subDocketEventId)
	{
	    this.subDocketEventId = subDocketEventId;
	}

	public String getAlternateSettingInd()
	{
	    return alternateSettingInd;
	}

	public void setAlternateSettingInd(String alternateSettingInd)
	{
	    this.alternateSettingInd = alternateSettingInd;
	}

	public String getAttorneyName()
	{
	    return attorneyName;
	}

	public void setAttorneyName(String attorneyName)
	{
	    this.attorneyName = attorneyName;
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

	public Date getCourtDate()
	{
	    return courtDate;
	}

	public void setCourtDate(Date courtDate)
	{
	    this.courtDate = courtDate;
	}

	public Date getCourtDateWithTime()
	{
	    return courtDateWithTime;
	}

	public void setCourtDateWithTime(Date courtDateWithTime)
	{
	    this.courtDateWithTime = courtDateWithTime;
	}

	public String getCourtId()
	{
	    return courtId;
	}

	public void setCourtId(String courtId)
	{
	    this.courtId = courtId;
	}

	public String getHearingDisposition()
	{
	    return hearingDisposition;
	}

	public void setHearingDisposition(String hearingDisposition)
	{
	    this.hearingDisposition = hearingDisposition;
	}

	public String getHearingDispositionDesc()
	{
	    return hearingDispositionDesc;
	}

	public void setHearingDispositionDesc(String hearingDispositionDesc)
	{
	    this.hearingDispositionDesc = hearingDispositionDesc;
	}

	public String getHearingResult()
	{
	    return hearingResult;
	}

	public void setHearingResult(String hearingResult)
	{
	    this.hearingResult = hearingResult;
	}

	public String getHearingResultDesc()
	{
	    return hearingResultDesc;
	}

	public void setHearingResultDesc(String hearingResultDesc)
	{
	    this.hearingResultDesc = hearingResultDesc;
	}

	public String getIssueFlag()
	{
	    return issueFlag;
	}

	public void setIssueFlag(String issueFlag)
	{
	    this.issueFlag = issueFlag;
	}

	public String getJuvenileNumber()
	{
	    return juvenileNumber;
	}

	public void setJuvenileNumber(String juvenileNumber)
	{
	    this.juvenileNumber = juvenileNumber;
	}

	public String getPetitionNumber()
	{
	    return petitionNumber;
	}

	public void setPetitionNumber(String petitionNumber)
	{
	    this.petitionNumber = petitionNumber;
	}

	public String getReferralNumber()
	{
	    return referralNumber;
	}

	public void setReferralNumber(String referralNumber)
	{
	    this.referralNumber = referralNumber;
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

	public String getAttorneyConnection()
	{
	    return attorneyConnection;
	}

	public void setAttorneyConnection(String attorneyConnection)
	{
	    this.attorneyConnection = attorneyConnection;
	}

	public String getResetReason()
	{
	    return resetReason;
	}

	public void setResetReason(String comments)
	{
	    this.resetReason = comments;
	}

	public Date getResetDate()
	{
	    return resetDate;
	}

	public void setResetDate(Date resetDate)
	{
	    this.resetDate = resetDate;
	}

	public String getRecType()
	{
	    return recType;
	}

	public void setRecType(String recType)
	{
	    this.recType = recType;
	}

	public String getTjpcseqnum()
	{
	    return tjpcseqnum;
	}

	public void setTjpcseqnum(String tjpcseqnum)
	{
	    this.tjpcseqnum = tjpcseqnum;
	}

	public String getTransferTo()
	{
	    return transferTo;
	}

	public void setTransferTo(String transferTo)
	{
	    this.transferTo = transferTo;
	}

	public String getUpdateSub()
	{
	    return updateSub;
	}

	public void setUpdateSub(String updateSub)
	{
	    this.updateSub = updateSub;
	}

	public String getSubChainNumber()
	{
	    return subChainNumber;
	}

	public void setSubChainNumber(String subChainNumber)
	{
	    this.subChainNumber = subChainNumber;
	}

	public Date getSubCourtDate()
	{
	    return subCourtDate;
	}

	public void setSubCourtDate(Date subCourtDate)
	{
	    this.subCourtDate = subCourtDate;
	}

	public String getSubCourtId()
	{
	    return subCourtId;
	}

	public void setSubCourtId(String subCourtId)
	{
	    this.subCourtId = subCourtId;
	}

	public String getSubSeqNumber()
	{
	    return subSeqNumber;
	}

	public void setSubSeqNumber(String subSeqNumber)
	{
	    this.subSeqNumber = subSeqNumber;
	}

	public String getUpdateHeader()
	{
	    return updateHeader;
	}

	public void setUpdateHeader(String updateHeader)
	{
	    this.updateHeader = updateHeader;
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

	public boolean isUpdateSubAdLitem()
	{
	    return updateSubAdLitem;
	}

	public void setUpdateSubAdLitem(boolean updateSubAdLitem)
	{
	    this.updateSubAdLitem = updateSubAdLitem;
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
	//task 147423
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

	    // task 168662
	    public String getInterpreter()
		{
		    return interpreter;
		}

		public void setInterpreter(String interpreter)
		{
		    this.interpreter = interpreter;
		}
	
}
