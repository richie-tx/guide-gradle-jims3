package messaging.juvenile;

import java.util.Collection;

/**
 * GetJuvenileStatusEvent.
 * 
 * @author mchowdhury
 */
public class GetJuvenileStatusEvent extends mojo.km.messaging.RequestEvent
{
    private Collection juvenileNums;

    /**
     * @roseuid 42A74E1900AB
     */
    public GetJuvenileStatusEvent()
    {

    }

    /**
     * @param juvenileNum
     *            The juvenileNum to set.
     */
    public void setJuvenileNums(Collection juvenileNums)
    {
        this.juvenileNums = juvenileNums;
    }

    /**
     * @return Returns the juvenileNums.
     */
    public Collection getJuvenileNums()
    {
        return juvenileNums;
    }
}
