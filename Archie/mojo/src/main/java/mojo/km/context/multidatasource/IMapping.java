package mojo.km.context.multidatasource;

import mojo.km.persistence.PersistentObject;
import mojo.km.config.CallbackProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;

import java.util.List;
import java.util.Map;

public interface IMapping
{
    CallbackProperties getCallback();

    String getConnectionName();

    EntityMappingProperties getEntityMap();

    void init(String key);

    /**
     * Used when there are multiple retrievers
     * 
     * @param pType
     * @param anEvent
     * @return java.util.ArrayList
     */
    List retrieve(IEvent anEvent, Class pType, Map retVal);

    MetaDataResponseEvent retrieveMeta(IEvent anEvent, Class pType, Map retVal);

    /**
     * @param pObj
     * @roseuid 407E7D7F037E
     */
    void save(PersistentObject pObj);

    /**
     * @param callBack
     *            (EventQueryProperties or SaveCallbackProperties)
     */
    void setCallback(CallbackProperties callBack);

    /**
     * @param connectionName
     */
    void setConnectionName(String connectionName);

    /**
     * @param entityMap
     */
    void setEntityMap(EntityMappingProperties entityMap);
}