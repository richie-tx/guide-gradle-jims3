package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class DeleteReferralOffenseEvent extends RequestEvent 
{
    private String offenseId;
    private String delComments;

    public String getOffenseId()
    {
        return offenseId;
    }

    public void setOffenseId(String offenseId)
    {
        this.offenseId = offenseId;
    }
    
    public String getDelComments()
    {
	return delComments;
    }

    public void setDelComments(String delComments)
    {
	this.delComments = delComments;
    }

}
