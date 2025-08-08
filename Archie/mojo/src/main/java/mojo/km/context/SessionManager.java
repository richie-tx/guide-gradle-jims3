package mojo.km.context;

import java.util.List;

import mojo.km.caching.generic.CacheManager;

public class SessionManager
{
    private SessionManager()
    {
    }

    /**
     * Return client resources for the currently executing thread
     * 
     * @return resources for the calling thread
     */
    static Session getSession()
    {
        String sessionId = ContextManager.currentContext().getCurrentSessionID();
        Session session = (Session) CacheManager.get(Session.class, sessionId);
        if (session == null)
        {
            session = new Session();
            session.setSessionID(sessionId);
            CacheManager.add(session, sessionId);
        }
        return session;
    }

    /**
     * Return client resources for the currently executing
     * 
     * @return resources for the calling thread
     */
    static void collectSession()
    {
        CacheManager.remove(Session.class, ContextManager.currentContext().getCurrentSessionID());
    }

    /**
     * Return client resources for the currently executing
     * 
     * @return resources for the calling thread
     */
    static void initializeSession()
    {
        collectSession();
        getSession();
    }

    static boolean hasSession()
    {
        String sessionId = ContextManager.currentContext().getCurrentSessionID();
        return sessionId != null && !sessionId.equals("") && CacheManager.get(Session.class, sessionId) != null;
    }

    static List getSessions()
    {
        return CacheManager.getInstances(Session.class);
    }

    public static void invalidateCurrentSession()
    {
        CacheManager.remove(Session.class, ContextManager.currentContext().getCurrentSessionID());
    }
}