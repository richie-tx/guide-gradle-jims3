package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class MoveCommonAppReportEvent extends RequestEvent 
{
    private String oldCasefileId;
    private String casefileId;
    private String commonAppDocumentId;

    
    public String getOldCasefileId()
    {
        return oldCasefileId;
    }

    public void setOldCasefileId(String oldCasefileId)
    {
        this.oldCasefileId = oldCasefileId;
    }

    public String getCasefileId()
    {
        return casefileId;
    }

    public void setCasefileId(String casefileId)
    {
        this.casefileId = casefileId;
    }

    public String getCommonAppDocumentId()
    {
        return commonAppDocumentId;
    }

    public void setCommonAppDocumentId(String commonAppDocumentId)
    {
        this.commonAppDocumentId = commonAppDocumentId;
    }
    
    
    
    
    
    

}
