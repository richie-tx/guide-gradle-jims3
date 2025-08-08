//Source file: Z:/java.vob/SystemServiceLayer/Request/QueueStrategy.java

package mojo.km.dispatch.Socket;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.noop.NoReply;

/**
 * Responsible for sending events to a TCP socket interface. These events will then be posted to a socket based host service.
 * @author Eric Amundson
 * @version 1.0
 * @since 20000315
 * @modelguid {F13AEE07-6671-4CC9-8D43-DDB476CD20E2}
 */
public class TCPPostEventStrategy extends EventManager {
    /** Hashtable holding all the sockets that maybe used by this strategy. 
     * @associates OutputStream
     * @modelguid {035A8FFC-DCE8-4BAA-8825-711675D05211}
     */
    static Hashtable sockets = new Hashtable();

	/** @modelguid {8D157764-F39B-4599-8E3B-E50F9AB9C334} */
    public TCPPostEventStrategy() {
    }

    /**
     * No op.
     *    @return NoReply
     * @modelguid {513BB2C7-FCCF-4BC4-A1EB-AE66FC79547A}
     */
    public IEvent getReply() {
        return new NoReply();
    }

    /**
     * Post event to tcp socket.
     * @param event - IEvent to be streamed to the TCP socket
     * @modelguid {51DC76BC-7A11-436D-A8A6-B4115F875121}
     */
    public void postEvent(IEvent event) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        String service = event.getTopic();
        try {
            ZipOutputStream zipOStream = new ZipOutputStream(outStream);
            zipOStream.putNextEntry(
                new ZipEntry(service));
            ObjectOutputStream oStream = new ObjectOutputStream(zipOStream);
            oStream.writeObject(event);
            zipOStream.closeEntry();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception.getMessage());
        }
        try {
            outStream.write(0); // terminate the message
            sendEvent(service, outStream);
        }
        catch (RuntimeException e) {
            // retry once (this gives it a chance to reconnect),
            // if that fails too, we let the exception go to a higher
            // authority.
            sendEvent(service, outStream);
        }
    }

    /**
     * Post serialized event in byte buffer to the given service name.
     * @param service - buffer holding service name
     * @param event - byte buffer holding serialized event.
     * @modelguid {51938830-3055-4340-9E77-0C58777446FB}
     */
    public void postEvent(String service, byte[] event) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            outStream.write(event);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception.getMessage());
        }
        try {
            outStream.write(0); // terminate the message
            sendEvent(service, outStream);
        }
        catch (RuntimeException e) {
            // retry once (this gives it a chance to reconnect),
            // if that fails too, we let the exception go to a higher
            // authority.
            sendEvent(service, outStream);
        }
    }

	/*
	  sendEvent is a private method, only to be called by the various
	  postEvent methods in this class.
	 * @modelguid {17A439EA-C70A-42A2-A337-E3C541CF250B}
	*/

    private void sendEvent(String service, ByteArrayOutputStream outStream) {
        OutputStream socketOutputStream = null;
        try {
            if (!sockets.containsKey(service)) {
                StringTokenizer tokenizer = new StringTokenizer(service, ":");
                if (tokenizer.countTokens() != 2) {
                    return;
                }
                String serverAddress = tokenizer.nextToken();
                int serverPort = Integer.parseInt(tokenizer.nextToken());
                Socket socket = new Socket(serverAddress, serverPort);
                socketOutputStream = socket.getOutputStream();
                sockets.put(service, socketOutputStream);
            }
            else {
                socketOutputStream = (OutputStream)sockets.get(service);
            }
        }
        catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
        if (socketOutputStream != null) {
            try {
                outStream.writeTo(socketOutputStream);
            }
            catch (Exception exception) {
                sockets.remove(service); // the write failed remove the socket
                throw new RuntimeException(exception.getMessage());
            }
        }
    }

    /** Cleanup sockets buffer used by this strategy. 
     * @modelguid {AD14FA77-E617-4484-AE93-428809136CC2}
     */
    protected void finalize() {
        try {
            super.finalize();
        } catch (Throwable t) {
            System.err.println(t.getMessage());
        }
        for (Enumeration e = sockets.elements(); e.hasMoreElements(); ) {
            OutputStream socket = (OutputStream)e.nextElement();
            try {
                socket.close();
            }
            catch (Exception exception) {
                // don't care
            }
        }
    }
}
