package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class UpdateReleaseRecordOnReferralSealEvent extends RequestEvent
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String OID;    
    private String juvenileNum; 
    private String referralNum;
    
    
    public String getOID()
    {
        return OID;
    }
    public void setOID(String oID)
    {
        OID = oID;
    }
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


    /**
     * @roseuid 45702FFC0393
     */
    public UpdateReleaseRecordOnReferralSealEvent()
    {

    }

}
