//Source file: Z:/java.vob/BusinessServiceLayer/BusinessContext/BusinessEventBroadcaster.java

package mojo.km.context;

import mojo.km.messaging.IEvent;

/**
 * Interface which defines the broadcast interface to be used by the business contexts to send
 * events to their appropriate listeners.
 * 
 * @author Eric A Amundson
 */
public interface IEventBroadcaster
{
	/**
	 * Adds a listener.
	 * 
	 * @param listener
	 *            the listener to add
	 * @param hashKey -
	 *            lookup hash index
	 * @see #removeListener
	 */
	public void addListener(ICommand listener, String hashKey);

	/**
	 * Check for registered listener
	 * 
	 * @param hashKey -
	 *            lookup key
	 * @return true if listener exists
	 */
	public boolean hasListener(String hashKey);

	/**
	 * Broadcasts an event to all of the listeners. The listener must have method with the specified
	 * name and a signature of "(EventObject)", like: "public void callMe(EventObject evt)".
	 * 
	 * @param event
	 *            the event to broadcast
	 */
	public void fireEvent(IEvent event);

	/**
	 * Queue a response event for the reply back to the requesting system context.
	 * 
	 * @param event
	 *            the reply event.
	 */
	public void addResponse(IEvent event);
}
