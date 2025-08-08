package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetPetitionByCJISEvent extends RequestEvent
{

    public String CJISNumber;

    public String getCJISNumber()
    {
        return CJISNumber;
    }

    public void setCJISNumber(String cJISNumber)
    {
        CJISNumber = cJISNumber;
    }
}
