package mojo.km.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mojo.km.messaging.IEvent;
import mojo.km.naming.MappingConstants;

/**
 * Responsible for defining meta data that will be used to enable access to persistent data sources.
 * 
 * @author Eric A Amundson
 */
public class EntityMappingProperties extends GenericProperties
{
    public static final String CONTEXTKEY = "contextKey";

    public static final String ENTITY = "name";

    public static final String MEMBERNAME = "memberName";

    public static final String UNDOABLE = "undoable";

    private String contextKey;

    private String entity;

    private boolean isBuffer;

    private boolean isModified = false;

    private boolean isReporting;

    private String memberName;

    private List queryCallbacks;

    private Map queryCallbacksMap;

    private List saveCallbacks;
    
    private SaveCallbackProperties sProps;

    private boolean undoable = false;

    /**
     *  
     */
    public EntityMappingProperties()
    {
        queryCallbacks = new ArrayList();
        queryCallbacksMap = new HashMap();
        saveCallbacks = new ArrayList();
    }

    /**
     * @param eventQueryProperty
     * @roseuid 404DA1FB0242
     */
    public void addQueryCallbacks(EventQueryProperties qProps)
    {
        markModified();
        queryCallbacks.add(qProps);
        queryCallbacksMap.put(qProps.getEventName(), qProps);
    }

    /**
     * @param saveCallback
     * @roseuid 404DA1FB0226
     */
    // TODO Is this method needed?
    public void addSaveCallback(List saveCallback)
    {
        markModified();
        saveCallbacks.addAll(saveCallback);
    }

    public void addSaveCallback(SaveCallbackProperties sProps)
    {
        saveCallbacks.add(sProps);
    }

    /**
     * @param eventName
     * @roseuid 4058A6FE02D4
     */
    //Fix to return null if eventName not found. MTP 02/24/05
    public EventQueryProperties getEventQueryProperties(String eventName)
    {
        /*EventQueryProperties eProps = null;
        Iterator i = queryCallbacks.iterator();
        while (i.hasNext())
        {
            eProps = (EventQueryProperties) i.next();
            if (eProps.getEventName().equals(eventName))
            {
                return eProps;
            }
        }*/
        return (EventQueryProperties) this.queryCallbacksMap.get(eventName);

    }

    public String getContextKey()
    {
        return contextKey;
    }

    public String getEntity()
    {
        return entity;
    }

    public EventQueryProperties getEventQueryProperties(Class aClass, String connectionName)
    {
        Iterator i = queryCallbacks.iterator();
        while (i.hasNext())
        {
            EventQueryProperties eProps = (EventQueryProperties) i.next();
            if (eProps.getEventName().equals(aClass.getName()) && eProps.getConnectionName().equals(connectionName))
            {
                return eProps;
            }
        }
        return null;
    }

    //	Fix to return null if event not found. MTP 02/24/05
    public EventQueryProperties getEventQueryProperties(IEvent anEvent, String connectionName)
    {
        EventQueryProperties eProps = null;
        Iterator i = queryCallbacks.iterator();
        while (i.hasNext())
        {
            eProps = (EventQueryProperties) i.next();
            if (eProps.getEventName().equals(anEvent.getClass().getName())
                    && eProps.getConnectionName().equals(connectionName))
            {
                return eProps;
            }
        }
        return null;
    }

    /**
     * @param entity
     * @return java.lang.String
     * @roseuid 404DA1FB0225
     */
    public String getMemberName()
    {
        return memberName;
    }

    public Iterator getQueryCallbacks()
    {
        return queryCallbacks.iterator();
    }
    
    public List getQueryCallbackList()
    {
    	return queryCallbacks;
    }
    
    /**
     * @return Returns the sProps.
     */
    public SaveCallbackProperties getSaveCallbackProperties()
    {
        return this.sProps;
    }

    /**
     * @param connectionName
     * @return mojo.km.config.SaveCallbackProperties
     * @deprecated
     * @roseuid 404DA1FB0275
     */
    //	Fix to return null if callback not found. MTP 02/24/05
    public SaveCallbackProperties getSaveCallbackProperties(String connectionName)
    {
        SaveCallbackProperties eProps = null;
        Iterator i = saveCallbacks.iterator();
        while (i.hasNext())
        {
            eProps = (SaveCallbackProperties) i.next();
            if (eProps.getConnectionName().equals(connectionName))
            {
                return eProps;
            }
        }
        return null;
    }

    public Iterator getSaveCallbacks()
    {
        return saveCallbacks.iterator();
    }   

    public boolean isBufferMapping()
    {
        return this.isBuffer;
    }

    public boolean isModified()
    {
        return isModified;
    }

    public boolean isReportMapping()
    {
        return this.isReporting;
    }

    public boolean isUndoable()
    {
        return undoable;
    }

    public void markModified()
    {
        isModified = true;
    }

    /**
     * @param contextKey
     * @roseuid 404DA1FB021A
     */
    public void setContextKey(String aContextKey)
    {
        markModified();
        this.contextKey = aContextKey;
        if (aContextKey.startsWith(MappingConstants.REPORTING_CONTEXT))
        {
            this.isReporting = true;
        }
        else
        {
            this.isReporting = false;
        }

        if (aContextKey.startsWith(MappingConstants.BUFFER_MAPPING_CONTEXT))
        {
            this.isBuffer = true;
        }
        else
        {
            this.isBuffer = false;
        }
    }

    /**
     * @param entity
     * @roseuid 404DA1FB021A
     */
    public void setEntity(String anEntity)
    {
        markModified();
        this.entity = anEntity;
    }

    /**
     * @param entity
     * @roseuid 404DA1FB021A
     */
    public void setMemberName(String aMemberName)
    {
        markModified();
        this.memberName = aMemberName;
    }
    /**
     * @param props The sProps to set.
     */
    public void setSaveCallbackProperties(SaveCallbackProperties props)
    {
        this.sProps = props;
    }

    public void setUndoable(String value)
    {
        if (value != null && value.equals("true"))
        {
            undoable = true;
        }
    }

    public String toString()
    {
        return this.entity + MojoProperties.CONTEXTSEPARATOR + this.memberName;
    }

    public void unmarkModified()
    {
        isModified = false;
    }
}
