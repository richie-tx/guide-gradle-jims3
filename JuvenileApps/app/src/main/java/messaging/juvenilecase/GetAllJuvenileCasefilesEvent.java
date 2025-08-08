package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetAllJuvenileCasefilesEvent extends RequestEvent
{ 
    public String juvenileNum;
    
    public GetAllJuvenileCasefilesEvent(){}
    

    public String getJuvenileNum()
    {
        return juvenileNum;
    }

    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
    }
    
    

}
