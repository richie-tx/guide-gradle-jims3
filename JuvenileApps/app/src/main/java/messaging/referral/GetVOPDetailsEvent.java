//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefileCourtOrdersEvent.java

package messaging.referral;

import mojo.km.messaging.RequestEvent;

public class GetVOPDetailsEvent extends RequestEvent 
{
    private String juvenileNumber;
    private String referralNumber;
    


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
