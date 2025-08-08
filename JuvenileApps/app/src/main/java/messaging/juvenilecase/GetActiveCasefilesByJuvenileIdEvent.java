//Source file: C:\\views\\dev\\app\\src\\messaging\\officer\\GetOfficerProfilesEvent.java

package messaging.juvenilecase;


import mojo.km.messaging.RequestEvent;

public class GetActiveCasefilesByJuvenileIdEvent extends RequestEvent
{
    private String juvenileId;

    public String getJuvenileId()
    {
        return juvenileId;
    }

    public void setJuvenileId(String juvenileNum)
    {
        this.juvenileId = juvenileNum;
    }
    
    
}
