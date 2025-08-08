package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class UpdatePetitionRecordSealOnReferralSealEvent extends RequestEvent
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String OID; 
    private String action;


    
    public String getAction()
    {
        return action;
    }





    public void setAction(String action)
    {
        this.action = action;
    }





    public String getOID()
    {
        return OID;
    }





    public void setOID(String oID)
    {
        OID = oID;
    }





    /**
     * @roseuid 45702FFC0393
     */
    public UpdatePetitionRecordSealOnReferralSealEvent()
    {

    }

}
