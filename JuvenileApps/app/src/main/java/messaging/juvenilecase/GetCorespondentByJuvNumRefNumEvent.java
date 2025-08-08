package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetCorespondentByJuvNumRefNumEvent extends RequestEvent
{
    private String juvenileNum;
    private String referralNum;
    
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
}
