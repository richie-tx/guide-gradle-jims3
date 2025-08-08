package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportJuvenileReferralsEvent extends RequestEvent
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String juvenileId;
    private String referralNum;
    private String referralOID;

    /**
     * @roseuid 45702FFC0393
     */
    public GetProductionSupportJuvenileReferralsEvent()
    {

    }

    /**
     * 
     * @return
     */
    public String getJuvenileId()
    {
	return juvenileId;
    }

    /**
     * 
     * @param juvenileId
     */
    public void setJuvenileId(String juvenileId)
    {
	this.juvenileId = juvenileId;
    }

    /**
     * @return the referralNum
     */
    public String getReferralNum()
    {
	return referralNum;
    }

    /**
     * @param referralNum
     *            the referralNum to set
     */
    public void setReferralNum(String referralNum)
    {
	this.referralNum = referralNum;
    }

    public String getReferralOID()
    {
	return referralOID;
    }

    public void setReferralOID(String referralOID)
    {
	this.referralOID = referralOID;
    }

   

}
