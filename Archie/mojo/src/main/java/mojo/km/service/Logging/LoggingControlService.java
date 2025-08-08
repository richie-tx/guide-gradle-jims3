//Source file: Z:/java.vob/MessagingLayer/MessageProcessing/VTService.java

package mojo.km.service.Logging;

import mojo.km.logging.*;
import mojo.km.service.*;
import mojo.km.config.AppProperties;
import mojo.km.context.*;
import java.io.*;
import java.net.*;

/**
 *    Resposible for handling receive log level  signal events from a the loggingSignalService.  Once a
 * log level event is received the log level for the specific context is set to the level defined in the received event.
 * @author Eric Amundson
 * @version 1.0
 * @since 20000601
 * @modelguid {F1AB07FF-8BB8-4E0A-AEC1-FFB7B0640EB7}
 */
public class LoggingControlService extends SessionService {
    /** Default constructor noop 
     * @modelguid {F83B571C-C5A4-4FCD-83F2-0EFAAD397105}
     */
    public LoggingControlService() {
    }

    /**
     * 	Set the port id to define the session id.
     * 	@param id or port id.
     * 	@return true if session id is set.
     * @modelguid {2703FE3E-7DB4-4A7F-8F1F-032CAD5258C2}
     */
    public boolean setSessionID(String id) {
        boolean retVal = true;
        try {
            port = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            retVal = false;
        }
        return retVal;
    }

    /**
     * 	Get the URL of the current session.
     * 	@return a valid URL.
     * @modelguid {73204494-C42B-4D13-B2BA-2BFBBD106C4B}
     */
    public String getSessionID() {
        String retVal = "";
        try {
            retVal = InetAddress.getLocalHost().getHostAddress() + ":" + port;
        } catch (Throwable e) {
        }
        return retVal;
    }

    /**
     * 	   Starts the objects thread.
     * 	   @modelguid {22572263-00AD-4D04-9D02-4AC004646154}
     */
    public void start() {
        try {
            URL aUrl = new URL(AppProperties.getInstance().getProperty("LOGGROUP"));
            mCastIP = aUrl.getHost();
            port = aUrl.getPort();
            InetAddress group = InetAddress.getByName(mCastIP);
            theSocket = new MulticastSocket(port);
            theSocket.joinGroup(group);
            byte[] buff = new byte[500000];
            while (true) {
                //synchronized (this) {
                thePacket = new DatagramPacket(buff, 50000);
                theSocket.receive(thePacket);
                int size = thePacket.getLength();
                byte[] dst = new byte[size];
                System.arraycopy(buff, 0, dst, 0, size);
                push(dst);
                //}
            }
        } catch (IOException e) {
        }
    }

    /**
     * 	   Stop the data aquisition service.
     * 	   @modelguid {54C9D299-5149-4340-9F93-07343E3EF0CE}
     */
    public void stop() {
        finalize();
    }

    /**
     * 	Execute the event loop.
     * 	   @modelguid {A41A9206-ED46-45F4-8B3F-FB2EA8CFA144}
     */
    public void run() {
        start();
    }

    /** Push events into the current context 
     * @modelguid {9174B627-DBCB-409C-8464-A05DC32CEF1B}
     */
    private void push(byte[] buffer) {
        // Print out the event spec
        ByteArrayInputStream inStream = new ByteArrayInputStream(buffer);
        try {
            DataInputStream dIStream = new DataInputStream(inStream);
            //String hash = oStream.readUTF();
            short logLevel = dIStream.readShort();
            String serverName = dIStream.readUTF();
            if (serverName.equals(ContextManager.getServerName()) || serverName.equals("All")) {
                LogStream.setlogging(logLevel);
            }
            inStream.close();
        } catch (IOException y) {
            y.printStackTrace();
        }
    }

    /** Cleanup log multicast socket. 
     * @modelguid {A95F9F69-BD48-4528-828A-BA2EF7361EC2}
     */
    protected void finalize() {
        try {
            super.finalize();
        } catch (Throwable t) {
            System.err.println(t.getMessage());
        }
        try {
            theSocket.close();
        } catch (Exception e) {
        }
    }

	/** @modelguid {C67EB3E1-E1C2-464A-A3C6-B32A20120CA1} */
    int port = 8900;

    /** Multicast URL 
     * @modelguid {9E529E37-67CF-4AD0-BC3A-6E3EDA136C51}
     */
    String mCastIP = "234.63.73.98";
	/** @modelguid {8A2FB7F3-CE90-4BEF-9BBB-CA0E7B5B6611} */
    MulticastSocket theSocket = null;
	/** @modelguid {AA6F7BB7-37A8-4E82-B663-70AAD13C7CB6} */
    DatagramPacket thePacket = null;
}
