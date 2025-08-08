package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class UpdateCourtRecordSealOnReferralSealEvent extends RequestEvent
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String docketId;
    private String action;
    

    public String getAction()
    {
        return action;
    }


    public void setAction(String action)
    {
        this.action = action;
    }


    public String getDocketId()
    {
        return docketId;
    }


    public void setDocketId(String docketId)
    {
        this.docketId = docketId;
    }


    /**
     * @roseuid 45702FFC0393
     */
    public UpdateCourtRecordSealOnReferralSealEvent()
    {

    }

}
