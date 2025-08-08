//Source file: Z:/java.vob/MessagingLayer/SessionEvents/LoginResponse.java

package mojo.km.messaging.Composite;

import java.util.Vector;
import java.util.Enumeration;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.RequestEvent;

/**
 * Returms a composite of events from a service request to another system context. E.G.  if a request were made to the get
 * account information.  All return events would be sent back in this composite response.
 * @author Eric Amundson
 * @version 1.0
 * @type Product Requirement
 * @modelguid {8F80B4DC-0A49-48CC-8433-4C1A69766D85}
 */
public class CompositeRequest extends RequestEvent {
    /**
     * @associates IEvent 
     * @modelguid {7169052E-1AAE-4A8A-8996-E510CE27496F}
     */
    private Vector requests = new Vector();

    /** Default constructor 
     * @modelguid {9E332F3A-2C9C-4AA3-9CF7-47F3F67304CC}
     */
    public CompositeRequest() {
    }

    /**
     * Add a new response to the composite response.
     *     @param event - add response event to composite.
     * @modelguid {4E460410-B9F5-4547-8971-273463A70A47}
     */

    public void addRequest(IEvent event) {
        //System.out.println( "adding " + event );

        if (!requests.contains(event)) {
        	requests.addElement(event);
        }
    }

    /**
     * Check if any response are returned in composite.
     *    @return true if has any response events.
     * @modelguid {540041C8-FD35-4842-80BB-C890F4C47A48}
     */
    public boolean hasRequests() {
        return (requests.size() > 0);
    }

    /**
     * Return an enumeration of all service responses.
     *    @return an enumeration of responses.
     * @modelguid {E821BD5D-59CA-4B37-9217-785B23F40905}
     */
    public Enumeration getRequests() {
        return requests.elements();
    }
}
