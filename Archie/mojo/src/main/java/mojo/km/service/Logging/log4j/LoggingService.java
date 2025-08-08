//Source file: Z:/java.vob/MessagingLayer/MessageProcessing/VTService.java

package mojo.km.service.Logging.log4j;

import mojo.km.config.AppProperties;
import mojo.km.service.*;
import java.io.*;
import java.net.*;
import org.apache.log4j.chainsaw.ChainsawAppender;
import org.apache.log4j.spi.LoggingEvent;

/**
 *    Responsible for receiving log event from various Server side services and
 * recording them in a log file.  The current implementation will write to the log4j
 * LF5 user interface.
 *
 * an argument maybe passed to override the URL this host will listen on.
 * E.G.
 *  java mojo.km.Logging.log4j.LoggingService ftp://127.0.0.1:8080
 * 
 *@author Eric A Amundson
 * @modelguid {B107269B-16B3-4126-9A13-EC8F95EDC41D}
 */
public class LoggingService extends SessionService {
    /** Default constructor.  Initializes file output stream and starts
     * rollover checking 
     * @modelguid {A2692096-8B8E-45EC-8274-099B5D2FF8B4}
     */
    public LoggingService() {
        checkOutputStream();
    }

    /**
     * 	Set session URL.
     * 	@param id or port id.
     *     @return true if sucessful
     * @modelguid {DD00F281-C7D4-4186-9CAE-D90449007383}
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
     * @modelguid {599F48DD-4794-4961-B0DB-0A5C8319DADB}
     */
    public String getSessionID() {
        String retVal = "";
        try {
            retVal = InetAddress.getLocalHost().getHostAddress() + ":" + port;
        } catch (UnknownHostException e) {
        }
        return retVal;
    }

    /**
     * 	   Start the data aquisition thread.
     * 	   @modelguid {952EC765-103B-4167-82C7-510F4AF36AFC}
     */
    public void start() {
        try {
            URL aURL = new URL(hostURL);
            port = aURL.getPort();
            theSocket = new DatagramSocket(port);
            byte[] buff = new byte[500000];
            while (true) {
                thePacket = new DatagramPacket(buff, 50000);
                theSocket.receive(thePacket);
                int size = thePacket.getLength();
                byte[] dst = new byte[size];
                System.arraycopy(buff, 0, dst, 0, size);
                push(dst);
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (theSocket != null)
                theSocket.close();
        }
    }

    /**
     * 	   Stop the data aquisition thread.
     * 	   @modelguid {6C0F1E7F-F38C-47DC-AA25-7001F0C2C98D}
     */
    public void stop() {
    }

    /**
     * 	Starts the execution of the event loop.
     * 	   @modelguid {6B3C8883-F67B-4D4B-8C93-A4688DF5A09D}
     */
    public void run() {
        start();
    }

    /** Push events into the current context 
     * @modelguid {08AB6DA3-D3C9-458D-86CC-2A5F7435E283}
     */
    private void push(byte[] buffer) {
        ByteArrayInputStream inStream = new ByteArrayInputStream(buffer);
        LoggingEvent returnVal = null;
        try {
            //ZipInputStream zipIStream = new ZipInputStream(inStream);
            //zipIStream.getNextEntry();
            ObjectInputStream oStream = new ObjectInputStream(inStream);
            //String hash = oStream.readUTF();
            returnVal = (LoggingEvent)oStream.readObject();
            dailyOutputStream.doAppend(returnVal);
        } catch (Exception e) { }
    }

    /** Cleanup communication streams. 
     * @modelguid {38BF9D50-5884-4A35-B75F-B0BD5745E6C3}
     */
    protected void finalize() {
        try {
            super.finalize();
        } catch (Throwable t) {
            System.err.println(t.getMessage());
        }
        try {
            theSocket.close();
            dailyOutputStream.close();
        } catch (Exception e) {
        }
    }

    /** Check if output stream is appropriate for date. 
     * @modelguid {DE462A34-73A0-48B6-99F3-7432CF5DCEDE}
     */
    private synchronized void checkOutputStream() {
        try {
            if (dailyOutputStream == null) {
            	dailyOutputStream = new ChainsawAppender();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /** Port listening on for log events. 
     * @modelguid {B686C30A-BEB1-4247-8782-37ADA2E552D1}
     */
    int port = 0;

    /** Data gram socket used by log service. 
     * @modelguid {0F50B990-9B14-4050-A9F7-C6E0FBAF313A}
     */
    DatagramSocket theSocket = null;

    /** Not used. 
     * @modelguid {7CC5FD52-26A4-4A3A-BB24-11D24D6FB95E}
     */
    DatagramSocket theAckSocket = null;

    /** Datagram packet receive by log stream. 
     * @modelguid {7E04B578-0224-46CD-9501-6E56ED8D815B}
     */
    DatagramPacket thePacket = null;

    /** Current output stream for logging.  Reference the current dated log file. 
     * @modelguid {CFA5EAD1-2A84-4629-9F75-C246FC9B4004}
     */
    private ChainsawAppender dailyOutputStream = null;


	/** @modelguid {254E2685-B700-413C-9FC7-19848047C3EB} */
    static private String hostURL = null;

    /**
     * This class runs as a java application.  Thus is a executable component.
     * This main instantiates a loggingService and starts it's event loop.
     *     @param args - none.
     * @modelguid {DB129BB8-EC5E-4B4B-A7A3-9CD1E36CA1B5}
     */
    public static void main(String[] args) {
        if (args.length == 1) {
            hostURL = args[0];
        } else {
            hostURL = AppProperties.getInstance().getProperty("loggingHost");
        }
        LoggingService logging = new LoggingService();
        Thread aThread = new Thread(logging);
        aThread.start();
    }
}
