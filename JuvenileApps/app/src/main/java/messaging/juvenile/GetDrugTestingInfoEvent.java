package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetDrugTestingInfoEvent extends RequestEvent
{
    String casefileId;
    String drugTestingId;
    String juvenileId;
    

    public String getCasefileId()
    {
        return casefileId;
    }

    public void setCasefileId(String casefileId)
    {
        this.casefileId = casefileId;
    }

    public String getDrugTestingId()
    {
        return drugTestingId;
    }

    public void setDrugTestingId(String drugTestingId)
    {
        this.drugTestingId = drugTestingId;
    }

    public String getJuvenileId()
    {
        return juvenileId;
    }

    public void setJuvenileId(String juvenileId)
    {
        this.juvenileId = juvenileId;
    }
    
    
    
}
