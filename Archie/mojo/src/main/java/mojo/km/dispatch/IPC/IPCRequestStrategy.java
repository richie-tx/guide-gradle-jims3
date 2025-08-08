//Source file: z:/java.vob/MessagingLayer/Event/RequestStrategy.java

package mojo.km.dispatch.IPC;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.logging.LogUtilities;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.noop.NoReply;

/**
 *   Class responsible for implementing a request reply strategy.
 * When the strategy is invoke the event will be sent to the service and a response will be immediately returned.
 * Once a the response is returned it will be posted to the current ContextManager, or a getReply call can be made to
 * access the return event.
 *@author Eric A Amundosn
 * @modelguid {BD1F36F9-88E1-4669-93F6-FA45A32061FA}
 */
public class IPCRequestStrategy extends EventManager {
    /** Initialize output stream with hard coded host name. 
     * @modelguid {7806ACA4-53EB-42C9-942C-9D384FA124BD}
     */
    public static void initialize() {
        try {
            StringTokenizer aTok = new StringTokenizer(host, ":");
            if (aTok.countTokens() != 2) {
                return;
            }
            String hostName = aTok.nextToken();
            Long port = new Long(aTok.nextToken());
            InetAddress serverAddr = InetAddress.getByName(hostName);
            sock = new Socket(serverAddr, port.intValue());
            outStream = new ObjectOutputStream(sock.getOutputStream());
            inStream = new ObjectInputStream(sock.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize output stream with Host URL.
     *     @param aHost - the host URL.
     * @modelguid {EA579069-3B89-44B3-94B5-192E3E91D44F}
     */
    public static void initialize(String aHost) {
        try {
            host = aHost;
            StringTokenizer aTok = new StringTokenizer(host, ":");
            if (aTok.countTokens() != 2) {
                return;
            }
            String hostName = aTok.nextToken();
            Long port = new Long(aTok.nextToken());
            InetAddress serverAddr = InetAddress.getByName(hostName);
            sock = new Socket(serverAddr, port.intValue());
            outStream = new ObjectOutputStream(sock.getOutputStream());
            inStream = new ObjectInputStream(sock.getInputStream());
            LogUtilities.contextMessage("Completed connection to Current Price database.");
        } catch (IOException e) {
            LogUtilities.contextMessage(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (NumberFormatException e) {
            LogUtilities.contextMessage(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

	/** @modelguid {10C9D348-2687-40FB-ACB9-51A30B0ADDC5} */
    private IEvent replyEvent = new NoReply();

	/** @modelguid {18B1570A-6BFE-4200-9272-A6A25D778260} */
    public IPCRequestStrategy() { }

    /**
     *	Post event to host service.
     *     @param event - the event to be sent.
     *	   @modelguid {978D4037-6EB9-4D94-AB9F-DCC6C653FDE4}
     */
    public void postEvent(IEvent event) {
        try {
            outStream.writeBoolean(true);
            outStream.writeObject(event);
            outStream.flush();
            outStream.reset();
            //ZipInputStream zipIStream = new ZipInputStream(inStream);
            //zipIStream.getNextEntry();
            replyEvent = (IEvent)inStream.readObject();
            //zipIStream.closeEntry();
            if (ContextManager.currentContext() != null && ContextManager.currentContext().hasListener(replyEvent.hashKey())) {
                ContextManager.currentContext().postEvent(replyEvent);
            }
        } catch (Throwable x) {
            x.printStackTrace();
            throw new RuntimeException(x.getMessage());
        }
    }

    /**
     *    Post byte array event.
     *     @param service - event topic
     *     @param event - serialized version of IEvent.
     * @modelguid {DA30E910-A6FB-43D0-870D-01A5470BA22C}
     */
    public void postEvent(String service, byte[] event) {
        try {
            outStream.writeBoolean(true);
            outStream.write(event);
            //ZipInputStream zipIStream = new ZipInputStream(inStream);
            //zipIStream.getNextEntry();
            ObjectInputStream iStream = new ObjectInputStream(inStream);
            replyEvent = (IEvent)iStream.readObject();
            //zipIStream.closeEntry();
            if (ContextManager.currentContext() != null && ContextManager.currentContext().hasListener(replyEvent.hashKey())) {
                ContextManager.currentContext().postEvent(replyEvent);
            }
            inStream.close();
        } catch (Throwable x) {
            initialize();
            x.printStackTrace();
        }
    }

    /**
     *	   Returns the reply event.
     @return IEvent object from IPC context.
     *	   @modelguid {2278BA43-77A3-436B-A672-70223C0C1529}
     */
    public IEvent getReply() { return replyEvent; }

    /** Close output stream used by IPC strategy. 
     * @modelguid {93D24206-8472-4A79-9965-8BC85C6BCC21}
     */
    public static void close() {
        try {
            if (sock != null) {
                outStream.writeBoolean(false);
                //sock.close();
            }
        } catch (IOException e) {
        }
    }

	/** @modelguid {8C3F5196-2F01-424F-897E-3E2F5DE3FBCF} */
    private static String host = "cxrequest1";
	/** @modelguid {16346F1A-BC71-40A8-B427-0F2956F971B7} */
    private static Socket sock = null;
	/** @modelguid {570BDFC5-0273-43C9-B664-6DF20A09E69F} */
    private static ObjectOutputStream outStream = null;
	/** @modelguid {9AFA9498-414B-43F6-AA80-B0420C063682} */
    private static ObjectInputStream inStream = null;
}
