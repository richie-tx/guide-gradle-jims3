package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetCasefileReferralsEvent extends RequestEvent
{
    private String juvenileNum;
    private String casefileId;
    

    public String getJuvenileNum()
    {
        return juvenileNum;
    }

    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
    }
    
    public String getCasefileId()
    {
        return casefileId;
    }

    public void setCasefileId(String casefileId)
    {
        this.casefileId = casefileId;
    }
    
}
