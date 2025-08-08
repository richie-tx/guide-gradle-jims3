package messaging.juvenilewarrant;

import java.util.Date;

import pd.codetable.criminal.JuvenileCourt;
import pd.codetable.criminal.JuvenileOffenseCode;
import mojo.km.messaging.RequestEvent;

public class SaveJuvenilePetitionInformationEvent extends RequestEvent
{
    private Date petitionDate;
    private String sequenceNum;
    /**
     * Properties for offenseCode
     * 
     * @referencedType pd.codetable.criminal.JuvenileOffenseCode
     * @detailerDoNotGenerate true
     */
    private JuvenileOffenseCode offenseCode = null;
    private String status; // petition status
    private String referralNum;
    private String amend; // petition Amendment
    private String courtId;
    private String type; // petitionType
    /**
     * Properties for court
     * 
     * @referencedType pd.codetable.criminal.JuvenileCourt
     * @detailerDoNotGenerate true
     */
    private JuvenileCourt court = null;
    private String offenseCodeId;
    private String petitionNum; // petitionNum
    private String severity;
    private String juvenileNum;

    private String recType;
    private String tjpcSeqNum;
    
    
    private Date lcDate;
    private String lcUser;
    private Date lcTime;
    // comes from JJSCLCOURT
    private Date amendmentDate; 
    private String crtChainNum;
    private String crtSeqNum;
    private String CJISNum;
    // comes from JJSCLCOURT
    
    
    
    /**
     * @return the petitionDate
     */
    public Date getPetitionDate()
    {
        return petitionDate;
    }
    /**
     * @param petitionDate the petitionDate to set
     */
    public void setPetitionDate(Date petitionDate)
    {
        this.petitionDate = petitionDate;
    }
    /**
     * @return the sequenceNum
     */
    public String getSequenceNum()
    {
        return sequenceNum;
    }
    /**
     * @param sequenceNum the sequenceNum to set
     */
    public void setSequenceNum(String sequenceNum)
    {
        this.sequenceNum = sequenceNum;
    }
    /**
     * @return the offenseCode
     */
    public JuvenileOffenseCode getOffenseCode()
    {
        return offenseCode;
    }
    /**
     * @param offenseCode the offenseCode to set
     */
    public void setOffenseCode(JuvenileOffenseCode offenseCode)
    {
        this.offenseCode = offenseCode;
    }
    /**
     * @return the status
     */
    public String getStatus()
    {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status)
    {
        this.status = status;
    }
    /**
     * @return the referralNum
     */
    public String getReferralNum()
    {
        return referralNum;
    }
    /**
     * @param referralNum the referralNum to set
     */
    public void setReferralNum(String referralNum)
    {
        this.referralNum = referralNum;
    }
    /**
     * @return the amend
     */
    public String getAmend()
    {
        return amend;
    }
    /**
     * @param amend the amend to set
     */
    public void setAmend(String amend)
    {
        this.amend = amend;
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
     * @return the type
     */
    public String getType()
    {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type)
    {
        this.type = type;
    }
    /**
     * @return the court
     */
    public JuvenileCourt getCourt()
    {
        return court;
    }
    /**
     * @param court the court to set
     */
    public void setCourt(JuvenileCourt court)
    {
        this.court = court;
    }
    /**
     * @return the offenseCodeId
     */
    public String getOffenseCodeId()
    {
        return offenseCodeId;
    }
    /**
     * @param offenseCodeId the offenseCodeId to set
     */
    public void setOffenseCodeId(String offenseCodeId)
    {
        this.offenseCodeId = offenseCodeId;
    }
    /**
     * @return the petitionNum
     */
    public String getPetitionNum()
    {
        return petitionNum;
    }
    /**
     * @param petitionNum the petitionNum to set
     */
    public void setPetitionNum(String petitionNum)
    {
        this.petitionNum = petitionNum;
    }
    /**
     * @return the severity
     */
    public String getSeverity()
    {
        return severity;
    }
    /**
     * @param severity the severity to set
     */
    public void setSeverity(String severity)
    {
        this.severity = severity;
    }
    /**
     * @return the juvenileNum
     */
    public String getJuvenileNum()
    {
        return juvenileNum;
    }
    /**
     * @param juvenileNum the juvenileNum to set
     */
    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
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
     * @return the tjpcSeqNum
     */
    public String getTjpcSeqNum()
    {
        return tjpcSeqNum;
    }
    /**
     * @param tjpcSeqNum the tjpcSeqNum to set
     */
    public void setTjpcSeqNum(String tjpcSeqNum)
    {
        this.tjpcSeqNum = tjpcSeqNum;
    }
    public Date getLcDate()
    {
	return lcDate;
    }
    public void setLcDate(Date lcDate)
    {
	this.lcDate = lcDate;
    }
    public String getLcUser()
    {
	return lcUser;
    }
    public void setLcUser(String lcUser)
    {
	this.lcUser = lcUser;
    }
    public Date getLcTime()
    {
	return lcTime;
    }
    public void setLcTime(Date lcTime)
    {
	this.lcTime = lcTime;
    }
    public Date getAmendmentDate()
    {
	return amendmentDate;
    }
    public void setAmendmentDate(Date amendmentDate)
    {
	this.amendmentDate = amendmentDate;
    }
  
    public String getCrtChainNum()
    {
	return crtChainNum;
    }
    public void setCrtChainNum(String crtChainNum)
    {
	this.crtChainNum = crtChainNum;
    }
    public String getCrtSeqNum()
    {
	return crtSeqNum;
    }
    public void setCrtSeqNum(String crtSeqNum)
    {
	this.crtSeqNum = crtSeqNum;
    }
    public String getCJISNum()
    {
        return CJISNum;
    }
    public void setCJISNum(String cJISNum)
    {
        CJISNum = cJISNum;
    }
}
