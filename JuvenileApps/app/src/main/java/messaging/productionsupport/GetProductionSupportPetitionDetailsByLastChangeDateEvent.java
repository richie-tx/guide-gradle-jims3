package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportPetitionDetailsByLastChangeDateEvent extends RequestEvent
{
    
    
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
    public String getLcDate()
    {
        return lcDate;
    }
    public void setLcDate(String lcDate)
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
    
    
    String juvenileNum;
    String referralNum;
    String lcDate;
    String lcUser;

}
