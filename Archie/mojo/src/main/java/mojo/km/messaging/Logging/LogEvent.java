//Source file: Z:/java.vob/SystemServiceLayer/Request/RequestEvent.java

package mojo.km.messaging.Logging;

import mojo.km.service.*;
import mojo.km.naming.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.SimpleDateFormat;
import mojo.km.messaging.IEvent;
import mojo.km.context.*;

/**
 * Responsible for holding information related to system logging.  Info include: Server name, User, Location, Message, etc.
 *@author Eric A Amundson
 * @modelguid {E702D746-C9C4-4CF8-86F2-39936C494EC5}
 */
public class LogEvent implements IEvent, Serializable {
   /*
   Interface required by receiving container.
   */

    /**
     * Implement IEvent interface.
     *     @return hash code.
     * @modelguid {AF5B1886-3A46-4F0B-87B3-EF3585A22D72}
     */
    public String hashKey() {
        StringBuffer aStr = new StringBuffer(50);
        aStr.append(this.getClass().getName()).toString();
        return aStr.toString();
    }

   /*
   Interface required by receiving container.
   */

    /**
     * Return the service id.
     *     @return event topic.
     * @modelguid {33729EB3-FDB3-436E-BF43-C679EDD4A7C7}
     */
    public String getTopic() {
        return service;
    }

    /**
     *     Set the service the event is to be associated with.
     *     @param aService - event topic.
     * @modelguid {3768442B-9070-4A15-9BE6-EE28628E94F2}
     */
    public void setTopic(String aService) {
        service = aService;
    }

    /**
     * Construct with input message buffer.
     * @param msg - holds byte buffer with log message.
     * @modelguid {8AEEC321-81FB-4DBF-9337-B645FED657D8}
     */
    public LogEvent(byte[] msg) {
        message = new String(msg);
        timeStamp = new Date();
        executableID = ContextManager.getServerName();
    }

    /** Default constructor 
     * @modelguid {26AEF97A-5505-424C-BE35-A58375CC7E44}
     */
    public LogEvent() {
    }

    /**
     *    Serialize event to string.
     *    @return event data.
     * @modelguid {D0FF9131-2ADF-4C8B-8CA5-111AB06C059F}
     */
    public String toString() {
        if (message.length() > 2) {
            return executableID + " : " + ContextManager.getCurrentUser() + " : " + codeLocation + " : " +
                dateFormat.format(timeStamp) + " : " + message;
        }
        return message;
    }

    /**
     * Return the server context name.
     *     @return server name of event.
     * @modelguid {92A9983C-43F4-4A2A-8C31-7D6F009EC8E8}
     */
    public String getServer() {
        return mServer;
    }

    /**
     * Set the value of the server context name.
     *     @param name - server event will be posted to
     * @modelguid {775F3BFA-16E1-4920-B741-C6437607409B}
     */
    public void setServer(String name) {
        mServer = name;
    }

	/** @modelguid {D754037A-1583-4782-AF68-E9C860E354DB} */
    private String service;
	/** @modelguid {7D63E095-D37C-4EA1-B249-6BF605CDBA2A} */
    private String executableID = "";
	/** @modelguid {59EBFDCB-D48B-46EE-9FEA-DB326CFCCC93} */
    private String codeLocation = "";
	/** @modelguid {78834083-8704-40B1-81C8-048225C99857} */
    private String message = "";
	/** @modelguid {390204E7-A8D7-4718-831C-4B45C2D0AEA3} */
    private Date timeStamp = null;
	/** @modelguid {66BCFCD4-BD3F-4D05-BCCF-A50EB4CE15DE} */
    transient SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	/** @modelguid {8E7185DE-E148-4661-8A03-63126AE9EB8E} */
    private String mServer = "";
}
