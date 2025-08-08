package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileWarrantAssociatesByWarrantIdEvent extends RequestEvent
{
    String warrantNum;

    public String getWarrantNum()
    {
        return warrantNum;
    }

    public void setWarrantNum(String warrantNum)
    {
        this.warrantNum = warrantNum;
    }
}
