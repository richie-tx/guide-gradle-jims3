//Source file: Z:/java.vob/MessagingLayer/MessageProcessing/VTService.java

package mojo.km.service.Multicast;

import mojo.km.service.*;
import mojo.km.messaging.*;
import mojo.km.config.AppProperties;
import mojo.km.context.ContextManager;
import java.io.*;
import java.net.*;

/**
 *    Implements the interface to receive multicast events that other message processing services will use.
 * @author Eric A Amundson
 * @modelguid {C8552DC7-457A-4937-8F15-9602F1675157}
 */
public class MulticastService extends SessionService {
	/** @modelguid {BAC518A6-2DC6-4B00-A94D-FC71F6A08D23} */
    public MulticastService() {
    }

    /**
     * 	Set the port id to define the session id.
     * 	@param id or port id.
     *     @return true if session id is set.
     * @modelguid {3A442EBE-1477-48C6-A881-6236E1DAC5AD}
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
     * @modelguid {26BAD786-514A-4045-A8D5-06F651222F7D}
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
     * 	   Start the data aquisition service.
     * 	   @modelguid {BD908263-0272-4EFA-BE5B-5559B1D7849D}
     */
    public void start() {
        while (true) {
            try {
                URL aURL = new URL(AppProperties.getInstance().getProperty("MCHOST"));
                group = InetAddress.getByName(aURL.getHost());
                theSocket = new MulticastSocket(aURL.getPort());
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
                e.printStackTrace();
                if (theSocket != null)
                    theSocket.close();
            }
        }
    }

    /**
     * 	   Stop the data aquisition service.re
     * 	   @modelguid {49938316-0866-4FAF-8563-B99BBBD2A253}
     */
    public void stop() {
        finalize();
    }

    /**
     * 	Runs the thread the that this class may execute under.
     * 	   @modelguid {558B95B7-710A-4F39-AB21-321C92C7ACB4}
     */
    public void run() {
        start();
    }

    /** Push events into the current context 
     * @modelguid {1223D62C-493D-41DC-AB3A-5C6107503CC0}
     */
    private void push(byte[] buffer) {
        // Print out the event spec
        ByteArrayInputStream inStream = new ByteArrayInputStream(buffer);
        IEvent returnVal = null;
        try {
            //ZipInputStream zipIStream = new ZipInputStream(inStream);
            //zipIStream.getNextEntry();
            ObjectInputStream oStream = new ObjectInputStream(inStream);
            //String hash = oStream.readUTF();
            returnVal = (IEvent)oStream.readObject();
            //LogUtilities.contextMessage(hash);
            //zipIStream.closeEntry();
            ContextManager.currentContext().postEvent(returnVal);
            inStream.close();
        } catch (IOException y) {
            y.printStackTrace();
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }

	/** @modelguid {66329CA3-03DD-4437-A830-3E7F2110CBBF} */
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

	/** @modelguid {5C4BD430-E11F-425A-87E0-AC97EA367A37} */
    int port = 8900;
	/** @modelguid {92A5438B-DDFF-4FC0-BFBB-30BB1821000F} */
    InetAddress group = null;
	/** @modelguid {12BC377F-1F4F-45CD-9A7B-0A633A01D3F2} */
    MulticastSocket theSocket = null;
	/** @modelguid {7B9C9D43-9184-49F9-A226-67200B60D3AA} */
    DatagramPacket thePacket = null;
}
