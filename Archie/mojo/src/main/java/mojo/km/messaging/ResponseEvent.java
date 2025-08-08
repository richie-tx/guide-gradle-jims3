package mojo.km.messaging;

import java.lang.reflect.Method;
import java.util.List;
import mojo.km.utilities.Reflection;

/**
 * Responsible for handling information related to all events that return from a context service.
 * 
 * @author Eric Amundson
 * @version 1.0
 */
public class ResponseEvent implements IEvent
{
    private String mServer;

    private String service;

    protected String topicMain;

    private String hashKey;

    public ResponseEvent()
    {
        this.service = "None";
        this.mServer = "";
        this.topicMain = "";
    }

    /**
     * Return the server context name.
     * 
     * @return server logical name
     * @see Naming.ServerNames.
     */
    public String getServer()
    {
        return mServer;
    }

    /**
     * Accesses the service property
     * 
     * @return event topic.
     */
    public String getTopic()
    {
        return this.service;
    }

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
     *            server logical name.
     * @see Naming.ServerNames
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
        this.service = aService;
    }

    /**
     * Returns a string representation of the properties of the object.
     * 
     * @return
     */
    public String toString()
    {
        StringBuilder buffer = new StringBuilder(200);
        buffer.append(" ");
        buffer.append(this.getClass().getName());
        buffer.append(" :: ");

        List accessors = Reflection.getAccessors(this.getClass());
        int len = accessors.size();
        for (int i = 0; i < len; i++)
        {
            Method aMethod = (Method) accessors.get(i);
            String propName = Reflection.getPropertyName(aMethod);
            buffer.append(propName);
            buffer.append(" = ");
            Object obj = Reflection.invokeAccessorMethod(this, propName);
            if (obj == null)
            {
                buffer.append("null");
            }
            else
            {
                buffer.append(obj);
            }
            if (i < len - 1)
            {
                buffer.append(",");
            }
        }
        return buffer.toString();
    }
}
