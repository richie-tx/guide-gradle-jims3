//Source file: Z:/java.vob/SystemServiceLayer/Request/RequestEvent.java

package mojo.km.messaging;

import java.io.Serializable;

/**
 * IEvent is a event type that is related to a pure event driven system with
 * different services that have listeners related to them.. A event may be routed to any service that it is set.
 *@author Eric A Amundson
 *@version 1.0
 * @modelguid {1B50671D-CC39-4D77-8831-586DCA7A22C5}
 */
public interface IEvent extends Serializable, ITopic {
    /**
     * Return the server context name.
     *     @return server name
     *     @see Naming.ServerNames
     * @modelguid {2FE7F536-DA0A-4CB9-BF32-1901021249C0}
     */
    public String getServer();

    /**
     * Set the value of the server context name.
     *     @param name of server
     *     @see Naming.ServerNames.
     * @modelguid {3A774B01-015A-4C82-8710-E1446B0B99A9}
     */
    public void setServer(String name);
}
