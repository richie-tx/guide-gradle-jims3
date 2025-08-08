package messaging.detentionCourtHearings;

import java.util.HashMap;

import mojo.km.messaging.RequestEvent;

public class GetJJSCLDetentionByDetRecResultEvent extends RequestEvent
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String detentionId;
    private HashMap<Integer, String> hearingResults;
    
    
    public String getDetentionId()
    {
        return detentionId;
    }
    public void setDetentionId(String detentionId)
    {
        this.detentionId = detentionId;
    }
    public HashMap<Integer, String> getHearingResults()
    {
        return hearingResults;
    }
    public void setHearingResults(HashMap<Integer, String> hearingResults)
    {
        this.hearingResults = hearingResults;
    }
   
    
}
