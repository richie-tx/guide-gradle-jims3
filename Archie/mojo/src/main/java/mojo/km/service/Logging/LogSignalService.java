//Source file: Z:/java.vob/SystemServiceLayer/Request/QueueStrategy.java

package mojo.km.service.Logging;

import java.io.*;
import java.net.*;

import mojo.km.config.AppProperties;

/**
 * Responsible for sending events to a Vitrial channel server. These events will then be posted to a vitria client.
 * @author Eric A Amundson
 * @modelguid {B15E4639-90C6-400D-98BB-D0D208B1CFB5}
 */
public class LogSignalService {
	/** @modelguid {7438A5DB-2FA6-484E-B42B-EA5C18EF72AE} */
    public LogSignalService() {
        try {
            URL aUrl = new URL(AppProperties.getInstance().getProperty("LOGGROUP"));
            mCastIP = aUrl.getHost();
            port = aUrl.getPort();
            group = InetAddress.getByName(mCastIP);
            theSocket = new MulticastSocket(port);
            theSocket.joinGroup(group);
        } catch (IOException e) {
        }
    }

    /**
    Sends the log level to each running server.
    @param logLevel - mask for logging filter.
    @param serverName - the sernames that should accept the filter request.
    @modelguid {3B618598-AE9F-40ED-9DA5-88CEE80C51D8} */
    public void postLogLevel(short logLevel, String serverName) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            DataOutputStream oStream = new DataOutputStream(outStream);
            //oStream.writeUTF(event.hashKey());
            oStream.writeShort(logLevel);
            oStream.writeUTF(serverName);
            oStream.flush();
            byte[] buf = outStream.toByteArray();
            thePacket = new DatagramPacket(buf, buf.length, group, port);
            theSocket.send(thePacket);
            //QueueStrategy.queue( service, outStream.toByteArray());
            //ServerReponseStrategy.buildEvent(outStream.toByteArray());
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     *    Start signaling program
     *    @param args - <int log level>
     * @modelguid {5DB16F48-1FDA-4236-B0E8-B5FD08FE6862}
     */
    public static void main(String[] args) {
        LogSignalService signal = new LogSignalService();
        if (args.length != 2)
            System.out.println("Usage:  LogSignalService <int log level> <Server Name>.");
        try {
            short logLevel = Short.parseShort(args[0]);
            signal.postLogLevel(logLevel, args[1]);
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Usage:  LogSignalService <int log level>.");
            e.printStackTrace();
        }
    }

	/** @modelguid {A1C96531-E26B-4600-BF18-43A692773C5F} */
    int port = 8900;
	/** @modelguid {467553C9-CE56-4F3E-8FA1-F61F5028B3F4} */
    String mCastIP = "234.63.73.98";
	/** @modelguid {0DCEF94B-7CAE-4B53-9637-90D1A77CEC40} */
    MulticastSocket theSocket = null;
	/** @modelguid {2EA004FF-B696-4EE6-B992-0718CCD755FB} */
    DatagramPacket thePacket = null;
	/** @modelguid {C835DCCB-8994-40AA-BFA0-980526DE248F} */
    InetAddress group = null;
}
