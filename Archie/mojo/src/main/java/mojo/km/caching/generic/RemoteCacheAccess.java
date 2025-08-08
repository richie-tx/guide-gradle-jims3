/*
 * Created on Oct 27, 2005
 *
 */
package mojo.km.caching.generic;

import messaging.cacheaccessevents.CacheGetEvent;
import messaging.cacheaccessevents.CachePutEvent;
import messaging.cacheaccessevents.CacheReplyEvent;
import mojo.km.caching.ICacheAccess;
import mojo.km.messaging.IEvent;
import mojo.km.config.CacheProperties;
import mojo.km.config.MojoProperties;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.context.ContextManager;
import org.apache.jcs.JCS;

/**
 * @author eamundson
 *  
 */
public class RemoteCacheAccess implements ICacheAccess
{

    static public class GetCommand implements mojo.km.context.ICommand
    {
        /**
         *  
         */
        public GetCommand()
        {
            super();
            // TODO Auto-generated constructor stub
        }

        /*
         * (non-Javadoc)
         * 
         * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
         */
        public void execute(IEvent event) throws Exception
        {
            // TODO Auto-generated method stub
            CacheGetEvent anEvent = (CacheGetEvent) event;
            CacheProperties.getInstance().setIsRemote(anEvent.getTypeName(), false);

            CacheReplyEvent replyEvent = new CacheReplyEvent();
            replyEvent.setId(anEvent.getId());
            Object returnObject = null;
            try
            {
                Class pType = Class.forName(anEvent.getTypeName());
                returnObject = CacheManager.get(pType, anEvent.getId());
            }
            catch (Throwable t)
            {
                return;
            }
            replyEvent.setCachedObject(returnObject);
            CacheProperties.getInstance().setIsRemote(anEvent.getTypeName(), true);
            EventManager.getSharedInstance(EventManager.REPLY).postEvent(replyEvent);
        }

        /*
         * (non-Javadoc)
         * 
         * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
         */
        public void onRegister(IEvent event)
        {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         * 
         * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
         */
        public void onUnregister(IEvent event)
        {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         * 
         * @see mojo.km.context.ICommand#update(java.lang.Object)
         */
        public void update(Object updateObject)
        {
            // TODO Auto-generated method stub

        }

    }

    static public class PutCommand implements mojo.km.context.ICommand
    {

        /**
         *  
         */
        public PutCommand()
        {
            super();
            // TODO Auto-generated constructor stub
        }

        /*
         * (non-Javadoc)
         * 
         * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
         */
        public void execute(IEvent event) throws Exception
        {
            // TODO Auto-generated method stub
            CachePutEvent anEvent = (CachePutEvent) event;
            CacheProperties.getInstance().setIsRemote(anEvent.getCachedObject().getClass().getName(), false);

            try
            {
                CacheManager.add(anEvent.getCachedObject(), anEvent.getId());
            }
            catch (Throwable t)
            {
                return;
            }
            CacheProperties.getInstance().setIsRemote(anEvent.getCachedObject().getClass().getName(), true);

        }

        /*
         * (non-Javadoc)
         * 
         * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
         */
        public void onRegister(IEvent event)
        {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         * 
         * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
         */
        public void onUnregister(IEvent event)
        {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         * 
         * @see mojo.km.context.ICommand#update(java.lang.Object)
         */
        public void update(Object updateObject)
        {
            // TODO Auto-generated method stub

        }

    }

    static public class RemoveCommand implements mojo.km.context.ICommand
    {
        /**
         *  
         */
        public RemoveCommand()
        {
            super();
            // TODO Auto-generated constructor stub
        }

        /*
         * (non-Javadoc)
         * 
         * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
         */
        public void execute(IEvent event) throws Exception
        {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         * 
         * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
         */
        public void onRegister(IEvent event)
        {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         * 
         * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
         */
        public void onUnregister(IEvent event)
        {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         * 
         * @see mojo.km.context.ICommand#update(java.lang.Object)
         */
        public void update(Object updateObject)
        {
            // TODO Auto-generated method stub

        }

    }

    JCS jcs = null;

    private String managedType = null;

    public RemoteCacheAccess(String typeName)
    {
        managedType = typeName;
        CacheProperties cacheProps = MojoProperties.getInstance().getCacheProperties();
        try
        {
            init(cacheProps.getRegion(managedType));
        }
        catch (Throwable t)
        {
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.caching.ICacheAccess#get(java.lang.String)
     */
    public Object get(String id)
    {
        // TODO Auto-generated method stub
        if (jcs.get(id) != null)
        {
            return jcs.get(id);
        }
        if (!ContextManager.isSet() || isRemoteServer())
        {
            return null;
        }

        CacheGetEvent getEvent = new CacheGetEvent();
        getEvent.setId(id);
        getEvent.setTypeName(managedType);
        mojo.km.dispatch.IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        try
        {
            dispatch.postEvent(getEvent);
        }
        catch (NullPointerException nullException)
        {
            return null;
        }
        CompositeResponse cEvent = (CompositeResponse) dispatch.getReply();

        ReturnException returnException = (ReturnException) MessageUtil.filterComposite(cEvent, ReturnException.class);
        if (returnException != null)
        {
            throw returnException;
        }

        CacheReplyEvent rEvent = (CacheReplyEvent) MessageUtil.filterComposite(cEvent, CacheReplyEvent.class);

        if (rEvent != null)
        {
            try
            {
                jcs.put(id, rEvent.getCachedObject());
            }
            catch (org.apache.jcs.access.exception.CacheException e)
            {
                System.err.println("Unable local store object");
            }
            return rEvent.getCachedObject();
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.caching.ICacheAccess#init(java.lang.String)
     */
    public void init(String region) throws mojo.km.caching.CacheException
    {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        try
        {
            jcs = JCS.getInstance(region);
        }
        catch (org.apache.jcs.access.exception.CacheException e)
        {
            throw new mojo.km.caching.CacheException(e.getMessage());
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.caching.ICacheAccess#invalidate(java.lang.String)
     */
    public void invalidate(String id) throws mojo.km.caching.CacheException
    {
        try
        {
            jcs.remove(id);
        }
        catch (org.apache.jcs.access.exception.CacheException e)
        {
            throw new mojo.km.caching.CacheException(e.getMessage());
        }
    }

    private boolean isRemoteServer()
    {
        return MojoProperties.getInstance().getActiveServerName().equals("RemoteCache");
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.caching.ICacheAccess#put(java.lang.String, java.lang.Object)
     */
    public void put(String id, Object item) throws mojo.km.caching.CacheException
    {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        try
        {
            jcs.put(id, item);
        }
        catch (org.apache.jcs.access.exception.CacheException e)
        {
            throw new mojo.km.caching.CacheException(e.getMessage());
        }
        if (!ContextManager.isSet())
        {
            return;
        }
        if (isRemoteServer())
        {
            return;
        }
        CachePutEvent putEvent = new CachePutEvent();
        putEvent.setId(id);
        putEvent.setCachedObject(item);
        mojo.km.dispatch.IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(putEvent);
        CompositeResponse cEvent = (CompositeResponse) dispatch.getReply();

        ReturnException returnException = (ReturnException) MessageUtil.filterComposite(cEvent, ReturnException.class);
        if (returnException != null)
        {
            throw returnException;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.caching.ICacheAccess#remove(java.lang.String)
     */
    public void remove(String id) throws mojo.km.caching.CacheException
    {
        try
        {
            jcs.remove(id);
        }
        catch (org.apache.jcs.access.exception.CacheException e)
        {
            throw new mojo.km.caching.CacheException(e.getMessage());
        }
    }
}
