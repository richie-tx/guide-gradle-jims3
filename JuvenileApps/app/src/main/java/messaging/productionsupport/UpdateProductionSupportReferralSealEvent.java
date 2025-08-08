package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportReferralSealEvent extends RequestEvent
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String OID;
    private String juvenileId;
    private String referralNum;
    private String sealedComments;
    private String action;

    private String sealedDate;    

    public String getJuvenileId()
    {
	return juvenileId;
    }

    public void setJuvenileId(String juvenileId)
    {
	this.juvenileId = juvenileId;
    }

    public String getReferralNum()
    {
	return referralNum;
    }

    public void setReferralNum(String referralNum)
    {
	this.referralNum = referralNum;
    }

    
    public String getOID()
    {
	return OID;
    }

    public void setOID(String oID)
    {
	OID = oID;
    }
    public String getSealedComments()
    {
        return sealedComments;
    }

    public void setSealedComments(String sealedComments)
    {
        this.sealedComments = sealedComments;
    }
    public String getSealedDate()
    {
        return sealedDate;
    }

    public void setSealedDate(String sealedDate)
    {
        this.sealedDate = sealedDate;
    }

    public String getAction()
    {
	return action;
    }

    public void setAction(String action)
    {
	this.action = action;
    }

    /**
     * @roseuid 45702FFC0393
     */
    public UpdateProductionSupportReferralSealEvent()
    {

    }

}
