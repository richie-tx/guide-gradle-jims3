package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class UpdateProdSupportMoveJuvenileProgramReferralEvent extends RequestEvent
{
    private String juvProgRefId;
    private String OID;
    
    public String getJuvProgRefId()
    {
	return juvProgRefId;
    }
    public void setJuvProgRefId(String juvProgRefId)
    {
	this.juvProgRefId = juvProgRefId;
    }
    public String getOID()
    {
	return OID;
    }
    public void setOID(String oID)
    {
	OID = oID;
    }
}
