package mojo.km.messaging.noop;

import java.io.*;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.ResponseEvent;

/**
 *This event is used by IDispatch types as a return on the getReply method if no data is returned.
 *@author Eric A Amundson
 * @modelguid {F26B3EB8-0B97-41A1-919B-4A88DECFEB10}
 */
public class NoReply extends ResponseEvent implements Serializable {
	/** @modelguid {35D17627-3496-4B0C-9176-0BBED7498EEA} */
    public NoReply() {
    }

    /**
     *     Get hashkey use to filter on event listener
     *     @return hash code.
     * @modelguid {A076E6EF-5586-4373-939A-9272B478C7E7}
     */
    public String hashKey() {
        return "";
    }

    /**
     * Returns the service event is set to.
     *    @return event topic.
     * @modelguid {BA02783E-84BB-493A-B55C-9046F3CF7905}
     */
    public String getTopic() {
        return "None";
    }

    /**
     * Set the service the event is to be associated with.
     *    @param aService - event topic.
     * @modelguid {54EC0B2C-7BE4-4105-8A44-9283E1E08BFD}
     */
    public void setTopic(String aService) {
    }

    /**
     * Return the server context name.
     *    @return server name.
     * @modelguid {9E7098BD-5D40-49F3-BB6F-7B16E04CA8BB}
     */
    public String getServer() { return serverContext; }

    /**
     * Set the value of the server context name.
     *    @param name - valid logical server name, listed in Naming.ServerNames
     * @modelguid {2CA2BFC1-E15E-4110-80C4-7FE2B578FA16}
     */
    public void setServer(String name) {  }

	/** @modelguid {922C4C5F-B751-4212-9ABC-465042AAA8F1} */
    String serverContext = "None";
}
