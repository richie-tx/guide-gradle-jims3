package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetTransOffenseReferralsQueryEvent extends RequestEvent 
{
    String juvenileNumber;
    String referralNumber;
    
    
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
}
