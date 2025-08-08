package messaging.referral.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class ReferralPACTResponseEvent extends ResponseEvent{
    private String caseFileId;
    private String referralNumber;
    private String juvenileNumber;
    private String riskLvl;
    private String needsLvl;
    private Date lastPactDate;
    private String riskNeedLvlId;
    private String status;
    private String offenseCode;
    private String pactId;
    public String getOffenseCode()
    {
        return offenseCode;
    }
    public void setOffenseCode(String offenseCode)
    {
        this.offenseCode = offenseCode;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getRiskNeedLvlId()
    {
        return riskNeedLvlId;
    }
    public void setRiskNeedLvlId(String riskNeedLvlId)
    {
        this.riskNeedLvlId = riskNeedLvlId;
    }
    public Date getLastPactDate()
    {
        return lastPactDate;
    }
    public void setLastPactDate(Date lastPactDate)
    {
        this.lastPactDate = lastPactDate;
    }
    public String getNeedsLvl()
    {
        return needsLvl;
    }
    public void setNeedsLvl(String needsLvl)
    {
        this.needsLvl = needsLvl;
    }
    public String getRiskLvl()
    {
        return riskLvl;
    }
    public void setRiskLvl(String riskLvl)
    {
        this.riskLvl = riskLvl;
    }
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
    public String getCaseFileId()
    {
        return caseFileId;
    }
    public void setCaseFileId(String caseFileId)
    {
        this.caseFileId = caseFileId;
    }
    public String getPactId()
    {
        return pactId;
    }
    public void setPactId(String pactId)
    {
        this.pactId = pactId;
    }
   
}
