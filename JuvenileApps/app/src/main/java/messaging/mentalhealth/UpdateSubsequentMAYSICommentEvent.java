package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

public class UpdateSubsequentMAYSICommentEvent extends RequestEvent
{
    public String maysiSubAssessmentId;
    public String maysiAssessmentId;
    public String existingComments;
    public String newComments;
    
    public String getMaysiSubAssessmentId()
    {
        return maysiSubAssessmentId;
    }
    public void setMaysiSubAssessmentId(String maysiSubAssessmentId)
    {
        this.maysiSubAssessmentId = maysiSubAssessmentId;
    }
    public String getMaysiAssessmentId()
    {
        return maysiAssessmentId;
    }
    public void setMaysiAssessmentId(String maysiAssessmentId)
    {
        this.maysiAssessmentId = maysiAssessmentId;
    }
    public String getExistingComments()
    {
        return existingComments;
    }
    public void setExistingComments(String existingComments)
    {
        this.existingComments = existingComments;
    }
    public String getNewComments()
    {
        return newComments;
    }
    public void setNewComments(String newComments)
    {
        this.newComments = newComments;
    }
   
}
