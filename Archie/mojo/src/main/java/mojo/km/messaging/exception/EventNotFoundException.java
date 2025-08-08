/*
 * Created on Aug 11, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.messaging.exception;

/**
 * Exception that is thrown when the event/service is not found.
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EventNotFoundException extends RuntimeException
{
	public EventNotFoundException(String msg)
	{
		super(msg);
	}
}
