package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetProgramServicesEvent extends RequestEvent
{
    String casefileId;

    public String getCasefileId()
    {
        return casefileId;
    }

    public void setCasefileId(String casefileId)
    {
        this.casefileId = casefileId;
    }

}
