package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class CreateJuvenileSsnViewLogEvent extends RequestEvent
{
    String juvenileNum;

    public String getJuvenileNum()
    {
        return juvenileNum;
    }

    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
    }
}
