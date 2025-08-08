package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCurrentPactScoresEvent extends RequestEvent
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String subjectEID;
    private String formName;
    
    
    public String getSubjectEID()
    {
        return subjectEID;
    }
    public void setSubjectEID(String subjectEID)
    {
        this.subjectEID = subjectEID;
    }
    public String getFormName()
    {
        return formName;
    }
    public void setFormName(String formName)
    {
        this.formName = formName;
    }

    
    
}
