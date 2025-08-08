package messaging.administercaseload;

import mojo.km.messaging.RequestEvent;

public class GetActiveCasesEvent extends RequestEvent
{
    private String defendantId;

    /**
     * @roseuid 46435F2003D4
     */
    public GetActiveCasesEvent()
    {

    }

    /**
     * @return Returns the defendantId.
     */
    public String getDefendantId()
    {
        return defendantId;
    }

    /**
     * @param defendantId
     *        The defendantId to set.
     */
    public void setDefendantId(String defendantId)
    {
        this.defendantId = defendantId;
    }
}
