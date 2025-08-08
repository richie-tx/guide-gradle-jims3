//Source file: Z:/java.vob/SystemServiceLayer/Request/PostEventStrategy.java

package mojo.km.dispatch;

import mojo.km.messaging.IEvent;

/**
 * Defines interface for enabling the posting of an event to any application context.
 *@author Eric A Amundson
 *@since 20000105
 * @modelguid {2AC0A199-D7C1-4FE6-A4A0-4312F182E300}
 */
public interface IDispatch {
    /**
     *    Post an event to middleware layers.
     *    @param event - data being posted
     *    @modelguid {354D2887-F1E3-4F60-8EF6-465E829FE8CF}
     */
    public void postEvent(IEvent event);

    /**
     *    Return a reply event.
     *     @return reply event.
     * @modelguid {5DCF3038-C600-4123-B5E8-6911D94D2D1A}
     */
    public IEvent getReply();
}
