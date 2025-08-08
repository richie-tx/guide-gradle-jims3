package messaging.juvenilecase.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class PactRiskLevelResponseEvent extends ResponseEvent implements Comparable
{
    private String caseFileId;
    private String referralNumber;
    private String juvenileNumber;
    private String riskLvl;
    private String needsLvl;
    private Date lastPactDate;
    private String riskNeedLvlId;
    private String status;
    private int pactId;
    
    
    public String getCaseFileId()
    {
        return caseFileId;
    }
    public void setCaseFileId(String caseFileId)
    {
        this.caseFileId = caseFileId;
    }
    public String getReferralNumber()
    {
        return referralNumber;
    }
    public void setReferralNumber(String referralNumber)
    {
        this.referralNumber = referralNumber;
    }
    public String getJuvenileNumber()
    {
        return juvenileNumber;
    }
    public void setJuvenileNumber(String juvenileNumber)
    {
        this.juvenileNumber = juvenileNumber;
    }
    public String getRiskLvl()
    {
        return riskLvl;
    }
    public void setRiskLvl(String riskLvl)
    {
        this.riskLvl = riskLvl;
    }
    public String getNeedsLvl()
    {
        return needsLvl;
    }
    public void setNeedsLvl(String needsLvl)
    {
        this.needsLvl = needsLvl;
    }
    public Date getLastPactDate()
    {
        return lastPactDate;
    }
    public void setLastPactDate(Date lastPactDate)
    {
        this.lastPactDate = lastPactDate;
    }
    public String getRiskNeedLvlId()
    {
        return riskNeedLvlId;
    }
    public void setRiskNeedLvlId(String riskNeedLvlId)
    {
        this.riskNeedLvlId = riskNeedLvlId;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public int getPactId()
    {
        return pactId;
    }
    public void setPactId(int pactId)
    {
        this.pactId = pactId;
    }
    @Override
    public int compareTo(Object o)
    {
	// TODO Auto-generated method stub
	return 0;
    }
    
}
