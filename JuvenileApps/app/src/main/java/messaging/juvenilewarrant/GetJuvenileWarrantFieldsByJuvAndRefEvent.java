package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileWarrantFieldsByJuvAndRefEvent extends RequestEvent
{
    int juvenileNum;
    int referralNum;
    
    public int getJuvenileNum()
    {
        return juvenileNum;
    }
    public void setJuvenileNum(int juvenileNum)
    {
        this.juvenileNum = juvenileNum;
    }
    public int getReferralNum()
    {
        return referralNum;
    }
    public void setReferralNum(int referralNum)
    {
        this.referralNum = referralNum;
    }
}
