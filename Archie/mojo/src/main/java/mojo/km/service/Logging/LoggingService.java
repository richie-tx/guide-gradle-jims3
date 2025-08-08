//Source file: Z:/java.vob/MessagingLayer/MessageProcessing/VTService.java

package mojo.km.service.Logging;

import mojo.km.config.AppProperties;
import mojo.km.service.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

/**
 *    Responsible for receiving log event from various Server side services and recording them in a log file.  The current
 * implementation will write a log file of the form ULOGyyyyMMdd.log.  It will
 * roll the date upon the first hour after midnight, if left running.
 *@author Eric A Amundson
 * @modelguid {348CE2FE-A3F4-49E4-BD19-F4F57A79314D}
 */
public class LoggingService extends SessionService {
    /** Default constructor.  Initializes file output stream and starts rollover checking 
     * @modelguid {F2F7CD67-CEEB-47F1-ACDA-2F26A1EE91C9}
     */
    public LoggingService() {
        checkOutputStream();
        Thread rolloverThread = new Thread(
            new CheckForRollover());
        rolloverThread.start();
    }

    /**
     * 	Set session URL.
     * 	@param id or port id.
     *     @return true if sucessful
     * @modelguid {AF94C27C-D5A4-45C2-9850-44DA165FAC58}
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
     * @modelguid {773FE35C-B60D-4145-AA32-8603F758B5A0}
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
     * 	   @modelguid {588FEFB4-ABF9-43CE-A8B2-2CEB349447C2}
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
     * 	   @modelguid {AA0DA523-4645-48ED-9A5D-4206E2657ADD}
     */
    public void stop() {
    }

    /**
     * 	Starts the execution of the event loop.
     * 	   @modelguid {6AA4FE1B-AF9D-418A-BD37-20135EF80632}
     */
    public void run() {
        start();
    }

    /** Push events into the current context 
     * @modelguid {9256D61F-9CCF-42E6-9F7D-7B815823BDDC}
     */
    private void push(byte[] buffer) {
        // Print out the event spec
        try {
            dailyOutputStream.write(buffer);
            dailyOutputStream.println();
        } catch (IOException e) { }
    }

    /** Cleanup communication streams. 
     * @modelguid {FE0E9DDE-D04C-4ECF-B112-3F96B68C8124}
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
     * @modelguid {B5B28B75-846C-486D-A4B1-59D5B4BD8347}
     */
    private synchronized void checkOutputStream() {
        try {
            String dateStr = dFormat.format(
                new Date());
            if (!mDateStr.equals(dateStr)) {
                if (dailyOutputStream != null)
                    dailyOutputStream.close();
                FileOutputStream fStream = new FileOutputStream("ULOG" + dateStr + ".log", true);
                dailyOutputStream = new PrintStream( fStream );
                mDateStr = dateStr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Helper class enables an internal thread to check for and create a new log file upon the date moving to the next day. 
     * @modelguid {72EF6241-EA49-47B6-808D-4EB0CA820A46}
     */
    class CheckForRollover implements Runnable {
		/** @modelguid {B062A7FE-80F5-423E-9BF4-5972227C407F} */
        public void run() {
            while (true) {
                try {
                    Thread.sleep(3600000);
                    checkOutputStream();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /** Port listening on for log events. 
     * @modelguid {024C2903-ADC8-4538-A513-39D8211D3318}
     */
    int port = 0;

    /** Data gram socket used by log service. 
     * @modelguid {1679C3E0-E536-496E-9AF5-08A56F3E9117}
     */
    DatagramSocket theSocket = null;

    /** Not used. 
     * @modelguid {CC89065B-50B9-4A3E-BE3D-81DE63E9C7CB}
     */
    DatagramSocket theAckSocket = null;

    /** Datagram packet receive by log stream. 
     * @modelguid {C07267AC-4067-4521-A367-3ACE8CB0383A}
     */
    DatagramPacket thePacket = null;

    /** Current output stream for logging.  Reference the current dated log file. 
     * @modelguid {FE96CBCE-4B8E-41CD-865A-E37CB9CBD3BF}
     */
    private PrintStream dailyOutputStream = null;

    /** Date format. 
     * @modelguid {924E6BE6-430B-4A4A-ACC4-768EAB4A30D0}
     */
    SimpleDateFormat dFormat = new SimpleDateFormat("yyyyMMdd");

    /** Current date string. 
     * @modelguid {D2A4F5C2-2603-44A8-BCA5-BE08C0036874}
     */
    private String mDateStr = "";

	/** @modelguid {527532B0-167C-48B9-A879-720B72864093} */
    static private String hostURL = null;

    /**
     * This class runs as a java application.  Thus is a executable component.
     * This main instantiates a loggingService and starts it's event loop.
     *     @param args - none.
     * @modelguid {C9ECB71C-7291-43FD-9CA9-04DA0E51AB47}
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
