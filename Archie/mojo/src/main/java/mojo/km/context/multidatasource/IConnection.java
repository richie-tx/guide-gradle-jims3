package mojo.km.context.multidatasource;

import mojo.km.config.EntityMappingProperties;
import mojo.km.config.EventQueryProperties;
import mojo.km.config.SaveCallbackProperties;

/**
 * Defines interface to resource connections.
 *  
 */
public interface IConnection
{
    void close();
    
    String createKey(String operation, EntityMappingProperties eProps);

    String getRegion();

    Object getResource();

    Object getQueryStatement(EventQueryProperties eProps);
    
    Object getInsertStatement(SaveCallbackProperties sProps);
    
    Object getUpdateStatement(SaveCallbackProperties sProps);

    Object getStatement(String key);

    void init(String name);

    boolean isBad();

    void open(String URL, int iPort);

    void open(String URL, String userID, String password);

    void resetUsage();

    void setName(String aName);
}
