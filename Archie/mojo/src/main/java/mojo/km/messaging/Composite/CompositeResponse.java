package mojo.km.messaging.Composite;

import java.util.ArrayList;
import java.util.List;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * Returms a composite of events from a service request to another system context. E.G. if a request
 * were made to the get account information. All return events would be sent back in this composite
 * response.
 */
public class CompositeResponse extends ResponseEvent
{
	private ArrayList replies;

	public CompositeResponse()
	{
		replies = new ArrayList();		
	}

	/**
	 * Add a new response to the composite response.
	 * 
	 * @param event -
	 *            add response event to composite.
	 */
	public void addResponse(IEvent event)
	{
		if (event instanceof CompositeResponse)
		{
			CompositeResponse r = (CompositeResponse) event;
			replies.addAll(r.getResponses());
		}
		else
		{
			replies.add(event);
		}
	}

	/**
	 * Check if any response are returned in composite.
	 * 
	 * @return true if has any response events.
	 */
	public boolean hasResponses()
	{
		return replies.isEmpty() == false;
	}

	/**
	 * Return a List of all service responses.
	 * 
	 * @return an enumeration of responses.
	 */
	public List getResponses()
	{
		return replies;
	}
}
