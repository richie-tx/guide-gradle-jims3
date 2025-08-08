package messaging.productionsupport.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class ProductionSupportTestingSessionInfoResponseEvent extends ResponseEvent
{
    String testingSessionId;
    Date testSessionDate;
    String jCMHDSMLISTId;
    String jCMHIQTESTLISTId;
    String jCMHACHLISTId;
    String jCMHADFNCLISTId;
    String jCMHABSLISTId;
    String fullName;
    String juvenileId;
    
    public String getTestingSessionId()
    {
        return testingSessionId;
    }
    public void setTestingSessionId(String testingSessionId)
    {
        this.testingSessionId = testingSessionId;
    }
    public Date getTestSessionDate()
    {
        return testSessionDate;
    }
    public void setTestSessionDate(Date testSessionDate)
    {
        this.testSessionDate = testSessionDate;
    }
    public String getjCMHDSMLISTId()
    {
        return jCMHDSMLISTId;
    }
    public void setjCMHDSMLISTId(String jCMHDSMLISTId)
    {
        this.jCMHDSMLISTId = jCMHDSMLISTId;
    }
    public String getjCMHIQTESTLISTId()
    {
        return jCMHIQTESTLISTId;
    }
    public void setjCMHIQTESTLISTId(String jCMHIQTESTLISTId)
    {
        this.jCMHIQTESTLISTId = jCMHIQTESTLISTId;
    }
    public String getjCMHACHLISTId()
    {
        return jCMHACHLISTId;
    }
    public void setjCMHACHLISTId(String jCMHACHLISTId)
    {
        this.jCMHACHLISTId = jCMHACHLISTId;
    }
    public String getjCMHADFNCLISTId()
    {
        return jCMHADFNCLISTId;
    }
    public void setjCMHADFNCLISTId(String jCMHADFNCLISTId)
    {
        this.jCMHADFNCLISTId = jCMHADFNCLISTId;
    }
    public String getjCMHABSLISTId()
    {
        return jCMHABSLISTId;
    }
    public void setjCMHABSLISTId(String jCMHABSLISTId)
    {
        this.jCMHABSLISTId = jCMHABSLISTId;
    }
    public String getFullName()
    {
        return fullName;
    }
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
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
