package mojo.km.context;

import mojo.km.messaging.IEvent;

/**
 * Interface that defines the behavior that occurs when an event is sent to a Context Manager.
 * 
 */
public interface ICommand
{
	/**
	 * Implement specific behavior when an event is sent to an application context.
	 * 
	 * @param event -
	 *            input event bean.
	 * @throws Exception
	 */
	void execute(IEvent event) throws Exception;
}
