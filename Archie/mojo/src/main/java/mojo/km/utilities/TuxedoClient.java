//Source file: Z:/java.vob/SystemServiceLayer/Request/QueueStrategy.java

package mojo.km.utilities;

import mojo.km.naming.*;
import java.io.*;

/**
 * Responsible for implementing a strategy to queue events to a TP monitor. The current implementation is specific to TUXEDO.
 * @author Eric A Amundson
 * @modelguid {15BF2ED5-7FC1-45DB-8811-77BBE1B37831}
 */
public class TuxedoClient {
    //Initialize JNI implementation of the post event.
    static {
        System.loadLibrary("TuxedoClientJNI");
    }

    /** Default constructor 
     * @modelguid {68FBE948-C898-47B2-8158-8AB52827C734}
     */
    public TuxedoClient() {
        peer = create();
    }

    /**
     *    Factory method to get new Tuxedo client.
     *    @return new instance of this class.
     * @modelguid {C5C24610-F3D1-4730-8A73-F1D68DE34FA1}
     */
    public static TuxedoClient getInstance() {
        return sharedInstance;
    }

    /**
     *     Constructs the C++ peer.
     *     @return the pointer variable to the C++ peer.
     * @modelguid {C9DFA2BF-F9E8-4045-B9D5-DBA2EA7E46A5}
     */
    native long create();

    /** Destroys the C++ peer. 
     * @modelguid {85DA5104-E87E-4C64-ADAE-220A730096B4}
     */
    native void destroy();

    /**
     *     Make a tuxedo queue call.
     *     @param service - request service name
     *     @param buffer - serialized IEvent
     *     @exception IOException thrown on error.
     *     @modelguid {00A3D543-3C06-459C-B54F-968EED024648}
     */
    public native void queue(String service, byte[] buffer) throws IOException;

    /**
     *     Process the buffer into a tuxedo tpcall.  (Utility function. )
     *     @param service - request service name
     *     @param buffer - serialized IEvent
     *     @exception IOException thrown on error.
     *     @modelguid {0BCFA700-AFF9-49F9-9209-7440DE4D65EF}
     */
    public native void process(String service, byte[] buffer) throws IOException;

    /**
     * Process a tuxedo request reply request.
     *     @param service - request service name
     *     @param buffer - serialized IEvent
     *     @return serialize IEvent object.
     *     @exception IOException thrown on error.
     * @modelguid {C49A1088-900C-4E68-9C5E-5C6EC7EFBFD3}
     */
    public native byte[] request(String service, byte[] buffer) throws IOException;

    /**
     * 	Send reply back to requesting service.
     *     @param service - request service name
     *     @param buffer - serialized IEvent
     *     @exception IOException thrown on error.
     * 	@modelguid {8950F693-F5D9-4C15-B3F8-C014D085A9D1}
     */
    public native void reply(String service, byte[] buffer) throws IOException;

    /**
     * Send a raw byte buffer as an event.
     *     @param service - request service name
     *     @param event - serialized IEvent
     * @modelguid {34A8A3FD-B9F7-4674-977C-35253686E14C}
     */
    public native void sendEvent(String service, byte[] event);

    /** @return Tuxedo allocated user id. 
     * @modelguid {BA6A43B7-B77A-451E-B4CC-71D69008DDF6}
     */
    public native byte[] getClientID();

    /**
     *    wrap Tuxedo publishing side of pub sub client method.
     *    @param clientID - client to publish to.
     *    @param buffer - serialized IEvent data.
     * @modelguid {8B1E8E43-101C-4053-8DBC-682425EE134C}
     */
    public native void notify(byte[] clientID, byte[] buffer);

    /**
     *    wrap receiving side of TUXEDO pub/sub capability
     *    @return serialized IEvent object.
     * @modelguid {910A7078-92E8-4FA4-ADD9-81548C18D20B}
     */
    public native byte[] receiveEvent();

    /**
     *    wrap receiving side of TUXEDO pub/sub capability
     *	@param aStr - subscription topic.
     *    @return serialized IEvent object.
     * @modelguid {EBC2BE27-CA8C-4688-B85E-7A0DABEDBB0E}
     */
    public native byte[] receiveEvent(String aStr);

    /**
     * 	    Enable application logging with in a service context.
     *     @param msg - message to be logged in TUXEDO log file.
     * 	    @modelguid {5187ED86-0774-42ED-B6FA-4BA12C19F890}
     */
    public native void contextMessage(String msg);

    /**
     * 	    Enable application logging with in a service context.
     *     @param msg - <CODE>byte[]</CODE> message to be logged in TUXEDO log file.
     * 	    @modelguid {12665704-8477-4B74-8F54-51C7C098A44E}
     */
    public native void contextMessage(byte[] msg);

	/** @modelguid {B639BE7F-5892-422E-9971-097B75C26F55} */
    protected void finalize() {
        try {
            super.finalize();
        } catch (Throwable t) {
            System.err.println(t.getMessage());
        }
        destroy();
    }

    /**
     * Send a raw byte buffer as an event.
     *     @param event - serialized IEvent
     * @modelguid {B388FFFC-6F25-44E4-9CD7-05C1062E3A88}
     */
    public native void broadcast(byte[] event);

	/** @modelguid {EB04F7E6-25D3-4A82-8CF6-0D71B6283328} */
    static TuxedoClient sharedInstance = new TuxedoClient();

    /** Pointer to the C++ peer. 
     * @modelguid {632A315B-D758-4B50-8736-E6FA54888B6D}
     */
    long peer;
}
