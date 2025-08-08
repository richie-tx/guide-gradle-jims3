package mojo.km.messaging;

import java.security.Principal;

import mojo.km.config.MojoProperties;

/**
 * Class responsible for handling any information related to events that are made as requests to services.
 * 
 * @author Eric A Amundson
 */
/**
 * @author racooper
 *
 */
public class RequestEvent implements IEvent, Principal
{
    private String hashKey;

    public boolean isWithUR() {
		return withUR;
	}

	public void setWithUR(boolean withUR) {
		this.withUR = withUR;
	}

	private String mServer;

    private String service;

    private String userID;
    private boolean withUR;

    public RequestEvent()
    {
        MojoProperties props = MojoProperties.getInstance();
        this.service = props.getServiceNameForEvent(this);
        this.mServer = "None";
    }

    /**
     * Returns the name of this principal.
     * 
     * @return name of requesting user.
     */
    public String getName()
    {
        return service;
    }

    /**
     * Return the server context name.
     * 
     * @return server logical name
     * @reference Naming.ServerNames
     *  
     */
    public String getServer()
    {
        return mServer;
    }

    public String getTopic()
    {
        return service;
    }

    /**
     * @return Returns the userID.
     */
    public String getUserID()
    {
        return userID;
    }

    /**
     * 
     * return hashcode.
     * 
     * @return hash code.
     *  
     */
    public int hashcode()
    {
        return hashKey().hashCode();
    }

    /**
     * Listener topic.
     * 
     * @return hash code.
     *  
     */
    public String hashKey()
    {
        if (this.hashKey == null)
        {
            StringBuilder str = new StringBuilder(50);
            str.append(this.service);
            str.append("::");
            str.append(this.getClass().getName());
            this.hashKey = str.toString();
        }
        return this.hashKey;
    }

    /**
     * Set the value of the server context name.
     * 
     * @param name -
     *            server logical name, from Naming.ServerNames.
     */
    public void setServer(String name)
    {
        mServer = name;
    }

    /**
     * Set the service the event is to be associated with.
     * 
     * @param aService -
     *            event topic.
     */
    public void setTopic(String aService)
    {
        service = aService;
    }

    /**
     * @param userID
     *            The userID to set.
     */
    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    /**
     * String representation.
     * 
     * @return string representation of this object.
     */
    public String toString()
    {
        return hashKey();
    }
}
