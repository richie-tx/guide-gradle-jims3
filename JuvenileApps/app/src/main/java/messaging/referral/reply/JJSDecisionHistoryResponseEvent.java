package messaging.referral.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class JJSDecisionHistoryResponseEvent extends ResponseEvent 
{

    private String juvenileNum;
    private String referralNum;
    private String inDecisionId;
    private String courtDecisionId;
    private String tjpcDisp;
    private Date   decisionDate;
    private String tjpcDispSeqNum;
    
    
    public String getJuvenileNum()
    {
        return juvenileNum;
    }
    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
    }
    public String getReferralNum()
    {
        return referralNum;
    }
    public void setReferralNum(String referralNum)
    {
        this.referralNum = referralNum;
    }
    public String getInDecisionId()
    {
        return inDecisionId;
    }
    public void setInDecisionId(String inDecisionId)
    {
        this.inDecisionId = inDecisionId;
    }
    public String getCourtDecisionId()
    {
        return courtDecisionId;
    }
    public void setCourtDecisionId(String courtDecisionId)
    {
        this.courtDecisionId = courtDecisionId;
    }
    public String getTjpcDisp()
    {
        return tjpcDisp;
    }
    public void setTjpcDisp(String tjpcDisp)
    {
        this.tjpcDisp = tjpcDisp;
    }
    public Date getDecisionDate()
    {
        return decisionDate;
    }
    public void setDecisionDate(Date decisionDate)
    {
        this.decisionDate = decisionDate;
    }
    public String getTjpcDispSeqNum()
    {
        return tjpcDispSeqNum;
    }
    public void setTjpcDispSeqNum(String tjpcDispSeqNum)
    {
        this.tjpcDispSeqNum = tjpcDispSeqNum;
    }
    
    
}
