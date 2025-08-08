/*
 * Created on Feb 15, 2006
 *
 */
package ui.action;

import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;

/**
 * @author jfisher
 *
 */
public interface IAction
{
	public CompositeResponse handleRequest(RequestEvent event);
}
