package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class UpdateJuvenileNumControlEvent extends RequestEvent
{

    String lastJuvenileNum;

    public String getLastJuvenileNum()
    {
        return lastJuvenileNum;
    }

    public void setLastJuvenileNum(String lastJuvenileNum)
    {
        this.lastJuvenileNum = lastJuvenileNum;
    }
}
