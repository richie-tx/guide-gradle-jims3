/*
 * Created on Nov 7, 2006
 *
 */
package messaging.user;

import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *  
 */
public class GetUserEvent extends RequestEvent
{
    private String logonId;

    /**
     * @return Returns the userId.
     */
    public String getLogonId()
    {
        return logonId;
    }

    /**
     * @param userId
     *            The userId to set.
     */
    public void setLogonId(String logonId)
    {
        this.logonId = logonId;
    }
}
