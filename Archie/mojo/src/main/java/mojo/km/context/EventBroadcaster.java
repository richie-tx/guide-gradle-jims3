package mojo.km.context;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import mojo.km.exceptionhandling.ExceptionHandler;

/**
 * EventBroadcaster provides a generic way to broadcast events
 * to a group of listeners.
 *
 @author Eric A Amundson
 * @version 1.0
 * @modelguid {130C7DDA-5CDC-4D05-A4E2-3EAA4DCB102D}
 */
public class EventBroadcaster implements IEventBroadcaster {
    /**
     *     The listeners waiting for events to be broadcast.
     *     @see #addListener
     *     @see #removeListener
     * @associates Vector
     * @modelguid {B14C5AA1-AAD6-48A2-816D-1327418050D0}
     */
    Hashtable listeners = new Hashtable();

    /**
     *     Constructs a default <code>EventBroadcaster</code> object.
     * @modelguid {39085569-0F21-42C6-A881-0F1B6B309574}
     */
    public EventBroadcaster() {
    }

    /**
     *     Adds a listener.
     *     @param listener the listener to add
     *     @param hashKey - key for listener lookup
     *     @see #removeListener
     * @modelguid {DB81FA32-F872-4FA8-92C2-AC50B570111A}
     */
    public synchronized void addListener(ICommand listener, String hashKey) {
        Vector list = null;
        if (!listeners.containsKey(hashKey)) {
            list = new Vector();
            listeners.put(hashKey, list);
        } else {
            list = (Vector)listeners.get(hashKey);
        }
        list.addElement(listener);
    }

    /**
     *     Removes a listener.
     *     @param listener the listener to remove
     *     @param hashKey of the item to register.
     *     @see #addListener
     * @modelguid {92FAA6A6-6B6B-416B-9B5A-74E638C3AF58}
     */
    public synchronized void removeListener(ICommand listener, String hashKey) {
        if (listeners.containsKey(hashKey)) {
            Vector list = (Vector)listeners.get(hashKey);
            while (list.removeElement(listener)) {
            }
            if (list.isEmpty()) {
                listeners.remove(hashKey);
            }
        }
    }

    /**
     *     Check for existing listener.
     @param hashKey - key for listner lookup.
     @return true if listerer is registered.
	 * @modelguid {D74A4050-1F75-4CE4-A96A-242D42DE62B6}
     */
    public synchronized boolean hasListener(String hashKey) {
        return listeners.containsKey(hashKey);
    }

    /**
     *     Broadcasts an event to all of the listeners.
     *     The listener must have method with the specified name and a signature
     *     of "(IEvent)", like: "public void callMe(IEvent evt)".
     *     @param event the event to broadcast
     * @modelguid {CD7213A5-0FF9-433C-9338-763F5007D192}
     */
    public void fireEvent(IEvent event) {
        EventManager.getSharedInstance(EventManager.REPLY).getReply();
        Vector fireToListeners = new Vector();
        String hashKey = event.hashKey();
        synchronized(listeners) {
            if (listeners.containsKey(hashKey)) {
                Vector list = (Vector)listeners.get(hashKey);
                fireToListeners = (Vector)list.clone();
            }
        }
        for (Enumeration e = fireToListeners.elements(); e.hasMoreElements(); ) {
            try {
                ICommand listener = null;
                listener = (ICommand)e.nextElement();
                listener.execute(event);
            }
            catch (Exception ex) {
                ExceptionHandler.executeCallbacks( ex );
            }
        }
    }
    
    /**
     * @ Removes all listeners.
     * @param listener the listener to remove
     * @see #addListener
     * @since VCafe 3.0 
     * @modelguid {C6645B7F-373F-404A-B85B-B34ABB3E8B93}
     */
    public void removeAllListeners(ICommand listener) { }

    /**
     *     Queue a response event for the reply back to the requesting system context.
     *
     *     @param event the reply event.
     * @modelguid {21CA293B-FF60-4A02-9EFC-7E0685BF9566}
     */
    public void addResponse(IEvent event) { }

    /**
    Update commands with passed object data.
    @param updateObject - update data.
	 * @modelguid {1F1374C3-2E30-4779-A1F6-BC8D15B58256}
    */
    public void updateCommands(Object updateObject) {
    }

    /**
     * Clear all registered listeners.
     * @modelguid {7F6C9C20-7BF0-43D6-AC6B-D417E9F5D29C}
     */
    public void resetListeners(){ }
}
